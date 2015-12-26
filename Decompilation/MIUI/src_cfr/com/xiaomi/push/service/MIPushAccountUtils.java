/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.text.TextUtils
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.BuildSettings;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import com.xiaomi.push.service.DeviceInfo;
import com.xiaomi.push.service.MIPushAccount;
import com.xiaomi.push.service.MIPushClientManager;
import java.io.IOException;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;

public class MIPushAccountUtils {
    private static MIPushAccount sAccount;

    public static void clearAccount(Context context) {
        context.getSharedPreferences("mipush_account", 0).edit().clear().commit();
        sAccount = null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String getAccountURL() {
        String string2;
        if (BuildSettings.IsOneBoxBuild()) {
            return "http://10.237.12.17:9085/pass/register";
        }
        StringBuilder stringBuilder = new StringBuilder().append("https://");
        if (BuildSettings.IsSandBoxBuild()) {
            string2 = "sandbox.xmpush.xiaomi.com";
            do {
                return stringBuilder.append(string2).append("/pass/register").toString();
                break;
            } while (true);
        }
        string2 = "register.xmpush.xiaomi.com";
        return stringBuilder.append(string2).append("/pass/register").toString();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static MIPushAccount getMIPushAccount(Context context) {
        Object var4_1 = null;
        synchronized (MIPushAccountUtils.class) {
            if (sAccount != null) {
                return sAccount;
            }
            SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_account", 0);
            String string2 = sharedPreferences.getString("uuid", null);
            String string3 = sharedPreferences.getString("token", null);
            String string4 = sharedPreferences.getString("security", null);
            String string5 = sharedPreferences.getString("app_id", null);
            String string6 = sharedPreferences.getString("app_token", null);
            String string7 = sharedPreferences.getString("package_name", null);
            Object object = sharedPreferences.getString("device_id", null);
            int n = sharedPreferences.getInt("env_type", 1);
            Object object2 = object;
            if (!TextUtils.isEmpty((CharSequence)object)) {
                object2 = object;
                if (object.startsWith("a-")) {
                    object2 = DeviceInfo.getSimpleDeviceId(context);
                    sharedPreferences.edit().putString("device_id", (String)object2).commit();
                }
            }
            object = var4_1;
            if (TextUtils.isEmpty((CharSequence)string2)) return object;
            object = var4_1;
            if (TextUtils.isEmpty((CharSequence)string3)) return object;
            object = var4_1;
            if (TextUtils.isEmpty((CharSequence)string4)) return object;
            object = DeviceInfo.getSimpleDeviceId(context);
            if ("com.xiaomi.xmsf".equals((Object)context.getPackageName())) return MIPushAccountUtils.sAccount = new MIPushAccount(string2, string3, string4, string5, string6, string7, n);
            if (TextUtils.isEmpty((CharSequence)object)) return MIPushAccountUtils.sAccount = new MIPushAccount(string2, string3, string4, string5, string6, string7, n);
            if (TextUtils.isEmpty((CharSequence)object2)) return MIPushAccountUtils.sAccount = new MIPushAccount(string2, string3, string4, string5, string6, string7, n);
            if (object2.equals(object)) return MIPushAccountUtils.sAccount = new MIPushAccount(string2, string3, string4, string5, string6, string7, n);
            MyLog.e("erase the old account.");
            MIPushAccountUtils.clearAccount(context);
            return var4_1;
        }
    }

    private static boolean isMIUIPush(Context context) {
        return context.getPackageName().equals((Object)"com.xiaomi.xmsf");
    }

    private static void persist(Context context, MIPushAccount mIPushAccount) {
        SharedPreferences.Editor editor = context.getSharedPreferences("mipush_account", 0).edit();
        editor.putString("uuid", mIPushAccount.account);
        editor.putString("security", mIPushAccount.security);
        editor.putString("token", mIPushAccount.token);
        editor.putString("app_id", mIPushAccount.appId);
        editor.putString("package_name", mIPushAccount.packageName);
        editor.putString("app_token", mIPushAccount.appToken);
        editor.putString("device_id", DeviceInfo.getSimpleDeviceId(context));
        editor.putInt("env_type", mIPushAccount.envType);
        editor.commit();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static MIPushAccount register(Context object, String object2, String string2, String string3) throws IOException, JSONException {
        synchronized (MIPushAccountUtils.class) {
            Object object3;
            Object object4 = new JSONObject();
            object4.put((String)"devid", (String)DeviceInfo.getDeviceId((Context)object));
            if (MIPushAccountUtils.isMIUIPush((Context)object)) {
                string2 = "1000271";
            }
            if (MIPushAccountUtils.isMIUIPush((Context)object)) {
                string3 = "420100086271";
            }
            if (MIPushAccountUtils.isMIUIPush((Context)object)) {
                object2 = "com.xiaomi.xmsf";
            }
            object4.put((String)"appid", (String)string2);
            object4.put((String)"apptoken", (String)string3);
            Object object5 = null;
            try {
                object5 = object3 = object.getPackageManager().getPackageInfo((String)object2, 16384);
            }
            catch (Exception var5_7) {
                MyLog.e(var5_7);
            }
            object5 = object5 != null ? String.valueOf((int)object5.versionCode) : "0";
            object4.put((String)"appversion", (Object)object5);
            object4.put((String)"'sdkversion", (String)Integer.toString((int)7));
            object4.put((String)"packagename", (String)object2);
            object4.put((String)"model", (String)Build.MODEL);
            object4.put((String)"imei_md5", (String)XMStringUtils.getMd5Digest(DeviceInfo.blockingGetIMEI((Context)object)));
            object4.put((String)"os", (String)(Build.VERSION.RELEASE + "-" + Build.VERSION.INCREMENTAL));
            object3 = Network.doHttpPost((Context)object, MIPushAccountUtils.getAccountURL(), object4);
            object5 = "";
            if (object3 != null) {
                object5 = object3.getResponseString();
            }
            if (TextUtils.isEmpty((CharSequence)object5)) return null;
            object3 = new JSONObject((String)object5);
            if (object3.getInt("code") == 0) {
                object4 = object3.getJSONObject("data");
                object5 = object4.getString("ssecurity");
                object3 = object4.getString("token");
                object4 = object4.getString("userId");
                object2 = new MIPushAccount((String)object4 + "@xiaomi.com/an" + XMStringUtils.generateRandomString(6), (String)object3, (String)object5, string2, string3, (String)object2, BuildSettings.getEnvType());
                MIPushAccountUtils.persist((Context)object, (MIPushAccount)object2);
                sAccount = object2;
                return object2;
            }
            MIPushClientManager.notifyRegisterError((Context)object, object3.getInt("code"), object3.optString("description"));
            MyLog.warn((String)object5);
            return null;
        }
    }
}

