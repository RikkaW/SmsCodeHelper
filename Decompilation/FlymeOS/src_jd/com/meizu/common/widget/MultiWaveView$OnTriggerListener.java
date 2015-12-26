package com.meizu.common.widget;

import android.view.View;

public abstract interface MultiWaveView$OnTriggerListener
{
  public static final int CENTER_HANDLE = 1;
  public static final int NO_HANDLE = 0;
  
  public abstract void onFinishFinalAnimation();
  
  public abstract void onGrabbed(View paramView, int paramInt);
  
  public abstract void onGrabbedStateChange(View paramView, int paramInt);
  
  public abstract void onReleased(View paramView, int paramInt);
  
  public abstract void onTrigger(View paramView, int paramInt, boolean paramBoolean);
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MultiWaveView.OnTriggerListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */