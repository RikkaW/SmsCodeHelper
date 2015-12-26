import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.ListView;
import com.android.mms.fragmentstyle.ConversationListFragment;

public class jh
  implements View.OnKeyListener
{
  public jh(ConversationListFragment paramConversationListFragment) {}
  
  public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getAction() == 0) {}
    switch (paramInt)
    {
    default: 
      return false;
    }
    long l = a.getListView().getSelectedItemId();
    if (l > 0L) {
      a.a(l, a.a);
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     jh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */