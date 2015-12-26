/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.String
 */
package com.android.mms.dom;

import com.android.mms.dom.DocumentImpl;
import com.android.mms.dom.NodeImpl;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.TypeInfo;

public class AttrImpl
extends NodeImpl
implements Attr {
    private String mName;
    private String mValue;

    protected AttrImpl(DocumentImpl documentImpl, String string) {
        super(documentImpl);
        this.mName = string;
    }

    @Override
    public String getName() {
        return this.mName;
    }

    @Override
    public Node getNextSibling() {
        return null;
    }

    @Override
    public String getNodeName() {
        return this.mName;
    }

    @Override
    public short getNodeType() {
        return 2;
    }

    @Override
    public Element getOwnerElement() {
        return null;
    }

    @Override
    public Node getParentNode() {
        return null;
    }

    @Override
    public Node getPreviousSibling() {
        return null;
    }

    @Override
    public TypeInfo getSchemaTypeInfo() {
        return null;
    }

    @Override
    public boolean getSpecified() {
        if (this.mValue != null) {
            return true;
        }
        return false;
    }

    @Override
    public String getValue() {
        return this.mValue;
    }

    @Override
    public boolean isId() {
        return false;
    }

    @Override
    public void setNodeValue(String string) throws DOMException {
        this.setValue(string);
    }

    @Override
    public void setValue(String string) throws DOMException {
        this.mValue = string;
    }
}

