/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.IntentService
 *  android.content.ComponentName
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.os.Bundle
 *  android.preference.PreferenceManager
 *  android.provider.Settings
 *  android.provider.Settings$Secure
 *  android.provider.Settings$System
 *  android.text.TextUtils
 *  android.util.Log
 *  com.xiaomi.accountsdk.activate.ActivateManager
 *  com.xiaomi.accountsdk.activate.ActivateManager$ActivateManagerFuture
 *  com.xiaomi.accountsdk.activate.ActivateStatusReceiver
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.Random
 *  miui.cloud.CloudManager
 *  miui.push.ServiceClient
 *  miui.telephony.exception.IllegalDeviceException
 *  miui.util.DropBoxLog
 *  org.apache.http.message.BasicNameValuePair
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.xiaomi.mms.transaction;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.MSimUtils;
import com.xiaomi.accountsdk.activate.ActivateManager;
import com.xiaomi.accountsdk.activate.ActivateStatusReceiver;
import com.xiaomi.mms.transaction.MxMessageService;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.MxCapabilityText;
import com.xiaomi.mms.utils.PrefUtils;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import miui.cloud.CloudManager;
import miui.push.ServiceClient;
import miui.telephony.exception.IllegalDeviceException;
import miui.util.DropBoxLog;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MxActivateService
extends IntentService {
    private static final Object BUSY_FLAG_LOCK;
    private static final long[] INTS;
    private static final Object MX_STATUS_INFO_LOCK;
    private static int mIsEnabling;
    private static Map<String, MxStatusInfo> mMxStatusInfo;
    private static boolean[] sEnableAfterActivation;

    static {
        INTS = new long[]{2000, 4000, 8000, 15000, 30000, 60000};
        mIsEnabling = 0;
        sEnableAfterActivation = new boolean[2];
        BUSY_FLAG_LOCK = new Object();
        MX_STATUS_INFO_LOCK = new Object();
    }

    public MxActivateService() {
        super("MxActivateService");
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static void checkMxLoginInfo(MxStatusInfo mxStatusInfo) {
        if (mxStatusInfo == null) {
            return;
        }
        if (TextUtils.isEmpty((CharSequence)mxStatusInfo.serviceToken)) {
            Log.d((String)"MxActivateService", (String)"saved service token is empty");
        }
        if (TextUtils.isEmpty((CharSequence)mxStatusInfo.security)) {
            Log.d((String)"MxActivateService", (String)"saved security is empty");
        }
        if (!TextUtils.isEmpty((CharSequence)mxStatusInfo.simId)) return;
        Log.d((String)"MxActivateService", (String)"sim if for saved info is empty");
    }

    public static void clearLastFailureCode(Context context) {
        MxActivateService.setLastFailureCode(context, -1);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void closeChannel(int n) {
        String string2;
        String string3 = string2 = PushSession.getInstance((Context)this).getMyMid(n);
        if (string2 == null) {
            string3 = string2;
            if (MSimUtils.getSimId((Context)this, n) != null) {
                string3 = ActivateManager.get((Context)this).getActivateInfo(n);
                try {
                    string3 = ((Bundle)string3.getResult()).getString("activate_sim_user_id");
                }
                catch (Exception var2_4) {
                    Log.e((String)"MxActivateService", (String)"error when get sim user", (Throwable)var2_4);
                    string3 = string2;
                }
            }
        }
        if (string3 == null) {
            Log.e((String)"MxActivateService", (String)"failed to get sim user, close channel aborted");
            return;
        }
        PushSession.getInstance((Context)this).setStatus((Context)this, n, PushSession.Status.DISCONNECTED);
        ServiceClient.getInstance((Context)this).closeChannel("3", string3);
    }

    public static void closeChannel(Context context, int n) {
        Intent intent = new Intent("com.xiaomi.mms.action.CLOSE_CHANNEL");
        intent.setPackage(context.getPackageName());
        intent.putExtra("sim_index", n);
        context.startService(intent);
    }

    private void disableOldMxEnabled() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences((Context)this);
        if (sharedPreferences.getBoolean("pref_key_enable_mx", false)) {
            sharedPreferences = sharedPreferences.edit();
            sharedPreferences.putBoolean("pref_key_enable_mx", false);
            sharedPreferences.commit();
        }
    }

    public static void enableAll(Context context, boolean bl) {
        if (MSimUtils.isSimInserted(0)) {
            MxActivateService.enableMx(context, 0, true, bl);
        }
        if (MSimUtils.isSimInserted(1)) {
            MxActivateService.enableMx(context, 1, true, bl);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void enableMx(Context context, int n, boolean bl, boolean bl2) {
        boolean bl3 = false;
        boolean bl4 = false;
        if (n == 0) {
            bl3 = bl;
            bl4 = MxActivateService.isMxEnabled(context, 1);
        } else if (n == 1) {
            bl3 = MxActivateService.isMxEnabled(context, 0);
            bl4 = bl;
        }
        if (!bl3 && !bl4) {
            MxMessageService.resetMxCount(context);
        }
        MxActivateService.setIsEnabling(n, bl);
        if (bl2) {
            MxActivateService.setMxEnabled(context, n, bl);
        }
        String string2 = bl ? "com.xiaomi.mms.action.ENABLE_MX" : "com.xiaomi.mms.action.DISABLE_MX";
        string2 = new Intent(string2);
        string2.putExtra("extra_manually", bl2);
        string2.putExtra("sim_index", n);
        string2.setPackage(context.getPackageName());
        context.startService((Intent)string2);
    }

    public static String generateRandomString(int n) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < n; ++i) {
            stringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".length())));
        }
        return stringBuffer.toString();
    }

    /*
     * Enabled aggressive block sorting
     */
    private static int getActivateStatus(int n) {
        Bundle bundle;
        if (n < 0 || (bundle = ActivateStatusReceiver.getActivateInfo((int)n)) == null) {
            return -1;
        }
        return bundle.getInt("activate_status", -1);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean getEnableAfterActivation(int n) {
        Object object = BUSY_FLAG_LOCK;
        synchronized (object) {
            return sEnableAfterActivation[n];
        }
    }

    public static int getLastFailureCode(Context context) {
        return PreferenceManager.getDefaultSharedPreferences((Context)context).getInt("pref_key_last_failure_code", -1);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String getMxActivateServiceFeedback(Context object) {
        object = null;
        Object object2 = MX_STATUS_INFO_LOCK;
        synchronized (object2) {
            if (mMxStatusInfo != null) {
                object = mMxStatusInfo;
            }
        }
        DropBoxLog dropBoxLog = new DropBoxLog();
        if (object == null) {
            dropBoxLog.v("MxActivateService", "no activate info is valid");
            return dropBoxLog.getMessage();
        }
        int n = mMxStatusInfo.size();
        dropBoxLog.v("MxActivateService", "activate info size is " + n);
        Iterator<MxStatusInfo> iterator = mMxStatusInfo.values().iterator();
        int n2 = 0;
        while (n2 < n) {
            MxStatusInfo mxStatusInfo = iterator.next();
            if (mxStatusInfo == null) {
                dropBoxLog.v("MxActivateService", "sim " + n2 + " activate info is null");
            } else {
                object = object2 = mxStatusInfo.mid;
                if (TextUtils.isEmpty((CharSequence)object2)) {
                    object = "null";
                }
                dropBoxLog.v("MxActivateService", "sim " + n2 + " mid = " + (String)object);
                object = object2 = mxStatusInfo.simId;
                if (TextUtils.isEmpty((CharSequence)object2)) {
                    object = "null";
                }
                dropBoxLog.v("MxActivateService", "sim " + n2 + " simId = " + (String)object);
                object = object2 = mxStatusInfo.serviceToken;
                if (TextUtils.isEmpty((CharSequence)object2)) {
                    object = "null";
                }
                dropBoxLog.v("MxActivateService", "sim " + n2 + " serviceToken = " + (String)object);
                object = object2 = mxStatusInfo.security;
                if (TextUtils.isEmpty((CharSequence)object2)) {
                    object = "null";
                }
                dropBoxLog.v("MxActivateService", "sim " + n2 + " security = " + (String)object);
                object = mxStatusInfo.enabled ? "true" : "false";
                dropBoxLog.v("MxActivateService", "sim " + n2 + " enabled = " + (String)object);
            }
            ++n2;
        }
        return dropBoxLog.getMessage();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int getSimActivateStatus(Context object) {
        int n;
        int n2 = -1;
        int n3 = MSimUtils.getMultiSimCount();
        object = new int[n3];
        for (n = 0; n < n3; ++n) {
            object[n] = -1;
        }
        for (n = 0; n < n3; ++n) {
            Bundle bundle = ActivateStatusReceiver.getActivateInfo((int)n);
            if (!bundle.getBoolean("sim_inserted")) continue;
            object[n] = bundle.getInt("activate_status");
        }
        if (n3 > 1) {
            if (object[0] == 3) return 3;
            if (object[1] == 3) {
                return 3;
            }
            if (object[0] == true) {
                if (object[1] == true) return 1;
            }
            if (object[0] == -1) {
                if (object[1] == true) return 1;
            }
            n = n2;
            if (object[0] != true) return n;
            n = n2;
            if (object[1] != -1) return n;
            return 1;
        }
        n = n2;
        if (n3 <= 0) return n;
        if (object[0] == 3) {
            return 3;
        }
        n = n2;
        if (object[0] != true) return n;
        return 1;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void invalidMxToken(Context context, int n) {
        Object object = MSimUtils.getSimId(context, n);
        if (TextUtils.isEmpty((CharSequence)object)) {
            return;
        }
        Object object2 = MX_STATUS_INFO_LOCK;
        synchronized (object2) {
            object = mMxStatusInfo.get(object);
            if (object != null) {
                object.security = null;
                object.serviceToken = null;
                MxActivateService.persistMxStatusInfo(context, mMxStatusInfo);
            }
            return;
        }
    }

    public static boolean isActivateStatusInitialized() {
        boolean bl = false;
        if (MxActivateService.getActivateStatus(0) != -1 || MxActivateService.getActivateStatus(1) != -1) {
            bl = true;
        }
        return bl;
    }

    public static boolean isActivating() {
        boolean bl = false;
        if (MxActivateService.getActivateStatus(0) == 2 || MxActivateService.getActivateStatus(1) == 2) {
            bl = true;
        }
        return bl;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean isBusy(int n) {
        boolean bl = false;
        if (n < 0) {
            return false;
        }
        Bundle bundle = ActivateStatusReceiver.getActivateInfo((int)n);
        Object object = BUSY_FLAG_LOCK;
        synchronized (object) {
            if ((mIsEnabling & 1 << n) > 0) return true;
            if (bundle.getInt("activate_status") != 2) return bl;
            return true;
        }
    }

    public static boolean isMxEnabled(Context context) {
        boolean bl = false;
        if (MxActivateService.isMxEnabled(context, 0, false) || MxActivateService.isMxEnabled(context, 1, false)) {
            bl = true;
        }
        return bl;
    }

    public static boolean isMxEnabled(Context context, int n) {
        return MxActivateService.isMxEnabled(context, n, false);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean isMxEnabled(Context context, int n, boolean bl) {
        if (n < 0) return bl;
        Object object = ActivateStatusReceiver.getActivateInfo((int)n);
        if (object == null) return bl;
        boolean bl2 = object.getBoolean("sim_inserted");
        int n2 = object.getInt("activate_status", -1);
        if (!bl2) return bl;
        if (n2 != 3) {
            return bl;
        }
        object = MX_STATUS_INFO_LOCK;
        synchronized (object) {
            if (mMxStatusInfo == null) {
                mMxStatusInfo = MxActivateService.readMxLoginInfo(context);
            }
            Object object2 = MSimUtils.getSimId(context, n);
            if ((object2 = mMxStatusInfo.get(object2)) != null) {
                return object2.enabled;
            }
            if (!mMxStatusInfo.isEmpty()) return bl;
            bl = PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean("pref_key_enable_mx", bl);
            Log.d((String)"MxActivateService", (String)("cached old mx toggle preference, enabled: " + bl));
            return bl;
        }
    }

    private void onDisable(int n) {
        MxActivateService.setMxEnabled((Context)this, n, false);
        MxActivateService.setIsEnabling(n, false);
        Intent intent = new Intent("com.xiaomi.mms.action.DISABLE_RESULT");
        intent.setPackage(this.getPackageName());
        intent.putExtra("sim_index", n);
        this.sendBroadcast(intent);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void onEnableFailure(int n, boolean bl) {
        boolean bl2 = !bl;
        MxActivateService.setMxEnabled((Context)this, n, bl2);
        if (bl) {
            this.disableOldMxEnabled();
        }
        MxActivateService.setIsEnabling(n, false);
        Intent intent = new Intent("com.xiaomi.mms.action.ENBALE_RESULT");
        intent.setPackage(this.getPackageName());
        intent.putExtra("success", false);
        intent.putExtra("sim_index", n);
        this.sendBroadcast(intent);
    }

    private void onEnableSuccess(int n, String string2, String string3, String string4, String string5) {
        this.openChannel(n, string2, string3, string4, string5);
        MxActivateService.setMxEnabled((Context)this, n, true);
        MxActivateService.setIsEnabling(n, false);
        string2 = new Intent("com.xiaomi.mms.action.ENBALE_RESULT");
        string2.putExtra("success", true);
        string2.putExtra("sim_index", n);
        this.sendBroadcast((Intent)string2);
    }

    private void onSevereFailure(int n, int n2) {
        MxActivateService.setMxEnabled((Context)this, n, false);
        this.disableOldMxEnabled();
        MxActivateService.setIsEnabling(n, false);
        Intent intent = new Intent("com.xiaomi.mms.action.ENBALE_RESULT");
        intent.putExtra("success", false);
        intent.setPackage(this.getPackageName());
        this.sendBroadcast(intent);
        MxActivateService.setLastFailureCode((Context)this, n2);
    }

    private void onStartActivate(int n) {
        Intent intent = new Intent("com.xiaomi.mms.action.STATUS_START");
        intent.setPackage(this.getPackageName());
        intent.putExtra("sim_index", n);
        this.sendBroadcast(intent);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void openChannel(int n, String string2, String string3, String string4, String string5) {
        String string6;
        PushSession pushSession = PushSession.getInstance((Context)this);
        String string7 = string6 = Settings.System.getString((ContentResolver)this.getContentResolver(), (String)"pref_mx_res");
        if (TextUtils.isEmpty((CharSequence)string6)) {
            string7 = MxActivateService.generateRandomString(16);
            Settings.System.putString((ContentResolver)this.getContentResolver(), (String)"pref_mx_res", (String)string7);
        }
        pushSession.setMyFullMidAndPhone(n, string2, string7, string5);
        string2 = ServiceClient.getInstance((Context)this);
        string7 = new ArrayList();
        string5 = MessageUtils.getEncryptedPhone(string4, string5);
        if (!TextUtils.isEmpty((CharSequence)string5)) {
            string7.add((Object)new BasicNameValuePair("pid", string5));
        } else {
            Log.e((String)"MxActivateService", (String)"pid is null");
        }
        string7.add((Object)new BasicNameValuePair("v", "1"));
        string5 = new ArrayList();
        string5.add((Object)new BasicNameValuePair("cap", MxCapabilityText.combine("sms", "mms", "mx2audio", "mxV2mms2", "mxV3")));
        try {
            string2.openChannel(pushSession.getMyFullMid(n), "3", string3, "XIAOMI-PASS", string4, false, (List)((Object)string5), (List)((Object)string7));
            return;
        }
        catch (Exception var2_3) {
            MyLog.e("MxActivateService", "openChanel faild", var2_3);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void persistMxStatusInfo(Context context, Map<String, MxStatusInfo> iterator) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        for (MxStatusInfo mxStatusInfo : iterator.values()) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("mid", (Object)mxStatusInfo.mid);
                jSONObject2.put("sim_id", (Object)mxStatusInfo.simId);
                jSONObject2.put("st", (Object)mxStatusInfo.serviceToken);
                jSONObject2.put("sec", (Object)mxStatusInfo.security);
                jSONObject2.put("enabled", mxStatusInfo.enabled);
            }
            catch (JSONException var5_7) {
                Log.e((String)"MxActivateService", (String)"error when persist login infos", (Throwable)var5_7);
            }
            jSONArray.put((Object)jSONObject2);
        }
        try {
            jSONObject.put("logins", (Object)jSONArray);
        }
        catch (JSONException var1_2) {
            Log.e((String)"MxActivateService", (String)"error when persist mx account infos", (Throwable)var1_2);
        }
        PrefUtils.saveString(context, "pref_mx_account_info", jSONObject.toString());
    }

    private static Map<String, MxStatusInfo> readMxLoginInfo(Context context) {
        String string2 = PrefUtils.getString(context, "pref_mx_account_info");
        context = new HashMap();
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            int n;
            try {
                string2 = new JSONObject(string2).getJSONArray("logins");
                n = 0;
            }
            catch (JSONException var3_2) {
                Log.e((String)"MxActivateService", (String)"error when parse mx account info", (Throwable)var3_2);
            }
            do {
                if (n < string2.length()) {
                    Object object = string2.getJSONObject(n);
                    String string3 = object.optString("mid", null);
                    String string4 = object.getString("sim_id");
                    String string5 = object.optString("st", null);
                    String string6 = object.optString("sec", null);
                    boolean bl = object.getBoolean("enabled");
                    object = new MxStatusInfo();
                    object.mid = string3;
                    object.serviceToken = string5;
                    object.security = string6;
                    object.enabled = bl;
                    object.simId = string4;
                    context.put((Object)string4, object);
                    ++n;
                    continue;
                }
                break;
            } while (true);
        }
        return context;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void setEnableAfterActivation(int n, boolean bl) {
        Object object = BUSY_FLAG_LOCK;
        synchronized (object) {
            MxActivateService.sEnableAfterActivation[n] = bl;
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void setIsEnabling(int n, boolean bl) {
        Object object = BUSY_FLAG_LOCK;
        synchronized (object) {
            mIsEnabling = bl ? (mIsEnabling |= 1 << n) : (mIsEnabling &= ~ (1 << n));
            return;
        }
    }

    private static void setLastFailureCode(Context context, int n) {
        context = PreferenceManager.getDefaultSharedPreferences((Context)context).edit();
        context.putInt("pref_key_last_failure_code", n);
        context.commit();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void setMxEnabled(Context context, int n, boolean bl) {
        block8 : {
            boolean bl2 = false;
            Object object = MSimUtils.getSimId(context, n);
            if (!TextUtils.isEmpty((CharSequence)object)) {
                Object object2 = MX_STATUS_INFO_LOCK;
                synchronized (object2) {
                    if (mMxStatusInfo == null) {
                        return;
                    }
                    if ((object = mMxStatusInfo.get(object)) != null) {
                        object.enabled = bl;
                        MxActivateService.persistMxStatusInfo(context, mMxStatusInfo);
                    }
                }
            }
            if (!MxActivateService.isMxEnabled(context, 0)) {
                bl = bl2;
                if (!MxActivateService.isMxEnabled(context, 1)) break block8;
            }
            bl = true;
        }
        MxActivateService.writeStatusToSettings(context, bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static void writeStatusToSettings(Context context, boolean bl) {
        context = context.getContentResolver();
        int n = bl ? 1 : 0;
        Settings.Secure.putInt((ContentResolver)context, (String)"cloud_messaging_on", (int)n);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    protected void onHandleIntent(Intent var1_1) {
        var10_5 = null;
        var2_6 = 1;
        if (var1_1 == null) {
            return;
        }
        var8_7 = var1_1.getAction();
        if ("com.xiaomi.mms.action.ENABLE_MX".equals(var8_7)) {
            var3_8 = var1_1.getIntExtra("sim_index", -1);
            if (var3_8 < 0) {
                throw new IllegalStateException("no sim index");
            }
            var11_10 = ActivateStatusReceiver.getActivateInfo((int)var3_8);
            var5_12 = var11_10.getBoolean("sim_inserted");
            var4_13 = var11_10.getInt("activate_status", -1);
            var7_15 = var1_1.getBooleanExtra("extra_manually", false);
            if (!var5_12) {
                Log.w((String)"MxActivateService", (String)"SIM unready, bail.");
                MxActivateService.setIsEnabling(var3_8, false);
                if (var7_15 == false) return;
                MxActivateService.setEnableAfterActivation(var3_8, true);
                return;
            }
            if (var4_13 != 3) {
                Log.w((String)"MxActivateService", (String)"Unactivated, bail.");
                MxActivateService.setIsEnabling(var3_8, false);
                if (var7_15 == false) return;
                MxActivateService.setEnableAfterActivation(var3_8, true);
                return;
            }
            var8_7 = MxActivateService.MX_STATUS_INFO_LOCK;
            // MONITORENTER : var8_7
            if (MxActivateService.mMxStatusInfo == null) {
                MxActivateService.mMxStatusInfo = MxActivateService.readMxLoginInfo((Context)this);
            }
            var1_1 = (var1_1 = MSimUtils.getSimId(this.getApplicationContext(), var3_8)) != null ? MxActivateService.mMxStatusInfo.get(var1_1) : null;
            if (var1_1 != null) {
                var1_1 = new MxStatusInfo((MxStatusInfo)var1_1);
                Log.d((String)"MxActivateService", (String)"cached Mx status info found");
                MxActivateService.checkMxLoginInfo((MxStatusInfo)var1_1);
            } else {
                var1_1 = null;
            }
            // MONITOREXIT : var8_7
            var5_12 = var1_1 != null && var1_1.enabled != false;
            var6_16 = var5_12;
            if (!var5_12) {
                var6_16 = var5_12;
                if (MxActivateService.mMxStatusInfo.isEmpty()) {
                    Log.d((String)"MxActivateService", (String)"load mx status from old preference");
                    var6_16 = PreferenceManager.getDefaultSharedPreferences((Context)this).getBoolean("pref_key_enable_mx", false);
                }
            }
            if (var1_1 == null && !var7_15 && !var6_16) {
                this.onEnableFailure(var3_8, var7_15);
            } else if (var7_15 || var6_16) {
                if (var7_15) {
                    Log.d((String)"MxActivateService", (String)"mi message is enabled manually");
                    MxActivateService.setMxEnabled((Context)this, var3_8, true);
                }
                MxActivateService.writeStatusToSettings((Context)this, true);
                this.onStartActivate(var3_8);
                MxActivateService.clearLastFailureCode((Context)this);
                if (var1_1 != null) {
                    var9_17 = var1_1.mid;
                    var8_7 = var1_1.serviceToken;
                    var1_1 = var1_1.security;
                } else {
                    var1_1 = null;
                    var8_7 = null;
                    var9_17 = null;
                }
                var12_22 = var11_10.getString("activate_sim_user_id");
                var11_10 = var11_10.getString("activate_phone");
                if (!TextUtils.equals((CharSequence)var12_22, (CharSequence)var9_17)) {
                    Log.i((String)"MxActivateService", (String)("sim is changed in slot " + var3_8));
                    var8_7 = null;
                    var1_1 = var10_5;
                } else {
                    var9_17 = var1_1;
                    var1_1 = var8_7;
                    var8_7 = var9_17;
                }
                if (!(TextUtils.isEmpty((CharSequence)var12_22) || TextUtils.isEmpty((CharSequence)var11_10) || TextUtils.isEmpty((CharSequence)var1_1) || TextUtils.isEmpty((CharSequence)var8_7))) {
                    this.onEnableSuccess(var3_8, var12_22, (String)var1_1, (String)var8_7, (String)var11_10);
                    return;
                }
                if (!TextUtils.isEmpty((CharSequence)var12_22) && !TextUtils.isEmpty((CharSequence)var11_10)) {
                    var1_1 = new Intent((Context)this, (Class)MxActivateService.class);
                    var1_1.setAction("com.xiaomi.mms.action.QUERY_TOKEN");
                    var1_1.putExtra("extra_manually", var7_15);
                    var1_1.putExtra("mid", var12_22);
                    var1_1.putExtra("phone", (String)var11_10);
                    var1_1.putExtra("sim_index", var3_8);
                    this.startService((Intent)var1_1);
                    var2_6 = 1;
                } else {
                    Log.e((String)"MxActivateService", (String)"failed to get mid or phone, activate mx failed");
                    this.onSevereFailure(var3_8, 2);
                    var2_6 = 1;
                }
            } else {
                var4_13 = MxActivateService.getLastFailureCode((Context)this);
                if (var4_13 > 0) {
                    Log.d((String)"MxActivateService", (String)("disable mx, failureCode: " + var4_13));
                    this.onSevereFailure(var3_8, var4_13);
                } else {
                    this.onEnableFailure(var3_8, true);
                }
            }
            if (var2_6 == 0) return;
            Log.d((String)"MxActivateService", (String)"need to close channel, starting to close channel");
            this.closeChannel(var3_8);
            return;
        }
        if ("com.xiaomi.mms.action.QUERY_TOKEN".equals(var8_7)) {
            var13_24 = var1_1.getStringExtra("mid");
            var14_25 = var1_1.getStringExtra("phone");
            var4_14 = var1_1.getIntExtra("sim_index", -1);
            if (var4_14 < 0) {
                Log.d((String)"MxActivateService", (String)("invalid simInidex: " + var4_14));
                throw new IllegalStateException("sim index not contained");
            }
            try {
                var11_11 = MSimUtils.blockingGetSimId((Context)this, var4_14);
            }
            catch (IllegalDeviceException var1_2) {
                var11_11 = null;
            }
            if (var11_11 == null) {
                Log.w((String)"MxActivateService", (String)("failed to get sim id for sim " + var4_14));
                this.onEnableFailure(var4_14, false);
                return;
            }
            var15_26 = ActivateManager.get((Context)this);
            var8_7 = null;
            var1_1 = null;
            var3_9 = 0;
        } else {
            if ("com.xiaomi.mms.action.CLOSE_CHANNEL".equals(var8_7)) {
                var2_6 = var1_1.getIntExtra("sim_index", -1);
                if (var2_6 < 0) {
                    throw new IllegalStateException("no sim index");
                }
                MxActivateService.clearLastFailureCode((Context)this);
                this.closeChannel(var2_6);
                return;
            }
            if ("com.xiaomi.mms.action.DISABLE_MX".equals(var8_7)) {
                var2_6 = var1_1.getIntExtra("sim_index", -1);
                if (var2_6 < 0) {
                    throw new IllegalStateException("no sim index");
                }
                MxActivateService.clearLastFailureCode((Context)this);
                this.closeChannel(var2_6);
                this.onDisable(var2_6);
                return;
            }
            if ("com.xiaomi.mms.action.ENBALE_MX_DIRECTLY".equals(var8_7) == false) return;
            var8_7 = var1_1.getStringExtra("phone");
            var9_21 = var1_1.getStringExtra("mid");
            var2_6 = var1_1.getIntExtra("sim_index", -1);
            if (var2_6 == -1) {
                Log.w((String)"MxActivateService", (String)"no sim index, do not activate now");
                return;
            }
            if (TextUtils.isEmpty((CharSequence)var8_7) || TextUtils.isEmpty((CharSequence)var9_21)) {
                Log.w((String)"MxActivateService", (String)"params missing, activation ignored");
                return;
            }
            if (MxActivateService.isMxEnabled((Context)this, var2_6) && PushSession.getInstance((Context)this).isConnected(var2_6)) {
                Log.i((String)"MxActivateService", (String)"mx already activated");
                MxActivateService.setIsEnabling(var2_6, false);
                return;
            }
            this.onStartActivate(var2_6);
            var1_1 = new Intent((Context)this, (Class)MxActivateService.class);
            var1_1.setAction("com.xiaomi.mms.action.QUERY_TOKEN");
            var1_1.putExtra("mid", var9_21);
            var1_1.putExtra("phone", (String)var8_7);
            var1_1.putExtra("sim_index", var2_6);
            this.startService((Intent)var1_1);
            return;
        }
        while (!Thread.currentThread().isInterrupted()) {
            if (var3_9 >= MxActivateService.INTS.length) ** GOTO lbl-1000
            Log.d((String)"MxActivateService", (String)("try to get sim auth token, round:" + var3_9));
            var12_23 = var15_26.getSimAuthToken(var4_14, "mixin");
            var9_18 = var1_1;
            var10_5 = var1_1;
            try {
                var12_23 = (Bundle)var12_23.getResult();
                var9_18 = var1_1;
                var10_5 = var1_1;
                var9_18 = var1_1 = var12_23.getString("user_token");
                var10_5 = var1_1;
                var8_7 = var12_23 = var12_23.getString("user_security");
                var2_6 = 0;
            }
            catch (IOException var1_3) {
                var2_6 = 1;
                var1_1 = var9_18;
            }
            catch (Exception var1_4) {
                Log.e((String)"MxActivateService", (String)"error when get SIM service token");
                var2_6 = 0;
                var1_1 = var10_5;
            }
            if (var1_1 != null) {
                ** if (var8_7 == null) goto lbl200
            }
            ** GOTO lbl-1000
lbl-1000: // 2 sources:
            {
                if (var1_1 == null || var8_7 == null) break;
                var12_23 = MxActivateService.MX_STATUS_INFO_LOCK;
                // MONITORENTER : var12_23
                var9_18 = var10_5 = MxActivateService.mMxStatusInfo.get(var11_11);
                if (var10_5 == null) {
                    var9_18 = new MxStatusInfo();
                    MxActivateService.mMxStatusInfo.put(var11_11, (MxStatusInfo)var9_18);
                }
                var9_18.mid = var13_24;
                var9_18.simId = var11_11;
                var9_18.serviceToken = var1_1;
                var9_18.security = var8_7;
                var9_18.enabled = true;
                MxActivateService.persistMxStatusInfo((Context)this, MxActivateService.mMxStatusInfo);
                // MONITOREXIT : var12_23
                this.onEnableSuccess(var4_14, var13_24, (String)var1_1, (String)var8_7, var14_25);
                return;
            }
lbl200: // 1 sources:
            if (var2_6 != 0) {
                try {
                    Log.d((String)"MxActivateService", (String)"wait until network connected");
                    CloudManager.waitUntilNetworkConnected((Context)this);
                }
                catch (InterruptedException var9_19) {
                    Thread.currentThread().interrupt();
                }
            } else lbl-1000: // 2 sources:
            {
                try {
                    Log.d((String)"MxActivateService", (String)("sleep " + MxActivateService.INTS[var3_9] + " ms, wait next try."));
                    Thread.sleep((long)MxActivateService.INTS[var3_9]);
                }
                catch (InterruptedException var9_20) {
                    Thread.currentThread().interrupt();
                }
            }
            ++var3_9;
        }
        Log.e((String)"MxActivateService", (String)"failed to get auth token");
        this.onEnableFailure(var4_14, false);
    }

    public static class MxStatusInfo {
        public boolean enabled;
        public String mid;
        public String security;
        public String serviceToken;
        public String simId;

        MxStatusInfo() {
        }

        MxStatusInfo(MxStatusInfo mxStatusInfo) {
            this.mid = mxStatusInfo.mid;
            this.simId = mxStatusInfo.simId;
            this.serviceToken = mxStatusInfo.serviceToken;
            this.security = mxStatusInfo.security;
            this.enabled = mxStatusInfo.enabled;
        }
    }

}

