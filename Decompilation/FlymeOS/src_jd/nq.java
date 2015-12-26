import android.net.Uri;
import com.android.mms.MmsApp;
import com.android.mms.transaction.MessagePopupService;
import com.android.mms.transaction.MessagingNotification;

public class nq
  implements Runnable
{
  public nq(MessagePopupService paramMessagePopupService, boolean paramBoolean) {}
  
  public void run()
  {
    if ((a) || (MmsApp.c().l())) {
      if (!MessagePopupService.g(b)) {
        break label51;
      }
    }
    label51:
    for (Uri localUri = wd.k(b);; localUri = Uri.parse("file:///system/media/audio/ui/msgNotifyIncompose.wav"))
    {
      MessagingNotification.a(b, localUri, 1.0F);
      b.g();
      return;
    }
  }
}

/* Location:
 * Qualified Name:     nq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */