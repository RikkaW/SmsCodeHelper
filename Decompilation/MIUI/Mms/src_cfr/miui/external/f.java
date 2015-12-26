/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
package miui.external;

import android.util.Log;
import miui.external.SdkConstants;

class f
implements SdkConstants {
    f() {
    }

    public static Class<?> o() throws ClassNotFoundException {
        try {
            Class class_ = Class.forName((String)"miui.core.SdkManager");
            return class_;
        }
        catch (ClassNotFoundException var0_1) {
            Class class_;
            try {
                class_ = Class.forName((String)"com.miui.internal.core.SdkManager");
            }
            catch (ClassNotFoundException var0_3) {
                Log.e((String)"miuisdk", (String)"no sdk found");
                throw var0_3;
            }
            Log.w((String)"miuisdk", (String)"using legacy sdk");
            return class_;
        }
    }
}

