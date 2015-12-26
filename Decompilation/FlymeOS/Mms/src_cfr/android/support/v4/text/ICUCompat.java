/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.text;

import android.os.Build;
import android.support.v4.text.ICUCompatIcs;

public class ICUCompat {
    private static final ICUCompatImpl IMPL = Build.VERSION.SDK_INT >= 14 ? new ICUCompatImplIcs() : new ICUCompatImplBase();

    public static String addLikelySubtags(String string2) {
        return IMPL.addLikelySubtags(string2);
    }

    public static String getScript(String string2) {
        return IMPL.getScript(string2);
    }

    static interface ICUCompatImpl {
        public String addLikelySubtags(String var1);

        public String getScript(String var1);
    }

    static class ICUCompatImplBase
    implements ICUCompatImpl {
        ICUCompatImplBase() {
        }

        @Override
        public String addLikelySubtags(String string2) {
            return string2;
        }

        @Override
        public String getScript(String string2) {
            return null;
        }
    }

    static class ICUCompatImplIcs
    implements ICUCompatImpl {
        ICUCompatImplIcs() {
        }

        @Override
        public String addLikelySubtags(String string2) {
            return ICUCompatIcs.addLikelySubtags(string2);
        }

        @Override
        public String getScript(String string2) {
            return ICUCompatIcs.getScript(string2);
        }
    }

}

