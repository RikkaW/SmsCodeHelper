package com.android.mms.layout;

public class HVGALayoutParameters
  implements LayoutParameters
{
  private int mType = -1;
  
  public HVGALayoutParameters(int paramInt)
  {
    if ((paramInt != 10) && (paramInt != 11)) {
      throw new IllegalArgumentException("Bad layout type detected: " + paramInt);
    }
    mType = paramInt;
  }
  
  public int getHeight()
  {
    if (mType == 10) {
      return 320;
    }
    return 480;
  }
  
  public int getImageHeight()
  {
    if (mType == 10) {
      return 240;
    }
    return 320;
  }
  
  public int getTextHeight()
  {
    if (mType == 10) {
      return 80;
    }
    return 160;
  }
  
  public int getWidth()
  {
    if (mType == 10) {
      return 480;
    }
    return 320;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.layout.HVGALayoutParameters
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */