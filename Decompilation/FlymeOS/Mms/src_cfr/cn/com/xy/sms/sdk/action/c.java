/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.os.Message
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.concurrent.FutureTask
 *  java.util.concurrent.TimeUnit
 */
package cn.com.xy.sms.sdk.action;

import android.os.Bundle;
import android.os.Message;
import cn.com.xy.sms.sdk.action.NearbyPoint;
import cn.com.xy.sms.sdk.action.b;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

final class c
extends Thread {
    private /* synthetic */ NearbyPoint a;

    private c(NearbyPoint nearbyPoint) {
        this.a = nearbyPoint;
    }

    /* synthetic */ c(NearbyPoint nearbyPoint, byte by2) {
        this(nearbyPoint);
    }

    public final void run() {
        if (NearbyPoint.a(this.a) < 0.0 || NearbyPoint.b(this.a) < 0.0 || NearbyPoint.c(this.a) == null || NearbyPoint.c(this.a).equalsIgnoreCase("")) {
            NearbyPoint.d(this.a).obtainMessage(4098).sendToTarget();
            return;
        }
        String string2 = NearbyPoint.a(this.a, NearbyPoint.c(this.a), NearbyPoint.a(this.a), NearbyPoint.b(this.a), 20000, NearbyPoint.e(this.a));
        if (string2 == null) {
            NearbyPoint.d(this.a).obtainMessage(4099).sendToTarget();
            return;
        }
        try {
            string2 = new FutureTask((Callable)new b(string2));
            new Thread((Runnable)((Object)string2)).start();
            string2 = (String)string2.get(5000, TimeUnit.MILLISECONDS);
        }
        catch (Throwable var1_2) {
            NearbyPoint.d(this.a).obtainMessage(4100).sendToTarget();
            return;
        }
        Message message = NearbyPoint.d(this.a).obtainMessage(4097);
        Bundle bundle = new Bundle();
        bundle.putString("queryResult", string2);
        message.setData(bundle);
        message.sendToTarget();
    }
}

