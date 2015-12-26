package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;

public class SwipeRefreshLayout
  extends ViewGroup
{
  private static final int ALPHA_ANIMATION_DURATION = 300;
  private static final int ANIMATE_TO_START_DURATION = 200;
  private static final int ANIMATE_TO_TRIGGER_DURATION = 200;
  private static final int CIRCLE_BG_LIGHT = -328966;
  private static final int CIRCLE_DIAMETER = 40;
  private static final int CIRCLE_DIAMETER_LARGE = 56;
  private static final float DECELERATE_INTERPOLATION_FACTOR = 2.0F;
  public static final int DEFAULT = 1;
  private static final int DEFAULT_CIRCLE_TARGET = 64;
  private static final float DRAG_RATE = 0.5F;
  private static final int INVALID_POINTER = -1;
  public static final int LARGE = 0;
  private static final int[] LAYOUT_ATTRS = { 16842766 };
  private static final String LOG_TAG = SwipeRefreshLayout.class.getSimpleName();
  private static final int MAX_ALPHA = 255;
  private static final float MAX_PROGRESS_ANGLE = 0.8F;
  private static final int SCALE_DOWN_DURATION = 150;
  private static final int STARTING_PROGRESS_ALPHA = 76;
  private int mActivePointerId = -1;
  private Animation mAlphaMaxAnimation;
  private Animation mAlphaStartAnimation;
  private final Animation mAnimateToCorrectPosition = new SwipeRefreshLayout.6(this);
  private final Animation mAnimateToStartPosition = new SwipeRefreshLayout.7(this);
  private int mCircleHeight;
  private CircleImageView mCircleView;
  private int mCircleViewIndex = -1;
  private int mCircleWidth;
  private int mCurrentTargetOffsetTop;
  private final DecelerateInterpolator mDecelerateInterpolator;
  protected int mFrom;
  private float mInitialDownY;
  private float mInitialMotionY;
  private boolean mIsBeingDragged;
  private OnRefreshListener mListener;
  private int mMediumAnimationDuration;
  private boolean mNotify;
  private boolean mOriginalOffsetCalculated = false;
  protected int mOriginalOffsetTop;
  private MaterialProgressDrawable mProgress;
  private Animation.AnimationListener mRefreshListener = new SwipeRefreshLayout.1(this);
  private boolean mRefreshing = false;
  private boolean mReturningToStart;
  private boolean mScale;
  private Animation mScaleAnimation;
  private Animation mScaleDownAnimation;
  private Animation mScaleDownToStartAnimation;
  private float mSpinnerFinalOffset;
  private float mStartingScale;
  private View mTarget;
  private float mTotalDragDistance = -1.0F;
  private int mTouchSlop;
  private boolean mUsingCustomStart;
  
  public SwipeRefreshLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public SwipeRefreshLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    mTouchSlop = ViewConfiguration.get(paramContext).getScaledTouchSlop();
    mMediumAnimationDuration = getResources().getInteger(17694721);
    setWillNotDraw(false);
    mDecelerateInterpolator = new DecelerateInterpolator(2.0F);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, LAYOUT_ATTRS);
    setEnabled(paramContext.getBoolean(0, true));
    paramContext.recycle();
    paramContext = getResources().getDisplayMetrics();
    mCircleWidth = ((int)(density * 40.0F));
    mCircleHeight = ((int)(density * 40.0F));
    createProgressView();
    ViewCompat.setChildrenDrawingOrderEnabled(this, true);
    mSpinnerFinalOffset = (density * 64.0F);
    mTotalDragDistance = mSpinnerFinalOffset;
  }
  
  private void animateOffsetToCorrectPosition(int paramInt, Animation.AnimationListener paramAnimationListener)
  {
    mFrom = paramInt;
    mAnimateToCorrectPosition.reset();
    mAnimateToCorrectPosition.setDuration(200L);
    mAnimateToCorrectPosition.setInterpolator(mDecelerateInterpolator);
    if (paramAnimationListener != null) {
      mCircleView.setAnimationListener(paramAnimationListener);
    }
    mCircleView.clearAnimation();
    mCircleView.startAnimation(mAnimateToCorrectPosition);
  }
  
  private void animateOffsetToStartPosition(int paramInt, Animation.AnimationListener paramAnimationListener)
  {
    if (mScale)
    {
      startScaleDownReturnToStartAnimation(paramInt, paramAnimationListener);
      return;
    }
    mFrom = paramInt;
    mAnimateToStartPosition.reset();
    mAnimateToStartPosition.setDuration(200L);
    mAnimateToStartPosition.setInterpolator(mDecelerateInterpolator);
    if (paramAnimationListener != null) {
      mCircleView.setAnimationListener(paramAnimationListener);
    }
    mCircleView.clearAnimation();
    mCircleView.startAnimation(mAnimateToStartPosition);
  }
  
  private void createProgressView()
  {
    mCircleView = new CircleImageView(getContext(), -328966, 20.0F);
    mProgress = new MaterialProgressDrawable(getContext(), this);
    mProgress.setBackgroundColor(-328966);
    mCircleView.setImageDrawable(mProgress);
    mCircleView.setVisibility(8);
    addView(mCircleView);
  }
  
  private void ensureTarget()
  {
    int i;
    if (mTarget == null) {
      i = 0;
    }
    for (;;)
    {
      if (i < getChildCount())
      {
        View localView = getChildAt(i);
        if (!localView.equals(mCircleView)) {
          mTarget = localView;
        }
      }
      else
      {
        return;
      }
      i += 1;
    }
  }
  
  private float getMotionEventY(MotionEvent paramMotionEvent, int paramInt)
  {
    paramInt = MotionEventCompat.findPointerIndex(paramMotionEvent, paramInt);
    if (paramInt < 0) {
      return -1.0F;
    }
    return MotionEventCompat.getY(paramMotionEvent, paramInt);
  }
  
  private boolean isAlphaUsedForScale()
  {
    return Build.VERSION.SDK_INT < 11;
  }
  
  private boolean isAnimationRunning(Animation paramAnimation)
  {
    return (paramAnimation != null) && (paramAnimation.hasStarted()) && (!paramAnimation.hasEnded());
  }
  
  private void moveToStart(float paramFloat)
  {
    setTargetOffsetTopAndBottom(mFrom + (int)((mOriginalOffsetTop - mFrom) * paramFloat) - mCircleView.getTop(), false);
  }
  
  private void onSecondaryPointerUp(MotionEvent paramMotionEvent)
  {
    int i = MotionEventCompat.getActionIndex(paramMotionEvent);
    if (MotionEventCompat.getPointerId(paramMotionEvent, i) == mActivePointerId) {
      if (i != 0) {
        break label33;
      }
    }
    label33:
    for (i = 1;; i = 0)
    {
      mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, i);
      return;
    }
  }
  
  private void setAnimationProgress(float paramFloat)
  {
    if (isAlphaUsedForScale())
    {
      setColorViewAlpha((int)(255.0F * paramFloat));
      return;
    }
    ViewCompat.setScaleX(mCircleView, paramFloat);
    ViewCompat.setScaleY(mCircleView, paramFloat);
  }
  
  private void setColorViewAlpha(int paramInt)
  {
    mCircleView.getBackground().setAlpha(paramInt);
    mProgress.setAlpha(paramInt);
  }
  
  private void setRefreshing(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (mRefreshing != paramBoolean1)
    {
      mNotify = paramBoolean2;
      ensureTarget();
      mRefreshing = paramBoolean1;
      if (mRefreshing) {
        animateOffsetToCorrectPosition(mCurrentTargetOffsetTop, mRefreshListener);
      }
    }
    else
    {
      return;
    }
    startScaleDownAnimation(mRefreshListener);
  }
  
  private void setTargetOffsetTopAndBottom(int paramInt, boolean paramBoolean)
  {
    mCircleView.bringToFront();
    mCircleView.offsetTopAndBottom(paramInt);
    mCurrentTargetOffsetTop = mCircleView.getTop();
    if ((paramBoolean) && (Build.VERSION.SDK_INT < 11)) {
      invalidate();
    }
  }
  
  private Animation startAlphaAnimation(int paramInt1, int paramInt2)
  {
    if ((mScale) && (isAlphaUsedForScale())) {
      return null;
    }
    SwipeRefreshLayout.4 local4 = new SwipeRefreshLayout.4(this, paramInt1, paramInt2);
    local4.setDuration(300L);
    mCircleView.setAnimationListener(null);
    mCircleView.clearAnimation();
    mCircleView.startAnimation(local4);
    return local4;
  }
  
  private void startProgressAlphaMaxAnimation()
  {
    mAlphaMaxAnimation = startAlphaAnimation(mProgress.getAlpha(), 255);
  }
  
  private void startProgressAlphaStartAnimation()
  {
    mAlphaStartAnimation = startAlphaAnimation(mProgress.getAlpha(), 76);
  }
  
  private void startScaleDownAnimation(Animation.AnimationListener paramAnimationListener)
  {
    mScaleDownAnimation = new SwipeRefreshLayout.3(this);
    mScaleDownAnimation.setDuration(150L);
    mCircleView.setAnimationListener(paramAnimationListener);
    mCircleView.clearAnimation();
    mCircleView.startAnimation(mScaleDownAnimation);
  }
  
  private void startScaleDownReturnToStartAnimation(int paramInt, Animation.AnimationListener paramAnimationListener)
  {
    mFrom = paramInt;
    if (isAlphaUsedForScale()) {}
    for (mStartingScale = mProgress.getAlpha();; mStartingScale = ViewCompat.getScaleX(mCircleView))
    {
      mScaleDownToStartAnimation = new SwipeRefreshLayout.8(this);
      mScaleDownToStartAnimation.setDuration(150L);
      if (paramAnimationListener != null) {
        mCircleView.setAnimationListener(paramAnimationListener);
      }
      mCircleView.clearAnimation();
      mCircleView.startAnimation(mScaleDownToStartAnimation);
      return;
    }
  }
  
  private void startScaleUpAnimation(Animation.AnimationListener paramAnimationListener)
  {
    mCircleView.setVisibility(0);
    if (Build.VERSION.SDK_INT >= 11) {
      mProgress.setAlpha(255);
    }
    mScaleAnimation = new SwipeRefreshLayout.2(this);
    mScaleAnimation.setDuration(mMediumAnimationDuration);
    if (paramAnimationListener != null) {
      mCircleView.setAnimationListener(paramAnimationListener);
    }
    mCircleView.clearAnimation();
    mCircleView.startAnimation(mScaleAnimation);
  }
  
  public boolean canChildScrollUp()
  {
    boolean bool = false;
    if (Build.VERSION.SDK_INT < 14)
    {
      if ((mTarget instanceof AbsListView))
      {
        AbsListView localAbsListView = (AbsListView)mTarget;
        return (localAbsListView.getChildCount() > 0) && ((localAbsListView.getFirstVisiblePosition() > 0) || (localAbsListView.getChildAt(0).getTop() < localAbsListView.getPaddingTop()));
      }
      if ((ViewCompat.canScrollVertically(mTarget, -1)) || (mTarget.getScrollY() > 0)) {
        bool = true;
      }
      return bool;
    }
    return ViewCompat.canScrollVertically(mTarget, -1);
  }
  
  protected int getChildDrawingOrder(int paramInt1, int paramInt2)
  {
    if (mCircleViewIndex < 0) {}
    do
    {
      return paramInt2;
      if (paramInt2 == paramInt1 - 1) {
        return mCircleViewIndex;
      }
    } while (paramInt2 < mCircleViewIndex);
    return paramInt2 + 1;
  }
  
  public int getProgressCircleDiameter()
  {
    if (mCircleView != null) {
      return mCircleView.getMeasuredHeight();
    }
    return 0;
  }
  
  public boolean isRefreshing()
  {
    return mRefreshing;
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    ensureTarget();
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    if ((mReturningToStart) && (i == 0)) {
      mReturningToStart = false;
    }
    if ((!isEnabled()) || (mReturningToStart) || (canChildScrollUp()) || (mRefreshing)) {
      return false;
    }
    switch (i)
    {
    }
    for (;;)
    {
      return mIsBeingDragged;
      setTargetOffsetTopAndBottom(mOriginalOffsetTop - mCircleView.getTop(), true);
      mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, 0);
      mIsBeingDragged = false;
      float f = getMotionEventY(paramMotionEvent, mActivePointerId);
      if (f == -1.0F) {
        break;
      }
      mInitialDownY = f;
      continue;
      if (mActivePointerId == -1)
      {
        Log.e(LOG_TAG, "Got ACTION_MOVE event but don't have an active pointer id.");
        return false;
      }
      f = getMotionEventY(paramMotionEvent, mActivePointerId);
      if (f == -1.0F) {
        break;
      }
      if ((f - mInitialDownY > mTouchSlop) && (!mIsBeingDragged))
      {
        mInitialMotionY = (mInitialDownY + mTouchSlop);
        mIsBeingDragged = true;
        mProgress.setAlpha(76);
        continue;
        onSecondaryPointerUp(paramMotionEvent);
        continue;
        mIsBeingDragged = false;
        mActivePointerId = -1;
      }
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt1 = getMeasuredWidth();
    paramInt2 = getMeasuredHeight();
    if (getChildCount() == 0) {}
    do
    {
      return;
      if (mTarget == null) {
        ensureTarget();
      }
    } while (mTarget == null);
    View localView = mTarget;
    paramInt3 = getPaddingLeft();
    paramInt4 = getPaddingTop();
    localView.layout(paramInt3, paramInt4, paramInt1 - getPaddingLeft() - getPaddingRight() + paramInt3, paramInt2 - getPaddingTop() - getPaddingBottom() + paramInt4);
    paramInt2 = mCircleView.getMeasuredWidth();
    paramInt3 = mCircleView.getMeasuredHeight();
    mCircleView.layout(paramInt1 / 2 - paramInt2 / 2, mCurrentTargetOffsetTop, paramInt1 / 2 + paramInt2 / 2, mCurrentTargetOffsetTop + paramInt3);
  }
  
  public void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (mTarget == null) {
      ensureTarget();
    }
    if (mTarget == null) {}
    for (;;)
    {
      return;
      mTarget.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), 1073741824));
      mCircleView.measure(View.MeasureSpec.makeMeasureSpec(mCircleWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(mCircleHeight, 1073741824));
      if ((!mUsingCustomStart) && (!mOriginalOffsetCalculated))
      {
        mOriginalOffsetCalculated = true;
        paramInt1 = -mCircleView.getMeasuredHeight();
        mOriginalOffsetTop = paramInt1;
        mCurrentTargetOffsetTop = paramInt1;
      }
      mCircleViewIndex = -1;
      paramInt1 = 0;
      while (paramInt1 < getChildCount())
      {
        if (getChildAt(paramInt1) == mCircleView)
        {
          mCircleViewIndex = paramInt1;
          return;
        }
        paramInt1 += 1;
      }
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    if ((mReturningToStart) && (i == 0)) {
      mReturningToStart = false;
    }
    if ((!isEnabled()) || (mReturningToStart) || (canChildScrollUp())) {
      return false;
    }
    switch (i)
    {
    case 4: 
    default: 
    case 0: 
    case 2: 
    case 5: 
    case 6: 
      for (;;)
      {
        return true;
        mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, 0);
        mIsBeingDragged = false;
        continue;
        i = MotionEventCompat.findPointerIndex(paramMotionEvent, mActivePointerId);
        if (i < 0)
        {
          Log.e(LOG_TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
          return false;
        }
        f2 = 0.5F * (MotionEventCompat.getY(paramMotionEvent, i) - mInitialMotionY);
        if (mIsBeingDragged)
        {
          mProgress.showArrow(true);
          f1 = f2 / mTotalDragDistance;
          if (f1 < 0.0F) {
            return false;
          }
          float f3 = Math.min(1.0F, Math.abs(f1));
          float f4 = (float)Math.max(f3 - 0.4D, 0.0D) * 5.0F / 3.0F;
          float f5 = Math.abs(f2);
          float f6 = mTotalDragDistance;
          label245:
          int j;
          if (mUsingCustomStart)
          {
            f1 = mSpinnerFinalOffset - mOriginalOffsetTop;
            f5 = Math.max(0.0F, Math.min(f5 - f6, 2.0F * f1) / f1);
            f5 = (float)(f5 / 4.0F - Math.pow(f5 / 4.0F, 2.0D)) * 2.0F;
            i = mOriginalOffsetTop;
            j = (int)(f1 * f3 + f1 * f5 * 2.0F);
            if (mCircleView.getVisibility() != 0) {
              mCircleView.setVisibility(0);
            }
            if (!mScale)
            {
              ViewCompat.setScaleX(mCircleView, 1.0F);
              ViewCompat.setScaleY(mCircleView, 1.0F);
            }
            if (f2 >= mTotalDragDistance) {
              break label486;
            }
            if (mScale) {
              setAnimationProgress(f2 / mTotalDragDistance);
            }
            if ((mProgress.getAlpha() > 76) && (!isAnimationRunning(mAlphaStartAnimation))) {
              startProgressAlphaStartAnimation();
            }
            mProgress.setStartEndTrim(0.0F, Math.min(0.8F, 0.8F * f4));
            mProgress.setArrowScale(Math.min(1.0F, f4));
          }
          for (;;)
          {
            mProgress.setProgressRotation((-0.25F + 0.4F * f4 + 2.0F * f5) * 0.5F);
            setTargetOffsetTopAndBottom(j + i - mCurrentTargetOffsetTop, true);
            break;
            f1 = mSpinnerFinalOffset;
            break label245;
            label486:
            if ((mProgress.getAlpha() < 255) && (!isAnimationRunning(mAlphaMaxAnimation))) {
              startProgressAlphaMaxAnimation();
            }
          }
          mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, MotionEventCompat.getActionIndex(paramMotionEvent));
          continue;
          onSecondaryPointerUp(paramMotionEvent);
        }
      }
    }
    if (mActivePointerId == -1)
    {
      if (i == 1) {
        Log.e(LOG_TAG, "Got ACTION_UP event but don't have an active pointer id.");
      }
      return false;
    }
    float f1 = MotionEventCompat.getY(paramMotionEvent, MotionEventCompat.findPointerIndex(paramMotionEvent, mActivePointerId));
    float f2 = mInitialMotionY;
    mIsBeingDragged = false;
    if ((f1 - f2) * 0.5F > mTotalDragDistance) {
      setRefreshing(true, true);
    }
    for (;;)
    {
      mActivePointerId = -1;
      return false;
      mRefreshing = false;
      mProgress.setStartEndTrim(0.0F, 0.0F);
      paramMotionEvent = null;
      if (!mScale) {
        paramMotionEvent = new SwipeRefreshLayout.5(this);
      }
      animateOffsetToStartPosition(mCurrentTargetOffsetTop, paramMotionEvent);
      mProgress.showArrow(false);
    }
  }
  
  public void requestDisallowInterceptTouchEvent(boolean paramBoolean) {}
  
  @Deprecated
  public void setColorScheme(int... paramVarArgs)
  {
    setColorSchemeResources(paramVarArgs);
  }
  
  public void setColorSchemeColors(int... paramVarArgs)
  {
    ensureTarget();
    mProgress.setColorSchemeColors(paramVarArgs);
  }
  
  public void setColorSchemeResources(int... paramVarArgs)
  {
    Resources localResources = getResources();
    int[] arrayOfInt = new int[paramVarArgs.length];
    int i = 0;
    while (i < paramVarArgs.length)
    {
      arrayOfInt[i] = localResources.getColor(paramVarArgs[i]);
      i += 1;
    }
    setColorSchemeColors(arrayOfInt);
  }
  
  public void setDistanceToTriggerSync(int paramInt)
  {
    mTotalDragDistance = paramInt;
  }
  
  public void setOnRefreshListener(OnRefreshListener paramOnRefreshListener)
  {
    mListener = paramOnRefreshListener;
  }
  
  @Deprecated
  public void setProgressBackgroundColor(int paramInt)
  {
    setProgressBackgroundColorSchemeResource(paramInt);
  }
  
  public void setProgressBackgroundColorSchemeColor(int paramInt)
  {
    mCircleView.setBackgroundColor(paramInt);
    mProgress.setBackgroundColor(paramInt);
  }
  
  public void setProgressBackgroundColorSchemeResource(int paramInt)
  {
    setProgressBackgroundColorSchemeColor(getResources().getColor(paramInt));
  }
  
  public void setProgressViewEndTarget(boolean paramBoolean, int paramInt)
  {
    mSpinnerFinalOffset = paramInt;
    mScale = paramBoolean;
    mCircleView.invalidate();
  }
  
  public void setProgressViewOffset(boolean paramBoolean, int paramInt1, int paramInt2)
  {
    mScale = paramBoolean;
    mCircleView.setVisibility(8);
    mCurrentTargetOffsetTop = paramInt1;
    mOriginalOffsetTop = paramInt1;
    mSpinnerFinalOffset = paramInt2;
    mUsingCustomStart = true;
    mCircleView.invalidate();
  }
  
  public void setRefreshing(boolean paramBoolean)
  {
    if ((paramBoolean) && (mRefreshing != paramBoolean))
    {
      mRefreshing = paramBoolean;
      if (!mUsingCustomStart) {}
      for (int i = (int)(mSpinnerFinalOffset + mOriginalOffsetTop);; i = (int)mSpinnerFinalOffset)
      {
        setTargetOffsetTopAndBottom(i - mCurrentTargetOffsetTop, true);
        mNotify = false;
        startScaleUpAnimation(mRefreshListener);
        return;
      }
    }
    setRefreshing(paramBoolean, false);
  }
  
  public void setSize(int paramInt)
  {
    if ((paramInt != 0) && (paramInt != 1)) {
      return;
    }
    DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
    int i;
    if (paramInt == 0)
    {
      i = (int)(density * 56.0F);
      mCircleWidth = i;
    }
    for (mCircleHeight = i;; mCircleHeight = i)
    {
      mCircleView.setImageDrawable(null);
      mProgress.updateSizes(paramInt);
      mCircleView.setImageDrawable(mProgress);
      return;
      i = (int)(density * 40.0F);
      mCircleWidth = i;
    }
  }
  
  public static abstract interface OnRefreshListener
  {
    public abstract void onRefresh();
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.SwipeRefreshLayout
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */