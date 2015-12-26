/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.net.NetworkInfo$State
 *  android.os.Build
 *  android.os.Build$VERSION
 *  java.lang.Class
 *  java.lang.Double
 *  java.lang.Float
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.lang.reflect.Method
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.amap.api.location.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.core.AMapLocException;
import com.amap.api.location.core.c;
import com.amap.api.location.core.e;
import com.amap.api.location.core.g;
import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;

public class d {
    public static String a = "";
    public static String b = "";
    static int c = 2048;
    static String d;
    private static String e;
    private static SharedPreferences f;
    private static SharedPreferences.Editor g;
    private static Method h;

    static {
        e = null;
        f = null;
        g = null;
        d = null;
    }

    public static String a() {
        try {
            String string2 = String.valueOf((long)System.currentTimeMillis());
            int n2 = string2.length();
            string2 = string2.substring(0, n2 - 2) + "1" + string2.substring(n2 - 1);
            return string2;
        }
        catch (Throwable var1_1) {
            var1_1.printStackTrace();
            return null;
        }
    }

    public static String a(String string2, String string3) {
        try {
            if (d == null || d.length() == 0) {
                d = c.a(null).i();
            }
            string2 = g.a(d + ":" + string2.substring(0, string2.length() - 3) + ":" + string3);
            return string2;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a(Context object, AMapLocation aMapLocation) {
        try {
            if (f == null) {
                f = object.getSharedPreferences("last_known_location", 0);
            }
            if (g == null) {
                g = f.edit();
            }
            if (e == null) {
                e = e.a("MD5", c.b());
            }
            object = e.d(String.valueOf((double)aMapLocation.getLatitude()).getBytes(), e);
            g.putString("a", (String)object);
            object = e.d(String.valueOf((double)aMapLocation.getLongitude()).getBytes(), e);
            g.putString("b", (String)object);
            object = aMapLocation.getProvince();
            if (object != null && object.length() > 0) {
                object = e.d(object.getBytes(), e);
                g.putString("c", (String)object);
            }
            if ((object = aMapLocation.getCity()) != null && object.length() > 0) {
                object = e.d(object.getBytes(), e);
                g.putString("d", (String)object);
            }
            if ((object = aMapLocation.getDistrict()) != null && object.length() > 0) {
                object = e.d(object.getBytes(), e);
                g.putString("e", (String)object);
            }
            if ((object = aMapLocation.getCityCode()) != null && object.length() > 0) {
                object = e.d(object.getBytes(), e);
                g.putString("f", (String)object);
            }
            if ((object = aMapLocation.getAdCode()) != null && object.length() > 0) {
                object = e.d(object.getBytes(), e);
                g.putString("g", (String)object);
            }
            if ((object = "" + aMapLocation.getAccuracy() + "") != null && object.length() > 0) {
                object = e.d(object.getBytes(), e);
                g.putString("h", (String)object);
            }
            if ((object = "" + aMapLocation.getTime() + "") != null && object.length() > 0) {
                object = e.d(object.getBytes(), e);
                g.putString("i", (String)object);
            }
            if ((object = aMapLocation.getAddress() + "") != null && object.length() > 0) {
                object = e.d(object.getBytes(), e);
                g.putString("j", (String)object);
            }
            if ((object = aMapLocation.getRoad() + "") != null && object.length() > 0) {
                object = e.d(object.getBytes(), e);
                g.putString("k", (String)object);
            }
            if ((object = aMapLocation.getPoiId() + "") != null && object.length() > 0) {
                object = e.d(object.getBytes(), e);
                g.putString("l", (String)object);
            }
            if ((object = aMapLocation.getPoiName() + "") != null && object.length() > 0) {
                object = e.d(object.getBytes(), e);
                g.putString("m", (String)object);
            }
            d.a(g);
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    private static void a(SharedPreferences.Editor editor) {
        if (editor == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 9) {
            try {
                if (h == null) {
                    h = SharedPreferences.Editor.class.getDeclaredMethod("apply", new Class[0]);
                }
                h.invoke((Object)editor, new Object[0]);
                return;
            }
            catch (Throwable var1_1) {
                var1_1.printStackTrace();
                editor.commit();
                return;
            }
        }
        editor.commit();
    }

    public static void a(String string2) {
        try {
            Object object = new JSONObject(string2);
            if (object.has("status")) {
                if (!object.has("info")) {
                    return;
                }
                string2 = object.getString("status");
                object = object.getString("info");
                if (!string2.equals((Object)"1") && string2.equals((Object)"0")) {
                    if (object.equals((Object)"INVALID_USER_KEY") || object.equals((Object)"INSUFFICIENT_PRIVILEGES") || object.equals((Object)"USERKEY_PLAT_NOMATCH") || object.equals((Object)"INVALID_USER_SCODE")) {
                        throw new AMapLocException("key\u9274\u6743\u5931\u8d25");
                    }
                    if (object.equals((Object)"SERVICE_NOT_EXIST") || object.equals((Object)"SERVICE_RESPONSE_ERROR") || object.equals((Object)"OVER_QUOTA") || object.equals((Object)"UNKNOWN_ERROR")) {
                        throw new AMapLocException("\u672a\u77e5\u7684\u9519\u8bef");
                    }
                    if (object.equals((Object)"INVALID_PARAMS")) {
                        throw new AMapLocException("\u65e0\u6548\u7684\u53c2\u6570 - IllegalArgumentException");
                    }
                }
            }
            return;
        }
        catch (JSONException var0_1) {
            return;
        }
    }

    public static boolean a(Context context) {
        block9 : {
            block8 : {
                NetworkInfo.State state;
                block7 : {
                    block6 : {
                        if (context == null) {
                            return false;
                        }
                        try {
                            context = (ConnectivityManager)context.getSystemService("connectivity");
                            if (context != null) break block6;
                            return false;
                        }
                        catch (Throwable var0_1) {
                            var0_1.printStackTrace();
                            return false;
                        }
                    }
                    context = context.getActiveNetworkInfo();
                    if (context != null) break block7;
                    return false;
                }
                context = context.getState();
                if (context == null) break block8;
                if (context != NetworkInfo.State.DISCONNECTED && context != (state = NetworkInfo.State.DISCONNECTING)) break block9;
            }
            return false;
        }
        return true;
    }

    public static AMapLocation b(Context context) {
        try {
            if (e == null) {
                e = e.a("MD5", context.getApplicationContext().getPackageName());
            }
            context = context.getSharedPreferences("last_known_location", 0);
            AMapLocation aMapLocation = new AMapLocation("");
            aMapLocation.setProvider("lbs");
            double d2 = Double.parseDouble((String)e.b(context.getString("a", ""), e));
            double d3 = Double.parseDouble((String)e.b(context.getString("b", ""), e));
            aMapLocation.setLatitude(d2);
            aMapLocation.setLongitude(d3);
            aMapLocation.setProvince(e.b(context.getString("c", ""), e));
            aMapLocation.setCity(e.b(context.getString("d", ""), e));
            aMapLocation.setDistrict(e.b(context.getString("e", ""), e));
            aMapLocation.setCityCode(e.b(context.getString("f", ""), e));
            aMapLocation.setAdCode(e.b(context.getString("g", ""), e));
            aMapLocation.setAccuracy(Float.parseFloat((String)e.b(context.getString("h", ""), e)));
            aMapLocation.setTime(Long.parseLong((String)e.b(context.getString("i", ""), e)));
            aMapLocation.setAddress(e.b(context.getString("j", ""), e));
            aMapLocation.setRoad(e.b(context.getString("k", ""), e));
            aMapLocation.setPoiId(e.b(context.getString("l", ""), e));
            aMapLocation.setPoiName(e.b(context.getString("m", ""), e));
            return aMapLocation;
        }
        catch (Throwable var0_1) {
            return null;
        }
    }
}

