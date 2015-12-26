import java.util.ArrayList;

class gp
  implements Runnable
{
  gp(gm.a.a parama) {}
  
  public void run()
  {
    for (;;)
    {
      Runnable localRunnable = null;
      synchronized (gm.a.a.a(a))
      {
        int i = gm.a.a.a(a).size();
        if (i != 0) {}
      }
      try
      {
        gm.a.a.a(a).wait();
        if (gm.a.a.a(a).size() > 0) {
          localRunnable = (Runnable)gm.a.a.a(a).remove(0);
        }
        if (localRunnable == null) {
          continue;
        }
        localRunnable.run();
        continue;
        localObject = finally;
        throw ((Throwable)localObject);
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;) {}
      }
    }
  }
}

/* Location:
 * Qualified Name:     gp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */