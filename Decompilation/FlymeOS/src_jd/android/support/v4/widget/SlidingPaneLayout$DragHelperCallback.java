package android.support.v4.widget;

import android.view.View;

class SlidingPaneLayout$DragHelperCallback
  extends ViewDragHelper.Callback
{
  private SlidingPaneLayout$DragHelperCallback(SlidingPaneLayout paramSlidingPaneLayout) {}
  
  public int clampViewPositionHorizontal(View paramView, int paramInt1, int paramInt2)
  {
    paramView = (SlidingPaneLayout.LayoutParams)SlidingPaneLayout.access$400(this$0).getLayoutParams();
    if (SlidingPaneLayout.access$700(this$0))
    {
      paramInt2 = this$0.getWidth();
      i = this$0.getPaddingRight();
      paramInt2 -= rightMargin + i + SlidingPaneLayout.access$400(this$0).getWidth();
      i = SlidingPaneLayout.access$800(this$0);
      return Math.max(Math.min(paramInt1, paramInt2), paramInt2 - i);
    }
    paramInt2 = this$0.getPaddingLeft();
    paramInt2 = leftMargin + paramInt2;
    int i = SlidingPaneLayout.access$800(this$0);
    return Math.min(Math.max(paramInt1, paramInt2), i + paramInt2);
  }
  
  public int clampViewPositionVertical(View paramView, int paramInt1, int paramInt2)
  {
    return paramView.getTop();
  }
  
  public int getViewHorizontalDragRange(View paramView)
  {
    return SlidingPaneLayout.access$800(this$0);
  }
  
  public void onEdgeDragStarted(int paramInt1, int paramInt2)
  {
    SlidingPaneLayout.access$200(this$0).captureChildView(SlidingPaneLayout.access$400(this$0), paramInt2);
  }
  
  public void onViewCaptured(View paramView, int paramInt)
  {
    this$0.setAllChildrenVisible();
  }
  
  public void onViewDragStateChanged(int paramInt)
  {
    if (SlidingPaneLayout.access$200(this$0).getViewDragState() == 0)
    {
      if (SlidingPaneLayout.access$300(this$0) == 0.0F)
      {
        this$0.updateObscuredViewsVisibility(SlidingPaneLayout.access$400(this$0));
        this$0.dispatchOnPanelClosed(SlidingPaneLayout.access$400(this$0));
        SlidingPaneLayout.access$502(this$0, false);
      }
    }
    else {
      return;
    }
    this$0.dispatchOnPanelOpened(SlidingPaneLayout.access$400(this$0));
    SlidingPaneLayout.access$502(this$0, true);
  }
  
  public void onViewPositionChanged(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    SlidingPaneLayout.access$600(this$0, paramInt1);
    this$0.invalidate();
  }
  
  public void onViewReleased(View paramView, float paramFloat1, float paramFloat2)
  {
    SlidingPaneLayout.LayoutParams localLayoutParams = (SlidingPaneLayout.LayoutParams)paramView.getLayoutParams();
    int i;
    int j;
    if (SlidingPaneLayout.access$700(this$0))
    {
      i = this$0.getPaddingRight();
      j = rightMargin + i;
      if (paramFloat1 >= 0.0F)
      {
        i = j;
        if (paramFloat1 == 0.0F)
        {
          i = j;
          if (SlidingPaneLayout.access$300(this$0) <= 0.5F) {}
        }
      }
      else
      {
        i = j + SlidingPaneLayout.access$800(this$0);
      }
      j = SlidingPaneLayout.access$400(this$0).getWidth();
      i = this$0.getWidth() - i - j;
    }
    for (;;)
    {
      SlidingPaneLayout.access$200(this$0).settleCapturedViewAt(i, paramView.getTop());
      this$0.invalidate();
      return;
      i = this$0.getPaddingLeft();
      j = leftMargin + i;
      if (paramFloat1 <= 0.0F)
      {
        i = j;
        if (paramFloat1 == 0.0F)
        {
          i = j;
          if (SlidingPaneLayout.access$300(this$0) <= 0.5F) {}
        }
      }
      else
      {
        i = j + SlidingPaneLayout.access$800(this$0);
      }
    }
  }
  
  public boolean tryCaptureView(View paramView, int paramInt)
  {
    if (SlidingPaneLayout.access$100(this$0)) {
      return false;
    }
    return getLayoutParamsslideable;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.SlidingPaneLayout.DragHelperCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */