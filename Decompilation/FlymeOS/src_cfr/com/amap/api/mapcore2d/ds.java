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
import com.amap.api.mapcore2d.dh;
import com.amap.api.mapcore2d.dn;
import com.amap.api.mapcore2d.dt;
import com.amap.api.mapcore2d.du;
import com.amap.api.mapcore2d.ed;
import java.util.List;

public class ds {
    private dn a;
    private Context b;

    public ds(Context context) {
        this.b = context;
        this.a = this.a(this.b);
    }

    private dn a(Context object) {
        try {
            object = new dn((Context)object);
            return object;
        }
        catch (Throwable var1_2) {
            ed.a(var1_2, "SDKDB", "getDB");
            var1_2.printStackTrace();
            return null;
        }
    }

    public List<dh> a() {
        try {
            Object object = new dt();
            String string2 = dt.c();
            object = this.a.c(string2, object);
            return object;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(dh object) {
        if (object == null) {
            return;
        }
        try {
            if (this.a == null) {
                this.a = this.a(this.b);
            }
            dt dt2 = new dt();
            dt2.a(object);
            object = dt.a(object.a());
            List list = this.a.c((String)object, new dt());
            if (list == null || list.size() == 0) {
                this.a.a(dt2);
                return;
            }
            this.a.b((String)object, dt2);
            return;
        }
        catch (Throwable var1_2) {
            ed.a(var1_2, "SDKDB", "insert");
            var1_2.printStackTrace();
            return;
        }
    }
}

