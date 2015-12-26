package com.meizu.common.widget;

class ProgressBar$RefreshProgressRunnable
  implements Runnable
{
  private boolean mFromUser;
  private int mId;
  private int mProgress;
  
  ProgressBar$RefreshProgressRunnable(ProgressBar paramProgressBar, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    mId = paramInt1;
    mProgress = paramInt2;
    mFromUser = paramBoolean;
  }
  
  public void run()
  {
    ProgressBar.access$000(this$0, mId, mProgress, mFromUser, true);
    ProgressBar.access$102(this$0, this);
  }
  
  public void setup(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    mId = paramInt1;
    mProgress = paramInt2;
    mFromUser = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.ProgressBar.RefreshProgressRunnable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */