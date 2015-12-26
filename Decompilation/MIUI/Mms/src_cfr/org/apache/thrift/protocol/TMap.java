/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package org.apache.thrift.protocol;

public final class TMap {
    public final byte keyType;
    public final int size;
    public final byte valueType;

    public TMap() {
        this(0, 0, 0);
    }

    public TMap(byte by, byte by2, int n) {
        this.keyType = by;
        this.valueType = by2;
        this.size = n;
    }
}

