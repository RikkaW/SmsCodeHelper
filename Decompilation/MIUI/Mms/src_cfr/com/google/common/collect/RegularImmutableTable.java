/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 */
package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import java.util.Map;
import java.util.Set;

abstract class RegularImmutableTable<R, C, V>
extends ImmutableTable<R, C, V> {
    private static final Function<Table.Cell<Object, Object, Object>, Object> GET_VALUE_FUNCTION = new Function<Table.Cell<Object, Object, Object>, Object>(){

        @Override
        public Object apply(Table.Cell<Object, Object, Object> cell) {
            return cell.getValue();
        }
    };
    private final ImmutableSet<Table.Cell<R, C, V>> cellSet;

    @Override
    public final ImmutableSet<Table.Cell<R, C, V>> cellSet() {
        return this.cellSet;
    }

    static final class DenseImmutableTable<R, C, V>
    extends RegularImmutableTable<R, C, V> {
        private final ImmutableBiMap<C, Integer> columnKeyToIndex;
        private final ImmutableBiMap<R, Integer> rowKeyToIndex;
        private volatile transient ImmutableMap<R, Map<C, V>> rowMap;
        private final V[][] values;

        private ImmutableMap<R, Map<C, V>> makeRowMap() {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            for (int i = 0; i < this.values.length; ++i) {
                V[] arrV = this.values[i];
                ImmutableMap.Builder<C, V> builder2 = ImmutableMap.builder();
                for (int j = 0; j < arrV.length; ++j) {
                    V v = arrV[j];
                    if (v == null) continue;
                    builder2.put(this.columnKeyToIndex.inverse().get(j), v);
                }
                builder.put(this.rowKeyToIndex.inverse().get(i), builder2.build());
            }
            return builder.build();
        }

        @Override
        public ImmutableMap<R, Map<C, V>> rowMap() {
            ImmutableMap<R, Map<C, Map<C, Map<C, Map<C, V>>>>> immutableMap;
            ImmutableMap<R, Map<C, Map<C, Map<C, Map<C, V>>>>> immutableMap2 = immutableMap = this.rowMap;
            if (immutableMap == null) {
                this.rowMap = immutableMap2 = this.makeRowMap();
            }
            return immutableMap2;
        }
    }

    static final class SparseImmutableTable<R, C, V>
    extends RegularImmutableTable<R, C, V> {
        private final ImmutableMap<R, Map<C, V>> rowMap;

        @Override
        public ImmutableMap<R, Map<C, V>> rowMap() {
            return this.rowMap;
        }
    }

}

