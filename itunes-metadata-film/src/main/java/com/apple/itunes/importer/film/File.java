//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7-b41 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.09.01 at 05:28:13 PM MSK 
//


package com.apple.itunes.importer.film;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for File complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="File">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{http://apple.com/itunes/importer}size"/>
 *         &lt;element ref="{http://apple.com/itunes/importer}file_name"/>
 *         &lt;element ref="{http://apple.com/itunes/importer}checksum"/>
 *         &lt;element ref="{http://apple.com/itunes/importer}read_only_info"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "File", propOrder = {
    "sizeOrFileNameOrChecksum"
})
@XmlSeeAlso({
    ExtraScreenshot.class
})
public class File {

    @XmlElementRefs({
        @XmlElementRef(name = "size", namespace = "http://apple.com/itunes/importer", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "checksum", namespace = "http://apple.com/itunes/importer", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "file_name", namespace = "http://apple.com/itunes/importer", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "read_only_info", namespace = "http://apple.com/itunes/importer", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> sizeOrFileNameOrChecksum;

    /**
     * Gets the value of the sizeOrFileNameOrChecksum property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sizeOrFileNameOrChecksum property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSizeOrFileNameOrChecksum().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link ReadOnlyInfo }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link Checksum }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getSizeOrFileNameOrChecksum() {
        if (sizeOrFileNameOrChecksum == null) {
            sizeOrFileNameOrChecksum = new ArrayList<JAXBElement<?>>();
        }
        return this.sizeOrFileNameOrChecksum;
    }

}