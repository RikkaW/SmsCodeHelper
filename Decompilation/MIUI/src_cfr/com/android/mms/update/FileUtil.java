/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  android.util.Log
 *  java.io.File
 *  java.io.FileOutputStream
 *  java.io.OutputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.android.mms.update;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.update.StreamRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class FileUtil {
    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static File createFile(String string2) {
        if ((string2 = new File(string2)).exists()) {
            return string2;
        }
        File file = string2.getParentFile();
        if (!file.exists() && !file.mkdirs()) {
            Log.e((String)"FileUtil", (String)("Create file " + file.getAbsolutePath() + " fails"));
            return file;
        }
        try {
            boolean bl = string2.createNewFile();
            if (bl) return string2;
            return null;
        }
        catch (IOException var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean deleteFile(File file) {
        if (file != null && file.exists()) {
            if (!file.isDirectory()) {
                return file.delete();
            }
            File[] arrfile = file.listFiles();
            int n = arrfile.length;
            int n2 = 0;
            do {
                if (n2 >= n) {
                    file.delete();
                    return true;
                }
                if (!FileUtil.deleteFile(arrfile[n2])) break;
                ++n2;
            } while (true);
        }
        return false;
    }

    public static boolean downLoadFileWithHeader(Context context, String string2, String string3, Map<String, String> map) {
        return FileUtil.downLoadFileWithHeader(context, string2, string3, map, 0);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static boolean downLoadFileWithHeader(Context var0, String var1_5, String var2_7, Map<String, String> var3_12, int var4_13) {
        block21 : {
            if (TextUtils.isEmpty((CharSequence)var1_5) || TextUtils.isEmpty((CharSequence)var2_7)) {
                Log.d((String)"FileUtil", (String)"The url or dest path should be null");
                return false;
            }
            var5_14 = false;
            var7_15 = new StreamRequest((Context)var0, var1_5);
            var7_15.overwriteNetworkAccess(var4_13);
            var1_5 = null;
            var6_16 = null;
            var0 = var1_5;
            if (FileUtil.createFile(var2_7) != null) break block21;
            var0 = var1_5;
            Log.e((String)"FileUtil", (String)("Create file " + var2_7 + " fails"));
            if (false == false) return false;
            try {
                throw new NullPointerException();
            }
            catch (IOException var0_1) {
                var0_1.printStackTrace();
                return false;
            }
        }
        var0 = var1_5;
        var1_5 = new FileOutputStream(new File(var2_7));
        if (var7_15.requestStream((OutputStream)var1_5, var3_12) == 0) {
            Log.d((String)"FileUtil", (String)("Download file " + var2_7 + " success"));
            var5_14 = true;
        } else {
            Log.d((String)"FileUtil", (String)("Download file " + var2_7 + " failed"));
        }
        if (var1_5 == null) return var5_14;
        try {
            var1_5.close();
            return var5_14;
        }
        catch (IOException var0_3) {
            var0_3.printStackTrace();
            return var5_14;
        }
        catch (IOException var2_8) {}
        ** GOTO lbl-1000
        catch (Throwable var2_10) {
            var0 = var1_5;
            var1_5 = var2_10;
            ** GOTO lbl-1000
        }
lbl-1000: // 2 sources:
        {
            do {
                var0 = var1_5;
                try {
                    var2_9.printStackTrace();
                    if (var1_5 == null) return false;
                }
                catch (Throwable var1_6) lbl-1000: // 2 sources:
                {
                    if (var0 == null) throw var1_5;
                    try {
                        var0.close();
                    }
                    catch (IOException var0_4) {
                        var0_4.printStackTrace();
                        throw var1_5;
                    }
                    throw var1_5;
                }
                try {
                    var1_5.close();
                    return false;
                }
                catch (IOException var0_2) {
                    var0_2.printStackTrace();
                    return false;
                }
                break;
            } while (true);
        }
        catch (IOException var2_11) {
            var1_5 = var6_16;
            ** continue;
        }
    }
}

