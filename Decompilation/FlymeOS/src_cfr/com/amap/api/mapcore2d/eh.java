/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.Date
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.df;
import com.amap.api.mapcore2d.dh;
import com.amap.api.mapcore2d.dp;
import com.amap.api.mapcore2d.ek;
import com.amap.api.mapcore2d.en;
import com.amap.api.mapcore2d.eo;
import com.amap.api.mapcore2d.es;
import java.util.Date;
import java.util.List;

class eh
extends en {
    private a a;

    eh() {
    }

    @Override
    protected int a() {
        return 0;
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
        String string3 = eo.a(new Date().getTime());
        return df.b(string2 + string3);
    }

    @Override
    protected String a(List<dh> list) {
        return null;
    }

    @Override
    protected String b() {
        return ek.c;
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
                this.b.b(string2, eh.this.a());
                return;
            }
            catch (Throwable var1_2) {
                var1_2.printStackTrace();
                return;
            }
        }
    }

}

