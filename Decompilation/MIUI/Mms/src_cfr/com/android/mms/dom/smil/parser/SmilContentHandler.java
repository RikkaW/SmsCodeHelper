/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.dom.smil.parser;

import com.android.mms.dom.smil.SmilDocumentImpl;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.smil.SMILDocument;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SmilContentHandler
extends DefaultHandler {
    private Node mCurrentNode;
    private SMILDocument mSmilDocument;

    @Override
    public void characters(char[] arrc, int n, int n2) {
    }

    @Override
    public void endElement(String string, String string2, String string3) {
        this.mCurrentNode = this.mCurrentNode.getParentNode();
    }

    public SMILDocument getSmilDocument() {
        return this.mSmilDocument;
    }

    public void reset() {
        this.mCurrentNode = this.mSmilDocument = new SmilDocumentImpl();
    }

    @Override
    public void startElement(String object, String string, String string2, Attributes attributes) {
        object = this.mSmilDocument.createElement(string);
        if (attributes != null) {
            for (int i = 0; i < attributes.getLength(); ++i) {
                object.setAttribute(attributes.getLocalName(i), attributes.getValue(i));
            }
        }
        this.mCurrentNode.appendChild((Node)object);
        this.mCurrentNode = object;
    }
}

