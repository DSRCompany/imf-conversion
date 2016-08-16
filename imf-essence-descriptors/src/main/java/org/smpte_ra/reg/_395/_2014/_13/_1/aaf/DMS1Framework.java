//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.11 at 01:51:42 PM MSK 
//


package org.smpte_ra.reg._395._2014._13._1.aaf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.smpte_ra.reg._2003._2012.ApplicationPluginObjectStrongReferenceSet;
import org.smpte_ra.reg._2003._2012.DescriptiveObjectStrongReference;
import org.smpte_ra.reg._2003._2012.DescriptiveObjectStrongReferenceSet;
import org.smpte_ra.reg._2003._2012.ISO639Ext;
import org.smpte_ra.reg._2003._2012.LocationGlobalReferenceSet;
import org.smpte_ra.reg._2003._2012.LocatorStrongReferenceVector;
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
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}FrameworkThesaurusName" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}SecondaryExtendedSpokenLanguageCode" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}PrimaryExtendedSpokenLanguageCode" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}ContactsListObject" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}MetadataServerLocators" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}TitlesObjects" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}ParticipantObjects" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}Locations" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}AnnotationObjects" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}FrameworkTitle" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}FrameworkExtendedTextLanguageCode" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}OriginalExtendedSpokenLanguageCode" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}LinkedDescriptiveFrameworkPluginID"/>
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
@XmlRootElement(name = "DMS1Framework")
public abstract class DMS1Framework {

    @XmlElement(name = "FrameworkThesaurusName", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected UTF16String frameworkThesaurusName;
    @XmlElement(name = "SecondaryExtendedSpokenLanguageCode", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected ISO639Ext secondaryExtendedSpokenLanguageCode;
    @XmlElement(name = "PrimaryExtendedSpokenLanguageCode", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected ISO639Ext primaryExtendedSpokenLanguageCode;
    @XmlElement(name = "ContactsListObject", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected DescriptiveObjectStrongReference contactsListObject;
    @XmlElement(name = "MetadataServerLocators", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected LocatorStrongReferenceVector metadataServerLocators;
    @XmlElement(name = "TitlesObjects", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected DescriptiveObjectStrongReferenceSet titlesObjects;
    @XmlElement(name = "ParticipantObjects", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected DescriptiveObjectStrongReferenceSet participantObjects;
    @XmlElement(name = "Locations", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected LocationGlobalReferenceSet locations;
    @XmlElement(name = "AnnotationObjects", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected DescriptiveObjectStrongReferenceSet annotationObjects;
    @XmlElement(name = "FrameworkTitle", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected UTF16String frameworkTitle;
    @XmlElement(name = "FrameworkExtendedTextLanguageCode", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected ISO639Ext frameworkExtendedTextLanguageCode;
    @XmlElement(name = "OriginalExtendedSpokenLanguageCode", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected ISO639Ext originalExtendedSpokenLanguageCode;
    @XmlElement(name = "LinkedDescriptiveFrameworkPluginID", namespace = "http://www.smpte-ra.org/reg/335/2012", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String linkedDescriptiveFrameworkPluginID;
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
     * Gets the value of the frameworkThesaurusName property.
     * 
     * @return
     *     possible object is
     *     {@link UTF16String }
     *     
     */
    public UTF16String getFrameworkThesaurusName() {
        return frameworkThesaurusName;
    }

    /**
     * Sets the value of the frameworkThesaurusName property.
     * 
     * @param value
     *     allowed object is
     *     {@link UTF16String }
     *     
     */
    public void setFrameworkThesaurusName(UTF16String value) {
        this.frameworkThesaurusName = value;
    }

    /**
     * Gets the value of the secondaryExtendedSpokenLanguageCode property.
     * 
     * @return
     *     possible object is
     *     {@link ISO639Ext }
     *     
     */
    public ISO639Ext getSecondaryExtendedSpokenLanguageCode() {
        return secondaryExtendedSpokenLanguageCode;
    }

    /**
     * Sets the value of the secondaryExtendedSpokenLanguageCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ISO639Ext }
     *     
     */
    public void setSecondaryExtendedSpokenLanguageCode(ISO639Ext value) {
        this.secondaryExtendedSpokenLanguageCode = value;
    }

    /**
     * Gets the value of the primaryExtendedSpokenLanguageCode property.
     * 
     * @return
     *     possible object is
     *     {@link ISO639Ext }
     *     
     */
    public ISO639Ext getPrimaryExtendedSpokenLanguageCode() {
        return primaryExtendedSpokenLanguageCode;
    }

    /**
     * Sets the value of the primaryExtendedSpokenLanguageCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ISO639Ext }
     *     
     */
    public void setPrimaryExtendedSpokenLanguageCode(ISO639Ext value) {
        this.primaryExtendedSpokenLanguageCode = value;
    }

    /**
     * Gets the value of the contactsListObject property.
     * 
     * @return
     *     possible object is
     *     {@link DescriptiveObjectStrongReference }
     *     
     */
    public DescriptiveObjectStrongReference getContactsListObject() {
        return contactsListObject;
    }

    /**
     * Sets the value of the contactsListObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link DescriptiveObjectStrongReference }
     *     
     */
    public void setContactsListObject(DescriptiveObjectStrongReference value) {
        this.contactsListObject = value;
    }

    /**
     * Gets the value of the metadataServerLocators property.
     * 
     * @return
     *     possible object is
     *     {@link LocatorStrongReferenceVector }
     *     
     */
    public LocatorStrongReferenceVector getMetadataServerLocators() {
        return metadataServerLocators;
    }

    /**
     * Sets the value of the metadataServerLocators property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocatorStrongReferenceVector }
     *     
     */
    public void setMetadataServerLocators(LocatorStrongReferenceVector value) {
        this.metadataServerLocators = value;
    }

    /**
     * Gets the value of the titlesObjects property.
     * 
     * @return
     *     possible object is
     *     {@link DescriptiveObjectStrongReferenceSet }
     *     
     */
    public DescriptiveObjectStrongReferenceSet getTitlesObjects() {
        return titlesObjects;
    }

    /**
     * Sets the value of the titlesObjects property.
     * 
     * @param value
     *     allowed object is
     *     {@link DescriptiveObjectStrongReferenceSet }
     *     
     */
    public void setTitlesObjects(DescriptiveObjectStrongReferenceSet value) {
        this.titlesObjects = value;
    }

    /**
     * Gets the value of the participantObjects property.
     * 
     * @return
     *     possible object is
     *     {@link DescriptiveObjectStrongReferenceSet }
     *     
     */
    public DescriptiveObjectStrongReferenceSet getParticipantObjects() {
        return participantObjects;
    }

    /**
     * Sets the value of the participantObjects property.
     * 
     * @param value
     *     allowed object is
     *     {@link DescriptiveObjectStrongReferenceSet }
     *     
     */
    public void setParticipantObjects(DescriptiveObjectStrongReferenceSet value) {
        this.participantObjects = value;
    }

    /**
     * Gets the value of the locations property.
     * 
     * @return
     *     possible object is
     *     {@link LocationGlobalReferenceSet }
     *     
     */
    public LocationGlobalReferenceSet getLocations() {
        return locations;
    }

    /**
     * Sets the value of the locations property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationGlobalReferenceSet }
     *     
     */
    public void setLocations(LocationGlobalReferenceSet value) {
        this.locations = value;
    }

    /**
     * Gets the value of the annotationObjects property.
     * 
     * @return
     *     possible object is
     *     {@link DescriptiveObjectStrongReferenceSet }
     *     
     */
    public DescriptiveObjectStrongReferenceSet getAnnotationObjects() {
        return annotationObjects;
    }

    /**
     * Sets the value of the annotationObjects property.
     * 
     * @param value
     *     allowed object is
     *     {@link DescriptiveObjectStrongReferenceSet }
     *     
     */
    public void setAnnotationObjects(DescriptiveObjectStrongReferenceSet value) {
        this.annotationObjects = value;
    }

    /**
     * Gets the value of the frameworkTitle property.
     * 
     * @return
     *     possible object is
     *     {@link UTF16String }
     *     
     */
    public UTF16String getFrameworkTitle() {
        return frameworkTitle;
    }

    /**
     * Sets the value of the frameworkTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link UTF16String }
     *     
     */
    public void setFrameworkTitle(UTF16String value) {
        this.frameworkTitle = value;
    }

    /**
     * Gets the value of the frameworkExtendedTextLanguageCode property.
     * 
     * @return
     *     possible object is
     *     {@link ISO639Ext }
     *     
     */
    public ISO639Ext getFrameworkExtendedTextLanguageCode() {
        return frameworkExtendedTextLanguageCode;
    }

    /**
     * Sets the value of the frameworkExtendedTextLanguageCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ISO639Ext }
     *     
     */
    public void setFrameworkExtendedTextLanguageCode(ISO639Ext value) {
        this.frameworkExtendedTextLanguageCode = value;
    }

    /**
     * Gets the value of the originalExtendedSpokenLanguageCode property.
     * 
     * @return
     *     possible object is
     *     {@link ISO639Ext }
     *     
     */
    public ISO639Ext getOriginalExtendedSpokenLanguageCode() {
        return originalExtendedSpokenLanguageCode;
    }

    /**
     * Sets the value of the originalExtendedSpokenLanguageCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ISO639Ext }
     *     
     */
    public void setOriginalExtendedSpokenLanguageCode(ISO639Ext value) {
        this.originalExtendedSpokenLanguageCode = value;
    }

    /**
     * Gets the value of the linkedDescriptiveFrameworkPluginID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkedDescriptiveFrameworkPluginID() {
        return linkedDescriptiveFrameworkPluginID;
    }

    /**
     * Sets the value of the linkedDescriptiveFrameworkPluginID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkedDescriptiveFrameworkPluginID(String value) {
        this.linkedDescriptiveFrameworkPluginID = value;
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