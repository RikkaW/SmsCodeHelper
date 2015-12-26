package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.XMPPException;
import com.xiaomi.xmpush.thrift.PushMetaInfo;
import com.xiaomi.xmpush.thrift.XmPushActionContainer;

final class MIPushEventProcessor$3
  extends XMPushService.Job
{
  MIPushEventProcessor$3(int paramInt, XMPushService paramXMPushService, XmPushActionContainer paramXmPushActionContainer)
  {
    super(paramInt);
  }
  
  public String getDesc()
  {
    return "send ack message for obsleted message.";
  }
  
  public void process()
  {
    try
    {
      XmPushActionContainer localXmPushActionContainer = MIPushEventProcessor.access$000(val$pushService, val$container);
      localXmPushActionContainer.getMetaInfo().putToExtra("message_obsleted", "1");
      val$pushService.sendMIPushPacket(localXmPushActionContainer);
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
 * Qualified Name:     com.xiaomi.push.service.MIPushEventProcessor.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */