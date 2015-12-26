import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.mms.ui.ComposeMessageActivity;

public class qb
  extends BroadcastReceiver
{
  public qb(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("android.intent.action.ACTION_SHUTDOWN".equals(paramIntent.getAction())) {
      ComposeMessageActivity.a(a, false);
    }
  }
}

/* Location:
 * Qualified Name:     qb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */