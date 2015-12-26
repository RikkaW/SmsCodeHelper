package com.android.mms.transaction.flyme;

import aau;
import aba;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Telephony.MmsSms.PendingMessages;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import ow;
import oz;
import zn;

final class FlymeTransactionService$a
  extends Handler
{
  public FlymeTransactionService$a(FlymeTransactionService paramFlymeTransactionService, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  private String a(int paramInt)
  {
    if (paramInt == 0) {
      return "NOTIFICATION_TRANSACTION";
    }
    if (paramInt == 1) {
      return "RETRIEVE_TRANSACTION";
    }
    if (paramInt == 2) {
      return "SEND_TRANSACTION";
    }
    if (paramInt == 3) {
      return "READREC_TRANSACTION";
    }
    return "invalid transaction type";
  }
  
  private String a(Message paramMessage)
  {
    if (what == 100) {
      return "EVENT_QUIT";
    }
    if (what == 3) {
      return "EVENT_CONTINUE_MMS_CONNECTIVITY";
    }
    if (what == 1) {
      return "EVENT_TRANSACTION_REQUEST";
    }
    if (what == 4) {
      return "EVENT_HANDLE_NEXT_PENDING_TRANSACTION";
    }
    if (what == 5) {
      return "EVENT_NEW_INTENT";
    }
    return "unknown message.what";
  }
  
  private boolean a(ow paramow)
  {
    synchronized (FlymeTransactionService.c(a))
    {
      Iterator localIterator = FlymeTransactionService.d(a).iterator();
      while (localIterator.hasNext()) {
        if (((ow)localIterator.next()).a(paramow))
        {
          Log.v("FlymeTransactionService", "Transaction already pending: " + paramow.g());
          return true;
        }
      }
      localIterator = FlymeTransactionService.c(a).iterator();
      while (localIterator.hasNext()) {
        if (((ow)localIterator.next()).a(paramow))
        {
          Log.v("FlymeTransactionService", "Duplicated transaction: " + paramow.g());
          return true;
        }
      }
    }
    Log.v("FlymeTransactionService", "processTransaction: call beginMmsConnectivity...");
    if (a.a() == 1)
    {
      FlymeTransactionService.d(a).add(paramow);
      FlymeTransactionService.e(a).put(paramow.j(), paramow);
      Log.v("FlymeTransactionService", "processTransaction: connResult=APN_REQUEST_STARTED, defer transaction pending MMS connectivity");
      return true;
    }
    Log.v("FlymeTransactionService", "Adding transaction to 'mProcessing' list: " + paramow);
    FlymeTransactionService.c(a).add(paramow);
    FlymeTransactionService.a(a).put(paramow.j(), paramow);
    FlymeTransactionService.f(a).removeMessages(6);
    ??? = FlymeTransactionService.f(a).obtainMessage(6);
    FlymeTransactionService.f(a).sendMessageDelayed((Message)???, 30000000L);
    Log.v("FlymeTransactionService", "processTransaction: starting transaction " + paramow);
    paramow.a(a);
    paramow.a();
    return true;
  }
  
  public void a(ow arg1, oz paramoz)
  {
    Log.v("FlymeTransactionService", "processPendingTxn: transaction=" + ???);
    label295:
    for (;;)
    {
      int i;
      synchronized (FlymeTransactionService.c(a))
      {
        if (FlymeTransactionService.d(a).size() == 0) {
          break label295;
        }
        ??? = (ow)FlymeTransactionService.d(a).remove(0);
        FlymeTransactionService.e(a).remove(???.j());
        i = FlymeTransactionService.c(a).size();
        if (??? != null)
        {
          if (paramoz != null) {
            ???.a(paramoz);
          }
          paramoz = ???;
        }
      }
      while (i != 0) {
        try
        {
          i = ???.g();
          paramoz = ???;
          Log.v("FlymeTransactionService", "processPendingTxn: process " + i);
          paramoz = ???;
          if (a(???))
          {
            paramoz = ???;
            Log.v("FlymeTransactionService", "Started deferred processing of transaction  " + ???);
            return;
            ??? = finally;
            throw ???;
          }
          paramoz = null;
          a.stopSelf(i);
          return;
        }
        catch (IOException localIOException)
        {
          if ((paramoz != null) && (!FlymeTransactionService.d(a).contains(paramoz))) {}
          synchronized (FlymeTransactionService.c(a))
          {
            FlymeTransactionService.d(a).add(paramoz);
            FlymeTransactionService.e(a).put(paramoz.j(), paramoz);
            Log.w("FlymeTransactionService", localIOException.getMessage(), localIOException);
            return;
          }
        }
      }
      Log.v("FlymeTransactionService", "processPendingTxn: no more transaction, endMmsConnectivity");
      a.b();
      return;
    }
  }
  
  protected boolean a(Uri paramUri)
  {
    if (paramUri == null) {
      return false;
    }
    Object localObject1 = paramUri.getLastPathSegment();
    for (;;)
    {
      Object localObject3;
      synchronized (FlymeTransactionService.c(a))
      {
        localObject3 = FlymeTransactionService.d(a).iterator();
        if (!((Iterator)localObject3).hasNext()) {
          break label372;
        }
        localObject2 = (ow)((Iterator)localObject3).next();
        if (!((ow)localObject2).i().getLastPathSegment().equals(localObject1)) {
          continue;
        }
        Log.v("FlymeTransactionService", "Transaction in pending: " + ((ow)localObject2).g());
        switch (((ow)localObject2).c())
        {
        case 0: 
          Log.w("FlymeTransactionService", " cancelMessage Invalid transaction type: " + ((ow)localObject2).c() + "id is " + ((ow)localObject2).g());
          FlymeTransactionService.e(a).remove(((ow)localObject2).j());
          FlymeTransactionService.d(a).remove(localObject2);
          return true;
          zn.a().a(((ow)localObject2).i(), 128, -1);
        }
      }
      zn.a().a(((ow)localObject2).i(), 128, -1);
      continue;
      paramUri = new ContentValues(1);
      localObject1 = new ContentValues(1);
      long l = ContentUris.parseId(((ow)localObject2).i());
      paramUri.put("err_type", Integer.valueOf(19));
      ((ContentValues)localObject1).put("resp_st", Integer.valueOf(9527));
      a.getApplicationContext().getContentResolver().update(Telephony.MmsSms.PendingMessages.CONTENT_URI, paramUri, "msg_id=" + l, null);
      a.getApplicationContext().getContentResolver().update(((ow)localObject2).i(), (ContentValues)localObject1, null, null);
      continue;
      label372:
      Object localObject2 = FlymeTransactionService.c(a).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (ow)((Iterator)localObject2).next();
        if (((ow)localObject3).i().getLastPathSegment().equals(localObject1))
        {
          Log.v("FlymeTransactionService", "Transaction in Processlist: " + ((ow)localObject3).g());
          aba.a().b(((ow)localObject3).j());
          return true;
        }
      }
      Log.v("FlymeTransactionService", "Can't find transaction in Processlist & pendinglist: " + aau.b(Uri.class, paramUri, "toSafeString"));
      return false;
    }
  }
  
  /* Error */
  public void handleMessage(Message arg1)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 8
    //   3: aconst_null
    //   4: astore 7
    //   6: ldc_w 314
    //   9: iconst_2
    //   10: invokestatic 318	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   13: ifeq +43 -> 56
    //   16: ldc 76
    //   18: new 78	java/lang/StringBuilder
    //   21: dup
    //   22: invokespecial 81	java/lang/StringBuilder:<init>	()V
    //   25: ldc_w 320
    //   28: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: aload_1
    //   32: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   35: ldc_w 322
    //   38: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: aload_0
    //   42: aload_1
    //   43: invokespecial 324	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	(Landroid/os/Message;)Ljava/lang/String;
    //   46: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   52: invokestatic 104	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   55: pop
    //   56: aload_1
    //   57: getfield 34	android/os/Message:what	I
    //   60: lookupswitch	default:+60->120, 1:+127->187, 4:+926->986, 5:+90->150, 6:+117->177, 7:+939->999, 100:+109->169
    //   120: ldc 76
    //   122: new 78	java/lang/StringBuilder
    //   125: dup
    //   126: invokespecial 81	java/lang/StringBuilder:<init>	()V
    //   129: ldc_w 326
    //   132: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: aload_1
    //   136: getfield 34	android/os/Message:what	I
    //   139: invokevirtual 94	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   142: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   145: invokestatic 227	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   148: pop
    //   149: return
    //   150: aload_0
    //   151: getfield 12	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	Lcom/android/mms/transaction/flyme/FlymeTransactionService;
    //   154: aload_1
    //   155: getfield 330	android/os/Message:obj	Ljava/lang/Object;
    //   158: checkcast 332	android/content/Intent
    //   161: aload_1
    //   162: getfield 335	android/os/Message:arg1	I
    //   165: invokevirtual 338	com/android/mms/transaction/flyme/FlymeTransactionService:a	(Landroid/content/Intent;I)V
    //   168: return
    //   169: aload_0
    //   170: invokevirtual 342	com/android/mms/transaction/flyme/FlymeTransactionService$a:getLooper	()Landroid/os/Looper;
    //   173: invokevirtual 347	android/os/Looper:quit	()V
    //   176: return
    //   177: ldc 76
    //   179: ldc_w 349
    //   182: invokestatic 351	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   185: pop
    //   186: return
    //   187: aload_1
    //   188: getfield 335	android/os/Message:arg1	I
    //   191: istore_2
    //   192: aload 8
    //   194: astore 5
    //   196: aload_1
    //   197: getfield 330	android/os/Message:obj	Ljava/lang/Object;
    //   200: checkcast 353	ox
    //   203: astore 9
    //   205: aload 8
    //   207: astore 5
    //   209: ldc 76
    //   211: new 78	java/lang/StringBuilder
    //   214: dup
    //   215: invokespecial 81	java/lang/StringBuilder:<init>	()V
    //   218: ldc_w 355
    //   221: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   224: aload 9
    //   226: invokevirtual 357	ox:d	()Ljava/lang/String;
    //   229: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   232: ldc_w 359
    //   235: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: aload 9
    //   240: invokevirtual 361	ox:e	()Ljava/lang/String;
    //   243: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   246: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   249: invokestatic 104	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   252: pop
    //   253: aload 8
    //   255: astore 5
    //   257: aload 9
    //   259: invokevirtual 357	ox:d	()Ljava/lang/String;
    //   262: astore 6
    //   264: aload 6
    //   266: ifnull +185 -> 451
    //   269: aload 8
    //   271: astore 5
    //   273: new 363	oz
    //   276: dup
    //   277: aload 6
    //   279: aload 9
    //   281: invokevirtual 361	ox:e	()Ljava/lang/String;
    //   284: aload 9
    //   286: invokevirtual 365	ox:f	()I
    //   289: invokespecial 368	oz:<init>	(Ljava/lang/String;Ljava/lang/String;I)V
    //   292: astore 6
    //   294: aload 8
    //   296: astore 5
    //   298: aload 9
    //   300: invokevirtual 369	ox:a	()I
    //   303: istore_3
    //   304: aload 8
    //   306: astore 5
    //   308: ldc_w 314
    //   311: iconst_2
    //   312: invokestatic 318	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   315: ifeq +721 -> 1036
    //   318: aload 8
    //   320: astore 5
    //   322: ldc 76
    //   324: new 78	java/lang/StringBuilder
    //   327: dup
    //   328: invokespecial 81	java/lang/StringBuilder:<init>	()V
    //   331: ldc_w 371
    //   334: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   337: iload_3
    //   338: invokevirtual 94	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   341: ldc_w 373
    //   344: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   347: aload_0
    //   348: iload_3
    //   349: invokespecial 375	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	(I)Ljava/lang/String;
    //   352: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   355: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   358: invokestatic 104	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   361: pop
    //   362: goto +674 -> 1036
    //   365: aload 8
    //   367: astore 5
    //   369: ldc 76
    //   371: new 78	java/lang/StringBuilder
    //   374: dup
    //   375: invokespecial 81	java/lang/StringBuilder:<init>	()V
    //   378: ldc_w 377
    //   381: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   384: iload_2
    //   385: invokevirtual 94	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   388: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   391: invokestatic 227	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   394: pop
    //   395: iconst_0
    //   396: ifne -247 -> 149
    //   399: ldc_w 314
    //   402: iconst_2
    //   403: invokestatic 318	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   406: ifeq +29 -> 435
    //   409: ldc 76
    //   411: new 78	java/lang/StringBuilder
    //   414: dup
    //   415: invokespecial 81	java/lang/StringBuilder:<init>	()V
    //   418: ldc_w 379
    //   421: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   424: iload_2
    //   425: invokevirtual 94	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   428: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   431: invokestatic 104	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   434: pop
    //   435: aload_0
    //   436: getfield 12	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	Lcom/android/mms/transaction/flyme/FlymeTransactionService;
    //   439: invokevirtual 202	com/android/mms/transaction/flyme/FlymeTransactionService:b	()V
    //   442: aload_0
    //   443: getfield 12	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	Lcom/android/mms/transaction/flyme/FlymeTransactionService;
    //   446: iload_2
    //   447: invokevirtual 187	com/android/mms/transaction/flyme/FlymeTransactionService:stopSelf	(I)V
    //   450: return
    //   451: aload 8
    //   453: astore 5
    //   455: new 363	oz
    //   458: dup
    //   459: aload_0
    //   460: getfield 12	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	Lcom/android/mms/transaction/flyme/FlymeTransactionService;
    //   463: aconst_null
    //   464: invokespecial 382	oz:<init>	(Landroid/content/Context;Ljava/lang/String;)V
    //   467: astore 6
    //   469: goto -175 -> 294
    //   472: aload 8
    //   474: astore 5
    //   476: aload 9
    //   478: invokevirtual 384	ox:b	()Ljava/lang/String;
    //   481: astore 9
    //   483: aload 9
    //   485: ifnull +545 -> 1030
    //   488: aload 8
    //   490: astore 5
    //   492: new 386	pb
    //   495: dup
    //   496: aload_0
    //   497: getfield 12	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	Lcom/android/mms/transaction/flyme/FlymeTransactionService;
    //   500: iload_2
    //   501: aload 6
    //   503: aload 9
    //   505: invokespecial 389	pb:<init>	(Landroid/content/Context;ILoz;Ljava/lang/String;)V
    //   508: astore 6
    //   510: aload 6
    //   512: astore 5
    //   514: aload_0
    //   515: aload 5
    //   517: invokespecial 182	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	(Low;)Z
    //   520: istore 4
    //   522: iload 4
    //   524: ifne +351 -> 875
    //   527: iconst_0
    //   528: ifne -379 -> 149
    //   531: ldc_w 314
    //   534: iconst_2
    //   535: invokestatic 318	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   538: ifeq +29 -> 567
    //   541: ldc 76
    //   543: new 78	java/lang/StringBuilder
    //   546: dup
    //   547: invokespecial 81	java/lang/StringBuilder:<init>	()V
    //   550: ldc_w 379
    //   553: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   556: iload_2
    //   557: invokevirtual 94	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   560: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   563: invokestatic 104	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   566: pop
    //   567: aload_0
    //   568: getfield 12	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	Lcom/android/mms/transaction/flyme/FlymeTransactionService;
    //   571: invokevirtual 202	com/android/mms/transaction/flyme/FlymeTransactionService:b	()V
    //   574: aload_0
    //   575: getfield 12	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	Lcom/android/mms/transaction/flyme/FlymeTransactionService;
    //   578: iload_2
    //   579: invokevirtual 187	com/android/mms/transaction/flyme/FlymeTransactionService:stopSelf	(I)V
    //   582: return
    //   583: aload 8
    //   585: astore 5
    //   587: new 391	pc
    //   590: dup
    //   591: aload_0
    //   592: getfield 12	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	Lcom/android/mms/transaction/flyme/FlymeTransactionService;
    //   595: iload_2
    //   596: aload 6
    //   598: aload 9
    //   600: invokevirtual 384	ox:b	()Ljava/lang/String;
    //   603: invokespecial 392	pc:<init>	(Landroid/content/Context;ILoz;Ljava/lang/String;)V
    //   606: astore 6
    //   608: aload 6
    //   610: astore 5
    //   612: goto -98 -> 514
    //   615: astore 5
    //   617: aload 7
    //   619: astore 6
    //   621: aload 5
    //   623: astore 7
    //   625: aload 6
    //   627: astore 5
    //   629: ldc 76
    //   631: new 78	java/lang/StringBuilder
    //   634: dup
    //   635: invokespecial 81	java/lang/StringBuilder:<init>	()V
    //   638: ldc_w 394
    //   641: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   644: aload_1
    //   645: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   648: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   651: aload 7
    //   653: invokestatic 197	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   656: pop
    //   657: aload 6
    //   659: ifnull +68 -> 727
    //   662: aload 6
    //   664: aload_0
    //   665: getfield 12	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	Lcom/android/mms/transaction/flyme/FlymeTransactionService;
    //   668: invokevirtual 396	ow:b	(Loo;)V
    //   671: aload_0
    //   672: getfield 12	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	Lcom/android/mms/transaction/flyme/FlymeTransactionService;
    //   675: invokestatic 51	com/android/mms/transaction/flyme/FlymeTransactionService:c	(Lcom/android/mms/transaction/flyme/FlymeTransactionService;)Ljava/util/ArrayList;
    //   678: aload 6
    //   680: invokevirtual 190	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   683: ifeq +44 -> 727
    //   686: aload_0
    //   687: getfield 12	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	Lcom/android/mms/transaction/flyme/FlymeTransactionService;
    //   690: invokestatic 51	com/android/mms/transaction/flyme/FlymeTransactionService:c	(Lcom/android/mms/transaction/flyme/FlymeTransactionService;)Ljava/util/ArrayList;
    //   693: astore_1
    //   694: aload_1
    //   695: monitorenter
    //   696: aload_0
    //   697: getfield 12	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	Lcom/android/mms/transaction/flyme/FlymeTransactionService;
    //   700: invokestatic 136	com/android/mms/transaction/flyme/FlymeTransactionService:a	(Lcom/android/mms/transaction/flyme/FlymeTransactionService;)Ljava/util/HashMap;
    //   703: aload 6
    //   705: invokevirtual 121	ow:j	()Ljava/lang/String;
    //   708: invokevirtual 176	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   711: pop
    //   712: aload_0
    //   713: getfield 12	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	Lcom/android/mms/transaction/flyme/FlymeTransactionService;
    //   716: invokestatic 51	com/android/mms/transaction/flyme/FlymeTransactionService:c	(Lcom/android/mms/transaction/flyme/FlymeTransactionService;)Ljava/util/ArrayList;
    //   719: aload 6
    //   721: invokevirtual 229	java/util/ArrayList:remove	(Ljava/lang/Object;)Z
    //   724: pop
    //   725: aload_1
    //   726: monitorexit
    //   727: aload 6
    //   729: ifnonnull -580 -> 149
    //   732: ldc_w 314
    //   735: iconst_2
    //   736: invokestatic 318	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   739: ifeq +29 -> 768
    //   742: ldc 76
    //   744: new 78	java/lang/StringBuilder
    //   747: dup
    //   748: invokespecial 81	java/lang/StringBuilder:<init>	()V
    //   751: ldc_w 379
    //   754: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   757: iload_2
    //   758: invokevirtual 94	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   761: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   764: invokestatic 104	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   767: pop
    //   768: aload_0
    //   769: getfield 12	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	Lcom/android/mms/transaction/flyme/FlymeTransactionService;
    //   772: invokevirtual 202	com/android/mms/transaction/flyme/FlymeTransactionService:b	()V
    //   775: aload_0
    //   776: getfield 12	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	Lcom/android/mms/transaction/flyme/FlymeTransactionService;
    //   779: iload_2
    //   780: invokevirtual 187	com/android/mms/transaction/flyme/FlymeTransactionService:stopSelf	(I)V
    //   783: return
    //   784: aload 8
    //   786: astore 5
    //   788: new 398	pd
    //   791: dup
    //   792: aload_0
    //   793: getfield 12	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	Lcom/android/mms/transaction/flyme/FlymeTransactionService;
    //   796: iload_2
    //   797: aload 6
    //   799: aload 9
    //   801: invokevirtual 384	ox:b	()Ljava/lang/String;
    //   804: invokespecial 399	pd:<init>	(Landroid/content/Context;ILoz;Ljava/lang/String;)V
    //   807: astore 6
    //   809: aload 6
    //   811: astore 5
    //   813: goto -299 -> 514
    //   816: astore_1
    //   817: aload 5
    //   819: ifnonnull +54 -> 873
    //   822: ldc_w 314
    //   825: iconst_2
    //   826: invokestatic 318	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   829: ifeq +29 -> 858
    //   832: ldc 76
    //   834: new 78	java/lang/StringBuilder
    //   837: dup
    //   838: invokespecial 81	java/lang/StringBuilder:<init>	()V
    //   841: ldc_w 379
    //   844: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   847: iload_2
    //   848: invokevirtual 94	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   851: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   854: invokestatic 104	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   857: pop
    //   858: aload_0
    //   859: getfield 12	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	Lcom/android/mms/transaction/flyme/FlymeTransactionService;
    //   862: invokevirtual 202	com/android/mms/transaction/flyme/FlymeTransactionService:b	()V
    //   865: aload_0
    //   866: getfield 12	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	Lcom/android/mms/transaction/flyme/FlymeTransactionService;
    //   869: iload_2
    //   870: invokevirtual 187	com/android/mms/transaction/flyme/FlymeTransactionService:stopSelf	(I)V
    //   873: aload_1
    //   874: athrow
    //   875: ldc 76
    //   877: new 78	java/lang/StringBuilder
    //   880: dup
    //   881: invokespecial 81	java/lang/StringBuilder:<init>	()V
    //   884: ldc_w 401
    //   887: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   890: aload_1
    //   891: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   894: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   897: invokestatic 104	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   900: pop
    //   901: aload 5
    //   903: ifnonnull -754 -> 149
    //   906: ldc_w 314
    //   909: iconst_2
    //   910: invokestatic 318	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   913: ifeq +29 -> 942
    //   916: ldc 76
    //   918: new 78	java/lang/StringBuilder
    //   921: dup
    //   922: invokespecial 81	java/lang/StringBuilder:<init>	()V
    //   925: ldc_w 379
    //   928: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   931: iload_2
    //   932: invokevirtual 94	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   935: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   938: invokestatic 104	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   941: pop
    //   942: aload_0
    //   943: getfield 12	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	Lcom/android/mms/transaction/flyme/FlymeTransactionService;
    //   946: invokevirtual 202	com/android/mms/transaction/flyme/FlymeTransactionService:b	()V
    //   949: aload_0
    //   950: getfield 12	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	Lcom/android/mms/transaction/flyme/FlymeTransactionService;
    //   953: iload_2
    //   954: invokevirtual 187	com/android/mms/transaction/flyme/FlymeTransactionService:stopSelf	(I)V
    //   957: return
    //   958: astore 5
    //   960: aload_1
    //   961: monitorexit
    //   962: aload 5
    //   964: athrow
    //   965: astore_1
    //   966: ldc 76
    //   968: ldc_w 403
    //   971: aload_1
    //   972: invokestatic 405	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   975: pop
    //   976: goto -249 -> 727
    //   979: astore_1
    //   980: aload 6
    //   982: astore 5
    //   984: aload_1
    //   985: athrow
    //   986: aload_0
    //   987: aconst_null
    //   988: aload_1
    //   989: getfield 330	android/os/Message:obj	Ljava/lang/Object;
    //   992: checkcast 363	oz
    //   995: invokevirtual 407	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	(Low;Loz;)V
    //   998: return
    //   999: aload_0
    //   1000: aload_1
    //   1001: getfield 330	android/os/Message:obj	Ljava/lang/Object;
    //   1004: checkcast 332	android/content/Intent
    //   1007: invokevirtual 410	android/content/Intent:getData	()Landroid/net/Uri;
    //   1010: invokevirtual 412	com/android/mms/transaction/flyme/FlymeTransactionService$a:a	(Landroid/net/Uri;)Z
    //   1013: pop
    //   1014: goto -894 -> 120
    //   1017: astore_1
    //   1018: goto -201 -> 817
    //   1021: astore 7
    //   1023: aload 5
    //   1025: astore 6
    //   1027: goto -402 -> 625
    //   1030: aconst_null
    //   1031: astore 5
    //   1033: goto -519 -> 514
    //   1036: iload_3
    //   1037: tableswitch	default:+27->1064, 0:+-565->472, 1:+-454->583, 2:+-253->784
    //   1064: goto -699 -> 365
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1067	0	this	a
    //   191	763	2	i	int
    //   303	734	3	j	int
    //   520	3	4	bool	boolean
    //   194	417	5	localObject1	Object
    //   615	7	5	localException1	Exception
    //   627	275	5	localObject2	Object
    //   958	5	5	localObject3	Object
    //   982	50	5	localObject4	Object
    //   262	764	6	localObject5	Object
    //   4	648	7	localObject6	Object
    //   1021	1	7	localException2	Exception
    //   1	784	8	localObject7	Object
    //   203	597	9	localObject8	Object
    // Exception table:
    //   from	to	target	type
    //   196	205	615	java/lang/Exception
    //   209	253	615	java/lang/Exception
    //   257	264	615	java/lang/Exception
    //   273	294	615	java/lang/Exception
    //   298	304	615	java/lang/Exception
    //   308	318	615	java/lang/Exception
    //   322	362	615	java/lang/Exception
    //   369	395	615	java/lang/Exception
    //   455	469	615	java/lang/Exception
    //   476	483	615	java/lang/Exception
    //   492	510	615	java/lang/Exception
    //   587	608	615	java/lang/Exception
    //   788	809	615	java/lang/Exception
    //   196	205	816	finally
    //   209	253	816	finally
    //   257	264	816	finally
    //   273	294	816	finally
    //   298	304	816	finally
    //   308	318	816	finally
    //   322	362	816	finally
    //   369	395	816	finally
    //   455	469	816	finally
    //   476	483	816	finally
    //   492	510	816	finally
    //   587	608	816	finally
    //   629	657	816	finally
    //   788	809	816	finally
    //   984	986	816	finally
    //   696	727	958	finally
    //   960	962	958	finally
    //   662	696	965	java/lang/Throwable
    //   962	965	965	java/lang/Throwable
    //   662	696	979	finally
    //   962	965	979	finally
    //   966	976	979	finally
    //   514	522	1017	finally
    //   875	901	1017	finally
    //   514	522	1021	java/lang/Exception
    //   875	901	1021	java/lang/Exception
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.flyme.FlymeTransactionService.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */