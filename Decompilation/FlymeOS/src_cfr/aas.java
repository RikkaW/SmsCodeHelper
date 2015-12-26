/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class aas
extends BroadcastReceiver {
    final /* synthetic */ aar a;

    aas(aar aar2) {
        this.a = aar2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void onReceive(Context object, Intent intent) {
        if (!"com.android.mms.RATE_LIMIT_CONFIRMED".equals((Object)intent.getAction())) {
            return;
        }
        synchronized (this) {
            object = this.a;
            int n2 = intent.getBooleanExtra("answer", false) ? 1 : 2;
            aar.a((aar)object, n2);
            this.notifyAll();
            return;
        }
    }
}

