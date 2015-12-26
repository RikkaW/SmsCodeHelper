package com.android.mms.update;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;
import miui.cloud.CloudManager;
import miui.util.CoderUtils;

public abstract class Request
{
  protected int mConnectTimeout;
  protected Context mContext;
  protected boolean mDecryptDownloadData;
  protected int mNetworkAccess = 0;
  private HashMap<String, String> mParamsMap;
  protected int mReadTimeout;
  protected String mRequestMethod;
  protected String mRequestUrl;
  protected boolean mRequireLogin;
  
  public Request(Context paramContext, String paramString)
  {
    mContext = paramContext;
    mRequestMethod = "GET";
    mRequestUrl = paramString;
    mDecryptDownloadData = true;
    mRequireLogin = false;
  }
  
  public static String decryptData(String paramString)
  {
    return CoderUtils.base6AesDecode(paramString, "d101b17c77ff93cs");
  }
  
  public static String encryptData(String paramString)
  {
    return CoderUtils.base64AesEncode(paramString, "d101b17c77ff93cs");
  }
  
  private static String genUrlSign(HashMap<String, String> paramHashMap, String paramString1, String paramString2)
  {
    if (paramHashMap.isEmpty()) {
      return "";
    }
    String[] arrayOfString = (String[])paramHashMap.keySet().toArray(new String[0]);
    Arrays.sort(arrayOfString);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString1);
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      paramString1 = arrayOfString[i];
      localStringBuilder.append(paramString1).append((String)paramHashMap.get(paramString1));
      i += 1;
    }
    localStringBuilder.append(paramString2);
    return CoderUtils.encodeSHA(localStringBuilder.toString()).toUpperCase(Locale.US);
  }
  
  private static HashMap<String, String> getEncryptedParam(HashMap<String, String> paramHashMap)
  {
    HashMap localHashMap = new HashMap();
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramHashMap.entrySet().iterator();
    if (localIterator.hasNext())
    {
      paramHashMap = (Map.Entry)localIterator.next();
      if (localStringBuilder.length() > 0) {
        localStringBuilder.append("&");
      }
      localStringBuilder.append((String)paramHashMap.getKey());
      localStringBuilder.append("=");
      if (TextUtils.isEmpty((CharSequence)paramHashMap.getValue())) {}
      for (paramHashMap = "";; paramHashMap = (String)paramHashMap.getValue())
      {
        localStringBuilder.append(URLEncoder.encode(paramHashMap));
        break;
      }
    }
    localHashMap.put("_encparam", encryptData(localStringBuilder.toString()));
    return localHashMap;
  }
  
  private static String getSignedUri(HashMap<String, String> paramHashMap, String paramString1, String paramString2)
  {
    String str = genUrlSign(paramHashMap, paramString1, paramString2);
    if (str.length() == 0) {
      return str;
    }
    paramString2 = new StringBuilder();
    paramString2.append("appkey=").append(paramString1).append("&sign=").append(str);
    paramHashMap = paramHashMap.entrySet().iterator();
    while (paramHashMap.hasNext())
    {
      paramString1 = (Map.Entry)paramHashMap.next();
      paramString2.append('&').append((String)paramString1.getKey()).append('=').append(URLEncoder.encode((String)paramString1.getValue()));
    }
    return paramString2.toString();
  }
  
  public static String getUserAgent()
  {
    return CloudManager.getUserAgent();
  }
  
  public static String signUrlParams(HashMap<String, String> paramHashMap)
  {
    return getSignedUri(getEncryptedParam(paramHashMap), "yellowpage", "77eb2e8a5755abd016c0d69ba74b219c");
  }
  
  public void addParam(String paramString1, String paramString2)
  {
    if (mParamsMap == null) {
      mParamsMap = new HashMap();
    }
    if (!mParamsMap.containsKey(paramString1)) {
      mParamsMap.put(paramString1, paramString2);
    }
  }
  
  protected HttpURLConnection getConn()
  {
    Object localObject5 = getRequestUrl();
    Log.d("Request", "The connection url is " + (String)localObject5);
    if (TextUtils.isEmpty((CharSequence)localObject5)) {
      return null;
    }
    Object localObject3 = null;
    Object localObject1 = null;
    try
    {
      localObject5 = (HttpURLConnection)new URL((String)localObject5).openConnection();
      localObject1 = localObject5;
      localObject3 = localObject5;
      if (mReadTimeout <= 0) {
        break label228;
      }
      localObject1 = localObject5;
      localObject3 = localObject5;
      ((HttpURLConnection)localObject5).setReadTimeout(mReadTimeout);
    }
    catch (MalformedURLException localMalformedURLException)
    {
      for (;;)
      {
        localMalformedURLException.printStackTrace();
        return (HttpURLConnection)localObject1;
        localObject1 = localObject5;
        localObject4 = localObject5;
        if (!Network.isWifiConnected(mContext)) {
          break;
        }
        localObject1 = localObject5;
        localObject4 = localObject5;
        ((HttpURLConnection)localObject5).setReadTimeout(10000);
      }
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      return (HttpURLConnection)localObject4;
    }
    localObject1 = localObject5;
    localObject3 = localObject5;
    if (mConnectTimeout > 0)
    {
      localObject1 = localObject5;
      localObject3 = localObject5;
    }
    for (int i = mConnectTimeout;; i = 10000)
    {
      localObject1 = localObject5;
      localObject3 = localObject5;
      ((HttpURLConnection)localObject5).setConnectTimeout(i);
      localObject1 = localObject5;
      localObject3 = localObject5;
      ((HttpURLConnection)localObject5).setRequestMethod(mRequestMethod);
      localObject1 = localObject5;
      localObject3 = localObject5;
      if (TextUtils.equals(mRequestMethod, "POST"))
      {
        localObject1 = localObject5;
        localObject3 = localObject5;
        ((HttpURLConnection)localObject5).setDoOutput(true);
        localObject1 = localObject5;
        localObject3 = localObject5;
        ((HttpURLConnection)localObject5).setUseCaches(false);
        localObject1 = localObject5;
        localObject3 = localObject5;
        ((HttpURLConnection)localObject5).setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      }
      localObject1 = localObject5;
      localObject3 = localObject5;
      ((HttpURLConnection)localObject5).setRequestProperty("User-Agent", getUserAgent());
      return (HttpURLConnection)localObject5;
      label228:
      Object localObject2 = localObject5;
      Object localObject4 = localObject5;
      ((HttpURLConnection)localObject5).setReadTimeout(30000);
      break;
    }
  }
  
  protected String getParams()
  {
    if (mParamsMap == null) {}
    for (HashMap localHashMap = new HashMap();; localHashMap = mParamsMap) {
      return signUrlParams(localHashMap);
    }
  }
  
  protected String getRequestUrl()
  {
    if (TextUtils.equals(mRequestMethod, "POST")) {
      return mRequestUrl;
    }
    String str = getParams();
    if (TextUtils.isEmpty(str)) {
      return mRequestUrl;
    }
    return String.format("%s?%s", new Object[] { mRequestUrl, str });
  }
  
  protected boolean isServerError(int paramInt)
  {
    return (paramInt == 400) || (paramInt == 401) || (paramInt == 403) || (paramInt == 406) || (paramInt / 100 == 5);
  }
  
  public void overwriteNetworkAccess(int paramInt)
  {
    mNetworkAccess = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.update.Request
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */