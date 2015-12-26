import android.content.Intent;
import com.android.mms.MmsApp;
import com.android.mms.ui.ComposeMessageActivity;

public class qn
  implements Runnable
{
  public qn(ComposeMessageActivity paramComposeMessageActivity, Intent paramIntent) {}
  
  public void run()
  {
    ComposeMessageActivity.a(a, MmsApp.c());
    ComposeMessageActivity.b(a, MmsApp.c());
  }
}

/* Location:
 * Qualified Name:     qn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */