/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.util.HashMap
 */
package com.xiaomi.mms.utils;

import java.util.HashMap;

public class EasyMap<K, V>
extends HashMap<K, V> {
    public EasyMap() {
    }

    public EasyMap(K k, V v) {
        this.put(k, v);
    }

    public EasyMap<K, V> easyPut(K k, V v) {
        this.put(k, v);
        return this;
    }
}

