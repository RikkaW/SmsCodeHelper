/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.HashMap
 *  org.apache.http.HttpEntity
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.df;
import com.amap.api.mapcore2d.dz;
import com.amap.api.mapcore2d.ey;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;

public class em
extends ey {
    private byte[] a;

    public em(byte[] arrby) {
        this.a = (byte[])arrby.clone();
    }

    private String f() {
        byte[] arrby = dz.a.getBytes();
        byte[] arrby2 = new byte[arrby.length + 50];
        System.arraycopy((Object)this.a, (int)0, (Object)arrby2, (int)0, (int)50);
        System.arraycopy((Object)arrby, (int)0, (Object)arrby2, (int)50, (int)arrby.length);
        return df.a(arrby2);
    }

    @Override
    public byte[] a_() {
        return this.a;
    }

    @Override
    public Map<String, String> b() {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", "application/zip");
        hashMap.put("Content-Length", String.valueOf((int)this.a.length));
        return hashMap;
    }

    @Override
    public Map<String, String> c() {
        return null;
    }

    @Override
    public String d() {
        return String.format((String)dz.b, (Object[])new Object[]{"1", "1", "1", "open", this.f()});
    }

    @Override
    public HttpEntity e() {
        return null;
    }
}

