import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

class ahj
  implements ServiceConnection
{
  ahj(ahi paramahi, ahi.a parama) {}
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    ahi.a(b, aft.a.a(paramIBinder));
    a.a(0);
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    ahi.a(b, null);
  }
}

/* Location:
 * Qualified Name:     ahj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */