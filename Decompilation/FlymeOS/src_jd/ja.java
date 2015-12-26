import android.util.Log;
import com.android.mms.fragmentstyle.ConversationListFragment;

public class ja
  implements Runnable
{
  public ja(ConversationListFragment paramConversationListFragment, long paramLong, boolean paramBoolean) {}
  
  public void run()
  {
    if (Log.isLoggable("Mms:app", 2)) {
      ConversationListFragment.a(c, "onDraftChanged: threadId=" + a + ", hasDraft=" + b, new Object[0]);
    }
    ConversationListFragment.a(c).notifyDataSetChanged();
  }
}

/* Location:
 * Qualified Name:     ja
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */