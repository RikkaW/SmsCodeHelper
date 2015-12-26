package com.android.mms.transaction;

import aau;
import aba;
import aej;
import android.app.Notification.Builder;
import android.app.Notification.InboxStyle;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Vibrator;
import android.provider.Settings.System;
import android.provider.Telephony.Mms.Draft;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.Sms;
import android.provider.Telephony.Sms.Conversations;
import android.provider.Telephony.Sms.Inbox;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.MmsApp.a;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.view.MessagePopupGroupView.b;
import fl;
import gm;
import gq;
import gr;
import gr.b;
import np;
import nq;
import nr;
import ns;
import nt;
import nu;
import nv;
import nw;
import nx;
import ny;
import ot;
import ph;
import po;
import vv;
import wd;
import zt;
import zv;

public class MessagePopupService
  extends Service
  implements MmsApp.a, MessagePopupGroupView.b
{
  private static final String[] d = { "body" };
  private static final String[] e = { "thread_id", "_id", "address", "body", "date", "protocol", "port", "sim_id", "imsi" };
  private static final String[] f = { "thread_id", "_id", "m_type", "slideshow_description", "date", "protocol", "sub", "sub_cs", "sim_id", "imsi" };
  private static final long[] k = { 0L, 250L, 250L, 250L };
  final Object a = new Object();
  PowerManager.WakeLock b;
  BroadcastReceiver c = new np(this);
  private d g;
  private Looper h;
  private Vibrator i;
  private long[] j;
  private a l;
  private aej m;
  private c n;
  private TelephonyManager o;
  private final BroadcastReceiver p = new nw(this);
  private ContentObserver q = null;
  
  private b a(Intent paramIntent)
  {
    paramIntent = paramIntent.getExtras();
    if (paramIntent == null)
    {
      a(true, 6, "MessagePopupService", "getMsgBeanFromIntent(), extras is null...");
      return null;
    }
    b localb = new b();
    b = paramIntent.getLong("popup_threadId", 0L);
    c = paramIntent.getBoolean("popup_msgType");
    d = paramIntent.getLong("popup_msgId", 0L);
    f = paramIntent.getString("popup_msgBody", null);
    k = paramIntent.getString("popup_veriType", null);
    return localb;
  }
  
  private void a(int paramInt, b paramb)
  {
    a(true, 3, "MessagePopupService", "startQueryInboxMsg()-->" + paramb);
    if (paramb == null) {
      return;
    }
    l.cancelOperation(paramInt);
    try
    {
      if (c)
      {
        str = "_id = " + d;
        l.startQuery(paramInt, paramb, Telephony.Sms.Inbox.CONTENT_URI, e, str, null, null);
        return;
      }
    }
    catch (SQLiteException paramb)
    {
      aau.a(this, paramb);
      return;
    }
    String str = "_id = " + d;
    l.startQuery(paramInt, paramb, Telephony.Mms.Inbox.CONTENT_URI, f, str, null, null);
  }
  
  private void a(long paramLong, String paramString)
  {
    if (Log.isLoggable("Mms:app", 2)) {
      fl.a("updateDraftSmsMessage tid=%d, contents=\"%s\"", new Object[] { Long.valueOf(paramLong), paramString });
    }
    if (paramLong <= 0L) {
      return;
    }
    ContentValues localContentValues = new ContentValues(3);
    localContentValues.put("thread_id", Long.valueOf(paramLong));
    localContentValues.put("body", paramString);
    localContentValues.put("type", Integer.valueOf(3));
    getContentResolver().insert(Telephony.Sms.CONTENT_URI, localContentValues);
    g(paramLong);
  }
  
  private void a(long paramLong, String paramString, boolean paramBoolean)
  {
    if ((paramString == null) || (TextUtils.getTrimmedLength(paramString) <= 0))
    {
      a(true, 3, "MessagePopupService", "asyncUpdateDraftSmsMessage-->threadId " + paramLong + " hasDraft " + paramBoolean);
      if (paramBoolean) {
        c(paramLong);
      }
      return;
    }
    new Thread(new nt(this, paramLong, paramString), "MessagePopupService.asyncUpdateDraftSmsMessage").start();
  }
  
  private final void a(long paramLong, boolean paramBoolean)
  {
    a(true, 3, "MessagePopupService", "forceHidePopup");
    if ((m == null) || (!m.g())) {
      return;
    }
    if ((!paramBoolean) && (m.h()))
    {
      a(true, 4, "MessagePopupService", "forceHidePopup--> screen off and reply mode will not hidden popup...");
      return;
    }
    b localb = m.i();
    String str = m.j();
    if ((localb != null) && (!TextUtils.isEmpty(str)))
    {
      a(true, 3, "MessagePopupService", "goContactInfo, save draft, msgBean.mThreadId = " + b);
      a(b, str, m);
    }
    if (paramLong == -1L)
    {
      m.a(true, false, "forceHidePopup");
      return;
    }
    if (paramLong == 0L)
    {
      m.a(false, true, "forceHidePopup");
      return;
    }
    if ((localb != null) && (b == paramLong))
    {
      m.a(true, false, "forceHidePopup");
      return;
    }
    m.a(false, true, "forceHidePopup");
  }
  
  private void a(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    a(true, 3, "MessagePopupService", "asyncDelete " + paramUri + ", selection " + paramString);
    new Thread(new nv(this, paramUri, paramString, paramArrayOfString), "MessagePopup.asyncDelete").start();
  }
  
  private final void a(b paramb, Cursor paramCursor)
  {
    String str = wd.a(this, wd.a(paramCursor, 6, 7));
    long l1 = paramCursor.getLong(2);
    paramCursor = paramCursor.getString(3);
    switch (i)
    {
    default: 
      if (l1 == 130L) {
        paramCursor = getString(2131493479);
      }
      break;
    }
    for (;;)
    {
      f = paramCursor;
      return;
      if (l1 == 130L)
      {
        if (TextUtils.isEmpty(paramCursor)) {
          paramCursor = getString(2131493470);
        } else if (paramCursor.contains(";talk#")) {
          paramCursor = getString(2131493484);
        } else {
          paramCursor = getString(2131493470);
        }
      }
      else if (TextUtils.isEmpty(paramCursor))
      {
        paramCursor = getString(2131493477);
      }
      else if (paramCursor.contains(";talk#"))
      {
        paramCursor = getString(2131493596);
      }
      else if (paramCursor.contains(";img#"))
      {
        paramCursor = getString(2131493474);
      }
      else if (paramCursor.contains(";video#"))
      {
        paramCursor = getString(2131493486);
      }
      else if (paramCursor.contains(";audio#"))
      {
        paramCursor = getString(2131493466);
      }
      else if (paramCursor.contains(";ref#"))
      {
        paramCursor = getString(2131493485);
      }
      else if (paramCursor.contains(";file#"))
      {
        paramCursor = getString(2131493469);
      }
      else if (paramCursor.contains(";location#"))
      {
        paramCursor = getString(2131493475);
      }
      else
      {
        paramCursor = getString(2131493471, new Object[] { str });
        continue;
        if (l1 == 130L)
        {
          if (TextUtils.isEmpty(paramCursor)) {
            paramCursor = getString(2131493467);
          } else if (paramCursor.contains(";talk#")) {
            paramCursor = getString(2131493484);
          } else {
            paramCursor = getString(2131493467);
          }
        }
        else if (TextUtils.isEmpty(paramCursor))
        {
          paramCursor = getString(2131493477);
        }
        else if (paramCursor.contains(";talk#"))
        {
          paramCursor = getString(2131493596);
        }
        else if (paramCursor.contains(";img#"))
        {
          paramCursor = getString(2131493474);
        }
        else if (paramCursor.contains(";video#"))
        {
          paramCursor = getString(2131493486);
        }
        else if (paramCursor.contains(";audio#"))
        {
          paramCursor = getString(2131493466);
        }
        else if (paramCursor.contains(";ref#"))
        {
          paramCursor = getString(2131493485);
        }
        else if (paramCursor.contains(";file#"))
        {
          paramCursor = getString(2131493469);
        }
        else if (paramCursor.contains(";location#"))
        {
          paramCursor = getString(2131493475);
        }
        else
        {
          paramCursor = getString(2131493477, new Object[] { str });
          continue;
          if (TextUtils.isEmpty(str)) {
            paramCursor = getString(2131493476);
          } else {
            paramCursor = getString(2131493473, new Object[] { str });
          }
        }
      }
    }
  }
  
  private void a(b paramb, String paramString1, String paramString2)
  {
    String[] arrayOfString = TextUtils.split(paramString2, ";");
    if ((arrayOfString == null) || (arrayOfString.length < 1)) {
      return;
    }
    if (wd.c(paramString2, ";"))
    {
      a(true, 2, "MessagePopupService", "Messagepopup-->preSendSmsWorker: send a sns msg");
      b(paramString1, arrayOfString, b);
    }
    for (;;)
    {
      f(b);
      return;
      if (((j) && (q)) || (((Boolean)aau.a("android.provider.Telephony$Mms", "isEmailAddress", String.class, arrayOfString[0])).booleanValue()))
      {
        a(true, 2, "MessagePopupService", "Messagepopup-->preSendSmsWorker: send a sip msg");
        a(paramString1, arrayOfString, b);
      }
      else
      {
        a(true, 2, "MessagePopupService", "Messagepopup-->preSendSmsWorker: send a sms msg msgBean.mSlotId = " + o);
        a(paramString1, arrayOfString, b, o);
      }
    }
  }
  
  private void a(String paramString, String[] paramArrayOfString, long paramLong)
  {
    paramString = new ph(this, paramArrayOfString, paramString, paramLong, -1);
    try
    {
      paramString.a(paramLong);
      return;
    }
    catch (Exception paramString)
    {
      a(true, 6, "MessagePopupService", "Failed to send SipSms message, threadId=" + paramLong, paramString);
    }
  }
  
  private void a(String paramString, String[] paramArrayOfString, long paramLong, int paramInt)
  {
    Log.i("MessagePopupService", "sendSmsWorker slotId = " + paramInt);
    paramString = new ot(this, paramArrayOfString, paramString, paramLong);
    paramString.a(paramInt);
    try
    {
      paramString.a(paramLong);
      return;
    }
    catch (Exception paramString)
    {
      a(true, 6, "MessagePopupService", "Failed to send SMS message, threadId=" + paramLong, paramString);
    }
  }
  
  public static final void a(boolean paramBoolean, int paramInt, String paramString1, String paramString2)
  {
    if ((!paramBoolean) && (Log.isLoggable("MessagePopupService", 2))) {}
  }
  
  public static final void a(boolean paramBoolean, int paramInt, String paramString1, String paramString2, Throwable paramThrowable)
  {
    if ((!paramBoolean) && (Log.isLoggable("MessagePopupService", 2))) {}
  }
  
  private long[] a(Resources paramResources, int paramInt1, int paramInt2, long[] paramArrayOfLong)
  {
    int[] arrayOfInt = paramResources.getIntArray(paramInt1);
    if (arrayOfInt == null) {
      return paramArrayOfLong;
    }
    if (arrayOfInt.length > paramInt2) {}
    for (;;)
    {
      paramResources = new long[paramInt2];
      paramInt1 = 0;
      for (;;)
      {
        paramArrayOfLong = paramResources;
        if (paramInt1 >= paramInt2) {
          break;
        }
        paramResources[paramInt1] = arrayOfInt[paramInt1];
        paramInt1 += 1;
      }
      paramInt2 = arrayOfInt.length;
    }
  }
  
  private void b(String paramString, String[] paramArrayOfString, long paramLong)
  {
    paramString = new po(this, paramArrayOfString, "", paramString, paramLong, -1);
    try
    {
      paramString.a(paramLong);
      return;
    }
    catch (Exception paramString)
    {
      a(true, 6, "MessagePopupService", "Failed to send SnsSms message, threadId=" + paramLong, paramString);
    }
  }
  
  private void d(long paramLong)
  {
    new ns(this, paramLong).execute(new Void[0]);
  }
  
  private String e(long paramLong)
  {
    a(false, 3, "MessagePopupService", "readDraftSmsMessage thread_id: " + paramLong);
    if ((paramLong <= 0L) || (!zt.c().a(paramLong))) {
      return "";
    }
    Object localObject1 = ContentUris.withAppendedId(Telephony.Sms.Conversations.CONTENT_URI, paramLong);
    Cursor localCursor = getContentResolver().query((Uri)localObject1, d, "type=3", null, null);
    if (localCursor != null) {
      for (;;)
      {
        try
        {
          if (localCursor.moveToFirst())
          {
            localObject1 = localCursor.getString(0);
            return (String)localObject1;
          }
        }
        finally
        {
          localCursor.close();
        }
        String str = "";
      }
    }
    return "";
  }
  
  private void f(long paramLong)
  {
    if (paramLong <= 0L) {
      return;
    }
    getContentResolver().delete(ContentUris.withAppendedId(Telephony.Sms.Conversations.CONTENT_URI, paramLong), "type=3", null);
  }
  
  private void g(long paramLong)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("thread_id");
    if (paramLong > 0L) {}
    for (String str = " = " + paramLong;; str = " IS NULL")
    {
      str = str;
      a(Telephony.Mms.Draft.CONTENT_URI, str, null);
      return;
    }
  }
  
  private void i()
  {
    a(true, 3, "MessagePopupService", "notifyForTipMode");
    NotificationManager localNotificationManager = (NotificationManager)getSystemService("notification");
    Notification.Builder localBuilder = new Notification.Builder(this);
    localBuilder.setContentTitle(getString(2131492888));
    localBuilder.setCategory("msg");
    localBuilder.setPriority(0);
    localBuilder.setDefaults(4);
    aau.b(aau.b(localBuilder, "mFlymeNotificationBuilder"), "setInternalApp", Integer.TYPE, Integer.valueOf(1));
    localBuilder.setAutoCancel(false);
    localNotificationManager.notify("mmsopp_noti_tag", 123456, new Notification.InboxStyle(localBuilder).build());
  }
  
  private void j()
  {
    a(true, 3, "MessagePopupService", "cancelNotifyForTipMode");
    ((NotificationManager)getSystemService("notification")).cancel("mmsopp_noti_tag", 123456);
  }
  
  private boolean k()
  {
    Object localObject = aau.a("android.os.ServiceManager", "checkService", String.class, "phone", "com.android.internal.telephony.ITelephony$Stub", "asInterface", IBinder.class, "isIdle", null, null);
    if ((localObject != null) && ((localObject instanceof Boolean))) {
      return ((Boolean)localObject).booleanValue();
    }
    return false;
  }
  
  private void l()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.android.mms.sip.dest_check_popup");
    localIntentFilter.addDataScheme("content");
    registerReceiver(p, localIntentFilter);
  }
  
  private void m()
  {
    synchronized (a)
    {
      if (b == null)
      {
        b = ((PowerManager)getSystemService("power")).newWakeLock(268435462, "MessagePopup");
        b.setReferenceCounted(false);
      }
      if (b.isHeld()) {
        b.release();
      }
      b.acquire(1L);
      a(true, 3, "MessagePopupService", "wakeUpScreen()-> mWakeLock.acquire(1)");
      return;
    }
  }
  
  private void n()
  {
    aau.a("android.app.ActivityManagerNative", null, "getDefault", null, null, "keyguardWaitingForActivityDrawn", null, null);
  }
  
  private void o()
  {
    synchronized (a)
    {
      if (b != null)
      {
        if (b.isHeld()) {
          b.release();
        }
        b = null;
      }
      return;
    }
  }
  
  private void p()
  {
    if (zv.a)
    {
      if (q == null) {
        q = new nx(this, new Handler());
      }
      getContentResolver().registerContentObserver(aba.a, false, q);
    }
  }
  
  private void q()
  {
    if (zv.a) {
      getContentResolver().unregisterContentObserver(q);
    }
  }
  
  public b a()
  {
    if ((m != null) && (m.g())) {
      return m.i();
    }
    return null;
  }
  
  public void a(long paramLong)
  {
    a(false, 2, "MessagePopupService", "onAppStart()-> currentThreadId = " + paramLong);
    a(paramLong, true);
  }
  
  public final void a(b paramb)
  {
    if (paramb == null)
    {
      a(true, 6, "MessagePopupService", "markMessageAsRead()--> msgBean is null, " + aau.a("android.os.Debug", "getCallers", Integer.TYPE, Integer.valueOf(2)));
      return;
    }
    if ((l) || (d <= 0L) || (b <= 0L))
    {
      a(true, 3, "MessagePopupService", "markMessageAsRead()-> msgBean.hasMarkAsRead = " + l + ", " + aau.a("android.os.Debug", "getCallers", Integer.TYPE, Integer.valueOf(2)));
      return;
    }
    a(true, 3, "MessagePopupService", "markMessageAsRead()-> msgBean.mThreadId = " + b + ", " + aau.a("android.os.Debug", "getCallers", Integer.TYPE, Integer.valueOf(2)));
    new Thread(new nr(this, paramb)).start();
  }
  
  public void a(b paramb, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString)
  {
    j();
    if (paramb == null) {}
    do
    {
      return;
      if (paramb != null) {
        j = false;
      }
      if ((paramBoolean1) && (!paramBoolean2))
      {
        MessagingNotification.a(this, b, false, false);
        return;
      }
    } while ((!paramBoolean1) || (!paramBoolean2) || (paramBoolean3));
    a(paramb);
  }
  
  public void a(String paramString)
  {
    a(true, 3, "MessagePopupService", "sendMsg");
    new Thread(new nu(this, m.i(), paramString), "MessagePopup.send SMS").start();
    m.a(true, false, "sendMsg");
  }
  
  public void a(boolean paramBoolean)
  {
    new Thread(new nq(this, paramBoolean)).start();
  }
  
  public void b()
  {
    m();
  }
  
  public void b(long paramLong)
  {
    if ((l == null) || (m == null)) {
      return;
    }
    l.removeMessages(3);
    l.sendEmptyMessageDelayed(3, 60000L - paramLong);
  }
  
  public void b(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      Log.e("MessagePopupService", "checkSipDestAddrIsAvailable numbers is null");
    }
    do
    {
      return;
      a(true, 3, "MessagePopupService", "checkSipDestAddrIsAvailable number is " + paramString + ", isSipOffLine = " + aba.a().c());
      if (aba.a().c())
      {
        m.a(false, paramString);
        return;
      }
    } while (!aba.a().b());
    aba.a().a(this, new String[] { paramString });
  }
  
  public void b(boolean paramBoolean)
  {
    if ((m != null) && (m.g())) {
      m.a(true);
    }
  }
  
  public void c()
  {
    a(true, 3, "MessagePopupService", "goContactInfo");
    if (MmsApp.c().l()) {
      n();
    }
    b localb = m.i();
    Object localObject = gr.a(MmsApp.c(), b, false).h();
    if ((localObject != null) && (((gq)localObject).size() > 0))
    {
      localObject = (gm)((gq)localObject).get(0);
      wd.a(this, ((gm)localObject).d(), ((gm)localObject).k(), ((gm)localObject).n(), true, ((gm)localObject).p(), true);
      localObject = m.j();
      if ((localb != null) && (!TextUtils.isEmpty((CharSequence)localObject)))
      {
        a(true, 3, "MessagePopupService", "goContactInfo, save draft, msgBean.mThreadId = " + b);
        a(b, (String)localObject, m);
      }
      m.a(true, false, "goContactInfo");
    }
  }
  
  public void c(long paramLong)
  {
    if (paramLong <= 0L) {
      return;
    }
    a(ContentUris.withAppendedId(Telephony.Sms.Conversations.CONTENT_URI, paramLong), "type=3", null);
  }
  
  public void d()
  {
    a(true, 3, "MessagePopupService", "openThread");
    MessagingNotification.b(this);
    if (MmsApp.c().l()) {
      n();
    }
    b localb = m.i();
    if (localb == null)
    {
      a(true, 6, "MessagePopupService", "openThread()-> msgBean is " + null);
      m.a(true, false, "openThread");
      return;
    }
    long l1 = b;
    if (l1 > 0L)
    {
      Intent localIntent = ComposeMessageActivity.a(this, l1, p);
      String str = m.j();
      if (!TextUtils.isEmpty(str))
      {
        a(true, 3, "MessagePopupService", "openThread, put editText to intent, msgBean.mThreadId = " + b);
        localIntent.putExtra("sms_body", str);
      }
      localIntent.addFlags(268435456);
      startActivity(localIntent);
    }
    for (;;)
    {
      m.a(true, false, "openThread");
      MmsApp.a(0);
      return;
      a(true, 6, "MessagePopupService", "openThread()-> threadId is " + l1);
    }
  }
  
  public void e()
  {
    a(true, 3, "MessagePopupService", "onSwapDismiss");
    MessagingNotification.b(this);
    String str = m.j();
    if ((str != null) && (!str.trim().equals("")))
    {
      b localb = m.i();
      if ((localb != null) && (!TextUtils.isEmpty(str)))
      {
        a(true, 3, "MessagePopupService", "onSwapDismiss, save draft, msgBean.mThreadId = " + b);
        a(b, str, m);
      }
    }
    m.a(true, false, "onSwapDismiss");
  }
  
  public void f()
  {
    a(m.i());
  }
  
  public final void g()
  {
    int i1 = 1;
    if (Settings.System.getInt(getContentResolver(), "vibrate_when_ringing", 0) == 1) {
      if (i1 != 0) {
        break label27;
      }
    }
    label27:
    do
    {
      return;
      i1 = 0;
      break;
      if (i == null) {
        i = ((Vibrator)getSystemService("vibrator"));
      }
    } while (!i.hasVibrator());
    long l1 = Binder.clearCallingIdentity();
    try
    {
      i.vibrate(j, -1);
      return;
    }
    finally
    {
      Binder.restoreCallingIdentity(l1);
    }
  }
  
  public final void h()
  {
    long l1;
    if (i != null) {
      l1 = Binder.clearCallingIdentity();
    }
    try
    {
      i.cancel();
      Binder.restoreCallingIdentity(l1);
      i = null;
      return;
    }
    finally
    {
      Binder.restoreCallingIdentity(l1);
    }
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    a(true, 3, "MessagePopupService", "oncreate()...");
    MmsApp.c().a(this);
    j = a(getResources(), 2131361801, 17, k);
    l = new a(getContentResolver());
    l();
    m = new aej(this, l);
    Object localObject = new HandlerThread("MessagePopupService", 10);
    ((HandlerThread)localObject).start();
    h = ((HandlerThread)localObject).getLooper();
    g = new d(h);
    localObject = new IntentFilter();
    ((IntentFilter)localObject).addAction("android.intent.action.SCREEN_OFF");
    registerReceiver(c, (IntentFilter)localObject);
    o = ((TelephonyManager)getSystemService("phone"));
    n = new c(this);
    p();
  }
  
  public void onDestroy()
  {
    a(true, 3, "MessagePopupService", "onDestroy()...");
    h();
    j();
    MmsApp.c().b(this);
    super.onDestroy();
    unregisterReceiver(p);
    unregisterReceiver(c);
    q();
    l.removeMessages(2);
    l.removeMessages(5);
    l.cancelOperation(1);
    l = null;
    if (m != null) {
      m.a(true, false, "onDestroy()");
    }
    m = null;
    g.removeMessages(4);
    h.quit();
    g = null;
    h = null;
    o();
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    paramIntent = a(paramIntent);
    a(true, 2, "MessagePopupService", "onStartCommand(), startId = " + paramInt2 + ", " + paramIntent);
    if (paramIntent == null) {
      return 2;
    }
    Message localMessage = g.obtainMessage();
    what = 4;
    obj = paramIntent;
    g.sendMessage(localMessage);
    localMessage = l.obtainMessage();
    what = 5;
    obj = paramIntent;
    l.sendMessageDelayed(localMessage, 8000L);
    return 2;
  }
  
  final class a
    extends gr.b
  {
    public a(ContentResolver paramContentResolver)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (what)
      {
      case 4: 
      default: 
        super.handleMessage(paramMessage);
      }
      do
      {
        do
        {
          return;
          removeMessages(3);
          if (MmsApp.c().d())
          {
            MessagePopupService.a(true, 4, "MessagePopupService", "SHOW_MSG_TOKEN, Mms is front");
            return;
          }
          paramMessage = (MessagePopupService.b)obj;
          MessagePopupService.a(MessagePopupService.this).a(paramMessage);
          if (!j) {
            b(e);
          }
          MessagePopupService.c(MessagePopupService.this);
          MessagePopupService.d(MessagePopupService.this).a(paramMessage);
          MessagePopupService.e(MessagePopupService.this).listen(MessagePopupService.d(MessagePopupService.this), 32);
          return;
          MessagePopupService.a(false, 3, "MessagePopupService", "TIMEOUT_SHOW_MSG_TOKEN-->time out, hidden popwindow...");
          return;
          paramMessage = (MessagePopupService.b)obj;
          MessagePopupService.a(false, 3, "MessagePopupService", "LOAD_DRAFT_TOKEN-->" + paramMessage);
        } while ((paramMessage == null) || (b <= 0L));
        MessagePopupService.a(MessagePopupService.this, b);
        return;
        MessagePopupService.a(true, 3, "MessagePopupService", "LOAD_CONTACT_TIMEOUT_VALUE-->" + obj);
      } while (obj == null);
      paramMessage = (MessagePopupService.b)obj;
      if (MessagePopupService.f(MessagePopupService.this) != null) {
        MessagePopupService.f(MessagePopupService.this).removeMessages(4, paramMessage);
      }
      n = true;
      MessagingNotification.a(MessagePopupService.this, b, false, true);
    }
    
    protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
    {
      switch (paramInt)
      {
      }
      do
      {
        return;
      } while (paramCursor == null);
      paramObject = (MessagePopupService.b)paramObject;
      if (paramObject == null)
      {
        MessagePopupService.a(true, 6, "MessagePopupService", "QUERY_LAST_UNREAD_MSG-->msgBean is null...");
        return;
      }
      long l;
      if (paramCursor.moveToNext())
      {
        if (!c) {
          break label273;
        }
        l = paramCursor.getLong(0);
        if (l != b)
        {
          j = false;
          b = l;
        }
        d = paramCursor.getLong(1);
        e = paramCursor.getString(2);
        g = paramCursor.getLong(4);
        i = paramCursor.getInt(5);
        h = paramCursor.getInt(6);
        if (f == null) {
          f = paramCursor.getString(3);
        }
        if (h == vv.a) {
          f = getString(2131493489);
        }
        o = paramCursor.getInt(7);
        p = paramCursor.getString(8);
      }
      for (;;)
      {
        paramCursor.close();
        paramCursor = new Message();
        what = 2;
        obj = paramObject;
        sendMessage(paramCursor);
        MessagePopupService.a(true, 6, "MessagePopupService", "QUERY_LAST_UNREAD_MSG-->" + ((MessagePopupService.b)paramObject).toString());
        return;
        label273:
        l = paramCursor.getLong(0);
        if (l != b)
        {
          j = false;
          b = l;
        }
        d = paramCursor.getLong(1);
        g = (paramCursor.getLong(4) * 1000L);
        i = paramCursor.getInt(5);
        o = paramCursor.getInt(8);
        p = paramCursor.getString(9);
        MessagePopupService.a(MessagePopupService.this, (MessagePopupService.b)paramObject, paramCursor);
      }
    }
  }
  
  public static final class b
  {
    public gr a = null;
    public long b = 0L;
    public boolean c = true;
    public long d = 0L;
    public String e = "";
    public String f = "";
    public long g = 0L;
    public int h;
    public int i = 0;
    public boolean j;
    public String k = null;
    public boolean l = false;
    public boolean m;
    public volatile boolean n;
    public int o = -1;
    public String p = "";
    public boolean q = true;
    
    public boolean a()
    {
      return k != null;
    }
    
    public String toString()
    {
      return String.format("[mThreadId = %d] [mMsgId %d] [mMsgAddress = %s] [mMessagePort = %d] [mProtocol = %d] [mIsSms = %b]", new Object[] { Long.valueOf(b), Long.valueOf(d), e, Integer.valueOf(h), Integer.valueOf(i), Boolean.valueOf(c) });
    }
  }
  
  class c
    extends PhoneStateListener
  {
    private MessagePopupService b;
    private MessagePopupService.b c;
    
    c(MessagePopupService paramMessagePopupService)
    {
      b = paramMessagePopupService;
    }
    
    public void a(MessagePopupService.b paramb)
    {
      c = paramb;
    }
    
    public void onCallStateChanged(int paramInt, String paramString)
    {
      if (paramInt == 1)
      {
        Log.d("MessagePopupService", "Ringing");
        MessagePopupService.a(b, c.b, true);
      }
      super.onCallStateChanged(paramInt, paramString);
    }
  }
  
  final class d
    extends Handler
  {
    public d(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (what)
      {
      default: 
        super.handleMessage(paramMessage);
        return;
      }
      if (obj == null)
      {
        MessagePopupService.a(true, 6, "MessagePopupService", "LOAD_CONTACT_TOKEN-->msg.obj is null...");
        return;
      }
      long l = System.currentTimeMillis();
      paramMessage = (MessagePopupService.b)obj;
      Object localObject = gr.a(MmsApp.c(), b, true);
      if ((localObject == null) || (((gr)localObject).h().size() != 1))
      {
        MessagePopupService.a(true, 6, "MessagePopupService", "LOAD_CONTACT_TOKEN)-->" + paramMessage);
        return;
      }
      MessagePopupService.a(false, 2, "MessagePopupService", "LOAD_CONTACT_TOKEN)-->" + paramMessage);
      a = ((gr)localObject);
      localObject = (gm)((gr)localObject).h().get(0);
      if (n)
      {
        MessagePopupService.a(true, 2, "MessagePopupService", "LOAD_CONTACT_TOKEN-->contact in load from cache time out " + ((gm)localObject).d() + ", time = " + (System.currentTimeMillis() - l));
        return;
      }
      if (MessagePopupService.b(MessagePopupService.this) != null) {
        MessagePopupService.b(MessagePopupService.this).removeMessages(5, paramMessage);
      }
      if ((MessagePopupService.a(MessagePopupService.this) != null) && (MessagePopupService.a(MessagePopupService.this).g()) && (!paramMessage.a()))
      {
        MessagePopupService.b localb = MessagePopupService.a(MessagePopupService.this).i();
        if ((MessagePopupService.a(MessagePopupService.this).h()) && ((localb == null) || (b != b)))
        {
          MessagePopupService.a(true, 2, "MessagePopupService", "LOAD_CONTACT_TOKEN)-->reply mode and other thread com in, show message in statuBar, number is " + ((gm)localObject).d());
          MessagingNotification.b(MessagePopupService.this, b, false, true);
          return;
        }
        j = j;
      }
      if ((((gm)localObject).k()) || (f != null))
      {
        MessagePopupService.a(false, 3, "MessagePopupService", "contact in db, query the message, show message in popup, number is " + ((gm)localObject).d() + ", time = " + (System.currentTimeMillis() - l));
        e = ((gm)localObject).d();
        MessagePopupService.a(MessagePopupService.this, 1, paramMessage);
        return;
      }
      MessagePopupService.a(false, 3, "MessagePopupService", "contact not in db, show message in statuBar, number is " + ((gm)localObject).d() + ", time = " + (System.currentTimeMillis() - l));
      MessagingNotification.b(MessagePopupService.this, b, false, true);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessagePopupService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */