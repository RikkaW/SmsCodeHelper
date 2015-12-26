import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import com.meizu.statsapp.UsageStatsProvider;
import com.meizu.statsapp.UsageStatsProxy.Event;
import com.meizu.statsapp.util.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

final class ajq
{
  private static volatile ajq a;
  private static Object e = new Object();
  private Context b;
  private boolean c;
  private UsageStatsProvider d;
  
  private ajq(Context paramContext, boolean paramBoolean)
  {
    b = paramContext;
    c = paramBoolean;
    if (c)
    {
      d = new UsageStatsProvider(b);
      d.onCreate();
    }
  }
  
  private int a(ArrayList<ContentProviderOperation> paramArrayList)
  {
    int j = 0;
    int i = 0;
    try
    {
      boolean bool = c;
      if (bool) {}
      int k;
      for (;;)
      {
        try
        {
          paramArrayList = d.applyBatch(paramArrayList);
          k = j;
          if (paramArrayList == null) {
            break;
          }
          k = j;
          if (paramArrayList.length <= 0) {
            break;
          }
          int m = paramArrayList.length;
          j = 0;
          k = i;
          if (j >= m) {
            break;
          }
          k = count.intValue();
          j += 1;
          i = k + i;
          continue;
        }
        catch (OperationApplicationException paramArrayList)
        {
          paramArrayList.printStackTrace();
          paramArrayList = null;
          continue;
        }
        try
        {
          paramArrayList = b.getContentResolver().applyBatch("com.meizu.usagestats", paramArrayList);
        }
        catch (RemoteException paramArrayList)
        {
          paramArrayList.printStackTrace();
          paramArrayList = null;
        }
        catch (OperationApplicationException paramArrayList)
        {
          paramArrayList.printStackTrace();
          paramArrayList = null;
        }
      }
      return k;
    }
    finally {}
  }
  
  public static ajq a(Context paramContext, boolean paramBoolean)
  {
    if (a == null) {}
    synchronized (e)
    {
      if (a == null) {
        a = new ajq(paramContext, paramBoolean);
      }
      return a;
    }
  }
  
  public static UsageStatsProxy.Event a(Cursor paramCursor)
  {
    if (paramCursor == null) {
      return null;
    }
    UsageStatsProxy.Event localEvent = new UsageStatsProxy.Event();
    localEvent.b(paramCursor.getInt(paramCursor.getColumnIndex("_id")));
    localEvent.a(paramCursor.getString(paramCursor.getColumnIndex("name")));
    localEvent.a(paramCursor.getInt(paramCursor.getColumnIndex("type")));
    localEvent.b(paramCursor.getString(paramCursor.getColumnIndex("sessionid")));
    localEvent.c(paramCursor.getString(paramCursor.getColumnIndex("package")));
    localEvent.d(paramCursor.getString(paramCursor.getColumnIndex("page")));
    localEvent.a(paramCursor.getLong(paramCursor.getColumnIndex("time")));
    String str = paramCursor.getString(paramCursor.getColumnIndex("properties"));
    if (!Utils.isEmpty(str)) {}
    for (;;)
    {
      try
      {
        localEvent.a(new JSONObject(str));
        localEvent.e(paramCursor.getString(paramCursor.getColumnIndex("network")));
        localEvent.b(paramCursor.getLong(paramCursor.getColumnIndex("channel")));
        localEvent.f(paramCursor.getString(paramCursor.getColumnIndex("flyme_version")));
        return localEvent;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        continue;
      }
      localEvent.a(new JSONObject());
    }
  }
  
  public static ContentValues b(UsageStatsProxy.Event paramEvent)
  {
    if (paramEvent == null) {
      return null;
    }
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("name", paramEvent.a());
    localContentValues.put("type", Integer.valueOf(paramEvent.b()));
    localContentValues.put("package", paramEvent.e());
    localContentValues.put("sessionid", paramEvent.d());
    localContentValues.put("time", Long.valueOf(paramEvent.c()));
    if (!Utils.isEmpty(paramEvent.f())) {
      localContentValues.put("page", paramEvent.f());
    }
    String str = paramEvent.h();
    if (!Utils.isEmpty(str)) {
      localContentValues.put("properties", str);
    }
    localContentValues.put("network", paramEvent.j());
    localContentValues.put("channel", Long.valueOf(paramEvent.k()));
    localContentValues.put("flyme_version", paramEvent.l());
    return localContentValues;
  }
  
  /* Error */
  public int a()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: iconst_0
    //   6: invokevirtual 222	ajq:a	(I)Landroid/database/Cursor;
    //   9: astore_3
    //   10: aload_3
    //   11: ifnonnull +7 -> 18
    //   14: aload_0
    //   15: monitorexit
    //   16: iload_1
    //   17: ireturn
    //   18: aload_3
    //   19: invokeinterface 225 1 0
    //   24: istore_2
    //   25: iload_2
    //   26: istore_1
    //   27: aload_3
    //   28: invokeinterface 228 1 0
    //   33: goto -19 -> 14
    //   36: astore_3
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_3
    //   40: athrow
    //   41: astore 4
    //   43: aload 4
    //   45: invokevirtual 229	java/lang/Exception:printStackTrace	()V
    //   48: aload_3
    //   49: invokeinterface 228 1 0
    //   54: goto -40 -> 14
    //   57: astore 4
    //   59: aload_3
    //   60: invokeinterface 228 1 0
    //   65: aload 4
    //   67: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	68	0	this	ajq
    //   1	26	1	i	int
    //   24	2	2	j	int
    //   9	19	3	localCursor	Cursor
    //   36	24	3	localObject1	Object
    //   41	3	4	localException	Exception
    //   57	9	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   4	10	36	finally
    //   27	33	36	finally
    //   48	54	36	finally
    //   59	68	36	finally
    //   18	25	41	java/lang/Exception
    //   18	25	57	finally
    //   43	48	57	finally
  }
  
  public int a(Collection<UsageStatsProxy.Event> paramCollection)
  {
    if (paramCollection != null) {}
    for (;;)
    {
      int j;
      try
      {
        i = paramCollection.size();
        if (i < 1)
        {
          j = 0;
          return j;
        }
        Uri localUri = Uri.parse("content://com.meizu.usagestats/event");
        ArrayList localArrayList = new ArrayList();
        paramCollection = paramCollection.iterator();
        k = 0;
        i = 0;
        if (paramCollection.hasNext())
        {
          UsageStatsProxy.Event localEvent = (UsageStatsProxy.Event)paramCollection.next();
          k += 1;
          ContentProviderOperation.Builder localBuilder = ContentProviderOperation.newDelete(localUri);
          localBuilder.withSelection("_id=?", new String[] { String.valueOf(localEvent.i()) });
          localArrayList.add(localBuilder.build());
          if (k > 50)
          {
            j = a(localArrayList);
            localArrayList.clear();
            j += i;
            i = 0;
            break label182;
          }
        }
        else
        {
          j = i;
          if (localArrayList.size() <= 0) {
            continue;
          }
          j = a(localArrayList);
          j = i + j;
          continue;
        }
        j = i;
      }
      finally {}
      int i = k;
      label182:
      int k = i;
      i = j;
    }
  }
  
  /* Error */
  public Cursor a(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iload_1
    //   3: ifle +56 -> 59
    //   6: new 297	java/lang/StringBuilder
    //   9: dup
    //   10: invokespecial 298	java/lang/StringBuilder:<init>	()V
    //   13: ldc_w 300
    //   16: invokevirtual 304	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: iload_1
    //   20: invokestatic 274	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   23: invokevirtual 304	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: invokevirtual 307	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   29: invokestatic 243	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   32: astore_2
    //   33: aload_0
    //   34: getfield 27	ajq:c	Z
    //   37: ifeq +31 -> 68
    //   40: aload_0
    //   41: getfield 34	ajq:d	Lcom/meizu/statsapp/UsageStatsProvider;
    //   44: aload_2
    //   45: aconst_null
    //   46: aconst_null
    //   47: aconst_null
    //   48: ldc_w 309
    //   51: invokevirtual 313	com/meizu/statsapp/UsageStatsProvider:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   54: astore_2
    //   55: aload_0
    //   56: monitorexit
    //   57: aload_2
    //   58: areturn
    //   59: ldc -19
    //   61: invokestatic 243	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   64: astore_2
    //   65: goto -32 -> 33
    //   68: aload_0
    //   69: getfield 25	ajq:b	Landroid/content/Context;
    //   72: invokevirtual 68	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   75: aload_2
    //   76: aconst_null
    //   77: aconst_null
    //   78: aconst_null
    //   79: ldc_w 309
    //   82: invokevirtual 314	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   85: astore_2
    //   86: goto -31 -> 55
    //   89: astore_2
    //   90: aload_0
    //   91: monitorexit
    //   92: aload_2
    //   93: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	94	0	this	ajq
    //   0	94	1	paramInt	int
    //   32	54	2	localObject1	Object
    //   89	4	2	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   6	33	89	finally
    //   33	55	89	finally
    //   59	65	89	finally
    //   68	86	89	finally
  }
  
  /* Error */
  public Cursor a(int paramInt, long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iload_1
    //   3: ifle +72 -> 75
    //   6: new 297	java/lang/StringBuilder
    //   9: dup
    //   10: invokespecial 298	java/lang/StringBuilder:<init>	()V
    //   13: ldc_w 300
    //   16: invokevirtual 304	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: iload_1
    //   20: invokestatic 274	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   23: invokevirtual 304	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: invokevirtual 307	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   29: invokestatic 243	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   32: astore 4
    //   34: aload_0
    //   35: getfield 27	ajq:c	Z
    //   38: ifeq +47 -> 85
    //   41: aload_0
    //   42: getfield 34	ajq:d	Lcom/meizu/statsapp/UsageStatsProvider;
    //   45: aload 4
    //   47: aconst_null
    //   48: ldc_w 317
    //   51: iconst_1
    //   52: anewarray 269	java/lang/String
    //   55: dup
    //   56: iconst_0
    //   57: lload_2
    //   58: invokestatic 320	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   61: aastore
    //   62: ldc_w 309
    //   65: invokevirtual 313	com/meizu/statsapp/UsageStatsProvider:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   68: astore 4
    //   70: aload_0
    //   71: monitorexit
    //   72: aload 4
    //   74: areturn
    //   75: ldc -19
    //   77: invokestatic 243	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   80: astore 4
    //   82: goto -48 -> 34
    //   85: aload_0
    //   86: getfield 25	ajq:b	Landroid/content/Context;
    //   89: invokevirtual 68	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   92: aload 4
    //   94: aconst_null
    //   95: ldc_w 317
    //   98: iconst_1
    //   99: anewarray 269	java/lang/String
    //   102: dup
    //   103: iconst_0
    //   104: lload_2
    //   105: invokestatic 320	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   108: aastore
    //   109: ldc_w 309
    //   112: invokevirtual 314	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   115: astore 4
    //   117: goto -47 -> 70
    //   120: astore 4
    //   122: aload_0
    //   123: monitorexit
    //   124: aload 4
    //   126: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	127	0	this	ajq
    //   0	127	1	paramInt	int
    //   0	127	2	paramLong	long
    //   32	84	4	localObject1	Object
    //   120	5	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   6	34	120	finally
    //   34	70	120	finally
    //   75	82	120	finally
    //   85	117	120	finally
  }
  
  /* Error */
  public void a(UsageStatsProxy.Event paramEvent)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnonnull +6 -> 9
    //   6: aload_0
    //   7: monitorexit
    //   8: return
    //   9: ldc -19
    //   11: invokestatic 243	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   14: astore_2
    //   15: aload_1
    //   16: invokestatic 325	ajq:b	(Lcom/meizu/statsapp/UsageStatsProxy$Event;)Landroid/content/ContentValues;
    //   19: astore_1
    //   20: aload_0
    //   21: getfield 27	ajq:c	Z
    //   24: ifeq +34 -> 58
    //   27: aload_0
    //   28: getfield 34	ajq:d	Lcom/meizu/statsapp/UsageStatsProvider;
    //   31: aload_2
    //   32: aload_1
    //   33: invokevirtual 329	com/meizu/statsapp/UsageStatsProvider:insert	(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;
    //   36: pop
    //   37: goto -31 -> 6
    //   40: astore_1
    //   41: aload_1
    //   42: invokevirtual 330	android/database/sqlite/SQLiteFullException:printStackTrace	()V
    //   45: aload_0
    //   46: invokevirtual 331	ajq:b	()I
    //   49: pop
    //   50: goto -44 -> 6
    //   53: astore_1
    //   54: aload_0
    //   55: monitorexit
    //   56: aload_1
    //   57: athrow
    //   58: aload_0
    //   59: getfield 25	ajq:b	Landroid/content/Context;
    //   62: invokevirtual 68	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   65: aload_2
    //   66: aload_1
    //   67: invokevirtual 332	android/content/ContentResolver:insert	(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;
    //   70: pop
    //   71: goto -65 -> 6
    //   74: astore_1
    //   75: aload_1
    //   76: invokevirtual 229	java/lang/Exception:printStackTrace	()V
    //   79: goto -73 -> 6
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	82	0	this	ajq
    //   0	82	1	paramEvent	UsageStatsProxy.Event
    //   14	52	2	localUri	Uri
    // Exception table:
    //   from	to	target	type
    //   20	37	40	android/database/sqlite/SQLiteFullException
    //   58	71	40	android/database/sqlite/SQLiteFullException
    //   9	20	53	finally
    //   20	37	53	finally
    //   41	50	53	finally
    //   58	71	53	finally
    //   75	79	53	finally
    //   20	37	74	java/lang/Exception
    //   58	71	74	java/lang/Exception
  }
  
  /* Error */
  public int b()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc_w 334
    //   5: invokestatic 243	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   8: astore_2
    //   9: aload_0
    //   10: getfield 27	ajq:c	Z
    //   13: ifeq +18 -> 31
    //   16: aload_0
    //   17: getfield 34	ajq:d	Lcom/meizu/statsapp/UsageStatsProvider;
    //   20: aload_2
    //   21: aconst_null
    //   22: aconst_null
    //   23: invokevirtual 338	com/meizu/statsapp/UsageStatsProvider:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   26: istore_1
    //   27: aload_0
    //   28: monitorexit
    //   29: iload_1
    //   30: ireturn
    //   31: aload_0
    //   32: getfield 25	ajq:b	Landroid/content/Context;
    //   35: invokevirtual 68	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   38: aload_2
    //   39: aconst_null
    //   40: aconst_null
    //   41: invokevirtual 339	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   44: istore_1
    //   45: goto -18 -> 27
    //   48: astore_2
    //   49: aload_0
    //   50: monitorexit
    //   51: aload_2
    //   52: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	53	0	this	ajq
    //   26	19	1	i	int
    //   8	31	2	localUri	Uri
    //   48	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	27	48	finally
    //   31	45	48	finally
  }
}

/* Location:
 * Qualified Name:     ajq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */