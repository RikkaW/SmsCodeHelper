package com.xiaomi.stats;

import com.xiaomi.push.thrift.ChannelStatsType;
import com.xiaomi.smack.ConnectionHelper;
import com.xiaomi.smack.XMPPException;
import java.net.UnknownHostException;

final class StatsAnalyser
{
  private static void checkNull(Exception paramException)
  {
    if (paramException == null) {
      throw new NullPointerException();
    }
  }
  
  static TypeWraper fromBind(Exception paramException)
  {
    checkNull(paramException);
    Object localObject2 = paramException;
    Object localObject1 = localObject2;
    if ((paramException instanceof XMPPException))
    {
      localObject1 = localObject2;
      if (((XMPPException)paramException).getWrappedThrowable() != null) {
        localObject1 = ((XMPPException)paramException).getWrappedThrowable();
      }
    }
    localObject2 = new TypeWraper();
    paramException = ((Throwable)localObject1).getMessage();
    if (((Throwable)localObject1).getCause() != null) {
      paramException = ((Throwable)localObject1).getCause().getMessage();
    }
    int i = ConnectionHelper.asErrorCode((Throwable)localObject1);
    localObject1 = localObject1.getClass().getSimpleName() + ":" + paramException;
    switch (i)
    {
    default: 
      type = ChannelStatsType.BIND_XMPP_ERR;
    }
    for (;;)
    {
      if ((type == ChannelStatsType.BIND_TCP_ERR) || (type == ChannelStatsType.BIND_XMPP_ERR) || (type == ChannelStatsType.BIND_BOSH_ERR)) {
        annotation = ((String)localObject1);
      }
      return (TypeWraper)localObject2;
      type = ChannelStatsType.BIND_TCP_READ_TIMEOUT;
      continue;
      type = ChannelStatsType.BIND_TCP_CONNRESET;
      continue;
      type = ChannelStatsType.BIND_TCP_BROKEN_PIPE;
      continue;
      type = ChannelStatsType.BIND_TCP_ERR;
      continue;
      type = ChannelStatsType.BIND_BOSH_ERR;
      if (paramException.startsWith("Terminal binding condition encountered: item-not-found")) {
        type = ChannelStatsType.BIND_BOSH_ITEM_NOT_FOUND;
      }
    }
  }
  
  static TypeWraper fromConnectionException(Exception paramException)
  {
    checkNull(paramException);
    Object localObject2 = paramException;
    Object localObject1 = localObject2;
    if ((paramException instanceof XMPPException))
    {
      localObject1 = localObject2;
      if (((XMPPException)paramException).getWrappedThrowable() != null) {
        localObject1 = ((XMPPException)paramException).getWrappedThrowable();
      }
    }
    localObject2 = new TypeWraper();
    paramException = ((Throwable)localObject1).getMessage();
    if (((Throwable)localObject1).getCause() != null) {
      paramException = ((Throwable)localObject1).getCause().getMessage();
    }
    int i = ConnectionHelper.asErrorCode((Throwable)localObject1);
    paramException = localObject1.getClass().getSimpleName() + ":" + paramException;
    if (i != 0)
    {
      type = ChannelStatsType.findByValue(ChannelStatsType.CONN_SUCCESS.getValue() + i);
      if (type == ChannelStatsType.CONN_BOSH_ERR)
      {
        localObject1 = ((Throwable)localObject1).getCause();
        if ((localObject1 == null) || (!(localObject1 instanceof UnknownHostException))) {}
      }
    }
    for (type = ChannelStatsType.CONN_BOSH_UNKNOWNHOST;; type = ChannelStatsType.CONN_XMPP_ERR)
    {
      if ((type == ChannelStatsType.CONN_TCP_ERR_OTHER) || (type == ChannelStatsType.CONN_XMPP_ERR) || (type == ChannelStatsType.CONN_BOSH_ERR)) {
        annotation = paramException;
      }
      return (TypeWraper)localObject2;
    }
  }
  
  static TypeWraper fromDisconnectEx(Exception paramException)
  {
    checkNull(paramException);
    Object localObject2 = paramException;
    Object localObject1 = localObject2;
    if ((paramException instanceof XMPPException))
    {
      localObject1 = localObject2;
      if (((XMPPException)paramException).getWrappedThrowable() != null) {
        localObject1 = ((XMPPException)paramException).getWrappedThrowable();
      }
    }
    paramException = new TypeWraper();
    localObject2 = ((Throwable)localObject1).getMessage();
    int i = ConnectionHelper.asErrorCode((Throwable)localObject1);
    localObject1 = localObject1.getClass().getSimpleName() + ":" + (String)localObject2;
    switch (i)
    {
    default: 
      type = ChannelStatsType.CHANNEL_XMPPEXCEPTION;
    }
    for (;;)
    {
      if ((type == ChannelStatsType.CHANNEL_TCP_ERR) || (type == ChannelStatsType.CHANNEL_XMPPEXCEPTION) || (type == ChannelStatsType.CHANNEL_BOSH_EXCEPTION)) {
        annotation = ((String)localObject1);
      }
      return paramException;
      type = ChannelStatsType.CHANNEL_TCP_READTIMEOUT;
      continue;
      type = ChannelStatsType.CHANNEL_TCP_CONNRESET;
      continue;
      type = ChannelStatsType.CHANNEL_TCP_BROKEN_PIPE;
      continue;
      type = ChannelStatsType.CHANNEL_TCP_ERR;
      continue;
      type = ChannelStatsType.CHANNEL_BOSH_EXCEPTION;
      if (((String)localObject2).startsWith("Terminal binding condition encountered: item-not-found")) {
        type = ChannelStatsType.CHANNEL_BOSH_ITEMNOTFIND;
      }
    }
  }
  
  static TypeWraper fromGslbException(Exception paramException)
  {
    checkNull(paramException);
    Object localObject2 = paramException;
    Object localObject1 = localObject2;
    if ((paramException instanceof XMPPException))
    {
      localObject1 = localObject2;
      if (((XMPPException)paramException).getWrappedThrowable() != null) {
        localObject1 = ((XMPPException)paramException).getWrappedThrowable();
      }
    }
    localObject2 = new TypeWraper();
    paramException = ((Throwable)localObject1).getMessage();
    if (((Throwable)localObject1).getCause() != null) {
      paramException = ((Throwable)localObject1).getCause().getMessage();
    }
    paramException = localObject1.getClass().getSimpleName() + ":" + paramException;
    int i = ConnectionHelper.asErrorCode((Throwable)localObject1);
    if (i != 0) {
      type = ChannelStatsType.findByValue(ChannelStatsType.GSLB_REQUEST_SUCCESS.getValue() + i);
    }
    if (type == null) {
      type = ChannelStatsType.GSLB_TCP_ERR_OTHER;
    }
    if (type == ChannelStatsType.GSLB_TCP_ERR_OTHER) {
      annotation = paramException;
    }
    return (TypeWraper)localObject2;
  }
  
  static class TypeWraper
  {
    String annotation;
    ChannelStatsType type;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.stats.StatsAnalyser
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */