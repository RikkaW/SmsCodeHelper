import android.view.inputmethod.InputMethodManager;
import com.android.mms.ui.ComposeMessageActivity;

public class qp
  implements Runnable
{
  public qp(ComposeMessageActivity paramComposeMessageActivity, InputMethodManager paramInputMethodManager) {}
  
  public void run()
  {
    if ((b.isFinishing()) || (!ComposeMessageActivity.v(b))) {}
    while ((ComposeMessageActivity.w(b).c()) || (b.getCurrentFocus() == null)) {
      return;
    }
    a.showSoftInput(b.getCurrentFocus(), 0, null);
  }
}

/* Location:
 * Qualified Name:     qp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */