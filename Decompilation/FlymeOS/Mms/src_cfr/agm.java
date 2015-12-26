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

final class agm
extends BroadcastReceiver {
    agm(aie aie2) {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final void onReceive(Context context, Intent intent) {
        if (intent == null) return;
        try {
            if (!intent.getAction().equals((Object)"android.location.GPS_FIX_CHANGE")) return;
            aie.b = false;
            return;
        }
        catch (Exception var1_2) {
            return;
        }
    }
}

