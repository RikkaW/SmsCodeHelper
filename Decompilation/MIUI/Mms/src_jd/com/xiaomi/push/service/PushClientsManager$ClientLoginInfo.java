package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.MyLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PushClientsManager$ClientLoginInfo
{
  public String authMethod;
  public String chid;
  public String clientExtra;
  public String cloudExtra;
  public Context context;
  private int currentRetrys = 0;
  public boolean kick;
  public ClientEventDispatcher mClientEventDispatcher;
  private XMPushService mPushService;
  public String pkgName;
  public String security;
  public String session;
  PushClientsManager.ClientStatus status = PushClientsManager.ClientStatus.unbind;
  private List<ClientStatusListener> statusChangeListeners = new ArrayList();
  private XMPushService.BindTimeoutJob timeOutJob = new XMPushService.BindTimeoutJob(this);
  public String token;
  public String userId;
  
  public PushClientsManager$ClientLoginInfo(XMPushService paramXMPushService)
  {
    mPushService = paramXMPushService;
    addClientStatusListener(new ClientStatusListener()
    {
      public void onChange(PushClientsManager.ClientStatus paramAnonymousClientStatus1, PushClientsManager.ClientStatus paramAnonymousClientStatus2, int paramAnonymousInt)
      {
        if (paramAnonymousClientStatus2 == PushClientsManager.ClientStatus.binding)
        {
          mPushService.executeJobDelayed(timeOutJob, 60000L);
          return;
        }
        mPushService.removeJobs(timeOutJob);
      }
    });
  }
  
  public void addClientStatusListener(ClientStatusListener paramClientStatusListener)
  {
    statusChangeListeners.add(paramClientStatusListener);
  }
  
  public String getDesc(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "unknown";
    case 1: 
      return "OPEN";
    case 2: 
      return "CLOSE";
    }
    return "KICK";
  }
  
  public long getNextRetryInterval()
  {
    return 1000L * ((Math.random() * 20.0D - 10.0D) + (currentRetrys + 1) * 15);
  }
  
  public void removeClientStatusListener(ClientStatusListener paramClientStatusListener)
  {
    statusChangeListeners.remove(paramClientStatusListener);
  }
  
  public void setStatus(PushClientsManager.ClientStatus paramClientStatus, int paramInt1, int paramInt2, String paramString1, String paramString2)
  {
    boolean bool = true;
    Iterator localIterator = statusChangeListeners.iterator();
    while (localIterator.hasNext()) {
      ((ClientStatusListener)localIterator.next()).onChange(status, paramClientStatus, paramInt2);
    }
    if (status != paramClientStatus)
    {
      MyLog.warn(String.format("update the client %7$s status. %1$s->%2$s %3$s %4$s %5$s %6$s", new Object[] { status, paramClientStatus, getDesc(paramInt1), PushConstants.getErrorDesc(paramInt2), paramString1, paramString2, chid }));
      status = paramClientStatus;
    }
    if (mClientEventDispatcher == null) {
      MyLog.e("status changed while the client dispatcher is missing");
    }
    do
    {
      return;
      if (paramInt1 == 2)
      {
        mClientEventDispatcher.notifyChannelClosed(context, this, paramInt2);
        return;
      }
      if (paramInt1 == 3)
      {
        mClientEventDispatcher.notifyKickedByServer(context, this, paramString2, paramString1);
        return;
      }
    } while (paramInt1 != 1);
    if (paramClientStatus == PushClientsManager.ClientStatus.binded)
    {
      if ((bool) || (!"wait".equals(paramString2))) {
        break label233;
      }
      currentRetrys += 1;
    }
    for (;;)
    {
      mClientEventDispatcher.notifyChannelOpenResult(context, this, bool, paramInt2, paramString1);
      return;
      bool = false;
      break;
      label233:
      if (bool) {
        currentRetrys = 0;
      }
    }
  }
  
  public static abstract interface ClientStatusListener
  {
    public abstract void onChange(PushClientsManager.ClientStatus paramClientStatus1, PushClientsManager.ClientStatus paramClientStatus2, int paramInt);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.PushClientsManager.ClientLoginInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */