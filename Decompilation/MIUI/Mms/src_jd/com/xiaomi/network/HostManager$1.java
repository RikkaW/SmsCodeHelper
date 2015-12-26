package com.xiaomi.network;

import com.xiaomi.common.logger.thrift.mfs.HttpApi;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

class HostManager$1
  implements UploadHostStatHelper.HttpRecordCallback
{
  HostManager$1(HostManager paramHostManager) {}
  
  public List<HttpApi> generateStat()
  {
    try
    {
      ArrayList localArrayList = this$0.generateHostStats();
      return localArrayList;
    }
    catch (JSONException localJSONException) {}
    return null;
  }
  
  public double getPercentage()
  {
    Fallback localFallback = this$0.getFallbacksByHost("f3.mi-stat.gslb.mi-idc.com");
    double d = 0.1D;
    if (localFallback != null) {
      d = localFallback.getPercent();
    }
    return d;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.network.HostManager.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */