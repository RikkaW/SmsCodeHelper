/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.telephony.CellLocation
 *  android.telephony.PhoneStateListener
 *  android.telephony.ServiceState
 *  android.telephony.SignalStrength
 *  java.lang.Integer
 *  java.lang.String
 *  java.lang.System
 */
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;

final class agh
extends PhoneStateListener {
    private /* synthetic */ agf a;

    private agh(agf agf2) {
        this.a = agf2;
    }

    /* synthetic */ agh(agf agf2, byte by2) {
        this(agf2);
    }

    public final void onCellLocationChanged(CellLocation cellLocation) {
        try {
            agf.b(this.a, System.currentTimeMillis());
            agf.a(this.a, cellLocation);
            super.onCellLocationChanged(cellLocation);
            return;
        }
        catch (Exception var1_2) {
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final void onServiceStateChanged(ServiceState serviceState) {
        try {
            if (serviceState.getState() == 0) {
                agf.a(this.a, true);
                String[] arrstring = agf.a(agf.f(this.a));
                agf.a(this.a, Integer.parseInt((String)arrstring[0]));
                agf.b(this.a, Integer.parseInt((String)arrstring[1]));
            } else {
                agf.a(this.a, false);
            }
            super.onServiceStateChanged(serviceState);
            return;
        }
        catch (Exception var1_2) {
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final void onSignalStrengthsChanged(SignalStrength signalStrength) {
        try {
            if (agf.g(this.a)) {
                agf.c(this.a, signalStrength.getCdmaDbm());
            } else {
                agf.c(this.a, signalStrength.getGsmSignalStrength());
                if (agf.h(this.a) == 99) {
                    agf.c(this.a, -1);
                } else {
                    agf.c(this.a, agf.h(this.a) * 2 - 113);
                }
            }
            super.onSignalStrengthsChanged(signalStrength);
            return;
        }
        catch (Exception var1_2) {
            return;
        }
    }
}

