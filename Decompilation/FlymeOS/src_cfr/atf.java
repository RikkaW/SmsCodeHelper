/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Character
 *  java.lang.Object
 *  java.lang.String
 */
public class atf {
    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean a(String string2) {
        boolean bl2 = false;
        if (string2 == null) return true;
        int n2 = string2.length();
        if (n2 == 0) {
            return true;
        }
        int n3 = 0;
        while (n3 < n2) {
            boolean bl3 = bl2;
            if (!Character.isWhitespace((char)string2.charAt(n3))) return bl3;
            ++n3;
        }
        return true;
    }

    public static boolean b(String string2) {
        if (!atf.a(string2)) {
            return true;
        }
        return false;
    }
}

