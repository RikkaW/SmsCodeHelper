/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Character
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Locale
 */
package android.support.v4.text;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.text.ICUCompat;
import java.util.Locale;

public class TextUtilsCompat {
    private static String ARAB_SCRIPT_SUBTAG;
    private static String HEBR_SCRIPT_SUBTAG;
    public static final Locale ROOT;

    static {
        ROOT = new Locale("", "");
        ARAB_SCRIPT_SUBTAG = "Arab";
        HEBR_SCRIPT_SUBTAG = "Hebr";
    }

    private static int getLayoutDirectionFromFirstChar(Locale locale) {
        switch (Character.getDirectionality((char)locale.getDisplayName(locale).charAt(0))) {
            default: {
                return 0;
            }
            case 1: 
            case 2: 
        }
        return 1;
    }

    public static int getLayoutDirectionFromLocale(@Nullable Locale locale) {
        if (locale != null && !locale.equals((Object)ROOT)) {
            String string2 = ICUCompat.getScript(ICUCompat.addLikelySubtags(locale.toString()));
            if (string2 == null) {
                return TextUtilsCompat.getLayoutDirectionFromFirstChar(locale);
            }
            if (string2.equalsIgnoreCase(ARAB_SCRIPT_SUBTAG) || string2.equalsIgnoreCase(HEBR_SCRIPT_SUBTAG)) {
                return 1;
            }
        }
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    @NonNull
    public static String htmlEncode(@NonNull String string2) {
        StringBuilder stringBuilder = new StringBuilder();
        int n2 = 0;
        while (n2 < string2.length()) {
            char c2 = string2.charAt(n2);
            switch (c2) {
                default: {
                    stringBuilder.append(c2);
                    break;
                }
                case '<': {
                    stringBuilder.append("&lt;");
                    break;
                }
                case '>': {
                    stringBuilder.append("&gt;");
                    break;
                }
                case '&': {
                    stringBuilder.append("&amp;");
                    break;
                }
                case '\'': {
                    stringBuilder.append("&#39;");
                    break;
                }
                case '\"': {
                    stringBuilder.append("&quot;");
                }
            }
            ++n2;
        }
        return stringBuilder.toString();
    }
}

