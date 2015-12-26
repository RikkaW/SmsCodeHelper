import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.Telephony.Threads;
import android.util.Log;
import com.android.mms.transaction.MmsSystemEventReceiver;

public class oj
  implements Runnable
{
  public oj(MmsSystemEventReceiver paramMmsSystemEventReceiver, Context paramContext) {}
  
  public void run()
  {
    wd.l(a);
    Object localObject1 = Telephony.Threads.CONTENT_URI.buildUpon().appendQueryParameter("simple", "true").build();
    localObject1 = a.getContentResolver().query((Uri)localObject1, new String[] { "_id", "recipient_ids", "message_count" }, "recipient_ids = '' ", null, null);
    if (localObject1 != null) {}
    try
    {
      int j = ((Cursor)localObject1).getCount();
      if ((j > 0) && (((Cursor)localObject1).moveToFirst()))
      {
        int i = 0;
        while (i < j)
        {
          a.getContentResolver().delete(ContentUris.withAppendedId(Telephony.Threads.CONTENT_URI, ((Cursor)localObject1).getInt(0)), null, null);
          Log.e("MmsSystemEventReceiver", "del draft thread " + ((Cursor)localObject1).getInt(0));
          ((Cursor)localObject1).moveToNext();
          i += 1;
        }
      }
      return;
    }
    finally
    {
      ((Cursor)localObject1).close();
    }
  }
}

/* Location:
 * Qualified Name:     oj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */