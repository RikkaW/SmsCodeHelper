/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.google.common.collect;

import com.google.common.collect.Hashing;
import com.google.common.collect.ImmutableSet;

final class RegularImmutableSet<E>
extends ImmutableSet.ArrayImmutableSet<E> {
    private final transient int hashCode;
    private final transient int mask;
    final transient Object[] table;

    RegularImmutableSet(Object[] arrobject, int n, Object[] arrobject2, int n2) {
        super(arrobject);
        this.table = arrobject2;
        this.mask = n2;
        this.hashCode = n;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public boolean contains(Object object) {
        if (object != null) {
            Object object2;
            int n = Hashing.smear(object.hashCode());
            while ((object2 = this.table[this.mask & n]) != null) {
                if (object2.equals(object)) {
                    return true;
                }
                ++n;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    @Override
    boolean isHashCodeFast() {
        return true;
    }
}

