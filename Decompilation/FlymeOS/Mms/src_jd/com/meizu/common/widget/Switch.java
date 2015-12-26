package com.meizu.common.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region.Op;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.CompoundButton;
import com.meizu.common.R.attr;
import com.meizu.common.R.drawable;
import com.meizu.common.R.styleable;
import com.meizu.common.interpolator.PathInterpolatorCompat;

public class Switch
  extends CompoundButton
{
  private static final int[] CHECKED_STATE_SET = { 16842912 };
  private static final int THUMB_ANIMATION_DURATION = 250;
  private static final int TOUCH_MODE_DOWN = 1;
  private static final int TOUCH_MODE_DRAGGING = 2;
  private static final int TOUCH_MODE_IDLE = 0;
  private static int[] sSwitchStyleableId;
  private boolean isChanged = false;
  private int mMinFlingVelocity;
  private Layout mOffLayout;
  private Layout mOnLayout;
  private ObjectAnimator mPositionAnimator;
  private boolean mSplitTrack;
  private ValueAnimator mSwitchAnimator;
  private int mSwitchBottom;
  private int mSwitchHeight;
  private int mSwitchLeft;
  private int mSwitchMinWidth;
  private int mSwitchPadding;
  private int mSwitchRight;
  private int mSwitchTop;
  private TransformationMethod2 mSwitchTransformationMethod;
  private int mSwitchWidth;
  private final Rect mTempRect = new Rect();
  private ColorStateList mTextColors;
  private TextPaint mTextPaint = new TextPaint(1);
  private Drawable mThumbOffCache = null;
  private Drawable mThumbOffDrawable;
  private Drawable mThumbOnCache = null;
  private Drawable mThumbOnDrawable;
  private float mThumbPosition;
  private int mThumbWidth;
  private int mTouchMode;
  private int mTouchSlop;
  private float mTouchX;
  private float mTouchY;
  private Drawable mTrackCache = null;
  private Drawable mTrackDrawable;
  private VelocityTracker mVelocityTracker = VelocityTracker.obtain();
  private Interpolator pathInterpolator;
  public CharSequence switchOff;
  public CharSequence switchOn;
  
  public Switch(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public Switch(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.MeizuCommon_Switch);
  }
  
  public Switch(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    Resources localResources = getResources();
    mTextPaint.density = getDisplayMetricsdensity;
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.Switch, paramInt, 0);
    mThumbOffDrawable = localTypedArray.getDrawable(R.styleable.Switch_mcThumbOff);
    if (mThumbOffDrawable == null) {
      mThumbOffDrawable = localResources.getDrawable(R.drawable.mc_switch_anim_thumb_off_selector);
    }
    if (mThumbOffDrawable != null) {
      mThumbOffDrawable.setCallback(this);
    }
    mThumbOnDrawable = localTypedArray.getDrawable(R.styleable.Switch_mcThumbOn);
    if (mThumbOnDrawable == null) {
      mThumbOnDrawable = localResources.getDrawable(R.drawable.mc_switch_anim_thumb_on_selector);
    }
    if (mThumbOnDrawable != null) {
      mThumbOnDrawable.setCallback(this);
    }
    mTrackDrawable = localTypedArray.getDrawable(R.styleable.Switch_mcTrack);
    if (mTrackDrawable == null) {
      mTrackDrawable = localResources.getDrawable(R.drawable.mc_switch_bg_default);
    }
    if (mTrackDrawable != null) {
      mTrackDrawable.setCallback(this);
    }
    mSwitchMinWidth = localTypedArray.getDimensionPixelSize(R.styleable.Switch_mcSwitchMinWidth, 0);
    mSwitchPadding = localTypedArray.getDimensionPixelSize(R.styleable.Switch_mcSwitchPadding, 0);
    mSplitTrack = false;
    localTypedArray.recycle();
    sSwitchStyleableId = new int[] { 16843044, 16843045 };
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, sSwitchStyleableId, 16843839, 0);
    switchOn = paramAttributeSet.getText(0);
    switchOff = paramAttributeSet.getText(1);
    paramAttributeSet.recycle();
    paramContext = ViewConfiguration.get(paramContext);
    mTouchSlop = paramContext.getScaledTouchSlop();
    mMinFlingVelocity = paramContext.getScaledMinimumFlingVelocity();
    refreshDrawableState();
    setChecked(isChecked());
  }
  
  private void animateThumbToCheckedState(boolean paramBoolean)
  {
    float f;
    if (paramBoolean)
    {
      f = 1.0F;
      if (mSwitchAnimator != null) {
        mSwitchAnimator.removeAllUpdateListeners();
      }
      mSwitchAnimator = ValueAnimator.ofFloat(new float[] { mThumbPosition, f });
      if (pathInterpolator == null) {
        if (Build.VERSION.SDK_INT < 21) {
          break label125;
        }
      }
    }
    label125:
    for (pathInterpolator = new PathInterpolator(0.33F, 0.0F, 0.33F, 1.0F);; pathInterpolator = new PathInterpolatorCompat(0.33F, 0.0F, 0.33F, 1.0F))
    {
      mSwitchAnimator.setInterpolator(pathInterpolator);
      mSwitchAnimator.setDuration(250L);
      mSwitchAnimator.start();
      mSwitchAnimator.addUpdateListener(new Switch.1(this));
      return;
      f = 0.0F;
      break;
    }
  }
  
  private void cancelPositionAnimator()
  {
    if (mSwitchAnimator != null) {
      mSwitchAnimator.cancel();
    }
  }
  
  private void cancelSuperTouch(MotionEvent paramMotionEvent)
  {
    paramMotionEvent = MotionEvent.obtain(paramMotionEvent);
    paramMotionEvent.setAction(3);
    super.onTouchEvent(paramMotionEvent);
    paramMotionEvent.recycle();
  }
  
  private boolean getTargetCheckedState()
  {
    return mThumbPosition > 0.5F;
  }
  
  private int getThumbOffset()
  {
    if (isLayoutRtl()) {}
    for (float f = 1.0F - mThumbPosition;; f = mThumbPosition) {
      return (int)(f * getThumbScrollRange() + 0.5F);
    }
  }
  
  private int getThumbScrollRange()
  {
    if (mTrackDrawable != null)
    {
      Rect localRect = mTempRect;
      mTrackDrawable.getPadding(localRect);
      if (mThumbOffDrawable != null) {}
      for (Insets localInsets = Insets.NONE;; localInsets = Insets.NONE) {
        return mSwitchWidth - mThumbWidth - left - right - left - right;
      }
    }
    return 0;
  }
  
  private boolean hitThumb(float paramFloat1, float paramFloat2)
  {
    return (paramFloat1 > mSwitchLeft) && (paramFloat1 < mSwitchRight) && (paramFloat2 > mSwitchTop) && (paramFloat2 < mSwitchBottom);
  }
  
  private Layout makeLayout(CharSequence paramCharSequence)
  {
    if (mSwitchTransformationMethod != null) {
      paramCharSequence = mSwitchTransformationMethod.getTransformation(paramCharSequence, this);
    }
    for (;;)
    {
      mTextPaint = new TextPaint(1);
      mTextPaint.density = getResourcesgetDisplayMetricsdensity;
      return new StaticLayout(paramCharSequence, mTextPaint, (int)Math.ceil(Layout.getDesiredWidth(paramCharSequence, mTextPaint)), Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
    }
  }
  
  private void setThumbPosition(float paramFloat)
  {
    mThumbPosition = paramFloat;
    invalidate();
  }
  
  private void stopDrag(MotionEvent paramMotionEvent)
  {
    boolean bool = true;
    mTouchMode = 0;
    int i;
    float f;
    if ((paramMotionEvent.getAction() == 1) && (isEnabled()))
    {
      i = 1;
      if (i == 0) {
        break label117;
      }
      mVelocityTracker.computeCurrentVelocity(1000);
      f = mVelocityTracker.getXVelocity();
      if (Math.abs(f) <= mMinFlingVelocity) {
        break label108;
      }
      if (!isLayoutRtl()) {
        break label96;
      }
      if (f >= 0.0F) {
        break label90;
      }
    }
    for (;;)
    {
      setChecked(bool);
      cancelSuperTouch(paramMotionEvent);
      return;
      i = 0;
      break;
      label90:
      bool = false;
      continue;
      label96:
      if (f <= 0.0F)
      {
        bool = false;
        continue;
        label108:
        bool = getTargetCheckedState();
        continue;
        label117:
        bool = isChecked();
      }
    }
  }
  
  public void draw(Canvas paramCanvas)
  {
    Rect localRect = mTempRect;
    int i2 = mSwitchLeft;
    int n = mSwitchTop;
    int i3 = mSwitchRight;
    int i1 = mSwitchBottom;
    int i4 = i2 + getThumbOffset();
    Insets localInsets;
    int i5;
    label111:
    int j;
    label139:
    int m;
    int k;
    if (mThumbOffDrawable != null)
    {
      localInsets = Insets.NONE;
      if (mTrackDrawable == null) {
        break label502;
      }
      mTrackDrawable.getPadding(localRect);
      i5 = left;
      if (localInsets == Insets.NONE) {
        break label485;
      }
      if (left <= left) {
        break label479;
      }
      i = left - left + i2;
      if (top <= top) {
        break label473;
      }
      j = top - top + n;
      m = i3;
      if (right > right) {
        m = i3 - (right - right);
      }
      if (bottom <= bottom) {
        break label466;
      }
      k = i1 - (bottom - bottom);
      label201:
      mTrackDrawable.setBounds(i, j, m, k);
    }
    label466:
    label473:
    label479:
    label485:
    label502:
    for (int i = i4 + i5;; i = i4)
    {
      if (mThumbOffDrawable != null)
      {
        mThumbOffDrawable.getPadding(localRect);
        j = (int)((mThumbPosition * getThumbScrollRange() + 0.5F) * 1.25F);
        k = left;
        m = (int)((mThumbPosition * getThumbScrollRange() + 0.5F) * 1.25F);
        i3 = mThumbOffDrawable.getIntrinsicWidth();
        i4 = (int)((mThumbOnDrawable.getIntrinsicWidth() - mThumbOffDrawable.getIntrinsicWidth()) * (0.8F - mThumbPosition));
        i5 = right;
        mThumbOffDrawable.setBounds(j + i2 - k, n, i2 + m + i3 + i4 + i5, i1);
        getBackground();
      }
      if (mThumbOnDrawable != null)
      {
        mThumbOnDrawable.getPadding(localRect);
        i = i + mThumbWidth - right;
        j = i - mThumbWidth - left;
        k = (int)((i - j) * (1.0F - mThumbPosition) * 3.5F);
        mThumbOnDrawable.setBounds(j + k, n + k, i - k, i1 - k);
      }
      super.draw(paramCanvas);
      return;
      localInsets = Insets.NONE;
      break;
      k = i1;
      break label201;
      j = n;
      break label139;
      i = i2;
      break label111;
      k = i1;
      j = n;
      i = i2;
      m = i3;
      break label201;
    }
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    int[] arrayOfInt = getDrawableState();
    if (mThumbOffDrawable != null) {
      mThumbOffDrawable.setState(arrayOfInt);
    }
    if (mThumbOnDrawable != null) {
      mThumbOnDrawable.setState(arrayOfInt);
    }
    if (mTrackDrawable != null) {
      mTrackDrawable.setState(arrayOfInt);
    }
    invalidate();
  }
  
  public int getCompoundPaddingLeft()
  {
    int i;
    if (!isLayoutRtl()) {
      i = super.getCompoundPaddingLeft();
    }
    int j;
    do
    {
      return i;
      j = super.getCompoundPaddingLeft() + mSwitchWidth;
      i = j;
    } while (TextUtils.isEmpty(getText()));
    return j + mSwitchPadding;
  }
  
  public int getCompoundPaddingRight()
  {
    int i;
    if (isLayoutRtl()) {
      i = super.getCompoundPaddingRight();
    }
    int j;
    do
    {
      return i;
      j = super.getCompoundPaddingRight() + mSwitchWidth;
      i = j;
    } while (TextUtils.isEmpty(getText()));
    return j + mSwitchPadding;
  }
  
  public int getSwitchMinWidth()
  {
    return mSwitchMinWidth;
  }
  
  public int getSwitchPadding()
  {
    return mSwitchPadding;
  }
  
  public Drawable getThumbDrawable()
  {
    return mThumbOnDrawable;
  }
  
  public Drawable getTrackDrawable()
  {
    return mTrackDrawable;
  }
  
  public boolean isLayoutRtl()
  {
    return false;
  }
  
  public void jumpDrawablesToCurrentState()
  {
    super.jumpDrawablesToCurrentState();
    if (mThumbOffDrawable != null) {
      mThumbOffDrawable.jumpToCurrentState();
    }
    if (mTrackDrawable != null) {
      mTrackDrawable.jumpToCurrentState();
    }
    if ((mSwitchAnimator != null) && (mSwitchAnimator.isRunning()))
    {
      mSwitchAnimator.end();
      mSwitchAnimator = null;
    }
  }
  
  protected int[] onCreateDrawableState(int paramInt)
  {
    int[] arrayOfInt = super.onCreateDrawableState(paramInt + 1);
    if (isChecked()) {
      mergeDrawableStates(arrayOfInt, CHECKED_STATE_SET);
    }
    return arrayOfInt;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    Object localObject = mTempRect;
    Drawable localDrawable2 = mTrackDrawable;
    int i;
    if (localDrawable2 != null)
    {
      localDrawable2.getPadding((Rect)localObject);
      i = mSwitchTop;
      i = mSwitchBottom;
      i = top;
      i = bottom;
      Drawable localDrawable1 = mThumbOffDrawable;
      if (localDrawable2 != null)
      {
        if ((!mSplitTrack) || (localDrawable1 == null)) {
          break label192;
        }
        Insets localInsets = Insets.NONE;
        localDrawable1.copyBounds((Rect)localObject);
        left += left;
        right -= right;
        i = paramCanvas.save();
        paramCanvas.clipRect((Rect)localObject, Region.Op.DIFFERENCE);
        localDrawable2.draw(paramCanvas);
        paramCanvas.restoreToCount(i);
      }
      label142:
      i = paramCanvas.save();
      localObject = mThumbOnDrawable;
      if ((localDrawable1 == null) || (mThumbPosition >= 0.8F)) {
        break label201;
      }
      localDrawable1.mutate();
      localDrawable1.draw(paramCanvas);
    }
    for (;;)
    {
      paramCanvas.restoreToCount(i);
      return;
      ((Rect)localObject).setEmpty();
      break;
      label192:
      localDrawable2.draw(paramCanvas);
      break label142;
      label201:
      ((Drawable)localObject).draw(paramCanvas);
    }
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    paramAccessibilityEvent.setClassName(Switch.class.getName());
  }
  
  public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    super.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfo);
    paramAccessibilityNodeInfo.setClassName(Switch.class.getName());
    if (isChecked()) {}
    CharSequence localCharSequence2;
    for (CharSequence localCharSequence1 = switchOn;; localCharSequence1 = switchOff)
    {
      if (!TextUtils.isEmpty(localCharSequence1))
      {
        localCharSequence2 = paramAccessibilityNodeInfo.getText();
        if (!TextUtils.isEmpty(localCharSequence2)) {
          break;
        }
        paramAccessibilityNodeInfo.setText(localCharSequence1);
      }
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(localCharSequence2).append(' ').append(localCharSequence1);
    paramAccessibilityNodeInfo.setText(localStringBuilder);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = 0;
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    Rect localRect;
    Insets localInsets;
    if (mThumbOffDrawable != null)
    {
      localRect = mTempRect;
      if (mTrackDrawable != null)
      {
        mTrackDrawable.getPadding(localRect);
        localInsets = Insets.NONE;
        paramInt2 = Math.max(0, left - left);
      }
    }
    for (paramInt1 = Math.max(0, right - right);; paramInt1 = i)
    {
      if (isLayoutRtl())
      {
        paramInt3 = getPaddingLeft() + paramInt2;
        paramInt4 = mSwitchWidth + paramInt3 - paramInt2 - paramInt1;
        label109:
        switch (getGravity() & 0x70)
        {
        default: 
          paramInt2 = getPaddingTop();
          paramInt1 = mSwitchHeight + paramInt2;
        }
      }
      for (;;)
      {
        mSwitchLeft = paramInt3;
        mSwitchTop = paramInt2;
        mSwitchBottom = paramInt1;
        mSwitchRight = paramInt4;
        return;
        localRect.setEmpty();
        break;
        paramInt4 = getWidth() - getPaddingRight() - paramInt1;
        paramInt3 = paramInt1 + (paramInt2 + (paramInt4 - mSwitchWidth));
        break label109;
        paramInt2 = (getPaddingTop() + getHeight() - getPaddingBottom()) / 2 - mSwitchHeight / 2;
        paramInt1 = mSwitchHeight + paramInt2;
        continue;
        paramInt1 = getHeight() - getPaddingBottom();
        paramInt2 = paramInt1 - mSwitchHeight;
      }
      paramInt2 = 0;
    }
  }
  
  public void onMeasure(int paramInt1, int paramInt2)
  {
    int k = 0;
    Object localObject = mTempRect;
    int i;
    if (mThumbOnDrawable != null)
    {
      mThumbOnDrawable.getPadding((Rect)localObject);
      j = mThumbOnDrawable.getIntrinsicWidth() - left - right;
      i = mThumbOnDrawable.getIntrinsicHeight();
      mThumbWidth = j;
      if (mTrackDrawable == null) {
        break label216;
      }
      mTrackDrawable.getPadding((Rect)localObject);
    }
    for (int j = mTrackDrawable.getIntrinsicHeight();; j = k)
    {
      int i1 = left;
      int n = right;
      int m = n;
      k = i1;
      if (mThumbOffDrawable != null)
      {
        localObject = Insets.NONE;
        k = Math.max(i1, left);
        m = Math.max(n, right);
      }
      k = Math.max(mSwitchMinWidth, m + (k + mThumbWidth * 2));
      i = Math.max(j, i);
      mSwitchWidth = k;
      mSwitchHeight = i;
      super.onMeasure(paramInt1, paramInt2);
      if (getMeasuredHeight() < i) {
        setMeasuredDimension(getMeasuredWidthAndState(), i);
      }
      return;
      i = 0;
      j = 0;
      break;
      label216:
      ((Rect)localObject).setEmpty();
    }
  }
  
  public void onPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    super.onPopulateAccessibilityEvent(paramAccessibilityEvent);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    mVelocityTracker.addMovement(paramMotionEvent);
    switch (paramMotionEvent.getActionMasked())
    {
    }
    for (;;)
    {
      return super.onTouchEvent(paramMotionEvent);
      float f1 = paramMotionEvent.getX();
      float f2 = paramMotionEvent.getY();
      if ((isEnabled()) && (hitThumb(f1, f2)))
      {
        mTouchMode = 1;
        mTouchX = f1;
        mTouchY = f2;
        continue;
        switch (mTouchMode)
        {
        case 0: 
        default: 
          break;
        case 1: 
          f1 = paramMotionEvent.getX();
          f2 = paramMotionEvent.getY();
          if ((Math.abs(f1 - mTouchX) > mTouchSlop) || (Math.abs(f2 - mTouchY) > mTouchSlop))
          {
            mTouchMode = 2;
            getParent().requestDisallowInterceptTouchEvent(true);
            mTouchX = f1;
            mTouchY = f2;
            return true;
          }
          break;
        case 2: 
          float f3 = paramMotionEvent.getX();
          int i = getThumbScrollRange();
          f1 = f3 - mTouchX;
          if (i != 0) {
            f1 /= i;
          }
          for (;;)
          {
            f2 = f1;
            if (isLayoutRtl()) {
              f2 = -f1;
            }
            f1 = MathUtils.constrain(f2 + mThumbPosition, 0.0F, 1.0F);
            if (f1 != mThumbPosition)
            {
              mTouchX = f3;
              setThumbPosition(f1);
            }
            return true;
            if (f1 > 0.0F) {
              f1 = 1.0F;
            } else {
              f1 = -1.0F;
            }
          }
          if (mTouchMode == 2)
          {
            stopDrag(paramMotionEvent);
            super.onTouchEvent(paramMotionEvent);
            return true;
          }
          mTouchMode = 0;
          mVelocityTracker.clear();
        }
      }
    }
  }
  
  public void setChecked(boolean paramBoolean)
  {
    setChecked(paramBoolean, true);
  }
  
  public void setChecked(boolean paramBoolean1, boolean paramBoolean2)
  {
    super.setChecked(paramBoolean1);
    paramBoolean1 = isChecked();
    if ((paramBoolean2) && (Build.VERSION.SDK_INT >= 19) && (isAttachedToWindow()) && (isLaidOut()))
    {
      animateThumbToCheckedState(paramBoolean1);
      return;
    }
    cancelPositionAnimator();
    if (paramBoolean1) {}
    for (float f = 1.0F;; f = 0.0F)
    {
      setThumbPosition(f);
      return;
    }
  }
  
  public void setCompoundDrawablesWithIntrinsicBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.setCompoundDrawablesWithIntrinsicBounds(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void setStyleDefault()
  {
    if (isChanged)
    {
      mThumbOnDrawable = mThumbOnCache;
      mThumbOffDrawable = mThumbOffCache;
      mTrackDrawable = mTrackCache;
      mThumbOnDrawable.setCallback(this);
      mThumbOffDrawable.setCallback(this);
      mTrackDrawable.setCallback(this);
      invalidate();
    }
  }
  
  public void setStyleWhite()
  {
    Resources localResources = getResources();
    mThumbOnCache = mThumbOnDrawable;
    mThumbOffCache = mThumbOffDrawable;
    mTrackCache = mTrackDrawable;
    mThumbOnDrawable = localResources.getDrawable(R.drawable.mc_switch_anim_thumb_on_selector_color_white);
    mThumbOffDrawable = localResources.getDrawable(R.drawable.mc_switch_anim_thumb_off_selector_color_white);
    mTrackDrawable = localResources.getDrawable(R.drawable.mc_switch_anim_track_color_white);
    if ((mThumbOnDrawable == null) || (mThumbOffDrawable == null) || (mTrackDrawable == null))
    {
      setStyleDefault();
      return;
    }
    mThumbOnDrawable.setCallback(this);
    mThumbOffDrawable.setCallback(this);
    mTrackDrawable.setCallback(this);
    invalidate();
    isChanged = true;
  }
  
  public void setSwitchMinWidth(int paramInt)
  {
    mSwitchMinWidth = paramInt;
    requestLayout();
  }
  
  public void setSwitchPadding(int paramInt)
  {
    mSwitchPadding = paramInt;
    requestLayout();
  }
  
  public void setThumbDrawable(Drawable paramDrawable)
  {
    if (mThumbOnDrawable != null) {
      mThumbOnDrawable.setCallback(null);
    }
    mThumbOnDrawable = paramDrawable;
    if (paramDrawable != null) {
      paramDrawable.setCallback(this);
    }
    requestLayout();
  }
  
  public void setThumbResource(int paramInt)
  {
    setThumbDrawable(getContext().getResources().getDrawable(paramInt));
  }
  
  public void setTrackDrawable(Drawable paramDrawable)
  {
    if (mTrackDrawable != null) {
      mTrackDrawable.setCallback(null);
    }
    mTrackDrawable = paramDrawable;
    if (paramDrawable != null) {
      paramDrawable.setCallback(this);
    }
    requestLayout();
  }
  
  public void setTrackResource(int paramInt)
  {
    setTrackDrawable(getContext().getResources().getDrawable(paramInt));
  }
  
  public void toggle()
  {
    if (!isChecked()) {}
    for (boolean bool = true;; bool = false)
    {
      setChecked(bool);
      return;
    }
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable)
  {
    return (super.verifyDrawable(paramDrawable)) || (paramDrawable == mThumbOffDrawable) || (paramDrawable == mTrackDrawable);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.Switch
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */