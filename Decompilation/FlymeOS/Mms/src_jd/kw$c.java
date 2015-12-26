import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

class kw$c
  extends Handler
{
  public kw$c(kw paramkw, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(Message arg1)
  {
    int i = what;
    switch (i)
    {
    default: 
      return;
    case 1: 
      ??? = (kw.b)obj;
      for (;;)
      {
        try
        {
          b = a.a(a);
        }
        catch (Exception localException)
        {
          Message localMessage1;
          b = new kw.a();
          Log.w("SearchLocationHelper", "An exception occured during performFiltering()!", localException);
          Message localMessage2 = kw.a(a).obtainMessage(i);
          obj = ???;
          localMessage2.sendToTarget();
          continue;
        }
        finally
        {
          Message localMessage3 = kw.a(a).obtainMessage(i);
          obj = ???;
          localMessage3.sendToTarget();
        }
        synchronized (kw.b(a))
        {
          if (kw.c(a) != null)
          {
            localMessage1 = kw.c(a).obtainMessage(2);
            kw.c(a).sendMessageDelayed(localMessage1, 3000L);
          }
          return;
        }
      }
    }
    synchronized (kw.b(a))
    {
      if (kw.c(a) != null)
      {
        kw.c(a).getLooper().quit();
        kw.a(a, null);
      }
      return;
    }
  }
}

/* Location:
 * Qualified Name:     kw.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */