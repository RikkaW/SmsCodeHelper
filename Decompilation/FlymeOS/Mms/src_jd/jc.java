import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.android.mms.fragmentstyle.ConversationListFragment;

public class jc
  implements View.OnClickListener
{
  public jc(ConversationListFragment paramConversationListFragment) {}
  
  public void onClick(View paramView)
  {
    if (wd.c(a.getActivity().getContentResolver()))
    {
      wd.m(ConversationListFragment.o(a));
      return;
    }
    wd.b(a.getActivity());
    ConversationListFragment.b(a).removeHeaderView(ConversationListFragment.z(a));
    ga.a(ConversationListFragment.o(a), false);
    ConversationListFragment.f(a, false);
  }
}

/* Location:
 * Qualified Name:     jc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */