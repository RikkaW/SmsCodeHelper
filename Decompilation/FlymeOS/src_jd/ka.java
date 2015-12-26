import android.app.Activity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.AbsListView.MultiChoiceModeListener;
import com.meizu.common.widget.SelectionButton;

public abstract class ka
  implements AbsListView.MultiChoiceModeListener
{
  protected ActionMode a;
  public volatile boolean b;
  public boolean c;
  public SelectionButton d;
  
  public void a(int paramInt1, int paramInt2)
  {
    if (d != null)
    {
      d.setCurrentCount(paramInt1);
      d.setTotalCount(paramInt2);
      if (!c) {
        if (paramInt1 <= 0) {
          break label42;
        }
      }
    }
    label42:
    for (boolean bool = true;; bool = false)
    {
      a(bool);
      return;
    }
  }
  
  public void a(int paramInt, Menu paramMenu, Activity paramActivity)
  {
    paramActivity.getMenuInflater().inflate(paramInt, paramMenu);
  }
  
  public void a(Menu paramMenu, int paramInt, SelectionButton paramSelectionButton)
  {
    b = true;
    d = paramSelectionButton;
    a(d, paramInt);
  }
  
  protected void a(SelectionButton paramSelectionButton, int paramInt)
  {
    if (paramSelectionButton != null)
    {
      paramSelectionButton.setTotalCount(paramInt);
      paramSelectionButton.setOnClickListener(new kb(this));
    }
  }
  
  public void a(boolean paramBoolean)
  {
    SelectionButton localSelectionButton;
    if (!c)
    {
      c = paramBoolean;
      localSelectionButton = d;
      if (!paramBoolean) {
        break label29;
      }
    }
    label29:
    for (int i = 0;; i = 8)
    {
      localSelectionButton.setVisibility(i);
      return;
    }
  }
  
  public boolean a()
  {
    return b;
  }
  
  public abstract void b();
  
  public void b(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      d.setVisibility(0);
      return;
    }
    d.setVisibility(8);
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
    if (d != null) {
      d.setOnClickListener(null);
    }
    if (a != null) {
      a = null;
    }
    b(false);
  }
}

/* Location:
 * Qualified Name:     ka
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */