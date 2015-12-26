import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.android.mms.recipient.MessageRecipient;

public class mw
  extends Handler
{
  public mw(MessageRecipient paramMessageRecipient, Looper paramLooper)
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
    MessageRecipient.b(a).a();
  }
}

/* Location:
 * Qualified Name:     mw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */