package com.amap.api.services.core;

class at
{
  static String a(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    paramString = aa.b(paramString.getBytes());
    return (char)(paramString.length() % 26 + 65) + paramString;
  }
  
  static String b(String paramString)
  {
    if (paramString.length() < 2) {
      return "";
    }
    return aa.a(paramString.substring(1));
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.at
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */