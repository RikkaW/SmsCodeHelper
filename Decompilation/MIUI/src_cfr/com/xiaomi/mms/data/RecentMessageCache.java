/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.mms.data;

import com.xiaomi.mms.utils.FixedSizeMap;

public class RecentMessageCache {
    private static final Object PRESENT;
    private static final FixedSizeMap<String, Object> mMap;

    static {
        mMap = new FixedSizeMap(20, 20);
        PRESENT = new Object();
    }

    private RecentMessageCache() {
    }

    public static void add(String string2) {
        synchronized (RecentMessageCache.class) {
            mMap.put((Object)string2, PRESENT);
            return;
        }
    }

    public static boolean contain(String string2) {
        synchronized (RecentMessageCache.class) {
            boolean bl = mMap.containsKey((Object)string2);
            return bl;
        }
    }
}

