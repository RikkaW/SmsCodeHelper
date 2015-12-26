package com.meizu.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.meizu.common.R.dimen;
import com.meizu.common.R.id;
import com.meizu.common.R.layout;

class GuidePopupWindow$HandleView
  extends FrameLayout
{
  private int layoutResourceId = R.layout.mc_guide_popup_window;
  private int mArrowLeft = -1;
  private int mArrowPadding;
  private View mBGLeft;
  private View mBGMiddle;
  private Rect mBGPadding = new Rect();
  private View mBGRight;
  private LinearLayout mBGVertical;
  private ImageView mCloseIcon;
  private LinearLayout mContentView;
  private Drawable mLeftDrawable;
  private int mLeftMinWidth;
  private int mMarging;
  private TextView mMessageTextView;
  private int mMidMinWidth;
  private Drawable mMiddleDrawable;
  private Drawable mMiddleDrawableUp;
  private int mMinHeight;
  private int mMinWidth;
  private Resources mResources;
  private Drawable mRightDrawable;
  private int mRightMinWidth;
  private int mTextSize;
  private boolean mWithArrow = true;
  
  public GuidePopupWindow$HandleView(GuidePopupWindow paramGuidePopupWindow, Context paramContext, Drawable paramDrawable1, Drawable paramDrawable2, Drawable paramDrawable3, Drawable paramDrawable4)
  {
    super(paramContext);
    View localView = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(layoutResourceId, this, true);
    mResources = paramContext.getResources();
    mMessageTextView = ((TextView)localView.findViewById(R.id.guide_message));
    mCloseIcon = ((ImageView)localView.findViewById(R.id.guide_close));
    mContentView = ((LinearLayout)localView.findViewById(R.id.guide_content));
    mBGLeft = localView.findViewById(R.id.guide_bg_left);
    mBGMiddle = localView.findViewById(R.id.guide_bg_middle);
    mBGRight = localView.findViewById(R.id.guide_bg_right);
    mBGVertical = ((LinearLayout)localView.findViewById(R.id.guide_bg_vertical));
    mTextSize = mResources.getDimensionPixelSize(R.dimen.mc_guide_popup_text_size);
    mMinHeight = mResources.getDimensionPixelSize(R.dimen.mc_guide_popup_min_height);
    mLeftDrawable = paramDrawable1;
    mMiddleDrawable = paramDrawable2;
    mMiddleDrawableUp = paramDrawable3;
    mRightDrawable = paramDrawable4;
    mBGLeft.setBackground(mLeftDrawable);
    mBGMiddle.setBackground(mMiddleDrawable);
    mBGRight.setBackground(mRightDrawable);
    mArrowPadding = mResources.getDimensionPixelSize(R.dimen.mc_guide_popup_arrow_padding);
    mMarging = mResources.getDimensionPixelSize(R.dimen.mc_guide_popup_marging);
    paramContext = new Rect();
    mLeftDrawable.getPadding(paramContext);
    mBGPadding.left = Math.max(left, mBGPadding.left);
    mBGPadding.top = Math.max(top, mBGPadding.top);
    mBGPadding.bottom = Math.max(bottom, mBGPadding.bottom);
    mMiddleDrawable.getPadding(paramContext);
    mBGPadding.top = Math.max(top, mBGPadding.top);
    mBGPadding.bottom = Math.max(bottom, mBGPadding.bottom);
    mRightDrawable.getPadding(paramContext);
    mBGPadding.right = Math.max(right, mBGPadding.right);
    mBGPadding.top = Math.max(top, mBGPadding.top);
    mBGPadding.bottom = Math.max(bottom, mBGPadding.bottom);
    mLeftMinWidth = mLeftDrawable.getIntrinsicWidth();
    mMidMinWidth = mMiddleDrawable.getIntrinsicWidth();
    mRightMinWidth = mRightDrawable.getIntrinsicWidth();
    mMinWidth = (mLeftMinWidth + mMidMinWidth + mRightMinWidth);
    mContentView.setMinimumHeight(mMinHeight + mBGPadding.top + mBGPadding.bottom);
    mContentView.setMinimumWidth(mMinWidth);
    mContentView.setPadding(mBGPadding.left, mBGPadding.top, mBGPadding.right, mBGPadding.bottom);
    mCloseIcon.setOnClickListener(new GuidePopupWindow.HandleView.1(this, paramGuidePopupWindow));
  }
  
  private void getParentBound(View paramView, int[] paramArrayOfInt)
  {
    if (paramView == null)
    {
      paramView = getResources().getDisplayMetrics();
      int i = widthPixels;
      int j = heightPixels;
      paramArrayOfInt[0] = 0;
      paramArrayOfInt[1] = 0;
      paramArrayOfInt[2] = i;
      paramArrayOfInt[3] = j;
      return;
    }
    int[] arrayOfInt = new int[2];
    paramView.getLocationOnScreen(arrayOfInt);
    paramArrayOfInt[0] = arrayOfInt[0];
    paramArrayOfInt[1] = arrayOfInt[1];
    paramArrayOfInt[2] = (paramArrayOfInt[0] + paramView.getWidth());
    paramArrayOfInt[3] = (paramArrayOfInt[1] + paramView.getHeight());
  }
  
  private int getPopMarging()
  {
    return mMarging;
  }
  
  private void setMessageWidth(int paramInt)
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)mMessageTextView.getLayoutParams();
    width = paramInt;
    mMessageTextView.setLayoutParams(localLayoutParams);
  }
  
  public void disableArrow(boolean paramBoolean)
  {
    if (!paramBoolean) {}
    for (paramBoolean = true;; paramBoolean = false)
    {
      mWithArrow = paramBoolean;
      return;
    }
  }
  
  public int getArrowPadding()
  {
    return mArrowPadding;
  }
  
  public int getArrowWidth()
  {
    return mMiddleDrawable.getMinimumWidth();
  }
  
  public int getBackgroundLeftMinWidth()
  {
    return mLeftMinWidth;
  }
  
  public int getBackgroundMidMinWidth()
  {
    return mMidMinWidth;
  }
  
  public int getBackgroundMinWidth()
  {
    return mMinWidth;
  }
  
  public int getBackgroundRightMinWidth()
  {
    return mRightMinWidth;
  }
  
  public int getCloseIconWidth()
  {
    return mCloseIcon.getMeasuredWidth();
  }
  
  public TextView getMessageTextView()
  {
    return mMessageTextView;
  }
  
  public int getPaddingLeft()
  {
    return mBGPadding.left;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    mContentView.measure(0, 0);
    paramInt1 = mContentView.getMeasuredHeight();
    paramInt2 = mContentView.getMeasuredWidth();
    setMeasuredDimension(paramInt2, paramInt1);
    mBGVertical.measure(paramInt2, paramInt1);
    if (!mWithArrow)
    {
      localLayoutParams = (LinearLayout.LayoutParams)mBGLeft.getLayoutParams();
      width = (paramInt2 - mRightDrawable.getMinimumWidth());
      height = paramInt1;
      mBGLeft.setLayoutParams(localLayoutParams);
      localLayoutParams = (LinearLayout.LayoutParams)mBGRight.getLayoutParams();
      width = mRightDrawable.getMinimumWidth();
      height = paramInt1;
      mBGRight.setLayoutParams(localLayoutParams);
      localLayoutParams = (LinearLayout.LayoutParams)mBGMiddle.getLayoutParams();
      width = 0;
      height = paramInt1;
      mBGMiddle.setLayoutParams(localLayoutParams);
      return;
    }
    if (mArrowLeft > 0)
    {
      localLayoutParams = (LinearLayout.LayoutParams)mBGLeft.getLayoutParams();
      width = mArrowLeft;
      height = paramInt1;
      mBGLeft.setLayoutParams(localLayoutParams);
      localLayoutParams = (LinearLayout.LayoutParams)mBGRight.getLayoutParams();
      width = (paramInt2 - mMiddleDrawable.getMinimumWidth() - mArrowLeft);
      height = paramInt1;
      mBGRight.setLayoutParams(localLayoutParams);
      localLayoutParams = (LinearLayout.LayoutParams)mBGMiddle.getLayoutParams();
      width = mMiddleDrawable.getMinimumWidth();
      height = paramInt1;
      mBGMiddle.setLayoutParams(localLayoutParams);
      return;
    }
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)mBGLeft.getLayoutParams();
    width = ((paramInt2 - mMiddleDrawable.getMinimumWidth()) / 2);
    height = paramInt1;
    mBGLeft.setLayoutParams(localLayoutParams);
    localLayoutParams = (LinearLayout.LayoutParams)mBGRight.getLayoutParams();
    width = ((paramInt2 - mMiddleDrawable.getMinimumWidth()) / 2);
    height = paramInt1;
    mBGRight.setLayoutParams(localLayoutParams);
    localLayoutParams = (LinearLayout.LayoutParams)mBGMiddle.getLayoutParams();
    width = mMiddleDrawable.getMinimumWidth();
    height = paramInt1;
    mBGMiddle.setLayoutParams(localLayoutParams);
  }
  
  public void setArrowDown()
  {
    mBGMiddle.setBackground(mMiddleDrawable);
  }
  
  public void setArrowPosition(int paramInt)
  {
    mArrowLeft = paramInt;
  }
  
  public void setArrowUp()
  {
    mBGMiddle.setBackground(mMiddleDrawableUp);
  }
  
  public void setMessageOnClickListener(View.OnClickListener paramOnClickListener)
  {
    mMessageTextView.setOnClickListener(paramOnClickListener);
  }
  
  public void setText(String paramString)
  {
    mMessageTextView.setText(paramString);
  }
  
  public void setTextSize(int paramInt)
  {
    mMessageTextView.setTextSize(1, paramInt);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.GuidePopupWindow.HandleView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */