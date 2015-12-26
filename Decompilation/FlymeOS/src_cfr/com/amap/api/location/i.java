/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
package com.amap.api.location;

import com.amap.api.location.AMapLocationListener;

public class i {
    long a;
    public AMapLocationListener b;
    Boolean c;

    public i(long l2, float f2, AMapLocationListener aMapLocationListener, String string2, boolean bl2) {
        this.a = l2;
        this.b = aMapLocationListener;
        this.c = bl2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) {
            return false;
        }
        if (this.getClass() != object.getClass()) {
            return false;
        }
        object = (i)object;
        if (this.b == null) {
            if (object.b == null) return true;
            return false;
        }
        if (!this.b.equals((Object)object.b)) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int hashCode() {
        int n2;
        if (this.b == null) {
            n2 = 0;
            do {
                return n2 + 31;
                break;
            } while (true);
        }
        n2 = this.b.hashCode();
        return n2 + 31;
    }
}

