import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.ted.android.contacts.updatesdk.IParsedDownload;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class apv
{
  private static boolean a = false;
  private static Context b;
  private static apv.a c;
  private static IntentFilter d;
  private static long e;
  private static IParsedDownload f;
  private static boolean g = true;
  private static int h;
  private static apu i;
  
  public static void a()
  {
    if ((b != null) && (c != null))
    {
      b.unregisterReceiver(c);
      c = null;
      d = null;
    }
    f = null;
  }
  
  public static void a(Context paramContext)
  {
    a(paramContext, 0);
  }
  
  public static void a(Context paramContext, int paramInt)
  {
    h = paramInt;
    if (a) {
      Log.e("update", "Update SDK init()");
    }
    com.ted.android.contacts.common.DataBus.APP_VERSION = b(paramContext);
    if (c == null)
    {
      c = new apv.a(null);
      d = new IntentFilter();
      d.addAction("android.intent.action.SCREEN_ON");
      d.addAction("android.intent.action.BATTERY_OKAY");
      d.addAction("android.intent.action.ACTION_POWER_CONNECTED");
      d.addAction("android.intent.action.DATE_CHANGED");
      d.addAction("android.net.conn.CONNECTIVITY_CHANGE");
      d.addAction("android.net.wifi.WIFI_STATE_CHANGED");
      d.addAction("android.net.wifi.STATE_CHANGE");
      b = paramContext;
      b.registerReceiver(c, d);
    }
  }
  
  public static void a(IParsedDownload paramIParsedDownload)
  {
    f = paramIParsedDownload;
  }
  
  public static void a(boolean paramBoolean)
  {
    g = paramBoolean;
  }
  
  private static String b(Context paramContext)
  {
    try
    {
      paramContext = getPackageManagergetPackageInfogetPackageName16384versionName;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  static class a
    extends BroadcastReceiver
  {
    private ExecutorService a;
    private int b = 0;
    
    private void a(String paramString)
    {
      apv.a(new apu(apv.d(), false));
      apv.e().c();
      boolean bool = anw.b(apv.d());
      switch (apv.f())
      {
      default: 
      case 1: 
      case 2: 
        do
        {
          do
          {
            return;
          } while (!bool);
          apv.e().a(apv.g());
          auw.a(apv.e());
          return;
        } while (bool);
        apv.e().a(apv.g());
        auw.a(apv.e());
        return;
      }
      apv.e().a(apv.g());
      auw.a(apv.e());
    }
    
    private boolean a()
    {
      long l = System.currentTimeMillis();
      if (l - apv.c() < 10000L)
      {
        apv.a(l);
        return false;
      }
      apv.a(l);
      return true;
    }
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (!apv.b()) {}
      int i;
      do
      {
        do
        {
          return;
        } while ((a != null) && (!a.isTerminated()));
        i = b;
        b = (i + 1);
      } while ((i <= 0) || (!a()));
      paramContext = paramIntent.getAction();
      a = Executors.newSingleThreadExecutor(new aps());
      a.execute(new apw(this, paramContext));
      a.shutdown();
    }
  }
}

/* Location:
 * Qualified Name:     apv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */