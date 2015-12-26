/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.google.common.collect;

import com.google.common.collect.UnmodifiableIterator;
import java.util.NoSuchElementException;

public abstract class AbstractLinkedIterator<T>
extends UnmodifiableIterator<T> {
    private T nextOrNull;

    protected AbstractLinkedIterator(T t) {
        this.nextOrNull = t;
    }

    protected abstract T computeNext(T var1);

    @Override
    public final boolean hasNext() {
        if (this.nextOrNull != null) {
            return true;
        }
        return false;
    }

    @Override
    public final T next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        try {
            T t = this.nextOrNull;
            return t;
        }
        finally {
            this.nextOrNull = this.computeNext(this.nextOrNull);
        }
    }
}

