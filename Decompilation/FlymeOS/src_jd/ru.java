import android.view.View;
import android.view.View.OnFocusChangeListener;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.view.MmsRichContainer;

public class ru
  implements View.OnFocusChangeListener
{
  public ru(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (paramView == null) {}
    do
    {
      do
      {
        return;
        if (paramBoolean) {
          break;
        }
        if (paramView == ComposeMessageActivity.j(a))
        {
          a.e(false);
          return;
        }
      } while (paramView != ComposeMessageActivity.l(a));
      ComposeMessageActivity.f(a, 1);
      return;
      if (paramView == ComposeMessageActivity.j(a))
      {
        ComposeMessageActivity.E(a).a(false, 0);
        a.e(false);
        return;
      }
    } while ((paramView == ComposeMessageActivity.k(a)) || (paramView != ComposeMessageActivity.l(a)));
    ComposeMessageActivity.f(a, ComposeMessageActivity.O(a));
  }
}

/* Location:
 * Qualified Name:     ru
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */