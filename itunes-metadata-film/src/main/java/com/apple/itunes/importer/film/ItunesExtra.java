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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ItunesExtra complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ItunesExtra">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{http://apple.com/itunes/importer}read_only_info"/>
 *         &lt;element ref="{http://apple.com/itunes/importer}vendor_id"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://apple.com/itunes/importer}content_item"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://apple.com/itunes/importer}release_date"/>
 *         &lt;element ref="{http://apple.com/itunes/importer}cleared_for_preorder"/>
 *         &lt;element ref="{http://apple.com/itunes/importer}disable_audio_in_store"/>
 *         &lt;element ref="{http://apple.com/itunes/importer}eidr"/>
 *         &lt;element ref="{http://apple.com/itunes/importer}rootnodes"/>
 *         &lt;element ref="{http://apple.com/itunes/importer}scene_groups"/>
 *         &lt;element ref="{http://apple.com/itunes/importer}scenes"/>
 *         &lt;element ref="{http://apple.com/itunes/importer}galleries"/>
 *         &lt;element ref="{http://apple.com/itunes/importer}assets"/>
 *         &lt;element name="locales">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="locale" type="{http://apple.com/itunes/importer}ItunesExtraLocale" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ItunesExtra", propOrder = {
    "readOnlyInfoOrVendorIdOrTitle"
})
public class ItunesExtra {

    @XmlElementRefs({
        @XmlElementRef(name = "rootnodes", namespace = "http://apple.com/itunes/importer", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "cleared_for_preorder", namespace = "http://apple.com/itunes/importer", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "disable_audio_in_store", namespace = "http://apple.com/itunes/importer", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "scenes", namespace = "http://apple.com/itunes/importer", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "scene_groups", namespace = "http://apple.com/itunes/importer", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "content_item", namespace = "http://apple.com/itunes/importer", type = ContentItem.class, required = false),
        @XmlElementRef(name = "title", namespace = "http://apple.com/itunes/importer", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "locales", namespace = "http://apple.com/itunes/importer", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "vendor_id", namespace = "http://apple.com/itunes/importer", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "description", namespace = "http://apple.com/itunes/importer", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "release_date", namespace = "http://apple.com/itunes/importer", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "galleries", namespace = "http://apple.com/itunes/importer", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "assets", namespace = "http://apple.com/itunes/importer", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "eidr", namespace = "http://apple.com/itunes/importer", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "read_only_info", namespace = "http://apple.com/itunes/importer", type = JAXBElement.class, required = false)
    })
    protected List<Object> readOnlyInfoOrVendorIdOrTitle;

    /**
     * Gets the value of the readOnlyInfoOrVendorIdOrTitle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the readOnlyInfoOrVendorIdOrTitle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReadOnlyInfoOrVendorIdOrTitle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link RootnodeList }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link SceneCollection }{@code >}
     * {@link JAXBElement }{@code <}{@link SceneGroupCollection }{@code >}
     * {@link ContentItem }
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link ItunesExtra.Locales }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link GalleryList }{@code >}
     * {@link JAXBElement }{@code <}{@link AssetList }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link ReadOnlyInfo }{@code >}
     * 
     * 
     */
    public List<Object> getReadOnlyInfoOrVendorIdOrTitle() {
        if (readOnlyInfoOrVendorIdOrTitle == null) {
            readOnlyInfoOrVendorIdOrTitle = new ArrayList<Object>();
        }
        return this.readOnlyInfoOrVendorIdOrTitle;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="locale" type="{http://apple.com/itunes/importer}ItunesExtraLocale" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "locale"
    })
    public static class Locales {

        protected List<ItunesExtraLocale> locale;

        /**
         * Gets the value of the locale property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the locale property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getLocale().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ItunesExtraLocale }
         * 
         * 
         */
        public List<ItunesExtraLocale> getLocale() {
            if (locale == null) {
                locale = new ArrayList<ItunesExtraLocale>();
            }
            return this.locale;
        }

    }

}