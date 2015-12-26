/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package org.w3c.dom.smil;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

public interface SMILElement
extends Element {
    public String getId();

    public void setId(String var1) throws DOMException;
}

