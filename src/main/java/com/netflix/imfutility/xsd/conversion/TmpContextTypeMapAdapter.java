package com.netflix.imfutility.xsd.conversion;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Map;

/**
 * Created by Alexander on 4/25/2016.
 */
public class TmpContextTypeMapAdapter extends XmlAdapter<TmpContextType, TmpContextTypeMap<String, ParamType>> {

    @Override
    public TmpContextTypeMap<String, ParamType> unmarshal(TmpContextType tmpContextTypes) throws Exception {
        TmpContextTypeMap<String, ParamType> map = new TmpContextTypeMap();
        for (ParamType pt : tmpContextTypes.getParam()) {
            map.getMap().put(pt.getId(), pt);
        }
        return map;
    }

    @Override
    public TmpContextType marshal(TmpContextTypeMap<String, ParamType> map) throws Exception {
        TmpContextType tmpContextType = new TmpContextType();
        for (Map.Entry<String, ParamType> entry : map.getMap().entrySet()) {
            tmpContextType.getParam().add(entry.getValue());
        }
        return tmpContextType;
    }
}
