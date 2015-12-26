/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.Throwable
 *  java.util.Arrays
 *  org.json.JSONObject
 */
package com.amap.api.location.core;

import android.content.Context;
import android.util.Log;
import com.amap.api.location.core.d;
import java.util.Arrays;
import org.json.JSONObject;

public class a {
    static String a;
    private static int b;
    private static String c;

    static {
        b = -1;
        a = "";
        c = "1";
    }

    public static int a() {
        return b;
    }

    public static String a(String string2) {
        Object object = string2.split("&");
        Arrays.sort((Object[])object);
        StringBuffer stringBuffer = new StringBuffer();
        int n2 = object.length;
        for (int i2 = 0; i2 < n2; ++i2) {
            stringBuffer.append((String)object[i2]);
            stringBuffer.append("&");
        }
        object = stringBuffer.toString();
        if (object.length() > 1) {
            return (String)object.subSequence(0, object.length() - 1);
        }
        return string2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean a(Context object) {
        synchronized (a.class) {
            boolean bl2;
            try {
                byte[] arrby = a.e("resType=json&encode=UTF-8&ec=1");
                String string2 = a.c();
                object = ahq.a().a((Context)object, string2, arrby, "loc");
                if (object != null) {
                    bl2 = a.d((String)object);
                } else {
                    b = 0;
                    bl2 = true;
                }
                boolean bl3 = bl2;
                if (b == 1) return bl3;
            }
            catch (Throwable var0_1) {
                b = 0;
                var0_1.printStackTrace();
                if (b == 1) return true;
                b = 0;
                return true;
            }
            catch (Throwable var0_2) {
                if (b == 1) throw var0_2;
                b = 0;
                throw var0_2;
            }
            b = 0;
            return bl2;
        }
    }

    public static String b(String string2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(string2);
        String string3 = d.a();
        stringBuffer.append("&ts=" + string3);
        stringBuffer.append("&scode=" + d.a(string3, string2));
        return stringBuffer.toString();
    }

    public static boolean b() {
        return "1".equals((Object)c);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean b(Context object) {
        synchronized (a.class) {
            boolean bl2 = true;
            byte[] arrby = a.e("resType=json&encode=UTF-8&ec=1&opertype=callamap&output=json");
            String string2 = a.d();
            object = ahq.a().a((Context)object, string2, arrby, "lswu");
            boolean bl3 = bl2;
            if (object == null) return bl3;
            try {
                return a.c((String)object);
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return bl2;
            }
        }
    }

    private static String c() {
        return "http://apiinit.amap.com/v3/log/init";
    }

    private static boolean c(String string2) {
        try {
            string2 = new JSONObject(string2);
            if (string2.has("result") && (string2 = string2.getJSONObject("result")).has("callamap") && (string2 = string2.getJSONObject("callamap")).has("callamapflag")) {
                c = string2.getString("callamapflag");
            }
            return true;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return false;
        }
    }

    private static String d() {
        return "http://restapi.amap.com/v3/config/resource?";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static boolean d(String string2) {
        try {
            string2 = new JSONObject(string2);
            if (string2.has("status")) {
                int n2 = string2.getInt("status");
                if (n2 == 1) {
                    b = 1;
                } else if (n2 == 0) {
                    b = 0;
                }
            }
            if (string2.has("info")) {
                a = string2.getString("info");
            }
            if (b == 0) {
                Log.i((String)"AuthFailure", (String)a);
            }
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            b = 0;
        }
        if (b == 1) {
            return true;
        }
        return false;
    }

    private static byte[] e(String string2) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(string2);
            string2 = (String)a.b(a.a(stringBuffer.toString())).toString().getBytes("UTF-8");
            return string2;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }
}

