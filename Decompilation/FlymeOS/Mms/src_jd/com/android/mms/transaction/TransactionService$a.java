package com.android.mms.transaction;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import ow;

final class TransactionService$a
  extends Handler
{
  public TransactionService$a(TransactionService paramTransactionService, Looper paramLooper)
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
    if (what == 2) {
      return "EVENT_TRANSACTION_PROCESSED";
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
  
  private boolean b(ow paramow)
  {
    Log.v("TransactionService", "process Transaction");
    synchronized (TransactionService.a(a))
    {
      Iterator localIterator = TransactionService.b(a).iterator();
      while (localIterator.hasNext()) {
        if (((ow)localIterator.next()).a(paramow))
        {
          Log.v("TransactionService", "Transaction already pending: " + paramow.g());
          return true;
        }
      }
      localIterator = TransactionService.a(a).iterator();
      while (localIterator.hasNext()) {
        if (((ow)localIterator.next()).a(paramow))
        {
          Log.v("TransactionService", "Duplicated transaction: " + paramow.g());
          return true;
        }
      }
    }
    Log.v("TransactionService", "----Directly Adding transaction to 'mProcessing' list: " + paramow);
    TransactionService.a(a).add(paramow);
    Log.v("TransactionService", "processTransaction: starting transaction " + paramow);
    paramow.a(a);
    paramow.a();
    if (TransactionService.c(a).hasMessages(6)) {
      TransactionService.c(a).removeMessages(6);
    }
    TransactionService.a(a, paramow);
    ??? = TransactionService.c(a).obtainMessage(6);
    obj = TransactionService.b(a, paramow);
    TransactionService.c(a).sendMessageDelayed((Message)???, 600000L);
    return true;
  }
  
  public void a(ow paramow)
  {
    Log.v("TransactionService", "processPendingTxn: transaction=" + paramow);
    label171:
    for (;;)
    {
      synchronized (TransactionService.a(a))
      {
        if (TransactionService.b(a).size() == 0) {
          break label171;
        }
        paramow = (ow)TransactionService.b(a).remove(0);
        TransactionService.a(a).size();
        if (paramow == null) {}
      }
      try
      {
        int i = paramow.g();
        Log.v("TransactionService", "processPendingTxn: process " + i);
        if (b(paramow))
        {
          Log.v("TransactionService", "Started deferred processing of transaction  " + paramow);
          return;
          paramow = finally;
          throw paramow;
        }
        a.stopSelf(i);
        return;
      }
      catch (IOException paramow)
      {
        Log.w("TransactionService", paramow.getMessage(), paramow);
        return;
      }
    }
  }
  
  /* Error */
  public void handleMessage(Message arg1)
  {
    // Byte code:
    //   0: ldc 50
    //   2: new 85	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   9: ldc -65
    //   11: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   14: aload_1
    //   15: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   18: ldc -63
    //   20: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: aload_0
    //   24: aload_1
    //   25: invokespecial 195	com/android/mms/transaction/TransactionService$a:a	(Landroid/os/Message;)Ljava/lang/String;
    //   28: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   34: invokestatic 58	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   37: pop
    //   38: aconst_null
    //   39: astore 7
    //   41: aload_1
    //   42: getfield 34	android/os/Message:what	I
    //   45: lookupswitch	default:+59->104, 1:+156->201, 2:+115->160, 4:+1021->1066, 5:+88->133, 6:+142->187, 100:+107->152
    //   104: ldc 50
    //   106: new 85	java/lang/StringBuilder
    //   109: dup
    //   110: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   113: ldc -59
    //   115: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: aload_1
    //   119: getfield 34	android/os/Message:what	I
    //   122: invokevirtual 101	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   125: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   128: invokestatic 199	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   131: pop
    //   132: return
    //   133: aload_0
    //   134: getfield 12	com/android/mms/transaction/TransactionService$a:a	Lcom/android/mms/transaction/TransactionService;
    //   137: aload_1
    //   138: getfield 149	android/os/Message:obj	Ljava/lang/Object;
    //   141: checkcast 201	android/content/Intent
    //   144: aload_1
    //   145: getfield 204	android/os/Message:arg1	I
    //   148: invokevirtual 207	com/android/mms/transaction/TransactionService:a	(Landroid/content/Intent;I)V
    //   151: return
    //   152: aload_0
    //   153: invokevirtual 211	com/android/mms/transaction/TransactionService$a:getLooper	()Landroid/os/Looper;
    //   156: invokevirtual 216	android/os/Looper:quit	()V
    //   159: return
    //   160: ldc 50
    //   162: ldc 38
    //   164: invokestatic 219	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   167: pop
    //   168: aload_0
    //   169: getfield 12	com/android/mms/transaction/TransactionService$a:a	Lcom/android/mms/transaction/TransactionService;
    //   172: aload_1
    //   173: getfield 149	android/os/Message:obj	Ljava/lang/Object;
    //   176: checkcast 201	android/content/Intent
    //   179: aload_1
    //   180: getfield 204	android/os/Message:arg1	I
    //   183: invokestatic 222	com/android/mms/transaction/TransactionService:a	(Lcom/android/mms/transaction/TransactionService;Landroid/content/Intent;I)V
    //   186: return
    //   187: aload_0
    //   188: getfield 12	com/android/mms/transaction/TransactionService$a:a	Lcom/android/mms/transaction/TransactionService;
    //   191: aload_1
    //   192: getfield 149	android/os/Message:obj	Ljava/lang/Object;
    //   195: checkcast 224	java/lang/String
    //   198: invokestatic 227	com/android/mms/transaction/TransactionService:a	(Lcom/android/mms/transaction/TransactionService;Ljava/lang/String;)V
    //   201: ldc 50
    //   203: ldc 40
    //   205: invokestatic 219	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   208: pop
    //   209: aload_1
    //   210: getfield 204	android/os/Message:arg1	I
    //   213: istore_2
    //   214: aload_1
    //   215: getfield 230	android/os/Message:arg2	I
    //   218: i2l
    //   219: lstore 4
    //   221: aload_1
    //   222: getfield 149	android/os/Message:obj	Ljava/lang/Object;
    //   225: checkcast 232	ox
    //   228: astore 8
    //   230: ldc 50
    //   232: new 85	java/lang/StringBuilder
    //   235: dup
    //   236: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   239: ldc -22
    //   241: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   244: aload 8
    //   246: invokevirtual 236	ox:d	()Ljava/lang/String;
    //   249: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   252: ldc -18
    //   254: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   257: aload 8
    //   259: invokevirtual 241	ox:e	()Ljava/lang/String;
    //   262: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   265: ldc -13
    //   267: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   270: lload 4
    //   272: invokevirtual 246	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   275: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   278: invokestatic 58	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   281: pop
    //   282: aload 8
    //   284: invokevirtual 236	ox:d	()Ljava/lang/String;
    //   287: astore 9
    //   289: aload 8
    //   291: invokevirtual 248	ox:a	()I
    //   294: istore_3
    //   295: ldc 50
    //   297: new 85	java/lang/StringBuilder
    //   300: dup
    //   301: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   304: ldc -6
    //   306: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   309: iload_3
    //   310: invokevirtual 101	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   313: ldc -4
    //   315: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   318: aload_0
    //   319: iload_3
    //   320: invokespecial 254	com/android/mms/transaction/TransactionService$a:a	(I)Ljava/lang/String;
    //   323: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   326: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   329: invokestatic 58	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   332: pop
    //   333: iload_3
    //   334: tableswitch	default:+747->1081, 0:+95->429, 1:+321->655, 2:+379->713, 3:+418->752
    //   364: ldc 50
    //   366: new 85	java/lang/StringBuilder
    //   369: dup
    //   370: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   373: ldc_w 256
    //   376: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   379: iload_2
    //   380: invokevirtual 101	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   383: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   386: invokestatic 199	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   389: pop
    //   390: iconst_0
    //   391: ifne -259 -> 132
    //   394: ldc 50
    //   396: new 85	java/lang/StringBuilder
    //   399: dup
    //   400: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   403: ldc_w 258
    //   406: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   409: iload_2
    //   410: invokevirtual 101	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   413: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   416: invokestatic 58	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   419: pop
    //   420: aload_0
    //   421: getfield 12	com/android/mms/transaction/TransactionService$a:a	Lcom/android/mms/transaction/TransactionService;
    //   424: iload_2
    //   425: invokevirtual 176	com/android/mms/transaction/TransactionService:stopSelf	(I)V
    //   428: return
    //   429: aload 8
    //   431: invokevirtual 260	ox:b	()Ljava/lang/String;
    //   434: astore 9
    //   436: ldc 50
    //   438: new 85	java/lang/StringBuilder
    //   441: dup
    //   442: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   445: ldc_w 262
    //   448: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   451: aload 9
    //   453: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   456: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   459: invokestatic 219	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   462: pop
    //   463: aload 9
    //   465: ifnull +81 -> 546
    //   468: new 264	om
    //   471: dup
    //   472: aload_0
    //   473: getfield 12	com/android/mms/transaction/TransactionService$a:a	Lcom/android/mms/transaction/TransactionService;
    //   476: iload_2
    //   477: aload 9
    //   479: lload 4
    //   481: invokespecial 267	om:<init>	(Landroid/content/Context;ILjava/lang/String;J)V
    //   484: astore 8
    //   486: aload 8
    //   488: astore 7
    //   490: aload 7
    //   492: astore 8
    //   494: aload_0
    //   495: aload 7
    //   497: invokespecial 171	com/android/mms/transaction/TransactionService$a:b	(Low;)Z
    //   500: istore 6
    //   502: iload 6
    //   504: ifne +287 -> 791
    //   507: iconst_0
    //   508: ifne -376 -> 132
    //   511: ldc 50
    //   513: new 85	java/lang/StringBuilder
    //   516: dup
    //   517: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   520: ldc_w 258
    //   523: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   526: iload_2
    //   527: invokevirtual 101	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   530: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   533: invokestatic 58	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   536: pop
    //   537: aload_0
    //   538: getfield 12	com/android/mms/transaction/TransactionService$a:a	Lcom/android/mms/transaction/TransactionService;
    //   541: iload_2
    //   542: invokevirtual 176	com/android/mms/transaction/TransactionService:stopSelf	(I)V
    //   545: return
    //   546: new 269	com/meizu/android/mms/pdu/MzPduParser
    //   549: dup
    //   550: aload 8
    //   552: invokevirtual 272	ox:c	()[B
    //   555: invokespecial 275	com/meizu/android/mms/pdu/MzPduParser:<init>	([B)V
    //   558: invokevirtual 279	com/meizu/android/mms/pdu/MzPduParser:parse	()Lcom/meizu/android/mms/pdu/MzGenericPdu;
    //   561: astore 8
    //   563: aload 8
    //   565: ifnull +42 -> 607
    //   568: aload 8
    //   570: invokevirtual 284	com/meizu/android/mms/pdu/MzGenericPdu:getMessageType	()I
    //   573: sipush 130
    //   576: if_icmpne +31 -> 607
    //   579: new 264	om
    //   582: dup
    //   583: aload_0
    //   584: getfield 12	com/android/mms/transaction/TransactionService$a:a	Lcom/android/mms/transaction/TransactionService;
    //   587: iload_2
    //   588: aload 8
    //   590: checkcast 286	com/meizu/android/mms/pdu/MzNotificationInd
    //   593: lload 4
    //   595: invokespecial 289	om:<init>	(Landroid/content/Context;ILcom/meizu/android/mms/pdu/MzNotificationInd;J)V
    //   598: astore 8
    //   600: aload 8
    //   602: astore 7
    //   604: goto -114 -> 490
    //   607: ldc 50
    //   609: ldc_w 291
    //   612: invokestatic 293	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   615: pop
    //   616: iconst_0
    //   617: ifne -485 -> 132
    //   620: ldc 50
    //   622: new 85	java/lang/StringBuilder
    //   625: dup
    //   626: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   629: ldc_w 258
    //   632: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   635: iload_2
    //   636: invokevirtual 101	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   639: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   642: invokestatic 58	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   645: pop
    //   646: aload_0
    //   647: getfield 12	com/android/mms/transaction/TransactionService$a:a	Lcom/android/mms/transaction/TransactionService;
    //   650: iload_2
    //   651: invokevirtual 176	com/android/mms/transaction/TransactionService:stopSelf	(I)V
    //   654: return
    //   655: ldc 50
    //   657: new 85	java/lang/StringBuilder
    //   660: dup
    //   661: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   664: ldc_w 295
    //   667: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   670: aload 8
    //   672: invokevirtual 260	ox:b	()Ljava/lang/String;
    //   675: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   678: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   681: invokestatic 219	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   684: pop
    //   685: new 297	oq
    //   688: dup
    //   689: aload_0
    //   690: getfield 12	com/android/mms/transaction/TransactionService$a:a	Lcom/android/mms/transaction/TransactionService;
    //   693: iload_2
    //   694: aload 8
    //   696: invokevirtual 260	ox:b	()Ljava/lang/String;
    //   699: lload 4
    //   701: invokespecial 298	oq:<init>	(Landroid/content/Context;ILjava/lang/String;J)V
    //   704: astore 8
    //   706: aload 8
    //   708: astore 7
    //   710: goto -220 -> 490
    //   713: ldc 50
    //   715: ldc_w 300
    //   718: invokestatic 219	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   721: pop
    //   722: new 302	os
    //   725: dup
    //   726: aload_0
    //   727: getfield 12	com/android/mms/transaction/TransactionService$a:a	Lcom/android/mms/transaction/TransactionService;
    //   730: iload_2
    //   731: aload 9
    //   733: aload 8
    //   735: invokevirtual 260	ox:b	()Ljava/lang/String;
    //   738: lload 4
    //   740: invokespecial 305	os:<init>	(Landroid/content/Context;ILjava/lang/String;Ljava/lang/String;J)V
    //   743: astore 8
    //   745: aload 8
    //   747: astore 7
    //   749: goto -259 -> 490
    //   752: ldc 50
    //   754: ldc_w 307
    //   757: invokestatic 219	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   760: pop
    //   761: new 309	op
    //   764: dup
    //   765: aload_0
    //   766: getfield 12	com/android/mms/transaction/TransactionService$a:a	Lcom/android/mms/transaction/TransactionService;
    //   769: iload_2
    //   770: aload 9
    //   772: aload 8
    //   774: invokevirtual 260	ox:b	()Ljava/lang/String;
    //   777: lload 4
    //   779: invokespecial 310	op:<init>	(Landroid/content/Context;ILjava/lang/String;Ljava/lang/String;J)V
    //   782: astore 8
    //   784: aload 8
    //   786: astore 7
    //   788: goto -298 -> 490
    //   791: aload 7
    //   793: astore 8
    //   795: ldc 50
    //   797: new 85	java/lang/StringBuilder
    //   800: dup
    //   801: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   804: ldc_w 312
    //   807: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   810: aload_1
    //   811: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   814: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   817: invokestatic 58	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   820: pop
    //   821: aload 7
    //   823: ifnonnull -691 -> 132
    //   826: ldc 50
    //   828: new 85	java/lang/StringBuilder
    //   831: dup
    //   832: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   835: ldc_w 258
    //   838: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   841: iload_2
    //   842: invokevirtual 101	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   845: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   848: invokestatic 58	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   851: pop
    //   852: aload_0
    //   853: getfield 12	com/android/mms/transaction/TransactionService$a:a	Lcom/android/mms/transaction/TransactionService;
    //   856: iload_2
    //   857: invokevirtual 176	com/android/mms/transaction/TransactionService:stopSelf	(I)V
    //   860: return
    //   861: astore 9
    //   863: aconst_null
    //   864: astore 7
    //   866: aload 7
    //   868: astore 8
    //   870: ldc 50
    //   872: new 85	java/lang/StringBuilder
    //   875: dup
    //   876: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   879: ldc_w 314
    //   882: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   885: aload_1
    //   886: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   889: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   892: aload 9
    //   894: invokestatic 183	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   897: pop
    //   898: aload 7
    //   900: ifnull +52 -> 952
    //   903: aload 7
    //   905: aload_0
    //   906: getfield 12	com/android/mms/transaction/TransactionService$a:a	Lcom/android/mms/transaction/TransactionService;
    //   909: invokevirtual 316	ow:b	(Loo;)V
    //   912: aload_0
    //   913: getfield 12	com/android/mms/transaction/TransactionService$a:a	Lcom/android/mms/transaction/TransactionService;
    //   916: invokestatic 61	com/android/mms/transaction/TransactionService:a	(Lcom/android/mms/transaction/TransactionService;)Ljava/util/ArrayList;
    //   919: aload 7
    //   921: invokevirtual 319	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   924: ifeq +28 -> 952
    //   927: aload_0
    //   928: getfield 12	com/android/mms/transaction/TransactionService$a:a	Lcom/android/mms/transaction/TransactionService;
    //   931: invokestatic 61	com/android/mms/transaction/TransactionService:a	(Lcom/android/mms/transaction/TransactionService;)Ljava/util/ArrayList;
    //   934: astore_1
    //   935: aload_1
    //   936: monitorenter
    //   937: aload_0
    //   938: getfield 12	com/android/mms/transaction/TransactionService$a:a	Lcom/android/mms/transaction/TransactionService;
    //   941: invokestatic 61	com/android/mms/transaction/TransactionService:a	(Lcom/android/mms/transaction/TransactionService;)Ljava/util/ArrayList;
    //   944: aload 7
    //   946: invokevirtual 321	java/util/ArrayList:remove	(Ljava/lang/Object;)Z
    //   949: pop
    //   950: aload_1
    //   951: monitorexit
    //   952: aload 7
    //   954: ifnonnull -822 -> 132
    //   957: ldc 50
    //   959: new 85	java/lang/StringBuilder
    //   962: dup
    //   963: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   966: ldc_w 258
    //   969: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   972: iload_2
    //   973: invokevirtual 101	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   976: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   979: invokestatic 58	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   982: pop
    //   983: aload_0
    //   984: getfield 12	com/android/mms/transaction/TransactionService$a:a	Lcom/android/mms/transaction/TransactionService;
    //   987: iload_2
    //   988: invokevirtual 176	com/android/mms/transaction/TransactionService:stopSelf	(I)V
    //   991: return
    //   992: astore 8
    //   994: aload_1
    //   995: monitorexit
    //   996: aload 8
    //   998: athrow
    //   999: astore_1
    //   1000: ldc 50
    //   1002: ldc_w 323
    //   1005: aload_1
    //   1006: invokestatic 325	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1009: pop
    //   1010: goto -58 -> 952
    //   1013: astore_1
    //   1014: aload 7
    //   1016: astore 8
    //   1018: aload_1
    //   1019: athrow
    //   1020: astore_1
    //   1021: aload 8
    //   1023: astore 7
    //   1025: aload 7
    //   1027: ifnonnull +37 -> 1064
    //   1030: ldc 50
    //   1032: new 85	java/lang/StringBuilder
    //   1035: dup
    //   1036: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   1039: ldc_w 258
    //   1042: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1045: iload_2
    //   1046: invokevirtual 101	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1049: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1052: invokestatic 58	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   1055: pop
    //   1056: aload_0
    //   1057: getfield 12	com/android/mms/transaction/TransactionService$a:a	Lcom/android/mms/transaction/TransactionService;
    //   1060: iload_2
    //   1061: invokevirtual 176	com/android/mms/transaction/TransactionService:stopSelf	(I)V
    //   1064: aload_1
    //   1065: athrow
    //   1066: aload_0
    //   1067: aconst_null
    //   1068: invokevirtual 327	com/android/mms/transaction/TransactionService$a:a	(Low;)V
    //   1071: return
    //   1072: astore_1
    //   1073: goto -48 -> 1025
    //   1076: astore 9
    //   1078: goto -212 -> 866
    //   1081: goto -717 -> 364
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1084	0	this	a
    //   213	848	2	i	int
    //   294	40	3	j	int
    //   219	559	4	l	long
    //   500	3	6	bool	boolean
    //   39	987	7	localObject1	Object
    //   228	641	8	localObject2	Object
    //   992	5	8	localObject3	Object
    //   1016	6	8	localObject4	Object
    //   287	484	9	str	String
    //   861	32	9	localException1	Exception
    //   1076	1	9	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   221	333	861	java/lang/Exception
    //   364	390	861	java/lang/Exception
    //   429	463	861	java/lang/Exception
    //   468	486	861	java/lang/Exception
    //   546	563	861	java/lang/Exception
    //   568	600	861	java/lang/Exception
    //   607	616	861	java/lang/Exception
    //   655	706	861	java/lang/Exception
    //   713	745	861	java/lang/Exception
    //   752	784	861	java/lang/Exception
    //   937	952	992	finally
    //   994	996	992	finally
    //   903	937	999	java/lang/Throwable
    //   996	999	999	java/lang/Throwable
    //   903	937	1013	finally
    //   996	999	1013	finally
    //   1000	1010	1013	finally
    //   494	502	1020	finally
    //   795	821	1020	finally
    //   870	898	1020	finally
    //   1018	1020	1020	finally
    //   221	333	1072	finally
    //   364	390	1072	finally
    //   429	463	1072	finally
    //   468	486	1072	finally
    //   546	563	1072	finally
    //   568	600	1072	finally
    //   607	616	1072	finally
    //   655	706	1072	finally
    //   713	745	1072	finally
    //   752	784	1072	finally
    //   494	502	1076	java/lang/Exception
    //   795	821	1076	java/lang/Exception
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.TransactionService.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */