import android.content.Context;
import com.android.mms.transaction.MessagingNotification;

final class jz
  implements Runnable
{
  jz(Long[] paramArrayOfLong, Context paramContext) {}
  
  public void run()
  {
    int i = 0;
    while (i < a.length)
    {
      MessagingNotification.c(b, a[i].longValue());
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     jz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */