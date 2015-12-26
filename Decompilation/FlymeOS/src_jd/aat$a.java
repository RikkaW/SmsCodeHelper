import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Telephony.Mms;
import android.util.Log;

public class aat$a
  extends aat
{
  private static final String[] b = { "thread_id", "count(*) as msg_count" };
  private static final String[] c = { "_id", "thread_id", "date" };
  private final String d = "MaxMmsMessagesPerThread";
  
  private void a(Context paramContext, long paramLong1, long paramLong2)
  {
    paramLong1 = paramContext.getContentResolver().delete(Telephony.Mms.CONTENT_URI, "thread_id=" + paramLong1 + " AND locked=0 AND date<" + paramLong2, null);
  }
  
  protected void a(Context paramContext, long paramLong, int paramInt)
  {
    if (paramLong == 0L) {}
    do
    {
      for (;;)
      {
        return;
        Cursor localCursor;
        try
        {
          localCursor = paramContext.getContentResolver().query(Telephony.Mms.CONTENT_URI, c, "thread_id=" + paramLong + " AND locked=0", null, "date DESC");
          if (localCursor != null) {}
        }
        finally
        {
          int i;
          long l;
          localCursor = null;
        }
        try
        {
          Log.e("Recycler", "MMS: deleteMessagesForThread got back null cursor");
          if (localCursor != null)
          {
            localCursor.close();
            return;
          }
        }
        finally
        {
          for (;;) {}
        }
      }
      i = localCursor.getCount();
      if (i - paramInt > 0) {
        break;
      }
    } while (localCursor == null);
    localCursor.close();
    return;
    localCursor.move(paramInt);
    l = localCursor.getLong(2);
    if (localCursor != null) {
      localCursor.close();
    }
    a(paramContext, paramLong, l);
    return;
    if (localCursor != null) {
      localCursor.close();
    }
    throw paramContext;
  }
  
  public void a(Context paramContext, Uri paramUri)
  {
    if (!b(paramContext)) {}
    do
    {
      do
      {
        for (;;)
        {
          return;
          try
          {
            paramUri = paramUri.getLastPathSegment();
            paramUri = paramContext.getContentResolver().query(Telephony.Mms.CONTENT_URI, c, "thread_id in (select thread_id from pdu where _id=" + paramUri + ") AND locked=0", null, "date DESC");
            if (paramUri != null) {}
          }
          finally
          {
            int i;
            int j;
            long l1;
            long l2;
            paramUri = null;
          }
          try
          {
            Log.e("Recycler", "MMS: deleteOldMessagesInSameThreadAsMessage got back null cursor");
            if (paramUri != null)
            {
              paramUri.close();
              return;
            }
          }
          finally
          {
            for (;;) {}
          }
        }
        i = paramUri.getCount();
        j = e(paramContext);
        if (i - j > 0) {
          break;
        }
      } while (paramUri == null);
      paramUri.close();
      return;
      paramUri.move(j);
      l1 = paramUri.getLong(2);
      l2 = paramUri.getLong(1);
      if (paramUri != null) {
        paramUri.close();
      }
    } while (l2 == 0L);
    a(paramContext, l2, l1);
    return;
    if (paramUri != null) {
      paramUri.close();
    }
    throw paramContext;
  }
  
  public int e(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("MaxMmsMessagesPerThread", ga.r());
  }
}

/* Location:
 * Qualified Name:     aat.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */