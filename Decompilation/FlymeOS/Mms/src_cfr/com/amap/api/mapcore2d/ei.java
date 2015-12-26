/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Looper
 *  java.lang.Object
 *  java.lang.String
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.os.Looper;
import com.amap.api.mapcore2d.dd;
import com.amap.api.mapcore2d.dv;
import com.amap.api.mapcore2d.dx;
import com.amap.api.mapcore2d.ek;
import com.amap.api.mapcore2d.el;

class ei
extends el {
    private static boolean a = true;

    protected ei(Context context) {
        super(context);
    }

    @Override
    protected String a() {
        return ek.b;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    protected boolean a(Context object) {
        if (dd.g((Context)object) == 1 && a) {
            a = false;
            Looper looper = Looper.getMainLooper();
            synchronized (looper) {
                object = new dv((Context)object);
                dx dx2 = object.a();
                if (dx2 == null) {
                    return true;
                }
                if (dx2.b()) {
                    dx2.b(false);
                    object.a(dx2);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    @Override
    protected int b() {
        return 1;
    }
}

