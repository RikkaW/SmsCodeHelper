package com.meizu.common.widget;

import android.view.animation.DecelerateInterpolator;

class EnhanceGallery$FlingRunnable
  implements Runnable
{
  private int mLastDelta;
  private int mLastFlingX;
  private int mLastOverFlingX = 0;
  private OverScroller mScroller;
  
  public EnhanceGallery$FlingRunnable(EnhanceGallery paramEnhanceGallery)
  {
    mScroller = new OverScroller(paramEnhanceGallery.getContext());
    mScroller.setEnableMZOverScroll(true, true);
  }
  
  private void endFling(boolean paramBoolean)
  {
    mScroller.forceFinished(true);
    if (paramBoolean)
    {
      EnhanceGallery.access$1000(this$0);
      return;
    }
    this$0.reportScrollStateChange(0);
  }
  
  private void startCommon()
  {
    this$0.removeCallbacks(this);
  }
  
  public void run()
  {
    if (this$0.mItemCount == 0) {
      endFling(true);
    }
    OverScroller localOverScroller;
    do
    {
      return;
      localOverScroller = mScroller;
      switch (EnhanceGallery.access$200(this$0))
      {
      default: 
        EnhanceGallery.access$202(this$0, -1);
      }
    } while (EnhanceGallery.access$300(this$0) == 0);
    this$0.reportScrollStateChange(0);
    return;
    EnhanceGallery.access$1102(this$0, false);
    boolean bool1 = localOverScroller.computeScrollOffset();
    int i = localOverScroller.getCurrX();
    int j = mLastFlingX - i;
    boolean bool2 = this$0.trackMotionScroll(j);
    if ((bool1) && (!EnhanceGallery.access$1100(this$0)) && (!bool2))
    {
      mLastFlingX = i;
      mLastDelta = j;
      this$0.post(this);
      return;
    }
    if ((bool1) && (!EnhanceGallery.access$1100(this$0)) && (bool2))
    {
      endFling(false);
      if (EnhanceGallery.access$200(this$0) == 2) {
        EnhanceGallery.access$202(this$0, 4);
      }
      for (;;)
      {
        if (EnhanceGallery.access$300(this$0) != 2) {
          this$0.reportScrollStateChange(2);
        }
        startSpringback();
        return;
        EnhanceGallery.access$202(this$0, 3);
      }
    }
    endFling(true);
    return;
    if (localOverScroller.computeScrollOffset())
    {
      i = localOverScroller.getCurrX();
      j = i - mLastOverFlingX;
      mLastOverFlingX = i;
      if (j != 0) {
        this$0.trackMotionScroll(-j);
      }
      this$0.invalidate();
      this$0.postOnAnimation(this);
      return;
    }
    endFling(false);
    EnhanceGallery.access$202(this$0, -1);
  }
  
  public void startSpringback()
  {
    if (mScroller.springBack(EnhanceGallery.access$900(this$0), 0, 0, 0, 0, 0))
    {
      EnhanceGallery.access$202(this$0, 4);
      mLastOverFlingX = EnhanceGallery.access$900(this$0);
      this$0.invalidate();
      this$0.postOnAnimation(this);
      return;
    }
    EnhanceGallery.access$202(this$0, -1);
  }
  
  public void startUsingDistance(int paramInt)
  {
    if (paramInt == 0) {
      return;
    }
    EnhanceGallery.access$202(this$0, 2);
    startCommon();
    mLastFlingX = 0;
    mScroller.setInterpolator(new DecelerateInterpolator());
    mScroller.startScroll(0, 0, -paramInt, 0, EnhanceGallery.access$800(this$0));
    this$0.postOnAnimation(this);
  }
  
  public void stop(boolean paramBoolean)
  {
    this$0.removeCallbacks(this);
    endFling(paramBoolean);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.EnhanceGallery.FlingRunnable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */