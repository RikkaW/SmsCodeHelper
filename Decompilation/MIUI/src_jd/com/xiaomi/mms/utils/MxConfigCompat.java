package com.xiaomi.mms.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.xiaomi.micloudsdk.CloudConfig;
import com.xiaomi.mms.mx.common.AsyncTaskUtils;
import com.xiaomi.mms.net.CloudRequestExecutor;
import com.xiaomi.mms.net.exception.InvalidResponseException;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.IOException;
import miui.util.XMAccountUtils;
import org.json.JSONException;
import org.json.JSONObject;

public final class MxConfigCompat
{
  private static final String MXV1_BASE_URL = CloudConfig.URL_RICH_MEDIA_BASE + "/mic/file/v2/user/%s/full";
  private static volatile long sMainlandMaxUserId = -1L;
  private static volatile long sMainlandMinUserId = -1L;
  
  private static void asyncRequest(Context paramContext)
  {
    AsyncTaskUtils.exe(2, new AsyncTask()
    {
      protected JSONObject doInBackground(Void... paramAnonymousVarArgs)
      {
        try
        {
          paramAnonymousVarArgs = MxConfigCompat.getIDCConfig(val$context);
          return paramAnonymousVarArgs;
        }
        catch (IOException paramAnonymousVarArgs)
        {
          MyLog.e("MxConfigCompat", "get mx config info from network failed, beause " + paramAnonymousVarArgs.toString());
          return null;
        }
        catch (InvalidResponseException paramAnonymousVarArgs)
        {
          for (;;)
          {
            MyLog.e("MxConfigCompat", "get mx config info from network failed, beause " + paramAnonymousVarArgs.toString());
          }
        }
      }
      
      protected void onPostExecute(JSONObject paramAnonymousJSONObject)
      {
        super.onPostExecute(paramAnonymousJSONObject);
        if (paramAnonymousJSONObject != null)
        {
          MxConfigCompat.saveIDCConfigInfo(val$context, paramAnonymousJSONObject);
          MxConfigCompat.readDomesticIDCConfigInfo(val$context);
        }
      }
    }, new Void[0]);
  }
  
  private static JSONObject getIDCConfig(Context paramContext)
    throws IOException, InvalidResponseException
  {
    return CloudRequestExecutor.getIDCConfig();
  }
  
  private static String getMaxUserIdByTag(JSONObject paramJSONObject, String paramString)
    throws JSONException
  {
    return getMxConfigUserId(paramJSONObject, paramString, "userId.max");
  }
  
  private static String getMinUserIdByTag(JSONObject paramJSONObject, String paramString)
    throws JSONException
  {
    return getMxConfigUserId(paramJSONObject, paramString, "userId.min");
  }
  
  private static String getMxConfigUserId(JSONObject paramJSONObject, String paramString1, String paramString2)
  {
    if (paramJSONObject != null)
    {
      paramJSONObject = paramJSONObject.optJSONObject(paramString1);
      if (paramJSONObject != null) {
        return paramJSONObject.optString(paramString2);
      }
    }
    return null;
  }
  
  public static String getMxV1Sid(Context paramContext, String paramString)
  {
    String str2 = getMxV1Sid(paramString);
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
    {
      requestAndSaveMxConfig(paramContext);
      str1 = getMxV1Sid(paramString);
    }
    return str1;
  }
  
  private static String getMxV1Sid(String paramString)
  {
    if (hasCached())
    {
      if (isDomesticMiid(paramString)) {
        return "micfile";
      }
      return "micfile";
    }
    return null;
  }
  
  public static String getMxV2RichMediaUrl(Context paramContext, String paramString)
  {
    String str2 = getMxV2RichMediaUrl(paramString);
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
    {
      requestAndSaveMxConfig(paramContext);
      str1 = getMxV2RichMediaUrl(paramString);
    }
    return str1;
  }
  
  private static String getMxV2RichMediaUrl(String paramString)
  {
    if (hasCached())
    {
      if (isDomesticMiid(paramString)) {
        return "http://mixin.api.files.xiaomi.com/mixin/v2/file/user/%s";
      }
      return "http://us.mixin.api.files.xiaomi.com/mixin/v2/file/user/%s";
    }
    return null;
  }
  
  public static String getMxV2Sid(Context paramContext, String paramString)
  {
    String str2 = getMxV2Sid(paramString);
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
    {
      requestAndSaveMxConfig(paramContext);
      str1 = getMxV2Sid(paramString);
    }
    return str1;
  }
  
  private static String getMxV2Sid(String paramString)
  {
    if (hasCached())
    {
      if (isDomesticMiid(paramString)) {
        return "mixin_mfs";
      }
      return "mixin_mfs_us";
    }
    return null;
  }
  
  public static String getOtherIDCMxV1Sid(Context paramContext, String paramString)
  {
    String str2 = getMxV1Sid(paramString);
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
    {
      requestAndSaveMxConfig(paramContext);
      str1 = getMxV1Sid(paramString);
    }
    return str1;
  }
  
  public static String getOtherIDCMxV2RichMediaUrl(Context paramContext, String paramString)
  {
    String str2 = getOtherIDCMxV2RichMediaUrl(paramString);
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
    {
      requestAndSaveMxConfig(paramContext);
      str1 = getOtherIDCMxV2RichMediaUrl(paramString);
    }
    return str1;
  }
  
  private static String getOtherIDCMxV2RichMediaUrl(String paramString)
  {
    if (hasCached())
    {
      if (isDomesticMiid(paramString)) {
        return "http://us.mixin.api.files.xiaomi.com/mixin/v2/file/user/%s";
      }
      return "http://mixin.api.files.xiaomi.com/mixin/v2/file/user/%s";
    }
    return null;
  }
  
  public static String getOtherIDCMxV2Sid(Context paramContext, String paramString)
  {
    String str2 = getOtherIDCMxV2Sid(paramString);
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
    {
      requestAndSaveMxConfig(paramContext);
      str1 = getOtherIDCMxV2Sid(paramString);
    }
    return str1;
  }
  
  private static String getOtherIDCMxV2Sid(String paramString)
  {
    if (hasCached())
    {
      if (isDomesticMiid(paramString)) {
        return "mixin_mfs_us";
      }
      return "mixin_mfs";
    }
    return null;
  }
  
  private static boolean hasCached()
  {
    return (sMainlandMaxUserId > 0L) && (sMainlandMinUserId >= 0L);
  }
  
  public static void init(Context paramContext)
  {
    readDomesticIDCConfigInfo(paramContext);
    if (!hasCached()) {
      asyncRequest(paramContext);
    }
  }
  
  public static boolean isDomesticMiid(String paramString)
  {
    boolean bool2 = false;
    paramString = XMAccountUtils.trimDomainSuffix(paramString);
    boolean bool1 = bool2;
    try
    {
      if (!TextUtils.isEmpty(paramString))
      {
        bool1 = bool2;
        if (XMAccountUtils.isNumeric(paramString))
        {
          long l1 = Long.valueOf(paramString).longValue();
          bool1 = bool2;
          if (sMainlandMinUserId <= l1)
          {
            long l2 = sMainlandMaxUserId;
            bool1 = bool2;
            if (l1 < l2) {
              bool1 = true;
            }
          }
        }
      }
      return bool1;
    }
    catch (NumberFormatException paramString)
    {
      MyLog.e("MxConfigCompat", paramString.toString());
    }
    return false;
  }
  
  public static boolean isMxV2GeneralRichmedia(String paramString)
  {
    return paramString.startsWith("s2t.");
  }
  
  public static boolean isMxV2PublicRichmedia(String paramString)
  {
    return paramString.startsWith("s2p.");
  }
  
  public static boolean isMxV2Richmedia(String paramString)
  {
    return (isMxV2PublicRichmedia(paramString)) || (isMxV2GeneralRichmedia(paramString));
  }
  
  private static void readDomesticIDCConfigInfo(Context paramContext)
  {
    paramContext = PrefUtils.getString(paramContext, "mx_config_idc_pref");
    if (!TextUtils.isEmpty(paramContext)) {}
    try
    {
      Object localObject = new JSONObject(paramContext);
      paramContext = getMaxUserIdByTag((JSONObject)localObject, "China");
      localObject = getMinUserIdByTag((JSONObject)localObject, "China");
      if (XMAccountUtils.isNumeric(paramContext)) {
        sMainlandMaxUserId = Long.valueOf(paramContext).longValue();
      }
      if (XMAccountUtils.isNumeric((String)localObject)) {
        sMainlandMinUserId = Long.valueOf((String)localObject).longValue();
      }
      return;
    }
    catch (JSONException paramContext)
    {
      MyLog.e("MxConfigCompat", paramContext.toString());
      return;
    }
    catch (NumberFormatException paramContext)
    {
      MyLog.e("MxConfigCompat", paramContext.toString());
    }
  }
  
  private static void requestAndSaveMxConfig(Context paramContext)
  {
    try
    {
      saveIDCConfigInfo(paramContext, getIDCConfig(paramContext));
      readDomesticIDCConfigInfo(paramContext);
      return;
    }
    catch (IOException paramContext)
    {
      MyLog.e("MxConfigCompat", "blocking get rich media upload url failed " + paramContext.toString());
      return;
    }
    catch (InvalidResponseException paramContext)
    {
      MyLog.e("MxConfigCompat", "blocking get rich media upload url failed " + paramContext.toString());
    }
  }
  
  private static void saveIDCConfigInfo(Context paramContext, JSONObject paramJSONObject)
  {
    if (paramJSONObject != null) {}
    try
    {
      paramJSONObject = paramJSONObject.getJSONObject("idc");
      if (paramJSONObject != null) {
        PrefUtils.saveString(paramContext, "mx_config_idc_pref", paramJSONObject.toString());
      }
      return;
    }
    catch (JSONException paramContext)
    {
      MyLog.e("MxConfigCompat", paramContext.toString());
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.MxConfigCompat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */