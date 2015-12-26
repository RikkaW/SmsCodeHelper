package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.AnimatorSet.Builder;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import com.meizu.common.R.array;
import com.meizu.common.R.dimen;
import com.meizu.common.R.string;

public class PullRefreshAnimationView
{
  private static int DRAW_ARC_VIEW = 1;
  private static int DRAW_LINE_VIEW = 0;
  private static int INVALID = -1;
  public static final String START_ANGLE_PROPERTY = "startAngle";
  public static final String SWEEP_ANGLE_PROPERTY = "sweepAngle";
  private final long BEFORE_LOADING_ANIM_DURATION = 1120L;
  private int COST_TIME_ARC = 1000;
  private int COST_TIME_LINE = 1500;
  private final long LOADING_ANIM_DURATION = 1760L;
  private String[] colorArray = { "chocolate", "coral", "dodgerBlue", "fireBrick", "forestGreeen", "LimeGreen", "peru", "tomato" };
  private float mAnimHeight;
  private Animator mAnimSet;
  private int mAnimationDistance;
  private boolean mArcAnimationRun = false;
  private float mCentX;
  private float mCentY;
  private RectF mCircleBounds = null;
  private float mCurrentCircleY = 0.0F;
  private int mCurrentOverScrollDistance = 0;
  private int mDrawType = DRAW_ARC_VIEW;
  private int mEndLine;
  private TypedArray mEndPointArray;
  private int mExtraOffset;
  private float mFontTop = 0.0F;
  private int mForegroundColor = INVALID;
  private float mHalfAnimHeight = 0.0F;
  private Handler mHandler = new Handler();
  private boolean mIsSpringBack = false;
  private boolean mLineAnimationRun = false;
  private RectF mLineRect = null;
  private float mMaxPullHeight;
  private float mMinPullHeight;
  private int mNewForegroundColor;
  private int mNewForegroundColorAlpha;
  private int mNewPaintArcBackColor;
  private int mNewPaintArcBackColorAlpha;
  private float mNewY = 0.0F;
  private int mOverscrollDistance;
  private Paint mPaint = null;
  private Paint mPaintArc = null;
  private Paint mPaintArcBack = null;
  private final int mPaintArcBackDefaultColor = 637534208;
  private Paint mPaintLine = null;
  private Paint mPaintLineBack = null;
  private float mPaintLineWidth = 0.0F;
  private float mPaintOffset = 3.0F;
  private String mPull2Refresh;
  private String mPull2RefreshDefault;
  private String mPullGoRefresh;
  private float mRadius = 30.0F;
  private String mRefreshing;
  private float mRingWidth = 5.0F;
  private Runnable mRunnable = new PullRefreshAnimationView.1(this);
  private int mShowArcDistance;
  private float mStartAngle;
  private int mStartLine;
  private TypedArray mStartPointArray;
  private long mStartTime;
  private float mSweepAngle;
  private int mTextColor = -1;
  private int mTextColorAlpha;
  private int mTextMarginTop = 30;
  private int mTextSize = 40;
  private int mTotalFrame;
  private View mView = null;
  
  static
  {
    DRAW_LINE_VIEW = 0;
  }
  
  public PullRefreshAnimationView(Context paramContext, int paramInt1, int paramInt2, View paramView)
  {
    mAnimationDistance = paramContext.getResources().getDimensionPixelOffset(R.dimen.mc_pullRefresh_holdheight);
    mOverscrollDistance = paramContext.getResources().getDimensionPixelOffset(R.dimen.mc_pullRefresh_overscrollheight);
    mShowArcDistance = paramContext.getResources().getDimensionPixelOffset(R.dimen.mc_pullRefresh_showarcheight);
    mRadius = paramContext.getResources().getDimension(R.dimen.mc_pullRefresh_radius);
    mRingWidth = paramContext.getResources().getDimension(R.dimen.mc_pullRefresh_ringwidth);
    mMaxPullHeight = paramContext.getResources().getDimension(R.dimen.mc_pullRefresh_maxheight);
    mMinPullHeight = paramContext.getResources().getDimension(R.dimen.mc_pullRefresh_minheight);
    mAnimHeight = paramContext.getResources().getDimension(R.dimen.mc_pullRefresh_animheight);
    mTextSize = paramContext.getResources().getDimensionPixelOffset(R.dimen.mc_pullRefresh_textsize);
    mTextMarginTop = paramContext.getResources().getDimensionPixelOffset(R.dimen.mc_pullRefresh_textmargintop);
    mStartPointArray = paramContext.getResources().obtainTypedArray(R.array.mc_pullline_move_start);
    mEndPointArray = paramContext.getResources().obtainTypedArray(R.array.mc_pullline_move_end);
    mTotalFrame = mEndPointArray.length();
    mPaintOffset = paramContext.getResources().getDimension(R.dimen.mc_pullRefresh_paintoffset);
    mDrawType = paramInt2;
    mForegroundColor = paramInt1;
    String str = paramContext.getResources().getString(R.string.mc_pull_refresh);
    mPull2Refresh = str;
    mPull2RefreshDefault = str;
    mRefreshing = paramContext.getResources().getString(R.string.mc_is_Refreshing);
    mPullGoRefresh = paramContext.getResources().getString(R.string.mc_go_Refreshing);
    mStartLine = 0;
    mPaint = new Paint(1);
    mPaint.setAntiAlias(true);
    mPaint.setColor(mTextColor);
    mPaint.setAntiAlias(true);
    mPaint.setTextAlign(Paint.Align.CENTER);
    mPaint.setTextSize(mTextSize);
    mPaintArc = new Paint(1);
    mPaintArc.setAntiAlias(true);
    mPaintArc.setColor(mForegroundColor);
    mPaintArc.setStyle(Paint.Style.STROKE);
    mPaintArc.setStrokeCap(Paint.Cap.ROUND);
    mPaintArc.setStrokeWidth(mRingWidth);
    mPaintArcBack = new Paint(1);
    mPaintArcBack.setAntiAlias(true);
    mPaintArcBack.setColor(637534208);
    mPaintArcBack.setStyle(Paint.Style.STROKE);
    mPaintArcBack.setStrokeWidth(mRingWidth);
    mPaintLine = new Paint(1);
    mPaintLine.setAntiAlias(true);
    mPaintLine.setColor(mForegroundColor);
    mPaintLine.setStyle(Paint.Style.FILL);
    mPaintLineBack = new Paint(1);
    mPaintLineBack.setAntiAlias(true);
    mPaintLineBack.setColor(-1);
    mPaintLineBack.setStyle(Paint.Style.FILL);
    mPaintLineBack.setAlpha(102);
    mNewForegroundColor = mForegroundColor;
    mNewForegroundColorAlpha = Color.alpha(mNewForegroundColor);
    mNewPaintArcBackColor = 637534208;
    mNewPaintArcBackColorAlpha = Color.alpha(mNewPaintArcBackColor);
    mTextColorAlpha = Color.alpha(mTextColor);
    mFontTop = (-mPaint.getFontMetrics().ascent);
    mHalfAnimHeight = ((mShowArcDistance + mTextMarginTop + mPaint.getTextSize()) / 2.0F);
    initView(paramView);
  }
  
  private Animator createLoadingAnimator()
  {
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[] { PropertyValuesHolder.ofKeyframe("startAngle", new Keyframe[] { Keyframe.ofFloat(0.0F, -90.0F), Keyframe.ofFloat(0.5F, 330.0F), Keyframe.ofFloat(1.0F, 630.0F) }), PropertyValuesHolder.ofFloat("sweepAngle", new float[] { 0.2F, -150.0F, 0.0F }) });
    localObjectAnimator.setDuration(1760L);
    localObjectAnimator.setInterpolator(new LinearInterpolator());
    localObjectAnimator.setRepeatCount(-1);
    return localObjectAnimator;
  }
  
  private Animator createRefreshAnimator()
  {
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[] { PropertyValuesHolder.ofFloat("startAngle", new float[] { -90.0F, 270.0F }), PropertyValuesHolder.ofFloat("sweepAngle", new float[] { -360.0F, 0.0F }) });
    localObjectAnimator.setDuration(1120L);
    localObjectAnimator.setInterpolator(new LinearInterpolator());
    Animator localAnimator = createLoadingAnimator();
    AnimatorSet localAnimatorSet = new AnimatorSet();
    localAnimatorSet.play(localObjectAnimator).before(localAnimator);
    return localAnimatorSet;
  }
  
  private void drawArcView(Canvas paramCanvas)
  {
    mPaintArc.setAlpha(mNewForegroundColorAlpha);
    mPaintArcBack.setAlpha(mNewPaintArcBackColorAlpha);
    mPaint.setAlpha(mTextColorAlpha);
    if (mCurrentOverScrollDistance < mAnimationDistance) {}
    int i;
    for (mCurrentCircleY = (mExtraOffset + (mCurrentOverScrollDistance - mHalfAnimHeight));; mCurrentCircleY = (mExtraOffset + (mAnimationDistance - mHalfAnimHeight)))
    {
      i = paramCanvas.save();
      if (mCurrentOverScrollDistance >= mShowArcDistance) {
        break;
      }
      return;
    }
    paramCanvas.translate(mView.getWidth() / 2, mCurrentCircleY + mView.getScrollY());
    float f1;
    if ((mCurrentOverScrollDistance <= mAnimationDistance) && (mCurrentOverScrollDistance >= mShowArcDistance)) {
      f1 = (mCurrentOverScrollDistance - mShowArcDistance) * 360 / (mAnimationDistance - mShowArcDistance);
    }
    for (;;)
    {
      float f2 = mCentX;
      mNewY = (mCentY + mRadius + mRingWidth + mTextMarginTop + mFontTop);
      float f3 = f1 / 360.0F;
      mPaintArcBack.setAlpha((int)(mNewPaintArcBackColorAlpha * f3));
      paramCanvas.drawArc(mCircleBounds, -90.0F, 360.0F, false, mPaintArcBack);
      if (mArcAnimationRun)
      {
        paramCanvas.drawArc(mCircleBounds, mStartAngle, mSweepAngle, false, mPaintArc);
        paramCanvas.drawText(mRefreshing, f2, mNewY, mPaint);
      }
      for (;;)
      {
        paramCanvas.restoreToCount(i);
        return;
        if (mCurrentOverScrollDistance < mShowArcDistance)
        {
          f1 = 0.0F;
          break;
        }
        if (mCurrentOverScrollDistance <= mAnimationDistance) {
          break label545;
        }
        f1 = 360.0F;
        break;
        if ((mCurrentOverScrollDistance >= mAnimationDistance) && (!mIsSpringBack))
        {
          paramCanvas.drawText(mPullGoRefresh, f2, mNewY, mPaint);
          paramCanvas.drawArc(mCircleBounds, -90.0F, f1, false, mPaintArc);
        }
        else if (mIsSpringBack)
        {
          mPaintArc.setAlpha((int)(mNewForegroundColorAlpha * f3));
          mPaint.setAlpha((int)(mTextColorAlpha * f3));
          paramCanvas.drawArc(mCircleBounds, mStartAngle, mSweepAngle, false, mPaintArc);
          paramCanvas.drawText(mRefreshing, f2, mNewY, mPaint);
        }
        else
        {
          mPaintArc.setAlpha((int)(mNewForegroundColorAlpha * f3));
          mPaint.setAlpha((int)(mTextColorAlpha * f3));
          paramCanvas.drawText(mPull2Refresh, f2, mNewY, mPaint);
          paramCanvas.drawArc(mCircleBounds, -90.0F, f1, false, mPaintArc);
        }
      }
      label545:
      f1 = 0.0F;
    }
  }
  
  @SuppressLint({"NewApi"})
  private void drawLineView(Canvas paramCanvas)
  {
    float f1 = 0.0F;
    long l = AnimationUtils.currentAnimationTimeMillis();
    int i = paramCanvas.save();
    float f2;
    if (mCurrentOverScrollDistance <= mMaxPullHeight / 4.0F)
    {
      mPaintLineWidth = mCurrentOverScrollDistance;
      paramCanvas.translate(0.0F, mView.getScrollY());
      f2 = mExtraOffset;
      float f3 = mView.getWidth();
      float f4 = mExtraOffset;
      paramCanvas.drawRect(0.0F, f2, f3, mPaintLineWidth + f4, mPaintLine);
      if (mLineAnimationRun)
      {
        int j = getArrayIndex((l - mStartTime) % COST_TIME_LINE);
        if ((j >= mStartPointArray.length()) || (j >= mEndPointArray.length())) {
          break label321;
        }
        f1 = mStartPointArray.getFloat(j, 0.0F);
        f2 = mView.getWidth() * f1;
        f1 = mEndPointArray.getFloat(j, 0.0F);
        f1 = mView.getWidth() * f1;
      }
    }
    for (;;)
    {
      paramCanvas.drawRect(f1, mExtraOffset, f2, mExtraOffset + mPaintLineWidth - mPaintOffset, mPaintLineBack);
      paramCanvas.restoreToCount(i);
      return;
      if ((mCurrentOverScrollDistance > mMaxPullHeight / 4.0F) && (mCurrentOverScrollDistance <= mAnimationDistance))
      {
        mPaintLineWidth = (mMaxPullHeight / 4.0F + mMaxPullHeight * 3.0F / 4.0F * (mCurrentOverScrollDistance - mMaxPullHeight / 4.0F) / (mAnimationDistance - mMaxPullHeight / 4.0F));
        break;
      }
      mPaintLineWidth = mMaxPullHeight;
      break;
      label321:
      f2 = 0.0F;
    }
  }
  
  private int getArrayIndex(long paramLong)
  {
    return (int)(mTotalFrame * paramLong / COST_TIME_LINE);
  }
  
  public float getStartAngle()
  {
    return mStartAngle;
  }
  
  public float getSweepAngle()
  {
    return mSweepAngle;
  }
  
  public void initView(View paramView)
  {
    mView = paramView;
    mCircleBounds = new RectF();
    mLineRect = new RectF(0.0F, 0.0F, mView.getWidth(), 100.0F);
    mCentX = (mView.getX() + mView.getWidth() / 2);
    mCentY = (mView.getY() - mRadius - mRingWidth - mTextMarginTop - mPaint.getTextSize());
    mCircleBounds.left = (mCentX - mRadius - mRingWidth / 2.0F);
    mCircleBounds.top = (mCentY - mRadius - mRingWidth / 2.0F);
    mCircleBounds.right = (mCentX + mRadius + mRingWidth / 2.0F);
    mCircleBounds.bottom = (mCentY + mRadius + mRingWidth / 2.0F);
  }
  
  public void removeCallbacksAndMessages()
  {
    if (mHandler != null) {
      mHandler.removeCallbacksAndMessages(null);
    }
    if (mAnimSet != null)
    {
      mAnimSet.end();
      mAnimSet = null;
    }
  }
  
  public void resetRingColor()
  {
    if ((mPaintArc != null) && (mPaintArcBack != null))
    {
      mPaintArc.setColor(mForegroundColor);
      mPaintArcBack.setColor(637534208);
      mNewForegroundColor = mForegroundColor;
      mNewPaintArcBackColor = 637534208;
      mNewForegroundColorAlpha = Color.alpha(mNewForegroundColor);
      mNewPaintArcBackColorAlpha = Color.alpha(mNewPaintArcBackColor);
    }
  }
  
  public void resetSpringFlag(boolean paramBoolean)
  {
    mIsSpringBack = paramBoolean;
  }
  
  public void setAnimationDistance(int paramInt)
  {
    mAnimationDistance = paramInt;
  }
  
  public void setCurrentOverScrollDistance(int paramInt, Canvas paramCanvas)
  {
    if (paramInt > 0) {}
    mCurrentOverScrollDistance = Math.abs(paramInt);
    if (DRAW_ARC_VIEW == mDrawType) {
      if ((mCurrentOverScrollDistance > mShowArcDistance) && (mCurrentOverScrollDistance < mAnimationDistance)) {
        mArcAnimationRun = false;
      }
    }
    while (DRAW_LINE_VIEW != mDrawType) {
      for (;;)
      {
        drawArcView(paramCanvas);
        return;
        if (mCurrentOverScrollDistance < mAnimationDistance) {
          mArcAnimationRun = false;
        }
      }
    }
    if (mCurrentOverScrollDistance < mMaxPullHeight / 4.0F - 1.0F) {
      mLineAnimationRun = false;
    }
    drawLineView(paramCanvas);
  }
  
  public void setExtraOffset(int paramInt)
  {
    mExtraOffset = paramInt;
  }
  
  public void setLastRefreshTime(String paramString)
  {
    mPull2Refresh = paramString;
  }
  
  public void setLastRefreshTimeDefault()
  {
    mPull2Refresh = mPull2RefreshDefault;
  }
  
  public void setOverScrollDistance(int paramInt)
  {
    mOverscrollDistance = paramInt;
  }
  
  public void setPaintArcBackColor(int paramInt)
  {
    if (mPaintArcBack != null)
    {
      mPaintArcBack.setColor(paramInt);
      mNewPaintArcBackColor = paramInt;
      mNewPaintArcBackColorAlpha = Color.alpha(mNewPaintArcBackColor);
    }
  }
  
  public void setPaintArcColor(int paramInt)
  {
    if (mPaintArc != null)
    {
      mPaintArc.setColor(paramInt);
      mNewForegroundColor = paramInt;
      mNewForegroundColorAlpha = Color.alpha(mNewForegroundColor);
    }
  }
  
  public void setStartAngle(float paramFloat)
  {
    mStartAngle = paramFloat;
  }
  
  public void setStartTime()
  {
    if ((mArcAnimationRun) || (mLineAnimationRun)) {
      return;
    }
    if (mDrawType == DRAW_LINE_VIEW)
    {
      mLineAnimationRun = true;
      mArcAnimationRun = false;
    }
    for (;;)
    {
      mStartTime = AnimationUtils.currentAnimationTimeMillis();
      if ((mArcAnimationRun) && (mAnimSet == null))
      {
        mAnimSet = createRefreshAnimator();
        mAnimSet.start();
      }
      if ((!mLineAnimationRun) && (!mArcAnimationRun)) {
        break;
      }
      mHandler.postDelayed(mRunnable, 1L);
      return;
      if (mDrawType == DRAW_ARC_VIEW)
      {
        mLineAnimationRun = false;
        mArcAnimationRun = true;
      }
    }
  }
  
  public void setSweepAngle(float paramFloat)
  {
    mSweepAngle = paramFloat;
    Log.d(null, "anim-sw:" + paramFloat);
  }
  
  public void setTextColor(int paramInt)
  {
    mTextColor = paramInt;
    mTextColorAlpha = Color.alpha(mTextColor);
    if (mPaint != null) {
      mPaint.setColor(mTextColor);
    }
  }
  
  public void stopDrawArcAnimation()
  {
    mArcAnimationRun = false;
    mIsSpringBack = true;
  }
  
  public void stopDrawLineAnimation()
  {
    mLineAnimationRun = false;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.PullRefreshAnimationView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */