package com.ted.sdk.bubble;

import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BankNumberUtils
{
  public static final int MAX_BANK_SIZE = 19;
  public static final int MIN_BANK_SIZE = 13;
  
  private static String changeAllChar2Digit(String paramString)
  {
    Matcher localMatcher = Pattern.compile("[loLO零一二三四五六七八九壹贰叁肆伍陆柒捌玖\\- \\.\\~]").matcher(paramString);
    StringBuffer localStringBuffer = new StringBuffer();
    for (;;)
    {
      if (!localMatcher.find())
      {
        localMatcher.appendTail(localStringBuffer);
        return localStringBuffer.toString();
      }
      String str2 = "";
      int i = "loLO零一二三四五六七八九壹贰叁肆伍陆柒捌玖".indexOf(paramString.substring(localMatcher.start(), localMatcher.end()));
      String str1 = str2;
      if (i > -1)
      {
        str1 = str2;
        if (i < 23) {
          str1 = "10100123456789123456789".substring(i, i + 1);
        }
      }
      localMatcher.appendReplacement(localStringBuffer, str1);
    }
  }
  
  public static boolean containsBankAccount(String paramString)
  {
    Matcher localMatcher = Pattern.compile(String.format("(%s){13,19}", new Object[] { "[0-9loLO零一二三四五六七八九壹贰叁肆伍陆柒捌玖]" })).matcher(paramString);
    boolean bool1 = false;
    boolean bool2;
    if (!localMatcher.find())
    {
      bool2 = bool1;
      if (!bool1) {
        localMatcher = Pattern.compile(String.format("(%s[- .~]?){13,19}", new Object[] { "[0-9loLO零一二三四五六七八九壹贰叁肆伍陆柒捌玖]" })).matcher(paramString);
      }
    }
    for (;;)
    {
      if (!localMatcher.find())
      {
        bool2 = bool1;
        return bool2;
        if (!luhmVerification(changeAllChar2Digit(paramString.substring(localMatcher.start(), localMatcher.end())))) {
          break;
        }
        bool1 = true;
        break;
      }
      if (luhmVerification(changeAllChar2Digit(paramString.substring(localMatcher.start(), localMatcher.end())))) {
        bool1 = true;
      }
    }
  }
  
  private static boolean luhmVerification(String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (!paramString.matches("\\d+"))) {
      return false;
    }
    char[] arrayOfChar = paramString.substring(0, paramString.length() - 1).trim().toCharArray();
    int j = arrayOfChar.length;
    int i = 0;
    j -= 1;
    int k = 0;
    if (j < 0) {
      if (k % 10 != 0) {
        break label133;
      }
    }
    label133:
    for (i = 48;; i = (char)(10 - k % 10 + 48))
    {
      if (paramString.charAt(paramString.length() - 1) != i) {
        break label148;
      }
      return true;
      int n = arrayOfChar[j] - '0';
      int m = n;
      if (i % 2 == 0)
      {
        m = n * 2;
        m = m % 10 + m / 10;
      }
      k += m;
      j -= 1;
      i += 1;
      break;
    }
    label148:
    return false;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.bubble.BankNumberUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */