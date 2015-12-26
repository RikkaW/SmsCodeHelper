package com.xiaomi.stats;

import com.xiaomi.push.service.PushClientsManager.ClientLoginInfo;
import com.xiaomi.push.service.PushClientsManager.ClientLoginInfo.ClientStatusListener;
import com.xiaomi.push.service.PushClientsManager.ClientStatus;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.XMPushService.Job;
import com.xiaomi.push.thrift.ChannelStatsType;
import com.xiaomi.push.thrift.StatsEvent;
import com.xiaomi.smack.Connection;

class BindTracker
  implements PushClientsManager.ClientLoginInfo.ClientStatusListener
{
  private PushClientsManager.ClientLoginInfo client;
  private Connection connection;
  private XMPushService pushService;
  private int reason;
  private PushClientsManager.ClientStatus status;
  private boolean tracked = false;
  
  BindTracker(XMPushService paramXMPushService, PushClientsManager.ClientLoginInfo paramClientLoginInfo)
  {
    pushService = paramXMPushService;
    status = PushClientsManager.ClientStatus.binding;
    client = paramClientLoginInfo;
  }
  
  private void done()
  {
    untrack();
    if (!tracked) {}
    while (reason == 11) {
      return;
    }
    StatsEvent localStatsEvent2 = StatsHandler.getInstance().createStatsEvent();
    Object localObject = localStatsEvent2;
    switch (status)
    {
    default: 
      localObject = localStatsEvent2;
    }
    while (localObject != null)
    {
      ((StatsEvent)localObject).setHost(connection.getHost());
      ((StatsEvent)localObject).setUser(client.userId);
      value = 1;
      try
      {
        ((StatsEvent)localObject).setChid((byte)Integer.parseInt(client.chid));
        StatsHandler.getInstance().add((StatsEvent)localObject);
        return;
        if (reason == 17)
        {
          type = ChannelStatsType.BIND_TCP_READ_TIMEOUT.getValue();
          localObject = localStatsEvent2;
          continue;
        }
        if (reason == 21)
        {
          type = ChannelStatsType.BIND_TIMEOUT.getValue();
          localObject = localStatsEvent2;
          continue;
        }
        try
        {
          localObject = StatsAnalyser.fromBind(StatsHandler.getContext().getCaughtException());
          type = type.getValue();
          localStatsEvent2.setAnnotation(annotation);
          localObject = localStatsEvent2;
        }
        catch (NullPointerException localNullPointerException)
        {
          localStatsEvent1 = null;
        }
        continue;
        type = ChannelStatsType.BIND_SUCCESS.getValue();
        StatsEvent localStatsEvent1 = localStatsEvent2;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        for (;;) {}
      }
    }
  }
  
  private void untrack()
  {
    client.removeClientStatusListener(this);
  }
  
  public void onChange(PushClientsManager.ClientStatus paramClientStatus1, PushClientsManager.ClientStatus paramClientStatus2, int paramInt)
  {
    if ((!tracked) && (paramClientStatus1 == PushClientsManager.ClientStatus.binding))
    {
      status = paramClientStatus2;
      reason = paramInt;
      tracked = true;
    }
    pushService.executeJob(new XMPushService.Job(4)
    {
      public String getDesc()
      {
        return "Handling bind stats";
      }
      
      public void process()
      {
        BindTracker.this.done();
      }
    });
  }
  
  void track()
  {
    client.addClientStatusListener(this);
    connection = pushService.getCurrentConnection();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.stats.BindTracker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */