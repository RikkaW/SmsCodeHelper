package android.support.v7.internal.app;

import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.support.v7.internal.widget.DecorToolbar;
import android.view.Window.Callback;

final class ToolbarActionBar$ActionMenuPresenterCallback
  implements MenuPresenter.Callback
{
  private boolean mClosingActionMenu;
  
  private ToolbarActionBar$ActionMenuPresenterCallback(ToolbarActionBar paramToolbarActionBar) {}
  
  public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
  {
    if (mClosingActionMenu) {
      return;
    }
    mClosingActionMenu = true;
    ToolbarActionBar.access$300(this$0).dismissPopupMenus();
    if (ToolbarActionBar.access$000(this$0) != null) {
      ToolbarActionBar.access$000(this$0).onPanelClosed(8, paramMenuBuilder);
    }
    mClosingActionMenu = false;
  }
  
  public boolean onOpenSubMenu(MenuBuilder paramMenuBuilder)
  {
    if (ToolbarActionBar.access$000(this$0) != null)
    {
      ToolbarActionBar.access$000(this$0).onMenuOpened(8, paramMenuBuilder);
      return true;
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.app.ToolbarActionBar.ActionMenuPresenterCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */