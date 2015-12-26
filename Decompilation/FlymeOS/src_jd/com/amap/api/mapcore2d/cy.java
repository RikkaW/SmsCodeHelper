package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build.VERSION;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class cy
{
  public static double[] a = { 7453.642D, 3742.9905D, 1873.333D, 936.89026D, 468.472D, 234.239D, 117.12D, 58.56D, 29.28D, 14.64D, 7.32D, 3.66D, 1.829D, 0.915D, 0.4575D, 0.228D, 0.1144D };
  
  public static double a(LatLng paramLatLng1, LatLng paramLatLng2)
  {
    double d4 = longitude;
    double d3 = latitude;
    double d2 = longitude;
    double d1 = latitude;
    double d5 = d4 * 0.01745329251994329D;
    double d6 = d3 * 0.01745329251994329D;
    d3 = d2 * 0.01745329251994329D;
    d4 = d1 * 0.01745329251994329D;
    d1 = Math.sin(d5);
    d2 = Math.sin(d6);
    d5 = Math.cos(d5);
    d6 = Math.cos(d6);
    double d7 = Math.sin(d3);
    double d8 = Math.sin(d4);
    d3 = Math.cos(d3);
    d4 = Math.cos(d4);
    paramLatLng1 = new double[3];
    paramLatLng2 = new double[3];
    paramLatLng1[0] = (d5 * d6);
    paramLatLng1[1] = (d6 * d1);
    paramLatLng1[2] = d2;
    paramLatLng2[0] = (d4 * d3);
    paramLatLng2[1] = (d4 * d7);
    paramLatLng2[2] = d8;
    return Math.asin(Math.sqrt((paramLatLng1[0] - paramLatLng2[0]) * (paramLatLng1[0] - paramLatLng2[0]) + (paramLatLng1[1] - paramLatLng2[1]) * (paramLatLng1[1] - paramLatLng2[1]) + (paramLatLng1[2] - paramLatLng2[2]) * (paramLatLng1[2] - paramLatLng2[2])) / 2.0D) * 1.27420015798544E7D;
  }
  
  public static float a(float paramFloat)
  {
    float f;
    if (paramFloat < 0.0F) {
      f = 0.0F;
    }
    do
    {
      return f;
      f = paramFloat;
    } while (paramFloat <= 45.0F);
    return 45.0F;
  }
  
  public static int a(int paramInt)
  {
    int i = (int)(Math.log(paramInt) / Math.log(2.0D));
    if (1 << i >= paramInt) {
      return 1 << i;
    }
    return 1 << i + 1;
  }
  
  public static int a(Object[] paramArrayOfObject)
  {
    return Arrays.hashCode(paramArrayOfObject);
  }
  
  public static Bitmap a(Bitmap paramBitmap, float paramFloat)
  {
    if (paramBitmap == null) {
      return null;
    }
    return Bitmap.createScaledBitmap(paramBitmap, (int)(paramBitmap.getWidth() * paramFloat), (int)(paramBitmap.getHeight() * paramFloat), true);
  }
  
  public static Bitmap a(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    if (paramBitmap.hasAlpha()) {}
    for (Object localObject = Bitmap.Config.ARGB_8888;; localObject = Bitmap.Config.RGB_565)
    {
      localObject = Bitmap.createBitmap(paramInt1, paramInt2, (Bitmap.Config)localObject);
      Canvas localCanvas = new Canvas((Bitmap)localObject);
      Paint localPaint = new Paint();
      localPaint.setAntiAlias(true);
      localPaint.setFilterBitmap(true);
      localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, localPaint);
      return (Bitmap)localObject;
    }
  }
  
  public static Bitmap a(String paramString)
  {
    try
    {
      paramString = BitmapDescriptorFactory.class.getResourceAsStream("/assets/" + paramString);
      Bitmap localBitmap = BitmapFactory.decodeStream(paramString);
      paramString.close();
      return localBitmap;
    }
    catch (Exception paramString)
    {
      a(paramString, "Util", "fromAsset");
    }
    return null;
  }
  
  public static ae a(LatLng paramLatLng)
  {
    if (paramLatLng == null) {
      return null;
    }
    return new ae((int)(latitude * 1000000.0D), (int)(longitude * 1000000.0D));
  }
  
  public static String a(String paramString, Object paramObject)
  {
    return paramString + "=" + String.valueOf(paramObject);
  }
  
  public static String a(String... paramVarArgs)
  {
    int i = 0;
    StringBuilder localStringBuilder = new StringBuilder();
    int k = paramVarArgs.length;
    int j = 0;
    while (i < k)
    {
      localStringBuilder.append(paramVarArgs[i]);
      if (j != paramVarArgs.length - 1) {
        localStringBuilder.append(",");
      }
      j += 1;
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static void a(Throwable paramThrowable, String paramString1, String paramString2)
  {
    ed localed = ed.b();
    if (localed != null) {
      localed.b(paramThrowable, paramString1, paramString2);
    }
    paramThrowable.printStackTrace();
  }
  
  public static boolean a()
  {
    return Build.VERSION.SDK_INT >= 8;
  }
  
  public static boolean a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (Math.abs(b(paramDouble1, paramDouble2, paramDouble3, paramDouble4, paramDouble5, paramDouble6)) < 1.0E-9D)
    {
      bool1 = bool2;
      if ((paramDouble1 - paramDouble3) * (paramDouble1 - paramDouble5) <= 0.0D)
      {
        bool1 = bool2;
        if ((paramDouble2 - paramDouble4) * (paramDouble2 - paramDouble6) <= 0.0D) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static boolean a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, double paramDouble8)
  {
    boolean bool2 = false;
    double d = (paramDouble3 - paramDouble1) * (paramDouble8 - paramDouble6) - (paramDouble4 - paramDouble2) * (paramDouble7 - paramDouble5);
    boolean bool1 = bool2;
    if (d != 0.0D)
    {
      paramDouble7 = ((paramDouble2 - paramDouble6) * (paramDouble7 - paramDouble5) - (paramDouble1 - paramDouble5) * (paramDouble8 - paramDouble6)) / d;
      paramDouble1 = ((paramDouble2 - paramDouble6) * (paramDouble3 - paramDouble1) - (paramDouble1 - paramDouble5) * (paramDouble4 - paramDouble2)) / d;
      bool1 = bool2;
      if (paramDouble7 >= 0.0D)
      {
        bool1 = bool2;
        if (paramDouble7 <= 1.0D)
        {
          bool1 = bool2;
          if (paramDouble1 >= 0.0D)
          {
            bool1 = bool2;
            if (paramDouble1 <= 1.0D) {
              bool1 = true;
            }
          }
        }
      }
    }
    return bool1;
  }
  
  public static boolean a(Context paramContext)
  {
    boolean bool;
    if (paramContext == null) {
      bool = false;
    }
    for (;;)
    {
      return bool;
      try
      {
        paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
        if (paramContext == null)
        {
          bool = false;
          continue;
        }
        paramContext = paramContext.getActiveNetworkInfo();
        if (paramContext == null)
        {
          bool = false;
          continue;
        }
        paramContext = paramContext.getState();
        if ((paramContext != null) && (paramContext != NetworkInfo.State.DISCONNECTED))
        {
          NetworkInfo.State localState = NetworkInfo.State.DISCONNECTING;
          if (paramContext != localState) {}
        }
        else
        {
          bool = false;
          continue;
        }
        bool = true;
      }
      finally {}
    }
  }
  
  public static boolean a(LatLng paramLatLng, List<LatLng> paramList)
  {
    double d1 = longitude;
    double d2 = latitude;
    double d3 = latitude;
    int j = 0;
    int i = 0;
    double d4;
    double d5;
    double d6;
    double d7;
    if (j < paramList.size() - 1)
    {
      d4 = getlongitude;
      d5 = getlatitude;
      d6 = get1longitude;
      d7 = get1latitude;
      if (a(d1, d2, d4, d5, d6, d7)) {
        return true;
      }
      if (Math.abs(d7 - d5) >= 1.0E-9D) {}
    }
    for (;;)
    {
      j += 1;
      break;
      if (a(d4, d5, d1, d2, 180.0D, d3))
      {
        if (d5 > d7) {
          i += 1;
        }
      }
      else if (a(d6, d7, d1, d2, 180.0D, d3))
      {
        if (d7 > d5) {
          i += 1;
        }
      }
      else if (a(d4, d5, d6, d7, d1, d2, 180.0D, d3))
      {
        i += 1;
        continue;
        return i % 2 != 0;
      }
    }
  }
  
  public static double b(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6)
  {
    return (paramDouble3 - paramDouble1) * (paramDouble6 - paramDouble2) - (paramDouble5 - paramDouble1) * (paramDouble4 - paramDouble2);
  }
  
  public static float b(float paramFloat)
  {
    float f;
    if (paramFloat > y.c) {
      f = y.c;
    }
    do
    {
      return f;
      f = paramFloat;
    } while (paramFloat >= y.d);
    return y.d;
  }
  
  public static String b(int paramInt)
  {
    if (paramInt < 1000) {
      return paramInt + "m";
    }
    return paramInt / 1000 + "km";
  }
  
  public static boolean b()
  {
    return Build.VERSION.SDK_INT >= 9;
  }
  
  public static boolean c()
  {
    return Build.VERSION.SDK_INT >= 11;
  }
  
  public static boolean d()
  {
    return Build.VERSION.SDK_INT >= 12;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */