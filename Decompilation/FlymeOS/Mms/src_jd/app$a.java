import java.util.concurrent.ThreadFactory;

class app$a
  implements ThreadFactory
{
  public Thread newThread(Runnable paramRunnable)
  {
    paramRunnable = new Thread(paramRunnable);
    paramRunnable.setUncaughtExceptionHandler(new app.b(null));
    return paramRunnable;
  }
}

/* Location:
 * Qualified Name:     app.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */