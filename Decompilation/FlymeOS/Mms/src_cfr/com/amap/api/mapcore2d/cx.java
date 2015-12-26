/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Map$Entry
 */
package com.amap.api.mapcore2d;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

class cx<K, V> {
    private final LinkedHashMap<K, V> a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;

    public cx(int n2) {
        if (n2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.c = n2;
        this.a = new LinkedHashMap(0, 0.75f, true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(int n2) {
        synchronized (this) {
            if (this.b < 0 || this.a.isEmpty() && this.b != 0) {
                throw new IllegalStateException(this.getClass().getName() + ".sizeOf() is reporting inconsistent results!");
            }
            if (this.b <= n2) {
                return;
            }
            Iterator<Map.Entry<K, V>> iterator = this.a.entrySet().iterator();
            Object object = null;
            while (iterator.hasNext()) {
                object = iterator.next();
            }
            return;
        }
    }

    private int c(K k2, V v2) {
        int n2 = this.a(k2, v2);
        if (n2 < 0) {
            throw new IllegalStateException("Negative size: " + k2 + "=" + v2);
        }
        return n2;
    }

    protected int a(K k2, V v2) {
        return 1;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public final V a(K k2) {
        if (k2 == null) {
            throw new NullPointerException("key == null");
        }
        // MONITORENTER : this
        V v2 = this.a.get(k2);
        if (v2 != null) {
            ++this.g;
            // MONITOREXIT : this
            return v2;
        }
        ++this.h;
        // MONITOREXIT : this
        v2 = this.b(k2);
        if (v2 == null) {
            return null;
        }
        // MONITORENTER : this
        ++this.e;
        Object object = this.a.put(k2, v2);
        if (object != null) {
            this.a.put(k2, object);
        } else {
            this.b += this.c(k2, v2);
        }
        // MONITOREXIT : this
        if (object != null) {
            this.a(false, k2, v2, object);
            return (V)object;
        }
        this.a(this.c);
        return v2;
    }

    public final void a() {
        this.a(-1);
    }

    protected void a(boolean bl2, K k2, V v2, V v3) {
    }

    protected V b(K k2) {
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public final V b(K k2, V v2) {
        if (k2 == null) throw new NullPointerException("key == null || value == null");
        if (v2 == null) {
            throw new NullPointerException("key == null || value == null");
        }
        // MONITORENTER : this
        ++this.d;
        this.b += this.c(k2, v2);
        Object object = this.a.put(k2, v2);
        if (object != null) {
            this.b -= this.c(k2, object);
        }
        // MONITOREXIT : this
        if (object != null) {
            this.a(false, k2, object, v2);
        }
        this.a(this.c);
        return (V)object;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final String toString() {
        int n2 = 0;
        synchronized (this) {
            int n3 = this.g + this.h;
            if (n3 == 0) return String.format((String)"LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", (Object[])new Object[]{this.c, this.g, this.h, n2});
            n2 = this.g * 100 / n3;
            return String.format((String)"LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", (Object[])new Object[]{this.c, this.g, this.h, n2});
        }
    }
}

