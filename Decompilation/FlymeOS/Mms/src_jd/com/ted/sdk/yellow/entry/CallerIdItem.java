package com.ted.sdk.yellow.entry;

import android.graphics.Color;
import android.text.TextUtils;
import com.ted.android.contacts.netparser.model.NumItem.IconData;
import com.ted.android.contacts.netparser.model.NumItem.MarkerData;

public class CallerIdItem
  extends BaseItem
{
  private final String mDisplayName;
  private final IconData mExtraData;
  private final String mIconUri;
  private final MarkerData mMarkerData;
  private final String mSource;
  
  public CallerIdItem(String paramString1, String paramString2, String paramString3, NumItem.IconData paramIconData, String paramString4, NumItem.MarkerData paramMarkerData)
  {
    super(paramString1);
    mIconUri = paramString2;
    mDisplayName = paramString3;
    if (paramIconData == null)
    {
      paramString1 = null;
      mExtraData = paramString1;
      mSource = paramString4;
      if (paramMarkerData != null) {
        break label63;
      }
    }
    label63:
    for (paramString1 = (String)localObject;; paramString1 = new MarkerData(paramMarkerData))
    {
      mMarkerData = paramString1;
      return;
      paramString1 = new IconData(paramIconData);
      break;
    }
  }
  
  public IconData getIconExtra()
  {
    return mExtraData;
  }
  
  public String getIconUri()
  {
    return mIconUri;
  }
  
  public MarkerData getMarkerData()
  {
    return mMarkerData;
  }
  
  public String getName()
  {
    return mDisplayName;
  }
  
  public String getSource()
  {
    return mSource;
  }
  
  public static class IconData
  {
    public static final int INVALID_COLOR = -1;
    private String mBgColor;
    private String mDescription;
    private String mFgColor;
    
    IconData(NumItem.IconData paramIconData)
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
  
  public static class MarkerData
  {
    private final String mClassify;
    private final int mCounter;
    private final boolean mIsCustomMark;
    private final boolean mIsNoMark;
    private final boolean mIsUploaded;
    private final int mRiskLevel;
    
    MarkerData(NumItem.MarkerData paramMarkerData)
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
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.entry.CallerIdItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */