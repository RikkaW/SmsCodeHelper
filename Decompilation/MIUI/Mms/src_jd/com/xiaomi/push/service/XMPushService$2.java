package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.protobuf.ChannelMessage.PushServiceConfigMsg;
import com.xiaomi.smack.ConnectionConfiguration;
import com.xiaomi.smack.HttpRequestProxy;
import java.util.Map;

class XMPushService$2
  extends ConnectionConfiguration
{
  XMPushService$2(XMPushService paramXMPushService, Map paramMap, int paramInt, String paramString, HttpRequestProxy paramHttpRequestProxy)
  {
    super(paramMap, paramInt, paramString, paramHttpRequestProxy);
  }
  
  public byte[] getConnectionBlob()
  {
    try
    {
      Object localObject = new ChannelMessage.PushServiceConfigMsg();
      ((ChannelMessage.PushServiceConfigMsg)localObject).setClientVersion(ServiceConfig.getInstance().getConfigVersion());
      localObject = ((ChannelMessage.PushServiceConfigMsg)localObject).toByteArray();
      return (byte[])localObject;
    }
    catch (Exception localException)
    {
      MyLog.warn("getOBBString err: " + localException.toString());
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.XMPushService.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */