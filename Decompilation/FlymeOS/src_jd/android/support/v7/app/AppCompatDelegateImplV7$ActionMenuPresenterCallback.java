package android.support.v7.app;

import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.view.Window.Callback;

final class AppCompatDelegateImplV7$ActionMenuPresenterCallback
  implements MenuPresenter.Callback
{
  private AppCompatDelegateImplV7$ActionMenuPresenterCallback(AppCompatDelegateImplV7 paramAppCompatDelegateImplV7) {}
  
  public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
  {
    AppCompatDelegateImplV7.access$900(this$0, paramMenuBuilder);
  }
  
  public boolean onOpenSubMenu(MenuBuilder paramMenuBuilder)
  {
    Window.Callback localCallback = this$0.getWindowCallback();
    if (localCallback != null) {
      localCallback.onMenuOpened(8, paramMenuBuilder);
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AppCompatDelegateImplV7.ActionMenuPresenterCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */