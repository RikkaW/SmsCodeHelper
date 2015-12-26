/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.AsyncTask
 *  android.text.TextUtils
 *  com.xiaomi.micloudsdk.CloudConfig
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  miui.util.XMAccountUtils
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.xiaomi.mms.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.xiaomi.micloudsdk.CloudConfig;
import com.xiaomi.mms.mx.common.AsyncTaskUtils;
import com.xiaomi.mms.net.CloudRequestExecutor;
import com.xiaomi.mms.net.exception.InvalidResponseException;
import com.xiaomi.mms.utils.PrefUtils;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.IOException;
import miui.util.XMAccountUtils;
import org.json.JSONException;
import org.json.JSONObject;

public final class MxConfigCompat {
    private static final String MXV1_BASE_URL = CloudConfig.URL_RICH_MEDIA_BASE + "/mic/file/v2/user/%s/full";
    private static volatile long sMainlandMaxUserId = -1;
    private static volatile long sMainlandMinUserId = -1;

    private static void asyncRequest(final Context context) {
        AsyncTaskUtils.exe(2, new AsyncTask<Void, Void, JSONObject>(){

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            protected /* varargs */ JSONObject doInBackground(Void ... jSONObject) {
                try {
                    return MxConfigCompat.getIDCConfig(context);
                }
                catch (IOException var1_2) {
                    MyLog.e("MxConfigCompat", "get mx config info from network failed, beause " + var1_2.toString());
                    do {
                        return null;
                        break;
                    } while (true);
                }
                catch (InvalidResponseException var1_3) {
                    MyLog.e("MxConfigCompat", "get mx config info from network failed, beause " + var1_3.toString());
                    return null;
                }
            }

            protected void onPostExecute(JSONObject jSONObject) {
                super.onPostExecute((Object)jSONObject);
                if (jSONObject != null) {
                    MxConfigCompat.saveIDCConfigInfo(context, jSONObject);
                    MxConfigCompat.readDomesticIDCConfigInfo(context);
                }
            }
        }, new Void[0]);
    }

    private static JSONObject getIDCConfig(Context context) throws IOException, InvalidResponseException {
        return CloudRequestExecutor.getIDCConfig();
    }

    private static String getMaxUserIdByTag(JSONObject jSONObject, String string2) throws JSONException {
        return MxConfigCompat.getMxConfigUserId(jSONObject, string2, "userId.max");
    }

    private static String getMinUserIdByTag(JSONObject jSONObject, String string2) throws JSONException {
        return MxConfigCompat.getMxConfigUserId(jSONObject, string2, "userId.min");
    }

    private static String getMxConfigUserId(JSONObject jSONObject, String string2, String string3) {
        if (jSONObject != null && (jSONObject = jSONObject.optJSONObject(string2)) != null) {
            return jSONObject.optString(string3);
        }
        return null;
    }

    public static String getMxV1Sid(Context context, String string2) {
        String string3;
        String string4 = string3 = MxConfigCompat.getMxV1Sid(string2);
        if (TextUtils.isEmpty((CharSequence)string3)) {
            MxConfigCompat.requestAndSaveMxConfig(context);
            string4 = MxConfigCompat.getMxV1Sid(string2);
        }
        return string4;
    }

    private static String getMxV1Sid(String string2) {
        if (MxConfigCompat.hasCached()) {
            if (MxConfigCompat.isDomesticMiid(string2)) {
                return "micfile";
            }
            return "micfile";
        }
        return null;
    }

    public static String getMxV2RichMediaUrl(Context context, String string2) {
        String string3;
        String string4 = string3 = MxConfigCompat.getMxV2RichMediaUrl(string2);
        if (TextUtils.isEmpty((CharSequence)string3)) {
            MxConfigCompat.requestAndSaveMxConfig(context);
            string4 = MxConfigCompat.getMxV2RichMediaUrl(string2);
        }
        return string4;
    }

    private static String getMxV2RichMediaUrl(String string2) {
        if (MxConfigCompat.hasCached()) {
            if (MxConfigCompat.isDomesticMiid(string2)) {
                return "http://mixin.api.files.xiaomi.com/mixin/v2/file/user/%s";
            }
            return "http://us.mixin.api.files.xiaomi.com/mixin/v2/file/user/%s";
        }
        return null;
    }

    public static String getMxV2Sid(Context context, String string2) {
        String string3;
        String string4 = string3 = MxConfigCompat.getMxV2Sid(string2);
        if (TextUtils.isEmpty((CharSequence)string3)) {
            MxConfigCompat.requestAndSaveMxConfig(context);
            string4 = MxConfigCompat.getMxV2Sid(string2);
        }
        return string4;
    }

    private static String getMxV2Sid(String string2) {
        if (MxConfigCompat.hasCached()) {
            if (MxConfigCompat.isDomesticMiid(string2)) {
                return "mixin_mfs";
            }
            return "mixin_mfs_us";
        }
        return null;
    }

    public static String getOtherIDCMxV1Sid(Context context, String string2) {
        String string3;
        String string4 = string3 = MxConfigCompat.getMxV1Sid(string2);
        if (TextUtils.isEmpty((CharSequence)string3)) {
            MxConfigCompat.requestAndSaveMxConfig(context);
            string4 = MxConfigCompat.getMxV1Sid(string2);
        }
        return string4;
    }

    public static String getOtherIDCMxV2RichMediaUrl(Context context, String string2) {
        String string3;
        String string4 = string3 = MxConfigCompat.getOtherIDCMxV2RichMediaUrl(string2);
        if (TextUtils.isEmpty((CharSequence)string3)) {
            MxConfigCompat.requestAndSaveMxConfig(context);
            string4 = MxConfigCompat.getOtherIDCMxV2RichMediaUrl(string2);
        }
        return string4;
    }

    private static String getOtherIDCMxV2RichMediaUrl(String string2) {
        if (MxConfigCompat.hasCached()) {
            if (MxConfigCompat.isDomesticMiid(string2)) {
                return "http://us.mixin.api.files.xiaomi.com/mixin/v2/file/user/%s";
            }
            return "http://mixin.api.files.xiaomi.com/mixin/v2/file/user/%s";
        }
        return null;
    }

    public static String getOtherIDCMxV2Sid(Context context, String string2) {
        String string3;
        String string4 = string3 = MxConfigCompat.getOtherIDCMxV2Sid(string2);
        if (TextUtils.isEmpty((CharSequence)string3)) {
            MxConfigCompat.requestAndSaveMxConfig(context);
            string4 = MxConfigCompat.getOtherIDCMxV2Sid(string2);
        }
        return string4;
    }

    private static String getOtherIDCMxV2Sid(String string2) {
        if (MxConfigCompat.hasCached()) {
            if (MxConfigCompat.isDomesticMiid(string2)) {
                return "mixin_mfs_us";
            }
            return "mixin_mfs";
        }
        return null;
    }

    private static boolean hasCached() {
        if (sMainlandMaxUserId > 0 && sMainlandMinUserId >= 0) {
            return true;
        }
        return false;
    }

    public static void init(Context context) {
        MxConfigCompat.readDomesticIDCConfigInfo(context);
        if (!MxConfigCompat.hasCached()) {
            MxConfigCompat.asyncRequest(context);
        }
    }

    public static boolean isDomesticMiid(String string2) {
        boolean bl;
        block4 : {
            boolean bl2 = false;
            string2 = XMAccountUtils.trimDomainSuffix((String)string2);
            bl = bl2;
            try {
                if (TextUtils.isEmpty((CharSequence)string2)) break block4;
                bl = bl2;
            }
            catch (NumberFormatException var0_1) {
                MyLog.e("MxConfigCompat", var0_1.toString());
                return false;
            }
            if (!XMAccountUtils.isNumeric((String)string2)) break block4;
            long l = Long.valueOf((String)string2);
            bl = bl2;
            if (sMainlandMinUserId > l) break block4;
            long l2 = sMainlandMaxUserId;
            bl = bl2;
            if (l >= l2) break block4;
            bl = true;
        }
        return bl;
    }

    public static boolean isMxV2GeneralRichmedia(String string2) {
        return string2.startsWith("s2t.");
    }

    public static boolean isMxV2PublicRichmedia(String string2) {
        return string2.startsWith("s2p.");
    }

    public static boolean isMxV2Richmedia(String string2) {
        if (MxConfigCompat.isMxV2PublicRichmedia(string2) || MxConfigCompat.isMxV2GeneralRichmedia(string2)) {
            return true;
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void readDomesticIDCConfigInfo(Context object) {
        if (TextUtils.isEmpty((CharSequence)(object = PrefUtils.getString((Context)object, "mx_config_idc_pref")))) return;
        try {
            Object object2 = new JSONObject((String)object);
            object = MxConfigCompat.getMaxUserIdByTag((JSONObject)object2, "China");
            object2 = MxConfigCompat.getMinUserIdByTag((JSONObject)object2, "China");
            if (XMAccountUtils.isNumeric((String)object)) {
                sMainlandMaxUserId = Long.valueOf((String)object);
            }
            if (!XMAccountUtils.isNumeric((String)object2)) return;
            sMainlandMinUserId = Long.valueOf((String)object2);
            return;
        }
        catch (JSONException var0_1) {
            MyLog.e("MxConfigCompat", var0_1.toString());
            return;
        }
        catch (NumberFormatException var0_2) {
            MyLog.e("MxConfigCompat", var0_2.toString());
            return;
        }
    }

    private static void requestAndSaveMxConfig(Context context) {
        try {
            MxConfigCompat.saveIDCConfigInfo(context, MxConfigCompat.getIDCConfig(context));
            MxConfigCompat.readDomesticIDCConfigInfo(context);
            return;
        }
        catch (IOException var0_1) {
            MyLog.e("MxConfigCompat", "blocking get rich media upload url failed " + var0_1.toString());
            return;
        }
        catch (InvalidResponseException var0_2) {
            MyLog.e("MxConfigCompat", "blocking get rich media upload url failed " + var0_2.toString());
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void saveIDCConfigInfo(Context context, JSONObject jSONObject) {
        if (jSONObject == null) return;
        try {
            jSONObject = jSONObject.getJSONObject("idc");
            if (jSONObject == null) return;
        }
        catch (JSONException var0_1) {
            MyLog.e("MxConfigCompat", var0_1.toString());
            return;
        }
        PrefUtils.saveString(context, "mx_config_idc_pref", jSONObject.toString());
    }

}

