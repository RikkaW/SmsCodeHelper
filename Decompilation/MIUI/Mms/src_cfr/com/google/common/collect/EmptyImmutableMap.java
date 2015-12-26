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
import java.util.Collection;
import java.util.Map;
import java.util.Set;

final class EmptyImmutableMap
extends ImmutableMap<Object, Object> {
    static final EmptyImmutableMap INSTANCE = new EmptyImmutableMap();
    private static final long serialVersionUID = 0;

    private EmptyImmutableMap() {
    }

    @Override
    public boolean containsKey(Object object) {
        return false;
    }

    @Override
    public boolean containsValue(Object object) {
        return false;
    }

    @Override
    public ImmutableSet<Map.Entry<Object, Object>> entrySet() {
        return ImmutableSet.of();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Map) {
            return ((Map)object).isEmpty();
        }
        return false;
    }

    @Override
    public Object get(Object object) {
        return null;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public ImmutableSet<Object> keySet() {
        return ImmutableSet.of();
    }

    Object readResolve() {
        return INSTANCE;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public String toString() {
        return "{}";
    }

    @Override
    public ImmutableCollection<Object> values() {
        return ImmutableCollection.EMPTY_IMMUTABLE_COLLECTION;
    }
}

