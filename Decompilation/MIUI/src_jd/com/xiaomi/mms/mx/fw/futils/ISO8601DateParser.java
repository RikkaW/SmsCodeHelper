package com.xiaomi.mms.mx.fw.futils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ISO8601DateParser
{
  public static Date parse(String paramString)
    throws ParseException
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
    if (paramString.endsWith("Z")) {}
    String str;
    for (paramString = paramString.substring(0, paramString.length() - 1) + "GMT-00:00";; paramString = str + "GMT" + paramString)
    {
      return localSimpleDateFormat.parse(paramString);
      str = paramString.substring(0, paramString.length() - 6);
      paramString = paramString.substring(paramString.length() - 6, paramString.length());
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.futils.ISO8601DateParser
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */