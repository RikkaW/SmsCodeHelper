/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 */
import android.text.TextUtils;

public class anu {
    public static final String[] a = new String[]{"17951", "12593", "17910", "17911", "10193", "10131", "96531", "17900", "17901", "17909", "11808"};
    private static final String[] b = new String[]{"+86"};

    public static String a(String string2) {
        return anu.a(string2, b);
    }

    private static String a(String string2, String[] arrstring) {
        int n2 = arrstring.length;
        int n3 = 0;
        while (n3 < n2) {
            String string3 = arrstring[n3];
            if (string2.startsWith(string3)) {
                return string2.substring(string3.length());
            }
            ++n3;
        }
        return string2;
    }

    public static String b(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return string2;
        }
        return anu.e(anu.a(anu.d(string2)));
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static boolean c(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return false;
        }
        if (string2.length() < 7) return false;
        if (string2.length() > 11) return false;
        char c2 = string2.charAt(0);
        char c3 = string2.charAt(1);
        if (c2 != '1') return false;
        if (c3 == '3') return true;
        if (c3 == '4') return true;
        if (c3 == '5') return true;
        if (c3 == '8') return true;
        if (c3 != '7') return false;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String d(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        int n2 = 0;
        while (n2 < string2.length()) {
            char c2 = string2.charAt(n2);
            if (c2 >= '0' && c2 <= '9') {
                stringBuilder.append(c2);
            } else if (n2 == 0 && c2 == '+') {
                stringBuilder.append(c2);
            }
            ++n2;
        }
        return stringBuilder.toString();
    }

    private static String e(String string2) {
        return anu.a(string2, a);
    }
}

