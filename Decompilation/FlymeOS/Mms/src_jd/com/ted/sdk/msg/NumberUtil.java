package com.ted.sdk.msg;

import android.text.TextUtils;
import anu;

public class NumberUtil
{
  private static final String FETION_PREFIX = "12520";
  
  private static final boolean isFetionServiceNumber(String paramString)
  {
    paramString = paramString.substring("12520".length());
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      do
      {
        return true;
      } while ((paramString.startsWith("025")) || (paramString.startsWith("026")));
      if (!paramString.startsWith("0")) {
        break;
      }
    } while (!paramString.startsWith("055"));
    return false;
    return (paramString.length() != 11) || (paramString.charAt(0) != '1') || (paramString.charAt(1) <= '2') || (paramString.charAt(1) >= '9');
  }
  
  private static final boolean isPossibleShortPhoneNumber(String paramString)
  {
    boolean bool2 = false;
    if (paramString != null) {}
    for (int i = paramString.length();; i = 0)
    {
      boolean bool1 = bool2;
      if (i >= 3)
      {
        bool1 = bool2;
        if (i <= 6)
        {
          bool1 = bool2;
          switch (paramString.charAt(0))
          {
          default: 
            bool1 = true;
          }
        }
      }
      return bool1;
    }
  }
  
  public static boolean isServiceNumber(String paramString)
  {
    if (paramString.startsWith("12520")) {
      if (!isFetionServiceNumber(paramString)) {}
    }
    do
    {
      return true;
      return false;
      paramString = anu.b(paramString);
      if (TextUtils.isEmpty(paramString)) {
        return false;
      }
    } while ((paramString.equals("13800138000")) || (paramString.equals("13800100186")) || (paramString.equals("18618610010")) || ((!anu.c(paramString)) && (!isPossibleShortPhoneNumber(paramString))));
    return false;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.msg.NumberUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */