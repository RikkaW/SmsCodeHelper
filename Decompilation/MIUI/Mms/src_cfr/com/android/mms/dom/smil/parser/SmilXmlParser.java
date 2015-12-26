/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  com.google.android.mms.MmsException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  org.xml.sax.SAXException
 *  org.xml.sax.helpers.XMLReaderFactory
 */
package com.android.mms.dom.smil.parser;

import com.android.mms.dom.smil.parser.SmilContentHandler;
import com.google.android.mms.MmsException;
import java.io.IOException;
import java.io.InputStream;
import org.w3c.dom.smil.SMILDocument;
import org.w3c.dom.smil.SMILElement;
import org.w3c.dom.smil.SMILLayoutElement;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class SmilXmlParser {
    private SmilContentHandler mContentHandler;
    private XMLReader mXmlReader;

    public SmilXmlParser() throws MmsException {
        System.setProperty((String)"org.xml.sax.driver", (String)"org.xmlpull.v1.sax2.Driver");
        try {
            this.mXmlReader = XMLReaderFactory.createXMLReader();
            this.mContentHandler = new SmilContentHandler();
            this.mXmlReader.setContentHandler(this.mContentHandler);
            return;
        }
        catch (SAXException var1_1) {
            throw new MmsException((Throwable)var1_1);
        }
    }

    private void validateDocument(SMILDocument sMILDocument) {
        sMILDocument.getBody();
        sMILDocument.getLayout();
    }

    public SMILDocument parse(InputStream object) throws IOException, SAXException {
        this.mContentHandler.reset();
        this.mXmlReader.parse(new InputSource((InputStream)object));
        object = this.mContentHandler.getSmilDocument();
        this.validateDocument((SMILDocument)object);
        return object;
    }
}

