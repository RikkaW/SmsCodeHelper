/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.google.common.collect;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

final class EmptyImmutableSet
extends ImmutableSet<Object> {
    private static final Object[] EMPTY_ARRAY;
    static final EmptyImmutableSet INSTANCE;
    private static final long serialVersionUID = 0;

    static {
        INSTANCE = new EmptyImmutableSet();
        EMPTY_ARRAY = new Object[0];
    }

    private EmptyImmutableSet() {
    }

    @Override
    public boolean contains(Object object) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return collection.isEmpty();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Set) {
            return ((Set)object).isEmpty();
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    boolean isHashCodeFast() {
        return true;
    }

    @Override
    public UnmodifiableIterator<Object> iterator() {
        return Iterators.emptyIterator();
    }

    Object readResolve() {
        return INSTANCE;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Object[] toArray() {
        return EMPTY_ARRAY;
    }

    @Override
    public <T> T[] toArray(T[] arrT) {
        if (arrT.length > 0) {
            arrT[0] = null;
        }
        return arrT;
    }

    @Override
    public String toString() {
        return "[]";
    }
}

