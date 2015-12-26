package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.network.HostManager.HttpGet;
import com.xiaomi.smack.util.SystemUtils;
import com.xiaomi.stats.StatsHelper;
import java.io.IOException;
import java.net.URL;

class PushHostManagerFactory$GslbHttpGet
  implements HostManager.HttpGet
{
  public String doGet(String paramString)
    throws IOException
  {
    paramString = new URL(paramString);
    if (paramString.getPort() == -1) {}
    for (i = 80;; i = paramString.getPort()) {
      try
      {
        long l1 = System.currentTimeMillis();
        String str = Network.downloadXml(SystemUtils.getContext(), paramString);
        long l2 = System.currentTimeMillis();
        StatsHelper.statsGslb(paramString.getHost() + ":" + i, (int)(l2 - l1), null);
        return str;
      }
      catch (IOException localIOException)
      {
        StatsHelper.statsGslb(paramString.getHost() + ":" + i, -1, localIOException);
        throw localIOException;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.PushHostManagerFactory.GslbHttpGet
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */