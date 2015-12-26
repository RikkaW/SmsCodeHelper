package com.meizu.common.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.SeekBar;

public class SkposSeekBar
  extends SeekBar
{
  private ValueAnimator mAnimator;
  private Interpolator mInterpolator;
  private float mOffset;
  private int mProcess = 0;
  
  public SkposSeekBar(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public SkposSeekBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }
  
  public SkposSeekBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }
  
  private void init()
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      mInterpolator = new PathInterpolator(0.33F, 0.0F, 0.1F, 1.0F);
      return;
    }
    mInterpolator = new LinearInterpolator();
  }
  
  private void startAnimation(int paramInt1, float paramFloat1, float paramFloat2, int paramInt2)
  {
    mAnimator = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
    mAnimator.addUpdateListener(new SkposSeekBar.1(this, paramFloat2, paramFloat1, paramInt1));
    mAnimator.setInterpolator(mInterpolator);
    mAnimator.setDuration(paramInt2);
    mAnimator.start();
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (getProgressDrawable() != null) {
      mOffset = (getProgressDrawable().getBounds().width() / getMax());
    }
  }
  
  public void setSikpProgress(int paramInt)
  {
    float f1 = getPaddingLeft();
    float f2 = paramInt;
    float f3 = mOffset;
    float f4 = getPaddingLeft();
    float f5 = getProgress();
    float f6 = mOffset;
    paramInt = getProgress();
    if ((mAnimator != null) && (mAnimator.isRunning()))
    {
      mAnimator.cancel();
      return;
    }
    startAnimation(paramInt, f4 + f5 * f6, f1 + f2 * f3, 384);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.SkposSeekBar
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */