/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Map$Entry
 */
package com.google.common.collect;

import com.google.common.base.Objects;
import java.util.Map;

abstract class AbstractMapEntry<K, V>
implements Map.Entry<K, V> {
    AbstractMapEntry() {
    }

    public boolean equals(Object object) {
        boolean bl;
        boolean bl2 = bl = false;
        if (object instanceof Map.Entry) {
            object = (Map.Entry)object;
            bl2 = bl;
            if (Objects.equal(this.getKey(), object.getKey())) {
                bl2 = bl;
                if (Objects.equal(this.getValue(), object.getValue())) {
                    bl2 = true;
                }
            }
        }
        return bl2;
    }

    public abstract K getKey();

    public abstract V getValue();

    /*
     * Enabled aggressive block sorting
     */
    public int hashCode() {
        int n = 0;
        K k = this.getKey();
        V v = this.getValue();
        int n2 = k == null ? 0 : k.hashCode();
        if (v == null) {
            return n ^ n2;
        }
        n = v.hashCode();
        return n ^ n2;
    }

    public V setValue(V v) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return this.getKey() + "=" + this.getValue();
    }
}

