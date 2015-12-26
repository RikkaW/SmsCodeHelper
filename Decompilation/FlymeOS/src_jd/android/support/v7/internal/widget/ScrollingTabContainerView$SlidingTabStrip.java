package android.support.v7.internal.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.color;
import android.support.v7.appcompat.R.dimen;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutCompat.LayoutParams;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.DecelerateInterpolator;

class ScrollingTabContainerView$SlidingTabStrip
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
  
  public ScrollingTabContainerView$SlidingTabStrip(ScrollingTabContainerView paramScrollingTabContainerView, Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramScrollingTabContainerView, paramContext, paramAttributeSet, 0);
  }
  
  public ScrollingTabContainerView$SlidingTabStrip(ScrollingTabContainerView paramScrollingTabContainerView, Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setWillNotDraw(false);
    mSelectedIndicatorPaint = new Paint();
    paramScrollingTabContainerView = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.MzActionBarTabBar, paramInt, 0);
    mSelectedIndicatorColor = paramScrollingTabContainerView.getColor(R.styleable.MzActionBarTabBar_mzTabBarIndicatorColor, getResources().getColor(R.color.mz_action_bar_tab_indicator_default_color));
    mSelectedIndicatorPaint.setColor(mSelectedIndicatorColor);
    mSelectedIndicatorHeight = paramScrollingTabContainerView.getDimensionPixelSize(R.styleable.MzActionBarTabBar_mzTabBarIndicatorHeight, getResources().getDimensionPixelSize(R.dimen.mz_action_bar_tab_indicator_height));
    mIndicatorDrawable = paramScrollingTabContainerView.getDrawable(R.styleable.MzActionBarTabBar_mzTabBarIndicatorDrawable);
    paramScrollingTabContainerView.recycle();
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
    } while (ScrollingTabContainerView.access$500(getAnimation()));
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
      } while (ScrollingTabContainerView.access$600(this$0) != 1);
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
    int j = ScrollingTabContainerView.access$700(this$0, 16);
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
    if (ScrollingTabContainerView.access$500(getAnimation())) {
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

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ScrollingTabContainerView.SlidingTabStrip
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */