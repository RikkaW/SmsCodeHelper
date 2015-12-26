package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.BuildSettings;
import com.xiaomi.channel.commonutils.network.HttpResponse;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;

public class MIPushAccountUtils
{
  private static MIPushAccount sAccount;
  
  public static void clearAccount(Context paramContext)
  {
    paramContext.getSharedPreferences("mipush_account", 0).edit().clear().commit();
    sAccount = null;
  }
  
  public static String getAccountURL()
  {
    if (BuildSettings.IsOneBoxBuild()) {
      return "http://10.237.12.17:9085/pass/register";
    }
    StringBuilder localStringBuilder = new StringBuilder().append("https://");
    if (BuildSettings.IsSandBoxBuild()) {}
    for (String str = "sandbox.xmpush.xiaomi.com";; str = "register.xmpush.xiaomi.com") {
      return str + "/pass/register";
    }
  }
  
  public static MIPushAccount getMIPushAccount(Context paramContext)
  {
    Object localObject3 = null;
    for (;;)
    {
      try
      {
        if (sAccount != null)
        {
          localObject1 = sAccount;
          return (MIPushAccount)localObject1;
        }
        SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("mipush_account", 0);
        String str1 = localSharedPreferences.getString("uuid", null);
        String str2 = localSharedPreferences.getString("token", null);
        String str3 = localSharedPreferences.getString("security", null);
        String str4 = localSharedPreferences.getString("app_id", null);
        String str5 = localSharedPreferences.getString("app_token", null);
        String str6 = localSharedPreferences.getString("package_name", null);
        localObject1 = localSharedPreferences.getString("device_id", null);
        int i = localSharedPreferences.getInt("env_type", 1);
        Object localObject2 = localObject1;
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          localObject2 = localObject1;
          if (((String)localObject1).startsWith("a-"))
          {
            localObject2 = DeviceInfo.getSimpleDeviceId(paramContext);
            localSharedPreferences.edit().putString("device_id", (String)localObject2).commit();
          }
        }
        localObject1 = localObject3;
        if (TextUtils.isEmpty(str1)) {
          continue;
        }
        localObject1 = localObject3;
        if (TextUtils.isEmpty(str2)) {
          continue;
        }
        localObject1 = localObject3;
        if (TextUtils.isEmpty(str3)) {
          continue;
        }
        localObject1 = DeviceInfo.getSimpleDeviceId(paramContext);
        if ((!"com.xiaomi.xmsf".equals(paramContext.getPackageName())) && (!TextUtils.isEmpty((CharSequence)localObject1)) && (!TextUtils.isEmpty((CharSequence)localObject2)) && (!((String)localObject2).equals(localObject1)))
        {
          MyLog.e("erase the old account.");
          clearAccount(paramContext);
          localObject1 = localObject3;
          continue;
        }
        sAccount = new MIPushAccount(str1, str2, str3, str4, str5, str6, i);
      }
      finally {}
      Object localObject1 = sAccount;
    }
  }
  
  private static boolean isMIUIPush(Context paramContext)
  {
    return paramContext.getPackageName().equals("com.xiaomi.xmsf");
  }
  
  private static void persist(Context paramContext, MIPushAccount paramMIPushAccount)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("mipush_account", 0).edit();
    localEditor.putString("uuid", account);
    localEditor.putString("security", security);
    localEditor.putString("token", token);
    localEditor.putString("app_id", appId);
    localEditor.putString("package_name", packageName);
    localEditor.putString("app_token", appToken);
    localEditor.putString("device_id", DeviceInfo.getSimpleDeviceId(paramContext));
    localEditor.putInt("env_type", envType);
    localEditor.commit();
  }
  
  public static MIPushAccount register(Context paramContext, String paramString1, String paramString2, String paramString3)
    throws IOException, JSONException
  {
    for (;;)
    {
      Object localObject1;
      try
      {
        Object localObject3 = new TreeMap();
        ((Map)localObject3).put("devid", DeviceInfo.getDeviceId(paramContext));
        if (isMIUIPush(paramContext))
        {
          paramString2 = "1000271";
          if (!isMIUIPush(paramContext)) {
            continue;
          }
          paramString3 = "420100086271";
          if (!isMIUIPush(paramContext)) {
            continue;
          }
          paramString1 = "com.xiaomi.xmsf";
          ((Map)localObject3).put("appid", paramString2);
          ((Map)localObject3).put("apptoken", paramString3);
          localObject1 = null;
        }
        try
        {
          localObject2 = paramContext.getPackageManager().getPackageInfo(paramString1, 16384);
          localObject1 = localObject2;
        }
        catch (Exception localException)
        {
          Object localObject2;
          MyLog.e(localException);
          continue;
        }
        if (localObject1 != null)
        {
          localObject1 = String.valueOf(versionCode);
          ((Map)localObject3).put("appversion", localObject1);
          ((Map)localObject3).put("'sdkversion", Integer.toString(7));
          ((Map)localObject3).put("packagename", paramString1);
          ((Map)localObject3).put("model", Build.MODEL);
          ((Map)localObject3).put("imei_md5", XMStringUtils.getMd5Digest(DeviceInfo.blockingGetIMEI(paramContext)));
          ((Map)localObject3).put("os", Build.VERSION.RELEASE + "-" + Build.VERSION.INCREMENTAL);
          localObject2 = Network.doHttpPost(paramContext, getAccountURL(), (Map)localObject3);
          localObject1 = "";
          if (localObject2 != null) {
            localObject1 = ((HttpResponse)localObject2).getResponseString();
          }
          if (TextUtils.isEmpty((CharSequence)localObject1)) {
            break label437;
          }
          localObject2 = new JSONObject((String)localObject1);
          if (((JSONObject)localObject2).getInt("code") != 0) {
            break label412;
          }
          localObject3 = ((JSONObject)localObject2).getJSONObject("data");
          localObject1 = ((JSONObject)localObject3).getString("ssecurity");
          localObject2 = ((JSONObject)localObject3).getString("token");
          localObject3 = ((JSONObject)localObject3).getString("userId");
          paramString1 = new MIPushAccount((String)localObject3 + "@xiaomi.com/an" + XMStringUtils.generateRandomString(6), (String)localObject2, (String)localObject1, paramString2, paramString3, paramString1, BuildSettings.getEnvType());
          persist(paramContext, paramString1);
          sAccount = paramString1;
          paramContext = paramString1;
          return paramContext;
          continue;
          continue;
          continue;
        }
        localObject1 = "0";
      }
      finally {}
      continue;
      label412:
      MIPushClientManager.notifyRegisterError(paramContext, localException.getInt("code"), localException.optString("description"));
      MyLog.warn((String)localObject1);
      label437:
      paramContext = null;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.MIPushAccountUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */