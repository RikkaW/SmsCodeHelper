/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.String
 */
package com.android.mms.dom;

import com.android.mms.dom.AttrImpl;
import com.android.mms.dom.NodeImpl;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

public abstract class DocumentImpl
extends NodeImpl
implements Document {
    public DocumentImpl() {
        super(null);
    }

    @Override
    public Node adoptNode(Node node) throws DOMException {
        throw new DOMException(9, null);
    }

    @Override
    public Attr createAttribute(String string) throws DOMException {
        return new AttrImpl(this, string);
    }

    @Override
    public Attr createAttributeNS(String string, String string2) throws DOMException {
        return null;
    }

    @Override
    public CDATASection createCDATASection(String string) throws DOMException {
        return null;
    }

    @Override
    public Comment createComment(String string) {
        return null;
    }

    @Override
    public DocumentFragment createDocumentFragment() {
        return null;
    }

    @Override
    public Element createElementNS(String string, String string2) throws DOMException {
        return null;
    }

    @Override
    public EntityReference createEntityReference(String string) throws DOMException {
        return null;
    }

    @Override
    public ProcessingInstruction createProcessingInstruction(String string, String string2) throws DOMException {
        return null;
    }

    @Override
    public Text createTextNode(String string) {
        return null;
    }

    @Override
    public DocumentType getDoctype() {
        return null;
    }

    @Override
    public String getDocumentURI() {
        return null;
    }

    @Override
    public DOMConfiguration getDomConfig() {
        throw new DOMException(9, null);
    }

    @Override
    public Element getElementById(String string) {
        return null;
    }

    @Override
    public NodeList getElementsByTagName(String string) {
        return null;
    }

    @Override
    public NodeList getElementsByTagNameNS(String string, String string2) {
        return null;
    }

    @Override
    public DOMImplementation getImplementation() {
        return null;
    }

    @Override
    public String getInputEncoding() {
        return null;
    }

    @Override
    public String getNodeName() {
        return "#document";
    }

    @Override
    public short getNodeType() {
        return 9;
    }

    @Override
    public boolean getStrictErrorChecking() {
        return true;
    }

    @Override
    public String getXmlEncoding() {
        return null;
    }

    @Override
    public boolean getXmlStandalone() {
        return false;
    }

    @Override
    public String getXmlVersion() {
        return null;
    }

    @Override
    public Node importNode(Node node, boolean bl) throws DOMException {
        return null;
    }

    @Override
    public void normalizeDocument() {
        throw new DOMException(9, null);
    }

    @Override
    public Node renameNode(Node node, String string, String string2) throws DOMException {
        throw new DOMException(9, null);
    }

    @Override
    public void setDocumentURI(String string) {
    }

    @Override
    public void setStrictErrorChecking(boolean bl) {
    }

    @Override
    public void setXmlStandalone(boolean bl) throws DOMException {
    }

    @Override
    public void setXmlVersion(String string) throws DOMException {
    }
}

