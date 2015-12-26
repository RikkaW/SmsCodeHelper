package com.ted.android.contacts.netparser.model;

public class DealItem
{
  private float mCurrPrice;
  private String mDealImage;
  private String mDealName;
  private String mDescription;
  private int mEnd;
  private float mOrigPrice;
  private boolean mReservation;
  private int mStart;
  private String mUrl;
  
  public void enableReserved(boolean paramBoolean)
  {
    mReservation = paramBoolean;
  }
  
  public float getCurrPrice()
  {
    return mCurrPrice;
  }
  
  public String getDealImage()
  {
    return mDealImage;
  }
  
  public String getDealName()
  {
    return mDealName;
  }
  
  public String getDescription()
  {
    return mDescription;
  }
  
  public int getEndTime()
  {
    return mEnd;
  }
  
  public float getOrigPrice()
  {
    return mOrigPrice;
  }
  
  public int getStartTime()
  {
    return mStart;
  }
  
  public String getUrl()
  {
    return mUrl;
  }
  
  public boolean isReserved()
  {
    return mReservation;
  }
  
  public void setCurrPrice(float paramFloat)
  {
    mCurrPrice = paramFloat;
  }
  
  public void setDealImage(String paramString)
  {
    mDealImage = paramString;
  }
  
  public void setDealName(String paramString)
  {
    mDealName = paramString;
  }
  
  public void setDescription(String paramString)
  {
    mDescription = paramString;
  }
  
  public void setEndTime(int paramInt)
  {
    mEnd = paramInt;
  }
  
  public void setOrigPrice(float paramFloat)
  {
    mOrigPrice = paramFloat;
  }
  
  public void setStartTime(int paramInt)
  {
    mStart = paramInt;
  }
  
  public void setUrl(String paramString)
  {
    mUrl = paramString;
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.netparser.model.DealItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */