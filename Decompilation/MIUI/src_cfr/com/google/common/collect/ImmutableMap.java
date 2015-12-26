/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Map$Entry
 */
package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.EmptyImmutableMap;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.RegularImmutableMap;
import com.google.common.collect.SingletonImmutableMap;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class ImmutableMap<K, V>
implements Map<K, V>,
Serializable {
    ImmutableMap() {
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder();
    }

    static <K, V> Map.Entry<K, V> entryOf(K k, V v) {
        return Maps.immutableEntry(Preconditions.checkNotNull(k, "null key"), Preconditions.checkNotNull(v, "null value"));
    }

    public static <K, V> ImmutableMap<K, V> of() {
        return EmptyImmutableMap.INSTANCE;
    }

    @Override
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsKey(Object object) {
        if (this.get(object) != null) {
            return true;
        }
        return false;
    }

    @Override
    public abstract ImmutableSet<Map.Entry<K, V>> entrySet();

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof Map) {
            object = (Map)object;
            return this.entrySet().equals(object.entrySet());
        }
        return false;
    }

    @Override
    public abstract V get(Object var1);

    @Override
    public int hashCode() {
        return this.entrySet().hashCode();
    }

    @Override
    public boolean isEmpty() {
        if (this.size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public abstract ImmutableSet<K> keySet();

    @Override
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final V remove(Object object) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return Maps.toStringImpl(this);
    }

    @Override
    public abstract ImmutableCollection<V> values();

    Object writeReplace() {
        return new SerializedForm(this);
    }

    public static class Builder<K, V> {
        final ArrayList<Map.Entry<K, V>> entries = Lists.newArrayList();

        private static <K, V> ImmutableMap<K, V> fromEntryList(List<Map.Entry<K, V>> list) {
            switch (list.size()) {
                default: {
                    return new RegularImmutableMap(list.toArray((T[])new Map.Entry[list.size()]));
                }
                case 0: {
                    return ImmutableMap.of();
                }
                case 1: 
            }
            return new SingletonImmutableMap<K, V>(Iterables.getOnlyElement(list));
        }

        public ImmutableMap<K, V> build() {
            return Builder.fromEntryList(this.entries);
        }

        public Builder<K, V> put(K k, V v) {
            this.entries.add(ImmutableMap.entryOf(k, v));
            return this;
        }
    }

    static class SerializedForm
    implements Serializable {
        private static final long serialVersionUID = 0;
        private final Object[] keys;
        private final Object[] values;

        SerializedForm(ImmutableMap<?, ?> object) {
            this.keys = new Object[object.size()];
            this.values = new Object[object.size()];
            int n = 0;
            for (Map.Entry entry : object.entrySet()) {
                this.keys[n] = entry.getKey();
                this.values[n] = entry.getValue();
                ++n;
            }
        }

        Object createMap(Builder<Object, Object> builder) {
            for (int i = 0; i < this.keys.length; ++i) {
                builder.put(this.keys[i], this.values[i]);
            }
            return builder.build();
        }

        Object readResolve() {
            return this.createMap(new Builder<Object, Object>());
        }
    }

}

