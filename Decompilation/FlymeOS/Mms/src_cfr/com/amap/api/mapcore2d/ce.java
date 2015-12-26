/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$CompressFormat
 *  java.io.ByteArrayOutputStream
 *  java.io.OutputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.HashMap
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpHost
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Bitmap;
import com.amap.api.mapcore2d.aw;
import com.amap.api.mapcore2d.bt;
import com.amap.api.mapcore2d.bw;
import com.amap.api.mapcore2d.cf;
import com.amap.api.mapcore2d.ck;
import com.amap.api.mapcore2d.dg;
import com.amap.api.mapcore2d.n;
import com.amap.api.mapcore2d.q;
import com.amap.api.mapcore2d.r;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;

class ce
extends bt<ArrayList<n.a>, ArrayList<n.a>> {
    private Context e;
    private aw f = null;

    public ce(Context context, ArrayList<n.a> arrayList) {
        super(arrayList);
        this.e = context;
        this.a(dg.a(this.e));
        this.a(5000);
        this.b(50000);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(n.a a2, int n2) {
        if (a2 == null || n2 < 0 || this.f == null || this.f.o == null) {
            return;
        }
        bw<n.a> bw2 = this.f.o;
        int n3 = bw2.size();
        int n4 = 0;
        while (n4 < n3) {
            n.a a3 = bw2.get(n4);
            if (a3 != null && a3.equals(a2)) {
                a3.g = n2;
                return;
            }
            ++n4;
        }
    }

    private byte[] a(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, (OutputStream)byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int a(byte[] arrby, n.a a2) {
        if (a2 == null) return -1;
        if (arrby == null) {
            return -1;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a2.b);
        stringBuilder.append("-");
        stringBuilder.append(a2.c);
        stringBuilder.append("-");
        stringBuilder.append(a2.d);
        if (this.f == null) return -1;
        if (this.f.m == null) {
            return -1;
        }
        int n2 = this.f.m.a(null, arrby, false, null, stringBuilder.toString());
        if (n2 < 0) {
            return -1;
        }
        this.a(a2, n2);
        int n3 = n2;
        if (this.f == null) return n3;
        n3 = n2;
        if (!this.f.g) return n3;
        arrby = this.a(this.f.m.a(n2));
        n3 = n2;
        if (this.f == null) return n3;
        n3 = n2;
        if (this.f.n == null) return n3;
        this.f.n.a(arrby, a2.b, a2.c, a2.d);
        return n2;
    }

    @Override
    protected /* synthetic */ Object a(byte[] arrby) {
        return this.b(arrby);
    }

    public void a(aw aw2) {
        this.f = aw2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected ArrayList<n.a> b(byte[] arrby) {
        n.a a2;
        n.a a3 = null;
        n.a a4 = a2 = null;
        if (this.a == null) return a4;
        if (arrby == null) {
            return a2;
        }
        int n2 = ((ArrayList)this.a).size();
        int n3 = 0;
        a2 = a3;
        do {
            a4 = a2;
            if (n3 >= n2) return a4;
            a3 = (n.a)((ArrayList)this.a).get(n3);
            a4 = a2;
            if (this.a(arrby, a3) < 0) {
                a4 = a2;
                if (a2 == null) {
                    a4 = new ArrayList();
                }
                a4.add((Object)new n.a(a3));
            }
            ++n3;
            a2 = a4;
        } while (true);
    }

    @Override
    public Map<String, String> b() {
        HashMap hashMap = new HashMap();
        hashMap.put("Accept-Encoding", "gzip");
        hashMap.put("User-Agent", "AMAP SDK Android 2DMap 2.5.0");
        return hashMap;
    }

    @Override
    public Map<String, String> c() {
        return null;
    }

    @Override
    public String d() {
        return this.f.j.a(((n.a)((ArrayList)this.a).get((int)0)).b, ((n.a)((ArrayList)this.a).get((int)0)).c, ((n.a)((ArrayList)this.a).get((int)0)).d);
    }

    @Override
    public HttpEntity e() {
        return null;
    }

    @Override
    protected /* synthetic */ Object f() {
        return this.g();
    }

    protected ArrayList<n.a> g() {
        ArrayList arrayList = new ArrayList();
        Iterator iterator = ((ArrayList)this.a).iterator();
        while (iterator.hasNext()) {
            arrayList.add((Object)new n.a((n.a)iterator.next()));
        }
        return arrayList;
    }
}

