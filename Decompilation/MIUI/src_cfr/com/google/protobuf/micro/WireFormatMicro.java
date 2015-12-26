/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.google.protobuf.micro;

public final class WireFormatMicro {
    static final int MESSAGE_SET_ITEM_END_TAG;
    static final int MESSAGE_SET_ITEM_TAG;
    static final int MESSAGE_SET_MESSAGE_TAG;
    static final int MESSAGE_SET_TYPE_ID_TAG;

    static {
        MESSAGE_SET_ITEM_TAG = WireFormatMicro.makeTag(1, 3);
        MESSAGE_SET_ITEM_END_TAG = WireFormatMicro.makeTag(1, 4);
        MESSAGE_SET_TYPE_ID_TAG = WireFormatMicro.makeTag(2, 0);
        MESSAGE_SET_MESSAGE_TAG = WireFormatMicro.makeTag(3, 2);
    }

    private WireFormatMicro() {
    }

    public static int getTagFieldNumber(int n) {
        return n >>> 3;
    }

    static int getTagWireType(int n) {
        return n & 7;
    }

    static int makeTag(int n, int n2) {
        return n << 3 | n2;
    }
}

