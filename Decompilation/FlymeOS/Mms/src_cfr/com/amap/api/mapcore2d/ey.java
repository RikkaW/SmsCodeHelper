/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpHost
 */
package com.amap.api.mapcore2d;

import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;

public abstract class ey {
    int b = 20000;
    int c = 20000;
    HttpHost d = null;

    public final void a(int n2) {
        this.b = n2;
    }

    public final void a(HttpHost httpHost) {
        this.d = httpHost;
    }

    public byte[] a_() {
        return null;
    }

    public abstract Map<String, String> b();

    public final void b(int n2) {
        this.c = n2;
    }

    public abstract Map<String, String> c();

    public abstract String d();

    public abstract HttpEntity e();
}

