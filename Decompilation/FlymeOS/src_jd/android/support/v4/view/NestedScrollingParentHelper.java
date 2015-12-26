package android.support.v4.view;

import android.view.View;
import android.view.ViewGroup;

public class NestedScrollingParentHelper
{
  private int mNestedScrollAxes;
  private final ViewGroup mViewGroup;
  
  public NestedScrollingParentHelper(ViewGroup paramViewGroup)
  {
    mViewGroup = paramViewGroup;
  }
  
  public int getNestedScrollAxes()
  {
    return mNestedScrollAxes;
  }
  
  public void onNestedScrollAccepted(View paramView1, View paramView2, int paramInt)
  {
    mNestedScrollAxes = paramInt;
  }
  
  public void onStopNestedScroll(View paramView)
  {
    mNestedScrollAxes = 0;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.NestedScrollingParentHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */