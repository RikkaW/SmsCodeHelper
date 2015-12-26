/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.telephony.CellLocation
 *  android.telephony.cdma.CdmaCellLocation
 *  android.telephony.gsm.GsmCellLocation
 *  java.lang.Object
 */
import android.telephony.CellLocation;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;

public final class agy {
    int a = Integer.MAX_VALUE;
    int b = Integer.MAX_VALUE;
    int c = Integer.MAX_VALUE;
    int d = Integer.MAX_VALUE;
    int e = Integer.MAX_VALUE;

    /*
     * Enabled aggressive block sorting
     */
    agy(CellLocation cellLocation) {
        if (cellLocation == null) return;
        {
            if (cellLocation instanceof GsmCellLocation) {
                cellLocation = (GsmCellLocation)cellLocation;
                this.e = cellLocation.getCid();
                this.d = cellLocation.getLac();
                return;
            } else {
                if (!(cellLocation instanceof CdmaCellLocation)) return;
                {
                    cellLocation = (CdmaCellLocation)cellLocation;
                    this.c = cellLocation.getBaseStationId();
                    this.b = cellLocation.getNetworkId();
                    this.a = cellLocation.getSystemId();
                    return;
                }
            }
        }
    }
}

