package com.xiaomi.mms.data;

import com.android.mms.data.Contact;
import java.util.HashMap;
import java.util.Map;

public class MidPhoneMap
{
  private static final Map<String, String> sMap = new HashMap();
  
  public static String get(String paramString)
  {
    try
    {
      paramString = (String)sMap.get(paramString);
      return paramString;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public static void put(String paramString1, String paramString2)
  {
    try
    {
      sMap.put(paramString1, Contact.normalizePhoneNumber(paramString2));
      return;
    }
    finally
    {
      paramString1 = finally;
      throw paramString1;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.data.MidPhoneMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */