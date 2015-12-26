import com.android.mms.MmsApp;
import com.android.mms.ui.ComposeMessageActivity;

public class rt
  implements Runnable
{
  public rt(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void run()
  {
    ComposeMessageActivity.a(a, a.c.b(false));
    if (ComposeMessageActivity.a(a) != null) {
      MmsApp.c().a(true);
    }
  }
}

/* Location:
 * Qualified Name:     rt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */