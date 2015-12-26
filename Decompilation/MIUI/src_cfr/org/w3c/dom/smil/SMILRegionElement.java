/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package org.w3c.dom.smil;

import org.w3c.dom.DOMException;
import org.w3c.dom.smil.ElementLayout;
import org.w3c.dom.smil.SMILElement;

public interface SMILRegionElement
extends ElementLayout,
SMILElement {
    public String getFit();

    public int getLeft();

    public int getTop();

    public void setFit(String var1) throws DOMException;

    public void setLeft(int var1) throws DOMException;

    public void setTop(int var1) throws DOMException;
}

