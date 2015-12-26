package com.android.mms.model;

public class RegionModel
  extends Model
{
  private String mBackgroundColor;
  private String mFit;
  private int mHeight;
  private int mLeft;
  private final String mRegionId;
  private int mTop;
  private int mWidth;
  
  public RegionModel(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this(paramString, "meet", paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public RegionModel(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this(paramString1, paramString2, paramInt1, paramInt2, paramInt3, paramInt4, null);
  }
  
  public RegionModel(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString3)
  {
    mRegionId = paramString1;
    mFit = paramString2;
    mLeft = paramInt1;
    mTop = paramInt2;
    mWidth = paramInt3;
    mHeight = paramInt4;
    mBackgroundColor = paramString3;
  }
  
  public String getBackgroundColor()
  {
    return mBackgroundColor;
  }
  
  public String getFit()
  {
    return mFit;
  }
  
  public int getHeight()
  {
    return mHeight;
  }
  
  public int getLeft()
  {
    return mLeft;
  }
  
  public String getRegionId()
  {
    return mRegionId;
  }
  
  public int getTop()
  {
    return mTop;
  }
  
  public int getWidth()
  {
    return mWidth;
  }
  
  public void setTop(int paramInt)
  {
    mTop = paramInt;
    notifyModelChanged(true);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.model.RegionModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */