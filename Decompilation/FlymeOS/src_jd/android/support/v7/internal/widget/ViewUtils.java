package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ViewUtils
{
  private static final String TAG = "ViewUtils";
  private static Method sComputeFitSystemWindowsMethod;
  private static Method sMakeOptionalFitsSystemWindowsMethod;
  
  public static int combineMeasuredStates(int paramInt1, int paramInt2)
  {
    return paramInt1 | paramInt2;
  }
  
  public static boolean computeFitSystemWindows(View paramView, Rect paramRect1, Rect paramRect2)
  {
    if (sComputeFitSystemWindowsMethod != null) {
      try
      {
        boolean bool = ((Boolean)sComputeFitSystemWindowsMethod.invoke(paramView, new Object[] { paramRect1, paramRect2 })).booleanValue();
        return bool;
      }
      catch (Exception paramView)
      {
        Log.d("ViewUtils", "Could not invoke computeFitSystemWindows", paramView);
      }
    }
    return false;
  }
  
  private static void getComputeFitSystemWindowsMethod()
  {
    if (Build.VERSION.SDK_INT >= 18) {}
    try
    {
      sComputeFitSystemWindowsMethod = View.class.getDeclaredMethod("computeFitSystemWindows", new Class[] { Rect.class, Rect.class });
      if (!sComputeFitSystemWindowsMethod.isAccessible()) {
        sComputeFitSystemWindowsMethod.setAccessible(true);
      }
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      Log.d("ViewUtils", "Could not find method computeFitSystemWindows. Oh well.");
    }
  }
  
  public static void init()
  {
    if (sComputeFitSystemWindowsMethod == null) {
      new InitThread(null).start();
    }
  }
  
  public static boolean isLayoutRtl(View paramView)
  {
    return ViewCompat.getLayoutDirection(paramView) == 1;
  }
  
  public static void makeOptionalFitsSystemWindows(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      try
      {
        if (sMakeOptionalFitsSystemWindowsMethod == null)
        {
          new Thread(new ViewUtils.1(paramView, new Handler())).start();
          return;
        }
        sMakeOptionalFitsSystemWindowsMethod.invoke(paramView, new Object[0]);
        return;
      }
      catch (InvocationTargetException paramView)
      {
        Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", paramView);
        return;
      }
      catch (IllegalAccessException paramView)
      {
        Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", paramView);
      }
    }
  }
  
  public static Context themifyContext(Context paramContext, AttributeSet paramAttributeSet, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.View, 0, 0);
    if (paramBoolean1) {}
    for (int i = paramAttributeSet.getResourceId(R.styleable.View_android_theme, 0);; i = 0)
    {
      int j = i;
      if (paramBoolean2)
      {
        j = i;
        if (i == 0)
        {
          i = paramAttributeSet.getResourceId(R.styleable.View_theme, 0);
          j = i;
          if (i != 0)
          {
            Log.i("ViewUtils", "app:theme is now deprecated. Please move to using android:theme instead.");
            j = i;
          }
        }
      }
      paramAttributeSet.recycle();
      paramAttributeSet = paramContext;
      if (j != 0) {
        if ((paramContext instanceof ContextThemeWrapper))
        {
          paramAttributeSet = paramContext;
          if (((ContextThemeWrapper)paramContext).getThemeResId() == j) {}
        }
        else
        {
          paramAttributeSet = new ContextThemeWrapper(paramContext, j);
        }
      }
      return paramAttributeSet;
    }
  }
  
  static class InitThread
    extends Thread
  {
    public void run() {}
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ViewUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */