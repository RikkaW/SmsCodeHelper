/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 */
package com.amap.api.location;

import com.amap.api.location.AMapLocalWeatherListener;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.b;
import java.util.List;

class b$a$1
extends Thread {
    final /* synthetic */ b a;
    final /* synthetic */ AMapLocation b;
    final /* synthetic */ b.a c;

    b$a$1(b.a a2, b b2, AMapLocation aMapLocation) {
        this.c = a2;
        this.a = b2;
        this.b = aMapLocation;
    }

    public void run() {
        int n2 = 0;
        do {
            block6 : {
                try {
                    if (n2 < this.a.d.size()) {
                        if (this.a.d.get(n2) == 1) {
                            this.a.a(this.b, "base", this.a.e.get(n2));
                        }
                        if (this.a.d.get(n2) == 2) {
                            this.a.a(this.b, "all", this.a.e.get(n2));
                        }
                        break block6;
                    }
                    this.a.d.clear();
                    this.a.e.clear();
                    return;
                }
                catch (Throwable var2_2) {
                    var2_2.printStackTrace();
                    return;
                }
            }
            ++n2;
        } while (true);
    }
}

