/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.google.common.collect;

import com.google.common.collect.Platform;
import java.util.Collection;
import java.util.Iterator;

public final class ObjectArrays {
    private ObjectArrays() {
    }

    private static Object[] fillArray(Iterable<?> object, Object[] arrobject) {
        int n = 0;
        object = object.iterator();
        while (object.hasNext()) {
            arrobject[n] = object.next();
            ++n;
        }
        return arrobject;
    }

    public static <T> T[] newArray(T[] arrT, int n) {
        return Platform.newArray(arrT, n);
    }

    static Object[] toArrayImpl(Collection<?> collection) {
        return ObjectArrays.fillArray(collection, new Object[collection.size()]);
    }

    static <T> T[] toArrayImpl(Collection<?> collection, T[] arrT) {
        int n = collection.size();
        Object[] arrobject = arrT;
        if (arrT.length < n) {
            arrobject = ObjectArrays.newArray(arrT, n);
        }
        ObjectArrays.fillArray(collection, arrobject);
        if (arrobject.length > n) {
            arrobject[n] = null;
        }
        return arrobject;
    }
}

