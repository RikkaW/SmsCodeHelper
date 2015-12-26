/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.util.ArrayList
 */
package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import java.util.ArrayList;

public final class Lists {
    private Lists() {
    }

    /*
     * Enabled aggressive block sorting
     */
    static int computeArrayListCapacity(int n) {
        boolean bl = n >= 0;
        Preconditions.checkArgument(bl);
        return Ints.saturatedCast(5 + (long)n + (long)(n / 10));
    }

    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList();
    }
}

