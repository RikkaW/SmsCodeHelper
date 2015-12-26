package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.XMPPException;

class XMPushService$ReBindJob
  extends XMPushService.Job
{
  PushClientsManager.ClientLoginInfo mLoginInfo = null;
  
  public XMPushService$ReBindJob(XMPushService paramXMPushService, PushClientsManager.ClientLoginInfo paramClientLoginInfo)
  {
    super(4);
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
      mLoginInfo.setStatus(PushClientsManager.ClientStatus.unbind, 1, 16, null, null);
      XMPushService.access$400(this$0).unbind(mLoginInfo.chid, mLoginInfo.userId);
      mLoginInfo.setStatus(PushClientsManager.ClientStatus.binding, 1, 16, null, null);
      XMPushService.access$400(this$0).bind(mLoginInfo);
      return;
    }
    catch (XMPPException localXMPPException)
    {
      MyLog.e(localXMPPException);
      this$0.disconnect(10, localXMPPException);
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.XMPushService.ReBindJob
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */