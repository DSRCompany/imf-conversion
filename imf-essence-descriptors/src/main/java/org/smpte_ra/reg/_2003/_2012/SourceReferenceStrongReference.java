//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.11 at 01:51:42 PM MSK 
//


package org.smpte_ra.reg._2003._2012;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.smpte_ra.reg._395._2014._13._1.aaf.DescriptiveClip;
import org.smpte_ra.reg._395._2014._13._1.aaf.HTMLClip;
import org.smpte_ra.reg._395._2014._13._1.aaf.SourceClip;


/**
 * <p>Java class for SourceReferenceStrongReference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SourceReferenceStrongReference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/395/2014/13/1/aaf}SourceClip"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/395/2014/13/1/aaf}DescriptiveClip"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/395/2014/13/1/aaf}HTMLClip"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SourceReferenceStrongReference", propOrder = {
    "sourceClip",
    "descriptiveClip",
    "htmlClip"
})
public class SourceReferenceStrongReference {

    @XmlElement(name = "SourceClip", namespace = "http://www.smpte-ra.org/reg/395/2014/13/1/aaf")
    protected SourceClip sourceClip;
    @XmlElement(name = "DescriptiveClip", namespace = "http://www.smpte-ra.org/reg/395/2014/13/1/aaf")
    protected DescriptiveClip descriptiveClip;
    @XmlElement(name = "HTMLClip", namespace = "http://www.smpte-ra.org/reg/395/2014/13/1/aaf")
    protected HTMLClip htmlClip;

    /**
     * Gets the value of the sourceClip property.
     * 
     * @return
     *     possible object is
     *     {@link SourceClip }
     *     
     */
    public SourceClip getSourceClip() {
        return sourceClip;
    }

    /**
     * Sets the value of the sourceClip property.
     * 
     * @param value
     *     allowed object is
     *     {@link SourceClip }
     *     
     */
    public void setSourceClip(SourceClip value) {
        this.sourceClip = value;
    }

    /**
     * Gets the value of the descriptiveClip property.
     * 
     * @return
     *     possible object is
     *     {@link DescriptiveClip }
     *     
     */
    public DescriptiveClip getDescriptiveClip() {
        return descriptiveClip;
    }

    /**
     * Sets the value of the descriptiveClip property.
     * 
     * @param value
     *     allowed object is
     *     {@link DescriptiveClip }
     *     
     */
    public void setDescriptiveClip(DescriptiveClip value) {
        this.descriptiveClip = value;
    }

    /**
     * Gets the value of the htmlClip property.
     * 
     * @return
     *     possible object is
     *     {@link HTMLClip }
     *     
     */
    public HTMLClip getHTMLClip() {
        return htmlClip;
    }

    /**
     * Sets the value of the htmlClip property.
     * 
     * @param value
     *     allowed object is
     *     {@link HTMLClip }
     *     
     */
    public void setHTMLClip(HTMLClip value) {
        this.htmlClip = value;
    }

}
