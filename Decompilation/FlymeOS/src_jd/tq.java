import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.ui.ComposeMessageActivity.h;
import com.android.mms.ui.MessageListView;
import java.util.HashMap;

public class tq
  implements View.OnClickListener
{
  public tq(ComposeMessageActivity.h paramh) {}
  
  public void onClick(View paramView)
  {
    int j = a.a.b.getCount();
    if (j == ComposeMessageActivity.R(a.a).getCheckedItemCount())
    {
      ComposeMessageActivity.W(a.a).f();
      ComposeMessageActivity.h.a(a, false);
      a.a(ComposeMessageActivity.R(a.a).getCheckedItemCount());
      return;
    }
    ComposeMessageActivity.W(a.a).b();
    int i = 0;
    label89:
    if (i < j)
    {
      paramView = (Cursor)a.a.b.getItem(i);
      if (paramView != null) {
        break label123;
      }
    }
    for (;;)
    {
      i += 1;
      break label89;
      break;
      label123:
      paramView = a.a.b.a(paramView);
      if (paramView != null)
      {
        long l = vx.a(paramView.j(), e);
        if (!ComposeMessageActivity.h.a(a).containsKey(Long.valueOf(l)))
        {
          ComposeMessageActivity.h.a(a, paramView, l);
          paramView.b(true);
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     tq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */