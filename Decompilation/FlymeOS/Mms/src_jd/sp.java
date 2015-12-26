import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.ImageButton;
import com.android.mms.smartmessage.SmartMessageViewContainer;
import com.android.mms.ui.ComposeMessageActivity;

public class sp
  extends Handler
{
  public sp(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void handleMessage(Message paramMessage)
  {
    boolean bool;
    if ((paramMessage != null) && (obj != null) && (!TextUtils.isEmpty(obj.toString())))
    {
      paramMessage = new aaw.a((String)obj);
      ComposeMessageActivity localComposeMessageActivity = a;
      if ((!a.p()) && (paramMessage != null))
      {
        bool = true;
        h = bool;
        if (a.h)
        {
          ComposeMessageActivity.ah(a);
          ComposeMessageActivity.ag(a);
          a.d.a(paramMessage, a.B());
          ne.a(new sq(this));
          ComposeMessageActivity.ai(a).setVisibility(0);
          ComposeMessageActivity.aj(a).setVisibility(8);
          ComposeMessageActivity.ak(a);
        }
      }
    }
    for (;;)
    {
      a.A();
      a.i();
      return;
      bool = false;
      break;
      a.h = false;
    }
  }
}

/* Location:
 * Qualified Name:     sp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */