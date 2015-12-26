package com.meizu.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.meizu.common.R.attr;
import com.meizu.common.R.dimen;
import com.meizu.common.R.id;
import com.meizu.common.R.styleable;

public class GalleryFlow
  extends AbsSpinner
  implements GestureDetector.OnGestureListener
{
  private static final int AREA_0 = 0;
  private static final int AREA_1 = 1;
  private static final int AREA_2 = 2;
  private static final int AREA_3 = 3;
  private static final int AREA_4 = 4;
  private static final int AREA_5 = 5;
  private static final int AREA_COUNT = 6;
  private static final float FACTOR = 0.083333336F;
  private static final int PIC_NUMS = 5;
  private static final int PIC_NUMS_OF_SIDE = 2;
  private static final int SCROLL_TO_FLING_UNCERTAINTY_TIMEOUT = 250;
  private static final String TAG = "GalleryFlow";
  private static final boolean localLOGV = false;
  private int[][] AREA_RANGE;
  private int[] AREA_RANGE_LENGTH;
  private int mAnimationDuration = 350;
  private int mCenterOfGalleryFlow;
  private boolean mCirculate = false;
  private AdapterView.AdapterContextMenuInfo mContextMenuInfo;
  private int mDelta_1 = 90;
  private int mDelta_2 = 30;
  private Runnable mDisableSuppressSelectionChangedRunnable = new GalleryFlow.1(this);
  private int mDownTouchPosition;
  private View mDownTouchView;
  private FlingRunnable mFlingRunnable = new FlingRunnable();
  private GestureDetector mGestureDetector = new GestureDetector(paramContext, this);
  private int mGravity;
  private int mHalfWidth;
  private boolean mHasInitial;
  private int mHeight;
  private boolean mIsFirstScroll;
  private int mLeftMost;
  private int mLength;
  private Matrix mMatrix = new Matrix();
  private int mPicHeightMid;
  private int mPicLengthMid;
  private int mPicLengthS1;
  private int mPicLengthS2;
  private int mPicLengthS3;
  private boolean mReceivedInvokeKeyDown;
  private int mRightMost;
  private int mSelectedCenterOffset;
  private View mSelectedChild;
  private boolean mShouldCallbackDuringFling = true;
  private boolean mShouldCallbackOnUnselectedItemClick = true;
  private boolean mShouldStopFling;
  private boolean mSuppressSelectionChanged;
  private int mWidth;
  
  public GalleryFlow(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public GalleryFlow(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.MeizuCommon_GalleryFlowStyle);
  }
  
  public GalleryFlow(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    mGestureDetector.setIsLongpressEnabled(true);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.GalleryFlow, paramInt, 0);
    mCirculate = paramContext.getBoolean(R.styleable.GalleryFlow_mcCirculate, false);
    mDelta_1 = getResources().getDimensionPixelSize(R.dimen.mc_galleryflow_delta_1);
    mDelta_2 = getResources().getDimensionPixelSize(R.dimen.mc_galleryflow_delta_2);
    initialData(paramContext.getDimensionPixelSize(R.styleable.GalleryFlow_mcPicSize, 300));
    paramContext.recycle();
    mHasInitial = true;
    setChildrenDrawingOrderEnabled(true);
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
  
  private void correctRealPoint(int paramInt)
  {
    if (paramInt != -1)
    {
      int i = 0;
      if (i < getChildCount())
      {
        if (i == paramInt) {}
        for (;;)
        {
          i += 1;
          break;
          View localView = getChildAt(i);
          int j = getCurAreaNum(getCenterOfViewX(localView));
          localView.offsetLeftAndRight(AREA_RANGE[j][0] - getCenterOfViewX(localView));
        }
      }
    }
    requestLayout();
  }
  
  private void detachOffScreenChildren(boolean paramBoolean)
  {
    int n = 0;
    int i2 = getChildCount();
    int i1 = mFirstPosition;
    View localView1;
    View localView2;
    if (paramBoolean)
    {
      j = 0;
      i = 0;
      for (;;)
      {
        m = i;
        k = n;
        if (j >= i2 - 1) {
          break;
        }
        localView1 = getChildAt(j);
        localView2 = getChildAt(j + 1);
        m = i;
        k = n;
        if (getCenterOfViewX(localView1) != AREA_RANGE[1][0]) {
          break;
        }
        m = i;
        k = n;
        if (getCenterOfViewX(localView2) != AREA_RANGE[1][0]) {
          break;
        }
        mRecycler.put(i1 + j, localView1);
        j += 1;
        i += 1;
      }
    }
    int j = i2 - 1;
    int k = 0;
    int i = 0;
    while (j >= 0)
    {
      localView1 = getChildAt(j);
      localView2 = getChildAt(j - 1);
      if ((getCenterOfViewX(localView1) != AREA_RANGE[5][0]) || (getCenterOfViewX(localView2) != AREA_RANGE[5][0])) {
        break;
      }
      mRecycler.put(i1 + j, localView1);
      i += 1;
      k = j;
      j -= 1;
    }
    int m = i;
    detachViewsFromParent(k, m);
    if (paramBoolean)
    {
      mFirstPosition = (m + mFirstPosition);
      mFirstPosition = getTransitionPosition(mCirculate, mFirstPosition);
    }
  }
  
  private boolean dispatchLongPress(View paramView, int paramInt, long paramLong)
  {
    int i;
    if (mOnItemLongClickListener != null) {
      i = getTransitionPosition(mCirculate, mDownTouchPosition);
    }
    for (boolean bool1 = mOnItemLongClickListener.onItemLongClick(this, mDownTouchView, i, paramLong);; bool1 = false)
    {
      boolean bool2 = bool1;
      if (!bool1)
      {
        mContextMenuInfo = new AdapterView.AdapterContextMenuInfo(paramView, paramInt, paramLong);
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
  
  private void fillToGalleryLeftLtr()
  {
    View localView = getChildAt(0);
    int i;
    int j;
    float f;
    label70:
    int k;
    if (localView != null)
    {
      i = mFirstPosition - 1;
      j = getCurAreaNum(getCenterOfViewX(localView));
      f = (getCenterOfViewX(localView) - AREA_RANGE[j][0]) / (AREA_RANGE_LENGTH[j] * 1.0F);
      j -= 1;
      if (j >= 0) {
        if (mCirculate)
        {
          if (j != 0) {
            break label146;
          }
          if (getCenterOfViewX(localView) <= AREA_RANGE[1][0]) {
            break label193;
          }
          k = AREA_RANGE[1][0];
          label100:
          localView = makeAndAddView(i, i - mSelectedPosition, Math.round(k + mPicLengthMid), false);
          mFirstPosition = i;
          i -= 1;
        }
      }
    }
    label146:
    label193:
    for (;;)
    {
      j -= 1;
      break;
      if (i >= 0) {
        break label70;
      }
      return;
      k = AREA_RANGE[j][0] + Math.round(AREA_RANGE_LENGTH[j] * f);
      break label100;
      getRight();
      getLeft();
      getPaddingRight();
      mShouldStopFling = true;
      return;
    }
  }
  
  private void fillToGalleryRightLtr()
  {
    int j = getChildCount();
    View localView = getChildAt(j - 1);
    if (localView != null)
    {
      int k = mFirstPosition;
      int i = getCurAreaNum(getCenterOfViewX(localView));
      float f = (getCenterOfViewX(localView) - AREA_RANGE[i][0]) / (AREA_RANGE_LENGTH[i] * 1.0F);
      j = k + j;
      i += 1;
      if (i < 6) {
        if (mCirculate) {
          label83:
          if (i != 5) {
            break label142;
          }
        }
      }
      label142:
      for (k = AREA_RANGE[i][0];; k = AREA_RANGE[i][0] + Math.round(AREA_RANGE_LENGTH[i] * f))
      {
        makeAndAddView(j, j - mSelectedPosition, Math.round(k - mPicLengthMid), true);
        j += 1;
        i += 1;
        break;
        if (j < mItemCount) {
          break label83;
        }
        return;
      }
    }
    mFirstPosition = (mItemCount - 1);
    getPaddingLeft();
    mShouldStopFling = true;
  }
  
  private int getCenterOfGalleryFlow()
  {
    return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2 + getPaddingLeft();
  }
  
  private static int getCenterOfViewX(View paramView)
  {
    return paramView.getLeft() + paramView.getWidth() / 2;
  }
  
  private static int getCenterOfViewY(View paramView)
  {
    return paramView.getTop() + paramView.getHeight() / 2;
  }
  
  private int getCurAreaNum(int paramInt)
  {
    int i = 0;
    while (i < 6)
    {
      if ((paramInt >= AREA_RANGE[i][0]) && (paramInt < AREA_RANGE[i][1])) {
        return i;
      }
      i += 1;
    }
    return 0;
  }
  
  private float getCurLengthFromX(int paramInt)
  {
    paramInt = Math.abs(getCenterOfGalleryFlow() - paramInt);
    if (paramInt > mHalfWidth) {
      return mPicLengthS2 * 2;
    }
    return (mLength - paramInt) * 0.083333336F * 2.0F;
  }
  
  private int getTransitionPosition(boolean paramBoolean, int paramInt)
  {
    if (!paramBoolean) {}
    while ((!paramBoolean) || (mItemCount == 0)) {
      return paramInt;
    }
    if (paramInt > 0) {
      paramInt %= mItemCount;
    }
    for (;;)
    {
      return paramInt;
      paramInt = (mItemCount + paramInt % mItemCount) % mItemCount;
    }
  }
  
  private void initialData(int paramInt)
  {
    mPicLengthMid = (paramInt / 2);
    mPicHeightMid = paramInt;
    mLength = Math.round(mPicLengthMid / 0.083333336F);
    mPicLengthS1 = Math.round((mLength - mPicLengthMid - mDelta_1) * 0.083333336F);
    mPicLengthS2 = Math.round((mLength - mPicLengthMid - mDelta_1 - mPicLengthS1 - mDelta_2) * 0.083333336F);
    mPicLengthS3 = (mPicLengthS2 - 15);
    mHalfWidth = (mPicLengthMid + mDelta_1 + mPicLengthS1 + mDelta_2);
    mWidth = ((mHalfWidth + mPicLengthS2 + 23) * 2);
    mHeight = mPicHeightMid;
    AREA_RANGE_LENGTH = new int[] { 1, mPicLengthS1 + mDelta_2, mPicLengthMid + mDelta_1, mPicLengthMid + mDelta_1, mPicLengthS1 + mDelta_2, 1 };
  }
  
  private View makeAndAddView(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    if (!mDataChanged)
    {
      localView = mRecycler.get(paramInt1);
      if (localView != null)
      {
        paramInt1 = localView.getLeft();
        mRightMost = Math.max(mRightMost, localView.getMeasuredWidth() + paramInt1);
        mLeftMost = Math.min(mLeftMost, paramInt1);
        setUpChild(localView, paramInt2, paramInt3, paramBoolean);
        return localView;
      }
    }
    paramInt1 = getTransitionPosition(mCirculate, paramInt1);
    View localView = mAdapter.getView(paramInt1, null, this);
    setUpChild(localView, paramInt2, paramInt3, paramBoolean);
    return localView;
  }
  
  private void offsetChildrenLeftAndRight(int paramInt)
  {
    if (paramInt == 0) {
      return;
    }
    if (paramInt < 0) {}
    int m;
    int n;
    int i2;
    int i;
    int k;
    for (int j = 1;; j = 0)
    {
      m = getChildCount() / 2;
      n = -1;
      i2 = getCenterOfGalleryFlow();
      if (j == 0) {
        break label510;
      }
      i = getChildCount();
      k = Integer.MAX_VALUE;
      i -= 1;
      while (i >= 0)
      {
        int i1 = getCenterOfViewX(getChildAt(i)) - i2;
        if ((i1 < 0) || (i1 >= k)) {
          break;
        }
        m = i;
        i -= 1;
        k = i1;
      }
    }
    View localView = getChildAt(m);
    float f;
    if (getCurAreaNum(getCenterOfViewX(localView) + paramInt) == 3)
    {
      f = paramInt / (AREA_RANGE_LENGTH[3] * 1.0F);
      paramInt = getChildCount() - 1;
      i = n;
      if (paramInt >= 0)
      {
        localView = getChildAt(paramInt);
        k = getCurAreaNum(getCenterOfViewX(localView));
        if ((paramInt == getChildCount() - 1) && (k == 5)) {}
        for (;;)
        {
          paramInt -= 1;
          break;
          m = Math.round(AREA_RANGE_LENGTH[k] * f);
          localView.offsetLeftAndRight(m);
          if (getCurAreaNum(getCenterOfViewX(localView)) != k) {
            localView.offsetLeftAndRight(-m);
          }
          if (getCenterOfViewX(localView) == i2) {
            i = paramInt;
          }
        }
      }
      paramInt = i;
    }
    for (;;)
    {
      if (j != 0)
      {
        localView = getChildAt(getChildCount() - 1);
        if ((getCurAreaNum(getCenterOfViewX(localView)) == 5) && (getCurAreaNum(getCenterOfViewX(getChildAt(getChildCount() - 2))) == 3)) {
          localView.offsetLeftAndRight(Math.round(AREA_RANGE_LENGTH[4] * f) + (AREA_RANGE[5][0] - getCenterOfViewX(localView)));
        }
      }
      for (;;)
      {
        if (paramInt == -1) {
          break label953;
        }
        correctRealPoint(paramInt);
        return;
        f = (getCenterOfViewX(localView) + paramInt - i2) / (AREA_RANGE_LENGTH[2] * 1.0F);
        i = -1;
        paramInt = 0;
        if (paramInt > getChildCount() - 1) {
          break label960;
        }
        localView = getChildAt(paramInt);
        m = getCurAreaNum(getCenterOfViewX(localView));
        if (m == 1) {
          localView.offsetLeftAndRight(AREA_RANGE[1][0] - getCenterOfViewX(localView));
        }
        for (;;)
        {
          k = i;
          if (getCenterOfViewX(localView) == i2) {
            k = paramInt;
          }
          do
          {
            paramInt += 1;
            i = k;
            break;
            if (paramInt != getChildCount() - 1) {
              break label467;
            }
            k = i;
          } while (m == 5);
          label467:
          k = Math.round(AREA_RANGE_LENGTH[(m - 1)] * f);
          localView.offsetLeftAndRight(AREA_RANGE[m][0] - getCenterOfViewX(localView) + k);
        }
        label510:
        k = Integer.MAX_VALUE;
        i = 0;
        while (i <= getChildCount() - 1)
        {
          n = i2 - getCenterOfViewX(getChildAt(i));
          if ((n < 0) || (n >= k)) {
            break;
          }
          m = i;
          i += 1;
          k = n;
        }
        localView = getChildAt(m);
        if ((getCenterOfViewX(localView) == i2) || (getCurAreaNum(getCenterOfViewX(localView) + paramInt) == 2))
        {
          f = paramInt / (AREA_RANGE_LENGTH[2] * 1.0F);
          i = -1;
          paramInt = 0;
          if (paramInt <= getChildCount() - 1)
          {
            localView = getChildAt(paramInt);
            k = getCurAreaNum(getCenterOfViewX(localView));
            if ((k == 1) && (paramInt == 0)) {}
            for (;;)
            {
              paramInt += 1;
              break;
              m = Math.round(AREA_RANGE_LENGTH[k] * f);
              localView.offsetLeftAndRight(m);
              if (getCurAreaNum(getCenterOfViewX(localView)) != k) {
                localView.offsetLeftAndRight(-m);
              }
              if (getCenterOfViewX(localView) == i2) {
                i = paramInt;
              }
            }
          }
          paramInt = i;
          break;
        }
        f = (getCenterOfViewX(localView) + paramInt - i2) / (AREA_RANGE_LENGTH[3] * 1.0F);
        paramInt = getChildCount() - 1;
        if (paramInt < 0) {
          break label955;
        }
        localView = getChildAt(paramInt);
        i = getCurAreaNum(getCenterOfViewX(localView));
        if (i == 4) {
          localView.offsetLeftAndRight(AREA_RANGE[5][0] - getCenterOfViewX(localView));
        }
        for (;;)
        {
          paramInt -= 1;
          break;
          if (((paramInt != 0) || (i != 1)) && ((paramInt != getChildCount() - 1) || (i != 5)))
          {
            k = Math.round(AREA_RANGE_LENGTH[(i + 1)] * f);
            localView.offsetLeftAndRight(AREA_RANGE[i][1] - getCenterOfViewX(localView) + k);
          }
        }
        localView = getChildAt(0);
        if ((getCurAreaNum(getCenterOfViewX(localView)) == 1) && (getCurAreaNum(getCenterOfViewX(getChildAt(1))) == 2))
        {
          Math.round(AREA_RANGE_LENGTH[1] * f);
          getCenterOfViewX(localView);
          i = AREA_RANGE[1][0];
          localView.offsetLeftAndRight(Math.round(AREA_RANGE_LENGTH[1] * f));
        }
      }
      label953:
      break;
      label955:
      paramInt = -1;
      continue;
      label960:
      paramInt = i;
    }
  }
  
  private void onFinishedMovement()
  {
    if (mSuppressSelectionChanged)
    {
      mSuppressSelectionChanged = false;
      super.selectionChanged();
    }
    mSelectedCenterOffset = 0;
    invalidate();
  }
  
  private void scaleView(View paramView)
  {
    int j = getCenterOfViewX(paramView);
    int k = getCenterOfViewY(paramView);
    mMatrix.reset();
    Object localObject;
    float f2;
    float f1;
    int i;
    if (paramView == getChildAt(0))
    {
      localObject = getChildAt(1);
      if ((localObject != null) && (getCurAreaNum(getCenterOfViewX((View)localObject)) == 1))
      {
        f2 = (getCenterOfViewX((View)localObject) - AREA_RANGE[1][0]) / (AREA_RANGE_LENGTH[1] * 1.0F);
        f1 = 30.0F * f2 + mPicLengthS3 * 2;
        i = Math.round(180.0F - f2 * 102.0F);
        mMatrix.postScale(f1 / mPicHeightMid, f1 / mPicHeightMid);
        mMatrix.preTranslate(-j, -k);
        mMatrix.postTranslate(j, k);
        localObject = (TextView)paramView.findViewById(R.id.mc_galleryflow_album_title);
        k = Math.abs(j - getCenterOfGalleryFlow());
        if (localObject != null)
        {
          if (k >= AREA_RANGE_LENGTH[2]) {
            break label437;
          }
          ((TextView)localObject).setVisibility(0);
          ((TextView)localObject).setAlpha(1.0F - k / (AREA_RANGE_LENGTH[2] * 1.0F));
        }
      }
    }
    for (;;)
    {
      if ((paramView instanceof FrameLayout))
      {
        paramView = (FrameLayout)paramView;
        localObject = paramView.getForeground();
        j = i;
        if (i == 0) {
          j = Math.round(110.0F * k / (mHalfWidth * 1.0F));
        }
        if (localObject != null)
        {
          ((Drawable)localObject).setAlpha(j);
          paramView.setForeground((Drawable)localObject);
        }
      }
      return;
      f1 = getCurLengthFromX(getCenterOfViewX(paramView));
      i = 0;
      break;
      if (paramView == getChildAt(getChildCount() - 1))
      {
        localObject = getChildAt(getChildCount() - 2);
        if ((localObject != null) && (getCurAreaNum(getCenterOfViewX((View)localObject)) == 4))
        {
          f2 = (AREA_RANGE[5][0] - getCenterOfViewX((View)localObject)) / (AREA_RANGE_LENGTH[4] * 1.0F);
          f1 = 30.0F * f2 + mPicLengthS3 * 2;
          i = Math.round(180.0F - f2 * 102.0F);
          break;
        }
        f1 = getCurLengthFromX(getCenterOfViewX(paramView));
        i = 0;
        break;
      }
      f1 = getCurLengthFromX(getCenterOfViewX(paramView));
      i = 0;
      break;
      label437:
      ((TextView)localObject).setVisibility(8);
    }
  }
  
  private void scrollIntoSlots()
  {
    if ((getChildCount() == 0) || (mSelectedChild == null)) {
      return;
    }
    int i = getCenterOfViewX(mSelectedChild);
    i = getCenterOfGalleryFlow() - i;
    if (i != 0)
    {
      mFlingRunnable.startUsingDistance(i);
      return;
    }
    onFinishedMovement();
  }
  
  private boolean scrollToChild(int paramInt)
  {
    View localView = getChildAt(getTransitionPosition(mCirculate, paramInt));
    if (localView != null)
    {
      getChildCount();
      paramInt = getCurAreaNum(getCenterOfViewX(localView));
      int i = AREA_RANGE_LENGTH[2];
      mFlingRunnable.startUsingDistance((3 - paramInt) * i);
      return true;
    }
    return false;
  }
  
  private void setSelectionToCenterChild()
  {
    View localView = mSelectedChild;
    if (mSelectedChild == null) {}
    int n;
    do
    {
      return;
      n = getCenterOfGalleryFlow();
    } while ((localView.getLeft() <= n) && (localView.getRight() >= n));
    int k = Integer.MAX_VALUE;
    int j = 0;
    int i = getChildCount() - 1;
    if (i >= 0)
    {
      localView = getChildAt(i);
      if ((localView.getLeft() > n) || (localView.getRight() < n)) {}
    }
    for (;;)
    {
      i = mFirstPosition + i;
      if (i == mSelectedPosition) {
        break;
      }
      i = getTransitionPosition(mCirculate, i);
      setSelectedPositionInt(i);
      setNextSelectedPositionInt(i);
      checkSelectionChanged();
      return;
      int m = Math.min(Math.abs(localView.getLeft() - n), Math.abs(localView.getRight() - n));
      if (m < k) {
        k = i;
      }
      for (j = m;; j = m)
      {
        i -= 1;
        m = j;
        j = k;
        k = m;
        break;
        m = k;
        k = j;
      }
      i = j;
    }
  }
  
  private void setUpChild(View paramView, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    boolean bool = false;
    Object localObject = paramView.getLayoutParams();
    int i;
    label57:
    int j;
    int k;
    if (localObject == null)
    {
      localObject = (LayoutParams)generateDefaultLayoutParams();
      i = mPicHeightMid;
      width = i;
      height = i;
      paramView.setLayoutParams((ViewGroup.LayoutParams)localObject);
      if (!paramBoolean) {
        break label218;
      }
      i = -1;
      addViewInLayout(paramView, i, (ViewGroup.LayoutParams)localObject);
      if (paramInt1 == 0) {
        bool = true;
      }
      paramView.setSelected(bool);
      paramInt1 = ViewGroup.getChildMeasureSpec(mHeightMeasureSpec, mSpinnerPadding.top + mSpinnerPadding.bottom, height);
      paramView.measure(ViewGroup.getChildMeasureSpec(mWidthMeasureSpec, mSpinnerPadding.left + mSpinnerPadding.right, width), paramInt1);
      i = calculateTop(paramView, true);
      j = paramView.getMeasuredHeight();
      k = paramView.getMeasuredWidth();
      if (!paramBoolean) {
        break label224;
      }
      paramInt1 = paramInt2;
      paramInt2 = k + paramInt2;
    }
    for (;;)
    {
      paramView.layout(paramInt1, i, paramInt2, i + j);
      return;
      if ((localObject instanceof LayoutParams))
      {
        localObject = (LayoutParams)localObject;
        break;
      }
      localObject = (LayoutParams)generateLayoutParams((ViewGroup.LayoutParams)localObject);
      break;
      label218:
      i = 0;
      break label57;
      label224:
      paramInt1 = paramInt2 - k;
    }
  }
  
  private void updateSelectedItemMetadata()
  {
    View localView1 = mSelectedChild;
    View localView2 = getChildAt(getTransitionPosition(mCirculate, mSelectedPosition - mFirstPosition));
    mSelectedChild = localView2;
    if (localView2 == null) {}
    do
    {
      return;
      localView2.setSelected(true);
      localView2.setFocusable(true);
      if (hasFocus()) {
        localView2.requestFocus();
      }
    } while ((localView1 == null) || (localView1 == localView2));
    localView1.setSelected(false);
    localView1.setFocusable(false);
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
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    return paramKeyEvent.dispatch(this, null, null);
  }
  
  protected void dispatchSetPressed(boolean paramBoolean)
  {
    if (mSelectedChild != null) {
      mSelectedChild.setPressed(paramBoolean);
    }
  }
  
  public void dispatchSetSelected(boolean paramBoolean) {}
  
  protected boolean drawChild(Canvas paramCanvas, View paramView, long paramLong)
  {
    Rect localRect = new Rect();
    scaleView(paramView);
    int i = paramCanvas.save();
    paramCanvas.concat(mMatrix);
    boolean bool = super.drawChild(paramCanvas, paramView, paramLong);
    paramCanvas.restoreToCount(i);
    localRect.setEmpty();
    paramView.getHitRect(localRect);
    return bool;
  }
  
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
  
  protected int getChildDrawingOrder(int paramInt1, int paramInt2)
  {
    int i = mSelectedPosition;
    int j = mFirstPosition;
    i = getTransitionPosition(mCirculate, i - j);
    if (i < 0) {}
    do
    {
      return paramInt2;
      if (paramInt2 == paramInt1 - 1) {
        return i;
      }
    } while (paramInt2 < i);
    return paramInt1 - 1 - (paramInt2 - i);
  }
  
  int getChildHeight(View paramView)
  {
    return paramView.getMeasuredHeight();
  }
  
  protected ContextMenu.ContextMenuInfo getContextMenuInfo()
  {
    return mContextMenuInfo;
  }
  
  int getLimitedMotionScrollAmount(boolean paramBoolean, int paramInt)
  {
    if (paramBoolean) {}
    View localView;
    for (int i = mItemCount - 1;; i = 0)
    {
      localView = getChildAt(i - mFirstPosition);
      if (localView != null) {
        break;
      }
      return paramInt;
    }
    i = getCenterOfViewX(localView);
    int j = getCenterOfGalleryFlow();
    if (i == j) {
      return 0;
    }
    i = j - i;
    if (paramBoolean) {}
    for (paramInt = Math.max(i, paramInt);; paramInt = Math.min(i, paramInt)) {
      return paramInt;
    }
  }
  
  void layout(int paramInt, boolean paramBoolean)
  {
    if (mDataChanged) {
      handleDataChanged();
    }
    if (mItemCount == 0)
    {
      resetList();
      return;
    }
    if (mNextSelectedPosition >= 0)
    {
      mNextSelectedPosition = getTransitionPosition(mCirculate, mNextSelectedPosition);
      setSelectedPositionInt(mNextSelectedPosition);
    }
    recycleAllViews();
    detachAllViewsFromParent();
    mRightMost = 0;
    mLeftMost = 0;
    mFirstPosition = mSelectedPosition;
    View localView = makeAndAddView(mSelectedPosition, 0, 0, true);
    localView.offsetLeftAndRight(getCenterOfGalleryFlow() - getCenterOfViewX(localView) + mSelectedCenterOffset);
    fillToGalleryRightLtr();
    fillToGalleryLeftLtr();
    mRecycler.clear();
    invalidate();
    checkSelectionChanged();
    mDataChanged = false;
    mNeedSync = false;
    mSelectedPosition = getTransitionPosition(mCirculate, mSelectedPosition);
    setNextSelectedPositionInt(mSelectedPosition);
    updateSelectedItemMetadata();
  }
  
  boolean moveNext()
  {
    if (mItemCount > 0)
    {
      scrollToChild(mSelectedPosition - mFirstPosition + 1);
      return true;
    }
    return false;
  }
  
  boolean movePrevious()
  {
    if (mItemCount > 0)
    {
      scrollToChild(mSelectedPosition - mFirstPosition - 1);
      return true;
    }
    return false;
  }
  
  void onCancel()
  {
    onUp();
  }
  
  public boolean onDown(MotionEvent paramMotionEvent)
  {
    mFlingRunnable.stop(false);
    mDownTouchPosition = pointToPosition((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
    mDownTouchView = getChildAt(getTransitionPosition(mCirculate, mDownTouchPosition - mFirstPosition));
    if (mDownTouchView != null) {
      mDownTouchView.setPressed(true);
    }
    mIsFirstScroll = true;
    return true;
  }
  
  public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    if (!mShouldCallbackDuringFling)
    {
      removeCallbacks(mDisableSuppressSelectionChangedRunnable);
      if (!mSuppressSelectionChanged) {
        mSuppressSelectionChanged = true;
      }
    }
    mFlingRunnable.startUsingVelocity((int)-paramFloat1);
    return true;
  }
  
  protected void onFocusChanged(boolean paramBoolean, int paramInt, Rect paramRect)
  {
    super.onFocusChanged(paramBoolean, paramInt, paramRect);
    if ((paramBoolean) && (mSelectedChild != null))
    {
      mSelectedChild.requestFocus(paramInt);
      mSelectedChild.setSelected(true);
    }
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    paramAccessibilityEvent.setClassName(GalleryFlow.class.getName());
  }
  
  public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    boolean bool = true;
    super.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfo);
    paramAccessibilityNodeInfo.setClassName(GalleryFlow.class.getName());
    if (mItemCount > 1) {}
    for (;;)
    {
      paramAccessibilityNodeInfo.setScrollable(bool);
      if (isEnabled())
      {
        if ((mItemCount > 0) && (mSelectedPosition < mItemCount - 1)) {
          paramAccessibilityNodeInfo.addAction(4096);
        }
        if ((isEnabled()) && (mItemCount > 0) && (mSelectedPosition > 0)) {
          paramAccessibilityNodeInfo.addAction(8192);
        }
      }
      return;
      bool = false;
    }
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    }
    for (;;)
    {
      return super.onKeyDown(paramInt, paramKeyEvent);
      if (movePrevious())
      {
        playSoundEffect(1);
        return true;
        if (moveNext())
        {
          playSoundEffect(3);
          return true;
          mReceivedInvokeKeyDown = true;
        }
      }
    }
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    default: 
      return super.onKeyUp(paramInt, paramKeyEvent);
    }
    if ((mReceivedInvokeKeyDown) && (mItemCount > 0))
    {
      dispatchPress(mSelectedChild);
      postDelayed(new GalleryFlow.2(this), ViewConfiguration.getPressedStateDuration());
      paramInt = mSelectedPosition;
      int i = mFirstPosition;
      int j = getTransitionPosition(mCirculate, mSelectedPosition);
      performItemClick(getChildAt(paramInt - i), j, mAdapter.getItemId(j));
    }
    mReceivedInvokeKeyDown = false;
    return true;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    mInLayout = true;
    mCenterOfGalleryFlow = getCenterOfGalleryFlow();
    paramInt1 = mCenterOfGalleryFlow;
    paramInt2 = mHalfWidth;
    paramInt3 = mCenterOfGalleryFlow;
    paramInt4 = mHalfWidth;
    int i = mCenterOfGalleryFlow;
    int j = mHalfWidth;
    int k = mCenterOfGalleryFlow;
    int m = mPicLengthMid;
    int n = mDelta_1;
    int i1 = mCenterOfGalleryFlow;
    int i2 = mPicLengthMid;
    int i3 = mDelta_1;
    int i4 = mCenterOfGalleryFlow;
    int i5 = mCenterOfGalleryFlow;
    int i6 = mCenterOfGalleryFlow;
    int i7 = mPicLengthMid;
    int i8 = mDelta_1;
    int i9 = mCenterOfGalleryFlow;
    int i10 = mPicLengthMid;
    int i11 = mDelta_1;
    int i12 = mCenterOfGalleryFlow;
    int i13 = mHalfWidth;
    int i14 = mCenterOfGalleryFlow;
    int i15 = mHalfWidth;
    int i16 = mCenterOfGalleryFlow;
    int i17 = mHalfWidth;
    AREA_RANGE = new int[][] { { paramInt1 - paramInt2 - 1, paramInt3 - paramInt4 }, { i - j, k - m - n }, { i1 - i2 - i3, i4 }, { i5, i6 + i7 + i8 }, { i9 + i10 + i11, i12 + i13 }, { i14 + i15, i16 + i17 + 1 } };
    layout(0, false);
    mInLayout = false;
  }
  
  public void onLongPress(MotionEvent paramMotionEvent) {}
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    setMeasuredDimension(resolveSizeAndState(mWidth, paramInt1, 0), resolveSizeAndState(mHeight, paramInt2, 0));
  }
  
  public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    getParent().requestDisallowInterceptTouchEvent(true);
    if (!mShouldCallbackDuringFling)
    {
      if (mIsFirstScroll)
      {
        if (!mSuppressSelectionChanged) {
          mSuppressSelectionChanged = true;
        }
        postDelayed(mDisableSuppressSelectionChangedRunnable, 250L);
      }
      if (Math.abs(paramFloat1) - AREA_RANGE_LENGTH[3] <= 1.0E-4D) {
        break label124;
      }
      if (paramFloat1 <= 0.0F) {
        break label112;
      }
      paramFloat1 = AREA_RANGE_LENGTH[3];
    }
    label112:
    label124:
    for (;;)
    {
      trackMotionScroll((int)paramFloat1 * -1);
      mIsFirstScroll = false;
      return true;
      if (!mSuppressSelectionChanged) {
        break;
      }
      mSuppressSelectionChanged = false;
      break;
      paramFloat1 = -AREA_RANGE_LENGTH[3];
    }
  }
  
  public void onShowPress(MotionEvent paramMotionEvent) {}
  
  public boolean onSingleTapUp(MotionEvent paramMotionEvent)
  {
    if (mDownTouchView != null)
    {
      scrollToChild(mDownTouchPosition - mFirstPosition);
      if ((mShouldCallbackOnUnselectedItemClick) || (mDownTouchPosition == mSelectedPosition))
      {
        int i = getTransitionPosition(mCirculate, mDownTouchPosition);
        performItemClick(mDownTouchView, i, mAdapter.getItemId(i));
      }
      return true;
    }
    return false;
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
    if (mFlingRunnable.mScroller.isFinished()) {
      scrollIntoSlots();
    }
    dispatchUnpress();
  }
  
  public boolean performAccessibilityAction(int paramInt, Bundle paramBundle)
  {
    boolean bool2 = false;
    boolean bool1;
    if (super.performAccessibilityAction(paramInt, paramBundle)) {
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
              do
              {
                return bool1;
                switch (paramInt)
                {
                default: 
                  return false;
                case 4096: 
                  bool1 = bool2;
                }
              } while (!isEnabled());
              bool1 = bool2;
            } while (mItemCount <= 0);
            bool1 = bool2;
          } while (mSelectedPosition >= mItemCount - 1);
          return scrollToChild(mSelectedPosition - mFirstPosition + 1);
          bool1 = bool2;
        } while (!isEnabled());
        bool1 = bool2;
      } while (mItemCount <= 0);
      bool1 = bool2;
    } while (mSelectedPosition <= 0);
    return scrollToChild(mSelectedPosition - mFirstPosition - 1);
  }
  
  public int pointToPosition(int paramInt1, int paramInt2)
  {
    Rect localRect = new Rect();
    int j = getChildCount();
    View localView;
    int k;
    int m;
    if (paramInt1 >= getCenterOfGalleryFlow())
    {
      i = 0;
      while (i < j)
      {
        localView = getChildAt(i);
        if (localView.getVisibility() == 0)
        {
          k = getCenterOfViewX(localView);
          m = getCenterOfViewY(localView);
          int n = (int)getCurLengthFromX(k) / 2;
          localRect.set(k - n, m - n, k + n, m + n);
          if (localRect.contains(paramInt1, paramInt2)) {
            return i + mFirstPosition;
          }
        }
        i += 1;
      }
    }
    int i = j - 1;
    while (i >= 0)
    {
      localView = getChildAt(i);
      if (localView.getVisibility() == 0)
      {
        j = getCenterOfViewX(localView);
        k = getCenterOfViewY(localView);
        m = (int)getCurLengthFromX(j) / 2;
        localRect.set(j - m, k - m, j + m, k + m);
        if (localRect.contains(paramInt1, paramInt2)) {
          return i + mFirstPosition;
        }
      }
      i -= 1;
    }
    return -1;
  }
  
  void selectionChanged()
  {
    if (!mSuppressSelectionChanged) {
      super.selectionChanged();
    }
  }
  
  public void setAdapter(SpinnerAdapter paramSpinnerAdapter)
  {
    int i = 2;
    super.setAdapter(paramSpinnerAdapter);
    if (paramSpinnerAdapter != null) {
      if ((mItemCount <= 0) || (2 >= mItemCount)) {
        break label51;
      }
    }
    for (;;)
    {
      i = getTransitionPosition(mCirculate, i);
      setSelectedPositionInt(i);
      setNextSelectedPositionInt(i);
      requestLayout();
      return;
      label51:
      i = mItemCount / 2;
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
  
  public void setCenterPicSize(int paramInt)
  {
    if ((paramInt > 0) && (paramInt != mPicHeightMid))
    {
      initialData(paramInt);
      requestLayout();
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
  
  void setSelectedPositionInt(int paramInt)
  {
    super.setSelectedPositionInt(paramInt);
    updateSelectedItemMetadata();
  }
  
  public boolean showContextMenu()
  {
    if (isPressed())
    {
      int i = mSelectedPosition;
      int j = mFirstPosition;
      return dispatchLongPress(getChildAt(getTransitionPosition(mCirculate, i - j)), mSelectedPosition, mSelectedRowId);
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
  
  void trackMotionScroll(int paramInt)
  {
    if ((getChildCount() == 0) || (paramInt == 0)) {
      return;
    }
    boolean bool;
    if (paramInt < 0)
    {
      bool = true;
      int i = paramInt;
      if (!mCirculate)
      {
        i = getLimitedMotionScrollAmount(bool, paramInt);
        if (i != paramInt)
        {
          mFlingRunnable.endFling(false);
          onFinishedMovement();
        }
      }
      offsetChildrenLeftAndRight(i);
      detachOffScreenChildren(bool);
      if (!bool) {
        break label135;
      }
      fillToGalleryRightLtr();
    }
    for (;;)
    {
      mRecycler.clear();
      setSelectionToCenterChild();
      View localView = mSelectedChild;
      if (localView != null)
      {
        paramInt = localView.getLeft();
        mSelectedCenterOffset = (localView.getWidth() / 2 + paramInt - getWidth() / 2);
      }
      onScrollChanged(0, 0, 0, 0);
      invalidate();
      return;
      bool = false;
      break;
      label135:
      fillToGalleryLeftLtr();
    }
  }
  
  class FlingRunnable
    implements Runnable
  {
    private int mLastFlingX;
    private OverScroller mScroller = new OverScroller(getContext());
    
    public FlingRunnable()
    {
      mScroller.setEnableMZOverScroll(true, true);
    }
    
    private void endFling(boolean paramBoolean)
    {
      mScroller.forceFinished(true);
      if (paramBoolean) {
        GalleryFlow.this.scrollIntoSlots();
      }
    }
    
    private void startCommon()
    {
      removeCallbacks(this);
    }
    
    public void run()
    {
      if (mItemCount == 0)
      {
        endFling(true);
        return;
      }
      GalleryFlow.access$602(GalleryFlow.this, false);
      OverScroller localOverScroller = mScroller;
      boolean bool = localOverScroller.computeScrollOffset();
      int j = localOverScroller.getCurrX();
      int i = mLastFlingX - j;
      if (i > 0) {}
      for (i = Math.min(AREA_RANGE_LENGTH[3], i);; i = Math.max(-AREA_RANGE_LENGTH[3], i))
      {
        if (i != 0) {
          trackMotionScroll(i);
        }
        if ((!bool) || (mShouldStopFling)) {
          break;
        }
        mLastFlingX = j;
        post(this);
        return;
      }
      endFling(true);
    }
    
    public void startUsingDistance(int paramInt)
    {
      if (paramInt == 0) {
        return;
      }
      startCommon();
      mLastFlingX = 0;
      mScroller.setInterpolator(new DecelerateInterpolator());
      mScroller.startScroll(0, 0, -paramInt, 0, mAnimationDuration);
      post(this);
    }
    
    public void startUsingVelocity(int paramInt)
    {
      int i = 4200;
      if ((paramInt == 0) || (Math.abs(paramInt) < 200)) {
        return;
      }
      startCommon();
      if (Math.abs(paramInt) > 4200) {
        if (paramInt > 0) {
          paramInt = i;
        }
      }
      for (;;)
      {
        if (paramInt < 0) {}
        for (i = Integer.MAX_VALUE;; i = 0)
        {
          mLastFlingX = i;
          mScroller.setInterpolator(null);
          mScroller.fling(i, 0, paramInt, 0, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
          post(this);
          return;
          paramInt = 61336;
          break;
        }
      }
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
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.GalleryFlow
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */