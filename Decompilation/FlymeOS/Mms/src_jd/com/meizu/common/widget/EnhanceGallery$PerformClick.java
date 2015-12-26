package com.meizu.common.widget;

import android.view.View;
import android.widget.SpinnerAdapter;

class EnhanceGallery$PerformClick
  extends EnhanceGallery.WindowRunnnable
  implements Runnable
{
  int mClickMotionPosition;
  
  private EnhanceGallery$PerformClick(EnhanceGallery paramEnhanceGallery)
  {
    super(paramEnhanceGallery, null);
  }
  
  public void run()
  {
    if (this$0.mDataChanged) {}
    SpinnerAdapter localSpinnerAdapter;
    int i;
    View localView;
    do
    {
      do
      {
        return;
        localSpinnerAdapter = this$0.getAdapter();
        i = mClickMotionPosition;
      } while ((localSpinnerAdapter == null) || (this$0.mItemCount <= 0) || (i == -1) || (i >= localSpinnerAdapter.getCount()) || (!sameWindow()));
      localView = this$0.getChildAt(i - this$0.mFirstPosition);
    } while (localView == null);
    this$0.performItemClicks(localView, i, localSpinnerAdapter.getItemId(i));
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.EnhanceGallery.PerformClick
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */