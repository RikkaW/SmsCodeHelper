/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.Throwable
 */
package com.google.common.base;

public final class Throwables {
    private Throwables() {
    }

    public static <X extends Throwable> void propagateIfInstanceOf(Throwable throwable, Class<X> class_) throws Throwable {
        if (throwable != null && class_.isInstance((Object)throwable)) {
            throw (Throwable)class_.cast((Object)throwable);
        }
    }
}

