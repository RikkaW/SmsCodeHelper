package com.android.mms;

import aaa;
import aad;
import aan;
import aaq.a;
import aau;
import aba;
import aba.b;
import abh;
import abh.a;
import abm;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.database.sqlite.SQLiteDatabase;
import android.drm.DrmManagerClient;
import android.graphics.drawable.Drawable;
import android.location.Country;
import android.location.ICountryDetector;
import android.location.ICountryListener.Stub;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.preference.PreferenceManager;
import android.provider.ContactsContract.Data;
import android.provider.Settings.System;
import android.provider.Telephony.Sms;
import android.telephony.TelephonyManager;
import android.util.EventLog;
import android.util.Log;
import com.android.mms.transaction.MessagePopupService.b;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.ui.FlymeMessageActivity;
import fn;
import fo;
import fp;
import fq;
import fr;
import fs;
import ft;
import fu;
import fv;
import fw;
import ga;
import gm;
import gr;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kd;
import ly;
import pj;
import wd;
import zn;
import zt;

public class MmsApp
  extends Application
{
  private static int A;
  private static final HashMap<Uri, h> F;
  private static boolean H;
  private static boolean L = false;
  public static boolean a = false;
  public static boolean b = false;
  public static boolean c = false;
  public static boolean d = false;
  public static boolean e = false;
  public static boolean f = false;
  public static boolean g = false;
  public static Uri j;
  public static boolean k;
  public static boolean l;
  public static boolean m;
  public static boolean n;
  public static final HashMap<Uri, c> p;
  public static final HashSet<d> r;
  private static MmsApp z = null;
  private aan B;
  private abm C;
  private DrmManagerClient D;
  private KeyguardManager E;
  private final HashSet<g> G = new HashSet();
  private aba.b I = new fs(this);
  private a J = null;
  private f K;
  private final BroadcastReceiver M = new ft(this);
  private final BroadcastReceiver N = new fu(this);
  boolean h = true;
  long i = -1L;
  Handler o = new fn(this);
  ContentObserver q = new fr(this, new Handler());
  Object s;
  final Object t = new Object();
  private TelephonyManager u;
  private ly v;
  private Class w;
  private b x;
  private String y;
  
  static
  {
    A = 0;
    j = null;
    k = false;
    l = false;
    n = false;
    F = new HashMap();
    p = new HashMap();
    H = true;
    r = new HashSet();
  }
  
  private final boolean A()
  {
    if (s == null) {
      s = aau.a("meizu.security.IAccessControlManager$Stub", this, (String)aau.a("android.content.ContextExt", "ACCESS_CONTROL_SERVICE"));
    }
    boolean bool = ((Boolean)aau.a(s, "checkAccessControl", String.class, "com.android.mms")).booleanValue();
    Log.d("Mms", "checkAccessControl -> encryption : " + bool);
    return bool;
  }
  
  private void B()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.meizu.theme.change");
    registerReceiver(M, localIntentFilter);
  }
  
  private void C()
  {
    IntentFilter localIntentFilter = new IntentFilter("android.intent.action.PACKAGE_INSTALL");
    localIntentFilter.addAction("android.intent.action.PACKAGE_DATA_CLEARED");
    localIntentFilter.addDataScheme("package");
    registerReceiver(N, localIntentFilter);
  }
  
  public static final Drawable a(Context paramContext, int paramInt)
  {
    Object localObject = paramContext.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getApplicationIcon(paramContext.getPackageName());
      return (Drawable)localObject;
    }
    catch (Exception localException) {}
    return paramContext.getResources().getDrawable(paramInt);
  }
  
  public static Uri a()
  {
    if (j == null) {
      j = Uri.parse("android.resource://" + z.getPackageName() + "/" + 2131296256);
    }
    return j;
  }
  
  public static void a(int paramInt)
  {
    A = paramInt;
  }
  
  public static void a(Context paramContext, boolean paramBoolean)
  {
    ComponentName localComponentName = new ComponentName(paramContext, FlymeMessageActivity.class);
    paramContext = paramContext.getPackageManager();
    if (paramBoolean) {}
    for (int i1 = 1;; i1 = 2)
    {
      paramContext.setComponentEnabledSetting(localComponentName, i1, 1);
      return;
    }
  }
  
  public static void a(String paramString)
  {
    if ((paramString != null) && (paramString.equals("com.android.mms")))
    {
      m = true;
      return;
    }
    m = false;
  }
  
  public static void b()
  {
    File localFile1 = new File("/sdcard/Download/FMessage/");
    if (!localFile1.exists())
    {
      File localFile2 = new File("/sdcard/Download/");
      if (!localFile2.exists())
      {
        bool = localFile2.mkdir();
        Log.d("Mms", "/sdcard/Download/ create success : " + bool);
      }
      boolean bool = localFile1.mkdir();
      Log.d("Mms", "/sdcard/Download/FMessage/ create success : " + bool);
    }
  }
  
  private static void b(Context paramContext)
  {
    a(Telephony.Sms.getDefaultSmsPackage(paramContext));
  }
  
  public static MmsApp c()
  {
    try
    {
      MmsApp localMmsApp = z;
      return localMmsApp;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private void c(boolean paramBoolean)
  {
    if (!gr.s())
    {
      gm.b();
      gr.v();
    }
    d(paramBoolean);
  }
  
  private void d(boolean paramBoolean)
  {
    Iterator localIterator = G.iterator();
    while (localIterator.hasNext()) {
      ((g)localIterator.next()).a(paramBoolean);
    }
  }
  
  public static boolean o()
  {
    return true;
  }
  
  public static boolean r()
  {
    return L;
  }
  
  public static int s()
  {
    return A;
  }
  
  public static boolean t()
  {
    return ((TelephonyManager)c().getSystemService("phone")).getCallState() == 0;
  }
  
  private void w()
  {
    try
    {
      Object localObject = aau.c("android.os.ServiceManager");
      w = aau.c("android.location.ICountryDetector$Stub");
      localObject = ((Class)localObject).getMethod("getService", new Class[] { String.class }).invoke(localObject, new Object[] { "country_detector" });
      v = new ly((ICountryDetector)w.getDeclaredMethod("asInterface", new Class[] { IBinder.class }).invoke(w, new Object[] { localObject }));
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
    }
  }
  
  private final void x()
  {
    new Thread(new fq(this)).start();
  }
  
  private void y()
  {
    try
    {
      Class localClass = Class.forName("android.os.Process");
      if ((Integer)localClass.getDeclaredMethod("myUid", new Class[0]).invoke(localClass, new Object[0]) != (Integer)localClass.getDeclaredField("SYSTEM_UID").get(localClass)) {
        EventLog.writeEvent(0, SQLiteDatabase.releaseMemory());
      }
      localClass = Class.forName("android.graphics.Canvas");
      localClass.getDeclaredMethod("freeCaches", new Class[0]).invoke(localClass, new Object[0]);
      localClass.getDeclaredMethod("freeTextLayoutCaches", new Class[0]).invoke(localClass, new Object[0]);
      localClass = Class.forName("com.android.internal.os.BinderInternal");
      localClass.getDeclaredMethod("forceGc", new Class[] { String.class }).invoke(localClass, new Object[] { "mem" });
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      Log.e("Mms", "MmsApp.handleLowMemory.happen ClassNotFoundException", localClassNotFoundException);
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      Log.e("Mms", "MmsApp.handleLowMemory.happen IllegalAccessException", localIllegalAccessException);
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      Log.e("Mms", "MmsApp.handleLowMemory.happen NoSuchMethodException", localNoSuchMethodException);
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      Log.e("Mms", "MmsApp.handleLowMemory.happen InvocationTargetException", localInvocationTargetException);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      Log.e("Mms", "MmsApp.handleLowMemory.happen IllegalArgumentException", localIllegalArgumentException);
      return;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      Log.e("Mms", "MmsApp.handleLowMemory.happen NoSuchFieldException", localNoSuchFieldException);
    }
  }
  
  private final boolean z()
  {
    return false;
  }
  
  public void a(long paramLong)
  {
    i = paramLong;
  }
  
  public void a(Uri paramUri)
  {
    synchronized (F)
    {
      F.remove(paramUri);
      return;
    }
  }
  
  public void a(Uri paramUri, long paramLong1, long paramLong2)
  {
    p.put(paramUri, new c(paramLong1, paramLong2));
    if (F.containsKey(paramUri)) {
      ((h)F.get(paramUri)).a(paramUri, paramLong1, paramLong2);
    }
  }
  
  public void a(Uri paramUri, h paramh)
  {
    synchronized (F)
    {
      F.put(paramUri, paramh);
      return;
    }
  }
  
  public void a(a parama)
  {
    synchronized (t)
    {
      J = parama;
      return;
    }
  }
  
  public void a(d paramd)
  {
    synchronized (r)
    {
      r.add(paramd);
      return;
    }
  }
  
  public void a(g paramg)
  {
    synchronized (G)
    {
      G.add(paramg);
      return;
    }
  }
  
  public void a(boolean paramBoolean)
  {
    h = paramBoolean;
  }
  
  public void b(long paramLong)
  {
    synchronized (t)
    {
      if (J != null) {
        J.a(paramLong);
      }
      return;
    }
  }
  
  public void b(Uri paramUri)
  {
    a(paramUri);
    p.remove(paramUri);
  }
  
  public void b(a arg1)
  {
    synchronized (t)
    {
      J = null;
      return;
    }
  }
  
  public void b(d paramd)
  {
    synchronized (r)
    {
      r.remove(paramd);
      return;
    }
  }
  
  public void b(g paramg)
  {
    synchronized (G)
    {
      G.remove(paramg);
      return;
    }
  }
  
  public final boolean d()
  {
    if (l()) {
      return false;
    }
    List localList = ((ActivityManager)getSystemService("activity")).getRunningTasks(10);
    if ((localList == null) || (localList.size() == 0)) {
      return false;
    }
    return get0baseActivity.getPackageName().equals(getPackageName());
  }
  
  public aan e()
  {
    return B;
  }
  
  public abm f()
  {
    return C;
  }
  
  public TelephonyManager g()
  {
    if (u == null) {
      u = ((TelephonyManager)getApplicationContext().getSystemService("phone"));
    }
    return u;
  }
  
  public String h()
  {
    if (y == null)
    {
      Country localCountry = v.a();
      if (localCountry != null)
      {
        y = localCountry.getCountryIso();
        Log.d("nzl", "-----country iso = " + y);
      }
    }
    if (y == null) {
      return Locale.getDefault().getCountry();
    }
    return y;
  }
  
  public DrmManagerClient i()
  {
    if (D == null) {
      D = new DrmManagerClient(getApplicationContext());
    }
    return D;
  }
  
  public boolean j()
  {
    return h;
  }
  
  public long k()
  {
    return i;
  }
  
  public boolean l()
  {
    if (E == null) {
      E = ((KeyguardManager)getSystemService("keyguard"));
    }
    return E.isKeyguardLocked();
  }
  
  public boolean m()
  {
    return (l()) && (E.isKeyguardSecure());
  }
  
  public void n()
  {
    synchronized (F)
    {
      F.clear();
      return;
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    kd.a().a(paramConfiguration);
  }
  
  public void onCreate()
  {
    super.onCreate();
    if (Log.isLoggable("Mms:strictmode", 3)) {
      StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
    }
    z = this;
    aau.a();
    PreferenceManager.setDefaultValues(this, 2131230723, false);
    w();
    x = new b();
    v.a(x, getMainLooper());
    B = new aan(z);
    C = new abm(z);
    ga.a(this);
    zn.a(z);
    gm.a(this);
    zt.a(this);
    gr.b(this);
    abh.a.a(this);
    aba.a(this, I);
    abh.a(this);
    kd.a(this);
    MessagingNotification.a(this);
    x();
    new Thread(new fo(this)).start();
    getContentResolver().registerContentObserver(Settings.System.getUriFor("mz_easy_mode"), true, new fp(this, new Handler()));
    getContentResolver().registerContentObserver(ContactsContract.Data.CONTENT_URI, true, q);
    getContentResolver().registerContentObserver(Uri.parse("content://com.meizu.netcontactservice.directory"), true, q);
    C();
    pj.d().a(getApplicationContext());
    B();
    a(this, wd.j());
    K = new f(o);
    aaa.a(z);
    aad.a(this);
  }
  
  public void onTerminate()
  {
    TempFileProvider.a(this);
    v.a(x);
    getContentResolver().unregisterContentObserver(q);
    pj.d().c();
    unregisterReceiver(M);
    unregisterReceiver(N);
  }
  
  public void onTrimMemory(int paramInt)
  {
    super.onTrimMemory(paramInt);
    if (paramInt >= 60) {
      y();
    }
    while (paramInt < 60) {
      return;
    }
    C.a();
    B.a();
  }
  
  public MessagePopupService.b p()
  {
    synchronized (t)
    {
      if (J != null)
      {
        MessagePopupService.b localb = J.a();
        return localb;
      }
      return null;
    }
  }
  
  public f q()
  {
    return K;
  }
  
  public final boolean u()
  {
    return (m()) || (A()) || (z());
  }
  
  public static abstract interface a
  {
    public abstract MessagePopupService.b a();
    
    public abstract void a(long paramLong);
  }
  
  public class b
    extends ICountryListener.Stub
  {
    public b() {}
    
    public void onCountryDetected(Country paramCountry)
    {
      MmsApp.a(MmsApp.this, paramCountry.getCountryIso());
    }
  }
  
  public class c
  {
    public long a;
    public long b;
    
    c(long paramLong1, long paramLong2)
    {
      a = paramLong1;
      b = paramLong2;
    }
  }
  
  public static abstract interface d
  {
    public abstract void a(int paramInt);
  }
  
  public static abstract interface e
  {
    public abstract int a();
    
    public abstract boolean b();
    
    public abstract void c();
    
    public abstract void d();
  }
  
  public static final class f
    implements aaq.a
  {
    private Handler a;
    private MmsApp.e b;
    private final Runnable c = new fv(this);
    private final Runnable d = new fw(this);
    
    public f(Handler paramHandler)
    {
      a = paramHandler;
    }
    
    private void a(Runnable paramRunnable, long paramLong)
    {
      if (a != null) {
        a.postDelayed(paramRunnable, paramLong);
      }
    }
    
    public void a()
    {
      try
      {
        MmsApp.b(true);
        c();
        if ((b != null) && (b.b())) {
          a(c, 500L);
        }
        return;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
    
    public void a(MmsApp.e parame)
    {
      b = parame;
    }
    
    public void b()
    {
      try
      {
        MmsApp.b(false);
        c();
        if ((b != null) && (b.b())) {
          a(d, 500L);
        }
        return;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
    
    /* Error */
    public void c()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 35	com/android/mms/MmsApp$f:a	Landroid/os/Handler;
      //   6: astore_1
      //   7: aload_1
      //   8: ifnonnull +6 -> 14
      //   11: aload_0
      //   12: monitorexit
      //   13: return
      //   14: aload_0
      //   15: getfield 35	com/android/mms/MmsApp$f:a	Landroid/os/Handler;
      //   18: aload_0
      //   19: getfield 28	com/android/mms/MmsApp$f:c	Ljava/lang/Runnable;
      //   22: invokevirtual 65	android/os/Handler:removeCallbacks	(Ljava/lang/Runnable;)V
      //   25: aload_0
      //   26: getfield 35	com/android/mms/MmsApp$f:a	Landroid/os/Handler;
      //   29: aload_0
      //   30: getfield 33	com/android/mms/MmsApp$f:d	Ljava/lang/Runnable;
      //   33: invokevirtual 65	android/os/Handler:removeCallbacks	(Ljava/lang/Runnable;)V
      //   36: goto -25 -> 11
      //   39: astore_1
      //   40: aload_0
      //   41: monitorexit
      //   42: aload_1
      //   43: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	44	0	this	f
      //   6	2	1	localHandler	Handler
      //   39	4	1	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   2	7	39	finally
      //   14	36	39	finally
    }
  }
  
  public static abstract interface g
  {
    public abstract void a(boolean paramBoolean);
  }
  
  public static abstract interface h
  {
    public abstract void a(Uri paramUri, long paramLong1, long paramLong2);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.MmsApp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */