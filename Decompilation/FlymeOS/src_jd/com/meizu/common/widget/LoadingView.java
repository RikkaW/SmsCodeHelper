package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.meizu.common.R.attr;
import com.meizu.common.R.styleable;

@SuppressLint({"NewApi"})
public class LoadingView
  extends View
{
  private static final int LOADING_ANIMATION = 1;
  private static final int PROGRESS_ANIMATION = 2;
  public static final String START_ANGLE_PROPERTY = "startAngle";
  public static final String SWEEP_ANGLE_PROPERTY = "sweepAngle";
  private final long LOADING_ANIM_DURATION = 1760L;
  private int mBackgroundColor;
  private float mCentX;
  private float mCentY;
  private RectF mCircleBounds = null;
  private Context mContext = null;
  private int mForegroundColor;
  private Animator mLoadingAnimator = null;
  private int mLoadingState = 1;
  private Paint mPaint = null;
  private Paint mPaintArc = null;
  private Paint mPaintArcBack = null;
  private int mPaintWidth = 0;
  private float mRadius;
  private float mRingWidth;
  private float mStartAngle;
  private float mSweepAngle;
  private int mThemeColor;
  
  public LoadingView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public LoadingView(Context paramContext, float paramFloat1, float paramFloat2)
  {
    this(paramContext, null);
    mRadius = paramFloat1;
    mRingWidth = paramFloat2;
    init();
  }
  
  public LoadingView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.MeizuCommon_LoadingViewStyle);
  }
  
  public LoadingView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    mContext = paramContext;
    mPaint = new Paint(1);
    mPaint.setAntiAlias(true);
    mPaint.setColor(-1);
    mPaint.setAntiAlias(true);
    mPaint.setTextAlign(Paint.Align.CENTER);
    mPaint.setTextSize(36.0F);
    TypedArray localTypedArray = mContext.obtainStyledAttributes(R.styleable.MZTheme);
    mThemeColor = localTypedArray.getInt(R.styleable.MZTheme_mzThemeColor, -16711936);
    localTypedArray.recycle();
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.LoadingView, paramInt, 0);
    mRadius = paramContext.getDimension(R.styleable.LoadingView_mcLoadingRadius, 30.0F);
    mRingWidth = paramContext.getDimension(R.styleable.LoadingView_mcRingWidth, 4.5F);
    mBackgroundColor = paramContext.getColor(R.styleable.LoadingView_mcLBackground, mThemeColor);
    mForegroundColor = paramContext.getColor(R.styleable.LoadingView_mcLForeground, mThemeColor);
    mLoadingState = paramContext.getInt(R.styleable.LoadingView_mcLoadingState, 1);
    paramContext.recycle();
    mPaintArc = new Paint(1);
    mPaintArc.setAntiAlias(true);
    mPaintArc.setColor(mForegroundColor);
    mPaintArc.setStyle(Paint.Style.STROKE);
    mPaintArc.setStrokeCap(Paint.Cap.ROUND);
    mPaintArcBack = new Paint(1);
    mPaintArcBack.setAntiAlias(true);
    mPaintArcBack.setColor(mBackgroundColor);
    mPaintArcBack.setStyle(Paint.Style.STROKE);
    mPaintArc.setStrokeWidth(mRingWidth - mPaintWidth);
    mPaintArcBack.setStrokeWidth(mRingWidth - mPaintWidth);
    init();
  }
  
  private Animator createLoadingAnimator()
  {
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[] { PropertyValuesHolder.ofKeyframe("startAngle", new Keyframe[] { Keyframe.ofFloat(0.0F, -90.0F), Keyframe.ofFloat(0.5F, 330.0F), Keyframe.ofFloat(1.0F, 630.0F) }), PropertyValuesHolder.ofFloat("sweepAngle", new float[] { 0.0F, -120.0F, 0.0F }) });
    localObjectAnimator.setDuration(1760L);
    localObjectAnimator.setInterpolator(new LinearInterpolator());
    localObjectAnimator.setRepeatCount(-1);
    return localObjectAnimator;
  }
  
  private void drawLoadingAnimation(Canvas paramCanvas)
  {
    paramCanvas.drawArc(mCircleBounds, -90.0F, 360.0F, false, mPaintArcBack);
    paramCanvas.drawArc(mCircleBounds, mStartAngle, mSweepAngle, false, mPaintArc);
  }
  
  private void init()
  {
    mCentX = (getX() + getPaddingLeft() + mRadius + mPaintWidth * 2 + mRingWidth);
    mCentY = (getY() + getPaddingTop() + mRadius + mPaintWidth * 2 + mRingWidth);
    mCircleBounds = new RectF();
    mCircleBounds.left = (mCentX - mRadius - mPaintWidth / 2 - mRingWidth / 2.0F);
    mCircleBounds.top = (mCentY - mRadius - mPaintWidth / 2 - mRingWidth / 2.0F);
    mCircleBounds.right = (mCentX + mRadius + mPaintWidth / 2 + mRingWidth / 2.0F);
    mCircleBounds.bottom = (mCentY + mRadius + mPaintWidth / 2 + mRingWidth / 2.0F);
  }
  
  private void startLoadingAnimation()
  {
    if ((mLoadingAnimator != null) && (mLoadingAnimator.isRunning())) {
      return;
    }
    mLoadingState = 1;
    mLoadingAnimator = createLoadingAnimator();
    mLoadingAnimator.start();
  }
  
  public float getStartAngle()
  {
    return mStartAngle;
  }
  
  public float getSweepAngle()
  {
    return mSweepAngle;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.translate(getWidth() / 2 - mRadius - mRingWidth, getHeight() / 2 - mRadius - mRingWidth);
    if (mForegroundColor == mBackgroundColor) {
      mPaintArcBack.setAlpha(26);
    }
    if (mLoadingState == 1)
    {
      drawLoadingAnimation(paramCanvas);
      return;
    }
    super.onDraw(paramCanvas);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = (int)(2.0F * (mRadius + mRingWidth + 1.0F));
    int j = getPaddingLeft();
    int k = getPaddingRight();
    int m = getPaddingTop();
    int n = getPaddingBottom();
    setMeasuredDimension(resolveSizeAndState(j + k + i, paramInt1, 0), resolveSizeAndState(i + (m + n), paramInt2, 0));
  }
  
  protected void onVisibilityChanged(View paramView, int paramInt)
  {
    super.onVisibilityChanged(paramView, paramInt);
    if (1 != mLoadingState) {}
    do
    {
      do
      {
        return;
        if (paramInt == 0) {
          break;
        }
      } while (mLoadingAnimator == null);
      mLoadingAnimator.cancel();
      mLoadingAnimator = null;
      return;
    } while (!isShown());
    startLoadingAnimation();
  }
  
  protected void onWindowVisibilityChanged(int paramInt)
  {
    super.onWindowVisibilityChanged(paramInt);
    if (1 != mLoadingState) {}
    do
    {
      do
      {
        return;
        if (paramInt == 0) {
          break;
        }
      } while (mLoadingAnimator == null);
      mLoadingAnimator.cancel();
      mLoadingAnimator = null;
      return;
    } while (!isShown());
    startLoadingAnimation();
  }
  
  public void setBarBackgroundColor(int paramInt)
  {
    if ((mPaintArcBack != null) && (mPaintArcBack.getColor() != paramInt))
    {
      mPaintArcBack.setColor(paramInt);
      mBackgroundColor = paramInt;
      postInvalidate();
    }
  }
  
  public void setBarColor(int paramInt)
  {
    if ((mPaintArc != null) && (mPaintArc.getColor() != paramInt))
    {
      mPaintArc.setColor(paramInt);
      mForegroundColor = paramInt;
      postInvalidate();
    }
  }
  
  public void setStartAngle(float paramFloat)
  {
    mStartAngle = paramFloat;
    invalidate();
  }
  
  public void setSweepAngle(float paramFloat)
  {
    mSweepAngle = paramFloat;
    invalidate();
  }
  
  public void setVisibility(int paramInt)
  {
    super.setVisibility(paramInt);
    if (paramInt == 0) {
      startLoadingAnimation();
    }
    while (((paramInt != 4) && (paramInt != 8)) || (mLoadingAnimator == null)) {
      return;
    }
    mLoadingAnimator.cancel();
    mLoadingAnimator = null;
  }
  
  public void startAnimator()
  {
    startLoadingAnimation();
  }
  
  @Deprecated
  public void startProgressAnimation()
  {
    mLoadingState = 2;
  }
  
  public void stopAnimator()
  {
    if (mLoadingAnimator != null)
    {
      mLoadingAnimator.cancel();
      mLoadingAnimator = null;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.LoadingView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */