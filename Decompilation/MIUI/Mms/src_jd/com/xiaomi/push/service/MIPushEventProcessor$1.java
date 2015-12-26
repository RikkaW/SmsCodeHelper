package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.XMPPException;
import com.xiaomi.xmpush.thrift.XmPushActionContainer;

final class MIPushEventProcessor$1
  extends XMPushService.Job
{
  MIPushEventProcessor$1(int paramInt, XMPushService paramXMPushService, XmPushActionContainer paramXmPushActionContainer)
  {
    super(paramInt);
  }
  
  public String getDesc()
  {
    return "send app absent message.";
  }
  
  public void process()
  {
    try
    {
      val$pushService.sendMIPushPacket(val$pushService.contructAppAbsentMessage(val$container.getPackageName(), val$container.getAppid()));
      return;
    }
    catch (XMPPException localXMPPException)
    {
      MyLog.e(localXMPPException);
      val$pushService.disconnect(10, localXMPPException);
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.MIPushEventProcessor.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */