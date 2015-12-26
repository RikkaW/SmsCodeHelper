/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.lang.reflect.Method
 */
package android.support.v4.text;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ICUCompatIcs {
    private static final String TAG = "ICUCompatIcs";
    private static Method sAddLikelySubtagsMethod;
    private static Method sGetScriptMethod;

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static {
        Class class_;
        try {
            class_ = Class.forName((String)"libcore.icu.ICU");
            if (class_ == null) return;
        }
        catch (Exception var0_1) {
            Log.w((String)"ICUCompatIcs", (Throwable)var0_1);
            return;
        }
        sGetScriptMethod = class_.getMethod("getScript", new Class[]{String.class});
        sAddLikelySubtagsMethod = class_.getMethod("addLikelySubtags", new Class[]{String.class});
    }

    ICUCompatIcs() {
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String addLikelySubtags(String string2) {
        try {
            if (sAddLikelySubtagsMethod == null) return string2;
            return (String)sAddLikelySubtagsMethod.invoke((Object)null, new Object[]{string2});
        }
        catch (IllegalAccessException var1_2) {
            Log.w((String)"ICUCompatIcs", (Throwable)var1_2);
        }
        return string2;
        catch (InvocationTargetException invocationTargetException) {
            Log.w((String)"ICUCompatIcs", (Throwable)invocationTargetException);
            return string2;
        }
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String getScript(String string2) {
        try {
            if (sGetScriptMethod == null) return null;
            return (String)sGetScriptMethod.invoke((Object)null, new Object[]{string2});
        }
        catch (IllegalAccessException var0_1) {
            Log.w((String)"ICUCompatIcs", (Throwable)var0_1);
        }
        return null;
        catch (InvocationTargetException invocationTargetException) {
            Log.w((String)"ICUCompatIcs", (Throwable)invocationTargetException);
            return null;
        }
    }
}

