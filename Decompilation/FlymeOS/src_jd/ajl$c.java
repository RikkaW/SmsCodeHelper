import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.meizu.statsapp.UsageStatusLog;

class ajl$c
  implements ServiceConnection
{
  private ajl$c(ajl paramajl) {}
  
  public void onServiceConnected(ComponentName arg1, IBinder paramIBinder)
  {
    synchronized (ajl.f(a))
    {
      try
      {
        UsageStatusLog.d("UsageStatsManager", "onServiceConnected, " + ajl.g(a));
        ajl.a(a, ajj.a.a(paramIBinder));
        UsageStatusLog.d("UsageStatsManager", "unbindService, " + ajl.g(a));
        ajl.d(a).unbindService(ajl.g(a));
        ajl.f(a).notifyAll();
        return;
      }
      catch (Exception paramIBinder)
      {
        for (;;)
        {
          paramIBinder.printStackTrace();
        }
      }
    }
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    UsageStatusLog.d("UsageStatsManager", "onServiceDisconnected, " + ajl.g(a));
  }
}

/* Location:
 * Qualified Name:     ajl.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */