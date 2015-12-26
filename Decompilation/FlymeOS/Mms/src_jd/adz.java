import android.os.Handler;
import android.os.Message;
import com.android.mms.view.MessageListItemTalk;

public class adz
  extends Handler
{
  public adz(MessageListItemTalk paramMessageListItemTalk) {}
  
  public void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    default: 
      return;
    case 0: 
      a.setTalkBackground(true);
      return;
    }
    a.setTalkBackground(false);
  }
}

/* Location:
 * Qualified Name:     adz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */