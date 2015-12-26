/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.location.GpsStatus
 *  android.location.GpsStatus$Listener
 *  android.location.GpsStatus$NmeaListener
 *  android.location.LocationListener
 *  android.os.Looper
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 */
import android.location.GpsStatus;
import android.location.LocationListener;
import android.os.Looper;
import java.util.List;

final class agn
extends Thread {
    final /* synthetic */ aie a;

    agn(aie aie2, String string2) {
        this.a = aie2;
        super(string2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final void run() {
        block9 : {
            try {
                Looper.prepare();
                aie.a(this.a, Looper.myLooper());
                aie.a(this.a, new agp(this.a));
            }
            catch (Exception var1_3) {
                return;
            }
            try {
                aie.e(this.a).addGpsStatusListener((GpsStatus.Listener)aie.d(this.a));
                aie.e(this.a).addNmeaListener((GpsStatus.NmeaListener)aie.d(this.a));
            }
            catch (Exception var1_1) {}
            aie.a(this.a, new ago(this));
            List list = aie.e(this.a).getAllProviders();
            if (list == null || !list.contains("gps")) break block9;
            list.contains("passive");
        }
        try {
            aie.e(this.a).requestLocationUpdates("passive", 1000, (float)aie.l(), aie.f(this.a));
        }
        catch (Exception var1_4) {}
        Looper.loop();
    }
}

