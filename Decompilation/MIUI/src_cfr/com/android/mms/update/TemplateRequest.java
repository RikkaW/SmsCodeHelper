/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.accounts.Account
 *  android.content.Context
 *  android.preference.PreferenceManager
 *  android.text.TextUtils
 *  android.util.Log
 *  java.io.BufferedReader
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.FileOutputStream
 *  java.io.InputStreamReader
 *  java.io.Reader
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.HashMap
 *  miui.os.Build
 *  miui.os.Environment
 *  miui.yellowpage.MiPubUtils
 *  org.json.JSONObject
 */
package com.android.mms.update;

import android.accounts.Account;
import android.content.Context;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.understand.TemplateUpdate;
import com.android.mms.update.FileUtil;
import com.android.mms.update.JSONRequest;
import com.android.mms.update.Network;
import com.android.mms.util.MiStatSdkHelper;
import com.android.mms.util.ThreadPool;
import com.android.mms.util.XiaomiAccount;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import miui.os.Build;
import miui.os.Environment;
import miui.yellowpage.MiPubUtils;
import org.json.JSONObject;

public class TemplateRequest {
    public static final File EXTERNAL_STORAGE_DIRECTORY = new File(Environment.getExternalStorageMiuiDirectory(), "Mms");
    private static String RESULT_ALL;
    private static String RESULT_DIFF;
    private static String RESULT_NO_UPDATE;
    public static String TEMPLATE_URL;
    private static boolean sIsRequesting;

    static {
        RESULT_NO_UPDATE = "no-update";
        RESULT_DIFF = "diff";
        RESULT_ALL = "all";
        TEMPLATE_URL = !Build.IS_STABLE_VERSION ? "http://trial.open.mp.huangye.miui.com/sms/smartr/template" : "http://open.mp.huangye.miui.com/sms/smartr/template";
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    private void copyFileToTarget(File var1_1) throws IOException {
        block17 : {
            var4_4 = null;
            var3_5 = null;
            try {
                var1_1 = new FileInputStream(var1_1);
            }
            catch (Throwable var2_7) {
                var1_1 = var4_4;
lbl7: // 3 sources:
                do {
                    if (var1_1 != null) {
                        var1_1.close();
                    }
                    throw var2_8;
                    finally {
                        if (var3_5 != null) {
                            var3_5.close();
                        }
                    }
                    break;
                } while (true);
            }
            var2_6 = new byte[var1_1.available()];
            var1_1.read(var2_6);
            if (!new File(TemplateUpdate.getDownloadedFile()).exists()) {
                FileUtil.createFile(TemplateUpdate.getDownloadedFile());
            }
            var4_4 = new FileOutputStream(TemplateUpdate.getDownloadedFile());
            var4_4.write(var2_6);
            if (var1_1 == null) break block17;
            var1_1.close();
        }
        return;
        finally {
            if (var4_4 != null) {
                var4_4.close();
            }
        }
        catch (Throwable var2_9) {
            ** GOTO lbl7
        }
        catch (Throwable var2_10) {
            var3_5 = var4_4;
            ** continue;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static long getDataVersion() {
        block49 : {
            var3 = 0;
            var12_1 = null;
            var8_2 = null;
            var9_4 = null;
            var11_16 = null;
            var16_17 = null;
            var6_18 = null;
            var15_25 = null;
            var13_26 = null;
            var7_27 = null;
            var14_30 = null;
            var10_31 = null;
            var5_34 = new FileInputStream(TemplateUpdate.getVersionFile());
            var6_18 = new InputStreamReader((InputStream)var5_34);
            var7_27 = new BufferedReader((Reader)var6_18);
            var8_2 = var7_27.readLine();
            var1_40 = var3;
            if (TextUtils.isEmpty((CharSequence)var8_2)) break block49;
            var0_41 = Integer.parseInt((String)var8_2);
            var1_40 = var0_41;
        }
        if (var7_27 != null) {
            var7_27.close();
        }
        ** GOTO lbl40
        catch (IOException var9_5) {
            block53 : {
                var5_34 = var15_25;
                ** GOTO lbl138
                catch (NumberFormatException var9_7) {
                    block52 : {
                        block51 : {
                            block50 : {
                                var11_16 = var12_1;
                                var10_31 = var13_26;
                                var5_34 = var16_17;
                                ** GOTO lbl84
                                catch (IOException var7_29) {
                                    var7_29.printStackTrace();
                                }
lbl40: // 2 sources:
                                if (var6_18 != null) {
                                    try {
                                        var6_18.close();
                                    }
                                    catch (IOException var6_24) {
                                        var6_24.printStackTrace();
                                    }
                                }
                                if (var5_34 == null) return var1_40;
                                try {
                                    var5_34.close();
                                    return var1_40;
                                }
                                catch (IOException var5_39) {
                                    var5_39.printStackTrace();
                                    return var1_40;
                                }
                                catch (Throwable var10_32) {
                                    var6_18 = var5_34;
                                    var7_27 = var14_30;
                                    var8_2 = var9_4;
                                    var5_34 = var10_32;
                                    ** GOTO lbl-1000
                                }
                                catch (Throwable var10_33) {
                                    var7_27 = var6_18;
                                    var6_18 = var5_34;
                                    var8_2 = var9_4;
                                    var5_34 = var10_33;
                                    ** GOTO lbl-1000
                                }
                                catch (Throwable var9_9) {
                                    var8_2 = var6_18;
                                    var6_18 = var5_34;
                                    var5_34 = var7_27;
                                    var7_27 = var8_2;
                                    var8_2 = var5_34;
                                    var5_34 = var9_9;
                                    ** GOTO lbl-1000
                                }
                                catch (NumberFormatException var9_10) {
                                    var10_31 = var13_26;
                                    var11_16 = var12_1;
                                    break block50;
                                }
                                catch (NumberFormatException var9_11) {
                                    var10_31 = var6_18;
                                    var11_16 = var12_1;
                                    break block50;
                                }
                                catch (NumberFormatException var9_12) {
                                    var10_31 = var6_18;
                                    var11_16 = var7_27;
                                }
                            }
                            var6_18 = var5_34;
                            var7_27 = var10_31;
                            var8_2 = var11_16;
                            try {
                                var9_8.printStackTrace();
                                if (var11_16 == null) break block51;
                            }
                            catch (Throwable var5_38) lbl-1000: // 4 sources:
                            {
                                if (var8_2 != null) {
                                    try {
                                        var8_2.close();
                                    }
                                    catch (IOException var8_3) {
                                        var8_3.printStackTrace();
                                    }
                                }
                                if (var7_27 != null) {
                                    try {
                                        var7_27.close();
                                    }
                                    catch (IOException var7_28) {
                                        var7_28.printStackTrace();
                                    }
                                }
                                if (var6_18 == null) throw var5_34;
                                try {
                                    var6_18.close();
                                }
                                catch (IOException var6_19) {
                                    var6_19.printStackTrace();
                                    throw var5_34;
                                }
                                throw var5_34;
                            }
                            try {
                                var11_16.close();
                            }
                            catch (IOException var6_22) {
                                var6_22.printStackTrace();
                            }
                        }
                        if (var10_31 != null) {
                            try {
                                var10_31.close();
                            }
                            catch (IOException var6_23) {
                                var6_23.printStackTrace();
                            }
                        }
                        var1_40 = var3;
                        if (var5_34 == null) return var1_40;
                        try {
                            var5_34.close();
                            return 0;
                        }
                        catch (IOException var5_37) {
                            ** GOTO lbl-1000
                        }
                        catch (IOException var9_13) {
                            break block52;
                        }
                        catch (IOException var9_14) {
                            var10_31 = var6_18;
                            break block52;
                        }
                        catch (IOException var9_15) {
                            var10_31 = var6_18;
                            var11_16 = var7_27;
                        }
                    }
                    var6_18 = var5_34;
                    var7_27 = var10_31;
                    var8_2 = var11_16;
                    var9_6.printStackTrace();
                    ** if (var11_16 == null) goto lbl-1000
lbl-1000: // 1 sources:
                    {
                        var11_16.close();
                    }
lbl-1000: // 2 sources:
                    {
                        break block53;
                    }
                }
                catch (IOException var6_20) {
                    var6_20.printStackTrace();
                }
            }
            if (var10_31 != null) {
                try {
                    var10_31.close();
                }
                catch (IOException var6_21) {
                    var6_21.printStackTrace();
                }
            }
            var1_40 = var3;
            if (var5_34 == null) return var1_40;
            try {
                var5_34.close();
                return 0;
            }
            catch (IOException var5_35) lbl-1000: // 2 sources:
            {
                var5_36.printStackTrace();
                return 0;
            }
        }
    }

    public static long getLocalVersion() {
        Log.v((String)"TemplateRequest", (String)" getLocalVersion ");
        return TemplateRequest.getDataVersion();
    }

    private JSONRequest getRequest(Context object) {
        String string2;
        Log.v((String)"TemplateRequest", (String)(" request uri is " + TEMPLATE_URL));
        JSONRequest jSONRequest = new JSONRequest((Context)object, TEMPLATE_URL);
        long l = TemplateRequest.getLocalVersion();
        Log.v((String)"TemplateRequest", (String)(" local version : " + l));
        jSONRequest.addParam("version", String.valueOf((long)l));
        jSONRequest.addParam("deviceId", MiPubUtils.getDeviceId((Context)object));
        Account account = XiaomiAccount.getAccount((Context)object);
        object = string2 = "";
        if (!Build.IS_STABLE_VERSION) {
            object = string2;
            if (account != null) {
                object = string2;
                if (!TextUtils.isEmpty((CharSequence)account.name)) {
                    object = account.name;
                }
            }
        }
        jSONRequest.addParam("userId", (String)object);
        return jSONRequest;
    }

    private static boolean isRequesting() {
        synchronized (TemplateRequest.class) {
            boolean bl = sIsRequesting;
            return bl;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void parseMsgTemplateInfo(Context context, JSONObject jSONObject) throws Exception {
        boolean bl;
        String string2 = jSONObject.getString("type");
        boolean bl2 = jSONObject.getBoolean("wifiOnly");
        if (RESULT_NO_UPDATE.equals((Object)string2)) {
            Log.d((String)"TemplateRequest", (String)" no update needed! ");
            return;
        }
        if (RESULT_DIFF.equals((Object)string2)) {
            bl = true;
        } else {
            if (!RESULT_ALL.equals((Object)string2)) {
                Log.e((String)"TemplateRequest", (String)" unknown type!");
                return;
            }
            bl = false;
        }
        String string3 = jSONObject.getString("uri");
        long l = jSONObject.getLong("new_version");
        Log.v((String)"TemplateRequest", (String)(" uri and isIncremental " + string3 + " " + string2));
        jSONObject = new File(EXTERNAL_STORAGE_DIRECTORY, ".msgTemplate.tmp");
        int n = 0;
        boolean bl3 = false;
        try {
            string2 = new HashMap();
            string2.put("x-xiaomi-meta-template-old-md5", "");
            string2.put("x-xiaomi-meta-template-md5", "");
            while (n < 3 && !bl3 && Network.allowUpdate(bl2, true)) {
                int n2;
                Log.v((String)"TemplateRequest", (String)(" begin to download file: " + string3));
                boolean bl4 = FileUtil.downLoadFileWithHeader(context, string3, jSONObject.getAbsolutePath(), string2);
                Log.v((String)"TemplateRequest", (String)(" downLoadFileWithHeader: " + (String)string2.get("x-xiaomi-meta-template-md5")));
                n = n2 = n + 1;
                bl3 = bl4;
                if (!bl4) continue;
                this.copyFileToTarget((File)jSONObject);
                Log.v((String)"TemplateRequest", (String)" download files success!");
                TemplateUpdate.UpdatePatch updatePatch = new TemplateUpdate.UpdatePatch();
                updatePatch.mVersion = l;
                updatePatch.mIsIncremental = bl;
                updatePatch.mMd5 = (String)string2.get("x-xiaomi-meta-template-md5");
                updatePatch.mOldMd5 = (String)string2.get("x-xiaomi-meta-template-old-md5");
                MiStatSdkHelper.recourdUpdateEvent("mms");
                TemplateUpdate.onTemplateDownloaded(updatePatch);
                n = n2;
                bl3 = bl4;
            }
            if (bl3) {
                return;
            }
            Log.e((String)"TemplateRequest", (String)"failed to download file!");
            throw new IOException("failed to download file!");
        }
        finally {
            FileUtil.deleteFile((File)jSONObject);
        }
    }

    private static void setRequesting(boolean bl) {
        synchronized (TemplateRequest.class) {
            sIsRequesting = bl;
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void tryUpdateTemplate() {
        String string2 = PreferenceManager.getDefaultSharedPreferences((Context)MmsApp.getApp()).getString("update_content_preference", "");
        if (TextUtils.isEmpty((CharSequence)string2)) {
            Log.w((String)"TemplateRequest", (String)" No update content");
            return;
        }
        boolean bl = true;
        boolean bl2 = false;
        boolean bl3 = bl;
        try {
            boolean bl4;
            string2 = new JSONObject(string2);
            bl3 = bl;
            bl3 = bl = string2.optBoolean("wifiOnly", true);
            bl3 = bl4 = string2.optBoolean("forced", false);
            bl2 = bl;
            bl = bl3;
        }
        catch (Exception var4_1) {
            var4_1.printStackTrace();
            bl = bl2;
            bl2 = bl3;
        }
        if (!Network.allowUpdate(bl2, bl)) {
            Log.v((String)"TemplateRequest", (String)" Not allowing");
            return;
        }
        if (TemplateRequest.isRequesting()) return;
        {
            TemplateRequest.setRequesting(true);
            ThreadPool.execute(new Runnable(){

                /*
                 * Enabled force condition propagation
                 * Lifted jumps to return sites
                 */
                @Override
                public void run() {
                    TemplateRequest templateRequest = new TemplateRequest();
                    try {
                        String string2 = templateRequest.requestUpdate();
                        Log.v((String)"TemplateRequest", (String)("result is " + string2));
                        string2 = new JSONObject(string2);
                        Log.d((String)"TemplateRequest", (String)("pulled data in json is: " + string2));
                        templateRequest.parseMsgTemplateInfo((Context)MmsApp.getApp(), (JSONObject)string2);
                        do {
                            return;
                            break;
                        } while (true);
                    }
                    catch (Exception var1_2) {
                        var1_2.printStackTrace();
                        return;
                    }
                    finally {
                        TemplateRequest.setRequesting(false);
                    }
                }
            });
            return;
        }
    }

    public String requestUpdate() {
        JSONRequest jSONRequest = this.getRequest((Context)MmsApp.getApp());
        if (jSONRequest == null) {
            return null;
        }
        int n = jSONRequest.getStatus();
        Log.d((String)"TemplateRequest", (String)("request status:" + n));
        switch (n) {
            default: {
                throw new IllegalStateException();
            }
            case 0: {
                return jSONRequest.requestData();
            }
            case 3: {
                throw new IllegalStateException();
            }
            case 4: {
                throw new IllegalStateException();
            }
            case 1: {
                throw new IllegalStateException();
            }
            case 2: {
                throw new IllegalStateException();
            }
            case 6: 
        }
        throw new IllegalStateException();
    }

}

