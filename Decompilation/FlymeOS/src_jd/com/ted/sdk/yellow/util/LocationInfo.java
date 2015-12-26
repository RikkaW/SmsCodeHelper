package com.ted.sdk.yellow.util;

public class LocationInfo
{
  private static LocationInfo mSelf;
  private String lat = "0";
  private String lnt = "0";
  
  public static LocationInfo getInstance()
  {
    if (mSelf == null) {}
    try
    {
      if (mSelf == null) {
        mSelf = new LocationInfo();
      }
      return mSelf;
    }
    finally {}
  }
  
  public String getLatitude()
  {
    return lat;
  }
  
  public String getLongitude()
  {
    return lnt;
  }
  
  public void setLatitude(String paramString)
  {
    lat = paramString;
  }
  
  public void setLongitude(String paramString)
  {
    lnt = paramString;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.util.LocationInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */