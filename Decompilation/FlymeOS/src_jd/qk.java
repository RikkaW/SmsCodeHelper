import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.view.View;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.view.EditTextEx;
import com.android.mms.view.MzContactHeaderWidget;

public class qk
  implements nd.a
{
  public qk(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void a(View paramView, nd.c paramc)
  {
    paramView = a;
    if (paramc == nd.c.c) {}
    for (boolean bool = true;; bool = false)
    {
      ComposeMessageActivity.a(paramView, bool, a.j.getSipVersion());
      ComposeMessageActivity.l(a).setFilters(new InputFilter[] { new InputFilter.LengthFilter(ga.a(ComposeMessageActivity.r(a))) });
      return;
    }
  }
}

/* Location:
 * Qualified Name:     qk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */