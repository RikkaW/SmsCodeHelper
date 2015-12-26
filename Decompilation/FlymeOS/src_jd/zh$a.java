import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

class zh$a
  implements ThreadFactory
{
  private final AtomicInteger a = new AtomicInteger(1);
  private final String b;
  
  public zh$a(String paramString)
  {
    b = paramString;
  }
  
  public Thread newThread(Runnable paramRunnable)
  {
    paramRunnable = new Thread(paramRunnable, b + "-" + a.getAndIncrement());
    if (paramRunnable.getPriority() != 1) {
      paramRunnable.setPriority(1);
    }
    return paramRunnable;
  }
}

/* Location:
 * Qualified Name:     zh.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */