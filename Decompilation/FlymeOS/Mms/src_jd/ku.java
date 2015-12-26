import android.content.Context;
import android.os.HandlerThread;
import android.os.Looper;

public class ku
{
  protected Looper a;
  protected final int b = 5;
  protected int c = -1;
  protected int d = -1;
  protected Context e;
  
  public ku(Context paramContext)
  {
    HandlerThread localHandlerThread = new HandlerThread("");
    localHandlerThread.start();
    a = localHandlerThread.getLooper();
    e = paramContext;
  }
  
  public void a()
  {
    a.quit();
  }
  
  public void a(int paramInt)
  {
    d = paramInt;
  }
}

/* Location:
 * Qualified Name:     ku
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */