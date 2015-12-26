import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings.System;
import com.meizu.statsapp.UsageStatsProxy.Event;
import com.meizu.statsapp.UsageStatusLog;
import com.meizu.statsapp.util.Utils;

public final class ajl
{
  private Context a;
  private boolean b;
  private boolean c;
  private HandlerThread d = new HandlerThread("UsageStatsManagerThread");
  private ajl.a e;
  private ajl.b f;
  private volatile boolean g = true;
  private ajl.c h;
  private volatile ajj i;
  
  public ajl(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    a = paramContext;
    b = paramBoolean1;
    c = paramBoolean2;
    d.start();
    e = new ajl.a(d.getLooper());
    e.post(new ajm(this));
  }
  
  private static boolean a(Context paramContext)
  {
    boolean bool2 = false;
    PackageManager localPackageManager = paramContext.getPackageManager();
    boolean bool1 = bool2;
    if (localPackageManager != null) {}
    try
    {
      paramContext = localPackageManager.getServiceInfo(new ComponentName(paramContext.getPackageName(), ajp.class.getName()), 0);
      bool1 = bool2;
      if (paramContext != null) {
        bool1 = true;
      }
      return bool1;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      UsageStatusLog.d("UsageStatsManager", paramContext.getMessage());
    }
    return false;
  }
  
  private void b()
  {
    boolean bool = false;
    UsageStatusLog.initLog();
    if (a(a)) {}
    for (;;)
    {
      synchronized (d)
      {
        h = new ajl.c(null);
        Intent localIntent = new Intent(a, ajp.class);
        localIntent.putExtra("online", b);
        localIntent.putExtra("upload", c);
        UsageStatusLog.d("UsageStatsManager", "bindService, " + h);
        a.bindService(localIntent, h, 1);
        try
        {
          d.wait();
          if (!b)
          {
            if (Settings.System.getInt(a.getContentResolver(), "meizu_data_collection", 0) != 0) {
              bool = true;
            }
            g = bool;
            f = new ajl.b(e);
            a.getContentResolver().registerContentObserver(Settings.System.getUriFor("meizu_data_collection"), true, f);
          }
          return;
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedException.printStackTrace();
          continue;
        }
      }
      i = ajn.a(a, b, c);
    }
  }
  
  public void a(UsageStatsProxy.Event paramEvent)
  {
    Message localMessage = e.obtainMessage(1);
    obj = paramEvent;
    e.sendMessage(localMessage);
  }
  
  public void a(String paramString1, boolean paramBoolean, String paramString2, long paramLong)
  {
    if (Utils.isEmpty(paramString2)) {
      return;
    }
    Message localMessage = e.obtainMessage(3);
    Bundle localBundle = new Bundle();
    localBundle.putString("pckageName", paramString1);
    localBundle.putBoolean("start", paramBoolean);
    localBundle.putString("name", paramString2);
    localBundle.putLong("time", paramLong);
    localMessage.setData(localBundle);
    e.sendMessage(localMessage);
  }
  
  public boolean a()
  {
    return g;
  }
  
  class a
    extends Handler
  {
    public a(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (what)
      {
      }
      label235:
      for (;;)
      {
        return;
        try
        {
          paramMessage = paramMessage.getData();
          if (ajl.b(ajl.this) == null) {
            continue;
          }
          ajl.b(ajl.this).a(paramMessage.getString("pckageName"), paramMessage.getBoolean("start"), paramMessage.getString("name"), paramMessage.getLong("time"));
          return;
        }
        catch (Exception paramMessage)
        {
          paramMessage.printStackTrace();
          return;
        }
        try
        {
          if (ajl.b(ajl.this) == null) {
            continue;
          }
          ajl.b(ajl.this).a((UsageStatsProxy.Event)obj);
          return;
        }
        catch (Exception paramMessage)
        {
          paramMessage.printStackTrace();
          return;
        }
        try
        {
          if (ajl.b(ajl.this) == null) {
            continue;
          }
          ajl.b(ajl.this).b((UsageStatsProxy.Event)obj);
          return;
        }
        catch (Exception paramMessage)
        {
          paramMessage.printStackTrace();
          return;
        }
        if (arg1 == 0) {}
        for (boolean bool = false;; bool = true)
        {
          if (ajl.c(ajl.this) == bool) {
            break label235;
          }
          ajl.a(ajl.this, bool);
          try
          {
            if (ajl.b(ajl.this) == null) {
              break;
            }
            ajl.b(ajl.this).a(ajl.c(ajl.this));
            return;
          }
          catch (Exception paramMessage)
          {
            paramMessage.printStackTrace();
            return;
          }
        }
      }
    }
  }
  
  class b
    extends ContentObserver
  {
    public b(Handler paramHandler)
    {
      super();
    }
    
    public void onChange(boolean paramBoolean, Uri paramUri)
    {
      paramBoolean = false;
      if (Settings.System.getInt(ajl.d(ajl.this).getContentResolver(), "meizu_data_collection", 0) != 0) {
        paramBoolean = true;
      }
      UsageStatusLog.d("UsageStatsManager", "usage stats switch changed : " + paramBoolean);
      if (ajl.e(ajl.this) != paramBoolean) {
        ajl.b(ajl.this, paramBoolean);
      }
    }
  }
  
  class c
    implements ServiceConnection
  {
    private c() {}
    
    public void onServiceConnected(ComponentName arg1, IBinder paramIBinder)
    {
      synchronized (ajl.f(ajl.this))
      {
        try
        {
          UsageStatusLog.d("UsageStatsManager", "onServiceConnected, " + ajl.g(ajl.this));
          ajl.a(ajl.this, ajj.a.a(paramIBinder));
          UsageStatusLog.d("UsageStatsManager", "unbindService, " + ajl.g(ajl.this));
          ajl.d(ajl.this).unbindService(ajl.g(ajl.this));
          ajl.f(ajl.this).notifyAll();
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
      UsageStatusLog.d("UsageStatsManager", "onServiceDisconnected, " + ajl.g(ajl.this));
    }
  }
}

/* Location:
 * Qualified Name:     ajl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */