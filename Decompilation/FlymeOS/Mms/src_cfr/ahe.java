/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.Context;
import android.util.Log;

public final class ahe {
    private static String a = "";

    protected static void a(String string2) {
        if (string2.equals((Object)"GPS_SATELLITE")) {
            // empty if block
        }
    }

    protected static boolean a(Context context) {
        if (context != null) {
            a = context.getPackageName();
            return true;
        }
        Log.d((String)a, (String)"Error: No SD Card!");
        return false;
    }
}

