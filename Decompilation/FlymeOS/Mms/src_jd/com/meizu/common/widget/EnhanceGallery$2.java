package com.meizu.common.widget;

import android.view.View;

class EnhanceGallery$2
  implements Runnable
{
  EnhanceGallery$2(EnhanceGallery paramEnhanceGallery) {}
  
  public void run()
  {
    View localView = this$0.getChildAt(this$0.getChildCount() - 1);
    int j = 0;
    int i;
    if (EnhanceGallery.access$100(this$0))
    {
      i = j;
      if (localView != null)
      {
        i = j;
        if (localView.getLeft() > this$0.getPaddingLeft()) {
          i = this$0.getPaddingLeft() - localView.getLeft();
        }
      }
    }
    for (;;)
    {
      EnhanceGallery.access$202(this$0, -1);
      if ((EnhanceGallery.access$300(this$0) != 2) && (i != 0)) {
        this$0.reportScrollStateChange(2);
      }
      EnhanceGallery.access$400(this$0).startUsingDistance(i);
      return;
      i = j;
      if (localView != null)
      {
        i = j;
        if (localView.getRight() < this$0.getWidth() - this$0.getPaddingRight()) {
          i = this$0.getWidth() - this$0.getPaddingRight() - localView.getRight();
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.EnhanceGallery.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */