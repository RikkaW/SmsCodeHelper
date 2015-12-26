import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.mms.transaction.MessagePopupService;
import com.android.mms.ui.ComposeMessageActivity;
import com.meizu.widget.RecipientStateInfo.SipState;
import java.util.ArrayList;

public class nw
  extends BroadcastReceiver
{
  public nw(MessagePopupService paramMessagePopupService) {}
  
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
        paramIntent = RecipientStateInfo.SipState.SIP_NO;
        if ((!localabd.c()) && (k >= 0))
        {
          paramContext = RecipientStateInfo.SipState.SIP_OFFLINE;
          label82:
          MessagePopupService.a(true, 3, "MessagePopupService", "onReceive online = " + aba.a().d() + ", receive number is " + str + ", result.isOnline() is " + localabd.c() + ", ability = " + j + ", version = " + k);
          paramIntent = MessagePopupService.a(a);
          if ((!aba.a().d()) || (paramContext != RecipientStateInfo.SipState.SIP_YES)) {
            break label222;
          }
        }
        label222:
        for (boolean bool = true;; bool = false)
        {
          paramIntent.a(bool, str);
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
          paramContext = RecipientStateInfo.SipState.SIP_YES;
          break label82;
        }
      }
    }
    else if ("android.intent.action.SCREEN_OFF".equals(paramIntent.getAction()))
    {
      MessagePopupService.a(false, 3, "MessagePopupService", "onReceive ACTION_SCREEN_OFF");
      MessagePopupService.a(a, 0L, false);
    }
  }
}

/* Location:
 * Qualified Name:     nw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */