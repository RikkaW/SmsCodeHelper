package android.support.v4.view;

import android.os.Build.VERSION;

public class ScaleGestureDetectorCompat
{
  static final ScaleGestureDetectorImpl IMPL = new BaseScaleGestureDetectorImpl(null);
  
  static
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      IMPL = new ScaleGestureDetectorCompatKitKatImpl(null);
      return;
    }
  }
  
  public static boolean isQuickScaleEnabled(Object paramObject)
  {
    return IMPL.isQuickScaleEnabled(paramObject);
  }
  
  public static void setQuickScaleEnabled(Object paramObject, boolean paramBoolean)
  {
    IMPL.setQuickScaleEnabled(paramObject, paramBoolean);
  }
  
  static class BaseScaleGestureDetectorImpl
    implements ScaleGestureDetectorCompat.ScaleGestureDetectorImpl
  {
    public boolean isQuickScaleEnabled(Object paramObject)
    {
      return false;
    }
    
    public void setQuickScaleEnabled(Object paramObject, boolean paramBoolean) {}
  }
  
  static class ScaleGestureDetectorCompatKitKatImpl
    implements ScaleGestureDetectorCompat.ScaleGestureDetectorImpl
  {
    public boolean isQuickScaleEnabled(Object paramObject)
    {
      return ScaleGestureDetectorCompatKitKat.isQuickScaleEnabled(paramObject);
    }
    
    public void setQuickScaleEnabled(Object paramObject, boolean paramBoolean)
    {
      ScaleGestureDetectorCompatKitKat.setQuickScaleEnabled(paramObject, paramBoolean);
    }
  }
  
  static abstract interface ScaleGestureDetectorImpl
  {
    public abstract boolean isQuickScaleEnabled(Object paramObject);
    
    public abstract void setQuickScaleEnabled(Object paramObject, boolean paramBoolean);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.ScaleGestureDetectorCompat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */