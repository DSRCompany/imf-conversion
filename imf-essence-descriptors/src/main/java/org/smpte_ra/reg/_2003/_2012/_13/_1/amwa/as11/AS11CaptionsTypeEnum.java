//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.11 at 01:51:42 PM MSK 
//


package org.smpte_ra.reg._2003._2012._13._1.amwa.as11;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AS_11_Captions_Type_Enum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AS_11_Captions_Type_Enum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Captions_Hard_of_Hearing"/>
 *     &lt;enumeration value="Captions_Translation"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AS_11_Captions_Type_Enum")
@XmlEnum
public enum AS11CaptionsTypeEnum {

    @XmlEnumValue("Captions_Hard_of_Hearing")
    CAPTIONS_HARD_OF_HEARING("Captions_Hard_of_Hearing"),
    @XmlEnumValue("Captions_Translation")
    CAPTIONS_TRANSLATION("Captions_Translation");
    private final String value;

    AS11CaptionsTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AS11CaptionsTypeEnum fromValue(String v) {
        for (AS11CaptionsTypeEnum c: AS11CaptionsTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
