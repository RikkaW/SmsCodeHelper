/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.google.common.primitives;

public final class Ints {
    private Ints() {
    }

    public static int saturatedCast(long l) {
        if (l > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (l < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int)l;
    }
}

