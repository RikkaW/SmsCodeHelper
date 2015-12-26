/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.HashMap
 *  org.apache.http.HttpHost
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.util.Log;
import com.amap.api.mapcore2d.da;
import com.amap.api.mapcore2d.dc;
import com.amap.api.mapcore2d.dg;
import com.amap.api.mapcore2d.dh;
import com.amap.api.mapcore2d.di;
import com.amap.api.mapcore2d.dj;
import com.amap.api.mapcore2d.ed;
import com.amap.api.mapcore2d.ex;
import com.amap.api.mapcore2d.ey;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpHost;
import org.json.JSONException;
import org.json.JSONObject;

public class db {
    public static int a = -1;
    public static String b = "";
    private static dh c;
    private static String d;
    private static String e;

    static {
        d = "http://apiinit.amap.com/v3/log/init";
        e = null;
    }

    private static String a() {
        return d;
    }

    private static Map<String, String> a(Context context) {
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("resType", "json");
            hashMap.put("encode", "UTF-8");
            hashMap.put("ec", "1");
            String string2 = dc.a();
            hashMap.put("ts", string2);
            hashMap.put("scode", dc.a(context, string2, di.a("resType=json&encode=UTF-8&ec=1")));
            return hashMap;
        }
        catch (Throwable var0_1) {
            ed.a(var0_1, "Auth", "gParams");
            var0_1.printStackTrace();
            return hashMap;
        }
    }

    public static void a(String string2) {
        da.a(string2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean a(Context context, dh object) {
        synchronized (db.class) {
            String string2;
            c = string2;
            boolean bl2 = true;
            try {
                string2 = db.a();
                HashMap hashMap = new HashMap();
                hashMap.put("Content-Type", "application/x-www-form-urlencoded");
                hashMap.put("Accept-Encoding", "gzip");
                hashMap.put("Connection", "Keep-Alive");
                hashMap.put("User-Agent", db.c.b);
                hashMap.put("X-INFO", dc.a(context, c, null));
                hashMap.put("ia", "1");
                hashMap.put("key", da.f(context));
                ex ex2 = ex.a();
                dj dj2 = new dj();
                dj2.a(dg.a(context));
                dj2.a((Map<String, String>)hashMap);
                dj2.b(db.a(context));
                dj2.a(string2);
                boolean bl3 = db.a(ex2.a(dj2));
                return bl3;
            }
            catch (Throwable var0_1) {
                ed.a(var0_1, "Auth", "getAuth");
                var0_1.printStackTrace();
            }
            return bl2;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static boolean a(byte[] object) {
        if (object == null) {
            return true;
        }
        try {
            object = new JSONObject(new String((byte[])object));
            if (object.has("status")) {
                int n2 = object.getInt("status");
                if (n2 == 1) {
                    a = 1;
                } else if (n2 == 0) {
                    a = 0;
                }
            }
            if (object.has("info")) {
                b = object.getString("info");
            }
            if (a == 0) {
                Log.i((String)"AuthFailure", (String)b);
            }
            if (a == 1) return true;
            return false;
        }
        catch (JSONException var0_1) {
            ed.a((Throwable)var0_1, "Auth", "lData");
            var0_1.printStackTrace();
            return false;
        }
        catch (Exception var0_2) {
            ed.a(var0_2, "Auth", "lData");
            var0_2.printStackTrace();
            return false;
        }
    }
}

