/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.util.Map$Entry
 */
package com.google.common.collect;

import com.google.common.collect.ForwardingObject;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public abstract class ForwardingMap<K, V>
extends ForwardingObject
implements Map<K, V> {
    protected ForwardingMap() {
    }

    @Override
    public void clear() {
        this.delegate().clear();
    }

    @Override
    public boolean containsKey(Object object) {
        return this.delegate().containsKey(object);
    }

    @Override
    public boolean containsValue(Object object) {
        return this.delegate().containsValue(object);
    }

    @Override
    protected abstract Map<K, V> delegate();

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return this.delegate().entrySet();
    }

    @Override
    public boolean equals(Object object) {
        if (object == this || this.delegate().equals(object)) {
            return true;
        }
        return false;
    }

    @Override
    public V get(Object object) {
        return this.delegate().get(object);
    }

    @Override
    public int hashCode() {
        return this.delegate().hashCode();
    }

    @Override
    public boolean isEmpty() {
        return this.delegate().isEmpty();
    }

    @Override
    public Set<K> keySet() {
        return this.delegate().keySet();
    }

    @Override
    public V put(K k, V v) {
        return this.delegate().put(k, v);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        this.delegate().putAll(map);
    }

    @Override
    public V remove(Object object) {
        return this.delegate().remove(object);
    }

    @Override
    public int size() {
        return this.delegate().size();
    }

    @Override
    public Collection<V> values() {
        return this.delegate().values();
    }
}

