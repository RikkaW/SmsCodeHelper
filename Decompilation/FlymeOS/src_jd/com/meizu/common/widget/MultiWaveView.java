package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.DecelerateInterpolator;
import com.meizu.common.R.attr;
import com.meizu.common.R.dimen;
import com.meizu.common.R.styleable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class MultiWaveView
  extends View
{
  private static final float ANGEL_BETWEEN_POINT = 0.3926991F;
  private static final int CIRCLE_ANIMATION_TIME = 1600;
  private static final boolean DEBUG = false;
  private static final int HIDE_ANIMATION_DELAY = 200;
  private static final int HIDE_ANIMATION_DURATION = 330;
  private static final int INVALID_POINTER_ID = -1;
  private static final int MAX_POINT_ALPHA = 255;
  private static final int MIN_POINT_ALPHA = 0;
  private static final int POINT_COUNT = 16;
  private static final int QUICK_CLICK_ANIMATION = 330;
  private static final int RETURN_TO_HOME_DELAY = 1200;
  private static final int RETURN_TO_HOME_DURATION = 330;
  private static final float SNAP_MARGIN_DEFAULT = 20.0F;
  private static final int STATE_FINISH = 5;
  private static final int STATE_FIRST_TOUCH = 2;
  private static final int STATE_IDLE = 0;
  private static final int STATE_SNAP = 4;
  private static final int STATE_START = 1;
  private static final int STATE_TRACKING = 3;
  private static final String TAG = "MultiWaveView";
  private static final float TAP_RADIUS_SCALE_ACCESSIBILITY_ENABLED = 1.3F;
  public static final int TARGET_ANSWER_ID = 0;
  public static final int TARGET_DECLINE_ID = 1;
  private static final TimeInterpolator circleAnimationInterpolator = new DecelerateInterpolator();
  private float MAX_ANGEL = 1.4765486F;
  private float MIN_ANGEL = 0.0F;
  private AnimatorSet animatorSet = null;
  private float density = getResourcesgetDisplayMetricsdensity;
  private ArgbEvaluator evaluator = new ArgbEvaluator();
  private float hightForHandler;
  private int mActivePointerId = -1;
  private int mActiveTarget = -1;
  private boolean mAnimatingTargets;
  private int mBallAlpha = 0;
  private Paint mBallCirclePaint;
  private int mBigCircleAlpha = 0;
  private Paint mBigCirclePaint;
  private ObjectAnimator mCircleAnimation;
  private int mColorGreen = -12339861;
  private int mColorOrange = -626650;
  private int mCurAlpha = 0;
  private float mCurAngel = 0.0F;
  private float mCurCircleRadius;
  private float mCurPointRadius;
  private Paint mDefaultBackgroundPaint;
  private int mDefaultBgColor = 234881023;
  private ArrayList<String> mDirectionDescriptions;
  private int mDirectionDescriptionsResourceId;
  private boolean mDisableQuickClick = true;
  private boolean mDragging;
  private boolean mDrawHandleCircle = false;
  private boolean mDrawPointCircle = true;
  private Animator.AnimatorListener mFinishListener = new MultiWaveView.10(this);
  private int mGrabbedState;
  private int mGravity = 1;
  private float mHandleAngleStep;
  private AnimationBundle mHandleAnimations = new AnimationBundle(null);
  private ObjectAnimator mHandleCircleAnimation;
  private int mHandleCircleColor;
  private Paint mHandleCirclePaint;
  private ValueHolder mHandleCircleValueHolder;
  private TargetDrawable mHandleDrawable;
  private boolean mHoldCircleAnimation = false;
  private int mHorizontalInset;
  private int mIconbgColor = mDefaultBgColor;
  private boolean mInitialLayout = true;
  private boolean mIsBluetoothAns = false;
  private float mMaxPointCircleRadius;
  private float mMaxPointRadius;
  private int mMaxTargetHeight;
  private int mMaxTargetWidth;
  private float mMinPointCircleRadius;
  private float mMinPointRadius;
  private float mMotionDownX = 0.0F;
  private float mMotionDownY = 0.0F;
  private AnimationBundle mMoveAnimations = new AnimationBundle(null);
  private MyPoint mMovePoint;
  private MyAnimatorUpdateListener mMoveUpdateListener = new MyAnimatorUpdateListener(null);
  private int mNewTargetResources;
  private OnTriggerListener mOnTriggerListener;
  private float mOuterRadius = 0.0F;
  private float mOuterRadiusHeight = 0.0F;
  private TargetDrawable mOuterRing;
  private int mPaintColor = mHandleCircleColor;
  private Paint mPointCirclePaint;
  private float mPointCircleX;
  private float mPointCircleY;
  private int mPointColor;
  private boolean mQuickClick = false;
  private Animator.AnimatorListener mResetListener = new MultiWaveView.1(this);
  private Animator.AnimatorListener mResetListenerWithPing = new MultiWaveView.2(this);
  private float mShakeAngel = 0.0F;
  private float mStrokeWidth;
  private float mTapRadius;
  private float mTargetBeginScaleDistance;
  private ArrayList<String> mTargetDescriptions;
  private int mTargetDescriptionsResourceId;
  private ArrayList<TargetDrawable> mTargetDrawables = new ArrayList();
  private float mTargetMaxRadius;
  private float mTargetMinRadius;
  private float mTargetRadiusFromCenter = 0.0F;
  private int mTargetResourceId;
  private float mTargetScaleStep;
  private int mTouchSlop;
  private float mTranslateCircleX;
  private ValueAnimator.AnimatorUpdateListener mUpdateListener = new MultiWaveView.3(this);
  private ValueHolder mValueHolder;
  private int mVerticalInset;
  private int mVibrationDuration = 0;
  private Vibrator mVibrator;
  private float mWaveCenterX;
  private float mWaveCenterY;
  private float widthForHandler;
  
  public MultiWaveView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public MultiWaveView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.MeizuCommon_MultiWaveView);
  }
  
  public MultiWaveView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    Object localObject = paramContext.getResources();
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.MultiWaveView, paramInt, 0);
    mTargetMinRadius = paramAttributeSet.getDimension(R.styleable.MultiWaveView_mcTargetMinRadius, mTargetMinRadius);
    mTargetMaxRadius = paramAttributeSet.getDimension(R.styleable.MultiWaveView_mcTargetMaxRadius, mTargetMaxRadius);
    mOuterRadius = paramAttributeSet.getDimension(R.styleable.MultiWaveView_mcOuterRadius, mOuterRadius);
    mVibrationDuration = paramAttributeSet.getInt(R.styleable.MultiWaveView_mcVibrationDuration, mVibrationDuration);
    mHandleDrawable = new TargetDrawable((Resources)localObject, peekValueMultiWaveView_mcHandleDrawableresourceId);
    mTapRadius = (mHandleDrawable.getWidth() / 2);
    mOuterRing = new TargetDrawable((Resources)localObject, peekValueMultiWaveView_mcWaveDrawableresourceId);
    widthForHandler = mHandleDrawable.getX();
    hightForHandler = mHandleDrawable.getY();
    localObject = new TypedValue();
    if (paramAttributeSet.getValue(R.styleable.MultiWaveView_mcTargetDrawables, (TypedValue)localObject)) {
      internalSetTargetResources(resourceId);
    }
    if ((mTargetDrawables == null) || (mTargetDrawables.size() == 0)) {
      throw new IllegalStateException("Must specify at least one target drawable");
    }
    if (paramAttributeSet.getValue(R.styleable.MultiWaveView_mcTargetDescriptions, (TypedValue)localObject))
    {
      paramInt = resourceId;
      if (paramInt == 0) {
        throw new IllegalStateException("Must specify target descriptions");
      }
      setTargetDescriptionsResourceId(paramInt);
    }
    if (paramAttributeSet.getValue(R.styleable.MultiWaveView_mcDirectionDescriptions, (TypedValue)localObject))
    {
      paramInt = resourceId;
      if (paramInt == 0) {
        throw new IllegalStateException("Must specify direction descriptions");
      }
      setDirectionDescriptionsResourceId(paramInt);
    }
    mMaxPointCircleRadius = paramAttributeSet.getDimension(R.styleable.MultiWaveView_mcMaxPointCircleRadius, mMaxPointCircleRadius);
    mMinPointCircleRadius = (mMaxPointCircleRadius * 0.12F);
    mMaxPointRadius = paramAttributeSet.getDimension(R.styleable.MultiWaveView_mcMaxPointRadius, mMaxPointRadius);
    mMinPointRadius = 0.0F;
    mOuterRadiusHeight = paramAttributeSet.getDimension(R.styleable.MultiWaveView_mcOuterRadiusHeight, mOuterRadiusHeight);
    mTargetRadiusFromCenter = (paramAttributeSet.getDimension(R.styleable.MultiWaveView_mcTargetToHandle, mTargetRadiusFromCenter) + mTapRadius + mTargetMinRadius);
    mTargetBeginScaleDistance = paramAttributeSet.getDimension(R.styleable.MultiWaveView_mcTargetSnapRadius, mTargetBeginScaleDistance);
    mTargetScaleStep = (mTargetMaxRadius / mTargetMinRadius);
    mHandleAngleStep = (135.0F / mTargetRadiusFromCenter);
    mPointColor = paramAttributeSet.getColor(R.styleable.MultiWaveView_mcPointColor, -16777216);
    mHandleCircleColor = paramAttributeSet.getColor(R.styleable.MultiWaveView_mcHandleCircleColor, 687865855);
    paramAttributeSet.recycle();
    mPointCirclePaint = new Paint();
    mPointCirclePaint.setAntiAlias(true);
    mPointCirclePaint.setColor(mPointColor);
    mDefaultBackgroundPaint = new Paint();
    mDefaultBackgroundPaint.setColor(mIconbgColor);
    mDefaultBackgroundPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    mDefaultBackgroundPaint.setStrokeWidth(getResources().getDimension(R.dimen.mc_multiwaveview_handle_circle_width));
    mBigCirclePaint = new Paint();
    mBigCirclePaint.setAntiAlias(true);
    mBigCirclePaint.setColor(mDefaultBgColor);
    mBigCirclePaint.setStyle(Paint.Style.STROKE);
    mBigCirclePaint.setStrokeWidth(5.0F);
    mHandleCirclePaint = new Paint();
    mHandleCirclePaint.setAntiAlias(true);
    mHandleCirclePaint.setColor(mHandleCircleColor);
    mHandleCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    mHandleCirclePaint.setStrokeWidth(getResources().getDimension(R.dimen.mc_multiwaveview_handle_circle_width));
    mBallCirclePaint = new Paint();
    mBallCirclePaint.setAntiAlias(true);
    mBallCirclePaint.setColor(mHandleCircleColor);
    mBallCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    mBallCirclePaint.setStrokeWidth(getResources().getDimension(R.dimen.mc_multiwaveview_handle_circle_width));
    mTouchSlop = ViewConfiguration.get(paramContext).getScaledTouchSlop();
    mValueHolder = new ValueHolder(null);
    resetCircleAnimation();
    mHandleCircleValueHolder = new ValueHolder(null);
    if (mVibrationDuration > 0) {}
    for (;;)
    {
      setVibrateEnabled(bool);
      assignDefaultsIfNeeded();
      return;
      bool = false;
    }
  }
  
  private void announceTargets()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int j = mTargetDrawables.size();
    int i = 0;
    while (i < j)
    {
      String str1 = getTargetDescription(i);
      String str2 = getDirectionDescription(i);
      if ((!TextUtils.isEmpty(str1)) && (!TextUtils.isEmpty(str2))) {
        localStringBuilder.append(String.format(str2, new Object[] { str1 }));
      }
      if (localStringBuilder.length() > 0) {
        announceText(localStringBuilder.toString());
      }
      i += 1;
    }
  }
  
  private void announceText(String paramString)
  {
    setContentDescription(paramString);
    sendAccessibilityEvent(8);
    setContentDescription(null);
  }
  
  private void assignDefaultsIfNeeded()
  {
    if (mOuterRadius == 0.0F) {
      mOuterRadius = (Math.max(mOuterRing.getWidth(), mOuterRing.getHeight()) / 2.0F);
    }
  }
  
  private void computeInsets(int paramInt1, int paramInt2)
  {
    int i = getLayoutDirection();
    i = Gravity.getAbsoluteGravity(mGravity, i);
    switch (i & 0x7)
    {
    case 4: 
    default: 
      mHorizontalInset = (paramInt1 / 2);
    }
    for (;;)
    {
      switch (i & 0x70)
      {
      default: 
        mVerticalInset = (paramInt2 / 2);
        return;
        mHorizontalInset = 0;
        continue;
        mHorizontalInset = paramInt1;
      }
    }
    mVerticalInset = 0;
    return;
    mVerticalInset = paramInt2;
  }
  
  private void detectHandleAnimation(int paramInt1, int paramInt2, TimeInterpolator paramTimeInterpolator, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Animator.AnimatorListener paramAnimatorListener)
  {
    AnimationBundle localAnimationBundle = new AnimationBundle(null);
    localAnimationBundle.add(Tweener.to(mHandleDrawable, paramInt1, new Object[] { "ease", paramTimeInterpolator, "delay", Integer.valueOf(paramInt2), "alpha", Float.valueOf(paramFloat1), "x", Float.valueOf(paramFloat2), "y", Float.valueOf(paramFloat3), "rotation", Float.valueOf(paramFloat4), "onUpdate", mUpdateListener, "onComplete", paramAnimatorListener }));
    localAnimationBundle.start();
  }
  
  private void dispatchOnFinishFinalAnimation()
  {
    if (mOnTriggerListener != null) {
      mOnTriggerListener.onFinishFinalAnimation();
    }
  }
  
  private void dispatchTriggerEvent(int paramInt)
  {
    vibrate();
    if (mOnTriggerListener != null)
    {
      Log.i("MultiWaveView", "onTrigger whichTarget = " + paramInt + " mIsBluetoothAns = " + mIsBluetoothAns);
      mOnTriggerListener.onTrigger(this, paramInt, mIsBluetoothAns);
      mIsBluetoothAns = false;
    }
  }
  
  private float dist2(float paramFloat1, float paramFloat2)
  {
    return paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2;
  }
  
  private void doFinish()
  {
    int j = mActiveTarget;
    int i;
    MultiWaveView.5 local5;
    if (j != -1)
    {
      i = 1;
      if (i == 0) {
        break label155;
      }
      local5 = new MultiWaveView.5(this);
      dispatchTriggerEvent(j);
      if (mDragging) {
        switch (j)
        {
        default: 
          label60:
          if (mCircleAnimation != null)
          {
            mCircleAnimation.cancel();
            resetCircleAnimation();
            invalidate();
          }
          break;
        }
      }
    }
    for (;;)
    {
      setGrabbedState(0);
      mDragging = false;
      return;
      i = 0;
      break;
      triggerHandleAnimation(100, 0, circleAnimationInterpolator, mHandleDrawable.getAlpha(), mTargetRadiusFromCenter, 0.0F, 0.0F, local5);
      break label60;
      triggerHandleAnimation(100, 0, circleAnimationInterpolator, mHandleDrawable.getAlpha(), -mTargetRadiusFromCenter, 0.0F, 135.0F, local5);
      break label60;
      label155:
      triggerHandleAnimation(330, 200, MultiWaveView.Ease.Quart.easeOut, 1.0F, 0.0F, 0.0F, 0.0F, mResetListenerWithPing);
    }
  }
  
  private void dump()
  {
    Log.v("MultiWaveView", "Outer Radius = " + mOuterRadius);
    Log.v("MultiWaveView", "VibrationDuration = " + mVibrationDuration);
    Log.v("MultiWaveView", "TapRadius = " + mTapRadius);
    Log.v("MultiWaveView", "WaveCenterX = " + mWaveCenterX);
    Log.v("MultiWaveView", "WaveCenterY = " + mWaveCenterY);
    Log.v("MultiWaveView", "mTargetRadiusFromCenter = " + mTargetRadiusFromCenter);
    Log.v("MultiWaveView", "mOuterRadiusHeight = " + mOuterRadiusHeight);
  }
  
  private int findActiveTarget(double paramDouble)
  {
    ArrayList localArrayList = mTargetDrawables;
    int j = -1;
    int m = localArrayList.size();
    int i = 0;
    int k;
    if (i < m)
    {
      TargetDrawable localTargetDrawable = (TargetDrawable)localArrayList.get(i);
      double d1 = (i - 0.5D) * 2.0D * 3.141592653589793D / m;
      double d2 = (i + 0.5D) * 2.0D * 3.141592653589793D / m;
      float f1 = localTargetDrawable.getPositionX();
      float f2 = localTargetDrawable.getX();
      float f3 = localTargetDrawable.getPositionY();
      float f4 = localTargetDrawable.getY();
      f1 = (float)Math.sqrt(dist2(f1 + f2 - (mHandleDrawable.getPositionX() + mHandleDrawable.getX()), f3 + f4 - (mHandleDrawable.getPositionY() + mHandleDrawable.getY())));
      if (!localTargetDrawable.isEnabled()) {
        break label291;
      }
      if (((paramDouble > d1) && (paramDouble <= d2)) || ((6.283185307179586D + paramDouble > d1) && (6.283185307179586D + paramDouble <= d2)))
      {
        k = 1;
        label205:
        if ((k == 0) || (f1 > mTargetBeginScaleDistance)) {
          break label291;
        }
        f1 = (mTargetScaleStep - 1.0F) / mTargetBeginScaleDistance;
        f1 = mTargetBeginScaleDistance;
        j = i;
      }
    }
    label291:
    for (;;)
    {
      i += 1;
      break;
      k = 0;
      break label205;
      switch (j)
      {
      default: 
        return j;
      case 0: 
        return 0;
      }
      return 1;
    }
  }
  
  private String getDirectionDescription(int paramInt)
  {
    if ((mDirectionDescriptions == null) || (mDirectionDescriptions.isEmpty()))
    {
      mDirectionDescriptions = loadDescriptions(mDirectionDescriptionsResourceId);
      if (mTargetDrawables.size() != mDirectionDescriptions.size())
      {
        Log.w("MultiWaveView", "The number of target drawables must be euqal to the number of direction descriptions.");
        return null;
      }
    }
    return (String)mDirectionDescriptions.get(paramInt);
  }
  
  private AccessibilityManager getInstance(Context paramContext)
  {
    try
    {
      paramContext = (AccessibilityManager)Class.forName("android.view.accessibility.AccessibilityManager").getMethod("getInstance", new Class[] { Context.class }).invoke(null, new Object[] { paramContext });
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  private float getScaledTapRadiusSquared()
  {
    if (getInstance(getContext()).isEnabled()) {}
    for (float f = 1.3F * mTapRadius * 2.0F;; f = mTapRadius * 2.0F) {
      return square(f);
    }
  }
  
  private String getTargetDescription(int paramInt)
  {
    if ((mTargetDescriptions == null) || (mTargetDescriptions.isEmpty()))
    {
      mTargetDescriptions = loadDescriptions(mTargetDescriptionsResourceId);
      if (mTargetDrawables.size() != mTargetDescriptions.size())
      {
        Log.w("MultiWaveView", "The number of target drawables must be euqal to the number of target descriptions.");
        return null;
      }
    }
    return (String)mTargetDescriptions.get(paramInt);
  }
  
  private void handleCancel(MotionEvent paramMotionEvent)
  {
    if ((!mDragging) || (paramMotionEvent == null)) {
      if (mActivePointerId == -1) {}
    }
    label90:
    for (;;)
    {
      return;
      float f2 = mMovePoint.x;
      float f1 = mMovePoint.y;
      for (;;)
      {
        if (mQuickClick) {
          break label90;
        }
        switchToState(5, f2, f1);
        return;
        int i = paramMotionEvent.getActionIndex();
        if (paramMotionEvent.getPointerId(i) != mActivePointerId) {
          break;
        }
        f2 = paramMotionEvent.getX(i);
        f1 = paramMotionEvent.getY(i);
        mActivePointerId = -1;
      }
    }
  }
  
  private void handleDown(MotionEvent paramMotionEvent)
  {
    float f2;
    float f1;
    if (paramMotionEvent == null)
    {
      if (mActivePointerId != -1) {
        return;
      }
      f2 = mMovePoint.x;
      f1 = mMovePoint.y;
    }
    for (;;)
    {
      mMotionDownX = f2;
      mMotionDownY = f1;
      switchToState(1, f2, f1);
      if (trySwitchToFirstTouchState(f2, f1)) {
        break;
      }
      mDragging = false;
      mQuickClick = false;
      mActiveTarget = -1;
      ping();
      return;
      int i = paramMotionEvent.getActionIndex();
      mActivePointerId = paramMotionEvent.getPointerId(i);
      f2 = paramMotionEvent.getX(i);
      f1 = paramMotionEvent.getY(i);
      cancelMove2TargetAnimation();
    }
  }
  
  private void handleMove(MotionEvent paramMotionEvent)
  {
    handleMove(paramMotionEvent, false);
  }
  
  private void handleMove(MotionEvent paramMotionEvent, boolean paramBoolean)
  {
    if (paramMotionEvent == null)
    {
      if (mActivePointerId == -1) {
        break label633;
      }
      break label12;
    }
    label12:
    while (mActivePointerId == -1) {
      return;
    }
    label62:
    label145:
    label341:
    label358:
    label389:
    label400:
    label406:
    label480:
    label633:
    for (int i = paramMotionEvent.findPointerIndex(mActivePointerId);; i = 0)
    {
      int j;
      float f1;
      float f2;
      float f3;
      int m;
      float f4;
      int k;
      float f5;
      if (paramMotionEvent == null)
      {
        j = 0;
        f1 = paramMotionEvent.getX();
        f2 = paramMotionEvent.getY();
        f3 = 0.0F;
        m = -1;
        f4 = 0.0F;
        k = 0;
        if (k >= j + 1) {
          break label480;
        }
        if (paramMotionEvent != null) {
          break label341;
        }
        f1 = mMovePoint.x;
        f2 = mMovePoint.y;
        f4 = f1 - mWaveCenterX;
        f5 = f2 - mWaveCenterY;
        f3 = (float)Math.sqrt(dist2(f4, f5));
        if (f3 <= mTargetRadiusFromCenter) {
          break label400;
        }
        f3 = mTargetRadiusFromCenter / f3;
        double d = Math.atan2(-f5, f4);
        if ((!mDragging) && (!mQuickClick)) {
          trySwitchToFirstTouchState(f1, f2);
        }
        if (mQuickClick) {
          break;
        }
        if (mDragging) {
          m = findActiveTarget(d);
        }
        if ((mHandleDrawable != null) && (mDragging))
        {
          if ((f4 >= 0.0F) || (f4 <= -117.0F * density)) {
            break label406;
          }
          float f6 = -f4 / (117.0F * density);
          mHandleDrawable.setRotation(135.0F * f6);
          mPaintColor = ((Integer)evaluator.evaluate(-f4 / (117.0F * density), Integer.valueOf(mHandleCircleColor), Integer.valueOf(mColorOrange))).intValue();
        }
      }
      for (;;)
      {
        k += 1;
        f5 *= f3;
        f4 *= f3;
        f3 = f5;
        break label62;
        j = paramMotionEvent.getHistorySize();
        break;
        if (k < j)
        {
          f1 = paramMotionEvent.getHistoricalX(i, k);
          if (k >= j) {
            break label389;
          }
        }
        for (f2 = paramMotionEvent.getHistoricalY(i, k);; f2 = paramMotionEvent.getY(i))
        {
          break;
          f1 = paramMotionEvent.getX(i);
          break label358;
        }
        f3 = 1.0F;
        break label145;
        if ((f4 > 0.0F) && (f4 < 117.0F * density))
        {
          mPaintColor = ((Integer)evaluator.evaluate(f4 / (117.0F * density), Integer.valueOf(mHandleCircleColor), Integer.valueOf(mColorGreen))).intValue();
          mHandleDrawable.setRotation(0.0F);
        }
      }
      if (!mDragging) {
        break;
      }
      if (m != -1)
      {
        switchToState(4, f4, f3);
        moveHandleTo(f4, f3, false);
      }
      for (;;)
      {
        f1 = Math.abs(mMotionDownX - f1);
        f2 = Math.abs(mMotionDownY - f2);
        if ((mDrawPointCircle) && ((f2 > mTouchSlop) || (f1 > mTouchSlop)) && (!mHoldCircleAnimation) && (mCircleAnimation != null) && (!mCircleAnimation.isStarted()))
        {
          mDrawPointCircle = false;
          invalidate();
        }
        invalidateGlobalRegion(mHandleDrawable);
        mActiveTarget = m;
        return;
        switchToState(3, f4, f3);
        moveHandleTo(f4, f3, false);
      }
    }
  }
  
  private void handleUp(MotionEvent paramMotionEvent)
  {
    float f2;
    float f1;
    if ((!mDragging) || (paramMotionEvent == null))
    {
      if (mActivePointerId != -1) {
        return;
      }
      f2 = mMovePoint.x;
      f1 = mMovePoint.y;
    }
    for (;;)
    {
      if (!mQuickClick) {
        switchToState(5, f2, f1);
      }
      if (!mDrawHandleCircle) {
        break;
      }
      mDrawPointCircle = false;
      return;
      int i = paramMotionEvent.getActionIndex();
      if (paramMotionEvent.getPointerId(i) != mActivePointerId) {
        break;
      }
      f2 = paramMotionEvent.getX(i);
      f1 = paramMotionEvent.getY(i);
      mActivePointerId = -1;
    }
  }
  
  private void internalSetTargetResources(int paramInt)
  {
    mTargetDrawables = loadDrawableArray(paramInt);
    mTargetResourceId = paramInt;
    int k = mTargetDrawables.size();
    int j = mHandleDrawable.getWidth();
    int i = mHandleDrawable.getHeight();
    paramInt = 0;
    while (paramInt < k)
    {
      TargetDrawable localTargetDrawable = (TargetDrawable)mTargetDrawables.get(paramInt);
      j = Math.max(j, localTargetDrawable.getWidth());
      i = Math.max(i, localTargetDrawable.getHeight());
      paramInt += 1;
    }
    if ((mMaxTargetWidth != j) || (mMaxTargetHeight != i))
    {
      mMaxTargetWidth = j;
      mMaxTargetHeight = i;
      requestLayout();
      return;
    }
    updateTargetPositions(mWaveCenterX, mWaveCenterY);
  }
  
  private ArrayList<String> loadDescriptions(int paramInt)
  {
    TypedArray localTypedArray = getContext().getResources().obtainTypedArray(paramInt);
    int i = localTypedArray.length();
    ArrayList localArrayList = new ArrayList(i);
    paramInt = 0;
    while (paramInt < i)
    {
      localArrayList.add(localTypedArray.getString(paramInt));
      paramInt += 1;
    }
    localTypedArray.recycle();
    return localArrayList;
  }
  
  private ArrayList<TargetDrawable> loadDrawableArray(int paramInt)
  {
    Resources localResources = getContext().getResources();
    TypedArray localTypedArray = localResources.obtainTypedArray(paramInt);
    int j = localTypedArray.length();
    ArrayList localArrayList = new ArrayList(j);
    paramInt = 0;
    while (paramInt < j)
    {
      Object localObject = localTypedArray.peekValue(paramInt);
      if ((localObject != null) && (type == 28))
      {
        localObject = new TargetDrawable(localResources.getColor(resourceId), mTargetMinRadius);
        localArrayList.add(localObject);
        paramInt += 1;
      }
      else
      {
        if (localObject != null) {}
        for (int i = resourceId;; i = 0)
        {
          localObject = new TargetDrawable(localResources, i);
          break;
        }
      }
    }
    localTypedArray.recycle();
    return localArrayList;
  }
  
  private void moveHandleTo(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    mHandleDrawable.setX(paramFloat1);
    mHandleDrawable.setY(paramFloat2);
  }
  
  private boolean replaceTargetDrawables(Resources paramResources, int paramInt1, int paramInt2)
  {
    boolean bool3 = false;
    boolean bool1 = false;
    boolean bool2 = bool1;
    if (paramInt1 != 0)
    {
      if (paramInt2 == 0) {
        bool2 = bool1;
      }
    }
    else {
      return bool2;
    }
    ArrayList localArrayList = mTargetDrawables;
    int j = localArrayList.size();
    int i = 0;
    bool1 = bool3;
    label45:
    if (i < j)
    {
      TargetDrawable localTargetDrawable = (TargetDrawable)localArrayList.get(i);
      if ((localTargetDrawable == null) || (localTargetDrawable.getResourceId() != paramInt1)) {
        break label113;
      }
      localTargetDrawable.setDrawable(paramResources, paramInt2);
      bool1 = true;
    }
    label113:
    for (;;)
    {
      i += 1;
      break label45;
      bool2 = bool1;
      if (!bool1) {
        break;
      }
      requestLayout();
      return bool1;
    }
  }
  
  private int resolveMeasured(int paramInt1, int paramInt2)
  {
    int j = View.MeasureSpec.getSize(paramInt1);
    int i = paramInt2;
    switch (View.MeasureSpec.getMode(paramInt1))
    {
    default: 
      i = j;
    case 0: 
      return i;
    }
    return Math.min(j, paramInt2);
  }
  
  private void setGrabbedState(int paramInt)
  {
    if (paramInt != mGrabbedState)
    {
      if (paramInt != 0) {
        vibrate();
      }
      mGrabbedState = paramInt;
      if (mOnTriggerListener != null)
      {
        if (paramInt != 0) {
          break label55;
        }
        mOnTriggerListener.onReleased(this, 1);
      }
    }
    for (;;)
    {
      mOnTriggerListener.onGrabbedStateChange(this, paramInt);
      return;
      label55:
      mOnTriggerListener.onGrabbed(this, 1);
    }
  }
  
  private void setHoldCircleAnimation(float paramFloat1, float paramFloat2)
  {
    if (mCircleAnimation != null)
    {
      mCircleAnimation.cancel();
      resetCircleAnimation();
      invalidate();
    }
    mDrawHandleCircle = true;
    startHandleCircleAnimation(180, new DecelerateInterpolator(3.0F), 43.0F * density, mTargetMaxRadius - 5.0F);
  }
  
  private float square(float paramFloat)
  {
    return paramFloat * paramFloat;
  }
  
  private void startCircleAnimation(int paramInt, TimeInterpolator paramTimeInterpolator, float paramFloat)
  {
    Object localObject1 = PropertyValuesHolder.ofFloat("radius", new float[] { 45.0F * density, 73.0F * density });
    PropertyValuesHolder localPropertyValuesHolder1 = PropertyValuesHolder.ofFloat("angel", new float[] { MIN_ANGEL, MAX_ANGEL });
    PropertyValuesHolder localPropertyValuesHolder2 = PropertyValuesHolder.ofFloat("strokewidth", new float[] { 5.0F, 0.0F });
    PropertyValuesHolder localPropertyValuesHolder3 = PropertyValuesHolder.ofFloat("translateX", new float[] { 53.0F * density, 171.0F * density });
    PropertyValuesHolder localPropertyValuesHolder4 = PropertyValuesHolder.ofInt("bigcirclealpha", new int[] { 30, 0 });
    PropertyValuesHolder localPropertyValuesHolder5 = PropertyValuesHolder.ofKeyframe("ballalpha", new Keyframe[] { Keyframe.ofInt(0.0F, 0), Keyframe.ofInt(0.4F, 255), Keyframe.ofInt(0.78F, 255), Keyframe.ofInt(1.0F, 0) });
    PropertyValuesHolder localPropertyValuesHolder6 = PropertyValuesHolder.ofKeyframe("pointRadius", new Keyframe[] { Keyframe.ofFloat(0.0F, mMinPointRadius), Keyframe.ofFloat(0.4F, mMaxPointRadius), Keyframe.ofFloat(0.78F, mMaxPointRadius), Keyframe.ofFloat(1.0F, mMinPointRadius) });
    PropertyValuesHolder localPropertyValuesHolder7 = PropertyValuesHolder.ofKeyframe("alpha", new Keyframe[] { Keyframe.ofInt(0.0F, 0), Keyframe.ofInt(0.4F, 160), Keyframe.ofInt(0.75F, 160), Keyframe.ofInt(1.0F, 0) });
    Object localObject2 = PropertyValuesHolder.ofKeyframe("rotation", new Keyframe[] { Keyframe.ofFloat(0.0F, 0.0F), Keyframe.ofFloat(0.25F, 9.0F), Keyframe.ofFloat(0.5F, -9.0F), Keyframe.ofFloat(0.75F, 9.0F), Keyframe.ofFloat(1.0F, 0.0F) });
    localObject2 = ObjectAnimator.ofPropertyValuesHolder(mValueHolder, new PropertyValuesHolder[] { localObject2 });
    ((ObjectAnimator)localObject2).setDuration(500L);
    ((ObjectAnimator)localObject2).setInterpolator(new DecelerateInterpolator());
    ((ObjectAnimator)localObject2).addUpdateListener(new MultiWaveView.6(this));
    localObject1 = ObjectAnimator.ofPropertyValuesHolder(mValueHolder, new PropertyValuesHolder[] { localObject1, localPropertyValuesHolder1, localPropertyValuesHolder5, localPropertyValuesHolder6, localPropertyValuesHolder2, localPropertyValuesHolder3, localPropertyValuesHolder7, localPropertyValuesHolder4 });
    ((ObjectAnimator)localObject1).setDuration(paramInt);
    ((ObjectAnimator)localObject1).setInterpolator(paramTimeInterpolator);
    ((ObjectAnimator)localObject1).addUpdateListener(new MultiWaveView.7(this, paramFloat, (ObjectAnimator)localObject1));
    animatorSet = new AnimatorSet();
    animatorSet.playSequentially(new Animator[] { localObject1, localObject2 });
    animatorSet.addListener(new MultiWaveView.8(this));
    animatorSet.start();
    mCircleAnimation = ((ObjectAnimator)localObject1);
  }
  
  private void startHandleCircleAnimation(int paramInt, TimeInterpolator paramTimeInterpolator, float paramFloat1, float paramFloat2)
  {
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(mHandleCircleValueHolder, "radius", new float[] { paramFloat1, paramFloat2 });
    localObjectAnimator.setDuration(paramInt);
    localObjectAnimator.setInterpolator(paramTimeInterpolator);
    localObjectAnimator.addUpdateListener(new MultiWaveView.9(this));
    localObjectAnimator.start();
    mHandleCircleAnimation = localObjectAnimator;
  }
  
  private void switchToState(int paramInt, float paramFloat1, float paramFloat2)
  {
    switch (paramInt)
    {
    case 1: 
    case 3: 
    case 4: 
    default: 
    case 0: 
    case 2: 
      do
      {
        return;
        mHandleDrawable.setState(TargetDrawable.STATE_INACTIVE);
        return;
        setGrabbedState(1);
      } while (!getInstance(getContext()).isEnabled());
      announceTargets();
      return;
    }
    doFinish();
  }
  
  private void triggerHandleAnimation(int paramInt1, int paramInt2, TimeInterpolator paramTimeInterpolator, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Animator.AnimatorListener paramAnimatorListener)
  {
    mHandleAnimations.cancel();
    mHandleAnimations.add(Tweener.to(mHandleDrawable, paramInt1, new Object[] { "ease", paramTimeInterpolator, "delay", Integer.valueOf(paramInt2), "alpha", Float.valueOf(paramFloat1), "x", Float.valueOf(paramFloat2), "y", Float.valueOf(paramFloat3), "rotation", Float.valueOf(paramFloat4), "onUpdate", mUpdateListener, "onComplete", paramAnimatorListener }));
    mHandleAnimations.start();
  }
  
  private boolean trySwitchToFirstTouchState(float paramFloat1, float paramFloat2)
  {
    boolean bool2 = false;
    float f1 = paramFloat1 - mWaveCenterX;
    float f2 = paramFloat2 - mWaveCenterY;
    boolean bool1;
    if (dist2(f1, f2) <= getScaledTapRadiusSquared())
    {
      switchToState(2, paramFloat1, paramFloat2);
      moveHandleTo(f1, f2, false);
      setHoldCircleAnimation(paramFloat1, paramFloat2);
      mDragging = true;
      bool1 = true;
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return bool1;
              bool1 = bool2;
            } while (mDisableQuickClick);
            bool1 = bool2;
          } while (Math.sqrt(dist2(f1, f2)) < mTargetRadiusFromCenter - mTargetBeginScaleDistance);
          bool1 = bool2;
        } while (Math.abs(f2) > mTapRadius);
        mActiveTarget = findActiveTarget(Math.atan2(-f2, f1));
        bool1 = bool2;
      } while (mHandleDrawable.getX() != widthForHandler);
      bool1 = bool2;
    } while (mHandleDrawable.getY() != hightForHandler);
    if (f1 < 0.0F) {
      triggerHandleAnimation(330, 0, circleAnimationInterpolator, mHandleDrawable.getAlpha(), -mTargetRadiusFromCenter, 0.0F, 135.0F, mFinishListener);
    }
    for (;;)
    {
      mQuickClick = true;
      if (mCircleAnimation != null)
      {
        mCircleAnimation.cancel();
        resetCircleAnimation();
        invalidate();
      }
      return true;
      if (f1 > 0.0F) {
        triggerHandleAnimation(330, 0, circleAnimationInterpolator, mHandleDrawable.getAlpha(), mTargetRadiusFromCenter, 0.0F, 0.0F, mFinishListener);
      }
    }
  }
  
  private void updateTargetPositions(float paramFloat1, float paramFloat2)
  {
    ArrayList localArrayList = mTargetDrawables;
    int j = localArrayList.size();
    float f1 = (float)(-6.283185307179586D / j);
    int i = 0;
    while (i < j)
    {
      TargetDrawable localTargetDrawable = (TargetDrawable)localArrayList.get(i);
      float f2 = i * f1;
      localTargetDrawable.setPositionX(paramFloat1);
      localTargetDrawable.setPositionY(paramFloat2);
      localTargetDrawable.setX(mTargetRadiusFromCenter * (float)Math.cos(f2));
      float f3 = mTargetRadiusFromCenter;
      localTargetDrawable.setY((float)Math.sin(f2) * f3);
      i += 1;
    }
  }
  
  private void vibrate()
  {
    if (mVibrator != null) {
      mVibrator.vibrate(mVibrationDuration);
    }
  }
  
  public void cancelMove2TargetAnimation()
  {
    if (mMoveAnimations != null) {
      mMoveAnimations.cancel();
    }
  }
  
  public int getDirectionDescriptionsResourceId()
  {
    return mDirectionDescriptionsResourceId;
  }
  
  public int getResourceIdForTarget(int paramInt)
  {
    TargetDrawable localTargetDrawable = (TargetDrawable)mTargetDrawables.get(paramInt);
    if (localTargetDrawable == null) {
      return 0;
    }
    return localTargetDrawable.getResourceId();
  }
  
  protected int getSuggestedMinimumHeight()
  {
    return (int)(mOuterRadiusHeight + mMaxTargetHeight);
  }
  
  protected int getSuggestedMinimumWidth()
  {
    return (int)(Math.max(mOuterRing.getWidth(), mOuterRadius * 2.0F) + mMaxTargetWidth * 2 + mTapRadius * 2.0F);
  }
  
  public int getTargetDescriptionsResourceId()
  {
    return mTargetDescriptionsResourceId;
  }
  
  public int getTargetPosition(int paramInt)
  {
    int i = 0;
    while (i < mTargetDrawables.size())
    {
      if (((TargetDrawable)mTargetDrawables.get(i)).getResourceId() == paramInt) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public int getTargetResourceId()
  {
    return mTargetResourceId;
  }
  
  void invalidateGlobalRegion(TargetDrawable paramTargetDrawable)
  {
    int i = paramTargetDrawable.getWidth();
    int j = paramTargetDrawable.getHeight();
    RectF localRectF = new RectF(0.0F, 0.0F, i, j);
    localRectF.offset(paramTargetDrawable.getX() - i / 2, paramTargetDrawable.getY() - j / 2);
    paramTargetDrawable = this;
    while ((paramTargetDrawable.getParent() != null) && ((paramTargetDrawable.getParent() instanceof View)))
    {
      paramTargetDrawable = (View)paramTargetDrawable.getParent();
      paramTargetDrawable.getMatrix().mapRect(localRectF);
      paramTargetDrawable.invalidate((int)Math.floor(left), (int)Math.floor(top), (int)Math.ceil(right), (int)Math.ceil(bottom));
    }
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    stop();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    if (mDrawPointCircle)
    {
      paramCanvas.save();
      paramCanvas.translate(mPointCircleX, mPointCircleY);
      mDefaultBackgroundPaint.setColor(mIconbgColor);
      paramCanvas.drawCircle(0.0F, 0.0F, 43.0F * density, mDefaultBackgroundPaint);
      mBigCirclePaint.setStrokeWidth(mStrokeWidth);
      mBigCirclePaint.setAlpha(mBigCircleAlpha);
      float f = (61.0F * density - mCurCircleRadius) / (18.0F * density);
      if ((mCurCircleRadius > 43.0F * density) && (mCurCircleRadius <= 61.0F * density))
      {
        mBallCirclePaint.setAlpha((int)(20.0F * f));
        mBigCirclePaint.setStrokeWidth(f * 5.0F);
      }
      if (mCurCircleRadius >= 61.0F * density)
      {
        mBigCirclePaint.setAlpha(0);
        mBigCirclePaint.setStrokeWidth(0.0F);
      }
      paramCanvas.drawCircle(0.0F, 0.0F, mCurCircleRadius, mBigCirclePaint);
      mBigCirclePaint.setAlpha(mBigCircleAlpha);
      mBigCirclePaint.setStrokeWidth(mStrokeWidth);
      if (mCurCircleRadius > 53.0F * density)
      {
        f = (77.0F * density - mCurCircleRadius) / (24.0F * density);
        mBigCirclePaint.setStrokeWidth(f * 5.0F);
        paramCanvas.drawCircle(0.0F, 0.0F, mCurCircleRadius - 9.0F * density, mBigCirclePaint);
      }
      mBallCirclePaint.setColor(mColorGreen);
      mBallCirclePaint.setAlpha(mBallAlpha);
      f = (density * 133.0F - mTranslateCircleX) / (33.0F * density);
      if ((mTranslateCircleX >= 100.0F * density) && (mTranslateCircleX <= density * 133.0F)) {
        mBallCirclePaint.setAlpha((int)(f * 255.0F));
      }
      if (mTranslateCircleX >= density * 133.0F) {
        mBallCirclePaint.setAlpha(0);
      }
      paramCanvas.drawCircle(mTranslateCircleX, 0.0F, 5.0F * density + 1.0F, mBallCirclePaint);
      mBallCirclePaint.setAlpha(mBallAlpha);
      if (mTranslateCircleX > 75.0F * density)
      {
        f = (density * 155.0F - mTranslateCircleX) / (33.0F * density);
        if ((mTranslateCircleX >= 122.0F * density) && (mTranslateCircleX <= density * 155.0F)) {
          mBallCirclePaint.setAlpha((int)(f * 255.0F));
        }
        if (mTranslateCircleX >= density * 155.0F) {
          mBallCirclePaint.setAlpha(0);
        }
        paramCanvas.drawCircle(mTranslateCircleX - 20.0F * density, 0.0F, 3.0F * density, mBallCirclePaint);
      }
      mBallCirclePaint.setAlpha(mBallAlpha);
      if (mTranslateCircleX > 91.0F * density)
      {
        if (mTranslateCircleX > 171.0F * density) {
          mBallCirclePaint.setAlpha(0);
        }
        paramCanvas.drawCircle(mTranslateCircleX - 36.0F * density, 0.0F, density + 1.0F, mBallCirclePaint);
      }
      mBallCirclePaint.setColor(mColorOrange);
      mBallCirclePaint.setAlpha(mBallAlpha);
      f = (density * 133.0F - mTranslateCircleX) / (33.0F * density);
      if ((mTranslateCircleX >= 100.0F * density) && (mTranslateCircleX <= density * 133.0F)) {
        mBallCirclePaint.setAlpha((int)(f * 255.0F));
      }
      if (mTranslateCircleX >= density * 133.0F) {
        mBallCirclePaint.setAlpha(0);
      }
      paramCanvas.drawCircle(-mTranslateCircleX, 0.0F, 5.0F * density + 1.0F, mBallCirclePaint);
      mBallCirclePaint.setAlpha(mBallAlpha);
      if (mTranslateCircleX > 75.0F * density)
      {
        f = (density * 155.0F - mTranslateCircleX) / (33.0F * density);
        if ((mTranslateCircleX >= 122.0F * density) && (mTranslateCircleX <= density * 155.0F)) {
          mBallCirclePaint.setAlpha((int)(f * 255.0F));
        }
        if (mTranslateCircleX >= density * 155.0F) {
          mBallCirclePaint.setAlpha(0);
        }
        paramCanvas.drawCircle(-mTranslateCircleX + 20.0F * density, 0.0F, 3.0F * density, mBallCirclePaint);
      }
      mBallCirclePaint.setAlpha(mBallAlpha);
      if (mTranslateCircleX > 91.0F * density)
      {
        if (mTranslateCircleX > 171.0F * density) {
          mBallCirclePaint.setAlpha(0);
        }
        paramCanvas.drawCircle(-mTranslateCircleX + 36.0F * density, 0.0F, density + 1.0F, mBallCirclePaint);
      }
      mHandleDrawable.setRotation(mShakeAngel);
      paramCanvas.restore();
    }
    if ((mDrawHandleCircle) && (mHandleCircleValueHolder != null))
    {
      mDrawPointCircle = false;
      paramCanvas.save();
      mHandleCirclePaint.setColor(mPaintColor);
      paramCanvas.drawCircle(mHandleDrawable.getX() + mHandleDrawable.getPositionX(), mHandleDrawable.getY() + mHandleDrawable.getPositionY(), mHandleCircleValueHolder.getRadius(), mHandleCirclePaint);
      paramCanvas.restore();
    }
    int j = mTargetDrawables.size();
    if (!mDrawPointCircle)
    {
      int i = 0;
      while (i < j)
      {
        TargetDrawable localTargetDrawable = (TargetDrawable)mTargetDrawables.get(i);
        if (localTargetDrawable != null) {
          localTargetDrawable.draw(paramCanvas, false);
        }
        i += 1;
      }
    }
    mHandleDrawable.draw(paramCanvas, true);
  }
  
  public boolean onHoverEvent(MotionEvent paramMotionEvent)
  {
    int i;
    if (getInstance(getContext()).isTouchExplorationEnabled())
    {
      i = paramMotionEvent.getAction();
      switch (i)
      {
      }
    }
    for (;;)
    {
      onTouchEvent(paramMotionEvent);
      paramMotionEvent.setAction(i);
      return super.onHoverEvent(paramMotionEvent);
      paramMotionEvent.setAction(0);
      continue;
      paramMotionEvent.setAction(2);
      continue;
      paramMotionEvent.setAction(1);
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    float f1 = Math.max(mOuterRing.getWidth(), mOuterRadius * 2.0F);
    float f2 = mHorizontalInset;
    f1 = Math.max(paramInt3 - paramInt1, f1 + mMaxTargetWidth * 2 + mTapRadius * 2.0F) / 2.0F + f2;
    f2 = mVerticalInset;
    f2 = Math.max(paramInt4 - paramInt2, mMaxTargetHeight + mOuterRadiusHeight) / 2.0F + f2;
    if (mInitialLayout)
    {
      moveHandleTo(0.0F, 0.0F, false);
      mInitialLayout = false;
    }
    mOuterRing.setPositionX(f1);
    mOuterRing.setPositionY(f2);
    mHandleDrawable.setPositionX(f1);
    mHandleDrawable.setPositionY(f2);
    updateTargetPositions(f1, f2);
    mWaveCenterX = f1;
    mWaveCenterY = f2;
    mPointCircleX = mWaveCenterX;
    mPointCircleY = mWaveCenterY;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = getSuggestedMinimumWidth();
    int j = getSuggestedMinimumHeight();
    paramInt1 = resolveMeasured(paramInt1, i);
    paramInt2 = resolveMeasured(paramInt2, j);
    computeInsets(paramInt1 - i, paramInt2 - j);
    setMeasuredDimension(paramInt1, paramInt2);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getActionMasked())
    {
    case 4: 
    case 5: 
    default: 
      return super.onTouchEvent(paramMotionEvent);
    case 0: 
      mPaintColor = mHandleCircleColor;
      handleDown(paramMotionEvent);
      if ((mDrawHandleCircle) && (mCircleAnimation != null)) {
        mCircleAnimation.cancel();
      }
      break;
    }
    for (;;)
    {
      invalidate();
      return true;
      handleMove(paramMotionEvent);
      continue;
      handleMove(paramMotionEvent);
      if (paramMotionEvent != null) {
        Log.i("MultiWaveView", "Action Up x = " + paramMotionEvent.getX() + " y = " + paramMotionEvent.getY());
      }
      handleUp(paramMotionEvent);
      continue;
      handleMove(paramMotionEvent);
      handleCancel(paramMotionEvent);
    }
  }
  
  public void ping()
  {
    if ((mActiveTarget != -1) && (!mDragging) && (!mQuickClick))
    {
      triggerHandleAnimation(330, 200, MultiWaveView.Ease.Quart.easeOut, 1.0F, 0.0F, 0.0F, 0.0F, mResetListenerWithPing);
      mActiveTarget = -1;
    }
    if ((mCircleAnimation == null) || (!mCircleAnimation.isStarted()))
    {
      resetCircleAnimation();
      startCircleAnimation(1600, circleAnimationInterpolator, 0.0F);
    }
    mDrawHandleCircle = false;
  }
  
  public boolean replaceTargetDrawablesIfPresent(ComponentName paramComponentName, String paramString, int paramInt)
  {
    if (paramInt == 0) {}
    for (;;)
    {
      return false;
      try
      {
        PackageManager localPackageManager = getContext().getPackageManager();
        Bundle localBundle = getActivityInfo128metaData;
        if (localBundle != null)
        {
          int i = localBundle.getInt(paramString);
          if (i != 0)
          {
            boolean bool = replaceTargetDrawables(localPackageManager.getResourcesForActivity(paramComponentName), paramInt, i);
            return bool;
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        Log.w("MultiWaveView", "Failed to swap drawable; " + paramComponentName.flattenToShortString() + " not found", paramString);
        return false;
      }
      catch (Resources.NotFoundException paramString)
      {
        Log.w("MultiWaveView", "Failed to swap drawable from " + paramComponentName.flattenToShortString(), paramString);
      }
    }
    return false;
  }
  
  public void reset(boolean paramBoolean)
  {
    mHandleAnimations.stop();
    triggerHandleAnimation(0, 0, MultiWaveView.Ease.Quart.easeOut, 1.0F, 0.0F, 0.0F, 0.0F, null);
    Tweener.reset();
  }
  
  public void resetCircleAnimation()
  {
    mCurCircleRadius = mMinPointCircleRadius;
    mCurPointRadius = mMinPointRadius;
    mCurAlpha = 0;
    mCurAngel = 0.0F;
    mHoldCircleAnimation = false;
    mPointCircleX = mWaveCenterX;
    mPointCircleY = mWaveCenterY;
    mDrawPointCircle = true;
    mBallAlpha = 0;
    mIconbgColor = 0;
    mShakeAngel = 0.0F;
    mBigCircleAlpha = 0;
  }
  
  public void resumeAnimations()
  {
    mHandleAnimations.setSuspended(false);
    mHandleAnimations.start();
  }
  
  public void setDirectionDescriptionsResourceId(int paramInt)
  {
    mDirectionDescriptionsResourceId = paramInt;
    if (mDirectionDescriptions != null) {
      mDirectionDescriptions.clear();
    }
  }
  
  public void setEnableTarget(int paramInt, boolean paramBoolean)
  {
    int i = 0;
    for (;;)
    {
      if (i < mTargetDrawables.size())
      {
        TargetDrawable localTargetDrawable = (TargetDrawable)mTargetDrawables.get(i);
        if (localTargetDrawable.getResourceId() == paramInt) {
          localTargetDrawable.setEnabled(paramBoolean);
        }
      }
      else
      {
        return;
      }
      i += 1;
    }
  }
  
  public void setGravity(int paramInt)
  {
    mGravity = paramInt;
  }
  
  public void setOnTriggerListener(OnTriggerListener paramOnTriggerListener)
  {
    mOnTriggerListener = paramOnTriggerListener;
  }
  
  public void setTargetDescriptionsResourceId(int paramInt)
  {
    mTargetDescriptionsResourceId = paramInt;
    if (mTargetDescriptions != null) {
      mTargetDescriptions.clear();
    }
  }
  
  public void setTargetResources(int paramInt)
  {
    if (mAnimatingTargets)
    {
      mNewTargetResources = paramInt;
      return;
    }
    internalSetTargetResources(paramInt);
  }
  
  public void setVibrateEnabled(boolean paramBoolean)
  {
    if ((paramBoolean) && (mVibrator == null)) {
      mVibrator = ((Vibrator)getContext().getSystemService("vibrator"));
    }
    while (paramBoolean) {
      return;
    }
    mVibrator = null;
  }
  
  public boolean startMove2TargetAnimation(int paramInt, long paramLong, boolean paramBoolean1, boolean paramBoolean2)
  {
    TargetDrawable localTargetDrawable;
    if ((mActiveTarget < 0) && (mActiveTarget != paramInt) && (paramInt >= 0) && (paramInt < mTargetDrawables.size()))
    {
      localTargetDrawable = (TargetDrawable)mTargetDrawables.get(paramInt);
      mActiveTarget = paramInt;
    }
    while (localTargetDrawable == null)
    {
      return false;
      localTargetDrawable = null;
    }
    mMoveAnimations.cancel();
    float f1;
    if (paramInt == 0)
    {
      f1 = 0.0F;
      if (paramInt != 0) {
        break label308;
      }
    }
    label308:
    for (float f2 = mTargetRadiusFromCenter;; f2 = -mTargetRadiusFromCenter)
    {
      mMoveAnimations.add(Tweener.to(mHandleDrawable, paramLong, new Object[] { "ease", circleAnimationInterpolator, "delay", Integer.valueOf(0), "alpha", Float.valueOf(mHandleDrawable.getAlpha()), "x", Float.valueOf(f2), "y", Integer.valueOf(0), "rotation", Float.valueOf(f1), "onUpdate", mUpdateListener, "onComplete", new MultiWaveView.4(this) }));
      mQuickClick = true;
      mMoveAnimations.start();
      if (mCircleAnimation != null)
      {
        mCircleAnimation.cancel();
        resetCircleAnimation();
        invalidate();
      }
      if (mHandleCircleValueHolder != null)
      {
        mDrawHandleCircle = true;
        mHandleCircleValueHolder.setRadius(mTargetMaxRadius - 5.0F);
      }
      mIsBluetoothAns = paramBoolean2;
      return true;
      f1 = 135.0F;
      break;
    }
  }
  
  public void stop()
  {
    if (mCircleAnimation != null) {
      mCircleAnimation.end();
    }
    if (mHandleAnimations != null) {
      mHandleAnimations.stop();
    }
    if (mHandleCircleAnimation != null) {
      mHandleCircleAnimation.end();
    }
    if (animatorSet != null) {
      animatorSet.cancel();
    }
    invalidate();
  }
  
  public void suspendAnimations()
  {
    mHandleAnimations.setSuspended(true);
  }
  
  class AnimationBundle
    extends ArrayList<MultiWaveView.Tweener>
  {
    private static final long serialVersionUID = -6319262269245852568L;
    private boolean mSuspended;
    
    private AnimationBundle() {}
    
    public void cancel()
    {
      int j = size();
      int i = 0;
      while (i < j)
      {
        getanimator.cancel();
        i += 1;
      }
      clear();
    }
    
    public void setSuspended(boolean paramBoolean)
    {
      mSuspended = paramBoolean;
    }
    
    public void start()
    {
      if (mSuspended) {}
      for (;;)
      {
        return;
        int j = size();
        int i = 0;
        while (i < j)
        {
          getanimator.start();
          i += 1;
        }
      }
    }
    
    public void stop()
    {
      int j = size();
      int i = 0;
      while (i < j)
      {
        getanimator.end();
        i += 1;
      }
      clear();
    }
  }
  
  static class Ease
  {
    private static final float DOMAIN = 1.0F;
    private static final float DURATION = 1.0F;
    private static final float START = 0.0F;
    
    static class Cubic
    {
      public static final TimeInterpolator easeIn = new MultiWaveView.Ease.Cubic.1();
      public static final TimeInterpolator easeInOut = new MultiWaveView.Ease.Cubic.3();
      public static final TimeInterpolator easeOut = new MultiWaveView.Ease.Cubic.2();
    }
    
    static class Linear
    {
      public static final TimeInterpolator easeNone = new MultiWaveView.Ease.Linear.1();
    }
    
    static class Quad
    {
      public static final TimeInterpolator easeIn = new MultiWaveView.Ease.Quad.1();
      public static final TimeInterpolator easeInOut = new MultiWaveView.Ease.Quad.3();
      public static final TimeInterpolator easeOut = new MultiWaveView.Ease.Quad.2();
    }
    
    static class Quart
    {
      public static final TimeInterpolator easeIn = new MultiWaveView.Ease.Quart.1();
      public static final TimeInterpolator easeInOut = new MultiWaveView.Ease.Quart.3();
      public static final TimeInterpolator easeOut = new MultiWaveView.Ease.Quart.2();
    }
    
    static class Quint
    {
      public static final TimeInterpolator easeIn = new MultiWaveView.Ease.Quint.1();
      public static final TimeInterpolator easeInOut = new MultiWaveView.Ease.Quint.3();
      public static final TimeInterpolator easeOut = new MultiWaveView.Ease.Quint.2();
    }
    
    static class Sine
    {
      public static final TimeInterpolator easeIn = new MultiWaveView.Ease.Sine.1();
      public static final TimeInterpolator easeInOut = new MultiWaveView.Ease.Sine.3();
      public static final TimeInterpolator easeOut = new MultiWaveView.Ease.Sine.2();
    }
    
    static class customTrack
    {
      public static final TimeInterpolator easeLadderShape = new MultiWaveView.Ease.customTrack.1();
      public static final TimeInterpolator easeSinShape = new MultiWaveView.Ease.customTrack.2();
      static float mOutput = 1.0F;
    }
  }
  
  class MyAnimatorUpdateListener
    implements ValueAnimator.AnimatorUpdateListener
  {
    public boolean ignorSnap = true;
    
    private MyAnimatorUpdateListener() {}
    
    public void onAnimationUpdate(ValueAnimator paramValueAnimator)
    {
      MultiWaveView.this.handleMove(null, ignorSnap);
      invalidate();
    }
  }
  
  static class MyPoint
  {
    public float x;
    public float y;
    
    public MyPoint(float paramFloat1, float paramFloat2)
    {
      x = paramFloat1;
      y = paramFloat2;
    }
    
    public float getX()
    {
      return x;
    }
    
    public float getY()
    {
      return y;
    }
    
    public void setX(float paramFloat)
    {
      x = paramFloat;
    }
    
    public void setY(float paramFloat)
    {
      y = paramFloat;
    }
  }
  
  public static abstract interface OnTriggerListener
  {
    public static final int CENTER_HANDLE = 1;
    public static final int NO_HANDLE = 0;
    
    public abstract void onFinishFinalAnimation();
    
    public abstract void onGrabbed(View paramView, int paramInt);
    
    public abstract void onGrabbedStateChange(View paramView, int paramInt);
    
    public abstract void onReleased(View paramView, int paramInt);
    
    public abstract void onTrigger(View paramView, int paramInt, boolean paramBoolean);
  }
  
  static class TargetDrawable
  {
    private static final boolean DEBUG = false;
    public static final int[] STATE_ACTIVE = { 16842910, 16842914 };
    public static final int[] STATE_FOCUSED = { 16842910, -16842914, 16842908 };
    public static final int[] STATE_INACTIVE = { 16842910, -16842914 };
    private static final String TAG = "TargetDrawable";
    private static final Xfermode sMode = new PorterDuffXfermode(PorterDuff.Mode.XOR);
    private float mAlpha = 1.0F;
    private Bitmap mBitmap;
    private int mCircleColor;
    private float mCircleRadius;
    private Drawable mDrawable;
    private boolean mEnabled = true;
    private boolean mIsCircle = false;
    private Paint mPaint;
    private float mPositionX = 0.0F;
    private float mPositionY = 0.0F;
    private final int mResourceId;
    private float mRotate = 0.0F;
    private float mScaleX = 1.0F;
    private float mScaleY = 1.0F;
    private float mTranslationX = 0.0F;
    private float mTranslationY = 0.0F;
    
    public TargetDrawable(int paramInt, float paramFloat)
    {
      mResourceId = -1;
      mDrawable = null;
      mBitmap = null;
      mIsCircle = true;
      mCircleColor = paramInt;
      mCircleRadius = paramFloat;
      mPaint = new Paint();
      mPaint.setFilterBitmap(true);
      mPaint.setAntiAlias(true);
    }
    
    public TargetDrawable(Resources paramResources, int paramInt)
    {
      mResourceId = paramInt;
      setDrawable(paramResources, paramInt);
      mPaint = new Paint();
      mPaint.setFilterBitmap(true);
      mPaint.setAntiAlias(true);
      mIsCircle = false;
    }
    
    public TargetDrawable(TargetDrawable paramTargetDrawable)
    {
      mResourceId = mResourceId;
      if (mDrawable != null) {}
      for (paramTargetDrawable = mDrawable.mutate();; paramTargetDrawable = null)
      {
        mDrawable = paramTargetDrawable;
        resizeDrawables();
        setState(STATE_INACTIVE);
        mPaint = new Paint();
        mPaint.setFilterBitmap(true);
        mPaint.setAntiAlias(true);
        mIsCircle = false;
        return;
      }
    }
    
    private Bitmap drawableToBitmap(Drawable paramDrawable)
    {
      int i = paramDrawable.getIntrinsicWidth();
      int j = paramDrawable.getIntrinsicHeight();
      if (paramDrawable.getOpacity() != -1) {}
      for (Object localObject = Bitmap.Config.ARGB_8888;; localObject = Bitmap.Config.RGB_565) {
        try
        {
          localObject = Bitmap.createBitmap(i, j, (Bitmap.Config)localObject);
          Canvas localCanvas = new Canvas((Bitmap)localObject);
          paramDrawable.setBounds(0, 0, i, j);
          paramDrawable.draw(localCanvas);
          return (Bitmap)localObject;
        }
        catch (OutOfMemoryError paramDrawable)
        {
          Log.w("TargetDrawable", paramDrawable.toString() + "");
          return null;
        }
        catch (IllegalArgumentException paramDrawable)
        {
          Log.w("TargetDrawable", paramDrawable.toString() + "");
        }
      }
      return null;
    }
    
    private int getStateCount(StateListDrawable paramStateListDrawable)
    {
      try
      {
        int i = ((Integer)paramStateListDrawable.getClass().getMethod("getStateCount", new Class[0]).invoke(paramStateListDrawable, new Object[0])).intValue();
        return i;
      }
      catch (Exception paramStateListDrawable)
      {
        paramStateListDrawable.printStackTrace();
      }
      return 0;
    }
    
    private Drawable getStateDrawable(StateListDrawable paramStateListDrawable, int paramInt)
    {
      try
      {
        paramStateListDrawable = (Drawable)paramStateListDrawable.getClass().getMethod("getStateDrawable", new Class[] { Integer.TYPE }).invoke(paramStateListDrawable, new Object[] { Integer.valueOf(paramInt) });
        return paramStateListDrawable;
      }
      catch (Exception paramStateListDrawable)
      {
        paramStateListDrawable.printStackTrace();
      }
      return null;
    }
    
    private int getStateDrawableIndex(StateListDrawable paramStateListDrawable, int[] paramArrayOfInt)
    {
      try
      {
        int i = ((Integer)paramStateListDrawable.getClass().getMethod("getStateDrawableIndex", new Class[] { int[].class }).invoke(paramStateListDrawable, new Object[] { paramArrayOfInt })).intValue();
        return i;
      }
      catch (Exception paramStateListDrawable)
      {
        paramStateListDrawable.printStackTrace();
      }
      return -1;
    }
    
    private void resizeDrawables()
    {
      if ((mDrawable instanceof StateListDrawable))
      {
        StateListDrawable localStateListDrawable = (StateListDrawable)mDrawable;
        int k = 0;
        int i = 0;
        int j = 0;
        while (k < getStateCount(localStateListDrawable))
        {
          Drawable localDrawable = getStateDrawable(localStateListDrawable, k);
          j = Math.max(j, localDrawable.getIntrinsicWidth());
          i = Math.max(i, localDrawable.getIntrinsicHeight());
          k += 1;
        }
        localStateListDrawable.setBounds(0, 0, j, i);
        k = 0;
        while (k < getStateCount(localStateListDrawable))
        {
          getStateDrawable(localStateListDrawable, k).setBounds(0, 0, j, i);
          k += 1;
        }
      }
      if (mDrawable != null) {
        mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
      }
      if (mDrawable != null) {
        mBitmap = drawableToBitmap(mDrawable);
      }
    }
    
    public void draw(Canvas paramCanvas)
    {
      draw(paramCanvas, false);
    }
    
    public void draw(Canvas paramCanvas, boolean paramBoolean)
    {
      if (!mEnabled) {
        return;
      }
      int i = getWidth() / 2;
      int j = getHeight() / 2;
      paramCanvas.save();
      paramCanvas.translate(mTranslationX + mPositionX, mTranslationY + mPositionY);
      paramCanvas.translate(-i, -j);
      Matrix localMatrix = new Matrix();
      localMatrix.preScale(mScaleX, mScaleY, i, j);
      localMatrix.postRotate(mRotate, i, j);
      if (mBitmap != null) {
        paramCanvas.drawBitmap(mBitmap, localMatrix, mPaint);
      }
      for (;;)
      {
        paramCanvas.restore();
        return;
        if (mIsCircle)
        {
          mPaint.setColor(mCircleColor);
          paramCanvas.drawCircle(i, j, mCircleRadius * mScaleX, mPaint);
        }
      }
    }
    
    public float getAlpha()
    {
      return mAlpha;
    }
    
    public int getHeight()
    {
      if (mDrawable != null) {
        return mDrawable.getIntrinsicHeight();
      }
      if (mBitmap != null) {
        return mBitmap.getHeight();
      }
      if (mIsCircle) {
        return (int)(mCircleRadius * 2.0F);
      }
      return 0;
    }
    
    public float getPositionX()
    {
      return mPositionX;
    }
    
    public float getPositionY()
    {
      return mPositionY;
    }
    
    public int getResourceId()
    {
      return mResourceId;
    }
    
    public float getRotation()
    {
      return mRotate;
    }
    
    public float getScaleX()
    {
      return mScaleX;
    }
    
    public float getScaleY()
    {
      return mScaleY;
    }
    
    public int getWidth()
    {
      if (mDrawable != null) {
        return mDrawable.getIntrinsicWidth();
      }
      if (mBitmap != null) {
        return mBitmap.getWidth();
      }
      if (mIsCircle) {
        return (int)(mCircleRadius * 2.0F);
      }
      return 0;
    }
    
    public float getX()
    {
      return mTranslationX;
    }
    
    public float getY()
    {
      return mTranslationY;
    }
    
    public boolean hasState(int[] paramArrayOfInt)
    {
      if ((mDrawable instanceof StateListDrawable)) {
        return getStateDrawableIndex((StateListDrawable)mDrawable, paramArrayOfInt) != -1;
      }
      return false;
    }
    
    public boolean isActive()
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      int[] arrayOfInt;
      int i;
      if ((mDrawable instanceof StateListDrawable))
      {
        arrayOfInt = ((StateListDrawable)mDrawable).getState();
        i = 0;
      }
      for (;;)
      {
        bool1 = bool2;
        if (i < arrayOfInt.length)
        {
          if (arrayOfInt[i] == 16842908) {
            bool1 = true;
          }
        }
        else {
          return bool1;
        }
        i += 1;
      }
    }
    
    public boolean isEnabled()
    {
      if (mEnabled)
      {
        if (mIsCircle) {}
        while ((mBitmap != null) || (mDrawable != null)) {
          return true;
        }
      }
      return false;
    }
    
    public void setAlpha(float paramFloat)
    {
      mAlpha = paramFloat;
    }
    
    public void setDrawable(Resources paramResources, int paramInt)
    {
      Drawable localDrawable = null;
      if (paramInt == 0) {}
      for (paramResources = null;; paramResources = paramResources.getDrawable(paramInt))
      {
        if (paramResources != null) {
          localDrawable = paramResources.mutate();
        }
        mDrawable = localDrawable;
        resizeDrawables();
        setState(STATE_INACTIVE);
        return;
      }
    }
    
    public void setEnabled(boolean paramBoolean)
    {
      mEnabled = paramBoolean;
    }
    
    public void setPositionX(float paramFloat)
    {
      mPositionX = paramFloat;
    }
    
    public void setPositionY(float paramFloat)
    {
      mPositionY = paramFloat;
    }
    
    public void setRotation(float paramFloat)
    {
      mRotate = paramFloat;
    }
    
    public void setScaleX(float paramFloat)
    {
      mScaleX = paramFloat;
    }
    
    public void setScaleY(float paramFloat)
    {
      mScaleY = paramFloat;
    }
    
    public void setState(int[] paramArrayOfInt)
    {
      if ((mDrawable instanceof StateListDrawable)) {
        ((StateListDrawable)mDrawable).setState(paramArrayOfInt);
      }
    }
    
    public void setX(float paramFloat)
    {
      mTranslationX = paramFloat;
    }
    
    public void setY(float paramFloat)
    {
      mTranslationY = paramFloat;
    }
  }
  
  static class Tweener
  {
    private static final boolean DEBUG = false;
    private static final String TAG = "Tweener";
    private static Animator.AnimatorListener mCleanupListener = new MultiWaveView.Tweener.1();
    private static HashMap<Object, Tweener> sTweens = new HashMap();
    ObjectAnimator animator;
    
    public Tweener(ObjectAnimator paramObjectAnimator)
    {
      animator = paramObjectAnimator;
    }
    
    private static void remove(Animator paramAnimator)
    {
      Iterator localIterator = sTweens.entrySet().iterator();
      while (localIterator.hasNext()) {
        if (nextgetValueanimator == paramAnimator) {
          localIterator.remove();
        }
      }
    }
    
    private static void replace(ArrayList<PropertyValuesHolder> paramArrayList, Object... paramVarArgs)
    {
      int j = paramVarArgs.length;
      int i = 0;
      if (i < j)
      {
        Object localObject = paramVarArgs[i];
        localObject = (Tweener)sTweens.get(localObject);
        if (localObject != null)
        {
          animator.cancel();
          if (paramArrayList == null) {
            break label74;
          }
          animator.setValues((PropertyValuesHolder[])paramArrayList.toArray(new PropertyValuesHolder[paramArrayList.size()]));
        }
        for (;;)
        {
          i += 1;
          break;
          label74:
          sTweens.remove(localObject);
        }
      }
    }
    
    public static void reset()
    {
      sTweens.clear();
    }
    
    public static Tweener to(Object paramObject, long paramLong, Object... paramVarArgs)
    {
      long l = 0L;
      Object localObject1 = null;
      Object localObject2 = null;
      Object localObject3 = null;
      ArrayList localArrayList = new ArrayList(paramVarArgs.length / 2);
      int i = 0;
      if (i < paramVarArgs.length)
      {
        if (!(paramVarArgs[i] instanceof String)) {
          throw new IllegalArgumentException("Key must be a string: " + paramVarArgs[i]);
        }
        localObject4 = (String)paramVarArgs[i];
        Object localObject5 = paramVarArgs[(i + 1)];
        if ("simultaneousTween".equals(localObject4))
        {
          localObject4 = localObject3;
          localObject3 = localObject1;
          localObject1 = localObject4;
        }
        for (;;)
        {
          i += 2;
          localObject4 = localObject3;
          localObject3 = localObject1;
          localObject1 = localObject4;
          break;
          if ("ease".equals(localObject4))
          {
            localObject4 = (TimeInterpolator)localObject5;
            localObject3 = localObject1;
            localObject1 = localObject4;
          }
          else if (("onUpdate".equals(localObject4)) || ("onUpdateListener".equals(localObject4)))
          {
            localObject4 = (ValueAnimator.AnimatorUpdateListener)localObject5;
            localObject1 = localObject3;
            localObject3 = localObject4;
          }
          else if (("onComplete".equals(localObject4)) || ("onCompleteListener".equals(localObject4)))
          {
            localObject4 = (Animator.AnimatorListener)localObject5;
            localObject2 = localObject3;
            localObject3 = localObject1;
            localObject1 = localObject2;
            localObject2 = localObject4;
          }
          else if ("delay".equals(localObject4))
          {
            l = ((Number)localObject5).longValue();
            localObject4 = localObject1;
            localObject1 = localObject3;
            localObject3 = localObject4;
          }
          else if ("syncWith".equals(localObject4))
          {
            localObject4 = localObject1;
            localObject1 = localObject3;
            localObject3 = localObject4;
          }
          else if ((localObject5 instanceof float[]))
          {
            localArrayList.add(PropertyValuesHolder.ofFloat((String)localObject4, new float[] { ((float[])(float[])localObject5)[0], ((float[])(float[])localObject5)[1] }));
            localObject4 = localObject1;
            localObject1 = localObject3;
            localObject3 = localObject4;
          }
          else if ((localObject5 instanceof int[]))
          {
            localArrayList.add(PropertyValuesHolder.ofInt((String)localObject4, new int[] { ((int[])(int[])localObject5)[0], ((int[])(int[])localObject5)[1] }));
            localObject4 = localObject1;
            localObject1 = localObject3;
            localObject3 = localObject4;
          }
          else
          {
            if (!(localObject5 instanceof Number)) {
              break label481;
            }
            localArrayList.add(PropertyValuesHolder.ofFloat((String)localObject4, new float[] { ((Number)localObject5).floatValue() }));
            localObject4 = localObject1;
            localObject1 = localObject3;
            localObject3 = localObject4;
          }
        }
        label481:
        throw new IllegalArgumentException("Bad argument for key \"" + (String)localObject4 + "\" with value " + localObject5.getClass());
      }
      Object localObject4 = (Tweener)sTweens.get(paramObject);
      if (localObject4 == null)
      {
        paramVarArgs = ObjectAnimator.ofPropertyValuesHolder(paramObject, (PropertyValuesHolder[])localArrayList.toArray(new PropertyValuesHolder[localArrayList.size()]));
        localObject4 = new Tweener(paramVarArgs);
        sTweens.put(paramObject, localObject4);
      }
      for (paramObject = localObject4;; paramObject = localObject4)
      {
        if (localObject3 != null) {
          paramVarArgs.setInterpolator((TimeInterpolator)localObject3);
        }
        paramVarArgs.setStartDelay(l);
        paramVarArgs.setDuration(paramLong);
        if (localObject1 != null)
        {
          paramVarArgs.removeAllUpdateListeners();
          paramVarArgs.addUpdateListener((ValueAnimator.AnimatorUpdateListener)localObject1);
        }
        if (localObject2 != null)
        {
          paramVarArgs.removeAllListeners();
          paramVarArgs.addListener((Animator.AnimatorListener)localObject2);
        }
        paramVarArgs.addListener(mCleanupListener);
        return (Tweener)paramObject;
        paramVarArgs = sTweensgetanimator;
        replace(localArrayList, new Object[] { paramObject });
      }
    }
    
    Tweener from(Object paramObject, long paramLong, Object... paramVarArgs)
    {
      return to(paramObject, paramLong, paramVarArgs);
    }
  }
  
  class ValueHolder
  {
    private int mAlpha;
    private float mAngel;
    private float mCircleRadius;
    private float mPointRadius;
    private float mRotion;
    
    private ValueHolder() {}
    
    public int getAlpha()
    {
      return mAlpha;
    }
    
    public float getAngel()
    {
      return mAngel;
    }
    
    public float getPointRadius()
    {
      return mPointRadius;
    }
    
    public float getRadius()
    {
      return mCircleRadius;
    }
    
    public float getRotation()
    {
      return mRotion;
    }
    
    public void setAlpha(int paramInt)
    {
      mAlpha = paramInt;
    }
    
    public void setAngel(float paramFloat)
    {
      mAngel = paramFloat;
    }
    
    public void setPointRadius(float paramFloat)
    {
      mPointRadius = paramFloat;
    }
    
    public void setRadius(float paramFloat)
    {
      mCircleRadius = paramFloat;
    }
    
    public void setRotation(float paramFloat)
    {
      mRotion = paramFloat;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MultiWaveView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */