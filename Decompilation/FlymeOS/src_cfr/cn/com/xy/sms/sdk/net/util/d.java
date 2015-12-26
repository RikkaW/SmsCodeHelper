/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.util.Arrays
 *  java.util.HashMap
 */
package cn.com.xy.sms.sdk.net.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class d {
    private static final String a = "-noWait";
    private static final String b = "-wait=";
    private static final String c = "-ids=";
    private static final String d = "-domain=";
    private static final String e = "-sql=";
    private int f;
    private boolean g = false;
    private int h = -1;
    private String i;
    private String j;
    private String[] k;
    private Map<String, String> l;
    private String m;

    public d(int n2, String string2) {
        this.f = n2;
        this.m = string2;
        this.d();
    }

    private void a(int n2) {
        this.f = n2;
    }

    private void a(String string2) {
        this.i = string2;
    }

    private void a(Map<String, String> map) {
        this.l = map;
    }

    private void a(boolean bl2) {
        this.g = bl2;
    }

    private void a(String[] arrstring) {
        this.k = arrstring;
    }

    private void b(int n2) {
        this.h = n2;
    }

    private void b(String string2) {
        this.j = string2;
    }

    private void c(String string2) {
        this.m = string2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void d() {
        String[] arrstring = this.m.split("\\s(?=-[a-zA-Z]+)");
        int n2 = arrstring.length;
        int n3 = 0;
        while (n3 < n2) {
            String[] arrstring2 = arrstring[n3];
            if (arrstring2.equals((Object)"-noWait")) {
                this.g = true;
            } else if (arrstring2.startsWith("-wait=")) {
                this.h = Integer.valueOf((String)arrstring2.substring(6));
            } else if (arrstring2.startsWith("-ids=")) {
                this.k = arrstring2.substring(5).split(",");
                this.g = true;
            } else if (arrstring2.startsWith("-domain=")) {
                this.i = arrstring2.substring(8);
            } else if (arrstring2.startsWith("-sql=")) {
                this.j = arrstring2.substring(5);
            } else {
                if (this.l == null) {
                    this.l = new HashMap();
                }
                if ((arrstring2 = arrstring2.split("=")).length < 2) {
                    this.l.put(arrstring2[0], "true");
                } else {
                    this.l.put(arrstring2[0], arrstring2[1]);
                }
            }
            ++n3;
        }
        return;
    }

    private boolean e() {
        return this.g;
    }

    private int f() {
        return this.h;
    }

    private String g() {
        return this.i;
    }

    private Map<String, String> h() {
        return this.l;
    }

    private String i() {
        return this.m;
    }

    public final int a() {
        return this.f;
    }

    public final String b() {
        return this.j;
    }

    public final String[] c() {
        return this.k;
    }

    /*
     * Enabled aggressive block sorting
     */
    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer("cmd : ").append(this.m).append("\n targetTo interface:");
        String string2 = this.f == 0 ? "all" : Integer.valueOf((int)this.f);
        stringBuffer = stringBuffer.append((Object)string2).append("\n execute right now? ").append(this.g).append("\n just for this ids:");
        string2 = this.k == null ? "all" : Arrays.toString((Object[])this.k);
        stringBuffer = stringBuffer.append(string2).append("\n reset Wait Date Period to ");
        string2 = this.h == -1 ? "no change" : Integer.valueOf((int)this.h);
        stringBuffer = stringBuffer.append((Object)string2).append("\n reset Domain Url to ");
        string2 = this.i == null ? "no change" : this.i;
        stringBuffer = stringBuffer.append(string2).append("\n sql:");
        if (this.j == null) {
            string2 = "nosql to execute";
            return stringBuffer.append(string2).append("\n other cmd:" + this.l).toString();
        }
        string2 = this.j;
        return stringBuffer.append(string2).append("\n other cmd:" + this.l).toString();
    }
}

