/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.util.Map$Entry
 */
package com.google.common.collect;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Set;

class RegularImmutableBiMap<K, V>
extends ImmutableBiMap<K, V> {
    final transient ImmutableMap<K, V> delegate;
    final transient ImmutableBiMap<V, K> inverse;

    RegularImmutableBiMap(ImmutableMap<K, V> object) {
        this.delegate = object;
        ImmutableMap.Builder<Object, Object> builder = ImmutableMap.builder();
        for (Map.Entry entry : object.entrySet()) {
            builder.put(entry.getValue(), entry.getKey());
        }
        this.inverse = new RegularImmutableBiMap<V, K>(builder.build(), this);
    }

    RegularImmutableBiMap(ImmutableMap<K, V> immutableMap, ImmutableBiMap<V, K> immutableBiMap) {
        this.delegate = immutableMap;
        this.inverse = immutableBiMap;
    }

    @Override
    ImmutableMap<K, V> delegate() {
        return this.delegate;
    }

    @Override
    public ImmutableBiMap<V, K> inverse() {
        return this.inverse;
    }
}

