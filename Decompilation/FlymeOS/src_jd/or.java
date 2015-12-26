import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.Mms.Outbox;
import android.provider.Telephony.MmsSms.PendingMessages;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.transaction.TransactionService;
import com.meizu.android.mms.pdu.MzPduPersister;

public class or
  implements oo
{
  private static or c;
  private final Context a;
  private final ContentResolver b;
  
  private or(Context paramContext)
  {
    a = paramContext;
    b = paramContext.getContentResolver();
  }
  
  private int a(long paramLong)
  {
    Cursor localCursor = b.query(Telephony.Mms.Outbox.CONTENT_URI, null, "_id=" + paramLong, null, null);
    if (localCursor == null) {
      return 0;
    }
    for (;;)
    {
      try
      {
        if (localCursor.moveToFirst())
        {
          i = localCursor.getInt(localCursor.getColumnIndexOrThrow("resp_st"));
          localCursor.close();
          if (i != 0) {
            Log.e("RetryScheduler", "Response status is: " + i);
          }
          return i;
        }
      }
      finally
      {
        localCursor.close();
      }
      int i = 0;
    }
  }
  
  public static or a(Context paramContext)
  {
    if (c == null) {
      c = new or(paramContext);
    }
    return c;
  }
  
  public static void a(Context paramContext, Uri paramUri)
  {
    long l = ContentUris.parseId(paramUri);
    paramUri = Telephony.MmsSms.PendingMessages.CONTENT_URI.buildUpon();
    paramUri.appendQueryParameter("protocol", "mms");
    paramUri = paramContext.getContentResolver().query(paramUri.build(), null, "err_type < ? AND due_time <= ? AND msg_id = ?", new String[] { String.valueOf(10), String.valueOf(Long.MAX_VALUE), String.valueOf(l) }, "due_time");
    if (paramUri != null) {
      try
      {
        if (paramUri.moveToNext())
        {
          l = paramUri.getLong(paramUri.getColumnIndexOrThrow("due_time"));
          PendingIntent localPendingIntent = PendingIntent.getService(paramContext, 0, new Intent("android.intent.action.ACTION_ONALARM", null, paramContext, TransactionService.class), 1073741824);
          ((AlarmManager)paramContext.getSystemService("alarm")).set(1, l, localPendingIntent);
          Log.v("RetryScheduler", "Next retry is scheduled at" + (l - System.currentTimeMillis()) + "ms from now");
        }
        return;
      }
      finally
      {
        paramUri.close();
      }
    }
  }
  
  private void a(Uri paramUri)
  {
    long l1 = ContentUris.parseId(paramUri);
    Object localObject1 = Telephony.MmsSms.PendingMessages.CONTENT_URI.buildUpon();
    ((Uri.Builder)localObject1).appendQueryParameter("protocol", "mms");
    ((Uri.Builder)localObject1).appendQueryParameter("message", String.valueOf(l1));
    localObject1 = b.query(((Uri.Builder)localObject1).build(), null, null, null, null);
    if (localObject1 != null) {}
    int i2;
    Object localObject2;
    try
    {
      if ((((Cursor)localObject1).getCount() != 1) || (!((Cursor)localObject1).moveToFirst())) {
        break label453;
      }
      i2 = ((Cursor)localObject1).getInt(((Cursor)localObject1).getColumnIndexOrThrow("msg_type"));
      i1 = ((Cursor)localObject1).getInt(((Cursor)localObject1).getColumnIndexOrThrow("retry_index")) + 1;
      n = 1;
      localObject2 = new no(a, i1);
      localContentValues = new ContentValues(4);
      l3 = System.currentTimeMillis();
      if (i2 != 130) {
        break label461;
      }
      k = 1;
    }
    finally
    {
      try
      {
        int i1;
        int n;
        ContentValues localContentValues;
        long l3;
        int k;
        int j;
        int i3;
        if (((Cursor)localObject2).moveToFirst()) {
          l1 = ((Cursor)localObject2).getLong(0);
        }
        ((Cursor)localObject2).close();
        l2 = l1;
        if (l2 != -1L) {
          MessagingNotification.b(a, l2);
        }
        zn.a().a(paramUri, 130, -1);
        i = 10;
      }
      finally
      {
        ((Cursor)localObject2).close();
      }
      ((Cursor)localObject1).close();
    }
    j = 1;
    i3 = a(l1);
    int i = 0;
    if (k == 0) {
      switch (i3)
      {
      case 132: 
        label244:
        if ((i != 0) || (wd.b(i3)))
        {
          zn.a().b(i);
          j = 0;
        }
        label265:
        if (MmsApp.b)
        {
          Log.d("RetryScheduler", "add for CT,Mms don't retry.");
          j = 0;
        }
        if ((i1 < ((no)localObject2).a()) && (j != 0))
        {
          l1 = ((no)localObject2).b() + l3;
          Log.v("RetryScheduler", "scheduleRetry: retry for " + paramUri + " is scheduled at " + (l1 - System.currentTimeMillis()) + "ms from now");
          localContentValues.put("due_time", Long.valueOf(l1));
        }
        break;
      }
    }
    for (i = n;; i = 10)
    {
      localContentValues.put("err_type", Integer.valueOf(i));
      localContentValues.put("retry_index", Integer.valueOf(i1));
      localContentValues.put("last_try", Long.valueOf(l3));
      l1 = ((Cursor)localObject1).getLong(((Cursor)localObject1).getColumnIndexOrThrow("_id"));
      b.update(Telephony.MmsSms.PendingMessages.CONTENT_URI, localContentValues, "_id=" + l1, null);
      label453:
      ((Cursor)localObject1).close();
      return;
      label461:
      k = 0;
      while (i2 != 135)
      {
        m = 0;
        break;
        i = 2131492972;
        break label244;
        i = 2131493112;
        break label244;
        i = 2131493272;
        break label244;
        i = 2131493110;
        break label244;
        if (b(l1) != 228) {
          break label265;
        }
        zn.a().b(2131493110);
        a.getContentResolver().delete(paramUri, null, null);
        ((Cursor)localObject1).close();
        return;
        long l2;
        if (k != 0)
        {
          localObject2 = a.getContentResolver().query(paramUri, new String[] { "thread_id" }, null, null, null);
          l1 = -1L;
          l2 = l1;
          if (localObject2 == null) {}
        }
        localObject2 = new ContentValues(1);
        if (i2 == 128) {
          ((ContentValues)localObject2).put("resp_st", Integer.valueOf(9527));
        }
        for (;;)
        {
          a.getContentResolver().update(paramUri, (ContentValues)localObject2, null, null);
          if (m != 0) {
            break;
          }
          MessagingNotification.a(a, true);
          break;
          ((ContentValues)localObject2).put("read", Integer.valueOf(0));
        }
      }
      int m = 1;
      break;
      break label244;
    }
  }
  
  private int b(long paramLong)
  {
    Cursor localCursor = b.query(Telephony.Mms.Inbox.CONTENT_URI, null, "_id=" + paramLong, null, null);
    for (;;)
    {
      try
      {
        if (localCursor.moveToFirst())
        {
          i = localCursor.getInt(localCursor.getColumnIndexOrThrow("resp_st"));
          localCursor.close();
          if (i != 0) {
            Log.v("RetryScheduler", "Retrieve status is: " + i);
          }
          return i;
        }
      }
      finally
      {
        localCursor.close();
      }
      int i = 0;
    }
  }
  
  public static void b(Context paramContext)
  {
    Cursor localCursor = MzPduPersister.getPduPersister(paramContext).getPendingMessages(Long.MAX_VALUE);
    if (localCursor != null) {
      try
      {
        if (localCursor.moveToNext())
        {
          long l = localCursor.getLong(localCursor.getColumnIndexOrThrow("due_time"));
          PendingIntent localPendingIntent = PendingIntent.getService(paramContext, 0, new Intent("android.intent.action.ACTION_ONALARM", null, paramContext, TransactionService.class), 1073741824);
          ((AlarmManager)paramContext.getSystemService("alarm")).set(1, l, localPendingIntent);
          Log.v("RetryScheduler", "Next retry is scheduled at" + (l - System.currentTimeMillis()) + "ms from now");
        }
        return;
      }
      finally
      {
        localCursor.close();
      }
    }
  }
  
  /* Error */
  public void a(on paramon)
  {
    // Byte code:
    //   0: aload_1
    //   1: checkcast 315	ow
    //   4: astore_3
    //   5: ldc 78
    //   7: new 37	java/lang/StringBuilder
    //   10: dup
    //   11: invokespecial 38	java/lang/StringBuilder:<init>	()V
    //   14: ldc_w 317
    //   17: invokevirtual 44	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   20: aload_1
    //   21: invokevirtual 241	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   24: invokevirtual 51	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   27: invokestatic 187	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   30: pop
    //   31: aload_3
    //   32: instanceof 319
    //   35: ifne +26 -> 61
    //   38: aload_3
    //   39: instanceof 321
    //   42: ifne +19 -> 61
    //   45: aload_3
    //   46: instanceof 323
    //   49: ifne +12 -> 61
    //   52: aload_3
    //   53: instanceof 325
    //   56: istore_2
    //   57: iload_2
    //   58: ifeq +35 -> 93
    //   61: aload_3
    //   62: invokevirtual 329	ow:f	()Lpa;
    //   65: astore_1
    //   66: aload_1
    //   67: invokevirtual 332	pa:a	()I
    //   70: iconst_2
    //   71: if_icmpne +17 -> 88
    //   74: aload_1
    //   75: invokevirtual 334	pa:b	()Landroid/net/Uri;
    //   78: astore_1
    //   79: aload_1
    //   80: ifnull +8 -> 88
    //   83: aload_0
    //   84: aload_1
    //   85: invokespecial 336	or:a	(Landroid/net/Uri;)V
    //   88: aload_3
    //   89: aload_0
    //   90: invokevirtual 339	ow:b	(Loo;)V
    //   93: aload_3
    //   94: invokevirtual 329	ow:f	()Lpa;
    //   97: astore_1
    //   98: aload_1
    //   99: invokevirtual 332	pa:a	()I
    //   102: iconst_2
    //   103: if_icmpne +20 -> 123
    //   106: aload_1
    //   107: invokevirtual 334	pa:b	()Landroid/net/Uri;
    //   110: astore_1
    //   111: aload_1
    //   112: ifnull +11 -> 123
    //   115: aload_0
    //   116: getfield 19	or:a	Landroid/content/Context;
    //   119: aload_1
    //   120: invokestatic 341	or:a	(Landroid/content/Context;Landroid/net/Uri;)V
    //   123: return
    //   124: astore_1
    //   125: aload_3
    //   126: aload_0
    //   127: invokevirtual 339	ow:b	(Loo;)V
    //   130: aload_1
    //   131: athrow
    //   132: astore_1
    //   133: aload_3
    //   134: invokevirtual 329	ow:f	()Lpa;
    //   137: astore_3
    //   138: aload_3
    //   139: invokevirtual 332	pa:a	()I
    //   142: iconst_2
    //   143: if_icmpne +20 -> 163
    //   146: aload_3
    //   147: invokevirtual 334	pa:b	()Landroid/net/Uri;
    //   150: astore_3
    //   151: aload_3
    //   152: ifnull +11 -> 163
    //   155: aload_0
    //   156: getfield 19	or:a	Landroid/content/Context;
    //   159: aload_3
    //   160: invokestatic 341	or:a	(Landroid/content/Context;Landroid/net/Uri;)V
    //   163: aload_1
    //   164: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	165	0	this	or
    //   0	165	1	paramon	on
    //   56	2	2	bool	boolean
    //   4	156	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   61	79	124	finally
    //   83	88	124	finally
    //   5	57	132	finally
    //   88	93	132	finally
    //   125	132	132	finally
  }
}

/* Location:
 * Qualified Name:     or
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */