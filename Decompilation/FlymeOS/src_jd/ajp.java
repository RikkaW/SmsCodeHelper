import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class ajp
  extends Service
{
  private ajj a;
  
  public IBinder onBind(Intent paramIntent)
  {
    if (a == null)
    {
      boolean bool1 = paramIntent.getBooleanExtra("online", false);
      boolean bool2 = paramIntent.getBooleanExtra("upload", true);
      Context localContext = getApplicationContext();
      paramIntent = localContext;
      if (localContext == null) {
        paramIntent = this;
      }
      a = ajn.a(paramIntent, bool1, bool2);
    }
    return a.asBinder();
  }
  
  public void onCreate()
  {
    super.onCreate();
  }
  
  public void onDestroy()
  {
    super.onDestroy();
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    return super.onStartCommand(paramIntent, paramInt1, paramInt2);
  }
}

/* Location:
 * Qualified Name:     ajp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */