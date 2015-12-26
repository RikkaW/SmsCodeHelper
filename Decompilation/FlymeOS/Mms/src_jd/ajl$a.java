import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.meizu.statsapp.UsageStatsProxy.Event;

class ajl$a
  extends Handler
{
  public ajl$a(ajl paramajl, Looper paramLooper)
  {
    super(paramLooper);
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
        if (ajl.b(a) == null) {
          continue;
        }
        ajl.b(a).a(paramMessage.getString("pckageName"), paramMessage.getBoolean("start"), paramMessage.getString("name"), paramMessage.getLong("time"));
        return;
      }
      catch (Exception paramMessage)
      {
        paramMessage.printStackTrace();
        return;
      }
      try
      {
        if (ajl.b(a) == null) {
          continue;
        }
        ajl.b(a).a((UsageStatsProxy.Event)obj);
        return;
      }
      catch (Exception paramMessage)
      {
        paramMessage.printStackTrace();
        return;
      }
      try
      {
        if (ajl.b(a) == null) {
          continue;
        }
        ajl.b(a).b((UsageStatsProxy.Event)obj);
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
        if (ajl.c(a) == bool) {
          break label235;
        }
        ajl.a(a, bool);
        try
        {
          if (ajl.b(a) == null) {
            break;
          }
          ajl.b(a).a(ajl.c(a));
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

/* Location:
 * Qualified Name:     ajl.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */