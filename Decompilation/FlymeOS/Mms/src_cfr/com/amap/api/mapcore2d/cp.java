/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.amap.api.mapcore2d;

public final class cp {
    private cp() {
        throw new AssertionError((Object)"Uninstantiable");
    }

    public static <T> T a(T t2) {
        if (t2 == null) {
            throw new NullPointerException("null reference");
        }
        return t2;
    }

    public static <T> T a(T t2, Object object) {
        if (t2 == null) {
            throw new NullPointerException(String.valueOf((Object)object));
        }
        return t2;
    }

    public static void a(boolean bl2, Object object) {
        if (!bl2) {
            throw new IllegalStateException(String.valueOf((Object)object));
        }
    }

    public static void a(boolean bl2, String string2, Object[] arrobject) {
        if (!bl2) {
            throw new IllegalArgumentException(String.format((String)string2, (Object[])arrobject));
        }
    }

    public static void b(boolean bl2, Object object) {
        if (!bl2) {
            throw new IllegalArgumentException(String.valueOf((Object)object));
        }
    }
}

