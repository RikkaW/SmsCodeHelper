import android.os.Handler;
import android.widget.TextView;
import com.android.mms.view.MessageListItemTalk;

public class aeb
  implements Runnable
{
  public aeb(MessageListItemTalk paramMessageListItemTalk) {}
  
  public void run()
  {
    int i = 1;
    try
    {
      String str = MessageListItemTalk.a(a, MessageListItemTalk.a(a));
      a.H.setText(str);
      if (ach.a().e() % 1000 < 500) {
        i = 0;
      }
      if (MessageListItemTalk.a(a) < i + ach.a().e() / 1000)
      {
        MessageListItemTalk.b(a);
        a.b.postDelayed(this, 1000L);
        return;
      }
      MessageListItemTalk.b(a, 1);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     aeb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */