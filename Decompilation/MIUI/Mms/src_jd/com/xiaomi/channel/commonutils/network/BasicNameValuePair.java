package com.xiaomi.channel.commonutils.network;

public class BasicNameValuePair
  implements NameValuePair
{
  private final String name;
  private final String value;
  
  public BasicNameValuePair(String paramString1, String paramString2)
  {
    if (paramString1 == null) {
      throw new IllegalArgumentException("Name may not be null");
    }
    name = paramString1;
    value = paramString2;
  }
  
  public String getName()
  {
    return name;
  }
  
  public String getValue()
  {
    return value;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.channel.commonutils.network.BasicNameValuePair
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */