/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.location.GpsSatellite
 *  android.location.GpsStatus
 *  android.location.GpsStatus$Listener
 *  android.location.LocationListener
 *  android.location.LocationManager
 *  android.os.Looper
 *  android.os.SystemClock
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.amap.api.location;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Looper;
import android.os.SystemClock;
import com.amap.api.location.a;
import com.amap.api.location.e;
import com.amap.api.location.f;
import com.amap.api.location.g;
import java.util.Iterator;

public class d {
    public LocationManager a = null;
    LocationListener b;
    LocationListener c;
    private a.a d;
    private a e;
    private Context f;
    private final GpsStatus.Listener g;

    d(Context context, LocationManager locationManager, a.a a2, a a3) {
        this.b = new e(this);
        this.c = new f(this);
        this.g = new g(this);
        this.f = context;
        this.a = locationManager;
        this.e = a3;
        this.d = a2;
    }

    static /* synthetic */ a a(d d2) {
        return d2.e;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(int n2, GpsStatus iterator) {
        if (n2 == 4) {
            int n3 = iterator.getMaxSatellites();
            iterator = iterator.getSatellites().iterator();
            n2 = 0;
            while (iterator.hasNext() && n2 <= n3) {
                if (!((GpsSatellite)iterator.next()).usedInFix()) continue;
                ++n2;
            }
            if (n2 < 3) return;
            {
                this.e.i = SystemClock.elapsedRealtime();
                return;
            }
        } else {
            if (n2 == 1 || n2 != 2) return;
            {
                this.e.b(false);
                return;
            }
        }
    }

    static /* synthetic */ void a(d d2, int n2, GpsStatus gpsStatus) {
        d2.a(n2, gpsStatus);
    }

    static /* synthetic */ a.a b(d d2) {
        return d2.d;
    }

    static /* synthetic */ Context c(d d2) {
        return d2.f;
    }

    void a() {
    }

    void a(long l2, float f2) {
        try {
            Looper looper = this.f.getMainLooper();
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            this.a.addGpsStatusListener(this.g);
            this.a.requestLocationUpdates("gps", l2, f2, this.c, looper);
            this.a.requestLocationUpdates("gps", 5000, 0.0f, this.b, looper);
            return;
        }
        catch (Throwable var4_4) {
            var4_4.printStackTrace();
            return;
        }
    }

    void b() {
        if (this.c != null) {
            this.a.removeUpdates(this.c);
        }
        if (this.g != null) {
            this.a.removeGpsStatusListener(this.g);
        }
        if (this.b != null) {
            this.a.removeUpdates(this.b);
        }
    }
}

