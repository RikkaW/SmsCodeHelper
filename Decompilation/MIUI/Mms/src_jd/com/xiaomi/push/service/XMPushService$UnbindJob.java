package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.XMPPException;

class XMPushService$UnbindJob
  extends XMPushService.Job
{
  String kickType;
  PushClientsManager.ClientLoginInfo mLoginInfo = null;
  int mNotifyType;
  String reason;
  
  public XMPushService$UnbindJob(XMPushService paramXMPushService, PushClientsManager.ClientLoginInfo paramClientLoginInfo, int paramInt, String paramString1, String paramString2)
  {
    super(9);
    mLoginInfo = paramClientLoginInfo;
    mNotifyType = paramInt;
    kickType = paramString1;
    reason = paramString2;
  }
  
  public String getDesc()
  {
    return "unbind the channel. " + mLoginInfo.chid + ", " + mLoginInfo.userId;
  }
  
  public void process()
  {
    if ((mLoginInfo.status != PushClientsManager.ClientStatus.unbind) && (XMPushService.access$400(this$0) != null)) {}
    try
    {
      XMPushService.access$400(this$0).unbind(mLoginInfo.chid, mLoginInfo.userId);
      mLoginInfo.setStatus(PushClientsManager.ClientStatus.unbind, mNotifyType, 0, reason, kickType);
      return;
    }
    catch (XMPPException localXMPPException)
    {
      for (;;)
      {
        MyLog.e(localXMPPException);
        this$0.disconnect(10, localXMPPException);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.XMPushService.UnbindJob
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */