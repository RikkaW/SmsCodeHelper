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
import com.amap.api.mapcore2d.dv;
import com.amap.api.mapcore2d.dx;
import com.amap.api.mapcore2d.ek;
import com.amap.api.mapcore2d.el;

class eg
extends el {
    private static boolean a = true;

    protected eg(Context context) {
        super(context);
    }

    @Override
    protected String a() {
        return ek.c;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    protected boolean a(Context object) {
        if (!a) {
            return false;
        }
        a = false;
        Looper looper = Looper.getMainLooper();
        synchronized (looper) {
            object = new dv((Context)object);
            dx dx2 = object.a();
            if (dx2 == null) {
                return true;
            }
            if (dx2.a()) {
                dx2.a(false);
                object.a(dx2);
                return true;
            }
            return false;
        }
    }

    @Override
    protected int b() {
        return 0;
    }
}

