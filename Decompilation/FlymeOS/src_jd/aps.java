import java.util.concurrent.ThreadFactory;

class aps
  implements ThreadFactory
{
  public Thread newThread(Runnable paramRunnable)
  {
    paramRunnable = new Thread(paramRunnable);
    paramRunnable.setUncaughtExceptionHandler(new apt());
    return paramRunnable;
  }
}

/* Location:
 * Qualified Name:     aps
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */