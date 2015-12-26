/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.google.common.collect;

import java.util.Iterator;

public abstract class UnmodifiableIterator<E>
implements Iterator<E> {
    protected UnmodifiableIterator() {
    }

    @Override
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

