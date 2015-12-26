package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ViewPager
  extends ViewGroup
{
  private static final int CLOSE_ENOUGH = 2;
  private static final Comparator<ItemInfo> COMPARATOR;
  private static final int COVER_ALPHA_BASE = 102;
  private static final boolean DEBUG = false;
  private static final int DEFAULT_GUTTER_SIZE = 16;
  private static final int DEFAULT_OFFSCREEN_PAGES = 1;
  private static final int DRAW_ORDER_DEFAULT = 0;
  private static final int DRAW_ORDER_FORWARD = 1;
  private static final int DRAW_ORDER_REVERSE = 2;
  private static final int INVALID_POINTER = -1;
  private static final int[] LAYOUT_ATTRS = { 16842931 };
  private static final int MAX_SETTLE_DURATION = 600;
  private static final int MAX_SWITCH_DURATION = 5000;
  private static final int MIN_DISTANCE_FOR_FLING = 25;
  private static final int MIN_FLING_VELOCITY = 400;
  private static final float[] OVERLAY_MODE_INTERPOLATIONS = { 0.0F, 0.0F, 0.003365234F, 0.01357806F, 0.030720964F, 0.05475371F, 0.08548926F, 0.12255032F, 0.16538717F, 0.21324258F, 0.2652047F, 0.32024413F, 0.37725833F, 0.4351431F, 0.49284747F, 0.5494277F, 0.6040792F, 0.6561299F, 0.7050707F, 0.7505254F, 0.7922336F, 0.8300537F, 0.86390066F, 0.8937803F, 0.91972214F, 0.94181687F, 0.9601534F, 0.974861F, 0.98606336F, 0.99389625F, 0.99851006F, 1.0F };
  public static final int SCROLL_STATE_DRAGGING = 1;
  public static final int SCROLL_STATE_IDLE = 0;
  public static final int SCROLL_STATE_SETTLING = 2;
  private static final String TAG = "ViewPager";
  private static final boolean USE_CACHE = false;
  private static final Interpolator overlayModeInterpolator = new ViewPager.4();
  private static final Interpolator sAutoScrollInterpolator = PathInterpolatorCompat.create(0.33F, 0.0F, 0.2F, 1.0F);
  private static final Interpolator sInterpolator;
  private static final ViewPositionComparator sPositionComparator;
  private static final ViewReversePositionComparator sReversePositionComparator;
  private int mActivePointerId = -1;
  private PagerAdapter mAdapter;
  private OnAdapterChangeListener mAdapterChangeListener;
  private int mBottomPageBounds;
  private boolean mCalledSuper;
  private int mChildHeightMeasureSpec;
  private int mChildWidthMeasureSpec;
  private int mCloseEnough;
  private Context mContext;
  private int mCoverAlpha;
  private Drawable mCoverDrawable;
  private int mCurIndex;
  private Interpolator mCurInterpolator;
  private int mCurItem;
  private int mDecorChildCount;
  private int mDefaultGutterSize;
  private int mDrawingOrder;
  private LinkedList<View> mDrawingOrderedChildren;
  private final Runnable mEndScrollRunnable = new ViewPager.3(this);
  private int mExpectedAdapterCount;
  private long mFakeDragBeginTime;
  private boolean mFakeDragging;
  private boolean mFirstLayout = true;
  private float mFirstOffset = -3.4028235E38F;
  private int mFlingDistance;
  private volatile FlipMode mFlipMode = FlipMode.FLIP_MODE_DEFAULT;
  private int mGutterSize;
  private boolean mIgnoreGutter;
  private boolean mInLayout;
  private float mInitialMotionX;
  private float mInitialMotionY;
  private OnPageChangeListener mInternalPageChangeListener;
  private boolean mIsBeingDragged;
  private boolean mIsUnableToDrag;
  private final ArrayList<ItemInfo> mItems = new ArrayList();
  private Interpolator mLastInterpolator;
  private float mLastMotionX;
  private float mLastMotionY;
  private float mLastOffset = Float.MAX_VALUE;
  private EdgeEffectCompat mLeftEdge;
  private Drawable mMarginDrawable;
  private int mMaximumVelocity;
  private int mMinimumVelocity;
  private boolean mNeedCalculatePageOffsets = false;
  private volatile boolean mNeedDrawShadow;
  private volatile boolean mNeedInitShadow;
  private PagerObserver mObserver;
  private int mOffscreenPageLimit = 1;
  private OnPageChangeListener mOnPageChangeListener;
  private List<OnPageChangeListener> mOnPageChangeListeners;
  private int mPageMargin;
  private PageTransformer mPageTransformer;
  private boolean mPopulatePending;
  private Parcelable mRestoredAdapterState = null;
  private ClassLoader mRestoredClassLoader = null;
  private int mRestoredCurItem = -1;
  private EdgeEffectCompat mRightEdge;
  private int mScrollState = 0;
  private Scroller mScroller;
  private boolean mScrollingCacheEnabled;
  private Method mSetChildrenDrawingOrderEnabled;
  private Drawable mShadowDrawable;
  private int mShdH;
  private int mShdL;
  private int mShdT;
  private Rect mSpecRect;
  private int mSpecTab;
  private final ItemInfo mTempItem = new ItemInfo();
  private final Rect mTempRect = new Rect();
  private int mTopIndex;
  private int mTopPageBounds;
  private int mTouchSlop;
  private int mTouchSlopAdjust = -1;
  private VelocityTracker mVelocityTracker;
  
  static
  {
    COMPARATOR = new ViewPager.1();
    sInterpolator = new ViewPager.2();
    sPositionComparator = new ViewPositionComparator();
    sReversePositionComparator = new ViewReversePositionComparator();
  }
  
  public ViewPager(Context paramContext)
  {
    super(paramContext);
    mContext = paramContext;
    initViewPager();
  }
  
  public ViewPager(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    mContext = paramContext;
    initViewPager();
  }
  
  private void calculatePageOffsets(ItemInfo paramItemInfo1, int paramInt, ItemInfo paramItemInfo2)
  {
    int m = mAdapter.getCount();
    int i = getClientWidth();
    float f2;
    if (i > 0)
    {
      f2 = mPageMargin / i;
      if (paramItemInfo2 == null) {
        break label409;
      }
      i = position;
      if (i < position)
      {
        f1 = offset + widthFactor + f2;
        i += 1;
        j = 0;
      }
    }
    else
    {
      for (;;)
      {
        if ((i > position) || (j >= mItems.size())) {
          break label409;
        }
        for (paramItemInfo2 = (ItemInfo)mItems.get(j);; paramItemInfo2 = (ItemInfo)mItems.get(j))
        {
          k = i;
          f3 = f1;
          if (i <= position) {
            break;
          }
          k = i;
          f3 = f1;
          if (j >= mItems.size() - 1) {
            break;
          }
          j += 1;
        }
        f2 = 0.0F;
        break;
        while (k < position)
        {
          f3 += mAdapter.getPageWidth(k) + f2;
          k += 1;
        }
        offset = f3;
        f1 = f3 + (widthFactor + f2);
        i = k + 1;
      }
    }
    if (i > position)
    {
      j = mItems.size() - 1;
      f1 = offset;
      i -= 1;
      while ((i >= position) && (j >= 0))
      {
        for (paramItemInfo2 = (ItemInfo)mItems.get(j);; paramItemInfo2 = (ItemInfo)mItems.get(j))
        {
          k = i;
          f3 = f1;
          if (i >= position) {
            break;
          }
          k = i;
          f3 = f1;
          if (j <= 0) {
            break;
          }
          j -= 1;
        }
        while (k > position)
        {
          f3 -= mAdapter.getPageWidth(k) + f2;
          k -= 1;
        }
        f1 = f3 - (widthFactor + f2);
        offset = f1;
        i = k - 1;
      }
    }
    label409:
    int k = mItems.size();
    float f3 = offset;
    i = position - 1;
    if (position == 0)
    {
      f1 = offset;
      mFirstOffset = f1;
      if (position != m - 1) {
        break label551;
      }
      f1 = offset + widthFactor - 1.0F;
      label475:
      mLastOffset = f1;
      j = paramInt - 1;
      f1 = f3;
    }
    for (;;)
    {
      if (j < 0) {
        break label605;
      }
      paramItemInfo2 = (ItemInfo)mItems.get(j);
      for (;;)
      {
        if (i > position)
        {
          f1 -= mAdapter.getPageWidth(i) + f2;
          i -= 1;
          continue;
          f1 = -3.4028235E38F;
          break;
          label551:
          f1 = Float.MAX_VALUE;
          break label475;
        }
      }
      f1 -= widthFactor + f2;
      offset = f1;
      if (position == 0) {
        mFirstOffset = f1;
      }
      i -= 1;
      j -= 1;
    }
    label605:
    float f1 = offset + widthFactor + f2;
    int j = position + 1;
    i = paramInt + 1;
    paramInt = j;
    while (i < k)
    {
      paramItemInfo1 = (ItemInfo)mItems.get(i);
      while (paramInt < position)
      {
        f1 = mAdapter.getPageWidth(paramInt) + f2 + f1;
        paramInt += 1;
      }
      if (position == m - 1) {
        mLastOffset = (widthFactor + f1 - 1.0F);
      }
      offset = f1;
      f1 += widthFactor + f2;
      paramInt += 1;
      i += 1;
    }
    mNeedCalculatePageOffsets = false;
  }
  
  private void completeScroll(boolean paramBoolean)
  {
    if (mScrollState == 2) {}
    int j;
    for (int i = 1;; i = 0)
    {
      if (i != 0)
      {
        setScrollingCacheEnabled(false);
        mScroller.abortAnimation();
        j = getScrollX();
        k = getScrollY();
        int m = mScroller.getCurrX();
        int n = mScroller.getCurrY();
        if ((j != m) || (k != n))
        {
          scrollTo(m, n);
          if (m != j) {
            pageScrolled(m);
          }
        }
      }
      mPopulatePending = false;
      int k = 0;
      j = i;
      i = k;
      while (i < mItems.size())
      {
        ItemInfo localItemInfo = (ItemInfo)mItems.get(i);
        if (scrolling)
        {
          scrolling = false;
          j = 1;
        }
        i += 1;
      }
    }
    if (j != 0)
    {
      if (paramBoolean) {
        ViewCompat.postOnAnimation(this, mEndScrollRunnable);
      }
    }
    else {
      return;
    }
    mEndScrollRunnable.run();
  }
  
  private int determineTargetPage(int paramInt1, float paramFloat, int paramInt2, int paramInt3)
  {
    if ((Math.abs(paramInt3) > mFlingDistance) && (Math.abs(paramInt2) > mMinimumVelocity))
    {
      if (paramInt2 > 0) {}
      for (;;)
      {
        paramInt2 = paramInt1;
        if (mItems.size() > 0)
        {
          ItemInfo localItemInfo1 = (ItemInfo)mItems.get(0);
          ItemInfo localItemInfo2 = (ItemInfo)mItems.get(mItems.size() - 1);
          paramInt2 = Math.max(position, Math.min(paramInt1, position));
        }
        return paramInt2;
        paramInt1 += 1;
      }
    }
    if (paramInt1 >= mCurItem) {}
    for (float f = 0.4F;; f = 0.6F)
    {
      paramInt1 = (int)(f + (paramInt1 + paramFloat));
      break;
    }
  }
  
  private void dispatchOnPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
    if (mOnPageChangeListener != null) {
      mOnPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
    }
    if (mOnPageChangeListeners != null)
    {
      int j = mOnPageChangeListeners.size();
      int i = 0;
      while (i < j)
      {
        OnPageChangeListener localOnPageChangeListener = (OnPageChangeListener)mOnPageChangeListeners.get(i);
        if (localOnPageChangeListener != null) {
          localOnPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
        }
        i += 1;
      }
    }
    if (mInternalPageChangeListener != null) {
      mInternalPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
    }
  }
  
  private void dispatchOnPageSelected(int paramInt)
  {
    if (mOnPageChangeListener != null) {
      mOnPageChangeListener.onPageSelected(paramInt);
    }
    if (mOnPageChangeListeners != null)
    {
      int j = mOnPageChangeListeners.size();
      int i = 0;
      while (i < j)
      {
        OnPageChangeListener localOnPageChangeListener = (OnPageChangeListener)mOnPageChangeListeners.get(i);
        if (localOnPageChangeListener != null) {
          localOnPageChangeListener.onPageSelected(paramInt);
        }
        i += 1;
      }
    }
    if (mInternalPageChangeListener != null) {
      mInternalPageChangeListener.onPageSelected(paramInt);
    }
  }
  
  private void dispatchOnScrollStateChanged(int paramInt)
  {
    if (mOnPageChangeListener != null) {
      mOnPageChangeListener.onPageScrollStateChanged(paramInt);
    }
    if (mOnPageChangeListeners != null)
    {
      int j = mOnPageChangeListeners.size();
      int i = 0;
      while (i < j)
      {
        OnPageChangeListener localOnPageChangeListener = (OnPageChangeListener)mOnPageChangeListeners.get(i);
        if (localOnPageChangeListener != null) {
          localOnPageChangeListener.onPageScrollStateChanged(paramInt);
        }
        i += 1;
      }
    }
    if (mInternalPageChangeListener != null) {
      mInternalPageChangeListener.onPageScrollStateChanged(paramInt);
    }
  }
  
  private void enableLayers(boolean paramBoolean)
  {
    int k = getChildCount();
    int i = 0;
    if (i < k)
    {
      if (paramBoolean) {}
      for (int j = 2;; j = 0)
      {
        ViewCompat.setLayerType(getChildAt(i), j, null);
        i += 1;
        break;
      }
    }
  }
  
  private void endDrag()
  {
    mIsBeingDragged = false;
    mIsUnableToDrag = false;
    if (mVelocityTracker != null)
    {
      mVelocityTracker.recycle();
      mVelocityTracker = null;
    }
  }
  
  private Rect getChildRectInPagerCoordinates(Rect paramRect, View paramView)
  {
    if (paramRect == null) {
      paramRect = new Rect();
    }
    for (;;)
    {
      if (paramView == null)
      {
        paramRect.set(0, 0, 0, 0);
        return paramRect;
      }
      left = paramView.getLeft();
      right = paramView.getRight();
      top = paramView.getTop();
      bottom = paramView.getBottom();
      for (paramView = paramView.getParent(); ((paramView instanceof ViewGroup)) && (paramView != this); paramView = paramView.getParent())
      {
        paramView = (ViewGroup)paramView;
        left += paramView.getLeft();
        right += paramView.getRight();
        top += paramView.getTop();
        bottom += paramView.getBottom();
      }
      return paramRect;
    }
  }
  
  private int getClientWidth()
  {
    return getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
  }
  
  private ItemInfo infoForCurrentScrollPosition()
  {
    int i = getClientWidth();
    float f1;
    float f2;
    label36:
    float f4;
    float f3;
    int k;
    int j;
    Object localObject1;
    label53:
    Object localObject2;
    ItemInfo localItemInfo;
    if (i > 0)
    {
      f1 = getScrollX() / i;
      if (i <= 0) {
        break label214;
      }
      f2 = mPageMargin / i;
      f4 = 0.0F;
      f3 = 0.0F;
      k = -1;
      i = 0;
      j = 1;
      localObject1 = null;
      localObject2 = localObject1;
      if (i < mItems.size())
      {
        localItemInfo = (ItemInfo)mItems.get(i);
        if ((j != 0) || (position == k + 1)) {
          break label249;
        }
        localItemInfo = mTempItem;
        offset = (f4 + f3 + f2);
        position = (k + 1);
        widthFactor = mAdapter.getPageWidth(position);
        i -= 1;
      }
    }
    label214:
    label219:
    label249:
    for (;;)
    {
      f3 = offset;
      f4 = widthFactor;
      if (j == 0)
      {
        localObject2 = localObject1;
        if (f1 < f3) {}
      }
      else
      {
        if ((f1 >= f4 + f3 + f2) && (i != mItems.size() - 1)) {
          break label219;
        }
        localObject2 = localItemInfo;
      }
      return (ItemInfo)localObject2;
      f1 = 0.0F;
      break;
      f2 = 0.0F;
      break label36;
      k = position;
      f4 = widthFactor;
      j = 0;
      i += 1;
      localObject1 = localItemInfo;
      break label53;
    }
  }
  
  private void initItemIndex()
  {
    int j = getClientWidth();
    int k = getPaddingLeft();
    int m = getScrollX();
    int i = 0;
    while (i < mItems.size())
    {
      ItemInfo localItemInfo = (ItemInfo)mItems.get(i);
      index = i;
      if ((int)(j * offset) + k == m) {
        mTopIndex = i;
      }
      if (position == mCurItem) {
        mCurIndex = i;
      }
      i += 1;
    }
  }
  
  private boolean isGutterDrag(float paramFloat1, float paramFloat2)
  {
    return ((paramFloat1 < mGutterSize) && (paramFloat2 > 0.0F)) || ((paramFloat1 > getWidth() - mGutterSize) && (paramFloat2 < 0.0F));
  }
  
  private boolean isShadowPrepared()
  {
    return setupShadow();
  }
  
  private void onSecondaryPointerUp(MotionEvent paramMotionEvent)
  {
    int i = MotionEventCompat.getActionIndex(paramMotionEvent);
    if (MotionEventCompat.getPointerId(paramMotionEvent, i) == mActivePointerId) {
      if (i != 0) {
        break label56;
      }
    }
    label56:
    for (i = 1;; i = 0)
    {
      mLastMotionX = MotionEventCompat.getX(paramMotionEvent, i);
      mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, i);
      if (mVelocityTracker != null) {
        mVelocityTracker.clear();
      }
      return;
    }
  }
  
  private boolean pageScrolled(int paramInt)
  {
    boolean bool = false;
    if (mItems.size() == 0)
    {
      mCalledSuper = false;
      onPageScrolled(0, 0.0F, 0);
      if (!mCalledSuper) {
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
      }
    }
    else
    {
      ItemInfo localItemInfo = infoForCurrentScrollPosition();
      int j = getClientWidth();
      int k = mPageMargin;
      float f = mPageMargin / j;
      int i = position;
      f = (paramInt / j - offset) / (widthFactor + f);
      paramInt = (int)((k + j) * f);
      mCalledSuper = false;
      onPageScrolled(i, f, paramInt);
      if (!mCalledSuper) {
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
      }
      bool = true;
    }
    return bool;
  }
  
  private boolean performDrag(float paramFloat)
  {
    int j = 1;
    boolean bool2 = false;
    boolean bool1 = false;
    float f1 = mLastMotionX;
    mLastMotionX = paramFloat;
    float f2 = getScrollX() + (f1 - paramFloat);
    int k = getClientWidth();
    paramFloat = k * mFirstOffset;
    f1 = k;
    float f3 = mLastOffset;
    ItemInfo localItemInfo1 = (ItemInfo)mItems.get(0);
    ItemInfo localItemInfo2 = (ItemInfo)mItems.get(mItems.size() - 1);
    if (position != 0) {
      paramFloat = offset * k;
    }
    for (int i = 0;; i = 1)
    {
      if (position != mAdapter.getCount() - 1)
      {
        f1 = offset * k;
        j = 0;
      }
      for (;;)
      {
        if (f2 < paramFloat)
        {
          f1 = paramFloat;
          if (i != 0)
          {
            bool1 = mLeftEdge.onPull(Math.abs(paramFloat - f2) / k);
            f1 = paramFloat;
          }
        }
        for (;;)
        {
          mLastMotionX += f1 - (int)f1;
          if (FlipMode.FLIP_MODE_OVERLAY == mFlipMode) {
            scrollSidePage((int)f1);
          }
          scrollTo((int)f1, getScrollY());
          pageScrolled((int)f1);
          return bool1;
          if (f2 > f1)
          {
            bool1 = bool2;
            if (j != 0) {
              bool1 = mRightEdge.onPull(Math.abs(f2 - f1) / k);
            }
          }
          else
          {
            f1 = f2;
          }
        }
        f1 *= f3;
      }
    }
  }
  
  private boolean pointInRect(MotionEvent paramMotionEvent, Rect paramRect)
  {
    boolean bool2 = false;
    float f1 = paramMotionEvent.getRawX();
    float f2 = paramMotionEvent.getRawY();
    Log.d("ViewPager", "pointInRect x = " + f1 + ", y = " + f2 + ", rect = " + paramRect);
    boolean bool1 = bool2;
    if (paramRect != null)
    {
      bool1 = bool2;
      if (mCurItem == mSpecTab)
      {
        bool1 = bool2;
        if (f1 >= left)
        {
          bool1 = bool2;
          if (f1 <= right)
          {
            bool1 = bool2;
            if (f2 >= top)
            {
              bool1 = bool2;
              if (f2 <= bottom) {
                bool1 = true;
              }
            }
          }
        }
      }
    }
    return bool1;
  }
  
  private void recomputeScrollPosition(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((paramInt2 > 0) && (!mItems.isEmpty()))
    {
      int i = getPaddingLeft();
      int j = getPaddingRight();
      int k = getPaddingLeft();
      int m = getPaddingRight();
      f = getScrollX() / (paramInt2 - k - m + paramInt4);
      paramInt2 = (int)((paramInt1 - i - j + paramInt3) * f);
      scrollTo(paramInt2, getScrollY());
      paramInt3 = mScroller.getDuration();
      paramInt4 = mScroller.timePassed();
      localItemInfo = infoForPosition(mCurItem);
      mScroller.startScroll(paramInt2, 0, (int)(offset * paramInt1 - paramInt2), 0, paramInt3 - paramInt4);
      return;
    }
    ItemInfo localItemInfo = infoForPosition(mCurItem);
    if (localItemInfo != null) {}
    for (float f = Math.min(offset, mLastOffset);; f = 0.0F)
    {
      paramInt1 = (int)(f * (paramInt1 - getPaddingLeft() - getPaddingRight()));
      if (paramInt1 == getScrollX()) {
        break;
      }
      completeScroll(false);
      scrollTo(paramInt1, getScrollY());
      return;
    }
  }
  
  private void removeNonDecorViews()
  {
    int j;
    for (int i = 0; i < getChildCount(); i = j + 1)
    {
      j = i;
      if (!getChildAtgetLayoutParamsisDecor)
      {
        removeViewAt(i);
        j = i - 1;
      }
    }
  }
  
  private void requestParentDisallowInterceptTouchEvent(boolean paramBoolean)
  {
    ViewParent localViewParent = getParent();
    if (localViewParent != null) {
      localViewParent.requestDisallowInterceptTouchEvent(paramBoolean);
    }
  }
  
  private void scrollSidePage(int paramInt)
  {
    if (mItems.size() == 0) {
      return;
    }
    int i4 = getPaddingLeft();
    int i3 = getClientWidth();
    int i;
    label47:
    int j;
    View localView;
    Object localObject1;
    label107:
    Object localObject2;
    label135:
    Object localObject4;
    label147:
    Object localObject3;
    label159:
    int k;
    if (mTopIndex >= mItems.size())
    {
      i = mItems.size() - 1;
      j = (int)(mItems.get(i)).offset * i3) + i4;
      localView = mItems.get(i)).view;
      if (i - 1 < 0) {
        break label682;
      }
      localObject1 = (ItemInfo)mItems.get(i - 1);
      if (i + 1 >= mItems.size()) {
        break label688;
      }
      localObject2 = (ItemInfo)mItems.get(i + 1);
      if (localObject1 == null) {
        break label694;
      }
      localObject4 = view;
      if (localObject2 == null) {
        break label700;
      }
      localObject3 = view;
      k = paramInt - j;
      if (k < mPageMargin / 2 + i3) {
        break label706;
      }
      i += 1;
    }
    label182:
    label188:
    label355:
    label383:
    label395:
    label407:
    label491:
    label682:
    label688:
    label694:
    label700:
    label706:
    label755:
    label761:
    label767:
    label773:
    label910:
    label916:
    label944:
    label946:
    for (;;)
    {
      Object localObject5;
      Object localObject6;
      if (i < 0)
      {
        i = 0;
        if (mTopIndex == i) {
          break label916;
        }
        mTopIndex = i;
        j = (int)(mItems.get(i)).offset * i3) + i4;
        localView = mItems.get(i)).view;
        if (localView != null) {
          localView.offsetLeftAndRight(j - localView.getLeft());
        }
        if ((i + 2 < mItems.size()) && (localObject3 != null)) {
          ((View)localObject3).offsetLeftAndRight((int)(i3 * offset) + i4 - ((View)localObject3).getLeft());
        }
        if ((i - 2 >= 0) && (localObject4 != null)) {
          ((View)localObject4).offsetLeftAndRight((int)(i3 * offset) + i4 - ((View)localObject4).getLeft());
        }
        if (i - 1 < 0) {
          break label755;
        }
        localObject1 = (ItemInfo)mItems.get(i - 1);
        if (i + 1 >= mItems.size()) {
          break label761;
        }
        localObject2 = (ItemInfo)mItems.get(i + 1);
        if (localObject1 == null) {
          break label767;
        }
        localObject3 = view;
        if (localObject2 == null) {
          break label773;
        }
        localObject4 = view;
        i = paramInt - j;
        localObject5 = localObject1;
        localObject6 = localObject3;
        localObject1 = localObject2;
        localObject3 = localObject5;
        localObject2 = localObject6;
      }
      for (;;)
      {
        if (localView == null) {
          break label944;
        }
        mShdH = localView.getMeasuredHeight();
        mShdT = localView.getTop();
        if ((mCurIndex >= 0) && (mCurIndex < mItems.size()))
        {
          localObject5 = (ItemInfo)mItems.get(mCurIndex);
          if (localObject5 == null) {
            break label910;
          }
        }
        for (k = (int)(i3 * offset) + i4;; k = 0)
        {
          int m;
          int n;
          if ((localObject1 != null) && (localObject4 != null))
          {
            m = (int)(i3 * offset) + i4;
            n = m - i3 / 2 - mPageMargin / 2 + i / 2;
          }
          for (;;)
          {
            int i2 = 0;
            int i1 = i2;
            if (localObject3 != null)
            {
              i1 = i2;
              if (localObject2 != null) {
                i1 = (int)(i3 * offset) + i4;
              }
            }
            if (((localObject5 != null) && (paramInt == k)) || (paramInt == j))
            {
              mNeedDrawShadow = false;
              if (localObject4 != null) {
                ((View)localObject4).offsetLeftAndRight(m - ((View)localObject4).getLeft());
              }
              if (localObject2 != null) {
                ((View)localObject2).offsetLeftAndRight(i1 - ((View)localObject2).getLeft());
              }
              localView.offsetLeftAndRight(j - localView.getLeft());
              return;
              if (mTopIndex < 0)
              {
                i = 0;
                break label47;
              }
              i = mTopIndex;
              break label47;
              localObject1 = null;
              break label107;
              localObject2 = null;
              break label135;
              localObject4 = null;
              break label147;
              localObject3 = null;
              break label159;
              if (k > -(mPageMargin / 2 + i3)) {
                break label946;
              }
              i -= 1;
              break label182;
              if (i >= mItems.size())
              {
                i = mItems.size() - 1;
                break label188;
              }
              break label188;
              localObject1 = null;
              break label355;
              localObject2 = null;
              break label383;
              localObject3 = null;
              break label395;
              localObject4 = null;
              break label407;
              localObject5 = null;
              break label491;
            }
            if ((i >= 0) && (localObject4 != null))
            {
              mShdL = localView.getRight();
              mCoverAlpha = ((int)(102.0D - i * 1.0D / i3 * 102.0D));
              mNeedDrawShadow = true;
              ((View)localObject4).offsetLeftAndRight(n - ((View)localObject4).getLeft());
              return;
            }
            if ((i >= 0) || (localObject2 == null)) {
              break;
            }
            mShdL = ((View)localObject2).getRight();
            mCoverAlpha = ((int)(-i * 1.0D / i3 * 102.0D));
            mNeedDrawShadow = true;
            localView.offsetLeftAndRight(i / 2 + j - localView.getLeft());
            return;
            n = 0;
            m = 0;
          }
        }
        localObject5 = localObject4;
        localObject6 = localObject1;
        localObject1 = localObject2;
        i = k;
        localObject4 = localObject3;
        localObject2 = localObject5;
        localObject3 = localObject6;
      }
      break;
    }
  }
  
  private void scrollToItem(int paramInt1, boolean paramBoolean1, int paramInt2, boolean paramBoolean2)
  {
    ItemInfo localItemInfo = infoForPosition(paramInt1);
    float f;
    if (localItemInfo != null) {
      f = getClientWidth();
    }
    for (int i = (int)(Math.max(mFirstOffset, Math.min(offset, mLastOffset)) * f);; i = 0)
    {
      if (paramBoolean1)
      {
        if (paramBoolean2) {
          dispatchOnPageSelected(paramInt1);
        }
        smoothScrollTo(i, 0, paramInt2);
        return;
      }
      if (paramBoolean2) {
        dispatchOnPageSelected(paramInt1);
      }
      completeScroll(false);
      scrollTo(i, 0);
      pageScrolled(i);
      return;
    }
  }
  
  private void setScrollState(int paramInt)
  {
    if (mScrollState == paramInt) {
      return;
    }
    mScrollState = paramInt;
    if (mPageTransformer != null) {
      if (paramInt == 0) {
        break label38;
      }
    }
    label38:
    for (boolean bool = true;; bool = false)
    {
      enableLayers(bool);
      dispatchOnScrollStateChanged(paramInt);
      return;
    }
  }
  
  private void setScrollingCacheEnabled(boolean paramBoolean)
  {
    if (mScrollingCacheEnabled != paramBoolean) {
      mScrollingCacheEnabled = paramBoolean;
    }
  }
  
  private boolean setupShadow()
  {
    if (!mNeedInitShadow) {
      if ((mShadowDrawable == null) || (mCoverDrawable == null)) {}
    }
    do
    {
      return true;
      return false;
      mNeedInitShadow = false;
      if (mShadowDrawable != null) {
        mShadowDrawable.setBounds(0, 0, mShadowDrawable.getIntrinsicWidth(), mShdH);
      }
      if (mCoverDrawable != null) {
        mCoverDrawable.setBounds(0, 0, getClientWidth(), mShdH);
      }
    } while ((mShadowDrawable != null) && (mCoverDrawable != null));
    return false;
  }
  
  private void sortChildDrawingOrder()
  {
    int j = 0;
    int i = 0;
    View localView;
    if (FlipMode.FLIP_MODE_OVERLAY == mFlipMode)
    {
      if (mDrawingOrderedChildren == null) {
        mDrawingOrderedChildren = new LinkedList();
      }
      for (;;)
      {
        j = getChildCount();
        while (i < j)
        {
          localView = getChildAt(i);
          mDrawingOrderedChildren.add(localView);
          i += 1;
        }
        mDrawingOrderedChildren.clear();
      }
      Collections.sort(mDrawingOrderedChildren, sReversePositionComparator);
    }
    while (mDrawingOrder == 0) {
      return;
    }
    if (mDrawingOrderedChildren == null) {
      mDrawingOrderedChildren = new LinkedList();
    }
    for (;;)
    {
      int k = getChildCount();
      i = j;
      while (i < k)
      {
        localView = getChildAt(i);
        mDrawingOrderedChildren.add(localView);
        i += 1;
      }
      mDrawingOrderedChildren.clear();
    }
    Collections.sort(mDrawingOrderedChildren, sPositionComparator);
  }
  
  public void addFocusables(ArrayList<View> paramArrayList, int paramInt1, int paramInt2)
  {
    int j = paramArrayList.size();
    int k = getDescendantFocusability();
    if (k != 393216)
    {
      int i = 0;
      while (i < getChildCount())
      {
        View localView = getChildAt(i);
        if (localView.getVisibility() == 0)
        {
          ItemInfo localItemInfo = infoForChild(localView);
          if ((localItemInfo != null) && (position == mCurItem)) {
            localView.addFocusables(paramArrayList, paramInt1, paramInt2);
          }
        }
        i += 1;
      }
    }
    if (((k == 262144) && (j != paramArrayList.size())) || (!isFocusable())) {}
    while ((((paramInt2 & 0x1) == 1) && (isInTouchMode()) && (!isFocusableInTouchMode())) || (paramArrayList == null)) {
      return;
    }
    paramArrayList.add(this);
  }
  
  ItemInfo addNewItem(int paramInt1, int paramInt2)
  {
    ItemInfo localItemInfo = new ItemInfo();
    position = paramInt1;
    object = mAdapter.instantiateItem(this, paramInt1);
    widthFactor = mAdapter.getPageWidth(paramInt1);
    if ((paramInt2 < 0) || (paramInt2 >= mItems.size()))
    {
      index = mItems.size();
      mItems.add(localItemInfo);
      return localItemInfo;
    }
    mItems.add(paramInt2, localItemInfo);
    index = paramInt2;
    return localItemInfo;
  }
  
  public void addOnPageChangeListener(OnPageChangeListener paramOnPageChangeListener)
  {
    if (mOnPageChangeListeners == null) {
      mOnPageChangeListeners = new ArrayList();
    }
    mOnPageChangeListeners.add(paramOnPageChangeListener);
  }
  
  public void addTouchables(ArrayList<View> paramArrayList)
  {
    int i = 0;
    while (i < getChildCount())
    {
      View localView = getChildAt(i);
      if (localView.getVisibility() == 0)
      {
        ItemInfo localItemInfo = infoForChild(localView);
        if ((localItemInfo != null) && (position == mCurItem)) {
          localView.addTouchables(paramArrayList);
        }
      }
      i += 1;
    }
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    if (!checkLayoutParams(paramLayoutParams)) {
      paramLayoutParams = generateLayoutParams(paramLayoutParams);
    }
    for (;;)
    {
      LayoutParams localLayoutParams = (LayoutParams)paramLayoutParams;
      isDecor |= paramView instanceof Decor;
      if (mInLayout)
      {
        if ((localLayoutParams != null) && (isDecor)) {
          throw new IllegalStateException("Cannot add pager decor view during layout");
        }
        needsMeasure = true;
        addViewInLayout(paramView, paramInt, paramLayoutParams);
        return;
      }
      super.addView(paramView, paramInt, paramLayoutParams);
      return;
    }
  }
  
  public boolean arrowScroll(int paramInt)
  {
    View localView = findFocus();
    Object localObject;
    int j;
    boolean bool;
    if (localView == this)
    {
      localObject = null;
      localView = FocusFinder.getInstance().findNextFocus(this, (View)localObject, paramInt);
      if ((localView == null) || (localView == localObject)) {
        break label328;
      }
      if (paramInt != 17) {
        break label265;
      }
      i = getChildRectInPagerCoordinatesmTempRect, localView).left;
      j = getChildRectInPagerCoordinatesmTempRect, (View)localObject).left;
      if ((localObject != null) && (i >= j))
      {
        bool = pageLeft();
        label89:
        if (bool) {
          playSoundEffect(SoundEffectConstants.getContantForFocusDirection(paramInt));
        }
        return bool;
      }
    }
    else
    {
      if (localView == null) {
        break label374;
      }
      localObject = localView.getParent();
      if (!(localObject instanceof ViewGroup)) {
        break label381;
      }
      if (localObject != this) {}
    }
    label265:
    label328:
    label374:
    label381:
    for (int i = 1;; i = 0)
    {
      if (i == 0)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(localView.getClass().getSimpleName());
        localObject = localView.getParent();
        for (;;)
        {
          if ((localObject instanceof ViewGroup))
          {
            localStringBuilder.append(" => ").append(localObject.getClass().getSimpleName());
            localObject = ((ViewParent)localObject).getParent();
            continue;
            localObject = ((ViewParent)localObject).getParent();
            break;
          }
        }
        Log.e("ViewPager", "arrowScroll tried to find focus based on non-child current focused view " + localStringBuilder.toString());
        localObject = null;
        break;
        bool = localView.requestFocus();
        break label89;
        if (paramInt == 66)
        {
          i = getChildRectInPagerCoordinatesmTempRect, localView).left;
          j = getChildRectInPagerCoordinatesmTempRect, (View)localObject).left;
          if ((localObject != null) && (i <= j))
          {
            bool = pageRight();
            break label89;
          }
          bool = localView.requestFocus();
          break label89;
          if ((paramInt == 17) || (paramInt == 1))
          {
            bool = pageLeft();
            break label89;
          }
          if ((paramInt == 66) || (paramInt == 2))
          {
            bool = pageRight();
            break label89;
          }
        }
        bool = false;
        break label89;
      }
      localObject = localView;
      break;
    }
  }
  
  public boolean beginFakeDrag()
  {
    if (mIsBeingDragged) {
      return false;
    }
    mFakeDragging = true;
    setScrollState(1);
    mLastMotionX = 0.0F;
    mInitialMotionX = 0.0F;
    if (mVelocityTracker == null) {
      mVelocityTracker = VelocityTracker.obtain();
    }
    for (;;)
    {
      long l = SystemClock.uptimeMillis();
      MotionEvent localMotionEvent = MotionEvent.obtain(l, l, 0, 0.0F, 0.0F, 0);
      mVelocityTracker.addMovement(localMotionEvent);
      localMotionEvent.recycle();
      mFakeDragBeginTime = l;
      return true;
      mVelocityTracker.clear();
    }
  }
  
  protected boolean canScroll(View paramView, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3)
  {
    int i;
    if ((paramView instanceof ViewGroup))
    {
      ViewGroup localViewGroup = (ViewGroup)paramView;
      int j = paramView.getScrollX();
      int k = paramView.getScrollY();
      i = localViewGroup.getChildCount() - 1;
      if (i >= 0)
      {
        localView = localViewGroup.getChildAt(i);
        if ((paramInt2 + j < localView.getLeft()) || (paramInt2 + j >= localView.getRight()) || (paramInt3 + k < localView.getTop()) || (paramInt3 + k >= localView.getBottom()) || (!canScroll(localView, true, paramInt1, paramInt2 + j - localView.getLeft(), paramInt3 + k - localView.getTop()))) {}
      }
    }
    while ((paramBoolean) && (ViewCompat.canScrollHorizontally(paramView, -paramInt1)))
    {
      View localView;
      return true;
      i -= 1;
      break;
    }
    return false;
  }
  
  public boolean canScrollHorizontally(int paramInt)
  {
    boolean bool2 = true;
    boolean bool1 = true;
    if (mAdapter == null) {}
    int i;
    int j;
    do
    {
      return false;
      i = getClientWidth();
      j = getScrollX();
      if (paramInt < 0)
      {
        if (j > (int)(i * mFirstOffset)) {}
        for (;;)
        {
          return bool1;
          bool1 = false;
        }
      }
    } while (paramInt <= 0);
    if (j < (int)(i * mLastOffset)) {}
    for (bool1 = bool2;; bool1 = false) {
      return bool1;
    }
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return ((paramLayoutParams instanceof LayoutParams)) && (super.checkLayoutParams(paramLayoutParams));
  }
  
  public void clearOnPageChangeListeners()
  {
    if (mOnPageChangeListeners != null) {
      mOnPageChangeListeners.clear();
    }
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
        if (FlipMode.FLIP_MODE_OVERLAY == mFlipMode) {
          scrollSidePage(k);
        }
        scrollTo(k, m);
        if (!pageScrolled(k))
        {
          mScroller.abortAnimation();
          scrollTo(0, m);
        }
      }
      ViewCompat.postInvalidateOnAnimation(this);
      return;
    }
    completeScroll(true);
  }
  
  void dataSetChanged()
  {
    int i2 = mAdapter.getCount();
    mExpectedAdapterCount = i2;
    int i;
    int j;
    int k;
    int n;
    int m;
    label67:
    Object localObject;
    int i1;
    if ((mItems.size() < mOffscreenPageLimit * 2 + 1) && (mItems.size() < i2))
    {
      i = 1;
      j = mCurItem;
      k = 0;
      n = 0;
      m = i;
      i = j;
      j = k;
      k = n;
      if (k >= mItems.size()) {
        break label299;
      }
      localObject = (ItemInfo)mItems.get(k);
      n = mAdapter.getItemPosition(object);
      if (n != -1) {
        break label157;
      }
      n = k;
      i1 = j;
      k = m;
      j = i;
      i = i1;
      m = n;
    }
    for (;;)
    {
      n = k;
      i1 = j;
      k = m + 1;
      j = i;
      i = i1;
      m = n;
      break label67;
      i = 0;
      break;
      label157:
      if (n == -2)
      {
        mItems.remove(k);
        m = k - 1;
        k = j;
        if (j == 0)
        {
          mAdapter.startUpdate(this);
          k = 1;
        }
        mAdapter.destroyItem(this, position, object);
        if (mCurItem == position)
        {
          j = Math.max(0, Math.min(mCurItem, i2 - 1));
          i = k;
          k = 1;
        }
      }
      else
      {
        if (position != n)
        {
          if (position == mCurItem) {
            i = n;
          }
          position = n;
          n = i;
          i1 = 1;
          m = k;
          i = j;
          j = n;
          k = i1;
          continue;
          label299:
          if (j != 0) {
            mAdapter.finishUpdate(this);
          }
          Collections.sort(mItems, COMPARATOR);
          if (FlipMode.FLIP_MODE_OVERLAY == mFlipMode) {
            initItemIndex();
          }
          if (m != 0)
          {
            k = getChildCount();
            j = 0;
            while (j < k)
            {
              localObject = (LayoutParams)getChildAt(j).getLayoutParams();
              if (!isDecor) {
                widthFactor = 0.0F;
              }
              j += 1;
            }
            setCurrentItemInternal(i, false, true);
            requestLayout();
          }
          return;
        }
        n = i;
        i1 = m;
        m = k;
        i = j;
        j = n;
        k = i1;
        continue;
      }
      j = i;
      n = 1;
      i = k;
      k = n;
    }
  }
  
  protected void dispatchDraw(Canvas paramCanvas)
  {
    super.dispatchDraw(paramCanvas);
    if ((FlipMode.FLIP_MODE_OVERLAY == mFlipMode) && (mNeedDrawShadow) && (isShadowPrepared()))
    {
      paramCanvas.save();
      paramCanvas.translate(mShdL, mShdT);
      mShadowDrawable.draw(paramCanvas);
      mCoverDrawable.setAlpha(mCoverAlpha);
      mCoverDrawable.draw(paramCanvas);
      paramCanvas.restore();
    }
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    return (super.dispatchKeyEvent(paramKeyEvent)) || (executeKeyEvent(paramKeyEvent));
  }
  
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramAccessibilityEvent.getEventType() == 4096)
    {
      bool1 = super.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent);
      return bool1;
    }
    int j = getChildCount();
    int i = 0;
    for (;;)
    {
      bool1 = bool2;
      if (i >= j) {
        break;
      }
      View localView = getChildAt(i);
      if (localView.getVisibility() == 0)
      {
        ItemInfo localItemInfo = infoForChild(localView);
        if ((localItemInfo != null) && (position == mCurItem) && (localView.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent))) {
          return true;
        }
      }
      i += 1;
    }
  }
  
  public boolean dispatchStatusBarTap()
  {
    int j = getChildCount();
    int i = 0;
    View localView;
    Object localObject2;
    if (i < j)
    {
      localView = getChildAt(i);
      localObject2 = infoForChild(localView);
      if ((localObject2 == null) || (position != mCurItem)) {}
    }
    for (;;)
    {
      if (localView != null)
      {
        try
        {
          localObject2 = View.class.getDeclaredMethod("dispatchStatusBarTap", new Class[0]);
          ((Method)localObject2).setAccessible(true);
          boolean bool = ((Boolean)((Method)localObject2).invoke(localView, new Object[0])).booleanValue();
          return bool;
        }
        catch (Exception localException)
        {
          return false;
        }
        i += 1;
        break;
      }
      return false;
      Object localObject1 = null;
    }
  }
  
  float distanceInfluenceForSnapDuration(float paramFloat)
  {
    return (float)Math.sin((float)((paramFloat - 0.5F) * 0.4712389167638204D));
  }
  
  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    Drawable localDrawable = mMarginDrawable;
    if ((localDrawable != null) && (localDrawable.isStateful())) {
      localDrawable.setState(getDrawableState());
    }
  }
  
  public void endFakeDrag()
  {
    if (!mFakeDragging) {
      throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }
    Object localObject = mVelocityTracker;
    ((VelocityTracker)localObject).computeCurrentVelocity(1000, mMaximumVelocity);
    int i = (int)VelocityTrackerCompat.getXVelocity((VelocityTracker)localObject, mActivePointerId);
    mPopulatePending = true;
    int j = getClientWidth();
    int k = getScrollX();
    localObject = infoForCurrentScrollPosition();
    setCurrentItemInternal(determineTargetPage(position, (k / j - offset) / widthFactor, i, (int)(mLastMotionX - mInitialMotionX)), true, true, i);
    endDrag();
    mFakeDragging = false;
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
      if (KeyEventCompat.hasNoModifiers(paramKeyEvent)) {
        return arrowScroll(2);
      }
    } while (!KeyEventCompat.hasModifiers(paramKeyEvent, 1));
    return arrowScroll(1);
  }
  
  public void fakeDragBy(float paramFloat)
  {
    if (!mFakeDragging) {
      throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }
    mLastMotionX += paramFloat;
    float f2 = getScrollX() - paramFloat;
    int i = getClientWidth();
    paramFloat = i;
    float f4 = mFirstOffset;
    float f1 = i;
    float f3 = mLastOffset;
    Object localObject = (ItemInfo)mItems.get(0);
    ItemInfo localItemInfo = (ItemInfo)mItems.get(mItems.size() - 1);
    if (position != 0) {}
    for (paramFloat = offset * i;; paramFloat *= f4)
    {
      if (position != mAdapter.getCount() - 1) {}
      for (f1 = offset * i;; f1 *= f3)
      {
        if (f2 < paramFloat) {}
        for (;;)
        {
          mLastMotionX += paramFloat - (int)paramFloat;
          scrollTo((int)paramFloat, getScrollY());
          pageScrolled((int)paramFloat);
          long l = SystemClock.uptimeMillis();
          localObject = MotionEvent.obtain(mFakeDragBeginTime, l, 2, mLastMotionX, 0.0F, 0);
          mVelocityTracker.addMovement((MotionEvent)localObject);
          ((MotionEvent)localObject).recycle();
          return;
          if (f2 > f1) {
            paramFloat = f1;
          } else {
            paramFloat = f2;
          }
        }
      }
    }
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams()
  {
    return new LayoutParams();
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return generateDefaultLayoutParams();
  }
  
  public PagerAdapter getAdapter()
  {
    return mAdapter;
  }
  
  protected int getChildDrawingOrder(int paramInt1, int paramInt2)
  {
    int i = paramInt2;
    if (mDrawingOrder == 2) {
      i = paramInt1 - 1 - paramInt2;
    }
    return mDrawingOrderedChildren.get(i)).getLayoutParams()).childIndex;
  }
  
  public int getCurrentItem()
  {
    return mCurItem;
  }
  
  public FlipMode getFlipMode()
  {
    return mFlipMode;
  }
  
  public int getOffscreenPageLimit()
  {
    return mOffscreenPageLimit;
  }
  
  public int getPageMargin()
  {
    return mPageMargin;
  }
  
  ItemInfo infoForAnyChild(View paramView)
  {
    for (;;)
    {
      ViewParent localViewParent = paramView.getParent();
      if (localViewParent == this) {
        break;
      }
      if ((localViewParent == null) || (!(localViewParent instanceof View))) {
        return null;
      }
      paramView = (View)localViewParent;
    }
    return infoForChild(paramView);
  }
  
  ItemInfo infoForChild(View paramView)
  {
    int i = 0;
    while (i < mItems.size())
    {
      ItemInfo localItemInfo = (ItemInfo)mItems.get(i);
      if (mAdapter.isViewFromObject(paramView, object)) {
        return localItemInfo;
      }
      i += 1;
    }
    return null;
  }
  
  ItemInfo infoForPosition(int paramInt)
  {
    int i = 0;
    while (i < mItems.size())
    {
      ItemInfo localItemInfo = (ItemInfo)mItems.get(i);
      if (position == paramInt) {
        return localItemInfo;
      }
      i += 1;
    }
    return null;
  }
  
  void initViewPager()
  {
    setWillNotDraw(false);
    setDescendantFocusability(262144);
    setFocusable(true);
    Context localContext = getContext();
    mScroller = new Scroller(localContext, sInterpolator);
    mCurInterpolator = sInterpolator;
    ViewConfiguration localViewConfiguration = ViewConfiguration.get(localContext);
    float f = getResourcesgetDisplayMetricsdensity;
    mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(localViewConfiguration);
    mMinimumVelocity = ((int)(400.0F * f));
    mMaximumVelocity = localViewConfiguration.getScaledMaximumFlingVelocity();
    mLeftEdge = new EdgeEffectCompat(localContext);
    mRightEdge = new EdgeEffectCompat(localContext);
    mFlingDistance = ((int)(25.0F * f));
    mCloseEnough = ((int)(2.0F * f));
    mDefaultGutterSize = ((int)(16.0F * f));
    ViewCompat.setAccessibilityDelegate(this, new MyAccessibilityDelegate());
    if (ViewCompat.getImportantForAccessibility(this) == 0) {
      ViewCompat.setImportantForAccessibility(this, 1);
    }
    if (FlipMode.FLIP_MODE_OVERLAY == mFlipMode) {
      setChildrenDrawingOrderEnabledCompat(true);
    }
  }
  
  public boolean isFakeDragging()
  {
    return mFakeDragging;
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    mFirstLayout = true;
  }
  
  protected void onDetachedFromWindow()
  {
    removeCallbacks(mEndScrollRunnable);
    super.onDetachedFromWindow();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    int k;
    int m;
    float f3;
    Object localObject;
    float f1;
    int n;
    int i;
    int i1;
    int j;
    if ((mPageMargin > 0) && (mMarginDrawable != null) && (mItems.size() > 0) && (mAdapter != null))
    {
      k = getScrollX();
      m = getWidth();
      f3 = mPageMargin / m;
      localObject = (ItemInfo)mItems.get(0);
      f1 = offset;
      n = mItems.size();
      i = position;
      i1 = mItems.get(n - 1)).position;
      j = 0;
    }
    for (;;)
    {
      float f2;
      if (i < i1)
      {
        while ((i > position) && (j < n))
        {
          localObject = mItems;
          j += 1;
          localObject = (ItemInfo)((ArrayList)localObject).get(j);
        }
        if (i != position) {
          break label271;
        }
        f2 = (offset + widthFactor) * m;
      }
      label271:
      float f4;
      for (f1 = offset + widthFactor + f3;; f1 += f4 + f3)
      {
        if (mPageMargin + f2 > k)
        {
          mMarginDrawable.setBounds((int)f2, mTopPageBounds, (int)(mPageMargin + f2 + 0.5F), mBottomPageBounds);
          mMarginDrawable.draw(paramCanvas);
        }
        if (f2 <= k + m) {
          break;
        }
        return;
        f4 = mAdapter.getPageWidth(i);
        f2 = (f1 + f4) * m;
      }
      i += 1;
    }
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction() & 0xFF;
    if ((i == 3) || (i == 1))
    {
      mIsBeingDragged = false;
      mIsUnableToDrag = false;
      mActivePointerId = -1;
      if (mVelocityTracker != null)
      {
        mVelocityTracker.recycle();
        mVelocityTracker = null;
      }
    }
    do
    {
      return false;
      if (i == 0) {
        break;
      }
      if (mIsBeingDragged) {
        return true;
      }
    } while (mIsUnableToDrag);
    switch (i)
    {
    }
    for (;;)
    {
      if (mVelocityTracker == null) {
        mVelocityTracker = VelocityTracker.obtain();
      }
      mVelocityTracker.addMovement(paramMotionEvent);
      return mIsBeingDragged;
      i = mActivePointerId;
      if (i != -1)
      {
        int j = MotionEventCompat.findPointerIndex(paramMotionEvent, i);
        if (j == -1)
        {
          Log.e("ViewPager", "Invalid pointerId=" + i + " in onInterceptTouchEvent ACTION_MOVE");
        }
        else
        {
          float f2 = MotionEventCompat.getX(paramMotionEvent, j);
          float f1 = f2 - mLastMotionX;
          float f4 = Math.abs(f1);
          float f3 = MotionEventCompat.getY(paramMotionEvent, j);
          float f5 = Math.abs(f3 - mInitialMotionY);
          if ((f1 != 0.0F) && (!isGutterDrag(mLastMotionX, f1)) && (canScroll(this, false, (int)f1, (int)f2, (int)f3)))
          {
            mLastMotionX = f2;
            mLastMotionY = f3;
            mIsUnableToDrag = true;
            return false;
          }
          if ((f4 < mTouchSlopAdjust) && (f5 < mTouchSlopAdjust) && (pointInRect(paramMotionEvent, mSpecRect)))
          {
            Log.d("ViewPager", "xDiff = " + f4 + ", yDiff = " + f5 + ", mTouchSlopAdj = " + mTouchSlopAdjust);
            return false;
          }
          if ((f4 > mTouchSlop) && (f4 > f5))
          {
            mIsBeingDragged = true;
            requestParentDisallowInterceptTouchEvent(true);
            setScrollState(1);
            if (f1 > 0.0F)
            {
              f1 = mInitialMotionX + mTouchSlop;
              label435:
              mLastMotionX = f1;
              mLastMotionY = f3;
              setScrollingCacheEnabled(true);
            }
          }
          while ((mIsBeingDragged) && (performDrag(f2)))
          {
            ViewCompat.postInvalidateOnAnimation(this);
            break;
            f1 = mInitialMotionX - mTouchSlop;
            break label435;
            if (f5 > mTouchSlop) {
              mIsUnableToDrag = true;
            }
          }
          f1 = paramMotionEvent.getX();
          mInitialMotionX = f1;
          mLastMotionX = f1;
          f1 = paramMotionEvent.getY();
          mInitialMotionY = f1;
          mLastMotionY = f1;
          mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, 0);
          mIsUnableToDrag = false;
          mScroller.computeScrollOffset();
          if ((mScrollState == 2) && (Math.abs(mScroller.getFinalX() - mScroller.getCurrX()) > mCloseEnough))
          {
            mScroller.abortAnimation();
            mPopulatePending = false;
            populate();
            mIsBeingDragged = true;
            requestParentDisallowInterceptTouchEvent(true);
            setScrollState(1);
          }
          else
          {
            completeScroll(false);
            mIsBeingDragged = false;
            continue;
            onSecondaryPointerUp(paramMotionEvent);
          }
        }
      }
    }
  }
  
  public boolean onInterceptTouchEventExt(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction() & 0xFF;
    if ((i == 3) || (i == 1))
    {
      mIsBeingDragged = false;
      mIsUnableToDrag = false;
      mActivePointerId = -1;
      if (mVelocityTracker != null)
      {
        mVelocityTracker.recycle();
        mVelocityTracker = null;
      }
    }
    do
    {
      return false;
      if (i == 0) {
        break;
      }
      if (mIsBeingDragged) {
        return true;
      }
    } while (mIsUnableToDrag);
    switch (i)
    {
    }
    for (;;)
    {
      if (mVelocityTracker == null) {
        mVelocityTracker = VelocityTracker.obtain();
      }
      mVelocityTracker.addMovement(paramMotionEvent);
      return mIsBeingDragged;
      i = mActivePointerId;
      if (i != -1)
      {
        i = MotionEventCompat.findPointerIndex(paramMotionEvent, i);
        float f2 = MotionEventCompat.getX(paramMotionEvent, i);
        float f1 = f2 - mLastMotionX;
        float f4 = Math.abs(f1);
        float f3 = MotionEventCompat.getY(paramMotionEvent, i);
        float f5 = Math.abs(f3 - mInitialMotionY);
        if ((f4 > mTouchSlop) && (f4 * 1.6D > f5))
        {
          mIsBeingDragged = true;
          requestParentDisallowInterceptTouchEvent(true);
          setScrollState(1);
          if (f1 > 0.0F)
          {
            f1 = mInitialMotionX + mTouchSlop;
            label260:
            mLastMotionX = f1;
            mLastMotionY = f3;
            setScrollingCacheEnabled(true);
          }
        }
        while ((mIsBeingDragged) && (performDrag(f2)))
        {
          ViewCompat.postInvalidateOnAnimation(this);
          break;
          f1 = mInitialMotionX - mTouchSlop;
          break label260;
          if (f5 > mTouchSlop) {
            mIsUnableToDrag = true;
          }
        }
        f1 = paramMotionEvent.getX();
        mInitialMotionX = f1;
        mLastMotionX = f1;
        f1 = paramMotionEvent.getY();
        mInitialMotionY = f1;
        mLastMotionY = f1;
        mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, 0);
        mIsUnableToDrag = false;
        mScroller.computeScrollOffset();
        if ((mScrollState == 2) && (Math.abs(mScroller.getFinalX() - mScroller.getCurrX()) > mCloseEnough))
        {
          mScroller.abortAnimation();
          mPopulatePending = false;
          populate();
          mIsBeingDragged = true;
          requestParentDisallowInterceptTouchEvent(true);
          setScrollState(1);
        }
        else
        {
          completeScroll(false);
          mIsBeingDragged = false;
          continue;
          onSecondaryPointerUp(paramMotionEvent);
        }
      }
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i1 = getChildCount();
    int i4 = paramInt3 - paramInt1;
    int i2 = paramInt4 - paramInt2;
    paramInt2 = getPaddingLeft();
    paramInt1 = getPaddingTop();
    int i = getPaddingRight();
    paramInt3 = getPaddingBottom();
    int i3 = getScrollX();
    int j = 0;
    int m = 0;
    View localView;
    LayoutParams localLayoutParams;
    int i5;
    int k;
    label154:
    int n;
    if (m < i1)
    {
      localView = getChildAt(m);
      if (localView.getVisibility() == 8) {
        break label699;
      }
      localLayoutParams = (LayoutParams)localView.getLayoutParams();
      if (!isDecor) {
        break label699;
      }
      paramInt4 = gravity;
      i5 = gravity;
      switch (paramInt4 & 0x7)
      {
      case 2: 
      case 4: 
      default: 
        paramInt4 = paramInt2;
        k = paramInt2;
        switch (i5 & 0x70)
        {
        default: 
          n = paramInt1;
          paramInt2 = paramInt1;
          paramInt1 = paramInt3;
          paramInt3 = n;
          label204:
          paramInt4 += i3;
          localView.layout(paramInt4, paramInt3, localView.getMeasuredWidth() + paramInt4, localView.getMeasuredHeight() + paramInt3);
          j += 1;
          paramInt4 = i;
          paramInt3 = k;
          i = paramInt1;
          paramInt1 = j;
        }
        break;
      }
    }
    for (;;)
    {
      m += 1;
      k = paramInt3;
      j = paramInt1;
      paramInt1 = paramInt2;
      paramInt3 = i;
      i = paramInt4;
      paramInt2 = k;
      break;
      k = localView.getMeasuredWidth();
      paramInt4 = paramInt2;
      k += paramInt2;
      break label154;
      paramInt4 = Math.max((i4 - localView.getMeasuredWidth()) / 2, paramInt2);
      k = paramInt2;
      break label154;
      k = localView.getMeasuredWidth();
      paramInt4 = i + localView.getMeasuredWidth();
      n = i4 - i - k;
      i = paramInt4;
      k = paramInt2;
      paramInt4 = n;
      break label154;
      n = localView.getMeasuredHeight();
      paramInt2 = paramInt3;
      n += paramInt1;
      paramInt3 = paramInt1;
      paramInt1 = paramInt2;
      paramInt2 = n;
      break label204;
      n = Math.max((i2 - localView.getMeasuredHeight()) / 2, paramInt1);
      paramInt2 = paramInt1;
      paramInt1 = paramInt3;
      paramInt3 = n;
      break label204;
      n = i2 - paramInt3 - localView.getMeasuredHeight();
      i5 = localView.getMeasuredHeight();
      paramInt2 = paramInt1;
      paramInt1 = paramInt3 + i5;
      paramInt3 = n;
      break label204;
      i = i4 - paramInt2 - i;
      paramInt4 = 0;
      while (paramInt4 < i1)
      {
        localView = getChildAt(paramInt4);
        if (localView.getVisibility() != 8)
        {
          localLayoutParams = (LayoutParams)localView.getLayoutParams();
          if (!isDecor)
          {
            ItemInfo localItemInfo = infoForChild(localView);
            if (localItemInfo != null)
            {
              k = (int)(i * offset) + paramInt2;
              if (needsMeasure)
              {
                needsMeasure = false;
                float f = i;
                localView.measure(View.MeasureSpec.makeMeasureSpec((int)(widthFactor * f), 1073741824), View.MeasureSpec.makeMeasureSpec(i2 - paramInt1 - paramInt3, 1073741824));
              }
              view = localView;
              localView.layout(k, paramInt1, localView.getMeasuredWidth() + k, localView.getMeasuredHeight() + paramInt1);
            }
          }
        }
        paramInt4 += 1;
      }
      mTopPageBounds = paramInt1;
      mBottomPageBounds = (i2 - paramInt3);
      mDecorChildCount = j;
      if (FlipMode.FLIP_MODE_OVERLAY == mFlipMode)
      {
        mNeedInitShadow = true;
        initItemIndex();
        scrollSidePage(i3);
      }
      if (mFirstLayout) {
        scrollToItem(mCurItem, false, 0, false);
      }
      mFirstLayout = false;
      return;
      label699:
      paramInt4 = j;
      j = paramInt1;
      k = paramInt2;
      paramInt1 = paramInt4;
      paramInt4 = i;
      i = paramInt3;
      paramInt2 = j;
      paramInt3 = k;
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(getDefaultSize(0, paramInt1), getDefaultSize(0, paramInt2));
    paramInt1 = getMeasuredWidth();
    mGutterSize = Math.min(paramInt1 / 10, mDefaultGutterSize);
    paramInt1 = paramInt1 - getPaddingLeft() - getPaddingRight();
    paramInt2 = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
    int i4 = getChildCount();
    int k = 0;
    View localView;
    int i;
    int j;
    LayoutParams localLayoutParams;
    int m;
    int i1;
    label183:
    int n;
    if (k < i4)
    {
      localView = getChildAt(k);
      i = paramInt1;
      j = paramInt2;
      if (localView.getVisibility() != 8)
      {
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        i = paramInt1;
        j = paramInt2;
        if (localLayoutParams != null)
        {
          i = paramInt1;
          j = paramInt2;
          if (isDecor)
          {
            i = gravity & 0x7;
            m = gravity & 0x70;
            i1 = Integer.MIN_VALUE;
            j = Integer.MIN_VALUE;
            if ((m != 48) && (m != 80)) {
              break label333;
            }
            m = 1;
            if ((i != 3) && (i != 5)) {
              break label339;
            }
            n = 1;
            label198:
            if (m == 0) {
              break label345;
            }
            i = 1073741824;
            label208:
            if (width == -2) {
              break label528;
            }
            i1 = 1073741824;
            if (width == -1) {
              break label522;
            }
            i = width;
          }
        }
      }
    }
    for (;;)
    {
      int i3;
      if (height != -2)
      {
        i2 = 1073741824;
        j = i2;
        if (height != -1)
        {
          i3 = height;
          j = i2;
        }
      }
      for (int i2 = i3;; i2 = paramInt2)
      {
        localView.measure(View.MeasureSpec.makeMeasureSpec(i, i1), View.MeasureSpec.makeMeasureSpec(i2, j));
        if (m != 0)
        {
          j = paramInt2 - localView.getMeasuredHeight();
          i = paramInt1;
        }
        for (;;)
        {
          k += 1;
          paramInt1 = i;
          paramInt2 = j;
          break;
          label333:
          m = 0;
          break label183;
          label339:
          n = 0;
          break label198;
          label345:
          i = i1;
          if (n == 0) {
            break label208;
          }
          j = 1073741824;
          i = i1;
          break label208;
          i = paramInt1;
          j = paramInt2;
          if (n != 0)
          {
            i = paramInt1 - localView.getMeasuredWidth();
            j = paramInt2;
          }
        }
        mChildWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824);
        mChildHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824);
        mInLayout = true;
        populate();
        mInLayout = false;
        i = getChildCount();
        paramInt2 = 0;
        while (paramInt2 < i)
        {
          localView = getChildAt(paramInt2);
          if (localView.getVisibility() != 8)
          {
            localLayoutParams = (LayoutParams)localView.getLayoutParams();
            if ((localLayoutParams == null) || (!isDecor))
            {
              float f = paramInt1;
              localView.measure(View.MeasureSpec.makeMeasureSpec((int)(widthFactor * f), 1073741824), mChildHeightMeasureSpec);
            }
          }
          paramInt2 += 1;
        }
        return;
      }
      label522:
      i = paramInt1;
      continue;
      label528:
      i1 = i;
      i = paramInt1;
    }
  }
  
  protected void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
    int i;
    View localView;
    if (mDecorChildCount > 0)
    {
      int i1 = getScrollX();
      i = getPaddingLeft();
      int j = getPaddingRight();
      int i2 = getWidth();
      int i3 = getChildCount();
      int n = 0;
      while (n < i3)
      {
        localView = getChildAt(n);
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        int m;
        int k;
        if (!isDecor)
        {
          m = i;
          k = j;
          n += 1;
          i = m;
          j = k;
        }
        else
        {
          switch (gravity & 0x7)
          {
          case 2: 
          case 4: 
          default: 
            k = i;
            m = j;
            j = i;
            i = m;
          }
          for (;;)
          {
            int i4 = k + i1 - localView.getLeft();
            k = i;
            m = j;
            if (i4 == 0) {
              break;
            }
            localView.offsetLeftAndRight(i4);
            k = i;
            m = j;
            break;
            k = localView.getWidth();
            m = k + i;
            k = i;
            i = j;
            j = m;
            continue;
            k = Math.max((i2 - localView.getMeasuredWidth()) / 2, i);
            m = i;
            i = j;
            j = m;
            continue;
            k = i2 - j - localView.getMeasuredWidth();
            i4 = localView.getMeasuredWidth();
            m = i;
            i = j + i4;
            j = m;
          }
        }
      }
    }
    dispatchOnPageScrolled(paramInt1, paramFloat, paramInt2);
    if (mPageTransformer != null)
    {
      paramInt2 = getScrollX();
      i = getChildCount();
      paramInt1 = 0;
      if (paramInt1 < i)
      {
        localView = getChildAt(paramInt1);
        if (getLayoutParamsisDecor) {}
        for (;;)
        {
          paramInt1 += 1;
          break;
          paramFloat = (localView.getLeft() - paramInt2) / getClientWidth();
          mPageTransformer.transformPage(localView, paramFloat);
        }
      }
    }
    mCalledSuper = true;
  }
  
  protected boolean onRequestFocusInDescendants(int paramInt, Rect paramRect)
  {
    int k = -1;
    int j = getChildCount();
    int i;
    if ((paramInt & 0x2) != 0)
    {
      k = 1;
      i = 0;
    }
    while (i != j)
    {
      View localView = getChildAt(i);
      if (localView.getVisibility() == 0)
      {
        ItemInfo localItemInfo = infoForChild(localView);
        if ((localItemInfo != null) && (position == mCurItem) && (localView.requestFocus(paramInt, paramRect)))
        {
          return true;
          i = j - 1;
          j = -1;
          continue;
        }
      }
      i += k;
    }
    return false;
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof SavedState))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    if (mAdapter != null)
    {
      mAdapter.restoreState(adapterState, loader);
      setCurrentItemInternal(position, false, true);
      return;
    }
    mRestoredCurItem = position;
    mRestoredAdapterState = adapterState;
    mRestoredClassLoader = loader;
  }
  
  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    position = mCurItem;
    if (mAdapter != null) {
      adapterState = mAdapter.saveState();
    }
    return localSavedState;
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramInt1 != paramInt3) {
      recomputeScrollPosition(paramInt1, paramInt3, mPageMargin, mPageMargin);
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int n = 0;
    if (mFakeDragging) {
      return true;
    }
    if ((paramMotionEvent.getAction() == 0) && (paramMotionEvent.getEdgeFlags() != 0)) {
      return false;
    }
    if ((mAdapter == null) || (mAdapter.getCount() == 0)) {
      return false;
    }
    if (mVelocityTracker == null) {
      mVelocityTracker = VelocityTracker.obtain();
    }
    mVelocityTracker.addMovement(paramMotionEvent);
    int i = n;
    switch (paramMotionEvent.getAction() & 0xFF)
    {
    default: 
      i = n;
    }
    for (;;)
    {
      if (i != 0) {
        ViewCompat.postInvalidateOnAnimation(this);
      }
      return true;
      mScroller.abortAnimation();
      mPopulatePending = false;
      populate();
      float f1 = paramMotionEvent.getX();
      mInitialMotionX = f1;
      mLastMotionX = f1;
      f1 = paramMotionEvent.getY();
      mInitialMotionY = f1;
      mLastMotionY = f1;
      mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, 0);
      i = n;
      continue;
      if (mCurInterpolator != sInterpolator)
      {
        mLastInterpolator = mCurInterpolator;
        setInterpolator(sInterpolator);
      }
      float f2;
      if (!mIsBeingDragged)
      {
        i = MotionEventCompat.findPointerIndex(paramMotionEvent, mActivePointerId);
        if (i == -1)
        {
          Log.e("ViewPager", "Invalid pointerId=" + mActivePointerId + " in onTouchEvent ACTION_MOVE");
          i = n;
          continue;
        }
        f1 = MotionEventCompat.getX(paramMotionEvent, i);
        float f3 = Math.abs(f1 - mLastMotionX);
        f2 = MotionEventCompat.getY(paramMotionEvent, i);
        float f4 = Math.abs(f2 - mLastMotionY);
        if ((f3 > mTouchSlop) && (f3 > f4))
        {
          mIsBeingDragged = true;
          requestParentDisallowInterceptTouchEvent(true);
          if (f1 - mInitialMotionX <= 0.0F) {
            break label451;
          }
        }
      }
      Object localObject;
      label451:
      for (f1 = mInitialMotionX + mTouchSlop;; f1 = mInitialMotionX - mTouchSlop)
      {
        mLastMotionX = f1;
        mLastMotionY = f2;
        setScrollState(1);
        setScrollingCacheEnabled(true);
        localObject = getParent();
        if (localObject != null) {
          ((ViewParent)localObject).requestDisallowInterceptTouchEvent(true);
        }
        i = n;
        if (!mIsBeingDragged) {
          break;
        }
        bool1 = false | performDrag(MotionEventCompat.getX(paramMotionEvent, MotionEventCompat.findPointerIndex(paramMotionEvent, mActivePointerId)));
        break;
      }
      boolean bool1 = n;
      if (mIsBeingDragged)
      {
        localObject = mVelocityTracker;
        ((VelocityTracker)localObject).computeCurrentVelocity(1000, mMaximumVelocity);
        int j = (int)VelocityTrackerCompat.getXVelocity((VelocityTracker)localObject, mActivePointerId);
        mPopulatePending = true;
        int i2 = getClientWidth();
        int i3 = getScrollX();
        localObject = infoForCurrentScrollPosition();
        int i1 = position;
        f1 = (i3 / i2 - offset) / widthFactor;
        i2 = MotionEventCompat.findPointerIndex(paramMotionEvent, mActivePointerId);
        if (i2 == -1)
        {
          Log.e("ViewPager", "Invalid pointerId=" + mActivePointerId + " in onTouchEvent ACTION_UP");
          j = n;
        }
        else
        {
          n = MotionEventCompat.getPointerCount(paramMotionEvent);
          boolean bool3;
          int k;
          if ((i2 < 0) || (i2 >= n))
          {
            setCurrentItemInternal(i1, true, true);
            mActivePointerId = -1;
            endDrag();
            bool3 = mLeftEdge.onRelease();
            k = mRightEdge.onRelease() | bool3;
          }
          else
          {
            if (FlipMode.FLIP_MODE_OVERLAY == mFlipMode) {
              setInterpolator(sInterpolator);
            }
            setCurrentItemInternal(determineTargetPage(i1, f1, k, (int)(MotionEventCompat.getX(paramMotionEvent, i2) - mInitialMotionX)), true, true, k);
            mActivePointerId = -1;
            endDrag();
            bool3 = mLeftEdge.onRelease();
            boolean bool2 = mRightEdge.onRelease() | bool3;
            continue;
            bool2 = n;
            if (mIsBeingDragged)
            {
              scrollToItem(mCurItem, true, 0, false);
              mActivePointerId = -1;
              endDrag();
              bool3 = mLeftEdge.onRelease();
              bool2 = mRightEdge.onRelease() | bool3;
              continue;
              int m = MotionEventCompat.getActionIndex(paramMotionEvent);
              mLastMotionX = MotionEventCompat.getX(paramMotionEvent, m);
              mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, m);
              m = n;
              continue;
              onSecondaryPointerUp(paramMotionEvent);
              mLastMotionX = MotionEventCompat.getX(paramMotionEvent, MotionEventCompat.findPointerIndex(paramMotionEvent, mActivePointerId));
              m = n;
            }
          }
        }
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
    if ((mAdapter != null) && (mCurItem < mAdapter.getCount() - 1))
    {
      setCurrentItem(mCurItem + 1, true);
      return true;
    }
    return false;
  }
  
  void populate()
  {
    populate(mCurItem);
  }
  
  void populate(int paramInt)
  {
    int i;
    Object localObject2;
    if (mCurItem != paramInt) {
      if (mCurItem < paramInt)
      {
        i = 66;
        localObject2 = infoForPosition(mCurItem);
        mCurItem = paramInt;
        if (((mAdapter == null) || (mPopulatePending) || (getWindowToken() == null)) && (FlipMode.FLIP_MODE_OVERLAY == mFlipMode)) {
          initItemIndex();
        }
      }
    }
    for (int j = i;; j = 2)
    {
      if (mAdapter == null) {
        sortChildDrawingOrder();
      }
      do
      {
        return;
        i = 17;
        break;
        if (mPopulatePending)
        {
          sortChildDrawingOrder();
          return;
        }
      } while (getWindowToken() == null);
      mAdapter.startUpdate(this);
      paramInt = mOffscreenPageLimit;
      int i3 = Math.max(0, mCurItem - paramInt);
      int i1 = mAdapter.getCount();
      int i2 = Math.min(i1 - 1, paramInt + mCurItem);
      Object localObject1;
      if (i1 != mExpectedAdapterCount) {
        try
        {
          String str = getResources().getResourceName(getId());
          throw new IllegalStateException("The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: " + mExpectedAdapterCount + ", found: " + i1 + " Pager id: " + str + " Pager class: " + getClass() + " Problematic adapter: " + mAdapter.getClass());
        }
        catch (Resources.NotFoundException localNotFoundException)
        {
          for (;;)
          {
            localObject1 = Integer.toHexString(getId());
          }
        }
      }
      paramInt = 0;
      if (paramInt < mItems.size())
      {
        localObject1 = (ItemInfo)mItems.get(paramInt);
        if (position >= mCurItem) {
          if (position != mCurItem) {
            break label1323;
          }
        }
      }
      for (;;)
      {
        if ((localObject1 == null) && (i1 > 0)) {}
        for (Object localObject3 = addNewItem(mCurItem, paramInt);; localObject3 = localObject1)
        {
          int n;
          label379:
          int i4;
          label392:
          float f3;
          int m;
          int k;
          Object localObject4;
          float f1;
          if (localObject3 != null)
          {
            n = paramInt - 1;
            if (n < 0) {
              break label675;
            }
            localObject1 = (ItemInfo)mItems.get(n);
            i4 = getClientWidth();
            if (i4 > 0) {
              break label681;
            }
            f2 = 0.0F;
            i = mCurItem;
            f3 = 0.0F;
            m = i - 1;
            k = paramInt;
            localObject4 = localObject1;
            if (m >= 0)
            {
              if ((f3 < f2) || (m >= i3)) {
                break label836;
              }
              if (localObject4 != null) {
                break label702;
              }
            }
            f1 = widthFactor;
            paramInt = k + 1;
            if (f1 < 2.0F)
            {
              if (paramInt >= mItems.size()) {
                break label956;
              }
              localObject1 = (ItemInfo)mItems.get(paramInt);
              label479:
              if (i4 > 0) {
                break label962;
              }
            }
          }
          label498:
          label556:
          label675:
          label681:
          label702:
          label827:
          label836:
          label956:
          label962:
          for (float f2 = 0.0F;; f2 = getPaddingRight() / i4 + 2.0F)
          {
            i = mCurItem;
            i += 1;
            if (i < i1)
            {
              if ((f1 < f2) || (i <= i2)) {
                break label1058;
              }
              if (localObject1 != null) {
                break label977;
              }
            }
            calculatePageOffsets((ItemInfo)localObject3, k, (ItemInfo)localObject2);
            localObject2 = mAdapter;
            paramInt = mCurItem;
            if (localObject3 == null) {
              break label1180;
            }
            localObject1 = object;
            ((PagerAdapter)localObject2).setPrimaryItem(this, paramInt, localObject1);
            mAdapter.finishUpdate(this);
            i = getChildCount();
            paramInt = 0;
            while (paramInt < i)
            {
              localObject2 = getChildAt(paramInt);
              localObject1 = (LayoutParams)((View)localObject2).getLayoutParams();
              childIndex = paramInt;
              if ((!isDecor) && (widthFactor == 0.0F))
              {
                localObject2 = infoForChild((View)localObject2);
                if (localObject2 != null)
                {
                  widthFactor = widthFactor;
                  position = position;
                }
              }
              paramInt += 1;
            }
            paramInt += 1;
            break;
            localObject1 = null;
            break label379;
            f2 = 2.0F - widthFactor + getPaddingLeft() / i4;
            break label392;
            localObject1 = localObject4;
            paramInt = n;
            f1 = f3;
            i = k;
            if (m == position)
            {
              localObject1 = localObject4;
              paramInt = n;
              f1 = f3;
              i = k;
              if (!scrolling)
              {
                mItems.remove(n);
                mAdapter.destroyItem(this, m, object);
                paramInt = n - 1;
                i = k - 1;
                if (paramInt < 0) {
                  break label827;
                }
                localObject1 = (ItemInfo)mItems.get(paramInt);
                f1 = f3;
              }
            }
            for (;;)
            {
              m -= 1;
              localObject4 = localObject1;
              n = paramInt;
              f3 = f1;
              k = i;
              break;
              localObject1 = null;
              f1 = f3;
              continue;
              if ((localObject4 != null) && (m == position))
              {
                f1 = f3 + widthFactor;
                paramInt = n - 1;
                if (paramInt >= 0)
                {
                  localObject1 = (ItemInfo)mItems.get(paramInt);
                  i = k;
                }
                else
                {
                  localObject1 = null;
                  i = k;
                }
              }
              else
              {
                f1 = f3 + addNewItem1widthFactor;
                i = k + 1;
                if (n >= 0)
                {
                  localObject1 = (ItemInfo)mItems.get(n);
                  paramInt = n;
                }
                else
                {
                  localObject1 = null;
                  paramInt = n;
                }
              }
            }
            localObject1 = null;
            break label479;
          }
          label977:
          if ((i == position) && (!scrolling))
          {
            mItems.remove(paramInt);
            mAdapter.destroyItem(this, i, object);
            if (paramInt < mItems.size()) {
              localObject1 = (ItemInfo)mItems.get(paramInt);
            }
          }
          for (;;)
          {
            i += 1;
            break label498;
            localObject1 = null;
            continue;
            label1058:
            if ((localObject1 != null) && (i == position))
            {
              f3 = widthFactor;
              paramInt += 1;
              if (paramInt < mItems.size()) {}
              for (localObject1 = (ItemInfo)mItems.get(paramInt);; localObject1 = null)
              {
                f1 += f3;
                break;
              }
            }
            localObject1 = addNewItem(i, paramInt);
            paramInt += 1;
            f3 = widthFactor;
            if (paramInt < mItems.size()) {}
            for (localObject1 = (ItemInfo)mItems.get(paramInt);; localObject1 = null)
            {
              f1 += f3;
              break;
            }
            label1180:
            localObject1 = null;
            break label556;
            sortChildDrawingOrder();
            if (hasFocus())
            {
              localObject1 = findFocus();
              if (localObject1 == null) {
                break label1300;
              }
              localObject1 = infoForAnyChild((View)localObject1);
              label1216:
              if ((localObject1 == null) || (position != mCurItem)) {
                paramInt = 0;
              }
            }
            for (;;)
            {
              if (paramInt < getChildCount())
              {
                localObject1 = getChildAt(paramInt);
                localObject2 = infoForChild((View)localObject1);
                if ((localObject2 == null) || (position != mCurItem) || (!((View)localObject1).requestFocus(j))) {}
              }
              else
              {
                if (FlipMode.FLIP_MODE_OVERLAY != mFlipMode) {
                  break;
                }
                initItemIndex();
                return;
                label1300:
                localObject1 = null;
                break label1216;
              }
              paramInt += 1;
            }
          }
        }
        label1323:
        localObject1 = null;
      }
      localObject2 = null;
    }
  }
  
  public void removeOnPageChangeListener(OnPageChangeListener paramOnPageChangeListener)
  {
    if (mOnPageChangeListeners != null) {
      mOnPageChangeListeners.remove(paramOnPageChangeListener);
    }
  }
  
  public void removeView(View paramView)
  {
    if (mInLayout)
    {
      removeViewInLayout(paramView);
      return;
    }
    super.removeView(paramView);
  }
  
  public void setAdapter(PagerAdapter paramPagerAdapter)
  {
    if (mAdapter != null)
    {
      mAdapter.unregisterDataSetObserver(mObserver);
      mAdapter.startUpdate(this);
      int i = 0;
      while (i < mItems.size())
      {
        localObject = (ItemInfo)mItems.get(i);
        mAdapter.destroyItem(this, position, object);
        i += 1;
      }
      mAdapter.finishUpdate(this);
      mItems.clear();
      removeNonDecorViews();
      mCurItem = 0;
      scrollTo(0, 0);
    }
    Object localObject = mAdapter;
    mAdapter = paramPagerAdapter;
    mExpectedAdapterCount = 0;
    boolean bool;
    if (mAdapter != null)
    {
      if (mObserver == null) {
        mObserver = new PagerObserver(null);
      }
      mAdapter.registerDataSetObserver(mObserver);
      mPopulatePending = false;
      bool = mFirstLayout;
      mFirstLayout = true;
      mExpectedAdapterCount = mAdapter.getCount();
      if (mRestoredCurItem < 0) {
        break label260;
      }
      mAdapter.restoreState(mRestoredAdapterState, mRestoredClassLoader);
      setCurrentItemInternal(mRestoredCurItem, false, true);
      mRestoredCurItem = -1;
      mRestoredAdapterState = null;
      mRestoredClassLoader = null;
    }
    for (;;)
    {
      if ((mAdapterChangeListener != null) && (localObject != paramPagerAdapter)) {
        mAdapterChangeListener.onAdapterChanged((PagerAdapter)localObject, paramPagerAdapter);
      }
      return;
      label260:
      if (!bool) {
        populate();
      } else {
        requestLayout();
      }
    }
  }
  
  void setChildrenDrawingOrderEnabledCompat(boolean paramBoolean)
  {
    if ((Build.VERSION.SDK_INT < 7) || (mSetChildrenDrawingOrderEnabled == null)) {}
    try
    {
      mSetChildrenDrawingOrderEnabled = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", new Class[] { Boolean.TYPE });
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;)
      {
        try
        {
          mSetChildrenDrawingOrderEnabled.setAccessible(true);
          mSetChildrenDrawingOrderEnabled.invoke(this, new Object[] { Boolean.valueOf(paramBoolean) });
          return;
        }
        catch (Exception localException)
        {
          Log.e("ViewPager", "Error changing children drawing order", localException);
        }
        localNoSuchMethodException = localNoSuchMethodException;
        Log.e("ViewPager", "Can't find setChildrenDrawingOrderEnabled", localNoSuchMethodException);
      }
    }
  }
  
  public void setCurrentItem(int paramInt)
  {
    mPopulatePending = false;
    if (!mFirstLayout) {}
    for (boolean bool = true;; bool = false)
    {
      setCurrentItemInternal(paramInt, bool, false);
      return;
    }
  }
  
  public void setCurrentItem(int paramInt1, int paramInt2)
  {
    if (mLastInterpolator != null)
    {
      setInterpolator(mLastInterpolator);
      mLastInterpolator = null;
    }
    if (mCurInterpolator == sInterpolator) {
      setInterpolator(sAutoScrollInterpolator);
    }
    mPopulatePending = false;
    if ((mFirstLayout) || (paramInt2 <= 0)) {
      setCurrentItemInternal(paramInt1, false, false);
    }
    if ((mAdapter == null) || (mAdapter.getCount() <= 0))
    {
      setScrollingCacheEnabled(false);
      return;
    }
    if ((mCurItem == paramInt1) && (mItems.size() != 0))
    {
      setScrollingCacheEnabled(false);
      return;
    }
    int i;
    if (paramInt1 < 0) {
      i = 0;
    }
    for (;;)
    {
      paramInt1 = mOffscreenPageLimit;
      if ((i <= mCurItem + paramInt1) && (i >= mCurItem - paramInt1)) {
        break;
      }
      paramInt1 = 0;
      while (paramInt1 < mItems.size())
      {
        mItems.get(paramInt1)).scrolling = true;
        paramInt1 += 1;
      }
      i = paramInt1;
      if (paramInt1 >= mAdapter.getCount()) {
        i = mAdapter.getCount() - 1;
      }
    }
    label215:
    ItemInfo localItemInfo;
    float f;
    if (mCurItem != i)
    {
      paramInt1 = 1;
      populate(i);
      localItemInfo = infoForPosition(i);
      if (localItemInfo == null) {
        break label436;
      }
      f = getClientWidth();
    }
    label436:
    for (int j = (int)(Math.max(mFirstOffset, Math.min(offset, mLastOffset)) * f);; j = 0)
    {
      if (getChildCount() == 0)
      {
        setScrollingCacheEnabled(false);
        return;
        paramInt1 = 0;
        break label215;
      }
      int k = getScrollX();
      int m = getScrollY();
      j -= k;
      int n = 0 - m;
      if ((j == 0) && (n == 0))
      {
        completeScroll(false);
        populate();
        setScrollState(0);
        return;
      }
      setScrollingCacheEnabled(true);
      setScrollState(2);
      paramInt2 = Math.max(1, Math.min(paramInt2, 5000));
      if (FlipMode.FLIP_MODE_OVERLAY == mFlipMode) {
        setInterpolator(overlayModeInterpolator);
      }
      mScroller.startScroll(k, m, j, n, paramInt2);
      ViewCompat.postInvalidateOnAnimation(this);
      if ((paramInt1 != 0) && (mOnPageChangeListener != null)) {
        mOnPageChangeListener.onPageSelected(i);
      }
      if ((paramInt1 == 0) || (mInternalPageChangeListener == null)) {
        break;
      }
      mInternalPageChangeListener.onPageSelected(i);
      return;
    }
  }
  
  public void setCurrentItem(int paramInt, boolean paramBoolean)
  {
    mPopulatePending = false;
    setCurrentItemInternal(paramInt, paramBoolean, false);
  }
  
  void setCurrentItemInternal(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    setCurrentItemInternal(paramInt, paramBoolean1, paramBoolean2, 0);
  }
  
  void setCurrentItemInternal(int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2)
  {
    boolean bool = false;
    if ((mAdapter == null) || (mAdapter.getCount() <= 0))
    {
      setScrollingCacheEnabled(false);
      return;
    }
    if ((!paramBoolean2) && (mCurItem == paramInt1) && (mItems.size() != 0))
    {
      setScrollingCacheEnabled(false);
      return;
    }
    int i;
    if (paramInt1 < 0) {
      i = 0;
    }
    for (;;)
    {
      paramInt1 = mOffscreenPageLimit;
      if ((i <= mCurItem + paramInt1) && (i >= mCurItem - paramInt1)) {
        break;
      }
      paramInt1 = 0;
      while (paramInt1 < mItems.size())
      {
        mItems.get(paramInt1)).scrolling = true;
        paramInt1 += 1;
      }
      i = paramInt1;
      if (paramInt1 >= mAdapter.getCount()) {
        i = mAdapter.getCount() - 1;
      }
    }
    paramBoolean2 = bool;
    if (mCurItem != i) {
      paramBoolean2 = true;
    }
    if (mFirstLayout)
    {
      mCurItem = i;
      if (paramBoolean2) {
        dispatchOnPageSelected(i);
      }
      requestLayout();
      return;
    }
    populate(i);
    scrollToItem(i, paramBoolean1, paramInt2, paramBoolean2);
  }
  
  public void setFlipMode(FlipMode paramFlipMode)
  {
    if (mFlipMode != paramFlipMode)
    {
      mFlipMode = paramFlipMode;
      if (FlipMode.FLIP_MODE_OVERLAY != mFlipMode) {
        break label46;
      }
      mPageMargin = 0;
      initItemIndex();
      setChildrenDrawingOrderEnabledCompat(true);
    }
    for (;;)
    {
      sortChildDrawingOrder();
      requestLayout();
      return;
      label46:
      setChildrenDrawingOrderEnabledCompat(false);
    }
  }
  
  OnPageChangeListener setInternalPageChangeListener(OnPageChangeListener paramOnPageChangeListener)
  {
    OnPageChangeListener localOnPageChangeListener = mInternalPageChangeListener;
    mInternalPageChangeListener = paramOnPageChangeListener;
    return localOnPageChangeListener;
  }
  
  public void setInterpolator(Interpolator paramInterpolator)
  {
    if ((mScroller != null) && (!mScroller.isFinished())) {
      mScroller.forceFinished(true);
    }
    if ((paramInterpolator != null) && (mCurInterpolator != paramInterpolator))
    {
      mCurInterpolator = paramInterpolator;
      mScroller = new Scroller(mContext, paramInterpolator);
    }
  }
  
  public void setOffscreenPageLimit(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 1)
    {
      Log.w("ViewPager", "Requested offscreen page limit " + paramInt + " too small; defaulting to " + 1);
      i = 1;
    }
    if (i != mOffscreenPageLimit)
    {
      mOffscreenPageLimit = i;
      populate();
    }
  }
  
  void setOnAdapterChangeListener(OnAdapterChangeListener paramOnAdapterChangeListener)
  {
    mAdapterChangeListener = paramOnAdapterChangeListener;
  }
  
  @Deprecated
  public void setOnPageChangeListener(OnPageChangeListener paramOnPageChangeListener)
  {
    mOnPageChangeListener = paramOnPageChangeListener;
  }
  
  public void setPageMargin(int paramInt)
  {
    if (FlipMode.FLIP_MODE_OVERLAY == mFlipMode) {
      return;
    }
    int i = mPageMargin;
    mPageMargin = paramInt;
    int j = getWidth();
    recomputeScrollPosition(j, j, paramInt, i);
    requestLayout();
  }
  
  public void setPageMarginDrawable(int paramInt)
  {
    setPageMarginDrawable(getContext().getResources().getDrawable(paramInt));
  }
  
  public void setPageMarginDrawable(Drawable paramDrawable)
  {
    mMarginDrawable = paramDrawable;
    if (paramDrawable != null) {
      refreshDrawableState();
    }
    if (paramDrawable == null) {}
    for (boolean bool = true;; bool = false)
    {
      setWillNotDraw(bool);
      invalidate();
      return;
    }
  }
  
  public void setPageTransformer(boolean paramBoolean, PageTransformer paramPageTransformer)
  {
    int j = 1;
    boolean bool1;
    boolean bool2;
    label28:
    int i;
    if (Build.VERSION.SDK_INT >= 11)
    {
      if (paramPageTransformer == null) {
        break label75;
      }
      bool1 = true;
      if (mPageTransformer == null) {
        break label81;
      }
      bool2 = true;
      if (bool1 == bool2) {
        break label87;
      }
      i = 1;
      label37:
      mPageTransformer = paramPageTransformer;
      setChildrenDrawingOrderEnabledCompat(bool1);
      if (!bool1) {
        break label92;
      }
      if (paramBoolean) {
        j = 2;
      }
    }
    label75:
    label81:
    label87:
    label92:
    for (mDrawingOrder = j;; mDrawingOrder = 0)
    {
      if (i != 0) {
        populate();
      }
      return;
      bool1 = false;
      break;
      bool2 = false;
      break label28;
      i = 0;
      break label37;
    }
  }
  
  public void setRectSlopScaleInTab(int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat, int paramInt5)
  {
    mSpecRect = new Rect();
    mSpecRect.left = paramInt1;
    mSpecRect.top = paramInt2;
    mSpecRect.right = paramInt3;
    mSpecRect.bottom = paramInt4;
    mTouchSlopAdjust = ((int)(mTouchSlop * paramFloat));
    mSpecTab = paramInt5;
    Log.d("ViewPager", "setRectSlopScaleInTab mSpecRect = " + mSpecRect + ", coef = " + paramFloat + ", specTab = " + mSpecTab);
  }
  
  public void setShadow(Drawable paramDrawable1, Drawable paramDrawable2)
  {
    if (paramDrawable1 != mShadowDrawable)
    {
      mShadowDrawable = paramDrawable1;
      mNeedInitShadow = true;
    }
    if (paramDrawable2 != mCoverDrawable)
    {
      mCoverDrawable = paramDrawable2;
      mNeedInitShadow = true;
    }
  }
  
  public void setShadowResource(int paramInt1, int paramInt2)
  {
    if (paramInt1 != 0)
    {
      mShadowDrawable = getContext().getResources().getDrawable(paramInt1);
      mNeedInitShadow = true;
    }
    if (paramInt2 != 0)
    {
      mCoverDrawable = getContext().getResources().getDrawable(paramInt2);
      mNeedInitShadow = true;
    }
  }
  
  void smoothScrollTo(int paramInt1, int paramInt2)
  {
    smoothScrollTo(paramInt1, paramInt2, 0);
  }
  
  void smoothScrollTo(int paramInt1, int paramInt2, int paramInt3)
  {
    if (getChildCount() == 0)
    {
      setScrollingCacheEnabled(false);
      return;
    }
    int i = getScrollX();
    int j = getScrollY();
    int k = paramInt1 - i;
    paramInt2 -= j;
    if ((k == 0) && (paramInt2 == 0))
    {
      completeScroll(false);
      populate();
      setScrollState(0);
      return;
    }
    setScrollingCacheEnabled(true);
    setScrollState(2);
    paramInt1 = getClientWidth();
    int m = paramInt1 / 2;
    float f3 = Math.min(1.0F, Math.abs(k) * 1.0F / paramInt1);
    float f1 = m;
    float f2 = m;
    f3 = distanceInfluenceForSnapDuration(f3);
    paramInt3 = Math.abs(paramInt3);
    if (paramInt3 > 0) {
      paramInt1 = Math.round(1000.0F * Math.abs((f2 * f3 + f1) / paramInt3)) * 4;
    }
    for (;;)
    {
      paramInt1 = Math.min(paramInt1, 600);
      mScroller.startScroll(i, j, k, paramInt2, paramInt1);
      ViewCompat.postInvalidateOnAnimation(this);
      return;
      f1 = paramInt1;
      f2 = mAdapter.getPageWidth(mCurItem);
      f1 = Math.abs(k) / (f1 * f2 + mPageMargin);
      if ((FlipMode.FLIP_MODE_OVERLAY == mFlipMode) && (f1 >= 1.0F)) {
        paramInt1 = (int)((f1 + 4.0F) * 100.0F);
      } else {
        paramInt1 = (int)((f1 + 1.0F) * 100.0F);
      }
    }
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable)
  {
    return (super.verifyDrawable(paramDrawable)) || (paramDrawable == mMarginDrawable);
  }
  
  static abstract interface Decor {}
  
  public static enum FlipMode
  {
    FLIP_MODE_DEFAULT,  FLIP_MODE_OVERLAY;
    
    private FlipMode() {}
  }
  
  static class ItemInfo
  {
    int index;
    Object object;
    float offset;
    int position;
    boolean scrolling;
    View view;
    float widthFactor;
  }
  
  public static class LayoutParams
    extends ViewGroup.LayoutParams
  {
    int childIndex;
    public int gravity;
    public boolean isDecor;
    boolean needsMeasure;
    int position;
    float widthFactor = 0.0F;
    
    public LayoutParams()
    {
      super(-1);
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, ViewPager.LAYOUT_ATTRS);
      gravity = paramContext.getInteger(0, 48);
      paramContext.recycle();
    }
  }
  
  class MyAccessibilityDelegate
    extends AccessibilityDelegateCompat
  {
    MyAccessibilityDelegate() {}
    
    private boolean canScroll()
    {
      return (mAdapter != null) && (mAdapter.getCount() > 1);
    }
    
    public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
    {
      super.onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
      paramAccessibilityEvent.setClassName(ViewPager.class.getName());
      paramView = AccessibilityRecordCompat.obtain();
      paramView.setScrollable(canScroll());
      if ((paramAccessibilityEvent.getEventType() == 4096) && (mAdapter != null))
      {
        paramView.setItemCount(mAdapter.getCount());
        paramView.setFromIndex(mCurItem);
        paramView.setToIndex(mCurItem);
      }
    }
    
    public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      super.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat);
      paramAccessibilityNodeInfoCompat.setClassName(ViewPager.class.getName());
      paramAccessibilityNodeInfoCompat.setScrollable(canScroll());
      if (canScrollHorizontally(1)) {
        paramAccessibilityNodeInfoCompat.addAction(4096);
      }
      if (canScrollHorizontally(-1)) {
        paramAccessibilityNodeInfoCompat.addAction(8192);
      }
    }
    
    public boolean performAccessibilityAction(View paramView, int paramInt, Bundle paramBundle)
    {
      if (super.performAccessibilityAction(paramView, paramInt, paramBundle)) {
        return true;
      }
      switch (paramInt)
      {
      default: 
        return false;
      case 4096: 
        if (canScrollHorizontally(1))
        {
          setCurrentItem(mCurItem + 1);
          return true;
        }
        return false;
      }
      if (canScrollHorizontally(-1))
      {
        setCurrentItem(mCurItem - 1);
        return true;
      }
      return false;
    }
  }
  
  static abstract interface OnAdapterChangeListener
  {
    public abstract void onAdapterChanged(PagerAdapter paramPagerAdapter1, PagerAdapter paramPagerAdapter2);
  }
  
  public static abstract interface OnPageChangeListener
  {
    public abstract void onPageScrollStateChanged(int paramInt);
    
    public abstract void onPageScrolled(int paramInt1, float paramFloat, int paramInt2);
    
    public abstract void onPageSelected(int paramInt);
  }
  
  public static abstract interface PageTransformer
  {
    public abstract void transformPage(View paramView, float paramFloat);
  }
  
  class PagerObserver
    extends DataSetObserver
  {
    private PagerObserver() {}
    
    public void onChanged()
    {
      dataSetChanged();
    }
    
    public void onInvalidated()
    {
      dataSetChanged();
    }
  }
  
  public static class SavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new ViewPager.SavedState.1());
    Parcelable adapterState;
    ClassLoader loader;
    int position;
    
    SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super();
      ClassLoader localClassLoader = paramClassLoader;
      if (paramClassLoader == null) {
        localClassLoader = getClass().getClassLoader();
      }
      position = paramParcel.readInt();
      adapterState = paramParcel.readParcelable(localClassLoader);
      loader = localClassLoader;
    }
    
    public SavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    public String toString()
    {
      return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + position + "}";
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(position);
      paramParcel.writeParcelable(adapterState, paramInt);
    }
  }
  
  public static class SimpleOnPageChangeListener
    implements ViewPager.OnPageChangeListener
  {
    public void onPageScrollStateChanged(int paramInt) {}
    
    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {}
    
    public void onPageSelected(int paramInt) {}
  }
  
  static class ViewPositionComparator
    implements Comparator<View>
  {
    public int compare(View paramView1, View paramView2)
    {
      paramView1 = (ViewPager.LayoutParams)paramView1.getLayoutParams();
      paramView2 = (ViewPager.LayoutParams)paramView2.getLayoutParams();
      if (isDecor != isDecor)
      {
        if (isDecor) {
          return 1;
        }
        return -1;
      }
      return position - position;
    }
  }
  
  static class ViewReversePositionComparator
    implements Comparator<View>
  {
    public int compare(View paramView1, View paramView2)
    {
      paramView1 = (ViewPager.LayoutParams)paramView1.getLayoutParams();
      paramView2 = (ViewPager.LayoutParams)paramView2.getLayoutParams();
      if (isDecor != isDecor)
      {
        if (isDecor) {
          return 1;
        }
        return -1;
      }
      return position - position;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.ViewPager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */