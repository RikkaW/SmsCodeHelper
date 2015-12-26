import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.provider.Telephony.Sms.Inbox;
import android.util.Log;

class aag
  implements Runnable
{
  aag(aad paramaad, String paramString, Context paramContext) {}
  
  public void run()
  {
    Object localObject = new ContentValues(6);
    ((ContentValues)localObject).put("address", "4007883333");
    ((ContentValues)localObject).put("body", a);
    ((ContentValues)localObject).put("date", new Long(System.currentTimeMillis()));
    ((ContentValues)localObject).put("read", Integer.valueOf(0));
    ((ContentValues)localObject).put("seen", Integer.valueOf(0));
    ((ContentValues)localObject).put("type", Integer.valueOf(1));
    localObject = b.getContentResolver().insert(Telephony.Sms.Inbox.CONTENT_URI, (ContentValues)localObject);
    if (localObject != null)
    {
      Log.d("MzAssistantHelper", "asyncInsertAssistantRecvMsg --> insertedUri = " + localObject);
      return;
    }
    Log.d("MzAssistantHelper", "asyncInsertAssistantRecvMsg --> insertedUri = null");
  }
}

/* Location:
 * Qualified Name:     aag
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */