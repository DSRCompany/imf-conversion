//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7-b41 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.09.01 at 06:27:35 PM MSK 
//


package com.apple.itunes.importer.tv;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReleaseType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ReleaseType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="new-release"/>
 *     &lt;enumeration value="digital-only"/>
 *     &lt;enumeration value="other"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ReleaseType")
@XmlEnum
public enum ReleaseType {

    @XmlEnumValue("new-release")
    NEW_RELEASE("new-release"),
    @XmlEnumValue("digital-only")
    DIGITAL_ONLY("digital-only"),
    @XmlEnumValue("other")
    OTHER("other");
    private final String value;

    ReleaseType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ReleaseType fromValue(String v) {
        for (ReleaseType c: ReleaseType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
