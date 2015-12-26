package com.xiaomi.smack.util;

import android.text.TextUtils;

class TrafficUtils$TrafficInfo
{
  public long bytes = 0L;
  public String imsi = "";
  public long messageTs = 0L;
  public int networkType = -1;
  public String packageName = "";
  public int rcv = -1;
  
  public TrafficUtils$TrafficInfo(String paramString1, long paramLong1, int paramInt1, int paramInt2, String paramString2, long paramLong2)
  {
    packageName = paramString1;
    messageTs = paramLong1;
    networkType = paramInt1;
    rcv = paramInt2;
    imsi = paramString2;
    bytes = paramLong2;
  }
  
  public boolean canAccumulate(TrafficInfo paramTrafficInfo)
  {
    return (TextUtils.equals(packageName, packageName)) && (TextUtils.equals(imsi, imsi)) && (networkType == networkType) && (rcv == rcv) && (Math.abs(messageTs - messageTs) <= 5000L);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.util.TrafficUtils.TrafficInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */