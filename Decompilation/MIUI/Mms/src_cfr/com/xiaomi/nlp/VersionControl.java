/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.nlp;

public class VersionControl {
    protected int platform = 7;
    protected int systemLevel = 1;
    protected String version;

    public boolean matchPlatform(int n) {
        if ((this.platform & n) == 0) {
            return false;
        }
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public String matchResourceVersion(String object) {
        int n;
        int n2;
        Object var6_2 = null;
        String[] arrstring = this.version.split("\\.");
        object = object.split("\\.");
        int n3 = Math.min((int)arrstring.length, (int)object.length);
        int n4 = 0;
        do {
            if (n4 >= n3) {
                object = var6_2;
                if (n3 < arrstring.length) return object;
                return this.version;
            }
            n = Integer.valueOf((String)arrstring[n4]);
            if (n != (n2 = Integer.valueOf((String)object[n4]).intValue())) break;
            ++n4;
        } while (true);
        object = var6_2;
        if (n >= n2) return object;
        return this.version;
    }

    public boolean matchSystemLevel(int n) {
        if (this.systemLevel <= n) {
            return true;
        }
        return false;
    }

    public String matchVersion(int n, int n2, String string2) {
        if (!this.matchPlatform(n) || !this.matchSystemLevel(n2)) {
            return null;
        }
        return this.matchResourceVersion(string2);
    }

    public void setPlatform(int n) {
        this.platform = n;
    }

    public void setSystemLevel(int n) {
        this.systemLevel = n;
    }

    public void setVersion(String string2) {
        this.version = string2;
    }
}

