/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.String
 */
package com.android.mms.dom;

import com.android.mms.dom.DocumentImpl;
import com.android.mms.dom.NamedNodeMapImpl;
import com.android.mms.dom.NodeImpl;
import com.android.mms.dom.NodeListImpl;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;

public class ElementImpl
extends NodeImpl
implements Element {
    private NamedNodeMap mAttributes = new NamedNodeMapImpl();
    private String mTagName;

    protected ElementImpl(DocumentImpl documentImpl, String string) {
        super(documentImpl);
        this.mTagName = string;
    }

    @Override
    public String getAttribute(String string) {
        Attr attr = this.getAttributeNode(string);
        string = "";
        if (attr != null) {
            string = attr.getValue();
        }
        return string;
    }

    @Override
    public String getAttributeNS(String string, String string2) {
        return null;
    }

    @Override
    public Attr getAttributeNode(String string) {
        return (Attr)this.mAttributes.getNamedItem(string);
    }

    @Override
    public Attr getAttributeNodeNS(String string, String string2) {
        return null;
    }

    @Override
    public NamedNodeMap getAttributes() {
        return this.mAttributes;
    }

    @Override
    public NodeList getElementsByTagName(String string) {
        return new NodeListImpl(this, string, true);
    }

    @Override
    public NodeList getElementsByTagNameNS(String string, String string2) {
        return null;
    }

    @Override
    public String getNodeName() {
        return this.mTagName;
    }

    @Override
    public short getNodeType() {
        return 1;
    }

    @Override
    public TypeInfo getSchemaTypeInfo() {
        return null;
    }

    @Override
    public String getTagName() {
        return this.mTagName;
    }

    @Override
    public boolean hasAttribute(String string) {
        if (this.getAttributeNode(string) != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasAttributeNS(String string, String string2) {
        return false;
    }

    @Override
    public boolean hasAttributes() {
        if (this.mAttributes.getLength() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void removeAttribute(String string) throws DOMException {
    }

    @Override
    public void removeAttributeNS(String string, String string2) throws DOMException {
    }

    @Override
    public Attr removeAttributeNode(Attr attr) throws DOMException {
        return null;
    }

    @Override
    public void setAttribute(String string, String string2) throws DOMException {
        Attr attr;
        Attr attr2 = attr = this.getAttributeNode(string);
        if (attr == null) {
            attr2 = this.mOwnerDocument.createAttribute(string);
        }
        attr2.setNodeValue(string2);
        this.mAttributes.setNamedItem(attr2);
    }

    @Override
    public void setAttributeNS(String string, String string2, String string3) throws DOMException {
    }

    @Override
    public Attr setAttributeNode(Attr attr) throws DOMException {
        return null;
    }

    @Override
    public Attr setAttributeNodeNS(Attr attr) throws DOMException {
        return null;
    }

    @Override
    public void setIdAttribute(String string, boolean bl) throws DOMException {
        throw new DOMException(9, null);
    }

    @Override
    public void setIdAttributeNS(String string, String string2, boolean bl) throws DOMException {
        throw new DOMException(9, null);
    }

    @Override
    public void setIdAttributeNode(Attr attr, boolean bl) throws DOMException {
        throw new DOMException(9, null);
    }
}

