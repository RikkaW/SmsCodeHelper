package android.support.v4.view;

import android.view.MenuItem;

class MenuItemCompat$IcsMenuVersionImpl$1
  implements MenuItemCompatIcs.SupportActionExpandProxy
{
  MenuItemCompat$IcsMenuVersionImpl$1(MenuItemCompat.IcsMenuVersionImpl paramIcsMenuVersionImpl, MenuItemCompat.OnActionExpandListener paramOnActionExpandListener) {}
  
  public boolean onMenuItemActionCollapse(MenuItem paramMenuItem)
  {
    return val$listener.onMenuItemActionCollapse(paramMenuItem);
  }
  
  public boolean onMenuItemActionExpand(MenuItem paramMenuItem)
  {
    return val$listener.onMenuItemActionExpand(paramMenuItem);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.MenuItemCompat.IcsMenuVersionImpl.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */