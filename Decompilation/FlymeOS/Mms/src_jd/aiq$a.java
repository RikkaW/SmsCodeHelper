import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

class aiq$a
  implements ServiceConnection
{
  private aiq$a(aiq paramaiq) {}
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    aiq.a(a, ais.a.a(paramIBinder));
    if (aiq.a(a) != null) {
      aiq.a(a).a();
    }
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    aiq.a(a, null);
    if (aiq.a(a) != null) {
      aiq.a(a).b();
    }
  }
}

/* Location:
 * Qualified Name:     aiq.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */