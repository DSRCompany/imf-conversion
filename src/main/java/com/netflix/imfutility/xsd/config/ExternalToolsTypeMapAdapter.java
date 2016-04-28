package com.netflix.imfutility.xsd.config;

import com.netflix.imfutility.xsd.conversion.FormatType;
import com.netflix.imfutility.xsd.conversion.FormatTypes;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Map;

/**
 * Created by Alexander on 4/25/2016.
 */
public class ExternalToolsTypeMapAdapter extends XmlAdapter<ExternalToolsType, ExternalToolsTypeMap<String, ToolType>> {

    @Override
    public ExternalToolsTypeMap<String, ToolType> unmarshal(ExternalToolsType externalTools) throws Exception {
        ExternalToolsTypeMap<String, ToolType> map = new ExternalToolsTypeMap();
        for (ToolType tt : externalTools.getTool()) {
            map.put(tt.getId(), tt);
        }
        return map;
    }

    @Override
    public ExternalToolsType marshal(ExternalToolsTypeMap<String, ToolType> map) throws Exception {
        ExternalToolsType externalTools = new ExternalToolsType();
        for (Map.Entry<String, ToolType> entry : map.entrySet()) {
            externalTools.getTool().add(entry.getValue());
        }
        return externalTools;
    }
}
