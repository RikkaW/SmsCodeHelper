import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.provider.Telephony.Sms.Inbox;
import android.util.Log;

final class aaf
  implements Runnable
{
  aaf(Context paramContext) {}
  
  public void run()
  {
    if (aad.b(a))
    {
      Object localObject = a.getString(2131493938);
      ContentValues localContentValues = new ContentValues(5);
      localContentValues.put("address", "4007883333");
      localContentValues.put("body", (String)localObject);
      localContentValues.put("date", new Long(System.currentTimeMillis()));
      localContentValues.put("read", Integer.valueOf(0));
      localContentValues.put("seen", Integer.valueOf(0));
      localContentValues.put("uuid", "43f66f568c0a44f678meizuassistant");
      localObject = a.getContentResolver().insert(Telephony.Sms.Inbox.CONTENT_URI, localContentValues);
      if (localObject != null)
      {
        Log.d("MzAssistantHelper", "asyncCreateMZAssistant --> insertedUri = " + localObject);
        ga.b(a, false);
      }
    }
    else
    {
      return;
    }
    Log.d("MzAssistantHelper", "asyncCreateMZAssistant --> insertedUri = null");
  }
}

/* Location:
 * Qualified Name:     aaf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */