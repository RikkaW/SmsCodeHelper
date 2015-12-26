/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.preference.PreferenceManager
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  miui.cloud.backup.ICloudBackup
 *  miui.cloud.backup.data.DataPackage
 *  miui.cloud.backup.data.SettingItem
 *  miui.os.Build
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.android.mms.cloudbackup;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.android.mms.cloudbackup.MessageConfigUtils;
import com.android.mms.ui.MessageUtils;
import com.android.mms.understand.UnderstandLoader;
import miui.cloud.backup.ICloudBackup;
import miui.cloud.backup.data.DataPackage;
import miui.cloud.backup.data.SettingItem;
import miui.os.Build;
import org.json.JSONException;
import org.json.JSONObject;

public class SettingsCloudBackupImpl
implements ICloudBackup {
    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static JSONObject logJSON(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        JSONObject jSONObject2 = jSONObject;
        if (Build.IS_OFFICIAL_VERSION) return jSONObject2;
        Log.v((String)"MmsSettingsCloudBackupService", (String)jSONObject.toString());
        return jSONObject;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void restoreMessageAdvancedSettings(Context context, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        if (jSONObject.has("CKAllowSiSlPush")) {
            MessageConfigUtils.setAllowSiSlPush(context, jSONObject.optBoolean("CKAllowSiSlPush"));
        }
        if (jSONObject.has("CKHideFestival")) {
            MessageConfigUtils.setHideFestival(context, jSONObject.optBoolean("CKHideFestival"));
        }
        if (!jSONObject.has("CKFestivalNetWorking")) return;
        MessageConfigUtils.setFestivalNetWorking(context, jSONObject.optBoolean("CKFestivalNetWorking"));
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void restoreMessageSettings(Context context, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences((Context)context);
        if (jSONObject.has("CKMSGNotification")) {
            MessageConfigUtils.setPrefNotificationBodyEnabled(context, jSONObject.optBoolean("CKMSGNotification"));
        }
        if (jSONObject.has("CKNotificationRingToneRepeat")) {
            MessageConfigUtils.setNotificationRingToneRepeat(sharedPreferences, jSONObject.optString("CKNotificationRingToneRepeat"));
        }
        if (jSONObject.has("CKShowRecentContact")) {
            MessageConfigUtils.setShowRecentContacts(context, jSONObject.optBoolean("CKShowRecentContact"));
        }
        if (jSONObject.has("CKTimeStyleAutoGroup")) {
            MessageConfigUtils.setTimeStyleAutoGroup(context, jSONObject.optBoolean("CKTimeStyleAutoGroup"));
        }
        if (jSONObject.has("CKCollapseSpMessage")) {
            MessageConfigUtils.setCollapseSpMessage(context, jSONObject.optBoolean("CKCollapseSpMessage"));
        }
        if (jSONObject.has("CKDateType")) {
            MessageConfigUtils.setDateType(sharedPreferences, jSONObject.optString("CKDateType"));
        }
        if (MessageUtils.isUnderstandSupported() && jSONObject.has("CKShowTemplate")) {
            boolean bl = jSONObject.optBoolean("CKShowTemplate");
            MessageConfigUtils.setShowTemplate(context, bl);
            if (bl) {
                UnderstandLoader.update();
            } else {
                UnderstandLoader.destroy();
            }
        }
        if (!jSONObject.has("CKShowBlocked")) return;
        MessageConfigUtils.setShowBlockedMessage(context, jSONObject.optBoolean("CKShowBlocked"));
    }

    private JSONObject saveMessageAdvancedSettings(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("CKAllowSiSlPush", MessageConfigUtils.getAllowSiSlPush(context));
            jSONObject.put("CKHideFestival", MessageConfigUtils.getHideFestival(context));
            jSONObject.put("CKFestivalNetWorking", MessageConfigUtils.getFestivalNetWorking(context));
            return jSONObject;
        }
        catch (JSONException var1_2) {
            Log.e((String)"MmsSettingsCloudBackupService", (String)"Put message advanced settings to JSON failed. ");
            return jSONObject;
        }
    }

    private JSONObject saveMessageSettings(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences((Context)context);
            jSONObject.put("CKMSGNotification", MessageConfigUtils.getPrefNotificationBodyEnabled(context));
            jSONObject.put("CKNotificationRingToneRepeat", (Object)MessageConfigUtils.getNotificationRingToneRepeat(sharedPreferences));
            jSONObject.put("CKShowRecentContact", MessageConfigUtils.getShowRecentContacts(context));
            jSONObject.put("CKTimeStyleAutoGroup", MessageConfigUtils.getTimeStyleAutoGroup(context));
            jSONObject.put("CKCollapseSpMessage", MessageConfigUtils.getCollapseSpMessage(context));
            jSONObject.put("CKDateType", (Object)MessageConfigUtils.getDateType(sharedPreferences));
            jSONObject.put("CKShowTemplate", MessageConfigUtils.getShowTemplate(context));
            jSONObject.put("CKShowBlocked", MessageConfigUtils.getShowBlockedMessage(context));
            return jSONObject;
        }
        catch (JSONException var1_2) {
            Log.e((String)"MmsSettingsCloudBackupService", (String)"Put message settings to JSON failed. ");
            return jSONObject;
        }
    }

    public int getCurrentVersion(Context context) {
        return 1;
    }

    public void onBackupSettings(Context context, DataPackage dataPackage) {
        Log.v((String)"MmsSettingsCloudBackupService", (String)"start settings backup. ");
        dataPackage.addKeyJson("MessageSettings", SettingsCloudBackupImpl.logJSON(this.saveMessageSettings(context)));
        dataPackage.addKeyJson("TelocationSettings", SettingsCloudBackupImpl.logJSON(this.saveMessageAdvancedSettings(context)));
        Log.v((String)"MmsSettingsCloudBackupService", (String)"end settings backup. ");
    }

    public void onRestoreSettings(Context context, DataPackage dataPackage, int n) {
        Log.v((String)"MmsSettingsCloudBackupService", (String)"start settings restore. ");
        this.restoreMessageSettings(context, SettingsCloudBackupImpl.logJSON((JSONObject)dataPackage.get("MessageSettings").getValue()));
        this.restoreMessageAdvancedSettings(context, SettingsCloudBackupImpl.logJSON((JSONObject)dataPackage.get("TelocationSettings").getValue()));
        Log.v((String)"MmsSettingsCloudBackupService", (String)"end settings restore. ");
    }
}

