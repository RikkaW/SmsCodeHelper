package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.network.Host;
import com.xiaomi.push.protobuf.ChannelConfig.PushServiceConfig;
import com.xiaomi.stats.StatsHandler;
import com.xiaomi.stats.StatsHelper;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class NetworkCheckup
{
  private static final Pattern IP_PATTERN = Pattern.compile("([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})");
  private static long lastCheckTime = 0L;
  private static ThreadPoolExecutor sExecutor = new ThreadPoolExecutor(1, 1, 20L, TimeUnit.SECONDS, new LinkedBlockingQueue());
  
  public static void connectivityTest()
  {
    long l = System.currentTimeMillis();
    if ((sExecutor.getActiveCount() > 0) && (l - lastCheckTime < 1800000L)) {}
    ChannelConfig.PushServiceConfig localPushServiceConfig;
    do
    {
      do
      {
        return;
      } while (!StatsHandler.getInstance().isAllowStats());
      localPushServiceConfig = ServiceConfig.getInstance().getConfig();
    } while ((localPushServiceConfig == null) || (localPushServiceConfig.getTestHostsCount() <= 0));
    lastCheckTime = l;
    connectivityTest(localPushServiceConfig.getTestHostsList(), true);
  }
  
  public static void connectivityTest(List<String> paramList, final boolean paramBoolean)
  {
    sExecutor.execute(new Runnable()
    {
      public void run()
      {
        int i = 1;
        boolean bool2 = NetworkCheckup.doConnectTest("www.baidu.com:80");
        Iterator localIterator = val$addrs.iterator();
        boolean bool1;
        do
        {
          do
          {
            bool1 = bool2;
            if (!localIterator.hasNext()) {
              break;
            }
            String str = (String)localIterator.next();
            if ((!bool2) && (!NetworkCheckup.doConnectTest(str))) {
              break label81;
            }
            bool1 = true;
            bool2 = bool1;
          } while (!bool1);
          bool2 = bool1;
        } while (paramBoolean);
        if (bool1) {}
        for (;;)
        {
          StatsHelper.count(i);
          return;
          label81:
          bool1 = false;
          break;
          i = 2;
        }
      }
    });
  }
  
  private static boolean doConnectTest(String paramString)
  {
    long l1 = System.currentTimeMillis();
    try
    {
      MyLog.warn("ConnectivityTest: begin to connect to " + paramString);
      Socket localSocket = new Socket();
      localSocket.connect(Host.from(paramString, 5222), 5000);
      localSocket.setTcpNoDelay(true);
      long l2 = System.currentTimeMillis();
      MyLog.warn("ConnectivityTest: connect to " + paramString + " in " + (l2 - l1));
      localSocket.close();
      return true;
    }
    catch (Throwable localThrowable)
    {
      MyLog.e("ConnectivityTest: could not connect to:" + paramString + " exception: " + localThrowable.getClass().getSimpleName() + " description: " + localThrowable.getMessage());
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.NetworkCheckup
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */