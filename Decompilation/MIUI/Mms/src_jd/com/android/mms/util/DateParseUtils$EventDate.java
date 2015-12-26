package com.android.mms.util;

import java.util.Date;

public class DateParseUtils$EventDate
{
  private Date mDate;
  private int mHourAlpha;
  private boolean mIsAllDayEvent = false;
  
  public DateParseUtils$EventDate(boolean paramBoolean, Date paramDate, int paramInt)
  {
    mIsAllDayEvent = paramBoolean;
    mDate = paramDate;
    mHourAlpha = paramInt;
  }
  
  public Date getDate()
  {
    return mDate;
  }
  
  public int getHourAlpha()
  {
    return mHourAlpha;
  }
  
  public boolean isAllDayEvent()
  {
    return mIsAllDayEvent;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.DateParseUtils.EventDate
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */