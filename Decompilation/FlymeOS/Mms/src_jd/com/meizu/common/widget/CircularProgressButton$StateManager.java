package com.meizu.common.widget;

class CircularProgressButton$StateManager
{
  private boolean mIsEnabled;
  private int mProgress;
  
  public CircularProgressButton$StateManager(CircularProgressButton paramCircularProgressButton1, CircularProgressButton paramCircularProgressButton2)
  {
    mIsEnabled = paramCircularProgressButton2.isEnabled();
    mProgress = paramCircularProgressButton2.getProgress();
  }
  
  public void checkState(CircularProgressButton paramCircularProgressButton)
  {
    if (paramCircularProgressButton.getProgress() != getProgress()) {
      paramCircularProgressButton.setProgress(paramCircularProgressButton.getProgress());
    }
    while (paramCircularProgressButton.isEnabled() == isEnabled()) {
      return;
    }
    paramCircularProgressButton.setEnabled(paramCircularProgressButton.isEnabled());
  }
  
  public int getProgress()
  {
    return mProgress;
  }
  
  public boolean isEnabled()
  {
    return mIsEnabled;
  }
  
  public void saveProgress(CircularProgressButton paramCircularProgressButton)
  {
    mProgress = paramCircularProgressButton.getProgress();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CircularProgressButton.StateManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */