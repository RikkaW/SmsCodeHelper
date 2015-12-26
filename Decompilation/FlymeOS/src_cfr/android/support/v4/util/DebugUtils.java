/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package android.support.v4.util;

public class DebugUtils {
    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static void buildShortClassTag(Object var0, StringBuilder var1_1) {
        if (var0 == null) {
            var1_1.append("null");
            return;
        }
        var4_2 = var0.getClass().getSimpleName();
        if (var4_2 == null) ** GOTO lbl-1000
        var3_3 = var4_2;
        if (var4_2.length() <= 0) lbl-1000: // 2 sources:
        {
            var4_2 = var0.getClass().getName();
            var2_4 = var4_2.lastIndexOf(46);
            var3_3 = var4_2;
            if (var2_4 > 0) {
                var3_3 = var4_2.substring(var2_4 + 1);
            }
        }
        var1_1.append(var3_3);
        var1_1.append('{');
        var1_1.append(Integer.toHexString((int)System.identityHashCode((Object)var0)));
    }
}

