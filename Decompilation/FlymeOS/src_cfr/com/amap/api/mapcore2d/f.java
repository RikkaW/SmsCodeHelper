/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Handler
 *  android.os.Message
 *  java.lang.Object
 *  java.lang.Thread
 *  java.lang.Throwable
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.amap.api.mapcore2d.az;
import com.amap.api.mapcore2d.b;
import com.amap.api.mapcore2d.ba;
import com.amap.api.mapcore2d.cy;
import com.amap.api.mapcore2d.db;
import com.amap.api.mapcore2d.y;
import com.amap.api.maps2d.MapsInitializer;

class f
extends Thread {
    final /* synthetic */ b a;

    f(b b2) {
        this.a = b2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void run() {
        Message message = new Message();
        while (!MapsInitializer.getNetworkEnable()) {
            try {
                Thread.sleep((long)5000);
            }
            catch (Exception var2_3) {
                cy.a(var2_3, "AMapDelegateImpGLSurfaceView", "run");
                Thread.currentThread().interrupt();
            }
        }
        try {
            message.obj = new az(b.e(this.a)).a();
        }
        catch (Throwable var2_4) {
            cy.a(var2_4, "AMapDelegateImpGLSurfaceView", "run");
        }
        finally {
            message.what = 3;
            this.a.j.sendMessage(message);
        }
        try {
            db.a(b.e(this.a), y.a(true));
            if (db.a == 0) {
                this.a.j.sendEmptyMessage(2);
            }
            return;
        }
        catch (Exception var1_2) {
            this.interrupt();
            cy.a(var1_2, "AMapDelegateImpGLSurfaceView", "run");
            return;
        }
    }
}

