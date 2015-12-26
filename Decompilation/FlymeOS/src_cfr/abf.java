/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.preference.PreferenceManager
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

final class abf
extends Thread {
    final /* synthetic */ Context a;
    final /* synthetic */ int b;
    final /* synthetic */ boolean c;

    abf(Context context, int n2, boolean bl2) {
        this.a = context;
        this.b = n2;
        this.c = bl2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void run() {
        try {
            Thread.sleep((long)500);
            boolean bl2 = 5 == aac.c(this.a, this.b);
            Log.e((String)"SmsCenterUtils", (String)("getSmsCenterFromSim slotId = " + this.b + ", isSimReady = " + bl2));
            String string2 = abe.a(this.a, this.b);
            for (int i2 = 0; bl2 && TextUtils.isEmpty((CharSequence)string2) && i2 < 5; ++i2) {
                Thread.sleep((long)1000);
                string2 = abe.a(this.a, this.b);
            }
            if (bl2) {
                Log.e((String)"SmsCenterUtils", (String)("getSmsCenterFromSim slotId = " + this.b + ", smscAddr = " + string2));
                abe.b(string2, this.b);
                if (!this.c) return;
                {
                    PreferenceManager.getDefaultSharedPreferences((Context)this.a).edit().putString("pref_key_mms_center_number_slot" + (this.b + 1), string2).commit();
                    return;
                }
            }
            Log.e((String)"SmsCenterUtils", (String)("getSmsCenterFromSim slotId = " + this.b + " is not ready"));
            return;
        }
        catch (Exception var3_4) {
            Log.e((String)"SmsCenterUtils", (String)("getSmsCenterFromSim simcard[ " + this.b + " ] e = " + (Object)((Object)var3_4)));
            return;
        }
    }
}

