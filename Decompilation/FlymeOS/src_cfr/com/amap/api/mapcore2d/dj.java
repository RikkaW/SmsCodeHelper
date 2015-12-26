/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.String
 *  java.util.HashMap
 *  org.apache.http.HttpEntity
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.ey;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;

class dj
extends ey {
    private Map<String, String> a = new HashMap();
    private String e;
    private Map<String, String> f = new HashMap();

    dj() {
    }

    void a(String string2) {
        this.e = string2;
    }

    void a(Map<String, String> map) {
        this.a.clear();
        this.a.putAll(map);
    }

    @Override
    public Map<String, String> b() {
        return this.a;
    }

    void b(Map<String, String> map) {
        this.f.clear();
        this.f.putAll(map);
    }

    @Override
    public Map<String, String> c() {
        return this.f;
    }

    @Override
    public String d() {
        return this.e;
    }

    @Override
    public HttpEntity e() {
        return null;
    }
}

