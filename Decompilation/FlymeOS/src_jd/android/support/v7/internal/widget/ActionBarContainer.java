package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.internal.VersionUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnDrawListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import aqy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ActionBarContainer
  extends FrameLayout
{
  private static Field sDirtyField;
  private static Method sGetViewRootImplMethod;
  private View mActionBarView;
  Drawable mBackground;
  private ViewTreeObserver.OnDrawListener mBlurOnDrawListener = getBlurOnDrawListener();
  private ViewTreeObserver.OnGlobalLayoutListener mBlurOnGlobalLayoutListener = new ActionBarContainer.2(this);
  private View mContextView;
  private int mHeight;
  boolean mIsSplit;
  boolean mIsStacked;
  private boolean mIsTransitioning;
  Drawable mSplitBackground;
  Drawable mStackedBackground;
  private View mTabContainer;
  private ViewTreeObserver mViewTreeObserver;
  private Rect mVisibleRect = new Rect();
  
  public ActionBarContainer(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ActionBarContainer(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    Object localObject;
    boolean bool;
    if (VersionUtils.isAtLeastL())
    {
      localObject = new ActionBarBackgroundDrawableV21(this);
      setBackgroundDrawable((Drawable)localObject);
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ActionBar);
      mBackground = paramContext.getDrawable(R.styleable.ActionBar_background);
      mStackedBackground = paramContext.getDrawable(R.styleable.ActionBar_backgroundStacked);
      mHeight = paramContext.getDimensionPixelSize(R.styleable.ActionBar_height, -1);
      if (getId() == R.id.split_action_bar)
      {
        mIsSplit = true;
        mSplitBackground = paramContext.getDrawable(R.styleable.ActionBar_backgroundSplit);
      }
      paramContext.recycle();
      if (!mIsSplit) {
        break label172;
      }
      if (mSplitBackground != null) {
        break label167;
      }
      bool = true;
    }
    for (;;)
    {
      setWillNotDraw(bool);
      return;
      localObject = new ActionBarBackgroundDrawable(this);
      break;
      label167:
      bool = false;
      continue;
      label172:
      if ((mBackground == null) && (mStackedBackground == null)) {
        bool = true;
      } else {
        bool = false;
      }
    }
  }
  
  private ViewTreeObserver.OnDrawListener getBlurOnDrawListener()
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return new ActionBarContainer.1(this);
    }
    return null;
  }
  
  private static Field getField()
  {
    try
    {
      Field localField = Class.forName("android.view.ViewRootImpl").getDeclaredField("mDirty");
      localField.setAccessible(true);
      return localField;
    }
    catch (Exception localException) {}
    return null;
  }
  
  private int getMeasuredHeightWithMargins(View paramView)
  {
    FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)paramView.getLayoutParams();
    int i = paramView.getMeasuredHeight();
    int j = topMargin;
    return bottomMargin + (i + j);
  }
  
  private static Method getMethod()
  {
    try
    {
      Method localMethod = View.class.getDeclaredMethod("getViewRootImpl", new Class[0]);
      return localMethod;
    }
    catch (NoSuchMethodException localNoSuchMethodException) {}
    return null;
  }
  
  private boolean isBlurDrawable(Drawable paramDrawable)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    int i;
    if (paramDrawable != null)
    {
      if (!(paramDrawable instanceof LayerDrawable)) {
        break label59;
      }
      i = 0;
      bool1 = bool2;
      if (i < ((LayerDrawable)paramDrawable).getNumberOfLayers())
      {
        if (!isBlurDrawable(((LayerDrawable)paramDrawable).getDrawable(i))) {
          break label52;
        }
        bool1 = true;
      }
    }
    label52:
    label59:
    do
    {
      return bool1;
      i += 1;
      break;
      bool1 = bool2;
    } while (!paramDrawable.getClass().getName().equals("com.meizu.common.drawble.BlurDrawable"));
    return true;
  }
  
  private boolean isCollapsed(View paramView)
  {
    return (paramView == null) || (paramView.getVisibility() == 8) || (paramView.getMeasuredHeight() == 0);
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    if ((mBackground != null) && (mBackground.isStateful())) {
      mBackground.setState(getDrawableState());
    }
    if ((mStackedBackground != null) && (mStackedBackground.isStateful())) {
      mStackedBackground.setState(getDrawableState());
    }
    if ((mSplitBackground != null) && (mSplitBackground.isStateful())) {
      mSplitBackground.setState(getDrawableState());
    }
  }
  
  public View getTabContainer()
  {
    return mTabContainer;
  }
  
  public void jumpDrawablesToCurrentState()
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      super.jumpDrawablesToCurrentState();
      if (mBackground != null) {
        mBackground.jumpToCurrentState();
      }
      if (mStackedBackground != null) {
        mStackedBackground.jumpToCurrentState();
      }
      if (mSplitBackground != null) {
        mSplitBackground.jumpToCurrentState();
      }
    }
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    boolean bool;
    if (mIsSplit) {
      bool = isBlurDrawable(mSplitBackground);
    }
    for (;;)
    {
      registerViewTreeObserver(bool);
      return;
      if ((isBlurDrawable(mBackground)) || (isBlurDrawable(mStackedBackground))) {
        bool = true;
      } else {
        bool = false;
      }
    }
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    registerViewTreeObserver(false);
  }
  
  public void onFinishInflate()
  {
    super.onFinishInflate();
    mActionBarView = findViewById(R.id.action_bar);
    mContextView = findViewById(R.id.action_context_bar);
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    return (mIsTransitioning) || (super.onInterceptTouchEvent(paramMotionEvent));
  }
  
  public void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = 1;
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    View localView = mTabContainer;
    if ((localView != null) && (localView.getVisibility() != 8))
    {
      paramBoolean = true;
      if ((localView != null) && (localView.getVisibility() != 8))
      {
        int j = getMeasuredHeight();
        FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
        int k = localView.getMeasuredHeight();
        localView.layout(leftMargin + paramInt1, j - k - bottomMargin, paramInt3 - rightMargin, j - bottomMargin);
      }
      if (!mIsSplit) {
        break label162;
      }
      if (mSplitBackground == null) {
        break label282;
      }
      mSplitBackground.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
      paramInt1 = i;
    }
    for (;;)
    {
      if (paramInt1 != 0) {
        invalidate();
      }
      return;
      paramBoolean = false;
      break;
      label162:
      if (mBackground != null) {
        if ((mActionBarView.getVisibility() == 0) || ((mContextView != null) && (mContextView.getVisibility() == 0))) {
          mBackground.setBounds(paramInt1, paramInt2, paramInt3, paramInt4);
        }
      }
      label209:
      for (paramInt1 = 1;; paramInt1 = 0)
      {
        mIsStacked = paramBoolean;
        if ((paramBoolean) && (mStackedBackground != null))
        {
          mStackedBackground.setBounds(localView.getLeft(), localView.getTop(), localView.getRight(), localView.getBottom());
          paramInt1 = i;
          break;
          mBackground.setBounds(0, 0, 0, 0);
          break label209;
        }
        break;
      }
      label282:
      paramInt1 = 0;
    }
  }
  
  public void onMeasure(int paramInt1, int paramInt2)
  {
    int i = paramInt2;
    if (mActionBarView == null)
    {
      i = paramInt2;
      if (View.MeasureSpec.getMode(paramInt2) == Integer.MIN_VALUE)
      {
        i = paramInt2;
        if (mHeight >= 0) {
          i = View.MeasureSpec.makeMeasureSpec(Math.min(mHeight, View.MeasureSpec.getSize(paramInt2)), Integer.MIN_VALUE);
        }
      }
    }
    super.onMeasure(paramInt1, i);
    if (mActionBarView == null) {}
    do
    {
      return;
      paramInt2 = View.MeasureSpec.getMode(i);
    } while ((mTabContainer == null) || (mTabContainer.getVisibility() == 8) || (paramInt2 == 1073741824));
    if (!isCollapsed(mActionBarView))
    {
      paramInt1 = getMeasuredHeightWithMargins(mActionBarView);
      if (paramInt2 != Integer.MIN_VALUE) {
        break label186;
      }
    }
    label186:
    for (paramInt2 = View.MeasureSpec.getSize(i);; paramInt2 = Integer.MAX_VALUE)
    {
      setMeasuredDimension(getMeasuredWidth(), Math.min(paramInt1 + getMeasuredHeightWithMargins(mTabContainer) + getPaddingTop() + getPaddingBottom(), paramInt2));
      return;
      if (!isCollapsed(mContextView))
      {
        paramInt1 = getMeasuredHeightWithMargins(mContextView);
        break;
      }
      paramInt1 = 0;
      break;
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    super.onTouchEvent(paramMotionEvent);
    return true;
  }
  
  public void registerViewTreeObserver(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT < 16) {}
    do
    {
      return;
      if (paramBoolean)
      {
        if ((sDirtyField == null) || (sGetViewRootImplMethod == null)) {
          new Thread(new GetReflectRunnable(null)).start();
        }
        if ((mViewTreeObserver != null) && (mViewTreeObserver.isAlive()))
        {
          mViewTreeObserver.removeOnDrawListener(mBlurOnDrawListener);
          mViewTreeObserver.removeOnGlobalLayoutListener(mBlurOnGlobalLayoutListener);
        }
        mViewTreeObserver = getViewTreeObserver();
        mViewTreeObserver.addOnDrawListener(mBlurOnDrawListener);
        mViewTreeObserver.addOnGlobalLayoutListener(mBlurOnGlobalLayoutListener);
        return;
      }
    } while (mViewTreeObserver == null);
    if (mViewTreeObserver.isAlive())
    {
      mViewTreeObserver.removeOnDrawListener(mBlurOnDrawListener);
      mViewTreeObserver.removeOnGlobalLayoutListener(mBlurOnGlobalLayoutListener);
    }
    mViewTreeObserver = null;
  }
  
  public void setPrimaryBackground(Drawable paramDrawable)
  {
    boolean bool = true;
    if (mBackground != null)
    {
      mBackground.setCallback(null);
      unscheduleDrawable(mBackground);
    }
    mBackground = paramDrawable;
    if (paramDrawable != null)
    {
      paramDrawable.setCallback(this);
      if (mActionBarView != null) {
        mBackground.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
      }
    }
    if (mIsSplit) {
      if (mSplitBackground != null) {}
    }
    for (;;)
    {
      setWillNotDraw(bool);
      registerViewTreeObserver(isBlurDrawable(mBackground));
      invalidate();
      return;
      bool = false;
      continue;
      if ((mBackground != null) || (mStackedBackground != null)) {
        bool = false;
      }
    }
  }
  
  public void setSplitBackground(Drawable paramDrawable)
  {
    boolean bool = true;
    if (mSplitBackground != null)
    {
      mSplitBackground.setCallback(null);
      unscheduleDrawable(mSplitBackground);
    }
    mSplitBackground = paramDrawable;
    if (paramDrawable != null)
    {
      paramDrawable.setCallback(this);
      if ((mIsSplit) && (mSplitBackground != null)) {
        mSplitBackground.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
      }
    }
    if (mIsSplit) {
      if (mSplitBackground != null) {}
    }
    for (;;)
    {
      setWillNotDraw(bool);
      registerViewTreeObserver(isBlurDrawable(mSplitBackground));
      invalidate();
      return;
      bool = false;
      continue;
      if ((mBackground != null) || (mStackedBackground != null)) {
        bool = false;
      }
    }
  }
  
  public void setStackedBackground(Drawable paramDrawable)
  {
    boolean bool = true;
    if (mStackedBackground != null)
    {
      mStackedBackground.setCallback(null);
      unscheduleDrawable(mStackedBackground);
    }
    mStackedBackground = paramDrawable;
    if (paramDrawable != null)
    {
      paramDrawable.setCallback(this);
      if ((mIsStacked) && (mStackedBackground != null)) {
        mStackedBackground.setBounds(mTabContainer.getLeft(), mTabContainer.getTop(), mTabContainer.getRight(), mTabContainer.getBottom());
      }
    }
    if (mIsSplit) {
      if (mSplitBackground != null) {}
    }
    for (;;)
    {
      setWillNotDraw(bool);
      registerViewTreeObserver(isBlurDrawable(mStackedBackground));
      invalidate();
      return;
      bool = false;
      continue;
      if ((mBackground != null) || (mStackedBackground != null)) {
        bool = false;
      }
    }
  }
  
  public void setTabContainer(ScrollingTabContainerView paramScrollingTabContainerView)
  {
    if (mTabContainer != null) {
      removeView(mTabContainer);
    }
    if ((mTabContainer == null) && (paramScrollingTabContainerView != null)) {
      mTabContainer = new aqy(getContext());
    }
    if (paramScrollingTabContainerView != null)
    {
      ((aqy)mTabContainer).setTabView(paramScrollingTabContainerView);
      addView(mTabContainer);
      localLayoutParams = mTabContainer.getLayoutParams();
      width = -1;
      height = -2;
      paramScrollingTabContainerView.setAllowCollapse(false);
    }
    while (mTabContainer == null)
    {
      ViewGroup.LayoutParams localLayoutParams;
      return;
    }
    ((aqy)mTabContainer).setTabView(null);
    mTabContainer = null;
  }
  
  public void setTransitioning(boolean paramBoolean)
  {
    mIsTransitioning = paramBoolean;
    if (paramBoolean) {}
    for (int i = 393216;; i = 262144)
    {
      setDescendantFocusability(i);
      return;
    }
  }
  
  public void setVisibility(int paramInt)
  {
    super.setVisibility(paramInt);
    if (paramInt == 0) {}
    for (boolean bool = true;; bool = false)
    {
      if (mBackground != null) {
        mBackground.setVisible(bool, false);
      }
      if (mStackedBackground != null) {
        mStackedBackground.setVisible(bool, false);
      }
      if (mSplitBackground != null) {
        mSplitBackground.setVisible(bool, false);
      }
      return;
    }
  }
  
  public android.support.v7.view.ActionMode startActionModeForChild(View paramView, android.support.v7.view.ActionMode.Callback paramCallback)
  {
    return null;
  }
  
  public android.view.ActionMode startActionModeForChild(View paramView, android.view.ActionMode.Callback paramCallback)
  {
    return null;
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable)
  {
    return ((paramDrawable == mBackground) && (!mIsSplit)) || ((paramDrawable == mStackedBackground) && (mIsStacked)) || ((paramDrawable == mSplitBackground) && (mIsSplit)) || (super.verifyDrawable(paramDrawable));
  }
  
  static class GetReflectRunnable
    implements Runnable
  {
    public void run()
    {
      ActionBarContainer.access$002(ActionBarContainer.access$400());
      ActionBarContainer.access$102(ActionBarContainer.access$500());
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ActionBarContainer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */