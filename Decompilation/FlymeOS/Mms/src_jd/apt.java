import android.util.Log;

class apt
  implements Thread.UncaughtExceptionHandler
{
  private static final String a = apt.class.getSimpleName();
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    Log.e(a, "caught: " + paramThrowable);
    paramThrowable.printStackTrace();
  }
}

/* Location:
 * Qualified Name:     apt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */