package com.ted.sdk.ivr;

import android.content.Context;
import android.util.Pair;

public class IvrEngine
{
  private static final String[] LEGAL_CITIES = { "全国", "北京", "上海", "广州", "深圳", "南京" };
  
  public static HotNumber getHotNumber(Context paramContext, int paramInt, String paramString1, String paramString2)
  {
    int j = 0;
    if (!IvrCache.INSTANCE.isLoaded()) {
      IvrParser.load(paramContext);
    }
    paramContext = null;
    String[] arrayOfString = LEGAL_CITIES;
    int k = arrayOfString.length;
    int i = 0;
    if (i >= k)
    {
      paramString1 = paramContext;
      label40:
      paramContext = paramString1;
      if (paramString1 == null) {
        paramContext = IvrCache.INSTANCE.get(paramInt, Pair.create("全国", paramString2));
      }
      paramString1 = paramContext;
      if (paramContext == null)
      {
        arrayOfString = LEGAL_CITIES;
        k = arrayOfString.length;
        i = j;
      }
    }
    for (;;)
    {
      if (i >= k) {
        paramString1 = paramContext;
      }
      do
      {
        return paramString1;
        if (arrayOfString[i].equals(paramString1))
        {
          paramString1 = IvrCache.INSTANCE.get(paramInt, Pair.create(paramString1, paramString2));
          break label40;
        }
        i += 1;
        break;
        paramContext = arrayOfString[i];
        paramContext = IvrCache.INSTANCE.get(paramInt, Pair.create(paramContext, paramString2));
        paramString1 = paramContext;
      } while (paramContext != null);
      i += 1;
    }
  }
  
  public static abstract interface City
  {
    public static final String BEIJING = "北京";
    public static final String GUANGZHOU = "广州";
    public static final String NANJING = "南京";
    public static final String NATIONWIDE = "全国";
    public static final String SHANGHAI = "上海";
    public static final String SHENZHEN = "深圳";
  }
  
  public static abstract interface Operator
  {
    public static final int OPERATOR_CMCC = 0;
    public static final int OPERATOR_CTCC = 2;
    public static final int OPERATOR_CUCC = 1;
    public static final int OPERATOR_DEF = -1;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.ivr.IvrEngine
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */