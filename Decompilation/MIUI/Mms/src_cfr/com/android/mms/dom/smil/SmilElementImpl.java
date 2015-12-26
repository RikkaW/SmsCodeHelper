/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.String
 */
package com.android.mms.dom.smil;

import com.android.mms.dom.DocumentImpl;
import com.android.mms.dom.ElementImpl;
import com.android.mms.dom.smil.SmilDocumentImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.smil.SMILElement;

public class SmilElementImpl
extends ElementImpl
implements SMILElement {
    SmilElementImpl(SmilDocumentImpl smilDocumentImpl, String string) {
        super(smilDocumentImpl, string.toLowerCase());
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String string) throws DOMException {
    }
}

