package com.xiaomi.push.service;

import com.xiaomi.push.protobuf.ChannelConfig.PushServiceConfig;
import com.xiaomi.push.protobuf.ChannelMessage.PushServiceConfigMsg;

public abstract class ServiceConfig$Listener
{
  public void onConfigChange(ChannelConfig.PushServiceConfig paramPushServiceConfig) {}
  
  public void onConfigMsgReceive(ChannelMessage.PushServiceConfigMsg paramPushServiceConfigMsg) {}
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.ServiceConfig.Listener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */