package com.meizu.common.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import com.meizu.common.R.attr;
import com.meizu.common.R.styleable;

public class AuraSeekBar
  extends SkposSeekBar
{
  private static final int mMaxHeight = 48;
  private static final int mMinHeight = 7;
  private int mAuraRadis;
  private float mInitialTouchX;
  private boolean mIsDrag = false;
  private int mScaledTouchSlop;
  private Drawable mThumb;
  
  public AuraSeekBar(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AuraSeekBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.MeizuCommon_AuraSeekBarStyle);
  }
  
  public AuraSeekBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    mScaledTouchSlop = ViewConfiguration.get(paramContext).getScaledTouchSlop();
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.AuraSeekBar, paramInt, 0);
    mThumb = paramContext.getDrawable(R.styleable.AuraSeekBar_mcAuraThumbDrawble);
    mAuraRadis = ((int)paramContext.getDimension(R.styleable.AuraSeekBar_mcAuraDistance, 9.0F));
    paramContext.recycle();
  }
  
  private void onProgressChanged()
  {
    mIsDrag = true;
    if (mThumb != null) {
      invalidate(mThumb.getBounds());
    }
  }
  
  private void onStopTrackTouch()
  {
    mIsDrag = false;
    if (mThumb != null) {
      invalidate(mThumb.getBounds());
    }
  }
  
  @TargetApi(16)
  protected void onDraw(Canvas paramCanvas)
  {
    try
    {
      if ((mIsDrag) && (getThumb() != null) && (mThumb != null))
      {
        Object localObject = getThumb();
        int j = ((Drawable)localObject).getIntrinsicHeight();
        int k = getHeight() - getPaddingTop() - getPaddingBottom();
        int i = 0;
        if (k > j) {
          i = (k - j) / 2;
        }
        localObject = ((Drawable)localObject).getBounds();
        paramCanvas.save();
        paramCanvas.translate(getPaddingLeft() - getThumb().getIntrinsicWidth() / 2, i + getPaddingTop());
        mThumb.setBounds(left - mAuraRadis, top - mAuraRadis, right + mAuraRadis, bottom + mAuraRadis);
        mThumb.setAlpha(204);
        mThumb.draw(paramCanvas);
        paramCanvas.restore();
      }
      super.onDraw(paramCanvas);
      return;
    }
    finally {}
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int j = 0;
    for (;;)
    {
      int k;
      try
      {
        Drawable localDrawable = getProgressDrawable();
        if (mThumb == null)
        {
          i = 0;
          if (localDrawable != null)
          {
            j = View.MeasureSpec.getSize(paramInt1);
            i = Math.max(i, Math.max(7, Math.min(48, localDrawable.getIntrinsicHeight())));
            k = getPaddingLeft();
            int m = getPaddingRight();
            int n = getPaddingTop();
            int i1 = getPaddingBottom();
            setMeasuredDimension(resolveSizeAndState(j + (k + m), paramInt1, 0), resolveSizeAndState(i + (n + i1), paramInt2, 0));
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
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = true;
    super.onTouchEvent(paramMotionEvent);
    if (!isEnabled()) {
      bool = false;
    }
    do
    {
      do
      {
        do
        {
          return bool;
          float f = paramMotionEvent.getX();
          switch (paramMotionEvent.getAction())
          {
          default: 
            return true;
          case 0: 
            mInitialTouchX = f;
            return true;
          }
        } while (Math.abs(paramMotionEvent.getX() - mInitialTouchX) <= mScaledTouchSlop);
        mIsDrag = true;
        onProgressChanged();
        return true;
      } while (!mIsDrag);
      onStopTrackTouch();
      return true;
    } while (!mIsDrag);
    onStopTrackTouch();
    return true;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.AuraSeekBar
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */