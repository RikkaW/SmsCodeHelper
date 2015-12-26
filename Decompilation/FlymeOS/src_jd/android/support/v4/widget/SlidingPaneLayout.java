package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SlidingPaneLayout
  extends ViewGroup
{
  private static final int DEFAULT_FADE_COLOR = -858993460;
  private static final int DEFAULT_OVERHANG_SIZE = 32;
  static final SlidingPanelLayoutImpl IMPL = new SlidingPanelLayoutImplBase();
  private static final int MIN_FLING_VELOCITY = 400;
  private static final String TAG = "SlidingPaneLayout";
  private boolean mCanSlide;
  private int mCoveredFadeColor;
  private final ViewDragHelper mDragHelper;
  private boolean mFirstLayout = true;
  private float mInitialMotionX;
  private float mInitialMotionY;
  private boolean mIsUnableToDrag;
  private final int mOverhangSize;
  private PanelSlideListener mPanelSlideListener;
  private int mParallaxBy;
  private float mParallaxOffset;
  private final ArrayList<DisableLayerRunnable> mPostedRunnables = new ArrayList();
  private boolean mPreservedOpenState;
  private Drawable mShadowDrawableLeft;
  private Drawable mShadowDrawableRight;
  private float mSlideOffset;
  private int mSlideRange;
  private View mSlideableView;
  private int mSliderFadeColor = -858993460;
  private final Rect mTmpRect = new Rect();
  
  static
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 17)
    {
      IMPL = new SlidingPanelLayoutImplJBMR1();
      return;
    }
    if (i >= 16)
    {
      IMPL = new SlidingPanelLayoutImplJB();
      return;
    }
  }
  
  public SlidingPaneLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public SlidingPaneLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public SlidingPaneLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    float f = getResourcesgetDisplayMetricsdensity;
    mOverhangSize = ((int)(32.0F * f + 0.5F));
    ViewConfiguration.get(paramContext);
    setWillNotDraw(false);
    ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegate());
    ViewCompat.setImportantForAccessibility(this, 1);
    mDragHelper = ViewDragHelper.create(this, 0.5F, new DragHelperCallback(null));
    mDragHelper.setMinVelocity(f * 400.0F);
  }
  
  private boolean closePane(View paramView, int paramInt)
  {
    boolean bool = false;
    if ((mFirstLayout) || (smoothSlideTo(0.0F, paramInt)))
    {
      mPreservedOpenState = false;
      bool = true;
    }
    return bool;
  }
  
  private void dimChildView(View paramView, float paramFloat, int paramInt)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    if ((paramFloat > 0.0F) && (paramInt != 0))
    {
      i = (int)(((0xFF000000 & paramInt) >>> 24) * paramFloat);
      if (dimPaint == null) {
        dimPaint = new Paint();
      }
      dimPaint.setColorFilter(new PorterDuffColorFilter(i << 24 | 0xFFFFFF & paramInt, PorterDuff.Mode.SRC_OVER));
      if (ViewCompat.getLayerType(paramView) != 2) {
        ViewCompat.setLayerType(paramView, 2, dimPaint);
      }
      invalidateChildRegion(paramView);
    }
    while (ViewCompat.getLayerType(paramView) == 0)
    {
      int i;
      return;
    }
    if (dimPaint != null) {
      dimPaint.setColorFilter(null);
    }
    paramView = new DisableLayerRunnable(paramView);
    mPostedRunnables.add(paramView);
    ViewCompat.postOnAnimation(this, paramView);
  }
  
  private void invalidateChildRegion(View paramView)
  {
    IMPL.invalidateChildRegion(this, paramView);
  }
  
  private boolean isLayoutRtlSupport()
  {
    return ViewCompat.getLayoutDirection(this) == 1;
  }
  
  private void onPanelDragged(int paramInt)
  {
    if (mSlideableView == null)
    {
      mSlideOffset = 0.0F;
      return;
    }
    boolean bool = isLayoutRtlSupport();
    LayoutParams localLayoutParams = (LayoutParams)mSlideableView.getLayoutParams();
    int j = mSlideableView.getWidth();
    int i = paramInt;
    if (bool) {
      i = getWidth() - paramInt - j;
    }
    if (bool)
    {
      paramInt = getPaddingRight();
      if (!bool) {
        break label148;
      }
    }
    label148:
    for (j = rightMargin;; j = leftMargin)
    {
      mSlideOffset = ((i - (j + paramInt)) / mSlideRange);
      if (mParallaxBy != 0) {
        parallaxOtherViews(mSlideOffset);
      }
      if (dimWhenOffset) {
        dimChildView(mSlideableView, mSlideOffset, mSliderFadeColor);
      }
      dispatchOnPanelSlide(mSlideableView);
      return;
      paramInt = getPaddingLeft();
      break;
    }
  }
  
  private boolean openPane(View paramView, int paramInt)
  {
    if ((mFirstLayout) || (smoothSlideTo(1.0F, paramInt)))
    {
      mPreservedOpenState = true;
      return true;
    }
    return false;
  }
  
  private void parallaxOtherViews(float paramFloat)
  {
    boolean bool = isLayoutRtlSupport();
    Object localObject = (LayoutParams)mSlideableView.getLayoutParams();
    int i;
    label43:
    int j;
    if (dimWhenOffset) {
      if (bool)
      {
        i = rightMargin;
        if (i > 0) {
          break label94;
        }
        i = 1;
        int n = getChildCount();
        j = 0;
        label52:
        if (j >= n) {
          return;
        }
        localObject = getChildAt(j);
        if (localObject != mSlideableView) {
          break label99;
        }
      }
    }
    label94:
    label99:
    do
    {
      j += 1;
      break label52;
      i = leftMargin;
      break;
      i = 0;
      break label43;
      int k = (int)((1.0F - mParallaxOffset) * mParallaxBy);
      mParallaxOffset = paramFloat;
      int m = k - (int)((1.0F - paramFloat) * mParallaxBy);
      k = m;
      if (bool) {
        k = -m;
      }
      ((View)localObject).offsetLeftAndRight(k);
    } while (i == 0);
    if (bool) {}
    for (float f = mParallaxOffset - 1.0F;; f = 1.0F - mParallaxOffset)
    {
      dimChildView((View)localObject, f, mCoveredFadeColor);
      break;
    }
  }
  
  private static boolean viewIsOpaque(View paramView)
  {
    if (ViewCompat.isOpaque(paramView)) {}
    do
    {
      return true;
      if (Build.VERSION.SDK_INT >= 18) {
        return false;
      }
      paramView = paramView.getBackground();
      if (paramView == null) {
        break;
      }
    } while (paramView.getOpacity() == -1);
    return false;
    return false;
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
        View localView = localViewGroup.getChildAt(i);
        if ((paramInt2 + j < localView.getLeft()) || (paramInt2 + j >= localView.getRight()) || (paramInt3 + k < localView.getTop()) || (paramInt3 + k >= localView.getBottom()) || (!canScroll(localView, true, paramInt1, paramInt2 + j - localView.getLeft(), paramInt3 + k - localView.getTop()))) {}
      }
    }
    for (;;)
    {
      return true;
      i -= 1;
      break;
      if (paramBoolean) {
        if (!isLayoutRtlSupport()) {
          break label165;
        }
      }
      while (!ViewCompat.canScrollHorizontally(paramView, paramInt1))
      {
        return false;
        label165:
        paramInt1 = -paramInt1;
      }
    }
  }
  
  @Deprecated
  public boolean canSlide()
  {
    return mCanSlide;
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return ((paramLayoutParams instanceof LayoutParams)) && (super.checkLayoutParams(paramLayoutParams));
  }
  
  public boolean closePane()
  {
    return closePane(mSlideableView, 0);
  }
  
  public void computeScroll()
  {
    if (mDragHelper.continueSettling(true))
    {
      if (!mCanSlide) {
        mDragHelper.abort();
      }
    }
    else {
      return;
    }
    ViewCompat.postInvalidateOnAnimation(this);
  }
  
  void dispatchOnPanelClosed(View paramView)
  {
    if (mPanelSlideListener != null) {
      mPanelSlideListener.onPanelClosed(paramView);
    }
    sendAccessibilityEvent(32);
  }
  
  void dispatchOnPanelOpened(View paramView)
  {
    if (mPanelSlideListener != null) {
      mPanelSlideListener.onPanelOpened(paramView);
    }
    sendAccessibilityEvent(32);
  }
  
  void dispatchOnPanelSlide(View paramView)
  {
    if (mPanelSlideListener != null) {
      mPanelSlideListener.onPanelSlide(paramView, mSlideOffset);
    }
  }
  
  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    Drawable localDrawable;
    if (isLayoutRtlSupport())
    {
      localDrawable = mShadowDrawableRight;
      if (getChildCount() <= 1) {
        break label53;
      }
    }
    label53:
    for (View localView = getChildAt(1);; localView = null)
    {
      if ((localView != null) && (localDrawable != null)) {
        break label59;
      }
      return;
      localDrawable = mShadowDrawableLeft;
      break;
    }
    label59:
    int k = localView.getTop();
    int m = localView.getBottom();
    int n = localDrawable.getIntrinsicWidth();
    int j;
    int i;
    if (isLayoutRtlSupport())
    {
      j = localView.getRight();
      i = j + n;
    }
    for (;;)
    {
      localDrawable.setBounds(j, k, i, m);
      localDrawable.draw(paramCanvas);
      return;
      i = localView.getLeft();
      j = i - n;
    }
  }
  
  protected boolean drawChild(Canvas paramCanvas, View paramView, long paramLong)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    int i = paramCanvas.save(2);
    boolean bool;
    if ((mCanSlide) && (!slideable) && (mSlideableView != null))
    {
      paramCanvas.getClipBounds(mTmpRect);
      if (isLayoutRtlSupport())
      {
        mTmpRect.left = Math.max(mTmpRect.left, mSlideableView.getRight());
        paramCanvas.clipRect(mTmpRect);
      }
    }
    else
    {
      if (Build.VERSION.SDK_INT < 11) {
        break label140;
      }
      bool = super.drawChild(paramCanvas, paramView, paramLong);
    }
    for (;;)
    {
      paramCanvas.restoreToCount(i);
      return bool;
      mTmpRect.right = Math.min(mTmpRect.right, mSlideableView.getLeft());
      break;
      label140:
      if ((dimWhenOffset) && (mSlideOffset > 0.0F))
      {
        if (!paramView.isDrawingCacheEnabled()) {
          paramView.setDrawingCacheEnabled(true);
        }
        Bitmap localBitmap = paramView.getDrawingCache();
        if (localBitmap != null)
        {
          paramCanvas.drawBitmap(localBitmap, paramView.getLeft(), paramView.getTop(), dimPaint);
          bool = false;
        }
        else
        {
          Log.e("SlidingPaneLayout", "drawChild: child view " + paramView + " returned null drawing cache");
          bool = super.drawChild(paramCanvas, paramView, paramLong);
        }
      }
      else
      {
        if (paramView.isDrawingCacheEnabled()) {
          paramView.setDrawingCacheEnabled(false);
        }
        bool = super.drawChild(paramCanvas, paramView, paramLong);
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
    if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams)) {
      return new LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams);
    }
    return new LayoutParams(paramLayoutParams);
  }
  
  public int getCoveredFadeColor()
  {
    return mCoveredFadeColor;
  }
  
  public int getParallaxDistance()
  {
    return mParallaxBy;
  }
  
  public int getSliderFadeColor()
  {
    return mSliderFadeColor;
  }
  
  boolean isDimmed(View paramView)
  {
    if (paramView == null) {
      return false;
    }
    paramView = (LayoutParams)paramView.getLayoutParams();
    if ((mCanSlide) && (dimWhenOffset) && (mSlideOffset > 0.0F)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isOpen()
  {
    return (!mCanSlide) || (mSlideOffset == 1.0F);
  }
  
  public boolean isSlideable()
  {
    return mCanSlide;
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    mFirstLayout = true;
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    mFirstLayout = true;
    int j = mPostedRunnables.size();
    int i = 0;
    while (i < j)
    {
      ((DisableLayerRunnable)mPostedRunnables.get(i)).run();
      i += 1;
    }
    mPostedRunnables.clear();
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool2 = false;
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    if ((!mCanSlide) && (i == 0) && (getChildCount() > 1))
    {
      View localView = getChildAt(1);
      if (localView != null) {
        if (mDragHelper.isViewUnder(localView, (int)paramMotionEvent.getX(), (int)paramMotionEvent.getY())) {
          break label108;
        }
      }
    }
    label108:
    for (boolean bool1 = true;; bool1 = false)
    {
      mPreservedOpenState = bool1;
      if ((mCanSlide) && ((!mIsUnableToDrag) || (i == 0))) {
        break;
      }
      mDragHelper.cancel();
      bool1 = super.onInterceptTouchEvent(paramMotionEvent);
      return bool1;
    }
    if ((i == 3) || (i == 1))
    {
      mDragHelper.cancel();
      return false;
    }
    switch (i)
    {
    }
    label164:
    float f1;
    float f2;
    do
    {
      for (i = 0;; i = 1)
      {
        if (!mDragHelper.shouldInterceptTouchEvent(paramMotionEvent))
        {
          bool1 = bool2;
          if (i == 0) {
            break;
          }
        }
        return true;
        mIsUnableToDrag = false;
        f1 = paramMotionEvent.getX();
        f2 = paramMotionEvent.getY();
        mInitialMotionX = f1;
        mInitialMotionY = f2;
        if ((!mDragHelper.isViewUnder(mSlideableView, (int)f1, (int)f2)) || (!isDimmed(mSlideableView))) {
          break label164;
        }
      }
      f2 = paramMotionEvent.getX();
      f1 = paramMotionEvent.getY();
      f2 = Math.abs(f2 - mInitialMotionX);
      f1 = Math.abs(f1 - mInitialMotionY);
    } while ((f2 <= mDragHelper.getTouchSlop()) || (f1 <= f2));
    mDragHelper.cancel();
    mIsUnableToDrag = true;
    return false;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    boolean bool = isLayoutRtlSupport();
    int k;
    label35:
    label46:
    int n;
    int m;
    if (bool)
    {
      mDragHelper.setEdgeTrackingEnabled(2);
      k = paramInt3 - paramInt1;
      if (!bool) {
        break label154;
      }
      paramInt1 = getPaddingRight();
      if (!bool) {
        break label162;
      }
      paramInt4 = getPaddingLeft();
      n = getPaddingTop();
      m = getChildCount();
      if (mFirstLayout) {
        if ((!mCanSlide) || (!mPreservedOpenState)) {
          break label171;
        }
      }
    }
    View localView;
    label154:
    label162:
    label171:
    for (float f = 1.0F;; f = 0.0F)
    {
      mSlideOffset = f;
      int i = 0;
      for (paramInt2 = paramInt1;; paramInt2 = paramInt3)
      {
        if (i >= m) {
          break label451;
        }
        localView = getChildAt(i);
        if (localView.getVisibility() != 8) {
          break;
        }
        paramInt3 = paramInt1;
        paramInt1 = paramInt2;
        paramInt2 = paramInt3;
        i += 1;
        paramInt3 = paramInt1;
        paramInt1 = paramInt2;
      }
      mDragHelper.setEdgeTrackingEnabled(1);
      break;
      paramInt1 = getPaddingLeft();
      break label35;
      paramInt4 = getPaddingRight();
      break label46;
    }
    LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
    int i1 = localView.getMeasuredWidth();
    int j;
    if (slideable)
    {
      paramInt3 = leftMargin;
      j = rightMargin;
      j = Math.min(paramInt1, k - paramInt4 - mOverhangSize) - paramInt2 - (paramInt3 + j);
      mSlideRange = j;
      if (bool)
      {
        paramInt3 = rightMargin;
        label258:
        if (paramInt2 + paramInt3 + j + i1 / 2 <= k - paramInt4) {
          break label388;
        }
        paramBoolean = true;
        label280:
        dimWhenOffset = paramBoolean;
        j = (int)(j * mSlideOffset);
        paramInt2 += paramInt3 + j;
        mSlideOffset = (j / mSlideRange);
        paramInt3 = 0;
        label321:
        if (!bool) {
          break label435;
        }
        j = k - paramInt2 + paramInt3;
        paramInt3 = j - i1;
      }
    }
    for (;;)
    {
      localView.layout(paramInt3, n, j, localView.getMeasuredHeight() + n);
      paramInt3 = localView.getWidth() + paramInt1;
      paramInt1 = paramInt2;
      paramInt2 = paramInt3;
      break;
      paramInt3 = leftMargin;
      break label258;
      label388:
      paramBoolean = false;
      break label280;
      if ((mCanSlide) && (mParallaxBy != 0))
      {
        paramInt3 = (int)((1.0F - mSlideOffset) * mParallaxBy);
        paramInt2 = paramInt1;
        break label321;
      }
      paramInt3 = 0;
      paramInt2 = paramInt1;
      break label321;
      label435:
      paramInt3 = paramInt2 - paramInt3;
      j = paramInt3 + i1;
    }
    label451:
    if (mFirstLayout)
    {
      if (!mCanSlide) {
        break label526;
      }
      if (mParallaxBy != 0) {
        parallaxOtherViews(mSlideOffset);
      }
      if (mSlideableView.getLayoutParams()).dimWhenOffset) {
        dimChildView(mSlideableView, mSlideOffset, mSliderFadeColor);
      }
    }
    for (;;)
    {
      updateObscuredViewsVisibility(mSlideableView);
      mFirstLayout = false;
      return;
      label526:
      paramInt1 = 0;
      while (paramInt1 < m)
      {
        dimChildView(getChildAt(paramInt1), 0.0F, mSliderFadeColor);
        paramInt1 += 1;
      }
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int k = View.MeasureSpec.getMode(paramInt1);
    int i = View.MeasureSpec.getSize(paramInt1);
    int j = View.MeasureSpec.getMode(paramInt2);
    paramInt1 = View.MeasureSpec.getSize(paramInt2);
    if (k != 1073741824) {
      if (isInEditMode()) {
        if (k == Integer.MIN_VALUE)
        {
          paramInt2 = i;
          i = j;
        }
      }
    }
    for (;;)
    {
      label85:
      boolean bool1;
      int i2;
      int i3;
      int i1;
      float f1;
      label136:
      View localView;
      LayoutParams localLayoutParams;
      switch (i)
      {
      default: 
        paramInt1 = 0;
        k = -1;
        bool1 = false;
        i2 = paramInt2 - getPaddingLeft() - getPaddingRight();
        i3 = getChildCount();
        if (i3 > 2) {
          Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported.");
        }
        mSlideableView = null;
        i1 = 0;
        j = i2;
        f1 = 0.0F;
        if (i1 >= i3) {
          break label617;
        }
        localView = getChildAt(i1);
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (localView.getVisibility() == 8)
        {
          dimWhenOffset = false;
          m = j;
          j = paramInt1;
          paramInt1 = m;
        }
        break;
      }
      float f2;
      for (;;)
      {
        i1 += 1;
        m = j;
        j = paramInt1;
        paramInt1 = m;
        break label136;
        if (k != 0) {
          break label1105;
        }
        i = j;
        paramInt2 = 300;
        break;
        throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
        if (j != 0) {
          break label1105;
        }
        if (isInEditMode())
        {
          if (j != 0) {
            break label1105;
          }
          j = Integer.MIN_VALUE;
          paramInt2 = i;
          paramInt1 = 300;
          i = j;
          break;
        }
        throw new IllegalStateException("Height must not be UNSPECIFIED");
        paramInt1 = paramInt1 - getPaddingTop() - getPaddingBottom();
        k = paramInt1;
        break label85;
        k = getPaddingTop();
        m = getPaddingBottom();
        j = 0;
        k = paramInt1 - k - m;
        paramInt1 = j;
        break label85;
        f2 = f1;
        if (weight <= 0.0F) {
          break label373;
        }
        f1 += weight;
        f2 = f1;
        if (width != 0) {
          break label373;
        }
        m = paramInt1;
        paramInt1 = j;
        j = m;
      }
      label373:
      int m = leftMargin + rightMargin;
      label409:
      int n;
      label429:
      int i4;
      if (width == -2)
      {
        m = View.MeasureSpec.makeMeasureSpec(i2 - m, Integer.MIN_VALUE);
        if (height != -2) {
          break label573;
        }
        n = View.MeasureSpec.makeMeasureSpec(k, Integer.MIN_VALUE);
        localView.measure(m, n);
        n = localView.getMeasuredWidth();
        i4 = localView.getMeasuredHeight();
        m = paramInt1;
        if (i == Integer.MIN_VALUE)
        {
          m = paramInt1;
          if (i4 > paramInt1) {
            m = Math.min(i4, k);
          }
        }
        paramInt1 = j - n;
        if (paramInt1 >= 0) {
          break label611;
        }
      }
      label573:
      label611:
      for (boolean bool2 = true;; bool2 = false)
      {
        slideable = bool2;
        if (slideable) {
          mSlideableView = localView;
        }
        j = m;
        bool1 = bool2 | bool1;
        f1 = f2;
        break;
        if (width == -1)
        {
          m = View.MeasureSpec.makeMeasureSpec(i2 - m, 1073741824);
          break label409;
        }
        m = View.MeasureSpec.makeMeasureSpec(width, 1073741824);
        break label409;
        if (height == -1)
        {
          n = View.MeasureSpec.makeMeasureSpec(k, 1073741824);
          break label429;
        }
        n = View.MeasureSpec.makeMeasureSpec(height, 1073741824);
        break label429;
      }
      label617:
      if ((bool1) || (f1 > 0.0F))
      {
        i1 = i2 - mOverhangSize;
        m = 0;
        if (m < i3)
        {
          localView = getChildAt(m);
          if (localView.getVisibility() == 8) {}
          for (;;)
          {
            m += 1;
            break;
            localLayoutParams = (LayoutParams)localView.getLayoutParams();
            if (localView.getVisibility() != 8)
            {
              if ((width == 0) && (weight > 0.0F))
              {
                i = 1;
                label715:
                if (i == 0) {
                  break label811;
                }
                n = 0;
                label723:
                if ((!bool1) || (localView == mSlideableView)) {
                  break label875;
                }
                if ((width >= 0) || ((n <= i1) && (weight <= 0.0F))) {
                  continue;
                }
                if (i == 0) {
                  break label859;
                }
                if (height != -2) {
                  break label821;
                }
                i = View.MeasureSpec.makeMeasureSpec(k, Integer.MIN_VALUE);
              }
              for (;;)
              {
                localView.measure(View.MeasureSpec.makeMeasureSpec(i1, 1073741824), i);
                break;
                i = 0;
                break label715;
                label811:
                n = localView.getMeasuredWidth();
                break label723;
                label821:
                if (height == -1)
                {
                  i = View.MeasureSpec.makeMeasureSpec(k, 1073741824);
                }
                else
                {
                  i = View.MeasureSpec.makeMeasureSpec(height, 1073741824);
                  continue;
                  label859:
                  i = View.MeasureSpec.makeMeasureSpec(localView.getMeasuredHeight(), 1073741824);
                }
              }
              label875:
              if (weight > 0.0F)
              {
                if (width == 0) {
                  if (height == -2) {
                    i = View.MeasureSpec.makeMeasureSpec(k, Integer.MIN_VALUE);
                  }
                }
                for (;;)
                {
                  if (!bool1) {
                    break label1021;
                  }
                  i4 = leftMargin;
                  i4 = i2 - (rightMargin + i4);
                  int i5 = View.MeasureSpec.makeMeasureSpec(i4, 1073741824);
                  if (n == i4) {
                    break;
                  }
                  localView.measure(i5, i);
                  break;
                  if (height == -1)
                  {
                    i = View.MeasureSpec.makeMeasureSpec(k, 1073741824);
                  }
                  else
                  {
                    i = View.MeasureSpec.makeMeasureSpec(height, 1073741824);
                    continue;
                    i = View.MeasureSpec.makeMeasureSpec(localView.getMeasuredHeight(), 1073741824);
                  }
                }
                label1021:
                i4 = Math.max(0, j);
                localView.measure(View.MeasureSpec.makeMeasureSpec((int)(weight * i4 / f1) + n, 1073741824), i);
              }
            }
          }
        }
      }
      setMeasuredDimension(paramInt2, getPaddingTop() + paramInt1 + getPaddingBottom());
      mCanSlide = bool1;
      if ((mDragHelper.getViewDragState() != 0) && (!bool1)) {
        mDragHelper.abort();
      }
      return;
      label1105:
      paramInt2 = i;
      i = j;
    }
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    if (isOpen) {
      openPane();
    }
    for (;;)
    {
      mPreservedOpenState = isOpen;
      return;
      closePane();
    }
  }
  
  protected Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    if (isSlideable()) {}
    for (boolean bool = isOpen();; bool = mPreservedOpenState)
    {
      isOpen = bool;
      return localSavedState;
    }
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramInt1 != paramInt3) {
      mFirstLayout = true;
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool1;
    if (!mCanSlide) {
      bool1 = super.onTouchEvent(paramMotionEvent);
    }
    float f1;
    float f2;
    do
    {
      int i;
      boolean bool2;
      float f3;
      float f4;
      do
      {
        do
        {
          return bool1;
          mDragHelper.processTouchEvent(paramMotionEvent);
          i = paramMotionEvent.getAction();
          bool2 = true;
          switch (i & 0xFF)
          {
          default: 
            return true;
          case 0: 
            f1 = paramMotionEvent.getX();
            f2 = paramMotionEvent.getY();
            mInitialMotionX = f1;
            mInitialMotionY = f2;
            return true;
          }
          bool1 = bool2;
        } while (!isDimmed(mSlideableView));
        f1 = paramMotionEvent.getX();
        f2 = paramMotionEvent.getY();
        f3 = f1 - mInitialMotionX;
        f4 = f2 - mInitialMotionY;
        i = mDragHelper.getTouchSlop();
        bool1 = bool2;
      } while (f3 * f3 + f4 * f4 >= i * i);
      bool1 = bool2;
    } while (!mDragHelper.isViewUnder(mSlideableView, (int)f1, (int)f2));
    closePane(mSlideableView, 0);
    return true;
  }
  
  public boolean openPane()
  {
    return openPane(mSlideableView, 0);
  }
  
  public void requestChildFocus(View paramView1, View paramView2)
  {
    super.requestChildFocus(paramView1, paramView2);
    if ((!isInTouchMode()) && (!mCanSlide)) {
      if (paramView1 != mSlideableView) {
        break label36;
      }
    }
    label36:
    for (boolean bool = true;; bool = false)
    {
      mPreservedOpenState = bool;
      return;
    }
  }
  
  void setAllChildrenVisible()
  {
    int j = getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = getChildAt(i);
      if (localView.getVisibility() == 4) {
        localView.setVisibility(0);
      }
      i += 1;
    }
  }
  
  public void setCoveredFadeColor(int paramInt)
  {
    mCoveredFadeColor = paramInt;
  }
  
  public void setPanelSlideListener(PanelSlideListener paramPanelSlideListener)
  {
    mPanelSlideListener = paramPanelSlideListener;
  }
  
  public void setParallaxDistance(int paramInt)
  {
    mParallaxBy = paramInt;
    requestLayout();
  }
  
  @Deprecated
  public void setShadowDrawable(Drawable paramDrawable)
  {
    setShadowDrawableLeft(paramDrawable);
  }
  
  public void setShadowDrawableLeft(Drawable paramDrawable)
  {
    mShadowDrawableLeft = paramDrawable;
  }
  
  public void setShadowDrawableRight(Drawable paramDrawable)
  {
    mShadowDrawableRight = paramDrawable;
  }
  
  @Deprecated
  public void setShadowResource(int paramInt)
  {
    setShadowDrawable(getResources().getDrawable(paramInt));
  }
  
  public void setShadowResourceLeft(int paramInt)
  {
    setShadowDrawableLeft(getResources().getDrawable(paramInt));
  }
  
  public void setShadowResourceRight(int paramInt)
  {
    setShadowDrawableRight(getResources().getDrawable(paramInt));
  }
  
  public void setSliderFadeColor(int paramInt)
  {
    mSliderFadeColor = paramInt;
  }
  
  @Deprecated
  public void smoothSlideClosed()
  {
    closePane();
  }
  
  @Deprecated
  public void smoothSlideOpen()
  {
    openPane();
  }
  
  boolean smoothSlideTo(float paramFloat, int paramInt)
  {
    if (!mCanSlide) {
      return false;
    }
    boolean bool = isLayoutRtlSupport();
    LayoutParams localLayoutParams = (LayoutParams)mSlideableView.getLayoutParams();
    int i;
    int j;
    if (bool)
    {
      paramInt = getPaddingRight();
      i = rightMargin;
      j = mSlideableView.getWidth();
    }
    for (paramInt = (int)(getWidth() - (i + paramInt + mSlideRange * paramFloat + j)); mDragHelper.smoothSlideViewTo(mSlideableView, paramInt, mSlideableView.getTop()); paramInt = (int)(leftMargin + paramInt + mSlideRange * paramFloat))
    {
      setAllChildrenVisible();
      ViewCompat.postInvalidateOnAnimation(this);
      return true;
      paramInt = getPaddingLeft();
    }
    return false;
  }
  
  void updateObscuredViewsVisibility(View paramView)
  {
    boolean bool = isLayoutRtlSupport();
    int i;
    int j;
    label31:
    int i4;
    int i5;
    int i6;
    int i1;
    int n;
    int m;
    int k;
    if (bool)
    {
      i = getWidth() - getPaddingRight();
      if (!bool) {
        break label123;
      }
      j = getPaddingLeft();
      i4 = getPaddingTop();
      i5 = getHeight();
      i6 = getPaddingBottom();
      if ((paramView == null) || (!viewIsOpaque(paramView))) {
        break label136;
      }
      i1 = paramView.getLeft();
      n = paramView.getRight();
      m = paramView.getTop();
      k = paramView.getBottom();
    }
    int i2;
    View localView;
    for (;;)
    {
      int i7 = getChildCount();
      i2 = 0;
      if (i2 < i7)
      {
        localView = getChildAt(i2);
        if (localView != paramView) {
          break label151;
        }
      }
      return;
      i = getPaddingLeft();
      break;
      label123:
      j = getWidth() - getPaddingRight();
      break label31;
      label136:
      k = 0;
      m = 0;
      n = 0;
      i1 = 0;
    }
    label151:
    if (bool)
    {
      i3 = j;
      label159:
      int i8 = Math.max(i3, localView.getLeft());
      int i9 = Math.max(i4, localView.getTop());
      if (!bool) {
        break label271;
      }
      i3 = i;
      label191:
      i3 = Math.min(i3, localView.getRight());
      int i10 = Math.min(i5 - i6, localView.getBottom());
      if ((i8 < i1) || (i9 < m) || (i3 > n) || (i10 > k)) {
        break label277;
      }
    }
    label271:
    label277:
    for (int i3 = 4;; i3 = 0)
    {
      localView.setVisibility(i3);
      i2 += 1;
      break;
      i3 = i;
      break label159;
      i3 = j;
      break label191;
    }
  }
  
  class AccessibilityDelegate
    extends AccessibilityDelegateCompat
  {
    private final Rect mTmpRect = new Rect();
    
    AccessibilityDelegate() {}
    
    private void copyNodeInfoNoChildren(AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat1, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat2)
    {
      Rect localRect = mTmpRect;
      paramAccessibilityNodeInfoCompat2.getBoundsInParent(localRect);
      paramAccessibilityNodeInfoCompat1.setBoundsInParent(localRect);
      paramAccessibilityNodeInfoCompat2.getBoundsInScreen(localRect);
      paramAccessibilityNodeInfoCompat1.setBoundsInScreen(localRect);
      paramAccessibilityNodeInfoCompat1.setVisibleToUser(paramAccessibilityNodeInfoCompat2.isVisibleToUser());
      paramAccessibilityNodeInfoCompat1.setPackageName(paramAccessibilityNodeInfoCompat2.getPackageName());
      paramAccessibilityNodeInfoCompat1.setClassName(paramAccessibilityNodeInfoCompat2.getClassName());
      paramAccessibilityNodeInfoCompat1.setContentDescription(paramAccessibilityNodeInfoCompat2.getContentDescription());
      paramAccessibilityNodeInfoCompat1.setEnabled(paramAccessibilityNodeInfoCompat2.isEnabled());
      paramAccessibilityNodeInfoCompat1.setClickable(paramAccessibilityNodeInfoCompat2.isClickable());
      paramAccessibilityNodeInfoCompat1.setFocusable(paramAccessibilityNodeInfoCompat2.isFocusable());
      paramAccessibilityNodeInfoCompat1.setFocused(paramAccessibilityNodeInfoCompat2.isFocused());
      paramAccessibilityNodeInfoCompat1.setAccessibilityFocused(paramAccessibilityNodeInfoCompat2.isAccessibilityFocused());
      paramAccessibilityNodeInfoCompat1.setSelected(paramAccessibilityNodeInfoCompat2.isSelected());
      paramAccessibilityNodeInfoCompat1.setLongClickable(paramAccessibilityNodeInfoCompat2.isLongClickable());
      paramAccessibilityNodeInfoCompat1.addAction(paramAccessibilityNodeInfoCompat2.getActions());
      paramAccessibilityNodeInfoCompat1.setMovementGranularities(paramAccessibilityNodeInfoCompat2.getMovementGranularities());
    }
    
    public boolean filter(View paramView)
    {
      return isDimmed(paramView);
    }
    
    public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
    {
      super.onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
      paramAccessibilityEvent.setClassName(SlidingPaneLayout.class.getName());
    }
    
    public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat = AccessibilityNodeInfoCompat.obtain(paramAccessibilityNodeInfoCompat);
      super.onInitializeAccessibilityNodeInfo(paramView, localAccessibilityNodeInfoCompat);
      copyNodeInfoNoChildren(paramAccessibilityNodeInfoCompat, localAccessibilityNodeInfoCompat);
      localAccessibilityNodeInfoCompat.recycle();
      paramAccessibilityNodeInfoCompat.setClassName(SlidingPaneLayout.class.getName());
      paramAccessibilityNodeInfoCompat.setSource(paramView);
      paramView = ViewCompat.getParentForAccessibility(paramView);
      if ((paramView instanceof View)) {
        paramAccessibilityNodeInfoCompat.setParent((View)paramView);
      }
      int j = getChildCount();
      int i = 0;
      while (i < j)
      {
        paramView = getChildAt(i);
        if ((!filter(paramView)) && (paramView.getVisibility() == 0))
        {
          ViewCompat.setImportantForAccessibility(paramView, 1);
          paramAccessibilityNodeInfoCompat.addChild(paramView);
        }
        i += 1;
      }
    }
    
    public boolean onRequestSendAccessibilityEvent(ViewGroup paramViewGroup, View paramView, AccessibilityEvent paramAccessibilityEvent)
    {
      if (!filter(paramView)) {
        return super.onRequestSendAccessibilityEvent(paramViewGroup, paramView, paramAccessibilityEvent);
      }
      return false;
    }
  }
  
  class DisableLayerRunnable
    implements Runnable
  {
    final View mChildView;
    
    DisableLayerRunnable(View paramView)
    {
      mChildView = paramView;
    }
    
    public void run()
    {
      if (mChildView.getParent() == SlidingPaneLayout.this)
      {
        ViewCompat.setLayerType(mChildView, 0, null);
        SlidingPaneLayout.this.invalidateChildRegion(mChildView);
      }
      mPostedRunnables.remove(this);
    }
  }
  
  class DragHelperCallback
    extends ViewDragHelper.Callback
  {
    private DragHelperCallback() {}
    
    public int clampViewPositionHorizontal(View paramView, int paramInt1, int paramInt2)
    {
      paramView = (SlidingPaneLayout.LayoutParams)mSlideableView.getLayoutParams();
      if (SlidingPaneLayout.this.isLayoutRtlSupport())
      {
        paramInt2 = getWidth();
        i = getPaddingRight();
        paramInt2 -= rightMargin + i + mSlideableView.getWidth();
        i = mSlideRange;
        return Math.max(Math.min(paramInt1, paramInt2), paramInt2 - i);
      }
      paramInt2 = getPaddingLeft();
      paramInt2 = leftMargin + paramInt2;
      int i = mSlideRange;
      return Math.min(Math.max(paramInt1, paramInt2), i + paramInt2);
    }
    
    public int clampViewPositionVertical(View paramView, int paramInt1, int paramInt2)
    {
      return paramView.getTop();
    }
    
    public int getViewHorizontalDragRange(View paramView)
    {
      return mSlideRange;
    }
    
    public void onEdgeDragStarted(int paramInt1, int paramInt2)
    {
      mDragHelper.captureChildView(mSlideableView, paramInt2);
    }
    
    public void onViewCaptured(View paramView, int paramInt)
    {
      setAllChildrenVisible();
    }
    
    public void onViewDragStateChanged(int paramInt)
    {
      if (mDragHelper.getViewDragState() == 0)
      {
        if (mSlideOffset == 0.0F)
        {
          updateObscuredViewsVisibility(mSlideableView);
          dispatchOnPanelClosed(mSlideableView);
          SlidingPaneLayout.access$502(SlidingPaneLayout.this, false);
        }
      }
      else {
        return;
      }
      dispatchOnPanelOpened(mSlideableView);
      SlidingPaneLayout.access$502(SlidingPaneLayout.this, true);
    }
    
    public void onViewPositionChanged(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      SlidingPaneLayout.this.onPanelDragged(paramInt1);
      invalidate();
    }
    
    public void onViewReleased(View paramView, float paramFloat1, float paramFloat2)
    {
      SlidingPaneLayout.LayoutParams localLayoutParams = (SlidingPaneLayout.LayoutParams)paramView.getLayoutParams();
      int i;
      int j;
      if (SlidingPaneLayout.this.isLayoutRtlSupport())
      {
        i = getPaddingRight();
        j = rightMargin + i;
        if (paramFloat1 >= 0.0F)
        {
          i = j;
          if (paramFloat1 == 0.0F)
          {
            i = j;
            if (mSlideOffset <= 0.5F) {}
          }
        }
        else
        {
          i = j + mSlideRange;
        }
        j = mSlideableView.getWidth();
        i = getWidth() - i - j;
      }
      for (;;)
      {
        mDragHelper.settleCapturedViewAt(i, paramView.getTop());
        invalidate();
        return;
        i = getPaddingLeft();
        j = leftMargin + i;
        if (paramFloat1 <= 0.0F)
        {
          i = j;
          if (paramFloat1 == 0.0F)
          {
            i = j;
            if (mSlideOffset <= 0.5F) {}
          }
        }
        else
        {
          i = j + mSlideRange;
        }
      }
    }
    
    public boolean tryCaptureView(View paramView, int paramInt)
    {
      if (mIsUnableToDrag) {
        return false;
      }
      return getLayoutParamsslideable;
    }
  }
  
  public static class LayoutParams
    extends ViewGroup.MarginLayoutParams
  {
    private static final int[] ATTRS = { 16843137 };
    Paint dimPaint;
    boolean dimWhenOffset;
    boolean slideable;
    public float weight = 0.0F;
    
    public LayoutParams()
    {
      super(-1);
    }
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, ATTRS);
      weight = paramContext.getFloat(0, 0.0F);
      paramContext.recycle();
    }
    
    public LayoutParams(LayoutParams paramLayoutParams)
    {
      super();
      weight = weight;
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }
  }
  
  public static abstract interface PanelSlideListener
  {
    public abstract void onPanelClosed(View paramView);
    
    public abstract void onPanelOpened(View paramView);
    
    public abstract void onPanelSlide(View paramView, float paramFloat);
  }
  
  static class SavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new SlidingPaneLayout.SavedState.1();
    boolean isOpen;
    
    private SavedState(Parcel paramParcel)
    {
      super();
      if (paramParcel.readInt() != 0) {}
      for (boolean bool = true;; bool = false)
      {
        isOpen = bool;
        return;
      }
    }
    
    SavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      if (isOpen) {}
      for (paramInt = 1;; paramInt = 0)
      {
        paramParcel.writeInt(paramInt);
        return;
      }
    }
  }
  
  public static class SimplePanelSlideListener
    implements SlidingPaneLayout.PanelSlideListener
  {
    public void onPanelClosed(View paramView) {}
    
    public void onPanelOpened(View paramView) {}
    
    public void onPanelSlide(View paramView, float paramFloat) {}
  }
  
  static abstract interface SlidingPanelLayoutImpl
  {
    public abstract void invalidateChildRegion(SlidingPaneLayout paramSlidingPaneLayout, View paramView);
  }
  
  static class SlidingPanelLayoutImplBase
    implements SlidingPaneLayout.SlidingPanelLayoutImpl
  {
    public void invalidateChildRegion(SlidingPaneLayout paramSlidingPaneLayout, View paramView)
    {
      ViewCompat.postInvalidateOnAnimation(paramSlidingPaneLayout, paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom());
    }
  }
  
  static class SlidingPanelLayoutImplJB
    extends SlidingPaneLayout.SlidingPanelLayoutImplBase
  {
    private Method mGetDisplayList;
    private Field mRecreateDisplayList;
    
    SlidingPanelLayoutImplJB()
    {
      try
      {
        mGetDisplayList = View.class.getDeclaredMethod("getDisplayList", (Class[])null);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        for (;;)
        {
          try
          {
            mRecreateDisplayList = View.class.getDeclaredField("mRecreateDisplayList");
            mRecreateDisplayList.setAccessible(true);
            return;
          }
          catch (NoSuchFieldException localNoSuchFieldException)
          {
            Log.e("SlidingPaneLayout", "Couldn't fetch mRecreateDisplayList field; dimming will be slow.", localNoSuchFieldException);
          }
          localNoSuchMethodException = localNoSuchMethodException;
          Log.e("SlidingPaneLayout", "Couldn't fetch getDisplayList method; dimming won't work right.", localNoSuchMethodException);
        }
      }
    }
    
    public void invalidateChildRegion(SlidingPaneLayout paramSlidingPaneLayout, View paramView)
    {
      if ((mGetDisplayList != null) && (mRecreateDisplayList != null)) {
        try
        {
          mRecreateDisplayList.setBoolean(paramView, true);
          mGetDisplayList.invoke(paramView, (Object[])null);
          super.invalidateChildRegion(paramSlidingPaneLayout, paramView);
          return;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            Log.e("SlidingPaneLayout", "Error refreshing display list state", localException);
          }
        }
      }
      paramView.invalidate();
    }
  }
  
  static class SlidingPanelLayoutImplJBMR1
    extends SlidingPaneLayout.SlidingPanelLayoutImplBase
  {
    public void invalidateChildRegion(SlidingPaneLayout paramSlidingPaneLayout, View paramView)
    {
      ViewCompat.setLayerPaint(paramView, getLayoutParamsdimPaint);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.SlidingPaneLayout
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */