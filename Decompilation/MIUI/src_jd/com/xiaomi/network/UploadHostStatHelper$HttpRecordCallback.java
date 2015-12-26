package com.xiaomi.network;

import com.xiaomi.common.logger.thrift.mfs.HttpApi;
import java.util.List;

public abstract interface UploadHostStatHelper$HttpRecordCallback
{
  public abstract List<HttpApi> generateStat();
  
  public abstract double getPercentage();
}

/* Location:
 * Qualified Name:     com.xiaomi.network.UploadHostStatHelper.HttpRecordCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */