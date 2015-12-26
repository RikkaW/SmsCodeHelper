package com.ted.sdk.location;

import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.ted.sdk.yellow.util.ProvinceUtils;

public class AreaCodeLocation
{
  public static final SparseArray<int[][]> AREACODE_MAP = new AreaCodeLocation.1();
  private static final SparseIntArray SET = new AreaCodeLocation.2();
  private static final Integer[] SHORT_AREA_CODES = { Integer.valueOf(10), Integer.valueOf(21), Integer.valueOf(22), Integer.valueOf(23), Integer.valueOf(24), Integer.valueOf(25), Integer.valueOf(27), Integer.valueOf(28), Integer.valueOf(29), Integer.valueOf(20) };
  
  public static String getAreaCode(String paramString1, String paramString2)
  {
    if (ProvinceUtils.PROVINCE_BJ.equals(paramString1)) {
      paramString2 = ProvinceUtils.PROVINCE_BJ;
    }
    if (ProvinceUtils.PROVINCE_SH.equals(paramString1)) {
      paramString2 = ProvinceUtils.PROVINCE_SH;
    }
    if (ProvinceUtils.PROVINCE_TJ.equals(paramString1)) {
      paramString2 = ProvinceUtils.PROVINCE_TJ;
    }
    if (ProvinceUtils.PROVINCE_CQ.equals(paramString1)) {
      paramString2 = ProvinceUtils.PROVINCE_CQ;
    }
    int i = ProvinceUtils.getProCityIdx(paramString1, paramString2);
    if (-1 == i) {}
    while (-1 == SET.get(i)) {
      return null;
    }
    return "0" + SET.get(i);
  }
  
  public static Pair<String, String> getAreaCodeLocation(String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramString.length() < 3) || (!paramString.startsWith("0"))) {
      return null;
    }
    String str = paramString.substring(0, 3);
    if (isShortCode(str)) {
      return getHeaderProCity(Integer.parseInt(str));
    }
    paramString = paramString.substring(0, 4);
    try
    {
      paramString = getHeaderProCity(Integer.parseInt(paramString));
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  private static Pair<String, String> getHeaderProCity(int paramInt)
  {
    int[][] arrayOfInt = (int[][])AREACODE_MAP.get(paramInt);
    String str = ProvinceUtils.ALL[arrayOfInt[0][0]];
    Object localObject2 = null;
    int[] arrayOfInt1 = arrayOfInt[1];
    int i = arrayOfInt1.length;
    paramInt = 0;
    if (paramInt >= i) {
      return Pair.create(str, localObject2);
    }
    int j = arrayOfInt1[paramInt];
    Object localObject1;
    if (localObject2 == null) {
      localObject1 = ProvinceUtils.CITIES[arrayOfInt[0][0]][j];
    }
    for (;;)
    {
      paramInt += 1;
      localObject2 = localObject1;
      break;
      localObject1 = localObject2;
      if (!((String)localObject2).contains(ProvinceUtils.CITIES[arrayOfInt[0][0]][j])) {
        localObject1 = localObject2 + "/" + ProvinceUtils.CITIES[arrayOfInt[0][0]][j];
      }
    }
  }
  
  private static boolean isShortCode(String paramString)
  {
    try
    {
      i = Integer.parseInt(paramString);
      paramString = SHORT_AREA_CODES;
      int k = paramString.length;
      j = 0;
      if (j >= k) {
        return false;
      }
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        int j;
        int i = 0;
        continue;
        if (paramString[j].intValue() == i) {
          return true;
        }
        j += 1;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.location.AreaCodeLocation
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */