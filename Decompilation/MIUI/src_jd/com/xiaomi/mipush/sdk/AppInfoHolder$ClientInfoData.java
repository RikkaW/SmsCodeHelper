package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.xiaomi.push.service.DeviceInfo;

class AppInfoHolder$ClientInfoData
{
  public String appID;
  public String appToken;
  public String deviceId;
  public int envType = 1;
  public boolean isPaused = false;
  public boolean isValid = true;
  public String regID;
  public String regResource;
  public String regSecret;
  public String versionName;
  
  private AppInfoHolder$ClientInfoData(AppInfoHolder paramAppInfoHolder) {}
  
  private String getVersionName()
  {
    return AppInfoHolder.getVersionName(AppInfoHolder.access$100(this$0), AppInfoHolder.access$100(this$0).getPackageName());
  }
  
  public void clear()
  {
    this$0.getSharedPreferences().edit().clear().commit();
    appID = null;
    appToken = null;
    regID = null;
    regSecret = null;
    deviceId = null;
    versionName = null;
    isValid = false;
    isPaused = false;
    envType = 1;
  }
  
  public void invalidate()
  {
    isValid = false;
    this$0.getSharedPreferences().edit().putBoolean("valid", isValid).commit();
  }
  
  public boolean isVaild()
  {
    return isVaild(appID, appToken);
  }
  
  public boolean isVaild(String paramString1, String paramString2)
  {
    return (TextUtils.equals(appID, paramString1)) && (TextUtils.equals(appToken, paramString2)) && (!TextUtils.isEmpty(regID)) && (!TextUtils.isEmpty(regSecret)) && (TextUtils.equals(deviceId, DeviceInfo.getSimpleDeviceId(AppInfoHolder.access$100(this$0))));
  }
  
  public void setEnvType(int paramInt)
  {
    envType = paramInt;
  }
  
  public void setIdAndToken(String paramString1, String paramString2, String paramString3)
  {
    appID = paramString1;
    appToken = paramString2;
    regResource = paramString3;
    paramString1 = this$0.getSharedPreferences().edit();
    paramString1.putString("appId", appID);
    paramString1.putString("appToken", paramString2);
    paramString1.putString("regResource", paramString3);
    paramString1.commit();
  }
  
  public void setPaused(boolean paramBoolean)
  {
    isPaused = paramBoolean;
  }
  
  public void setRegIdAndSecret(String paramString1, String paramString2)
  {
    regID = paramString1;
    regSecret = paramString2;
    deviceId = DeviceInfo.getSimpleDeviceId(AppInfoHolder.access$100(this$0));
    versionName = getVersionName();
    isValid = true;
    SharedPreferences.Editor localEditor = this$0.getSharedPreferences().edit();
    localEditor.putString("regId", paramString1);
    localEditor.putString("regSec", paramString2);
    localEditor.putString("devId", deviceId);
    localEditor.putString("vName", getVersionName());
    localEditor.putBoolean("valid", true);
    localEditor.commit();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mipush.sdk.AppInfoHolder.ClientInfoData
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */