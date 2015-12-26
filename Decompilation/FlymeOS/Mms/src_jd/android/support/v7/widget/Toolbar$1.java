package android.support.v7.widget;

import android.view.MenuItem;

class Toolbar$1
  implements ActionMenuView.OnMenuItemClickListener
{
  Toolbar$1(Toolbar paramToolbar) {}
  
  public boolean onMenuItemClick(MenuItem paramMenuItem)
  {
    if (Toolbar.access$000(this$0) != null) {
      return Toolbar.access$000(this$0).onMenuItemClick(paramMenuItem);
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.Toolbar.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */