/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.google.common.collect;

final class Hashing {
    private Hashing() {
    }

    static int smear(int n) {
        n ^= n >>> 20 ^ n >>> 12;
        return n >>> 7 ^ n ^ n >>> 4;
    }
}

