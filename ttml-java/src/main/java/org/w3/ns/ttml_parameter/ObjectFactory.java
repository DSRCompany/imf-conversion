//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.11 at 04:29:49 PM MSK 
//


package org.w3.ns.ttml_parameter;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.w3.ns.ttml_parameter package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Feature_QNAME = new QName("http://www.w3.org/ns/ttml#parameter", "feature");
    private final static QName _Features_QNAME = new QName("http://www.w3.org/ns/ttml#parameter", "features");
    private final static QName _Extensions_QNAME = new QName("http://www.w3.org/ns/ttml#parameter", "extensions");
    private final static QName _Profile_QNAME = new QName("http://www.w3.org/ns/ttml#parameter", "profile");
    private final static QName _Extension_QNAME = new QName("http://www.w3.org/ns/ttml#parameter", "extension");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.w3.ns.ttml_parameter
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FeaturesEltype }
     * 
     */
    public FeaturesEltype createFeaturesEltype() {
        return new FeaturesEltype();
    }

    /**
     * Create an instance of {@link ExtensionsEltype }
     * 
     */
    public ExtensionsEltype createExtensionsEltype() {
        return new ExtensionsEltype();
    }

    /**
     * Create an instance of {@link ExtensionEltype }
     * 
     */
    public ExtensionEltype createExtensionEltype() {
        return new ExtensionEltype();
    }

    /**
     * Create an instance of {@link FeatureEltype }
     * 
     */
    public FeatureEltype createFeatureEltype() {
        return new FeatureEltype();
    }

    /**
     * Create an instance of {@link ProfileEltype }
     * 
     */
    public ProfileEltype createProfileEltype() {
        return new ProfileEltype();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FeatureEltype }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/ttml#parameter", name = "feature")
    public JAXBElement<FeatureEltype> createFeature(FeatureEltype value) {
        return new JAXBElement<FeatureEltype>(_Feature_QNAME, FeatureEltype.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FeaturesEltype }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/ttml#parameter", name = "features")
    public JAXBElement<FeaturesEltype> createFeatures(FeaturesEltype value) {
        return new JAXBElement<FeaturesEltype>(_Features_QNAME, FeaturesEltype.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExtensionsEltype }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/ttml#parameter", name = "extensions")
    public JAXBElement<ExtensionsEltype> createExtensions(ExtensionsEltype value) {
        return new JAXBElement<ExtensionsEltype>(_Extensions_QNAME, ExtensionsEltype.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProfileEltype }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/ttml#parameter", name = "profile")
    public JAXBElement<ProfileEltype> createProfile(ProfileEltype value) {
        return new JAXBElement<ProfileEltype>(_Profile_QNAME, ProfileEltype.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExtensionEltype }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/ttml#parameter", name = "extension")
    public JAXBElement<ExtensionEltype> createExtension(ExtensionEltype value) {
        return new JAXBElement<ExtensionEltype>(_Extension_QNAME, ExtensionEltype.class, null, value);
    }

}
