package com.amap.api.mapcore2d;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class dc
{
  public static String a()
  {
    try
    {
      str1 = String.valueOf(System.currentTimeMillis());
      int i;
      String str2;
      ed.a(localThrowable1, "CInfo", "getTS");
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        i = str1.length();
        str2 = str1.substring(0, i - 2) + "1" + str1.substring(i - 1);
        return str2;
      }
      catch (Throwable localThrowable2)
      {
        String str1;
        for (;;) {}
      }
      localThrowable1 = localThrowable1;
      str1 = null;
    }
    localThrowable1.printStackTrace();
    return str1;
  }
  
  public static String a(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      localStringBuilder.append("\"key\":\"").append(da.f(paramContext)).append("\",\"ct\":\"android\",\"ime\":\"").append(dd.m(paramContext)).append("\",\"pkg\":\"").append(da.b(paramContext)).append("\",\"mod\":\"").append(Build.MODEL).append("\",\"apn\":\"").append(da.a(paramContext)).append("\",\"apv\":\"").append(da.c(paramContext)).append("\",\"sv\":\"").append(Build.VERSION.RELEASE).append("\",");
      return localStringBuilder.toString();
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        ed.a(paramContext, "CInfo", "getPublicJSONInfo");
        paramContext.printStackTrace();
      }
    }
  }
  
  public static String a(Context paramContext, dh paramdh)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      String str = dd.q(paramContext);
      localStringBuilder.append("\"sim\":\"").append(str).append("\",\"av\":\"").append(a).append("\",\"pro\":\"").append(c).append("\",\"ed\":\"").append(paramdh.d()).append("\",\"nt\":\"").append(dd.k(paramContext)).append("\",\"np\":\"").append(dd.p(paramContext)).append("\",\"mnc\":\"").append(dd.e(paramContext)).append("\",\"lnt\":\"").append(dd.f(paramContext)).append("\",\"ant\":\"").append(dd.h(paramContext)).append("\"");
      return localStringBuilder.toString();
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public static String a(Context paramContext, dh paramdh, Map<String, String> paramMap)
  {
    try
    {
      paramdh = b(paramContext, paramdh, paramMap);
      if ("".equals(paramdh)) {
        return null;
      }
      paramContext = b(paramContext, paramdh.getBytes("utf-8"));
      return paramContext;
    }
    catch (UnsupportedEncodingException paramContext)
    {
      ed.a(paramContext, "CInfo", "rsaInfo");
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = da.d(paramContext);
      paramContext = df.a(paramContext + ":" + paramString1.substring(0, paramString1.length() - 3) + ":" + paramString2);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      ed.a(paramContext, "CInfo", "Scode");
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String a(Context paramContext, byte[] paramArrayOfByte)
  {
    try
    {
      paramContext = c(paramContext, paramArrayOfByte);
      return paramContext;
    }
    catch (InvalidKeyException paramContext)
    {
      paramContext.printStackTrace();
      return "";
    }
    catch (NoSuchAlgorithmException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    catch (NoSuchPaddingException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    catch (IllegalBlockSizeException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    catch (BadPaddingException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    catch (InvalidKeySpecException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    catch (CertificateException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    catch (IOException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  private static String b(Context paramContext, dh paramdh, Map<String, String> paramMap)
  {
    localStringBuilder = new StringBuilder();
    try
    {
      String str = dd.m(paramContext);
      localStringBuilder.append("ct=android");
      localStringBuilder.append("&ime=").append(str);
      localStringBuilder.append("&pkg=").append(da.b(paramContext));
      localStringBuilder.append("&mod=");
      localStringBuilder.append(Build.MODEL);
      localStringBuilder.append("&apn=").append(da.a(paramContext));
      localStringBuilder.append("&apv=").append(da.c(paramContext));
      localStringBuilder.append("&sv=");
      localStringBuilder.append(Build.VERSION.RELEASE);
      str = dd.n(paramContext);
      localStringBuilder.append("&sim=").append(str);
      localStringBuilder.append("&av=").append(a);
      localStringBuilder.append("&pro=").append(c);
      localStringBuilder.append("&cid=").append(dd.d(paramContext));
      localStringBuilder.append("&dmac=").append(dd.c(paramContext));
      localStringBuilder.append("&wmac=").append(dd.b(paramContext));
      localStringBuilder.append("&nt=");
      localStringBuilder.append(dd.l(paramContext));
      paramdh = dd.o(paramContext);
      localStringBuilder.append("&np=");
      localStringBuilder.append(paramdh);
      localStringBuilder.append("&ia=1&");
      str = dd.a(paramContext);
      paramdh = str;
      if (str == null) {
        paramdh = "";
      }
      localStringBuilder.append("utd=").append(paramdh).append("&");
      paramdh = da.f(paramContext);
      if ((paramdh != null) && (paramdh.length() > 0))
      {
        localStringBuilder.append("key=");
        localStringBuilder.append(paramdh);
        localStringBuilder.append("&");
      }
      localStringBuilder.append("ctm=" + System.currentTimeMillis());
      localStringBuilder.append("&re=" + dd.j(paramContext));
      if (paramMap != null)
      {
        paramContext = paramMap.entrySet().iterator();
        while (paramContext.hasNext())
        {
          paramdh = (Map.Entry)paramContext.next();
          localStringBuilder.append("&").append((String)paramdh.getKey()).append("=").append((String)paramdh.getValue());
        }
      }
      return localStringBuilder.toString();
    }
    catch (Throwable paramContext)
    {
      ed.a(paramContext, "CInfo", "InitXInfo");
      paramContext.printStackTrace();
    }
  }
  
  public static String b(Context paramContext, byte[] paramArrayOfByte)
  {
    try
    {
      paramContext = c(paramContext, paramArrayOfByte);
      return paramContext;
    }
    catch (InvalidKeyException paramContext)
    {
      ed.a(paramContext, "CInfo", "AESData");
      paramContext.printStackTrace();
      return "";
    }
    catch (NoSuchAlgorithmException paramContext)
    {
      for (;;)
      {
        ed.a(paramContext, "CInfo", "AESData");
        paramContext.printStackTrace();
      }
    }
    catch (NoSuchPaddingException paramContext)
    {
      for (;;)
      {
        ed.a(paramContext, "CInfo", "AESData");
        paramContext.printStackTrace();
      }
    }
    catch (IllegalBlockSizeException paramContext)
    {
      for (;;)
      {
        ed.a(paramContext, "CInfo", "AESData");
        paramContext.printStackTrace();
      }
    }
    catch (BadPaddingException paramContext)
    {
      for (;;)
      {
        ed.a(paramContext, "CInfo", "AESData");
        paramContext.printStackTrace();
      }
    }
    catch (InvalidKeySpecException paramContext)
    {
      for (;;)
      {
        ed.a(paramContext, "CInfo", "AESData");
        paramContext.printStackTrace();
      }
    }
    catch (CertificateException paramContext)
    {
      for (;;)
      {
        ed.a(paramContext, "CInfo", "AESData");
        paramContext.printStackTrace();
      }
    }
    catch (IOException paramContext)
    {
      for (;;)
      {
        ed.a(paramContext, "CInfo", "AESData");
        paramContext.printStackTrace();
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        ed.a(paramContext, "CInfo", "AESData");
        paramContext.printStackTrace();
      }
    }
  }
  
  private static String c(Context paramContext, byte[] paramArrayOfByte)
  {
    Object localObject = KeyGenerator.getInstance("AES");
    if (localObject == null) {}
    do
    {
      return null;
      ((KeyGenerator)localObject).init(256);
      localObject = ((KeyGenerator)localObject).generateKey().getEncoded();
      paramContext = di.a(paramContext);
    } while (paramContext == null);
    paramContext = de.a((byte[])localObject, paramContext);
    paramArrayOfByte = de.a((byte[])localObject, paramArrayOfByte);
    localObject = new byte[paramContext.length + paramArrayOfByte.length];
    System.arraycopy(paramContext, 0, localObject, 0, paramContext.length);
    System.arraycopy(paramArrayOfByte, 0, localObject, paramContext.length, paramArrayOfByte.length);
    paramContext = di.a((byte[])localObject);
    if (paramContext != null) {
      return de.a(paramContext);
    }
    return "";
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.dc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */