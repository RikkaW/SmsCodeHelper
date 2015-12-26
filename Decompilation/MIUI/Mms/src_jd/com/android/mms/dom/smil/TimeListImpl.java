package com.android.mms.dom.smil;

import java.util.ArrayList;
import org.w3c.dom.smil.Time;
import org.w3c.dom.smil.TimeList;

public class TimeListImpl
  implements TimeList
{
  private final ArrayList<Time> mTimes;
  
  TimeListImpl(ArrayList<Time> paramArrayList)
  {
    mTimes = paramArrayList;
  }
  
  public int getLength()
  {
    return mTimes.size();
  }
  
  public Time item(int paramInt)
  {
    try
    {
      Time localTime = (Time)mTimes.get(paramInt);
      return localTime;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException) {}
    return null;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.smil.TimeListImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */