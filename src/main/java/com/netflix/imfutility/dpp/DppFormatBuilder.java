package com.netflix.imfutility.dpp;

import com.netflix.imfutility.AbstractFormatBuilder;
import com.netflix.imfutility.Format;
import com.netflix.imfutility.conversion.templateParameter.TemplateParameterContext;
import com.netflix.imfutility.conversion.templateParameter.context.DynamicTemplateParameterContext;
import com.netflix.imfutility.conversion.templateParameter.context.segment.SegmentContextParameters;
import com.netflix.imfutility.conversion.templateParameter.context.segment.SegmentTemplateParameterContext;
import com.netflix.imfutility.xsd.conversion.SegmentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Alexander on 4/22/2016.
 */
public class DppFormatBuilder extends AbstractFormatBuilder {

    private final Logger logger = LoggerFactory.getLogger(AbstractFormatBuilder.class);

    public DppFormatBuilder() {
        super(Format.DPP);
    }

    @Override
    protected void fillDynamicContext() {
        logger.info("Creating Dynamic context...");

        // FIXME

        DynamicTemplateParameterContext dynamicContext = contextProvider.getDynamicContext();
        dynamicContext.addParameter("outputMxf", "output.mxf");
        dynamicContext.addParameter("audioChannels", "2");

        logger.info("Created Dynamic context: OK\n");
    }

    @Override
    protected void fillSegmentContext() {
        logger.info("Creating Segment context...");

        //FIXME

        SegmentTemplateParameterContext segmentContext = (SegmentTemplateParameterContext) contextProvider.getContext(TemplateParameterContext.SEGMENT);

        String pathToMedia = "G:\\Netflix\\test\\encode\\Aladdin_trailer_ATT.ts";

        segmentContext.addSegmentParameter(0, SegmentType.VIDEO, SegmentContextParameters.ESSENCE, pathToMedia);
        segmentContext.addSegmentParameter(0, SegmentType.VIDEO, SegmentContextParameters.START_TIME, "10");
        segmentContext.addSegmentParameter(0, SegmentType.VIDEO, SegmentContextParameters.DURATION, "5");

        segmentContext.addSegmentParameter(0, SegmentType.AUDIO, SegmentContextParameters.ESSENCE, pathToMedia);
        segmentContext.addSegmentParameter(0, SegmentType.AUDIO, SegmentContextParameters.START_TIME, "10");
        segmentContext.addSegmentParameter(0, SegmentType.AUDIO, SegmentContextParameters.DURATION, "5");

        segmentContext.addSegmentParameter(1, SegmentType.VIDEO, SegmentContextParameters.ESSENCE, pathToMedia);
        segmentContext.addSegmentParameter(1, SegmentType.VIDEO, SegmentContextParameters.START_TIME, "25");
        segmentContext.addSegmentParameter(1, SegmentType.VIDEO, SegmentContextParameters.DURATION, "5");

        segmentContext.addSegmentParameter(1, SegmentType.AUDIO, SegmentContextParameters.ESSENCE, pathToMedia);
        segmentContext.addSegmentParameter(1, SegmentType.AUDIO, SegmentContextParameters.START_TIME, "25");
        segmentContext.addSegmentParameter(1, SegmentType.AUDIO, SegmentContextParameters.DURATION, "5");

        segmentContext.addSegmentParameter(2, SegmentType.VIDEO, SegmentContextParameters.ESSENCE, pathToMedia);
        segmentContext.addSegmentParameter(2, SegmentType.VIDEO, SegmentContextParameters.START_TIME, "35");
        segmentContext.addSegmentParameter(2, SegmentType.VIDEO, SegmentContextParameters.DURATION, "5");

        segmentContext.addSegmentParameter(2, SegmentType.AUDIO, SegmentContextParameters.ESSENCE, pathToMedia);
        segmentContext.addSegmentParameter(2, SegmentType.AUDIO, SegmentContextParameters.START_TIME, "35");
        segmentContext.addSegmentParameter(2, SegmentType.AUDIO, SegmentContextParameters.DURATION, "5");

        logger.info("Created Segment context: OK\n");
    }

    @Override
    protected String getConversionConfiguration() {
        return conversionProvider.getConvertConfiguration(Format.DPP).get(0);
    }
}
