package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;

class ReconnectionManager
{
  private static int MAX_RETRY_INTERVAL = 300;
  private int attempts = 0;
  private int curDelay;
  private long lastConnectTime;
  private XMPushService mPushService;
  
  public ReconnectionManager(XMPushService paramXMPushService)
  {
    mPushService = paramXMPushService;
    curDelay = 10;
    lastConnectTime = 0L;
  }
  
  private int timeDelay()
  {
    int j = 40;
    int i = 10;
    if (attempts > 8) {
      i = 300;
    }
    do
    {
      return i;
      if (attempts > 4) {
        return 60;
      }
    } while (attempts >= 1);
    if (lastConnectTime == 0L) {
      return 0;
    }
    long l = System.currentTimeMillis() - lastConnectTime;
    if (l < 300000L)
    {
      if (curDelay >= MAX_RETRY_INTERVAL) {
        return curDelay;
      }
      i = curDelay;
      curDelay = ((int)(curDelay * 1.5D));
      return i;
    }
    if (l < 900000L)
    {
      i = j;
      if (curDelay < 40) {
        i = curDelay;
      }
      curDelay = i;
      return curDelay;
    }
    if (l < 1800000L)
    {
      if (curDelay < 20) {}
      for (i = curDelay;; i = 20)
      {
        curDelay = i;
        return curDelay;
      }
    }
    curDelay = 10;
    return curDelay;
  }
  
  public void onConnectSucceeded()
  {
    lastConnectTime = System.currentTimeMillis();
    mPushService.removeJobs(1);
    attempts = 0;
  }
  
  public void tryReconnect(boolean paramBoolean)
  {
    if (mPushService.shouldReconnect())
    {
      XMPushService localXMPushService1;
      XMPushService localXMPushService2;
      if (paramBoolean)
      {
        mPushService.removeJobs(1);
        localXMPushService1 = mPushService;
        localXMPushService2 = mPushService;
        localXMPushService2.getClass();
        localXMPushService1.executeJob(new XMPushService.ConnectJob(localXMPushService2));
        attempts += 1;
      }
      do
      {
        do
        {
          return;
        } while (mPushService.hasJob(1));
        int i = timeDelay();
        MyLog.warn("schedule reconnect in " + i + "s");
        localXMPushService1 = mPushService;
        localXMPushService2 = mPushService;
        localXMPushService2.getClass();
        localXMPushService1.executeJobDelayed(new XMPushService.ConnectJob(localXMPushService2), i * 1000);
        attempts += 1;
      } while (attempts != 3);
      NetworkCheckup.connectivityTest();
      return;
    }
    MyLog.v("should not reconnect as no client or network.");
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.ReconnectionManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */