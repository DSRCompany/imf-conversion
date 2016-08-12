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
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}Rightsholder" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}RightsCondition" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}CopyrightOwner" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}RightsComment" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}MaxNumberOfUsages" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}RegionAreaOfIPLicense" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}RightsStopDateTime" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}IntellectualPropertyRight" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}RightsStartDateTime" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}RightsManagementAuthority" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}IntellectualPropertyDescription" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}ThesaurusName" minOccurs="0"/>
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
@XmlRootElement(name = "Rights")
public class Rights {

    @XmlElement(name = "Rightsholder", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected UTF16String rightsholder;
    @XmlElement(name = "RightsCondition", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected UTF16String rightsCondition;
    @XmlElement(name = "CopyrightOwner", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected UTF16String copyrightOwner;
    @XmlElement(name = "RightsComment", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected UTF16String rightsComment;
    @XmlElement(name = "MaxNumberOfUsages", namespace = "http://www.smpte-ra.org/reg/335/2012")
    @XmlSchemaType(name = "anySimpleType")
    protected String maxNumberOfUsages;
    @XmlElement(name = "RegionAreaOfIPLicense", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected UTF16String regionAreaOfIPLicense;
    @XmlElement(name = "RightsStopDateTime", namespace = "http://www.smpte-ra.org/reg/335/2012")
    @XmlSchemaType(name = "anySimpleType")
    protected String rightsStopDateTime;
    @XmlElement(name = "IntellectualPropertyRight", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected UTF16String intellectualPropertyRight;
    @XmlElement(name = "RightsStartDateTime", namespace = "http://www.smpte-ra.org/reg/335/2012")
    @XmlSchemaType(name = "anySimpleType")
    protected String rightsStartDateTime;
    @XmlElement(name = "RightsManagementAuthority", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected UTF16String rightsManagementAuthority;
    @XmlElement(name = "IntellectualPropertyDescription", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected UTF16String intellectualPropertyDescription;
    @XmlElement(name = "ThesaurusName", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected UTF16String thesaurusName;
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
     * Gets the value of the rightsholder property.
     * 
     * @return
     *     possible object is
     *     {@link UTF16String }
     *     
     */
    public UTF16String getRightsholder() {
        return rightsholder;
    }

    /**
     * Sets the value of the rightsholder property.
     * 
     * @param value
     *     allowed object is
     *     {@link UTF16String }
     *     
     */
    public void setRightsholder(UTF16String value) {
        this.rightsholder = value;
    }

    /**
     * Gets the value of the rightsCondition property.
     * 
     * @return
     *     possible object is
     *     {@link UTF16String }
     *     
     */
    public UTF16String getRightsCondition() {
        return rightsCondition;
    }

    /**
     * Sets the value of the rightsCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link UTF16String }
     *     
     */
    public void setRightsCondition(UTF16String value) {
        this.rightsCondition = value;
    }

    /**
     * Gets the value of the copyrightOwner property.
     * 
     * @return
     *     possible object is
     *     {@link UTF16String }
     *     
     */
    public UTF16String getCopyrightOwner() {
        return copyrightOwner;
    }

    /**
     * Sets the value of the copyrightOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link UTF16String }
     *     
     */
    public void setCopyrightOwner(UTF16String value) {
        this.copyrightOwner = value;
    }

    /**
     * Gets the value of the rightsComment property.
     * 
     * @return
     *     possible object is
     *     {@link UTF16String }
     *     
     */
    public UTF16String getRightsComment() {
        return rightsComment;
    }

    /**
     * Sets the value of the rightsComment property.
     * 
     * @param value
     *     allowed object is
     *     {@link UTF16String }
     *     
     */
    public void setRightsComment(UTF16String value) {
        this.rightsComment = value;
    }

    /**
     * Gets the value of the maxNumberOfUsages property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxNumberOfUsages() {
        return maxNumberOfUsages;
    }

    /**
     * Sets the value of the maxNumberOfUsages property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxNumberOfUsages(String value) {
        this.maxNumberOfUsages = value;
    }

    /**
     * Gets the value of the regionAreaOfIPLicense property.
     * 
     * @return
     *     possible object is
     *     {@link UTF16String }
     *     
     */
    public UTF16String getRegionAreaOfIPLicense() {
        return regionAreaOfIPLicense;
    }

    /**
     * Sets the value of the regionAreaOfIPLicense property.
     * 
     * @param value
     *     allowed object is
     *     {@link UTF16String }
     *     
     */
    public void setRegionAreaOfIPLicense(UTF16String value) {
        this.regionAreaOfIPLicense = value;
    }

    /**
     * Gets the value of the rightsStopDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRightsStopDateTime() {
        return rightsStopDateTime;
    }

    /**
     * Sets the value of the rightsStopDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRightsStopDateTime(String value) {
        this.rightsStopDateTime = value;
    }

    /**
     * Gets the value of the intellectualPropertyRight property.
     * 
     * @return
     *     possible object is
     *     {@link UTF16String }
     *     
     */
    public UTF16String getIntellectualPropertyRight() {
        return intellectualPropertyRight;
    }

    /**
     * Sets the value of the intellectualPropertyRight property.
     * 
     * @param value
     *     allowed object is
     *     {@link UTF16String }
     *     
     */
    public void setIntellectualPropertyRight(UTF16String value) {
        this.intellectualPropertyRight = value;
    }

    /**
     * Gets the value of the rightsStartDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRightsStartDateTime() {
        return rightsStartDateTime;
    }

    /**
     * Sets the value of the rightsStartDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRightsStartDateTime(String value) {
        this.rightsStartDateTime = value;
    }

    /**
     * Gets the value of the rightsManagementAuthority property.
     * 
     * @return
     *     possible object is
     *     {@link UTF16String }
     *     
     */
    public UTF16String getRightsManagementAuthority() {
        return rightsManagementAuthority;
    }

    /**
     * Sets the value of the rightsManagementAuthority property.
     * 
     * @param value
     *     allowed object is
     *     {@link UTF16String }
     *     
     */
    public void setRightsManagementAuthority(UTF16String value) {
        this.rightsManagementAuthority = value;
    }

    /**
     * Gets the value of the intellectualPropertyDescription property.
     * 
     * @return
     *     possible object is
     *     {@link UTF16String }
     *     
     */
    public UTF16String getIntellectualPropertyDescription() {
        return intellectualPropertyDescription;
    }

    /**
     * Sets the value of the intellectualPropertyDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link UTF16String }
     *     
     */
    public void setIntellectualPropertyDescription(UTF16String value) {
        this.intellectualPropertyDescription = value;
    }

    /**
     * Gets the value of the thesaurusName property.
     * 
     * @return
     *     possible object is
     *     {@link UTF16String }
     *     
     */
    public UTF16String getThesaurusName() {
        return thesaurusName;
    }

    /**
     * Sets the value of the thesaurusName property.
     * 
     * @param value
     *     allowed object is
     *     {@link UTF16String }
     *     
     */
    public void setThesaurusName(UTF16String value) {
        this.thesaurusName = value;
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
