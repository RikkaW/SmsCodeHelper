import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuBuilder.Callback;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

class arj$b
  extends ActionMode
  implements MenuBuilder.Callback
{
  private ActionMode.Callback b;
  private MenuBuilder c;
  
  public arj$b(arj paramarj, ActionMode.Callback paramCallback)
  {
    c = new MenuBuilder(arj.f(paramarj));
    c.setCallback(this);
    b = paramCallback;
  }
  
  public boolean a()
  {
    c.stopDispatchingItemsChanged();
    try
    {
      boolean bool = b.onCreateActionMode(this, c);
      return bool;
    }
    finally
    {
      c.startDispatchingItemsChanged();
    }
  }
  
  public void finish()
  {
    if (arj.a(a) != this) {
      return;
    }
    a.dismiss();
    b.onDestroyActionMode(this);
    b = null;
    arj.a(a, null);
  }
  
  public View getCustomView()
  {
    return null;
  }
  
  public Menu getMenu()
  {
    return c;
  }
  
  public MenuInflater getMenuInflater()
  {
    return new MenuInflater(arj.f(a));
  }
  
  public CharSequence getSubtitle()
  {
    return null;
  }
  
  public CharSequence getTitle()
  {
    return null;
  }
  
  public void invalidate()
  {
    c.stopDispatchingItemsChanged();
    try
    {
      b.onPrepareActionMode(this, c);
      return;
    }
    finally
    {
      c.startDispatchingItemsChanged();
    }
  }
  
  public boolean onMenuItemSelected(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem)
  {
    if (b != null) {
      return b.onActionItemClicked(this, paramMenuItem);
    }
    return false;
  }
  
  public void onMenuModeChange(MenuBuilder paramMenuBuilder) {}
  
  public void setCustomView(View paramView) {}
  
  public void setSubtitle(int paramInt) {}
  
  public void setSubtitle(CharSequence paramCharSequence) {}
  
  public void setTitle(int paramInt) {}
  
  public void setTitle(CharSequence paramCharSequence) {}
}

/* Location:
 * Qualified Name:     arj.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */