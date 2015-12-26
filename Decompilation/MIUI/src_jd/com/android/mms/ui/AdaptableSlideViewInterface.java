package com.android.mms.ui;

public abstract interface AdaptableSlideViewInterface
  extends SlideViewInterface
{
  public abstract void setImageRegion(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public abstract void setOnSizeChangedListener(OnSizeChangedListener paramOnSizeChangedListener);
  
  public abstract void setTextRegion(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public abstract void setVideoRegion(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public static abstract interface OnSizeChangedListener
  {
    public abstract void onSizeChanged(int paramInt1, int paramInt2);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AdaptableSlideViewInterface
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */