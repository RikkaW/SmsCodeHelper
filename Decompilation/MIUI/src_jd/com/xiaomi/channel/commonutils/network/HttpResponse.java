package com.xiaomi.channel.commonutils.network;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse
{
  public Map<String, String> headers = new HashMap();
  public int responseCode;
  public String responseString;
  
  public String getResponseString()
  {
    return responseString;
  }
  
  public String toString()
  {
    return String.format("resCode = %1$d, headers = %2$s, response = %3$s", new Object[] { Integer.valueOf(responseCode), headers.toString(), responseString });
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.channel.commonutils.network.HttpResponse
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */