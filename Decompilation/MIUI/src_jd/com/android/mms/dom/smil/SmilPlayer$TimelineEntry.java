package com.android.mms.dom.smil;

import org.w3c.dom.smil.ElementTime;

final class SmilPlayer$TimelineEntry
{
  private final int mAction;
  private final ElementTime mElement;
  private final double mOffsetTime;
  
  public SmilPlayer$TimelineEntry(double paramDouble, ElementTime paramElementTime, int paramInt)
  {
    mOffsetTime = paramDouble;
    mElement = paramElementTime;
    mAction = paramInt;
  }
  
  public int getAction()
  {
    return mAction;
  }
  
  public ElementTime getElement()
  {
    return mElement;
  }
  
  public double getOffsetTime()
  {
    return mOffsetTime;
  }
  
  public String toString()
  {
    return "Type = " + mElement + " offset = " + getOffsetTime() + " action = " + getAction();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.smil.SmilPlayer.TimelineEntry
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */