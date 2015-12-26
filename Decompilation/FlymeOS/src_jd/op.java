import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony.Mms.Outbox;
import android.util.Log;
import com.android.mms.ui.MessagingPreferenceActivity;
import com.google.android.mms.InvalidHeaderValueException;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.pdu.MzEncodedStringValue;
import com.meizu.android.mms.pdu.MzGenericPdu;
import com.meizu.android.mms.pdu.MzPduPersister;
import com.meizu.android.mms.pdu.MzReadRecInd;
import com.meizu.android.mms.util.MzSqliteWrapper;

public class op
  extends ow
  implements Runnable
{
  private Thread a;
  private String p;
  private String q;
  
  public op(Context paramContext, int paramInt, String paramString1, String paramString2, long paramLong)
  {
    super(paramContext, paramInt, paramLong);
    g = Uri.parse(paramString2);
    p = paramString1;
    c = paramString2;
    a(or.a(paramContext));
    b(g);
  }
  
  public void a()
  {
    a = new Thread(this, "ReadRecTransaction");
    a.start();
  }
  
  public String b()
  {
    return q;
  }
  
  public int c()
  {
    return 3;
  }
  
  public void run()
  {
    Log.d("ReadRecTransaction", "ReadRecTransaction: process()");
    Object localObject5 = new MzEncodedStringValue[1];
    Cursor localCursor = MzSqliteWrapper.query(b, b.getContentResolver(), g, new String[] { "m_id", "rr", "_id" }, null, null, null);
    if (localCursor != null) {}
    for (;;)
    {
      try
      {
        Object localObject1;
        if (localCursor.moveToFirst())
        {
          localObject1 = localCursor.getString(0);
          i = localCursor.getInt(1);
          l = localCursor.getLong(2);
          localCursor.close();
          Log.d("ReadRecTransaction", "messageid:" + (String)localObject1 + ",and readreport flag:" + i + ", mSubId = " + f);
          localCursor = MzSqliteWrapper.query(b, b.getContentResolver(), Uri.parse("content://mms/" + l + "/addr"), new String[] { "address", "charset" }, "type = 137", null, null);
          if (localCursor == null) {}
        }
        String str;
        l = 0L;
      }
      finally
      {
        try
        {
          if (localCursor.moveToFirst())
          {
            str = localCursor.getString(0);
            i = localCursor.getInt(1);
            Log.d("ReadRecTransaction", "find address:" + str + ",charset:" + i);
            localObject5[0] = new MzEncodedStringValue(i, MzPduPersister.getBytes(str));
          }
          localCursor.close();
        }
        finally
        {
          localCursor.close();
        }
        try
        {
          localObject1 = new MzReadRecInd(new MzEncodedStringValue("insert-address-token".getBytes()), ((String)localObject1).getBytes(), 18, 128, (MzEncodedStringValue[])localObject5);
          ((MzReadRecInd)localObject1).setDate(System.currentTimeMillis() / 1000L);
          localObject1 = MzPduPersister.getPduPersister(b).persist((MzGenericPdu)localObject1, Telephony.Mms.Outbox.CONTENT_URI, true, MessagingPreferenceActivity.b(b), null);
          localObject5 = new Intent("com.android.mms.transaction.TRANSACION_PROCESSED");
          ((Intent)localObject5).putExtra("subscription", f);
          ((Intent)localObject5).putExtra("bundle_uri", g.toString());
          localObject5 = PendingIntent.getBroadcast(b, 0, (Intent)localObject5, 134217728);
          aau.a(aac.c(f), "sendStoredMultimediaMessage", new Class[] { Uri.class, Bundle.class, PendingIntent.class }, new Object[] { localObject1, null, localObject5 });
          return;
        }
        catch (InvalidHeaderValueException localInvalidHeaderValueException)
        {
          Log.e("ReadRecTransaction", "Invalide header value", localInvalidHeaderValueException);
          f().a(2);
          f().a(g);
          d();
          return;
        }
        catch (MmsException localMmsException)
        {
          Log.e("ReadRecTransaction", "Persist message failed", localMmsException);
          f().a(2);
          f().a(g);
          d();
          return;
        }
        catch (Throwable localThrowable)
        {
          Log.e("ReadRecTransaction", Log.getStackTraceString(localThrowable));
          f().a(2);
          f().a(g);
          d();
          return;
        }
        localObject2 = finally;
        localCursor.close();
      }
      Object localObject4 = null;
      int i = 0;
      continue;
      long l = 0L;
      localObject4 = null;
      i = 0;
    }
  }
}

/* Location:
 * Qualified Name:     op
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */