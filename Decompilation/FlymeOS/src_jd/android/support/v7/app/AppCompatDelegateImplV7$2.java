package android.support.v7.app;

import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.view.View;

class AppCompatDelegateImplV7$2
  implements OnApplyWindowInsetsListener
{
  AppCompatDelegateImplV7$2(AppCompatDelegateImplV7 paramAppCompatDelegateImplV7) {}
  
  public WindowInsetsCompat onApplyWindowInsets(View paramView, WindowInsetsCompat paramWindowInsetsCompat)
  {
    int i = paramWindowInsetsCompat.getSystemWindowInsetTop();
    int j = AppCompatDelegateImplV7.access$300(this$0, i);
    WindowInsetsCompat localWindowInsetsCompat = paramWindowInsetsCompat;
    if (i != j) {
      localWindowInsetsCompat = paramWindowInsetsCompat.replaceSystemWindowInsets(paramWindowInsetsCompat.getSystemWindowInsetLeft(), j, paramWindowInsetsCompat.getSystemWindowInsetRight(), paramWindowInsetsCompat.getSystemWindowInsetBottom());
    }
    return ViewCompat.onApplyWindowInsets(paramView, localWindowInsetsCompat);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AppCompatDelegateImplV7.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */