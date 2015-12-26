package com.meizu.common.preference;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.LinearLayout.LayoutParams;

@SuppressLint({"NewApi"})
public class ExpandableListPreference$AnimHelper
{
  public static final int COLLAPSE = 1;
  public static final int EXPAND = 0;
  private float mEndAlpha;
  private int mEndBottomMargin;
  private int mEndHeight;
  private boolean mIsAnimating = false;
  private LinearLayout.LayoutParams mLayoutParams;
  private int mMarginTop = 0;
  private long mMillisTime;
  private View mRonateView;
  private float mStartAlpha;
  private int mStartBottomMargin;
  private View mSummaryView;
  private View mTarget;
  private int mType;
  
  public ExpandableListPreference$AnimHelper(ExpandableListPreference paramExpandableListPreference) {}
  
  private Interpolator getInterpolator()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return new PathInterpolator(0.33F, 0.0F, 0.1F, 1.0F);
    }
    return new AnimInterpolator(null);
  }
  
  public void animateHeightPrt()
  {
    if (mType == 0)
    {
      mStartBottomMargin = (-mEndHeight + mMarginTop);
      mEndBottomMargin = 0;
      mStartAlpha = 0.0F;
      mEndAlpha = 1.0F;
    }
    for (;;)
    {
      AnimatorSet localAnimatorSet = new AnimatorSet();
      ValueAnimator localValueAnimator1 = ValueAnimator.ofFloat(new float[] { mEndAlpha, mStartAlpha });
      localValueAnimator1.setDuration((int)(mMillisTime * 0.4D));
      if (mType == 1) {
        localValueAnimator1.setStartDelay((int)(mMillisTime * 0.6D));
      }
      localValueAnimator1.setInterpolator(getInterpolator());
      localValueAnimator1.addUpdateListener(new ExpandableListPreference.AnimHelper.1(this));
      localValueAnimator1.addListener(new ExpandableListPreference.AnimHelper.2(this));
      ValueAnimator localValueAnimator2 = ValueAnimator.ofFloat(new float[] { mStartAlpha, mEndAlpha });
      localValueAnimator2.setDuration((int)(mMillisTime * 0.5D));
      if (mType == 0) {
        localValueAnimator2.setStartDelay((int)(mMillisTime * 0.4D));
      }
      localValueAnimator2.setInterpolator(getInterpolator());
      localValueAnimator2.addUpdateListener(new ExpandableListPreference.AnimHelper.3(this));
      ValueAnimator localValueAnimator3 = ValueAnimator.ofInt(new int[] { mStartBottomMargin, mEndBottomMargin });
      localValueAnimator3.setInterpolator(getInterpolator());
      localValueAnimator3.addUpdateListener(new ExpandableListPreference.AnimHelper.4(this));
      localValueAnimator3.addListener(new ExpandableListPreference.AnimHelper.5(this, localValueAnimator3));
      localValueAnimator3.setDuration(mMillisTime);
      localAnimatorSet.playTogether(new Animator[] { localValueAnimator3, localValueAnimator2, localValueAnimator1 });
      localAnimatorSet.start();
      return;
      if (mType == 1)
      {
        mStartBottomMargin = 0;
        mEndBottomMargin = (-mEndHeight + mMarginTop);
        mStartAlpha = 1.0F;
        mEndAlpha = 0.0F;
      }
    }
  }
  
  public boolean iSAnimating()
  {
    return mIsAnimating;
  }
  
  public void init(View paramView1, View paramView2, View paramView3, int paramInt, long paramLong)
  {
    float f2 = 1.0F;
    mTarget = paramView1;
    mSummaryView = paramView3;
    mRonateView = paramView2;
    mType = paramInt;
    mMillisTime = paramLong;
    mLayoutParams = ((LinearLayout.LayoutParams)mTarget.getLayoutParams());
    mEndHeight = mLayoutParams.height;
    if (mType == 0)
    {
      mLayoutParams.bottomMargin = (-mEndHeight);
      mTarget.setVisibility(0);
      paramView1 = mTarget;
      if (mType != 0) {
        break label145;
      }
      f1 = 0.0F;
      label97:
      paramView1.setAlpha(f1);
      mSummaryView.setVisibility(0);
      paramView1 = mSummaryView;
      if (mType != 0) {
        break label151;
      }
    }
    label145:
    label151:
    for (float f1 = f2;; f1 = 0.0F)
    {
      paramView1.setAlpha(f1);
      return;
      mLayoutParams.bottomMargin = 0;
      break;
      f1 = 1.0F;
      break label97;
    }
  }
  
  public void setMarginTop(int paramInt)
  {
    mMarginTop = paramInt;
  }
  
  class AnimInterpolator
    implements Interpolator
  {
    private AnimInterpolator() {}
    
    public float getInterpolation(float paramFloat)
    {
      return (float)(1.0D - Math.pow(1.0F - paramFloat, 3.0D));
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.preference.ExpandableListPreference.AnimHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */