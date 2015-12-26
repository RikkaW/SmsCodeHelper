package com.meizu.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView.SelectionBoundsAdjuster;
import android.widget.FrameLayout;
import com.meizu.common.R.drawable;
import com.meizu.widget.ListDragShadowItem;

public class PartitionItemLayout
  extends FrameLayout
  implements AbsListView.SelectionBoundsAdjuster, ListDragShadowItem
{
  protected Drawable mContentBackground;
  private Rect mItemShadeRect;
  
  public PartitionItemLayout(Context paramContext)
  {
    super(paramContext);
  }
  
  public PartitionItemLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public PartitionItemLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = getResources().getDrawable(R.drawable.mz_list_new_item_bg_light_activated);
    if (paramContext != null) {
      setContentBackground(paramContext);
    }
    if (mItemShadeRect == null) {
      mItemShadeRect = new Rect();
    }
  }
  
  public void adjustListItemSelectionBounds(Rect paramRect)
  {
    left += mItemShadeRect.left;
    top += mItemShadeRect.top;
    right -= mItemShadeRect.right;
    bottom -= mItemShadeRect.bottom;
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    if ((mContentBackground != null) && (mContentBackground.isStateful())) {
      mContentBackground.setState(getDrawableState());
    }
  }
  
  public Drawable getContentBackground()
  {
    return mContentBackground;
  }
  
  public View getDragView()
  {
    return getChildAt(0);
  }
  
  public void jumpDrawablesToCurrentState()
  {
    super.jumpDrawablesToCurrentState();
    if (mContentBackground != null) {
      mContentBackground.jumpToCurrentState();
    }
  }
  
  public boolean needBackground()
  {
    return true;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    mContentBackground.setBounds(mItemShadeRect.left, mItemShadeRect.top, getMeasuredWidth() - mItemShadeRect.right, getMeasuredHeight() - mItemShadeRect.bottom);
    mContentBackground.draw(paramCanvas);
    super.onDraw(paramCanvas);
  }
  
  public void setBackground(Drawable paramDrawable)
  {
    setBackgroundDrawable(paramDrawable);
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    super.setBackgroundDrawable(paramDrawable);
    if (mItemShadeRect == null) {
      mItemShadeRect = new Rect();
    }
    if (paramDrawable != null)
    {
      paramDrawable.getPadding(mItemShadeRect);
      return;
    }
    mItemShadeRect.setEmpty();
  }
  
  public void setContentBackground(Drawable paramDrawable)
  {
    if (mContentBackground != paramDrawable)
    {
      if (mContentBackground != null)
      {
        mContentBackground.setCallback(null);
        unscheduleDrawable(mContentBackground);
      }
      mContentBackground = paramDrawable;
      if (paramDrawable == null) {
        break label75;
      }
      setWillNotDraw(false);
      paramDrawable.setCallback(this);
      if (paramDrawable.isStateful()) {
        paramDrawable.setState(getDrawableState());
      }
    }
    for (;;)
    {
      requestLayout();
      invalidate();
      return;
      label75:
      setWillNotDraw(true);
    }
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable)
  {
    return (super.verifyDrawable(paramDrawable)) || (paramDrawable == mContentBackground);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.PartitionItemLayout
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */