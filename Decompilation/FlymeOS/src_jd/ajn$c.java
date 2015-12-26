import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.meizu.statsapp.UsageStatsProxy.Event;
import com.meizu.statsapp.UsageStatusLog;
import com.meizu.statsapp.util.Utils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

class ajn$c
  extends Handler
{
  public ajn$c(ajn paramajn, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  private void a(UsageStatsProxy.Event paramEvent)
  {
    try
    {
      String str2 = (String)ajn.a(a).get(paramEvent.e());
      String str1 = str2;
      if (Utils.isEmpty(str2))
      {
        str1 = UUID.randomUUID().toString();
        ajn.a(a).put(paramEvent.e(), str1);
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
      ajn.j(a).a(paramEvent);
      if (ajn.i(a)) {
        ajn.e(a).a();
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
        UsageStatusLog.d("UsageStatsManagerServer", "ON_EVENT, mRecording=" + ajn.b(a) + ", event=" + obj);
        if (!ajn.b(a)) {
          return;
        }
        paramMessage = (UsageStatsProxy.Event)obj;
        paramMessage.b(ajn.c(a));
        paramMessage.e(ajn.d(a));
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
    UsageStatusLog.d("UsageStatsManagerServer", "ON_EVENT_REALTIME, mRecording=" + ajn.b(a) + ", event=" + obj);
    if (ajn.b(a))
    {
      paramMessage = (UsageStatsProxy.Event)obj;
      paramMessage.b(ajn.c(a));
      paramMessage.e(ajn.d(a));
      a(paramMessage);
      try
      {
        if (ajn.e(a).a(paramMessage)) {
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
      UsageStatusLog.d("UsageStatsManagerServer", "ON_PAGE_START, mRecording=" + ajn.b(a) + ", page=" + obj);
      if (ajn.b(a))
      {
        paramMessage = (ajn.b)obj;
        if (ajn.f(a).size() <= 0)
        {
          ajn.h(a).removeCallbacks(ajn.g(a));
          UsageStatusLog.d("UsageStatsManagerServer", "ON_PAGE_START, app boot, new session.");
          if (Utils.isEmpty((String)ajn.a(a).get(paramMessage.a())))
          {
            ajn.a(a).put(paramMessage.a(), UUID.randomUUID().toString());
            if (ajn.i(a)) {
              ajn.e(a).b();
            }
          }
        }
        ajn.f(a).addLast(paramMessage);
        return;
        UsageStatusLog.d("UsageStatsManagerServer", "ON_PAGE_STOP, mRecording=" + ajn.b(a) + ", page=" + obj);
        if (ajn.b(a))
        {
          paramMessage = (ajn.b)obj;
          Object localObject2 = ajn.f(a).iterator();
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
                  ((UsageStatsProxy.Event)localObject1).b(ajn.c(a));
                  ((UsageStatsProxy.Event)localObject1).e(ajn.d(a));
                  a((UsageStatsProxy.Event)localObject1);
                  b((UsageStatsProxy.Event)localObject1);
                }
              }
              else
              {
                if (ajn.f(a).size() <= 0)
                {
                  ajn.a(a, ajn.b.a(paramMessage));
                  ajn.h(a).postDelayed(ajn.g(a), 30000L);
                  UsageStatusLog.d("UsageStatsManagerServer", "ON_PAGE_STOP, switch to background.");
                }
                int j = ajn.f(a).size() - 100;
                if (j <= 0) {
                  break;
                }
                UsageStatusLog.d("UsageStatsManagerServer", "ON_PAGE_STOP, too many pages in stack, delete pages " + j);
                int i = 0;
                while (i < j)
                {
                  ajn.f(a).removeFirst();
                  i += 1;
                }
              }
              if (Math.abs(l - ajn.b.a((ajn.b)localObject1)) > 43200000L)
              {
                UsageStatusLog.d("UsageStatsManagerServer", "ON_PAGE_STOP, page time out :" + localObject1);
                ((Iterator)localObject2).remove();
              }
            }
            ajn.f(a).clear();
            ajn.a(a, 0L);
            return;
          }
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     ajn.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */