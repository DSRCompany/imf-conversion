package com.netflix.imfutility.dpp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Alexandr on 5/6/2016.
 * A helper to get human readable errors of metadata.xml loading and parsing.
 */
public class MetadataXmlParsingHandler implements ContentHandler, ErrorHandler {

    final Logger logger = LoggerFactory.getLogger(MetadataXmlParsingHandler.class);

    /**
     * Unmarshaller content handler that actually parses the metadata.xml.
     */
    private ContentHandler contentHandler;
    /**
     * A node name that is being processed
     */
    private String qname;

    /**
     * A stack of current parsed nodes.
     */
    private Stack<String> qnames = new Stack<String>();
    /**
     * A collection of all found errors.
     */
    private List<String> errorMessages = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param contentHandler Unmarshaller content handler that actually parses the metadata.xml.
     */
    public MetadataXmlParsingHandler(ContentHandler contentHandler) {
        this.contentHandler = contentHandler;
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        contentHandler.characters(ch, start, length);
    }

    public void endDocument() throws SAXException {
        contentHandler.endDocument();
    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        qnames.pop();
        qname = qnames.size() > 0 ? qnames.lastElement() : "root";
        contentHandler.endElement(uri, localName, qName);
    }

    public void endPrefixMapping(String prefix) throws SAXException {
        contentHandler.endPrefixMapping(prefix);
    }

    public void ignorableWhitespace(char[] ch, int start, int length)
            throws SAXException {
        contentHandler.ignorableWhitespace(ch, start, length);
    }

    public void processingInstruction(String target, String data)
            throws SAXException {
        contentHandler.processingInstruction(target, data);
    }

    public void setDocumentLocator(Locator locator) {
        contentHandler.setDocumentLocator(locator);
    }

    public void skippedEntity(String name) throws SAXException {
        contentHandler.skippedEntity(name);
    }

    public void startDocument() throws SAXException {
        contentHandler.startDocument();
    }

    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        qnames.push(qName);
        qname = qName;
        contentHandler.startElement(uri, localName, qName, atts);
    }

    public void startPrefixMapping(String prefix, String uri)
            throws SAXException {
        contentHandler.startPrefixMapping(prefix, uri);
    }

    public void error(SAXParseException exception) throws SAXException {
        registerError(exception);
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        registerError(exception);
    }

    public void warning(SAXParseException exception) throws SAXException {
        registerError(exception);
    }

    /**
     * Returns all found errors.
     *
     * @return a collection with all found errors.
     */
    public List<String> getParsingErrors() {
        return errorMessages;
    }

    /**
     * Registers error.
     *
     * @param exception current SAXParseException with error description.
     */
    private void registerError(SAXParseException exception) {
        StringBuilder errorMessage = new StringBuilder();
        ;
        errorMessage.append("Line ").append(exception.getLineNumber()).append(", ");
        errorMessage.append("column ").append(exception.getColumnNumber());

        if (qname != null) {
            errorMessage.append(", ").append("node <").append(qname).append(">");
        }
        errorMessage.append(". ").append(exception.getLocalizedMessage());
        errorMessages.add(errorMessage.toString());
        logger.error(errorMessage.toString());
    }
}
