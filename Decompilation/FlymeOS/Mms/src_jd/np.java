import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.mms.transaction.MessagePopupService;

public class np
  extends BroadcastReceiver
{
  public np(MessagePopupService paramMessagePopupService) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    boolean bool2;
    if (paramIntent.getAction().equals("android.intent.action.SCREEN_OFF"))
    {
      bool2 = ((KeyguardManager)paramContext.getSystemService("keyguard")).isKeyguardSecure();
      if (MessagePopupService.a(a) == null) {
        break label135;
      }
    }
    label135:
    for (boolean bool1 = MessagePopupService.a(a).g();; bool1 = false)
    {
      Log.d("MessagePopupService", "mIntentReceiver isKeyguardSecure -> " + bool2 + ", isShowing -> " + bool1);
      if ((bool2) && (bool1)) {
        MessagePopupService.a(a).a(false, true, "mScreenStatusReceiver -> onReceive");
      }
      return;
      Log.w("MessagePopupService", "mIntentReceiver received unexpected Intent: " + paramIntent.getAction());
      return;
    }
  }
}

/* Location:
 * Qualified Name:     np
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */