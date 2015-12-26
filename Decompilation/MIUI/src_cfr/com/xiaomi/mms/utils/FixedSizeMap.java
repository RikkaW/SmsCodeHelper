/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.util.Map$Entry
 */
package com.xiaomi.mms.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class FixedSizeMap<K, V>
extends LinkedHashMap<K, V> {
    private int mMaxSize;

    public FixedSizeMap(int n, int n2) {
        super(n);
        this.mMaxSize = n2;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
        if (this.size() > this.mMaxSize) {
            return true;
        }
        return false;
    }
}

