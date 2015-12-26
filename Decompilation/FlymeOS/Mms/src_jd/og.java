import android.content.Context;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.transaction.MessagingNotification.b;

class og
  implements Runnable
{
  og(of paramof) {}
  
  public void run()
  {
    Context localContext = a.a;
    MessagingNotification.b[] arrayOfb;
    if (a.b == 1)
    {
      arrayOfb = new MessagingNotification.b[1];
      arrayOfb[0] = a.c[0];
    }
    for (;;)
    {
      MessagingNotification.b(localContext, arrayOfb);
      gr.a(a.a, false);
      return;
      arrayOfb = a.c;
    }
  }
}

/* Location:
 * Qualified Name:     og
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */