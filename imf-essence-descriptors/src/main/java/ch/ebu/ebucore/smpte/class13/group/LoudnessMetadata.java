//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.11 at 01:51:42 PM MSK 
//


package ch.ebu.ebucore.smpte.class13.group;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.smpte_ra.reg._2003._2012.ApplicationPluginObjectStrongReferenceSet;
import org.smpte_ra.reg._2003._2012.UTF16String;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element ref="{http://www.ebu.ch/ebucore/smpte/class13/element}loudnessMethod" minOccurs="0"/>
 *         &lt;element ref="{http://www.ebu.ch/ebucore/smpte/class13/element}loudnessMaxMomentary" minOccurs="0"/>
 *         &lt;element ref="{http://www.ebu.ch/ebucore/smpte/class13/element}loudnessMaxTruePeak" minOccurs="0"/>
 *         &lt;element ref="{http://www.ebu.ch/ebucore/smpte/class13/element}integratedLoudness" minOccurs="0"/>
 *         &lt;element ref="{http://www.ebu.ch/ebucore/smpte/class13/element}loudnessRange" minOccurs="0"/>
 *         &lt;element ref="{http://www.ebu.ch/ebucore/smpte/class13/element}loudnessMaxShortTerm" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}LinkedDescriptiveObjectPluginID" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}InstanceID" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}ObjectClass" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}ApplicationPlugInObjects" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}LinkedGenerationID" minOccurs="0"/>
 *       &lt;/all>
 *       &lt;attribute ref="{http://sandflow.com/ns/SMPTEST2001-1/baseline}path"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "loudnessMetadata")
public class LoudnessMetadata {

    @XmlElement(namespace = "http://www.ebu.ch/ebucore/smpte/class13/element")
    protected UTF16String loudnessMethod;
    @XmlElement(namespace = "http://www.ebu.ch/ebucore/smpte/class13/element")
    protected Float loudnessMaxMomentary;
    @XmlElement(namespace = "http://www.ebu.ch/ebucore/smpte/class13/element")
    protected Float loudnessMaxTruePeak;
    @XmlElement(namespace = "http://www.ebu.ch/ebucore/smpte/class13/element")
    protected Float integratedLoudness;
    @XmlElement(namespace = "http://www.ebu.ch/ebucore/smpte/class13/element")
    protected Float loudnessRange;
    @XmlElement(namespace = "http://www.ebu.ch/ebucore/smpte/class13/element")
    protected Float loudnessMaxShortTerm;
    @XmlElement(name = "LinkedDescriptiveObjectPluginID", namespace = "http://www.smpte-ra.org/reg/335/2012")
    @XmlSchemaType(name = "anySimpleType")
    protected String linkedDescriptiveObjectPluginID;
    @XmlElement(name = "InstanceID", namespace = "http://www.smpte-ra.org/reg/335/2012")
    @XmlSchemaType(name = "anyURI")
    protected String instanceID;
    @XmlElement(name = "ObjectClass", namespace = "http://www.smpte-ra.org/reg/335/2012")
    @XmlSchemaType(name = "anySimpleType")
    protected String objectClass;
    @XmlElement(name = "ApplicationPlugInObjects", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected ApplicationPluginObjectStrongReferenceSet applicationPlugInObjects;
    @XmlElement(name = "LinkedGenerationID", namespace = "http://www.smpte-ra.org/reg/335/2012")
    @XmlSchemaType(name = "anyURI")
    protected String linkedGenerationID;
    @XmlAttribute(name = "path", namespace = "http://sandflow.com/ns/SMPTEST2001-1/baseline")
    protected String path;

    /**
     * Gets the value of the loudnessMethod property.
     * 
     * @return
     *     possible object is
     *     {@link UTF16String }
     *     
     */
    public UTF16String getLoudnessMethod() {
        return loudnessMethod;
    }

    /**
     * Sets the value of the loudnessMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link UTF16String }
     *     
     */
    public void setLoudnessMethod(UTF16String value) {
        this.loudnessMethod = value;
    }

    /**
     * Gets the value of the loudnessMaxMomentary property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getLoudnessMaxMomentary() {
        return loudnessMaxMomentary;
    }

    /**
     * Sets the value of the loudnessMaxMomentary property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setLoudnessMaxMomentary(Float value) {
        this.loudnessMaxMomentary = value;
    }

    /**
     * Gets the value of the loudnessMaxTruePeak property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getLoudnessMaxTruePeak() {
        return loudnessMaxTruePeak;
    }

    /**
     * Sets the value of the loudnessMaxTruePeak property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setLoudnessMaxTruePeak(Float value) {
        this.loudnessMaxTruePeak = value;
    }

    /**
     * Gets the value of the integratedLoudness property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getIntegratedLoudness() {
        return integratedLoudness;
    }

    /**
     * Sets the value of the integratedLoudness property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setIntegratedLoudness(Float value) {
        this.integratedLoudness = value;
    }

    /**
     * Gets the value of the loudnessRange property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getLoudnessRange() {
        return loudnessRange;
    }

    /**
     * Sets the value of the loudnessRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setLoudnessRange(Float value) {
        this.loudnessRange = value;
    }

    /**
     * Gets the value of the loudnessMaxShortTerm property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getLoudnessMaxShortTerm() {
        return loudnessMaxShortTerm;
    }

    /**
     * Sets the value of the loudnessMaxShortTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setLoudnessMaxShortTerm(Float value) {
        this.loudnessMaxShortTerm = value;
    }

    /**
     * Gets the value of the linkedDescriptiveObjectPluginID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkedDescriptiveObjectPluginID() {
        return linkedDescriptiveObjectPluginID;
    }

    /**
     * Sets the value of the linkedDescriptiveObjectPluginID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkedDescriptiveObjectPluginID(String value) {
        this.linkedDescriptiveObjectPluginID = value;
    }

    /**
     * Gets the value of the instanceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstanceID() {
        return instanceID;
    }

    /**
     * Sets the value of the instanceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstanceID(String value) {
        this.instanceID = value;
    }

    /**
     * Gets the value of the objectClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectClass() {
        return objectClass;
    }

    /**
     * Sets the value of the objectClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectClass(String value) {
        this.objectClass = value;
    }

    /**
     * Gets the value of the applicationPlugInObjects property.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationPluginObjectStrongReferenceSet }
     *     
     */
    public ApplicationPluginObjectStrongReferenceSet getApplicationPlugInObjects() {
        return applicationPlugInObjects;
    }

    /**
     * Sets the value of the applicationPlugInObjects property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationPluginObjectStrongReferenceSet }
     *     
     */
    public void setApplicationPlugInObjects(ApplicationPluginObjectStrongReferenceSet value) {
        this.applicationPlugInObjects = value;
    }

    /**
     * Gets the value of the linkedGenerationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkedGenerationID() {
        return linkedGenerationID;
    }

    /**
     * Sets the value of the linkedGenerationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkedGenerationID(String value) {
        this.linkedGenerationID = value;
    }

    /**
     * Gets the value of the path property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the value of the path property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPath(String value) {
        this.path = value;
    }

}
