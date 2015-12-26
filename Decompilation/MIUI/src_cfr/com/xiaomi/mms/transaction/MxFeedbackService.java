/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.accounts.Account
 *  android.accounts.AccountManager
 *  android.accounts.AccountManagerCallback
 *  android.accounts.AccountManagerFuture
 *  android.accounts.AuthenticatorException
 *  android.accounts.OperationCanceledException
 *  android.app.Activity
 *  android.app.ActivityManager
 *  android.app.ActivityManager$RunningAppProcessInfo
 *  android.app.IntentService
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.miui.Shell
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.SystemProperties
 *  android.preference.PreferenceManager
 *  android.telephony.TelephonyManager
 *  android.text.TextUtils
 *  android.widget.Toast
 *  java.io.BufferedInputStream
 *  java.io.BufferedOutputStream
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.FileNotFoundException
 *  java.io.FileOutputStream
 *  java.io.OutputStream
 *  java.io.OutputStreamWriter
 *  java.io.Writer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.text.SimpleDateFormat
 *  java.util.Date
 *  java.util.Locale
 *  java.util.zip.ZipEntry
 *  java.util.zip.ZipOutputStream
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.xiaomi.mms.transaction;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.IntentService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.miui.Shell;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemProperties;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;
import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

public class MxFeedbackService
extends IntentService {
    private static final String BUG_REPORT_PATH = "/data/bugreport" + File.separator + "outbox";
    private static final String MX_BUGREPORT_PATH = "/data/data/com.android.mms" + File.separator + "bugreport";
    private static final String SUMMARY_FILE_PATH = MX_BUGREPORT_PATH + File.separator + "summary.txt";
    private Process mProcess;
    private Handler mUIHandler = new Handler();

    public MxFeedbackService() {
        super("MxFeedbackService");
    }

    private static void addAccount(Context context, AccountManagerCallback<Bundle> accountManagerCallback) {
        AccountManager.get((Context)context).addAccount("com.xiaomi", "micloud", null, null, (Activity)context, accountManagerCallback, null);
    }

    /*
     * Exception decompiling
     */
    private String dumpFeedback() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.UnsupportedOperationException
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.LoopIdentifier.considerAsDoLoopStart(LoopIdentifier.java:347)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.LoopIdentifier.identifyLoops1(LoopIdentifier.java:60)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:614)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    private String formatDateString(Context context, long l) {
        return new SimpleDateFormat("yyyy-MM-dd-HHmmss").format(new Date(l));
    }

    private String getMxFeedback(Context object) {
        object = MxActivateService.getMxActivateServiceFeedback((Context)this);
        if (TextUtils.isEmpty((CharSequence)object)) {
            return "";
        }
        return (String)object + "\n";
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private int getPidByPackageName(String string2) {
        List list = ((ActivityManager)this.getSystemService("activity")).getRunningAppProcesses();
        if (list == null) return -1;
        if (list.size() == 0) {
            return -1;
        }
        int n = 0;
        while (n < list.size()) {
            try {
                ActivityManager.RunningAppProcessInfo runningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)list.get(n);
                if (string2.equals((Object)runningAppProcessInfo.processName)) {
                    return runningAppProcessInfo.pid;
                }
            }
            catch (Exception var5_5) {
                MyLog.e("MxFeedbackService", "getPidByPackageName Exception", var5_5);
            }
            ++n;
        }
        return -1;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private String getSummaryString(String string2) {
        JSONObject jSONObject = new JSONObject();
        TelephonyManager telephonyManager = (TelephonyManager)this.getSystemService("phone");
        try {
            jSONObject.put("ro.product.board", (Object)Build.BOARD);
            jSONObject.put("ro.product.brand", (Object)Build.BRAND);
            jSONObject.put("ro.build.id", (Object)Build.DISPLAY);
            jSONObject.put("ro.build.type", (Object)Build.TYPE);
            jSONObject.put("ro.build.version.codename", (Object)Build.VERSION.CODENAME);
            jSONObject.put("ro.product.device", (Object)Build.DEVICE);
            jSONObject.put("ro.build.version.incremental", (Object)Build.VERSION.INCREMENTAL);
            jSONObject.put("ro.soc.maxfreq", (Object)SystemProperties.get((String)"ro.soc.maxfreq", (String)""));
            jSONObject.put("ro.product.model", (Object)Build.MODEL);
            jSONObject.put("network.name", (Object)telephonyManager.getNetworkOperatorName());
            jSONObject.put("network.type", telephonyManager.getNetworkType());
            jSONObject.put("phone.type", telephonyManager.getPhoneType());
            jSONObject.put("ro.product.name", (Object)Build.PRODUCT);
            jSONObject.put("ro.build.version.release", (Object)Build.VERSION.RELEASE);
            jSONObject.put("ro.serialno", (Object)SystemProperties.get((String)"ro.serialno", (String)""));
            jSONObject.put("timestamp", (Object)this.formatDateString((Context)this, System.currentTimeMillis()));
            jSONObject.put("ui.language", (Object)Locale.getDefault().toString());
            jSONObject.put("description", (Object)("[Cloud Messaging]:" + string2));
            jSONObject.put("type", (Object)"others");
        }
        catch (JSONException var1_2) {
            MyLog.e("MxFeedbackService", "getSummaryString JSONException", (Throwable)var1_2);
            return jSONObject.toString();
        }
        do {
            return jSONObject.toString();
            break;
        } while (true);
    }

    private static Account getXiaomiAccount(Context arraccount) {
        if ((arraccount = AccountManager.get((Context)arraccount).getAccountsByType("com.xiaomi")).length > 0) {
            return arraccount[0];
        }
        return null;
    }

    private String getXmsfFeedback(Context context) {
        return "";
    }

    private void initBugReportDirs() {
        File file = new File(MX_BUGREPORT_PATH);
        if (!file.exists()) {
            Shell.runShell((String)"mkdir -p %s", (Object[])new Object[]{file});
            Shell.runShell((String)"chmod 777 %s", (Object[])new Object[]{file});
        }
        if (!(file = new File(BUG_REPORT_PATH)).exists()) {
            Shell.runShell((String)"mkdir -p %s", (Object[])new Object[]{file});
            Shell.runShell((String)"chmod 755 %s", (Object[])new Object[]{file});
            Shell.runShell((String)"chown system.system %s", (Object[])new Object[]{file});
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean isFeedbackEnabled() {
        boolean bl = false;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences((Context)this);
        long l = sharedPreferences.getLong("pref_feedback_last_time", 0);
        if (l == 0 || System.currentTimeMillis() - l > 86400000) {
            sharedPreferences = sharedPreferences.edit();
            sharedPreferences.putInt("pref_feedback_times", 0);
            sharedPreferences.putLong("pref_feedback_last_time", System.currentTimeMillis());
            sharedPreferences.apply();
            return true;
        }
        int n = sharedPreferences.getInt("pref_feedback_times", 0);
        if (n > 1) return bl;
        sharedPreferences = sharedPreferences.edit();
        sharedPreferences.putInt("pref_feedback_times", n + 1);
        sharedPreferences.putLong("pref_feedback_last_time", System.currentTimeMillis());
        sharedPreferences.apply();
        return true;
    }

    private void removeFiles() {
        Shell.runShell((String)"rm %s/*", (Object[])new Object[]{MX_BUGREPORT_PATH});
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void saveContentToFile(String var1_1, String var2_5) {
        var9_15 = new File(var1_1);
        var1_1 = null;
        var7_16 = null;
        var8_17 = null;
        var6_18 = null;
        var5_19 = null;
        var3_20 = var1_1;
        var4_21 = var8_17;
        if (!var9_15.exists()) {
            var3_20 = var1_1;
            var4_21 = var8_17;
            var9_15.createNewFile();
        }
        var3_20 = var1_1;
        var4_21 = var8_17;
        var1_1 = new FileOutputStream(var9_15);
        var3_20 = new OutputStreamWriter((OutputStream)var1_1);
        var3_20.append((CharSequence)var2_5);
        var3_20.flush();
        ** if (var3_20 == null) goto lbl25
lbl-1000: // 1 sources:
        {
            var3_20.close();
        }
lbl25: // 2 sources:
        ** GOTO lbl31
        catch (IOException var2_6) {
            block27 : {
                block26 : {
                    var1_1 = var7_16;
                    ** GOTO lbl52
                    catch (IOException var2_10) {
                        MyLog.e("MxFeedbackService", "close writer IOException", var2_10);
                    }
lbl31: // 2 sources:
                    if (var1_1 == null) return;
                    try {
                        var1_1.close();
                        return;
                    }
                    catch (IOException var1_4) {
                        MyLog.e("MxFeedbackService", "close output IOException", var1_4);
                        return;
                    }
                    catch (Throwable var2_11) {
                        var3_20 = var1_1;
                        var4_21 = var6_18;
                        var1_1 = var2_11;
                        ** GOTO lbl-1000
                    }
                    catch (Throwable var2_12) {
                        var4_21 = var3_20;
                        var3_20 = var1_1;
                        var1_1 = var2_12;
                        ** GOTO lbl-1000
                    }
                    catch (IOException var2_13) {
                        break block26;
                    }
                    catch (IOException var2_14) {
                        var5_19 = var3_20;
                    }
                }
                var3_20 = var1_1;
                var4_21 = var5_19;
                try {
                    MyLog.e("MxFeedbackService", "saveContentToFile IOException", (Throwable)var2_5);
                    ** if (var5_19 == null) goto lbl-1000
                }
                catch (Throwable var1_3) lbl-1000: // 3 sources:
                {
                    if (var4_21 != null) {
                        try {
                            var4_21.close();
                        }
                        catch (IOException var2_7) {
                            MyLog.e("MxFeedbackService", "close writer IOException", var2_7);
                        }
                    }
                    if (var3_20 == null) throw var1_1;
                    try {
                        var3_20.close();
                    }
                    catch (IOException var2_8) {
                        MyLog.e("MxFeedbackService", "close output IOException", var2_8);
                        throw var1_1;
                    }
                    throw var1_1;
                }
lbl-1000: // 1 sources:
                {
                    var5_19.close();
                }
lbl-1000: // 2 sources:
                {
                    break block27;
                }
                catch (IOException var2_9) {
                    MyLog.e("MxFeedbackService", "close writer IOException", var2_9);
                }
            }
            if (var1_1 == null) return;
            try {
                var1_1.close();
                return;
            }
            catch (IOException var1_2) {
                MyLog.e("MxFeedbackService", "close output IOException", var1_2);
                return;
            }
        }
    }

    private void startFeedback(String string2) {
        String string3 = MX_BUGREPORT_PATH + File.separator;
        String string4 = this.getXmsfFeedback((Context)this);
        String string5 = this.getMxFeedback((Context)this);
        if (!TextUtils.isEmpty((CharSequence)(string4 = string4 + string5 + this.dumpFeedback()))) {
            this.saveContentToFile(string3 + "logcat_" + System.currentTimeMillis() + ".log", string4);
        }
        if (!TextUtils.isEmpty((CharSequence)(string2 = this.getSummaryString(string2)))) {
            this.saveContentToFile(SUMMARY_FILE_PATH, string2);
        }
        if (this.zipFilesAndSubmit()) {
            this.toastFeedback(2131362284);
        }
    }

    public static void startMxFeedback(final Activity activity, final String string2) {
        if (MxFeedbackService.getXiaomiAccount((Context)activity) == null) {
            MxFeedbackService.addAccount((Context)activity, new AccountManagerCallback<Bundle>(){

                /*
                 * Enabled force condition propagation
                 * Lifted jumps to return sites
                 */
                public void run(AccountManagerFuture<Bundle> bundle) {
                    if (bundle == null) return;
                    bundle = (Bundle)bundle.getResult();
                    if (bundle == null) return;
                    try {
                        if (TextUtils.isEmpty((CharSequence)bundle.getString("authAccount"))) return;
                        bundle = new Intent("com.xiaomi.mms.mx.ACTION_START_MX_FEEDBACK");
                        bundle.putExtra("extra_description", string2);
                        bundle.setPackage(activity.getPackageName());
                        activity.startService((Intent)bundle);
                        return;
                    }
                    catch (OperationCanceledException var1_2) {
                        MyLog.e("MxFeedbackService", "startMxFeedback OperationCanceledException", (Throwable)var1_2);
                        return;
                    }
                    catch (AuthenticatorException var1_3) {
                        MyLog.e("MxFeedbackService", "startMxFeedback AuthenticatorException", (Throwable)var1_3);
                        return;
                    }
                    catch (IOException var1_4) {
                        MyLog.e("MxFeedbackService", "startMxFeedback IOException", var1_4);
                        return;
                    }
                }
            });
            return;
        }
        Intent intent = new Intent("com.xiaomi.mms.mx.ACTION_START_MX_FEEDBACK");
        intent.putExtra("extra_description", string2);
        intent.setPackage(activity.getPackageName());
        activity.startService(intent);
    }

    private void submitZipToBugReport(String string2) {
        Intent intent = new Intent();
        intent.setClassName("com.miui.bugreport", "com.miui.bugreport.service.FeedbackBackgroundService");
        intent.putExtra("service_command", "submit_feedback");
        intent.putExtra("feedback_file", string2);
        this.startService(intent);
    }

    private void toastFeedback(final int n) {
        this.mUIHandler.post(new Runnable(){

            @Override
            public void run() {
                Toast.makeText((Context)MxFeedbackService.this, (int)n, (int)0).show();
            }
        });
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void zipFile(File var1_1, ZipOutputStream var2_2, String var3_3) throws FileNotFoundException, IOException {
        block7 : {
            var5_5 = new StringBuilder().append(var3_3);
            var3_3 = var3_3.trim().length() == 0 ? "" : File.separator;
            var5_6 = var5_5.append(var3_3).append(var1_1.getName()).toString();
            if (var1_1.exists() == false) return;
            if (var1_1.isFile() == false) return;
            var6_8 = new byte[524288];
            var3_3 = null;
            var1_1 = new BufferedInputStream((InputStream)new FileInputStream((File)var1_1), 524288);
            try {
                var2_2.putNextEntry(new ZipEntry(var5_6));
                while ((var4_9 = var1_1.read(var6_8)) != -1) {
                    var2_2.write(var6_8, 0, var4_9);
                }
                break block7;
            }
            catch (Throwable var3_4) {}
            ** GOTO lbl-1000
        }
        if (var1_1 != null) {
            var1_1.close();
        }
        var2_2.flush();
        var2_2.closeEntry();
        return;
        catch (Throwable var5_7) {
            var1_1 = var3_3;
            var3_3 = var5_7;
        }
lbl-1000: // 2 sources:
        {
            if (var1_1 != null) {
                var1_1.close();
            }
            var2_2.flush();
            var2_2.closeEntry();
            throw var3_3;
        }
    }

    private void zipFiles(Collection<File> object, File file) throws IOException {
        file = new ZipOutputStream((OutputStream)new BufferedOutputStream((OutputStream)new FileOutputStream(file), 524288));
        object = object.iterator();
        while (object.hasNext()) {
            File file2 = (File)object.next();
            if (file2 == null) continue;
            try {
                this.zipFile(file2, (ZipOutputStream)file, "");
            }
            catch (FileNotFoundException var3_4) {
                MyLog.e("MxFeedbackService", "zipFiles FileNotFoundException", (Throwable)var3_4);
            }
        }
        file.close();
    }

    /*
     * Exception decompiling
     */
    private boolean zipFilesAndSubmit() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 5[CATCHBLOCK]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onHandleIntent(Intent intent) {
        if (intent == null || !"com.xiaomi.mms.mx.ACTION_START_MX_FEEDBACK".equals((Object)intent.getAction())) {
            return;
        }
        if (!this.isFeedbackEnabled()) {
            this.toastFeedback(2131362285);
            return;
        }
        this.toastFeedback(2131362283);
        this.initBugReportDirs();
        this.startFeedback(intent.getStringExtra("extra_description"));
    }

}

