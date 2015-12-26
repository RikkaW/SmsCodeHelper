package com.meizu.common.widget;

public abstract interface EnhanceGallery$OnScrollListener
{
  public static final int SCROLL_STATE_FLING = 2;
  public static final int SCROLL_STATE_IDLE = 0;
  public static final int SCROLL_STATE_TOUCH_SCROLL = 1;
  
  public abstract void onScroll(EnhanceGallery paramEnhanceGallery, int paramInt1, int paramInt2, int paramInt3);
  
  public abstract void onScrollStateChanged(EnhanceGallery paramEnhanceGallery, int paramInt);
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.EnhanceGallery.OnScrollListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */