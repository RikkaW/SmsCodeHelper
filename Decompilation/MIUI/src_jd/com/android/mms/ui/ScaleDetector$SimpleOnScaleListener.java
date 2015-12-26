package com.android.mms.ui;

public abstract class ScaleDetector$SimpleOnScaleListener
  implements ScaleDetector.OnScaleListener
{
  private float mMaxTextSize;
  private float mMinTextSize;
  private float mTextSize = 0.0F;
  
  public ScaleDetector$SimpleOnScaleListener(float paramFloat1, float paramFloat2)
  {
    mMinTextSize = paramFloat1;
    mMaxTextSize = paramFloat2;
  }
  
  public boolean onScale(ScaleDetector paramScaleDetector)
  {
    float f2 = mTextSize * paramScaleDetector.getScaleFactor();
    if ((mTextSize > 0.0F) && (Math.abs(f2 - mTextSize) < 0.2F)) {
      return false;
    }
    float f1;
    if (f2 < mMinTextSize) {
      f1 = mMinTextSize;
    }
    for (;;)
    {
      mTextSize = f1;
      performChangeText(f1);
      return true;
      f1 = f2;
      if (f2 > mMaxTextSize) {
        f1 = mMaxTextSize;
      }
    }
  }
  
  public void onScaleEnd(ScaleDetector paramScaleDetector)
  {
    MessageUtils.setTextSize(mTextSize);
  }
  
  public boolean onScaleStart(ScaleDetector paramScaleDetector)
  {
    return true;
  }
  
  protected abstract void performChangeText(float paramFloat);
  
  public void setTextSize(float paramFloat)
  {
    mTextSize = paramFloat;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ScaleDetector.SimpleOnScaleListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */