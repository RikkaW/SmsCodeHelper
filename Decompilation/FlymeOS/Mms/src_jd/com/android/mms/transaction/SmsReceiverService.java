package com.android.mms.transaction;

import aac;
import aat;
import aat.b;
import aau;
import aaw;
import abl;
import abl.a;
import aev;
import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings.System;
import android.provider.Telephony.Sms;
import android.provider.Telephony.Sms.Inbox;
import android.provider.Telephony.Sms.Intents;
import android.provider.Telephony.Sms.Outbox;
import android.telephony.ServiceState;
import android.telephony.SmsMessage;
import android.telephony.SmsMessage.MessageClass;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.telephony.IccUtils;
import com.android.mms.MmsApp;
import com.android.mms.ui.ClassZeroActivity;
import com.google.android.mms.MmsException;
import ga;
import gm;
import gq;
import gr;
import gx;
import ot;
import ou;
import wd;

public class SmsReceiverService
  extends Service
{
  protected static final Uri a = Uri.withAppendedPath(Telephony.Sms.CONTENT_URI, "raw");
  private static final String[] e = { "_id", "thread_id", "address", "body", "status", "port", "group_msg_id", "sub_id" };
  private static final String[] f = { "_id", "section_count", "status", "message_uri" };
  private static final String[] g = { "_id", "type" };
  private static final String[] h = { "_id", "pdu", "sequence", "destination_port", "sms_id" };
  private static final String[] i = { "error_code" };
  private static final String[] k = { "_id", "address", "protocol" };
  private a b;
  private Looper c;
  private boolean d;
  private int j;
  
  private ContentValues a(SmsMessage paramSmsMessage)
  {
    int m = 0;
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("address", paramSmsMessage.getDisplayOriginatingAddress());
    if ((MmsApp.b) || (MmsApp.a)) {
      localContentValues.put("date", Long.valueOf(System.currentTimeMillis()));
    }
    for (;;)
    {
      localContentValues.put("date_sent", Long.valueOf(paramSmsMessage.getTimestampMillis()));
      localContentValues.put("protocol", Integer.valueOf(paramSmsMessage.getProtocolIdentifier()));
      localContentValues.put("read", Integer.valueOf(0));
      localContentValues.put("seen", Integer.valueOf(0));
      if (paramSmsMessage.getPseudoSubject().length() > 0) {
        localContentValues.put("subject", paramSmsMessage.getPseudoSubject());
      }
      if (paramSmsMessage.isReplyPathPresent()) {
        m = 1;
      }
      localContentValues.put("reply_path_present", Integer.valueOf(m));
      localContentValues.put("service_center", paramSmsMessage.getServiceCenterAddress());
      return localContentValues;
      localContentValues.put("date", Long.valueOf(paramSmsMessage.getTimestampMillis()));
    }
  }
  
  private Uri a(Context paramContext, ContentValues paramContentValues)
  {
    abl.a locala = abl.a(paramContentValues.getAsString("address"), paramContentValues.getAsString("body"), paramContentValues.getAsLong("date").longValue());
    paramContentValues.put("association_id", new Long(System.currentTimeMillis()));
    paramContentValues.put("imsi", aac.d(paramContext, paramContentValues.getAsInteger("sim_id").intValue()));
    if (b) {
      return abl.a(paramContext, paramContentValues, locala);
    }
    return a(paramContext, paramContentValues, a);
  }
  
  private Uri a(Context paramContext, ContentValues paramContentValues, gm paramgm)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    Object localObject = paramContentValues.getAsString("address");
    wd.a(true);
    Long localLong = null;
    if (localObject != null)
    {
      localLong = Long.valueOf(gr.b(paramContext, (String)localObject));
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
      wd.a(false);
      aat.a().a(paramContext, localLong.longValue());
      aat.c(getApplicationContext());
      aev.a(paramContext);
      return paramContentValues;
      paramgm = gm.a((String)localObject, true);
      break;
      paramContentValues.put("address", getString(2131493159));
    }
  }
  
  private Uri a(Context paramContext, SmsMessage[] paramArrayOfSmsMessage, int paramInt1, int paramInt2)
  {
    Object localObject1 = paramArrayOfSmsMessage[0];
    ContentValues localContentValues = a((SmsMessage)localObject1);
    localContentValues.put("error_code", Integer.valueOf(paramInt1));
    localContentValues.put("sim_id", Integer.valueOf(paramInt2));
    localContentValues.put("imsi", aac.d(paramContext, paramInt2));
    int n = paramArrayOfSmsMessage.length;
    if (n == 1) {
      localContentValues.put("body", a(((SmsMessage)localObject1).getDisplayMessageBody()));
    }
    for (;;)
    {
      Object localObject2 = paramContext.getContentResolver();
      String str1 = ((SmsMessage)localObject1).getOriginatingAddress();
      localObject1 = Integer.toString(((SmsMessage)localObject1).getProtocolIdentifier());
      String str2 = Integer.toString(paramInt2);
      localObject1 = ((ContentResolver)localObject2).query(Telephony.Sms.Inbox.CONTENT_URI, k, "address = ? AND protocol = ? AND sim_id = ?", new String[] { str1, localObject1, str2 }, null);
      if (localObject1 != null) {}
      try
      {
        if (((Cursor)localObject1).moveToFirst())
        {
          long l = ((Cursor)localObject1).getLong(0);
          paramContext = ContentUris.withAppendedId(Telephony.Sms.CONTENT_URI, l);
          ((ContentResolver)localObject2).update(paramContext, localContentValues, null, null);
          return paramContext;
          localObject2 = new StringBuilder();
          int m = 0;
          while (m < n)
          {
            localObject1 = paramArrayOfSmsMessage[m];
            if (aau.a(localObject1, "mWrappedSmsMessage") != null) {
              ((StringBuilder)localObject2).append(((SmsMessage)localObject1).getDisplayMessageBody());
            }
            m += 1;
          }
          localContentValues.put("body", a(((StringBuilder)localObject2).toString()));
          continue;
        }
        return b(paramContext, paramArrayOfSmsMessage, paramInt1, paramInt2);
      }
      finally
      {
        ((Cursor)localObject1).close();
      }
    }
  }
  
  private Uri a(Context paramContext, SmsMessage[] paramArrayOfSmsMessage, int paramInt1, int paramInt2, int paramInt3)
  {
    SmsMessage localSmsMessage = paramArrayOfSmsMessage[0];
    int m = 0;
    int n = 0;
    while (m < paramArrayOfSmsMessage.length)
    {
      n += paramArrayOfSmsMessage[m].getUserData().length;
      m += 1;
    }
    byte[] arrayOfByte = new byte[n];
    m = 0;
    n = 0;
    while (m < paramArrayOfSmsMessage.length)
    {
      System.arraycopy(paramArrayOfSmsMessage[m].getUserData(), 0, arrayOfByte, n, paramArrayOfSmsMessage[m].getUserData().length);
      n += paramArrayOfSmsMessage[m].getUserData().length;
      m += 1;
    }
    paramArrayOfSmsMessage = a(localSmsMessage);
    paramArrayOfSmsMessage.put("error_code", Integer.valueOf(paramInt1));
    paramArrayOfSmsMessage.put("port", Integer.valueOf(paramInt2));
    paramArrayOfSmsMessage.put("body", IccUtils.bytesToHexString(arrayOfByte));
    paramArrayOfSmsMessage.put("sim_id", Integer.valueOf(paramInt3));
    return a(paramContext, paramArrayOfSmsMessage);
  }
  
  private Uri a(Context paramContext, SmsMessage[] paramArrayOfSmsMessage, int paramInt1, String paramString, int paramInt2)
  {
    SmsMessage localSmsMessage = paramArrayOfSmsMessage[0];
    if (localSmsMessage.getMessageClass() == SmsMessage.MessageClass.CLASS_0)
    {
      paramArrayOfSmsMessage = b(paramContext, paramArrayOfSmsMessage, paramInt1, paramInt2);
      if (!wd.a(paramArrayOfSmsMessage)) {
        a(paramContext, localSmsMessage, paramString, paramArrayOfSmsMessage);
      }
      return paramArrayOfSmsMessage;
    }
    if (localSmsMessage.isReplace()) {
      return a(paramContext, paramArrayOfSmsMessage, paramInt1, paramInt2);
    }
    return b(paramContext, paramArrayOfSmsMessage, paramInt1, paramInt2);
  }
  
  private static String a(int paramInt)
  {
    switch (paramInt)
    {
    case 0: 
    default: 
      return "Unknown error code";
    case -1: 
      return "Activity.RESULT_OK";
    case 1: 
      return "SmsManager.RESULT_ERROR_GENERIC_FAILURE";
    case 2: 
      return "SmsManager.RESULT_ERROR_RADIO_OFF";
    case 3: 
      return "SmsManager.RESULT_ERROR_NULL_PDU";
    case 4: 
      return "SmsManager.RESULT_ERROR_NO_SERVICE";
    case 5: 
      return "SmsManager.RESULT_ERROR_LIMIT_EXCEEDED";
    }
    return "SmsManager.RESULT_ERROR_FDN_CHECK_FAILURE";
  }
  
  public static String a(String paramString)
  {
    if (paramString == null) {
      return "";
    }
    return paramString.replace('\f', '\n');
  }
  
  private void a(Context paramContext, SmsMessage paramSmsMessage, String paramString, Uri paramUri)
  {
    if (!MmsApp.c().d()) {
      paramContext.startActivity(new Intent(paramContext, ClassZeroActivity.class).putExtra("pdu", paramSmsMessage.getPdu()).putExtra("format", paramString).setData(paramUri).setFlags(268435456));
    }
  }
  
  private void a(Intent paramIntent)
  {
    if (((ServiceState)aau.a(ServiceState.class, "newFromBundle", Bundle.class, paramIntent.getExtras())).getState() == 0) {
      a();
    }
  }
  
  private void a(Intent paramIntent, int paramInt)
  {
    Uri localUri = paramIntent.getData();
    d = false;
    boolean bool2 = paramIntent.getBooleanExtra("SendNextMsg", false);
    int m;
    if (paramIntent != null) {
      m = paramIntent.getIntExtra("result", 0);
    }
    for (;;)
    {
      j = m;
      Log.v("SmsReceiverService", "handleSmsSent uri: " + localUri + " sendNextMsg: " + bool2 + " mResultCode: " + j + " = " + a(j) + " error: " + paramInt);
      if (j == -1)
      {
        Log.v("SmsReceiverService", "handleSmsSent sending uri: " + localUri);
        if (bool2)
        {
          paramIntent = getContentResolver().query(localUri, new String[] { "type" }, null, null, null);
          if (paramIntent == null) {}
        }
        try
        {
          if ((paramIntent.getCount() == 1) && (paramIntent.moveToFirst()) && (paramIntent.getInt(0) != 5))
          {
            if (((Boolean)aau.a("android.provider.Telephony$Sms", "moveMessageToFolder", new Class[] { Context.class, Uri.class, Integer.TYPE, Integer.TYPE }, new Object[] { this, localUri, Integer.valueOf(2), Integer.valueOf(paramInt) })).booleanValue()) {
              break label344;
            }
            Log.e("SmsReceiverService", "handleSmsSent: failed to move message " + localUri + " to sent folder");
          }
          for (;;)
          {
            paramIntent.close();
            if (bool2) {
              a();
            }
            MessagingNotification.e(this);
            return;
            m = 0;
            break;
            label344:
            paramInt = Settings.System.getInt(getContentResolver(), "key_system_tips_sound", 1);
            Log.d("SmsReceiverService", "message hasKeySystemTipsSound = " + paramInt);
            if (paramInt == 1) {
              MessagingNotification.a(MmsApp.c(), MmsApp.a(), 0.25F);
            }
          }
          if (j != 4) {
            break label587;
          }
        }
        finally
        {
          paramIntent.close();
        }
      }
    }
    Log.v("SmsReceiverService", "handleSmsSent: no service, queuing message w/ uri: " + localObject);
    g();
    if (5 == ((TelephonyManager)getSystemService("phone")).getSimState()) {}
    for (boolean bool1 = true; bool1; bool1 = false)
    {
      aau.b("android.provider.Telephony$Sms", "moveMessageToFolder", new Class[] { Context.class, Uri.class, Integer.TYPE, Integer.TYPE }, new Object[] { this, localObject, Integer.valueOf(6), Integer.valueOf(paramInt) });
      return;
    }
    Log.v("SmsReceiverService", "isSIMReady is  " + bool1);
    a((Uri)localObject, paramInt, bool2);
    return;
    label587:
    if (j == 2)
    {
      Log.v("SmsReceiverService", "handleSmsSent: RESULT_ERROR_RADIO_OFF, failed message w/ uri: " + localObject);
      a((Uri)localObject, paramInt, bool2);
      return;
    }
    if (j == 6)
    {
      Log.v("SmsReceiverService", "handleSmsSent: RESULT_ERROR_FDN_CHECK_FAILURE, failed message w/ uri: " + localObject);
      a((Uri)localObject, j, bool2);
      return;
    }
    Log.v("SmsReceiverService", "handleSmsSent msg failed uri: " + localObject);
    a((Uri)localObject, paramInt, bool2);
  }
  
  private void a(Uri paramUri, int paramInt, boolean paramBoolean)
  {
    Log.v("SmsReceiverService", "messageFailedToSend msg failed uri: " + paramUri + " error: " + paramInt);
    aau.b("android.provider.Telephony$Sms", "moveMessageToFolder", new Class[] { Context.class, Uri.class, Integer.TYPE, Integer.TYPE }, new Object[] { this, paramUri, Integer.valueOf(5), Integer.valueOf(paramInt) });
    MessagingNotification.a(getApplicationContext(), true);
    if (paramBoolean) {
      a();
    }
    if (aaw.a()) {
      aaw.a(paramUri);
    }
  }
  
  private Uri b(Context paramContext, SmsMessage[] paramArrayOfSmsMessage, int paramInt1, int paramInt2)
  {
    Object localObject1 = paramArrayOfSmsMessage[0];
    ContentValues localContentValues = a((SmsMessage)localObject1);
    localContentValues.put("error_code", Integer.valueOf(paramInt1));
    localContentValues.put("sim_id", Integer.valueOf(paramInt2));
    Object localObject2;
    if (MmsApp.l)
    {
      localObject2 = i();
      if (localObject2 != null)
      {
        Log.w("SmsReceiverService", "SmsReceiverService->storeMessage: use test number testAddress");
        localContentValues.put("address", (String)localObject2);
      }
    }
    if (Build.VERSION.SDK_INT > 21) {}
    for (long l = ((Integer)aau.a(localObject1, "getSubId")).intValue(); !aac.b(l); l = ((Long)aau.a(localObject1, "getSubId")).longValue())
    {
      Log.e("SmsReceiverService", "subId in storeMessage() is invalid!");
      return null;
    }
    localContentValues.put("sub_id", Long.valueOf(l));
    paramInt2 = paramArrayOfSmsMessage.length;
    if (paramInt2 == 1)
    {
      localObject1 = ((SmsMessage)localObject1).getDisplayMessageBody();
      paramArrayOfSmsMessage = (SmsMessage[])localObject1;
      if (localObject1 != null) {
        paramArrayOfSmsMessage = wd.k((String)localObject1);
      }
      localContentValues.put("body", paramArrayOfSmsMessage);
    }
    for (;;)
    {
      return a(paramContext, localContentValues);
      localObject1 = new StringBuilder();
      paramInt1 = 0;
      while (paramInt1 < paramInt2)
      {
        localObject2 = paramArrayOfSmsMessage[paramInt1];
        if (aau.b(localObject2, "mWrappedSmsMessage") != null) {
          ((StringBuilder)localObject1).append(((SmsMessage)localObject2).getDisplayMessageBody());
        }
        paramInt1 += 1;
      }
      localObject1 = ((StringBuilder)localObject1).toString();
      paramArrayOfSmsMessage = (SmsMessage[])localObject1;
      if (localObject1 != null) {
        paramArrayOfSmsMessage = wd.k((String)localObject1);
      }
      localContentValues.put("body", paramArrayOfSmsMessage);
    }
  }
  
  private void b()
  {
    if (!d) {
      a();
    }
  }
  
  private void b(Intent paramIntent, int paramInt)
  {
    int m = paramIntent.getIntExtra("port", 0);
    Object localObject1 = Telephony.Sms.Intents.getMessagesFromIntent(paramIntent);
    Object localObject2 = localObject1[0];
    long l1;
    int n;
    boolean bool;
    if (Build.VERSION.SDK_INT > 21)
    {
      l1 = ((Integer)aau.a(localObject2, "getSubId")).intValue();
      n = aac.a(l1);
      paramIntent = paramIntent.getStringExtra("format");
      bool = false;
      if (m == 0) {
        break label300;
      }
      paramIntent = a(this, (SmsMessage[])localObject1, paramInt, m, n);
      if ((paramIntent != null) && (!wd.a(paramIntent)))
      {
        long l2 = MessagingNotification.a(this, paramIntent);
        Log.d("SmsReceiverService", "handleSmsReceived messageUri: " + paramIntent + " threadId: " + l2 + ", subId = " + l1 + ", slotId = " + n);
        localObject2 = localObject1[0].getServiceCenterAddress();
        if (wd.h(this, localObject1[0].getDisplayOriginatingAddress())) {
          break label337;
        }
        if (!TextUtils.isEmpty((CharSequence)localObject2)) {
          MessagingNotification.a((String)localObject2);
        }
        MessagingNotification.a(this, l2, false, paramIntent, bool, true, localObject1[0].getMessageBody());
      }
    }
    for (;;)
    {
      aaw.a(paramIntent, (SmsMessage[])localObject1, n);
      if (wd.c(getContentResolver()))
      {
        Log.d("SmsReceiverService", "in isSupperPowerMode");
        localObject1 = new Intent("android.intent.action.flyme.powersavingmode.messages");
        ((Intent)localObject1).putExtra("message_uri", paramIntent.toString());
        ((Intent)localObject1).putExtra("type", "sms");
        sendBroadcast((Intent)localObject1);
      }
      return;
      l1 = ((Long)aau.a(localObject2, "getSubId")).longValue();
      break;
      label300:
      if (localObject1[0].getMessageClass() == SmsMessage.MessageClass.CLASS_0) {}
      for (bool = true;; bool = false)
      {
        paramIntent = a(this, (SmsMessage[])localObject1, paramInt, paramIntent, n);
        break;
      }
      label337:
      localObject2 = new Intent("com.android.mms.NEW_SMS_RECEIVED");
      ((Intent)localObject2).putExtra("message_uri", paramIntent.toString());
      ((Intent)localObject2).putExtra("class_zero", bool);
      sendBroadcast((Intent)localObject2);
    }
  }
  
  private void c()
  {
    e();
    a();
  }
  
  private void d()
  {
    if (f() > 0) {
      MessagingNotification.a(getApplicationContext(), true);
    }
  }
  
  private int e()
  {
    ContentValues localContentValues = new ContentValues(1);
    localContentValues.put("type", Integer.valueOf(6));
    int m = getContentResolver().update(Telephony.Sms.Outbox.CONTENT_URI, localContentValues, "type = 4", null);
    if (!Log.isLoggable("Mms:transaction", 2)) {
      return m;
    }
    Log.v("SmsReceiverService", "moveOutboxMessagesToQueuedBox messageCount: " + m);
    return m;
  }
  
  private int f()
  {
    ContentValues localContentValues = new ContentValues(3);
    localContentValues.put("type", Integer.valueOf(5));
    localContentValues.put("error_code", Integer.valueOf(1));
    localContentValues.put("read", Integer.valueOf(0));
    int m = getContentResolver().update(Telephony.Sms.Outbox.CONTENT_URI, localContentValues, "type = 4 OR type = 6", null);
    Log.v("SmsReceiverService", "moveOutboxAndQueuedBoxMessagesToFailedBox messageCount: " + m);
    return m;
  }
  
  private void g()
  {
    Context localContext = getApplicationContext();
    h();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.SERVICE_STATE");
    Log.v("SmsReceiverService", "registerForServiceStateChanges");
    localContext.registerReceiver(SmsReceiver.a(), localIntentFilter);
  }
  
  private void h()
  {
    Log.v("SmsReceiverService", "unRegisterForServiceStateChanges");
    try
    {
      getApplicationContext().unregisterReceiver(SmsReceiver.a());
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException) {}
  }
  
  /* Error */
  private String i()
  {
    // Byte code:
    //   0: new 784	java/io/FileInputStream
    //   3: dup
    //   4: ldc_w 786
    //   7: invokespecial 787	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   10: astore_2
    //   11: aload_2
    //   12: astore_1
    //   13: aload_2
    //   14: invokevirtual 790	java/io/FileInputStream:available	()I
    //   17: newarray <illegal type>
    //   19: astore_3
    //   20: aload_2
    //   21: astore_1
    //   22: aload_2
    //   23: aload_3
    //   24: invokevirtual 793	java/io/FileInputStream:read	([B)I
    //   27: pop
    //   28: aload_2
    //   29: astore_1
    //   30: aload_3
    //   31: ldc_w 795
    //   34: invokestatic 800	org/apache/http/util/EncodingUtils:getString	([BLjava/lang/String;)Ljava/lang/String;
    //   37: astore_3
    //   38: aload_3
    //   39: astore_1
    //   40: aload_2
    //   41: ifnull +9 -> 50
    //   44: aload_2
    //   45: invokevirtual 801	java/io/FileInputStream:close	()V
    //   48: aload_3
    //   49: astore_1
    //   50: aload_1
    //   51: astore_2
    //   52: aload_1
    //   53: ifnull +14 -> 67
    //   56: aload_1
    //   57: ldc_w 803
    //   60: ldc_w 412
    //   63: invokevirtual 807	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   66: astore_2
    //   67: aload_2
    //   68: astore_1
    //   69: aload_2
    //   70: invokestatic 241	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   73: ifeq +5 -> 78
    //   76: aconst_null
    //   77: astore_1
    //   78: aload_1
    //   79: areturn
    //   80: astore_1
    //   81: aload_1
    //   82: invokevirtual 810	java/lang/Exception:printStackTrace	()V
    //   85: aload_3
    //   86: astore_1
    //   87: goto -37 -> 50
    //   90: astore_3
    //   91: aconst_null
    //   92: astore_2
    //   93: aload_2
    //   94: astore_1
    //   95: aload_3
    //   96: invokevirtual 810	java/lang/Exception:printStackTrace	()V
    //   99: aload_2
    //   100: ifnull +55 -> 155
    //   103: aload_2
    //   104: invokevirtual 801	java/io/FileInputStream:close	()V
    //   107: ldc_w 412
    //   110: astore_1
    //   111: goto -61 -> 50
    //   114: astore_1
    //   115: aload_1
    //   116: invokevirtual 810	java/lang/Exception:printStackTrace	()V
    //   119: ldc_w 412
    //   122: astore_1
    //   123: goto -73 -> 50
    //   126: astore_2
    //   127: aconst_null
    //   128: astore_1
    //   129: aload_1
    //   130: ifnull +7 -> 137
    //   133: aload_1
    //   134: invokevirtual 801	java/io/FileInputStream:close	()V
    //   137: aload_2
    //   138: athrow
    //   139: astore_1
    //   140: aload_1
    //   141: invokevirtual 810	java/lang/Exception:printStackTrace	()V
    //   144: goto -7 -> 137
    //   147: astore_2
    //   148: goto -19 -> 129
    //   151: astore_3
    //   152: goto -59 -> 93
    //   155: ldc_w 412
    //   158: astore_1
    //   159: goto -109 -> 50
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	162	0	this	SmsReceiverService
    //   12	67	1	localObject1	Object
    //   80	2	1	localException1	Exception
    //   86	25	1	localObject2	Object
    //   114	2	1	localException2	Exception
    //   122	12	1	str1	String
    //   139	2	1	localException3	Exception
    //   158	1	1	str2	String
    //   10	94	2	localObject3	Object
    //   126	12	2	localObject4	Object
    //   147	1	2	localObject5	Object
    //   19	67	3	localObject6	Object
    //   90	6	3	localException4	Exception
    //   151	1	3	localException5	Exception
    // Exception table:
    //   from	to	target	type
    //   44	48	80	java/lang/Exception
    //   0	11	90	java/lang/Exception
    //   103	107	114	java/lang/Exception
    //   0	11	126	finally
    //   133	137	139	java/lang/Exception
    //   13	20	147	finally
    //   22	28	147	finally
    //   30	38	147	finally
    //   95	99	147	finally
    //   13	20	151	java/lang/Exception
    //   22	28	151	java/lang/Exception
    //   30	38	151	java/lang/Exception
  }
  
  public void a()
  {
    int m = 1;
    for (;;)
    {
      try
      {
        Object localObject1 = Uri.parse("content://sms/queued");
        localObject1 = getContentResolver().query((Uri)localObject1, e, "protocol < 256 OR protocol IS NULL ", null, "date ASC");
        if (localObject1 != null) {
          try
          {
            Uri localUri;
            if (((Cursor)localObject1).moveToFirst())
            {
              Object localObject4 = ((Cursor)localObject1).getString(3);
              String str1 = ((Cursor)localObject1).getString(2);
              m = ((Cursor)localObject1).getInt(1);
              int n = ((Cursor)localObject1).getInt(4);
              int i1 = ((Cursor)localObject1).getInt(5);
              String str2 = ((Cursor)localObject1).getString(6);
              long l1 = ((Cursor)localObject1).getLong(7);
              int i2 = ((Cursor)localObject1).getInt(0);
              localUri = ContentUris.withAppendedId(Telephony.Sms.CONTENT_URI, i2);
              long l2 = m;
              if (n == 32)
              {
                bool = true;
                localObject4 = new ou(this, str1, (String)localObject4, l2, bool, localUri, i1, str2, l1);
                Log.v("SmsReceiverService", "sendFirstQueuedMessage " + localUri + ", address: " + str1 + ", threadId: " + m + ", subId = " + l1);
              }
              try
              {
                ((ot)localObject4).a(-1L);
                d = true;
                m = 1;
                ((Cursor)localObject1).close();
                if (m != 0) {
                  h();
                }
                return;
              }
              catch (MmsException localMmsException)
              {
                Log.e("SmsReceiverService", "sendFirstQueuedMessage: failed to send message " + localUri + ", caught ", localMmsException);
                d = false;
                a(localUri, 1, false);
                m = 0;
                sendBroadcast(new Intent("com.android.mms.transaction.SEND_MESSAGE", null, this, SmsReceiver.class));
              }
              boolean bool = false;
              continue;
            }
            continue;
            localObject2 = finally;
          }
          finally
          {
            ((Cursor)localObject1).close();
          }
        }
        m = 1;
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
    HandlerThread localHandlerThread = new HandlerThread("SmsReceiverService", 10);
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
    paramInt1 = 0;
    if (paramIntent != null) {
      paramInt1 = paramIntent.getIntExtra("result", 0);
    }
    j = paramInt1;
    if (j != 0) {
      Log.v("SmsReceiverService", "onStart: #" + paramInt2 + " mResultCode: " + j + " = " + a(j));
    }
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
      Log.v("SmsReceiverService", "handleMessage serviceId: " + i + " intent: " + paramMessage);
      String str;
      int j;
      if ((paramMessage != null) && (ga.C()))
      {
        str = paramMessage.getAction();
        j = paramMessage.getIntExtra("errorCode", 0);
        Log.v("SmsReceiverService", "handleMessage action: " + str + " error: " + j);
        if (!"com.android.mms.transaction.MESSAGE_SENT".equals(paramMessage.getAction())) {
          break label136;
        }
        SmsReceiverService.a(SmsReceiverService.this, paramMessage, j);
      }
      for (;;)
      {
        SmsReceiver.a(SmsReceiverService.this, i);
        return;
        label136:
        if ("android.provider.Telephony.SMS_DELIVER".equals(str)) {
          SmsReceiverService.b(SmsReceiverService.this, paramMessage, j);
        } else if ("android.intent.action.DATA_SMS_RECEIVED".equals(str)) {
          SmsReceiverService.b(SmsReceiverService.this, paramMessage, j);
        } else if ("android.intent.action.BOOT_COMPLETED".equals(str)) {
          SmsReceiverService.a(SmsReceiverService.this);
        } else if ("android.intent.action.SERVICE_STATE".equals(str)) {
          SmsReceiverService.a(SmsReceiverService.this, paramMessage);
        } else if ("com.android.mms.transaction.SEND_MESSAGE".endsWith(str)) {
          SmsReceiverService.b(SmsReceiverService.this);
        } else if ("com.android.mms.transaction.SEND_INACTIVE_MESSAGE".equals(str)) {
          SmsReceiverService.c(SmsReceiverService.this);
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.SmsReceiverService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */