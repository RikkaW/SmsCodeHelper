package android.support.v4.content;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class ModernAsyncTask$1
  implements ThreadFactory
{
  private final AtomicInteger mCount = new AtomicInteger(1);
  
  public Thread newThread(Runnable paramRunnable)
  {
    return new Thread(paramRunnable, "ModernAsyncTask #" + mCount.getAndIncrement());
  }
}

/* Location:
 * Qualified Name:     android.support.v4.content.ModernAsyncTask.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */