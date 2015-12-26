/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.Log
 *  java.lang.String
 *  java.lang.Thread
 */
import android.content.Context;
import android.util.Log;
import com.android.mms.MmsApp;

final class abg
extends Thread {
    final /* synthetic */ int a;
    final /* synthetic */ String b;

    abg(int n2, String string2) {
        this.a = n2;
        this.b = string2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void run() {
        try {
            boolean bl2 = 5 == aac.a(this.a);
            Log.e((String)"SmsCenterUtils", (String)("setSmsCenterToSim slotId = " + this.a + ", isSimReady = " + bl2));
            if (bl2) {
                abe.a((Context)MmsApp.c(), this.b, this.a);
                abe.b(this.b, this.a);
                return;
            }
            Log.e((String)"SmsCenterUtils", (String)("setSmsCenterToSim slotId = " + this.a + " is not ready"));
            return;
        }
        catch (Exception var2_2) {
            return;
        }
    }
}

