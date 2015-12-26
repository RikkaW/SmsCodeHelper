/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.location.GpsStatus
 *  android.location.GpsStatus$NmeaListener
 *  android.os.Looper
 *  android.telephony.PhoneStateListener
 *  java.lang.String
 *  java.lang.Thread
 *  java.util.Timer
 */
import android.location.GpsStatus;
import android.os.Looper;
import android.telephony.PhoneStateListener;
import java.util.Timer;

final class agg
extends Thread {
    private /* synthetic */ agf a;

    agg(agf agf2, String string2) {
        this.a = agf2;
        super(string2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final void run() {
        try {
            Looper.prepare();
            agf.a(this.a, Looper.myLooper());
            agf.a(this.a, new Timer());
            agf.a(this.a, new agh(this.a, 0));
            agf.a(this.a, (PhoneStateListener)agf.a(this.a));
            agf.a(this.a, new agi(this.a, 0));
        }
        catch (Exception var1_1) {
            return;
        }
        try {
            agf.a(this.a, (GpsStatus.NmeaListener)agf.b(this.a));
        }
        catch (Exception var1_2) {}
        Looper.loop();
    }
}

