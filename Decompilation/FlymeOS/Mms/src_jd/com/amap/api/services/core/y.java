package com.amap.api.services.core;

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

public class y
{
  public static String a()
  {
    try
    {
      str1 = String.valueOf(System.currentTimeMillis());
      int i;
      String str2;
      ay.a(localThrowable1, "CInfo", "getTS");
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
      localStringBuilder.append("\"key\":\"").append(w.f(paramContext)).append("\",\"ct\":\"android\",\"ime\":\"").append(z.m(paramContext)).append("\",\"pkg\":\"").append(w.b(paramContext)).append("\",\"mod\":\"").append(Build.MODEL).append("\",\"apn\":\"").append(w.a(paramContext)).append("\",\"apv\":\"").append(w.c(paramContext)).append("\",\"sv\":\"").append(Build.VERSION.RELEASE).append("\",");
      return localStringBuilder.toString();
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        ay.a(paramContext, "CInfo", "getPublicJSONInfo");
        paramContext.printStackTrace();
      }
    }
  }
  
  public static String a(Context paramContext, ad paramad)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      String str = z.q(paramContext);
      localStringBuilder.append("\"sim\":\"").append(str).append("\",\"av\":\"").append(a).append("\",\"pro\":\"").append(c).append("\",\"ed\":\"").append(paramad.d()).append("\",\"nt\":\"").append(z.k(paramContext)).append("\",\"np\":\"").append(z.p(paramContext)).append("\",\"mnc\":\"").append(z.e(paramContext)).append("\",\"lnt\":\"").append(z.f(paramContext)).append("\",\"ant\":\"").append(z.h(paramContext)).append("\"");
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
  
  public static String a(Context paramContext, ad paramad, Map<String, String> paramMap)
  {
    try
    {
      paramad = b(paramContext, paramad, paramMap);
      if ("".equals(paramad)) {
        return null;
      }
      paramContext = b(paramContext, paramad.getBytes("utf-8"));
      return paramContext;
    }
    catch (UnsupportedEncodingException paramContext)
    {
      ay.a(paramContext, "CInfo", "rsaInfo");
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = w.d(paramContext);
      paramContext = ab.a(paramContext + ":" + paramString1.substring(0, paramString1.length() - 3) + ":" + paramString2);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      ay.a(paramContext, "CInfo", "Scode");
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
  
  private static String b(Context paramContext, ad paramad, Map<String, String> paramMap)
  {
    localStringBuilder = new StringBuilder();
    try
    {
      String str = z.m(paramContext);
      localStringBuilder.append("ct=android");
      localStringBuilder.append("&ime=").append(str);
      localStringBuilder.append("&pkg=").append(w.b(paramContext));
      localStringBuilder.append("&mod=");
      localStringBuilder.append(Build.MODEL);
      localStringBuilder.append("&apn=").append(w.a(paramContext));
      localStringBuilder.append("&apv=").append(w.c(paramContext));
      localStringBuilder.append("&sv=");
      localStringBuilder.append(Build.VERSION.RELEASE);
      str = z.n(paramContext);
      localStringBuilder.append("&sim=").append(str);
      localStringBuilder.append("&av=").append(a);
      localStringBuilder.append("&pro=").append(c);
      localStringBuilder.append("&cid=").append(z.d(paramContext));
      localStringBuilder.append("&dmac=").append(z.c(paramContext));
      localStringBuilder.append("&wmac=").append(z.b(paramContext));
      localStringBuilder.append("&nt=");
      localStringBuilder.append(z.l(paramContext));
      paramad = z.o(paramContext);
      localStringBuilder.append("&np=");
      localStringBuilder.append(paramad);
      localStringBuilder.append("&ia=1&");
      str = z.a(paramContext);
      paramad = str;
      if (str == null) {
        paramad = "";
      }
      localStringBuilder.append("utd=").append(paramad).append("&");
      paramad = w.f(paramContext);
      if ((paramad != null) && (paramad.length() > 0))
      {
        localStringBuilder.append("key=");
        localStringBuilder.append(paramad);
        localStringBuilder.append("&");
      }
      localStringBuilder.append("ctm=" + System.currentTimeMillis());
      localStringBuilder.append("&re=" + z.j(paramContext));
      if (paramMap != null)
      {
        paramContext = paramMap.entrySet().iterator();
        while (paramContext.hasNext())
        {
          paramad = (Map.Entry)paramContext.next();
          localStringBuilder.append("&").append((String)paramad.getKey()).append("=").append((String)paramad.getValue());
        }
      }
      return localStringBuilder.toString();
    }
    catch (Throwable paramContext)
    {
      ay.a(paramContext, "CInfo", "InitXInfo");
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
      ay.a(paramContext, "CInfo", "AESData");
      paramContext.printStackTrace();
      return "";
    }
    catch (NoSuchAlgorithmException paramContext)
    {
      for (;;)
      {
        ay.a(paramContext, "CInfo", "AESData");
        paramContext.printStackTrace();
      }
    }
    catch (NoSuchPaddingException paramContext)
    {
      for (;;)
      {
        ay.a(paramContext, "CInfo", "AESData");
        paramContext.printStackTrace();
      }
    }
    catch (IllegalBlockSizeException paramContext)
    {
      for (;;)
      {
        ay.a(paramContext, "CInfo", "AESData");
        paramContext.printStackTrace();
      }
    }
    catch (BadPaddingException paramContext)
    {
      for (;;)
      {
        ay.a(paramContext, "CInfo", "AESData");
        paramContext.printStackTrace();
      }
    }
    catch (InvalidKeySpecException paramContext)
    {
      for (;;)
      {
        ay.a(paramContext, "CInfo", "AESData");
        paramContext.printStackTrace();
      }
    }
    catch (CertificateException paramContext)
    {
      for (;;)
      {
        ay.a(paramContext, "CInfo", "AESData");
        paramContext.printStackTrace();
      }
    }
    catch (IOException paramContext)
    {
      for (;;)
      {
        ay.a(paramContext, "CInfo", "AESData");
        paramContext.printStackTrace();
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        ay.a(paramContext, "CInfo", "AESData");
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
      paramContext = ae.a(paramContext);
    } while (paramContext == null);
    paramContext = aa.a((byte[])localObject, paramContext);
    paramArrayOfByte = aa.a((byte[])localObject, paramArrayOfByte);
    localObject = new byte[paramContext.length + paramArrayOfByte.length];
    System.arraycopy(paramContext, 0, localObject, 0, paramContext.length);
    System.arraycopy(paramArrayOfByte, 0, localObject, paramContext.length, paramArrayOfByte.length);
    paramContext = ae.a((byte[])localObject);
    if (paramContext != null) {
      return aa.a(paramContext);
    }
    return "";
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.y
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */