package com.google.common.base;

public final class Ascii
{
  public static boolean isUpperCase(char paramChar)
  {
    return (paramChar >= 'A') && (paramChar <= 'Z');
  }
  
  public static char toLowerCase(char paramChar)
  {
    char c = paramChar;
    if (isUpperCase(paramChar)) {
      c = (char)(paramChar ^ 0x20);
    }
    return c;
  }
  
  public static String toLowerCase(String paramString)
  {
    int j = paramString.length();
    StringBuilder localStringBuilder = new StringBuilder(j);
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append(toLowerCase(paramString.charAt(i)));
      i += 1;
    }
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     com.google.common.base.Ascii
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */