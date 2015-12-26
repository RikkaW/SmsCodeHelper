package android.support.v4.view;

import android.view.MenuItem;

class MenuItemCompat$IcsMenuVersionImpl
  extends MenuItemCompat.HoneycombMenuVersionImpl
{
  public boolean collapseActionView(MenuItem paramMenuItem)
  {
    return MenuItemCompatIcs.collapseActionView(paramMenuItem);
  }
  
  public boolean expandActionView(MenuItem paramMenuItem)
  {
    return MenuItemCompatIcs.expandActionView(paramMenuItem);
  }
  
  public boolean isActionViewExpanded(MenuItem paramMenuItem)
  {
    return MenuItemCompatIcs.isActionViewExpanded(paramMenuItem);
  }
  
  public MenuItem setOnActionExpandListener(MenuItem paramMenuItem, MenuItemCompat.OnActionExpandListener paramOnActionExpandListener)
  {
    if (paramOnActionExpandListener == null) {
      return MenuItemCompatIcs.setOnActionExpandListener(paramMenuItem, null);
    }
    return MenuItemCompatIcs.setOnActionExpandListener(paramMenuItem, new MenuItemCompat.IcsMenuVersionImpl.1(this, paramOnActionExpandListener));
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.MenuItemCompat.IcsMenuVersionImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */