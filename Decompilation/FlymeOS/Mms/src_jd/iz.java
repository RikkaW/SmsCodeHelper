import android.app.Activity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.AbsListView.MultiChoiceModeListener;

public abstract class iz
  implements AbsListView.MultiChoiceModeListener
{
  protected ActionMode a;
  public volatile boolean b = false;
  protected boolean c = false;
  
  public void a()
  {
    b = true;
  }
  
  public void a(int paramInt1, int paramInt2) {}
  
  public void a(int paramInt, Menu paramMenu, Activity paramActivity)
  {
    paramActivity.getMenuInflater().inflate(paramInt, paramMenu);
  }
  
  public boolean b()
  {
    return b;
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    a = paramActionMode;
    return true;
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode)
  {
    c = false;
    b = false;
    if (a != null) {
      a = null;
    }
  }
}

/* Location:
 * Qualified Name:     iz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */