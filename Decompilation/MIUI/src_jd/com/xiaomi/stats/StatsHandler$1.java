package com.xiaomi.stats;

import com.xiaomi.push.protobuf.ChannelMessage.PushServiceConfigMsg;
import com.xiaomi.push.service.ServiceConfig.Listener;

class StatsHandler$1
  extends ServiceConfig.Listener
{
  StatsHandler$1(StatsHandler paramStatsHandler) {}
  
  public void onConfigMsgReceive(ChannelMessage.PushServiceConfigMsg paramPushServiceConfigMsg)
  {
    if (paramPushServiceConfigMsg.hasDots()) {
      StatsHandler.getInstance().setDuration(paramPushServiceConfigMsg.getDots());
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.stats.StatsHandler.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */