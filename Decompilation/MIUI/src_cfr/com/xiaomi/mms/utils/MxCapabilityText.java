/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  com.google.android.collect.Sets
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Collections
 *  java.util.HashSet
 */
package com.xiaomi.mms.utils;

import com.google.android.collect.Sets;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MxCapabilityText {
    public static /* varargs */ String combine(String ... arrstring) {
        if (arrstring == null || arrstring.length == 0) {
            throw new IllegalArgumentException("capabilities not specified");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String string2 : arrstring) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("#");
            }
            stringBuilder.append(string2);
        }
        return stringBuilder.toString();
    }

    public static Set<String> parse(String string2) {
        HashSet hashSet = Sets.newHashSet();
        if (string2 != null) {
            Collections.addAll((Collection)hashSet, (Object[])string2.split("#"));
        }
        return hashSet;
    }
}

