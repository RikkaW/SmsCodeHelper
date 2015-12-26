/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  com.amap.api.mapcore2d.MapMessage
 *  java.lang.Object
 *  java.util.concurrent.CopyOnWriteArrayList
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.MapMessage;
import com.amap.api.mapcore2d.b;
import com.amap.api.mapcore2d.u;
import java.util.concurrent.CopyOnWriteArrayList;

class bd {
    b a;
    private CopyOnWriteArrayList<u> b = new CopyOnWriteArrayList();
    private CopyOnWriteArrayList<MapMessage> c = new CopyOnWriteArrayList();

    public bd(b b2) {
        this.a = b2;
    }

    public void a(u u2) {
        this.b.add((Object)u2);
    }
}

