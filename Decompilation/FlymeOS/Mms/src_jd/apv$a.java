import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class apv$a
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

/* Location:
 * Qualified Name:     apv.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */