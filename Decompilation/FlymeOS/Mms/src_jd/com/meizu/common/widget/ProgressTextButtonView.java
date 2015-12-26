package com.meizu.common.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ProgressTextButtonView
  extends FrameLayout
{
  private ValueAnimator mAlphaAnimator;
  private CircularProgressButton mButton;
  private boolean mIsShowText = false;
  private TextView mText;
  
  public ProgressTextButtonView(Context paramContext)
  {
    super(paramContext);
  }
  
  public ProgressTextButtonView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public void cancelAllAnimation()
  {
    if (mAlphaAnimator != null)
    {
      mAlphaAnimator.cancel();
      mAlphaAnimator.removeAllUpdateListeners();
    }
    if (mButton != null) {
      mButton.cancelAllAnimation();
    }
  }
  
  public CircularProgressButton getButton()
  {
    return mButton;
  }
  
  public TextView getTextView()
  {
    return mText;
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    if (getChildCount() != 2) {
      throw new IllegalStateException("ProgressTextButtonView must has two children");
    }
    int i = 0;
    if (i < getChildCount())
    {
      if ((getChildAt(i) instanceof CircularProgressButton)) {
        mButton = ((CircularProgressButton)getChildAt(i));
      }
      for (;;)
      {
        i += 1;
        break;
        if ((getChildAt(i) instanceof TextView)) {
          mText = ((TextView)getChildAt(i));
        }
      }
    }
    showText(mIsShowText, false);
  }
  
  public void showText(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (!paramBoolean2) {
      if (paramBoolean1)
      {
        mText.setAlpha(1.0F);
        mButton.setVisibility(8);
        mText.setVisibility(0);
      }
    }
    while (((paramBoolean1) && (mText.getVisibility() == 0)) || ((!paramBoolean1) && (mButton.getVisibility() == 0)))
    {
      return;
      mButton.setAlpha(1.0F);
      mButton.setVisibility(0);
      mText.setVisibility(8);
      return;
    }
    mAlphaAnimator = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
    mAlphaAnimator.setDuration(100L);
    mAlphaAnimator.addUpdateListener(new ProgressTextButtonView.1(this, paramBoolean1));
    mAlphaAnimator.addListener(new ProgressTextButtonView.2(this, paramBoolean1));
    mAlphaAnimator.start();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.ProgressTextButtonView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */