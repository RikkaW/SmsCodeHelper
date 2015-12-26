/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.telephony.ServiceState
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.ServiceState;

class abk
extends BroadcastReceiver {
    final /* synthetic */ abh.a a;

    abk(abh.a a2) {
        this.a = a2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void onReceive(Context object, Intent intent) {
        if (!"android.intent.action.SERVICE_STATE".equals((Object)intent.getAction())) {
            return;
        }
        object = aau.a("android.telephony.ServiceState", "newFromBundle", Bundle.class, (Object)intent.getExtras());
        boolean bl2 = object != null && object instanceof ServiceState ? ((ServiceState)object).getRoaming() : false;
        object = abh.a.b();
        synchronized (object) {
            abh.a.a(this.a, abh.a.a(abh.a.a(this.a), bl2));
            return;
        }
    }
}

