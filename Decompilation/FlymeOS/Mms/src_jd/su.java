import android.os.Handler;
import android.os.Message;
import com.android.mms.ui.ComposeMessageActivity;

public class su
  extends Handler
{
  public su(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void handleMessage(Message paramMessage)
  {
    if (what == ComposeMessageActivity.C()) {
      ComposeMessageActivity.b(a, ComposeMessageActivity.ao(a));
    }
  }
}

/* Location:
 * Qualified Name:     su
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */