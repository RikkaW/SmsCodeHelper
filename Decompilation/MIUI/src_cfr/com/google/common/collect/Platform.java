/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Object
 */
package com.google.common.collect;

import com.google.common.collect.MapMaker;
import java.lang.reflect.Array;

class Platform {
    private Platform() {
    }

    static <T> T[] clone(T[] arrT) {
        return (Object[])arrT.clone();
    }

    static <T> T[] newArray(T[] arrT, int n) {
        return (Object[])Array.newInstance(arrT.getClass().getComponentType(), n);
    }

    static MapMaker tryWeakKeys(MapMaker mapMaker) {
        return mapMaker.weakKeys();
    }
}

