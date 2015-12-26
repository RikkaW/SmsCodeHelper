import android.os.Message;
import android.text.TextUtils;
import com.android.mms.recipient.MessageRecipient;
import com.android.mms.recipient.MessageRecipient.b;
import com.meizu.commonwidget.RecipientEdit.h;
import java.util.ArrayList;
import java.util.HashMap;

public class mx
  implements RecipientEdit.h
{
  public mx(MessageRecipient paramMessageRecipient) {}
  
  public void a(String paramString, int paramInt)
  {
    switch (paramInt)
    {
    }
    do
    {
      do
      {
        nd.c localc;
        do
        {
          return;
          localc = (nd.c)MessageRecipient.c(a).get(paramString);
        } while ((MessageRecipient.d(a).contains(paramString)) || (MessageRecipient.e(a).contains(paramString)) || ((localc != null) && (localc != nd.c.e)));
        if (nc.a(paramString, MessageRecipient.f(a)))
        {
          if ((MessageRecipient.g(a)) && (MessageRecipient.h(a) != null) && (MessageRecipient.e(a).isEmpty()))
          {
            MessageRecipient.c(a).put(paramString, nd.c.d);
            MessageRecipient.d(a).add(paramString);
            MessageRecipient.i(a).obtainMessage(1, paramString).sendToTarget();
            return;
          }
          MessageRecipient.c(a).put(paramString, nd.c.e);
          return;
        }
        MessageRecipient.e(a).add(paramString);
      } while ((MessageRecipient.e(a).size() != 1) || (MessageRecipient.j(a) == null));
      MessageRecipient.j(a).a(a, nd.c.a);
      return;
      if (MessageRecipient.i(a) != null) {
        MessageRecipient.i(a).removeMessages(1, paramString);
      }
      MessageRecipient.d(a).remove(paramString);
      MessageRecipient.c(a).remove(paramString);
      MessageRecipient.k(a).remove(paramString);
      if (MessageRecipient.e(a).remove(paramString)) {
        MessageRecipient.l(a);
      }
    } while (MessageRecipient.j(a) == null);
    MessageRecipient.j(a).a(a, a.getSipState());
  }
  
  public void a(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((!TextUtils.isEmpty(paramString)) && (!a.getRecipientDataList().contains(paramString)))
    {
      if (MessageRecipient.i(a) != null) {
        MessageRecipient.i(a).removeMessages(1, paramString);
      }
      MessageRecipient.c(a).remove(paramString);
      MessageRecipient.k(a).remove(paramString);
      MessageRecipient.d(a).remove(paramString);
      MessageRecipient.e(a).remove(paramString);
      if ((paramInt1 == 0) && (paramInt3 == 0)) {
        MessageRecipient.l(a);
      }
    }
  }
  
  public void b(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    a.removeCallbacks(MessageRecipient.m(a));
    a.postDelayed(MessageRecipient.m(a), 1500L);
    if ((TextUtils.isEmpty(paramString)) && (MessageRecipient.d(a).size() == 0) && (MessageRecipient.j(a) != null)) {
      MessageRecipient.j(a).a(a, a.getSipState());
    }
  }
}

/* Location:
 * Qualified Name:     mx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */