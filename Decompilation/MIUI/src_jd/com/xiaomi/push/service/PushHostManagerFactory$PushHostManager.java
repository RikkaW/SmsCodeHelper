package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.network.HostFilter;
import com.xiaomi.network.HostManager.HttpGet;
import com.xiaomi.network.HostManagerV2;
import com.xiaomi.push.thrift.ChannelStatsType;
import com.xiaomi.stats.StatsHelper;
import java.io.IOException;
import java.util.ArrayList;

class PushHostManagerFactory$PushHostManager
  extends HostManagerV2
{
  protected PushHostManagerFactory$PushHostManager(Context paramContext, HostFilter paramHostFilter, HostManager.HttpGet paramHttpGet, String paramString)
  {
    super(paramContext, paramHostFilter, paramHttpGet, paramString);
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

/* Location:
 * Qualified Name:     com.xiaomi.push.service.PushHostManagerFactory.PushHostManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */