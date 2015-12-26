/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.google.common.base;

public abstract class Equivalence<T> {
    protected Equivalence() {
    }

    protected abstract boolean doEquivalent(T var1, T var2);

    protected abstract int doHash(T var1);

    public final boolean equivalent(T t, T t2) {
        if (t == t2) {
            return true;
        }
        if (t == null || t2 == null) {
            return false;
        }
        return this.doEquivalent(t, t2);
    }

    public final int hash(T t) {
        if (t == null) {
            return 0;
        }
        return this.doHash(t);
    }
}

