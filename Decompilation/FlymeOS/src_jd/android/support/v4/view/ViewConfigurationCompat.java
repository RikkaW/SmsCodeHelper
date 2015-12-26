package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.ViewConfiguration;

public class ViewConfigurationCompat
{
  static final ViewConfigurationVersionImpl IMPL = new BaseViewConfigurationVersionImpl();
  
  static
  {
    if (Build.VERSION.SDK_INT >= 14)
    {
      IMPL = new IcsViewConfigurationVersionImpl();
      return;
    }
    if (Build.VERSION.SDK_INT >= 11)
    {
      IMPL = new HoneycombViewConfigurationVersionImpl();
      return;
    }
    if (Build.VERSION.SDK_INT >= 8)
    {
      IMPL = new FroyoViewConfigurationVersionImpl();
      return;
    }
  }
  
  public static int getScaledPagingTouchSlop(ViewConfiguration paramViewConfiguration)
  {
    return IMPL.getScaledPagingTouchSlop(paramViewConfiguration);
  }
  
  public static boolean hasPermanentMenuKey(ViewConfiguration paramViewConfiguration)
  {
    return IMPL.hasPermanentMenuKey(paramViewConfiguration);
  }
  
  static class BaseViewConfigurationVersionImpl
    implements ViewConfigurationCompat.ViewConfigurationVersionImpl
  {
    public int getScaledPagingTouchSlop(ViewConfiguration paramViewConfiguration)
    {
      return paramViewConfiguration.getScaledTouchSlop();
    }
    
    public boolean hasPermanentMenuKey(ViewConfiguration paramViewConfiguration)
    {
      return true;
    }
  }
  
  static class FroyoViewConfigurationVersionImpl
    extends ViewConfigurationCompat.BaseViewConfigurationVersionImpl
  {
    public int getScaledPagingTouchSlop(ViewConfiguration paramViewConfiguration)
    {
      return ViewConfigurationCompatFroyo.getScaledPagingTouchSlop(paramViewConfiguration);
    }
  }
  
  static class HoneycombViewConfigurationVersionImpl
    extends ViewConfigurationCompat.FroyoViewConfigurationVersionImpl
  {
    public boolean hasPermanentMenuKey(ViewConfiguration paramViewConfiguration)
    {
      return false;
    }
  }
  
  static class IcsViewConfigurationVersionImpl
    extends ViewConfigurationCompat.HoneycombViewConfigurationVersionImpl
  {
    public boolean hasPermanentMenuKey(ViewConfiguration paramViewConfiguration)
    {
      return ViewConfigurationCompatICS.hasPermanentMenuKey(paramViewConfiguration);
    }
  }
  
  static abstract interface ViewConfigurationVersionImpl
  {
    public abstract int getScaledPagingTouchSlop(ViewConfiguration paramViewConfiguration);
    
    public abstract boolean hasPermanentMenuKey(ViewConfiguration paramViewConfiguration);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.ViewConfigurationCompat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */