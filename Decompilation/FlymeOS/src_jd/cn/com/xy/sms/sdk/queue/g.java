package cn.com.xy.sms.sdk.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class g
{
  public static BlockingQueue<i> a = new LinkedBlockingQueue();
  public static int b = 10;
  private static Thread c = null;
  
  public static void a()
  {
    try
    {
      if (c == null)
      {
        h localh = new h();
        c = localh;
        localh.start();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static void a(i parami)
  {
    try
    {
      a.put(parami);
      return;
    }
    catch (Throwable parami)
    {
      parami.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.queue.g
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */