package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.XMPPException;
import com.xiaomi.smack.packet.Message;

public class BatchSendMessageJob
  extends XMPushService.Job
{
  private Message[] mMessages;
  private XMPushService pushService = null;
  
  public BatchSendMessageJob(XMPushService paramXMPushService, Message[] paramArrayOfMessage)
  {
    super(4);
    pushService = paramXMPushService;
    mMessages = paramArrayOfMessage;
  }
  
  public String getDesc()
  {
    return "batch send message.";
  }
  
  public void process()
  {
    try
    {
      pushService.batchSendPacket(mMessages);
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
 * Qualified Name:     com.xiaomi.push.service.BatchSendMessageJob
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */