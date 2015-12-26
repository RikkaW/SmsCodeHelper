/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package org.apache.thrift.protocol;

import org.apache.thrift.protocol.TList;

public final class TSet {
    public final byte elemType;
    public final int size;

    public TSet() {
        this(0, 0);
    }

    public TSet(byte by, int n) {
        this.elemType = by;
        this.size = n;
    }

    public TSet(TList tList) {
        this(tList.elemType, tList.size);
    }
}

