/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.SparseArray
 *  java.lang.Object
 *  java.lang.String
 */
import android.util.SparseArray;

public class ahk {
    static String a = null;
    static String b = null;
    static String c = null;
    static String d = "";
    static String e = "";
    static String f = "";
    static boolean g = false;
    static boolean h = true;
    static long i = 10000;
    static long j = 30000;
    static boolean k = true;
    static final SparseArray<String> l = new SparseArray();
    static final String[] m;

    static {
        l.append(0, (Object)"UNKNOWN");
        l.append(1, (Object)"GPRS");
        l.append(2, (Object)"EDGE");
        l.append(3, (Object)"UMTS");
        l.append(4, (Object)"CDMA");
        l.append(5, (Object)"EVDO_0");
        l.append(6, (Object)"EVDO_A");
        l.append(7, (Object)"1xRTT");
        l.append(8, (Object)"HSDPA");
        l.append(9, (Object)"HSUPA");
        l.append(10, (Object)"HSPA");
        l.append(11, (Object)"IDEN");
        l.append(12, (Object)"EVDO_B");
        l.append(13, (Object)"LTE");
        l.append(14, (Object)"EHRPD");
        l.append(15, (Object)"HSPAP");
        m = new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS", "android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.INTERNET", "android.permission.READ_PHONE_STATE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    }

    static void a(String string2) {
        d = string2;
    }

    static void a(boolean bl2) {
        g = bl2;
    }

    static void b(String string2) {
        e = string2;
    }

    static void c(String string2) {
        f = string2;
    }
}

