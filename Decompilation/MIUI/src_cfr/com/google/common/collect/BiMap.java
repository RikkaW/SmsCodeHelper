/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.google.common.collect;

import java.util.Map;
import java.util.Set;

public interface BiMap<K, V>
extends Map<K, V> {
    @Override
    public Set<V> values();
}

