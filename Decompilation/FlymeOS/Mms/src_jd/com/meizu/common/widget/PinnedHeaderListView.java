package com.meizu.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListAdapter;

public class PinnedHeaderListView
  extends AutoScrollListView
  implements AbsListView.OnScrollListener, AdapterView.OnItemSelectedListener
{
  private static final int BOTTOM = 1;
  private static final int DEFAULT_ANIMATION_DURATION = 20;
  private static final int FADING = 2;
  private static final int MAX_ALPHA = 255;
  private static final int TOP = 0;
  private PinnedHeaderAdapter mAdapter;
  private boolean mAnimating;
  private int mAnimationDuration = 20;
  private long mAnimationTargetTime;
  private RectF mBounds = new RectF();
  private Rect mClipRect = new Rect();
  private Drawable mHeaderBackground = null;
  private int mHeaderPaddingLeft;
  private int mHeaderPaddingTop;
  private int mHeaderWidth;
  private PinnedHeader[] mHeaders;
  private AdapterView.OnItemSelectedListener mOnItemSelectedListener;
  private AbsListView.OnScrollListener mOnScrollListener;
  private int mScrollState;
  private int mSize;
  
  public PinnedHeaderListView(Context paramContext)
  {
    this(paramContext, null, 16842868);
  }
  
  public PinnedHeaderListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 16842868);
  }
  
  public PinnedHeaderListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    super.setOnScrollListener(this);
    super.setOnItemSelectedListener(this);
  }
  
  private void drawHeader(Canvas paramCanvas, PinnedHeader paramPinnedHeader, long paramLong)
  {
    int i;
    View localView;
    if (animating)
    {
      i = (int)(targetTime - paramLong);
      if (i <= 0)
      {
        y = targetY;
        visible = targetVisible;
        animating = false;
      }
    }
    else if (visible)
    {
      localView = view;
      i = paramCanvas.save();
      if ((state != 0) && (state != 2)) {
        break label184;
      }
      paramCanvas.translate(mHeaderPaddingLeft, y + mHeaderPaddingTop);
    }
    for (;;)
    {
      if (state == 2)
      {
        mBounds.set(0.0F, 0.0F, mHeaderWidth, localView.getHeight());
        paramCanvas.saveLayerAlpha(mBounds, alpha, 31);
      }
      localView.draw(paramCanvas);
      paramCanvas.restoreToCount(i);
      return;
      int j = targetY;
      y = (i * (sourceY - targetY) / mAnimationDuration + j);
      break;
      label184:
      paramCanvas.translate(mHeaderPaddingLeft, y);
    }
  }
  
  private void ensurePinnedHeaderLayout(int paramInt)
  {
    View localView = mHeaders[paramInt].view;
    int j;
    ViewGroup.LayoutParams localLayoutParams;
    if (localView.isLayoutRequested())
    {
      j = View.MeasureSpec.makeMeasureSpec(mHeaderWidth, 1073741824);
      localLayoutParams = localView.getLayoutParams();
      if ((localLayoutParams == null) || (height <= 0)) {
        break label96;
      }
    }
    label96:
    for (int i = View.MeasureSpec.makeMeasureSpec(height, 1073741824);; i = View.MeasureSpec.makeMeasureSpec(0, 0))
    {
      localView.measure(j, i);
      i = localView.getMeasuredHeight();
      mHeaders[paramInt].height = i;
      localView.layout(0, 0, mHeaderWidth, i);
      return;
    }
  }
  
  private void invalidateIfAnimating()
  {
    int i = 0;
    mAnimating = false;
    for (;;)
    {
      if (i < mSize)
      {
        if (mHeaders[i].animating)
        {
          mAnimating = true;
          invalidate();
        }
      }
      else {
        return;
      }
      i += 1;
    }
  }
  
  private boolean smoothScrollToPartition(int paramInt)
  {
    int j = 0;
    int m = mAdapter.getScrollPositionForHeader(paramInt);
    if (m == -1) {
      return false;
    }
    int i = 0;
    while (i < paramInt)
    {
      PinnedHeader localPinnedHeader = mHeaders[i];
      int k = j;
      if (visible) {
        k = j + height;
      }
      i += 1;
      j = k;
    }
    smoothScrollToPositionFromTop(getHeaderViewsCount() + m, j);
    return true;
  }
  
  protected void dispatchDraw(Canvas paramCanvas)
  {
    int i2 = 0;
    long l;
    int m;
    int i;
    int j;
    int k;
    label28:
    PinnedHeader localPinnedHeader;
    int n;
    int i1;
    if (mAnimating)
    {
      l = System.currentTimeMillis();
      m = getBottom();
      i = 0;
      j = 0;
      k = 0;
      if (k >= mSize) {
        break label159;
      }
      localPinnedHeader = mHeaders[k];
      n = m;
      i1 = j;
      if (visible)
      {
        if ((state != 1) || (y >= m)) {
          break label114;
        }
        n = y;
        i = 1;
        i1 = j;
      }
    }
    for (;;)
    {
      k += 1;
      m = n;
      j = i1;
      break label28;
      l = 0L;
      break;
      label114:
      if ((state == 0) || (state == 2))
      {
        i1 = y + height;
        if (i1 > j)
        {
          i = 1;
          n = m;
          continue;
          label159:
          if (i != 0)
          {
            paramCanvas.save();
            mClipRect.set(0, 0, getWidth(), m);
            paramCanvas.clipRect(mClipRect);
          }
          super.dispatchDraw(paramCanvas);
          if (i != 0)
          {
            paramCanvas.restore();
            i = mSize;
            for (;;)
            {
              j = i - 1;
              i = i2;
              if (j < 0) {
                break;
              }
              localPinnedHeader = mHeaders[j];
              i = j;
              if (visible) {
                if (state != 0)
                {
                  i = j;
                  if (state != 2) {}
                }
                else
                {
                  drawHeader(paramCanvas, localPinnedHeader, l);
                  i = j;
                }
              }
            }
            while (i < mSize)
            {
              localPinnedHeader = mHeaders[i];
              if ((visible) && (state == 1)) {
                drawHeader(paramCanvas, localPinnedHeader, l);
              }
              i += 1;
            }
          }
          invalidateIfAnimating();
          return;
        }
      }
      i = 1;
      n = m;
      i1 = j;
    }
  }
  
  public int getCurrentOverScrollDistance()
  {
    int j = 0;
    int i = j;
    if (getFirstVisiblePosition() == 0)
    {
      i = j;
      if (getChildCount() > 0)
      {
        i = getChildAt(0).getTop();
        i = getPaddingTop() - i;
      }
    }
    return i;
  }
  
  public int getHeaderPaddingTop()
  {
    return mHeaderPaddingTop;
  }
  
  public int getPinnedHeaderHeight(int paramInt)
  {
    ensurePinnedHeaderLayout(paramInt);
    return mHeaders[paramInt].view.getHeight();
  }
  
  public int getPositionAt(int paramInt)
  {
    int k = 0;
    int m = getChildCount();
    int i = k;
    if (m > 0)
    {
      if (isStackFromBottom()) {
        j = m - 1;
      }
    }
    else {
      for (;;)
      {
        i = k;
        if (j >= 0)
        {
          if (paramInt >= getChildAt(j).getTop()) {
            i = getFirstVisiblePosition() + j;
          }
        }
        else {
          return i;
        }
        j -= 1;
      }
    }
    int j = 0;
    for (;;)
    {
      i = k;
      if (j >= m) {
        break;
      }
      if (paramInt <= getChildAt(j).getBottom()) {
        return getFirstVisiblePosition() + j;
      }
      j += 1;
    }
  }
  
  protected float getTopFadingEdgeStrength()
  {
    if (mSize > 0) {
      return 0.0F;
    }
    return super.getTopFadingEdgeStrength();
  }
  
  public int getTotalTopPinnedHeaderHeight()
  {
    int i = mSize;
    PinnedHeader localPinnedHeader;
    do
    {
      int j;
      do
      {
        j = i - 1;
        if (j < 0) {
          break;
        }
        localPinnedHeader = mHeaders[j];
        i = j;
      } while (!visible);
      i = j;
    } while (state != 0);
    return y + height;
    return 0;
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    return super.onInterceptTouchEvent(paramMotionEvent);
  }
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    int j = 0;
    int m = getHeight();
    int i = 0;
    Object localObject;
    if (j < mSize)
    {
      localObject = mHeaders[j];
      int k = i;
      if (visible)
      {
        if (state != 0) {
          break label76;
        }
        k = y + height;
      }
      label76:
      do
      {
        j += 1;
        i = k;
        break;
        k = i;
      } while (state != 1);
    }
    for (j = y;; j = m)
    {
      localObject = getSelectedView();
      if (localObject != null)
      {
        if (((View)localObject).getTop() >= i) {
          break label146;
        }
        setSelectionFromTop(paramInt, i);
      }
      for (;;)
      {
        if (mOnItemSelectedListener != null) {
          mOnItemSelectedListener.onItemSelected(paramAdapterView, paramView, paramInt, paramLong);
        }
        return;
        label146:
        if (((View)localObject).getBottom() > j) {
          setSelectionFromTop(paramInt, j - ((View)localObject).getHeight());
        }
      }
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    mHeaderPaddingLeft = 0;
    mHeaderWidth = (paramInt3 - paramInt1);
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView)
  {
    if (mOnItemSelectedListener != null) {
      mOnItemSelectedListener.onNothingSelected(paramAdapterView);
    }
  }
  
  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    int j = 0;
    if (mAdapter != null)
    {
      int k = mAdapter.getPinnedHeaderCount();
      int i = j;
      if (k != mSize)
      {
        mSize = k;
        if (mHeaders != null) {
          break label174;
        }
        mHeaders = new PinnedHeader[mSize];
        i = j;
      }
      while (i < mSize)
      {
        if (mHeaders[i] == null) {
          mHeaders[i] = new PinnedHeader(null);
        }
        mHeaders[i].view = mAdapter.getPinnedHeaderView(i, mHeaders[i].view, this);
        if ((mHeaderBackground != null) && (mHeaders[i].view != null)) {
          mHeaders[i].view.setBackground(mHeaderBackground);
        }
        i += 1;
        continue;
        label174:
        i = j;
        if (mHeaders.length < mSize)
        {
          paramAbsListView = mHeaders;
          mHeaders = new PinnedHeader[mSize];
          System.arraycopy(paramAbsListView, 0, mHeaders, 0, paramAbsListView.length);
          i = j;
        }
      }
      mAnimationTargetTime = (System.currentTimeMillis() + mAnimationDuration);
      mAdapter.configurePinnedHeaders(this);
      invalidateIfAnimating();
    }
    if (mOnScrollListener != null) {
      mOnScrollListener.onScroll(this, paramInt1, paramInt2, paramInt3);
    }
  }
  
  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    mScrollState = paramInt;
    if (mOnScrollListener != null) {
      mOnScrollListener.onScrollStateChanged(this, paramInt);
    }
  }
  
  public void setAdapter(ListAdapter paramListAdapter)
  {
    mAdapter = ((PinnedHeaderAdapter)paramListAdapter);
    super.setAdapter(paramListAdapter);
  }
  
  public void setFadingHeader(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    ensurePinnedHeaderLayout(paramInt1);
    if (getChildAt(paramInt2 - getFirstVisiblePosition()) == null) {
      return;
    }
    PinnedHeader localPinnedHeader = mHeaders[paramInt1];
    visible = true;
    state = 2;
    alpha = 255;
    animating = false;
    y = getTotalTopPinnedHeaderHeight();
  }
  
  public void setHeaderBackground(Drawable paramDrawable)
  {
    if ((paramDrawable != null) && (paramDrawable != mHeaderBackground))
    {
      if (mHeaderBackground != null)
      {
        mHeaderBackground.setCallback(null);
        unscheduleDrawable(mHeaderBackground);
      }
      mHeaderBackground = paramDrawable;
      mHeaderBackground.setCallback(this);
      requestLayout();
      invalidate();
    }
  }
  
  public void setHeaderInvisible(int paramInt, boolean paramBoolean)
  {
    PinnedHeader localPinnedHeader = mHeaders[paramInt];
    if ((visible) && ((paramBoolean) || (animating)) && (state == 1))
    {
      sourceY = y;
      if (!animating)
      {
        visible = true;
        targetY = (getBottom() + height);
      }
      animating = true;
      targetTime = mAnimationTargetTime;
      targetVisible = false;
      return;
    }
    visible = false;
  }
  
  public void setHeaderPaddingTop(int paramInt)
  {
    if (paramInt >= 0) {
      mHeaderPaddingTop = paramInt;
    }
  }
  
  public void setHeaderPinnedAtBottom(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    ensurePinnedHeaderLayout(paramInt1);
    PinnedHeader localPinnedHeader = mHeaders[paramInt1];
    state = 1;
    if (animating)
    {
      targetTime = mAnimationTargetTime;
      sourceY = y;
      targetY = paramInt2;
      return;
    }
    if ((paramBoolean) && ((y != paramInt2) || (!visible)))
    {
      if (visible) {}
      for (sourceY = y;; sourceY = (height + paramInt2))
      {
        animating = true;
        targetVisible = true;
        targetTime = mAnimationTargetTime;
        targetY = paramInt2;
        return;
        visible = true;
      }
    }
    visible = true;
    y = paramInt2;
  }
  
  public void setHeaderPinnedAtTop(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    ensurePinnedHeaderLayout(paramInt1);
    PinnedHeader localPinnedHeader = mHeaders[paramInt1];
    visible = true;
    y = paramInt2;
    state = 0;
    animating = false;
  }
  
  public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener paramOnItemSelectedListener)
  {
    mOnItemSelectedListener = paramOnItemSelectedListener;
    super.setOnItemSelectedListener(this);
  }
  
  public void setOnScrollListener(AbsListView.OnScrollListener paramOnScrollListener)
  {
    mOnScrollListener = paramOnScrollListener;
    super.setOnScrollListener(this);
  }
  
  public void setPinnedHeaderAnimationDuration(int paramInt)
  {
    mAnimationDuration = paramInt;
  }
  
  public void setSelection(int paramInt)
  {
    if ((mAdapter instanceof PinnedHeaderIndexerListAdapter))
    {
      PinnedHeaderIndexerListAdapter localPinnedHeaderIndexerListAdapter = (PinnedHeaderIndexerListAdapter)mAdapter;
      if ((!getItemPlacementInSectiongetHeaderViewsCountfirstInSection) && (localPinnedHeaderIndexerListAdapter.isSectionHeaderDisplayEnabled()) && (mSize > 0))
      {
        super.setSelectionFromTop(paramInt, getPinnedHeaderHeight(0));
        return;
      }
    }
    super.setSelection(paramInt);
  }
  
  static final class PinnedHeader
  {
    int alpha;
    boolean animating;
    int height;
    int sourceY;
    int state;
    long targetTime;
    boolean targetVisible;
    int targetY;
    View view;
    boolean visible;
    int y;
  }
  
  public static abstract interface PinnedHeaderAdapter
  {
    public abstract void configurePinnedHeaders(PinnedHeaderListView paramPinnedHeaderListView);
    
    public abstract int getPinnedHeaderCount();
    
    public abstract View getPinnedHeaderView(int paramInt, View paramView, ViewGroup paramViewGroup);
    
    public abstract int getScrollPositionForHeader(int paramInt);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.PinnedHeaderListView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */