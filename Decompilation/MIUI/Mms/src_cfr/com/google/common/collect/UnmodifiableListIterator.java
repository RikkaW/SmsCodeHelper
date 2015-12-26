/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.google.common.collect;

import com.google.common.collect.UnmodifiableIterator;
import java.util.ListIterator;

public abstract class UnmodifiableListIterator<E>
extends UnmodifiableIterator<E>
implements ListIterator<E> {
    protected UnmodifiableListIterator() {
    }

    @Override
    public final void add(E e2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void set(E e2) {
        throw new UnsupportedOperationException();
    }
}

