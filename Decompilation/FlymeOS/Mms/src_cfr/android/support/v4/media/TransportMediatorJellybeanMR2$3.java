/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Parcelable
 *  android.util.Log
 *  android.view.KeyEvent
 *  java.lang.String
 *  java.lang.Throwable
 */
package android.support.v4.media;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.media.TransportMediatorCallback;
import android.support.v4.media.TransportMediatorJellybeanMR2;
import android.util.Log;
import android.view.KeyEvent;

class TransportMediatorJellybeanMR2$3
extends BroadcastReceiver {
    final /* synthetic */ TransportMediatorJellybeanMR2 this$0;

    TransportMediatorJellybeanMR2$3(TransportMediatorJellybeanMR2 transportMediatorJellybeanMR2) {
        this.this$0 = transportMediatorJellybeanMR2;
    }

    public void onReceive(Context context, Intent intent) {
        try {
            context = (KeyEvent)intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
            this.this$0.mTransportCallback.handleKey((KeyEvent)context);
            return;
        }
        catch (ClassCastException var1_2) {
            Log.w((String)"TransportController", (Throwable)var1_2);
            return;
        }
    }
}

