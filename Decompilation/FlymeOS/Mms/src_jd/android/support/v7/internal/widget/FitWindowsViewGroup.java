package android.support.v7.internal.widget;

import android.graphics.Rect;

public abstract interface FitWindowsViewGroup
{
  public abstract void setOnFitSystemWindowsListener(OnFitSystemWindowsListener paramOnFitSystemWindowsListener);
  
  public static abstract interface OnFitSystemWindowsListener
  {
    public abstract void onFitSystemWindows(Rect paramRect);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.FitWindowsViewGroup
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */