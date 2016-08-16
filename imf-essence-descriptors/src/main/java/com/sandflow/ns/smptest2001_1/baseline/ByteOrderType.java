//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.11 at 01:51:42 PM MSK 
//


package com.sandflow.ns.smptest2001_1.baseline;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ByteOrderType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ByteOrderType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BigEndian"/>
 *     &lt;enumeration value="LittleEndian"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ByteOrderType", namespace = "http://sandflow.com/ns/SMPTEST2001-1/baseline")
@XmlEnum
public enum ByteOrderType {

    @XmlEnumValue("BigEndian")
    BIG_ENDIAN("BigEndian"),
    @XmlEnumValue("LittleEndian")
    LITTLE_ENDIAN("LittleEndian");
    private final String value;

    ByteOrderType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ByteOrderType fromValue(String v) {
        for (ByteOrderType c: ByteOrderType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}