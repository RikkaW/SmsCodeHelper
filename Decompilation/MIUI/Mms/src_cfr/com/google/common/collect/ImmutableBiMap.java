/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Map$Entry
 */
package com.google.common.collect;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableBiMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public abstract class ImmutableBiMap<K, V>
extends ImmutableMap<K, V>
implements BiMap<K, V> {
    private static final ImmutableBiMap<Object, Object> EMPTY_IMMUTABLE_BIMAP = new EmptyBiMap();

    ImmutableBiMap() {
    }

    public static <K, V> ImmutableBiMap<K, V> of() {
        return EMPTY_IMMUTABLE_BIMAP;
    }

    @Override
    public boolean containsKey(Object object) {
        return this.delegate().containsKey(object);
    }

    @Override
    public boolean containsValue(Object object) {
        return this.inverse().containsKey(object);
    }

    abstract ImmutableMap<K, V> delegate();

    @Override
    public ImmutableSet<Map.Entry<K, V>> entrySet() {
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

    public abstract ImmutableBiMap<V, K> inverse();

    @Override
    public boolean isEmpty() {
        return this.delegate().isEmpty();
    }

    @Override
    public ImmutableSet<K> keySet() {
        return this.delegate().keySet();
    }

    @Override
    public int size() {
        return this.delegate().size();
    }

    @Override
    public String toString() {
        return this.delegate().toString();
    }

    @Override
    public ImmutableSet<V> values() {
        return this.inverse().keySet();
    }

    @Override
    Object writeReplace() {
        return new SerializedForm(this);
    }

    public static final class Builder<K, V>
    extends ImmutableMap.Builder<K, V> {
        @Override
        public ImmutableBiMap<K, V> build() {
            ImmutableMap immutableMap = super.build();
            if (immutableMap.isEmpty()) {
                return ImmutableBiMap.of();
            }
            return new RegularImmutableBiMap(immutableMap);
        }

        @Override
        public Builder<K, V> put(K k, V v) {
            super.put(k, v);
            return this;
        }
    }

    static class EmptyBiMap
    extends ImmutableBiMap<Object, Object> {
        EmptyBiMap() {
        }

        @Override
        ImmutableMap<Object, Object> delegate() {
            return ImmutableMap.of();
        }

        @Override
        public ImmutableBiMap<Object, Object> inverse() {
            return this;
        }

        Object readResolve() {
            return EMPTY_IMMUTABLE_BIMAP;
        }
    }

    private static class SerializedForm
    extends ImmutableMap.SerializedForm {
        private static final long serialVersionUID = 0;

        SerializedForm(ImmutableBiMap<?, ?> immutableBiMap) {
            super(immutableBiMap);
        }

        @Override
        Object readResolve() {
            return this.createMap(new Builder<Object, Object>());
        }
    }

}

