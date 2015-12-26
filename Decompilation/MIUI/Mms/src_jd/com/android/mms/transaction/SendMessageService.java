package com.android.mms.transaction;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.provider.Telephony.MmsSms.PendingMessages;
import android.provider.Telephony.Threads;
import android.text.TextUtils;
import com.android.mms.data.Conversation;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.utils.logger.MyLog;
import java.util.ArrayList;

public class SendMessageService
  extends IntentService
{
  private static Uri TIMED_MMS_URI = Uri.parse("content://mms/sent");
  private static Uri TIMED_SMS_URI = Uri.parse("content://sms/sent");
  
  public SendMessageService()
  {
    super("SendMessageService");
  }
  
  /* Error */
  private void handleSendTimedMessages()
  {
    // Byte code:
    //   0: invokestatic 37	java/lang/System:currentTimeMillis	()J
    //   3: lstore 5
    //   5: new 39	java/util/ArrayList
    //   8: dup
    //   9: invokespecial 41	java/util/ArrayList:<init>	()V
    //   12: astore 7
    //   14: new 39	java/util/ArrayList
    //   17: dup
    //   18: invokespecial 41	java/util/ArrayList:<init>	()V
    //   21: astore 9
    //   23: aload_0
    //   24: invokevirtual 45	com/android/mms/transaction/SendMessageService:getContentResolver	()Landroid/content/ContentResolver;
    //   27: astore 8
    //   29: getstatic 19	com/android/mms/transaction/SendMessageService:TIMED_SMS_URI	Landroid/net/Uri;
    //   32: astore 10
    //   34: new 47	java/lang/StringBuilder
    //   37: dup
    //   38: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   41: ldc 50
    //   43: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: lload 5
    //   48: invokevirtual 57	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   51: ldc 59
    //   53: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   56: invokestatic 64	com/android/mms/MmsApp:getStartupTime	()J
    //   59: invokevirtual 57	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   62: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   65: astore 11
    //   67: aload_0
    //   68: aload 8
    //   70: aload 10
    //   72: iconst_2
    //   73: anewarray 70	java/lang/String
    //   76: dup
    //   77: iconst_0
    //   78: ldc 72
    //   80: aastore
    //   81: dup
    //   82: iconst_1
    //   83: ldc 74
    //   85: aastore
    //   86: aload 11
    //   88: aconst_null
    //   89: aconst_null
    //   90: invokestatic 80	android/database/sqlite/SqliteWrapper:query	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   93: astore 8
    //   95: aload 8
    //   97: ifnull +103 -> 200
    //   100: ldc 27
    //   102: new 47	java/lang/StringBuilder
    //   105: dup
    //   106: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   109: ldc 82
    //   111: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: aload 8
    //   116: invokeinterface 88 1 0
    //   121: invokevirtual 91	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   124: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   127: invokestatic 97	com/xiaomi/mms/utils/logger/MyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   130: aload 8
    //   132: invokeinterface 101 1 0
    //   137: ifeq +56 -> 193
    //   140: aload 7
    //   142: aload 8
    //   144: iconst_0
    //   145: invokeinterface 105 2 0
    //   150: invokestatic 111	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   153: invokeinterface 117 2 0
    //   158: pop
    //   159: aload 9
    //   161: aload 8
    //   163: iconst_1
    //   164: invokeinterface 105 2 0
    //   169: invokestatic 111	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   172: invokeinterface 117 2 0
    //   177: pop
    //   178: goto -48 -> 130
    //   181: astore 7
    //   183: aload 8
    //   185: invokeinterface 120 1 0
    //   190: aload 7
    //   192: athrow
    //   193: aload 8
    //   195: invokeinterface 120 1 0
    //   200: new 122	android/content/ContentValues
    //   203: dup
    //   204: iconst_3
    //   205: invokespecial 125	android/content/ContentValues:<init>	(I)V
    //   208: astore 8
    //   210: aload 8
    //   212: ldc 127
    //   214: iconst_0
    //   215: invokestatic 132	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   218: invokevirtual 136	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   221: aload 8
    //   223: ldc -118
    //   225: bipush 6
    //   227: invokestatic 132	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   230: invokevirtual 136	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   233: aload 7
    //   235: invokeinterface 142 1 0
    //   240: astore 10
    //   242: iconst_0
    //   243: istore_2
    //   244: iconst_0
    //   245: istore_1
    //   246: aload 10
    //   248: invokeinterface 147 1 0
    //   253: ifeq +74 -> 327
    //   256: aload 10
    //   258: invokeinterface 151 1 0
    //   263: checkcast 107	java/lang/Long
    //   266: astore 11
    //   268: aload 8
    //   270: ldc -103
    //   272: iload_1
    //   273: i2l
    //   274: lload 5
    //   276: ladd
    //   277: invokestatic 111	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   280: invokevirtual 156	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   283: aload_0
    //   284: aload_0
    //   285: invokevirtual 45	com/android/mms/transaction/SendMessageService:getContentResolver	()Landroid/content/ContentResolver;
    //   288: getstatic 19	com/android/mms/transaction/SendMessageService:TIMED_SMS_URI	Landroid/net/Uri;
    //   291: aload 8
    //   293: new 47	java/lang/StringBuilder
    //   296: dup
    //   297: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   300: ldc -98
    //   302: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   305: aload 11
    //   307: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   310: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   313: aconst_null
    //   314: invokestatic 165	android/database/sqlite/SqliteWrapper:update	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   317: iload_2
    //   318: iadd
    //   319: istore_2
    //   320: iload_1
    //   321: iconst_1
    //   322: iadd
    //   323: istore_1
    //   324: goto -78 -> 246
    //   327: iload_2
    //   328: ifle +12 -> 340
    //   331: aload_0
    //   332: aload 9
    //   334: invokestatic 171	com/android/mms/ui/MessageUtils:getSlotId	(Ljava/util/List;)I
    //   337: invokestatic 177	com/android/mms/util/MSimUtils:sendQueuedMessage	(Landroid/content/Context;I)V
    //   340: aload 9
    //   342: invokeinterface 180 1 0
    //   347: aload 7
    //   349: invokeinterface 180 1 0
    //   354: aload_0
    //   355: invokevirtual 45	com/android/mms/transaction/SendMessageService:getContentResolver	()Landroid/content/ContentResolver;
    //   358: astore 9
    //   360: getstatic 23	com/android/mms/transaction/SendMessageService:TIMED_MMS_URI	Landroid/net/Uri;
    //   363: astore 10
    //   365: new 47	java/lang/StringBuilder
    //   368: dup
    //   369: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   372: ldc -74
    //   374: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   377: lload 5
    //   379: invokevirtual 57	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   382: ldc -72
    //   384: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   387: invokestatic 64	com/android/mms/MmsApp:getStartupTime	()J
    //   390: invokevirtual 57	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   393: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   396: astore 11
    //   398: aload_0
    //   399: aload 9
    //   401: aload 10
    //   403: iconst_1
    //   404: anewarray 70	java/lang/String
    //   407: dup
    //   408: iconst_0
    //   409: ldc 72
    //   411: aastore
    //   412: aload 11
    //   414: aconst_null
    //   415: aconst_null
    //   416: invokestatic 80	android/database/sqlite/SqliteWrapper:query	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   419: astore 9
    //   421: aload 9
    //   423: ifnull +84 -> 507
    //   426: ldc 27
    //   428: new 47	java/lang/StringBuilder
    //   431: dup
    //   432: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   435: ldc -70
    //   437: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   440: aload 9
    //   442: invokeinterface 88 1 0
    //   447: invokevirtual 91	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   450: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   453: invokestatic 97	com/xiaomi/mms/utils/logger/MyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   456: aload 9
    //   458: invokeinterface 101 1 0
    //   463: ifeq +37 -> 500
    //   466: aload 7
    //   468: aload 9
    //   470: iconst_0
    //   471: invokeinterface 105 2 0
    //   476: invokestatic 111	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   479: invokeinterface 117 2 0
    //   484: pop
    //   485: goto -29 -> 456
    //   488: astore 7
    //   490: aload 9
    //   492: invokeinterface 120 1 0
    //   497: aload 7
    //   499: athrow
    //   500: aload 9
    //   502: invokeinterface 120 1 0
    //   507: aload 8
    //   509: invokevirtual 187	android/content/ContentValues:clear	()V
    //   512: aload 8
    //   514: ldc 127
    //   516: iconst_0
    //   517: invokestatic 132	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   520: invokevirtual 136	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   523: aload 8
    //   525: ldc -67
    //   527: iconst_4
    //   528: invokestatic 132	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   531: invokevirtual 136	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   534: aload 7
    //   536: invokeinterface 142 1 0
    //   541: astore 7
    //   543: iconst_0
    //   544: istore 4
    //   546: iload_1
    //   547: istore_3
    //   548: iload 4
    //   550: istore_1
    //   551: aload 7
    //   553: invokeinterface 147 1 0
    //   558: ifeq +78 -> 636
    //   561: aload 7
    //   563: invokeinterface 151 1 0
    //   568: checkcast 107	java/lang/Long
    //   571: astore 9
    //   573: aload 8
    //   575: ldc -65
    //   577: iload_3
    //   578: i2l
    //   579: lload 5
    //   581: ladd
    //   582: invokestatic 111	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   585: invokevirtual 156	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   588: aload_0
    //   589: aload_0
    //   590: invokevirtual 45	com/android/mms/transaction/SendMessageService:getContentResolver	()Landroid/content/ContentResolver;
    //   593: getstatic 23	com/android/mms/transaction/SendMessageService:TIMED_MMS_URI	Landroid/net/Uri;
    //   596: aload 8
    //   598: new 47	java/lang/StringBuilder
    //   601: dup
    //   602: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   605: ldc -98
    //   607: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   610: aload 9
    //   612: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   615: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   618: aconst_null
    //   619: invokestatic 165	android/database/sqlite/SqliteWrapper:update	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   622: istore 4
    //   624: iload_3
    //   625: iconst_1
    //   626: iadd
    //   627: istore_3
    //   628: iload 4
    //   630: iload_1
    //   631: iadd
    //   632: istore_1
    //   633: goto -82 -> 551
    //   636: iload_1
    //   637: ifle +18 -> 655
    //   640: aload_0
    //   641: new 193	android/content/Intent
    //   644: dup
    //   645: aload_0
    //   646: ldc -61
    //   648: invokespecial 198	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   651: invokevirtual 202	com/android/mms/transaction/SendMessageService:startService	(Landroid/content/Intent;)Landroid/content/ComponentName;
    //   654: pop
    //   655: iload_2
    //   656: ifgt +7 -> 663
    //   659: iload_1
    //   660: ifle +7 -> 667
    //   663: aload_0
    //   664: invokestatic 208	com/android/mms/transaction/TimedMessageReceiver:scheduleNextTimedMsg	(Landroid/content/Context;)V
    //   667: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	668	0	this	SendMessageService
    //   245	415	1	i	int
    //   243	413	2	j	int
    //   547	81	3	k	int
    //   544	88	4	m	int
    //   3	577	5	l	long
    //   12	129	7	localArrayList	ArrayList
    //   181	286	7	localObject1	Object
    //   488	47	7	localObject2	Object
    //   541	21	7	localIterator	java.util.Iterator
    //   27	570	8	localObject3	Object
    //   21	590	9	localObject4	Object
    //   32	370	10	localObject5	Object
    //   65	348	11	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   130	178	181	finally
    //   456	485	488	finally
  }
  
  private void markThreadAsRead(String paramString)
  {
    paramString = Conversation.get(Telephony.Threads.getOrCreateThreadId(this, paramString));
    if (paramString != null) {
      paramString.markAsReadSync();
    }
  }
  
  public static void startDelete(Context paramContext, Uri paramUri)
  {
    Intent localIntent = new Intent(paramContext, SendMessageService.class);
    localIntent.setAction("com.android.mms.transaction.ACTION_DELETE_BACKGROUND");
    localIntent.putExtra("extra_uri", paramUri.toString());
    paramContext.startService(localIntent);
  }
  
  public static void startMarkFail(Context paramContext, ArrayList<String> paramArrayList1, ArrayList<String> paramArrayList2)
  {
    Intent localIntent = new Intent(paramContext, SendMessageService.class);
    localIntent.setAction("com.android.mms.transaction.MARK_FAIL_BACKGROUND");
    localIntent.putStringArrayListExtra("extra_mms_msg_ids", paramArrayList1);
    localIntent.putStringArrayListExtra("extra_sms_msg_ids", paramArrayList2);
    paramContext.startService(localIntent);
  }
  
  public static void startMarkRead(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent(paramContext, SendMessageService.class);
    localIntent.setAction("com.android.mms.transaction.ACTION_MARK_READ_BACKGROUND");
    localIntent.putExtra("extra_address", paramString);
    paramContext.startService(localIntent);
  }
  
  public static void startReSendMms(Context paramContext, ArrayList<String> paramArrayList)
  {
    Intent localIntent = new Intent(paramContext, SendMessageService.class);
    localIntent.setAction("com.android.mms.transaction.RESEND_MMS_BACKGROUND");
    localIntent.putStringArrayListExtra("extra_mms_msg_ids", paramArrayList);
    paramContext.startService(localIntent);
  }
  
  public static void startReSendSms(Context paramContext, ArrayList<String> paramArrayList, int paramInt)
  {
    Intent localIntent = new Intent(paramContext, SendMessageService.class);
    localIntent.setAction("com.android.sms.transaction.RESEND_SMS_BACKGROUND");
    localIntent.putStringArrayListExtra("extra_sms_msg_ids", paramArrayList);
    localIntent.putExtra(MSimUtils.SLOT_ID, paramInt);
    paramContext.startService(localIntent);
  }
  
  public static void startSend(Context paramContext, String paramString1, String paramString2, boolean paramBoolean, int paramInt)
  {
    Intent localIntent = new Intent(paramContext, SendMessageService.class);
    localIntent.setAction("com.android.mms.transaction.SEND_SMS_BACKGROUND");
    localIntent.putExtra("extra_address", paramString1);
    localIntent.putExtra("extra_text", paramString2);
    localIntent.putExtra("extra_send_by_mx", paramBoolean);
    localIntent.putExtra(MSimUtils.SLOT_ID, paramInt);
    paramContext.startService(localIntent);
  }
  
  public static void startSendTimedMsg(Context paramContext)
  {
    Intent localIntent = new Intent(paramContext, SendMessageService.class);
    localIntent.setAction("com.android.mms.transaction.ACTION_SEND_TIMED_MESSAGE");
    paramContext.startService(localIntent);
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    Object localObject1 = paramIntent.getAction();
    Object localObject2;
    int i;
    long l;
    if ("com.android.mms.transaction.SEND_SMS_BACKGROUND".equals(localObject1))
    {
      localObject1 = paramIntent.getStringExtra("extra_address");
      localObject2 = paramIntent.getStringExtra("extra_text");
      boolean bool = paramIntent.getBooleanExtra("extra_send_by_mx", false);
      i = MSimUtils.getSlotIdFromIntent(paramIntent);
      markThreadAsRead((String)localObject1);
      l = Telephony.Threads.getOrCreateThreadId(this, (String)localObject1);
      paramIntent = new SmsMessageSender(this, new String[] { localObject1 }, (String)localObject2, l, 0L, bool, i);
    }
    label298:
    label449:
    do
    {
      do
      {
        do
        {
          try
          {
            paramIntent.sendMessage();
            return;
          }
          catch (Exception paramIntent)
          {
            MyLog.e("SendMessageService", "Failed to send SMS message, threadId=" + l, paramIntent);
            return;
          }
          if (!"com.android.sms.transaction.RESEND_SMS_BACKGROUND".equals(localObject1)) {
            break label298;
          }
          localObject1 = new ContentValues();
          i = MSimUtils.getSlotIdFromIntent(paramIntent);
          l = System.currentTimeMillis();
          paramIntent = paramIntent.getStringArrayListExtra("extra_sms_msg_ids");
          localObject2 = "_id IN (" + TextUtils.join(",", paramIntent) + ")";
          if (paramIntent.size() <= 0) {
            break;
          }
          ((ContentValues)localObject1).put("date", Long.valueOf(l));
          ((ContentValues)localObject1).put("timed", Integer.valueOf(0));
          ((ContentValues)localObject1).put("type", Integer.valueOf(6));
          MyLog.v("SendMessageService", "smsWhere == " + (String)localObject2);
        } while (SqliteWrapper.update(this, getContentResolver(), TIMED_SMS_URI, (ContentValues)localObject1, (String)localObject2, null) <= 0);
        MSimUtils.sendQueuedMessage(this, i);
        return;
        MyLog.v("SendMessageService", "smsWhere == NULL");
        return;
        if (!"com.android.mms.transaction.RESEND_MMS_BACKGROUND".equals(localObject1)) {
          break label449;
        }
        localObject1 = new ContentValues();
        l = System.currentTimeMillis();
        paramIntent = paramIntent.getStringArrayListExtra("extra_mms_msg_ids");
        localObject2 = "_id IN (" + TextUtils.join(",", paramIntent) + ")";
        if (paramIntent.size() <= 0) {
          break;
        }
        ((ContentValues)localObject1).put("date_full", Long.valueOf(l));
        ((ContentValues)localObject1).put("timed", Integer.valueOf(0));
        ((ContentValues)localObject1).put("msg_box", Integer.valueOf(4));
      } while (SqliteWrapper.update(this, getContentResolver(), TIMED_MMS_URI, (ContentValues)localObject1, (String)localObject2, null) <= 0);
      startService(new Intent(this, TransactionService.class));
      return;
      MyLog.v("SendMessageService", "mmsWhere == NULL");
      return;
      if ("com.android.mms.transaction.MARK_FAIL_BACKGROUND".equals(localObject1))
      {
        localObject1 = new ContentValues();
        l = System.currentTimeMillis();
        localObject2 = paramIntent.getStringArrayListExtra("extra_mms_msg_ids");
        String str1 = "_id IN (" + TextUtils.join(",", (Iterable)localObject2) + ")";
        String str2 = "msg_id IN (" + TextUtils.join(",", (Iterable)localObject2) + ")";
        if (((ArrayList)localObject2).size() > 0)
        {
          ((ContentValues)localObject1).put("date_full", Long.valueOf(l));
          ((ContentValues)localObject1).put("timed", Integer.valueOf(0));
          ((ContentValues)localObject1).put("msg_box", Integer.valueOf(4));
          SqliteWrapper.update(this, getContentResolver(), TIMED_MMS_URI, (ContentValues)localObject1, str1, null);
          ((ContentValues)localObject1).clear();
          ((ContentValues)localObject1).put("err_type", Integer.valueOf(10));
          SqliteWrapper.update(this, getContentResolver(), Telephony.MmsSms.PendingMessages.CONTENT_URI, (ContentValues)localObject1, str2, null);
        }
        for (;;)
        {
          paramIntent = paramIntent.getStringArrayListExtra("extra_sms_msg_ids");
          localObject2 = "_id IN (" + TextUtils.join(",", paramIntent) + ")";
          if (paramIntent.size() <= 0) {
            break;
          }
          MyLog.v("SendMessageService", "mark sms fail");
          ((ContentValues)localObject1).clear();
          ((ContentValues)localObject1).put("date", Long.valueOf(l));
          ((ContentValues)localObject1).put("timed", Integer.valueOf(0));
          ((ContentValues)localObject1).put("type", Integer.valueOf(5));
          SqliteWrapper.update(this, getContentResolver(), TIMED_SMS_URI, (ContentValues)localObject1, (String)localObject2, null);
          return;
          MyLog.v("SendMessageService", "mark fail == NULL");
        }
      }
      if ("com.android.mms.transaction.ACTION_MARK_READ_BACKGROUND".equals(localObject1))
      {
        markThreadAsRead(paramIntent.getStringExtra("extra_address"));
        return;
      }
      if ("com.android.mms.transaction.ACTION_DELETE_BACKGROUND".equals(localObject1))
      {
        paramIntent = Uri.parse(paramIntent.getStringExtra("extra_uri"));
        getContentResolver().delete(paramIntent, null, null);
        return;
      }
    } while (!"com.android.mms.transaction.ACTION_SEND_TIMED_MESSAGE".equals(localObject1));
    handleSendTimedMessages();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.SendMessageService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */