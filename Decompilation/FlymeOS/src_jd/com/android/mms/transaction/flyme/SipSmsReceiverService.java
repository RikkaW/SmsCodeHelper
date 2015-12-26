package com.android.mms.transaction.flyme;

import aat;
import aat.b;
import aau;
import abl;
import abl.a;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.provider.Telephony.Sms;
import android.provider.Telephony.Sms.Inbox;
import android.provider.Telephony.Threads;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.transaction.MessageStatusReceiver;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.transaction.SmsReceiver;
import com.google.android.mms.MmsException;
import gm;
import gq;
import gx;
import ph;
import pi;
import wd;

public class SipSmsReceiverService
  extends Service
{
  private static final String[] d = { "_id", "thread_id", "address", "body", "status", "port", "uuid" };
  private static final String[] e = { "_id" };
  private static final String[] f = { "_id", "type" };
  private a a;
  private Looper b;
  private boolean c;
  private int g;
  
  private Uri a(Context paramContext, ContentValues paramContentValues, gm paramgm)
  {
    Long localLong = null;
    ContentResolver localContentResolver = paramContext.getContentResolver();
    Object localObject = paramContentValues.getAsString("address");
    Log.e("SipSmsReceiverService", "storeSipMessage from " + (String)localObject);
    if (localObject != null)
    {
      localLong = (Long)aau.a(Telephony.Threads.class, "getOrCreateThreadId", new Class[] { Context.class, String.class }, new Object[] { paramContext, localObject });
      paramContentValues.put("thread_id", localLong);
    }
    if (!TextUtils.isEmpty((CharSequence)localObject)) {
      if (paramgm != null) {
        if (paramgm != null)
        {
          paramgm.d();
          localObject = new gq();
          ((gq)localObject).add(paramgm);
          gx.a(localLong.longValue(), (gq)localObject);
        }
      }
    }
    for (;;)
    {
      paramContentValues = localContentResolver.insert(Telephony.Sms.Inbox.CONTENT_URI, paramContentValues);
      aat.a().a(paramContext, localLong.longValue());
      aat.c(getApplicationContext());
      return paramContentValues;
      paramgm = gm.a((String)localObject, true);
      break;
      paramContentValues.put("address", getString(2131493159));
    }
  }
  
  private Uri a(Context paramContext, Intent paramIntent, int paramInt)
  {
    return b(paramContext, paramIntent, paramInt);
  }
  
  private void a(Intent paramIntent)
  {
    int i = paramIntent.getIntExtra("deliveryStatus", -1);
    Object localObject = paramIntent.getStringExtra("uuid");
    if ((localObject == null) || (i == -1))
    {
      Log.w("SipSmsReceiverService", "invalid message  status code [" + i + "] received");
      return;
    }
    long l = paramIntent.getLongExtra("deliveryDate", 0L);
    Log.d("SipSmsReceiverService", "Status report message received  status code[" + i + "]," + " unixtime[" + l + "]");
    paramIntent = "uuid = '" + (String)localObject + "'";
    paramIntent = getContentResolver().query(Telephony.Sms.CONTENT_URI, e, paramIntent, null, null);
    if (paramIntent != null) {
      if ((paramIntent.getCount() == 1) && (paramIntent.moveToFirst())) {
        if (i == 1) {
          i = 0;
        }
      }
    }
    for (;;)
    {
      localObject = new Intent("com.android.mms.transaction.MessageStatusReceiver.MESSAGE_STATUS_RECEIVED_MZ", Uri.withAppendedPath(Telephony.Sms.CONTENT_URI, paramIntent.getString(0)), this, MessageStatusReceiver.class);
      ((Intent)localObject).putExtra("status", i);
      ((Intent)localObject).putExtra("date", l);
      try
      {
        PendingIntent.getBroadcast(this, 0, (Intent)localObject, 0).send();
        for (;;)
        {
          paramIntent.close();
          return;
          i = 64;
          break;
          Log.d("SipSmsReceiverService", "Status report message in db count is [" + paramIntent.getColumnCount());
        }
        Log.d("SipSmsReceiverService", "Status report message received uuid[" + (String)localObject + "] is not in DB");
        return;
      }
      catch (PendingIntent.CanceledException localCanceledException)
      {
        for (;;) {}
      }
    }
  }
  
  private void a(Intent paramIntent, int paramInt)
  {
    Uri localUri = paramIntent.getData();
    c = false;
    boolean bool = paramIntent.getBooleanExtra("SendNextMsg", false);
    String str = paramIntent.getStringExtra("address");
    Log.d("SipSmsReceiverService", "handleSmsSent -> address : " + str);
    paramInt = paramIntent.getIntExtra("SendStatus", 0);
    if (paramInt == 1)
    {
      Log.v("SipSmsReceiverService", "handleSmsSent sending uri: " + localUri);
      if (!((Boolean)aau.a("android.provider.Telephony$Sms", "moveMessageToFolder", new Class[] { Context.class, Uri.class, Integer.TYPE, Integer.TYPE }, new Object[] { this, localUri, Integer.valueOf(2), Integer.valueOf(paramInt - 1) })).booleanValue()) {
        Log.e("SipSmsReceiverService", "handleSmsSent: failed to move message " + localUri + " to sent folder");
      }
      for (;;)
      {
        if (bool) {
          a();
        }
        MessagingNotification.e(this);
        return;
        MessagingNotification.a(MmsApp.c(), MmsApp.a(), 0.25F);
      }
    }
    Log.v("SipSmsReceiverService", "handleSipSmsSent msg failed uri: " + localUri);
    a(localUri, paramInt - 1, bool);
  }
  
  private void a(Uri paramUri, int paramInt, boolean paramBoolean)
  {
    Log.v("SipSmsReceiverService", "messageFailedToSend msg failed uri: " + paramUri);
    aau.a("android.provider.Telephony$Sms", "moveMessageToFolder", new Class[] { Context.class, Uri.class, Integer.TYPE, Integer.TYPE }, new Object[] { this, paramUri, Integer.valueOf(5), Integer.valueOf(paramInt) });
    if (paramInt == -3) {}
    MessagingNotification.a(getApplicationContext(), true);
    if (paramBoolean) {
      a();
    }
  }
  
  /* Error */
  private boolean a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: ldc 63
    //   5: new 65	java/lang/StringBuilder
    //   8: dup
    //   9: invokespecial 66	java/lang/StringBuilder:<init>	()V
    //   12: ldc_w 370
    //   15: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: aload_1
    //   19: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   25: invokestatic 212	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   28: pop
    //   29: new 65	java/lang/StringBuilder
    //   32: dup
    //   33: invokespecial 66	java/lang/StringBuilder:<init>	()V
    //   36: ldc -42
    //   38: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: aload_1
    //   42: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: ldc -40
    //   47: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: astore_1
    //   54: aload_0
    //   55: invokevirtual 217	com/android/mms/transaction/flyme/SipSmsReceiverService:getContentResolver	()Landroid/content/ContentResolver;
    //   58: getstatic 132	android/provider/Telephony$Sms$Inbox:CONTENT_URI	Landroid/net/Uri;
    //   61: getstatic 40	com/android/mms/transaction/flyme/SipSmsReceiverService:e	[Ljava/lang/String;
    //   64: aload_1
    //   65: aconst_null
    //   66: aconst_null
    //   67: invokevirtual 224	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   70: astore_1
    //   71: aload_1
    //   72: astore 5
    //   74: aload 5
    //   76: ifnull +158 -> 234
    //   79: aload 5
    //   81: astore_1
    //   82: aload 5
    //   84: invokeinterface 230 1 0
    //   89: istore_2
    //   90: iload_2
    //   91: ifle +143 -> 234
    //   94: iconst_1
    //   95: istore_3
    //   96: iload_3
    //   97: istore 4
    //   99: aload 5
    //   101: ifnull +13 -> 114
    //   104: aload 5
    //   106: invokeinterface 269 1 0
    //   111: iload_3
    //   112: istore 4
    //   114: ldc 63
    //   116: new 65	java/lang/StringBuilder
    //   119: dup
    //   120: invokespecial 66	java/lang/StringBuilder:<init>	()V
    //   123: ldc_w 372
    //   126: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: iload 4
    //   131: invokevirtual 375	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   134: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   137: invokestatic 212	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   140: pop
    //   141: iload 4
    //   143: ireturn
    //   144: astore 6
    //   146: aconst_null
    //   147: astore 5
    //   149: aload 5
    //   151: astore_1
    //   152: ldc 63
    //   154: new 65	java/lang/StringBuilder
    //   157: dup
    //   158: invokespecial 66	java/lang/StringBuilder:<init>	()V
    //   161: ldc_w 377
    //   164: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: aload 6
    //   169: invokevirtual 300	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   172: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   175: invokestatic 81	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   178: pop
    //   179: aload 5
    //   181: ifnull +47 -> 228
    //   184: aload 5
    //   186: invokeinterface 269 1 0
    //   191: iconst_0
    //   192: istore 4
    //   194: goto -80 -> 114
    //   197: astore_1
    //   198: aload 5
    //   200: ifnull +10 -> 210
    //   203: aload 5
    //   205: invokeinterface 269 1 0
    //   210: aload_1
    //   211: athrow
    //   212: astore 6
    //   214: aload_1
    //   215: astore 5
    //   217: aload 6
    //   219: astore_1
    //   220: goto -22 -> 198
    //   223: astore 6
    //   225: goto -76 -> 149
    //   228: iconst_0
    //   229: istore 4
    //   231: goto -117 -> 114
    //   234: iconst_0
    //   235: istore_3
    //   236: goto -140 -> 96
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	239	0	this	SipSmsReceiverService
    //   0	239	1	paramString	String
    //   89	2	2	i	int
    //   95	141	3	bool1	boolean
    //   97	133	4	bool2	boolean
    //   1	215	5	str	String
    //   144	24	6	localException1	Exception
    //   212	6	6	localObject	Object
    //   223	1	6	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   29	71	144	java/lang/Exception
    //   29	71	197	finally
    //   82	90	212	finally
    //   152	179	212	finally
    //   82	90	223	java/lang/Exception
  }
  
  private ContentValues b(Intent paramIntent)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("address", paramIntent.getStringExtra("Addr"));
    localContentValues.put("date", Long.valueOf(System.currentTimeMillis()));
    localContentValues.put("date_sent", Long.valueOf(paramIntent.getLongExtra("Date", 0L) * 1000L));
    localContentValues.put("association_id", new Long(System.currentTimeMillis()));
    localContentValues.put("imsi", "-10");
    localContentValues.put("sim_id", Integer.valueOf(-10));
    localContentValues.put("protocol", Integer.valueOf(256));
    localContentValues.put("read", Integer.valueOf(0));
    localContentValues.put("seen", Integer.valueOf(0));
    localContentValues.put("reply_path_present", Integer.valueOf(0));
    localContentValues.put("service_center", "");
    localContentValues.put("port", paramIntent.getStringExtra("Port"));
    localContentValues.put("body", paramIntent.getStringExtra("MsgBody"));
    return localContentValues;
  }
  
  private Uri b(Context paramContext, Intent paramIntent, int paramInt)
  {
    paramIntent = b(paramIntent);
    Object localObject = paramIntent.getAsString("address");
    String str = paramIntent.getAsString("body");
    long l = paramIntent.getAsLong("date").longValue();
    boolean bool = wd.i(paramContext);
    Log.d("SipSmsReceiverService", "isOn : " + bool);
    if (bool)
    {
      bool = wd.f(paramContext, (String)localObject);
      Log.d("SipSmsReceiverService", "isContactRecord : " + bool);
      if (!bool) {
        return null;
      }
    }
    localObject = abl.a((String)localObject, str, l);
    if (b) {
      return abl.a(paramContext, paramIntent, (abl.a)localObject);
    }
    return a(paramContext, paramIntent, a);
  }
  
  private void b()
  {
    Log.e("SipSmsReceiverService", "handleSendMessage ");
    if (!c) {
      a();
    }
  }
  
  private void b(Intent paramIntent, int paramInt)
  {
    Object localObject = paramIntent.getStringExtra("id");
    if (a((String)localObject)) {}
    do
    {
      return;
      Log.d("SipSmsReceiverService", "handleSmsReceived, uuid = " + (String)localObject);
      paramIntent = a(this, paramIntent, paramInt);
    } while ((paramIntent == null) || (wd.a(paramIntent)));
    long l = MessagingNotification.a(this, paramIntent);
    if (!wd.a(getContentResolver()))
    {
      MessagingNotification.a(this, l, false, paramIntent, false, true);
      return;
    }
    localObject = new Intent("com.android.mms.NEW_SIP_SMS_RECEIVED");
    ((Intent)localObject).putExtra("message_uri", paramIntent.toString());
    sendBroadcast((Intent)localObject);
  }
  
  public void a()
  {
    for (;;)
    {
      try
      {
        Object localObject1 = Uri.parse("content://sms/queued");
        localObject1 = getContentResolver().query((Uri)localObject1, d, "protocol = 256", null, "date ASC");
        if (localObject1 != null) {
          try
          {
            Object localObject4;
            Uri localUri;
            boolean bool;
            if (((Cursor)localObject1).moveToFirst())
            {
              localObject4 = ((Cursor)localObject1).getString(3);
              String str1 = ((Cursor)localObject1).getString(2);
              int i = ((Cursor)localObject1).getInt(1);
              int j = ((Cursor)localObject1).getInt(4);
              int k = ((Cursor)localObject1).getInt(5);
              String str2 = ((Cursor)localObject1).getString(6);
              int m = ((Cursor)localObject1).getInt(0);
              localUri = ContentUris.withAppendedId(Telephony.Sms.CONTENT_URI, m);
              long l = i;
              if (j != 32) {
                continue;
              }
              bool = true;
              localObject4 = new pi(this, str1, (String)localObject4, l, bool, localUri, k, str2);
              Log.v("SipSmsReceiverService", "sendFirstQueuedMessage " + localUri + ", address: " + str1 + ", threadId: " + i);
            }
            try
            {
              ((ph)localObject4).a(-1L);
              c = true;
              ((Cursor)localObject1).close();
              return;
              bool = false;
            }
            catch (MmsException localMmsException)
            {
              Log.e("SipSmsReceiverService", "sendFirstQueuedMessage: failed to send message " + localUri + ", caught ", localMmsException);
              c = false;
              a(localUri, 1, false);
              sendBroadcast(new Intent("com.android.mms.transaction.SEND_SIP_MESSAGE", null, this, SmsReceiver.class));
              continue;
            }
            localObject2 = finally;
          }
          finally
          {
            ((Cursor)localObject1).close();
          }
        }
        c = false;
      }
      finally {}
    }
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    HandlerThread localHandlerThread = new HandlerThread("SipSmsReceiverService", 10);
    localHandlerThread.start();
    b = localHandlerThread.getLooper();
    a = new a(b);
  }
  
  public void onDestroy()
  {
    b.quit();
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    paramInt1 = 0;
    if (paramIntent != null) {
      paramInt1 = paramIntent.getIntExtra("result", 0);
    }
    g = paramInt1;
    Message localMessage = a.obtainMessage();
    arg1 = paramInt2;
    obj = paramIntent;
    a.sendMessage(localMessage);
    return 2;
  }
  
  final class a
    extends Handler
  {
    public a(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      int i = arg1;
      paramMessage = (Intent)obj;
      String str;
      int j;
      if (paramMessage != null)
      {
        str = paramMessage.getAction();
        j = paramMessage.getIntExtra("errorCode", 0);
        if (!"com.android.mms.transaction.SIP_MESSAGE_SENT".equals(paramMessage.getAction())) {
          break label61;
        }
        SipSmsReceiverService.a(SipSmsReceiverService.this, paramMessage, j);
      }
      for (;;)
      {
        SmsReceiver.a(SipSmsReceiverService.this, i);
        return;
        label61:
        if ("android.provider.Telephony.SIP_SMS_RECEIVED".equals(str)) {
          SipSmsReceiverService.b(SipSmsReceiverService.this, paramMessage, j);
        } else if ("com.android.mms.transaction.SEND_SIP_MESSAGE".endsWith(str)) {
          SipSmsReceiverService.a(SipSmsReceiverService.this);
        } else if ("android.provider.Telephony.SIP_SMS_STATUS_RECEIVED".equals(paramMessage.getAction())) {
          SipSmsReceiverService.a(SipSmsReceiverService.this, paramMessage);
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.flyme.SipSmsReceiverService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */