/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
import android.content.Context;
import android.content.SharedPreferences;

public class akk {
    public static final long a(Context context) {
        context = amx.c(context);
        long l2 = 0;
        if (context != null) {
            l2 = context.getLong("check_update_time", 0);
        }
        return l2;
    }

    public static final void a(Context context, int n2) {
        context = amx.c(context).edit();
        context.putInt("cur_need_update", n2);
        context.apply();
    }

    /*
     * Enabled aggressive block sorting
     */
    public static final boolean a(Context context, long l2) {
        long l3 = akk.a(context);
        if (akk.d(context) == 1 || l3 <= 0) {
            return true;
        }
        long l4 = System.currentTimeMillis();
        if (l2 <= 0) {
            l2 = 259200000;
        }
        if (Math.abs((long)(l4 - l3)) > l2) return true;
        return false;
    }

    public static final void b(Context context) {
        akk.b(context, System.currentTimeMillis());
    }

    private static final void b(Context context, long l2) {
        context = amx.c(context).edit();
        context.putLong("check_update_time", l2);
        context.apply();
    }

    public static final void c(Context context) {
        akk.b(context, 0);
    }

    private static final int d(Context context) {
        context = amx.c(context);
        int n2 = 0;
        if (context != null) {
            n2 = context.getInt("cur_need_update", 0);
        }
        return n2;
    }
}

