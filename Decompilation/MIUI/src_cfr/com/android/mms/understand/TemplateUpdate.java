/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.FileUtils
 *  android.text.TextUtils
 *  android.util.Log
 *  java.io.BufferedWriter
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.FileOutputStream
 *  java.io.OutputStream
 *  java.io.OutputStreamWriter
 *  java.io.Writer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  miui.os.Environment
 *  miui.util.Patcher
 */
package com.android.mms.understand;

import android.os.FileUtils;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.understand.UnderstandLoader;
import com.android.mms.util.Coder;
import com.android.mms.util.MiStatSdkHelper;
import com.android.mms.util.ThreadPool;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import miui.os.Environment;
import miui.util.Patcher;

public class TemplateUpdate {
    private static boolean createFile(String string2) {
        if ((string2 = new File(string2)).exists()) {
            return true;
        }
        File file = string2.getParentFile();
        if (!file.exists() && !file.mkdirs()) {
            Log.e((String)"TemplateUpdate", (String)("Create file " + file.getAbsolutePath() + " fails"));
            return false;
        }
        try {
            boolean bl = string2.createNewFile();
            return bl;
        }
        catch (IOException var0_1) {
            var0_1.printStackTrace();
            return false;
        }
    }

    public static String getDownloadedFile() {
        return "/data/data/com.android.mms/app_understand/downloads.tmp";
    }

    public static String getTemporaryFile() {
        return "/data/data/com.android.mms/app_understand/understand.temp";
    }

    public static String getUpdatedFile() {
        return "/data/data/com.android.mms/app_understand/understand.zip";
    }

    public static String getVersionFile() {
        return "/data/data/com.android.mms/app_understand/version";
    }

    public static void onTemplateDownloaded(final UpdatePatch updatePatch) {
        ThreadPool.execute(new Runnable(){

            @Override
            public void run() {
                TemplateUpdate.updateTemplate(updatePatch);
            }
        });
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private static void updateTemplate(UpdatePatch var0) {
        // MONITORENTER : com.android.mms.understand.TemplateUpdate.class
        if (var0.mIsIncremental) {
            var5_7 = new Patcher();
            var2_13 = Environment.getMiuiDataDirectory();
            var4_19 = var2_13.getAbsolutePath() + "/mms/understand.zip";
            var2_13 = var3_21 = TemplateUpdate.getUpdatedFile();
            if (!new File(var3_21).exists()) {
                var2_13 = var4_19;
            }
            var3_21 = TemplateUpdate.getTemporaryFile();
            var4_19 = TemplateUpdate.getDownloadedFile();
            Log.v((String)"TemplateUpdate", (String)(" Trying apply patch file: " + var4_19 + " on old File " + (String)var2_13));
            if (!TextUtils.isEmpty((CharSequence)var0.mOldMd5) && !var0.mOldMd5.equals((Object)(var6_25 = Coder.encodeMD5(new File((String)var2_13))))) {
                if (!TextUtils.isEmpty((CharSequence)var0.mMd5) && (var1_27 = var0.mMd5.equals((Object)var6_25))) {
                    // MONITOREXIT : com.android.mms.understand.TemplateUpdate.class
                    return;
                }
                TemplateUpdate.updateTemplateVersion(0);
                MiStatSdkHelper.recordStringPropertyEvent("understand_category", "template_update", "3");
                Log.w((String)"TemplateUpdate", (String)" old md5 verified fail!");
                return;
            }
            if (var5_7.applyPatch((String)var2_13, var3_21, var4_19) != 0) {
                MiStatSdkHelper.recordStringPropertyEvent("understand_category", "template_update", "2");
                Log.e((String)"TemplateUpdate", (String)" error apply patch!!");
                new File(var3_21).delete();
                new File(var4_19).delete();
                TemplateUpdate.updateTemplateVersion(0);
                return;
            }
            Log.v((String)"TemplateUpdate", (String)" apply success!");
            if (!TextUtils.isEmpty((CharSequence)var0.mMd5)) {
                if (!var0.mMd5.equals((Object)Coder.encodeMD5(new File(var3_21)))) {
                    TemplateUpdate.updateTemplateVersion(0);
                    MiStatSdkHelper.recordStringPropertyEvent("understand_category", "template_update", "1");
                    Log.w((String)"TemplateUpdate", (String)" md5 verified fail!");
                    return;
                }
                Log.v((String)"TemplateUpdate", (String)" md5 verified ok");
            }
            new File(var4_19).delete();
            var2_13 = TemplateUpdate.getUpdatedFile();
            if (TemplateUpdate.createFile((String)var2_13)) {
                FileUtils.copyFile((File)new File(var3_21), (File)new File((String)var2_13));
            }
            new File(var3_21).delete();
            MiStatSdkHelper.recordStringPropertyEvent("understand_category", "template_update", "0");
        } else {
            block51 : {
                block52 : {
                    block50 : {
                        var8_28 = null;
                        var7_29 = null;
                        var2_14 = null;
                        var5_8 = null;
                        var6_26 = null;
                        var3_22 = var8_28;
                        var4_20 /* !! */  = var2_14;
                        if (!TextUtils.isEmpty((CharSequence)var0.mMd5)) {
                            var3_22 = var8_28;
                            var4_20 /* !! */  = var2_14;
                            if (!var0.mMd5.equals((Object)Coder.encodeMD5(new File(TemplateUpdate.getDownloadedFile())))) break block50;
                            var3_22 = var8_28;
                            var4_20 /* !! */  = var2_14;
                            Log.v((String)"TemplateUpdate", (String)" md5 verified ok");
                        }
                        var3_22 = var8_28;
                        var4_20 /* !! */  = var2_14;
                        var2_14 = new FileInputStream(TemplateUpdate.getDownloadedFile());
                        var4_20 /* !! */  = (InputStream)new byte[var2_14.available()];
                        var2_14.read((byte[])var4_20 /* !! */ );
                        var3_22 = new FileOutputStream(TemplateUpdate.getUpdatedFile());
                        var3_22.write((byte[])var4_20 /* !! */ );
                        MiStatSdkHelper.recordStringPropertyEvent("understand_category", "template_update", "0");
                        ** if (var3_22 == null) goto lbl-1000
lbl-1000: // 1 sources:
                        {
                            var3_22.close();
                        }
lbl-1000: // 2 sources:
                        {
                            break block51;
                        }
                    }
                    var3_22 = var8_28;
                    var4_20 /* !! */  = var2_14;
                    MiStatSdkHelper.recordStringPropertyEvent("understand_category", "template_update", "6");
                    var3_22 = var8_28;
                    var4_20 /* !! */  = var2_14;
                    Log.w((String)"TemplateUpdate", (String)" md5 verified fail!");
                    if (!false) break block52;
                    try {
                        throw new NullPointerException();
                    }
                    catch (IOException var0_1) {
                        var0_1.printStackTrace();
                    }
                }
                if (false) {
                    try {
                        throw new NullPointerException();
                    }
                    catch (IOException var0_2) {
                        var0_2.printStackTrace();
                    }
                }
                Log.v((String)"TemplateUpdate", (String)(" Delete the downloaded file: " + TemplateUpdate.getDownloadedFile().toString()));
                new File(TemplateUpdate.getDownloadedFile()).delete();
                return;
                catch (IOException var3_23) {
                    var3_23.printStackTrace();
                }
            }
            if (var2_14 != null) {
                try {
                    var2_14.close();
                }
                catch (IOException var2_15) {
                    var2_15.printStackTrace();
                }
            }
            Log.v((String)"TemplateUpdate", (String)(" Delete the downloaded file: " + TemplateUpdate.getDownloadedFile().toString()));
            new File(TemplateUpdate.getDownloadedFile()).delete();
        }
        ** GOTO lbl159
        catch (IOException var5_9) {
            block54 : {
                block53 : {
                    var2_14 = var7_29;
                    ** GOTO lbl120
                    catch (Throwable var0_5) {
                        var3_22 = var2_14;
                        var4_20 /* !! */  = var5_8;
                        ** GOTO lbl-1000
                    }
                    catch (Throwable var0_6) {
                        var4_20 /* !! */  = var3_22;
                        var3_22 = var2_14;
                        ** GOTO lbl-1000
                    }
                    catch (IOException var5_11) {
                        break block53;
                    }
                    catch (IOException var5_12) {
                        var6_26 = var3_22;
                    }
                }
                var3_22 = var2_14;
                var4_20 /* !! */  = var6_26;
                try {
                    MiStatSdkHelper.recordStringPropertyEvent("understand_category", "template_update", "8");
                    var3_22 = var2_14;
                    var4_20 /* !! */  = var6_26;
                    var5_10.printStackTrace();
                    if (var6_26 == null) break block54;
                }
                catch (Throwable var0_3) lbl-1000: // 3 sources:
                {
                    if (var4_20 /* !! */  != null) {
                        try {
                            var4_20 /* !! */ .close();
                        }
                        catch (IOException var2_17) {
                            var2_17.printStackTrace();
                        }
                    }
                    if (var3_22 != null) {
                        try {
                            var3_22.close();
                        }
                        catch (IOException var2_18) {
                            var2_18.printStackTrace();
                        }
                    }
                    Log.v((String)"TemplateUpdate", (String)(" Delete the downloaded file: " + TemplateUpdate.getDownloadedFile().toString()));
                    new File(TemplateUpdate.getDownloadedFile()).delete();
                    throw var0_4;
                }
                try {
                    var6_26.close();
                }
                catch (IOException var3_24) {
                    var3_24.printStackTrace();
                }
            }
            if (var2_14 != null) {
                try {
                    var2_14.close();
                }
                catch (IOException var2_16) {
                    var2_16.printStackTrace();
                }
            }
            Log.v((String)"TemplateUpdate", (String)(" Delete the downloaded file: " + TemplateUpdate.getDownloadedFile().toString()));
            new File(TemplateUpdate.getDownloadedFile()).delete();
lbl159: // 3 sources:
            UnderstandLoader.rePrepare(var0);
            return;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void updateTemplateVersion(long var0) {
        block35 : {
            Log.v((String)"TemplateUpdate", (String)(" set DataVersion, version is " + var0));
            var7_1 = null;
            var9_2 = null;
            var8_3 = null;
            var5_4 = null;
            var3_9 = null;
            var6_15 = null;
            var10_17 = null;
            var4_18 = null;
            var2_23 = new FileOutputStream(TemplateUpdate.getVersionFile());
            var3_9 = new OutputStreamWriter((OutputStream)var2_23);
            var4_18 = new BufferedWriter((Writer)var3_9);
            var4_18.write(String.valueOf((long)var0));
            var4_18.newLine();
            var4_18.flush();
            if (var4_18 == null) break block35;
            try {
                var4_18.close();
            }
            catch (IOException var4_19) {
                var4_19.printStackTrace();
            }
        }
        if (var3_9 != null) {
            try {
                var3_9.close();
            }
            catch (IOException var3_10) {
                var3_10.printStackTrace();
            }
        }
        if (var2_23 == null) return;
        try {
            var2_23.close();
            return;
        }
        catch (IOException var2_24) {
            var2_24.printStackTrace();
            return;
        }
        catch (IOException var5_5) {
            block37 : {
                block36 : {
                    var2_23 = var3_9;
                    var3_9 = var5_5;
                    ** GOTO lbl66
                    catch (Throwable var4_21) {
                        var3_9 = var10_17;
                        var7_1 = var9_2;
                        ** GOTO lbl76
                    }
                    catch (Throwable var4_22) {
                        var7_1 = var9_2;
                        ** GOTO lbl76
                    }
                    catch (Throwable var5_7) {
                        var7_1 = var4_18;
                        var4_18 = var5_7;
                        ** GOTO lbl76
                    }
                    catch (IOException var3_14) {
                        break block36;
                    }
                    catch (IOException var5_8) {
                        var4_18 = var3_9;
                        var3_9 = var5_8;
                        break block36;
                    }
                    catch (IOException var6_16) {
                        var5_4 = var3_9;
                        var8_3 = var4_18;
                        var3_9 = var6_16;
                        var4_18 = var5_4;
                    }
                }
                var5_4 = var2_23;
                var6_15 = var4_18;
                var7_1 = var8_3;
                try {
                    var3_9.printStackTrace();
                    if (var8_3 == null) break block37;
                }
                catch (Throwable var4_20) {
                    var3_9 = var6_15;
                    var2_23 = var5_4;
lbl76: // 4 sources:
                    if (var7_1 != null) {
                        try {
                            var7_1.close();
                        }
                        catch (IOException var5_6) {
                            var5_6.printStackTrace();
                        }
                    }
                    if (var3_9 != null) {
                        try {
                            var3_9.close();
                        }
                        catch (IOException var3_13) {
                            var3_13.printStackTrace();
                        }
                    }
                    if (var2_23 == null) throw var4_18;
                    try {
                        var2_23.close();
                    }
                    catch (IOException var2_26) {
                        var2_26.printStackTrace();
                        throw var4_18;
                    }
                    throw var4_18;
                }
                try {
                    var8_3.close();
                }
                catch (IOException var3_11) {
                    var3_11.printStackTrace();
                }
            }
            if (var4_18 != null) {
                try {
                    var4_18.close();
                }
                catch (IOException var3_12) {
                    var3_12.printStackTrace();
                }
            }
            if (var2_23 == null) return;
            try {
                var2_23.close();
                return;
            }
            catch (IOException var2_25) {
                var2_25.printStackTrace();
                return;
            }
        }
    }

    public static class UpdatePatch {
        public boolean mIsIncremental;
        public String mMd5;
        public String mOldMd5;
        public long mVersion;
    }

}

