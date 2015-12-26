import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.ted.sdk.yellow.YellowPageEngine;
import com.ted.sdk.yellow.YellowPageEngine.b;

public class asn
  extends Handler
{
  public asn(YellowPageEngine paramYellowPageEngine, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    default: 
      return;
    }
    ((YellowPageEngine.b)obj).a();
  }
}

/* Location:
 * Qualified Name:     asn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */