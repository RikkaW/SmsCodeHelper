/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.String
 */
package com.android.mms.dom.smil;

import com.android.mms.dom.smil.SmilDocumentImpl;
import com.android.mms.dom.smil.SmilElementImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.smil.SMILRootLayoutElement;

public class SmilRootLayoutElementImpl
extends SmilElementImpl
implements SMILRootLayoutElement {
    SmilRootLayoutElementImpl(SmilDocumentImpl smilDocumentImpl, String string) {
        super(smilDocumentImpl, string);
    }

    private int parseAbsoluteLength(String string) {
        String string2 = string;
        if (string.endsWith("px")) {
            string2 = string.substring(0, string.indexOf("px"));
        }
        try {
            int n = Integer.parseInt((String)string2);
            return n;
        }
        catch (NumberFormatException var1_2) {
            return 0;
        }
    }

    @Override
    public String getBackgroundColor() {
        return this.getAttribute("backgroundColor");
    }

    @Override
    public int getHeight() {
        return this.parseAbsoluteLength(this.getAttribute("height"));
    }

    @Override
    public int getWidth() {
        return this.parseAbsoluteLength(this.getAttribute("width"));
    }

    @Override
    public void setBackgroundColor(String string) throws DOMException {
        this.setAttribute("backgroundColor", string);
    }

    @Override
    public void setHeight(int n) throws DOMException {
        this.setAttribute("height", String.valueOf((int)n) + "px");
    }

    @Override
    public void setWidth(int n) throws DOMException {
        this.setAttribute("width", String.valueOf((int)n) + "px");
    }
}

