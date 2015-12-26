package com.xiaomi.channel.commonutils.network;

import java.util.Map;

public class Network$HttpHeaderInfo
{
  public Map<String, String> AllHeaders;
  public int ResponseCode;
  
  public String toString()
  {
    return String.format("resCode = %1$d, headers = %2$s", new Object[] { Integer.valueOf(ResponseCode), AllHeaders.toString() });
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.channel.commonutils.network.Network.HttpHeaderInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */