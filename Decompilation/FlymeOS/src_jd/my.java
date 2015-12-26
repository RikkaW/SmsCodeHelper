import android.os.Message;
import android.text.TextUtils;
import com.android.mms.recipient.MessageRecipient;
import com.android.mms.recipient.MessageRecipient.b;
import java.util.ArrayList;
import java.util.HashMap;

public class my
  implements Runnable
{
  public my(MessageRecipient paramMessageRecipient) {}
  
  public void run()
  {
    String str = a.getInputText();
    if (nc.a(str, MessageRecipient.f(a))) {
      if (!a.getRecipientDataList().contains(str))
      {
        if ((!MessageRecipient.g(a)) || (MessageRecipient.h(a) == null) || (!MessageRecipient.e(a).isEmpty())) {
          break label112;
        }
        MessageRecipient.c(a).put(str, nd.c.d);
        MessageRecipient.d(a).add(str);
        MessageRecipient.i(a).obtainMessage(1, str).sendToTarget();
      }
    }
    label112:
    do
    {
      do
      {
        return;
        MessageRecipient.c(a).put(str, nd.c.e);
        return;
      } while (TextUtils.isEmpty(str));
      MessageRecipient.e(a).add(str);
    } while ((MessageRecipient.e(a).size() != 1) || (MessageRecipient.j(a) == null));
    MessageRecipient.j(a).a(a, nd.c.a);
  }
}

/* Location:
 * Qualified Name:     my
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */