import android.os.Handler;
import android.os.Message;
import cn.com.xy.sms.util.ParseBubbleManager;
import com.android.mms.ui.ComposeMessageActivity;

public class sr
  implements Runnable
{
  public sr(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void run()
  {
    String str = aaw.a(a.a);
    ParseBubbleManager.loadBubbleDataByPhoneNum(str, true);
    str = aaw.a.a(str, ComposeMessageActivity.t(a));
    Message localMessage = new Message();
    obj = str;
    a.q.sendMessage(localMessage);
  }
}

/* Location:
 * Qualified Name:     sr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */