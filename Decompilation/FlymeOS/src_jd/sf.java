import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.ui.MessageListView;
import java.util.ArrayList;

public class sf
  extends BroadcastReceiver
{
  public sf(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("com.android.mms.sip.dest_check_popup".equals(paramIntent.getAction()))
    {
      ArrayList localArrayList = aba.a(paramIntent);
      int i = 0;
      if (i < localArrayList.size())
      {
        abd localabd = (abd)localArrayList.get(i);
        String str = localabd.a();
        int j = localabd.b();
        int k = ComposeMessageActivity.e(j);
        paramIntent = nd.c.a;
        if ((!localabd.c()) && (k >= 0))
        {
          paramContext = nd.c.a;
          label82:
          if (!aba.a().d()) {
            break label194;
          }
          Log.i("Mms/compose", "mSipRecipientStateReceiver online, receive number is " + str + ", result.isOnline() is " + localabd.c() + ", ability = " + j + ", version = " + k);
          ComposeMessageActivity.a(a, str, paramContext, k);
        }
        for (;;)
        {
          i += 1;
          break;
          paramContext = paramIntent;
          if (!localabd.c()) {
            break label82;
          }
          paramContext = paramIntent;
          if (k < 0) {
            break label82;
          }
          paramContext = nd.c.c;
          break label82;
          label194:
          Log.i("Mms/compose", "mSipRecipientStateReceiver offline, receive number is " + str + ", result.isOnline() is " + localabd.c() + ", ability = " + j + ", version = " + k);
          ComposeMessageActivity.a(a, str, nd.c.a, 0);
        }
      }
    }
    else
    {
      if ((!"android.intent.action.MEDIA_BAD_REMOVAL".equals(paramIntent.getAction())) && (!"android.intent.action.MEDIA_REMOVED".equals(paramIntent.getAction())) && (!"android.intent.action.MEDIA_UNMOUNTED".equals(paramIntent.getAction()))) {
        break label369;
      }
      if ((ComposeMessageActivity.R(a).a(false)) && (a.l()))
      {
        a.b.b();
        if ((a.l()) && (ComposeMessageActivity.v(a))) {}
        ComposeMessageActivity.u(a, false);
      }
    }
    label369:
    while ((!"android.intent.action.MEDIA_MOUNTED".equals(paramIntent.getAction())) || (!ComposeMessageActivity.R(a).a(true))) {
      return;
    }
    a.b.b();
    ComposeMessageActivity.u(a, true);
  }
}

/* Location:
 * Qualified Name:     sf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */