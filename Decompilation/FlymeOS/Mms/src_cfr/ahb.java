/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
public final class ahb {
    protected static boolean a = false;
    protected static final String[] b = new String[]{"android.permission.READ_PHONE_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.CHANGE_WIFI_STATE", "android.permission.ACCESS_NETWORK_STATE"};

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected static boolean a(String[] arrstring, String string2) {
        boolean bl2;
        boolean bl3 = bl2 = false;
        if (arrstring == null) return bl3;
        bl3 = bl2;
        if (string2 == null) return bl3;
        int n2 = 0;
        do {
            bl3 = bl2;
            if (n2 >= arrstring.length) return bl3;
            if (arrstring[n2].equals((Object)string2)) {
                return true;
            }
            ++n2;
        } while (true);
    }
}

