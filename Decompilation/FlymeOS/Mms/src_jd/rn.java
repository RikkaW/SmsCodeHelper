import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.view.EditTextEx;

public class rn
  implements Runnable
{
  public rn(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void run()
  {
    ComposeMessageActivity.l(a).requestFocus();
    ComposeMessageActivity.j(a, true);
    a.d(true);
  }
}

/* Location:
 * Qualified Name:     rn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */