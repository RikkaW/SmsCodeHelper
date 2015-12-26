package com.google.common.base;

public abstract class Ticker
{
  private static final Ticker SYSTEM_TICKER = new Ticker()
  {
    public long read()
    {
      return Platform.systemNanoTime();
    }
  };
  
  public static Ticker systemTicker()
  {
    return SYSTEM_TICKER;
  }
  
  public abstract long read();
}

/* Location:
 * Qualified Name:     com.google.common.base.Ticker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */