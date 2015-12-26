import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import java.util.ArrayList;

class agu$b
  extends BroadcastReceiver
{
  private agu$b(agu paramagu) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent == null) {}
    do
    {
      do
      {
        do
        {
          for (;;)
          {
            return;
            try
            {
              paramIntent = paramIntent.getAction();
              if (paramIntent.equals("android.net.wifi.SCAN_RESULTS"))
              {
                if (agu.d(a) == null) {
                  continue;
                }
                agu.a(a, agu.d(a).getScanResults());
                agu.c(a, ahz.b());
                if (agu.e(a) != null) {
                  continue;
                }
                agu.a(a, new ArrayList());
              }
            }
            catch (Throwable paramContext)
            {
              paramContext.printStackTrace();
              return;
            }
          }
          if (!paramIntent.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
            break;
          }
          paramContext = agu.d(a);
        } while (paramContext == null);
        int i = 4;
        try
        {
          int j = agu.d(a).getWifiState();
          i = j;
        }
        catch (SecurityException paramContext)
        {
          for (;;) {}
        }
        switch (i)
        {
        case 2: 
        case 3: 
        default: 
          return;
        case 0: 
          agu.f(a);
          return;
        case 1: 
          agu.f(a);
          return;
        }
        agu.f(a);
        return;
        if (paramIntent.equals("android.intent.action.SCREEN_ON"))
        {
          agu.g(a);
          agu.h(a);
          ahk.i = 10000L;
          ahk.j = 30000L;
          return;
        }
        if (!paramIntent.equals("android.intent.action.SCREEN_OFF")) {
          break;
        }
      } while (agu.i(a) < 5);
      ahk.i = 20000L;
      ahk.j = 60000L;
      return;
      if (paramIntent.equals("android.intent.action.AIRPLANE_MODE"))
      {
        agu.a(a, ahz.a(paramContext));
        return;
      }
    } while (!paramIntent.equals("android.net.conn.CONNECTIVITY_CHANGE"));
    a.a(true, 2);
  }
}

/* Location:
 * Qualified Name:     agu.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */