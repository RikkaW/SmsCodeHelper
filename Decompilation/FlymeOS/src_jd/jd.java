import android.widget.ListView;
import com.android.mms.fragmentstyle.ConversationListFragment;
import com.android.mms.view.ConversationListItem;

public class jd
  implements Runnable
{
  public jd(ConversationListFragment paramConversationListFragment) {}
  
  public void run()
  {
    if (ConversationListFragment.b(a) != null)
    {
      ConversationListFragment.a(System.currentTimeMillis());
      int i = ConversationListFragment.b(a).getFirstVisiblePosition();
      while (i <= ConversationListFragment.b(a).getLastVisiblePosition())
      {
        if ((ConversationListFragment.b(a).getChildAt(i) instanceof ConversationListItem))
        {
          ConversationListItem localConversationListItem = (ConversationListItem)ConversationListFragment.b(a).getChildAt(i);
          if (localConversationListItem != null) {
            localConversationListItem.a(false);
          }
        }
        i += 1;
      }
    }
  }
}

/* Location:
 * Qualified Name:     jd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */