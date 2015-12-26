package com.xiaomi.mms.net;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.android.mms.MmsApp;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.mms.utils.EasyMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import miui.cloud.CloudManager;
import miui.telephony.exception.IllegalDeviceException;

public class CloudRequestFactory
{
  public static final String ACCOUNT_URL_BASE = XMPassport.URL_ACCOUNT_BASE;
  public static final String API_URL_V2_BASE = XMPassport.URL_ACCOUNT_API_V2_BASE;
  public static final String URL_LOGIN = ACCOUNT_URL_BASE + "/serviceLogin";
  
  protected static String appendParams(String paramString, Map<String, String> paramMap)
  {
    Object localObject = paramString;
    if (paramMap != null)
    {
      localObject = paramString;
      if (!paramMap.isEmpty())
      {
        paramString = Uri.parse(paramString).buildUpon();
        paramMap = paramMap.entrySet().iterator();
        while (paramMap.hasNext())
        {
          localObject = (Map.Entry)paramMap.next();
          paramString.appendQueryParameter((String)((Map.Entry)localObject).getKey(), (String)((Map.Entry)localObject).getValue());
        }
        localObject = paramString.build().toString();
      }
    }
    return (String)localObject;
  }
  
  public static SimpleRequest newGetIDCConfigRequest()
  {
    return new HttpGetSimpleRequest("http://api.account.xiaomi.com/pass/configuration?keys=idc");
  }
  
  public static SimpleRequest newGetUserIdRequest(String paramString)
  {
    HttpGetSimpleRequest localHttpGetSimpleRequest = new HttpGetSimpleRequest(appendParams("https://api.account.xiaomi.com/pass/v3/user@id", new EasyMap("type", "MXPH").easyPut("externalId", paramString)));
    paramString = null;
    try
    {
      String str = CloudManager.getHashedDeviceId(MmsApp.getApp());
      paramString = str;
    }
    catch (IllegalDeviceException localIllegalDeviceException)
    {
      for (;;)
      {
        localIllegalDeviceException.printStackTrace();
      }
    }
    if (!TextUtils.isEmpty(paramString)) {
      localHttpGetSimpleRequest.addCookie("deviceId", paramString);
    }
    return localHttpGetSimpleRequest;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.net.CloudRequestFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */