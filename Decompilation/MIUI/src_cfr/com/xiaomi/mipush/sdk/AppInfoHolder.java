/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.DeviceInfo;

public class AppInfoHolder {
    private static AppInfoHolder sInstance;
    private Context mContext;
    private ClientInfoData mInfoData;

    private AppInfoHolder(Context context) {
        this.mContext = context;
        this.init();
    }

    public static AppInfoHolder getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new AppInfoHolder(context);
        }
        return sInstance;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String getVersionName(Context context, String string2) {
        Object var2_3 = null;
        try {
            context = context.getPackageManager().getPackageInfo(string2, 16384);
        }
        catch (Exception var0_1) {
            MyLog.e(var0_1);
            context = var2_3;
        }
        if (context != null) {
            return context.versionName;
        }
        return "1.0";
    }

    private void init() {
        this.mInfoData = new ClientInfoData();
        SharedPreferences sharedPreferences = this.getSharedPreferences();
        this.mInfoData.appID = sharedPreferences.getString("appId", null);
        this.mInfoData.appToken = sharedPreferences.getString("appToken", null);
        this.mInfoData.regID = sharedPreferences.getString("regId", null);
        this.mInfoData.regSecret = sharedPreferences.getString("regSec", null);
        this.mInfoData.deviceId = sharedPreferences.getString("devId", null);
        if (!TextUtils.isEmpty((CharSequence)this.mInfoData.deviceId) && this.mInfoData.deviceId.startsWith("a-")) {
            this.mInfoData.deviceId = DeviceInfo.getSimpleDeviceId(this.mContext);
            sharedPreferences.edit().putString("devId", this.mInfoData.deviceId).commit();
        }
        this.mInfoData.versionName = sharedPreferences.getString("vName", null);
        this.mInfoData.isValid = sharedPreferences.getBoolean("valid", true);
        this.mInfoData.isPaused = sharedPreferences.getBoolean("paused", false);
        this.mInfoData.envType = sharedPreferences.getInt("envType", 1);
        this.mInfoData.regResource = sharedPreferences.getString("regResource", null);
    }

    public boolean appRegistered() {
        return this.mInfoData.isVaild();
    }

    public boolean appRegistered(String string2, String string3) {
        return this.mInfoData.isVaild(string2, string3);
    }

    public boolean checkAppInfo() {
        if (!this.mInfoData.isVaild()) {
            MyLog.warn("Don't send message before initialization succeeded!");
            return false;
        }
        return true;
    }

    public boolean checkVersionNameChanged() {
        if (!TextUtils.equals((CharSequence)AppInfoHolder.getVersionName(this.mContext, this.mContext.getPackageName()), (CharSequence)this.mInfoData.versionName)) {
            return true;
        }
        return false;
    }

    public void clear() {
        this.mInfoData.clear();
    }

    public String getAppID() {
        return this.mInfoData.appID;
    }

    public String getAppToken() {
        return this.mInfoData.appToken;
    }

    public int getEnvType() {
        return this.mInfoData.envType;
    }

    public String getRegID() {
        return this.mInfoData.regID;
    }

    public String getRegResource() {
        return this.mInfoData.regResource;
    }

    public String getRegSecret() {
        return this.mInfoData.regSecret;
    }

    public SharedPreferences getSharedPreferences() {
        return this.mContext.getSharedPreferences("mipush", 0);
    }

    public void invalidate() {
        this.mInfoData.invalidate();
    }

    public boolean invalidated() {
        if (!this.mInfoData.isValid) {
            return true;
        }
        return false;
    }

    public boolean isPaused() {
        return this.mInfoData.isPaused;
    }

    public void putAppIDAndToken(String string2, String string3, String string4) {
        this.mInfoData.setIdAndToken(string2, string3, string4);
    }

    public void putRegIDAndSecret(String string2, String string3) {
        this.mInfoData.setRegIdAndSecret(string2, string3);
    }

    public void setEnvType(int n) {
        this.mInfoData.setEnvType(n);
        this.getSharedPreferences().edit().putInt("envType", n).commit();
    }

    public void setPaused(boolean bl) {
        this.mInfoData.setPaused(bl);
        this.getSharedPreferences().edit().putBoolean("paused", bl).commit();
    }

    public void updateVersionName(String string2) {
        SharedPreferences.Editor editor = this.getSharedPreferences().edit();
        editor.putString("vName", string2);
        editor.commit();
        this.mInfoData.versionName = string2;
    }

    private class ClientInfoData {
        public String appID;
        public String appToken;
        public String deviceId;
        public int envType;
        public boolean isPaused;
        public boolean isValid;
        public String regID;
        public String regResource;
        public String regSecret;
        public String versionName;

        private ClientInfoData() {
            this.isValid = true;
            this.isPaused = false;
            this.envType = 1;
        }

        private String getVersionName() {
            return AppInfoHolder.getVersionName(AppInfoHolder.this.mContext, AppInfoHolder.this.mContext.getPackageName());
        }

        public void clear() {
            AppInfoHolder.this.getSharedPreferences().edit().clear().commit();
            this.appID = null;
            this.appToken = null;
            this.regID = null;
            this.regSecret = null;
            this.deviceId = null;
            this.versionName = null;
            this.isValid = false;
            this.isPaused = false;
            this.envType = 1;
        }

        public void invalidate() {
            this.isValid = false;
            AppInfoHolder.this.getSharedPreferences().edit().putBoolean("valid", this.isValid).commit();
        }

        public boolean isVaild() {
            return this.isVaild(this.appID, this.appToken);
        }

        public boolean isVaild(String string2, String string3) {
            if (TextUtils.equals((CharSequence)this.appID, (CharSequence)string2) && TextUtils.equals((CharSequence)this.appToken, (CharSequence)string3) && !TextUtils.isEmpty((CharSequence)this.regID) && !TextUtils.isEmpty((CharSequence)this.regSecret) && TextUtils.equals((CharSequence)this.deviceId, (CharSequence)DeviceInfo.getSimpleDeviceId(AppInfoHolder.this.mContext))) {
                return true;
            }
            return false;
        }

        public void setEnvType(int n) {
            this.envType = n;
        }

        public void setIdAndToken(String string2, String string3, String string4) {
            this.appID = string2;
            this.appToken = string3;
            this.regResource = string4;
            string2 = AppInfoHolder.this.getSharedPreferences().edit();
            string2.putString("appId", this.appID);
            string2.putString("appToken", string3);
            string2.putString("regResource", string4);
            string2.commit();
        }

        public void setPaused(boolean bl) {
            this.isPaused = bl;
        }

        public void setRegIdAndSecret(String string2, String string3) {
            this.regID = string2;
            this.regSecret = string3;
            this.deviceId = DeviceInfo.getSimpleDeviceId(AppInfoHolder.this.mContext);
            this.versionName = this.getVersionName();
            this.isValid = true;
            SharedPreferences.Editor editor = AppInfoHolder.this.getSharedPreferences().edit();
            editor.putString("regId", string2);
            editor.putString("regSec", string3);
            editor.putString("devId", this.deviceId);
            editor.putString("vName", this.getVersionName());
            editor.putBoolean("valid", true);
            editor.commit();
        }
    }

}

