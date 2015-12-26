package com.android.mms.cloudbackup;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.android.mms.ui.MessageUtils;
import com.android.mms.understand.UnderstandLoader;
import miui.cloud.backup.ICloudBackup;
import miui.cloud.backup.data.DataPackage;
import miui.cloud.backup.data.SettingItem;
import miui.os.Build;
import org.json.JSONException;
import org.json.JSONObject;

public class SettingsCloudBackupImpl
  implements ICloudBackup
{
  private static JSONObject logJSON(JSONObject paramJSONObject)
  {
    JSONObject localJSONObject;
    if (paramJSONObject == null) {
      localJSONObject = null;
    }
    do
    {
      return localJSONObject;
      localJSONObject = paramJSONObject;
    } while (Build.IS_OFFICIAL_VERSION);
    Log.v("MmsSettingsCloudBackupService", paramJSONObject.toString());
    return paramJSONObject;
  }
  
  private void restoreMessageAdvancedSettings(Context paramContext, JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {}
    do
    {
      return;
      if (paramJSONObject.has("CKAllowSiSlPush")) {
        MessageConfigUtils.setAllowSiSlPush(paramContext, paramJSONObject.optBoolean("CKAllowSiSlPush"));
      }
      if (paramJSONObject.has("CKHideFestival")) {
        MessageConfigUtils.setHideFestival(paramContext, paramJSONObject.optBoolean("CKHideFestival"));
      }
    } while (!paramJSONObject.has("CKFestivalNetWorking"));
    MessageConfigUtils.setFestivalNetWorking(paramContext, paramJSONObject.optBoolean("CKFestivalNetWorking"));
  }
  
  private void restoreMessageSettings(Context paramContext, JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {}
    for (;;)
    {
      return;
      SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
      if (paramJSONObject.has("CKMSGNotification")) {
        MessageConfigUtils.setPrefNotificationBodyEnabled(paramContext, paramJSONObject.optBoolean("CKMSGNotification"));
      }
      if (paramJSONObject.has("CKNotificationRingToneRepeat")) {
        MessageConfigUtils.setNotificationRingToneRepeat(localSharedPreferences, paramJSONObject.optString("CKNotificationRingToneRepeat"));
      }
      if (paramJSONObject.has("CKShowRecentContact")) {
        MessageConfigUtils.setShowRecentContacts(paramContext, paramJSONObject.optBoolean("CKShowRecentContact"));
      }
      if (paramJSONObject.has("CKTimeStyleAutoGroup")) {
        MessageConfigUtils.setTimeStyleAutoGroup(paramContext, paramJSONObject.optBoolean("CKTimeStyleAutoGroup"));
      }
      if (paramJSONObject.has("CKCollapseSpMessage")) {
        MessageConfigUtils.setCollapseSpMessage(paramContext, paramJSONObject.optBoolean("CKCollapseSpMessage"));
      }
      if (paramJSONObject.has("CKDateType")) {
        MessageConfigUtils.setDateType(localSharedPreferences, paramJSONObject.optString("CKDateType"));
      }
      if ((MessageUtils.isUnderstandSupported()) && (paramJSONObject.has("CKShowTemplate")))
      {
        boolean bool = paramJSONObject.optBoolean("CKShowTemplate");
        MessageConfigUtils.setShowTemplate(paramContext, bool);
        if (!bool) {
          break label181;
        }
        UnderstandLoader.update();
      }
      while (paramJSONObject.has("CKShowBlocked"))
      {
        MessageConfigUtils.setShowBlockedMessage(paramContext, paramJSONObject.optBoolean("CKShowBlocked"));
        return;
        label181:
        UnderstandLoader.destroy();
      }
    }
  }
  
  private JSONObject saveMessageAdvancedSettings(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("CKAllowSiSlPush", MessageConfigUtils.getAllowSiSlPush(paramContext));
      localJSONObject.put("CKHideFestival", MessageConfigUtils.getHideFestival(paramContext));
      localJSONObject.put("CKFestivalNetWorking", MessageConfigUtils.getFestivalNetWorking(paramContext));
      return localJSONObject;
    }
    catch (JSONException paramContext)
    {
      Log.e("MmsSettingsCloudBackupService", "Put message advanced settings to JSON failed. ");
    }
    return localJSONObject;
  }
  
  private JSONObject saveMessageSettings(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
      localJSONObject.put("CKMSGNotification", MessageConfigUtils.getPrefNotificationBodyEnabled(paramContext));
      localJSONObject.put("CKNotificationRingToneRepeat", MessageConfigUtils.getNotificationRingToneRepeat(localSharedPreferences));
      localJSONObject.put("CKShowRecentContact", MessageConfigUtils.getShowRecentContacts(paramContext));
      localJSONObject.put("CKTimeStyleAutoGroup", MessageConfigUtils.getTimeStyleAutoGroup(paramContext));
      localJSONObject.put("CKCollapseSpMessage", MessageConfigUtils.getCollapseSpMessage(paramContext));
      localJSONObject.put("CKDateType", MessageConfigUtils.getDateType(localSharedPreferences));
      localJSONObject.put("CKShowTemplate", MessageConfigUtils.getShowTemplate(paramContext));
      localJSONObject.put("CKShowBlocked", MessageConfigUtils.getShowBlockedMessage(paramContext));
      return localJSONObject;
    }
    catch (JSONException paramContext)
    {
      Log.e("MmsSettingsCloudBackupService", "Put message settings to JSON failed. ");
    }
    return localJSONObject;
  }
  
  public int getCurrentVersion(Context paramContext)
  {
    return 1;
  }
  
  public void onBackupSettings(Context paramContext, DataPackage paramDataPackage)
  {
    Log.v("MmsSettingsCloudBackupService", "start settings backup. ");
    paramDataPackage.addKeyJson("MessageSettings", logJSON(saveMessageSettings(paramContext)));
    paramDataPackage.addKeyJson("TelocationSettings", logJSON(saveMessageAdvancedSettings(paramContext)));
    Log.v("MmsSettingsCloudBackupService", "end settings backup. ");
  }
  
  public void onRestoreSettings(Context paramContext, DataPackage paramDataPackage, int paramInt)
  {
    Log.v("MmsSettingsCloudBackupService", "start settings restore. ");
    restoreMessageSettings(paramContext, logJSON((JSONObject)paramDataPackage.get("MessageSettings").getValue()));
    restoreMessageAdvancedSettings(paramContext, logJSON((JSONObject)paramDataPackage.get("TelocationSettings").getValue()));
    Log.v("MmsSettingsCloudBackupService", "end settings restore. ");
  }
}

/* Location:
 * Qualified Name:     com.android.mms.cloudbackup.SettingsCloudBackupImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */