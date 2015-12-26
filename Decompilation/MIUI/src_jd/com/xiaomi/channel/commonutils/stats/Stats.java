package com.xiaomi.channel.commonutils.stats;

import java.util.LinkedList;

public class Stats
{
  private LinkedList<Item> statsQueue = new LinkedList();
  
  private void checkSize()
  {
    if (statsQueue.size() > 100) {
      statsQueue.removeFirst();
    }
  }
  
  public static Stats instance()
  {
    return Item.sStats;
  }
  
  public int getCount()
  {
    try
    {
      int i = statsQueue.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public LinkedList<Item> getStats()
  {
    try
    {
      LinkedList localLinkedList = statsQueue;
      statsQueue = new LinkedList();
      return localLinkedList;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void stat(Object paramObject)
  {
    try
    {
      statsQueue.add(new Item(0, paramObject));
      checkSize();
      return;
    }
    finally
    {
      paramObject = finally;
      throw ((Throwable)paramObject);
    }
  }
  
  public static class Item
  {
    private static final Stats sStats = new Stats();
    public String annotation;
    public int key;
    public Object obj;
    
    Item(int paramInt, Object paramObject)
    {
      key = paramInt;
      obj = paramObject;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.channel.commonutils.stats.Stats
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */