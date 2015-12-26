package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.XMPPException;

class XMPushService$6
  extends XMPushService.Job
{
  XMPushService$6(XMPushService paramXMPushService, int paramInt, String paramString, byte[] paramArrayOfByte)
  {
    super(paramInt);
  }
  
  public String getDesc()
  {
    return "send mi push message";
  }
  
  public void process()
  {
    try
    {
      this$0.sendMIPushPacket(val$packageName, val$payload);
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
 * Qualified Name:     com.xiaomi.push.service.XMPushService.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */