package com.android.mms.transaction.sns;

import aau;
import abh;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import ow;

final class SnsTransactionService$a
  extends Handler
{
  public SnsTransactionService$a(SnsTransactionService paramSnsTransactionService, Looper paramLooper)
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
    if (what == 1) {
      return "EVENT_TRANSACTION_REQUEST";
    }
    if (what == 2) {
      return "EVENT_NEW_INTENT";
    }
    return "unknown message.what";
  }
  
  private boolean a(ow paramow)
  {
    synchronized (SnsTransactionService.a(a))
    {
      Iterator localIterator = SnsTransactionService.a(a).iterator();
      while (localIterator.hasNext()) {
        if (((ow)localIterator.next()).a(paramow))
        {
          Log.v("SnsTransactionService", "Duplicated transaction: " + paramow.g());
          return true;
        }
      }
      Log.v("SnsTransactionService", "Adding transaction to 'mProcessing' list: " + paramow);
      SnsTransactionService.a(a).add(paramow);
      SnsTransactionService.b(a).put(paramow.j(), paramow);
      Log.v("SnsTransactionService", "processTransaction: starting transaction " + paramow);
      paramow.a(a);
      paramow.a();
      return true;
    }
  }
  
  protected boolean a(Uri paramUri)
  {
    if (paramUri == null) {
      return false;
    }
    String str = paramUri.getLastPathSegment();
    synchronized (SnsTransactionService.a(a))
    {
      Iterator localIterator = SnsTransactionService.a(a).iterator();
      while (localIterator.hasNext())
      {
        ow localow = (ow)localIterator.next();
        if (localow.i().getLastPathSegment().equals(str))
        {
          Log.v("SnsTransactionService", "cancelMessage Transaction in mProcessing list: " + localow.g() + ", uri is: " + aau.b(Uri.class, paramUri, "toSafeString"));
          abh.a().a(localow.a(paramUri), localow.e());
          return true;
        }
      }
    }
    Log.e("SnsTransactionService", "cancelMessage, Can't find transaction in Processlist & pendinglist: " + aau.b(Uri.class, paramUri, "toSafeString"));
    return false;
  }
  
  /* Error */
  public void handleMessage(Message arg1)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: ldc 68
    //   5: new 70	java/lang/StringBuilder
    //   8: dup
    //   9: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   12: ldc -79
    //   14: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: aload_1
    //   18: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   21: ldc -77
    //   23: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: aload_0
    //   27: aload_1
    //   28: invokespecial 181	com/android/mms/transaction/sns/SnsTransactionService$a:a	(Landroid/os/Message;)Ljava/lang/String;
    //   31: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   37: invokestatic 96	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   40: pop
    //   41: aload_1
    //   42: getfield 34	android/os/Message:what	I
    //   45: lookupswitch	default:+43->88, 1:+71->116, 2:+44->89, 3:+726->771, 100:+63->108
    //   88: return
    //   89: aload_0
    //   90: getfield 12	com/android/mms/transaction/sns/SnsTransactionService$a:a	Lcom/android/mms/transaction/sns/SnsTransactionService;
    //   93: aload_1
    //   94: getfield 185	android/os/Message:obj	Ljava/lang/Object;
    //   97: checkcast 187	android/content/Intent
    //   100: aload_1
    //   101: getfield 190	android/os/Message:arg1	I
    //   104: invokevirtual 193	com/android/mms/transaction/sns/SnsTransactionService:a	(Landroid/content/Intent;I)V
    //   107: return
    //   108: aload_0
    //   109: invokevirtual 197	com/android/mms/transaction/sns/SnsTransactionService$a:getLooper	()Landroid/os/Looper;
    //   112: invokevirtual 202	android/os/Looper:quit	()V
    //   115: return
    //   116: aload_1
    //   117: getfield 190	android/os/Message:arg1	I
    //   120: istore_2
    //   121: aload_1
    //   122: getfield 185	android/os/Message:obj	Ljava/lang/Object;
    //   125: checkcast 204	ox
    //   128: astore 7
    //   130: new 206	oz
    //   133: dup
    //   134: aload_0
    //   135: getfield 12	com/android/mms/transaction/sns/SnsTransactionService$a:a	Lcom/android/mms/transaction/sns/SnsTransactionService;
    //   138: aconst_null
    //   139: invokespecial 209	oz:<init>	(Landroid/content/Context;Ljava/lang/String;)V
    //   142: astore 6
    //   144: aload 7
    //   146: invokevirtual 211	ox:a	()I
    //   149: istore_3
    //   150: ldc 68
    //   152: new 70	java/lang/StringBuilder
    //   155: dup
    //   156: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   159: ldc -43
    //   161: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: iload_3
    //   165: invokevirtual 86	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   168: ldc -41
    //   170: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: aload_0
    //   174: iload_3
    //   175: invokespecial 217	com/android/mms/transaction/sns/SnsTransactionService$a:a	(I)Ljava/lang/String;
    //   178: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   181: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   184: invokestatic 96	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   187: pop
    //   188: iload_3
    //   189: tableswitch	default:+613->802, 0:+99->288, 1:+198->387, 2:+227->416
    //   216: ldc 68
    //   218: new 70	java/lang/StringBuilder
    //   221: dup
    //   222: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   225: ldc -37
    //   227: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   230: iload_2
    //   231: invokevirtual 86	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   234: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   237: invokestatic 222	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   240: pop
    //   241: iconst_0
    //   242: ifne -154 -> 88
    //   245: ldc -32
    //   247: iconst_2
    //   248: invokestatic 228	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   251: ifeq +28 -> 279
    //   254: ldc 68
    //   256: new 70	java/lang/StringBuilder
    //   259: dup
    //   260: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   263: ldc -26
    //   265: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   268: iload_2
    //   269: invokevirtual 86	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   272: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   275: invokestatic 96	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   278: pop
    //   279: aload_0
    //   280: getfield 12	com/android/mms/transaction/sns/SnsTransactionService$a:a	Lcom/android/mms/transaction/sns/SnsTransactionService;
    //   283: iload_2
    //   284: invokevirtual 234	com/android/mms/transaction/sns/SnsTransactionService:stopSelf	(I)V
    //   287: return
    //   288: aload 7
    //   290: invokevirtual 236	ox:b	()Ljava/lang/String;
    //   293: astore 7
    //   295: aload 7
    //   297: ifnull +499 -> 796
    //   300: new 238	pl
    //   303: dup
    //   304: aload_0
    //   305: getfield 12	com/android/mms/transaction/sns/SnsTransactionService$a:a	Lcom/android/mms/transaction/sns/SnsTransactionService;
    //   308: iload_2
    //   309: aload 6
    //   311: aload 7
    //   313: iconst_3
    //   314: invokespecial 241	pl:<init>	(Landroid/content/Context;ILoz;Ljava/lang/String;I)V
    //   317: astore 6
    //   319: aload 6
    //   321: astore 5
    //   323: aload 5
    //   325: astore 6
    //   327: aload_0
    //   328: aload 5
    //   330: invokespecial 242	com/android/mms/transaction/sns/SnsTransactionService$a:a	(Low;)Z
    //   333: istore 4
    //   335: iload 4
    //   337: ifne +108 -> 445
    //   340: iconst_0
    //   341: ifne -253 -> 88
    //   344: ldc -32
    //   346: iconst_2
    //   347: invokestatic 228	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   350: ifeq +28 -> 378
    //   353: ldc 68
    //   355: new 70	java/lang/StringBuilder
    //   358: dup
    //   359: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   362: ldc -26
    //   364: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   367: iload_2
    //   368: invokevirtual 86	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   371: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   374: invokestatic 96	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   377: pop
    //   378: aload_0
    //   379: getfield 12	com/android/mms/transaction/sns/SnsTransactionService$a:a	Lcom/android/mms/transaction/sns/SnsTransactionService;
    //   382: iload_2
    //   383: invokevirtual 234	com/android/mms/transaction/sns/SnsTransactionService:stopSelf	(I)V
    //   386: return
    //   387: new 244	pm
    //   390: dup
    //   391: aload_0
    //   392: getfield 12	com/android/mms/transaction/sns/SnsTransactionService$a:a	Lcom/android/mms/transaction/sns/SnsTransactionService;
    //   395: iload_2
    //   396: aload 6
    //   398: aload 7
    //   400: invokevirtual 236	ox:b	()Ljava/lang/String;
    //   403: iconst_3
    //   404: invokespecial 245	pm:<init>	(Landroid/content/Context;ILoz;Ljava/lang/String;I)V
    //   407: astore 6
    //   409: aload 6
    //   411: astore 5
    //   413: goto -90 -> 323
    //   416: new 247	pn
    //   419: dup
    //   420: aload_0
    //   421: getfield 12	com/android/mms/transaction/sns/SnsTransactionService$a:a	Lcom/android/mms/transaction/sns/SnsTransactionService;
    //   424: iload_2
    //   425: aload 6
    //   427: aload 7
    //   429: invokevirtual 236	ox:b	()Ljava/lang/String;
    //   432: iconst_3
    //   433: invokespecial 248	pn:<init>	(Landroid/content/Context;ILoz;Ljava/lang/String;I)V
    //   436: astore 6
    //   438: aload 6
    //   440: astore 5
    //   442: goto -119 -> 323
    //   445: aload 5
    //   447: astore 6
    //   449: ldc -32
    //   451: iconst_2
    //   452: invokestatic 228	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   455: ifeq +32 -> 487
    //   458: aload 5
    //   460: astore 6
    //   462: ldc 68
    //   464: new 70	java/lang/StringBuilder
    //   467: dup
    //   468: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   471: ldc -6
    //   473: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   476: aload_1
    //   477: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   480: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   483: invokestatic 96	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   486: pop
    //   487: aload 5
    //   489: ifnonnull -401 -> 88
    //   492: ldc -32
    //   494: iconst_2
    //   495: invokestatic 228	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   498: ifeq +28 -> 526
    //   501: ldc 68
    //   503: new 70	java/lang/StringBuilder
    //   506: dup
    //   507: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   510: ldc -26
    //   512: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   515: iload_2
    //   516: invokevirtual 86	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   519: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   522: invokestatic 96	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   525: pop
    //   526: aload_0
    //   527: getfield 12	com/android/mms/transaction/sns/SnsTransactionService$a:a	Lcom/android/mms/transaction/sns/SnsTransactionService;
    //   530: iload_2
    //   531: invokevirtual 234	com/android/mms/transaction/sns/SnsTransactionService:stopSelf	(I)V
    //   534: return
    //   535: astore 7
    //   537: aconst_null
    //   538: astore 5
    //   540: aload 5
    //   542: astore 6
    //   544: ldc 68
    //   546: new 70	java/lang/StringBuilder
    //   549: dup
    //   550: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   553: ldc -4
    //   555: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   558: aload_1
    //   559: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   562: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   565: aload 7
    //   567: invokestatic 255	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   570: pop
    //   571: aload 5
    //   573: ifnull +68 -> 641
    //   576: aload 5
    //   578: aload_0
    //   579: getfield 12	com/android/mms/transaction/sns/SnsTransactionService$a:a	Lcom/android/mms/transaction/sns/SnsTransactionService;
    //   582: invokevirtual 257	ow:b	(Loo;)V
    //   585: aload_0
    //   586: getfield 12	com/android/mms/transaction/sns/SnsTransactionService$a:a	Lcom/android/mms/transaction/sns/SnsTransactionService;
    //   589: invokestatic 46	com/android/mms/transaction/sns/SnsTransactionService:a	(Lcom/android/mms/transaction/sns/SnsTransactionService;)Ljava/util/ArrayList;
    //   592: aload 5
    //   594: invokevirtual 260	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   597: ifeq +44 -> 641
    //   600: aload_0
    //   601: getfield 12	com/android/mms/transaction/sns/SnsTransactionService$a:a	Lcom/android/mms/transaction/sns/SnsTransactionService;
    //   604: invokestatic 46	com/android/mms/transaction/sns/SnsTransactionService:a	(Lcom/android/mms/transaction/sns/SnsTransactionService;)Ljava/util/ArrayList;
    //   607: astore_1
    //   608: aload_1
    //   609: monitorenter
    //   610: aload_0
    //   611: getfield 12	com/android/mms/transaction/sns/SnsTransactionService$a:a	Lcom/android/mms/transaction/sns/SnsTransactionService;
    //   614: invokestatic 109	com/android/mms/transaction/sns/SnsTransactionService:b	(Lcom/android/mms/transaction/sns/SnsTransactionService;)Ljava/util/HashMap;
    //   617: aload 5
    //   619: invokevirtual 112	ow:j	()Ljava/lang/String;
    //   622: invokevirtual 264	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   625: pop
    //   626: aload_0
    //   627: getfield 12	com/android/mms/transaction/sns/SnsTransactionService$a:a	Lcom/android/mms/transaction/sns/SnsTransactionService;
    //   630: invokestatic 46	com/android/mms/transaction/sns/SnsTransactionService:a	(Lcom/android/mms/transaction/sns/SnsTransactionService;)Ljava/util/ArrayList;
    //   633: aload 5
    //   635: invokevirtual 266	java/util/ArrayList:remove	(Ljava/lang/Object;)Z
    //   638: pop
    //   639: aload_1
    //   640: monitorexit
    //   641: aload 5
    //   643: ifnonnull -555 -> 88
    //   646: ldc -32
    //   648: iconst_2
    //   649: invokestatic 228	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   652: ifeq +28 -> 680
    //   655: ldc 68
    //   657: new 70	java/lang/StringBuilder
    //   660: dup
    //   661: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   664: ldc -26
    //   666: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   669: iload_2
    //   670: invokevirtual 86	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   673: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   676: invokestatic 96	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   679: pop
    //   680: aload_0
    //   681: getfield 12	com/android/mms/transaction/sns/SnsTransactionService$a:a	Lcom/android/mms/transaction/sns/SnsTransactionService;
    //   684: iload_2
    //   685: invokevirtual 234	com/android/mms/transaction/sns/SnsTransactionService:stopSelf	(I)V
    //   688: return
    //   689: astore 6
    //   691: aload_1
    //   692: monitorexit
    //   693: aload 6
    //   695: athrow
    //   696: astore_1
    //   697: ldc 68
    //   699: ldc_w 268
    //   702: aload_1
    //   703: invokestatic 270	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   706: pop
    //   707: goto -66 -> 641
    //   710: astore_1
    //   711: aload 5
    //   713: astore 6
    //   715: aload_1
    //   716: athrow
    //   717: astore_1
    //   718: aload 6
    //   720: astore 5
    //   722: aload 5
    //   724: ifnonnull +45 -> 769
    //   727: ldc -32
    //   729: iconst_2
    //   730: invokestatic 228	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   733: ifeq +28 -> 761
    //   736: ldc 68
    //   738: new 70	java/lang/StringBuilder
    //   741: dup
    //   742: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   745: ldc -26
    //   747: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   750: iload_2
    //   751: invokevirtual 86	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   754: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   757: invokestatic 96	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   760: pop
    //   761: aload_0
    //   762: getfield 12	com/android/mms/transaction/sns/SnsTransactionService$a:a	Lcom/android/mms/transaction/sns/SnsTransactionService;
    //   765: iload_2
    //   766: invokevirtual 234	com/android/mms/transaction/sns/SnsTransactionService:stopSelf	(I)V
    //   769: aload_1
    //   770: athrow
    //   771: aload_0
    //   772: aload_1
    //   773: getfield 185	android/os/Message:obj	Ljava/lang/Object;
    //   776: checkcast 187	android/content/Intent
    //   779: invokevirtual 273	android/content/Intent:getData	()Landroid/net/Uri;
    //   782: invokevirtual 275	com/android/mms/transaction/sns/SnsTransactionService$a:a	(Landroid/net/Uri;)Z
    //   785: pop
    //   786: return
    //   787: astore_1
    //   788: goto -66 -> 722
    //   791: astore 7
    //   793: goto -253 -> 540
    //   796: aconst_null
    //   797: astore 5
    //   799: goto -476 -> 323
    //   802: goto -586 -> 216
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	805	0	this	a
    //   120	646	2	i	int
    //   149	40	3	j	int
    //   333	3	4	bool	boolean
    //   1	797	5	localObject1	Object
    //   142	401	6	localObject2	Object
    //   689	5	6	localObject3	Object
    //   713	6	6	localObject4	Object
    //   128	300	7	localObject5	Object
    //   535	31	7	localException1	Exception
    //   791	1	7	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   121	188	535	java/lang/Exception
    //   216	241	535	java/lang/Exception
    //   288	295	535	java/lang/Exception
    //   300	319	535	java/lang/Exception
    //   387	409	535	java/lang/Exception
    //   416	438	535	java/lang/Exception
    //   610	641	689	finally
    //   691	693	689	finally
    //   576	610	696	java/lang/Throwable
    //   693	696	696	java/lang/Throwable
    //   576	610	710	finally
    //   693	696	710	finally
    //   697	707	710	finally
    //   327	335	717	finally
    //   449	458	717	finally
    //   462	487	717	finally
    //   544	571	717	finally
    //   715	717	717	finally
    //   121	188	787	finally
    //   216	241	787	finally
    //   288	295	787	finally
    //   300	319	787	finally
    //   387	409	787	finally
    //   416	438	787	finally
    //   327	335	791	java/lang/Exception
    //   449	458	791	java/lang/Exception
    //   462	487	791	java/lang/Exception
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.sns.SnsTransactionService.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */