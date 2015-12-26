/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package org.w3c.dom.smil;

import org.w3c.dom.DOMException;
import org.w3c.dom.smil.TimeList;

public interface ElementTime {
    public boolean beginElement();

    public boolean endElement();

    public TimeList getBegin();

    public float getDur();

    public TimeList getEnd();

    public short getFill();

    public void pauseElement();

    public void resumeElement();

    public void seekElement(float var1);

    public void setDur(float var1) throws DOMException;
}

