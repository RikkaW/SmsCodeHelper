import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.ServiceState;
import android.util.Log;

class zp
  extends BroadcastReceiver
{
  zp(zn paramzn) {}
  
  public void onReceive(Context arg1, Intent paramIntent)
  {
    if ("android.intent.action.SERVICE_STATE".equals(paramIntent.getAction()))
    {
      Log.v("DownloadManager", "Service state changed: " + paramIntent.getExtras());
      boolean bool = ((ServiceState)aau.a(ServiceState.class, "newFromBundle", Bundle.class, paramIntent.getExtras())).getRoaming();
      Log.v("DownloadManager", "mRoamingStateListener roaming ------> " + bool);
      synchronized (zn.c())
      {
        if (zv.a)
        {
          zn.a(a, a.a(zn.c(a), 0));
          zn.b(a, a.a(zn.c(a), 1));
          Log.v("DownloadManager", "mRoamingStateListener mAutoDownload ------> " + zn.a(a));
          Log.v("DownloadManager", "mRoamingStateListener mAutoDownload2 ------> " + zn.b(a) + ", multi sim device = " + zv.a);
          return;
        }
        zn.a(a, zn.a(zn.c(a), bool));
      }
    }
  }
}

/* Location:
 * Qualified Name:     zp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */