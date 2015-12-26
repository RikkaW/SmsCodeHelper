package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.DeviceInfo;

public class AppInfoHolder
{
  private static AppInfoHolder sInstance;
  private Context mContext;
  private ClientInfoData mInfoData;
  
  private AppInfoHolder(Context paramContext)
  {
    mContext = paramContext;
    init();
  }
  
  public static AppInfoHolder getInstance(Context paramContext)
  {
    if (sInstance == null) {
      sInstance = new AppInfoHolder(paramContext);
    }
    return sInstance;
  }
  
  public static String getVersionName(Context paramContext, String paramString)
  {
    Object localObject = null;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 16384);
      if (paramContext != null) {
        return versionName;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        MyLog.e(paramContext);
        paramContext = (Context)localObject;
      }
    }
    return "1.0";
  }
  
  private void init()
  {
    mInfoData = new ClientInfoData(null);
    SharedPreferences localSharedPreferences = getSharedPreferences();
    mInfoData.appID = localSharedPreferences.getString("appId", null);
    mInfoData.appToken = localSharedPreferences.getString("appToken", null);
    mInfoData.regID = localSharedPreferences.getString("regId", null);
    mInfoData.regSecret = localSharedPreferences.getString("regSec", null);
    mInfoData.deviceId = localSharedPreferences.getString("devId", null);
    if ((!TextUtils.isEmpty(mInfoData.deviceId)) && (mInfoData.deviceId.startsWith("a-")))
    {
      mInfoData.deviceId = DeviceInfo.getSimpleDeviceId(mContext);
      localSharedPreferences.edit().putString("devId", mInfoData.deviceId).commit();
    }
    mInfoData.versionName = localSharedPreferences.getString("vName", null);
    mInfoData.isValid = localSharedPreferences.getBoolean("valid", true);
    mInfoData.isPaused = localSharedPreferences.getBoolean("paused", false);
    mInfoData.envType = localSharedPreferences.getInt("envType", 1);
    mInfoData.regResource = localSharedPreferences.getString("regResource", null);
  }
  
  public boolean appRegistered()
  {
    return mInfoData.isVaild();
  }
  
  public boolean appRegistered(String paramString1, String paramString2)
  {
    return mInfoData.isVaild(paramString1, paramString2);
  }
  
  public boolean checkAppInfo()
  {
    if (!mInfoData.isVaild())
    {
      MyLog.warn("Don't send message before initialization succeeded!");
      return false;
    }
    return true;
  }
  
  public boolean checkVersionNameChanged()
  {
    return !TextUtils.equals(getVersionName(mContext, mContext.getPackageName()), mInfoData.versionName);
  }
  
  public void clear()
  {
    mInfoData.clear();
  }
  
  public String getAppID()
  {
    return mInfoData.appID;
  }
  
  public String getAppToken()
  {
    return mInfoData.appToken;
  }
  
  public int getEnvType()
  {
    return mInfoData.envType;
  }
  
  public String getRegID()
  {
    return mInfoData.regID;
  }
  
  public String getRegResource()
  {
    return mInfoData.regResource;
  }
  
  public String getRegSecret()
  {
    return mInfoData.regSecret;
  }
  
  public SharedPreferences getSharedPreferences()
  {
    return mContext.getSharedPreferences("mipush", 0);
  }
  
  public void invalidate()
  {
    mInfoData.invalidate();
  }
  
  public boolean invalidated()
  {
    return !mInfoData.isValid;
  }
  
  public boolean isPaused()
  {
    return mInfoData.isPaused;
  }
  
  public void putAppIDAndToken(String paramString1, String paramString2, String paramString3)
  {
    mInfoData.setIdAndToken(paramString1, paramString2, paramString3);
  }
  
  public void putRegIDAndSecret(String paramString1, String paramString2)
  {
    mInfoData.setRegIdAndSecret(paramString1, paramString2);
  }
  
  public void setEnvType(int paramInt)
  {
    mInfoData.setEnvType(paramInt);
    getSharedPreferences().edit().putInt("envType", paramInt).commit();
  }
  
  public void setPaused(boolean paramBoolean)
  {
    mInfoData.setPaused(paramBoolean);
    getSharedPreferences().edit().putBoolean("paused", paramBoolean).commit();
  }
  
  public void updateVersionName(String paramString)
  {
    SharedPreferences.Editor localEditor = getSharedPreferences().edit();
    localEditor.putString("vName", paramString);
    localEditor.commit();
    mInfoData.versionName = paramString;
  }
  
  private class ClientInfoData
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
    
    private ClientInfoData() {}
    
    private String getVersionName()
    {
      return AppInfoHolder.getVersionName(mContext, mContext.getPackageName());
    }
    
    public void clear()
    {
      getSharedPreferences().edit().clear().commit();
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
      getSharedPreferences().edit().putBoolean("valid", isValid).commit();
    }
    
    public boolean isVaild()
    {
      return isVaild(appID, appToken);
    }
    
    public boolean isVaild(String paramString1, String paramString2)
    {
      return (TextUtils.equals(appID, paramString1)) && (TextUtils.equals(appToken, paramString2)) && (!TextUtils.isEmpty(regID)) && (!TextUtils.isEmpty(regSecret)) && (TextUtils.equals(deviceId, DeviceInfo.getSimpleDeviceId(mContext)));
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
      paramString1 = getSharedPreferences().edit();
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
      deviceId = DeviceInfo.getSimpleDeviceId(mContext);
      versionName = getVersionName();
      isValid = true;
      SharedPreferences.Editor localEditor = getSharedPreferences().edit();
      localEditor.putString("regId", paramString1);
      localEditor.putString("regSec", paramString2);
      localEditor.putString("devId", deviceId);
      localEditor.putString("vName", getVersionName());
      localEditor.putBoolean("valid", true);
      localEditor.commit();
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mipush.sdk.AppInfoHolder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */