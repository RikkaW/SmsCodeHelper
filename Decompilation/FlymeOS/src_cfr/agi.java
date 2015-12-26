/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.location.GpsStatus
 *  android.location.GpsStatus$NmeaListener
 *  java.lang.Object
 *  java.lang.String
 */
import android.location.GpsStatus;

final class agi
implements GpsStatus.NmeaListener {
    private /* synthetic */ agf a;

    private agi(agf agf2) {
        this.a = agf2;
    }

    /* synthetic */ agi(agf agf2, byte by2) {
        this(agf2);
    }

    public final void onNmeaReceived(long l2, String string2) {
        try {
            agf.c(this.a, l2);
            agf.a(this.a, string2);
            return;
        }
        catch (Exception var3_3) {
            return;
        }
    }
}

