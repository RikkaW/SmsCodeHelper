/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package org.w3c.dom.smil;

import org.w3c.dom.DOMException;

public interface ElementLayout {
    public String getBackgroundColor();

    public int getHeight();

    public int getWidth();

    public void setBackgroundColor(String var1) throws DOMException;

    public void setHeight(int var1) throws DOMException;

    public void setWidth(int var1) throws DOMException;
}

