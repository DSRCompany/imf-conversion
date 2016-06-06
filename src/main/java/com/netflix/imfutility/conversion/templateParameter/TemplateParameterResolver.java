package com.netflix.imfutility.conversion.templateParameter;

import com.netflix.imfutility.conversion.templateParameter.context.ITemplateParameterContext;
import com.netflix.imfutility.conversion.templateParameter.context.TemplateParameterContextProvider;
import com.netflix.imfutility.conversion.templateParameter.exception.UnknownTemplateParameterContextException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Resolves a given template parameter using an appropriate {@link ITemplateParameterContext}.
 * <ul>
 * <li>A simple parameter has the following form: %{paramContext.paramName}</li>
 * <li>The parameter is resolved by finding a value for the parameter "paramName" in the
 * {@link ITemplateParameterContext} associated with the "paramContext".</li>
 * <li>The parameter may contain sub-parameters, such as %{dynamic.%{segm.num}}</li>
 * </ul>
 */
public class TemplateParameterResolver {

    private final TemplateParameterContextProvider contextProvider;

    public TemplateParameterResolver(TemplateParameterContextProvider contextProvider) {
        this.contextProvider = contextProvider;
    }

    public TemplateParameterContextProvider getContextProvider() {
        return contextProvider;
    }

    /**
     * Resolves the given parameter using an appropriate {@link ITemplateParameterContext}.
     * <ul>
     * <li>A simple parameter has the following form: %{paramContext.paramName}</li>
     * <li>The parameter is resolved by finding a value for the parameter "paramName" in the
     * {@link ITemplateParameterContext} associated with the "paramContext".</li>
     * <li>The parameter may contain sub-parameters, such as %{dynamic.%{segm.num}}</li>
     * </ul>
     *
     * @param parameterStr the parameter to be resolved
     * @param contextInfo  a context info helping to resolved the parameter.
     * @return resolved parameter value as a string.
     */
    public String resolveTemplateParameter(String parameterStr, ContextInfo contextInfo) {
        String unresolvedParam = parameterStr;
        String resolvedParam = null;
        // resolve all sub-parameters, such as %{dynamic.%{segm.num}}
        while (!unresolvedParam.equals(resolvedParam)) {
            unresolvedParam = resolvedParam != null ? resolvedParam : unresolvedParam;
            resolvedParam = doResolveSubParameters(unresolvedParam, contextInfo);
        }
        return resolvedParam;
    }

    private String doResolveSubParameters(String parameterStr, ContextInfo contextInfo) {
        String resolvedParam = parameterStr;

        // resolve each template parameter the param contains
        Matcher m = Pattern.compile(TemplateParameter.TEMPLATE_PARAM).matcher(parameterStr);
        while (m.find()) {
            String templateParam = m.group();
            String resolvedTemplateParam = doResolveTemplateParameter(new TemplateParameter(templateParam), contextInfo);
            resolvedParam = resolvedParam.replace(templateParam, resolvedTemplateParam);
        }

        return resolvedParam;
    }

    private String doResolveTemplateParameter(TemplateParameter templateParameter, ContextInfo contextInfo) {
        ITemplateParameterContext context = contextProvider.getContext(templateParameter.getContext());
        if (context == null) {
            throw new UnknownTemplateParameterContextException(
                    templateParameter.toString(),
                    String.format("'%s' context not defined.", templateParameter.getContext().getName()));
        }

        return context.resolveTemplateParameter(templateParameter, contextInfo);
    }
}
