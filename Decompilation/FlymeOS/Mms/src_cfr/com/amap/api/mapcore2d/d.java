/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.Message
 *  android.view.MotionEvent
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.amap.api.mapcore2d;

import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import com.amap.api.mapcore2d.b;
import com.amap.api.mapcore2d.cy;

class d
extends Handler {
    String a;
    final /* synthetic */ b b;

    d(b b2) {
        this.b = b2;
        this.a = "onTouchHandler";
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        try {
            if (b.a(this.b) != null) {
                b.a(this.b).onTouch((MotionEvent)message.obj);
            }
            return;
        }
        catch (Throwable var1_2) {
            cy.a(var1_2, "AMapDelegateImpGLSurfaceView", this.a);
            return;
        }
    }
}

