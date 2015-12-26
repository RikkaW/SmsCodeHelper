/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Arrays
 *  java.util.HashMap
 *  java.util.HashSet
 */
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class afj {
    private String a;
    private List<String> b;
    private Map<String, Collection<String>> c = new HashMap();
    private String d;
    private List<String> e;
    private byte[] f;

    public String a() {
        return this.a;
    }

    public void a(String string2) {
        if (this.a != null) {
            Log.w((String)"vCard", (String)String.format((String)"Property name is re-defined (existing: %s, requested: %s", (Object[])new Object[]{this.a, string2}));
        }
        this.a = string2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(String string2, String string3) {
        HashSet hashSet;
        if (!this.c.containsKey(string2)) {
            hashSet = string2.equals((Object)"TYPE") ? new HashSet() : new ArrayList();
            this.c.put(string2, (Collection<String>)hashSet);
        } else {
            hashSet = this.c.get(string2);
        }
        hashSet.add(string3);
    }

    public void a(List<String> list) {
        this.e = list;
    }

    public void a(byte[] arrby) {
        this.f = arrby;
    }

    public /* varargs */ void a(String ... arrstring) {
        this.e = Arrays.asList((Object[])arrstring);
    }

    public Map<String, Collection<String>> b() {
        return this.c;
    }

    public void b(String string2) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(string2);
    }

    public String c() {
        return this.d;
    }

    public void c(String string2) {
        this.d = string2;
    }

    public Collection<String> d(String string2) {
        return this.c.get(string2);
    }

    public List<String> d() {
        return this.e;
    }

    public byte[] e() {
        return this.f;
    }
}

