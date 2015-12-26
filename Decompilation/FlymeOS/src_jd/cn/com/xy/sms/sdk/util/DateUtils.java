package cn.com.xy.sms.sdk.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils
{
  public static final long ONE_DAY_TIME = 86400000L;
  
  public static String addDays(String paramString1, String paramString2, int paramInt)
  {
    return getTimeString(paramString2, getTime(paramString1, paramString2) + 86400000L * paramInt);
  }
  
  public static boolean compareDateString(String paramString1, String paramString2, String paramString3)
  {
    return getTime(paramString1, paramString3) > getTime(paramString2, paramString3);
  }
  
  public static String getCurrentTimeString(String paramString)
  {
    try
    {
      paramString = new SimpleDateFormat(paramString).format(new Date(System.currentTimeMillis()));
      return paramString;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  public static long getTime(String paramString1, String paramString2)
  {
    try
    {
      long l = new SimpleDateFormat(paramString2).parse(paramString1).getTime();
      return l;
    }
    catch (ParseException paramString1)
    {
      paramString1.printStackTrace();
    }
    return 0L;
  }
  
  public static String getTimeString(String paramString, long paramLong)
  {
    try
    {
      paramString = new SimpleDateFormat(paramString).format(new Date(paramLong));
      return paramString;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.DateUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */