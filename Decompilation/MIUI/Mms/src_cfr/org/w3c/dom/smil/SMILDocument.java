/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package org.w3c.dom.smil;

import org.w3c.dom.Document;
import org.w3c.dom.smil.ElementSequentialTimeContainer;
import org.w3c.dom.smil.SMILElement;
import org.w3c.dom.smil.SMILLayoutElement;

public interface SMILDocument
extends Document,
ElementSequentialTimeContainer {
    public SMILElement getBody();

    public SMILElement getHead();

    public SMILLayoutElement getLayout();
}

