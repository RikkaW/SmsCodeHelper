import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.ui.NotificationReply;
import java.util.ArrayList;

public class xc
  extends BroadcastReceiver
{
  public xc(NotificationReply paramNotificationReply) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("com.android.mms.sip.dest_check_popup".equals(paramIntent.getAction()))
    {
      paramContext = aba.a(paramIntent);
      if (paramContext.size() == 1)
      {
        paramContext = (abd)paramContext.get(0);
        int i = ComposeMessageActivity.e(paramContext.b());
        if ((paramContext.c()) && (i >= 0)) {
          break label108;
        }
        NotificationReply.a(a, false);
      }
    }
    for (;;)
    {
      NotificationReply.b(a, NotificationReply.f(a));
      Log.i("NotificationReply", "onReceive() --> isSipOnline = " + NotificationReply.f(a));
      return;
      label108:
      NotificationReply.a(a, aba.a().d());
    }
  }
}

/* Location:
 * Qualified Name:     xc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */