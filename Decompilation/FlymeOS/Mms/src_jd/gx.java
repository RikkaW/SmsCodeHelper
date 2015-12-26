import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.util.LongSparseArray;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class gx
{
  private static Uri a = Uri.parse("content://mms-sms/canonical-addresses");
  private static Uri b = Uri.parse("content://mms-sms/canonical-address");
  private static gx c;
  private final LongSparseArray<String> d = new LongSparseArray();
  private final Context e;
  
  gx(Context paramContext)
  {
    e = paramContext;
  }
  
  public static gx a()
  {
    return c;
  }
  
  public static String a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getContentResolver().query(ContentUris.withAppendedId(b, Long.parseLong(paramString)), null, null, null, null);
    if (paramContext == null)
    {
      fl.b("Mms/cache", new Object[] { "null Cursor looking up recipient: " + paramString });
      return null;
    }
    try
    {
      if (paramContext.moveToFirst())
      {
        paramString = paramContext.getString(0);
        return paramString;
      }
      return null;
    }
    finally
    {
      paramContext.close();
    }
  }
  
  public static List<gx.a> a(String paramString)
  {
    int i;
    for (;;)
    {
      ArrayList localArrayList;
      long l;
      String str;
      synchronized (c)
      {
        localArrayList = new ArrayList();
        String[] arrayOfString = paramString.split(" ");
        int j = arrayOfString.length;
        i = 0;
        if (i < j) {
          paramString = arrayOfString[i];
        }
      }
      return localArrayList;
    }
  }
  
  public static void a(long paramLong, gq arg2)
  {
    Iterator localIterator = ???.iterator();
    gm localgm;
    String str1;
    while (localIterator.hasNext())
    {
      localgm = (gm)localIterator.next();
      if (localgm.e())
      {
        localgm.b(false);
        paramLong = localgm.j();
        if (paramLong != 0L) {
          str1 = localgm.d();
        }
      }
    }
    for (;;)
    {
      synchronized (c)
      {
        String str2 = (String)cd.get(paramLong);
        if (Log.isLoggable("Mms:app", 2))
        {
          Log.d("Mms/cache", "[RecipientIdCache] updateNumbers: contact=" + localgm + ", wasModified=true, recipientId=" + paramLong);
          Log.d("Mms/cache", "   contact.getNumber=" + str1 + ", sInstance.mCache.get(recipientId)=" + str2);
        }
        if (str1.equalsIgnoreCase(str2)) {
          break label212;
        }
        cd.put(paramLong, str1);
        i = 1;
        if (i == 0) {
          break;
        }
        c.a(paramLong, str1);
      }
      return;
      label212:
      int i = 0;
    }
  }
  
  private void a(long paramLong, String paramString)
  {
    if (Log.isLoggable("Mms:app", 2)) {
      Log.d("Mms/cache", "[RecipientIdCache] updateCanonicalAddressInDb: id=" + paramLong + ", number=" + paramString);
    }
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("address", paramString);
    paramString = new StringBuilder("_id");
    paramString.append('=').append(paramLong);
    Uri localUri = ContentUris.withAppendedId(b, paramLong);
    new gz(this, "updateCanonicalAddressInDb", e.getContentResolver(), localUri, localContentValues, paramString).start();
  }
  
  static void a(Context paramContext)
  {
    c = new gx(paramContext);
    new Thread(new gy(), "RecipientIdCache.init").start();
  }
  
  public static void b()
  {
    if (Log.isLoggable("Mms:threadcache", 2)) {
      fl.a("[RecipientIdCache] fill: begin", new Object[0]);
    }
    Cursor localCursor = ce.getContentResolver().query(a, null, null, null, null);
    if (localCursor == null) {
      Log.w("Mms/cache", "null Cursor in fill()");
    }
    do
    {
      return;
      try
      {
        synchronized (c)
        {
          cd.clear();
          if (localCursor.moveToNext())
          {
            long l = localCursor.getLong(0);
            String str = localCursor.getString(1);
            cd.put(l, str);
          }
        }
      }
      finally
      {
        localCursor.close();
      }
      localCursor.close();
    } while (!Log.isLoggable("Mms:threadcache", 2));
    fl.a("[RecipientIdCache] fill: finished", new Object[0]);
    c();
  }
  
  public static void c()
  {
    synchronized (c)
    {
      Log.d("Mms/cache", "*** Recipient ID cache dump ***");
      int i = 0;
      while (i < cd.size())
      {
        Log.d("Mms/cache", cd.keyAt(i) + ": " + (String)cd.valueAt(i));
        i += 1;
      }
      return;
    }
  }
  
  public void a(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      new Thread(new ha(this)).start();
      return;
    }
    synchronized (c)
    {
      cd.clear();
      return;
    }
  }
  
  public static class a
  {
    public long a;
    public String b;
    
    public a(long paramLong, String paramString)
    {
      a = paramLong;
      b = paramString;
    }
  }
}

/* Location:
 * Qualified Name:     gx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */