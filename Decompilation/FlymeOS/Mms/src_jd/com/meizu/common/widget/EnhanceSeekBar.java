package com.meizu.common.widget;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import com.meizu.common.R.attr;
import com.meizu.common.R.color;
import com.meizu.common.R.drawable;
import com.meizu.common.R.styleable;

public class EnhanceSeekBar
  extends View
{
  private static final int CIRCLE_RADIUS = 6;
  private static final int MIN_HEIGHT = 20;
  private static final int MIN_WIDTH = 64;
  private static final int STROKE_WIDTH = 4;
  private static final String TAG = "EnhanceSeekBar";
  private static final int TEXT_HEIGHT = 50;
  private static final int TEXT_SIZE = 40;
  private boolean hasMoved = false;
  private int mAuraRadis;
  private Drawable mAuraThumb;
  private DrawableXYHolder mDrawableXYHolder = new DrawableXYHolder();
  private XYHolder mEndXY = new XYHolder();
  private int mHalfThumbHeight = 0;
  private int mHalfThumbWidth = 0;
  private float mInitialThumbX;
  private float mInitialTouchX;
  private Interpolator mInterpolator;
  private boolean mIsDrag = false;
  private boolean mIsDragging;
  private boolean mIsInItemPositon;
  private CharSequence[] mItems;
  private int mMax;
  private ObjectAnimator mObjectAnimator;
  private OnEnhanceSeekBarChangeListener mOnEnhanceSeekBarChangeListener;
  private Paint mPaint;
  private int mPaintColor;
  private int mProgress;
  private int mScaledTouchSlop;
  private XYHolder mStartXY = new XYHolder();
  private Drawable mThumb;
  private int mThumbOffset;
  private int mTouchDownProgress = 0;
  private XYEvaluator mXYEvaluator = new XYEvaluator(null);
  
  public EnhanceSeekBar(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public EnhanceSeekBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.MeizuCommon_EnhanceSeekBarStyle);
  }
  
  public EnhanceSeekBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    mScaledTouchSlop = ViewConfiguration.get(paramContext).getScaledTouchSlop();
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.EnhanceSeekBar, paramInt, 0);
    setItems(localTypedArray.getTextArray(R.styleable.EnhanceSeekBar_mcEItems));
    setProgress(localTypedArray.getInt(R.styleable.EnhanceSeekBar_mcEProgress, 0));
    setItemsCount(localTypedArray.getInt(R.styleable.EnhanceSeekBar_mcEItemsCount, 1));
    Drawable localDrawable = localTypedArray.getDrawable(R.styleable.EnhanceSeekBar_mcEThumb);
    paramAttributeSet = localDrawable;
    if (localDrawable == null) {
      paramAttributeSet = paramContext.getResources().getDrawable(R.drawable.mz_scrubber_control_selector);
    }
    mAuraThumb = localTypedArray.getDrawable(R.styleable.EnhanceSeekBar_mcAuraEnhanceThumbDrawble);
    mAuraRadis = ((int)localTypedArray.getDimension(R.styleable.EnhanceSeekBar_mcAuraEnhanceDistance, 10.0F));
    setThumb(paramAttributeSet);
    localTypedArray.recycle();
    mPaint = new Paint();
    mPaintColor = getResources().getColor(R.color.mc_enhance_seekbar_circle_color);
    mPaint.setColor(mPaintColor);
    mPaint.setStrokeWidth(4.0F);
    if (Build.VERSION.SDK_INT >= 21)
    {
      mInterpolator = new PathInterpolator(0.33F, 0.0F, 0.1F, 1.0F);
      return;
    }
    mInterpolator = new AccelerateInterpolator();
  }
  
  private void attemptClaimDrag()
  {
    if (getParent() != null) {
      getParent().requestDisallowInterceptTouchEvent(true);
    }
  }
  
  private void flingThumb(MotionEvent paramMotionEvent)
  {
    int k = getWidth();
    int m = getPaddingLeft();
    int n = getPaddingRight();
    int i1 = mHalfThumbWidth;
    int i2 = mAuraRadis;
    int i = (int)paramMotionEvent.getX();
    paramMotionEvent = mThumb.getBounds();
    float f = mInitialThumbX;
    int j = (int)(i + f - mInitialTouchX);
    if (j < 0) {
      i = 0;
    }
    for (;;)
    {
      setProgress(Math.round((i - getPaddingLeft() - mAuraRadis) / (k - m - n - i1 * 2 - i2 * 2) * getMax()), true);
      mThumb.setBounds(i, top, mThumb.getIntrinsicWidth() + i, bottom);
      invalidate();
      return;
      i = j;
      if (j > k - getPaddingRight() - mHalfThumbWidth * 2 - mAuraRadis * 2) {
        i = k - getPaddingRight() - mHalfThumbWidth * 2 - mAuraRadis * 2;
      }
    }
  }
  
  private int getMax()
  {
    try
    {
      int i = mMax;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private boolean isPointInside(int paramInt1, int paramInt2)
  {
    return true;
  }
  
  private void onProgressRefresh(float paramFloat)
  {
    Drawable localDrawable = mThumb;
    if (localDrawable != null) {
      setThumbPos(getWidth(), localDrawable, paramFloat, Integer.MIN_VALUE);
    }
  }
  
  /* Error */
  private void setMax(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iload_1
    //   3: istore_3
    //   4: iload_1
    //   5: ifge +5 -> 10
    //   8: iconst_0
    //   9: istore_3
    //   10: iload_3
    //   11: aload_0
    //   12: getfield 327	com/meizu/common/widget/EnhanceSeekBar:mMax	I
    //   15: if_icmpeq +45 -> 60
    //   18: aload_0
    //   19: iload_3
    //   20: putfield 327	com/meizu/common/widget/EnhanceSeekBar:mMax	I
    //   23: aload_0
    //   24: getfield 338	com/meizu/common/widget/EnhanceSeekBar:mProgress	I
    //   27: iload_3
    //   28: if_icmple +8 -> 36
    //   31: aload_0
    //   32: iload_3
    //   33: putfield 338	com/meizu/common/widget/EnhanceSeekBar:mProgress	I
    //   36: aload_0
    //   37: getfield 327	com/meizu/common/widget/EnhanceSeekBar:mMax	I
    //   40: ifle +23 -> 63
    //   43: aload_0
    //   44: getfield 338	com/meizu/common/widget/EnhanceSeekBar:mProgress	I
    //   47: i2f
    //   48: aload_0
    //   49: getfield 327	com/meizu/common/widget/EnhanceSeekBar:mMax	I
    //   52: i2f
    //   53: fdiv
    //   54: fstore_2
    //   55: aload_0
    //   56: fload_2
    //   57: invokespecial 340	com/meizu/common/widget/EnhanceSeekBar:onProgressRefresh	(F)V
    //   60: aload_0
    //   61: monitorexit
    //   62: return
    //   63: fconst_0
    //   64: fstore_2
    //   65: goto -10 -> 55
    //   68: astore 4
    //   70: aload_0
    //   71: monitorexit
    //   72: aload 4
    //   74: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	75	0	this	EnhanceSeekBar
    //   0	75	1	paramInt	int
    //   54	11	2	f	float
    //   3	30	3	i	int
    //   68	5	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   10	36	68	finally
    //   36	55	68	finally
    //   55	60	68	finally
  }
  
  private void setThumbOffset(int paramInt)
  {
    mThumbOffset = paramInt;
    invalidate();
  }
  
  private void setThumbPos(int paramInt1, Drawable paramDrawable, float paramFloat, int paramInt2)
  {
    int i = getPaddingLeft();
    int j = getPaddingRight();
    int k = mAuraRadis;
    int m = paramDrawable.getIntrinsicWidth();
    paramInt1 = paramInt1 - i - j - k * 2 - m;
    int n = paramInt1 - Math.round((1.0F - paramFloat) * paramInt1);
    if (paramInt2 == Integer.MIN_VALUE)
    {
      Rect localRect = paramDrawable.getBounds();
      i = top;
      j = bottom;
      paramInt1 = 1;
      k = paramInt1;
      if (mObjectAnimator != null)
      {
        if (paramInt1 != 0) {
          break label184;
        }
        mObjectAnimator.cancel();
        mObjectAnimator = null;
        k = paramInt1;
      }
    }
    for (;;)
    {
      if (k != 0)
      {
        paramInt1 = getBoundsleft;
        if (paramInt1 == n)
        {
          mIsInItemPositon = true;
          if ((paramInt2 == Integer.MIN_VALUE) && (mOnEnhanceSeekBarChangeListener != null)) {
            mOnEnhanceSeekBarChangeListener.onProgressChanged(this, getProgress());
          }
          return;
          j = paramDrawable.getIntrinsicHeight() + paramInt2;
          i = paramInt2;
          paramInt1 = 0;
          break;
          label184:
          k = paramInt1;
          if (!mObjectAnimator.isStarted()) {
            continue;
          }
          k = 0;
          continue;
        }
        mStartXY.setXY(paramInt1, i);
        mEndXY.setXY(n, i);
        mDrawableXYHolder.setDrawable(paramDrawable);
        mObjectAnimator = ObjectAnimator.ofObject(mDrawableXYHolder, "xY", mXYEvaluator, new Object[] { mStartXY, mEndXY });
        mObjectAnimator.setDuration(256L);
        mObjectAnimator.setInterpolator(mInterpolator);
        mObjectAnimator.addListener(new EnhanceSeekBar.1(this));
        mObjectAnimator.start();
      }
    }
    for (;;)
    {
      mIsInItemPositon = true;
      return;
      mThumb.setBounds(n, i, n + m, j);
      invalidate();
    }
  }
  
  private void trackTapUpTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = getWidth();
    int j = getPaddingLeft();
    int k = getPaddingRight();
    int m = mHalfThumbWidth;
    int n = mAuraRadis;
    float f1 = Math.round(((int)paramMotionEvent.getX() - getPaddingLeft() - mAuraRadis) / (i - j - k - m * 2 - n * 2) * getMax());
    float f2 = f1 / mMax;
    setProgress((int)f1, true);
    setThumbPos(getWidth(), mThumb, f2, Integer.MIN_VALUE);
  }
  
  private void trackTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = getWidth();
    int j = getPaddingLeft();
    int k = getPaddingRight();
    int m = mHalfThumbWidth;
    int n = mAuraRadis;
    int i1 = (int)paramMotionEvent.getX();
    float f = mInitialThumbX;
    i1 = (int)(i1 + f - mInitialTouchX);
    if (i1 < 0) {
      f = 0.0F;
    }
    for (;;)
    {
      setProgress(Math.round(f * getMax() + 0.0F), false);
      return;
      if (i1 > i - getPaddingRight() - mHalfThumbWidth * 2) {
        f = 1.0F;
      } else {
        f = (i1 - getPaddingLeft() - mAuraRadis) / (i - j - k - m * 2 - n * 2);
      }
    }
  }
  
  private void updateThumbPos(int paramInt1, int paramInt2)
  {
    Drawable localDrawable = mThumb;
    paramInt2 = getMax();
    if (paramInt2 > 0) {}
    for (float f = getProgress() / paramInt2;; f = 0.0F)
    {
      if (localDrawable != null) {
        setThumbPos(paramInt1, localDrawable, f, 0);
      }
      return;
    }
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    if ((mThumb != null) && (mThumb.isStateful()))
    {
      int[] arrayOfInt = getDrawableState();
      mThumb.setState(arrayOfInt);
    }
  }
  
  /* Error */
  public int getItemsCount()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 425	com/meizu/common/widget/EnhanceSeekBar:mItems	[Ljava/lang/CharSequence;
    //   6: ifnull +13 -> 19
    //   9: aload_0
    //   10: getfield 425	com/meizu/common/widget/EnhanceSeekBar:mItems	[Ljava/lang/CharSequence;
    //   13: arraylength
    //   14: istore_1
    //   15: aload_0
    //   16: monitorexit
    //   17: iload_1
    //   18: ireturn
    //   19: aload_0
    //   20: getfield 327	com/meizu/common/widget/EnhanceSeekBar:mMax	I
    //   23: istore_1
    //   24: goto -9 -> 15
    //   27: astore_2
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_2
    //   31: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	32	0	this	EnhanceSeekBar
    //   14	10	1	i	int
    //   27	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	15	27	finally
    //   19	24	27	finally
  }
  
  public int getProgress()
  {
    try
    {
      int i = mProgress;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    int j;
    float f1;
    for (;;)
    {
      try
      {
        super.onDraw(paramCanvas);
        if (mThumb == null) {
          break label486;
        }
        paramCanvas.save();
        if (mItems != null)
        {
          paramCanvas.translate(getPaddingLeft() + mHalfThumbWidth + mAuraRadis, getPaddingTop() + 50 + mHalfThumbHeight);
          float f2 = getWidth() - getPaddingLeft() - getPaddingRight() - mHalfThumbWidth * 2 - mAuraRadis * 2;
          j = getMax();
          if (j > 0)
          {
            f1 = f2 / j;
            mPaint.setColor(mPaintColor);
            mPaint.setAntiAlias(true);
            paramCanvas.drawLine(0.0F, 0.0F, f2, 0.0F, mPaint);
            i = 0;
            if (i > j) {
              break;
            }
            paramCanvas.drawCircle(i * f1, 0.0F, 6.0F, mPaint);
            i += 1;
            continue;
          }
        }
        else
        {
          paramCanvas.translate(getPaddingLeft() + mHalfThumbWidth + mAuraRadis, getPaddingTop() + mHalfThumbHeight);
          continue;
        }
        f1 = 0.0F;
      }
      finally {}
    }
    mPaint.setTextSize(40.0F);
    int i = 0;
    for (;;)
    {
      if (i <= j)
      {
        if (getProgress() == i) {
          mPaint.setColor(-12303292);
        }
        while (mItems != null)
        {
          String str = (String)mItems[i];
          paramCanvas.drawText(str, i * f1 - mPaint.measureText(str) / 2.0F, -mHalfThumbHeight, mPaint);
          break;
          mPaint.setColor(mPaintColor);
        }
      }
      paramCanvas.restore();
      paramCanvas.save();
      if (mItems != null) {
        paramCanvas.translate(getPaddingLeft() + mAuraRadis, getPaddingTop() + 50);
      }
      for (;;)
      {
        if ((mIsDrag) && (mAuraThumb != null))
        {
          i = mThumb.getBounds().centerX();
          j = mThumb.getBounds().centerY();
          int k = mThumb.getIntrinsicWidth() / 2;
          mAuraThumb.setBounds(i - k - mAuraRadis, j - k - mAuraRadis, i + k + mAuraRadis, j + k + mAuraRadis);
          mAuraThumb.setAlpha(204);
          mAuraThumb.draw(paramCanvas);
        }
        mThumb.draw(paramCanvas);
        paramCanvas.restore();
        label486:
        return;
        paramCanvas.translate(getPaddingLeft() + mAuraRadis, getPaddingTop());
      }
      i += 1;
    }
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    paramAccessibilityEvent.setClassName(EnhanceSeekBar.class.getName());
  }
  
  public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    super.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfo);
    paramAccessibilityNodeInfo.setClassName(EnhanceSeekBar.class.getName());
    if (isEnabled())
    {
      int i = getProgress();
      if (i > 0) {
        paramAccessibilityNodeInfo.addAction(8192);
      }
      if (i < getMax()) {
        paramAccessibilityNodeInfo.addAction(4096);
      }
    }
  }
  
  /* Error */
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 6
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_0
    //   6: getfield 285	com/meizu/common/widget/EnhanceSeekBar:mThumb	Landroid/graphics/drawable/Drawable;
    //   9: ifnonnull +124 -> 133
    //   12: iconst_0
    //   13: istore 4
    //   15: bipush 20
    //   17: istore_3
    //   18: aload_0
    //   19: getfield 425	com/meizu/common/widget/EnhanceSeekBar:mItems	[Ljava/lang/CharSequence;
    //   22: ifnull +6 -> 28
    //   25: bipush 70
    //   27: istore_3
    //   28: aload_0
    //   29: invokevirtual 274	com/meizu/common/widget/EnhanceSeekBar:getPaddingLeft	()I
    //   32: istore 7
    //   34: aload_0
    //   35: invokevirtual 277	com/meizu/common/widget/EnhanceSeekBar:getPaddingRight	()I
    //   38: istore 8
    //   40: iload_3
    //   41: istore 5
    //   43: iload 4
    //   45: ifeq +29 -> 74
    //   48: iload 6
    //   50: istore 5
    //   52: aload_0
    //   53: getfield 425	com/meizu/common/widget/EnhanceSeekBar:mItems	[Ljava/lang/CharSequence;
    //   56: ifnull +7 -> 63
    //   59: bipush 50
    //   61: istore 5
    //   63: iload 5
    //   65: iload 4
    //   67: iadd
    //   68: iload_3
    //   69: invokestatic 515	java/lang/Math:max	(II)I
    //   72: istore 5
    //   74: aload_0
    //   75: invokevirtual 437	com/meizu/common/widget/EnhanceSeekBar:getPaddingTop	()I
    //   78: istore_3
    //   79: aload_0
    //   80: invokevirtual 518	com/meizu/common/widget/EnhanceSeekBar:getPaddingBottom	()I
    //   83: istore 4
    //   85: aload_0
    //   86: getfield 200	com/meizu/common/widget/EnhanceSeekBar:mAuraRadis	I
    //   89: istore 6
    //   91: aload_0
    //   92: iload 7
    //   94: bipush 64
    //   96: iadd
    //   97: iload 8
    //   99: iadd
    //   100: iload_1
    //   101: invokestatic 523	android/view/View$MeasureSpec:getSize	(I)I
    //   104: invokestatic 515	java/lang/Math:max	(II)I
    //   107: iload_1
    //   108: iconst_0
    //   109: invokestatic 527	com/meizu/common/widget/EnhanceSeekBar:resolveSizeAndState	(III)I
    //   112: iload 5
    //   114: iload_3
    //   115: iload 4
    //   117: iadd
    //   118: iload 6
    //   120: iadd
    //   121: iadd
    //   122: iload_2
    //   123: iconst_0
    //   124: invokestatic 527	com/meizu/common/widget/EnhanceSeekBar:resolveSizeAndState	(III)I
    //   127: invokevirtual 530	com/meizu/common/widget/EnhanceSeekBar:setMeasuredDimension	(II)V
    //   130: aload_0
    //   131: monitorexit
    //   132: return
    //   133: aload_0
    //   134: getfield 285	com/meizu/common/widget/EnhanceSeekBar:mThumb	Landroid/graphics/drawable/Drawable;
    //   137: invokevirtual 365	android/graphics/drawable/Drawable:getIntrinsicHeight	()I
    //   140: istore 4
    //   142: goto -127 -> 15
    //   145: astore 9
    //   147: aload_0
    //   148: monitorexit
    //   149: aload 9
    //   151: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	152	0	this	EnhanceSeekBar
    //   0	152	1	paramInt1	int
    //   0	152	2	paramInt2	int
    //   17	101	3	i	int
    //   13	128	4	j	int
    //   41	81	5	k	int
    //   1	120	6	m	int
    //   32	65	7	n	int
    //   38	62	8	i1	int
    //   145	5	9	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   5	12	145	finally
    //   18	25	145	finally
    //   28	40	145	finally
    //   52	59	145	finally
    //   63	74	145	finally
    //   74	130	145	finally
    //   133	142	145	finally
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    setProgress(progress, true);
  }
  
  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    progress = mProgress;
    return localSavedState;
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    updateThumbPos(paramInt1, paramInt2);
  }
  
  void onStartTrackingTouch()
  {
    mIsDragging = true;
    if (mOnEnhanceSeekBarChangeListener != null) {
      mOnEnhanceSeekBarChangeListener.onStartTrackingTouch(this);
    }
  }
  
  void onStopTrackingTouch()
  {
    mIsDragging = false;
    if (mOnEnhanceSeekBarChangeListener != null) {
      mOnEnhanceSeekBarChangeListener.onStopTrackingTouch(this);
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!isEnabled()) {}
    while (getMax() == 0) {
      return false;
    }
    float f = paramMotionEvent.getX();
    switch (paramMotionEvent.getAction())
    {
    }
    for (;;)
    {
      return true;
      mInitialTouchX = f;
      if (mThumb != null) {
        mInitialThumbX = mThumb.getBounds().left;
      }
      int i = getWidth();
      int j = getPaddingLeft();
      int k = getPaddingRight();
      int m = mHalfThumbWidth;
      int n = mAuraRadis;
      mTouchDownProgress = Math.round((f - getPaddingLeft() - mAuraRadis) / (i - j - k - m * 2 - n * 2) * getMax());
      if (isPointInside((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY()))
      {
        setPressed(true);
        if (mThumb != null) {
          invalidate(mThumb.getBounds());
        }
        onStartTrackingTouch();
      }
      attemptClaimDrag();
      hasMoved = false;
      continue;
      if (mIsDragging)
      {
        mIsInItemPositon = false;
        flingThumb(paramMotionEvent);
        attemptClaimDrag();
      }
      if (Math.abs(paramMotionEvent.getX() - mInitialTouchX) > mScaledTouchSlop)
      {
        hasMoved = true;
        mIsDrag = true;
        if (mAuraThumb != null) {
          invalidate(mAuraThumb.getBounds());
        }
      }
      else
      {
        hasMoved = false;
        continue;
        if (!hasMoved)
        {
          trackTapUpTouchEvent(paramMotionEvent);
        }
        else
        {
          mIsDrag = false;
          if (mAuraThumb != null) {
            invalidate(mAuraThumb.getBounds());
          }
          if (mIsDragging)
          {
            trackTouchEvent(paramMotionEvent);
            onStopTrackingTouch();
            setPressed(false);
          }
          else
          {
            setProgress(mTouchDownProgress, false);
            continue;
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
  }
  
  public boolean performAccessibilityAction(int paramInt, Bundle paramBundle)
  {
    if (super.performAccessibilityAction(paramInt, paramBundle)) {
      return true;
    }
    if (!isEnabled()) {
      return false;
    }
    int i = getProgress();
    int j = Math.max(1, Math.round(getMax() / 5.0F));
    switch (paramInt)
    {
    default: 
      return false;
    case 8192: 
      if (i <= 0) {
        return false;
      }
      setProgress(i - j, false);
      return true;
    }
    if (i >= getMax()) {
      return false;
    }
    setProgress(i + j, false);
    return true;
  }
  
  public void setItems(CharSequence[] paramArrayOfCharSequence)
  {
    if ((paramArrayOfCharSequence == null) || (paramArrayOfCharSequence.length == 0))
    {
      mItems = null;
      setMax(0);
      return;
    }
    int i = paramArrayOfCharSequence.length;
    mItems = new CharSequence[i];
    System.arraycopy(paramArrayOfCharSequence, 0, mItems, 0, i);
    setMax(i - 1);
  }
  
  public void setItemsCount(int paramInt)
  {
    if ((mItems != null) && (mItems.length < paramInt))
    {
      setMax(mItems.length - 1);
      return;
    }
    if (paramInt < 1)
    {
      setMax(0);
      return;
    }
    setMax(paramInt - 1);
  }
  
  public void setOnEnhanceSeekBarChangeListener(OnEnhanceSeekBarChangeListener paramOnEnhanceSeekBarChangeListener)
  {
    mOnEnhanceSeekBarChangeListener = paramOnEnhanceSeekBarChangeListener;
  }
  
  public void setPaintColor(int paramInt)
  {
    mPaintColor = paramInt;
  }
  
  public void setProgress(int paramInt)
  {
    try
    {
      setProgress(paramInt, false);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  void setProgress(int paramInt, boolean paramBoolean)
  {
    if (paramInt < 0)
    {
      paramInt = 0;
      for (;;)
      {
        int i = paramInt;
        try
        {
          if (paramInt > mMax) {
            i = mMax;
          }
          float f;
          if ((i != mProgress) || (!mIsInItemPositon))
          {
            mProgress = i;
            if (paramBoolean) {
              break label83;
            }
            if (mMax <= 0) {
              break label78;
            }
            f = mProgress / mMax;
            onProgressRefresh(f);
          }
          for (;;)
          {
            return;
            label78:
            f = 0.0F;
            break;
            label83:
            if ((mOnEnhanceSeekBarChangeListener != null) && (mIsDragging)) {
              mOnEnhanceSeekBarChangeListener.onProgressDragging(this, getProgress());
            }
          }
        }
        finally {}
      }
    }
  }
  
  public void setThumb(Drawable paramDrawable)
  {
    Drawable localDrawable = paramDrawable;
    if (paramDrawable == null) {
      localDrawable = getResources().getDrawable(R.drawable.mz_scrubber_control_selector);
    }
    if ((mThumb != null) && (localDrawable != mThumb))
    {
      mThumb.setCallback(null);
      mThumb.getIntrinsicWidth();
    }
    for (int i = 1;; i = 0)
    {
      if (localDrawable != null)
      {
        if (i != 0) {}
        localDrawable.setCallback(this);
        mThumbOffset = (localDrawable.getIntrinsicWidth() / 2);
        if ((i != 0) && ((localDrawable.getIntrinsicWidth() != mThumb.getIntrinsicWidth()) || (localDrawable.getIntrinsicHeight() != mThumb.getIntrinsicHeight()))) {
          requestLayout();
        }
        mHalfThumbWidth = (localDrawable.getIntrinsicWidth() / 2);
        mHalfThumbHeight = (localDrawable.getIntrinsicHeight() / 2);
      }
      mThumb = localDrawable;
      invalidate();
      if (i != 0)
      {
        updateThumbPos(getWidth(), getHeight());
        if ((localDrawable != null) && (localDrawable.isStateful())) {
          localDrawable.setState(getDrawableState());
        }
      }
      return;
    }
  }
  
  class DrawableXYHolder
  {
    private Drawable mDrawable;
    
    public DrawableXYHolder() {}
    
    public DrawableXYHolder(Drawable paramDrawable)
    {
      mDrawable = paramDrawable;
    }
    
    public EnhanceSeekBar.XYHolder getXY()
    {
      if (mDrawable == null) {
        return null;
      }
      Rect localRect = mDrawable.getBounds();
      return new EnhanceSeekBar.XYHolder(EnhanceSeekBar.this, left, top);
    }
    
    public void setDrawable(Drawable paramDrawable)
    {
      mDrawable = paramDrawable;
    }
    
    public void setXY(EnhanceSeekBar.XYHolder paramXYHolder)
    {
      if (mDrawable == null) {
        return;
      }
      mDrawable.setBounds((int)paramXYHolder.getX(), (int)paramXYHolder.getY(), (int)(paramXYHolder.getX() + mDrawable.getIntrinsicWidth()), (int)(paramXYHolder.getY() + mDrawable.getIntrinsicHeight()));
      invalidate();
    }
  }
  
  public static abstract interface OnEnhanceSeekBarChangeListener
  {
    public abstract void onProgressChanged(EnhanceSeekBar paramEnhanceSeekBar, int paramInt);
    
    public abstract void onProgressDragging(EnhanceSeekBar paramEnhanceSeekBar, int paramInt);
    
    public abstract void onStartTrackingTouch(EnhanceSeekBar paramEnhanceSeekBar);
    
    public abstract void onStopTrackingTouch(EnhanceSeekBar paramEnhanceSeekBar);
  }
  
  static class SavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new EnhanceSeekBar.SavedState.1();
    int progress;
    
    private SavedState(Parcel paramParcel)
    {
      super();
      progress = paramParcel.readInt();
    }
    
    SavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(progress);
    }
  }
  
  class XYEvaluator
    implements TypeEvaluator
  {
    private XYEvaluator() {}
    
    public Object evaluate(float paramFloat, Object paramObject1, Object paramObject2)
    {
      paramObject1 = (EnhanceSeekBar.XYHolder)paramObject1;
      paramObject2 = (EnhanceSeekBar.XYHolder)paramObject2;
      return new EnhanceSeekBar.XYHolder(EnhanceSeekBar.this, ((EnhanceSeekBar.XYHolder)paramObject1).getX() + (((EnhanceSeekBar.XYHolder)paramObject2).getX() - ((EnhanceSeekBar.XYHolder)paramObject1).getX()) * paramFloat, ((EnhanceSeekBar.XYHolder)paramObject1).getY() + (((EnhanceSeekBar.XYHolder)paramObject2).getY() - ((EnhanceSeekBar.XYHolder)paramObject1).getY()) * paramFloat);
    }
  }
  
  class XYHolder
  {
    private float mX;
    private float mY;
    
    public XYHolder()
    {
      mY = 0.0F;
      mX = 0.0F;
    }
    
    public XYHolder(float paramFloat1, float paramFloat2)
    {
      mX = paramFloat1;
      mY = paramFloat2;
    }
    
    public float getX()
    {
      return mX;
    }
    
    public float getY()
    {
      return mY;
    }
    
    public void setX(float paramFloat)
    {
      mX = paramFloat;
    }
    
    public void setXY(float paramFloat1, float paramFloat2)
    {
      mX = paramFloat1;
      mY = paramFloat2;
    }
    
    public void setY(float paramFloat)
    {
      mY = paramFloat;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.EnhanceSeekBar
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */