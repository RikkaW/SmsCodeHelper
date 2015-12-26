package com.amap.api.mapcore2d;

class dy
{
  static String a(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    paramString = de.b(paramString.getBytes());
    return (char)(paramString.length() % 26 + 65) + paramString;
  }
  
  static String b(String paramString)
  {
    if (paramString.length() < 2) {
      return "";
    }
    return de.a(paramString.substring(1));
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.dy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */