/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
package com.google.android.mms.util;

import java.util.HashMap;

public abstract class AbstractCache<K, V> {
    private static final boolean DEBUG = false;
    private static final boolean LOCAL_LOGV = false;
    private static final int MAX_CACHED_ITEMS = 500;
    private static final String TAG = "AbstractCache";
    private final HashMap<K, CacheEntry<V>> mCacheMap = new HashMap();

    protected AbstractCache() {
    }

    public V get(K object) {
        if (object != null && (object = (CacheEntry)this.mCacheMap.get(object)) != null) {
            ++object.hit;
            return object.value;
        }
        return null;
    }

    public V purge(K object) {
        if ((object = (CacheEntry)this.mCacheMap.remove(object)) != null) {
            return object.value;
        }
        return null;
    }

    public void purgeAll() {
        this.mCacheMap.clear();
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean put(K k, V v) {
        if (this.mCacheMap.size() >= 500 || k == null) {
            return false;
        }
        CacheEntry cacheEntry = new CacheEntry();
        cacheEntry.value = v;
        this.mCacheMap.put(k, cacheEntry);
        return true;
    }

    public int size() {
        return this.mCacheMap.size();
    }

    private static class CacheEntry<V> {
        int hit;
        V value;

        private CacheEntry() {
        }
    }

}

