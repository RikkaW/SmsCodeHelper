package android.support.v4.view;

import android.view.View;

class ViewCompatKitKat
{
  public static int getAccessibilityLiveRegion(View paramView)
  {
    return paramView.getAccessibilityLiveRegion();
  }
  
  public static boolean isAttachedToWindow(View paramView)
  {
    return paramView.isAttachedToWindow();
  }
  
  public static boolean isLaidOut(View paramView)
  {
    return paramView.isLaidOut();
  }
  
  public static void setAccessibilityLiveRegion(View paramView, int paramInt)
  {
    paramView.setAccessibilityLiveRegion(paramInt);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.ViewCompatKitKat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */