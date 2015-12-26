package com.android.mms.ui;

import miui.app.OnStatusBarChangeListener;

class SlideshowActivity$3
  implements OnStatusBarChangeListener
{
  SlideshowActivity$3(SlideshowActivity paramSlideshowActivity) {}
  
  public void onStatusBarHeightChange(int paramInt)
  {
    if (SlideshowActivity.access$200(this$0) == paramInt) {
      return;
    }
    SlideshowActivity.access$300(this$0).setStatusBarHeight(paramInt);
    SlideshowActivity.access$300(this$0).setPadding(SlideshowActivity.access$300(this$0).getPaddingLeft(), SlideshowActivity.access$300(this$0).getPaddingTop() - SlideshowActivity.access$200(this$0) + paramInt, SlideshowActivity.access$300(this$0).getPaddingRight(), SlideshowActivity.access$300(this$0).getPaddingBottom());
    SlideshowActivity.access$202(this$0, paramInt);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowActivity.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */