package com.android.mms.transaction.flyme;

import aau;
import aba;
import aba.a;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.MmsSms.PendingMessages;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.transaction.MmsSystemEventReceiver;
import com.meizu.android.mms.pdu.MzPduPersister;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import oo;
import or;
import ow;
import ox;
import oz;
import pe;
import pf;
import pg;
import zn;

public class FlymeTransactionService
  extends Service
  implements oo
{
  public Handler a = new pg(this);
  private a b;
  private Looper c;
  private final ArrayList<ow> d = new ArrayList();
  private final ArrayList<ow> e = new ArrayList();
  private final HashMap<String, ow> f = new HashMap();
  private final HashMap<String, ow> g = new HashMap();
  private ConnectivityManager h;
  private TelephonyManager i;
  private PowerManager.WakeLock j;
  private boolean k = false;
  private long l = 0L;
  private int m = 0;
  private final aba.a n = new pe(this);
  private final BroadcastReceiver o = new pf(this);
  
  private void a(int paramInt)
  {
    if (f.size() > 0)
    {
      Iterator localIterator = f.entrySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)((Map.Entry)localIterator.next()).getKey();
        ??? = (ow)f.get(str);
        Log.d("FlymeTransactionService", "resetMsgStatus -> Transaction Uri : " + ((ow)???).i().toString());
        ((ow)???).b(130);
        synchronized (f.get(str)).i)
        {
          f.get(str)).i.notify();
          MmsApp.c().b(((ow)f.get(str)).i());
        }
      }
    }
    Log.e("FlymeTransactionService", "resetMsgStatus -> No processing msg");
    stopSelf(paramInt);
  }
  
  private void a(int paramInt1, int paramInt2)
  {
    int i1 = 2;
    Log.v("FlymeTransactionService", "onNetworkUnavailable: sid=" + paramInt1 + ", type=" + paramInt2);
    if (paramInt2 == 1) {
      paramInt1 = i1;
    }
    for (;;)
    {
      if (paramInt1 != -1) {
        a.sendEmptyMessage(paramInt1);
      }
      d();
      return;
      if (paramInt2 == 2) {
        paramInt1 = 1;
      } else {
        paramInt1 = -1;
      }
    }
  }
  
  private void a(int paramInt, ox paramox, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      Log.w("FlymeTransactionService", "launchTransaction: no network error!");
      a(paramInt, paramox.a());
      return;
    }
    Message localMessage = b.obtainMessage(1);
    arg1 = paramInt;
    obj = paramox;
    if (Log.isLoggable("Mms:transaction", 2)) {
      Log.v("FlymeTransactionService", "launchTransaction: sending message " + localMessage);
    }
    b.sendMessage(localMessage);
    paramox = b.obtainMessage(6);
    b.sendMessageDelayed(paramox, 30000000L);
  }
  
  private void a(Context paramContext, int paramInt, boolean paramBoolean)
  {
    Object localObject1 = "m_type = " + String.valueOf(130) + " AND " + "thread_id" + " = " + 64536 + " AND " + "protocol" + " = " + 2;
    paramContext = paramContext.getContentResolver().query(Telephony.Mms.Inbox.CONTENT_URI, null, (String)localObject1, null, null);
    if (paramContext != null)
    {
      try
      {
        int i1 = paramContext.getCount();
        Log.d("FlymeTransactionService", "handleAllNoNotifyMsg -> count : " + i1);
        while (paramContext.moveToNext())
        {
          int i2 = paramContext.getColumnIndexOrThrow("_id");
          i1 = paramContext.getColumnIndexOrThrow("thread_id");
          i2 = paramContext.getInt(i2);
          i1 = paramContext.getInt(i1);
          Log.d("FlymeTransactionService", "PduID : " + i2 + ", threadID : " + i1);
          localObject1 = Uri.parse(Telephony.Mms.Inbox.CONTENT_URI + "/" + i2);
          Bundle localBundle = new Bundle();
          localBundle.putString("bundle_uri", ((Uri)localObject1).toString());
          a(paramInt, new ox(localBundle), paramBoolean);
        }
      }
      finally
      {
        paramContext.close();
      }
      return;
    }
    Log.d("FlymeTransactionService", "No data to handle");
  }
  
  private void b(int paramInt)
  {
    synchronized (d)
    {
      if ((d.isEmpty()) && (e.isEmpty()))
      {
        Log.v("FlymeTransactionService", "stopSelfIfIdle: STOP!");
        Log.v("FlymeTransactionService", "stopSelfIfIdle: unRegisterForConnectionStateChanges");
        MmsSystemEventReceiver.c(getApplicationContext());
        stopSelf(paramInt);
      }
      return;
    }
  }
  
  private boolean c()
  {
    return true;
  }
  
  private static boolean c(int paramInt)
  {
    return (paramInt < 10) && (paramInt > 0);
  }
  
  private int d(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      Log.w("FlymeTransactionService", "Unrecognized MESSAGE_TYPE: " + paramInt);
      return -1;
    case 130: 
      return 1;
    case 135: 
      return 3;
    }
    return 2;
  }
  
  private void d()
  {
    Log.v("FlymeTransactionService", "onNetworkUnavailable():");
    b();
    e();
    e.clear();
    MmsSystemEventReceiver.c(getApplicationContext());
    stopSelf();
  }
  
  private void e()
  {
    Iterator localIterator;
    if (e.size() > 0) {
      localIterator = e.iterator();
    }
    for (;;)
    {
      ow localow;
      if (localIterator.hasNext())
      {
        localow = (ow)localIterator.next();
        Log.w("FlymeTransactionService", " transaction type: " + localow.c() + "id is " + localow.g() + " uri is " + localow.i());
      }
      switch (localow.c())
      {
      default: 
        Log.w("FlymeTransactionService", "Invalid transaction type: " + localow.c() + "id is " + localow.g());
        return;
      case 0: 
        zn.a().a(localow.i(), 128, -1);
        break;
      case 1: 
        zn.a().a(localow.i(), 128, -1);
        break;
      case 2: 
        ContentValues localContentValues1 = new ContentValues(1);
        ContentValues localContentValues2 = new ContentValues(1);
        long l1 = ContentUris.parseId(localow.i());
        localContentValues1.put("err_type", Integer.valueOf(19));
        localContentValues2.put("resp_st", Integer.valueOf(9527));
        getContentResolver().update(Telephony.MmsSms.PendingMessages.CONTENT_URI, localContentValues1, "msg_id=" + l1, null);
        getContentResolver().update(localow.i(), localContentValues2, null, null);
      }
    }
  }
  
  private void f()
  {
    try
    {
      if (j == null)
      {
        j = ((PowerManager)getSystemService("power")).newWakeLock(1, "MMS Connectivity");
        j.setReferenceCounted(false);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private void g()
  {
    j.acquire();
  }
  
  private void h()
  {
    if ((j != null) && (j.isHeld())) {
      j.release();
    }
  }
  
  protected int a()
  {
    f();
    Log.v("FlymeTransactionService", "beginMmsConnectivity: result=" + 0);
    switch (0)
    {
    default: 
      throw new IOException("Cannot establish MMS connectivity");
    }
    g();
    return 0;
  }
  
  public void a(Intent paramIntent, int paramInt)
  {
    int i2 = 0;
    h = ((ConnectivityManager)getSystemService("connectivity"));
    i = ((TelephonyManager)getSystemService("phone"));
    boolean bool1;
    if (!c()) {
      bool1 = true;
    }
    for (;;)
    {
      Object localObject = new StringBuilder().append("    networkAvailable=");
      boolean bool2;
      if (!bool1)
      {
        bool2 = true;
        label64:
        Log.v("FlymeTransactionService", bool2);
        if ((!"android.intent.action.ACTION_ONALARM".equals(paramIntent.getAction())) && (paramIntent.getExtras() != null)) {
          break label436;
        }
      }
      try
      {
        localObject = MzPduPersister.getPduPersister(this).getMmsPendingMessages(System.currentTimeMillis(), 2);
        if (localObject != null) {}
        for (;;)
        {
          try
          {
            int i1 = ((Cursor)localObject).getCount();
            if (Log.isLoggable("Mms:transaction", 2)) {
              Log.v("FlymeTransactionService", "onNewIntent: cursor.count=" + i1);
            }
            if (i1 == 0)
            {
              Log.v("FlymeTransactionService", "onNewIntent: no pending messages. Stopping service.");
              b(paramInt);
              ((Cursor)localObject).close();
              return;
              bool1 = false;
              break;
              bool2 = false;
              break label64;
            }
            int i3 = ((Cursor)localObject).getColumnIndexOrThrow("msg_id");
            int i4 = ((Cursor)localObject).getColumnIndexOrThrow("msg_type");
            i1 = i2;
            if (bool1)
            {
              Log.v("FlymeTransactionService", "onNewIntent: registerForConnectionStateChanges");
              MmsSystemEventReceiver.b(getApplicationContext());
              i1 = i2;
            }
            if (((Cursor)localObject).moveToNext())
            {
              i2 = d(((Cursor)localObject).getInt(i4));
              if (bool1)
              {
                a(paramInt, i2);
                ((Cursor)localObject).close();
                return;
              }
              switch (i2)
              {
              case -1: 
              case 0: 
              default: 
                a(paramInt, new ox(i2, ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, ((Cursor)localObject).getLong(i3)).toString()), false);
                i1 = 1;
                break;
              case 1: 
                if (c(((Cursor)localObject).getInt(((Cursor)localObject).getColumnIndexOrThrow("err_type")))) {
                  continue;
                }
                break;
              }
            }
            if (i1 == 0) {
              b(paramInt);
            }
            ((Cursor)localObject).close();
            return;
          }
          finally {}
          Log.v("FlymeTransactionService", "onNewIntent: no pending messages. Stopping service.");
          or.b(this);
          b(paramInt);
        }
      }
      finally
      {
        for (;;)
        {
          localObject = null;
        }
      }
    }
    ((Cursor)localObject).close();
    throw paramIntent;
    label436:
    if ((paramIntent.getAction() != null) && (paramIntent.getAction().equals("android.intent.action.REHANDLE_ALL_UNHANDLE_MSG")))
    {
      Log.v("FlymeTransactionService", "onNewIntent: android.intent.action_REHANDLE_ALL_UNHANDLE_MSG");
      a(getApplicationContext(), paramInt, bool1);
      return;
    }
    if ((paramIntent.getAction() != null) && (paramIntent.getAction().equals("android.intent.action.ACTION_RESET_MSG_STATUS_WHEN_SERVICE_DISCONNECTED")))
    {
      Log.v("FlymeTransactionService", "onNewIntent: android.intent.action.ACTION_RESET_MSG_STATUS_WHEN_SERVICE_DISCONNECTED");
      a(paramInt);
      return;
    }
    if (Log.isLoggable("Mms:transaction", 2)) {
      Log.v("FlymeTransactionService", "onNewIntent: launch transaction...");
    }
    a(paramInt, new ox(paramIntent.getExtras()), bool1);
  }
  
  /* Error */
  public void a(on arg1)
  {
    // Byte code:
    //   0: aload_1
    //   1: checkcast 119	ow
    //   4: astore_1
    //   5: aload_1
    //   6: invokevirtual 358	ow:g	()I
    //   9: istore_2
    //   10: ldc 121
    //   12: new 123	java/lang/StringBuilder
    //   15: dup
    //   16: invokespecial 124	java/lang/StringBuilder:<init>	()V
    //   19: ldc_w 553
    //   22: invokevirtual 130	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: iload_2
    //   26: invokevirtual 176	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   29: invokevirtual 140	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   32: invokestatic 181	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   35: pop
    //   36: aload_0
    //   37: getfield 47	com/android/mms/transaction/flyme/FlymeTransactionService:d	Ljava/util/ArrayList;
    //   40: astore 6
    //   42: aload 6
    //   44: monitorenter
    //   45: aload_0
    //   46: getfield 54	com/android/mms/transaction/flyme/FlymeTransactionService:f	Ljava/util/HashMap;
    //   49: aload_1
    //   50: invokevirtual 555	ow:j	()Ljava/lang/String;
    //   53: invokevirtual 558	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   56: pop
    //   57: aload_0
    //   58: getfield 47	com/android/mms/transaction/flyme/FlymeTransactionService:d	Ljava/util/ArrayList;
    //   61: aload_1
    //   62: invokevirtual 560	java/util/ArrayList:remove	(Ljava/lang/Object;)Z
    //   65: pop
    //   66: aload_0
    //   67: getfield 49	com/android/mms/transaction/flyme/FlymeTransactionService:e	Ljava/util/ArrayList;
    //   70: invokevirtual 349	java/util/ArrayList:size	()I
    //   73: ifle +219 -> 292
    //   76: ldc 121
    //   78: ldc_w 562
    //   81: invokestatic 181	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   84: pop
    //   85: aload_0
    //   86: getfield 203	com/android/mms/transaction/flyme/FlymeTransactionService:b	Lcom/android/mms/transaction/flyme/FlymeTransactionService$a;
    //   89: iconst_4
    //   90: aload_1
    //   91: invokevirtual 565	ow:h	()Loz;
    //   94: invokevirtual 568	com/android/mms/transaction/flyme/FlymeTransactionService$a:obtainMessage	(ILjava/lang/Object;)Landroid/os/Message;
    //   97: astore 7
    //   99: aload_0
    //   100: getfield 203	com/android/mms/transaction/flyme/FlymeTransactionService:b	Lcom/android/mms/transaction/flyme/FlymeTransactionService$a;
    //   103: aload 7
    //   105: invokevirtual 230	com/android/mms/transaction/flyme/FlymeTransactionService$a:sendMessage	(Landroid/os/Message;)Z
    //   108: pop
    //   109: aload 6
    //   111: monitorexit
    //   112: new 475	android/content/Intent
    //   115: dup
    //   116: ldc_w 570
    //   119: invokespecial 571	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   122: astore 6
    //   124: aload_1
    //   125: invokevirtual 574	ow:f	()Lpa;
    //   128: astore 7
    //   130: aload 7
    //   132: invokevirtual 577	pa:a	()I
    //   135: istore_3
    //   136: aload 6
    //   138: ldc_w 579
    //   141: iload_3
    //   142: invokevirtual 583	android/content/Intent:putExtra	(Ljava/lang/String;I)Landroid/content/Intent;
    //   145: pop
    //   146: iload_3
    //   147: tableswitch	default:+508->655, 1:+239->386, 2:+401->548
    //   168: ldc 121
    //   170: new 123	java/lang/StringBuilder
    //   173: dup
    //   174: invokespecial 124	java/lang/StringBuilder:<init>	()V
    //   177: ldc_w 585
    //   180: invokevirtual 130	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: iload_2
    //   184: invokevirtual 176	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   187: ldc_w 587
    //   190: invokevirtual 130	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: iload_3
    //   194: invokevirtual 176	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   197: invokevirtual 140	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   200: invokestatic 181	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   203: pop
    //   204: ldc 121
    //   206: new 123	java/lang/StringBuilder
    //   209: dup
    //   210: invokespecial 124	java/lang/StringBuilder:<init>	()V
    //   213: ldc_w 589
    //   216: invokevirtual 130	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   219: iload_3
    //   220: invokevirtual 176	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   223: invokevirtual 140	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   226: invokestatic 181	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   229: pop
    //   230: aload_0
    //   231: aload 6
    //   233: invokevirtual 593	com/android/mms/transaction/flyme/FlymeTransactionService:sendBroadcast	(Landroid/content/Intent;)V
    //   236: aload_1
    //   237: aload_0
    //   238: invokevirtual 596	ow:b	(Loo;)V
    //   241: aload_0
    //   242: getfield 47	com/android/mms/transaction/flyme/FlymeTransactionService:d	Ljava/util/ArrayList;
    //   245: astore_1
    //   246: aload_1
    //   247: monitorenter
    //   248: aload_0
    //   249: getfield 47	com/android/mms/transaction/flyme/FlymeTransactionService:d	Ljava/util/ArrayList;
    //   252: invokevirtual 321	java/util/ArrayList:isEmpty	()Z
    //   255: ifeq +34 -> 289
    //   258: aload_0
    //   259: getfield 49	com/android/mms/transaction/flyme/FlymeTransactionService:e	Ljava/util/ArrayList;
    //   262: invokevirtual 321	java/util/ArrayList:isEmpty	()Z
    //   265: ifeq +24 -> 289
    //   268: ldc 121
    //   270: ldc_w 598
    //   273: invokestatic 181	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   276: pop
    //   277: aload_0
    //   278: invokevirtual 329	com/android/mms/transaction/flyme/FlymeTransactionService:getApplicationContext	()Landroid/content/Context;
    //   281: invokestatic 334	com/android/mms/transaction/MmsSystemEventReceiver:c	(Landroid/content/Context;)V
    //   284: aload_0
    //   285: iload_2
    //   286: invokevirtual 170	com/android/mms/transaction/flyme/FlymeTransactionService:stopSelf	(I)V
    //   289: aload_1
    //   290: monitorexit
    //   291: return
    //   292: aload_0
    //   293: getfield 47	com/android/mms/transaction/flyme/FlymeTransactionService:d	Ljava/util/ArrayList;
    //   296: invokevirtual 349	java/util/ArrayList:size	()I
    //   299: ifne -190 -> 109
    //   302: ldc 121
    //   304: ldc_w 600
    //   307: invokestatic 181	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   310: pop
    //   311: aload_0
    //   312: invokevirtual 341	com/android/mms/transaction/flyme/FlymeTransactionService:b	()V
    //   315: goto -206 -> 109
    //   318: astore 7
    //   320: aload 6
    //   322: monitorexit
    //   323: aload 7
    //   325: athrow
    //   326: astore 6
    //   328: aload_1
    //   329: aload_0
    //   330: invokevirtual 596	ow:b	(Loo;)V
    //   333: aload_0
    //   334: getfield 47	com/android/mms/transaction/flyme/FlymeTransactionService:d	Ljava/util/ArrayList;
    //   337: astore_1
    //   338: aload_1
    //   339: monitorenter
    //   340: aload_0
    //   341: getfield 47	com/android/mms/transaction/flyme/FlymeTransactionService:d	Ljava/util/ArrayList;
    //   344: invokevirtual 321	java/util/ArrayList:isEmpty	()Z
    //   347: ifeq +34 -> 381
    //   350: aload_0
    //   351: getfield 49	com/android/mms/transaction/flyme/FlymeTransactionService:e	Ljava/util/ArrayList;
    //   354: invokevirtual 321	java/util/ArrayList:isEmpty	()Z
    //   357: ifeq +24 -> 381
    //   360: ldc 121
    //   362: ldc_w 598
    //   365: invokestatic 181	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   368: pop
    //   369: aload_0
    //   370: invokevirtual 329	com/android/mms/transaction/flyme/FlymeTransactionService:getApplicationContext	()Landroid/content/Context;
    //   373: invokestatic 334	com/android/mms/transaction/MmsSystemEventReceiver:c	(Landroid/content/Context;)V
    //   376: aload_0
    //   377: iload_2
    //   378: invokevirtual 170	com/android/mms/transaction/flyme/FlymeTransactionService:stopSelf	(I)V
    //   381: aload_1
    //   382: monitorexit
    //   383: aload 6
    //   385: athrow
    //   386: ldc 121
    //   388: new 123	java/lang/StringBuilder
    //   391: dup
    //   392: invokespecial 124	java/lang/StringBuilder:<init>	()V
    //   395: ldc_w 602
    //   398: invokevirtual 130	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   401: iload_2
    //   402: invokevirtual 176	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   405: invokevirtual 140	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   408: invokestatic 181	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   411: pop
    //   412: aload 6
    //   414: ldc_w 604
    //   417: aload 7
    //   419: invokevirtual 606	pa:b	()Landroid/net/Uri;
    //   422: invokevirtual 609	android/content/Intent:putExtra	(Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;
    //   425: pop
    //   426: aload_1
    //   427: invokevirtual 354	ow:c	()I
    //   430: tableswitch	default:+228->658, 0:+26->456, 1:+26->456, 2:+106->536
    //   456: aload_0
    //   457: aload 7
    //   459: invokevirtual 606	pa:b	()Landroid/net/Uri;
    //   462: invokestatic 614	com/android/mms/transaction/MessagingNotification:b	(Landroid/content/Context;Landroid/net/Uri;)J
    //   465: lstore 4
    //   467: aload_0
    //   468: invokevirtual 394	com/android/mms/transaction/flyme/FlymeTransactionService:getContentResolver	()Landroid/content/ContentResolver;
    //   471: invokestatic 619	wd:a	(Landroid/content/ContentResolver;)Z
    //   474: ifne +24 -> 498
    //   477: aload_0
    //   478: lload 4
    //   480: iconst_0
    //   481: aload 7
    //   483: invokevirtual 606	pa:b	()Landroid/net/Uri;
    //   486: iconst_0
    //   487: iconst_0
    //   488: invokestatic 622	com/android/mms/transaction/MessagingNotification:a	(Landroid/content/Context;JZLandroid/net/Uri;ZZ)V
    //   491: aload_0
    //   492: invokestatic 624	com/android/mms/transaction/MessagingNotification:f	(Landroid/content/Context;)V
    //   495: goto -291 -> 204
    //   498: new 475	android/content/Intent
    //   501: dup
    //   502: ldc_w 626
    //   505: invokespecial 571	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   508: astore 8
    //   510: aload 8
    //   512: ldc_w 628
    //   515: aload 7
    //   517: invokevirtual 606	pa:b	()Landroid/net/Uri;
    //   520: invokevirtual 139	android/net/Uri:toString	()Ljava/lang/String;
    //   523: invokevirtual 631	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   526: pop
    //   527: aload_0
    //   528: aload 8
    //   530: invokevirtual 593	com/android/mms/transaction/flyme/FlymeTransactionService:sendBroadcast	(Landroid/content/Intent;)V
    //   533: goto -42 -> 491
    //   536: invokestatic 160	com/android/mms/MmsApp:c	()Lcom/android/mms/MmsApp;
    //   539: invokestatic 636	aar:a	(Landroid/content/Context;)Laar;
    //   542: invokevirtual 638	aar:a	()V
    //   545: goto -341 -> 204
    //   548: ldc 121
    //   550: new 123	java/lang/StringBuilder
    //   553: dup
    //   554: invokespecial 124	java/lang/StringBuilder:<init>	()V
    //   557: ldc_w 640
    //   560: invokevirtual 130	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   563: iload_2
    //   564: invokevirtual 176	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   567: invokevirtual 140	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   570: invokestatic 181	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   573: pop
    //   574: aload_1
    //   575: invokevirtual 354	ow:c	()I
    //   578: ifeq +11 -> 589
    //   581: aload_1
    //   582: invokevirtual 354	ow:c	()I
    //   585: iconst_1
    //   586: if_icmpne +25 -> 611
    //   589: aload_1
    //   590: invokevirtual 642	ow:k	()I
    //   593: sipush 9528
    //   596: if_icmpeq +15 -> 611
    //   599: aload_0
    //   600: getfield 79	com/android/mms/transaction/flyme/FlymeTransactionService:a	Landroid/os/Handler;
    //   603: iconst_2
    //   604: invokevirtual 187	android/os/Handler:sendEmptyMessage	(I)Z
    //   607: pop
    //   608: goto -404 -> 204
    //   611: aload_1
    //   612: invokevirtual 354	ow:c	()I
    //   615: iconst_2
    //   616: if_icmpne -412 -> 204
    //   619: aload_1
    //   620: invokevirtual 642	ow:k	()I
    //   623: sipush 9528
    //   626: if_icmpeq -422 -> 204
    //   629: aload_0
    //   630: getfield 79	com/android/mms/transaction/flyme/FlymeTransactionService:a	Landroid/os/Handler;
    //   633: iconst_4
    //   634: invokevirtual 187	android/os/Handler:sendEmptyMessage	(I)Z
    //   637: pop
    //   638: goto -434 -> 204
    //   641: astore 6
    //   643: aload_1
    //   644: monitorexit
    //   645: aload 6
    //   647: athrow
    //   648: astore 6
    //   650: aload_1
    //   651: monitorexit
    //   652: aload 6
    //   654: athrow
    //   655: goto -487 -> 168
    //   658: goto -454 -> 204
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	661	0	this	FlymeTransactionService
    //   9	555	2	i1	int
    //   135	85	3	i2	int
    //   465	14	4	l1	long
    //   326	87	6	localObject2	Object
    //   641	5	6	localObject3	Object
    //   648	5	6	localObject4	Object
    //   97	34	7	localObject5	Object
    //   318	198	7	localObject6	Object
    //   508	21	8	localIntent	Intent
    // Exception table:
    //   from	to	target	type
    //   45	109	318	finally
    //   109	112	318	finally
    //   292	315	318	finally
    //   320	323	318	finally
    //   36	45	326	finally
    //   112	146	326	finally
    //   168	204	326	finally
    //   204	236	326	finally
    //   323	326	326	finally
    //   386	456	326	finally
    //   456	491	326	finally
    //   491	495	326	finally
    //   498	533	326	finally
    //   536	545	326	finally
    //   548	589	326	finally
    //   589	608	326	finally
    //   611	638	326	finally
    //   248	289	641	finally
    //   289	291	641	finally
    //   643	645	641	finally
    //   340	381	648	finally
    //   381	383	648	finally
    //   650	652	648	finally
  }
  
  protected void b()
  {
    try
    {
      Log.v("FlymeTransactionService", "endMmsConnectivity");
      b.removeMessages(3);
      return;
    }
    finally
    {
      h();
    }
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    Log.v("FlymeTransactionService", "Creating FlymeTransactionService");
    HandlerThread localHandlerThread = new HandlerThread("FlymeTransactionService");
    localHandlerThread.start();
    c = localHandlerThread.getLooper();
    b = new a(c);
    aba.a().a(n);
  }
  
  public void onDestroy()
  {
    Log.v("FlymeTransactionService", "Destroying TransactionService");
    if (!e.isEmpty()) {
      Log.w("FlymeTransactionService", "TransactionService exiting with transaction still pending");
    }
    h();
    aba.a().b(n);
    b.sendEmptyMessage(100);
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (paramIntent != null)
    {
      int i1 = 5;
      paramInt1 = i1;
      if (paramIntent.getAction() != null)
      {
        paramInt1 = i1;
        if (paramIntent.getAction().equals("android.intent.action.CANCEL_FLYME_MSG")) {
          paramInt1 = 7;
        }
      }
      Message localMessage = b.obtainMessage(paramInt1);
      arg1 = paramInt2;
      obj = paramIntent;
      b.sendMessage(localMessage);
    }
    return 2;
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
      synchronized (FlymeTransactionService.c(FlymeTransactionService.this))
      {
        Iterator localIterator = FlymeTransactionService.d(FlymeTransactionService.this).iterator();
        while (localIterator.hasNext()) {
          if (((ow)localIterator.next()).a(paramow))
          {
            Log.v("FlymeTransactionService", "Transaction already pending: " + paramow.g());
            return true;
          }
        }
        localIterator = FlymeTransactionService.c(FlymeTransactionService.this).iterator();
        while (localIterator.hasNext()) {
          if (((ow)localIterator.next()).a(paramow))
          {
            Log.v("FlymeTransactionService", "Duplicated transaction: " + paramow.g());
            return true;
          }
        }
      }
      Log.v("FlymeTransactionService", "processTransaction: call beginMmsConnectivity...");
      if (a() == 1)
      {
        FlymeTransactionService.d(FlymeTransactionService.this).add(paramow);
        FlymeTransactionService.e(FlymeTransactionService.this).put(paramow.j(), paramow);
        Log.v("FlymeTransactionService", "processTransaction: connResult=APN_REQUEST_STARTED, defer transaction pending MMS connectivity");
        return true;
      }
      Log.v("FlymeTransactionService", "Adding transaction to 'mProcessing' list: " + paramow);
      FlymeTransactionService.c(FlymeTransactionService.this).add(paramow);
      FlymeTransactionService.a(FlymeTransactionService.this).put(paramow.j(), paramow);
      FlymeTransactionService.f(FlymeTransactionService.this).removeMessages(6);
      ??? = FlymeTransactionService.f(FlymeTransactionService.this).obtainMessage(6);
      FlymeTransactionService.f(FlymeTransactionService.this).sendMessageDelayed((Message)???, 30000000L);
      Log.v("FlymeTransactionService", "processTransaction: starting transaction " + paramow);
      paramow.a(FlymeTransactionService.this);
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
        synchronized (FlymeTransactionService.c(FlymeTransactionService.this))
        {
          if (FlymeTransactionService.d(FlymeTransactionService.this).size() == 0) {
            break label295;
          }
          ??? = (ow)FlymeTransactionService.d(FlymeTransactionService.this).remove(0);
          FlymeTransactionService.e(FlymeTransactionService.this).remove(???.j());
          i = FlymeTransactionService.c(FlymeTransactionService.this).size();
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
            stopSelf(i);
            return;
          }
          catch (IOException localIOException)
          {
            if ((paramoz != null) && (!FlymeTransactionService.d(FlymeTransactionService.this).contains(paramoz))) {}
            synchronized (FlymeTransactionService.c(FlymeTransactionService.this))
            {
              FlymeTransactionService.d(FlymeTransactionService.this).add(paramoz);
              FlymeTransactionService.e(FlymeTransactionService.this).put(paramoz.j(), paramoz);
              Log.w("FlymeTransactionService", localIOException.getMessage(), localIOException);
              return;
            }
          }
        }
        Log.v("FlymeTransactionService", "processPendingTxn: no more transaction, endMmsConnectivity");
        b();
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
        synchronized (FlymeTransactionService.c(FlymeTransactionService.this))
        {
          localObject3 = FlymeTransactionService.d(FlymeTransactionService.this).iterator();
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
            FlymeTransactionService.e(FlymeTransactionService.this).remove(((ow)localObject2).j());
            FlymeTransactionService.d(FlymeTransactionService.this).remove(localObject2);
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
        getApplicationContext().getContentResolver().update(Telephony.MmsSms.PendingMessages.CONTENT_URI, paramUri, "msg_id=" + l, null);
        getApplicationContext().getContentResolver().update(((ow)localObject2).i(), (ContentValues)localObject1, null, null);
        continue;
        label372:
        Object localObject2 = FlymeTransactionService.c(FlymeTransactionService.this).iterator();
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
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.flyme.FlymeTransactionService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */