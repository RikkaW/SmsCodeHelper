/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.cz;

public class dh {
    String a;
    String b;
    String c;
    private boolean d = true;
    private String e = "standard";
    private String[] f = null;

    private dh(a a2) {
        this.a = a2.a;
        this.c = a2.b;
        this.b = a2.c;
        this.d = a2.d;
        this.e = a2.e;
        this.f = a2.f;
    }

    public String a() {
        return this.c;
    }

    public String b() {
        return this.a;
    }

    public String c() {
        return this.b;
    }

    public String d() {
        return this.e;
    }

    public boolean e() {
        return this.d;
    }

    public String[] f() {
        return (String[])this.f.clone();
    }

    public static class a {
        private String a;
        private String b;
        private String c;
        private boolean d = true;
        private String e = "standard";
        private String[] f = null;

        public a(String string2, String string3, String string4) {
            this.a = string3;
            this.c = string4;
            this.b = string2;
        }

        public a a(String string2) {
            this.e = string2;
            return this;
        }

        public a a(boolean bl2) {
            this.d = bl2;
            return this;
        }

        public a a(String[] arrstring) {
            this.f = (String[])arrstring.clone();
            return this;
        }

        public dh a() {
            if (this.f == null) {
                throw new cz("sdk packages is null");
            }
            return new dh(this);
        }
    }

}

