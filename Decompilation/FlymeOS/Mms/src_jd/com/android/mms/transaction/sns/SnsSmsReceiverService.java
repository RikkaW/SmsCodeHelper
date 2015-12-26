package com.android.mms.transaction.sns;

import aat;
import aat.b;
import aau;
import abl;
import abl.a;
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
import android.telephony.MzTelephony.SmsExt;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.transaction.SmsReceiver;
import com.google.android.mms.MmsException;
import gm;
import gq;
import gx;
import po;
import pp;
import pq;
import wd;
import zv;

public class SnsSmsReceiverService
  extends Service
{
  private static final String[] e = { "_id", "thread_id", "address", "body", "status", "port", "uuid", "subject" };
  public Handler a = new Handler();
  private a b;
  private Looper c;
  private boolean d;
  
  private Uri a(Context paramContext, Intent paramIntent, int paramInt)
  {
    return b(paramContext, paramIntent, paramInt);
  }
  
  private void a(Intent paramIntent, int paramInt)
  {
    Uri localUri = paramIntent.getData();
    d = false;
    boolean bool = paramIntent.getBooleanExtra("SendNextMsg", true);
    paramInt = paramIntent.getIntExtra("error_code", -1);
    Log.i("SnsMessageManager.SnsSmsReceiverService", "handleSmsSent: errcode is " + paramInt + ", uri is " + aau.b(Uri.class, localUri, "toSafeString"));
    if (paramInt == 0)
    {
      String str = paramIntent.getStringExtra("msgbody");
      paramIntent = paramIntent.getStringExtra("uuid");
      Log.v("SnsMessageManager.SnsSmsReceiverService", "handleSmsSent sending uri: " + localUri + ", return the uuid is " + paramIntent);
      if (!MzTelephony.SmsExt.moveMessageToFolder(this, localUri, 2, paramInt, paramIntent, str)) {
        Log.e("SnsMessageManager.SnsSmsReceiverService", "handleSmsSent: failed to move message " + localUri + " to sent folder");
      }
      if (bool) {
        a();
      }
      MessagingNotification.e(this);
      return;
    }
    paramIntent = paramIntent.getStringExtra("error_msg");
    Log.v("SnsMessageManager.SnsSmsReceiverService", "handleSnsSmsSent msg failed uri: " + localUri + "--errorCode = " + paramInt + "-- errMsg = " + paramIntent);
    a(localUri, paramInt, bool);
  }
  
  private void a(Uri paramUri, int paramInt, boolean paramBoolean)
  {
    if (Log.isLoggable("Mms:transaction", 2)) {
      Log.v("SnsMessageManager.SnsSmsReceiverService", "messageFailedToSend msg failed uri: " + paramUri);
    }
    aau.a("android.provider.Telephony$Sms", "moveMessageToFolder", new Class[] { Context.class, Uri.class, Integer.TYPE, Integer.TYPE }, new Object[] { this, paramUri, Integer.valueOf(5), Integer.valueOf(paramInt) });
    a.post(new pp(this, paramInt));
    MessagingNotification.a(getApplicationContext(), true);
    if (paramBoolean) {
      a();
    }
  }
  
  private Uri b(Context paramContext, Intent paramIntent, int paramInt)
  {
    Object localObject1 = paramIntent.getStringExtra("Addr");
    Object localObject2 = paramIntent.getStringExtra("MsgBody");
    long l = paramIntent.getLongExtra("Date", 0L) * 1000L;
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("address", (String)localObject1);
    localContentValues.put("date", Long.valueOf(l));
    localContentValues.put("association_id", new Long(System.currentTimeMillis()));
    localContentValues.put("imsi", zv.c(0));
    localContentValues.put("protocol", Integer.valueOf(266));
    localContentValues.put("read", Integer.valueOf(0));
    localContentValues.put("seen", Integer.valueOf(0));
    localContentValues.put("reply_path_present", Integer.valueOf(0));
    localContentValues.put("service_center", "");
    if (paramIntent.hasExtra("Port")) {
      localContentValues.put("port", paramIntent.getStringExtra("Port"));
    }
    if (paramIntent.hasExtra("uuid")) {
      localContentValues.put("uuid", paramIntent.getStringExtra("uuid"));
    }
    localContentValues.put("body", (String)localObject2);
    Object localObject3 = abl.a((String)localObject1, (String)localObject2, l);
    if (b)
    {
      localContentValues.remove("attachment_type");
      return abl.a(paramContext, localContentValues, (abl.a)localObject3);
    }
    paramIntent = null;
    localObject2 = paramContext.getContentResolver();
    if (localObject1 != null)
    {
      paramIntent = (Long)aau.a(Telephony.Threads.class, "getOrCreateThreadId", new Class[] { Context.class, String.class }, new Object[] { paramContext, localObject1 });
      localContentValues.put("thread_id", paramIntent);
    }
    if (!TextUtils.isEmpty((CharSequence)localObject1)) {
      if (a != null)
      {
        localObject1 = a;
        if (localObject1 != null)
        {
          ((gm)localObject1).d();
          localObject3 = new gq();
          ((gq)localObject3).add(localObject1);
          gx.a(paramIntent.longValue(), (gq)localObject3);
        }
      }
    }
    for (;;)
    {
      localObject1 = ((ContentResolver)localObject2).insert(Telephony.Sms.Inbox.CONTENT_URI, localContentValues);
      aat.a().a(paramContext, paramIntent.longValue());
      aat.c(getApplicationContext());
      return (Uri)localObject1;
      localObject1 = gm.a((String)localObject1, true);
      break;
      localContentValues.put("address", getString(2131493159));
    }
  }
  
  private void b()
  {
    Log.e("SnsMessageManager.SnsSmsReceiverService", "handleSendMessage()---> mSending = " + d);
    if (!d) {
      a();
    }
  }
  
  private void b(Intent paramIntent, int paramInt)
  {
    paramIntent = a(this, paramIntent, paramInt);
    if ((paramIntent != null) && (!wd.a(paramIntent)))
    {
      long l = MessagingNotification.a(this, paramIntent);
      if (!wd.a(getContentResolver())) {
        MessagingNotification.a(this, l, false, paramIntent, false, true);
      }
    }
    else
    {
      return;
    }
    Intent localIntent = new Intent("com.android.mms.NEW_SNS_SMS_RECEIVED");
    localIntent.putExtra("message_uri", paramIntent.toString());
    sendBroadcast(localIntent);
  }
  
  public void a()
  {
    for (;;)
    {
      try
      {
        Object localObject1 = Uri.parse("content://sms/queued");
        localObject1 = getContentResolver().query((Uri)localObject1, e, "protocol = 266", null, "date ASC");
        if (localObject1 != null) {
          try
          {
            Object localObject4;
            Uri localUri;
            boolean bool;
            if (((Cursor)localObject1).moveToFirst())
            {
              String str1 = ((Cursor)localObject1).getString(3);
              String str2 = ((Cursor)localObject1).getString(2);
              int i = ((Cursor)localObject1).getInt(1);
              int j = ((Cursor)localObject1).getInt(4);
              int k = ((Cursor)localObject1).getInt(5);
              localObject4 = ((Cursor)localObject1).getString(6);
              String str3 = ((Cursor)localObject1).getString(7);
              int m = ((Cursor)localObject1).getInt(0);
              localUri = ContentUris.withAppendedId(Telephony.Sms.CONTENT_URI, m);
              long l = i;
              if (j != 32) {
                continue;
              }
              bool = true;
              localObject4 = new pq(this, str2, str3, str1, l, bool, localUri, k, (String)localObject4);
              Log.v("SnsMessageManager.SnsSmsReceiverService", "sendFirstQueuedMessage " + localUri + ", address: " + str2 + ", threadId: " + i + ", body: " + str1);
            }
            try
            {
              ((po)localObject4).a(-1L);
              d = true;
              ((Cursor)localObject1).close();
              return;
              bool = false;
            }
            catch (MmsException localMmsException)
            {
              Log.e("SnsMessageManager.SnsSmsReceiverService", "sendFirstQueuedMessage: failed to send message " + localUri + ", caught ", localMmsException);
              d = false;
              a(localUri, 1, false);
              sendBroadcast(new Intent("com.android.mms.transaction.SEND_SNS_MESSAGE", null, this, SmsReceiver.class));
              continue;
            }
            localObject2 = finally;
          }
          finally
          {
            ((Cursor)localObject1).close();
          }
        }
        d = false;
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
    HandlerThread localHandlerThread = new HandlerThread("SnsMessageManager.SnsSmsReceiverService", 10);
    localHandlerThread.start();
    c = localHandlerThread.getLooper();
    b = new a(c);
  }
  
  public void onDestroy()
  {
    c.quit();
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    Message localMessage = b.obtainMessage();
    arg1 = paramInt2;
    obj = paramIntent;
    b.sendMessage(localMessage);
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
        Log.d("SnsMessageManager.SnsSmsReceiverService", "SnsSmsReceiverService-->handleMessage(), action is " + str + ", serviceId is " + i + ", error is " + j);
        if (!"com.android.mms.transaction.SEND_SNS_MESSAGE".endsWith(str)) {
          break label101;
        }
        SnsSmsReceiverService.a(SnsSmsReceiverService.this);
      }
      for (;;)
      {
        SmsReceiver.a(SnsSmsReceiverService.this, i);
        return;
        label101:
        if ("com.android.mms.transaction.SNS_MESSAGE_SENT".equals(paramMessage.getAction())) {
          SnsSmsReceiverService.a(SnsSmsReceiverService.this, paramMessage, j);
        } else if ("android.provider.Telephony.SNS_SMS_RECEIVED".equals(str)) {
          SnsSmsReceiverService.b(SnsSmsReceiverService.this, paramMessage, j);
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.sns.SnsSmsReceiverService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */