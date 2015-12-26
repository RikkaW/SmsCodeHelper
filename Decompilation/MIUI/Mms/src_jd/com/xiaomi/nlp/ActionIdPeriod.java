package com.xiaomi.nlp;

public class ActionIdPeriod
{
  private int actionId;
  private long endPeriodOfValidity = 4102416000000L;
  private long startPeriodOfValidity = 1104508800000L;
  
  public ActionIdPeriod(int paramInt)
  {
    actionId = paramInt;
  }
  
  public ActionIdPeriod(int paramInt, long paramLong1, long paramLong2)
  {
    actionId = paramInt;
    startPeriodOfValidity = paramLong1;
    endPeriodOfValidity = paramLong2;
  }
  
  public int getActionId()
  {
    return actionId;
  }
  
  public boolean matchPeriod()
  {
    long l = System.currentTimeMillis();
    return (startPeriodOfValidity <= l) && (endPeriodOfValidity >= l);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.nlp.ActionIdPeriod
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */