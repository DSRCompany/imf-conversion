/*
 * Copyright (C) 2016 Netflix, Inc.
 *
 *     This file is part of IMF Conversion Utility.
 *
 *     IMF Conversion Utility is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     IMF Conversion Utility is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with IMF Conversion Utility.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.netflix.imfutility.conversion.templateParameter;

import com.netflix.imfutility.ConversionException;
import com.netflix.imfutility.conversion.templateParameter.context.TemplateParameterContextProvider;
import com.netflix.imfutility.conversion.templateParameter.context.parameters.DestContextParameters;
import com.netflix.imfutility.conversion.templateParameter.context.parameters.DynamicContextParameters;
import com.netflix.imfutility.conversion.templateParameter.context.parameters.SegmentContextParameters;
import com.netflix.imfutility.conversion.templateParameter.context.parameters.SequenceContextParameters;
import com.netflix.imfutility.cpl.uuid.SegmentUUID;
import com.netflix.imfutility.cpl.uuid.SequenceUUID;
import com.netflix.imfutility.generated.conversion.DynamicParameterConcatType;
import com.netflix.imfutility.generated.conversion.SequenceType;
import com.netflix.imfutility.xsd.conversion.DestContextTypeMap;
import org.junit.Test;

import static com.netflix.imfutility.util.TemplateParameterContextCreator.createDefaultContextProvider;
import static com.netflix.imfutility.util.TemplateParameterContextCreator.createDefaultContextProviderWithDestContext;
import static com.netflix.imfutility.util.TemplateParameterContextCreator.fillCPLContext;
import static com.netflix.imfutility.util.TemplateParameterContextCreator.getResourceUuid;
import static com.netflix.imfutility.util.TemplateParameterContextCreator.getSegmentUuid;
import static com.netflix.imfutility.util.TemplateParameterContextCreator.getSequenceUuid;
import static com.netflix.imfutility.util.TemplateParameterContextCreator.getWorkingDir;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests whether each context can initialize or create parameters correctly.
 */
public class TemplateParameterInitializationTest {

    private static final int DEFAULT_PARAMETERS_COUNT = 2;

    @Test
    public void testDefaultDynamicParameters() throws Exception {
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();

        assertEquals(DEFAULT_PARAMETERS_COUNT,
                contextProvider.getDynamicContext().getAllParametersAsString().size());
        assertEquals(getWorkingDir().getAbsolutePath(),
                contextProvider.getDynamicContext().getParameterValueAsString(DynamicContextParameters.WORKING_DIR));
        assertEquals("errors.xml",
                contextProvider.getDynamicContext().getParameterValueAsString(DynamicContextParameters.OUTPUT_VALIDATION_FILE));
    }

    @Test
    public void testAddDynamicParameterSimple() throws Exception {
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();
        contextProvider.getDynamicContext()
                .addParameter("addDynamicSimple1", "addDynamicValue1", ContextInfo.EMPTY)
                .addParameter("addDynamicSimple2", "addDynamicValue2", ContextInfo.EMPTY);

        assertEquals(DEFAULT_PARAMETERS_COUNT + 2, contextProvider.getDynamicContext().getAllParametersAsString().size());
        assertEquals("addDynamicValue1", contextProvider.getDynamicContext().getParameterValueAsString("addDynamicSimple1"));
        assertEquals("addDynamicValue2", contextProvider.getDynamicContext().getParameterValueAsString("addDynamicSimple2"));
    }

    @Test
    public void testAppendDynamicParameterSimple() throws Exception {
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();

        contextProvider.getDynamicContext()
                .appendParameter("appendDynamicSimple1", "appendDynamicValue1_1", ContextInfo.EMPTY)
                .appendParameter("appendDynamicSimple1", "_2", ContextInfo.EMPTY)
                .appendParameter("appendDynamicSimple1", "_3", ContextInfo.EMPTY)

                .appendParameter("appendDynamicSimple2", "appendDynamicValue2_1", ContextInfo.EMPTY)
                .appendParameter("appendDynamicSimple2", "_2", ContextInfo.EMPTY)
                .appendParameter("appendDynamicSimple2", "_3", ContextInfo.EMPTY);

        assertEquals(DEFAULT_PARAMETERS_COUNT + 2, contextProvider.getDynamicContext().getAllParametersAsString().size());
        assertEquals("appendDynamicValue1_1_2_3", contextProvider.getDynamicContext().getParameterValueAsString("appendDynamicSimple1"));
        assertEquals("appendDynamicValue2_1_2_3", contextProvider.getDynamicContext().getParameterValueAsString("appendDynamicSimple2"));
    }

    @Test
    public void testAddDynamicParameterWithParamsInValue() throws Exception {
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();
        fillCPLContext(contextProvider, 2, 2, 2, 2);

        contextProvider.getDynamicContext().addParameter(
                "addDynamicWithParam1",
                "%{tmp.tmpParamSimple}",
                ContextInfo.EMPTY);
        contextProvider.getDynamicContext().addParameter(
                "addDynamicWithParam2",
                "%{segm.num}-%{seq.num}-%{seq.type}-%{resource.num}-%{resource.repeat}-%{tmp.tmpParamSimple}",
                new ContextInfoBuilder()
                        .setSegmentUuid(getSegmentUuid(0))
                        .setSequenceUuid(getSequenceUuid(1, SequenceType.AUDIO))
                        .setResourceUuid(getResourceUuid(0, 1, SequenceType.AUDIO, 1, 1))
                        .setSequenceType(SequenceType.AUDIO).build());

        assertEquals("tmpParamSimple", contextProvider.getDynamicContext().getParameterValueAsString("addDynamicWithParam1"));
        assertEquals("0-1-audio-3-1-tmpParamSimple", contextProvider.getDynamicContext().getParameterValueAsString("addDynamicWithParam2"));
    }

    @Test
    public void testAddDynamicParameterWithParamsInName() throws Exception {
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();
        fillCPLContext(contextProvider, 2, 2, 2, 2);

        contextProvider.getDynamicContext().addParameter(
                "name-%{tmp.tmpParamSimple}",
                "value1",
                ContextInfo.EMPTY);
        contextProvider.getDynamicContext().addParameter(
                "name-%{segm.num}-%{seq.num}-%{seq.type}-%{resource.num}-%{resource.repeat}-%{tmp.tmpParamSimple}",
                "value2",
                new ContextInfoBuilder()
                        .setSegmentUuid(getSegmentUuid(0))
                        .setSequenceUuid(getSequenceUuid(1, SequenceType.AUDIO))
                        .setResourceUuid(getResourceUuid(0, 1, SequenceType.AUDIO, 1, 1))
                        .setSequenceType(SequenceType.AUDIO).build());

        assertEquals("value1", contextProvider.getDynamicContext().getParameterValueAsString("name-tmpParamSimple"));
        assertEquals("value2", contextProvider.getDynamicContext().getParameterValueAsString("name-0-1-audio-3-1-tmpParamSimple"));
    }

    @Test
    public void testAddDynamicParameterWithParamsInNameAndValue() throws Exception {
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();
        fillCPLContext(contextProvider, 2, 2, 2, 2);

        contextProvider.getDynamicContext().addParameter(
                "name-%{tmp.tmpParamSimple}",
                "%{tmp.tmpParamSimple}",
                ContextInfo.EMPTY);
        contextProvider.getDynamicContext().addParameter(
                "name-%{segm.num}-%{seq.num}-%{seq.type}-%{resource.num}-%{resource.repeat}-%{tmp.tmpParamSimple}",
                "%{segm.num}-%{seq.num}-%{seq.type}-%{resource.num}-%{resource.repeat}-%{tmp.tmpParamSimple}",
                new ContextInfoBuilder()
                        .setSegmentUuid(getSegmentUuid(0))
                        .setSequenceUuid(getSequenceUuid(1, SequenceType.AUDIO))
                        .setResourceUuid(getResourceUuid(0, 1, SequenceType.AUDIO, 1, 1))
                        .setSequenceType(SequenceType.AUDIO).build());

        assertEquals("tmpParamSimple",
                contextProvider.getDynamicContext().getParameterValueAsString("name-tmpParamSimple"));
        assertEquals("0-1-audio-3-1-tmpParamSimple",
                contextProvider.getDynamicContext().getParameterValueAsString("name-0-1-audio-3-1-tmpParamSimple"));
    }

    @Test
    public void testAppendDynamicParameterWithParamsInValue() throws Exception {
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();
        fillCPLContext(contextProvider, 2, 2, 2);

        contextProvider.getDynamicContext().appendParameter("appendDynamicWithParam1", "%{tmp.tmpParamSimple}_1", ContextInfo.EMPTY);
        contextProvider.getDynamicContext().appendParameter("appendDynamicWithParam1", "_%{tmp.tmpParamSimple}_2", ContextInfo.EMPTY);

        contextProvider.getDynamicContext().appendParameter(
                "appendDynamicWithParam2",
                "%{segm.num}-%{seq.num}-%{seq.type}-%{resource.num}-%{tmp.tmpParamSimple}",
                new ContextInfoBuilder()
                        .setSegmentUuid(getSegmentUuid(0))
                        .setSequenceUuid(getSequenceUuid(0, SequenceType.AUDIO))
                        .setResourceUuid(getResourceUuid(0, 0, SequenceType.AUDIO, 0, 0))
                        .setSequenceType(SequenceType.AUDIO).build());

        contextProvider.getDynamicContext().appendParameter(
                "appendDynamicWithParam2",
                "_%{segm.num}-%{seq.num}-%{seq.type}-%{resource.num}-%{tmp.tmpParamSimple}",
                new ContextInfoBuilder()
                        .setSegmentUuid(getSegmentUuid(1))
                        .setSequenceUuid(getSequenceUuid(1, SequenceType.VIDEO))
                        .setResourceUuid(getResourceUuid(1, 1, SequenceType.VIDEO, 1, 0))
                        .setSequenceType(SequenceType.VIDEO).build());

        assertEquals(DEFAULT_PARAMETERS_COUNT + 2, contextProvider.getDynamicContext().getAllParametersAsString().size());
        assertEquals("tmpParamSimple_1_tmpParamSimple_2",
                contextProvider.getDynamicContext().getParameterValueAsString("appendDynamicWithParam1"));
        assertEquals("0-0-audio-0-tmpParamSimple_1-1-video-1-tmpParamSimple",
                contextProvider.getDynamicContext().getParameterValueAsString("appendDynamicWithParam2"));
    }

    @Test
    public void testAppendDynamicParameterWithParamsInName() throws Exception {
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();
        fillCPLContext(contextProvider, 2, 2, 2);

        contextProvider.getDynamicContext().appendParameter("%{tmp.tmpParamSimple}", "appendDynamicWithParam1", ContextInfo.EMPTY);
        contextProvider.getDynamicContext().appendParameter("%{tmp.tmpParamSimple}", "_appendDynamicWithParam1", ContextInfo.EMPTY);

        contextProvider.getDynamicContext().appendParameter(
                "%{segm.num}-%{seq.num}-%{seq.type}-%{resource.num}-%{tmp.tmpParamSimple}",
                "appendDynamicWithParam2",
                new ContextInfoBuilder()
                        .setSegmentUuid(getSegmentUuid(0))
                        .setSequenceUuid(getSequenceUuid(0, SequenceType.AUDIO))
                        .setResourceUuid(getResourceUuid(0, 0, SequenceType.AUDIO, 0, 0))
                        .setSequenceType(SequenceType.AUDIO).build());

        contextProvider.getDynamicContext().appendParameter(
                "%{segm.num}-%{seq.num}-%{seq.type}-%{resource.num}-%{tmp.tmpParamSimple}",
                "_appendDynamicWithParam2",
                new ContextInfoBuilder()
                        .setSegmentUuid(getSegmentUuid(0))
                        .setSequenceUuid(getSequenceUuid(0, SequenceType.AUDIO))
                        .setResourceUuid(getResourceUuid(0, 0, SequenceType.AUDIO, 0, 0))
                        .setSequenceType(SequenceType.AUDIO).build());

        assertEquals(DEFAULT_PARAMETERS_COUNT + 2, contextProvider.getDynamicContext().getAllParametersAsString().size());
        assertEquals("appendDynamicWithParam1_appendDynamicWithParam1",
                contextProvider.getDynamicContext().getParameterValueAsString("tmpParamSimple"));
        assertEquals("appendDynamicWithParam2_appendDynamicWithParam2",
                contextProvider.getDynamicContext().getParameterValueAsString("0-0-audio-0-tmpParamSimple"));
    }

    @Test
    public void testAppendDynamicParameterWithParamsInNameAndValue() throws Exception {
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();
        fillCPLContext(contextProvider, 2, 2, 2);

        contextProvider.getDynamicContext().appendParameter(
                "name-%{segm.num}-%{seq.num}-%{seq.type}-%{resource.num}-%{tmp.tmpParamSimple}",
                "%{segm.num}-%{seq.num}-%{seq.type}-%{resource.num}-%{tmp.tmpParamSimple}",
                new ContextInfoBuilder()
                        .setSegmentUuid(getSegmentUuid(0))
                        .setSequenceUuid(getSequenceUuid(0, SequenceType.AUDIO))
                        .setResourceUuid(getResourceUuid(0, 0, SequenceType.AUDIO, 0, 0))
                        .setSequenceType(SequenceType.AUDIO).build());

        contextProvider.getDynamicContext().appendParameter(
                "name-%{segm.num}-%{seq.num}-%{seq.type}-%{resource.num}-%{tmp.tmpParamSimple}",
                "_%{segm.num}-%{seq.num}-%{seq.type}-%{resource.num}-%{tmp.tmpParamSimple}",
                new ContextInfoBuilder()
                        .setSegmentUuid(getSegmentUuid(0))
                        .setSequenceUuid(getSequenceUuid(0, SequenceType.AUDIO))
                        .setResourceUuid(getResourceUuid(0, 0, SequenceType.AUDIO, 0, 0))
                        .setSequenceType(SequenceType.AUDIO).build());

        assertEquals(DEFAULT_PARAMETERS_COUNT + 1, contextProvider.getDynamicContext().getAllParametersAsString().size());
        assertEquals("0-0-audio-0-tmpParamSimple_0-0-audio-0-tmpParamSimple",
                contextProvider.getDynamicContext().getParameterValueAsString("name-0-0-audio-0-tmpParamSimple"));
    }

    @Test
    public void testAddDynamicParameterDeleteOnExit() throws Exception {
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();
        contextProvider.getDynamicContext()
                .addParameter("addDynamicSimple1", "addDynamicValue1", false, ContextInfo.EMPTY)
                .addParameter("addDynamicSimple2", "addDynamicValue2", true, ContextInfo.EMPTY);

        assertFalse(contextProvider.getDynamicContext().getParameterValue("addDynamicSimple1").isDeleteOnExit());
        assertTrue(contextProvider.getDynamicContext().getParameterValue("addDynamicSimple2").isDeleteOnExit());
    }

    @Test
    public void testAddDynamicParameterFromConversionXml() throws Exception {
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();

        DynamicParameterConcatType dynamicParameter1 = new DynamicParameterConcatType();
        dynamicParameter1.setName("addDynamicSimple1");
        dynamicParameter1.setValue("addDynamicValue1");
        dynamicParameter1.setDeleteOnExit(false);

        DynamicParameterConcatType dynamicParameter2 = new DynamicParameterConcatType();
        dynamicParameter2.setName("addDynamicSimple2");
        dynamicParameter2.setValue("addDynamicValue2");
        dynamicParameter2.setDeleteOnExit(true);


        contextProvider.getDynamicContext().addParameter(dynamicParameter1, ContextInfo.EMPTY, false);
        contextProvider.getDynamicContext().addParameter(dynamicParameter2, ContextInfo.EMPTY, false);

        assertEquals(DEFAULT_PARAMETERS_COUNT + 2, contextProvider.getDynamicContext().getAllParametersAsString().size());
        assertEquals("addDynamicValue1", contextProvider.getDynamicContext().getParameterValueAsString("addDynamicSimple1"));
        assertEquals("addDynamicValue2", contextProvider.getDynamicContext().getParameterValueAsString("addDynamicSimple2"));
        assertFalse(contextProvider.getDynamicContext().getParameterValue("addDynamicSimple1").isDeleteOnExit());
        assertTrue(contextProvider.getDynamicContext().getParameterValue("addDynamicSimple2").isDeleteOnExit());
    }

    @Test
    public void testAppendDynamicParameterFromConversionXml() throws Exception {
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();

        DynamicParameterConcatType dynamicParameter1 = new DynamicParameterConcatType();
        dynamicParameter1.setName("addDynamicSimple1");
        dynamicParameter1.setValue("addDynamicValue1");
        dynamicParameter1.setConcat(true);
        dynamicParameter1.setDeleteOnExit(false);

        DynamicParameterConcatType dynamicParameter2 = new DynamicParameterConcatType();
        dynamicParameter2.setName("addDynamicSimple1");
        dynamicParameter2.setValue("addDynamicValue1_1");
        dynamicParameter2.setConcat(false);
        dynamicParameter2.setDeleteOnExit(true);

        DynamicParameterConcatType dynamicParameter3 = new DynamicParameterConcatType();
        dynamicParameter3.setName("appendDynamicSimple3");
        dynamicParameter3.setValue("addDynamicValue3");
        dynamicParameter3.setConcat(false);
        dynamicParameter3.setDeleteOnExit(true);

        DynamicParameterConcatType dynamicParameter4 = new DynamicParameterConcatType();
        dynamicParameter4.setName("appendDynamicSimple3");
        dynamicParameter4.setValue("addDynamicValue4");
        dynamicParameter4.setConcat(true);
        dynamicParameter4.setDeleteOnExit(true);

        DynamicParameterConcatType dynamicParameter3_whitespace = new DynamicParameterConcatType();
        dynamicParameter3_whitespace.setName("appendDynamicSimple3_ws");
        dynamicParameter3_whitespace.setValue("addDynamicValue3");
        dynamicParameter3_whitespace.setConcat(false);
        dynamicParameter3_whitespace.setDeleteOnExit(false);
        dynamicParameter3_whitespace.setConcatWhitespace(true);

        DynamicParameterConcatType dynamicParameter4_whitespace = new DynamicParameterConcatType();
        dynamicParameter4_whitespace.setName("appendDynamicSimple3_ws");
        dynamicParameter4_whitespace.setValue("addDynamicValue4");
        dynamicParameter4_whitespace.setConcat(true);
        dynamicParameter4_whitespace.setDeleteOnExit(false);
        dynamicParameter4_whitespace.setConcatWhitespace(true);

        contextProvider.getDynamicContext().addParameter(dynamicParameter1, ContextInfo.EMPTY, false);
        contextProvider.getDynamicContext().addParameter(dynamicParameter2, ContextInfo.EMPTY, false);
        contextProvider.getDynamicContext().addParameter(dynamicParameter3, ContextInfo.EMPTY, false);
        contextProvider.getDynamicContext().addParameter(dynamicParameter4, ContextInfo.EMPTY, false);
        contextProvider.getDynamicContext().addParameter(dynamicParameter3_whitespace, ContextInfo.EMPTY, false);
        contextProvider.getDynamicContext().addParameter(dynamicParameter4_whitespace, ContextInfo.EMPTY, false);

        assertEquals(DEFAULT_PARAMETERS_COUNT + 3, contextProvider.getDynamicContext().getAllParametersAsString().size());
        assertEquals("addDynamicValue1_1", contextProvider.getDynamicContext().getParameterValueAsString("addDynamicSimple1"));
        assertEquals("addDynamicValue3addDynamicValue4", contextProvider.getDynamicContext()
                .getParameterValueAsString("appendDynamicSimple3"));
        assertEquals("addDynamicValue3 addDynamicValue4", contextProvider.getDynamicContext()
                .getParameterValueAsString("appendDynamicSimple3_ws"));

        assertTrue(contextProvider.getDynamicContext().getParameterValue("addDynamicSimple1").isDeleteOnExit());
        assertTrue(contextProvider.getDynamicContext().getParameterValue("appendDynamicSimple3").isDeleteOnExit());
        assertFalse(contextProvider.getDynamicContext().getParameterValue("appendDynamicSimple3_ws").isDeleteOnExit());
    }

    @Test
    public void dynamicParameterAddOperationCanAddition() throws Exception {
        DynamicParameterConcatType param = new DynamicParameterConcatType();
        param.setName("param");
        param.setValue("10");
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();

        contextProvider.getDynamicContext().addParameter(param, ContextInfo.EMPTY, false);

        param.setAdd("10");
        contextProvider.getDynamicContext().addParameter(param, ContextInfo.EMPTY, false);

        assertEquals("20", contextProvider.getDynamicContext().getParameterValueAsString(param.getName()));
    }

    @Test
    public void dynamicParameterAddOperationCanAdditionWhenParameterIsAbsent() throws Exception {
        DynamicParameterConcatType param = new DynamicParameterConcatType();
        param.setName("param");
        param.setValue("10");
        param.setAdd("10");
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();

        contextProvider.getDynamicContext().addParameter(param, ContextInfo.EMPTY, false);

        assertEquals("10", contextProvider.getDynamicContext().getParameterValueAsString(param.getName()));
    }

    @Test
    public void dynamicParameterAddOperationCanAdditionWhenAddIsParameter() throws Exception {
        DynamicParameterConcatType param = new DynamicParameterConcatType();
        param.setName("param");
        param.setValue("10");
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();

        contextProvider.getDynamicContext().addParameter(param, ContextInfo.EMPTY, false);
        contextProvider.getDynamicContext().addParameter("add", "3");

        param.setAdd("%{dynamic.add}");
        contextProvider.getDynamicContext().addParameter(param, ContextInfo.EMPTY, false);

        assertEquals("13", contextProvider.getDynamicContext().getParameterValueAsString(param.getName()));
    }

    @Test(expected = ConversionException.class)
    public void dynamicParameterAddOperationFailsWhenParamValueIsNotInt() throws Exception {
        DynamicParameterConcatType param = new DynamicParameterConcatType();
        param.setName("param");
        param.setValue("test");
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();

        contextProvider.getDynamicContext().addParameter(param, ContextInfo.EMPTY, false);

        param.setAdd("10");
        contextProvider.getDynamicContext().addParameter(param, ContextInfo.EMPTY, false);
    }

    @Test(expected = ConversionException.class)
    public void dynamicParameterAddOperationFailsWhenAdditionValueIsNotInt() throws Exception {
        DynamicParameterConcatType param = new DynamicParameterConcatType();
        param.setName("param");
        param.setValue("10");
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();

        contextProvider.getDynamicContext().addParameter(param, ContextInfo.EMPTY, false);

        param.setAdd("test");
        contextProvider.getDynamicContext().addParameter(param, ContextInfo.EMPTY, false);
    }

    @Test
    public void testAddDestParameter() throws Exception {
        TemplateParameterContextProvider contextProvider = createDefaultContextProviderWithDestContext(
                new DestContextTypeMap());
        contextProvider.getDestContext()
                .addParameter(DestContextParameters.ASPECT_RATIO, "16/9")
                .addParameter("dest1", "dest1Value")
                .addParameter("dest2", "dest2Value");

        assertEquals(3, contextProvider.getDestContext().getAllParameters().size());
        assertEquals("16/9", contextProvider.getDestContext().getParameterValue(DestContextParameters.ASPECT_RATIO));
        assertEquals("dest1Value", contextProvider.getDestContext().getParameterValue("dest1"));
        assertEquals("dest2Value", contextProvider.getDestContext().getParameterValue("dest2"));
    }

    @Test
    public void testInitSegmentsOrder() throws Exception {
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();
        contextProvider.getSegmentContext().initSegment(SegmentUUID.create("urn:uuid:3333"));
        contextProvider.getSegmentContext().initSegment(SegmentUUID.create("urn:uuid:1111"));
        contextProvider.getSegmentContext().initSegment(SegmentUUID.create("urn:uuid:2222"));

        assertEquals(3, contextProvider.getSegmentContext().getSegmentsNum());
        assertArrayEquals(
                new SegmentUUID[]{
                        SegmentUUID.create("urn:uuid:3333"),
                        SegmentUUID.create("urn:uuid:1111"),
                        SegmentUUID.create("urn:uuid:2222")},
                contextProvider.getSegmentContext().getUuids().toArray());
    }

    @Test
    public void testInitSegmentsNoDuplicate() throws Exception {
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();
        contextProvider.getSegmentContext().initSegment(SegmentUUID.create("urn:uuid:3333"));
        contextProvider.getSegmentContext().initSegment(SegmentUUID.create("urn:uuid:1111"));
        contextProvider.getSegmentContext().initSegment(SegmentUUID.create("urn:uuid:2222"));
        contextProvider.getSegmentContext().initSegment(SegmentUUID.create("urn:uuid:3333"));
        contextProvider.getSegmentContext().initSegment(SegmentUUID.create("urn:uuid:1111"));

        assertEquals(3, contextProvider.getSegmentContext().getSegmentsNum());
        assertArrayEquals(
                new SegmentUUID[]{
                        SegmentUUID.create("urn:uuid:3333"),
                        SegmentUUID.create("urn:uuid:1111"),
                        SegmentUUID.create("urn:uuid:2222")},
                contextProvider.getSegmentContext().getUuids().toArray());
    }

    @Test
    public void testInitDefaultSegmentParameters() throws Exception {
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();
        contextProvider.getSegmentContext().initSegment(
                SegmentUUID.create("urn:uuid:3333"));
        contextProvider.getSegmentContext().initSegment(
                SegmentUUID.create("urn:uuid:1111"));
        contextProvider.getSegmentContext().initSegment(
                SegmentUUID.create("urn:uuid:2222"));

        assertDefaultSegmentParameters(contextProvider, 0, SegmentUUID.create("urn:uuid:3333"));
        assertDefaultSegmentParameters(contextProvider, 1, SegmentUUID.create("urn:uuid:1111"));
        assertDefaultSegmentParameters(contextProvider, 2, SegmentUUID.create("urn:uuid:2222"));
    }

    private void assertDefaultSegmentParameters(TemplateParameterContextProvider contextProvider, int segmNum,
                                                SegmentUUID segmUuid) {
        assertEquals(
                String.valueOf(segmNum),
                contextProvider.getSegmentContext().resolveTemplateParameter(
                        new TemplateParameter("%{segm.num}"),
                        new ContextInfoBuilder().setSegmentUuid(segmUuid).build()));
        assertEquals(
                segmUuid.getUuid(),
                contextProvider.getSegmentContext().resolveTemplateParameter(
                        new TemplateParameter("%{segm.uuid}"),
                        new ContextInfoBuilder().setSegmentUuid(segmUuid).build()));
    }

    @Test
    public void testAddSegmentParametersInitsSegment() throws Exception {
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();
        contextProvider.getSegmentContext().addSegmentParameter(
                SegmentUUID.create("urn:uuid:3333"), SegmentContextParameters.NUM, "5");

        assertEquals(1, contextProvider.getSegmentContext().getSegmentsNum());
        assertArrayEquals(
                new SegmentUUID[]{SegmentUUID.create("urn:uuid:3333")},
                contextProvider.getSegmentContext().getUuids().toArray());

        assertDefaultSegmentParameters(contextProvider, 5, SegmentUUID.create("urn:uuid:3333"));
    }

    @Test
    public void testInitSequenceOrder() throws Exception {
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();
        contextProvider.getSequenceContext().initSequence(SequenceType.VIDEO, SequenceUUID.create("urn:uuid:3333"));
        contextProvider.getSequenceContext().initSequence(SequenceType.VIDEO, SequenceUUID.create("urn:uuid:1111"));
        contextProvider.getSequenceContext().initSequence(SequenceType.VIDEO, SequenceUUID.create("urn:uuid:2222"));

        contextProvider.getSequenceContext().initSequence(SequenceType.AUDIO, SequenceUUID.create("urn:uuid:2222"));
        contextProvider.getSequenceContext().initSequence(SequenceType.AUDIO, SequenceUUID.create("urn:uuid:3333"));
        contextProvider.getSequenceContext().initSequence(SequenceType.AUDIO, SequenceUUID.create("urn:uuid:1111"));
        contextProvider.getSequenceContext().initSequence(SequenceType.AUDIO, SequenceUUID.create("urn:uuid:4444"));

        assertEquals(3, contextProvider.getSequenceContext().getSequenceCount(SequenceType.VIDEO));
        assertArrayEquals(
                new SequenceUUID[]{
                        SequenceUUID.create("urn:uuid:3333"),
                        SequenceUUID.create("urn:uuid:1111"),
                        SequenceUUID.create("urn:uuid:2222")},
                contextProvider.getSequenceContext().getUuids(SequenceType.VIDEO).toArray());

        assertEquals(4, contextProvider.getSequenceContext().getSequenceCount(SequenceType.AUDIO));
        assertArrayEquals(
                new SequenceUUID[]{
                        SequenceUUID.create("urn:uuid:2222"),
                        SequenceUUID.create("urn:uuid:3333"),
                        SequenceUUID.create("urn:uuid:1111"),
                        SequenceUUID.create("urn:uuid:4444")},
                contextProvider.getSequenceContext().getUuids(SequenceType.AUDIO).toArray());
    }

    @Test
    public void testInitSequenceNoDuplicate() throws Exception {
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();
        contextProvider.getSequenceContext().initSequence(SequenceType.VIDEO, SequenceUUID.create("urn:uuid:3333"));
        contextProvider.getSequenceContext().initSequence(SequenceType.VIDEO, SequenceUUID.create("urn:uuid:1111"));
        contextProvider.getSequenceContext().initSequence(SequenceType.VIDEO, SequenceUUID.create("urn:uuid:2222"));
        contextProvider.getSequenceContext().initSequence(SequenceType.VIDEO, SequenceUUID.create("urn:uuid:3333"));
        contextProvider.getSequenceContext().initSequence(SequenceType.VIDEO, SequenceUUID.create("urn:uuid:2222"));

        contextProvider.getSequenceContext().initSequence(SequenceType.AUDIO, SequenceUUID.create("urn:uuid:2222"));
        contextProvider.getSequenceContext().initSequence(SequenceType.AUDIO, SequenceUUID.create("urn:uuid:3333"));
        contextProvider.getSequenceContext().initSequence(SequenceType.AUDIO, SequenceUUID.create("urn:uuid:3333"));
        contextProvider.getSequenceContext().initSequence(SequenceType.AUDIO, SequenceUUID.create("urn:uuid:3333"));
        contextProvider.getSequenceContext().initSequence(SequenceType.AUDIO, SequenceUUID.create("urn:uuid:1111"));
        contextProvider.getSequenceContext().initSequence(SequenceType.AUDIO, SequenceUUID.create("urn:uuid:4444"));
        contextProvider.getSequenceContext().initSequence(SequenceType.AUDIO, SequenceUUID.create("urn:uuid:2222"));

        assertEquals(3, contextProvider.getSequenceContext().getSequenceCount(SequenceType.VIDEO));
        assertArrayEquals(
                new SequenceUUID[]{
                        SequenceUUID.create("urn:uuid:3333"),
                        SequenceUUID.create("urn:uuid:1111"),
                        SequenceUUID.create("urn:uuid:2222")},
                contextProvider.getSequenceContext().getUuids(SequenceType.VIDEO).toArray());

        assertEquals(4, contextProvider.getSequenceContext().getSequenceCount(SequenceType.AUDIO));
        assertArrayEquals(
                new SequenceUUID[]{
                        SequenceUUID.create("urn:uuid:2222"),
                        SequenceUUID.create("urn:uuid:3333"),
                        SequenceUUID.create("urn:uuid:1111"),
                        SequenceUUID.create("urn:uuid:4444")},
                contextProvider.getSequenceContext().getUuids(SequenceType.AUDIO).toArray());
    }

    @Test
    public void testInitDefaultSequenceParameters() throws Exception {
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();
        contextProvider.getSequenceContext().initSequence(SequenceType.VIDEO, SequenceUUID.create("urn:uuid:3333"));
        contextProvider.getSequenceContext().initSequence(SequenceType.VIDEO, SequenceUUID.create("urn:uuid:1111"));

        contextProvider.getSequenceContext().initSequence(SequenceType.AUDIO, SequenceUUID.create("urn:uuid:2222"));
        contextProvider.getSequenceContext().initSequence(SequenceType.AUDIO, SequenceUUID.create("urn:uuid:3333"));

        assertDefaultSequenceParameters(contextProvider, 0, SequenceUUID.create("urn:uuid:3333"), SequenceType.VIDEO);
        assertDefaultSequenceParameters(contextProvider, 1, SequenceUUID.create("urn:uuid:1111"), SequenceType.VIDEO);
        assertDefaultSequenceParameters(contextProvider, 0, SequenceUUID.create("urn:uuid:2222"), SequenceType.AUDIO);
        assertDefaultSequenceParameters(contextProvider, 1, SequenceUUID.create("urn:uuid:3333"), SequenceType.AUDIO);
    }

    private void assertDefaultSequenceParameters(TemplateParameterContextProvider contextProvider, int seqNum,
                                                 SequenceUUID seqUuid, SequenceType seqType) {
        assertEquals(
                String.valueOf(seqNum),
                contextProvider.getSequenceContext().resolveTemplateParameter(
                        new TemplateParameter("%{seq.num}"),
                        new ContextInfoBuilder().setSequenceUuid(seqUuid).setSequenceType(seqType).build()));
        assertEquals(
                seqUuid.getUuid(),
                contextProvider.getSequenceContext().resolveTemplateParameter(
                        new TemplateParameter("%{seq.uuid}"),
                        new ContextInfoBuilder().setSequenceUuid(seqUuid).setSequenceType(seqType).build()));
        assertEquals(
                seqType.value(),
                contextProvider.getSequenceContext().resolveTemplateParameter(
                        new TemplateParameter("%{seq.type}"),
                        new ContextInfoBuilder().setSequenceUuid(seqUuid).setSequenceType(seqType).build()));
    }

    @Test
    public void testAddSequenceParameterInitsSequence() throws Exception {
        TemplateParameterContextProvider contextProvider = createDefaultContextProvider();
        contextProvider.getSequenceContext().addSequenceParameter(SequenceType.VIDEO, SequenceUUID.create("urn:uuid:3333"),
                SequenceContextParameters.NUM, "5");

        assertEquals(1, contextProvider.getSequenceContext().getSequenceCount(SequenceType.VIDEO));
        assertArrayEquals(
                new SequenceUUID[]{SequenceUUID.create("urn:uuid:3333")},
                contextProvider.getSequenceContext().getUuids(SequenceType.VIDEO).toArray());

        assertDefaultSequenceParameters(contextProvider, 5, SequenceUUID.create("urn:uuid:3333"), SequenceType.VIDEO);
    }

}
