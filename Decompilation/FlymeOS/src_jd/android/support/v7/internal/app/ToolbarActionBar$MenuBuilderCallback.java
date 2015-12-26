package android.support.v7.internal.app;

import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuBuilder.Callback;
import android.support.v7.internal.widget.DecorToolbar;
import android.view.MenuItem;
import android.view.Window.Callback;

final class ToolbarActionBar$MenuBuilderCallback
  implements MenuBuilder.Callback
{
  private ToolbarActionBar$MenuBuilderCallback(ToolbarActionBar paramToolbarActionBar) {}
  
  public boolean onMenuItemSelected(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem)
  {
    return false;
  }
  
  public void onMenuModeChange(MenuBuilder paramMenuBuilder)
  {
    if (ToolbarActionBar.access$000(this$0) != null)
    {
      if (!ToolbarActionBar.access$300(this$0).isOverflowMenuShowing()) {
        break label41;
      }
      ToolbarActionBar.access$000(this$0).onPanelClosed(8, paramMenuBuilder);
    }
    label41:
    while (!ToolbarActionBar.access$000(this$0).onPreparePanel(0, null, paramMenuBuilder)) {
      return;
    }
    ToolbarActionBar.access$000(this$0).onMenuOpened(8, paramMenuBuilder);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.app.ToolbarActionBar.MenuBuilderCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */