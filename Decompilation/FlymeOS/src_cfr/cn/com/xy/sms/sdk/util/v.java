/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.HashMap
 *  java.util.HashSet
 *  java.util.Map$Entry
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public final class v {
    public static final Map<String, JSONObject> f = new HashMap();
    public static final Map<String, JSONObject> g = new HashMap();
    public static final HashSet<String> h = new HashSet();
    public static final HashSet<String> i = new HashSet();
    private static HashMap<String, v> j = new HashMap();
    public Map<String, JSONObject> a = new HashMap();
    public Map<String, JSONObject> b = new HashMap();
    public Map<String, JSONArray> c = new HashMap();
    public HashSet<String> d = new HashSet();
    public HashSet<String> e = new HashSet();

    public static v a(String string2) {
        return (v)j.get((Object)string2);
    }

    public static void a() {
        try {
            f.clear();
            g.clear();
            h.clear();
            i.clear();
            return;
        }
        catch (Throwable var0) {
            var0.printStackTrace();
            return;
        }
    }

    public static void a(String string2, Map<String, JSONObject> map) {
        if (map == null) {
            return;
        }
        v.b((String)string2).a.putAll(map);
    }

    public static v b(String string2) {
        v v2;
        v v3 = v2 = v.a(string2);
        if (v2 == null) {
            v3 = new v();
            j.put((Object)string2, (Object)v3);
        }
        return v3;
    }

    public static void b(String string2, Map<String, JSONObject> map) {
        if (map == null) {
            return;
        }
        v.b(string2);
        f.putAll(map);
    }

    public static void c(String object) {
        if ((object = v.a((String)object)) != null) {
            object.b();
            v.a();
        }
    }

    public static void e(String string2) {
        try {
            Iterator iterator = j.entrySet().iterator();
            do {
                if (!iterator.hasNext()) {
                    return;
                }
                ((v)((Map.Entry)iterator.next()).getValue()).d(string2);
            } while (true);
        }
        catch (Throwable var0_1) {
            return;
        }
    }

    public final void b() {
        try {
            this.a.clear();
            this.b.clear();
            this.c.clear();
            this.d.clear();
            this.e.clear();
            return;
        }
        catch (Throwable var1_1) {
            var1_1.printStackTrace();
            return;
        }
    }

    public final void d(String string2) {
        try {
            f.remove(string2);
            g.remove(string2);
            h.remove((Object)string2);
            i.remove((Object)string2);
            this.a.remove(string2);
            this.c.remove(string2);
            this.e.remove((Object)string2);
            i.remove((Object)string2);
            this.b.remove(string2);
            return;
        }
        catch (Throwable var1_2) {
            return;
        }
    }
}

