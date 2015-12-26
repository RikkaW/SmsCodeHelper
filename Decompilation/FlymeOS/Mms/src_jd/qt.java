import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.view.EditTextEx;
import com.android.mms.view.MmsRichContainer;

public class qt
  implements Runnable
{
  public qt(ComposeMessageActivity paramComposeMessageActivity, boolean paramBoolean) {}
  
  public void run()
  {
    if ((!b.r()) && (!b.l()) && (!ComposeMessageActivity.B(b)) && (!ComposeMessageActivity.C(b))) {
      ComposeMessageActivity.g(b, a);
    }
    ComposeMessageActivity.h(b, a);
    if (a)
    {
      if (!MmsApp.a) {
        break label175;
      }
      ComposeMessageActivity.D(b).setText(b.getString(2131493019));
      ComposeMessageActivity.D(b).setVisibility(0);
    }
    for (;;)
    {
      ComposeMessageActivity.E(b).b(false);
      ComposeMessageActivity.a(b, ComposeMessageActivity.l(b).getText(), 0, 0, 0);
      if (a) {
        break label211;
      }
      ComposeMessageActivity.l(b).requestFocus();
      if (ComposeMessageActivity.l(b).length() != 0) {
        break;
      }
      b.i();
      return;
      label175:
      ComposeMessageActivity.D(b).setVisibility(8);
    }
    ComposeMessageActivity.l(b).setSelection(ComposeMessageActivity.l(b).length());
    return;
    label211:
    b.i();
  }
}

/* Location:
 * Qualified Name:     qt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */