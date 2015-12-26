package com.amap.api.services.core;

import android.content.Context;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

public abstract class r<T, V>
  extends bt
{
  public T a;
  protected int b = 1;
  protected String c = "";
  public Context d;
  private int h = 1;
  
  public r(Context paramContext, T paramT)
  {
    a(paramContext, paramT);
  }
  
  private String a(String paramString)
  {
    Object localObject = paramString.split("&");
    Arrays.sort((Object[])localObject);
    StringBuffer localStringBuffer = new StringBuffer();
    int j = localObject.length;
    int i = 0;
    while (i < j)
    {
      localStringBuffer.append(d(localObject[i]));
      localStringBuffer.append("&");
      i += 1;
    }
    localObject = localStringBuffer.toString();
    if (((String)localObject).length() > 1) {
      return (String)((String)localObject).subSequence(0, ((String)localObject).length() - 1);
    }
    return paramString;
  }
  
  private void a(Context paramContext, T paramT)
  {
    d = paramContext;
    a = paramT;
    b = 1;
    d(ServiceSettings.getInstance().getSoTimeOut());
    c(ServiceSettings.getInstance().getConnectionTimeOut());
  }
  
  private V b(byte[] paramArrayOfByte)
  {
    return (V)a(paramArrayOfByte);
  }
  
  private V f()
  {
    int i = 0;
    Object localObject1 = null;
    Object localObject5;
    Object localObject6;
    if (i < b)
    {
      localObject5 = localObject1;
      localObject6 = localObject1;
    }
    for (;;)
    {
      try
      {
        j = ServiceSettings.getInstance().getProtocol();
        localObject5 = localObject1;
        localObject6 = localObject1;
        Object localObject3 = bs.a(false);
        localObject5 = localObject1;
        localObject6 = localObject1;
        a(ac.a(d));
        if (j == 1)
        {
          localObject5 = localObject1;
          localObject6 = localObject1;
          localObject3 = ((bs)localObject3).a(this);
          localObject5 = localObject1;
          localObject6 = localObject1;
          localObject1 = b((byte[])localObject3);
          localObject5 = localObject1;
          localObject6 = localObject1;
          j = b;
          i = j;
          break;
        }
        if (j != 2) {
          break label264;
        }
        localObject5 = localObject1;
        localObject6 = localObject1;
        localObject3 = ((bs)localObject3).b(this);
        continue;
      }
      catch (AMapException localAMapException)
      {
        d.a(localAMapException, "ProtocalHandler", "getDataMayThrowAMapException");
        int j = i + 1;
        i = j;
        localObject1 = localObject5;
        if (j < b) {
          break;
        }
        throw new AMapException(localAMapException.getErrorMessage());
      }
      catch (v localv)
      {
        d.a(localv, "ProtocalHandler", "getDataMayThrowAMapCoreException");
        i += 1;
        Object localObject2;
        if (i < b) {
          try
          {
            Thread.sleep(h * 1000);
            localObject2 = localObject6;
          }
          catch (InterruptedException localInterruptedException)
          {
            d.a((Throwable)localObject2, "ProtocalHandler", "getDataMayThrowInterruptedException");
            throw new AMapException(((v)localObject2).getMessage());
          }
        }
        h();
        throw new AMapException(((v)localObject2).a());
      }
      catch (Throwable localThrowable)
      {
        d.a(localThrowable, "ProtocalHandler", "getDataMayThrowAMapCoreException");
        throw new AMapException("未知的错误");
      }
      return localThrowable;
      label264:
      Object localObject4 = null;
    }
  }
  
  protected V a(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = new String(paramArrayOfByte, "utf-8");
      if ((paramArrayOfByte == null) || (paramArrayOfByte.equals(""))) {
        return null;
      }
    }
    catch (Exception paramArrayOfByte)
    {
      for (;;)
      {
        d.a(paramArrayOfByte, "ProtocalHandler", "loadData");
        paramArrayOfByte = null;
      }
      d.b(paramArrayOfByte);
    }
    return (V)b(paramArrayOfByte);
  }
  
  public abstract String a_();
  
  public abstract V b(String paramString);
  
  public String c(String paramString)
  {
    if (paramString == null) {
      return paramString;
    }
    try
    {
      paramString = URLEncoder.encode(paramString, "utf-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      d.a(paramString, "ProtocalHandler", "strEncoderUnsupportedEncodingException");
      return new String();
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        d.a(paramString, "ProtocalHandler", "strEncoderException");
      }
    }
  }
  
  public Map<String, String> c_()
  {
    return null;
  }
  
  protected String d(String paramString)
  {
    if (paramString == null) {
      return paramString;
    }
    try
    {
      paramString = URLDecoder.decode(paramString, "utf-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      d.a(paramString, "ProtocalHandler", "strReEncoder");
      return new String();
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        d.a(paramString, "ProtocalHandler", "strReEncoderException");
      }
    }
  }
  
  public Map<String, String> d_()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("Content-Type", "application/x-www-form-urlencoded");
    localHashMap.put("Accept-Encoding", "gzip");
    localHashMap.put("User-Agent", "AMAP SDK Android Search 2.5.0");
    localHashMap.put("X-INFO", y.a(d, l.a, null));
    localHashMap.put("ia", "1");
    localHashMap.put("ec", "1");
    localHashMap.put("key", w.f(d));
    return localHashMap;
  }
  
  public HttpEntity e()
  {
    try
    {
      String str = a_();
      Object localObject = a(str);
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append(str);
      str = y.a();
      localStringBuffer.append("&ts=" + str);
      localStringBuffer.append("&scode=" + y.a(d, str, (String)localObject));
      localObject = new StringEntity(localStringBuffer.toString());
      return (HttpEntity)localObject;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      d.a(localUnsupportedEncodingException, "ProtocalHandler", "getEntity");
    }
    return null;
  }
  
  public V g()
  {
    if (a != null) {
      return (V)f();
    }
    return null;
  }
  
  protected V h()
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.r
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */