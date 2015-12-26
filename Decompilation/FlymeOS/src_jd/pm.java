import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.Threads;
import android.util.Log;
import com.android.mms.ui.MessagingPreferenceActivity;
import com.meizu.android.mms.pdu.MzEncodedStringValue;
import com.meizu.android.mms.pdu.MzGenericPdu;
import com.meizu.android.mms.pdu.MzNotificationInd;
import com.meizu.android.mms.pdu.MzPduComposer;
import com.meizu.android.mms.pdu.MzPduParser;
import com.meizu.android.mms.pdu.MzPduPersister;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class pm
  extends ow
  implements Runnable
{
  static final String[] a = { "ct_l", "locked", "m_size" };
  private long p;
  
  public pm(Context paramContext, int paramInt1, oz paramoz, String paramString, int paramInt2)
  {
    super(paramContext, paramInt1, paramoz);
    g = Uri.parse(paramString);
    try
    {
      paramContext = (MzNotificationInd)MzPduPersister.getPduPersister(b).load(g);
      c = a(paramContext);
      p = paramContext.getMessageSize();
      h = paramInt2;
      l = a(g);
      return;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        Log.e("SnsRetrieveTransaction", "Exception", paramContext);
      }
    }
  }
  
  private String a(MzNotificationInd paramMzNotificationInd)
  {
    Class localClass = aau.c("org.apache.harmony.security.utils.Array");
    try
    {
      paramMzNotificationInd = String.valueOf(localClass.getDeclaredMethod("getBytesAsString", new Class[] { byte[].class }).invoke(localClass, new Object[] { paramMzNotificationInd.getContentLocation() }));
      return paramMzNotificationInd;
    }
    catch (NoSuchMethodException paramMzNotificationInd)
    {
      paramMzNotificationInd.printStackTrace();
      return null;
    }
    catch (InvocationTargetException paramMzNotificationInd)
    {
      paramMzNotificationInd.printStackTrace();
      return null;
    }
    catch (IllegalAccessException paramMzNotificationInd)
    {
      paramMzNotificationInd.printStackTrace();
    }
    return null;
  }
  
  public void a()
  {
    new Thread(this, "RetrieveTransaction").start();
  }
  
  public int c()
  {
    return 1;
  }
  
  public void run()
  {
    for (;;)
    {
      try
      {
        zn.a().a(g, 129, -1);
        l = p;
        localObject7 = b.getContentResolver().query(g, new String[] { "_id", "file_link", "m_size" }, null, null, null);
        if (localObject7 == null) {
          continue;
        }
      }
      catch (Throwable localThrowable)
      {
        Log.e("SnsRetrieveTransaction", Log.getStackTraceString(localThrowable));
        if (d.a() == 1) {
          continue;
        }
        d.a(2);
        d.a(g);
        Log.d("SnsRetrieveTransaction", "RetrieveTransaction -> [ " + m + " ] GET PDU FAILED!");
        localObject3 = new ContentValues(1);
        ((ContentValues)localObject3).put("t_rate", Long.valueOf(m()));
        Log.d("SnsRetrieveTransaction", "failed, RetrieveTransaction progress：" + m() + ", mUri = " + g);
        b.getContentResolver().update(g, (ContentValues)localObject3, null, null);
        d();
        return;
        localObject3 = "null pdu";
        continue;
        localMzPduPersister = MzPduPersister.getPduPersister(b);
        localObject7 = "mimetype";
        localCursor = b.getContentResolver().query(g, new String[] { "_id", "m_size", "file_link", "slideshow_description" }, null, null, null);
        if (localCursor == null) {
          continue;
        }
      }
      finally
      {
        try
        {
          MzGenericPdu localMzGenericPdu;
          MzPduPersister localMzPduPersister;
          if ((localCursor.getCount() <= 0) || (!localCursor.moveToFirst())) {
            continue;
          }
          l = localCursor.getLong(localCursor.getColumnIndexOrThrow("m_size"));
          localObject7 = localCursor.getString(localCursor.getColumnIndexOrThrow("file_link"));
          Object localObject3 = localCursor.getString(localCursor.getColumnIndexOrThrow("slideshow_description"));
          localCursor.close();
          localObject8 = new ContentValues(4);
          ((ContentValues)localObject8).put("m_size", Long.valueOf(l));
          ((ContentValues)localObject8).put("file_link", (String)localObject7);
          ((ContentValues)localObject8).put("protocol", Integer.valueOf(h));
          ((ContentValues)localObject8).put("slideshow_description", (String)localObject3);
          ((ContentValues)localObject8).put("t_rate", Long.valueOf(m()));
          ((ContentValues)localObject8).put("association_id", new Long(System.currentTimeMillis()));
          ((ContentValues)localObject8).put("imsi", zv.c(0));
          Log.d("SnsRetrieveTransaction", "Success, NotificationTransaction progress : " + m() + " and size: " + l);
          localObject3 = localMzPduPersister.persist(localMzGenericPdu, Telephony.Mms.Inbox.CONTENT_URI, true, MessagingPreferenceActivity.b(b), null, h, (ContentValues)localObject8);
          b.getContentResolver().delete(g, "m_type = 130", null);
          b.getContentResolver().delete(Telephony.Threads.OBSOLETE_THREADS_URI, null, null);
          Log.v("SnsRetrieveTransaction", "NotificationTransaction received new mms message: " + localObject3);
          g = ((Uri)localObject3);
          continue;
        }
        finally
        {
          Cursor localCursor;
          localCursor.close();
        }
        localObject4 = finally;
        if (d.a() == 1) {
          continue;
        }
        d.a(2);
        d.a(g);
        Log.d("SnsRetrieveTransaction", "RetrieveTransaction -> [ " + m + " ] GET PDU FAILED!");
        Object localObject7 = new ContentValues(1);
        ((ContentValues)localObject7).put("t_rate", Long.valueOf(m()));
        Log.d("SnsRetrieveTransaction", "failed, RetrieveTransaction progress：" + m() + ", mUri = " + g);
        b.getContentResolver().update(g, (ContentValues)localObject7, null, null);
        d();
        throw ((Throwable)localObject4);
        Log.d("SnsRetrieveTransaction", "RetrieveTransaction -> [ " + m + " ] GET PDU SUCCESS!");
        continue;
        Log.d("SnsRetrieveTransaction", "RetrieveTransaction -> [ " + m + " ] GET PDU SUCCESS!");
        continue;
        Log.d("SnsRetrieveTransaction", "RetrieveTransaction -> [ " + m + " ] GET PDU SUCCESS!");
        continue;
        Object localObject8 = localObject5;
        long l = 0L;
        Object localObject6 = localObject7;
        localObject7 = localObject8;
        continue;
        localObject8 = localObject6;
        l = 0L;
        localObject6 = localObject7;
        localObject7 = localObject8;
        continue;
        localObject6 = "";
        continue;
        localObject6 = "";
        continue;
      }
      try
      {
        if ((((Cursor)localObject7).getCount() > 0) && (((Cursor)localObject7).moveToFirst()))
        {
          Object localObject1 = ((Cursor)localObject7).getString(((Cursor)localObject7).getColumnIndexOrThrow("file_link"));
          ((Cursor)localObject7).getLong(((Cursor)localObject7).getColumnIndexOrThrow("m_size"));
          ((Cursor)localObject7).close();
          Log.d("SnsRetrieveTransaction", "SnsRetrieveTransaction -> Send PDU start : Uri = " + g.toString());
          localObject7 = (MzNotificationInd)MzPduPersister.getPduPersister(b).load(g);
          localObject8 = new MzPduComposer(b, (MzGenericPdu)localObject7, h);
          localObject7 = abh.a().a(this.l, ((MzPduComposer)localObject8).make(), ((MzNotificationInd)localObject7).getSubject().getString(), (String)localObject1, p);
          Log.d("SnsRetrieveTransaction", "SnsRetrieveTransaction -> End PDU start  : Uri = " + g.toString());
          if (localObject7 != null)
          {
            localMzGenericPdu = new MzPduParser((byte[])localObject7, h).parse();
            if ((localMzGenericPdu != null) && (localMzGenericPdu.getMessageType() == 132)) {
              continue;
            }
            localObject7 = new StringBuilder().append("Invalid M-RETRIEVE.CONF PDU. ");
            if (localMzGenericPdu != null)
            {
              localObject1 = "message type: " + localMzGenericPdu.getMessageType();
              Log.e("SnsRetrieveTransaction", (String)localObject1);
              d.a(2);
            }
          }
          else
          {
            if (d.a() == 1) {
              continue;
            }
            d.a(2);
            d.a(g);
            Log.d("SnsRetrieveTransaction", "RetrieveTransaction -> [ " + m + " ] GET PDU FAILED!");
            localObject1 = new ContentValues(1);
            ((ContentValues)localObject1).put("t_rate", Long.valueOf(m()));
            Log.d("SnsRetrieveTransaction", "failed, RetrieveTransaction progress：" + m() + ", mUri = " + g);
            b.getContentResolver().update(g, (ContentValues)localObject1, null, null);
            d();
            return;
          }
        }
      }
      finally
      {
        ((Cursor)localObject7).close();
      }
    }
  }
}

/* Location:
 * Qualified Name:     pm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */