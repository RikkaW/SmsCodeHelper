package android.support.v7.app;

import android.support.v7.internal.view.WindowCallbackWrapper;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window.Callback;

class AppCompatDelegateImplBase$AppCompatWindowCallbackBase
  extends WindowCallbackWrapper
{
  AppCompatDelegateImplBase$AppCompatWindowCallbackBase(AppCompatDelegateImplBase paramAppCompatDelegateImplBase, Window.Callback paramCallback)
  {
    super(paramCallback);
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (super.dispatchKeyEvent(paramKeyEvent)) {
      return true;
    }
    return this$0.dispatchKeyEvent(paramKeyEvent);
  }
  
  public boolean dispatchKeyShortcutEvent(KeyEvent paramKeyEvent)
  {
    if (this$0.onKeyShortcut(paramKeyEvent.getKeyCode(), paramKeyEvent)) {
      return true;
    }
    return super.dispatchKeyShortcutEvent(paramKeyEvent);
  }
  
  public void onContentChanged() {}
  
  public boolean onCreatePanelMenu(int paramInt, Menu paramMenu)
  {
    if ((paramInt == 0) && (!(paramMenu instanceof MenuBuilder))) {
      return false;
    }
    return super.onCreatePanelMenu(paramInt, paramMenu);
  }
  
  public boolean onMenuOpened(int paramInt, Menu paramMenu)
  {
    if (this$0.onMenuOpened(paramInt, paramMenu)) {
      return true;
    }
    return super.onMenuOpened(paramInt, paramMenu);
  }
  
  public void onPanelClosed(int paramInt, Menu paramMenu)
  {
    if (this$0.onPanelClosed(paramInt, paramMenu)) {
      return;
    }
    super.onPanelClosed(paramInt, paramMenu);
  }
  
  public boolean onPreparePanel(int paramInt, View paramView, Menu paramMenu)
  {
    MenuBuilder localMenuBuilder;
    boolean bool1;
    if ((paramMenu instanceof MenuBuilder))
    {
      localMenuBuilder = (MenuBuilder)paramMenu;
      if ((paramInt != 0) || (localMenuBuilder != null)) {
        break label34;
      }
      bool1 = false;
    }
    label34:
    boolean bool2;
    do
    {
      return bool1;
      localMenuBuilder = null;
      break;
      if (localMenuBuilder != null) {
        localMenuBuilder.setOverrideVisibleItems(true);
      }
      bool2 = super.onPreparePanel(paramInt, paramView, paramMenu);
      bool1 = bool2;
    } while (localMenuBuilder == null);
    localMenuBuilder.setOverrideVisibleItems(false);
    return bool2;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AppCompatDelegateImplBase.AppCompatWindowCallbackBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */