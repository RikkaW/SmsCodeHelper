import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class aad$b
  implements ServiceConnection
{
  public aad$b(aad paramaad) {}
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    Log.d("MzAssistantHelper", "onServiceConnected");
    aad.a(a, ano.a.b(paramIBinder));
    try
    {
      aad.a(a).a("com.android.mms", 4);
      aad.a(a, new aad.a(a));
      aad.a(a).a("com.android.mms", aad.b(a));
      Log.d("MzAssistantHelper", "onServiceConnected | mVoiceAssistantService = " + aad.a(a) + ", mVoiceAssistantCallback = " + aad.b(a));
      return;
    }
    catch (RemoteException paramComponentName)
    {
      for (;;)
      {
        Log.d("MzAssistantHelper", "onServiceConnected | remote exception = " + paramComponentName);
      }
    }
    catch (Exception paramComponentName)
    {
      for (;;)
      {
        Log.d("MzAssistantHelper", "onServiceConnected | exception = " + paramComponentName);
      }
    }
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    Log.d("MzAssistantHelper", "onServiceDisconnected");
    try
    {
      aad.a(a).a("com.android.mms");
      aad.a(a, null);
      return;
    }
    catch (RemoteException paramComponentName)
    {
      Log.d("MzAssistantHelper", "onServiceDisconnected | remote exception = " + paramComponentName);
      return;
    }
    catch (Exception paramComponentName)
    {
      Log.d("MzAssistantHelper", "onServiceDisconnected | exception = " + paramComponentName);
    }
  }
}

/* Location:
 * Qualified Name:     aad.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */