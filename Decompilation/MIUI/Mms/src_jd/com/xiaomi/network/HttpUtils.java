package com.xiaomi.network;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.network.NameValuePair;
import com.xiaomi.channel.commonutils.network.Network;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class HttpUtils
{
  public static String get(Context paramContext, String paramString, List<NameValuePair> paramList)
  {
    return httpRequest(paramContext, paramString, paramList, new DefaultHttpGetProcessor(), true);
  }
  
  static int getHttpGetTxtTraffic(int paramInt1, int paramInt2)
  {
    return (paramInt2 + 243) / 1448 * 132 + 1080 + paramInt1 + paramInt2;
  }
  
  static int getHttpPostTxtTraffic(int paramInt1, int paramInt2, int paramInt3)
  {
    return (paramInt2 + 200) / 1448 * 132 + 1011 + paramInt2 + paramInt1 + paramInt3;
  }
  
  static int getPostDataLength(List<NameValuePair> paramList)
  {
    int i = 0;
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      NameValuePair localNameValuePair = (NameValuePair)paramList.next();
      int j = i;
      if (!TextUtils.isEmpty(localNameValuePair.getName())) {
        j = i + localNameValuePair.getName().length();
      }
      i = j;
      if (!TextUtils.isEmpty(localNameValuePair.getValue())) {
        i = j + localNameValuePair.getValue().length();
      }
    }
    return i * 2;
  }
  
  static int getStringUTF8Length(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return 0;
    }
    try
    {
      int i = paramString.getBytes("UTF-8").length;
      return i;
    }
    catch (UnsupportedEncodingException paramString) {}
    return 0;
  }
  
  private static int getTraffic(HttpProcessor paramHttpProcessor, String paramString1, List<NameValuePair> paramList, String paramString2)
  {
    if (paramHttpProcessor.getRequestType() == 1) {
      return getHttpGetTxtTraffic(paramString1.length(), getStringUTF8Length(paramString2));
    }
    if (paramHttpProcessor.getRequestType() == 2)
    {
      int i = getPostDataLength(paramList);
      return getHttpPostTxtTraffic(paramString1.length(), i, getStringUTF8Length(paramString2));
    }
    return -1;
  }
  
  public static String httpRequest(Context paramContext, String paramString, List<NameValuePair> paramList, HttpProcessor paramHttpProcessor, boolean paramBoolean)
  {
    if (Network.hasNetwork(paramContext)) {}
    for (;;)
    {
      ArrayList localArrayList;
      Object localObject2;
      Object localObject1;
      Object localObject3;
      String str;
      long l;
      try
      {
        localArrayList = new ArrayList();
        localObject2 = null;
        localObject1 = localArrayList;
        if (paramBoolean)
        {
          localObject3 = HostManager.getInstance().getFallbacksByURL(paramString);
          localObject2 = localObject3;
          localObject1 = localArrayList;
          if (localObject3 != null)
          {
            localObject1 = ((Fallback)localObject3).getUrls(paramString);
            localObject2 = localObject3;
          }
        }
        if (!((ArrayList)localObject1).contains(paramString)) {
          ((ArrayList)localObject1).add(paramString);
        }
        paramString = null;
        Iterator localIterator = ((ArrayList)localObject1).iterator();
        localObject3 = paramString;
        if (!localIterator.hasNext()) {
          break label305;
        }
        str = (String)localIterator.next();
        if (paramList == null) {
          break label308;
        }
        localArrayList = new ArrayList(paramList);
        l = System.currentTimeMillis();
        localObject3 = paramString;
        try
        {
          if (!paramHttpProcessor.prepare(paramContext, str, localArrayList)) {
            return paramString;
          }
          localObject3 = paramString;
          localObject1 = paramHttpProcessor.visit(paramContext, str, localArrayList);
          localObject3 = localObject1;
          if (TextUtils.isEmpty((CharSequence)localObject1)) {
            break label262;
          }
          localObject3 = localObject1;
          if (localObject2 == null) {
            break label305;
          }
          localObject3 = localObject1;
          ((Fallback)localObject2).succeedUrl(str, System.currentTimeMillis() - l, getTraffic(paramHttpProcessor, str, localArrayList, (String)localObject1));
          return (String)localObject1;
        }
        catch (IOException paramString)
        {
          if (localObject2 != null) {
            ((Fallback)localObject2).failedUrl(str, System.currentTimeMillis() - l, getTraffic(paramHttpProcessor, str, localArrayList, (String)localObject3), paramString);
          }
          paramString.printStackTrace();
          paramString = (String)localObject3;
        }
        continue;
        return null;
      }
      catch (MalformedURLException paramContext)
      {
        paramContext.printStackTrace();
      }
      label262:
      paramString = (String)localObject1;
      if (localObject2 != null)
      {
        localObject3 = localObject1;
        ((Fallback)localObject2).failedUrl(str, System.currentTimeMillis() - l, getTraffic(paramHttpProcessor, str, localArrayList, (String)localObject1), null);
        paramString = (String)localObject1;
        continue;
        label305:
        return (String)localObject3;
        label308:
        localArrayList = null;
      }
    }
  }
  
  public static class DefaultHttpGetProcessor
    extends HttpProcessor
  {
    public DefaultHttpGetProcessor()
    {
      super();
    }
    
    public String visit(Context paramContext, String paramString, List<NameValuePair> paramList)
      throws IOException
    {
      if (paramList == null) {
        return Network.downloadXml(paramContext, new URL(paramString));
      }
      paramString = Uri.parse(paramString).buildUpon();
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        NameValuePair localNameValuePair = (NameValuePair)paramList.next();
        paramString.appendQueryParameter(localNameValuePair.getName(), localNameValuePair.getValue());
      }
      return Network.downloadXml(paramContext, new URL(paramString.toString()));
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.network.HttpUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */