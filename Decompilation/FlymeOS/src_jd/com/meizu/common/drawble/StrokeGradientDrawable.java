package com.meizu.common.drawble;

import android.graphics.drawable.GradientDrawable;

public class StrokeGradientDrawable
{
  private GradientDrawable mGradientDrawable;
  private int mStrokeColor;
  private int mStrokeWidth;
  
  public StrokeGradientDrawable(GradientDrawable paramGradientDrawable)
  {
    mGradientDrawable = paramGradientDrawable;
  }
  
  public GradientDrawable getGradientDrawable()
  {
    return mGradientDrawable;
  }
  
  public int getStrokeColor()
  {
    return mStrokeColor;
  }
  
  public int getStrokeWidth()
  {
    return mStrokeWidth;
  }
  
  public void setStrokeColor(int paramInt)
  {
    mStrokeColor = paramInt;
    mGradientDrawable.setStroke(getStrokeWidth(), paramInt);
  }
  
  public void setStrokeWidth(int paramInt)
  {
    mStrokeWidth = paramInt;
    mGradientDrawable.setStroke(paramInt, getStrokeColor());
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.drawble.StrokeGradientDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */