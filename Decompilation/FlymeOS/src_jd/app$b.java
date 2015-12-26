import android.util.Log;

class app$b
  implements Thread.UncaughtExceptionHandler
{
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    Log.e(app.a(), "caught: " + paramThrowable);
    paramThrowable.printStackTrace();
  }
}

/* Location:
 * Qualified Name:     app.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */