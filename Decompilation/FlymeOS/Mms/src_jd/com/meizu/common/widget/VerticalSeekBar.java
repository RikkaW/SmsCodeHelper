package com.meizu.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.meizu.common.R.attr;
import com.meizu.common.R.styleable;

public class VerticalSeekBar
  extends AbsSeekBar
{
  private OnVerSeekBarChangeListener mOnSeekBarChangeListener;
  
  public VerticalSeekBar(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public VerticalSeekBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.MeizuCommon_VerticalSeekBarStyle);
  }
  
  public VerticalSeekBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SeekBar, paramInt, 0);
    setBreakPoint(paramContext.getInt(R.styleable.SeekBar_mcBreakPoint, 0));
    paramContext.recycle();
    setIsVertical(true);
    setTouchMode(1);
  }
  
  void onProgressRefresh(float paramFloat, boolean paramBoolean)
  {
    super.onProgressRefresh(paramFloat, paramBoolean);
    if (mOnSeekBarChangeListener != null) {
      mOnSeekBarChangeListener.onProgressChanged(this, getProgress(), paramBoolean);
    }
  }
  
  void onStartTrackingTouch()
  {
    super.onStartTrackingTouch();
    if (mOnSeekBarChangeListener != null) {
      mOnSeekBarChangeListener.onStartTrackingTouch(this);
    }
  }
  
  void onStopTrackingTouch()
  {
    super.onStopTrackingTouch();
    if (mOnSeekBarChangeListener != null) {
      mOnSeekBarChangeListener.onStopTrackingTouch(this);
    }
  }
  
  public void setOnSeekBarChangeListener(OnVerSeekBarChangeListener paramOnVerSeekBarChangeListener)
  {
    mOnSeekBarChangeListener = paramOnVerSeekBarChangeListener;
  }
  
  public static abstract interface OnVerSeekBarChangeListener
  {
    public abstract void onProgressChanged(VerticalSeekBar paramVerticalSeekBar, int paramInt, boolean paramBoolean);
    
    public abstract void onStartTrackingTouch(VerticalSeekBar paramVerticalSeekBar);
    
    public abstract void onStopTrackingTouch(VerticalSeekBar paramVerticalSeekBar);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.VerticalSeekBar
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */