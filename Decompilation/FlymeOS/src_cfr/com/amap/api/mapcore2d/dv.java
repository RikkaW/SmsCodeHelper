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
import com.amap.api.mapcore2d.dn;
import com.amap.api.mapcore2d.du;
import com.amap.api.mapcore2d.dw;
import com.amap.api.mapcore2d.dx;
import com.amap.api.mapcore2d.ed;
import java.util.List;

public class dv {
    private dn a;
    private Context b;

    public dv(Context context) {
        this.b = context;
        this.a = this.a(this.b);
    }

    private dn a(Context object) {
        try {
            object = new dn((Context)object);
            return object;
        }
        catch (Throwable var1_2) {
            ed.a(var1_2, "UpdateLogDB", "getDB");
            var1_2.printStackTrace();
            return null;
        }
    }

    public dx a() {
        try {
            Object object;
            if (this.a == null) {
                this.a = this.a(this.b);
            }
            if ((object = this.a.c("1=1", new dw())).size() > 0) {
                object = (dx)object.get(0);
                return object;
            }
        }
        catch (Throwable var1_2) {
            ed.a(var1_2, "UpdateLogDB", "getUpdateLog");
            var1_2.printStackTrace();
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(dx object) {
        if (object == null) {
            return;
        }
        try {
            if (this.a == null) {
                this.a = this.a(this.b);
            }
            dw dw2 = new dw();
            dw2.a(object);
            object = this.a.c("1=1", new dw());
            if (object == null || object.size() == 0) {
                this.a.a(dw2);
                return;
            }
            this.a.b("1=1", dw2);
            return;
        }
        catch (Throwable var1_2) {
            ed.a(var1_2, "UpdateLogDB", "updateLog");
            var1_2.printStackTrace();
            return;
        }
    }
}

