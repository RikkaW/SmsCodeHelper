package com.android.mms.transaction;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class MessageStatusService
  extends IntentService
{
  private static final String[] a = { "_id" };
  private static final Uri b = Uri.parse("content://sms/status");
  
  public MessageStatusService()
  {
    super(MessageStatusService.class.getName());
    setIntentRedelivery(true);
  }
  
  /* Error */
  private android.telephony.SmsMessage a(Context paramContext, Uri paramUri, byte[] paramArrayOfByte, String paramString)
  {
    // Byte code:
    //   0: ldc 44
    //   2: ldc 46
    //   4: iconst_2
    //   5: anewarray 30	java/lang/Class
    //   8: dup
    //   9: iconst_0
    //   10: ldc 48
    //   12: aastore
    //   13: dup
    //   14: iconst_1
    //   15: ldc 12
    //   17: aastore
    //   18: iconst_2
    //   19: anewarray 50	java/lang/Object
    //   22: dup
    //   23: iconst_0
    //   24: aload_3
    //   25: aastore
    //   26: dup
    //   27: iconst_1
    //   28: aload 4
    //   30: aastore
    //   31: invokestatic 55	aau:a	(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;
    //   34: checkcast 44	android/telephony/SmsMessage
    //   37: astore 4
    //   39: aload 4
    //   41: ifnonnull +5 -> 46
    //   44: aconst_null
    //   45: areturn
    //   46: aload_1
    //   47: invokevirtual 61	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   50: aload_2
    //   51: getstatic 16	com/android/mms/transaction/MessageStatusService:a	[Ljava/lang/String;
    //   54: aconst_null
    //   55: aconst_null
    //   56: aconst_null
    //   57: invokevirtual 67	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   60: astore_3
    //   61: aload_3
    //   62: invokeinterface 73 1 0
    //   67: ifeq +147 -> 214
    //   70: aload_3
    //   71: iconst_0
    //   72: invokeinterface 77 2 0
    //   77: istore 5
    //   79: getstatic 26	com/android/mms/transaction/MessageStatusService:b	Landroid/net/Uri;
    //   82: iload 5
    //   84: i2l
    //   85: invokestatic 83	android/content/ContentUris:withAppendedId	(Landroid/net/Uri;J)Landroid/net/Uri;
    //   88: astore 7
    //   90: aload 4
    //   92: invokevirtual 87	android/telephony/SmsMessage:getStatus	()I
    //   95: istore 5
    //   97: aload 4
    //   99: invokevirtual 90	android/telephony/SmsMessage:isStatusReportMessage	()Z
    //   102: istore 6
    //   104: new 92	android/content/ContentValues
    //   107: dup
    //   108: iconst_2
    //   109: invokespecial 95	android/content/ContentValues:<init>	(I)V
    //   112: astore 8
    //   114: ldc 97
    //   116: iconst_3
    //   117: invokestatic 103	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   120: ifeq +46 -> 166
    //   123: aload_0
    //   124: new 105	java/lang/StringBuilder
    //   127: dup
    //   128: invokespecial 107	java/lang/StringBuilder:<init>	()V
    //   131: ldc 109
    //   133: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   136: aload_2
    //   137: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   140: ldc 118
    //   142: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: iload 5
    //   147: invokevirtual 121	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   150: ldc 123
    //   152: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   155: iload 6
    //   157: invokevirtual 126	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   160: invokevirtual 129	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   163: invokespecial 131	com/android/mms/transaction/MessageStatusService:b	(Ljava/lang/String;)V
    //   166: aload 8
    //   168: ldc -123
    //   170: iload 5
    //   172: invokestatic 139	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   175: invokevirtual 143	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   178: aload 8
    //   180: ldc -111
    //   182: invokestatic 151	java/lang/System:currentTimeMillis	()J
    //   185: invokestatic 156	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   188: invokevirtual 159	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   191: aload_1
    //   192: invokevirtual 61	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   195: aload 7
    //   197: aload 8
    //   199: aconst_null
    //   200: aconst_null
    //   201: invokevirtual 163	android/content/ContentResolver:update	(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   204: pop
    //   205: aload_3
    //   206: invokeinterface 166 1 0
    //   211: aload 4
    //   213: areturn
    //   214: aload_0
    //   215: new 105	java/lang/StringBuilder
    //   218: dup
    //   219: invokespecial 107	java/lang/StringBuilder:<init>	()V
    //   222: ldc -88
    //   224: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   227: aload_2
    //   228: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   231: invokevirtual 129	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   234: invokespecial 170	com/android/mms/transaction/MessageStatusService:a	(Ljava/lang/String;)V
    //   237: goto -32 -> 205
    //   240: astore_1
    //   241: aload_3
    //   242: invokeinterface 166 1 0
    //   247: aload_1
    //   248: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	249	0	this	MessageStatusService
    //   0	249	1	paramContext	Context
    //   0	249	2	paramUri	Uri
    //   0	249	3	paramArrayOfByte	byte[]
    //   0	249	4	paramString	String
    //   77	94	5	i	int
    //   102	54	6	bool	boolean
    //   88	108	7	localUri	Uri
    //   112	86	8	localContentValues	ContentValues
    // Exception table:
    //   from	to	target	type
    //   61	166	240	finally
    //   166	205	240	finally
    //   214	237	240	finally
  }
  
  private void a(String paramString)
  {
    Log.e("MessageStatusReceiver", "[MessageStatusReceiver] " + paramString);
  }
  
  private boolean a(Context paramContext, Uri paramUri, int paramInt, long paramLong)
  {
    Cursor localCursor = paramContext.getContentResolver().query(paramUri, a, " type = 2 ", null, null);
    if (localCursor != null) {
      try
      {
        if (localCursor.moveToFirst())
        {
          int i = localCursor.getInt(0);
          Uri localUri = ContentUris.withAppendedId(b, i);
          ContentValues localContentValues = new ContentValues(3);
          if (Log.isLoggable("Mms", 3)) {
            b("updateMessageStatus: msgUrl=" + paramUri + ", status=" + paramInt);
          }
          localContentValues.put("status", Integer.valueOf(paramInt));
          localContentValues.put("report_date", Long.valueOf(paramLong));
          localContentValues.put("date_sent", Long.valueOf(System.currentTimeMillis()));
          paramContext.getContentResolver().update(localUri, localContentValues, null, null);
        }
        for (boolean bool = true;; bool = false)
        {
          return bool;
          a("Can't find message for status update: " + paramUri);
        }
        return false;
      }
      finally
      {
        localCursor.close();
      }
    }
  }
  
  private void b(String paramString)
  {
    Log.d("MessageStatusReceiver", "[MessageStatusReceiver] " + paramString);
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    if (paramIntent.getAction().equals("com.android.mms.transaction.MessageStatusReceiver.MESSAGE_STATUS_RECEIVED_MZ"))
    {
      Uri localUri = paramIntent.getData();
      int i = paramIntent.getIntExtra("status", 0);
      long l = paramIntent.getLongExtra("date", 0L);
      a(getApplicationContext(), localUri, i, l);
      return;
    }
    a(this, paramIntent.getData(), paramIntent.getByteArrayExtra("pdu"), paramIntent.getStringExtra("format"));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessageStatusService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */