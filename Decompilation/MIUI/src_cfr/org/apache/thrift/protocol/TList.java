/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package org.apache.thrift.protocol;

public final class TList {
    public final byte elemType;
    public final int size;

    public TList() {
        this(0, 0);
    }

    public TList(byte by, int n) {
        this.elemType = by;
        this.size = n;
    }
}

