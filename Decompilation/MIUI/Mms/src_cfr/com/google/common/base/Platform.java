/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.System
 *  java.lang.ThreadLocal
 */
package com.google.common.base;

final class Platform {
    private static final ThreadLocal<char[]> DEST_TL = new ThreadLocal<char[]>(){

        protected char[] initialValue() {
            return new char[1024];
        }
    };

    private Platform() {
    }

    static long systemNanoTime() {
        return System.nanoTime();
    }

}

