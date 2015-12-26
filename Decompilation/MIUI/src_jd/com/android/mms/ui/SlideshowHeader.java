package com.android.mms.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class SlideshowHeader
  extends LinearLayout
{
  private View mHomeView;
  private View mSlideBtnView;
  private int mStatusBarHeight = 0;
  private View mSubTitleView;
  private View mTitleView;
  
  public SlideshowHeader(Context paramContext)
  {
    super(paramContext);
  }
  
  public SlideshowHeader(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public SlideshowHeader(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    mHomeView = findViewById(2131820651);
    mTitleView = findViewById(2131820652);
    mSubTitleView = findViewById(2131820653);
    mSlideBtnView = findViewById(2131820654);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    int i = paramInt2 + mStatusBarHeight;
    if (mSubTitleView.getVisibility() == 0) {}
    for (paramInt2 = (paramInt4 - i - mTitleView.getMeasuredHeight() - mSubTitleView.getMeasuredHeight()) / 2;; paramInt2 = (paramInt4 - i - mTitleView.getMeasuredHeight()) / 2)
    {
      paramInt2 = Math.max(mTitleView.getMeasuredHeight() / 2 + paramInt2 - mHomeView.getMeasuredHeight() / 2, 0) + mStatusBarHeight;
      mHomeView.layout(paramInt1, paramInt2, mHomeView.getMeasuredWidth() + paramInt1, mHomeView.getMeasuredHeight() + paramInt2);
      paramInt1 = Math.max((paramInt4 - i - mSlideBtnView.getMeasuredHeight()) / 2, 0) + mStatusBarHeight;
      mSlideBtnView.layout(paramInt3 - mSlideBtnView.getMeasuredWidth(), paramInt1, paramInt3, mSlideBtnView.getMeasuredHeight() + paramInt1);
      return;
    }
  }
  
  public void setStatusBarHeight(int paramInt)
  {
    mStatusBarHeight = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowHeader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */