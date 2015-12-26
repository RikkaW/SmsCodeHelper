package com.meizu.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.meizu.common.R.styleable;

public abstract class AbsSeekBar
  extends ProgressBar
{
  private static final int NO_ALPHA = 255;
  public static final int SEEK_BAR_SCROLL_MODE_COMMON = 0;
  public static final int SEEK_BAR_SCROLL_MODE_SLOW = 1;
  private float mDisabledAlpha;
  private float mDragTouchDownX;
  private float mDragTouchDownY = 0.0F;
  private int mHalfThumbHeight = 0;
  private int mHalfThumbWidth = 0;
  private boolean mInDragoning = false;
  private boolean mIsDragging;
  boolean mIsUserSeekable = true;
  boolean mIsVertical = false;
  private int mKeyProgressIncrement = 1;
  private int mScaledTouchSlop;
  private Drawable mThumb;
  private int mThumbOffset;
  private int mTouchDownProgress = 0;
  private float mTouchDownX;
  private float mTouchDownY;
  float mTouchProgressOffset;
  protected int mTouchScrollMode = 0;
  private int mTouchSlopSquare = 256;
  
  public AbsSeekBar(Context paramContext)
  {
    super(paramContext);
  }
  
  public AbsSeekBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public AbsSeekBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SeekBar, paramInt, 0);
    setThumb(paramAttributeSet.getDrawable(R.styleable.SeekBar_mcThumb));
    setThumbOffset(paramAttributeSet.getDimensionPixelOffset(R.styleable.SeekBar_mcThumbOffset, getThumbOffset()));
    paramAttributeSet.recycle();
    mDisabledAlpha = 0.5F;
    mScaledTouchSlop = ViewConfiguration.get(paramContext).getScaledTouchSlop();
    mTouchSlopSquare = (mScaledTouchSlop * mScaledTouchSlop);
  }
  
  private void attemptClaimDrag()
  {
    if (getParent() != null) {
      getParent().requestDisallowInterceptTouchEvent(true);
    }
  }
  
  private int getIntFromFloat(float paramFloat)
  {
    return Math.round(paramFloat);
  }
  
  private int getPosByProgress(int paramInt)
  {
    int i;
    int k;
    int j;
    float f;
    if (mIsVertical)
    {
      i = getHeight() - getPaddingTop() - getPaddingBottom();
      k = getPaddingLeft();
      j = getPaddingBottom();
      f = paramInt - mTouchProgressOffset;
      if (f >= 0.0F) {
        break label81;
      }
      if (!mIsVertical) {
        break label78;
      }
      paramInt = j;
    }
    label78:
    label81:
    int m;
    do
    {
      return paramInt;
      i = getWidth() - getPaddingLeft() - getPaddingRight();
      break;
      return k;
      if (f > getMax())
      {
        if (mIsVertical) {
          return getHeight() - getPaddingBottom();
        }
        return getWidth() - getPaddingRight();
      }
      if (getMax() <= 0)
      {
        if (mIsVertical) {
          return j;
        }
        return k;
      }
      m = (int)(i * (f / getMax()));
      paramInt = (int)(i * (f / getMax())) + j;
    } while (mIsVertical);
    return k + m;
  }
  
  private int getProgressByPos(int paramInt)
  {
    int i;
    if (mIsVertical)
    {
      i = getHeight() - getPaddingTop() - getPaddingBottom();
      paramInt = i - paramInt;
    }
    for (;;)
    {
      return (int)(getMax() * paramInt / i - mTouchProgressOffset);
      i = getWidth() - getPaddingLeft() - getPaddingRight();
    }
  }
  
  private void setThumbPos(int paramInt1, int paramInt2, Drawable paramDrawable, float paramFloat, int paramInt3)
  {
    int i = paramDrawable.getIntrinsicWidth();
    int j = paramDrawable.getIntrinsicHeight();
    Rect localRect;
    if (mIsVertical)
    {
      paramInt1 = paramInt2 - getPaddingTop() - getPaddingBottom() - j;
      paramInt1 += mThumbOffset * 2;
      if (!mIsVertical) {
        break label125;
      }
      paramInt2 = (int)(paramInt1 * (1.0F - paramFloat));
      if (paramInt3 != Integer.MIN_VALUE) {
        break label116;
      }
      localRect = paramDrawable.getBounds();
      paramInt3 = left;
    }
    label116:
    for (paramInt1 = right;; paramInt1 = paramInt3 + i)
    {
      paramDrawable.setBounds(paramInt3, paramInt2, paramInt1, paramInt2 + j);
      return;
      paramInt1 = paramInt1 - getPaddingLeft() - getPaddingRight() - i;
      break;
    }
    label125:
    paramInt2 = (int)(paramInt1 * paramFloat);
    if (paramInt3 == Integer.MIN_VALUE)
    {
      localRect = paramDrawable.getBounds();
      paramInt3 = top;
    }
    for (paramInt1 = bottom;; paramInt1 = paramInt3 + j)
    {
      paramDrawable.setBounds(paramInt2, paramInt3, i + paramInt2, paramInt1);
      return;
    }
  }
  
  private void trackTouchEvent(MotionEvent paramMotionEvent)
  {
    float f1 = 1.0F;
    float f3 = 0.0F;
    float f2 = 0.0F;
    if (mIsVertical)
    {
      i = getHeight();
      j = getPaddingTop();
      k = getPaddingBottom();
      m = (int)paramMotionEvent.getY();
      if (m < getPaddingTop()) {}
      for (;;)
      {
        f1 = f1 * getMax() + f2;
        setProgress((int)f1, true);
        return;
        if (m > i - getPaddingBottom())
        {
          f1 = 0.0F;
        }
        else
        {
          f1 = 1.0F - (m - getPaddingTop()) / (i - j - k);
          f2 = mTouchProgressOffset;
        }
      }
    }
    int i = getWidth();
    int j = getPaddingLeft();
    int k = getPaddingRight();
    int m = (int)paramMotionEvent.getX();
    if (m < getPaddingLeft())
    {
      f1 = 0.0F;
      f2 = f3;
    }
    for (;;)
    {
      f1 += f2 * getMax();
      break;
      if (m > i - getPaddingRight())
      {
        f1 = 0.0F;
        f2 = 1.0F;
      }
      else
      {
        f2 = (m - getPaddingLeft()) / (i - j - k);
        f1 = mTouchProgressOffset;
      }
    }
  }
  
  private void updateThumbPos(int paramInt1, int paramInt2)
  {
    float f2 = 0.0F;
    float f1 = 0.0F;
    Drawable localDrawable1 = getCurrentDrawable();
    Drawable localDrawable2 = mThumb;
    int i;
    int j;
    int k;
    if (mIsVertical) {
      if (localDrawable2 == null)
      {
        i = 0;
        j = Math.min(mMaxWidth, paramInt1 - getPaddingLeft() - getPaddingRight());
        k = getMax();
        if (k > 0) {
          f1 = getProgress() / k;
        }
        if (i <= j) {
          break label153;
        }
        if (localDrawable2 != null) {
          setThumbPos(paramInt1, paramInt2, localDrawable2, f1, 0);
        }
        i = (i - j) / 2;
        if (localDrawable1 != null) {
          localDrawable1.setBounds(i, 0, paramInt1 - getPaddingRight() - i - getPaddingLeft(), paramInt2 - getPaddingBottom() - getPaddingTop());
        }
      }
    }
    label153:
    label345:
    do
    {
      do
      {
        return;
        i = localDrawable2.getIntrinsicWidth();
        break;
        if (localDrawable1 != null) {
          localDrawable1.setBounds(0, 0, paramInt1 - getPaddingRight() - getPaddingLeft(), paramInt2 - getPaddingBottom() - getPaddingTop());
        }
        i = (j - i) / 2;
      } while (localDrawable2 == null);
      setThumbPos(paramInt1, paramInt2, localDrawable2, f1, i);
      return;
      if (localDrawable2 == null) {}
      for (i = 0;; i = localDrawable2.getIntrinsicHeight())
      {
        j = Math.min(mMaxHeight, paramInt2 - getPaddingTop() - getPaddingBottom());
        k = getMax();
        f1 = f2;
        if (k > 0) {
          f1 = getProgress() / k;
        }
        if (i <= j) {
          break label345;
        }
        if (localDrawable2 != null) {
          setThumbPos(paramInt1, paramInt2, localDrawable2, f1, 0);
        }
        i = (i - j) / 2;
        if (localDrawable1 == null) {
          break;
        }
        localDrawable1.setBounds(0, i, paramInt1 - getPaddingRight() - getPaddingLeft(), paramInt2 - getPaddingBottom() - i - getPaddingTop());
        return;
      }
      if (localDrawable1 != null) {
        localDrawable1.setBounds(0, 0, paramInt1 - getPaddingRight() - getPaddingLeft(), paramInt2 - getPaddingBottom() - getPaddingTop());
      }
      i = (j - i) / 2;
    } while (localDrawable2 == null);
    setThumbPos(paramInt1, paramInt2, localDrawable2, f1, i);
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    Object localObject = getProgressDrawable();
    if (localObject != null) {
      if (!isEnabled()) {
        break label61;
      }
    }
    label61:
    for (int i = 255;; i = (int)(255.0F * mDisabledAlpha))
    {
      ((Drawable)localObject).setAlpha(i);
      if ((mThumb != null) && (mThumb.isStateful()))
      {
        localObject = getDrawableState();
        mThumb.setState((int[])localObject);
      }
      return;
    }
  }
  
  public int getKeyProgressIncrement()
  {
    return mKeyProgressIncrement;
  }
  
  public Drawable getThumb()
  {
    return mThumb;
  }
  
  public int getThumbOffset()
  {
    return mThumbOffset;
  }
  
  public boolean isInScrollingContainer()
  {
    for (ViewParent localViewParent = getParent(); (localViewParent != null) && ((localViewParent instanceof ViewGroup)); localViewParent = localViewParent.getParent()) {
      if (((ViewGroup)localViewParent).shouldDelayChildPressedState()) {
        return true;
      }
    }
    return false;
  }
  
  public void jumpDrawablesToCurrentState()
  {
    super.jumpDrawablesToCurrentState();
    if (mThumb != null) {
      mThumb.jumpToCurrentState();
    }
  }
  
  /* Error */
  protected void onDraw(android.graphics.Canvas paramCanvas)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokespecial 282	com/meizu/common/widget/ProgressBar:onDraw	(Landroid/graphics/Canvas;)V
    //   7: aload_0
    //   8: getfield 224	com/meizu/common/widget/AbsSeekBar:mThumb	Landroid/graphics/drawable/Drawable;
    //   11: ifnull +46 -> 57
    //   14: aload_1
    //   15: invokevirtual 287	android/graphics/Canvas:save	()I
    //   18: pop
    //   19: aload_0
    //   20: getfield 41	com/meizu/common/widget/AbsSeekBar:mIsVertical	Z
    //   23: ifeq +37 -> 60
    //   26: aload_1
    //   27: aload_0
    //   28: invokevirtual 154	com/meizu/common/widget/AbsSeekBar:getPaddingLeft	()I
    //   31: i2f
    //   32: aload_0
    //   33: invokevirtual 148	com/meizu/common/widget/AbsSeekBar:getPaddingTop	()I
    //   36: aload_0
    //   37: getfield 178	com/meizu/common/widget/AbsSeekBar:mThumbOffset	I
    //   40: isub
    //   41: i2f
    //   42: invokevirtual 291	android/graphics/Canvas:translate	(FF)V
    //   45: aload_0
    //   46: getfield 224	com/meizu/common/widget/AbsSeekBar:mThumb	Landroid/graphics/drawable/Drawable;
    //   49: aload_1
    //   50: invokevirtual 294	android/graphics/drawable/Drawable:draw	(Landroid/graphics/Canvas;)V
    //   53: aload_1
    //   54: invokevirtual 297	android/graphics/Canvas:restore	()V
    //   57: aload_0
    //   58: monitorexit
    //   59: return
    //   60: aload_1
    //   61: aload_0
    //   62: invokevirtual 154	com/meizu/common/widget/AbsSeekBar:getPaddingLeft	()I
    //   65: aload_0
    //   66: getfield 178	com/meizu/common/widget/AbsSeekBar:mThumbOffset	I
    //   69: isub
    //   70: i2f
    //   71: aload_0
    //   72: invokevirtual 148	com/meizu/common/widget/AbsSeekBar:getPaddingTop	()I
    //   75: i2f
    //   76: invokevirtual 291	android/graphics/Canvas:translate	(FF)V
    //   79: aload_0
    //   80: getfield 224	com/meizu/common/widget/AbsSeekBar:mThumb	Landroid/graphics/drawable/Drawable;
    //   83: aload_1
    //   84: invokevirtual 294	android/graphics/drawable/Drawable:draw	(Landroid/graphics/Canvas;)V
    //   87: aload_1
    //   88: invokevirtual 297	android/graphics/Canvas:restore	()V
    //   91: goto -34 -> 57
    //   94: astore_1
    //   95: aload_0
    //   96: monitorexit
    //   97: aload_1
    //   98: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	99	0	this	AbsSeekBar
    //   0	99	1	paramCanvas	android.graphics.Canvas
    // Exception table:
    //   from	to	target	type
    //   2	57	94	finally
    //   60	91	94	finally
  }
  
  void onKeyChange() {}
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (isEnabled())
    {
      int i = getProgress();
      if (((paramInt == 21) && (!mIsVertical)) || ((paramInt == 20) && (mIsVertical)))
      {
        if (i > 0)
        {
          setProgress(i - mKeyProgressIncrement, true);
          onKeyChange();
          return true;
        }
      }
      else if (((paramInt == 22) && (!mIsVertical)) || ((paramInt == 19) && (mIsVertical) && (i < getMax())))
      {
        setProgress(i + mKeyProgressIncrement, true);
        onKeyChange();
        return true;
      }
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int j = 0;
    for (;;)
    {
      int k;
      try
      {
        Drawable localDrawable = getCurrentDrawable();
        if (mThumb == null)
        {
          i = 0;
          if (localDrawable != null)
          {
            j = Math.max(mMinWidth, Math.min(mMaxWidth, localDrawable.getIntrinsicWidth()));
            i = Math.max(i, Math.max(mMinHeight, Math.min(mMaxHeight, localDrawable.getIntrinsicHeight())));
            k = getPaddingLeft();
            int m = getPaddingRight();
            int n = getPaddingTop();
            int i1 = getPaddingBottom();
            setMeasuredDimension(resolveSizeAndState(j + (k + m), paramInt1, 0), resolveSizeAndState(i + (n + i1), paramInt2, 0));
            if (getMeasuredHeight() > getMeasuredWidth()) {
              mIsVertical = true;
            }
          }
        }
        else
        {
          i = mThumb.getIntrinsicHeight();
          continue;
        }
        k = 0;
      }
      finally {}
      int i = j;
      j = k;
    }
  }
  
  void onProgressRefresh(float paramFloat, boolean paramBoolean)
  {
    super.onProgressRefresh(paramFloat, paramBoolean);
    Drawable localDrawable = mThumb;
    if (localDrawable != null)
    {
      setThumbPos(getWidth(), getHeight(), localDrawable, paramFloat, Integer.MIN_VALUE);
      invalidate();
    }
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    updateThumbPos(paramInt1, paramInt2);
  }
  
  void onStartTrackingTouch()
  {
    mIsDragging = true;
  }
  
  void onStopTrackingTouch()
  {
    mIsDragging = false;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((!mIsUserSeekable) || (!isEnabled())) {
      return false;
    }
    float f3 = paramMotionEvent.getX();
    float f4 = paramMotionEvent.getY();
    switch (paramMotionEvent.getAction())
    {
    }
    for (;;)
    {
      return true;
      if (isInScrollingContainer())
      {
        mTouchDownX = f3;
        mTouchDownY = f4;
        if (mTouchScrollMode == 1)
        {
          onStartTrackingTouch();
          mDragTouchDownX = f3;
          mDragTouchDownY = f4;
          mInDragoning = false;
          mTouchDownProgress = getProgress();
          attemptClaimDrag();
        }
      }
      else
      {
        setPressed(true);
        if (mThumb != null) {
          invalidate(mThumb.getBounds());
        }
        onStartTrackingTouch();
        mTouchDownProgress = 0;
        if (mTouchScrollMode == 1) {
          if (mIsVertical)
          {
            mDragTouchDownX = f3;
            mDragTouchDownY = paramMotionEvent.getY();
            label190:
            mInDragoning = false;
            mTouchDownProgress = getProgress();
          }
        }
        for (;;)
        {
          attemptClaimDrag();
          break;
          mDragTouchDownX = paramMotionEvent.getX();
          mDragTouchDownY = f4;
          break label190;
          trackTouchEvent(paramMotionEvent);
        }
        float f2;
        float f1;
        label281:
        int i;
        label345:
        int j;
        if (mIsDragging)
        {
          if (mTouchScrollMode == 1)
          {
            if (mIsVertical)
            {
              f2 = Math.abs(paramMotionEvent.getX() - mDragTouchDownX);
              f1 = Math.abs(f4 - mDragTouchDownY);
              if ((f1 * f1 + f2 * f2 > mTouchSlopSquare) && (!mInDragoning))
              {
                mDragTouchDownX = f3;
                mInDragoning = true;
              }
              if (!mInDragoning) {
                continue;
              }
              if (!mIsVertical) {
                break label428;
              }
              i = getHeight() - getPaddingTop() - getPaddingBottom();
              if (i == 0) {
                continue;
              }
              if (!mIsVertical) {
                break label447;
              }
            }
            label428:
            label447:
            for (f1 = (mDragTouchDownY - f4) / i;; f1 = (f3 - mDragTouchDownX) / i)
            {
              i = getMax();
              j = mTouchDownProgress;
              setProgress(getIntFromFloat(f1 * i) + j, true);
              break;
              f2 = Math.abs(f3 - mDragTouchDownX);
              f1 = Math.abs(paramMotionEvent.getY() - mDragTouchDownY);
              break label281;
              i = getWidth() - getPaddingLeft() - getPaddingRight();
              break label345;
            }
          }
          else
          {
            trackTouchEvent(paramMotionEvent);
          }
        }
        else
        {
          if (mIsVertical) {}
          for (f1 = Math.abs(f4 - mTouchDownY); f1 > mScaledTouchSlop; f1 = Math.abs(f3 - mTouchDownX))
          {
            setPressed(true);
            if (mThumb != null) {
              invalidate(mThumb.getBounds());
            }
            onStartTrackingTouch();
            trackTouchEvent(paramMotionEvent);
            attemptClaimDrag();
            break;
          }
          if (mIsDragging) {
            if (mIsVertical)
            {
              i = getHeight() - getPaddingTop() - getPaddingBottom();
              label581:
              if ((mTouchScrollMode != 1) || (mInDragoning)) {
                break label777;
              }
              if (!mIsVertical) {
                break label736;
              }
              f1 = f4;
              label606:
              if ((i - f1 + getPaddingBottom() >= getPosByProgress(mTouchDownProgress) - mHalfThumbWidth) && (i - f1 + getPaddingBottom() <= getPosByProgress(mTouchDownProgress) + mHalfThumbWidth)) {
                continue;
              }
              if (getProgressByPos((int)f1) < mTouchDownProgress + mKeyProgressIncrement) {
                break label742;
              }
              setProgress(mTouchDownProgress + mKeyProgressIncrement, true);
              label696:
              onStopTrackingTouch();
              setPressed(false);
            }
          }
          for (;;)
          {
            invalidate();
            mInDragoning = false;
            break;
            i = getWidth() - getPaddingLeft() - getPaddingRight();
            break label581;
            label736:
            f1 = f3;
            break label606;
            label742:
            if (getProgressByPos((int)f1) >= mTouchDownProgress + mKeyProgressIncrement) {
              break label696;
            }
            setProgress(mTouchDownProgress - mKeyProgressIncrement, true);
            break label696;
            label777:
            if ((mTouchScrollMode == 1) && (mInDragoning == true))
            {
              if (i == 0) {
                break;
              }
              f1 = mDragTouchDownX;
              f2 = mDragTouchDownY;
              if (mIsVertical) {}
              for (f1 = (f2 - f4) / i;; f1 = (f3 - f1) / i)
              {
                i = getMax();
                j = mTouchDownProgress;
                setProgress(getIntFromFloat(f1 * i) + j, true);
                break;
              }
            }
            trackTouchEvent(paramMotionEvent);
            break label696;
            onStartTrackingTouch();
            trackTouchEvent(paramMotionEvent);
            onStopTrackingTouch();
          }
          if (mIsDragging)
          {
            onStopTrackingTouch();
            setPressed(false);
          }
          invalidate();
        }
      }
    }
  }
  
  public void setKeyProgressIncrement(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0) {
      i = -paramInt;
    }
    mKeyProgressIncrement = i;
  }
  
  public void setMax(int paramInt)
  {
    try
    {
      super.setMax(paramInt);
      if ((mKeyProgressIncrement == 0) || (getMax() / mKeyProgressIncrement > 20)) {
        setKeyProgressIncrement(Math.max(1, Math.round(getMax() / 20.0F)));
      }
      return;
    }
    finally {}
  }
  
  public void setThumb(Drawable paramDrawable)
  {
    int i;
    if ((mThumb != null) && (paramDrawable != mThumb))
    {
      mThumb.setCallback(null);
      i = 1;
      if (paramDrawable != null)
      {
        paramDrawable.setCallback(this);
        if (!mIsVertical) {
          break label154;
        }
      }
    }
    label154:
    for (mThumbOffset = (paramDrawable.getIntrinsicHeight() / 2);; mThumbOffset = (paramDrawable.getIntrinsicWidth() / 2))
    {
      if ((i != 0) && ((paramDrawable.getIntrinsicWidth() != mThumb.getIntrinsicWidth()) || (paramDrawable.getIntrinsicHeight() != mThumb.getIntrinsicHeight()))) {
        requestLayout();
      }
      mHalfThumbWidth = (paramDrawable.getIntrinsicWidth() / 2);
      mHalfThumbHeight = (paramDrawable.getIntrinsicHeight() / 2);
      mThumb = paramDrawable;
      invalidate();
      if (i != 0)
      {
        updateThumbPos(getWidth(), getHeight());
        if (paramDrawable.isStateful()) {
          paramDrawable.setState(getDrawableState());
        }
      }
      return;
      i = 0;
      break;
    }
  }
  
  public void setThumbOffset(int paramInt)
  {
    mThumbOffset = paramInt;
    invalidate();
  }
  
  protected void setTouchMode(int paramInt)
  {
    mTouchScrollMode = paramInt;
    if (mTouchScrollMode > 1) {
      mTouchScrollMode = 0;
    }
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable)
  {
    return (paramDrawable == mThumb) || (super.verifyDrawable(paramDrawable));
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.AbsSeekBar
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */