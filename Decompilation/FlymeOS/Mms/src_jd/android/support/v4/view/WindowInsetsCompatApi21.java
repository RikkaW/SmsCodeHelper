package android.support.v4.view;

import android.graphics.Rect;
import android.view.WindowInsets;

class WindowInsetsCompatApi21
  extends WindowInsetsCompat
{
  private final WindowInsets mSource;
  
  WindowInsetsCompatApi21(WindowInsets paramWindowInsets)
  {
    mSource = paramWindowInsets;
  }
  
  public WindowInsetsCompat consumeStableInsets()
  {
    return new WindowInsetsCompatApi21(mSource.consumeStableInsets());
  }
  
  public WindowInsetsCompat consumeSystemWindowInsets()
  {
    return new WindowInsetsCompatApi21(mSource.consumeSystemWindowInsets());
  }
  
  public int getStableInsetBottom()
  {
    return mSource.getStableInsetBottom();
  }
  
  public int getStableInsetLeft()
  {
    return mSource.getStableInsetLeft();
  }
  
  public int getStableInsetRight()
  {
    return mSource.getStableInsetRight();
  }
  
  public int getStableInsetTop()
  {
    return mSource.getStableInsetTop();
  }
  
  public int getSystemWindowInsetBottom()
  {
    return mSource.getSystemWindowInsetBottom();
  }
  
  public int getSystemWindowInsetLeft()
  {
    return mSource.getSystemWindowInsetLeft();
  }
  
  public int getSystemWindowInsetRight()
  {
    return mSource.getSystemWindowInsetRight();
  }
  
  public int getSystemWindowInsetTop()
  {
    return mSource.getSystemWindowInsetTop();
  }
  
  public boolean hasInsets()
  {
    return mSource.hasInsets();
  }
  
  public boolean hasStableInsets()
  {
    return mSource.hasStableInsets();
  }
  
  public boolean hasSystemWindowInsets()
  {
    return mSource.hasSystemWindowInsets();
  }
  
  public boolean isConsumed()
  {
    return mSource.isConsumed();
  }
  
  public boolean isRound()
  {
    return mSource.isRound();
  }
  
  public WindowInsetsCompat replaceSystemWindowInsets(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return new WindowInsetsCompatApi21(mSource.replaceSystemWindowInsets(paramInt1, paramInt2, paramInt3, paramInt4));
  }
  
  public WindowInsetsCompat replaceSystemWindowInsets(Rect paramRect)
  {
    return new WindowInsetsCompatApi21(mSource.replaceSystemWindowInsets(paramRect));
  }
  
  WindowInsets unwrap()
  {
    return mSource;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.WindowInsetsCompatApi21
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */