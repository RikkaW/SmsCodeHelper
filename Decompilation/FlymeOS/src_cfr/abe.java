/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.telephony.MzTelephonyManager
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.Context;
import android.telephony.MzTelephonyManager;
import android.text.TextUtils;
import android.util.Log;

public class abe {
    public static String a;
    public static String b;

    public static String a(int n2) {
        if (n2 == 0) {
            if (a != null) {
                return a;
            }
            return "";
        }
        if (n2 == 1) {
            if (b != null) {
                return b;
            }
            return "";
        }
        return "";
    }

    public static String a(Context context, int n2) {
        return MzTelephonyManager.getScAddress((Context)context, (int)n2);
    }

    private static void a(Context context, int n2, boolean bl2) {
        Log.d((String)"SmsCenterUtils", (String)("getSmsCenterFromSim slotId= " + n2));
        new abf(context, n2, bl2).start();
    }

    public static void a(Context context, String string2, int n2) {
        MzTelephonyManager.setScAddress((Context)context, (String)string2, (int)n2);
    }

    public static void a(Context context, boolean bl2) {
        if (!zv.a) {
            abe.a(context, 0, bl2);
            return;
        }
        abe.a(context, 0, bl2);
        abe.a(context, 1, bl2);
    }

    public static void a(String string2, int n2) {
        new abg(n2, string2).start();
    }

    static /* synthetic */ void b(String string2, int n2) {
        abe.c(string2, n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static void c(String string2, int n2) {
        if (n2 == 0) {
            if (TextUtils.isEmpty((CharSequence)string2)) {
                Log.e((String)"SmsCenterUtils", (String)"setSmsCenterNumber mSmsCenterNumber1 is null");
                return;
            }
            a = string2;
            Log.e((String)"SmsCenterUtils", (String)("setSmsCenterNumber mSmsCenterNumber1 = " + string2));
            return;
        }
        if (n2 != 1) return;
        {
            if (!TextUtils.isEmpty((CharSequence)string2)) {
                b = string2;
                Log.e((String)"SmsCenterUtils", (String)("setSmsCenterNumber mSmsCenterNumber2 = " + string2));
                return;
            }
        }
        Log.e((String)"SmsCenterUtils", (String)"setSmsCenterNumber mSmsCenterNumber2 is null");
    }
}

