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
import org.smpte_ra.reg._2003._2012.Boolean;
import org.smpte_ra.reg._2003._2012.Stream;


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
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}EssenceStreamID" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}PrecedingIndexTable" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}SingularPartitionUsage" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}IndexStreamID" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}LinkedPackageID"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}SampleIndex" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}EssenceStream" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}FollowingIndexTable" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}IsSparse" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}InstanceID" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}ObjectClass" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}ApplicationPlugInObjects" minOccurs="0"/>
 *         &lt;element ref="{http://www.smpte-ra.org/reg/335/2012}LinkedGenerationID" minOccurs="0"/>
 *       &lt;/all>
 *       &lt;attribute ref="{http://sandflow.com/ns/SMPTEST2001-1/baseline}uid use="required""/>
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
@XmlRootElement(name = "EssenceData")
public class EssenceData {

    @XmlElement(name = "EssenceStreamID", namespace = "http://www.smpte-ra.org/reg/335/2012")
    @XmlSchemaType(name = "anySimpleType")
    protected String essenceStreamID;
    @XmlElement(name = "PrecedingIndexTable", namespace = "http://www.smpte-ra.org/reg/335/2012")
    @XmlSchemaType(name = "token")
    protected Boolean precedingIndexTable;
    @XmlElement(name = "SingularPartitionUsage", namespace = "http://www.smpte-ra.org/reg/335/2012")
    @XmlSchemaType(name = "token")
    protected Boolean singularPartitionUsage;
    @XmlElement(name = "IndexStreamID", namespace = "http://www.smpte-ra.org/reg/335/2012")
    @XmlSchemaType(name = "anySimpleType")
    protected String indexStreamID;
    @XmlElement(name = "LinkedPackageID", namespace = "http://www.smpte-ra.org/reg/335/2012", required = true)
    protected String linkedPackageID;
    @XmlElement(name = "SampleIndex", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected Stream sampleIndex;
    @XmlElement(name = "EssenceStream", namespace = "http://www.smpte-ra.org/reg/335/2012")
    protected Stream essenceStream;
    @XmlElement(name = "FollowingIndexTable", namespace = "http://www.smpte-ra.org/reg/335/2012")
    @XmlSchemaType(name = "token")
    protected Boolean followingIndexTable;
    @XmlElement(name = "IsSparse", namespace = "http://www.smpte-ra.org/reg/335/2012")
    @XmlSchemaType(name = "token")
    protected Boolean isSparse;
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
    @XmlAttribute(name = "uid", namespace = "http://sandflow.com/ns/SMPTEST2001-1/baseline", required = true)
    protected String uid;
    @XmlAttribute(name = "path", namespace = "http://sandflow.com/ns/SMPTEST2001-1/baseline")
    protected String path;

    /**
     * Gets the value of the essenceStreamID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEssenceStreamID() {
        return essenceStreamID;
    }

    /**
     * Sets the value of the essenceStreamID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEssenceStreamID(String value) {
        this.essenceStreamID = value;
    }

    /**
     * Gets the value of the precedingIndexTable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getPrecedingIndexTable() {
        return precedingIndexTable;
    }

    /**
     * Sets the value of the precedingIndexTable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrecedingIndexTable(Boolean value) {
        this.precedingIndexTable = value;
    }

    /**
     * Gets the value of the singularPartitionUsage property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getSingularPartitionUsage() {
        return singularPartitionUsage;
    }

    /**
     * Sets the value of the singularPartitionUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSingularPartitionUsage(Boolean value) {
        this.singularPartitionUsage = value;
    }

    /**
     * Gets the value of the indexStreamID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndexStreamID() {
        return indexStreamID;
    }

    /**
     * Sets the value of the indexStreamID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndexStreamID(String value) {
        this.indexStreamID = value;
    }

    /**
     * Gets the value of the linkedPackageID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkedPackageID() {
        return linkedPackageID;
    }

    /**
     * Sets the value of the linkedPackageID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkedPackageID(String value) {
        this.linkedPackageID = value;
    }

    /**
     * Gets the value of the sampleIndex property.
     * 
     * @return
     *     possible object is
     *     {@link Stream }
     *     
     */
    public Stream getSampleIndex() {
        return sampleIndex;
    }

    /**
     * Sets the value of the sampleIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link Stream }
     *     
     */
    public void setSampleIndex(Stream value) {
        this.sampleIndex = value;
    }

    /**
     * Gets the value of the essenceStream property.
     * 
     * @return
     *     possible object is
     *     {@link Stream }
     *     
     */
    public Stream getEssenceStream() {
        return essenceStream;
    }

    /**
     * Sets the value of the essenceStream property.
     * 
     * @param value
     *     allowed object is
     *     {@link Stream }
     *     
     */
    public void setEssenceStream(Stream value) {
        this.essenceStream = value;
    }

    /**
     * Gets the value of the followingIndexTable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getFollowingIndexTable() {
        return followingIndexTable;
    }

    /**
     * Sets the value of the followingIndexTable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFollowingIndexTable(Boolean value) {
        this.followingIndexTable = value;
    }

    /**
     * Gets the value of the isSparse property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getIsSparse() {
        return isSparse;
    }

    /**
     * Sets the value of the isSparse property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSparse(Boolean value) {
        this.isSparse = value;
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
     * Gets the value of the uid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUid() {
        return uid;
    }

    /**
     * Sets the value of the uid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUid(String value) {
        this.uid = value;
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