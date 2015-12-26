/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import java.util.Collection;
import java.util.Iterator;

public final class Collections2 {
    static final Joiner STANDARD_JOINER = Joiner.on(", ");

    private Collections2() {
    }

    static boolean containsAllImpl(Collection<?> collection, Collection<?> object) {
        Preconditions.checkNotNull(collection);
        object = object.iterator();
        while (object.hasNext()) {
            if (collection.contains(object.next())) continue;
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    static StringBuilder newStringBuilderForCollection(int n) {
        boolean bl = n >= 0;
        Preconditions.checkArgument(bl, "size must be non-negative");
        return new StringBuilder((int)Math.min((long)((long)n * 8), (long)0x40000000));
    }

    static String toStringImpl(final Collection<?> collection) {
        StringBuilder stringBuilder = Collections2.newStringBuilderForCollection(collection.size()).append('[');
        STANDARD_JOINER.appendTo(stringBuilder, Iterables.transform(collection, new Function<Object, Object>(){

            @Override
            public Object apply(Object object) {
                Object object2 = object;
                if (object == collection) {
                    object2 = "(this Collection)";
                }
                return object2;
            }
        }));
        return stringBuilder.append(']').toString();
    }

}

