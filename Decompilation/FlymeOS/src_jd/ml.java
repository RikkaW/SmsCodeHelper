import android.view.inputmethod.InputMethodManager;
import com.android.mms.quickreply.EditQuickReplyActivity;

public class ml
  implements Runnable
{
  public ml(EditQuickReplyActivity paramEditQuickReplyActivity, InputMethodManager paramInputMethodManager) {}
  
  public void run()
  {
    if ((b.isFinishing()) || (!EditQuickReplyActivity.g(b))) {
      return;
    }
    a.showSoftInput(b.getCurrentFocus(), 0, null);
  }
}

/* Location:
 * Qualified Name:     ml
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */