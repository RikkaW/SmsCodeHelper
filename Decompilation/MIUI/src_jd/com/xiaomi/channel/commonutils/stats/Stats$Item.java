package com.xiaomi.channel.commonutils.stats;

public class Stats$Item
{
  private static final Stats sStats = new Stats();
  public String annotation;
  public int key;
  public Object obj;
  
  Stats$Item(int paramInt, Object paramObject)
  {
    key = paramInt;
    obj = paramObject;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.channel.commonutils.stats.Stats.Item
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */