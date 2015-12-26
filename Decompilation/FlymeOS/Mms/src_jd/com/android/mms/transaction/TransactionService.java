package com.android.mms.transaction;

import aac;
import aar;
import aau;
import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager.WakeLock;
import android.provider.Settings.Global;
import android.provider.Telephony.Mms;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.android.mms.MmsApp;
import com.meizu.android.mms.pdu.MzPduPersister;
import ga;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import om;
import on;
import oo;
import op;
import oq;
import or;
import os;
import ow;
import ox;
import oy;
import pa;
import wd;

public class TransactionService
  extends Service
  implements oo
{
  public Handler a = new oy(this);
  public Map<String, ow> b = new ConcurrentHashMap();
  private a c;
  private Looper d;
  private final ArrayList<ow> e = new ArrayList();
  private final ArrayList<ow> f = new ArrayList();
  private ConnectivityManager g;
  private PowerManager.WakeLock h;
  private boolean i = false;
  private long j = 0L;
  private int k = 0;
  private int l = 0;
  
  private ow a(String paramString)
  {
    Log.d("TransactionService", "----getTransaction() enter uri: " + paramString + " sProcessingTxn: " + b);
    Map localMap = b;
    if (paramString != null) {}
    try
    {
      if (b.containsKey(paramString))
      {
        paramString = (ow)b.get(paramString);
        return paramString;
      }
      return null;
    }
    finally {}
  }
  
  private void a()
  {
    if ((h != null) && (h.isHeld()))
    {
      Log.v("TransactionService", "mms releaseWakeLock");
      h.release();
    }
  }
  
  private void a(int paramInt)
  {
    synchronized (e)
    {
      if ((e.isEmpty()) && (f.isEmpty()))
      {
        Log.v("TransactionService", "stopSelfIfIdle: STOP!");
        stopSelf(paramInt);
      }
      return;
    }
  }
  
  private void a(int paramInt1, int paramInt2)
  {
    int m = 1;
    Log.v("TransactionService", "onNetworkUnavailable: sid=" + paramInt1 + ", type=" + paramInt2);
    if ((paramInt2 == 1) || (paramInt2 == 0)) {
      m = 2;
    }
    for (;;)
    {
      if (m != -1) {
        a.sendEmptyMessage(m);
      }
      stopSelf(paramInt1);
      return;
      if (paramInt2 != 2) {
        if (paramInt2 == 4) {
          m = 4;
        } else {
          m = -1;
        }
      }
    }
  }
  
  private void a(int paramInt, ox paramox, boolean paramBoolean, long paramLong)
  {
    if (paramBoolean)
    {
      Log.w("TransactionService", "launchTransaction: no network error!");
      a(paramInt, paramox.a());
      return;
    }
    Message localMessage = c.obtainMessage(1);
    arg1 = paramInt;
    if (paramLong <= Long.MAX_VALUE) {
      arg2 = ((int)paramLong & 0xFFFFFFFF);
    }
    for (;;)
    {
      obj = paramox;
      Log.v("TransactionService", "launchTransaction: sending message " + localMessage);
      c.sendMessage(localMessage);
      return;
      Log.e("TransactionService", "launchTransaction: subId is too large, impossible!!!");
    }
  }
  
  private void a(Intent paramIntent, ow paramow)
  {
    String str;
    int m;
    if (((paramow instanceof om)) || ((paramow instanceof oq)))
    {
      str = paramIntent.getStringExtra("uri");
      m = paramIntent.getIntExtra("result", 1);
      if (str != null) {
        paramow.f().a(Uri.parse(str));
      }
      if (!(paramow instanceof om)) {
        break label81;
      }
      if (m != -1) {
        break label70;
      }
      ((om)paramow).a(129);
    }
    label70:
    label81:
    while ((!(paramow instanceof oq)) || (m != -1) || (str == null))
    {
      return;
      ((om)paramow).a(131);
      return;
    }
    ((oq)paramow).a(str);
  }
  
  private void a(ow paramow)
  {
    String str = b(paramow);
    Map localMap;
    if (str != null)
    {
      localMap = b;
      if (str == null) {}
    }
    try
    {
      if (!b.containsKey(str)) {
        b.put(str, paramow);
      }
      Log.d("TransactionService", "----setTransaction() enter, uri: " + str + "  sProcessingTxn: " + b);
      return;
    }
    finally {}
  }
  
  /* Error */
  private void a(ow paramow, int paramInt)
  {
    // Byte code:
    //   0: ldc 68
    //   2: new 70	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 71	java/lang/StringBuilder:<init>	()V
    //   9: ldc -8
    //   11: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   14: iload_2
    //   15: invokevirtual 134	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   18: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   21: invokestatic 116	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   24: pop
    //   25: aload_1
    //   26: instanceof 250
    //   29: ifeq +317 -> 346
    //   32: ldc 68
    //   34: ldc -4
    //   36: invokestatic 91	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   39: pop
    //   40: aload_1
    //   41: checkcast 250	os
    //   44: invokevirtual 255	os:i	()Landroid/net/Uri;
    //   47: astore 7
    //   49: aload 7
    //   51: astore 6
    //   53: iconst_1
    //   54: iload_2
    //   55: if_icmpne +48 -> 103
    //   58: new 257	android/content/ContentValues
    //   61: dup
    //   62: iconst_1
    //   63: invokespecial 259	android/content/ContentValues:<init>	(I)V
    //   66: astore 6
    //   68: aload 6
    //   70: ldc_w 261
    //   73: sipush 9527
    //   76: invokestatic 267	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   79: invokevirtual 270	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   82: aload_0
    //   83: invokevirtual 274	com/android/mms/transaction/TransactionService:getApplicationContext	()Landroid/content/Context;
    //   86: invokevirtual 280	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   89: aload 7
    //   91: aload 6
    //   93: aconst_null
    //   94: aconst_null
    //   95: invokevirtual 286	android/content/ContentResolver:update	(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   98: pop
    //   99: aload 7
    //   101: astore 6
    //   103: aload 6
    //   105: ifnull +396 -> 501
    //   108: aload_1
    //   109: getfield 289	ow:d	Lpa;
    //   112: aload 6
    //   114: invokevirtual 218	pa:a	(Landroid/net/Uri;)V
    //   117: aload 6
    //   119: invokestatic 295	android/content/ContentUris:parseId	(Landroid/net/Uri;)J
    //   122: lstore 4
    //   124: aload_1
    //   125: instanceof 188
    //   128: ifeq +394 -> 522
    //   131: invokestatic 300	zn:a	()Lzn;
    //   134: aload_1
    //   135: getfield 302	ow:f	J
    //   138: invokevirtual 305	zn:a	(J)Z
    //   141: ifne +370 -> 511
    //   144: aload_1
    //   145: getfield 289	ow:d	Lpa;
    //   148: iconst_1
    //   149: invokevirtual 306	pa:a	(I)V
    //   152: getstatic 312	android/provider/Telephony$MmsSms$PendingMessages:CONTENT_URI	Landroid/net/Uri;
    //   155: invokevirtual 316	android/net/Uri:buildUpon	()Landroid/net/Uri$Builder;
    //   158: astore_1
    //   159: aload_1
    //   160: ldc_w 318
    //   163: ldc_w 320
    //   166: invokevirtual 326	android/net/Uri$Builder:appendQueryParameter	(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   169: pop
    //   170: aload_1
    //   171: ldc_w 328
    //   174: lload 4
    //   176: invokestatic 333	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   179: invokevirtual 326	android/net/Uri$Builder:appendQueryParameter	(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   182: pop
    //   183: aload_0
    //   184: invokevirtual 274	com/android/mms/transaction/TransactionService:getApplicationContext	()Landroid/content/Context;
    //   187: invokevirtual 280	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   190: aload_1
    //   191: invokevirtual 336	android/net/Uri$Builder:build	()Landroid/net/Uri;
    //   194: aconst_null
    //   195: aconst_null
    //   196: aconst_null
    //   197: aconst_null
    //   198: invokevirtual 340	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   201: astore_1
    //   202: aload_1
    //   203: ifnull +142 -> 345
    //   206: aload_1
    //   207: invokeinterface 345 1 0
    //   212: iconst_1
    //   213: if_icmpne +126 -> 339
    //   216: aload_1
    //   217: invokeinterface 348 1 0
    //   222: ifeq +117 -> 339
    //   225: new 350	no
    //   228: dup
    //   229: aload_0
    //   230: invokevirtual 274	com/android/mms/transaction/TransactionService:getApplicationContext	()Landroid/content/Context;
    //   233: bipush 100
    //   235: invokespecial 353	no:<init>	(Landroid/content/Context;I)V
    //   238: astore 6
    //   240: iconst_1
    //   241: iload_2
    //   242: if_icmpne +291 -> 533
    //   245: new 257	android/content/ContentValues
    //   248: dup
    //   249: iconst_2
    //   250: invokespecial 259	android/content/ContentValues:<init>	(I)V
    //   253: astore 7
    //   255: aload 7
    //   257: ldc_w 355
    //   260: bipush 10
    //   262: invokestatic 267	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   265: invokevirtual 270	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   268: aload 7
    //   270: ldc_w 357
    //   273: aload 6
    //   275: invokevirtual 358	no:a	()I
    //   278: invokestatic 267	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   281: invokevirtual 270	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   284: aload_1
    //   285: aload_1
    //   286: ldc_w 360
    //   289: invokeinterface 364 2 0
    //   294: invokeinterface 368 2 0
    //   299: lstore 4
    //   301: aload_0
    //   302: invokevirtual 274	com/android/mms/transaction/TransactionService:getApplicationContext	()Landroid/content/Context;
    //   305: invokevirtual 280	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   308: getstatic 312	android/provider/Telephony$MmsSms$PendingMessages:CONTENT_URI	Landroid/net/Uri;
    //   311: aload 7
    //   313: new 70	java/lang/StringBuilder
    //   316: dup
    //   317: invokespecial 71	java/lang/StringBuilder:<init>	()V
    //   320: ldc_w 370
    //   323: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   326: lload 4
    //   328: invokevirtual 373	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   331: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   334: aconst_null
    //   335: invokevirtual 286	android/content/ContentResolver:update	(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   338: pop
    //   339: aload_1
    //   340: invokeinterface 376 1 0
    //   345: return
    //   346: aload_1
    //   347: instanceof 188
    //   350: ifeq +24 -> 374
    //   353: ldc 68
    //   355: ldc_w 378
    //   358: invokestatic 91	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   361: pop
    //   362: aload_1
    //   363: checkcast 188	om
    //   366: invokevirtual 379	om:i	()Landroid/net/Uri;
    //   369: astore 6
    //   371: goto -268 -> 103
    //   374: aload_1
    //   375: instanceof 190
    //   378: ifeq +24 -> 402
    //   381: ldc 68
    //   383: ldc_w 381
    //   386: invokestatic 91	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   389: pop
    //   390: aload_1
    //   391: checkcast 190	oq
    //   394: invokevirtual 382	oq:i	()Landroid/net/Uri;
    //   397: astore 6
    //   399: goto -296 -> 103
    //   402: aload_1
    //   403: instanceof 384
    //   406: ifeq +80 -> 486
    //   409: ldc 68
    //   411: ldc_w 386
    //   414: invokestatic 91	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   417: pop
    //   418: aload_1
    //   419: checkcast 384	op
    //   422: invokevirtual 387	op:i	()Landroid/net/Uri;
    //   425: astore 6
    //   427: new 257	android/content/ContentValues
    //   430: dup
    //   431: iconst_1
    //   432: invokespecial 259	android/content/ContentValues:<init>	(I)V
    //   435: astore 7
    //   437: aload 7
    //   439: ldc_w 389
    //   442: sipush 129
    //   445: invokestatic 267	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   448: invokevirtual 270	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   451: aload_0
    //   452: invokevirtual 274	com/android/mms/transaction/TransactionService:getApplicationContext	()Landroid/content/Context;
    //   455: invokevirtual 280	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   458: aload 6
    //   460: aload 7
    //   462: aconst_null
    //   463: aconst_null
    //   464: invokevirtual 286	android/content/ContentResolver:update	(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   467: pop
    //   468: aload_1
    //   469: getfield 289	ow:d	Lpa;
    //   472: iconst_2
    //   473: invokevirtual 306	pa:a	(I)V
    //   476: aload_1
    //   477: getfield 289	ow:d	Lpa;
    //   480: aload 6
    //   482: invokevirtual 218	pa:a	(Landroid/net/Uri;)V
    //   485: return
    //   486: ldc 68
    //   488: ldc_w 391
    //   491: invokestatic 91	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   494: pop
    //   495: aconst_null
    //   496: astore 6
    //   498: goto -395 -> 103
    //   501: ldc 68
    //   503: ldc_w 393
    //   506: invokestatic 185	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   509: pop
    //   510: return
    //   511: aload_1
    //   512: getfield 289	ow:d	Lpa;
    //   515: iconst_2
    //   516: invokevirtual 306	pa:a	(I)V
    //   519: goto -367 -> 152
    //   522: aload_1
    //   523: getfield 289	ow:d	Lpa;
    //   526: iconst_2
    //   527: invokevirtual 306	pa:a	(I)V
    //   530: goto -378 -> 152
    //   533: iconst_3
    //   534: iload_2
    //   535: if_icmpne -196 -> 339
    //   538: aload_1
    //   539: aload_1
    //   540: ldc_w 357
    //   543: invokeinterface 364 2 0
    //   548: invokeinterface 397 2 0
    //   553: istore_3
    //   554: iload_3
    //   555: istore_2
    //   556: iload_3
    //   557: ifle +7 -> 564
    //   560: iload_3
    //   561: iconst_1
    //   562: isub
    //   563: istore_2
    //   564: ldc 68
    //   566: new 70	java/lang/StringBuilder
    //   569: dup
    //   570: invokespecial 71	java/lang/StringBuilder:<init>	()V
    //   573: ldc_w 399
    //   576: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   579: iload_2
    //   580: invokevirtual 134	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   583: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   586: invokestatic 91	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   589: pop
    //   590: new 257	android/content/ContentValues
    //   593: dup
    //   594: iconst_1
    //   595: invokespecial 259	android/content/ContentValues:<init>	(I)V
    //   598: astore 6
    //   600: aload 6
    //   602: ldc_w 357
    //   605: iload_2
    //   606: invokestatic 267	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   609: invokevirtual 270	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   612: aload_1
    //   613: aload_1
    //   614: ldc_w 360
    //   617: invokeinterface 364 2 0
    //   622: invokeinterface 368 2 0
    //   627: lstore 4
    //   629: aload_0
    //   630: invokevirtual 274	com/android/mms/transaction/TransactionService:getApplicationContext	()Landroid/content/Context;
    //   633: invokevirtual 280	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   636: getstatic 312	android/provider/Telephony$MmsSms$PendingMessages:CONTENT_URI	Landroid/net/Uri;
    //   639: aload 6
    //   641: new 70	java/lang/StringBuilder
    //   644: dup
    //   645: invokespecial 71	java/lang/StringBuilder:<init>	()V
    //   648: ldc_w 370
    //   651: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   654: lload 4
    //   656: invokevirtual 373	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   659: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   662: aconst_null
    //   663: invokevirtual 286	android/content/ContentResolver:update	(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   666: pop
    //   667: goto -328 -> 339
    //   670: astore 6
    //   672: aload_1
    //   673: invokeinterface 376 1 0
    //   678: aload 6
    //   680: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	681	0	this	TransactionService
    //   0	681	1	paramow	ow
    //   0	681	2	paramInt	int
    //   553	10	3	m	int
    //   122	533	4	l1	long
    //   51	589	6	localObject1	Object
    //   670	9	6	localObject2	Object
    //   47	414	7	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   206	240	670	finally
    //   245	339	670	finally
    //   538	554	670	finally
    //   564	667	670	finally
  }
  
  private boolean a(long paramLong)
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)getSystemService("phone");
    if (localTelephonyManager != null) {
      if (Build.VERSION.SDK_INT <= 21) {
        break label57;
      }
    }
    label57:
    for (l = ((Integer)aau.c(localTelephonyManager, "getCallState", Integer.TYPE, Integer.valueOf((int)paramLong))).intValue(); l != 0; l = ((Integer)aau.c(localTelephonyManager, "getCallState", Long.TYPE, Long.valueOf(paramLong))).intValue()) {
      return true;
    }
    return false;
  }
  
  private String b(ow paramow)
  {
    Object localObject;
    if (paramow == null)
    {
      Log.d("TransactionService", "----getTransactionUri() transaction null ");
      localObject = null;
      return (String)localObject;
    }
    if ((paramow instanceof op)) {}
    for (String str = ((op)paramow).b();; str = paramow.l())
    {
      localObject = str;
      if (str == null) {
        break;
      }
      Log.d("TransactionService", "----getTransactionUri() uri: " + str + " transaction: " + paramow);
      return str;
    }
  }
  
  private void b(Intent paramIntent, int paramInt)
  {
    ow localow;
    int m;
    boolean bool1;
    label193:
    boolean bool2;
    label221:
    boolean bool3;
    if (!e.isEmpty())
    {
      String str1 = paramIntent.getStringExtra("bundle_uri");
      String str2 = paramIntent.getStringExtra("oriuri");
      String str3 = paramIntent.getStringExtra("uri");
      localow = a(str2);
      m = paramIntent.getIntExtra("result", 1);
      Log.d("TransactionService", "handleTransactionProcessed(), error type = " + m + ", bundle Uri = " + str1 + ", oriUri = " + str2 + ", contentUri = " + str3);
      if ((localow == null) || (localow.i() == null)) {
        break label631;
      }
      localow.f().a(localow.i());
      paramInt = m;
      if (m == -1)
      {
        if (!(localow instanceof om)) {
          break label359;
        }
        paramInt = ((om)localow).b();
      }
      m = aac.a(f);
      int n = Settings.Global.getInt(getApplicationContext().getContentResolver(), "airplane_mode_on", -1);
      if (m < 0) {
        break label381;
      }
      bool1 = true;
      int i1 = aac.c(this, m);
      if ((2 != i1) && (3 != i1) && (4 != i1)) {
        break label387;
      }
      bool2 = true;
      if ((bool1) && (n != 1) && (!bool2)) {
        break label393;
      }
      bool3 = true;
      label240:
      Log.d("TransactionService", "handleTransactionProcessed(), slotId = " + m + ", mSubId = " + f + ", subDisabled = " + bool3 + ", isSubInserted = " + bool1 + ", flightModeOn = " + n + ", isLocked = " + bool2);
      if ((paramInt != -1) && (!(localow instanceof op))) {
        break label399;
      }
      localow.f().a(1);
      a(paramIntent, localow);
    }
    for (;;)
    {
      localow.d();
      return;
      label359:
      paramInt = m;
      if (!(localow instanceof oq)) {
        break;
      }
      paramInt = ((oq)localow).b();
      break;
      label381:
      bool1 = false;
      break label193;
      label387:
      bool2 = false;
      break label221;
      label393:
      bool3 = false;
      break label240;
      label399:
      if (bool3) {
        a(localow, 1);
      } else {
        switch (paramInt)
        {
        case 6: 
        default: 
          Log.e("TransactionService", "Unknown Error type");
          a(localow, 1);
          break;
        case 1: 
          a(localow, 1);
          break;
        case 2: 
          if (ga.D()) {
            a(localow, 2);
          } else {
            a(localow, 1);
          }
          break;
        case 5: 
          a(localow, 2);
          break;
        case 3: 
        case 4: 
        case 7: 
          if (ga.E())
          {
            if (((localow instanceof os)) || ((localow instanceof oq)))
            {
              bool1 = a(paramIntent.getLongExtra("subscription", -1L));
              Log.d("TransactionService", "incall? " + bool1);
              if (bool1) {
                a(localow, 3);
              } else {
                a(localow, 2);
              }
            }
            else
            {
              a(localow, 2);
            }
          }
          else {
            a(localow, 2);
          }
          break;
        }
      }
    }
    label631:
    Log.e("TransactionService", "handleTransactionProcessed(), uri not match !");
  }
  
  private void b(String paramString)
  {
    Log.d("TransactionService", "----removeTransaction() enter, uri = " + paramString);
    Map localMap = b;
    if (paramString != null) {}
    try
    {
      if (b.containsKey(paramString)) {
        b.remove(paramString);
      }
      if (b != null) {
        Log.d("TransactionService", "----removeTransaction() sProcessingTxn: " + b);
      }
      return;
    }
    finally {}
  }
  
  private static boolean b(int paramInt)
  {
    return (paramInt < 10) && (paramInt > 0);
  }
  
  private int c(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      Log.w("TransactionService", "Unrecognized MESSAGE_TYPE: " + paramInt);
      return -1;
    case 130: 
      return 1;
    case 135: 
      return 3;
    }
    return 2;
  }
  
  private void c(String paramString)
  {
    if (!e.isEmpty())
    {
      Log.d("TransactionService", "----handleTransactionTimeout(),  uri = " + paramString);
      c(a(paramString));
    }
  }
  
  private void c(ow paramow)
  {
    Object localObject3 = null;
    Intent localIntent = new Intent("com.android.mms.transaction.TRANSACION_PROCESSED");
    if ((paramow instanceof os))
    {
      Log.d("TransactionService", "checkTransactionState. :Send");
      localObject3 = ((os)paramow).i();
    }
    for (;;)
    {
      int n;
      try
      {
        localObject1 = getApplicationContext().getContentResolver().query((Uri)localObject3, new String[] { "msg_box" }, null, null, null);
        if (localObject1 == null) {
          break label346;
        }
        if (localObject1 == null) {
          break label175;
        }
      }
      finally
      {
        try
        {
          if (((Cursor)localObject1).getCount() != 1) {
            break label346;
          }
          ((Cursor)localObject1).moveToFirst();
          m = ((Cursor)localObject1).getInt(0);
          if (m != 2) {
            break label346;
          }
          n = -1;
          m = n;
          paramow = (ow)localObject3;
          if (localObject1 != null)
          {
            ((Cursor)localObject1).close();
            paramow = (ow)localObject3;
            m = n;
          }
          localIntent.putExtra("bundle_uri", paramow.toString());
          localIntent.putExtra("result", m);
          b(localIntent, 0);
          return;
        }
        finally
        {
          for (;;)
          {
            Object localObject1;
            int m;
            continue;
            n = 1;
          }
        }
        paramow = finally;
        localObject1 = null;
      }
      ((Cursor)localObject1).close();
      label175:
      throw paramow;
      if (((paramow instanceof om)) || ((paramow instanceof oq)))
      {
        Log.d("TransactionService", "checkTransactionState. :Notification/Retrieve");
        localObject1 = paramow.i();
        paramow = (ow)localObject3;
        try
        {
          localObject3 = getApplicationContext().getContentResolver().query((Uri)localObject1, new String[] { "msg_box" }, null, null, null);
          if (localObject3 != null)
          {
            paramow = (ow)localObject3;
            m = ((Cursor)localObject3).getCount();
            if (m != 0) {
              break label341;
            }
          }
          n = -1;
          m = n;
          paramow = (ow)localObject1;
          if (localObject3 == null) {
            continue;
          }
          ((Cursor)localObject3).close();
          m = n;
          paramow = (ow)localObject1;
          continue;
        }
        finally
        {
          if (paramow != null) {
            paramow.close();
          }
        }
      }
      else if ((paramow instanceof op))
      {
        Log.d("TransactionService", "set Transaction Fail. :ReadRec");
        paramow = ((op)paramow).i();
        m = -1;
      }
      else
      {
        Log.d("TransactionService", "checkTransactionState. type cann't be recognised");
        return;
        label341:
        label346:
        n = 1;
      }
    }
  }
  
  public void a(Intent paramIntent, int paramInt)
  {
    g = ((ConnectivityManager)getSystemService("connectivity"));
    if ((g == null) || (!ga.C()))
    {
      stopSelf(paramInt);
      Log.e("TransactionService", "onNewIntent(), not support SMS");
      return;
    }
    g.getNetworkInfo(2);
    Log.v("TransactionService", "onNewIntent: serviceId: " + paramInt + ": " + paramIntent.getExtras() + " intent=" + paramIntent);
    Log.v("TransactionService", "    networkAvailable=" + true);
    Object localObject;
    if (("android.intent.action.ACTION_ONALARM".equals(paramIntent.getAction())) || (paramIntent.getExtras() == null))
    {
      localObject = MzPduPersister.getPduPersister(this).getMmsPendingMessages(System.currentTimeMillis(), 1);
      if (localObject == null) {}
    }
    for (;;)
    {
      long l1;
      try
      {
        int m = ((Cursor)localObject).getCount();
        Log.v("TransactionService", "onNewIntent: cursor.count=" + m + " action=" + paramIntent.getAction());
        if (m == 0)
        {
          Log.v("TransactionService", "onNewIntent: no pending messages. Stopping service.");
          or.b(this);
          a(paramInt);
          return;
        }
        m = ((Cursor)localObject).getColumnIndexOrThrow("msg_id");
        int n = ((Cursor)localObject).getColumnIndexOrThrow("msg_type");
        int i1 = ((Cursor)localObject).getColumnIndexOrThrow("pending_sub_id");
        if (!((Cursor)localObject).moveToNext()) {
          break label512;
        }
        int i2 = ((Cursor)localObject).getInt(n);
        int i3 = c(i2);
        l1 = ((Cursor)localObject).getLong(i1);
        Log.v("TransactionService", "onNewIntent: msgType=" + i2 + " transactionType=" + i3 + ", subId = " + l1);
        switch (i3)
        {
        case -1: 
        case 0: 
          paramIntent = ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, ((Cursor)localObject).getLong(m));
          ox localox = new ox(i3, paramIntent.toString());
          Log.v("TransactionService", "onNewIntent: launchTransaction uri=" + paramIntent + ", subId = " + l1);
          a(paramInt, localox, false, l1);
          break;
        case 1: 
          bool = b(((Cursor)localObject).getInt(((Cursor)localObject).getColumnIndexOrThrow("err_type")));
        }
      }
      finally
      {
        ((Cursor)localObject).close();
      }
      boolean bool;
      if (!bool)
      {
        continue;
        label512:
        ((Cursor)localObject).close();
        return;
        Log.v("TransactionService", "onNewIntent: no pending messages. Stopping service.");
        or.b(this);
        a(paramInt);
        return;
        Log.v("TransactionService", "onNewIntent: launch transaction...");
        localObject = new ox(paramIntent.getIntExtra("type", 3), paramIntent.getStringExtra("bundle_uri"));
        l1 = paramIntent.getLongExtra("subscription", -1L);
        if (!aac.b(l1))
        {
          Log.e("TransactionService", "onNewIntent subId error, " + ((ox)localObject).toString() + ", subId = " + l1);
          return;
        }
        a(paramInt, (ox)localObject, false, l1);
        return;
      }
    }
  }
  
  public void a(on paramon)
  {
    paramon = (ow)paramon;
    int m = paramon.g();
    Log.v("TransactionService", "update transaction " + m);
    for (;;)
    {
      try
      {
        synchronized (e)
        {
          e.remove(paramon);
          if (f.size() > 0)
          {
            Log.v("TransactionService", "update: handle next pending transaction...");
            localObject3 = c.obtainMessage(4);
            c.sendMessage((Message)localObject3);
          }
          ??? = new Intent("android.intent.action.TRANSACTION_COMPLETED_ACTION");
          Object localObject3 = paramon.f();
          int n = ((pa)localObject3).a();
          ((Intent)???).putExtra("state", n);
          switch (n)
          {
          case 1: 
            Log.v("TransactionService", "Transaction state unknown: " + m + " " + n);
            if (Log.isLoggable("Mms:transaction", 2)) {
              Log.v("TransactionService", "update: broadcast transaction result " + n);
            }
            sendBroadcast((Intent)???);
            return;
          }
        }
        Log.d("TransactionService", "update: result=SUCCESS");
      }
      finally
      {
        paramon.b(this);
        a(m);
        b(b(paramon));
      }
      Log.v("TransactionService", "Transaction complete: " + m);
      ((Intent)localObject2).putExtra("uri", ((pa)localObject4).b());
      switch (paramon.c())
      {
      case 0: 
      case 1: 
        long l1 = MessagingNotification.b(this, ((pa)localObject4).b());
        if (!wd.a(getContentResolver())) {
          MessagingNotification.a(this, l1, false, ((pa)localObject4).b(), false, false);
        }
        for (;;)
        {
          if (wd.c(getContentResolver()))
          {
            localIntent = new Intent("android.intent.action.flyme.powersavingmode.messages");
            localIntent.putExtra("message_uri", ((pa)localObject4).b().toString());
            localIntent.putExtra("type", "mms");
            sendBroadcast(localIntent);
          }
          MessagingNotification.f(this);
          break;
          Intent localIntent = new Intent("com.android.mms.NEW_MMS_RECEIVED");
          localIntent.putExtra("message_uri", ((pa)localObject4).b().toString());
          sendBroadcast(localIntent);
        }
      case 2: 
        aar.a(MmsApp.c()).a();
        continue;
        Log.d("TransactionService", "update: result=FAILED");
        Log.v("TransactionService", "Transaction failed: " + m);
        continue;
      }
    }
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    Log.v("TransactionService", "Creating TransactionService");
    HandlerThread localHandlerThread = new HandlerThread("TransactionService");
    localHandlerThread.start();
    d = localHandlerThread.getLooper();
    c = new a(d);
  }
  
  public void onDestroy()
  {
    Log.v("TransactionService", "Destroying TransactionService");
    if (!f.isEmpty()) {
      Log.w("TransactionService", "TransactionService exiting with transaction still pending");
    }
    a();
    c.sendEmptyMessage(100);
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (paramIntent != null)
    {
      Log.d("TransactionService", "intent.Extras = " + paramIntent.getExtras() + ", intent.Action = " + paramIntent.getAction() + ", intent = " + paramIntent);
      if (!"com.android.mms.transaction.TRANSACION_PROCESSED".equals(paramIntent.getAction())) {
        break label133;
      }
      if (c.hasMessages(6))
      {
        Log.d("TransactionService", "Remove EVENT_PROCESS_TIME_OUT !");
        c.removeMessages(6);
      }
    }
    label133:
    for (Message localMessage = c.obtainMessage(2);; localMessage = c.obtainMessage(5))
    {
      arg1 = paramInt2;
      obj = paramIntent;
      c.sendMessage(localMessage);
      return 2;
    }
  }
  
  final class a
    extends Handler
  {
    public a(Looper paramLooper)
    {
      super();
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
      synchronized (TransactionService.a(TransactionService.this))
      {
        Iterator localIterator = TransactionService.b(TransactionService.this).iterator();
        while (localIterator.hasNext()) {
          if (((ow)localIterator.next()).a(paramow))
          {
            Log.v("TransactionService", "Transaction already pending: " + paramow.g());
            return true;
          }
        }
        localIterator = TransactionService.a(TransactionService.this).iterator();
        while (localIterator.hasNext()) {
          if (((ow)localIterator.next()).a(paramow))
          {
            Log.v("TransactionService", "Duplicated transaction: " + paramow.g());
            return true;
          }
        }
      }
      Log.v("TransactionService", "----Directly Adding transaction to 'mProcessing' list: " + paramow);
      TransactionService.a(TransactionService.this).add(paramow);
      Log.v("TransactionService", "processTransaction: starting transaction " + paramow);
      paramow.a(TransactionService.this);
      paramow.a();
      if (TransactionService.c(TransactionService.this).hasMessages(6)) {
        TransactionService.c(TransactionService.this).removeMessages(6);
      }
      TransactionService.a(TransactionService.this, paramow);
      ??? = TransactionService.c(TransactionService.this).obtainMessage(6);
      obj = TransactionService.b(TransactionService.this, paramow);
      TransactionService.c(TransactionService.this).sendMessageDelayed((Message)???, 600000L);
      return true;
    }
    
    public void a(ow paramow)
    {
      Log.v("TransactionService", "processPendingTxn: transaction=" + paramow);
      label171:
      for (;;)
      {
        synchronized (TransactionService.a(TransactionService.this))
        {
          if (TransactionService.b(TransactionService.this).size() == 0) {
            break label171;
          }
          paramow = (ow)TransactionService.b(TransactionService.this).remove(0);
          TransactionService.a(TransactionService.this).size();
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
          stopSelf(i);
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
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.TransactionService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */