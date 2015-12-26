import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.meizu.statsapp.UsageStatusLog;

public class ajs
  extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    UsageStatusLog.d("SJC", "action=" + paramIntent.getAction());
    if ("android.net.conn.CONNECTIVITY_CHANGE".equals(paramIntent.getAction())) {
      ajt.a(false);
    }
    while (!"com.meizu.usagestats.check_upload".equals(paramIntent.getAction())) {
      return;
    }
    ajt.a(true);
  }
}

/* Location:
 * Qualified Name:     ajs
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */