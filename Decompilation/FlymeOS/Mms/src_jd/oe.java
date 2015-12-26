import android.content.Context;
import android.util.Log;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.transaction.MessagingNotification.b;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class oe
  implements Runnable
{
  public oe(Context paramContext, int paramInt) {}
  
  public void run()
  {
    Object localObject1 = new ArrayList();
    Object localObject2 = new HashSet(4);
    MessagingNotification.a(a, (Set)localObject2, (List)localObject1);
    MessagingNotification.b(a, (Set)localObject2, (List)localObject1);
    Log.e("Mms:app", "markUnSeenMsgAsRead number is " + ((List)localObject1).size());
    if (((List)localObject1).size() == 0)
    {
      gr.a(a, false);
      return;
    }
    localObject2 = (MessagingNotification.b[])((List)localObject1).toArray(new MessagingNotification.b[((List)localObject1).size()]);
    Context localContext = a;
    localObject1 = localObject2;
    if (b == 1) {
      localObject1 = new MessagingNotification.b[] { localObject2[0] };
    }
    MessagingNotification.a(localContext, (MessagingNotification.b[])localObject1);
    gr.a(a, false);
  }
}

/* Location:
 * Qualified Name:     oe
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */