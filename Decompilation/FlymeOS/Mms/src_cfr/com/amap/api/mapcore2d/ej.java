/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.df;
import com.amap.api.mapcore2d.dh;
import com.amap.api.mapcore2d.dp;
import com.amap.api.mapcore2d.ek;
import com.amap.api.mapcore2d.en;
import com.amap.api.mapcore2d.es;
import java.util.List;

class ej
extends en {
    private a a;

    ej() {
    }

    @Override
    protected int a() {
        return 1;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    protected es a(dp dp2) {
        try {
            if (this.a == null) {
                this.a = new a(dp2);
            }
            do {
                return this.a;
                break;
            } while (true);
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return this.a;
        }
    }

    @Override
    protected String a(String string2) {
        return df.b(string2);
    }

    @Override
    protected String a(List<dh> list) {
        return null;
    }

    @Override
    protected String b() {
        return ek.b;
    }

    class a
    implements es {
        private dp b;

        a(dp dp2) {
            this.b = dp2;
        }

        @Override
        public void a(String string2) {
            try {
                this.b.b(string2, ej.this.a());
                return;
            }
            catch (Throwable var1_2) {
                var1_2.printStackTrace();
                return;
            }
        }
    }

}

