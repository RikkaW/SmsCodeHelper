/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Iterator;
import java.util.Set;

final class SingletonImmutableSet<E>
extends ImmutableSet<E> {
    private transient int cachedHashCode;
    final transient E element;

    SingletonImmutableSet(E e2) {
        this.element = Preconditions.checkNotNull(e2);
    }

    SingletonImmutableSet(E e2, int n) {
        this.element = e2;
        this.cachedHashCode = n;
    }

    @Override
    public boolean contains(Object object) {
        return this.element.equals(object);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Set)) return false;
        if ((object = (Set)object).size() != 1) return false;
        if (this.element.equals(object.iterator().next())) return true;
        return false;
    }

    @Override
    public final int hashCode() {
        int n;
        int n2 = n = this.cachedHashCode;
        if (n == 0) {
            this.cachedHashCode = n2 = this.element.hashCode();
        }
        return n2;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    boolean isHashCodeFast() {
        if (this.cachedHashCode != 0) {
            return true;
        }
        return false;
    }

    @Override
    public UnmodifiableIterator<E> iterator() {
        return Iterators.singletonIterator(this.element);
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public Object[] toArray() {
        return new Object[]{this.element};
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public <T> T[] toArray(T[] arrT) {
        T[] arrT2;
        if (arrT.length == 0) {
            arrT2 = ObjectArrays.newArray(arrT, 1);
        } else {
            arrT2 = arrT;
            if (arrT.length > 1) {
                arrT[1] = null;
                arrT2 = arrT;
            }
        }
        arrT2[0] = this.element;
        return arrT2;
    }

    @Override
    public String toString() {
        String string2 = this.element.toString();
        return new StringBuilder(string2.length() + 2).append('[').append(string2).append(']').toString();
    }
}

