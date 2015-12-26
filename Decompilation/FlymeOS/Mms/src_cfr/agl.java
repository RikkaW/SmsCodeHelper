/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.location.Location
 *  android.location.LocationListener
 *  android.os.Bundle
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.text.SimpleDateFormat
 */
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import java.text.SimpleDateFormat;

final class agl
implements LocationListener {
    private /* synthetic */ aie a;

    agl(aie aie2) {
        this.a = aie2;
    }

    private static boolean a(Location location) {
        if (location != null && "gps".equalsIgnoreCase(location.getProvider()) && location.getLatitude() > -90.0 && location.getLatitude() < 90.0 && location.getLongitude() > -180.0 && location.getLongitude() < 180.0) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final void onLocationChanged(Location location) {
        try {
            long l2 = location.getTime();
            long l3 = System.currentTimeMillis();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            simpleDateFormat.format((Object)l2);
            simpleDateFormat.format((Object)l3);
            if (l2 <= 0) {
                l2 = l3;
            }
            if (location != null) {
                if (!agl.a(location)) {
                    return;
                }
                if (location.getSpeed() > (float)aie.g()) {
                    agt.a(aie.h());
                    agt.b(aie.h() * 10);
                } else if (location.getSpeed() > (float)aie.i()) {
                    agt.a(aie.j());
                    agt.b(aie.j() * 10);
                } else {
                    agt.a(aie.k());
                    agt.b(aie.k() * 10);
                }
                aie.b(this.a).a();
                agl.a(location);
                if (aie.b(this.a).a() && agl.a(location)) {
                    location.setTime(System.currentTimeMillis());
                    aie.a(this.a, System.currentTimeMillis());
                    aie.a(this.a, location);
                    if (!aie.c(this.a)) {
                        aie.a(this.a, location, 0, l2);
                        return;
                    }
                    aie.a(this.a, "new location in indoor collect");
                    return;
                }
            }
            return;
        }
        catch (Exception var1_2) {
            return;
        }
    }

    public final void onProviderDisabled(String string2) {
    }

    public final void onProviderEnabled(String string2) {
    }

    public final void onStatusChanged(String string2, int n2, Bundle bundle) {
    }
}

