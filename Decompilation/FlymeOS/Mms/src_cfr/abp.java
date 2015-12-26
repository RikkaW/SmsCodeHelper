/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.net.Uri
 *  android.util.Log
 *  java.io.FileNotFoundException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class abp {
    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static int a(Context var0, Uri var1_3) {
        try {
            var0 = var0.getContentResolver().openInputStream(var1_3);
            ** if (var0 != null) goto lbl-1000
        }
        catch (FileNotFoundException var0_1) {
            Log.e((String)"ThumbnailUtils", (String)var0_1.getMessage(), (Throwable)var0_1);
            return 0;
        }
lbl-1000: // 1 sources:
        {
            Log.w((String)"ThumbnailUtils", (String)"getOrientFromInputStream ipStream  null ");
            return 0;
        }
lbl-1000: // 1 sources:
        {
        }
        var2_4 = abp.a((InputStream)var0);
        try {
            var0.close();
            return var2_4;
        }
        catch (IOException var0_2) {
            Log.e((String)"ThumbnailUtils", (String)("getOrientFromInputStream " + var0_2.toString()));
            return var2_4;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static int a(InputStream var0) {
        if (var0 == null) {
            return 0;
        }
        var6_3 = new byte[6];
        if (var0.read(var6_3, 0, 4) != 4) return 0;
        if (var6_3[0] != -1) return 0;
        if (var6_3[1] != -40) return 0;
        if (var6_3[2] != -1) return 0;
        if (var6_3[3] != -31) return 0;
        if (2 != var0.read(var6_3, 0, 2)) return 0;
        var1_4 = ((var6_3[0] & 255) << 8) + (var6_3[1] & 255);
        if (var1_4 < 8) return 0;
        var5_5 = var1_4 - 8;
        if (6 != var0.read(var6_3, 0, 6)) return 0;
        if (var6_3[0] != 69) return 0;
        if (var6_3[1] != 120) return 0;
        if (var6_3[2] != 105) return 0;
        if (var6_3[3] != 102) return 0;
        if (var6_3[4] != 0) return 0;
        if (var6_3[5] != 0) return 0;
        var6_3 = new byte[var5_5];
        if (var5_5 < 12) return 0;
        try {
            if (var5_5 != var0.read(var6_3, 0, var5_5)) return 0;
        }
        catch (IOException var0_1) {
            Log.e((String)"ThumbnailUtils", (String)("getOrientFromInputStream " + var0_1.toString()));
            return 0;
        }
        catch (Exception var0_2) {
            Log.e((String)"ThumbnailUtils", (String)("getOrientFromInputStream " + var0_2.toString()));
            return 0;
        }
        if (var6_3[0] != 73 || var6_3[1] != 73) ** GOTO lbl37
        var2_6 = false;
        ** GOTO lbl40
lbl37: // 1 sources:
        if (var6_3[0] != 77) return 0;
        if (var6_3[1] != 77) return 0;
        var2_6 = true;
lbl40: // 2 sources:
        if (var2_6) {
            if (var6_3[2] != 0) return 0;
            if (var6_3[3] != 42) return 0;
        } else {
            if (var6_3[3] != 0) return 0;
            if (var6_3[2] != 42) {
                return 0;
            }
        }
        if (var2_6) {
            if (var6_3[4] != 0) return 0;
            if (var6_3[5] != 0) return 0;
            var3_7 = ((var6_3[6] & 255) << 8) + (var6_3[7] & 255);
        } else {
            if (var6_3[7] != 0) return 0;
            if (var6_3[6] != 0) return 0;
            var3_7 = ((var6_3[5] & 255) << 8) + (var6_3[4] & 255);
        }
        if (var3_7 > var5_5 - 2) return 0;
        var1_4 = var2_6 != false ? ((var6_3[var3_7] & 255) << 8) + (var6_3[var3_7 + 1] & 255) : ((var6_3[var3_7 + 1] & 255) << 8) + (var6_3[var3_7] & 255);
        if (var1_4 == 0) return 0;
        var4_8 = var3_7 + 2;
        var3_7 = var1_4;
        var1_4 = var4_8;
        while (var1_4 <= var5_5 - 12) {
            var4_8 = var2_6 != false ? ((var6_3[var1_4] & 255) << 8) + (var6_3[var1_4 + 1] & 255) : ((var6_3[var1_4 + 1] & 255) << 8) + (var6_3[var1_4] & 255);
            if (var4_8 == 274) {
                if (!var2_6) {
                    if (var6_3[var1_4 + 9] != 0) return 0;
                    var1_4 = var6_3[var1_4 + 8];
                    return var1_4 & 255;
                }
                if (var6_3[var1_4 + 8] != 0) return 0;
                return var6_3[var1_4 + 9] & 255;
            }
            if (--var3_7 == 0) return 0;
            var1_4 += 12;
        }
        return 0;
    }
}

