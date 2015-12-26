package android.support.v7.internal.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.color;
import android.support.v7.appcompat.R.dimen;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.internal.view.ActionBarPolicy;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutCompat.LayoutParams;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Iterator;

public class ScrollingTabContainerView
  extends HorizontalScrollView
  implements AdapterViewCompat.OnItemClickListener
{
  private static final int ANIMATION_DURATION = 300;
  private static final int FADE_DURATION = 200;
  private static final int FIXED_WRAP_GUTTER_MIN = 16;
  private static final int MODE_FIXED = 1;
  public static final int MODE_SCROLLABLE = 0;
  private static final int MOTION_NON_ADJACENT_OFFSET = 24;
  private static final String TAG = "ScrollingTabContainerView";
  private static final Interpolator sAlphaInterpolator = new DecelerateInterpolator();
  private boolean isAtToolbar;
  private boolean mAllowCollapse;
  private int mContentHeight;
  int mMaxTabWidth;
  private int mMode = 0;
  private int mSelectedTabIndex;
  int mStackedTabMaxWidth;
  private TabClickListener mTabClickListener;
  private SlidingTabStrip mTabLayout;
  Runnable mTabSelector;
  private SpinnerCompat mTabSpinner;
  protected final VisibilityAnimListener mVisAnimListener = new VisibilityAnimListener();
  protected ViewPropertyAnimatorCompat mVisibilityAnim;
  
  public ScrollingTabContainerView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ScrollingTabContainerView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.mzActionBarTabScrollViewStyle);
  }
  
  public ScrollingTabContainerView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setOverScrollMode(2);
    setHorizontalFadingEdgeEnabled(true);
    setFadingEdgeLength(getResources().getDimensionPixelSize(R.dimen.mz_action_bar_tab_scroll_fading_edge_length));
    paramContext = ActionBarPolicy.get(paramContext);
    setContentHeight(paramContext.getTabContainerHeight());
    mStackedTabMaxWidth = paramContext.getStackedTabMaxWidth();
    mTabLayout = createTabLayout();
    addView(mTabLayout, new ViewGroup.LayoutParams(-2, -1));
  }
  
  private int calculateScrollXForTab(int paramInt, float paramFloat)
  {
    int i = 0;
    int j = 0;
    View localView2;
    View localView1;
    if (mMode == 0)
    {
      localView2 = mTabLayout.getChildAt(paramInt);
      if (paramInt + 1 >= mTabLayout.getChildCount()) {
        break label119;
      }
      localView1 = mTabLayout.getChildAt(paramInt + 1);
      if (localView2 == null) {
        break label125;
      }
    }
    label119:
    label125:
    for (paramInt = localView2.getWidth();; paramInt = 0)
    {
      i = j;
      if (localView1 != null) {
        i = localView1.getWidth();
      }
      float f = localView2.getLeft();
      i = (int)((i + paramInt) * paramFloat * 0.5F + f + localView2.getWidth() * 0.5F - getWidth() * 0.5F);
      return i;
      localView1 = null;
      break;
    }
  }
  
  private SpinnerCompat createSpinner()
  {
    SpinnerCompat localSpinnerCompat = new SpinnerCompat(getContext(), null, R.attr.actionDropDownStyle);
    localSpinnerCompat.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
    localSpinnerCompat.setOnItemClickListenerInt(this);
    return localSpinnerCompat;
  }
  
  private SlidingTabStrip createTabLayout()
  {
    SlidingTabStrip localSlidingTabStrip = new SlidingTabStrip(getContext(), null, R.attr.actionBarTabBarStyle);
    localSlidingTabStrip.setGravity(17);
    localSlidingTabStrip.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
    return localSlidingTabStrip;
  }
  
  private TabView createTabView(ActionBar.Tab paramTab, boolean paramBoolean)
  {
    paramTab = new TabView(getContext(), paramTab, paramBoolean);
    if (paramBoolean)
    {
      paramTab.setBackgroundDrawable(null);
      paramTab.setLayoutParams(new AbsListView.LayoutParams(-1, mContentHeight));
      return paramTab;
    }
    paramTab.setFocusable(true);
    if (mTabClickListener == null) {
      mTabClickListener = new TabClickListener(null);
    }
    paramTab.setOnClickListener(mTabClickListener);
    return paramTab;
  }
  
  private int dpToPx(int paramInt)
  {
    return Math.round(getResourcesgetDisplayMetricsdensity * paramInt);
  }
  
  private static boolean isAnimationRunning(Animation paramAnimation)
  {
    return (paramAnimation != null) && (paramAnimation.hasStarted()) && (!paramAnimation.hasEnded());
  }
  
  private boolean isCollapsed()
  {
    return (mTabSpinner != null) && (mTabSpinner.getParent() == this);
  }
  
  private void performCollapse()
  {
    if (isCollapsed()) {
      return;
    }
    if (mTabSpinner == null) {
      mTabSpinner = createSpinner();
    }
    removeView(mTabLayout);
    addView(mTabSpinner, new ViewGroup.LayoutParams(-2, -1));
    if (mTabSpinner.getAdapter() == null) {
      mTabSpinner.setAdapter(new TabAdapter(null));
    }
    if (mTabSelector != null)
    {
      removeCallbacks(mTabSelector);
      mTabSelector = null;
    }
    mTabSpinner.setSelection(mSelectedTabIndex);
  }
  
  private boolean performExpand()
  {
    if (!isCollapsed()) {
      return false;
    }
    removeView(mTabSpinner);
    addView(mTabLayout, new ViewGroup.LayoutParams(-2, -1));
    setTabSelected(mTabSpinner.getSelectedItemPosition());
    return false;
  }
  
  private void setSelectedTabView(int paramInt)
  {
    int j = mTabLayout.getChildCount();
    int i = 0;
    if (i < j)
    {
      View localView = mTabLayout.getChildAt(i);
      if (i == paramInt) {}
      for (boolean bool = true;; bool = false)
      {
        localView.setSelected(bool);
        i += 1;
        break;
      }
    }
  }
  
  public void addTab(ActionBar.Tab paramTab, int paramInt, boolean paramBoolean)
  {
    paramTab = createTabView(paramTab, false);
    mTabLayout.addView(paramTab, paramInt, new LinearLayoutCompat.LayoutParams(-2, -1));
    if (mTabSpinner != null) {
      ((TabAdapter)mTabSpinner.getAdapter()).notifyDataSetChanged();
    }
    if (paramBoolean)
    {
      paramTab.setSelected(true);
      mTabLayout.setIndicatorPositionFromTabPosition(mTabLayout.getChildCount() - 1, 0.0F);
    }
    if (mAllowCollapse) {
      requestLayout();
    }
  }
  
  public void addTab(ActionBar.Tab paramTab, boolean paramBoolean)
  {
    paramTab = createTabView(paramTab, false);
    mTabLayout.addView(paramTab, new LinearLayoutCompat.LayoutParams(-2, -1));
    if (mTabSpinner != null) {
      ((TabAdapter)mTabSpinner.getAdapter()).notifyDataSetChanged();
    }
    if (paramBoolean)
    {
      paramTab.setSelected(true);
      mTabLayout.setIndicatorPositionFromTabPosition(mTabLayout.getChildCount() - 1, 0.0F);
    }
    if (mAllowCollapse) {
      requestLayout();
    }
  }
  
  public void animateToTab(int paramInt)
  {
    View localView = mTabLayout.getChildAt(paramInt);
    if (mTabSelector != null) {
      removeCallbacks(mTabSelector);
    }
    mTabSelector = new ScrollingTabContainerView.1(this, localView, paramInt);
    post(mTabSelector);
  }
  
  public void animateToVisibility(int paramInt)
  {
    if (mVisibilityAnim != null) {
      mVisibilityAnim.cancel();
    }
    if (paramInt == 0)
    {
      if (getVisibility() != 0) {
        ViewCompat.setAlpha(this, 0.0F);
      }
      localViewPropertyAnimatorCompat = ViewCompat.animate(this).alpha(1.0F);
      localViewPropertyAnimatorCompat.setDuration(200L);
      localViewPropertyAnimatorCompat.setInterpolator(sAlphaInterpolator);
      localViewPropertyAnimatorCompat.setListener(mVisAnimListener.withFinalVisibility(localViewPropertyAnimatorCompat, paramInt));
      localViewPropertyAnimatorCompat.start();
      return;
    }
    ViewPropertyAnimatorCompat localViewPropertyAnimatorCompat = ViewCompat.animate(this).alpha(0.0F);
    localViewPropertyAnimatorCompat.setDuration(200L);
    localViewPropertyAnimatorCompat.setInterpolator(sAlphaInterpolator);
    localViewPropertyAnimatorCompat.setListener(mVisAnimListener.withFinalVisibility(localViewPropertyAnimatorCompat, paramInt));
    localViewPropertyAnimatorCompat.start();
  }
  
  public int getTabStripWidth()
  {
    return mTabLayout.getMeasuredWidth();
  }
  
  public void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (mTabSelector != null) {
      post(mTabSelector);
    }
  }
  
  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (Build.VERSION.SDK_INT >= 8) {
      super.onConfigurationChanged(paramConfiguration);
    }
    paramConfiguration = ActionBarPolicy.get(getContext());
    if (isAtToolbar) {}
    for (int i = paramConfiguration.getActionBarHeight();; i = paramConfiguration.getTabContainerHeight())
    {
      setContentHeight(i);
      mStackedTabMaxWidth = paramConfiguration.getStackedTabMaxWidth();
      return;
    }
  }
  
  public void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (mTabSelector != null) {
      removeCallbacks(mTabSelector);
    }
  }
  
  public void onItemClick(AdapterViewCompat<?> paramAdapterViewCompat, View paramView, int paramInt, long paramLong)
  {
    ((TabView)paramView).getTab().select();
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (mTabLayout.getParent() != this) {}
    do
    {
      View localView;
      do
      {
        return;
        localView = mTabLayout.getChildAt(mSelectedTabIndex);
      } while (localView == null);
      paramInt1 = localView.getLeft() - (getWidth() - localView.getWidth()) / 2;
    } while (paramInt1 <= 0);
    setScrollX(paramInt1);
  }
  
  public void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt2 = 1;
    int i = View.MeasureSpec.getMode(paramInt1);
    boolean bool;
    if (i == 1073741824)
    {
      bool = true;
      setFillViewport(bool);
      int j = mTabLayout.getChildCount();
      if ((j <= 1) || ((i != 1073741824) && (i != Integer.MIN_VALUE))) {
        break label195;
      }
      if (j <= 2) {
        break label182;
      }
      mMaxTabWidth = ((int)(View.MeasureSpec.getSize(paramInt1) * 0.4F));
      label72:
      mMaxTabWidth = Math.min(mMaxTabWidth, mStackedTabMaxWidth);
      label87:
      i = View.MeasureSpec.makeMeasureSpec(mContentHeight, 1073741824);
      if ((bool) || (!mAllowCollapse)) {
        break label203;
      }
      label110:
      if (paramInt2 == 0) {
        break label216;
      }
      mTabLayout.measure(0, i);
      if (mTabLayout.getMeasuredWidth() <= View.MeasureSpec.getSize(paramInt1)) {
        break label208;
      }
      performCollapse();
    }
    for (;;)
    {
      paramInt2 = getMeasuredWidth();
      super.onMeasure(paramInt1, i);
      paramInt1 = getMeasuredWidth();
      if ((bool) && (paramInt2 != paramInt1)) {
        setTabSelected(mSelectedTabIndex);
      }
      return;
      bool = false;
      break;
      label182:
      mMaxTabWidth = (View.MeasureSpec.getSize(paramInt1) / 2);
      break label72;
      label195:
      mMaxTabWidth = -1;
      break label87;
      label203:
      paramInt2 = 0;
      break label110;
      label208:
      performExpand();
      continue;
      label216:
      performExpand();
    }
  }
  
  public void removeAllTabs()
  {
    mTabLayout.removeAllViews();
    if (mTabSpinner != null) {
      ((TabAdapter)mTabSpinner.getAdapter()).notifyDataSetChanged();
    }
    if (mAllowCollapse) {
      requestLayout();
    }
  }
  
  public void removeTabAt(int paramInt)
  {
    mTabLayout.removeViewAt(paramInt);
    mTabLayout.resetPosition(paramInt);
    if (mTabSpinner != null) {
      ((TabAdapter)mTabSpinner.getAdapter()).notifyDataSetChanged();
    }
    if (mAllowCollapse) {
      requestLayout();
    }
  }
  
  public void setAllowCollapse(boolean paramBoolean)
  {
    mAllowCollapse = paramBoolean;
  }
  
  public void setContentHeight(int paramInt)
  {
    mContentHeight = paramInt;
    requestLayout();
  }
  
  public void setIndicatorDrawable(Drawable paramDrawable)
  {
    mTabLayout.setIndicatorDrawable(paramDrawable);
  }
  
  public void setScrollPosition(int paramInt, float paramFloat, boolean paramBoolean)
  {
    if (isAnimationRunning(getAnimation())) {}
    do
    {
      do
      {
        return;
      } while ((paramInt < 0) || (paramInt >= mTabLayout.getChildCount()));
      mTabLayout.setIndicatorPositionFromTabPosition(paramInt, paramFloat);
      smoothScrollTo(calculateScrollXForTab(paramInt, paramFloat), 0);
    } while (!paramBoolean);
    setSelectedTabView(Math.round(paramInt + paramFloat));
  }
  
  public void setTabSelected(int paramInt)
  {
    mSelectedTabIndex = paramInt;
    int j = mTabLayout.getChildCount();
    int i = 0;
    if (i < j)
    {
      View localView = mTabLayout.getChildAt(i);
      if (i == paramInt) {}
      for (boolean bool = true;; bool = false)
      {
        localView.setSelected(bool);
        if (bool) {
          animateToTab(paramInt);
        }
        i += 1;
        break;
      }
    }
    if ((mTabSpinner != null) && (paramInt >= 0)) {
      mTabSpinner.setSelection(paramInt);
    }
  }
  
  public void showAtToolbar(boolean paramBoolean)
  {
    if (isAtToolbar != paramBoolean)
    {
      i = 1;
      if (i == 0) {
        return;
      }
      isAtToolbar = paramBoolean;
      localObject1 = ActionBarPolicy.get(getContext());
      if (!isAtToolbar) {
        break label140;
      }
    }
    int j;
    Object localObject2;
    label140:
    for (int i = ((ActionBarPolicy)localObject1).getActionBarHeight();; i = ((ActionBarPolicy)localObject1).getTabContainerHeight())
    {
      setContentHeight(i);
      if ((mTabLayout == null) || (mTabLayout.getChildCount() <= 0)) {
        return;
      }
      int k = mTabLayout.getChildCount();
      localObject1 = new ArrayList();
      j = mSelectedTabIndex;
      i = 0;
      while (i < k)
      {
        localObject2 = (TabView)mTabLayout.getChildAt(i);
        if (((TabView)localObject2).isSelected()) {
          j = i;
        }
        ((ArrayList)localObject1).add(((TabView)localObject2).getTab());
        i += 1;
      }
      i = 0;
      break;
    }
    mTabLayout.removeAllViews();
    Object localObject1 = ((ArrayList)localObject1).iterator();
    if (((Iterator)localObject1).hasNext())
    {
      localObject2 = (ActionBar.Tab)((Iterator)localObject1).next();
      if (((ActionBar.Tab)localObject2).getPosition() == j) {}
      for (paramBoolean = true;; paramBoolean = false)
      {
        addTab((ActionBar.Tab)localObject2, paramBoolean);
        break;
      }
    }
    setTabSelected(j);
  }
  
  public void updateTab(int paramInt)
  {
    ((TabView)mTabLayout.getChildAt(paramInt)).update();
    if (mTabSpinner != null) {
      ((TabAdapter)mTabSpinner.getAdapter()).notifyDataSetChanged();
    }
    if (mAllowCollapse) {
      requestLayout();
    }
  }
  
  class SlidingTabStrip
    extends LinearLayoutCompat
  {
    private ValueAnimator mIndicatorAnimator;
    private Drawable mIndicatorDrawable;
    private int mIndicatorLeft = -1;
    private int mIndicatorRight = -1;
    private int mSelectedIndicatorColor;
    private int mSelectedIndicatorHeight;
    private final Paint mSelectedIndicatorPaint;
    private int mSelectedPosition = -1;
    private float mSelectionOffset;
    
    public SlidingTabStrip(Context paramContext, AttributeSet paramAttributeSet)
    {
      this(paramContext, paramAttributeSet, 0);
    }
    
    public SlidingTabStrip(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
      super(paramAttributeSet, paramInt);
      setWillNotDraw(false);
      mSelectedIndicatorPaint = new Paint();
      this$1 = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.MzActionBarTabBar, paramInt, 0);
      mSelectedIndicatorColor = getColor(R.styleable.MzActionBarTabBar_mzTabBarIndicatorColor, getResources().getColor(R.color.mz_action_bar_tab_indicator_default_color));
      mSelectedIndicatorPaint.setColor(mSelectedIndicatorColor);
      mSelectedIndicatorHeight = getDimensionPixelSize(R.styleable.MzActionBarTabBar_mzTabBarIndicatorHeight, getResources().getDimensionPixelSize(R.dimen.mz_action_bar_tab_indicator_height));
      mIndicatorDrawable = getDrawable(R.styleable.MzActionBarTabBar_mzTabBarIndicatorDrawable);
      recycle();
      setMotionEventSplittingEnabled(false);
    }
    
    private void setIndicatorPosition(int paramInt1, int paramInt2)
    {
      if ((paramInt1 != mIndicatorLeft) || (paramInt2 != mIndicatorRight))
      {
        mIndicatorLeft = paramInt1;
        mIndicatorRight = paramInt2;
        ViewCompat.postInvalidateOnAnimation(this);
      }
    }
    
    private void updateIndicatorPosition()
    {
      View localView = getChildAt(mSelectedPosition);
      int i;
      int j;
      if ((localView != null) && (localView.getWidth() > 0))
      {
        int m = localView.getLeft();
        int k = localView.getRight();
        i = k;
        j = m;
        if (mSelectionOffset > 0.0F)
        {
          i = k;
          j = m;
          if (mSelectedPosition < getChildCount() - 1)
          {
            localView = getChildAt(mSelectedPosition + 1);
            float f1 = mSelectionOffset;
            float f2 = localView.getLeft();
            float f3 = mSelectionOffset;
            j = (int)(m * (1.0F - f3) + f1 * f2);
            f1 = mSelectionOffset;
            f2 = localView.getRight();
            f3 = mSelectionOffset;
            i = (int)(k * (1.0F - f3) + f2 * f1);
          }
        }
      }
      for (;;)
      {
        setIndicatorPosition(j, i);
        return;
        i = -1;
        j = -1;
      }
    }
    
    void animateIndicatorToPosition(int paramInt1, int paramInt2)
    {
      if (ViewCompat.getLayoutDirection(this) == 1) {}
      View localView = getChildAt(paramInt1);
      if (localView == null) {
        return;
      }
      int i = localView.getLeft();
      int j = localView.getRight();
      int k = mIndicatorLeft;
      int m = mIndicatorRight;
      if (((k != i) || (m != j)) && (k >= 0) && (m >= 0))
      {
        if ((mIndicatorAnimator != null) && (mIndicatorAnimator.isRunning())) {
          mIndicatorAnimator.cancel();
        }
        mIndicatorAnimator = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
        mIndicatorAnimator.setDuration(paramInt2);
        mIndicatorAnimator.setInterpolator(new DecelerateInterpolator());
        mIndicatorAnimator.addUpdateListener(new ScrollingTabContainerView.SlidingTabStrip.1(this, k, i, m, j));
        mIndicatorAnimator.addListener(new ScrollingTabContainerView.SlidingTabStrip.2(this, paramInt1));
        mIndicatorAnimator.start();
        return;
      }
      mSelectedPosition = paramInt1;
      mSelectionOffset = 0.0F;
    }
    
    public void cancelIndicatorAnim()
    {
      if ((mIndicatorAnimator != null) && (mIndicatorAnimator.isRunning())) {
        mIndicatorAnimator.cancel();
      }
    }
    
    public boolean isIndicatorAnimRunning()
    {
      return (mIndicatorAnimator != null) && (mIndicatorAnimator.isRunning());
    }
    
    protected void onDraw(Canvas paramCanvas)
    {
      if ((mIndicatorLeft >= 0) && (mIndicatorRight > mIndicatorLeft))
      {
        if (mIndicatorDrawable != null)
        {
          int i = mIndicatorDrawable.getIntrinsicHeight();
          mIndicatorDrawable.setBounds(mIndicatorLeft, getHeight() - i, mIndicatorRight, getHeight());
          mIndicatorDrawable.draw(paramCanvas);
        }
      }
      else {
        return;
      }
      paramCanvas.drawRect(mIndicatorLeft, getHeight() - mSelectedIndicatorHeight, mIndicatorRight, getHeight(), mSelectedIndicatorPaint);
    }
    
    protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      int i = getChildCount();
      if (i == 0) {}
      label96:
      label203:
      do
      {
        return;
        paramInt2 = 0;
        paramInt1 = 0;
        while (paramInt1 < i)
        {
          paramInt2 += getChildAt(paramInt1).getMeasuredWidth();
          paramInt1 += 1;
        }
        if (paramInt2 < getMeasuredWidth())
        {
          paramInt1 = 0;
          int j;
          View localView;
          int k;
          if (i == 2)
          {
            paramInt1 = getResources().getDimensionPixelSize(R.dimen.mz_action_bar_tab_bar_inset_2_tab);
            j = (getMeasuredWidth() - paramInt1 * 2) / i;
            paramInt3 = 0;
            paramInt2 = paramInt1;
            if (paramInt3 >= i) {
              continue;
            }
            localView = getChildAt(paramInt3);
            k = localView.getMeasuredWidth();
            if (k <= j) {
              break label203;
            }
            paramInt4 = paramInt2 - (k - j) / 2;
            paramInt2 = paramInt4;
            if (paramInt4 < 0) {
              paramInt2 = 0;
            }
          }
          for (;;)
          {
            localView.layout(paramInt2, localView.getTop(), k + paramInt2, localView.getBottom());
            paramInt3 += 1;
            paramInt2 = j * paramInt3 + paramInt1;
            break label96;
            if (i != 3) {
              break;
            }
            paramInt1 = getResources().getDimensionPixelSize(R.dimen.mz_action_bar_tab_bar_inset_3_tab);
            break;
            paramInt2 += (j - k) / 2;
          }
        }
      } while (ScrollingTabContainerView.isAnimationRunning(getAnimation()));
      updateIndicatorPosition();
    }
    
    protected void onMeasure(int paramInt1, int paramInt2)
    {
      super.onMeasure(paramInt1, paramInt2);
      if (View.MeasureSpec.getMode(paramInt1) != 1073741824) {}
      int k;
      int i;
      Object localObject;
      do
      {
        do
        {
          return;
        } while (mMode != 1);
        k = getChildCount();
        int m = View.MeasureSpec.makeMeasureSpec(0, 0);
        j = 0;
        i = 0;
        while (j < k)
        {
          localObject = getChildAt(j);
          ((View)localObject).measure(m, paramInt2);
          i = Math.max(i, ((View)localObject).getMeasuredWidth());
          j += 1;
        }
      } while (i <= 0);
      int j = ScrollingTabContainerView.this.dpToPx(16);
      if (i * k <= getMeasuredWidth() - j * 2)
      {
        j = 0;
        while (j < k)
        {
          localObject = (LinearLayoutCompat.LayoutParams)getChildAt(j).getLayoutParams();
          width = i;
          weight = 0.0F;
          j += 1;
        }
      }
      super.onMeasure(paramInt1, paramInt2);
    }
    
    public void resetPosition(int paramInt)
    {
      if (mSelectedPosition == paramInt)
      {
        mIndicatorLeft = -1;
        mIndicatorRight = -1;
        return;
      }
      setIndicatorPositionFromTabPosition(Math.max(0, paramInt - 1), 0.0F);
    }
    
    void setIndicatorDrawable(Drawable paramDrawable)
    {
      if (mIndicatorDrawable != paramDrawable)
      {
        mIndicatorDrawable = paramDrawable;
        invalidate();
      }
    }
    
    void setIndicatorPositionFromTabPosition(int paramInt, float paramFloat)
    {
      if (ScrollingTabContainerView.isAnimationRunning(getAnimation())) {
        return;
      }
      cancelIndicatorAnim();
      mSelectedPosition = paramInt;
      mSelectionOffset = paramFloat;
      updateIndicatorPosition();
    }
    
    void setSelectedIndicatorColor(int paramInt)
    {
      mSelectedIndicatorPaint.setColor(paramInt);
      ViewCompat.postInvalidateOnAnimation(this);
    }
    
    void setSelectedIndicatorHeight(int paramInt)
    {
      mSelectedIndicatorHeight = paramInt;
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  class TabAdapter
    extends BaseAdapter
  {
    private TabAdapter() {}
    
    public int getCount()
    {
      return mTabLayout.getChildCount();
    }
    
    public Object getItem(int paramInt)
    {
      return ((ScrollingTabContainerView.TabView)mTabLayout.getChildAt(paramInt)).getTab();
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null) {
        return ScrollingTabContainerView.this.createTabView((ActionBar.Tab)getItem(paramInt), true);
      }
      ((ScrollingTabContainerView.TabView)paramView).bindTab((ActionBar.Tab)getItem(paramInt));
      return paramView;
    }
  }
  
  class TabClickListener
    implements View.OnClickListener
  {
    private TabClickListener() {}
    
    public void onClick(View paramView)
    {
      ((ScrollingTabContainerView.TabView)paramView).getTab().select();
      int j = mTabLayout.getChildCount();
      int i = 0;
      if (i < j)
      {
        View localView = mTabLayout.getChildAt(i);
        if (localView == paramView) {}
        for (boolean bool = true;; bool = false)
        {
          localView.setSelected(bool);
          i += 1;
          break;
        }
      }
    }
  }
  
  class TabView
    extends LinearLayoutCompat
    implements View.OnLongClickListener
  {
    private final int[] BG_ATTRS;
    private View mCustomView;
    private ImageView mIconView;
    private ActionBar.Tab mTab;
    private TextView mTextView;
    
    public TabView(Context paramContext, ActionBar.Tab paramTab, boolean paramBoolean) {}
    
    public void bindTab(ActionBar.Tab paramTab)
    {
      mTab = paramTab;
      update();
    }
    
    public ActionBar.Tab getTab()
    {
      return mTab;
    }
    
    public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
    {
      super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
      paramAccessibilityEvent.setClassName(ActionBar.Tab.class.getName());
    }
    
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo paramAccessibilityNodeInfo)
    {
      super.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfo);
      if (Build.VERSION.SDK_INT >= 14) {
        paramAccessibilityNodeInfo.setClassName(ActionBar.Tab.class.getName());
      }
    }
    
    public boolean onLongClick(View paramView)
    {
      paramView = new int[2];
      getLocationOnScreen(paramView);
      Object localObject = getContext();
      int i = getWidth();
      int j = getHeight();
      int k = getResourcesgetDisplayMetricswidthPixels;
      localObject = Toast.makeText((Context)localObject, mTab.getContentDescription(), 0);
      ((Toast)localObject).setGravity(49, paramView[0] + i / 2 - k / 2, j);
      ((Toast)localObject).show();
      return true;
    }
    
    public void onMeasure(int paramInt1, int paramInt2)
    {
      super.onMeasure(paramInt1, paramInt2);
    }
    
    public void setSelected(boolean paramBoolean)
    {
      if (isSelected() != paramBoolean) {}
      for (int i = 1;; i = 0)
      {
        super.setSelected(paramBoolean);
        if ((i != 0) && (paramBoolean)) {
          sendAccessibilityEvent(4);
        }
        return;
      }
    }
    
    public void update()
    {
      ActionBar.Tab localTab = mTab;
      Object localObject1 = localTab.getCustomView();
      Object localObject2;
      if (localObject1 != null)
      {
        localObject2 = ((View)localObject1).getParent();
        if (localObject2 != this)
        {
          if (localObject2 != null) {
            ((ViewGroup)localObject2).removeView((View)localObject1);
          }
          addView((View)localObject1);
        }
        mCustomView = ((View)localObject1);
        if (mTextView != null) {
          mTextView.setVisibility(8);
        }
        if (mIconView != null)
        {
          mIconView.setVisibility(8);
          mIconView.setImageDrawable(null);
        }
      }
      for (;;)
      {
        setEnabled(localTab.isEnabled());
        return;
        if (mCustomView != null)
        {
          removeView(mCustomView);
          mCustomView = null;
        }
        localObject2 = localTab.getIcon();
        localObject1 = localTab.getText();
        label219:
        int i;
        label229:
        int j;
        if (localObject2 != null)
        {
          Object localObject3;
          if (mIconView == null)
          {
            localObject3 = new ImageView(getContext());
            LinearLayoutCompat.LayoutParams localLayoutParams = new LinearLayoutCompat.LayoutParams(-2, -2);
            gravity = 16;
            ((ImageView)localObject3).setLayoutParams(localLayoutParams);
            addView((View)localObject3, 0);
            mIconView = ((ImageView)localObject3);
          }
          mIconView.setImageDrawable((Drawable)localObject2);
          mIconView.setVisibility(0);
          if (TextUtils.isEmpty((CharSequence)localObject1)) {
            break label415;
          }
          i = 1;
          if (i == 0) {
            break label427;
          }
          if (mTextView == null)
          {
            localObject2 = getContext();
            if (!isAtToolbar) {
              break label420;
            }
            j = R.attr.mzToolBarTabTextStyle;
            label260:
            localObject2 = new AppCompatTextView((Context)localObject2, null, j);
            ((TextView)localObject2).setEllipsize(TextUtils.TruncateAt.END);
            localObject3 = new LinearLayoutCompat.LayoutParams(-2, -2);
            gravity = 16;
            ((TextView)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject3);
            addView((View)localObject2);
            mTextView = ((TextView)localObject2);
          }
          mTextView.setText((CharSequence)localObject1);
          mTextView.setVisibility(0);
          mTextView.setEnabled(localTab.isEnabled());
        }
        for (;;)
        {
          if (mIconView != null) {
            mIconView.setContentDescription(localTab.getContentDescription());
          }
          if ((i != 0) || (TextUtils.isEmpty(localTab.getContentDescription()))) {
            break label454;
          }
          setOnLongClickListener(this);
          break;
          if (mIconView == null) {
            break label219;
          }
          mIconView.setVisibility(8);
          mIconView.setImageDrawable(null);
          break label219;
          label415:
          i = 0;
          break label229;
          label420:
          j = R.attr.actionBarTabTextStyle;
          break label260;
          label427:
          if (mTextView != null)
          {
            mTextView.setVisibility(8);
            mTextView.setText(null);
          }
        }
        label454:
        setOnLongClickListener(null);
        setLongClickable(false);
      }
    }
  }
  
  public class VisibilityAnimListener
    implements ViewPropertyAnimatorListener
  {
    private boolean mCanceled = false;
    private int mFinalVisibility;
    
    protected VisibilityAnimListener() {}
    
    public void onAnimationCancel(View paramView)
    {
      mCanceled = true;
    }
    
    public void onAnimationEnd(View paramView)
    {
      if (mCanceled) {
        return;
      }
      mVisibilityAnim = null;
      setVisibility(mFinalVisibility);
    }
    
    public void onAnimationStart(View paramView)
    {
      setVisibility(0);
      mCanceled = false;
    }
    
    public VisibilityAnimListener withFinalVisibility(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, int paramInt)
    {
      mFinalVisibility = paramInt;
      mVisibilityAnim = paramViewPropertyAnimatorCompat;
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ScrollingTabContainerView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */