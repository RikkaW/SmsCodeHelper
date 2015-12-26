import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.ui.ComposeMessageActivity.f;
import com.android.mms.view.AttachmentGroupView;

public class sz
  implements View.OnClickListener
{
  public sz(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void onClick(View paramView)
  {
    if (wd.c(a.getContentResolver()))
    {
      wd.a(a, 2131493765, 0, ComposeMessageActivity.n(a));
      return;
    }
    if (a.y())
    {
      ComposeMessageActivity.j(a, true);
      ComposeMessageActivity.w(a, false);
      a.d(true);
      return;
    }
    ComposeMessageActivity.w(a, true);
    ComposeMessageActivity.as(a);
    ComposeMessageActivity.a(a, ComposeMessageActivity.f.e, 1);
    ComposeMessageActivity.at(a).setVisibility(0);
  }
}

/* Location:
 * Qualified Name:     sz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */