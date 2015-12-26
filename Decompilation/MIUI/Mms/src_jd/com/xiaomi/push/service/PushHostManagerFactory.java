package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostFilter;
import com.xiaomi.network.HostManager;
import com.xiaomi.network.HostManager.HostManagerFactory;
import com.xiaomi.network.HostManager.HttpGet;
import com.xiaomi.network.HostManagerV2;
import com.xiaomi.push.protobuf.ChannelConfig.PushServiceConfig;
import com.xiaomi.push.protobuf.ChannelMessage.PushServiceConfigMsg;
import com.xiaomi.push.thrift.ChannelStatsType;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.ConnectionConfiguration;
import com.xiaomi.smack.util.SystemUtils;
import com.xiaomi.stats.StatsHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class PushHostManagerFactory
  extends ServiceConfig.Listener
  implements HostManager.HostManagerFactory
{
  private long lastFetchTime;
  private XMPushService pushService;
  
  PushHostManagerFactory(XMPushService paramXMPushService)
  {
    pushService = paramXMPushService;
  }
  
  public static void init(XMPushService paramXMPushService)
  {
    PushHostManagerFactory localPushHostManagerFactory = new PushHostManagerFactory(paramXMPushService);
    ServiceConfig.getInstance().addListener(localPushHostManagerFactory);
    ChannelConfig.PushServiceConfig localPushServiceConfig = ServiceConfig.getInstance().getConfig();
    boolean bool2 = true;
    boolean bool1 = bool2;
    if (localPushServiceConfig != null)
    {
      bool1 = bool2;
      if (localPushServiceConfig.hasUseBucketV2()) {
        bool1 = localPushServiceConfig.hasUseBucketV2();
      }
    }
    if (bool1) {
      HostManager.setHostManagerFactory(localPushHostManagerFactory);
    }
    HostManager.init(paramXMPushService, null, new GslbHttpGet(), "0", "push", "2.2");
  }
  
  public HostManager createHostManager(Context paramContext, HostFilter paramHostFilter, HostManager.HttpGet paramHttpGet, String paramString)
  {
    return new PushHostManager(paramContext, paramHostFilter, paramHttpGet, paramString);
  }
  
  public void onConfigChange(ChannelConfig.PushServiceConfig paramPushServiceConfig)
  {
    if (paramPushServiceConfig.hasUseBucketV2())
    {
      MyLog.warn("Switch to BucketV2 :" + paramPushServiceConfig.getUseBucketV2());
      HostManager localHostManager = HostManager.getInstance();
      try
      {
        if (paramPushServiceConfig.getUseBucketV2()) {
          if (!(localHostManager instanceof HostManagerV2))
          {
            HostManager.setHostManagerFactory(this);
            HostManager.init(pushService, null, new GslbHttpGet(), "0", "push", "2.2");
          }
        }
        for (;;)
        {
          return;
          if ((HostManager.getInstance() instanceof HostManagerV2))
          {
            HostManager.setHostManagerFactory(null);
            HostManager.init(pushService, null, new GslbHttpGet(), "0", "push", "2.2");
          }
        }
        return;
      }
      finally {}
    }
  }
  
  public void onConfigMsgReceive(ChannelMessage.PushServiceConfigMsg paramPushServiceConfigMsg)
  {
    if ((paramPushServiceConfigMsg.hasFetchBucket()) && (System.currentTimeMillis() - lastFetchTime > 3600000L))
    {
      MyLog.warn("fetch bucket :" + paramPushServiceConfigMsg.getFetchBucket());
      lastFetchTime = System.currentTimeMillis();
      Object localObject = HostManager.getInstance();
      ((HostManager)localObject).clear();
      ((HostManager)localObject).refreshFallbacks();
      paramPushServiceConfigMsg = pushService.getCurrentConnection();
      if (paramPushServiceConfigMsg != null)
      {
        localObject = ((HostManager)localObject).getFallbacksByHost(paramPushServiceConfigMsg.getConfiguration().getHost());
        if (localObject != null)
        {
          localObject = ((Fallback)localObject).getHosts();
          int j = 1;
          Iterator localIterator = ((ArrayList)localObject).iterator();
          do
          {
            i = j;
            if (!localIterator.hasNext()) {
              break;
            }
          } while (!((String)localIterator.next()).equals(paramPushServiceConfigMsg.getHost()));
          int i = 0;
          if ((i != 0) && (!((ArrayList)localObject).isEmpty()))
          {
            MyLog.warn("bucket changed, force reconnect");
            pushService.disconnect(0, null);
            pushService.scheduleConnect(false);
          }
        }
      }
    }
  }
  
  static class GslbHttpGet
    implements HostManager.HttpGet
  {
    public String doGet(String paramString)
      throws IOException
    {
      paramString = new URL(paramString);
      if (paramString.getPort() == -1) {}
      for (i = 80;; i = paramString.getPort()) {
        try
        {
          long l1 = System.currentTimeMillis();
          String str = Network.downloadXml(SystemUtils.getContext(), paramString);
          long l2 = System.currentTimeMillis();
          StatsHelper.statsGslb(paramString.getHost() + ":" + i, (int)(l2 - l1), null);
          return str;
        }
        catch (IOException localIOException)
        {
          StatsHelper.statsGslb(paramString.getHost() + ":" + i, -1, localIOException);
          throw localIOException;
        }
      }
    }
  }
  
  static class PushHostManager
    extends HostManagerV2
  {
    protected PushHostManager(Context paramContext, HostFilter paramHostFilter, HostManager.HttpGet paramHttpGet, String paramString)
    {
      super(paramHostFilter, paramHttpGet, paramString);
    }
    
    protected String getRemoteFallbackJSON(ArrayList<String> paramArrayList, String paramString1, String paramString2)
      throws IOException
    {
      try
      {
        paramArrayList = super.getRemoteFallbackJSON(paramArrayList, paramString1, paramString2);
        return paramArrayList;
      }
      catch (IOException paramArrayList)
      {
        StatsHelper.stats(0, ChannelStatsType.GSLB_ERR.getValue(), 1, null);
        throw paramArrayList;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.PushHostManagerFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */