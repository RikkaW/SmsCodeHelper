package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.XMPPException;
import com.xiaomi.stats.StatsHelper;

class XMPushService$BindJob
  extends XMPushService.Job
{
  PushClientsManager.ClientLoginInfo mLoginInfo = null;
  
  public XMPushService$BindJob(XMPushService paramXMPushService, PushClientsManager.ClientLoginInfo paramClientLoginInfo)
  {
    super(9);
    mLoginInfo = paramClientLoginInfo;
  }
  
  public String getDesc()
  {
    return "bind the client. " + mLoginInfo.chid + ", " + mLoginInfo.userId;
  }
  
  public void process()
  {
    try
    {
      if (!this$0.isConnected())
      {
        MyLog.e("trying bind while the connection is not created, quit!");
        return;
      }
      PushClientsManager.ClientLoginInfo localClientLoginInfo = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(mLoginInfo.chid, mLoginInfo.userId);
      if (localClientLoginInfo == null)
      {
        MyLog.warn("ignore bind because the channel " + mLoginInfo.chid + " is removed ");
        return;
      }
    }
    catch (XMPPException localXMPPException)
    {
      MyLog.e(localXMPPException);
      this$0.disconnect(10, localXMPPException);
      return;
    }
    if (status == PushClientsManager.ClientStatus.unbind)
    {
      localXMPPException.setStatus(PushClientsManager.ClientStatus.binding, 0, 0, null, null);
      XMPushService.access$400(this$0).bind(localXMPPException);
      StatsHelper.statsBind(this$0, localXMPPException);
      return;
    }
    MyLog.warn("trying duplicate bind, ingore! " + status);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.XMPushService.BindJob
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */