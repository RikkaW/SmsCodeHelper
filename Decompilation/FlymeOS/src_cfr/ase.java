/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.Character
 *  java.lang.Object
 *  java.lang.String
 */
import android.text.TextUtils;

public class ase {
    /*
     * Enabled aggressive block sorting
     */
    public static boolean a(String string2) {
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            int n2 = string2.length();
            int n3 = 0;
            do {
                if (n3 >= n2) {
                    return true;
                }
                char c2 = string2.charAt(n3);
                if (!Character.isDigit((char)c2) && c2 != '+') break;
                ++n3;
            } while (true);
        }
        return false;
    }
}

