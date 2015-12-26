/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 */
import android.text.TextUtils;
import com.ted.android.contacts.common.DataBus;

public class apo {
    private static final String[] a = new String[]{"+", "0", "400", "800"};

    public static String a(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return "";
        }
        return apo.d(anu.b(string2));
    }

    private static boolean b(String string2) {
        String[] arrstring = a;
        int n2 = arrstring.length;
        int n3 = 0;
        while (n3 < n2) {
            if (string2.startsWith(arrstring[n3])) {
                return true;
            }
            ++n3;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static boolean c(String string2) {
        int n2 = string2.length();
        if (n2 <= 8 && n2 >= 5 && string2.startsWith("96") || n2 <= 8 && n2 >= 7 && !string2.startsWith("1") && !string2.startsWith("9")) {
            return true;
        }
        return false;
    }

    private static String d(String string2) {
        String string3 = string2;
        if (!apo.b(string2)) {
            string3 = string2;
            if (apo.c(string2)) {
                string3 = string2;
                if (!TextUtils.isEmpty((CharSequence)DataBus.USER_CITY_CODE)) {
                    string3 = String.valueOf((Object)DataBus.USER_CITY_CODE) + string2;
                }
            }
        }
        return string3;
    }
}

