/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.String
 */
package org.w3c.dom.events;

public class EventException
extends RuntimeException {
    public short code;

    public EventException(short s, String string2) {
        super(string2);
        this.code = s;
    }
}

