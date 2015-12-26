package flyme.support.v7.widget;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

class MzRecyclerView$c
  implements MzRecyclerView.b
{
  private MzRecyclerView.b b;
  
  MzRecyclerView$c(MzRecyclerView paramMzRecyclerView) {}
  
  public void a(ActionMode paramActionMode, int paramInt, long paramLong, boolean paramBoolean)
  {
    b.a(paramActionMode, paramInt, paramLong, paramBoolean);
    if ((a.getCheckedItemCount() == 0) && (a.a == 5)) {}
  }
  
  public void a(MzRecyclerView.b paramb)
  {
    b = paramb;
  }
  
  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    return b.onActionItemClicked(paramActionMode, paramMenuItem);
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    if (b.onCreateActionMode(paramActionMode, paramMenu))
    {
      if ((a.a == 4) || (a.a == 5))
      {
        a.setLongClickable(true);
        return true;
      }
      a.setLongClickable(false);
      return true;
    }
    return false;
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode)
  {
    b.onDestroyActionMode(paramActionMode);
    a.b = null;
    a.b();
    MzRecyclerView.a(a);
    a.setLongClickable(true);
  }
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    return b.onPrepareActionMode(paramActionMode, paramMenu);
  }
}

/* Location:
 * Qualified Name:     flyme.support.v7.widget.MzRecyclerView.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */