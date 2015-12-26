import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.net.Uri.Builder;
import android.util.Log;

class abw
  implements Runnable
{
  abw(abu paramabu, String paramString, int paramInt) {}
  
  public void run()
  {
    for (;;)
    {
      try
      {
        localObject1 = Uri.parse("content://mms-sms/thread_id").buildUpon();
        ((Uri.Builder)localObject1).appendQueryParameter("recipient", a);
        Uri localUri = ((Uri.Builder)localObject1).build();
        localObject1 = abu.c(c).getContentResolver().query(localUri, abu.b(), null, null, null);
        Log.d(abu.c(), "uri = " + localUri + ", cursor = " + DatabaseUtils.dumpCursorToString((Cursor)localObject1));
        if (localObject1 == null) {
          continue;
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException1)
      {
        Object localObject1;
        long l = -1L;
        localIllegalArgumentException1.printStackTrace();
        continue;
        l = -1L;
        continue;
      }
      try
      {
        if (((Cursor)localObject1).moveToFirst()) {
          l = ((Cursor)localObject1).getLong(0);
        }
      }
      finally
      {
        ((Cursor)localObject1).close();
      }
      try
      {
        ((Cursor)localObject1).close();
        Log.d(abu.c(), "thread_id = " + l);
        localObject1 = new Intent("android.intent.action.SENDTO", Uri.fromParts("smsto", a, null));
        ((Intent)localObject1).putExtra("thread_id", l);
        ((Intent)localObject1).putExtra("conversation_last_imsi", zv.c(b));
        abu.c(c).startActivity((Intent)localObject1);
        return;
      }
      catch (IllegalArgumentException localIllegalArgumentException2)
      {
        continue;
      }
      Log.d(abu.c(), "getThreadId returned no rows!");
      l = -1L;
    }
  }
}

/* Location:
 * Qualified Name:     abw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */