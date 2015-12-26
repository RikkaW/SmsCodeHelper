package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.GradientDrawable;
import android.widget.TextView;
import com.meizu.common.drawble.StrokeGradientDrawable;

class CircularProgressButton$MorphingAnimation
{
  public static final int DURATION_INSTANT = 1;
  public static final int DURATION_NORMAL = 300;
  private AnimatorSet mAnimSet;
  private StrokeGradientDrawable mDrawable;
  private int mDuration;
  private int mFromColor;
  private float mFromCornerRadius;
  private int mFromStrokeColor;
  private int mFromWidth;
  private CircularProgressButton.OnAnimationEndListener mListener;
  private float mPadding;
  private int mToColor;
  private float mToCornerRadius;
  private int mToStrokeColor;
  private int mToWidth;
  private TextView mView;
  
  public CircularProgressButton$MorphingAnimation(CircularProgressButton paramCircularProgressButton, TextView paramTextView, StrokeGradientDrawable paramStrokeGradientDrawable)
  {
    mView = paramTextView;
    mDrawable = paramStrokeGradientDrawable;
  }
  
  public void cancelAllAnim()
  {
    mAnimSet.end();
    mAnimSet.removeAllListeners();
  }
  
  public void setDuration(int paramInt)
  {
    mDuration = paramInt;
  }
  
  public void setFromColor(int paramInt)
  {
    mFromColor = paramInt;
  }
  
  public void setFromCornerRadius(float paramFloat)
  {
    mFromCornerRadius = paramFloat;
  }
  
  public void setFromStrokeColor(int paramInt)
  {
    mFromStrokeColor = paramInt;
  }
  
  public void setFromWidth(int paramInt)
  {
    mFromWidth = paramInt;
  }
  
  public void setListener(CircularProgressButton.OnAnimationEndListener paramOnAnimationEndListener)
  {
    mListener = paramOnAnimationEndListener;
  }
  
  public void setPadding(float paramFloat)
  {
    mPadding = paramFloat;
  }
  
  public void setToColor(int paramInt)
  {
    mToColor = paramInt;
  }
  
  public void setToCornerRadius(float paramFloat)
  {
    mToCornerRadius = paramFloat;
  }
  
  public void setToStrokeColor(int paramInt)
  {
    mToStrokeColor = paramInt;
  }
  
  public void setToWidth(int paramInt)
  {
    mToWidth = paramInt;
  }
  
  public void start()
  {
    ValueAnimator localValueAnimator = ValueAnimator.ofInt(new int[] { mFromWidth, mToWidth });
    Object localObject = mDrawable.getGradientDrawable();
    localValueAnimator.addUpdateListener(new CircularProgressButton.MorphingAnimation.1(this, (GradientDrawable)localObject));
    ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofInt(localObject, "color", new int[] { mFromColor, mToColor });
    localObjectAnimator1.setEvaluator(new ArgbEvaluator());
    ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofInt(mDrawable, "strokeColor", new int[] { mFromStrokeColor, mToStrokeColor });
    localObjectAnimator2.setEvaluator(new ArgbEvaluator());
    localObject = ObjectAnimator.ofFloat(localObject, "cornerRadius", new float[] { mFromCornerRadius, mToCornerRadius });
    mAnimSet = new AnimatorSet();
    mAnimSet.setInterpolator(CircularProgressButton.access$1800(this$0));
    mAnimSet.setDuration(mDuration);
    mAnimSet.playTogether(new Animator[] { localValueAnimator, localObjectAnimator1, localObjectAnimator2, localObject });
    mAnimSet.addListener(new CircularProgressButton.MorphingAnimation.2(this));
    mAnimSet.start();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CircularProgressButton.MorphingAnimation
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */