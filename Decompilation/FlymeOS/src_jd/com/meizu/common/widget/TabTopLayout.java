package com.meizu.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;

public class TabTopLayout
  extends TabLayout
{
  private static final String TAG = TabTopLayout.class.getSimpleName();
  private int mDefaultMargin = 20;
  private int mDividerHeight = 1;
  private View mDividerView;
  private int mHeaderHeight = 0;
  private View mHeaderView;
  private boolean mIsAttachTop = false;
  private int mLRMargin = 0;
  private OnHeaderScrollListener mOnHeaderScrollListener;
  private View mParentLayoutView;
  private int mScale = 2;
  
  public TabTopLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    mDividerView = new View(paramContext);
    mDividerView.setBackgroundColor(-4013374);
    addView(mDividerView);
  }
  
  private void dragView(int paramInt)
  {
    int i = getHeaderHeight();
    int j = Math.abs(i - paramInt);
    if ((paramInt >= 0) && (paramInt < i))
    {
      scrollToView(0, paramInt);
      if ((j < mLRMargin) && (!mIsAttachTop)) {
        updateViewParam(j);
      }
      if (i - paramInt >= mLRMargin) {
        mIsAttachTop = false;
      }
    }
    while ((!mIsAttachTop) || (j >= mLRMargin)) {
      return;
    }
    updateViewParam(mLRMargin - j);
  }
  
  private int getHeaderHeight()
  {
    if (mHeaderView != null) {
      return mHeaderView.getHeight();
    }
    return mHeaderHeight;
  }
  
  private void initDividerView()
  {
    if (mDividerView != null)
    {
      removeView(mDividerView);
      FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-1, mDividerHeight);
      localLayoutParams.setMargins(mLRMargin / mScale, getHeight() - mDividerHeight, mLRMargin / mScale, 0);
      addView(mDividerView, localLayoutParams);
      Log.v(TAG, "drawDivider");
    }
  }
  
  private void pushView(int paramInt)
  {
    int i = getHeaderHeight();
    int j = Math.abs(i - paramInt);
    if ((paramInt >= 0) && (paramInt < i))
    {
      scrollToView(0, paramInt);
      if ((j < mLRMargin) && (!mIsAttachTop)) {
        updateViewParam(j);
      }
    }
    do
    {
      return;
      if (paramInt - i >= mLRMargin) {
        mIsAttachTop = true;
      }
    } while ((!mIsAttachTop) || (j >= mLRMargin));
    updateViewParam(mLRMargin - j);
  }
  
  private void scrollToView(int paramInt1, int paramInt2)
  {
    if (mOnHeaderScrollListener != null) {
      mOnHeaderScrollListener.onHeaderScroll(paramInt1, paramInt2);
    }
    if (mParentLayoutView != null)
    {
      mParentLayoutView.scrollTo(paramInt1, paramInt2);
      return;
    }
    scrollTo(paramInt1, paramInt2);
  }
  
  public void onScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt1 = getHeaderHeight();
    if (paramInt2 - paramInt4 > 0)
    {
      if (paramInt2 < 0) {
        return;
      }
      if ((paramInt2 >= 0) && (paramInt2 > paramInt1))
      {
        updateViewParam(0);
        scrollToView(0, paramInt1);
      }
      pushView(paramInt2);
      return;
    }
    if (paramInt2 < paramInt1 - mLRMargin)
    {
      updateViewParam(mLRMargin);
      scrollToView(0, 0);
    }
    dragView(paramInt2);
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    initDividerView();
  }
  
  public void setDividerColor(int paramInt)
  {
    mDividerView.setBackgroundColor(paramInt);
  }
  
  public void setDividerHeight(int paramInt)
  {
    mDividerHeight = paramInt;
    initDividerView();
  }
  
  public void setDividerParam(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mDefaultMargin = paramInt1;
    if (paramInt2 > 0) {
      mDividerHeight = paramInt2;
    }
    if (paramInt3 > 0) {
      mScale = paramInt3;
    }
    if (paramInt4 > 0) {
      mDividerView.setBackgroundColor(paramInt4);
    }
    mLRMargin = (mDefaultMargin * mScale);
    updateViewParam(mLRMargin);
  }
  
  public void setHeaderHeight(int paramInt)
  {
    mHeaderHeight = paramInt;
    if (mHeaderHeight == 0) {
      Log.w(TAG, "mHeaderHeight is 0");
    }
  }
  
  protected void setHeaderView(int paramInt)
  {
    mHeaderView = findViewById(paramInt);
  }
  
  public void setHeaderView(View paramView)
  {
    mHeaderView = paramView;
  }
  
  public void setOnHeaderScrollListener(OnHeaderScrollListener paramOnHeaderScrollListener)
  {
    mOnHeaderScrollListener = paramOnHeaderScrollListener;
  }
  
  public void setParentLayoutView(View paramView)
  {
    mParentLayoutView = paramView;
  }
  
  public void updateViewParam(int paramInt)
  {
    paramInt /= mScale;
    FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)mDividerView.getLayoutParams();
    if (localLayoutParams == null) {
      return;
    }
    height = mDividerHeight;
    localLayoutParams.setMargins(paramInt, topMargin, paramInt, bottomMargin);
    mDividerView.setLayoutParams(localLayoutParams);
    mDividerView.invalidate();
  }
  
  public static abstract interface OnHeaderScrollListener
  {
    public abstract void onHeaderScroll(int paramInt1, int paramInt2);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.TabTopLayout
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */