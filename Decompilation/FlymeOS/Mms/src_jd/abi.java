import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

class abi
  implements ServiceConnection
{
  abi(abh paramabh) {}
  
  public void onServiceConnected(ComponentName arg1, IBinder paramIBinder)
  {
    abh.a(a, aiv.a.a(paramIBinder));
    Log.i("SnsMessageManager", "onServiceConnected, mSnsSmsService is " + abh.a(a));
    synchronized (abh.b(a))
    {
      abh.b(a).notifyAll();
      return;
    }
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    abh.a(a, null);
    Log.e("SnsMessageManager", "onServiceDisconnected, mSnsSmsService is null");
  }
}

/* Location:
 * Qualified Name:     abi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */