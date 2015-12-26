import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.android.mms.ui.SelectConversationList;
import com.android.mms.ui.SelectConversationList.b;

public class xr
  implements View.OnClickListener
{
  public xr(SelectConversationList.b paramb) {}
  
  public void onClick(View paramView)
  {
    if (SelectConversationList.a(a.e).getAdapter().getCount() == SelectConversationList.a(a.e).getCheckedItemCount()) {
      SelectConversationList.b(a.e).f();
    }
    for (;;)
    {
      SelectConversationList.b.a(a);
      a.c();
      return;
      SelectConversationList.b(a.e).b();
    }
  }
}

/* Location:
 * Qualified Name:     xr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */