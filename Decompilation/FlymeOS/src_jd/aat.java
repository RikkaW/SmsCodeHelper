import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Sms.Conversations;
import android.telephony.MzTelephony.MmsSmsExt;
import android.util.Log;

public abstract class aat
{
  public static boolean a = true;
  private static final String[] b = { "sum(message_count) as msg_count" };
  private static aat.b c;
  private static aat.a d;
  private static boolean e = false;
  
  public static aat.b a()
  {
    if (c == null) {
      c = new aat.b();
    }
    return c;
  }
  
  private static void a(long paramLong)
  {
    if (paramLong > 10000L) {}
    for (int i = 1; i != 0; i = 0)
    {
      e = true;
      return;
    }
    e = false;
  }
  
  public static void a(AsyncQueryHandler paramAsyncQueryHandler, int paramInt)
  {
    paramAsyncQueryHandler.cancelOperation(paramInt);
    paramAsyncQueryHandler.startQuery(paramInt, null, MzTelephony.MmsSmsExt.CONTENT_MSGCOUNT_URI, b, null, null, null);
  }
  
  public static void a(Context paramContext)
  {
    a = PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("show_over_limit", true);
  }
  
  public static void a(Context paramContext, boolean paramBoolean)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putBoolean("show_over_limit", paramBoolean).apply();
    a = paramBoolean;
  }
  
  public static void a(Cursor paramCursor)
  {
    long l2 = 0L;
    long l1 = l2;
    if (paramCursor != null) {
      l1 = l2;
    }
    try
    {
      if (paramCursor.moveToFirst()) {
        l1 = paramCursor.getLong(0);
      }
      if (paramCursor != null) {
        paramCursor.close();
      }
      a(l1);
      return;
    }
    finally
    {
      if (paramCursor != null) {
        paramCursor.close();
      }
    }
  }
  
  public static aat.a b()
  {
    if (d == null) {
      d = new aat.a();
    }
    return d;
  }
  
  public static boolean b(Context paramContext)
  {
    return false;
  }
  
  public static void c(Context paramContext)
  {
    if (!b(paramContext)) {
      return;
    }
    a(d(paramContext));
  }
  
  public static boolean c()
  {
    return (e) && (a);
  }
  
  protected static long d(Context paramContext)
  {
    paramContext = paramContext.getContentResolver().query(MzTelephony.MmsSmsExt.CONTENT_MSGCOUNT_URI, b, null, null, "top DESC, date DESC");
    if (paramContext != null)
    {
      paramContext.moveToFirst();
      long l = paramContext.getLong(0);
      paramContext.close();
      return l;
    }
    return 0L;
  }
  
  public void a(Context paramContext, long paramLong)
  {
    if (!b(paramContext)) {
      return;
    }
    a(paramContext, paramLong, e(paramContext));
  }
  
  protected abstract void a(Context paramContext, long paramLong, int paramInt);
  
  public abstract int e(Context paramContext);
  
  public static class a
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
  
  public static class b
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
}

/* Location:
 * Qualified Name:     aat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */