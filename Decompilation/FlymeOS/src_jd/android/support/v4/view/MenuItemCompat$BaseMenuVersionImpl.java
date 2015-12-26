package android.support.v4.view;

import android.view.MenuItem;
import android.view.View;

class MenuItemCompat$BaseMenuVersionImpl
  implements MenuItemCompat.MenuVersionImpl
{
  public boolean collapseActionView(MenuItem paramMenuItem)
  {
    return false;
  }
  
  public boolean expandActionView(MenuItem paramMenuItem)
  {
    return false;
  }
  
  public View getActionView(MenuItem paramMenuItem)
  {
    return null;
  }
  
  public boolean isActionViewExpanded(MenuItem paramMenuItem)
  {
    return false;
  }
  
  public MenuItem setActionView(MenuItem paramMenuItem, int paramInt)
  {
    return paramMenuItem;
  }
  
  public MenuItem setActionView(MenuItem paramMenuItem, View paramView)
  {
    return paramMenuItem;
  }
  
  public MenuItem setOnActionExpandListener(MenuItem paramMenuItem, MenuItemCompat.OnActionExpandListener paramOnActionExpandListener)
  {
    return paramMenuItem;
  }
  
  public void setShowAsAction(MenuItem paramMenuItem, int paramInt) {}
}

/* Location:
 * Qualified Name:     android.support.v4.view.MenuItemCompat.BaseMenuVersionImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */