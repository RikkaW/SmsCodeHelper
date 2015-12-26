/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package org.w3c.dom.smil;

import org.w3c.dom.NodeList;
import org.w3c.dom.smil.SMILElement;
import org.w3c.dom.smil.SMILRootLayoutElement;

public interface SMILLayoutElement
extends SMILElement {
    public NodeList getRegions();

    public SMILRootLayoutElement getRootLayout();
}

