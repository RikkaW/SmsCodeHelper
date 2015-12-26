/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.String
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.b;
import com.amap.api.mapcore2d.bj;
import com.amap.api.mapcore2d.ck;

class b$2
extends ck {
    final /* synthetic */ b a;

    b$2(b b2) {
        this.a = b2;
    }

    @Override
    public String a(int n2, int n3, int n4) {
        return bj.a().c() + "/trafficengine/mapabc/traffictile?v=w2.61&zoom=" + (17 - n4) + "&x=" + n2 + "&y=" + n3;
    }
}

