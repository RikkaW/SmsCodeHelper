package com.meizu.common.widget;

import android.animation.ObjectAnimator;
import java.util.ArrayList;

class MultiWaveView$AnimationBundle
  extends ArrayList<MultiWaveView.Tweener>
{
  private static final long serialVersionUID = -6319262269245852568L;
  private boolean mSuspended;
  
  private MultiWaveView$AnimationBundle(MultiWaveView paramMultiWaveView) {}
  
  public void cancel()
  {
    int j = size();
    int i = 0;
    while (i < j)
    {
      getanimator.cancel();
      i += 1;
    }
    clear();
  }
  
  public void setSuspended(boolean paramBoolean)
  {
    mSuspended = paramBoolean;
  }
  
  public void start()
  {
    if (mSuspended) {}
    for (;;)
    {
      return;
      int j = size();
      int i = 0;
      while (i < j)
      {
        getanimator.start();
        i += 1;
      }
    }
  }
  
  public void stop()
  {
    int j = size();
    int i = 0;
    while (i < j)
    {
      getanimator.end();
      i += 1;
    }
    clear();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MultiWaveView.AnimationBundle
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */