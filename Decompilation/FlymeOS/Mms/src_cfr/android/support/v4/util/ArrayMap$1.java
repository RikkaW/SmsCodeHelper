/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package android.support.v4.util;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.MapCollections;
import java.util.Map;

class ArrayMap$1
extends MapCollections<K, V> {
    final /* synthetic */ ArrayMap this$0;

    ArrayMap$1(ArrayMap arrayMap) {
        this.this$0 = arrayMap;
    }

    @Override
    protected void colClear() {
        this.this$0.clear();
    }

    @Override
    protected Object colGetEntry(int n2, int n3) {
        return this.this$0.mArray[(n2 << 1) + n3];
    }

    @Override
    protected Map<K, V> colGetMap() {
        return this.this$0;
    }

    @Override
    protected int colGetSize() {
        return this.this$0.mSize;
    }

    @Override
    protected int colIndexOfKey(Object object) {
        return this.this$0.indexOfKey(object);
    }

    @Override
    protected int colIndexOfValue(Object object) {
        return this.this$0.indexOfValue(object);
    }

    @Override
    protected void colPut(K k2, V v2) {
        this.this$0.put(k2, v2);
    }

    @Override
    protected void colRemoveAt(int n2) {
        this.this$0.removeAt(n2);
    }

    @Override
    protected V colSetValue(int n2, V v2) {
        return this.this$0.setValueAt(n2, v2);
    }
}

