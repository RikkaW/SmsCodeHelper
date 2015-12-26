import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.provider.Telephony.Sms.Conversations;
import android.util.Log;

public class aat$b
  extends aat
{
  private static final String[] b = { "thread_id", "msg_count" };
  private static final String[] c = { "_id", "thread_id", "address", "body", "date", "read", "type", "status" };
  private final String d = "MaxSmsMessagesPerThread";
  
  protected void a(Context paramContext, long paramLong, int paramInt)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    do
    {
      do
      {
        for (;;)
        {
          try
          {
            paramContext = localContentResolver.query(ContentUris.withAppendedId(Telephony.Sms.Conversations.CONTENT_URI, paramLong), c, "locked=0", null, "date DESC");
            if (paramContext != null) {}
          }
          finally
          {
            int i;
            long l;
            paramContext = null;
          }
          try
          {
            Log.e("Recycler", "SMS: deleteMessagesForThread got back null cursor");
            if (paramContext != null) {
              paramContext.close();
            }
            return;
          }
          finally
          {
            for (;;) {}
          }
        }
        i = paramContext.getCount();
        if (i - paramInt > 0) {
          break;
        }
      } while (paramContext == null);
      paramContext.close();
      return;
      paramContext.move(paramInt);
      l = paramContext.getLong(4);
      paramInt = localContentResolver.delete(ContentUris.withAppendedId(Telephony.Sms.Conversations.CONTENT_URI, paramLong), "locked=0 AND date<" + l, null);
      paramLong = paramInt;
    } while (paramContext == null);
    paramContext.close();
    return;
    if (paramContext != null) {
      paramContext.close();
    }
    throw ((Throwable)localObject1);
  }
  
  public int e(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("MaxSmsMessagesPerThread", ga.q());
  }
}

/* Location:
 * Qualified Name:     aat.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */