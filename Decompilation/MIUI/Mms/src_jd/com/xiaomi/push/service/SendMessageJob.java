package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.XMPPException;
import com.xiaomi.smack.packet.Packet;

public class SendMessageJob
  extends XMPushService.Job
{
  private Packet mPacket;
  private XMPushService pushService = null;
  
  public SendMessageJob(XMPushService paramXMPushService, Packet paramPacket)
  {
    super(4);
    pushService = paramXMPushService;
    mPacket = paramPacket;
  }
  
  public String getDesc()
  {
    return "send a message.";
  }
  
  public void process()
  {
    try
    {
      pushService.sendPacket(mPacket);
      return;
    }
    catch (XMPPException localXMPPException)
    {
      MyLog.e(localXMPPException);
      pushService.disconnect(10, localXMPPException);
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.SendMessageJob
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */