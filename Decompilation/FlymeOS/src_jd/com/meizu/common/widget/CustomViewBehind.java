package com.meizu.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.meizu.common.R.color;

public class CustomViewBehind
  extends ViewGroup
{
  private static final int INVALID_POINTER = -1;
  private static final int MARGIN_THRESHOLD = 50;
  private static final String TAG = "CustomViewBehind";
  private static int mGestureAreaWidth = 100;
  private int mActivePointerId = -1;
  private boolean mChildrenEnabled;
  private View mContent;
  private float mFadeDegree;
  private boolean mFadeEnabled;
  private final Paint mFadePaint = new Paint();
  private float mInitialMotionX;
  private boolean mIsBeingDragged;
  private float mLastMotionX;
  private float mLastMotionY;
  private int mMarginThreshold = (int)TypedValue.applyDimension(1, 50.0F, getResources().getDisplayMetrics());
  private int mMaximumVelocity;
  private int mMenuWidth;
  private int mMinimumVelocity;
  private int mMode;
  private float mScrollScale;
  private View mSecondaryContent;
  private Drawable mSecondaryShadowDrawable;
  private View mSelectedView;
  private Bitmap mSelectorDrawable;
  private boolean mSelectorEnabled = true;
  private Drawable mShadowDrawable;
  private int mShadowWidth;
  private int mTouchMode = 0;
  private int mTouchSlop;
  private SlidingMenu.CanvasTransformer mTransformer;
  private VelocityTracker mVelocityTracker;
  private CustomViewAbove mViewAbove;
  private int[] mViewBehindLocation = new int[2];
  private boolean mVisibleAlways = false;
  private boolean mWidthChanged;
  
  public CustomViewBehind(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public CustomViewBehind(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setGestureAreaWidth(mMarginThreshold);
    paramContext = ViewConfiguration.get(getContext());
    mTouchSlop = paramContext.getScaledTouchSlop();
    mMinimumVelocity = paramContext.getScaledMinimumFlingVelocity();
    mMaximumVelocity = paramContext.getScaledMaximumFlingVelocity();
    setBackgroundColor(getResources().getColor(R.color.mz_slidingmenu_background_light));
  }
  
  private int getSelectorTop()
  {
    return mSelectedView.getTop() + (mSelectedView.getHeight() - mSelectorDrawable.getHeight()) / 2;
  }
  
  private void initOrResetVelocityTracker()
  {
    if (mVelocityTracker == null)
    {
      mVelocityTracker = VelocityTracker.obtain();
      return;
    }
    mVelocityTracker.clear();
  }
  
  private void initVelocityTrackerIfNotExists()
  {
    if (mVelocityTracker == null) {
      mVelocityTracker = VelocityTracker.obtain();
    }
  }
  
  private void onSecondaryPointerUp(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionIndex();
    if (paramMotionEvent.getPointerId(i) == mActivePointerId) {
      if (i != 0) {
        break label46;
      }
    }
    label46:
    for (i = 1;; i = 0)
    {
      mLastMotionX = paramMotionEvent.getX(i);
      mActivePointerId = paramMotionEvent.getPointerId(i);
      recycleVelocityTracker();
      return;
    }
  }
  
  private void recycleVelocityTracker()
  {
    if (mVelocityTracker != null) {}
    try
    {
      mVelocityTracker.recycle();
      mVelocityTracker = null;
      return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;) {}
    }
  }
  
  public boolean IsBeingDragged()
  {
    return mIsBeingDragged;
  }
  
  protected void dispatchDraw(Canvas paramCanvas)
  {
    if (mTransformer != null)
    {
      paramCanvas.save();
      mTransformer.transformCanvas(paramCanvas, mViewAbove.getPercentOpen());
      super.dispatchDraw(paramCanvas);
      paramCanvas.restore();
      return;
    }
    super.dispatchDraw(paramCanvas);
  }
  
  public void drawFade(View paramView, Canvas paramCanvas, float paramFloat)
  {
    int i = 0;
    if (!mFadeEnabled) {
      return;
    }
    int j = (int)(mFadeDegree * 255.0F * Math.abs(1.0F - paramFloat));
    mFadePaint.setColor(Color.argb(j, 0, 0, 0));
    if (mMode == 0)
    {
      j = paramView.getLeft() - getBehindWidth();
      i = paramView.getLeft();
    }
    for (;;)
    {
      paramCanvas.drawRect(j, 0.0F, i, getHeight(), mFadePaint);
      return;
      if (mMode == 1)
      {
        j = paramView.getRight();
        i = paramView.getRight() + getBehindWidth();
      }
      else if (mMode == 2)
      {
        i = paramView.getLeft();
        j = getBehindWidth();
        int k = paramView.getLeft();
        paramCanvas.drawRect(i - j, 0.0F, k, getHeight(), mFadePaint);
        j = paramView.getRight();
        i = paramView.getRight() + getBehindWidth();
      }
      else
      {
        j = 0;
      }
    }
  }
  
  public void drawSelector(View paramView, Canvas paramCanvas, float paramFloat)
  {
    if (!mSelectorEnabled) {}
    while ((mSelectorDrawable == null) || (mSelectedView == null) || (!((String)mSelectedView.getTag()).equals("CustomViewBehindSelectedView"))) {
      return;
    }
    paramCanvas.save();
    int i = (int)(mSelectorDrawable.getWidth() * paramFloat);
    int j;
    if (mMode == 0)
    {
      j = paramView.getLeft();
      i = j - i;
      paramCanvas.clipRect(i, 0, j, getHeight());
      paramCanvas.drawBitmap(mSelectorDrawable, i, getSelectorTop(), null);
    }
    for (;;)
    {
      paramCanvas.restore();
      return;
      if (mMode == 1)
      {
        j = paramView.getRight();
        i += j;
        paramCanvas.clipRect(j, 0, i, getHeight());
        paramCanvas.drawBitmap(mSelectorDrawable, i - mSelectorDrawable.getWidth(), getSelectorTop(), null);
      }
    }
  }
  
  public void drawShadow(View paramView, Canvas paramCanvas)
  {
    if (mShadowDrawable == null) {}
    do
    {
      return;
      if (mShadowWidth <= 0) {
        mShadowWidth = mShadowDrawable.getIntrinsicWidth();
      }
      if (mMode != 0) {
        break;
      }
      paramView.getLocationInWindow(mViewBehindLocation);
    } while (mViewBehindLocation[0] <= 0);
    int i = paramView.getLeft() + mShadowWidth;
    for (;;)
    {
      mShadowDrawable.setBounds(i - mShadowWidth, 0, i, getHeight());
      mShadowDrawable.draw(paramCanvas);
      return;
      if (mMode == 1)
      {
        i = paramView.getRight();
      }
      else if (mMode == 2)
      {
        if (mSecondaryShadowDrawable != null)
        {
          i = paramView.getRight();
          mSecondaryShadowDrawable.setBounds(i - mShadowWidth, 0, i, getHeight());
          mSecondaryShadowDrawable.draw(paramCanvas);
        }
        i = paramView.getLeft() + mShadowWidth;
      }
      else
      {
        i = 0;
      }
    }
  }
  
  public int getAbsLeftBound(View paramView)
  {
    int j = 0;
    if (mViewAbove != null) {}
    for (int i = mViewAbove.getAboveOffsetLeft();; i = 0)
    {
      if ((mMode == 0) || (mMode == 2)) {
        i = paramView.getLeft() - getBehindWidth() + i;
      }
      do
      {
        return i;
        i = j;
      } while (mMode != 1);
      return paramView.getLeft();
    }
  }
  
  public int getAbsRightBound(View paramView)
  {
    if (mMode == 0) {
      return paramView.getLeft();
    }
    if ((mMode == 1) || (mMode == 2)) {
      return paramView.getLeft() + getBehindWidth();
    }
    return 0;
  }
  
  public int getBehindWidth()
  {
    return mContent.getWidth();
  }
  
  public View getContent()
  {
    return mContent;
  }
  
  public int getMenuLeft(View paramView, int paramInt)
  {
    int i = 0;
    if (mViewAbove != null) {
      i = mViewAbove.getAboveOffsetLeft();
    }
    if (mMode == 0) {
      switch (paramInt)
      {
      }
    }
    for (;;)
    {
      return paramView.getLeft();
      return i + paramView.getLeft() - getBehindWidth();
      return i + paramView.getLeft();
      if (mMode == 1) {
        switch (paramInt)
        {
        case 1: 
        default: 
          break;
        case 0: 
          return i + paramView.getLeft();
        case 2: 
          return i + paramView.getLeft() + getBehindWidth();
        }
      } else if (mMode == 2) {
        switch (paramInt)
        {
        }
      }
    }
    return i + paramView.getLeft() - getBehindWidth();
    return i + paramView.getLeft() + getBehindWidth();
  }
  
  public int getMenuPage(int paramInt)
  {
    int i;
    if (paramInt > 1) {
      i = 2;
    }
    while ((mMode == 0) && (i > 1))
    {
      return 0;
      i = paramInt;
      if (paramInt < 1) {
        i = 0;
      }
    }
    if ((mMode == 1) && (i < 1)) {
      return 2;
    }
    return i;
  }
  
  public int getMode()
  {
    return mMode;
  }
  
  public float getScrollScale()
  {
    return mScrollScale;
  }
  
  public View getSecondaryContent()
  {
    return mSecondaryContent;
  }
  
  public boolean marginTouchAllowed(View paramView, int paramInt)
  {
    int i = paramView.getLeft();
    int j = paramView.getRight();
    if (mMode == 0) {
      if ((paramInt < i) || (paramInt > i + mGestureAreaWidth)) {}
    }
    do
    {
      do
      {
        return true;
        return false;
        if (mMode != 1) {
          break;
        }
      } while ((paramInt <= j) && (paramInt >= j - mGestureAreaWidth));
      return false;
      if (mMode != 2) {
        break;
      }
    } while (((paramInt >= i) && (paramInt <= i + mGestureAreaWidth)) || ((paramInt <= j) && (paramInt >= j - mGestureAreaWidth)));
    return false;
    return false;
  }
  
  public boolean menuClosedSlideAllowed(float paramFloat)
  {
    if (mMode == 0) {
      if (paramFloat <= 0.0F) {}
    }
    do
    {
      do
      {
        return true;
        return false;
        if (mMode != 1) {
          break;
        }
      } while (paramFloat < 0.0F);
      return false;
    } while (mMode == 2);
    return false;
  }
  
  public boolean menuOpenSlideAllowed(float paramFloat)
  {
    if (mMode == 0) {
      if (paramFloat >= 0.0F) {}
    }
    do
    {
      do
      {
        return true;
        return false;
        if (mMode != 1) {
          break;
        }
      } while (paramFloat > 0.0F);
      return false;
    } while (mMode == 2);
    return false;
  }
  
  public boolean menuOpenTouchAllowed(View paramView, int paramInt, float paramFloat)
  {
    switch (mTouchMode)
    {
    default: 
      return false;
    case 1: 
      return true;
    }
    return menuTouchInQuickReturn(paramView, paramInt, paramFloat);
  }
  
  public boolean menuTouchInQuickReturn(View paramView, int paramInt, float paramFloat)
  {
    if ((mMode == 0) || ((mMode == 2) && (paramInt == 0))) {
      if (paramFloat < paramView.getLeft()) {}
    }
    do
    {
      return true;
      return false;
      if ((mMode != 1) && ((mMode != 2) || (paramInt != 2))) {
        break;
      }
    } while (paramFloat <= paramView.getRight());
    return false;
    return false;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!mViewAbove.isSlidingEnabled()) {
      return false;
    }
    if (mViewAbove.IsBeingDragged())
    {
      mIsBeingDragged = false;
      return true;
    }
    int i = paramMotionEvent.getAction();
    if ((i == 2) && (mIsBeingDragged)) {
      return true;
    }
    switch (i & 0xFF)
    {
    }
    for (;;)
    {
      return mIsBeingDragged;
      i = mActivePointerId;
      if (i != -1)
      {
        int j = paramMotionEvent.findPointerIndex(i);
        i = (int)paramMotionEvent.getX(j);
        j = (int)paramMotionEvent.getY(j);
        int k = (int)Math.abs(mLastMotionX - i);
        int m = (int)Math.abs(mLastMotionY - j);
        if ((k > mTouchSlop) && (k - m > 0) && (mViewAbove.thisSlideAllowed(i - mLastMotionX)))
        {
          mIsBeingDragged = true;
          mLastMotionX = i;
          mLastMotionY = j;
          initVelocityTrackerIfNotExists();
          mVelocityTracker.addMovement(paramMotionEvent);
          if (getParent() != null)
          {
            getParent().requestDisallowInterceptTouchEvent(true);
            continue;
            i = (int)paramMotionEvent.getX();
            i = (int)paramMotionEvent.getY();
            mIsBeingDragged = false;
            float f = (int)paramMotionEvent.getX();
            mInitialMotionX = f;
            mLastMotionX = f;
            mLastMotionY = i;
            mActivePointerId = paramMotionEvent.getPointerId(0);
            initOrResetVelocityTracker();
            mVelocityTracker.addMovement(paramMotionEvent);
            continue;
            mIsBeingDragged = false;
            mActivePointerId = -1;
            recycleVelocityTracker();
            continue;
            i = paramMotionEvent.getActionIndex();
            mLastMotionX = ((int)paramMotionEvent.getX(i));
            mLastMotionY = ((int)paramMotionEvent.getY(i));
            mActivePointerId = paramMotionEvent.getPointerId(i);
            continue;
            onSecondaryPointerUp(paramMotionEvent);
            mLastMotionX = ((int)paramMotionEvent.getX(paramMotionEvent.findPointerIndex(mActivePointerId)));
            mLastMotionY = ((int)paramMotionEvent.getY(paramMotionEvent.findPointerIndex(mActivePointerId)));
          }
        }
      }
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt1 = Math.min(mContent.getMeasuredWidth(), paramInt3 - paramInt1);
    paramInt2 = Math.min(mContent.getMeasuredHeight(), paramInt4 - paramInt2);
    mContent.layout(0, 0, paramInt1, paramInt2);
    if (mSecondaryContent != null) {
      mSecondaryContent.layout(0, 0, paramInt1, paramInt2);
    }
    if (mWidthChanged) {
      switch (mViewAbove.getCurrentItem())
      {
      }
    }
    for (;;)
    {
      mWidthChanged = false;
      return;
      mViewAbove.setCurrentItem(0, false, true);
      continue;
      mViewAbove.setCurrentItem(2, false, true);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int j = getDefaultSize(0, paramInt1);
    int i = getDefaultSize(0, paramInt2);
    setMeasuredDimension(j, i);
    paramInt1 = getChildMeasureSpec(paramInt1, 0, Math.min(j, mMenuWidth));
    paramInt2 = getChildMeasureSpec(paramInt2, 0, i);
    mContent.measure(paramInt1, paramInt2);
    if (mSecondaryContent != null) {
      mSecondaryContent.measure(paramInt1, paramInt2);
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = false;
    if (!mViewAbove.isSlidingEnabled())
    {
      bool = super.onTouchEvent(paramMotionEvent);
      return bool;
    }
    if (mViewAbove.IsBeingDragged())
    {
      mIsBeingDragged = false;
      return false;
    }
    initVelocityTrackerIfNotExists();
    mVelocityTracker.addMovement(paramMotionEvent);
    float f1;
    int j;
    int i;
    float f2;
    float f3;
    switch (paramMotionEvent.getAction() & 0xFF)
    {
    case 4: 
    case 5: 
    default: 
    case 0: 
    case 2: 
      label301:
      do
      {
        for (;;)
        {
          return true;
          if (getChildCount() == 0) {
            break;
          }
          f1 = (int)paramMotionEvent.getX();
          mInitialMotionX = f1;
          mLastMotionX = f1;
          mLastMotionY = ((int)paramMotionEvent.getY());
          mActivePointerId = paramMotionEvent.getPointerId(0);
        }
        j = paramMotionEvent.findPointerIndex(mActivePointerId);
        i = (int)paramMotionEvent.getX(j);
        j = (int)paramMotionEvent.getY(j);
        f2 = mLastMotionX - i;
        f3 = mLastMotionY;
        float f4 = j;
        f1 = f2;
        if (!mIsBeingDragged)
        {
          f1 = f2;
          if (Math.abs(f2) > mTouchSlop / 2)
          {
            f1 = f2;
            if (Math.abs(f2) - Math.abs(f3 - f4) > 0.0F)
            {
              f1 = f2;
              if (mViewAbove.thisSlideAllowed(i - mLastMotionX))
              {
                paramMotionEvent = getParent();
                if (paramMotionEvent != null) {
                  paramMotionEvent.requestDisallowInterceptTouchEvent(true);
                }
                mIsBeingDragged = true;
                if (f2 <= 0.0F) {
                  break label399;
                }
                f1 = f2 - mTouchSlop;
              }
            }
          }
        }
      } while (!mIsBeingDragged);
      mLastMotionX = i;
      mLastMotionY = j;
      f1 = mViewAbove.getScrollX() + f1;
      f2 = mViewAbove.getLeftBound();
      f3 = mViewAbove.getRightBound();
      if (f1 < f2) {
        f1 = f2;
      }
      break;
    }
    for (;;)
    {
      mLastMotionX += f1 - (int)f1;
      mViewAbove.scrollTo((int)f1, getScrollY());
      mViewAbove.pageScrolled((int)f1);
      break;
      label399:
      f1 = f2 + mTouchSlop;
      break label301;
      if (f1 > f3)
      {
        f1 = f3;
        continue;
        if (!mIsBeingDragged) {
          break;
        }
        VelocityTracker localVelocityTracker = mVelocityTracker;
        localVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
        i = (int)localVelocityTracker.getXVelocity(mActivePointerId);
        f1 = (mViewAbove.getScrollX() - mViewAbove.getDestScrollX(mViewAbove.getCurrentItem())) / getBehindWidth();
        j = paramMotionEvent.findPointerIndex(mActivePointerId);
        if (mActivePointerId != -1)
        {
          j = (int)(paramMotionEvent.getX(j) - mInitialMotionX);
          j = mViewAbove.determineTargetPage(f1, i, j);
          mViewAbove.setCurrentItemInternal(j, true, true, i);
        }
        for (;;)
        {
          mActivePointerId = -1;
          mIsBeingDragged = false;
          recycleVelocityTracker();
          break;
          mViewAbove.setCurrentItemInternal(mViewAbove.getCurrentItem(), true, true, i);
        }
        onSecondaryPointerUp(paramMotionEvent);
        break;
      }
    }
  }
  
  public void scrollBehindTo(View paramView, int paramInt1, int paramInt2)
  {
    int i = 0;
    int m = 0;
    int j = 0;
    int k = 0;
    if (mMode == 0)
    {
      i = k;
      if (paramInt1 >= paramView.getLeft())
      {
        i = k;
        if (!mVisibleAlways) {
          i = 4;
        }
      }
      scrollTo((int)((getBehindWidth() + paramInt1) * mScrollScale), paramInt2);
    }
    for (;;)
    {
      if (i == 4) {
        Log.v("CustomViewBehind", "behind INVISIBLE");
      }
      setVisibility(i);
      if (i == 0) {
        invalidate();
      }
      return;
      if (mMode == 1)
      {
        i = m;
        if (paramInt1 <= paramView.getLeft())
        {
          i = m;
          if (!mVisibleAlways) {
            i = 4;
          }
        }
        scrollTo((int)(getBehindWidth() - getWidth() + (paramInt1 - getBehindWidth()) * mScrollScale), paramInt2);
      }
      else if (mMode == 2)
      {
        View localView = mContent;
        if (paramInt1 >= paramView.getLeft())
        {
          i = 4;
          label185:
          localView.setVisibility(i);
          localView = mSecondaryContent;
          if ((paramInt1 > paramView.getLeft()) || (mVisibleAlways)) {
            break label269;
          }
        }
        label269:
        for (i = 4;; i = 0)
        {
          localView.setVisibility(i);
          i = j;
          if (paramInt1 == 0) {
            i = 4;
          }
          if (paramInt1 > paramView.getLeft()) {
            break label275;
          }
          scrollTo((int)((getBehindWidth() + paramInt1) * mScrollScale), paramInt2);
          break;
          i = 0;
          break label185;
        }
        label275:
        scrollTo((int)(getBehindWidth() - getWidth() + (paramInt1 - getBehindWidth()) * mScrollScale), paramInt2);
      }
    }
  }
  
  public void scrollTo(int paramInt1, int paramInt2)
  {
    super.scrollTo(paramInt1, paramInt2);
    if (mTransformer != null) {
      invalidate();
    }
  }
  
  public void setCanvasTransformer(SlidingMenu.CanvasTransformer paramCanvasTransformer)
  {
    mTransformer = paramCanvasTransformer;
  }
  
  public void setChildrenEnabled(boolean paramBoolean)
  {
    mChildrenEnabled = paramBoolean;
  }
  
  public void setContent(View paramView)
  {
    if (mContent != null) {
      removeView(mContent);
    }
    mContent = paramView;
    addView(mContent);
  }
  
  public void setCustomViewAbove(CustomViewAbove paramCustomViewAbove)
  {
    mViewAbove = paramCustomViewAbove;
  }
  
  public void setFadeDegree(float paramFloat)
  {
    if ((paramFloat > 1.0F) || (paramFloat < 0.0F)) {
      throw new IllegalStateException("The BehindFadeDegree must be between 0.0f and 1.0f");
    }
    mFadeDegree = paramFloat;
  }
  
  public void setFadeEnabled(boolean paramBoolean)
  {
    mFadeEnabled = paramBoolean;
  }
  
  public void setGestureAreaWidth(int paramInt)
  {
    mGestureAreaWidth = paramInt;
  }
  
  public void setMenuVisibleAlways(boolean paramBoolean)
  {
    mVisibleAlways = paramBoolean;
  }
  
  public void setMenuWidth(int paramInt)
  {
    if (mMenuWidth != paramInt) {}
    for (boolean bool = true;; bool = false)
    {
      mWidthChanged = bool;
      mMenuWidth = paramInt;
      requestLayout();
      return;
    }
  }
  
  public void setMode(int paramInt)
  {
    if ((paramInt == 0) || (paramInt == 1))
    {
      if (mContent != null) {
        mContent.setVisibility(0);
      }
      if (mSecondaryContent != null) {
        mSecondaryContent.setVisibility(4);
      }
    }
    mMode = paramInt;
  }
  
  public void setScrollScale(float paramFloat)
  {
    mScrollScale = paramFloat;
  }
  
  public void setSecondaryContent(View paramView)
  {
    if (mSecondaryContent != null) {
      removeView(mSecondaryContent);
    }
    mSecondaryContent = paramView;
    addView(mSecondaryContent);
  }
  
  public void setSecondaryShadowDrawable(Drawable paramDrawable)
  {
    mSecondaryShadowDrawable = paramDrawable;
    invalidate();
  }
  
  public void setSelectedView(View paramView)
  {
    if (mSelectedView != null)
    {
      mSelectedView.setTag("");
      mSelectedView = null;
    }
    if ((paramView != null) && (paramView.getParent() != null))
    {
      mSelectedView = paramView;
      mSelectedView.setTag("CustomViewBehindSelectedView");
      invalidate();
    }
  }
  
  public void setSelectorBitmap(Bitmap paramBitmap)
  {
    mSelectorDrawable = paramBitmap;
    refreshDrawableState();
  }
  
  public void setSelectorEnabled(boolean paramBoolean)
  {
    mSelectorEnabled = paramBoolean;
  }
  
  public void setShadowDrawable(Drawable paramDrawable)
  {
    mShadowDrawable = paramDrawable;
    invalidate();
  }
  
  public void setShadowWidth(int paramInt)
  {
    mShadowWidth = paramInt;
    invalidate();
  }
  
  public void setTouchMode(int paramInt)
  {
    mTouchMode = paramInt;
  }
  
  public void setVisibleAlways(boolean paramBoolean)
  {
    mVisibleAlways = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CustomViewBehind
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */