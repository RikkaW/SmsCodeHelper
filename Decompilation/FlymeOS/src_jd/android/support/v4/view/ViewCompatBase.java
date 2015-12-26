package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.view.View;
import java.lang.reflect.Field;

class ViewCompatBase
{
  private static final String TAG = "ViewCompatBase";
  private static Field sMinHeightField;
  private static boolean sMinHeightFieldFetched;
  private static Field sMinWidthField;
  private static boolean sMinWidthFieldFetched;
  
  static ColorStateList getBackgroundTintList(View paramView)
  {
    if ((paramView instanceof TintableBackgroundView)) {
      return ((TintableBackgroundView)paramView).getSupportBackgroundTintList();
    }
    return null;
  }
  
  static PorterDuff.Mode getBackgroundTintMode(View paramView)
  {
    if ((paramView instanceof TintableBackgroundView)) {
      return ((TintableBackgroundView)paramView).getSupportBackgroundTintMode();
    }
    return null;
  }
  
  static int getMinimumHeight(View paramView)
  {
    if (!sMinHeightFieldFetched) {}
    try
    {
      sMinHeightField = View.class.getDeclaredField("mMinHeight");
      sMinHeightField.setAccessible(true);
      sMinHeightFieldFetched = true;
      if (sMinHeightField != null) {
        try
        {
          int i = ((Integer)sMinHeightField.get(paramView)).intValue();
          return i;
        }
        catch (Exception paramView) {}
      }
      return 0;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      for (;;) {}
    }
  }
  
  static int getMinimumWidth(View paramView)
  {
    if (!sMinWidthFieldFetched) {}
    try
    {
      sMinWidthField = View.class.getDeclaredField("mMinWidth");
      sMinWidthField.setAccessible(true);
      sMinWidthFieldFetched = true;
      if (sMinWidthField != null) {
        try
        {
          int i = ((Integer)sMinWidthField.get(paramView)).intValue();
          return i;
        }
        catch (Exception paramView) {}
      }
      return 0;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      for (;;) {}
    }
  }
  
  static boolean isAttachedToWindow(View paramView)
  {
    return paramView.getWindowToken() != null;
  }
  
  static boolean isLaidOut(View paramView)
  {
    return (paramView.getWidth() > 0) && (paramView.getHeight() > 0);
  }
  
  static void setBackgroundTintList(View paramView, ColorStateList paramColorStateList)
  {
    if ((paramView instanceof TintableBackgroundView)) {
      ((TintableBackgroundView)paramView).setSupportBackgroundTintList(paramColorStateList);
    }
  }
  
  static void setBackgroundTintMode(View paramView, PorterDuff.Mode paramMode)
  {
    if ((paramView instanceof TintableBackgroundView)) {
      ((TintableBackgroundView)paramView).setSupportBackgroundTintMode(paramMode);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.ViewCompatBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */