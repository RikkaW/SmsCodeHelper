import android.os.Handler;
import android.os.Message;
import com.android.mms.ui.ComposeMessageActivity;

public class ri
  extends Handler
{
  public ri(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    case 4: 
    case 5: 
    case 6: 
    default: 
    case 1: 
    case 2: 
      do
      {
        return;
        ComposeMessageActivity.b(a);
        return;
      } while (!ComposeMessageActivity.c(a));
      ComposeMessageActivity.d(a);
      return;
    case 3: 
    case 7: 
    case 8: 
    case 9: 
    case 10: 
    case 13: 
      ComposeMessageActivity.a(a, what, -1L);
      return;
    case 11: 
      if (a.p)
      {
        a.c.a(true);
        return;
      }
      ComposeMessageActivity.a(a, 2131493535);
      return;
    }
    a.c.a(true);
  }
}

/* Location:
 * Qualified Name:     ri
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */