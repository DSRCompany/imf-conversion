package com.netflix.imfutility.conversion.templateParameter.context;

import com.netflix.imfutility.conversion.templateParameter.ContextInfo;
import com.netflix.imfutility.conversion.templateParameter.TemplateParameter;

/**
 * Template Parameter Context.
 * <ul>
 * <li>It's used to replace template parameters in conversion operations.</li>
 * </ul>
 */
public interface ITemplateParameterContext {

    /**
     * Resolves the given parameter.
     * The returned value is never null.
     * A runtime exception is thrown if parameter can not be resolved.
     *
     * @param templateParameter the template parameter to be resolved.
     * @param contextInfo       a context info helping to resolve the parameter.
     * @return resolved parameter value as a string. Never null.
     */
    String resolveTemplateParameter(TemplateParameter templateParameter, ContextInfo contextInfo);

}
