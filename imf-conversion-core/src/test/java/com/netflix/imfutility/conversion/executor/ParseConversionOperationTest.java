/**
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
package com.netflix.imfutility.conversion.executor;

import com.netflix.imfutility.conversion.templateParameter.ContextInfo;
import com.netflix.imfutility.conversion.templateParameter.TemplateParameterResolver;
import com.netflix.imfutility.conversion.templateParameter.context.DynamicTemplateParameterContext;
import com.netflix.imfutility.conversion.templateParameter.context.TemplateParameterContextProvider;
import com.netflix.imfutility.util.TemplateParameterContextCreator;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for Conversion Operation parser.
 * <ul>
 * <li>Checks whether conversion operation is split successfully to arguments</li>
 * <li>Checks whether all template parameters are resolved</li>
 * <li>Checks whether whitespaces, end of line and quotes are processed correctly</li>
 * </ul>
 */
public class ParseConversionOperationTest {

    private static ConversionOperationParser parser;

    @BeforeClass
    public static void setUpAll() throws Exception {
        TemplateParameterContextProvider contextProvider = TemplateParameterContextCreator.createDefaultContextProvider();
        fillDynamic(contextProvider);

        parser = new ConversionOperationParser(new TemplateParameterResolver(contextProvider));
    }

    private static void fillDynamic(TemplateParameterContextProvider contextProvider) {
        DynamicTemplateParameterContext dynamicContext = contextProvider.getDynamicContext();
        dynamicContext.addParameter("dynamicSimple", "dynamicValueSimple", ContextInfo.EMPTY);
        dynamicContext.addParameter("dynamicWhitespace", "dynamicValue whitespace", ContextInfo.EMPTY);
    }

    @Test
    public void testSplitWhitespaces() {
        String conversionOperation =
                "exec    -arg1     arg2 --arg3 arg4                  arg6=value";

        List<String> actual = parser.parseOperation(conversionOperation, ContextInfo.EMPTY);
        List<String> expected = Arrays.asList(
                "exec",
                "-arg1",
                "arg2",
                "--arg3",
                "arg4",
                "arg6=value");

        assertThat(actual, is(expected));
    }

    @Test
    public void testSplitQuotes() {
        String conversionOperation =
                "exec arg1 --arg2  \"arg3 in double quotes\" \"arg4 'in double quotes'\" " +
                        "'arg5 in single quotes'  'arg6 \"in single quotes\"' arg7=\"value\" arg8='value'";

        List<String> actual = parser.parseOperation(conversionOperation, ContextInfo.EMPTY);
        // all leading and tailing quotes must be removed
        List<String> expected = Arrays.asList(
                "exec",
                "arg1",
                "--arg2",
                "arg3 in double quotes",
                "arg4 'in double quotes'",
                "arg5 in single quotes",
                "arg6 \"in single quotes\"",
                "arg7=\"value\"",
                "arg8='value'"
        );

        assertThat(actual, is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSplitThrowsExceptionIfWrongQuotes() {
        String conversionOperation =
                "exec \"arg in mixed quotes'";
        parser.parseOperation(conversionOperation, ContextInfo.EMPTY);
    }

    @Test
    public void testSplitWithNewlinesN() {
        String conversionOperation =
                "\n\nexec  -arg1\narg2\narg3\n--arg4 \n -arg5\n arg6 \n\n";

        List<String> actual = parser.parseOperation(conversionOperation, ContextInfo.EMPTY);
        List<String> expected = Arrays.asList(
                "exec",
                "-arg1",
                "arg2",
                "arg3",
                "--arg4",
                "-arg5",
                "arg6");

        assertThat(actual, is(expected));
    }

    @Test
    public void testSplitWithNewlinesR() {
        String conversionOperation =
                "\r\rexec  -arg1\rarg2\rarg3\r--arg4 \r -arg5\r arg6 \r\r";

        List<String> actual = parser.parseOperation(conversionOperation, ContextInfo.EMPTY);
        List<String> expected = Arrays.asList(
                "exec",
                "-arg1",
                "arg2",
                "arg3",
                "--arg4",
                "-arg5",
                "arg6");

        assertThat(actual, is(expected));
    }

    @Test
    public void testSplitWithNewlinesRN() {
        String conversionOperation =
                "\r\n\r\nexec  -arg1\r\narg2\r\narg3\r\n--arg4 \r\n -arg5\r\n arg6 \r\n\r\n";

        List<String> actual = parser.parseOperation(conversionOperation, ContextInfo.EMPTY);
        List<String> expected = Arrays.asList(
                "exec",
                "-arg1",
                "arg2",
                "arg3",
                "--arg4",
                "-arg5",
                "arg6");

        assertThat(actual, is(expected));
    }

    @Test
    public void testSplitWithNewlinesAndQuotesN() {
        String conversionOperation =
                "exec \"arg1\narg2\narg3\"\narg4\n\"arg5\"\n'arg6'\n\'arg7\narg8'";

        List<String> actual = parser.parseOperation(conversionOperation, ContextInfo.EMPTY);
        // all leading and tailing quotes must be removed
        List<String> expected = Arrays.asList(
                "exec",
                "arg1\narg2\narg3",
                "arg4",
                "arg5",
                "arg6",
                "arg7\narg8");

        assertThat(actual, is(expected));
    }

    @Test
    public void testSplitWithNewlinesAndQuotesR() {
        String conversionOperation =
                "exec \"arg1\rarg2\rarg3\"\rarg4\r\"arg5\"\r'arg6'\r\'arg7\rarg8'";

        List<String> actual = parser.parseOperation(conversionOperation, ContextInfo.EMPTY);
        // all leading and tailing quotes must be removed
        List<String> expected = Arrays.asList(
                "exec",
                "arg1\rarg2\rarg3",
                "arg4",
                "arg5",
                "arg6",
                "arg7\rarg8");

        assertThat(actual, is(expected));
    }

    @Test
    public void testSplitWithNewlinesAndQuotesRN() {
        String conversionOperation =
                "exec \"arg1\r\narg2\r\narg3\"\r\narg4\r\n\"arg5\"\r\n'arg6'\r\n\'arg7\r\narg8'";

        List<String> actual = parser.parseOperation(conversionOperation, ContextInfo.EMPTY);
        // all leading and tailing quotes must be removed
        List<String> expected = Arrays.asList(
                "exec",
                "arg1\r\narg2\r\narg3",
                "arg4",
                "arg5",
                "arg6",
                "arg7\r\narg8");

        assertThat(actual, is(expected));
    }

    @Test
    public void testResolvesArgs() {
        String conversionOperation =
                "exec %{tool.toolSimple} %{tmp.tmpParamSimple} %{dynamic.dynamicSimple}";

        List<String> actual = parser.parseOperation(conversionOperation, ContextInfo.EMPTY);
        List<String> expected = Arrays.asList(
                "exec",
                "root\\toolSimple",
                "tmpParamSimple",
                "dynamicValueSimple");

        assertThat(actual, is(expected));
    }

    @Test
    public void testAddQuotes() {
        String conversionOperation =
                "exec \"%{tool.toolWhitespace}\" \"%{tmp.tmpParamWhitespace}\" \"%{dynamic.dynamicWhitespace}\"";

        List<String> actual = parser.parseWithQuotes(conversionOperation, ContextInfo.EMPTY);
        List<String> expected = Arrays.asList(
                "exec",
                "\"root\\tool whitespace\"",
                "\"tmpParam whitespace\"",
                "\"dynamicValue whitespace\"");

        assertThat(actual, is(expected));
    }

    @Test
    public void testAddQuotesOnlyWhenNeeded() {
        String conversionOperation =
                "exec \"%{tool.toolSimple}\" \"%{tool.toolWhitespace}\" '%{tmp.tmpParamWhitespace}' \"%{dynamic.dynamicWhitespace}\"";

        List<String> actual = parser.parseWithQuotes(conversionOperation, ContextInfo.EMPTY);
        List<String> expected = Arrays.asList(
                "exec",
                "\"root\\toolSimple\"",
                "\"root\\tool whitespace\"",
                "'tmpParam whitespace'",
                "\"dynamicValue whitespace\"");

        assertThat(actual, is(expected));
    }

    @Test
    public void testResolveComplexParameters() {
        String conversionOperation =
                "exec arg1=%{tool.toolSimple} arg2=%{dynamic.dynamicSimple}/%{tmp.tmpParamSimple} %{dynamic.dynamicSimple}=%{tmp.tmpParamSimple}";

        List<String> actual = parser.parseOperation(conversionOperation, ContextInfo.EMPTY);
        List<String> expected = Arrays.asList(
                "exec",
                "arg1=root\\toolSimple",
                "arg2=dynamicValueSimple/tmpParamSimple",
                "dynamicValueSimple=tmpParamSimple");

        assertThat(actual, is(expected));
    }

    @Test
    public void testAddQuotesWhenNeededForComplexParameters() {
        String conversionOperation =
                "exec " +
                        "arg1=\"%{tool.toolWhitespace}\" arg2=\"%{tool.toolWhitespace}\" arg3='%{tool.toolWhitespace}' " +
                        "arg4=\"%{dynamic.dynamicSimple}/%{tmp.tmpParamWhitespace}\"" +
                        " %{dynamic.dynamicSimple}=\"%{tmp.tmpParamWhitespace}\" %{dynamic.dynamicSimple}=\"%{tmp.tmpParamWhitespace}\" " +
                        "%{dynamic.dynamicSimple}='%{tmp.tmpParamWhitespace}'";

        List<String> actual = parser.parseWithQuotes(conversionOperation, ContextInfo.EMPTY);
        List<String> expected = Arrays.asList(
                "exec",
                "arg1=\"root\\tool whitespace\"",
                "arg2=\"root\\tool whitespace\"",
                "arg3='root\\tool whitespace'",
                "arg4=\"dynamicValueSimple/tmpParam whitespace\"",
                "dynamicValueSimple=\"tmpParam whitespace\"",
                "dynamicValueSimple=\"tmpParam whitespace\"",
                "dynamicValueSimple='tmpParam whitespace'");

        assertThat(actual, is(expected));
    }

}
