/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.cy;
import com.amap.api.mapcore2d.cz;
import com.amap.api.mapcore2d.ex;
import com.amap.api.mapcore2d.ey;
import com.amap.api.maps2d.AMapException;

abstract class bt<T, V>
extends ey {
    protected T a;
    private int e = 1;
    private int f = 2;

    public bt() {
    }

    public bt(T t2) {
        this();
        this.a = t2;
    }

    private V b(byte[] arrby) {
        return this.a(arrby);
    }

    private V g() {
        int n2 = 0;
        V v2 = null;
        while (n2 < this.e) {
            V v3 = v2;
            V v4 = v2;
            v3 = v2 = (V)this.b(ex.a(false).b(this));
            v4 = v2;
            try {
                int n3;
                n2 = n3 = this.e;
                continue;
            }
            catch (AMapException var3_3) {
                cy.a(var3_3, "ProtocalHandler", "GetDataMaythrow");
                if (++n2 < this.e) {
                    try {
                        Thread.sleep((long)(this.f * 1000));
                        v2 = v3;
                        continue;
                    }
                    catch (InterruptedException var3_4) {
                        throw new AMapException(var3_4.getMessage());
                    }
                }
                var3_3.printStackTrace();
                this.f();
                throw new AMapException(var3_3.getErrorMessage());
            }
            catch (cz var3_5) {
                if (++n2 < this.e) {
                    try {
                        Thread.sleep((long)(this.f * 1000));
                    }
                    catch (InterruptedException var4_8) {
                        throw new AMapException(var3_5.getMessage());
                    }
                    cy.a(var3_5, "ProtocalHandler", "GetDataMaythrow");
                    v2 = v4;
                    continue;
                }
                var3_5.printStackTrace();
                this.f();
                throw new AMapException(var3_5.a());
            }
        }
        return v2;
    }

    public V a() {
        V v2 = null;
        if (this.a != null) {
            v2 = this.g();
        }
        return v2;
    }

    protected abstract V a(byte[] var1);

    protected V f() {
        return null;
    }
}

