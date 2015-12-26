/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Map$Entry
 */
package com.google.common.collect;

import com.google.common.base.Joiner;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableEntry;
import java.util.Map;

public final class Maps {
    static final Joiner.MapJoiner STANDARD_JOINER = Collections2.STANDARD_JOINER.withKeyValueSeparator("=");

    private Maps() {
    }

    public static <K, V> Map.Entry<K, V> immutableEntry(K k, V v) {
        return new ImmutableEntry<K, V>(k, v);
    }

    static String toStringImpl(Map<?, ?> map) {
        StringBuilder stringBuilder = Collections2.newStringBuilderForCollection(map.size()).append('{');
        STANDARD_JOINER.appendTo(stringBuilder, map);
        return stringBuilder.append('}').toString();
    }
}

