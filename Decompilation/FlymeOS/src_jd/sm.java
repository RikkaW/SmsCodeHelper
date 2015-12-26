import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.android.mms.recipient.MessageRecipient;
import com.android.mms.ui.ComposeMessageActivity;

public class sm
  implements AdapterView.OnItemClickListener
{
  public sm(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramInt = ((Integer)paramView.getTag()).intValue();
    if ((paramInt != 3) && (paramInt != 5) && (paramInt != 7) && (!ComposeMessageActivity.a(a, false, false))) {
      return;
    }
    ComposeMessageActivity.P(a);
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      ComposeMessageActivity.a(a, 1, false);
      return;
    case 1: 
      ComposeMessageActivity.a(a, 0, false);
      return;
    case 2: 
      ComposeMessageActivity.a(a, 8, false);
      return;
    case 3: 
      ComposeMessageActivity.a(a, 7, false);
      return;
    case 4: 
      if (!ComposeMessageActivity.i(a))
      {
        ComposeMessageActivity.v(a, true);
        return;
      }
      if ((ComposeMessageActivity.j(a) != null) && (ComposeMessageActivity.j(a).g()))
      {
        ComposeMessageActivity.ad(a);
        return;
      }
      ComposeMessageActivity.v(a, ComposeMessageActivity.ae(a));
      return;
    case 5: 
      ComposeMessageActivity.af(a);
      return;
    case 6: 
      ComposeMessageActivity.a(a, 5, false);
      return;
    case 7: 
      ComposeMessageActivity.a(a, 10, false);
      return;
    }
    ComposeMessageActivity.a(a, 6, false);
  }
}

/* Location:
 * Qualified Name:     sm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */