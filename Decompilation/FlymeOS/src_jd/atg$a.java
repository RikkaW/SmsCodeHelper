import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class atg$a
  extends Handler
{
  private final atg a;
  
  atg$a(atg paramatg, Looper paramLooper)
  {
    super(paramLooper);
    a = paramatg;
  }
  
  public void handleMessage(Message paramMessage)
  {
    a.a(paramMessage);
  }
}

/* Location:
 * Qualified Name:     atg.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */