/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.google.common.collect;

import com.google.common.collect.AbstractMapEntry;
import java.io.Serializable;

class ImmutableEntry<K, V>
extends AbstractMapEntry<K, V>
implements Serializable {
    private static final long serialVersionUID = 0;
    private final K key;
    private final V value;

    ImmutableEntry(K k, V v) {
        this.key = k;
        this.value = v;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    @Override
    public final V setValue(V v) {
        throw new UnsupportedOperationException();
    }
}

