/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package org.apache.thrift.protocol;

public class TField {
    public final short id;
    public final String name;
    public final byte type;

    public TField() {
        this("", 0, 0);
    }

    public TField(String string2, byte by, short s) {
        this.name = string2;
        this.type = by;
        this.id = s;
    }

    public String toString() {
        return "<TField name:'" + this.name + "' type:" + this.type + " field-id:" + this.id + ">";
    }
}

