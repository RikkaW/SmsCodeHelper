import android.content.ContentResolver;
import android.content.ContentValues;
import android.provider.Telephony.Sms;
import com.android.mms.ui.NotificationReply;

public class xd
  implements Runnable
{
  public xd(NotificationReply paramNotificationReply, long paramLong, String paramString) {}
  
  public void run()
  {
    try
    {
      zv.d(NotificationReply.e(c));
      zt.c().a(true);
      zt.c().a(a, true);
      ContentValues localContentValues = new ContentValues(3);
      localContentValues.put("thread_id", Long.valueOf(a));
      localContentValues.put("body", b);
      localContentValues.put("type", Integer.valueOf(3));
      localContentValues.put("imsi", zv.e());
      zv.f();
      c.getContentResolver().insert(Telephony.Sms.CONTENT_URI, localContentValues);
      return;
    }
    finally
    {
      zt.c().a(false);
    }
  }
}

/* Location:
 * Qualified Name:     xd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */