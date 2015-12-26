import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings.System;
import com.meizu.statsapp.UsageStatsProxy.Event;
import com.meizu.statsapp.UsageStatusLog;
import com.meizu.statsapp.util.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ajn
  extends ajj.a
{
  private static Object a = new Object();
  private static volatile ajn b;
  private Context c;
  private boolean d;
  private boolean e;
  private ajq f;
  private ajt g;
  private HandlerThread h = new HandlerThread("RecordEventThread");
  private ajn.c i;
  private Map<String, String> j = new HashMap();
  private ajn.d k;
  private volatile boolean l = true;
  private LinkedList<ajn.b> m = new LinkedList();
  private long n;
  private List<UsageStatsProxy.Event> o = new ArrayList();
  private int p = 0;
  private String q = null;
  private String r = null;
  private String s = null;
  private Runnable t = new ajo(this);
  
  private ajn(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    c = paramContext;
    d = paramBoolean1;
    e = paramBoolean2;
    h.start();
    i = new ajn.c(h.getLooper());
    a();
  }
  
  static ajn a(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (b == null) {}
    synchronized (a)
    {
      if (b == null) {
        b = new ajn(paramContext, paramBoolean1, paramBoolean2);
      }
      return b;
    }
  }
  
  private void a()
  {
    IntentFilter localIntentFilter = null;
    try
    {
      localObject = akc.a("com.android.internal.telephony.TelephonyIntents", "ACTION_SERVICE_STATE_CHANGED");
      if (localObject == null)
      {
        localObject = localIntentFilter;
        s = ((String)localObject);
        UsageStatusLog.initLog();
        a(c);
        q = Utils.getNetworkType(c);
        localObject = new ajn.a();
        localIntentFilter = new IntentFilter();
        localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        localIntentFilter.addAction(s);
      }
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      try
      {
        for (;;)
        {
          Object localObject;
          c.unregisterReceiver((BroadcastReceiver)localObject);
          c.registerReceiver((BroadcastReceiver)localObject, localIntentFilter);
          f = ajq.a(c, d);
          if (d) {
            g = ajt.a(c, d, e);
          }
          if (!d)
          {
            if (Settings.System.getInt(c.getContentResolver(), "meizu_data_collection", 0) == 0) {
              break;
            }
            bool = true;
            l = bool;
            k = new ajn.d(i);
            c.getContentResolver().registerContentObserver(Settings.System.getUriFor("meizu_data_collection"), true, k);
          }
          return;
          localNoSuchFieldException = localNoSuchFieldException;
          localNoSuchFieldException.printStackTrace();
          str = null;
        }
        String str = str.toString();
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
          continue;
          boolean bool = false;
        }
      }
    }
  }
  
  private void a(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    if (localPackageManager != null) {
      try
      {
        paramContext = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 128);
        if ((paramContext != null) && (metaData != null))
        {
          if (metaData.get("uxip_channel_num") == null) {}
          for (int i1 = 0;; i1 = ((Integer)metaData.get("uxip_channel_num")).intValue())
          {
            p = i1;
            return;
          }
        }
        return;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public void a(UsageStatsProxy.Event paramEvent)
  {
    paramEvent = i.obtainMessage(1, paramEvent);
    i.sendMessage(paramEvent);
  }
  
  public void a(String paramString1, boolean paramBoolean, String paramString2, long paramLong)
  {
    if (Utils.isEmpty(paramString2)) {
      return;
    }
    paramString1 = new ajn.b(paramString1, paramString2, paramLong);
    if (paramBoolean) {}
    for (paramString1 = i.obtainMessage(3, paramString1);; paramString1 = i.obtainMessage(4, paramString1))
    {
      i.sendMessage(paramString1);
      return;
    }
  }
  
  public void a(boolean paramBoolean)
  {
    if ((d) && (e != paramBoolean))
    {
      e = paramBoolean;
      g.b(e);
    }
  }
  
  public void b(UsageStatsProxy.Event paramEvent)
  {
    paramEvent = i.obtainMessage(2, paramEvent);
    i.sendMessage(paramEvent);
  }
  
  public class a
    extends BroadcastReceiver
  {
    public a() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ("android.net.conn.CONNECTIVITY_CHANGE".equals(paramIntent.getAction())) {
        ajn.a(ajn.this, Utils.getNetworkType(ajn.k(ajn.this)));
      }
      while (!ajn.l(ajn.this).equals(paramIntent.getAction())) {
        return;
      }
      ajn.a(ajn.this, Utils.getNetworkType(ajn.k(ajn.this)));
    }
  }
  
  static class b
  {
    private String a;
    private String b;
    private long c;
    
    public b(String paramString1, String paramString2, long paramLong)
    {
      a = paramString1;
      b = paramString2;
      c = paramLong;
    }
    
    public String a()
    {
      return a;
    }
    
    public String b()
    {
      return b;
    }
    
    public long c()
    {
      return c;
    }
    
    public String toString()
    {
      return "{" + b + ", " + c + ", " + a + "}";
    }
  }
  
  class c
    extends Handler
  {
    public c(Looper paramLooper)
    {
      super();
    }
    
    private void a(UsageStatsProxy.Event paramEvent)
    {
      try
      {
        String str2 = (String)ajn.a(ajn.this).get(paramEvent.e());
        String str1 = str2;
        if (Utils.isEmpty(str2))
        {
          str1 = UUID.randomUUID().toString();
          ajn.a(ajn.this).put(paramEvent.e(), str1);
        }
        paramEvent.b(str1);
        if (3 == paramEvent.b()) {
          paramEvent.c("com.meizu.uxip.log");
        }
        return;
      }
      catch (Exception paramEvent)
      {
        paramEvent.printStackTrace();
      }
    }
    
    private void b(UsageStatsProxy.Event paramEvent)
    {
      UsageStatusLog.d("UsageStatsManagerServer", "insert Event " + paramEvent.toString());
      try
      {
        ajn.j(ajn.this).a(paramEvent);
        if (ajn.i(ajn.this)) {
          ajn.e(ajn.this).a();
        }
        return;
      }
      catch (Exception paramEvent)
      {
        paramEvent.printStackTrace();
      }
    }
    
    public void handleMessage(Message paramMessage)
    {
      try
      {
        switch (what)
        {
        case 1: 
          UsageStatusLog.d("UsageStatsManagerServer", "ON_EVENT, mRecording=" + ajn.b(ajn.this) + ", event=" + obj);
          if (!ajn.b(ajn.this)) {
            return;
          }
          paramMessage = (UsageStatsProxy.Event)obj;
          paramMessage.b(ajn.c(ajn.this));
          paramMessage.e(ajn.d(ajn.this));
          a(paramMessage);
          b(paramMessage);
          return;
        }
      }
      catch (Exception paramMessage)
      {
        paramMessage.printStackTrace();
        return;
      }
      UsageStatusLog.d("UsageStatsManagerServer", "ON_EVENT_REALTIME, mRecording=" + ajn.b(ajn.this) + ", event=" + obj);
      if (ajn.b(ajn.this))
      {
        paramMessage = (UsageStatsProxy.Event)obj;
        paramMessage.b(ajn.c(ajn.this));
        paramMessage.e(ajn.d(ajn.this));
        a(paramMessage);
        try
        {
          if (ajn.e(ajn.this).a(paramMessage)) {
            return;
          }
          UsageStatusLog.d("UsageStatsManagerServer", "ON_EVENT_REALTIME, uploadEvent unsuccessfully, store event.");
          b(paramMessage);
          return;
        }
        catch (Exception paramMessage)
        {
          paramMessage.printStackTrace();
          return;
        }
        UsageStatusLog.d("UsageStatsManagerServer", "ON_PAGE_START, mRecording=" + ajn.b(ajn.this) + ", page=" + obj);
        if (ajn.b(ajn.this))
        {
          paramMessage = (ajn.b)obj;
          if (ajn.f(ajn.this).size() <= 0)
          {
            ajn.h(ajn.this).removeCallbacks(ajn.g(ajn.this));
            UsageStatusLog.d("UsageStatsManagerServer", "ON_PAGE_START, app boot, new session.");
            if (Utils.isEmpty((String)ajn.a(ajn.this).get(paramMessage.a())))
            {
              ajn.a(ajn.this).put(paramMessage.a(), UUID.randomUUID().toString());
              if (ajn.i(ajn.this)) {
                ajn.e(ajn.this).b();
              }
            }
          }
          ajn.f(ajn.this).addLast(paramMessage);
          return;
          UsageStatusLog.d("UsageStatsManagerServer", "ON_PAGE_STOP, mRecording=" + ajn.b(ajn.this) + ", page=" + obj);
          if (ajn.b(ajn.this))
          {
            paramMessage = (ajn.b)obj;
            Object localObject2 = ajn.f(ajn.this).iterator();
            if (localObject2 != null)
            {
              long l = System.currentTimeMillis();
              for (;;)
              {
                Object localObject1;
                if (((Iterator)localObject2).hasNext())
                {
                  localObject1 = (ajn.b)((Iterator)localObject2).next();
                  if (paramMessage.b().equals(((ajn.b)localObject1).b()))
                  {
                    ((Iterator)localObject2).remove();
                    localObject2 = new HashMap();
                    ((Map)localObject2).put("start_time", String.valueOf(ajn.b.a((ajn.b)localObject1)));
                    ((Map)localObject2).put("stop_time", String.valueOf(ajn.b.a(paramMessage)));
                    localObject1 = new UsageStatsProxy.Event(paramMessage.b(), 2, paramMessage.c(), null, paramMessage.a(), null, localObject2, Build.DISPLAY);
                    ((UsageStatsProxy.Event)localObject1).b(ajn.c(ajn.this));
                    ((UsageStatsProxy.Event)localObject1).e(ajn.d(ajn.this));
                    a((UsageStatsProxy.Event)localObject1);
                    b((UsageStatsProxy.Event)localObject1);
                  }
                }
                else
                {
                  if (ajn.f(ajn.this).size() <= 0)
                  {
                    ajn.a(ajn.this, ajn.b.a(paramMessage));
                    ajn.h(ajn.this).postDelayed(ajn.g(ajn.this), 30000L);
                    UsageStatusLog.d("UsageStatsManagerServer", "ON_PAGE_STOP, switch to background.");
                  }
                  int j = ajn.f(ajn.this).size() - 100;
                  if (j <= 0) {
                    break;
                  }
                  UsageStatusLog.d("UsageStatsManagerServer", "ON_PAGE_STOP, too many pages in stack, delete pages " + j);
                  int i = 0;
                  while (i < j)
                  {
                    ajn.f(ajn.this).removeFirst();
                    i += 1;
                  }
                }
                if (Math.abs(l - ajn.b.a((ajn.b)localObject1)) > 43200000L)
                {
                  UsageStatusLog.d("UsageStatsManagerServer", "ON_PAGE_STOP, page time out :" + localObject1);
                  ((Iterator)localObject2).remove();
                }
              }
              ajn.f(ajn.this).clear();
              ajn.a(ajn.this, 0L);
              return;
            }
          }
        }
      }
    }
  }
  
  class d
    extends ContentObserver
  {
    public d(Handler paramHandler)
    {
      super();
    }
    
    public void onChange(boolean paramBoolean, Uri paramUri)
    {
      paramBoolean = false;
      if (Settings.System.getInt(ajn.k(ajn.this).getContentResolver(), "meizu_data_collection", 0) != 0) {
        paramBoolean = true;
      }
      UsageStatusLog.d("UsageStatsManagerServer", "usage stats switch changed : " + paramBoolean);
      if (ajn.b(ajn.this) != paramBoolean)
      {
        ajn.a(ajn.this, paramBoolean);
        if (!ajn.b(ajn.this)) {
          ajn.h(ajn.this).sendEmptyMessage(5);
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     ajn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */