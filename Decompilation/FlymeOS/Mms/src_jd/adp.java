import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import com.android.mms.view.MessageListItemSms;

public class adp
  implements ActionMode.Callback
{
  public adp(MessageListItemSms paramMessageListItemSms) {}
  
  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    return false;
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    return true;
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode)
  {
    if (a.z == null) {
      return;
    }
    a.x();
  }
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     adp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */