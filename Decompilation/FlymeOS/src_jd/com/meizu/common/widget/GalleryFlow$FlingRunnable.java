package com.meizu.common.widget;

import android.view.animation.DecelerateInterpolator;

class GalleryFlow$FlingRunnable
  implements Runnable
{
  private int mLastFlingX;
  private OverScroller mScroller;
  
  public GalleryFlow$FlingRunnable(GalleryFlow paramGalleryFlow)
  {
    mScroller = new OverScroller(paramGalleryFlow.getContext());
    mScroller.setEnableMZOverScroll(true, true);
  }
  
  private void endFling(boolean paramBoolean)
  {
    mScroller.forceFinished(true);
    if (paramBoolean) {
      GalleryFlow.access$500(this$0);
    }
  }
  
  private void startCommon()
  {
    this$0.removeCallbacks(this);
  }
  
  public void run()
  {
    if (this$0.mItemCount == 0)
    {
      endFling(true);
      return;
    }
    GalleryFlow.access$602(this$0, false);
    OverScroller localOverScroller = mScroller;
    boolean bool = localOverScroller.computeScrollOffset();
    int j = localOverScroller.getCurrX();
    int i = mLastFlingX - j;
    if (i > 0) {}
    for (i = Math.min(GalleryFlow.access$700(this$0)[3], i);; i = Math.max(-GalleryFlow.access$700(this$0)[3], i))
    {
      if (i != 0) {
        this$0.trackMotionScroll(i);
      }
      if ((!bool) || (GalleryFlow.access$600(this$0))) {
        break;
      }
      mLastFlingX = j;
      this$0.post(this);
      return;
    }
    endFling(true);
  }
  
  public void startUsingDistance(int paramInt)
  {
    if (paramInt == 0) {
      return;
    }
    startCommon();
    mLastFlingX = 0;
    mScroller.setInterpolator(new DecelerateInterpolator());
    mScroller.startScroll(0, 0, -paramInt, 0, GalleryFlow.access$400(this$0));
    this$0.post(this);
  }
  
  public void startUsingVelocity(int paramInt)
  {
    int i = 4200;
    if ((paramInt == 0) || (Math.abs(paramInt) < 200)) {
      return;
    }
    startCommon();
    if (Math.abs(paramInt) > 4200) {
      if (paramInt > 0) {
        paramInt = i;
      }
    }
    for (;;)
    {
      if (paramInt < 0) {}
      for (i = Integer.MAX_VALUE;; i = 0)
      {
        mLastFlingX = i;
        mScroller.setInterpolator(null);
        mScroller.fling(i, 0, paramInt, 0, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
        this$0.post(this);
        return;
        paramInt = 61336;
        break;
      }
    }
  }
  
  public void stop(boolean paramBoolean)
  {
    this$0.removeCallbacks(this);
    endFling(paramBoolean);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.GalleryFlow.FlingRunnable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */