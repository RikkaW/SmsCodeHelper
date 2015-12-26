import android.content.ContentResolver;
import android.content.ContentValues;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Sms;
import com.android.mms.transaction.MessagePopupService;
import com.android.mms.transaction.MessagePopupService.b;
import com.android.mms.transaction.MessagingNotification;

public class nr
  implements Runnable
{
  public nr(MessagePopupService paramMessagePopupService, MessagePopupService.b paramb) {}
  
  public void run()
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("read", Integer.valueOf(1));
    localContentValues.put("seen", Integer.valueOf(1));
    String str;
    if (a.c) {
      str = "read = 0 AND type = 1 AND _id = " + a.d;
    }
    for (int i = b.getContentResolver().update(Telephony.Sms.CONTENT_URI, localContentValues, str, null);; i = b.getContentResolver().update(Telephony.Mms.CONTENT_URI, localContentValues, str, null))
    {
      if (i > 0)
      {
        a.l = true;
        MessagingNotification.b(b, a.b, false, false);
      }
      return;
      str = "read = 0 AND msg_box = 1 AND _id = " + a.d;
    }
  }
}

/* Location:
 * Qualified Name:     nr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */