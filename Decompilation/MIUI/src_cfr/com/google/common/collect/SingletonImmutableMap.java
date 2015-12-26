/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Map$Entry
 */
package com.google.common.collect;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

final class SingletonImmutableMap<K, V>
extends ImmutableMap<K, V> {
    private transient Map.Entry<K, V> entry;
    private transient ImmutableSet<Map.Entry<K, V>> entrySet;
    private transient ImmutableSet<K> keySet;
    final transient K singleKey;
    final transient V singleValue;
    private transient ImmutableCollection<V> values;

    SingletonImmutableMap(Map.Entry<K, V> entry) {
        this.entry = entry;
        this.singleKey = entry.getKey();
        this.singleValue = entry.getValue();
    }

    private Map.Entry<K, V> entry() {
        Map.Entry<K, V> entry;
        Map.Entry<K, V> entry2 = entry = this.entry;
        if (entry == null) {
            this.entry = entry2 = Maps.immutableEntry(this.singleKey, this.singleValue);
        }
        return entry2;
    }

    @Override
    public boolean containsKey(Object object) {
        return this.singleKey.equals(object);
    }

    @Override
    public boolean containsValue(Object object) {
        return this.singleValue.equals(object);
    }

    @Override
    public ImmutableSet<Map.Entry<K, V>> entrySet() {
        ImmutableSet<Map.Entry<K, V>> immutableSet;
        ImmutableSet<Map.Entry<K, V>> immutableSet2 = immutableSet = this.entrySet;
        if (immutableSet == null) {
            this.entrySet = immutableSet2 = ImmutableSet.of(this.entry());
        }
        return immutableSet2;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public boolean equals(Object entry) {
        if (entry == this) {
            return true;
        }
        if (!(entry instanceof Map)) return false;
        if ((entry = (Map)entry).size() != 1) {
            return false;
        }
        if (!this.singleKey.equals((entry = entry.entrySet().iterator().next()).getKey())) return false;
        if (this.singleValue.equals(entry.getValue())) return true;
        return false;
    }

    @Override
    public V get(Object object) {
        if (this.singleKey.equals(object)) {
            return this.singleValue;
        }
        return null;
    }

    @Override
    public int hashCode() {
        return this.singleKey.hashCode() ^ this.singleValue.hashCode();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ImmutableSet<K> keySet() {
        ImmutableSet<K> immutableSet;
        ImmutableSet<K> immutableSet2 = immutableSet = this.keySet;
        if (immutableSet == null) {
            this.keySet = immutableSet2 = ImmutableSet.of(this.singleKey);
        }
        return immutableSet2;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public String toString() {
        return "" + '{' + this.singleKey.toString() + '=' + this.singleValue.toString() + '}';
    }

    @Override
    public ImmutableCollection<V> values() {
        ImmutableCollection<V> immutableCollection;
        ImmutableCollection<V> immutableCollection2 = immutableCollection = this.values;
        if (immutableCollection == null) {
            this.values = immutableCollection2 = new Values<V>(this.singleValue);
        }
        return immutableCollection2;
    }

    private static class Values<V>
    extends ImmutableCollection<V> {
        final V singleValue;

        Values(V v) {
            this.singleValue = v;
        }

        @Override
        public boolean contains(Object object) {
            return this.singleValue.equals(object);
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public UnmodifiableIterator<V> iterator() {
            return Iterators.singletonIterator(this.singleValue);
        }

        @Override
        public int size() {
            return 1;
        }
    }

}

