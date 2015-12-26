/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package org.w3c.dom.smil;

import org.w3c.dom.NodeList;
import org.w3c.dom.smil.ElementTime;

public interface ElementTimeContainer
extends ElementTime {
    public NodeList getActiveChildrenAt(float var1);

    public NodeList getTimeChildren();
}

