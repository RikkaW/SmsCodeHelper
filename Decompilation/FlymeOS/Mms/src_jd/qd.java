import android.widget.EditText;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.view.EditTextEx;
import com.meizu.commonwidget.RecipientEdit.f;

public class qd
  implements RecipientEdit.f
{
  public qd(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void a()
  {
    if ((ComposeMessageActivity.k(a) != null) && (ComposeMessageActivity.k(a).isShown())) {
      ComposeMessageActivity.k(a).requestFocus();
    }
    while (!ComposeMessageActivity.l(a).isShown()) {
      return;
    }
    ComposeMessageActivity.l(a).requestFocus();
  }
}

/* Location:
 * Qualified Name:     qd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */