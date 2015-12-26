package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.XMPPException;
import com.xiaomi.xmpush.thrift.PushMetaInfo;
import com.xiaomi.xmpush.thrift.XmPushActionContainer;

final class MIPushEventProcessor$6
  extends XMPushService.Job
{
  MIPushEventProcessor$6(int paramInt, XMPushService paramXMPushService, XmPushActionContainer paramXmPushActionContainer, String paramString1, String paramString2)
  {
    super(paramInt);
  }
  
  public String getDesc()
  {
    return "send wrong message ack for message.";
  }
  
  public void process()
  {
    try
    {
      XmPushActionContainer localXmPushActionContainer = MIPushEventProcessor.access$000(val$pushService, val$container);
      metaInfo.putToExtra("error", val$error);
      metaInfo.putToExtra("reason", val$reason);
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
 * Qualified Name:     com.xiaomi.push.service.MIPushEventProcessor.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */