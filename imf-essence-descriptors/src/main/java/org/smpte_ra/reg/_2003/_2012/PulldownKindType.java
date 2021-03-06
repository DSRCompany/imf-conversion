//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.11 at 01:51:42 PM MSK 
//


package org.smpte_ra.reg._2003._2012;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PulldownKindType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PulldownKindType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="TwoThreePD"/>
 *     &lt;enumeration value="PALPD"/>
 *     &lt;enumeration value="OneToOneNTSC"/>
 *     &lt;enumeration value="OneToOnePAL"/>
 *     &lt;enumeration value="VideoTapNTSC"/>
 *     &lt;enumeration value="OneToOneHDSixty"/>
 *     &lt;enumeration value="TwentyFourToSixtyPD"/>
 *     &lt;enumeration value="TwoToOnePD"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PulldownKindType")
@XmlEnum
public enum PulldownKindType {

    @XmlEnumValue("TwoThreePD")
    TWO_THREE_PD("TwoThreePD"),
    PALPD("PALPD"),
    @XmlEnumValue("OneToOneNTSC")
    ONE_TO_ONE_NTSC("OneToOneNTSC"),
    @XmlEnumValue("OneToOnePAL")
    ONE_TO_ONE_PAL("OneToOnePAL"),
    @XmlEnumValue("VideoTapNTSC")
    VIDEO_TAP_NTSC("VideoTapNTSC"),
    @XmlEnumValue("OneToOneHDSixty")
    ONE_TO_ONE_HD_SIXTY("OneToOneHDSixty"),
    @XmlEnumValue("TwentyFourToSixtyPD")
    TWENTY_FOUR_TO_SIXTY_PD("TwentyFourToSixtyPD"),
    @XmlEnumValue("TwoToOnePD")
    TWO_TO_ONE_PD("TwoToOnePD");
    private final String value;

    PulldownKindType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PulldownKindType fromValue(String v) {
        for (PulldownKindType c: PulldownKindType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
