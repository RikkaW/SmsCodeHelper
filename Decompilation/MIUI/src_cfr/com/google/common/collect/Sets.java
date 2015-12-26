/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public final class Sets {
    private Sets() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static boolean equalsImpl(Set<?> set, Object object) {
        boolean bl = true;
        boolean bl2 = false;
        if (set == object) {
            return true;
        }
        if (!(object instanceof Set)) return bl2;
        object = (Set)object;
        try {
            if (set.size() != object.size()) return false;
            bl2 = set.containsAll(object);
            if (!bl2) return false;
            return bl;
        }
        catch (NullPointerException var0_1) {
            return false;
        }
        catch (ClassCastException var0_2) {
            return false;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    static int hashCodeImpl(Set<?> iterator) {
        int n = 0;
        iterator = iterator.iterator();
        while (iterator.hasNext()) {
            Object e2 = iterator.next();
            int n2 = e2 != null ? e2.hashCode() : 0;
            n += n2;
        }
        return n;
    }
}

