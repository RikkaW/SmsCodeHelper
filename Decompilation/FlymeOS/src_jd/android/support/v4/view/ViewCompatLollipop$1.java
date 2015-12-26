package android.support.v4.view;

import android.view.View;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.WindowInsets;

final class ViewCompatLollipop$1
  implements View.OnApplyWindowInsetsListener
{
  ViewCompatLollipop$1(OnApplyWindowInsetsListener paramOnApplyWindowInsetsListener) {}
  
  public WindowInsets onApplyWindowInsets(View paramView, WindowInsets paramWindowInsets)
  {
    paramWindowInsets = new WindowInsetsCompatApi21(paramWindowInsets);
    return ((WindowInsetsCompatApi21)val$listener.onApplyWindowInsets(paramView, paramWindowInsets)).unwrap();
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.ViewCompatLollipop.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */