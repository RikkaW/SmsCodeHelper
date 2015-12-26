/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Double
 *  java.lang.Math
 *  java.lang.Object
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.z;

public class ae {
    private long a = Long.MIN_VALUE;
    private long b = Long.MIN_VALUE;
    private double c = Double.MIN_VALUE;
    private double d = Double.MIN_VALUE;

    public ae() {
        this.a = 0;
        this.b = 0;
    }

    private ae(double d2, double d3, long l2, long l3) {
        this.c = d2;
        this.d = d3;
        this.a = l2;
        this.b = l3;
    }

    ae(double d2, double d3, boolean bl2) {
        if (bl2) {
            this.a = (long)(d2 * 1000000.0);
            this.b = (long)(d3 * 1000000.0);
            return;
        }
        this.c = d2;
        this.d = d3;
    }

    public ae(int n2, int n3) {
        this.a = n2;
        this.b = n3;
    }

    public int a() {
        return (int)this.b;
    }

    public void a(double d2) {
        this.d = d2;
    }

    public int b() {
        return (int)this.a;
    }

    public void b(double d2) {
        this.c = d2;
    }

    public long c() {
        return this.b;
    }

    public long d() {
        return this.a;
    }

    public double e() {
        if (Double.doubleToLongBits((double)this.d) == Double.doubleToLongBits((double)Double.MIN_VALUE)) {
            this.d = z.a(this.b) * 2.003750834E7 / 180.0;
        }
        return this.d;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (this.getClass() != object.getClass()) {
            return false;
        }
        object = (ae)object;
        if (this.a != object.a) {
            return false;
        }
        if (this.b != object.b) {
            return false;
        }
        if (Double.doubleToLongBits((double)this.c) != Double.doubleToLongBits((double)object.c)) {
            return false;
        }
        if (Double.doubleToLongBits((double)this.d) == Double.doubleToLongBits((double)object.d)) return true;
        return false;
    }

    public double f() {
        if (Double.doubleToLongBits((double)this.c) == Double.doubleToLongBits((double)Double.MIN_VALUE)) {
            this.c = Math.log((double)Math.tan((double)((z.a(this.a) + 90.0) * 3.141592653589793 / 360.0))) / 0.017453292519943295 * 2.003750834E7 / 180.0;
        }
        return this.c;
    }

    public ae g() {
        return new ae(this.c, this.d, this.a, this.b);
    }

    public int hashCode() {
        int n2 = (int)(this.a ^ this.a >>> 32);
        int n3 = (int)(this.b ^ this.b >>> 32);
        long l2 = Double.doubleToLongBits((double)this.c);
        int n4 = (int)(l2 ^ l2 >>> 32);
        l2 = Double.doubleToLongBits((double)this.d);
        return (((n2 + 31) * 31 + n3) * 31 + n4) * 31 + (int)(l2 ^ l2 >>> 32);
    }
}

