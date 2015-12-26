/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.net.wifi.ScanResult
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.Timer
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

final class agj
extends BroadcastReceiver {
    final /* synthetic */ agf a;

    private agj(agf agf2) {
        this.a = agf2;
    }

    /* synthetic */ agj(agf agf2, byte by2) {
        this(agf2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public final void onReceive(Context object, Intent intent) {
        ScanResult scanResult;
        if (object == null) return;
        if (scanResult == null) return;
        if (agf.c(this.a) == null) return;
        if (agf.d(this.a) == null) return;
        if (agf.e(this.a) == null) return;
        if (scanResult.getAction() == null) return;
        if (!"android.net.wifi.SCAN_RESULTS".equals((Object)scanResult.getAction())) return;
        object = agf.c(this.a).getScanResults();
        // MONITORENTER : this
        agf.e(this.a).clear();
        agf.a(this.a, System.currentTimeMillis());
        if (object != null && object.size() > 0) {
            for (int i2 = 0; i2 < object.size(); ++i2) {
                scanResult = (ScanResult)object.get(i2);
                agf.e(this.a).add(scanResult);
            }
        }
        // MONITOREXIT : this
        try {
            object = new agk(this);
            // MONITORENTER : this
        }
        catch (Exception var1_2) {
            return;
        }
        if (agf.d(this.a) != null) {
            agf.d(this.a).cancel();
            agf.a(this.a, null);
        }
        agf.a(this.a, new Timer());
        agf.d(this.a).schedule((TimerTask)object, (long)agf.z());
        // MONITOREXIT : this
    }
}

