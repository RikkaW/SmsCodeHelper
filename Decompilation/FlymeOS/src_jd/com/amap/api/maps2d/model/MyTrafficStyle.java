package com.amap.api.maps2d.model;

public class MyTrafficStyle
{
  private int a = -16735735;
  private int b = -35576;
  private int c = -1441006;
  private int d = -7208950;
  
  public int getCongestedColor()
  {
    return c;
  }
  
  public int getSeriousCongestedColor()
  {
    return d;
  }
  
  public int getSlowColor()
  {
    return b;
  }
  
  public int getSmoothColor()
  {
    return a;
  }
  
  public void setCongestedColor(int paramInt)
  {
    c = paramInt;
  }
  
  public void setSeriousCongestedColor(int paramInt)
  {
    d = paramInt;
  }
  
  public void setSlowColor(int paramInt)
  {
    b = paramInt;
  }
  
  public void setSmoothColor(int paramInt)
  {
    a = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.MyTrafficStyle
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */