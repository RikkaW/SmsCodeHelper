package com.meizu.common.widget;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;

class EnhanceSeekBar$DrawableXYHolder
{
  private Drawable mDrawable;
  
  public EnhanceSeekBar$DrawableXYHolder(EnhanceSeekBar paramEnhanceSeekBar) {}
  
  public EnhanceSeekBar$DrawableXYHolder(EnhanceSeekBar paramEnhanceSeekBar, Drawable paramDrawable)
  {
    mDrawable = paramDrawable;
  }
  
  public EnhanceSeekBar.XYHolder getXY()
  {
    if (mDrawable == null) {
      return null;
    }
    Rect localRect = mDrawable.getBounds();
    return new EnhanceSeekBar.XYHolder(this$0, left, top);
  }
  
  public void setDrawable(Drawable paramDrawable)
  {
    mDrawable = paramDrawable;
  }
  
  public void setXY(EnhanceSeekBar.XYHolder paramXYHolder)
  {
    if (mDrawable == null) {
      return;
    }
    mDrawable.setBounds((int)paramXYHolder.getX(), (int)paramXYHolder.getY(), (int)(paramXYHolder.getX() + mDrawable.getIntrinsicWidth()), (int)(paramXYHolder.getY() + mDrawable.getIntrinsicHeight()));
    this$0.invalidate();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.EnhanceSeekBar.DrawableXYHolder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */