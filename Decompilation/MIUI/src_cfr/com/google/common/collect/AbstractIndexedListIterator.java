/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableListIterator;
import java.util.NoSuchElementException;

abstract class AbstractIndexedListIterator<E>
extends UnmodifiableListIterator<E> {
    private int position;
    private final int size;

    protected AbstractIndexedListIterator(int n) {
        this(n, 0);
    }

    protected AbstractIndexedListIterator(int n, int n2) {
        Preconditions.checkPositionIndex(n2, n);
        this.size = n;
        this.position = n2;
    }

    protected abstract E get(int var1);

    @Override
    public final boolean hasNext() {
        if (this.position < this.size) {
            return true;
        }
        return false;
    }

    @Override
    public final boolean hasPrevious() {
        if (this.position > 0) {
            return true;
        }
        return false;
    }

    @Override
    public final E next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        int n = this.position;
        this.position = n + 1;
        return this.get(n);
    }

    @Override
    public final int nextIndex() {
        return this.position;
    }

    @Override
    public final E previous() {
        int n;
        if (!this.hasPrevious()) {
            throw new NoSuchElementException();
        }
        this.position = n = this.position - 1;
        return this.get(n);
    }

    @Override
    public final int previousIndex() {
        return this.position - 1;
    }
}

