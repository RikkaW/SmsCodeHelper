package com.ted.sdk.yellow.entry;

import android.graphics.Color;
import android.text.TextUtils;
import com.ted.android.contacts.netparser.model.NumItem.IconData;

public class CallerIdItem$IconData
{
  public static final int INVALID_COLOR = -1;
  private String mBgColor;
  private String mDescription;
  private String mFgColor;
  
  CallerIdItem$IconData(NumItem.IconData paramIconData)
  {
    mBgColor = a;
    mFgColor = b;
    mDescription = c;
  }
  
  public int getBackgroundColor()
  {
    if (!TextUtils.isEmpty(mBgColor)) {
      return Color.parseColor("#" + mBgColor);
    }
    return -1;
  }
  
  public String getDescription()
  {
    return mDescription;
  }
  
  public int getForegroundColor()
  {
    if (!TextUtils.isEmpty(mFgColor)) {
      return Color.parseColor("#" + mFgColor);
    }
    return -1;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.entry.CallerIdItem.IconData
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */