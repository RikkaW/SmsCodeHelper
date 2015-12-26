/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.google.common.collect;

import java.util.Set;

public interface Table<R, C, V> {
    public Set<Cell<R, C, V>> cellSet();

    public static interface Cell<R, C, V> {
        public V getValue();
    }

}

