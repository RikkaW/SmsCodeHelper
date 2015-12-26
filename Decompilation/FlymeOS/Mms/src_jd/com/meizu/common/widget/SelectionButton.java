package com.meizu.common.widget;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.meizu.common.R.attr;
import com.meizu.common.R.dimen;
import com.meizu.common.R.layout;
import com.meizu.common.R.string;
import com.meizu.common.R.styleable;

public class SelectionButton
  extends LinearLayout
{
  private static final int FRAME_COUNT = 12;
  private static final float[] interpolationsEnter = { 0.0F, 0.215313F, 0.513045F, 0.675783F, 0.777778F, 0.848013F, 0.898385F, 0.934953F, 0.96126F, 0.979572F, 0.991439F, 0.997972F, 1.0F, 1.0F };
  private static final float[] interpolationsOut = { 0.0F, 0.002028F, 0.008561F, 0.020428F, 0.03874F, 0.065047F, 0.101615F, 0.151987F, 0.222222F, 0.324217F, 0.486955F, 0.784687F, 1.0F, 1.0F };
  private ObjectAnimator mAnimator;
  Context mContext;
  private int mCurrentCount = 0;
  Drawable mDrawable = null;
  private boolean mIsAllSelected = false;
  private boolean mIsAnimation = false;
  private ColorStateList mSelectTextColor;
  private TextView mText;
  private int mTotalCount = 0;
  private int targetVisibility;
  
  public SelectionButton(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public SelectionButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.MeizuCommon_SelectionButtonStyle);
  }
  
  public SelectionButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    mContext = paramContext;
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SelectionButton, paramInt, 0);
    mDrawable = paramContext.getDrawable(R.styleable.SelectionButton_mcBackground);
    mSelectTextColor = paramContext.getColorStateList(R.styleable.SelectionButton_mcSelectTextColor);
    paramContext.recycle();
    initView();
    initAnimation();
  }
  
  private void initAnimation()
  {
    if ((mAnimator != null) || (mText == null)) {
      return;
    }
    mIsAnimation = true;
    targetVisibility = getVisibility();
    setupAnimation();
  }
  
  private void initView()
  {
    if (LayoutInflater.from(mContext).inflate(R.layout.mc_selection_button, this) == null) {
      Log.w("SelectionButton", "can not inflate the view");
    }
    do
    {
      return;
      setClickable(true);
      setGravity(17);
      setMinimumWidth(getContext().getResources().getDimensionPixelSize(R.dimen.mz_action_button_min_width));
      mText = ((TextView)findViewById(16908308));
      update();
      mText.setActivated(false);
      if (mDrawable != null) {
        mText.setBackgroundDrawable(mDrawable);
      }
    } while (mSelectTextColor == null);
    mText.setTextColor(mSelectTextColor);
  }
  
  private void setupAnimation()
  {
    PropertyValuesHolder localPropertyValuesHolder1 = PropertyValuesHolder.ofFloat("scaleX", new float[] { 0.0F, 1.0F });
    PropertyValuesHolder localPropertyValuesHolder2 = PropertyValuesHolder.ofFloat("scaleY", new float[] { 0.0F, 1.0F });
    mAnimator = ObjectAnimator.ofPropertyValuesHolder(mText, new PropertyValuesHolder[] { localPropertyValuesHolder1, localPropertyValuesHolder2 }).setDuration(200L);
    mAnimator.setInterpolator(new SelectionButton.1(this));
    mAnimator.addListener(new SelectionButton.2(this));
  }
  
  private void update()
  {
    if (mCurrentCount > mTotalCount) {
      mCurrentCount = mTotalCount;
    }
    if ((mTotalCount > 0) && (mCurrentCount == mTotalCount))
    {
      mIsAllSelected = true;
      mText.setText(getContext().getResources().getString(R.string.mc_selectionbutton_all));
      mText.setActivated(true);
      return;
    }
    mText.setText(String.valueOf(mCurrentCount));
    mIsAllSelected = false;
    mText.setActivated(false);
  }
  
  public int getCurrentCount()
  {
    return mCurrentCount;
  }
  
  public int getTotalCount()
  {
    return mTotalCount;
  }
  
  public boolean isAllSelected()
  {
    return mIsAllSelected;
  }
  
  public void setAllSelected(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (mCurrentCount = mTotalCount;; mCurrentCount = 0)
    {
      update();
      return;
    }
  }
  
  public void setCurrentCount(int paramInt)
  {
    if (mCurrentCount != paramInt) {
      if (paramInt < 0) {
        break label22;
      }
    }
    for (;;)
    {
      mCurrentCount = paramInt;
      update();
      return;
      label22:
      paramInt = 0;
    }
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    super.setEnabled(paramBoolean);
    if (mText != null) {
      mText.setEnabled(paramBoolean);
    }
  }
  
  public void setIsAnimation(boolean paramBoolean)
  {
    mIsAnimation = paramBoolean;
  }
  
  public void setSelectBackground(Drawable paramDrawable)
  {
    if (paramDrawable != null)
    {
      mDrawable = paramDrawable;
      mText.setBackgroundDrawable(mDrawable);
    }
  }
  
  public void setSelectTextColor(int paramInt)
  {
    mText.setTextColor(paramInt);
  }
  
  public void setTotalCount(int paramInt)
  {
    if (mTotalCount != paramInt) {
      if (paramInt < 0) {
        break label22;
      }
    }
    for (;;)
    {
      mTotalCount = paramInt;
      update();
      return;
      label22:
      paramInt = 0;
    }
  }
  
  public void setVisibility(int paramInt)
  {
    if (!mIsAnimation) {
      super.setVisibility(paramInt);
    }
    while (targetVisibility == paramInt) {
      return;
    }
    targetVisibility = paramInt;
    if (paramInt == 0)
    {
      super.setVisibility(paramInt);
      mAnimator.start();
      return;
    }
    mAnimator.reverse();
  }
  
  public void setVisibility(int paramInt, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      setVisibility(paramInt);
      return;
    }
    super.setVisibility(paramInt);
    targetVisibility = getVisibility();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.SelectionButton
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */