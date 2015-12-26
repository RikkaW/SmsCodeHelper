import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.meizu.statsapp.UsageStatusLog;

class ajt$a
  extends BroadcastReceiver
{
  private ajt$a(ajt paramajt) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("android.intent.action.ACTION_POWER_CONNECTED".equals(paramIntent.getAction()))
    {
      if (!ajt.o(a)) {
        ajt.d(a, true);
      }
      ajt.f(a).removeMessages(5);
      ajt.f(a).sendEmptyMessage(5);
      UsageStatusLog.d("UsageStatsUploader", "ACTION_POWER_CONNECTED, mPowerConnecting=" + ajt.o(a));
    }
    do
    {
      return;
      if ("android.intent.action.ACTION_POWER_DISCONNECTED".equals(paramIntent.getAction()))
      {
        if (ajt.o(a)) {
          ajt.d(a, false);
        }
        UsageStatusLog.d("UsageStatsUploader", "ACTION_POWER_DISCONNECTED, mPowerConnecting=" + ajt.o(a));
        return;
      }
    } while (!"android.intent.action.TIME_SET".equals(paramIntent.getAction()));
    Log.d("UsageStatsUploader", "ACTION_TIME_CHANGED, ");
    ajt.f(a).sendEmptyMessage(4);
  }
}

/* Location:
 * Qualified Name:     ajt.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */