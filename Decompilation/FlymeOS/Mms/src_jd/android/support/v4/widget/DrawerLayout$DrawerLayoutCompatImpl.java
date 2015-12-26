package android.support.v4.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;

abstract interface DrawerLayout$DrawerLayoutCompatImpl
{
  public abstract void applyMarginInsets(ViewGroup.MarginLayoutParams paramMarginLayoutParams, Object paramObject, int paramInt);
  
  public abstract void configureApplyInsets(View paramView);
  
  public abstract void dispatchChildInsets(View paramView, Object paramObject, int paramInt);
  
  public abstract Drawable getDefaultStatusBarBackground(Context paramContext);
  
  public abstract int getTopInset(Object paramObject);
}

/* Location:
 * Qualified Name:     android.support.v4.widget.DrawerLayout.DrawerLayoutCompatImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */