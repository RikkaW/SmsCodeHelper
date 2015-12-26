/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package org.w3c.dom.smil;

import org.w3c.dom.DOMException;
import org.w3c.dom.smil.ElementTime;
import org.w3c.dom.smil.SMILElement;

public interface SMILMediaElement
extends ElementTime,
SMILElement {
    public String getSrc();

    public void setSrc(String var1) throws DOMException;
}

