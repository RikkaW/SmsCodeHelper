package com.ted.sdk.location;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class CmSpecailLocation
{
  private static final HashMap<String, ArrayList<NumberSegment>> SPECIAL = new CmSpecailLocation.1();
  
  public static int getInSpecialIdx(String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramString.length() < 10)) {
      return 1023;
    }
    Object localObject = paramString.substring(3, 6);
    localObject = (ArrayList)SPECIAL.get(localObject);
    if (localObject != null)
    {
      paramString = paramString.substring(6, 10);
      localObject = ((ArrayList)localObject).iterator();
    }
    NumberSegment localNumberSegment;
    do
    {
      if (!((Iterator)localObject).hasNext()) {
        return 1023;
      }
      localNumberSegment = (NumberSegment)((Iterator)localObject).next();
    } while (!localNumberSegment.isIn(paramString));
    return localNumberSegment.getProvCityIdx();
  }
  
  static class NumberSegment
  {
    private int from;
    private int idx;
    private int to;
    
    public NumberSegment(String paramString, int paramInt)
    {
      if (paramString.contains("-"))
      {
        paramString = paramString.split("-");
        from = Integer.valueOf(remove0(paramString[0])).intValue();
      }
      for (to = Integer.valueOf(remove0(paramString[1])).intValue();; to = from)
      {
        idx = paramInt;
        return;
        from = Integer.valueOf(remove0(paramString)).intValue();
      }
    }
    
    private static String remove0(String paramString)
    {
      for (;;)
      {
        if ((!paramString.startsWith("0")) || (paramString.length() <= 1)) {
          return paramString;
        }
        paramString = paramString.substring(1);
      }
    }
    
    public int getProvCityIdx()
    {
      return idx;
    }
    
    public boolean isIn(String paramString)
    {
      int i = Integer.valueOf(remove0(paramString)).intValue();
      return (from <= i) && (i <= to);
    }
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.location.CmSpecailLocation
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */