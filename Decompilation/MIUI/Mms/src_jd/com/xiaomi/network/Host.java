package com.xiaomi.network;

import java.net.InetSocketAddress;

public final class Host
{
  private String hostAddress;
  private int port;
  
  public Host(String paramString, int paramInt)
  {
    hostAddress = paramString;
    port = paramInt;
  }
  
  public static InetSocketAddress from(String paramString, int paramInt)
  {
    paramString = parse(paramString, paramInt);
    return new InetSocketAddress(paramString.getHost(), paramString.getPort());
  }
  
  public static Host parse(String paramString, int paramInt)
  {
    int k = paramString.lastIndexOf(":");
    localObject = paramString;
    j = paramInt;
    i = j;
    if (k != -1) {
      str = paramString.substring(0, k);
    }
    try
    {
      i = Integer.parseInt(paramString.substring(k + 1));
      j = i;
      localObject = str;
      i = j;
      if (j <= 0)
      {
        i = paramInt;
        localObject = str;
      }
    }
    catch (NumberFormatException paramString)
    {
      for (;;)
      {
        localObject = str;
        i = j;
      }
    }
    return new Host((String)localObject, i);
  }
  
  public String getHost()
  {
    return hostAddress;
  }
  
  public int getPort()
  {
    return port;
  }
  
  public String toString()
  {
    if (port > 0) {
      return hostAddress + ":" + port;
    }
    return hostAddress;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.network.Host
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */