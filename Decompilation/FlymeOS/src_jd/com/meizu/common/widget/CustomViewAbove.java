package com.meizu.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomViewAbove
  extends ViewGroup
{
  private static final boolean DEBUG = false;
  private static final int INVALID_POINTER = -1;
  private static final int MAX_SETTLE_DURATION = 300;
  private static final int MIN_DISTANCE_FOR_FLING = 25;
  private static final String TAG = "CustomViewAbove";
  private static final boolean USE_CACHE = false;
  private static int mCriticalVelocity = 650;
  private static final DecelerateInterpolator sInterpolator = new DecelerateInterpolator();
  private int mActivePointerId = -1;
  private SlidingMenu.OnClosedListener mClosedListener;
  private View mContent;
  private int mCurItem;
  private boolean mEnabled = true;
  private int mFlingDistance;
  private List<View> mIgnoredViews = new ArrayList();
  private int mInitialAbsVelocity = -1;
  private float mInitialMotionX;
  private OnPageChangeListener mInternalPageChangeListener;
  private boolean mIsBeingDragged;
  private boolean mIsUnableToDrag;
  private float mLastMotionX;
  private float mLastMotionY;
  private int mMaximumVelocity;
  private int mMinimumVelocity;
  private OnPageChangeListener mOnPageChangeListener;
  private SlidingMenu.OnOpenedListener mOpenedListener;
  private int mPlusVelocitys = 0;
  private boolean mQuickReturn = false;
  private float mScrollX = 0.0F;
  private Scroller mScroller;
  private boolean mScrolling;
  private boolean mScrollingCacheEnabled;
  protected int mTouchMode = 1;
  private int mTouchSlop;
  private int mVelocityCount = 0;
  protected VelocityTracker mVelocityTracker;
  private CustomViewBehind mViewBehind;
  private int mViewOffsetLeft = 0;
  
  public CustomViewAbove(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public CustomViewAbove(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initCustomViewAbove();
  }
  
  private void completeScroll()
  {
    if (mScrolling)
    {
      setScrollingCacheEnabled(false);
      mScroller.abortAnimation();
      int i = getScrollX();
      int j = getScrollY();
      int k = mScroller.getCurrX();
      int m = mScroller.getCurrY();
      if ((i != k) || (j != m)) {
        scrollTo(k, m);
      }
      if (!isMenuOpen()) {
        break label93;
      }
      if (mOpenedListener != null) {
        mOpenedListener.onOpened();
      }
    }
    for (;;)
    {
      mScrolling = false;
      return;
      label93:
      if (mClosedListener != null) {
        mClosedListener.onClosed();
      }
    }
  }
  
  private void endDrag()
  {
    mQuickReturn = false;
    mIsBeingDragged = false;
    mIsUnableToDrag = false;
    mActivePointerId = -1;
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
  
  private int getPointerIndex(MotionEvent paramMotionEvent, int paramInt)
  {
    paramInt = paramMotionEvent.findPointerIndex(paramInt);
    if (paramInt == -1) {
      mActivePointerId = -1;
    }
    return paramInt;
  }
  
  private int getWindowBackgroud()
  {
    TypedArray localTypedArray = getContext().getTheme().obtainStyledAttributes(new int[] { 16842836 });
    int i = localTypedArray.getResourceId(0, 0);
    localTypedArray.recycle();
    return i;
  }
  
  private boolean isInIgnoredView(MotionEvent paramMotionEvent)
  {
    Rect localRect = new Rect();
    Iterator localIterator = mIgnoredViews.iterator();
    while (localIterator.hasNext())
    {
      ((View)localIterator.next()).getHitRect(localRect);
      if (localRect.contains((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY())) {
        return true;
      }
    }
    return false;
  }
  
  private boolean isInternalContentView(MotionEvent paramMotionEvent)
  {
    Rect localRect = new Rect();
    mContent.getHitRect(localRect);
    return localRect.contains((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
  }
  
  private void onSecondaryPointerUp(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionIndex();
    if (paramMotionEvent.getPointerId(i) == mActivePointerId) {
      if (i != 0) {
        break label56;
      }
    }
    label56:
    for (i = 1;; i = 0)
    {
      mLastMotionX = paramMotionEvent.getX(i);
      mActivePointerId = paramMotionEvent.getPointerId(i);
      if (mVelocityTracker != null) {
        mVelocityTracker.clear();
      }
      return;
    }
  }
  
  private void setDefaultBackgroud(View paramView, int paramInt)
  {
    int j = 1;
    int i;
    if (paramView.getBackground() == null)
    {
      i = 1;
      if (paramView == null) {
        break label34;
      }
    }
    for (;;)
    {
      if ((j & i) != 0) {
        paramView.setBackgroundResource(paramInt);
      }
      return;
      i = 0;
      break;
      label34:
      j = 0;
    }
  }
  
  private void setScrollingCacheEnabled(boolean paramBoolean)
  {
    if (mScrollingCacheEnabled != paramBoolean) {
      mScrollingCacheEnabled = paramBoolean;
    }
  }
  
  private void startDrag()
  {
    mIsBeingDragged = true;
    mQuickReturn = false;
  }
  
  private boolean thisTouchAllowed(MotionEvent paramMotionEvent)
  {
    boolean bool2 = false;
    int i = (int)(paramMotionEvent.getX() + mScrollX);
    boolean bool1;
    if (isMenuOpen()) {
      bool1 = mViewBehind.menuOpenTouchAllowed(mContent, mCurItem, i);
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
        switch (mTouchMode)
        {
        case 2: 
        default: 
          return false;
        case 0: 
          return mViewBehind.marginTouchAllowed(mContent, i);
        }
        bool1 = bool2;
      } while (isInIgnoredView(paramMotionEvent));
      bool1 = bool2;
    } while (!isInternalContentView(paramMotionEvent));
    return true;
  }
  
  public boolean IsBeingDragged()
  {
    return mIsBeingDragged;
  }
  
  public void addIgnoredView(View paramView)
  {
    if (!mIgnoredViews.contains(paramView)) {
      mIgnoredViews.add(paramView);
    }
  }
  
  public boolean arrowScroll(int paramInt)
  {
    View localView2 = findFocus();
    View localView1 = localView2;
    if (localView2 == this) {
      localView1 = null;
    }
    localView2 = FocusFinder.getInstance().findNextFocus(this, localView1, paramInt);
    boolean bool;
    if ((localView2 != null) && (localView2 != localView1)) {
      if (paramInt == 17) {
        bool = localView2.requestFocus();
      }
    }
    for (;;)
    {
      if (bool) {
        playSoundEffect(SoundEffectConstants.getContantForFocusDirection(paramInt));
      }
      return bool;
      if (paramInt == 66)
      {
        if ((localView1 != null) && (localView2.getLeft() <= localView1.getLeft()))
        {
          bool = pageRight();
          continue;
        }
        bool = localView2.requestFocus();
        continue;
        if ((paramInt == 17) || (paramInt == 1))
        {
          bool = pageLeft();
          continue;
        }
        if ((paramInt == 66) || (paramInt == 2))
        {
          bool = pageRight();
          continue;
        }
      }
      bool = false;
    }
  }
  
  public void clearIgnoredViews()
  {
    mIgnoredViews.clear();
  }
  
  public void computeScroll()
  {
    if ((!mScroller.isFinished()) && (mScroller.computeScrollOffset()))
    {
      int i = getScrollX();
      int j = getScrollY();
      int k = mScroller.getCurrX();
      int m = mScroller.getCurrY();
      if ((i != k) || (j != m))
      {
        scrollTo(k, m);
        pageScrolled(k);
      }
      invalidate();
      return;
    }
    completeScroll();
  }
  
  int determineTargetPage(float paramFloat, int paramInt1, int paramInt2)
  {
    int j = mCurItem;
    if ((Math.abs(paramInt2) > mFlingDistance) && (Math.abs(paramInt1) > mMinimumVelocity))
    {
      int i;
      if ((paramInt1 > 0) && (paramInt2 > 0)) {
        i = j - 1;
      }
      do
      {
        do
        {
          return i;
          i = j;
        } while (paramInt1 >= 0);
        i = j;
      } while (paramInt2 >= 0);
      return j + 1;
    }
    return Math.round(mCurItem + paramFloat);
  }
  
  protected void dispatchDraw(Canvas paramCanvas)
  {
    super.dispatchDraw(paramCanvas);
    mViewBehind.drawShadow(mContent, paramCanvas);
    mViewBehind.drawFade(mContent, paramCanvas, getPercentOpen());
    mViewBehind.drawSelector(mContent, paramCanvas, getPercentOpen());
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    return (super.dispatchKeyEvent(paramKeyEvent)) || (executeKeyEvent(paramKeyEvent));
  }
  
  float distanceInfluenceForSnapDuration(float paramFloat)
  {
    return FloatMath.sin((float)((paramFloat - 0.5F) * 0.4712389167638204D));
  }
  
  public boolean executeKeyEvent(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getAction() == 0) {
      switch (paramKeyEvent.getKeyCode())
      {
      }
    }
    do
    {
      do
      {
        return false;
        return arrowScroll(17);
        return arrowScroll(66);
      } while (Build.VERSION.SDK_INT < 11);
      if (paramKeyEvent.hasNoModifiers()) {
        return arrowScroll(2);
      }
    } while (!paramKeyEvent.hasModifiers(1));
    return arrowScroll(1);
  }
  
  public int getAboveOffsetLeft()
  {
    return mViewOffsetLeft;
  }
  
  public int getBehindWidth()
  {
    if (mViewBehind == null) {
      return 0;
    }
    return mViewBehind.getBehindWidth();
  }
  
  public int getChildWidth(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return 0;
    case 0: 
      return getBehindWidth();
    }
    return mContent.getWidth();
  }
  
  public View getContent()
  {
    return mContent;
  }
  
  public int getContentLeft()
  {
    return mContent.getLeft() + mContent.getPaddingLeft();
  }
  
  public int getCurrentItem()
  {
    return mCurItem;
  }
  
  public int getDestScrollX(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return 0;
    case 0: 
    case 2: 
      return mViewBehind.getMenuLeft(mContent, paramInt);
    }
    return mContent.getLeft();
  }
  
  public int getLeftBound()
  {
    return mViewBehind.getAbsLeftBound(mContent);
  }
  
  protected float getPercentOpen()
  {
    return Math.abs(mScrollX - mContent.getLeft()) / getBehindWidth();
  }
  
  public int getRightBound()
  {
    return mViewBehind.getAbsRightBound(mContent);
  }
  
  public int getTouchMode()
  {
    return mTouchMode;
  }
  
  void initCustomViewAbove()
  {
    setWillNotDraw(false);
    setDescendantFocusability(262144);
    setFocusable(true);
    Context localContext = getContext();
    mScroller = new Scroller(localContext, sInterpolator);
    ViewConfiguration localViewConfiguration = ViewConfiguration.get(localContext);
    mTouchSlop = localViewConfiguration.getScaledPagingTouchSlop();
    mMinimumVelocity = localViewConfiguration.getScaledMinimumFlingVelocity();
    mMaximumVelocity = localViewConfiguration.getScaledMaximumFlingVelocity();
    setInternalPageChangeListener(new CustomViewAbove.1(this));
    mFlingDistance = ((int)(getResourcesgetDisplayMetricsdensity * 25.0F));
  }
  
  public boolean isMenuOpen()
  {
    return (mCurItem == 0) || (mCurItem == 2);
  }
  
  public boolean isSlidingEnabled()
  {
    return mEnabled;
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool2 = false;
    boolean bool1;
    if (!mEnabled)
    {
      bool1 = bool2;
      if (isMenuOpen()) {
        bool1 = true;
      }
      return bool1;
    }
    if (mViewBehind.IsBeingDragged())
    {
      mIsBeingDragged = false;
      return true;
    }
    int i = paramMotionEvent.getAction() & 0xFF;
    if ((i == 3) || (i == 1) || ((i != 0) && (mIsUnableToDrag)))
    {
      endDrag();
      return false;
    }
    if (mVelocityTracker == null) {
      mVelocityTracker = VelocityTracker.obtain();
    }
    mVelocityTracker.addMovement(paramMotionEvent);
    switch (i)
    {
    }
    for (;;)
    {
      if (!mIsBeingDragged)
      {
        bool1 = bool2;
        if (!mQuickReturn) {
          break;
        }
      }
      return true;
      if ((mTouchMode == 0) && (!isMenuOpen()))
      {
        VelocityTracker localVelocityTracker = mVelocityTracker;
        localVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
        i = (int)localVelocityTracker.getXVelocity(mActivePointerId);
        if (i > 0)
        {
          mPlusVelocitys = (i + mPlusVelocitys);
          mVelocityCount += 1;
        }
      }
      i = mActivePointerId;
      if (i != -1)
      {
        i = getPointerIndex(paramMotionEvent, i);
        if (i != -1)
        {
          float f1 = paramMotionEvent.getX(i);
          float f2 = f1 - mLastMotionX;
          float f3 = Math.abs(f2);
          float f4 = Math.abs(paramMotionEvent.getY(i) - mLastMotionY);
          if ((f3 > mTouchSlop) && (f3 > f4) && (thisSlideAllowed(f2)))
          {
            if ((mTouchMode == 0) && (mVelocityCount > 0))
            {
              bool1 = bool2;
              if (mPlusVelocitys / mVelocityCount > mCriticalVelocity) {
                break;
              }
            }
            startDrag();
            mLastMotionX = f1;
            setScrollingCacheEnabled(true);
            continue;
          }
          if (f4 > mTouchSlop)
          {
            mIsUnableToDrag = true;
            continue;
            i = paramMotionEvent.getAction();
            if (Build.VERSION.SDK_INT >= 8) {}
            mActivePointerId = (i & 0xFF00);
            f1 = paramMotionEvent.getX(mActivePointerId);
            mInitialMotionX = f1;
            mLastMotionX = f1;
            mLastMotionY = paramMotionEvent.getY(mActivePointerId);
            if (thisTouchAllowed(paramMotionEvent))
            {
              mIsBeingDragged = false;
              mIsUnableToDrag = false;
              if ((isMenuOpen()) && (mViewBehind.menuTouchInQuickReturn(mContent, mCurItem, paramMotionEvent.getX() + mScrollX))) {
                mQuickReturn = true;
              }
            }
            for (;;)
            {
              mPlusVelocitys = 0;
              mVelocityCount = 0;
              break;
              mIsUnableToDrag = true;
            }
            mPlusVelocitys = 0;
            mVelocityCount = 0;
            onSecondaryPointerUp(paramMotionEvent);
          }
        }
      }
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt1 = mContent.getMeasuredWidth();
    paramInt2 = mContent.getMeasuredHeight();
    mContent.layout(0, 0, paramInt1, paramInt2);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int j = getDefaultSize(0, paramInt1);
    int i = getDefaultSize(0, paramInt2);
    setMeasuredDimension(j, i);
    paramInt1 = getChildMeasureSpec(paramInt1, 0, j);
    paramInt2 = getChildMeasureSpec(paramInt2, 0, i);
    mContent.measure(paramInt1, paramInt2);
  }
  
  protected void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
    if (mOnPageChangeListener != null) {
      mOnPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
    }
    if (mInternalPageChangeListener != null) {
      mInternalPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
    }
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramInt1 != paramInt3)
    {
      completeScroll();
      scrollTo(getDestScrollX(mCurItem), getScrollY());
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!mEnabled) {
      if (thisTouchAllowed(paramMotionEvent)) {}
    }
    while ((!mIsBeingDragged) && (!thisTouchAllowed(paramMotionEvent)))
    {
      return false;
      return true;
    }
    if (mViewBehind.IsBeingDragged())
    {
      mIsBeingDragged = false;
      return false;
    }
    int i = paramMotionEvent.getAction();
    if (mVelocityTracker == null) {
      mVelocityTracker = VelocityTracker.obtain();
    }
    mVelocityTracker.addMovement(paramMotionEvent);
    float f1;
    float f3;
    switch (i & 0xFF)
    {
    case 4: 
    default: 
    case 0: 
    case 2: 
      do
      {
        do
        {
          do
          {
            do
            {
              for (;;)
              {
                return true;
                completeScroll();
                f1 = paramMotionEvent.getX();
                mInitialMotionX = f1;
                mLastMotionX = f1;
                mActivePointerId = paramMotionEvent.getPointerId(0);
                mPlusVelocitys = 0;
                mVelocityCount = 0;
              }
              if (mIsBeingDragged) {
                break;
              }
            } while (mActivePointerId == -1);
            i = getPointerIndex(paramMotionEvent, mActivePointerId);
          } while (i == -1);
          f1 = paramMotionEvent.getX(i);
          f2 = f1 - mLastMotionX;
          f3 = Math.abs(f2);
          float f4 = Math.abs(paramMotionEvent.getY(i) - mLastMotionY);
          if (((f3 <= mTouchSlop) && ((!mQuickReturn) || (f3 <= mTouchSlop / 4))) || (f3 <= f4) || (!thisSlideAllowed(f2))) {
            break;
          }
          startDrag();
          mLastMotionX = f1;
          setScrollingCacheEnabled(true);
        } while (!mIsBeingDragged);
        i = getPointerIndex(paramMotionEvent, mActivePointerId);
      } while (mActivePointerId == -1);
      f1 = paramMotionEvent.getX(i);
      float f2 = mLastMotionX;
      mLastMotionX = f1;
      f1 = getScrollX() + (f2 - f1);
      f2 = getLeftBound();
      f3 = getRightBound();
      if (f1 < f2) {
        f1 = f2;
      }
      break;
    }
    for (;;)
    {
      mLastMotionX += f1 - (int)f1;
      scrollTo((int)f1, getScrollY());
      pageScrolled((int)f1);
      break;
      if (f1 > f3)
      {
        f1 = f3;
        continue;
        mPlusVelocitys = 0;
        mVelocityCount = 0;
        if (mIsBeingDragged)
        {
          VelocityTracker localVelocityTracker = mVelocityTracker;
          localVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
          i = (int)localVelocityTracker.getXVelocity(mActivePointerId);
          f1 = (getScrollX() - getDestScrollX(mCurItem)) / getBehindWidth();
          int j = getPointerIndex(paramMotionEvent, mActivePointerId);
          if (mActivePointerId != -1) {
            setCurrentItemInternal(determineTargetPage(f1, i, (int)(paramMotionEvent.getX(j) - mInitialMotionX)), true, true, i);
          }
          for (;;)
          {
            mActivePointerId = -1;
            endDrag();
            break;
            setCurrentItemInternal(mCurItem, true, true, i);
          }
        }
        if ((!mQuickReturn) || (!mViewBehind.menuTouchInQuickReturn(mContent, mCurItem, paramMotionEvent.getX() + mScrollX))) {
          break;
        }
        setCurrentItem(1);
        endDrag();
        break;
        if (!mIsBeingDragged) {
          break;
        }
        setCurrentItemInternal(mCurItem, true, true);
        mActivePointerId = -1;
        endDrag();
        break;
        i = paramMotionEvent.getActionIndex();
        mLastMotionX = paramMotionEvent.getX(i);
        mActivePointerId = paramMotionEvent.getPointerId(i);
        break;
        onSecondaryPointerUp(paramMotionEvent);
        i = getPointerIndex(paramMotionEvent, mActivePointerId);
        if (mActivePointerId == -1) {
          break;
        }
        mLastMotionX = paramMotionEvent.getX(i);
        break;
      }
    }
  }
  
  boolean pageLeft()
  {
    if (mCurItem > 0)
    {
      setCurrentItem(mCurItem - 1, true);
      return true;
    }
    return false;
  }
  
  boolean pageRight()
  {
    if (mCurItem < 1)
    {
      setCurrentItem(mCurItem + 1, true);
      return true;
    }
    return false;
  }
  
  public void pageScrolled(int paramInt)
  {
    int i = getWidth();
    int j = paramInt / i;
    paramInt %= i;
    onPageScrolled(j, paramInt / i, paramInt);
  }
  
  public void removeIgnoredView(View paramView)
  {
    mIgnoredViews.remove(paramView);
  }
  
  public void scrollTo(int paramInt1, int paramInt2)
  {
    super.scrollTo(paramInt1, paramInt2);
    mScrollX = paramInt1;
    mViewBehind.scrollBehindTo(mContent, paramInt1, paramInt2);
  }
  
  public void setAboveOffsetLeft(int paramInt)
  {
    CustomViewBehind localCustomViewBehind = mViewBehind;
    if (paramInt > 0) {}
    for (boolean bool = true;; bool = false)
    {
      localCustomViewBehind.setVisibleAlways(bool);
      mViewOffsetLeft = paramInt;
      requestLayout();
      return;
    }
  }
  
  public void setContent(View paramView)
  {
    if (mContent != null) {
      removeView(mContent);
    }
    mContent = paramView;
    addView(mContent, -1, -1);
    setDefaultBackgroud(mContent, getWindowBackgroud());
  }
  
  public void setCriticalVelocity(int paramInt)
  {
    mCriticalVelocity = paramInt;
  }
  
  public void setCurrentItem(int paramInt)
  {
    setCurrentItemInternal(paramInt, true, false);
  }
  
  public void setCurrentItem(int paramInt, boolean paramBoolean)
  {
    setCurrentItemInternal(paramInt, paramBoolean, false);
  }
  
  public void setCurrentItem(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    setCurrentItemInternal(paramInt, paramBoolean1, paramBoolean2);
  }
  
  void setCurrentItemInternal(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    setCurrentItemInternal(paramInt, paramBoolean1, paramBoolean2, 0);
  }
  
  void setCurrentItemInternal(int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2)
  {
    if ((!paramBoolean2) && (mCurItem == paramInt1))
    {
      setScrollingCacheEnabled(false);
      return;
    }
    int i = mViewBehind.getMenuPage(paramInt1);
    if (mCurItem != i) {}
    int j;
    for (paramInt1 = 1;; paramInt1 = 0)
    {
      mCurItem = i;
      j = getDestScrollX(mCurItem);
      if ((paramInt1 != 0) && (mOnPageChangeListener != null)) {
        mOnPageChangeListener.onPageSelected(i);
      }
      if ((paramInt1 != 0) && (mInternalPageChangeListener != null)) {
        mInternalPageChangeListener.onPageSelected(i);
      }
      if (!paramBoolean1) {
        break;
      }
      smoothScrollTo(j, 0, paramInt2);
      return;
    }
    completeScroll();
    scrollTo(j, 0);
  }
  
  public void setCustomViewBehind(CustomViewBehind paramCustomViewBehind)
  {
    mViewBehind = paramCustomViewBehind;
  }
  
  OnPageChangeListener setInternalPageChangeListener(OnPageChangeListener paramOnPageChangeListener)
  {
    OnPageChangeListener localOnPageChangeListener = mInternalPageChangeListener;
    mInternalPageChangeListener = paramOnPageChangeListener;
    return localOnPageChangeListener;
  }
  
  public void setOnClosedListener(SlidingMenu.OnClosedListener paramOnClosedListener)
  {
    mClosedListener = paramOnClosedListener;
  }
  
  public void setOnOpenedListener(SlidingMenu.OnOpenedListener paramOnOpenedListener)
  {
    mOpenedListener = paramOnOpenedListener;
  }
  
  public void setOnPageChangeListener(OnPageChangeListener paramOnPageChangeListener)
  {
    mOnPageChangeListener = paramOnPageChangeListener;
  }
  
  public void setSlidingEnabled(boolean paramBoolean)
  {
    mEnabled = paramBoolean;
  }
  
  public void setTouchMode(int paramInt)
  {
    mTouchMode = paramInt;
  }
  
  void smoothScrollTo(int paramInt1, int paramInt2)
  {
    smoothScrollTo(paramInt1, paramInt2, 0);
  }
  
  void smoothScrollTo(int paramInt1, int paramInt2, int paramInt3)
  {
    if (getChildCount() == 0) {
      setScrollingCacheEnabled(false);
    }
    int i;
    do
    {
      do
      {
        return;
        paramInt3 = getScrollX();
        i = getScrollY();
        paramInt1 -= paramInt3;
        paramInt2 -= i;
        if ((paramInt1 != 0) || (paramInt2 != 0)) {
          break label86;
        }
        completeScroll();
        if (!isMenuOpen()) {
          break;
        }
      } while (mOpenedListener == null);
      mOpenedListener.onOpened();
      return;
    } while (mClosedListener == null);
    mClosedListener.onClosed();
    return;
    label86:
    setScrollingCacheEnabled(true);
    mScrolling = true;
    int j = getBehindWidth();
    j = Math.min((int)((Math.abs(paramInt1) / j + 2.0F) * 100.0F), 300);
    if (mViewBehind.getVisibility() != 0) {
      mViewBehind.setVisibility(0);
    }
    mScroller.startScroll(paramInt3, i, paramInt1, paramInt2, j);
    invalidate();
  }
  
  public boolean thisSlideAllowed(float paramFloat)
  {
    if (isMenuOpen()) {
      return mViewBehind.menuOpenSlideAllowed(paramFloat);
    }
    return mViewBehind.menuClosedSlideAllowed(paramFloat);
  }
  
  public static abstract interface OnPageChangeListener
  {
    public abstract void onPageScrolled(int paramInt1, float paramFloat, int paramInt2);
    
    public abstract void onPageSelected(int paramInt);
  }
  
  public static class SimpleOnPageChangeListener
    implements CustomViewAbove.OnPageChangeListener
  {
    public void onPageScrollStateChanged(int paramInt) {}
    
    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {}
    
    public void onPageSelected(int paramInt) {}
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CustomViewAbove
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */