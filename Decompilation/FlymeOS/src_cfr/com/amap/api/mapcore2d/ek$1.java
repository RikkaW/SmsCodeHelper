/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import com.amap.api.mapcore2d.en;

final class ek$1
implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ Context b;
    final /* synthetic */ Throwable c;
    final /* synthetic */ String d;
    final /* synthetic */ String e;

    ek$1(int n2, Context context, Throwable throwable, String string2, String string3) {
        this.a = n2;
        this.b = context;
        this.c = throwable;
        this.d = string2;
        this.e = string3;
    }

    @Override
    public void run() {
        en en2;
        block3 : {
            try {
                en2 = en.a(this.a);
                if (en2 != null) break block3;
                return;
            }
            catch (Throwable var1_2) {
                var1_2.printStackTrace();
                return;
            }
        }
        en2.a(this.b, this.c, this.d, this.e);
    }
}

