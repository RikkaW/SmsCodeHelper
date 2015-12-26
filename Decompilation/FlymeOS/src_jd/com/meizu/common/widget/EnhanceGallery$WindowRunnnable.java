package com.meizu.common.widget;

class EnhanceGallery$WindowRunnnable
{
  private int mOriginalAttachCount;
  
  private EnhanceGallery$WindowRunnnable(EnhanceGallery paramEnhanceGallery) {}
  
  public void rememberWindowAttachCount()
  {
    mOriginalAttachCount = EnhanceGallery.access$2000(this$0);
  }
  
  public boolean sameWindow()
  {
    return (this$0.hasWindowFocus()) && (EnhanceGallery.access$2100(this$0) == mOriginalAttachCount);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.EnhanceGallery.WindowRunnnable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */