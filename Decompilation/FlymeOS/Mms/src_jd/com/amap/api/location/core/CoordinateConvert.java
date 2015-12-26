package com.amap.api.location.core;

import aia;

public class CoordinateConvert
{
  public static GeoPoint fromGpsToAMap(double paramDouble1, double paramDouble2)
  {
    try
    {
      Object localObject = aia.a(paramDouble2, paramDouble1);
      localObject = new GeoPoint((int)(localObject[1] * 1000000.0D), (int)(localObject[0] * 1000000.0D));
      return (GeoPoint)localObject;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public static double[] fromSeveralGpsToAMap(String paramString)
  {
    int i = 0;
    try
    {
      String[] arrayOfString = paramString.split(",");
      int j = arrayOfString.length;
      double[] arrayOfDouble = new double[j];
      for (;;)
      {
        paramString = arrayOfDouble;
        if (i >= j / 2) {
          break;
        }
        paramString = aia.a(Double.parseDouble(arrayOfString[(i * 2)]), Double.parseDouble(arrayOfString[(i * 2 + 1)]));
        arrayOfDouble[(i * 2)] = paramString[0];
        arrayOfDouble[(i * 2 + 1)] = paramString[1];
        i += 1;
      }
      return paramString;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
      paramString = null;
    }
  }
  
  public static double[] fromSeveralGpsToAMap(double[] paramArrayOfDouble)
  {
    int i = 0;
    try
    {
      int j = paramArrayOfDouble.length;
      double[] arrayOfDouble2 = new double[j];
      double[] arrayOfDouble1;
      for (;;)
      {
        arrayOfDouble1 = arrayOfDouble2;
        if (i >= j / 2) {
          break;
        }
        arrayOfDouble1 = aia.a(paramArrayOfDouble[(i * 2)], paramArrayOfDouble[(i * 2 + 1)]);
        arrayOfDouble2[(i * 2)] = arrayOfDouble1[0];
        arrayOfDouble2[(i * 2 + 1)] = arrayOfDouble1[1];
        i += 1;
      }
      return arrayOfDouble1;
    }
    catch (Throwable paramArrayOfDouble)
    {
      paramArrayOfDouble.printStackTrace();
      arrayOfDouble1 = null;
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.core.CoordinateConvert
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */