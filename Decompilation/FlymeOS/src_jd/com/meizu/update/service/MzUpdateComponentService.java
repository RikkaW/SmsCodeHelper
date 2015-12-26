package com.meizu.update.service;

import akd;
import akf;
import aki;
import akz;
import alb;
import all;
import alo;
import alt;
import alu;
import alx;
import amf;
import amj;
import amk;
import amn;
import amr;
import amr.a;
import amv;
import amx;
import amy;
import amz;
import ana;
import anb;
import anc;
import and;
import and.a;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.SystemClock;
import android.text.TextUtils;
import ane;
import anf;
import anj;
import anl;
import com.meizu.cloud.pushsdk.PushManager;
import com.meizu.update.UpdateInfo;
import com.meizu.update.iresponse.MzUpdateResponse;
import java.io.File;

public class MzUpdateComponentService
  extends Service
{
  private static long a = 0L;
  private amf b;
  private volatile Looper c;
  private volatile a d;
  private Handler e;
  
  private static final PendingIntent a(Context paramContext, int paramInt, Intent paramIntent)
  {
    return PendingIntent.getService(paramContext, paramInt, paramIntent, 134217728);
  }
  
  public static final PendingIntent a(Context paramContext, UpdateInfo paramUpdateInfo)
  {
    return a(paramContext, 1, a(paramContext, paramUpdateInfo, true));
  }
  
  public static final PendingIntent a(Context paramContext, UpdateInfo paramUpdateInfo, String paramString)
  {
    return a(paramContext, 2, b(paramContext, paramUpdateInfo, paramString, null));
  }
  
  private static final Intent a(Context paramContext, UpdateInfo paramUpdateInfo, boolean paramBoolean)
  {
    paramContext = new Intent(paramContext, MzUpdateComponentService.class);
    paramContext.putExtra("action", 1);
    paramContext.putExtra("update_info", paramUpdateInfo);
    if (paramBoolean) {
      paramContext.putExtra("from_notification", true);
    }
    return paramContext;
  }
  
  private void a()
  {
    Object localObject1 = null;
    if (anl.d()) {
      return;
    }
    Object localObject4 = (PowerManager)getSystemService("power");
    if (localObject4 != null) {}
    for (PowerManager.WakeLock localWakeLock = ((PowerManager)localObject4).newWakeLock(1, "MzUpdateComponent[PushCheck]");; localWakeLock = null)
    {
      if (localWakeLock != null)
      {
        anf.a(this, "acquire wake lock for push check.");
        localWakeLock.acquire();
      }
      int i;
      Object localObject7;
      for (;;)
      {
        try
        {
          anf.a(this, "onPushUpdate check...");
          long l = SystemClock.elapsedRealtime();
          i = 3;
          localObject4 = null;
          int j = i - 1;
          Object localObject5 = localObject4;
          Object localObject8 = localObject1;
          if (i > 0)
          {
            localObject5 = localObject4;
            localObject8 = localObject1;
            if (SystemClock.elapsedRealtime() - l <= 60000L) {
              anf.a(this, "start check try:" + j);
            }
          }
          try
          {
            localObject5 = akf.b(this, getPackageName());
            localObject1 = localObject4;
            localObject4 = localObject5;
            if (localObject4 != null)
            {
              localObject8 = localObject4;
              localObject5 = localObject1;
              localObject4 = new amv(this);
              if (localObject8 == null) {
                break label493;
              }
              anf.a(this, "push update check end:" + mExistsUpdate + "," + mNeedUpdate);
              if ((!mExistsUpdate) && (!mNeedUpdate)) {
                break label474;
              }
              if ((mNeedUpdate) || (!amx.c(this, mVersionName))) {
                continue;
              }
              anf.c("on push while version skip: " + mVersionName);
              ((amv)localObject4).c("Version skip: " + mVersionName);
              return;
            }
          }
          catch (ane localane)
          {
            localObject4 = localObject1;
            localObject1 = localane;
            continue;
            try
            {
              Thread.sleep(3000L);
              i = j;
              Object localObject6 = localObject4;
              localObject4 = localObject1;
              localObject1 = localObject6;
            }
            catch (InterruptedException localInterruptedException)
            {
              i = j;
              localObject7 = localObject4;
              localObject4 = localObject1;
              localObject1 = localObject7;
            }
          }
          continue;
          a((UpdateInfo)localObject8);
          ((amv)localObject4).a("New Version: " + mVersionName);
          continue;
          anf.c("on push while no update!");
        }
        finally
        {
          if (localWakeLock != null)
          {
            localWakeLock.release();
            anf.a(this, "wake lock state after release:" + localWakeLock.isHeld());
          }
        }
        label474:
        aki.a(this);
        ((amv)localObject4).b("No update!");
      }
      label493:
      if (localObject7 == null) {}
      for (Object localObject3 = new ane("Unknown Exception!");; localObject3 = localObject7)
      {
        if (((ane)localObject3).b()) {}
        for (i = ((ane)localObject3).a();; i = 100000)
        {
          ((amv)localObject4).a(i, ((ane)localObject3).getMessage());
          anf.a(this, "push update check return null");
          break;
        }
      }
    }
  }
  
  private void a(int paramInt1, Intent paramIntent, int paramInt2)
  {
    paramIntent = d.obtainMessage(paramInt1, paramInt2, 0, paramIntent);
    d.sendMessage(paramIntent);
  }
  
  public static final void a(Context paramContext)
  {
    anf.b(paramContext, "Handle push msg");
    Intent localIntent = new Intent(paramContext, MzUpdateComponentService.class);
    localIntent.putExtra("action", 3);
    paramContext.startService(localIntent);
  }
  
  public static final void a(Context paramContext, UpdateInfo paramUpdateInfo, MzUpdateResponse paramMzUpdateResponse)
  {
    paramUpdateInfo = a(paramContext, paramUpdateInfo, false);
    if (paramMzUpdateResponse != null) {
      paramUpdateInfo.putExtra("response", paramMzUpdateResponse);
    }
    paramContext.startService(paramUpdateInfo);
  }
  
  public static final void a(Context paramContext, UpdateInfo paramUpdateInfo, String paramString, MzUpdateResponse paramMzUpdateResponse)
  {
    paramContext.startService(b(paramContext, paramUpdateInfo, paramString, paramMzUpdateResponse));
  }
  
  private void a(Intent paramIntent, int paramInt)
  {
    Bundle localBundle;
    int i;
    if ((paramIntent != null) && (paramIntent.getExtras() != null))
    {
      localBundle = paramIntent.getExtras();
      if (localBundle.containsKey("action"))
      {
        i = localBundle.getInt("action");
        anf.c("handle command : " + i);
        if (!a(i)) {
          break label90;
        }
        anf.d("Request too fast, skip action: " + i);
      }
    }
    return;
    switch (i)
    {
    default: 
      return;
    case 0: 
      a((UpdateInfo)localBundle.getParcelable("update_info"));
      a(0, paramIntent, paramInt);
      return;
    case 3: 
      a(3, paramIntent, paramInt);
      return;
    case 1: 
      a(1, paramIntent, paramInt);
      return;
    case 2: 
      a(2, paramIntent, paramInt);
      return;
    case 4: 
      c((UpdateInfo)localBundle.getParcelable("update_info"));
      a(0, paramIntent, paramInt);
      return;
    case 5: 
      d((UpdateInfo)localBundle.getParcelable("update_info"));
      a(0, paramIntent, paramInt);
      return;
    case 6: 
      e((UpdateInfo)localBundle.getParcelable("update_info"));
      a(0, paramIntent, paramInt);
      return;
    case 7: 
      f((UpdateInfo)localBundle.getParcelable("update_info"));
      a(0, paramIntent, paramInt);
      return;
    case 8: 
      g((UpdateInfo)localBundle.getParcelable("update_info"));
      a(0, paramIntent, paramInt);
      return;
    case 9: 
      b();
      a(0, paramIntent, paramInt);
      return;
    case 10: 
      label90:
      a(4, paramIntent, paramInt);
      return;
    }
    b((UpdateInfo)localBundle.getParcelable("update_info"));
    a(0, paramIntent, paramInt);
  }
  
  private void a(UpdateInfo paramUpdateInfo)
  {
    if (paramUpdateInfo != null) {
      new anc(this, paramUpdateInfo).a();
    }
  }
  
  private void a(UpdateInfo paramUpdateInfo, MzUpdateResponse paramMzUpdateResponse)
  {
    if (paramUpdateInfo != null)
    {
      localanc = new anc(this, paramUpdateInfo);
      aki.a(this, mVersionName);
      localObject1 = aki.c(this, mVersionName);
      if (anl.c(this, (String)localObject1))
      {
        localanc.f();
        a(paramUpdateInfo, (String)localObject1, paramMzUpdateResponse);
      }
    }
    while (paramMzUpdateResponse == null) {
      for (;;)
      {
        anc localanc;
        Object localObject1;
        return;
        Object localObject2 = new File((String)localObject1);
        if (((File)localObject2).exists()) {
          ((File)localObject2).delete();
        }
        localanc.a(0, 0L);
        localObject2 = aki.b(this, mVersionName);
        alu localalu = new alu(mUpdateUrl, (String)localObject2, null, null);
        localalu.a(new amz(this, localanc));
        amk localamk = new amk(5);
        localamk.a(mUpdateUrl2);
        b = new amf(this, mUpdateUrl, localalu, localamk);
        b.a(h(this, paramUpdateInfo));
        try
        {
          if (b.a(this)) {
            if ((anl.c(this, (String)localObject2)) && (aki.a((String)localObject2, (String)localObject1)))
            {
              localanc.f();
              a(paramUpdateInfo, (String)localObject1, paramMzUpdateResponse);
              return;
            }
          }
        }
        catch (alt paramUpdateInfo)
        {
          localanc.f();
          if (paramMzUpdateResponse != null)
          {
            paramMzUpdateResponse.b();
            return;
            anf.d("download apk cant parse or rename!");
            localObject1 = new File((String)localObject2);
            if (((File)localObject1).exists()) {
              ((File)localObject1).delete();
            }
            if (paramMzUpdateResponse != null) {
              paramMzUpdateResponse.b();
            }
            and.a(this).a(and.a.h, mVersionName);
            localanc.c();
            return;
          }
        }
        catch (alx localalx)
        {
          for (;;)
          {
            localalx.printStackTrace();
          }
        }
      }
    }
    paramMzUpdateResponse.a();
  }
  
  private void a(UpdateInfo paramUpdateInfo, String paramString, MzUpdateResponse paramMzUpdateResponse)
  {
    if (paramMzUpdateResponse != null)
    {
      paramMzUpdateResponse.a(paramString);
      return;
    }
    Context localContext = akd.a();
    if (localContext != null)
    {
      anf.d("start dialog for tracker : " + localContext);
      a(new ana(this, localContext, paramUpdateInfo, paramString));
      return;
    }
    a(paramUpdateInfo, paramString, paramMzUpdateResponse, false);
  }
  
  private void a(UpdateInfo paramUpdateInfo, String paramString, MzUpdateResponse paramMzUpdateResponse, boolean paramBoolean)
  {
    int j;
    anc localanc;
    amr.a locala;
    int i;
    if (anl.e(this, getPackageName()))
    {
      j = 1;
      localanc = new anc(this, paramUpdateInfo);
      localanc.b();
      amy.a(this, paramUpdateInfo);
      locala = amr.a(this, paramString);
      if (amr.a.b.equals(locala))
      {
        and.a(this).a(and.a.l, mVersionName);
        localanc.e();
        i = j;
        if (paramMzUpdateResponse != null)
        {
          paramMzUpdateResponse.c();
          i = j;
        }
      }
    }
    for (;;)
    {
      if (i == 0)
      {
        if (paramMzUpdateResponse == null) {
          break;
        }
        paramMzUpdateResponse.e();
      }
      return;
      if (amr.a.c.equals(locala))
      {
        and.a(this).a(and.a.m, mVersionName, anl.b(this, getPackageName()));
        localanc.d();
        i = j;
        if (paramMzUpdateResponse != null)
        {
          paramMzUpdateResponse.d();
          i = j;
        }
      }
      else
      {
        localanc.f();
        i = 0;
      }
    }
    paramMzUpdateResponse = akd.a();
    if (paramMzUpdateResponse != null)
    {
      anf.d("start system install for tracker : " + paramMzUpdateResponse);
      a(new anb(this, paramMzUpdateResponse, paramString, paramUpdateInfo));
      return;
    }
    if (!paramBoolean)
    {
      new anc(this, paramUpdateInfo).a(paramString);
      return;
    }
    amy.a(this, paramUpdateInfo);
    paramUpdateInfo = amr.a(paramString);
    paramUpdateInfo.addFlags(268435456);
    startActivity(paramUpdateInfo);
  }
  
  private void a(Runnable paramRunnable)
  {
    e.post(paramRunnable);
  }
  
  private boolean a(int paramInt)
  {
    if ((paramInt == 7) || (paramInt == 4) || (paramInt == 8) || (paramInt == 5))
    {
      if (SystemClock.elapsedRealtime() - a < 500L) {
        return true;
      }
      a = SystemClock.elapsedRealtime();
    }
    return false;
  }
  
  public static final PendingIntent b(Context paramContext, UpdateInfo paramUpdateInfo)
  {
    Intent localIntent = new Intent(paramContext, MzUpdateComponentService.class);
    localIntent.putExtra("action", 4);
    localIntent.putExtra("update_info", paramUpdateInfo);
    return a(paramContext, 4, localIntent);
  }
  
  private static final Intent b(Context paramContext, UpdateInfo paramUpdateInfo, String paramString, MzUpdateResponse paramMzUpdateResponse)
  {
    paramContext = new Intent(paramContext, MzUpdateComponentService.class);
    paramContext.putExtra("action", 2);
    paramContext.putExtra("update_info", paramUpdateInfo);
    paramContext.putExtra("apk_path", paramString);
    if (paramMzUpdateResponse != null) {
      paramContext.putExtra("response", paramMzUpdateResponse);
    }
    return paramContext;
  }
  
  private void b()
  {
    if (b != null) {
      b.a();
    }
  }
  
  public static final void b(Context paramContext)
  {
    anf.b(paramContext, "Request push register");
    Intent localIntent = new Intent(paramContext, MzUpdateComponentService.class);
    localIntent.putExtra("action", 10);
    paramContext.startService(localIntent);
  }
  
  private void b(UpdateInfo paramUpdateInfo)
  {
    if (paramUpdateInfo != null)
    {
      aki.a(this);
      and.a(this).a(and.a.l, mVersionName);
      new anc(this, paramUpdateInfo).e();
      return;
    }
    anf.d("notifyUpdateFinish info null");
  }
  
  private void b(UpdateInfo paramUpdateInfo, String paramString, MzUpdateResponse paramMzUpdateResponse)
  {
    if ((paramUpdateInfo != null) && (!TextUtils.isEmpty(paramString))) {
      a(paramUpdateInfo, paramString, paramMzUpdateResponse, true);
    }
    while (paramMzUpdateResponse == null) {
      return;
    }
    paramMzUpdateResponse.d();
  }
  
  public static final PendingIntent c(Context paramContext, UpdateInfo paramUpdateInfo)
  {
    Intent localIntent = new Intent(paramContext, MzUpdateComponentService.class);
    localIntent.putExtra("action", 5);
    localIntent.putExtra("update_info", paramUpdateInfo);
    return a(paramContext, 5, localIntent);
  }
  
  private void c()
  {
    String str = PushManager.getPushId(this);
    if (!TextUtils.isEmpty(str))
    {
      if (akf.c(this, str)) {
        amx.a(this, true);
      }
    }
    else {
      return;
    }
    anf.d("register push error.");
  }
  
  public static final void c(Context paramContext)
  {
    Intent localIntent = new Intent(paramContext, MzUpdateComponentService.class);
    localIntent.putExtra("action", 9);
    paramContext.startService(localIntent);
  }
  
  private void c(UpdateInfo paramUpdateInfo)
  {
    if (paramUpdateInfo != null) {
      new akz(this, paramUpdateInfo).b();
    }
  }
  
  public static final PendingIntent d(Context paramContext, UpdateInfo paramUpdateInfo)
  {
    Intent localIntent = new Intent(paramContext, MzUpdateComponentService.class);
    localIntent.putExtra("action", 6);
    localIntent.putExtra("update_info", paramUpdateInfo);
    return a(paramContext, 6, localIntent);
  }
  
  private void d(UpdateInfo paramUpdateInfo)
  {
    if (paramUpdateInfo != null) {
      new alo(this, null, paramUpdateInfo, true, true).b();
    }
  }
  
  public static final PendingIntent e(Context paramContext, UpdateInfo paramUpdateInfo)
  {
    Intent localIntent = new Intent(paramContext, MzUpdateComponentService.class);
    localIntent.putExtra("action", 7);
    localIntent.putExtra("update_info", paramUpdateInfo);
    return a(paramContext, 7, localIntent);
  }
  
  private void e(UpdateInfo paramUpdateInfo)
  {
    if (paramUpdateInfo != null)
    {
      new anc(this, paramUpdateInfo).f();
      new all(this, paramUpdateInfo, null, true).b();
    }
  }
  
  public static final PendingIntent f(Context paramContext, UpdateInfo paramUpdateInfo)
  {
    Intent localIntent = new Intent(paramContext, MzUpdateComponentService.class);
    localIntent.putExtra("action", 8);
    localIntent.putExtra("update_info", paramUpdateInfo);
    return a(paramContext, 8, localIntent);
  }
  
  private void f(UpdateInfo paramUpdateInfo)
  {
    if (paramUpdateInfo != null) {
      new alb(this, paramUpdateInfo, true).b();
    }
  }
  
  public static final PendingIntent g(Context paramContext, UpdateInfo paramUpdateInfo)
  {
    Intent localIntent = new Intent(paramContext, MzUpdateComponentService.class);
    localIntent.putExtra("action", 11);
    localIntent.putExtra("update_info", paramUpdateInfo);
    return a(paramContext, 11, localIntent);
  }
  
  private void g(UpdateInfo paramUpdateInfo)
  {
    if (paramUpdateInfo != null) {
      new alb(this, paramUpdateInfo, false).b();
    }
  }
  
  protected amj h(Context paramContext, UpdateInfo paramUpdateInfo)
  {
    return new amj(paramContext, mVerifyMode, paramContext.getPackageName(), mSizeByte, mDigest, 0);
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    anf.d("onCreate");
    HandlerThread localHandlerThread = new HandlerThread("MzUpdateComponentService[InternalTread]");
    localHandlerThread.start();
    c = localHandlerThread.getLooper();
    d = new a(c);
    e = new Handler(getMainLooper());
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    anf.d("onDestroy");
    c.quit();
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    a(paramIntent, paramInt2);
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
      Object localObject1 = null;
      Object localObject2 = null;
      Bundle localBundle = ((Intent)obj).getExtras();
      switch (what)
      {
      }
      for (;;)
      {
        stopSelf(arg1);
        return;
        anj.a(true);
        localObject1 = localObject2;
        if (localBundle.containsKey("response")) {
          localObject1 = (MzUpdateResponse)localBundle.getParcelable("response");
        }
        localObject2 = (UpdateInfo)localBundle.getParcelable("update_info");
        if (localBundle.containsKey("from_notification")) {
          and.a(MzUpdateComponentService.this).a(and.a.d, mVersionName, anl.b(MzUpdateComponentService.this, getPackageName()));
        }
        MzUpdateComponentService.a(MzUpdateComponentService.this, (UpdateInfo)localObject2, (MzUpdateResponse)localObject1);
        anj.a(false);
        continue;
        anj.a(true);
        localObject2 = localBundle.getString("apk_path");
        if (localBundle.containsKey("response")) {
          localObject1 = (MzUpdateResponse)localBundle.getParcelable("response");
        }
        MzUpdateComponentService.a(MzUpdateComponentService.this, (UpdateInfo)localBundle.getParcelable("update_info"), (String)localObject2, (MzUpdateResponse)localObject1);
        anj.a(false);
        continue;
        MzUpdateComponentService.a(MzUpdateComponentService.this);
        continue;
        MzUpdateComponentService.b(MzUpdateComponentService.this);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.update.service.MzUpdateComponentService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */