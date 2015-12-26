package com.meizu.common.widget;

import android.content.ClipData;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.LongSparseArray;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.animation.DecelerateInterpolator;
import android.widget.Checkable;
import android.widget.SpinnerAdapter;
import com.meizu.common.R.attr;
import com.meizu.common.R.dimen;
import com.meizu.common.R.drawable;
import com.meizu.common.R.styleable;

public class EnhanceGallery
  extends AbsSpinner
  implements GestureDetector.OnGestureListener
{
  private static final int CHECK_POSITION_SEARCH_DISTANCE = 20;
  public static final int CHOICE_MODE_MULTIPLE = 1;
  public static final int CHOICE_MODE_SINGLE = 0;
  private static final int MIN_FLING_VELOCITY = 1500;
  private static final int SCROLL_TO_FLING_UNCERTAINTY_TIMEOUT = 250;
  static final int TOUCH_MODE_DOWN = 0;
  static final int TOUCH_MODE_FLING = 2;
  static final int TOUCH_MODE_OVERFLING = 4;
  static final int TOUCH_MODE_OVERSCROLL = 3;
  static final int TOUCH_MODE_REST = -1;
  static final int TOUCH_MODE_SCROLL = 1;
  private int mAnimationDuration = 250;
  private boolean mChangeChildAlphaWhenDragView = false;
  private SparseBooleanArray mCheckStates;
  LongSparseArray<Integer> mCheckedIdStates;
  private int mCheckedItemCount;
  private int mChildWidth;
  private ActionMode mChoiceActionMode;
  private int mChoiceMode = 0;
  private AdapterContextMenuInfo mContextMenuInfo;
  private int mCurrentOverScrollDistance;
  private int mDefaultMaxOverScrollDistance;
  private int mDeltaLength;
  private Runnable mDisableSuppressSelectionChangedRunnable = new EnhanceGallery.1(this);
  private int mDownFirstPosition;
  private int mDownLastPosition;
  private int mDownTouchPosition;
  private View mDownTouchView;
  protected int mDragAndDropPosition = -1;
  private boolean mDragEnable = false;
  private int mDragOffsetX = 0;
  private int mDragOffsetY = 0;
  private int mDragScrollY = 0;
  private int mDragViewBackground = R.drawable.mz_list_selector_background_long_pressed;
  private int mDragViewBackgroundDelete = R.drawable.mz_list_selector_background_delete;
  private int mDragViewBackgroundFilter = R.drawable.mz_list_selector_background_filter;
  private FlingRunnable mFlingRunnable = new FlingRunnable();
  private GestureDetector mGestureDetector = new GestureDetector(paramContext, this);
  private int mGravity;
  private boolean mIsFirstScroll;
  private boolean mIsRtl = false;
  private int mLastScrollState = 0;
  private int mMaxOverScrollDistance;
  int mMotionX;
  int mMotionY;
  MultiChoiceModeWrapper mMultiChoiceModeCallback;
  private OnScrollListener mOnScrollListener;
  private PerformClick mPerformClick;
  private boolean mScrollEnableWhenLessContent = false;
  private View mSelectedChild;
  private ListViewDragShadowBuilder mShadowBuilder;
  private boolean mShouldCallbackDuringFling = true;
  private boolean mShouldCallbackOnUnselectedItemClick = true;
  private boolean mShouldStopFling;
  private int mSpacing = 0;
  private boolean mSuppressSelectionChanged;
  private Rect mTouchFrame;
  private int mTouchMode = -1;
  
  public EnhanceGallery(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public EnhanceGallery(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.MeizuCommon_EnhanceGalleryStyle);
  }
  
  public EnhanceGallery(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    mGestureDetector.setIsLongpressEnabled(true);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.EnhanceGallery, paramInt, 0);
    setSpacing(paramContext.getDimensionPixelSize(R.styleable.EnhanceGallery_mcSpacing, 10));
    mDefaultMaxOverScrollDistance = getResources().getDimensionPixelSize(R.dimen.mc_enhancegallery_max_overscroll_distance);
    mMaxOverScrollDistance = paramContext.getDimensionPixelSize(R.styleable.EnhanceGallery_mcMaxOverScrollDistance, mDefaultMaxOverScrollDistance);
    mScrollEnableWhenLessContent = paramContext.getBoolean(R.styleable.EnhanceGallery_mcScrollEnableWhenLessContent, false);
    paramContext.recycle();
  }
  
  private int calculateTop(View paramView, boolean paramBoolean)
  {
    int i;
    if (paramBoolean)
    {
      i = getMeasuredHeight();
      label9:
      if (!paramBoolean) {
        break label66;
      }
    }
    label66:
    for (int j = paramView.getMeasuredHeight();; j = paramView.getHeight()) {
      switch (mGravity)
      {
      default: 
        return 0;
        i = getHeight();
        break label9;
      }
    }
    return mSpinnerPadding.top;
    int k = mSpinnerPadding.bottom;
    int m = mSpinnerPadding.top;
    int n = mSpinnerPadding.top;
    return (i - k - m - j) / 2 + n;
    return i - mSpinnerPadding.bottom - j;
  }
  
  private void clearChoices()
  {
    if (mCheckStates != null) {
      mCheckStates.clear();
    }
    if (mCheckedIdStates != null) {
      mCheckedIdStates.clear();
    }
    mCheckedItemCount = 0;
  }
  
  private void detachOffScreenChildren(boolean paramBoolean)
  {
    int i1 = 0;
    int i2 = getChildCount();
    int i3 = mFirstPosition;
    int n;
    int j;
    int i;
    int k;
    label39:
    int m;
    label62:
    View localView;
    if (paramBoolean) {
      if (mIsRtl)
      {
        n = getPaddingLeft();
        j = 0;
        i = 0;
        k = 0;
        if (j < i2 - 1)
        {
          if (!mIsRtl) {
            break label137;
          }
          m = i2 - 1 - j;
          if (!mIsRtl) {
            break label143;
          }
          localView = getChildAt(m - 1);
          label79:
          if (localView.getLeft() <= n) {
            break label156;
          }
        }
        if (mIsRtl) {
          break label363;
        }
        j = 0;
      }
    }
    for (;;)
    {
      detachViewsFromParent(j, i);
      if (paramBoolean != mIsRtl) {
        mFirstPosition = (i + mFirstPosition);
      }
      return;
      n = getPaddingLeft() + mSpacing;
      break;
      label137:
      m = j;
      break label62;
      label143:
      localView = getChildAt(m + 1);
      break label79;
      label156:
      i += 1;
      localView = getChildAt(m);
      mRecycler.put(i3 + m, localView);
      j += 1;
      k = m;
      break label39;
      if (mIsRtl)
      {
        n = getWidth() - getPaddingRight() - mSpacing;
        label216:
        k = i2 - 1;
        i = 0;
      }
      for (j = 0;; j = m)
      {
        if (k >= 1)
        {
          if (!mIsRtl) {
            break label302;
          }
          m = i2 - 1 - k;
          label248:
          if (!mIsRtl) {
            break label309;
          }
        }
        label302:
        label309:
        for (localView = getChildAt(m + 1);; localView = getChildAt(m - 1))
        {
          if (localView.getRight() >= n) {
            break label322;
          }
          if (!mIsRtl) {
            break label360;
          }
          j = i1;
          break;
          n = getWidth() - getPaddingRight();
          break label216;
          m = k;
          break label248;
        }
        label322:
        i += 1;
        localView = getChildAt(m);
        mRecycler.put(i3 + m, localView);
        k -= 1;
      }
      label360:
      continue;
      label363:
      j = k;
    }
  }
  
  private boolean dispatchLongPress(View paramView, int paramInt, long paramLong)
  {
    if (mOnItemLongClickListener != null) {}
    for (boolean bool1 = mOnItemLongClickListener.onItemLongClick(this, mDownTouchView, mDownTouchPosition, paramLong);; bool1 = false)
    {
      boolean bool2 = bool1;
      if (!bool1)
      {
        mContextMenuInfo = new AdapterContextMenuInfo(paramView, paramInt, paramLong);
        bool2 = super.showContextMenuForChild(this);
      }
      if (bool2) {
        performHapticFeedback(0);
      }
      return bool2;
    }
  }
  
  private void dispatchPress(View paramView)
  {
    if (paramView != null) {
      paramView.setPressed(true);
    }
    setPressed(true);
  }
  
  private void dispatchUnpress()
  {
    int i = getChildCount() - 1;
    while (i >= 0)
    {
      getChildAt(i).setPressed(false);
      i -= 1;
    }
    setPressed(false);
  }
  
  private void fillToGalleryLeft()
  {
    if (mIsRtl)
    {
      fillToGalleryLeftRtl();
      return;
    }
    fillToGalleryLeftLtr();
  }
  
  private void fillToGalleryLeftLtr()
  {
    int k = mSpacing;
    int m = getPaddingLeft();
    View localView = getChildAt(0);
    int j;
    int i;
    if (localView != null)
    {
      j = mFirstPosition - 1;
      i = localView.getLeft() - k;
    }
    while ((i > m) && (j >= 0))
    {
      localView = makeAndAddView(j, j - mSelectedPosition, i, false);
      mFirstPosition = j;
      i = localView.getLeft() - k;
      j -= 1;
      continue;
      i = getRight() - getLeft() - getPaddingRight();
      mShouldStopFling = true;
      j = 0;
    }
  }
  
  private void fillToGalleryLeftRtl()
  {
    int k = mSpacing;
    int m = getPaddingLeft();
    int i = getChildCount();
    View localView = getChildAt(i - 1);
    int j;
    if (localView != null)
    {
      j = mFirstPosition + i;
      i = localView.getLeft() - k;
    }
    while ((i > m) && (j < mItemCount))
    {
      i = makeAndAddView(j, j - mSelectedPosition, i, false).getLeft() - k;
      j += 1;
      continue;
      j = mItemCount - 1;
      mFirstPosition = j;
      i = getRight() - getLeft() - getPaddingRight();
      mShouldStopFling = true;
    }
  }
  
  private void fillToGalleryRight()
  {
    if (mIsRtl)
    {
      fillToGalleryRightRtl();
      return;
    }
    fillToGalleryRightLtr();
  }
  
  private void fillToGalleryRightLtr()
  {
    int k = mSpacing;
    int m = getRight();
    int n = getLeft();
    int i1 = getPaddingRight();
    int i = getChildCount();
    int i2 = mItemCount;
    View localView = getChildAt(i - 1);
    int j;
    if (localView != null)
    {
      j = mFirstPosition + i;
      i = localView.getRight() + k;
    }
    while ((i < m - n - i1) && (j < i2))
    {
      i = makeAndAddView(j, j - mSelectedPosition, i, true).getRight() + k;
      j += 1;
      continue;
      j = mItemCount - 1;
      mFirstPosition = j;
      i = getPaddingLeft();
      mShouldStopFling = true;
    }
  }
  
  private void fillToGalleryRightRtl()
  {
    int j = 0;
    int k = mSpacing;
    int m = getRight();
    int n = getLeft();
    int i1 = getPaddingRight();
    View localView = getChildAt(0);
    int i;
    if (localView != null)
    {
      j = mFirstPosition - 1;
      i = localView.getRight() + k;
    }
    while ((i < m - n - i1) && (j >= 0))
    {
      localView = makeAndAddView(j, j - mSelectedPosition, i, true);
      mFirstPosition = j;
      i = localView.getRight() + k;
      j -= 1;
      continue;
      i = getPaddingLeft();
      mShouldStopFling = true;
    }
  }
  
  private int getCenterOfEnhanceGallery()
  {
    return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2 + getPaddingLeft();
  }
  
  private static int getCenterOfView(View paramView)
  {
    return paramView.getLeft() + paramView.getWidth() / 2;
  }
  
  private void layoutChildren()
  {
    int k = mSpacing;
    int n = getPaddingLeft();
    int i1 = getRight() - getLeft() - getPaddingRight();
    int m = mItemCount;
    if (mIsRtl)
    {
      i = mFirstPosition;
      j = i1 - mSpacing;
      while ((j > n) && (i < m))
      {
        j = makeAndAddView(i, i - mSelectedPosition, j, false).getLeft() - k;
        i += 1;
      }
    }
    int i = mFirstPosition;
    int j = n + k;
    while ((j < i1) && (i < m))
    {
      j = makeAndAddView(i, i - mSelectedPosition, j, true).getRight() + k;
      i += 1;
    }
  }
  
  private View makeAndAddView(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    if (!mDataChanged)
    {
      localView = mRecycler.get(paramInt1);
      if (localView != null)
      {
        setUpChild(localView, paramInt2, paramInt3, paramBoolean);
        return localView;
      }
    }
    View localView = mAdapter.getView(paramInt1, null, this);
    setUpChild(localView, paramInt2, paramInt3, paramBoolean);
    return localView;
  }
  
  private void offsetChildrenLeftAndRight(int paramInt)
  {
    int i = getChildCount() - 1;
    while (i >= 0)
    {
      getChildAt(i).offsetLeftAndRight(paramInt);
      i -= 1;
    }
  }
  
  private void onFinishedMovement()
  {
    if (mSuppressSelectionChanged)
    {
      mSuppressSelectionChanged = false;
      super.selectionChanged();
    }
    invalidate();
  }
  
  private void scrollIntoSlots()
  {
    int k = getChildCount();
    if ((k == 0) || (mSelectedChild == null))
    {
      if (mLastScrollState != 0) {
        reportScrollStateChange(0);
      }
      return;
    }
    View localView1 = getChildAt(0);
    View localView2 = getChildAt(k - 1);
    int i;
    int m;
    if (mIsRtl)
    {
      i = getWidth() - getPaddingRight() - mSpacing;
      m = getPaddingLeft();
      if ((mTouchMode == 2) && (mFirstPosition + k == mItemCount)) {
        i = mSpacing + m - localView2.getLeft();
      }
    }
    for (;;)
    {
      if (i != 0)
      {
        if (mLastScrollState != 2) {
          reportScrollStateChange(2);
        }
        mFlingRunnable.startUsingDistance(i);
        return;
        if (localView1.getRight() != i)
        {
          if (getCenterOfView(localView1) >= i) {}
          for (int j = i - getChildAt(1).getRight();; j = i - localView1.getRight())
          {
            i = j;
            if (k + mFirstPosition != mItemCount) {
              break;
            }
            i = j;
            if (localView2.getLeft() + j <= m) {
              break;
            }
            i = m - localView2.getLeft() + mSpacing;
            break;
          }
          i = getPaddingLeft() + mSpacing;
          m = getWidth() - getPaddingRight();
          if ((mTouchMode == 2) && (mFirstPosition + k == mItemCount))
          {
            i = m - localView2.getRight() - mSpacing;
            continue;
          }
          if (localView1.getLeft() != i)
          {
            if (getCenterOfView(localView1) < i) {}
            for (j = i - getChildAt(1).getLeft();; j = i - localView1.getLeft())
            {
              i = j;
              if (k + mFirstPosition != mItemCount) {
                break;
              }
              i = j;
              if (localView2.getRight() + j == m - mSpacing) {
                break;
              }
              i = m - localView2.getRight() - mSpacing;
              break;
            }
          }
        }
      }
      else
      {
        if (mLastScrollState != 0) {
          reportScrollStateChange(0);
        }
        onFinishedMovement();
        mTouchMode = -1;
        return;
      }
      i = 0;
    }
  }
  
  private void setSelectionView()
  {
    if (mSelectedChild == null) {}
    int i;
    do
    {
      return;
      i = mFirstPosition;
    } while (i == mSelectedPosition);
    setSelectedPositionInt(i);
    setNextSelectedPositionInt(i);
    checkSelectionChanged();
  }
  
  private void setUpChild(View paramView, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    boolean bool = false;
    Object localObject = paramView.getLayoutParams();
    int i;
    label35:
    int j;
    int k;
    if (localObject == null)
    {
      localObject = (LayoutParams)generateDefaultLayoutParams();
      if (paramBoolean == mIsRtl) {
        break label230;
      }
      i = -1;
      addViewInLayout(paramView, i, (ViewGroup.LayoutParams)localObject);
      if (mChoiceMode == 0)
      {
        if (paramInt1 == 0) {
          bool = true;
        }
        paramView.setSelected(bool);
      }
      paramInt1 = ViewGroup.getChildMeasureSpec(mHeightMeasureSpec, mSpinnerPadding.top + mSpinnerPadding.bottom, height);
      paramView.measure(ViewGroup.getChildMeasureSpec(mWidthMeasureSpec, mSpinnerPadding.left + mSpinnerPadding.right, width), paramInt1);
      i = calculateTop(paramView, true);
      j = paramView.getMeasuredHeight();
      k = paramView.getMeasuredWidth();
      if (!paramBoolean) {
        break label236;
      }
      paramInt1 = paramInt2;
      paramInt2 = k + paramInt2;
    }
    for (;;)
    {
      paramView.layout(paramInt1, i, paramInt2, i + j);
      if ((mChoiceMode == 1) && (mDragEnable)) {
        paramView.setOnDragListener(new EnhanceGallery.3(this));
      }
      return;
      if ((localObject instanceof LayoutParams))
      {
        localObject = (LayoutParams)localObject;
        break;
      }
      localObject = (LayoutParams)generateLayoutParams((ViewGroup.LayoutParams)localObject);
      break;
      label230:
      i = 0;
      break label35;
      label236:
      paramInt1 = paramInt2 - k;
    }
  }
  
  private void updateOnScreenCheckedViews()
  {
    int j = 0;
    int k = mFirstPosition;
    int m = getChildCount();
    int i;
    View localView;
    int n;
    if (getContextgetApplicationInfotargetSdkVersion >= 11)
    {
      i = 1;
      if (j >= m) {
        return;
      }
      localView = getChildAt(j);
      n = k + j;
      if (!(localView instanceof Checkable)) {
        break label87;
      }
      ((Checkable)localView).setChecked(mCheckStates.get(n));
    }
    for (;;)
    {
      j += 1;
      break;
      i = 0;
      break;
      label87:
      if (i != 0) {
        localView.setActivated(mCheckStates.get(n));
      }
    }
  }
  
  private void updateSelectedItemMetadata()
  {
    View localView1 = mSelectedChild;
    View localView2 = getChildAt(mSelectedPosition - mFirstPosition);
    mSelectedChild = localView2;
    if (localView2 == null) {}
    do
    {
      do
      {
        return;
      } while (mChoiceMode != 0);
      localView2.setSelected(true);
      localView2.setFocusable(true);
      if (hasFocus()) {
        localView2.requestFocus();
      }
    } while ((localView1 == null) || (localView1 == localView2));
    localView1.setSelected(false);
    localView1.setFocusable(false);
  }
  
  public void changeChildAlphaWhenDragView(boolean paramBoolean)
  {
    mChangeChildAlphaWhenDragView = paramBoolean;
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return paramLayoutParams instanceof LayoutParams;
  }
  
  protected int computeHorizontalScrollExtent()
  {
    return 1;
  }
  
  protected int computeHorizontalScrollOffset()
  {
    return mSelectedPosition;
  }
  
  protected int computeHorizontalScrollRange()
  {
    return mItemCount;
  }
  
  void confirmCheckedPositionsById()
  {
    mCheckStates.clear();
    int i = 0;
    int j = 0;
    long l2;
    int n;
    int k;
    if (i < mCheckedIdStates.size())
    {
      l2 = mCheckedIdStates.keyAt(i);
      n = ((Integer)mCheckedIdStates.valueAt(i)).intValue();
      long l1 = -1L;
      if (n < mItemCount) {
        l1 = mAdapter.getItemId(n);
      }
      if ((n >= mItemCount) || (l2 != l1))
      {
        k = Math.max(0, n - 20);
        m = Math.min(n + 20, mItemCount);
        label116:
        if (k >= m) {
          break label278;
        }
        if (l2 == mAdapter.getItemId(k))
        {
          mCheckStates.put(k, true);
          mCheckedIdStates.setValueAt(i, Integer.valueOf(k));
        }
      }
    }
    label278:
    for (int m = 1;; m = 0)
    {
      k = i;
      if (m == 0)
      {
        mCheckedIdStates.delete(l2);
        mCheckedItemCount -= 1;
        if ((mChoiceActionMode != null) && (mMultiChoiceModeCallback != null)) {
          mMultiChoiceModeCallback.onItemCheckedStateChanged(mChoiceActionMode, n, l2, false);
        }
        k = i - 1;
        j = 1;
      }
      for (i = j;; i = j)
      {
        j = i;
        i = k + 1;
        break;
        k += 1;
        break label116;
        mCheckStates.put(n, true);
        k = i;
      }
      if ((j != 0) && (mChoiceActionMode != null)) {
        mChoiceActionMode.invalidate();
      }
      return;
    }
  }
  
  ContextMenu.ContextMenuInfo createContextMenuInfo(View paramView, int paramInt, long paramLong)
  {
    return new AdapterContextMenuInfo(paramView, paramInt, paramLong);
  }
  
  protected void dispatchSetPressed(boolean paramBoolean)
  {
    if (mSelectedChild != null) {
      mSelectedChild.setPressed(paramBoolean);
    }
  }
  
  public void dispatchSetSelected(boolean paramBoolean) {}
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams()
  {
    return new LayoutParams(-2, -2);
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return new LayoutParams(paramLayoutParams);
  }
  
  public int getCheckedItemCount()
  {
    return mCheckedItemCount;
  }
  
  public long[] getCheckedItemIds()
  {
    int i = 0;
    Object localObject;
    if ((mChoiceMode == 0) || (mCheckedIdStates == null) || (mAdapter == null))
    {
      localObject = new long[0];
      return (long[])localObject;
    }
    LongSparseArray localLongSparseArray = mCheckedIdStates;
    int j = localLongSparseArray.size();
    long[] arrayOfLong = new long[j];
    for (;;)
    {
      localObject = arrayOfLong;
      if (i >= j) {
        break;
      }
      arrayOfLong[i] = localLongSparseArray.keyAt(i);
      i += 1;
    }
  }
  
  public SparseBooleanArray getCheckedItemPositions()
  {
    if (mChoiceMode != 0) {
      return mCheckStates;
    }
    return null;
  }
  
  int getChildHeight(View paramView)
  {
    return paramView.getMeasuredHeight();
  }
  
  protected ContextMenu.ContextMenuInfo getContextMenuInfo()
  {
    return mContextMenuInfo;
  }
  
  public void invalidateViews()
  {
    mDataChanged = true;
    mItemCount = mAdapter.getCount();
    requestLayout();
    invalidate();
  }
  
  void invokeOnItemScrollListener()
  {
    if (mOnScrollListener != null) {
      mOnScrollListener.onScroll(this, mFirstPosition, getChildCount(), mItemCount);
    }
  }
  
  public boolean isItemChecked(int paramInt)
  {
    if ((mChoiceMode != 0) && (mCheckStates != null)) {
      return mCheckStates.get(paramInt);
    }
    return false;
  }
  
  void layout(int paramInt, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      if (getLayoutDirection() != 1) {
        break label88;
      }
    }
    label88:
    for (paramBoolean = true;; paramBoolean = false)
    {
      mIsRtl = paramBoolean;
      if (mDataChanged) {
        handleDataChanged();
      }
      if ((mDataChanged) && (mChoiceMode == 1) && (mAdapter != null) && (mAdapter.hasStableIds())) {
        confirmCheckedPositionsById();
      }
      if (mItemCount != 0) {
        break;
      }
      invokeOnItemScrollListener();
      resetList();
      return;
    }
    if (mNextSelectedPosition >= 0) {
      setSelectedPositionInt(mNextSelectedPosition);
    }
    recycleAllViews();
    detachAllViewsFromParent();
    mFirstPosition = mSelectedPosition;
    layoutChildren();
    mRecycler.clear();
    invalidate();
    checkSelectionChanged();
    mDataChanged = false;
    mNeedSync = false;
    setNextSelectedPositionInt(mSelectedPosition);
    updateSelectedItemMetadata();
    mDeltaLength = 0;
    View localView = getChildAt(0);
    if (localView != null)
    {
      mChildWidth = localView.getWidth();
      mDeltaLength = (getWidth() - getPaddingLeft() - getPaddingRight() - mItemCount * (mChildWidth + mSpacing));
      if ((mDeltaLength <= 0) || (mScrollEnableWhenLessContent)) {
        break label312;
      }
      if ((mFirstPosition != 0) && (mSelectedPosition < mItemCount))
      {
        if (!mIsRtl) {
          break label294;
        }
        paramInt = -mSelectedPosition * (mChildWidth + mSpacing);
        trackMotionScroll(paramInt);
        scrollIntoSlots();
      }
    }
    for (;;)
    {
      invokeOnItemScrollListener();
      return;
      label294:
      paramInt = mSelectedPosition * (mChildWidth + mSpacing);
      break;
      label312:
      if (mDeltaLength <= 0)
      {
        paramInt = getChildCount();
        int i;
        if (mIsRtl)
        {
          i = getPaddingLeft() + mSpacing;
          if ((mFirstPosition + paramInt == mItemCount) && (getChildAt(paramInt - 1).getLeft() != i))
          {
            trackMotionScroll(i - getChildAt(paramInt - 1).getLeft());
            scrollIntoSlots();
          }
        }
        else
        {
          i = getWidth() - getPaddingRight() - mSpacing;
          if ((mFirstPosition + paramInt == mItemCount) && (getChildAt(paramInt - 1).getRight() != i))
          {
            trackMotionScroll(i - getChildAt(paramInt - 1).getRight());
            scrollIntoSlots();
          }
        }
      }
    }
  }
  
  void onCancel()
  {
    onUp();
  }
  
  public boolean onDown(MotionEvent paramMotionEvent)
  {
    if ((mTouchMode == 2) || (mTouchMode == 4))
    {
      mTouchMode = 1;
      reportScrollStateChange(1);
    }
    for (;;)
    {
      mFlingRunnable.stop(false);
      mDownTouchPosition = pointToPosition((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
      if (mDownTouchPosition >= 0)
      {
        mDownTouchView = getChildAt(mDownTouchPosition - mFirstPosition);
        mDownTouchView.setPressed(true);
      }
      mMotionX = ((int)paramMotionEvent.getX());
      mMotionY = ((int)paramMotionEvent.getY());
      mDownFirstPosition = mFirstPosition;
      mDownLastPosition = (mFirstPosition + getChildCount() - 1);
      mIsFirstScroll = true;
      return true;
      mTouchMode = 0;
    }
  }
  
  public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    if ((mDeltaLength > 0) && (!mScrollEnableWhenLessContent)) {
      return false;
    }
    if (!mShouldCallbackDuringFling)
    {
      removeCallbacks(mDisableSuppressSelectionChangedRunnable);
      if (!mSuppressSelectionChanged) {
        mSuppressSelectionChanged = true;
      }
    }
    int j = getChildCount();
    switch (mTouchMode)
    {
    }
    for (;;)
    {
      return true;
      if (Math.abs(paramFloat1) < 1500.0F) {
        break;
      }
      mTouchMode = 2;
      int i = (int)Math.floor((getWidth() - getPaddingLeft() - getPaddingRight()) / (mChildWidth + mSpacing)) * (mChildWidth + mSpacing);
      if (paramFloat1 > 0.0F) {
        if (mIsRtl)
        {
          paramMotionEvent1 = getChildAt(mDownLastPosition - mFirstPosition);
          i = getWidth() - getPaddingRight() - mSpacing;
          if (paramMotionEvent1 != null) {
            i -= paramMotionEvent1.getRight();
          }
        }
      }
      for (;;)
      {
        reportScrollStateChange(2);
        mFlingRunnable.startUsingDistance(i);
        break;
        i -= getChildAt(getChildCount() - 1).getRight();
        continue;
        paramMotionEvent1 = getChildAt(mDownFirstPosition - mFirstPosition);
        if (paramMotionEvent1 != null)
        {
          i -= paramMotionEvent1.getLeft() - getPaddingLeft() - mSpacing;
        }
        else
        {
          i = getPaddingLeft() + mSpacing - getChildAt(0).getLeft() + i;
          continue;
          if (mIsRtl)
          {
            paramMotionEvent1 = getChildAt(mDownFirstPosition - mFirstPosition);
            j = getWidth() - getPaddingRight() - mSpacing;
            if (paramMotionEvent1 != null) {
              i = -(i - (j - paramMotionEvent1.getRight()));
            } else {
              i = -(i - (j - getChildAt(0).getRight()));
            }
          }
          else
          {
            paramMotionEvent1 = getChildAt(mDownLastPosition - mFirstPosition);
            if (paramMotionEvent1 != null) {
              i = getPaddingLeft() + mSpacing - paramMotionEvent1.getLeft();
            } else {
              i = getPaddingLeft() + mSpacing - getChildAt(j - 1).getLeft();
            }
          }
        }
      }
      mTouchMode = 4;
    }
  }
  
  protected void onFocusChanged(boolean paramBoolean, int paramInt, Rect paramRect)
  {
    super.onFocusChanged(paramBoolean, paramInt, paramRect);
    if ((paramBoolean) && (mSelectedChild != null) && (mChoiceMode == 0))
    {
      mSelectedChild.requestFocus(paramInt);
      mSelectedChild.setSelected(true);
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    mInLayout = true;
    layout(0, false);
    mInLayout = false;
  }
  
  public void onLongPress(MotionEvent paramMotionEvent)
  {
    if (mDownTouchPosition < 0) {
      return;
    }
    int i;
    long l;
    if (mChoiceMode == 1)
    {
      paramMotionEvent = getChildAt(mDownTouchPosition - mFirstPosition);
      if (paramMotionEvent != null)
      {
        i = mDownTouchPosition;
        l = mAdapter.getItemId(mDownTouchPosition);
        if (mDataChanged) {
          break label119;
        }
      }
    }
    label119:
    for (boolean bool = performLongPress(paramMotionEvent, i, l);; bool = false)
    {
      if (bool)
      {
        mTouchMode = -1;
        setPressed(false);
        paramMotionEvent.setPressed(false);
      }
      performHapticFeedback(0);
      l = getItemIdAtPosition(mDownTouchPosition);
      dispatchLongPress(mDownTouchView, mDownTouchPosition, l);
      return;
    }
  }
  
  public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    if ((mDeltaLength > 0) && (!mScrollEnableWhenLessContent)) {
      return false;
    }
    getParent().requestDisallowInterceptTouchEvent(true);
    int j;
    if (!mShouldCallbackDuringFling)
    {
      if (mIsFirstScroll)
      {
        if (!mSuppressSelectionChanged) {
          mSuppressSelectionChanged = true;
        }
        postDelayed(mDisableSuppressSelectionChangedRunnable, 250L);
      }
      if (mIsFirstScroll) {
        reportScrollStateChange(1);
      }
      mTouchMode = 1;
      getChildCount();
      j = (int)paramFloat1;
      if (mMaxOverScrollDistance > getWidth()) {
        mMaxOverScrollDistance = mDefaultMaxOverScrollDistance;
      }
      i = j;
      if (mCurrentOverScrollDistance != 0)
      {
        i = j;
        if (mMaxOverScrollDistance != 0)
        {
          mTouchMode = 3;
          if (Math.abs(mCurrentOverScrollDistance) < mMaxOverScrollDistance) {
            break label189;
          }
        }
      }
    }
    for (int i = 0;; i = (int)(j * (1.0F - paramFloat1)))
    {
      if (i != 0) {
        trackMotionScroll(i * -1);
      }
      mIsFirstScroll = false;
      return true;
      if (!mSuppressSelectionChanged) {
        break;
      }
      mSuppressSelectionChanged = false;
      break;
      label189:
      paramFloat1 = Math.abs(mCurrentOverScrollDistance) * 1.0F / mMaxOverScrollDistance;
    }
  }
  
  public void onShowPress(MotionEvent paramMotionEvent) {}
  
  public boolean onSingleTapUp(MotionEvent paramMotionEvent)
  {
    if (mDownTouchPosition >= 0)
    {
      if (mChoiceMode != 0) {
        break label60;
      }
      if ((mShouldCallbackOnUnselectedItemClick) || (mDownTouchPosition == mSelectedPosition)) {
        performItemClick(mDownTouchView, mDownTouchPosition, mAdapter.getItemId(mDownTouchPosition));
      }
    }
    label60:
    while (mChoiceMode != 1) {
      return true;
    }
    if (mPerformClick == null) {
      mPerformClick = new PerformClick(null);
    }
    paramMotionEvent = mPerformClick;
    mClickMotionPosition = mDownTouchPosition;
    paramMotionEvent.rememberWindowAttachCount();
    post(paramMotionEvent);
    return true;
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    postDelayed(new EnhanceGallery.2(this), 200L);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = mGestureDetector.onTouchEvent(paramMotionEvent);
    int i = paramMotionEvent.getAction();
    if (i == 1) {
      onUp();
    }
    while (i != 3) {
      return bool;
    }
    onCancel();
    return bool;
  }
  
  void onUp()
  {
    switch (mTouchMode)
    {
    }
    for (;;)
    {
      dispatchUnpress();
      return;
      scrollIntoSlots();
      continue;
      scrollIntoSlots();
      continue;
      if (mCurrentOverScrollDistance != 0)
      {
        if (mLastScrollState != 2) {
          reportScrollStateChange(2);
        }
        mFlingRunnable.startSpringback();
      }
    }
  }
  
  public boolean performItemClicks(View paramView, int paramInt, long paramLong)
  {
    boolean bool;
    label92:
    label107:
    int i;
    label139:
    int j;
    if (mChoiceMode != 0) {
      if ((mChoiceMode == 1) && (mChoiceActionMode != null)) {
        if (!mCheckStates.get(paramInt, false))
        {
          bool = true;
          mCheckStates.put(paramInt, bool);
          if ((mCheckedIdStates != null) && (mAdapter.hasStableIds()))
          {
            if (!bool) {
              break label201;
            }
            mCheckedIdStates.put(mAdapter.getItemId(paramInt), Integer.valueOf(paramInt));
          }
          if (!bool) {
            break label221;
          }
          mCheckedItemCount += 1;
          if ((mChoiceActionMode == null) || (mMultiChoiceModeCallback == null)) {
            break label236;
          }
          mMultiChoiceModeCallback.onItemCheckedStateChanged(mChoiceActionMode, paramInt, paramLong, bool);
          i = 0;
          j = 1;
          label142:
          k = i;
          if (j != 0) {
            updateOnScreenCheckedViews();
          }
        }
      }
    }
    for (int k = i;; k = 1)
    {
      if ((k != 0) && (mOnItemClickListener != null))
      {
        if (paramView != null) {
          paramView.sendAccessibilityEvent(1);
        }
        mOnItemClickListener.onItemClick(this, paramView, paramInt, paramLong);
        return true;
        bool = false;
        break;
        label201:
        mCheckedIdStates.delete(mAdapter.getItemId(paramInt));
        break label92;
        label221:
        mCheckedItemCount -= 1;
        break label107;
      }
      return false;
      label236:
      i = 1;
      break label139;
      j = 0;
      i = 1;
      break label142;
    }
  }
  
  boolean performLongPress(View paramView, int paramInt, long paramLong)
  {
    if (mChoiceMode == 1)
    {
      Object localObject;
      if (mChoiceActionMode == null)
      {
        if (mChoiceActionMode == null)
        {
          localObject = startActionMode(mMultiChoiceModeCallback);
          mChoiceActionMode = ((ActionMode)localObject);
          if (localObject == null) {}
        }
      }
      else
      {
        mDragAndDropPosition = paramInt;
        View localView = paramView.findViewById(16908289);
        if ((localView != null) && ((localView instanceof Checkable))) {
          ((Checkable)localView).setChecked(true);
        }
        Rect localRect = mTouchFrame;
        localObject = localRect;
        if (localRect == null)
        {
          mTouchFrame = new Rect();
          localObject = mTouchFrame;
        }
        paramView.getHitRect((Rect)localObject);
        mDragOffsetX = Math.max(0, mMotionX - left);
        mDragOffsetY = Math.max(0, mMotionY - top);
        paramView.setActivated(false);
        paramView.jumpDrawablesToCurrentState();
        if (!mDragEnable) {
          break label386;
        }
        if ((paramView instanceof DragShadowItem)) {
          localObject = (DragShadowItem)paramView;
        }
        for (mShadowBuilder = new ListViewDragShadowBuilder(((DragShadowItem)localObject).getDragView(), ((DragShadowItem)localObject).needBackground(), ((DragShadowItem)localObject).getDragViewShowPosition()); !startDragNow(null, mShadowBuilder, this, 0); mShadowBuilder = new ListViewDragShadowBuilder(paramView))
        {
          if ((localView != null) && ((localView instanceof Checkable))) {
            ((Checkable)localView).setChecked(false);
          }
          mChoiceActionMode.finish();
          mDragAndDropPosition = -1;
          if (mPerformClick == null) {
            mPerformClick = new PerformClick(null);
          }
          paramView = mPerformClick;
          mClickMotionPosition = paramInt;
          paramView.rememberWindowAttachCount();
          post(paramView);
          bool2 = true;
          return bool2;
        }
        performHapticFeedback(0);
        if (!(paramView instanceof DragShadowItem)) {
          break label378;
        }
        localObject = ((DragShadowItem)paramView).getDragView();
        if (localObject != null) {
          ((View)localObject).setAlpha(0.0F);
        }
        if (mChangeChildAlphaWhenDragView) {
          paramView.setAlpha(0.0F);
        }
      }
      for (;;)
      {
        return true;
        label378:
        paramView.setAlpha(0.0F);
        continue;
        label386:
        setItemChecked(mDragAndDropPosition, true);
      }
    }
    if (mOnItemLongClickListener != null) {}
    for (boolean bool2 = mOnItemLongClickListener.onItemLongClick(this, paramView, paramInt, paramLong);; bool2 = false)
    {
      boolean bool1 = bool2;
      if (!bool2)
      {
        mContextMenuInfo = ((AdapterContextMenuInfo)createContextMenuInfo(paramView, paramInt, paramLong));
        bool1 = super.showContextMenuForChild(this);
      }
      bool2 = bool1;
      if (!bool1) {
        break;
      }
      performHapticFeedback(0);
      return bool1;
    }
  }
  
  void reportScrollStateChange(int paramInt)
  {
    if (paramInt != mLastScrollState)
    {
      mLastScrollState = paramInt;
      if (mOnScrollListener != null) {
        mOnScrollListener.onScrollStateChanged(this, paramInt);
      }
    }
  }
  
  void selectionChanged()
  {
    if (!mSuppressSelectionChanged) {
      super.selectionChanged();
    }
  }
  
  public void setAdapter(SpinnerAdapter paramSpinnerAdapter)
  {
    super.setAdapter(paramSpinnerAdapter);
    if ((paramSpinnerAdapter != null) && (mChoiceMode != 0))
    {
      if (mCheckStates == null) {
        mCheckStates = new SparseBooleanArray();
      }
      if ((paramSpinnerAdapter.hasStableIds()) && (mCheckedIdStates == null)) {
        mCheckedIdStates = new LongSparseArray();
      }
      clearChoices();
    }
  }
  
  public void setAnimationDuration(int paramInt)
  {
    mAnimationDuration = paramInt;
  }
  
  public void setCallbackDuringFling(boolean paramBoolean)
  {
    mShouldCallbackDuringFling = paramBoolean;
  }
  
  public void setCallbackOnUnselectedItemClick(boolean paramBoolean)
  {
    mShouldCallbackOnUnselectedItemClick = paramBoolean;
  }
  
  public void setChoiceMode(int paramInt)
  {
    mChoiceMode = paramInt;
    if (mChoiceActionMode != null)
    {
      mChoiceActionMode.finish();
      mChoiceActionMode = null;
    }
    if (mChoiceMode != 0)
    {
      if (mCheckStates == null) {
        mCheckStates = new SparseBooleanArray();
      }
      if ((mCheckedIdStates == null) && (mAdapter != null) && (mAdapter.hasStableIds())) {
        mCheckedIdStates = new LongSparseArray();
      }
      if (mChoiceMode == 1)
      {
        clearChoices();
        setLongClickable(true);
      }
    }
  }
  
  public void setDragEnable(boolean paramBoolean)
  {
    mDragEnable = paramBoolean;
  }
  
  public void setDragItemBackgroundResources(int[] paramArrayOfInt)
  {
    if (paramArrayOfInt != null)
    {
      if (paramArrayOfInt.length > 0) {
        mDragViewBackground = paramArrayOfInt[0];
      }
      if (paramArrayOfInt.length > 1) {
        mDragViewBackgroundFilter = paramArrayOfInt[1];
      }
      if (paramArrayOfInt.length > 2) {
        mDragViewBackgroundDelete = paramArrayOfInt[2];
      }
    }
  }
  
  public void setGravity(int paramInt)
  {
    if (mGravity != paramInt)
    {
      mGravity = paramInt;
      requestLayout();
    }
  }
  
  public void setItemChecked(int paramInt, boolean paramBoolean)
  {
    if (mChoiceMode == 0) {}
    label158:
    label224:
    label339:
    label366:
    for (;;)
    {
      return;
      if ((paramBoolean) && (mChoiceActionMode == null) && (mChoiceMode == 1))
      {
        if ((mMultiChoiceModeCallback == null) || (!mMultiChoiceModeCallback.hasWrappedCallback())) {
          throw new IllegalStateException("StaggeredGridView: attempted to start selection mode for CHOICE_MODE_MULTIPLE_MODAL but no choice mode callback was supplied. Call setMultiChoiceModeListener to set a callback.");
        }
        mChoiceActionMode = startActionMode(mMultiChoiceModeCallback);
      }
      if (mChoiceMode == 1)
      {
        boolean bool = mCheckStates.get(paramInt);
        mCheckStates.put(paramInt, paramBoolean);
        if ((mCheckedIdStates != null) && (mAdapter.hasStableIds()))
        {
          if (paramBoolean) {
            mCheckedIdStates.put(mAdapter.getItemId(paramInt), Integer.valueOf(paramInt));
          }
        }
        else
        {
          if (bool != paramBoolean)
          {
            if (!paramBoolean) {
              break label224;
            }
            mCheckedItemCount += 1;
          }
          if (mChoiceActionMode != null)
          {
            long l = mAdapter.getItemId(paramInt);
            mMultiChoiceModeCallback.onItemCheckedStateChanged(mChoiceActionMode, paramInt, l, paramBoolean);
          }
        }
      }
      for (;;)
      {
        if (mInLayout) {
          break label366;
        }
        invalidateViews();
        return;
        mCheckedIdStates.delete(mAdapter.getItemId(paramInt));
        break;
        mCheckedItemCount -= 1;
        break label158;
        if ((mCheckedIdStates != null) && (mAdapter.hasStableIds())) {}
        for (int i = 1;; i = 0)
        {
          if ((paramBoolean) || (isItemChecked(paramInt)))
          {
            mCheckStates.clear();
            if (i != 0) {
              mCheckedIdStates.clear();
            }
          }
          if (!paramBoolean) {
            break label339;
          }
          mCheckStates.put(paramInt, true);
          if (i != 0) {
            mCheckedIdStates.put(mAdapter.getItemId(paramInt), Integer.valueOf(paramInt));
          }
          mCheckedItemCount = 1;
          break;
        }
        if ((mCheckStates.size() == 0) || (!mCheckStates.valueAt(0))) {
          mCheckedItemCount = 0;
        }
      }
    }
  }
  
  public void setMaxOverScrollDistance(int paramInt)
  {
    if (paramInt < 0)
    {
      mMaxOverScrollDistance = mDefaultMaxOverScrollDistance;
      return;
    }
    mMaxOverScrollDistance = paramInt;
  }
  
  public void setMultiChoiceModeListener(MultiChoiceModeListener paramMultiChoiceModeListener)
  {
    if (mMultiChoiceModeCallback == null) {
      mMultiChoiceModeCallback = new MultiChoiceModeWrapper();
    }
    mMultiChoiceModeCallback.setWrapped(paramMultiChoiceModeListener);
  }
  
  public void setOnScrollListener(OnScrollListener paramOnScrollListener)
  {
    mOnScrollListener = paramOnScrollListener;
    invokeOnItemScrollListener();
  }
  
  public void setScrollEnableWhenLessContent(boolean paramBoolean)
  {
    mScrollEnableWhenLessContent = paramBoolean;
  }
  
  void setSelectedPositionInt(int paramInt)
  {
    super.setSelectedPositionInt(paramInt);
    updateSelectedItemMetadata();
  }
  
  public void setSpacing(int paramInt)
  {
    mSpacing = paramInt;
  }
  
  public boolean showContextMenu()
  {
    if ((isPressed()) && (mSelectedPosition >= 0)) {
      return dispatchLongPress(getChildAt(mSelectedPosition - mFirstPosition), mSelectedPosition, mSelectedRowId);
    }
    return false;
  }
  
  public boolean showContextMenuForChild(View paramView)
  {
    int i = getPositionForView(paramView);
    if (i < 0) {
      return false;
    }
    return dispatchLongPress(paramView, i, mAdapter.getItemId(i));
  }
  
  boolean startDragNow(ClipData paramClipData, View.DragShadowBuilder paramDragShadowBuilder, Object paramObject, int paramInt)
  {
    return false;
  }
  
  boolean trackMotionScroll(int paramInt)
  {
    boolean bool2 = true;
    int j = getChildCount();
    if ((j == 0) || (paramInt == 0)) {
      return false;
    }
    boolean bool1;
    if (paramInt < 0)
    {
      bool1 = true;
      if (!mIsRtl) {
        break label264;
      }
      if ((mFirstPosition != 0) || (getChildAt(0).getRight() < getWidth() - getPaddingRight() - mSpacing) || (paramInt > 0)) {
        break label254;
      }
      i = 1;
      label70:
      if ((mFirstPosition + j != mItemCount) || (getChildAt(j - 1).getLeft() < getPaddingLeft()) || (paramInt < 0)) {
        break label259;
      }
      j = 1;
      label106:
      if ((j == 0) && (i == 0)) {
        break label524;
      }
    }
    label140:
    label254:
    label259:
    label264:
    label297:
    label354:
    label366:
    label409:
    label524:
    for (int i = 1;; i = 0)
    {
      offsetChildrenLeftAndRight(paramInt);
      int k;
      if (i == 0)
      {
        detachOffScreenChildren(bool1);
        if (bool1)
        {
          fillToGalleryRight();
          mRecycler.clear();
          setSelectionView();
        }
      }
      else
      {
        mCurrentOverScrollDistance = 0;
        paramInt = getChildCount();
        if (!mIsRtl) {
          break label409;
        }
        i = getChildAt(0).getRight();
        j = getChildAt(paramInt - 1).getLeft();
        k = getWidth() - getPaddingRight() - mSpacing;
        if ((mFirstPosition != 0) || (i >= k)) {
          break label366;
        }
        mCurrentOverScrollDistance = (k - i);
        bool1 = bool2;
      }
      for (;;)
      {
        invokeOnItemScrollListener();
        onScrollChanged(0, 0, 0, 0);
        invalidate();
        return bool1;
        bool1 = false;
        break;
        i = 0;
        break label70;
        j = 0;
        break label106;
        if ((mFirstPosition == 0) && (getChildAt(0).getLeft() >= getPaddingLeft() + mSpacing) && (paramInt >= 0))
        {
          i = 1;
          if ((mFirstPosition + j != mItemCount) || (getChildAt(j - 1).getRight() > getWidth() - getPaddingRight()) || (paramInt > 0)) {
            break label354;
          }
        }
        for (j = 1;; j = 0)
        {
          k = i;
          i = j;
          j = k;
          break;
          i = 0;
          break label297;
        }
        fillToGalleryLeft();
        break label140;
        if ((paramInt + mFirstPosition == mItemCount) && (j > getPaddingLeft()))
        {
          mCurrentOverScrollDistance = (getPaddingLeft() + mSpacing - j);
          bool1 = bool2;
          continue;
          i = getChildAt(0).getLeft();
          j = getChildAt(paramInt - 1).getRight();
          k = getPaddingLeft() + mSpacing;
          int m = getWidth() - getPaddingRight();
          if ((mFirstPosition == 0) && (i > k))
          {
            mCurrentOverScrollDistance = (k - i);
            bool1 = bool2;
            continue;
          }
          if ((paramInt + mFirstPosition == mItemCount) && (j < m))
          {
            mCurrentOverScrollDistance = (m - j - mSpacing);
            bool1 = bool2;
            continue;
          }
        }
        bool1 = false;
      }
    }
  }
  
  public static class AdapterContextMenuInfo
    implements ContextMenu.ContextMenuInfo
  {
    public long id;
    public int position;
    public View targetView;
    
    public AdapterContextMenuInfo(View paramView, int paramInt, long paramLong)
    {
      targetView = paramView;
      position = paramInt;
      id = paramLong;
    }
  }
  
  public static abstract interface DragShadowItem
  {
    public abstract View getDragView();
    
    public abstract Point getDragViewShowPosition();
    
    public abstract boolean needBackground();
  }
  
  class FlingRunnable
    implements Runnable
  {
    private int mLastDelta;
    private int mLastFlingX;
    private int mLastOverFlingX = 0;
    private OverScroller mScroller = new OverScroller(getContext());
    
    public FlingRunnable()
    {
      mScroller.setEnableMZOverScroll(true, true);
    }
    
    private void endFling(boolean paramBoolean)
    {
      mScroller.forceFinished(true);
      if (paramBoolean)
      {
        EnhanceGallery.this.scrollIntoSlots();
        return;
      }
      reportScrollStateChange(0);
    }
    
    private void startCommon()
    {
      removeCallbacks(this);
    }
    
    public void run()
    {
      if (mItemCount == 0) {
        endFling(true);
      }
      OverScroller localOverScroller;
      do
      {
        return;
        localOverScroller = mScroller;
        switch (mTouchMode)
        {
        default: 
          EnhanceGallery.access$202(EnhanceGallery.this, -1);
        }
      } while (mLastScrollState == 0);
      reportScrollStateChange(0);
      return;
      EnhanceGallery.access$1102(EnhanceGallery.this, false);
      boolean bool1 = localOverScroller.computeScrollOffset();
      int i = localOverScroller.getCurrX();
      int j = mLastFlingX - i;
      boolean bool2 = trackMotionScroll(j);
      if ((bool1) && (!mShouldStopFling) && (!bool2))
      {
        mLastFlingX = i;
        mLastDelta = j;
        post(this);
        return;
      }
      if ((bool1) && (!mShouldStopFling) && (bool2))
      {
        endFling(false);
        if (mTouchMode == 2) {
          EnhanceGallery.access$202(EnhanceGallery.this, 4);
        }
        for (;;)
        {
          if (mLastScrollState != 2) {
            reportScrollStateChange(2);
          }
          startSpringback();
          return;
          EnhanceGallery.access$202(EnhanceGallery.this, 3);
        }
      }
      endFling(true);
      return;
      if (localOverScroller.computeScrollOffset())
      {
        i = localOverScroller.getCurrX();
        j = i - mLastOverFlingX;
        mLastOverFlingX = i;
        if (j != 0) {
          trackMotionScroll(-j);
        }
        invalidate();
        postOnAnimation(this);
        return;
      }
      endFling(false);
      EnhanceGallery.access$202(EnhanceGallery.this, -1);
    }
    
    public void startSpringback()
    {
      if (mScroller.springBack(mCurrentOverScrollDistance, 0, 0, 0, 0, 0))
      {
        EnhanceGallery.access$202(EnhanceGallery.this, 4);
        mLastOverFlingX = mCurrentOverScrollDistance;
        invalidate();
        postOnAnimation(this);
        return;
      }
      EnhanceGallery.access$202(EnhanceGallery.this, -1);
    }
    
    public void startUsingDistance(int paramInt)
    {
      if (paramInt == 0) {
        return;
      }
      EnhanceGallery.access$202(EnhanceGallery.this, 2);
      startCommon();
      mLastFlingX = 0;
      mScroller.setInterpolator(new DecelerateInterpolator());
      mScroller.startScroll(0, 0, -paramInt, 0, mAnimationDuration);
      postOnAnimation(this);
    }
    
    public void stop(boolean paramBoolean)
    {
      removeCallbacks(this);
      endFling(paramBoolean);
    }
  }
  
  public static class LayoutParams
    extends ViewGroup.LayoutParams
  {
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
  }
  
  class ListViewDragShadowBuilder
    extends View.DragShadowBuilder
  {
    private static final int STATE_ENTER_NORMAL = 0;
    private static final int STATE_ENTER_WARNING = 1;
    private static final int STATE_IDLE = -1;
    private Drawable mBackground;
    private Rect mBackgroundPadding;
    private int mHeight;
    private Drawable mHightLightNormal;
    private Drawable mHightLightWarning;
    private boolean mNeedBackground = true;
    private Point mShowPoint = null;
    private int mState = -1;
    private int mWidth;
    
    public ListViewDragShadowBuilder(View paramView)
    {
      this(paramView, true, null);
    }
    
    public ListViewDragShadowBuilder(View paramView, boolean paramBoolean, Point paramPoint)
    {
      super();
      mNeedBackground = paramBoolean;
      mShowPoint = paramPoint;
      if (paramView == null) {
        return;
      }
      if (paramBoolean)
      {
        mBackground = getResources().getDrawable(mDragViewBackground);
        mBackgroundPadding = new Rect();
        mBackground.getPadding(mBackgroundPadding);
        paramPoint = mBackgroundPadding;
        int j = paramView.getWidth();
        int i = paramView.getHeight();
        mWidth = (j + left + right);
        j = top;
        mHeight = (bottom + (j + i));
        mBackground.setBounds(0, 0, mWidth, mHeight);
        mHightLightNormal = getResources().getDrawable(mDragViewBackgroundFilter);
        mHightLightNormal.setBounds(0, 0, mWidth, mHeight);
        mHightLightWarning = getResources().getDrawable(mDragViewBackgroundDelete);
        mHightLightWarning.setBounds(0, 0, mWidth, mHeight);
      }
      for (;;)
      {
        EnhanceGallery.access$1702(EnhanceGallery.this, 0);
        if (mHeight <= getHeight()) {
          break;
        }
        paramPoint = new int[2];
        getLocationOnScreen(paramPoint);
        int[] arrayOfInt = new int[2];
        paramView.getLocationOnScreen(arrayOfInt);
        if (arrayOfInt[1] < paramPoint[1])
        {
          EnhanceGallery.access$1702(EnhanceGallery.this, paramPoint[1] - arrayOfInt[1]);
          EnhanceGallery.access$1702(EnhanceGallery.this, Math.min(mHeight - getHeight(), mDragScrollY));
        }
        mHeight = getHeight();
        return;
        mWidth = paramView.getWidth();
        mHeight = paramView.getHeight();
      }
    }
    
    public int getDragingState()
    {
      return mState;
    }
    
    public void onDrawShadow(Canvas paramCanvas)
    {
      if (mNeedBackground)
      {
        if (mState == 0) {
          mHightLightNormal.draw(paramCanvas);
        }
        for (;;)
        {
          paramCanvas.save();
          paramCanvas.translate(mBackgroundPadding.left, mBackgroundPadding.top - mDragScrollY);
          super.onDrawShadow(paramCanvas);
          paramCanvas.restore();
          return;
          if (mState == 1) {
            mHightLightWarning.draw(paramCanvas);
          } else {
            mBackground.draw(paramCanvas);
          }
        }
      }
      if (mDragScrollY != 0)
      {
        paramCanvas.save();
        paramCanvas.translate(0.0F, -mDragScrollY);
        super.onDrawShadow(paramCanvas);
        paramCanvas.restore();
        return;
      }
      super.onDrawShadow(paramCanvas);
    }
    
    public void onProvideShadowMetrics(Point paramPoint1, Point paramPoint2)
    {
      super.onProvideShadowMetrics(paramPoint1, paramPoint2);
      paramPoint1.set(mWidth, mHeight);
      if (mNeedBackground)
      {
        paramPoint2.set(mDragOffsetX + mBackgroundPadding.left, mDragOffsetY + mBackgroundPadding.top - mDragScrollY);
        return;
      }
      paramPoint2.set(mDragOffsetX, mDragOffsetY - mDragScrollY);
    }
    
    public void setDragingState(int paramInt)
    {
      mState = paramInt;
    }
  }
  
  public static abstract interface MultiChoiceModeListener
    extends ActionMode.Callback
  {
    public abstract void onItemCheckedStateChanged(ActionMode paramActionMode, int paramInt, long paramLong, boolean paramBoolean);
  }
  
  class MultiChoiceModeWrapper
    implements EnhanceGallery.MultiChoiceModeListener
  {
    private EnhanceGallery.MultiChoiceModeListener mWrapped;
    
    MultiChoiceModeWrapper() {}
    
    public boolean hasWrappedCallback()
    {
      return mWrapped != null;
    }
    
    public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
    {
      return mWrapped.onActionItemClicked(paramActionMode, paramMenuItem);
    }
    
    public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      if (mWrapped.onCreateActionMode(paramActionMode, paramMenu))
      {
        if (mChoiceMode == 1)
        {
          setLongClickable(true);
          return true;
        }
        setLongClickable(false);
        return true;
      }
      return false;
    }
    
    public void onDestroyActionMode(ActionMode paramActionMode)
    {
      mWrapped.onDestroyActionMode(paramActionMode);
      EnhanceGallery.access$602(EnhanceGallery.this, null);
      EnhanceGallery.this.clearChoices();
      invalidateViews();
      setLongClickable(true);
    }
    
    public void onItemCheckedStateChanged(ActionMode paramActionMode, int paramInt, long paramLong, boolean paramBoolean)
    {
      mWrapped.onItemCheckedStateChanged(paramActionMode, paramInt, paramLong, paramBoolean);
      if (getCheckedItemCount() == 0) {
        paramActionMode.finish();
      }
    }
    
    public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      return mWrapped.onPrepareActionMode(paramActionMode, paramMenu);
    }
    
    public void setWrapped(EnhanceGallery.MultiChoiceModeListener paramMultiChoiceModeListener)
    {
      mWrapped = paramMultiChoiceModeListener;
    }
  }
  
  public static abstract interface OnScrollListener
  {
    public static final int SCROLL_STATE_FLING = 2;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_TOUCH_SCROLL = 1;
    
    public abstract void onScroll(EnhanceGallery paramEnhanceGallery, int paramInt1, int paramInt2, int paramInt3);
    
    public abstract void onScrollStateChanged(EnhanceGallery paramEnhanceGallery, int paramInt);
  }
  
  class PerformClick
    extends EnhanceGallery.WindowRunnnable
    implements Runnable
  {
    int mClickMotionPosition;
    
    private PerformClick()
    {
      super(null);
    }
    
    public void run()
    {
      if (mDataChanged) {}
      SpinnerAdapter localSpinnerAdapter;
      int i;
      View localView;
      do
      {
        do
        {
          return;
          localSpinnerAdapter = getAdapter();
          i = mClickMotionPosition;
        } while ((localSpinnerAdapter == null) || (mItemCount <= 0) || (i == -1) || (i >= localSpinnerAdapter.getCount()) || (!sameWindow()));
        localView = getChildAt(i - mFirstPosition);
      } while (localView == null);
      performItemClicks(localView, i, localSpinnerAdapter.getItemId(i));
    }
  }
  
  class WindowRunnnable
  {
    private int mOriginalAttachCount;
    
    private WindowRunnnable() {}
    
    public void rememberWindowAttachCount()
    {
      mOriginalAttachCount = getWindowAttachCount();
    }
    
    public boolean sameWindow()
    {
      return (hasWindowFocus()) && (getWindowAttachCount() == mOriginalAttachCount);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.EnhanceGallery
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */