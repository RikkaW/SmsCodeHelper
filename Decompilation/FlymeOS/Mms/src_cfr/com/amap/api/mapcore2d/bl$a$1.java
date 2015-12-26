/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Locale
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.bj;
import com.amap.api.mapcore2d.bl;
import com.amap.api.mapcore2d.ck;
import com.amap.api.mapcore2d.y;
import java.util.Locale;

class bl$a$1
extends ck {
    final /* synthetic */ bl.a a;

    bl$a$1(bl.a a2) {
        this.a = a2;
    }

    @Override
    public String a(int n2, int n3, int n4) {
        if (y.h == null || y.h.equals((Object)"")) {
            return bj.a().b() + "/appmaptile?z=" + n4 + "&x=" + n2 + "&y=" + n3 + "&lang=" + this.a.d + "&size=1&scale=1&style=7";
        }
        return String.format((Locale)Locale.US, (String)y.h, (Object[])new Object[]{n4, n2, n3});
    }
}

