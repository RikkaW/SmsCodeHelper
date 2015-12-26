package android.support.v4.view;

import android.view.ViewGroup;

class ViewGroupCompatLollipop
{
  public static int getNestedScrollAxes(ViewGroup paramViewGroup)
  {
    return paramViewGroup.getNestedScrollAxes();
  }
  
  public static boolean isTransitionGroup(ViewGroup paramViewGroup)
  {
    return paramViewGroup.isTransitionGroup();
  }
  
  public static void setTransitionGroup(ViewGroup paramViewGroup, boolean paramBoolean)
  {
    paramViewGroup.setTransitionGroup(paramBoolean);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.ViewGroupCompatLollipop
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */