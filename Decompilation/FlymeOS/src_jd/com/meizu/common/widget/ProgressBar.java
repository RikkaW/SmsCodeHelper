package com.meizu.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewDebug.ExportedProperty;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.RemoteViews.RemoteView;
import com.meizu.common.R.attr;
import com.meizu.common.R.styleable;

@RemoteViews.RemoteView
public class ProgressBar
  extends View
{
  private static final int MAX_LEVEL = 10000;
  private AlphaAnimation mAnimation;
  private int mBehavior;
  private int mBreakPoint = 0;
  private Drawable mCurrentDrawable;
  private int mDuration;
  private boolean mHasAnimation;
  private boolean mInDrawing;
  private boolean mIndeterminate;
  private Drawable mIndeterminateDrawable;
  private Interpolator mInterpolator;
  private boolean mIsVertical = false;
  private int mMax;
  int mMaxHeight;
  int mMaxWidth;
  int mMinHeight;
  int mMinWidth;
  private boolean mNoInvalidate;
  private boolean mOnlyIndeterminate;
  private int mProgress;
  private Drawable mProgressDrawable;
  private boolean mRefreshIsPosted;
  private RefreshProgressRunnable mRefreshProgressRunnable;
  Bitmap mSampleTile;
  private int mSecondaryProgress;
  private boolean mShouldStartAnimationDrawable;
  private Transformation mTransformation;
  private long mUiThreadId = Thread.currentThread().getId();
  
  public ProgressBar(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ProgressBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.MeizuCommon_ProgressBarStyle);
  }
  
  public ProgressBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    this(paramContext, paramAttributeSet, paramInt, 0);
  }
  
  public ProgressBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1);
    initProgressBar();
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ProgressBar, paramInt1, paramInt2);
    mNoInvalidate = true;
    Drawable localDrawable = paramAttributeSet.getDrawable(R.styleable.ProgressBar_mcProgressDrawable);
    if (localDrawable != null) {
      setProgressDrawable(tileify(localDrawable, false));
    }
    mDuration = paramAttributeSet.getInt(R.styleable.ProgressBar_mcIndeterminateDuration, mDuration);
    mMinWidth = paramAttributeSet.getDimensionPixelSize(R.styleable.ProgressBar_mcMinWidth, mMinWidth);
    mMaxWidth = paramAttributeSet.getDimensionPixelSize(R.styleable.ProgressBar_mcMaxWidth, mMaxWidth);
    mMinHeight = paramAttributeSet.getDimensionPixelSize(R.styleable.ProgressBar_mcMinHeight, mMinHeight);
    mMaxHeight = paramAttributeSet.getDimensionPixelSize(R.styleable.ProgressBar_mcMaxHeight, mMaxHeight);
    mBehavior = paramAttributeSet.getInt(R.styleable.ProgressBar_mcIndeterminateBehavior, mBehavior);
    paramInt1 = paramAttributeSet.getResourceId(R.styleable.ProgressBar_mcInterpolator, 17432587);
    if (paramInt1 > 0) {
      setInterpolator(paramContext, paramInt1);
    }
    setMax(paramAttributeSet.getInt(R.styleable.ProgressBar_mcMax, mMax));
    setProgress(paramAttributeSet.getInt(R.styleable.ProgressBar_mcProgress, mProgress));
    setSecondaryProgress(paramAttributeSet.getInt(R.styleable.ProgressBar_mcSecondaryProgress, mSecondaryProgress));
    paramContext = paramAttributeSet.getDrawable(R.styleable.ProgressBar_mcIndeterminateDrawable);
    if (paramContext != null) {
      setIndeterminateDrawable(tileifyIndeterminate(paramContext));
    }
    mOnlyIndeterminate = paramAttributeSet.getBoolean(R.styleable.ProgressBar_mcIndeterminateOnly, mOnlyIndeterminate);
    mNoInvalidate = false;
    if ((mOnlyIndeterminate) || (paramAttributeSet.getBoolean(R.styleable.ProgressBar_mcIndeterminate, mIndeterminate))) {
      bool = true;
    }
    setIndeterminate(bool);
    paramAttributeSet.recycle();
  }
  
  private void doRefreshProgress(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    for (;;)
    {
      Object localObject2;
      try
      {
        float f;
        if (mMax > 0)
        {
          f = paramInt2 / mMax;
          localObject2 = mCurrentDrawable;
          if (localObject2 != null)
          {
            Drawable localDrawable = null;
            if (!(localObject2 instanceof LayerDrawable)) {
              break label103;
            }
            localDrawable = ((LayerDrawable)localObject2).findDrawableByLayerId(paramInt1);
            break label103;
            ((Drawable)localObject2).setLevel(paramInt2);
            if ((paramBoolean2) && (paramInt1 == 16908301)) {
              onProgressRefresh(f, paramBoolean1);
            }
          }
        }
        else
        {
          f = 0.0F;
          continue;
        }
        invalidate();
        continue;
        paramInt2 = (int)(10000.0F * f);
      }
      finally {}
      label103:
      if (localObject1 != null) {
        localObject2 = localObject1;
      }
    }
  }
  
  private void initProgressBar()
  {
    mMax = 100;
    mProgress = 0;
    mSecondaryProgress = 0;
    mIndeterminate = false;
    mOnlyIndeterminate = false;
    mDuration = 4000;
    mBehavior = 1;
    mMinWidth = 24;
    mMaxWidth = 48;
    mMinHeight = 24;
    mMaxHeight = 48;
  }
  
  private void refreshProgress(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    for (;;)
    {
      try
      {
        if (mUiThreadId == Thread.currentThread().getId())
        {
          doRefreshProgress(paramInt1, paramInt2, paramBoolean, true);
          return;
        }
        if (mRefreshProgressRunnable != null)
        {
          RefreshProgressRunnable localRefreshProgressRunnable1 = mRefreshProgressRunnable;
          mRefreshProgressRunnable = null;
          localRefreshProgressRunnable1.setup(paramInt1, paramInt2, paramBoolean);
          post(localRefreshProgressRunnable1);
        }
        else
        {
          RefreshProgressRunnable localRefreshProgressRunnable2 = new RefreshProgressRunnable(paramInt1, paramInt2, paramBoolean);
        }
      }
      finally {}
    }
  }
  
  private Drawable tileify(Drawable paramDrawable, boolean paramBoolean)
  {
    int j = 0;
    Object localObject = paramDrawable;
    if ((paramDrawable instanceof LayerDrawable))
    {
      paramDrawable = (LayerDrawable)paramDrawable;
      int k = paramDrawable.getNumberOfLayers();
      localObject = new Drawable[k];
      int i = 0;
      if (i < k)
      {
        int m = paramDrawable.getId(i);
        Drawable localDrawable = paramDrawable.getDrawable(i);
        if ((m == 16908301) || (m == 16908303)) {}
        for (paramBoolean = true;; paramBoolean = false)
        {
          localObject[i] = tileify(localDrawable, paramBoolean);
          i += 1;
          break;
        }
      }
      localObject = new LayerDrawable((Drawable[])localObject);
      i = j;
      while (i < k)
      {
        ((LayerDrawable)localObject).setId(i, paramDrawable.getId(i));
        i += 1;
      }
    }
    return (Drawable)localObject;
  }
  
  private Drawable tileifyIndeterminate(Drawable paramDrawable)
  {
    Object localObject = paramDrawable;
    if ((paramDrawable instanceof AnimationDrawable))
    {
      paramDrawable = (AnimationDrawable)paramDrawable;
      int j = paramDrawable.getNumberOfFrames();
      localObject = new AnimationDrawable();
      ((AnimationDrawable)localObject).setOneShot(paramDrawable.isOneShot());
      int i = 0;
      while (i < j)
      {
        Drawable localDrawable = tileify(paramDrawable.getFrame(i), true);
        localDrawable.setLevel(10000);
        ((AnimationDrawable)localObject).addFrame(localDrawable, paramDrawable.getDuration(i));
        i += 1;
      }
      ((AnimationDrawable)localObject).setLevel(10000);
    }
    return (Drawable)localObject;
  }
  
  private void updateDrawableBounds(int paramInt1, int paramInt2)
  {
    int j = paramInt1 - getPaddingRight() - getPaddingLeft();
    int i = paramInt2 - getPaddingBottom() - getPaddingTop();
    int k;
    float f1;
    float f2;
    if (mIndeterminateDrawable != null) {
      if ((mOnlyIndeterminate) && (!(mIndeterminateDrawable instanceof AnimationDrawable)))
      {
        k = mIndeterminateDrawable.getIntrinsicWidth();
        int m = mIndeterminateDrawable.getIntrinsicHeight();
        f1 = k / m;
        f2 = paramInt1 / paramInt2;
        if (f1 != f2) {
          if (f2 > f1)
          {
            paramInt2 = (int)(f1 * paramInt2);
            k = (paramInt1 - paramInt2) / 2;
            paramInt2 += k;
            paramInt1 = i;
            j = 0;
            i = k;
            mIndeterminateDrawable.setBounds(i, j, paramInt2, paramInt1);
          }
        }
      }
    }
    for (;;)
    {
      if (mProgressDrawable != null) {
        mProgressDrawable.setBounds(0, 0, paramInt2, paramInt1);
      }
      return;
      f2 = paramInt1;
      paramInt1 = (int)(1.0F / f1 * f2);
      i = (paramInt2 - paramInt1) / 2;
      paramInt2 = j;
      paramInt1 += i;
      j = i;
      i = 0;
      break;
      k = 0;
      paramInt2 = j;
      paramInt1 = i;
      j = 0;
      i = k;
      break;
      paramInt1 = i;
      paramInt2 = j;
    }
  }
  
  private void updateDrawableState()
  {
    int[] arrayOfInt = getDrawableState();
    if ((mProgressDrawable != null) && (mProgressDrawable.isStateful())) {
      mProgressDrawable.setState(arrayOfInt);
    }
    if ((mIndeterminateDrawable != null) && (mIndeterminateDrawable.isStateful())) {
      mIndeterminateDrawable.setState(arrayOfInt);
    }
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    updateDrawableState();
  }
  
  Drawable getCurrentDrawable()
  {
    return mCurrentDrawable;
  }
  
  Shape getDrawableShape()
  {
    return new RoundRectShape(new float[] { 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F }, null, null);
  }
  
  public Drawable getIndeterminateDrawable()
  {
    return mIndeterminateDrawable;
  }
  
  public Interpolator getInterpolator()
  {
    return mInterpolator;
  }
  
  @ViewDebug.ExportedProperty(category="progress")
  public int getMax()
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
  
  /* Error */
  @ViewDebug.ExportedProperty(category="progress")
  public int getProgress()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 217	com/meizu/common/widget/ProgressBar:mIndeterminate	Z
    //   6: istore_2
    //   7: iload_2
    //   8: ifeq +9 -> 17
    //   11: iconst_0
    //   12: istore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: iload_1
    //   16: ireturn
    //   17: aload_0
    //   18: getfield 182	com/meizu/common/widget/ProgressBar:mProgress	I
    //   21: istore_1
    //   22: goto -9 -> 13
    //   25: astore_3
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_3
    //   29: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	30	0	this	ProgressBar
    //   12	10	1	i	int
    //   6	2	2	bool	boolean
    //   25	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	25	finally
    //   17	22	25	finally
  }
  
  public Drawable getProgressDrawable()
  {
    return mProgressDrawable;
  }
  
  /* Error */
  @ViewDebug.ExportedProperty(category="progress")
  public int getSecondaryProgress()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 217	com/meizu/common/widget/ProgressBar:mIndeterminate	Z
    //   6: istore_2
    //   7: iload_2
    //   8: ifeq +9 -> 17
    //   11: iconst_0
    //   12: istore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: iload_1
    //   16: ireturn
    //   17: aload_0
    //   18: getfield 190	com/meizu/common/widget/ProgressBar:mSecondaryProgress	I
    //   21: istore_1
    //   22: goto -9 -> 13
    //   25: astore_3
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_3
    //   29: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	30	0	this	ProgressBar
    //   12	10	1	i	int
    //   6	2	2	bool	boolean
    //   25	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	25	finally
    //   17	22	25	finally
  }
  
  public final void incrementProgressBy(int paramInt)
  {
    try
    {
      setProgress(mProgress + paramInt);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public final void incrementSecondaryProgressBy(int paramInt)
  {
    try
    {
      setSecondaryProgress(mSecondaryProgress + paramInt);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void invalidateDrawable(Drawable paramDrawable)
  {
    if (!mInDrawing)
    {
      if (verifyDrawable(paramDrawable))
      {
        paramDrawable = paramDrawable.getBounds();
        int i = getScrollX() + getPaddingLeft();
        int j = getScrollY() + getPaddingTop();
        invalidate(left + i, top + j, i + right, bottom + j);
      }
    }
    else {
      return;
    }
    super.invalidateDrawable(paramDrawable);
  }
  
  @ViewDebug.ExportedProperty(category="progress")
  public boolean isIndeterminate()
  {
    try
    {
      boolean bool = mIndeterminate;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  protected boolean isIsVertical()
  {
    return mIsVertical;
  }
  
  public void jumpDrawablesToCurrentState()
  {
    super.jumpDrawablesToCurrentState();
    if (mProgressDrawable != null) {
      mProgressDrawable.jumpToCurrentState();
    }
    if (mIndeterminateDrawable != null) {
      mIndeterminateDrawable.jumpToCurrentState();
    }
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (mIndeterminate) {
      startAnimation();
    }
  }
  
  protected void onDetachedFromWindow()
  {
    if (mIndeterminate) {
      stopAnimation();
    }
    if (mRefreshProgressRunnable != null) {
      removeCallbacks(mRefreshProgressRunnable);
    }
    if ((mRefreshProgressRunnable != null) && (mRefreshIsPosted)) {
      removeCallbacks(mRefreshProgressRunnable);
    }
    super.onDetachedFromWindow();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    for (;;)
    {
      Drawable localDrawable;
      int j;
      float f;
      try
      {
        super.onDraw(paramCanvas);
        localDrawable = mCurrentDrawable;
        if (localDrawable != null)
        {
          if (!mIsVertical) {
            continue;
          }
          i = getHeight() - getPaddingTop() - getPaddingBottom();
          j = mBreakPoint * i / getMax();
          if (!mIsVertical) {
            continue;
          }
          localRect2 = new Rect(0, 0, getWidth(), i - j);
          localRect1 = new Rect(0, i - j + 5, getWidth(), i);
          paramCanvas.save();
          paramCanvas.translate(getPaddingLeft(), getPaddingTop());
          long l = getDrawingTime();
          if (mHasAnimation)
          {
            mAnimation.getTransformation(l, mTransformation);
            f = mTransformation.getAlpha();
          }
        }
      }
      finally {}
      try
      {
        mInDrawing = true;
        localDrawable.setLevel((int)(f * 10000.0F));
        mInDrawing = false;
        postInvalidate();
        if ((mBreakPoint == getMax()) || (mBreakPoint == 0)) {
          break label354;
        }
        paramCanvas.clipRect(localRect2);
        localDrawable.draw(paramCanvas);
        paramCanvas.restore();
        paramCanvas.save();
        paramCanvas.translate(getPaddingLeft(), getPaddingTop());
        paramCanvas.clipRect(localRect1);
        localDrawable.draw(paramCanvas);
        paramCanvas.restore();
        if ((mShouldStartAnimationDrawable) && ((localDrawable instanceof Animatable)))
        {
          ((Animatable)localDrawable).start();
          mShouldStartAnimationDrawable = false;
        }
        return;
      }
      finally
      {
        mInDrawing = false;
      }
      int i = getWidth() - getPaddingLeft() - getPaddingRight();
      continue;
      Rect localRect2 = new Rect(0, 0, j, getHeight());
      Rect localRect1 = new Rect(j + 5, 0, i, getHeight());
      continue;
      label354:
      localDrawable.draw(paramCanvas);
      paramCanvas.restore();
    }
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    paramAccessibilityEvent.setClassName(ProgressBar.class.getName());
    paramAccessibilityEvent.setItemCount(mMax);
    paramAccessibilityEvent.setCurrentItemIndex(mProgress);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = 0;
    for (;;)
    {
      try
      {
        Drawable localDrawable = mCurrentDrawable;
        if (localDrawable != null)
        {
          j = Math.max(mMinWidth, Math.min(mMaxWidth, localDrawable.getIntrinsicWidth()));
          i = Math.max(mMinHeight, Math.min(mMaxHeight, localDrawable.getIntrinsicHeight()));
          updateDrawableState();
          int k = getPaddingLeft();
          int m = getPaddingRight();
          int n = getPaddingTop();
          int i1 = getPaddingBottom();
          setMeasuredDimension(resolveSizeAndState(j + (k + m), paramInt1, 0), resolveSizeAndState(i + (n + i1), paramInt2, 0));
          return;
        }
      }
      finally {}
      int j = 0;
    }
  }
  
  void onProgressRefresh(float paramFloat, boolean paramBoolean) {}
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    setProgress(progress);
    setSecondaryProgress(secondaryProgress);
  }
  
  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    progress = mProgress;
    secondaryProgress = mSecondaryProgress;
    return localSavedState;
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    updateDrawableBounds(paramInt1, paramInt2);
  }
  
  protected void onVisibilityChanged(View paramView, int paramInt)
  {
    super.onVisibilityChanged(paramView, paramInt);
    if (mIndeterminate)
    {
      if ((paramInt == 8) || (paramInt == 4)) {
        stopAnimation();
      }
    }
    else {
      return;
    }
    startAnimation();
  }
  
  public void postInvalidate()
  {
    if (!mNoInvalidate) {
      super.postInvalidate();
    }
  }
  
  public void setBreakPoint(int paramInt)
  {
    mBreakPoint = paramInt;
    invalidate();
  }
  
  /* Error */
  public void setIndeterminate(boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 208	com/meizu/common/widget/ProgressBar:mOnlyIndeterminate	Z
    //   6: ifeq +10 -> 16
    //   9: aload_0
    //   10: getfield 217	com/meizu/common/widget/ProgressBar:mIndeterminate	Z
    //   13: ifne +32 -> 45
    //   16: iload_1
    //   17: aload_0
    //   18: getfield 217	com/meizu/common/widget/ProgressBar:mIndeterminate	Z
    //   21: if_icmpeq +24 -> 45
    //   24: aload_0
    //   25: iload_1
    //   26: putfield 217	com/meizu/common/widget/ProgressBar:mIndeterminate	Z
    //   29: iload_1
    //   30: ifeq +18 -> 48
    //   33: aload_0
    //   34: aload_0
    //   35: getfield 324	com/meizu/common/widget/ProgressBar:mIndeterminateDrawable	Landroid/graphics/drawable/Drawable;
    //   38: putfield 236	com/meizu/common/widget/ProgressBar:mCurrentDrawable	Landroid/graphics/drawable/Drawable;
    //   41: aload_0
    //   42: invokevirtual 427	com/meizu/common/widget/ProgressBar:startAnimation	()V
    //   45: aload_0
    //   46: monitorexit
    //   47: return
    //   48: aload_0
    //   49: aload_0
    //   50: getfield 336	com/meizu/common/widget/ProgressBar:mProgressDrawable	Landroid/graphics/drawable/Drawable;
    //   53: putfield 236	com/meizu/common/widget/ProgressBar:mCurrentDrawable	Landroid/graphics/drawable/Drawable;
    //   56: aload_0
    //   57: invokevirtual 431	com/meizu/common/widget/ProgressBar:stopAnimation	()V
    //   60: goto -15 -> 45
    //   63: astore_2
    //   64: aload_0
    //   65: monitorexit
    //   66: aload_2
    //   67: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	68	0	this	ProgressBar
    //   0	68	1	paramBoolean	boolean
    //   63	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	16	63	finally
    //   16	29	63	finally
    //   33	45	63	finally
    //   48	60	63	finally
  }
  
  public void setIndeterminateDrawable(Drawable paramDrawable)
  {
    if (paramDrawable != null) {
      paramDrawable.setCallback(this);
    }
    mIndeterminateDrawable = paramDrawable;
    if (mIndeterminate)
    {
      mCurrentDrawable = paramDrawable;
      postInvalidate();
    }
  }
  
  public void setInterpolator(Context paramContext, int paramInt)
  {
    setInterpolator(AnimationUtils.loadInterpolator(paramContext, paramInt));
  }
  
  public void setInterpolator(Interpolator paramInterpolator)
  {
    mInterpolator = paramInterpolator;
  }
  
  protected void setIsVertical(boolean paramBoolean)
  {
    mIsVertical = paramBoolean;
  }
  
  public void setMax(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0) {
      i = 0;
    }
    try
    {
      if (i != mMax)
      {
        mMax = i;
        postInvalidate();
        if (mProgress > i) {
          mProgress = i;
        }
        refreshProgress(16908301, mProgress, false);
      }
      return;
    }
    finally {}
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
    try
    {
      boolean bool = mIndeterminate;
      if (bool) {
        return;
      }
      if (paramInt < 0) {
        paramInt = 0;
      }
      for (;;)
      {
        int i = paramInt;
        if (paramInt > mMax) {
          i = mMax;
        }
        if (i == mProgress) {
          break;
        }
        mProgress = i;
        refreshProgress(16908301, mProgress, paramBoolean);
        break;
      }
    }
    finally {}
  }
  
  public void setProgressDrawable(Drawable paramDrawable)
  {
    if ((mProgressDrawable != null) && (paramDrawable != mProgressDrawable)) {
      mProgressDrawable.setCallback(null);
    }
    for (int i = 1;; i = 0)
    {
      if (paramDrawable != null)
      {
        paramDrawable.setCallback(this);
        int j = paramDrawable.getMinimumHeight();
        if (mMaxHeight < j)
        {
          mMaxHeight = j;
          requestLayout();
        }
      }
      mProgressDrawable = paramDrawable;
      if (!mIndeterminate)
      {
        mCurrentDrawable = paramDrawable;
        postInvalidate();
      }
      if (i != 0)
      {
        updateDrawableBounds(getWidth(), getHeight());
        updateDrawableState();
        doRefreshProgress(16908301, mProgress, false, false);
        doRefreshProgress(16908303, mSecondaryProgress, false, false);
      }
      return;
    }
  }
  
  public void setProgressDrawableResource(int paramInt)
  {
    Drawable localDrawable1 = getProgressDrawable();
    Drawable localDrawable2 = getContext().getResources().getDrawable(paramInt);
    localDrawable2.setBounds(localDrawable1.copyBounds());
    setProgressDrawable(localDrawable2);
    if (mProgress > 0)
    {
      incrementProgressBy(-1);
      incrementProgressBy(1);
    }
  }
  
  public void setSecondaryProgress(int paramInt)
  {
    int i = 0;
    try
    {
      boolean bool = mIndeterminate;
      if (bool) {
        return;
      }
      if (paramInt < 0) {
        paramInt = i;
      }
      for (;;)
      {
        i = paramInt;
        if (paramInt > mMax) {
          i = mMax;
        }
        if (i == mSecondaryProgress) {
          break;
        }
        mSecondaryProgress = i;
        refreshProgress(16908303, mSecondaryProgress, false);
        break;
      }
    }
    finally {}
  }
  
  public void setVisibility(int paramInt)
  {
    if (getVisibility() != paramInt)
    {
      super.setVisibility(paramInt);
      if (mIndeterminate)
      {
        if ((paramInt != 8) && (paramInt != 4)) {
          break label36;
        }
        stopAnimation();
      }
    }
    return;
    label36:
    startAnimation();
  }
  
  void startAnimation()
  {
    if (getVisibility() != 0) {
      return;
    }
    if ((mIndeterminateDrawable instanceof Animatable))
    {
      mShouldStartAnimationDrawable = true;
      mAnimation = null;
    }
    for (;;)
    {
      postInvalidate();
      return;
      if (mInterpolator == null) {
        mInterpolator = new LinearInterpolator();
      }
      mTransformation = new Transformation();
      mAnimation = new AlphaAnimation(0.0F, 1.0F);
      mAnimation.setRepeatMode(mBehavior);
      mAnimation.setRepeatCount(-1);
      mAnimation.setDuration(mDuration);
      mAnimation.setInterpolator(mInterpolator);
      mAnimation.setStartTime(-1L);
    }
  }
  
  void stopAnimation()
  {
    mAnimation = null;
    mTransformation = null;
    if ((mIndeterminateDrawable instanceof Animatable))
    {
      ((Animatable)mIndeterminateDrawable).stop();
      mShouldStartAnimationDrawable = false;
    }
    postInvalidate();
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable)
  {
    return (paramDrawable == mProgressDrawable) || (paramDrawable == mIndeterminateDrawable) || (super.verifyDrawable(paramDrawable));
  }
  
  class RefreshProgressRunnable
    implements Runnable
  {
    private boolean mFromUser;
    private int mId;
    private int mProgress;
    
    RefreshProgressRunnable(int paramInt1, int paramInt2, boolean paramBoolean)
    {
      mId = paramInt1;
      mProgress = paramInt2;
      mFromUser = paramBoolean;
    }
    
    public void run()
    {
      ProgressBar.this.doRefreshProgress(mId, mProgress, mFromUser, true);
      ProgressBar.access$102(ProgressBar.this, this);
    }
    
    public void setup(int paramInt1, int paramInt2, boolean paramBoolean)
    {
      mId = paramInt1;
      mProgress = paramInt2;
      mFromUser = paramBoolean;
    }
  }
  
  static class SavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new ProgressBar.SavedState.1();
    int progress;
    int secondaryProgress;
    
    private SavedState(Parcel paramParcel)
    {
      super();
      progress = paramParcel.readInt();
      secondaryProgress = paramParcel.readInt();
    }
    
    SavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(progress);
      paramParcel.writeInt(secondaryProgress);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.ProgressBar
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */