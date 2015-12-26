package com.xiaomi.mms.mx.fw.faccount;

import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class JIDUtils
{
  private static final Pattern XM_ACCOUNT_PATTERN = Pattern.compile(".*@xiaomi.com(/.*)?");
  
  public static String getFullSmtpName(String paramString)
  {
    String str;
    if (TextUtils.isEmpty(paramString)) {
      str = "";
    }
    do
    {
      return str;
      str = paramString;
    } while (XM_ACCOUNT_PATTERN.matcher(paramString).matches());
    return paramString + "@xiaomi.com";
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.faccount.JIDUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */