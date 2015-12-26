package com.xiaomi.stats;

import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.thrift.ChannelStatsType;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.ConnectionListener;

public class StatsContext
  implements ConnectionListener
{
  Connection connection;
  private Exception exception;
  XMPushService pushService;
  private int reason;
  
  StatsContext(XMPushService paramXMPushService)
  {
    pushService = paramXMPushService;
  }
  
  public void connectionClosed(Connection paramConnection, int paramInt, Exception paramException)
  {
    if ((reason == 0) && (exception == null))
    {
      reason = paramInt;
      exception = paramException;
      StatsHelper.connectionDown(paramConnection.getHost(), paramException);
    }
  }
  
  public void connectionStarted(Connection paramConnection)
  {
    reason = 0;
    exception = null;
    connection = paramConnection;
    StatsHelper.trackStart(0, ChannelStatsType.CONN_SUCCESS.getValue());
  }
  
  Exception getCaughtException()
  {
    return exception;
  }
  
  public void reconnectionFailed(Connection paramConnection, Exception paramException)
  {
    StatsHelper.stats(0, ChannelStatsType.CHANNEL_CON_FAIL.getValue(), 1, paramConnection.getHost());
  }
  
  public void reconnectionSuccessful(Connection paramConnection)
  {
    StatsHelper.trackEnd(0, ChannelStatsType.CONN_SUCCESS.getValue(), paramConnection.getHost(), paramConnection.getConnTryTimes());
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.stats.StatsContext
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */