/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.google.common.collect;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Table;
import java.util.Map;
import java.util.Set;

public abstract class ImmutableTable<R, C, V>
implements Table<R, C, V> {
    ImmutableTable() {
    }

    @Override
    public abstract ImmutableSet<Table.Cell<R, C, V>> cellSet();

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof Table) {
            object = (Table)object;
            return this.cellSet().equals(object.cellSet());
        }
        return false;
    }

    public int hashCode() {
        return this.cellSet().hashCode();
    }

    public abstract ImmutableMap<R, Map<C, V>> rowMap();

    public String toString() {
        return this.rowMap().toString();
    }
}

