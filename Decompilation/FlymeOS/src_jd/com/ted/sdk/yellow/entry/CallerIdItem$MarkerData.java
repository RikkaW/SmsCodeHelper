package com.ted.sdk.yellow.entry;

import com.ted.android.contacts.netparser.model.NumItem.MarkerData;

public class CallerIdItem$MarkerData
{
  private final String mClassify;
  private final int mCounter;
  private final boolean mIsCustomMark;
  private final boolean mIsNoMark;
  private final boolean mIsUploaded;
  private final int mRiskLevel;
  
  CallerIdItem$MarkerData(NumItem.MarkerData paramMarkerData)
  {
    mCounter = a;
    mClassify = b;
    mRiskLevel = c;
    mIsNoMark = d;
    mIsUploaded = e;
    mIsCustomMark = f;
  }
  
  public String getClassify()
  {
    return mClassify;
  }
  
  public int getCounter()
  {
    return mCounter;
  }
  
  public int getRiskLevel()
  {
    return mRiskLevel;
  }
  
  public boolean isCustomMark()
  {
    return mIsCustomMark;
  }
  
  public boolean isNoMark()
  {
    return mIsNoMark;
  }
  
  public boolean isUploaded()
  {
    return mIsUploaded;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.entry.CallerIdItem.MarkerData
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */