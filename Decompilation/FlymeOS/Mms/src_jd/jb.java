import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.MmsApp;
import com.android.mms.fragmentstyle.ConversationListFragment;

public class jb
  implements View.OnClickListener
{
  public jb(ConversationListFragment paramConversationListFragment) {}
  
  public void onClick(View paramView)
  {
    Log.d("ConversationList", "markAllThreadAsRead onclicked");
    ConversationListFragment.e(a, true);
    gr.a(MmsApp.c(), ConversationListFragment.y(a));
    aab.a(ConversationListFragment.o(a), "mark_all_notice_as_read_click", "ConversationListFragment");
    aaa.a(a.getListView(), true);
  }
}

/* Location:
 * Qualified Name:     jb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */