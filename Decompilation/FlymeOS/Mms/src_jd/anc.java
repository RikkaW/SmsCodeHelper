import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import com.meizu.update.UpdateInfo;
import com.meizu.update.service.MzUpdateComponentService;

public class anc
{
  private Service a;
  private UpdateInfo b;
  private NotificationManager c;
  private Notification.Builder d;
  
  public anc(Service paramService, UpdateInfo paramUpdateInfo)
  {
    a = paramService;
    b = paramUpdateInfo;
    c = ((NotificationManager)a.getSystemService("notification"));
  }
  
  public static String a(UpdateInfo paramUpdateInfo, Context paramContext)
  {
    String str = anl.h(paramContext);
    return String.format(paramContext.getString(akq.d.mzuc_update_title_s), new Object[] { str, mVersionName });
  }
  
  public static final void a(Notification.Builder paramBuilder)
  {
    try
    {
      aji.a(c(paramBuilder), "setInternalApp", new Class[] { Integer.TYPE }, new Object[] { Integer.valueOf(1) });
      return;
    }
    catch (Exception paramBuilder)
    {
      paramBuilder.printStackTrace();
    }
  }
  
  public static final void a(Notification.Builder paramBuilder, Bitmap paramBitmap)
  {
    paramBuilder.setSmallIcon(akq.c.mzuc_stat_sys_update);
    if (paramBitmap != null) {
      paramBuilder.setLargeIcon(paramBitmap);
    }
  }
  
  public static void a(Context paramContext)
  {
    ((NotificationManager)paramContext.getSystemService("notification")).cancel(101);
  }
  
  public static final void a(Context paramContext, Notification.Builder paramBuilder)
  {
    try
    {
      aji.a(c(paramBuilder), "setCircleProgressBar", new Class[] { Boolean.TYPE }, new Object[] { Boolean.valueOf(true) });
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private void a(String paramString, PendingIntent paramPendingIntent)
  {
    a(paramString, paramPendingIntent, 100);
  }
  
  private void a(String paramString, PendingIntent paramPendingIntent, int paramInt)
  {
    String str = b(b, a);
    Notification.Builder localBuilder = new Notification.Builder(a);
    localBuilder.setTicker(str);
    localBuilder.setContentTitle(str);
    localBuilder.setContentText(paramString);
    localBuilder.setAutoCancel(true);
    localBuilder.setWhen(System.currentTimeMillis());
    a(localBuilder, g());
    a(localBuilder);
    if (paramPendingIntent != null) {
      localBuilder.setContentIntent(paramPendingIntent);
    }
    paramString = b(localBuilder);
    c.notify(paramInt, paramString);
  }
  
  private void a(String paramString1, String paramString2, int paramInt)
  {
    Notification.Builder localBuilder = new Notification.Builder(a);
    localBuilder.setTicker(paramString1);
    localBuilder.setContentTitle(paramString1);
    localBuilder.setContentText(paramString2);
    localBuilder.setContentIntent(m());
    localBuilder.setOngoing(true);
    localBuilder.setWhen(0L);
    localBuilder.setProgress(100, paramInt, false);
    a(localBuilder, g());
    a(localBuilder);
    a(a, localBuilder);
    d = localBuilder;
  }
  
  public static String b(UpdateInfo paramUpdateInfo, Context paramContext)
  {
    String str = anl.h(paramContext);
    return String.format(paramContext.getString(akq.d.mzuc_update_msg_title_s), new Object[] { str, mVersionName });
  }
  
  private static final Object c(Notification.Builder paramBuilder)
  {
    return aji.a(paramBuilder, paramBuilder.getClass(), "mFlymeNotificationBuilder");
  }
  
  private void n()
  {
    a.stopForeground(true);
    c.cancel(100);
    d = null;
  }
  
  public void a()
  {
    Object localObject = a(b, a);
    String str = String.format(a.getString(akq.d.mzuc_notification_message_s), new Object[] { b.mVersionDesc });
    Notification.Builder localBuilder = new Notification.Builder(a);
    localBuilder.setTicker((CharSequence)localObject);
    localBuilder.setContentTitle((CharSequence)localObject);
    localBuilder.setContentText(str);
    localBuilder.setContentIntent(h());
    localBuilder.setAutoCancel(true);
    a(localBuilder, g());
    a(localBuilder);
    if (ani.c())
    {
      Notification.BigTextStyle localBigTextStyle = new Notification.BigTextStyle();
      localBigTextStyle.bigText(str);
      localBigTextStyle.setBigContentTitle((CharSequence)localObject);
      localBuilder.setStyle(localBigTextStyle);
      localBuilder.setContentInfo(null);
      if (!b.mNeedUpdate) {
        localBuilder.addAction(0, a.getString(akq.d.mzuc_skip_version), j());
      }
      localBuilder.addAction(0, a.getString(akq.d.mzuc_update_immediately), i());
    }
    localObject = b(localBuilder);
    c.notify(100, (Notification)localObject);
    and.a(a).a(and.a.c, b.mVersionName, anl.b(a, a.getPackageName()));
  }
  
  public void a(int paramInt, long paramLong)
  {
    String str1 = b(b, a);
    String str2 = anl.a(paramLong) + "/S";
    str2 = String.format(a.getString(akq.d.mzuc_download_progress_desc_s), new Object[] { str2, b.mSize });
    if (d == null)
    {
      a(str1, str2, paramInt);
      a.startForeground(100, b(d));
      return;
    }
    d.setContentText(str2);
    d.setProgress(100, paramInt, false);
    c.notify(100, b(d));
  }
  
  public void a(String paramString)
  {
    paramString = b(paramString);
    a(a.getString(akq.d.mzuc_download_finish_install), paramString);
  }
  
  public Notification b(Notification.Builder paramBuilder)
  {
    if (ani.a()) {
      return paramBuilder.build();
    }
    return paramBuilder.getNotification();
  }
  
  public PendingIntent b(String paramString)
  {
    return MzUpdateComponentService.a(a, b, paramString);
  }
  
  public void b()
  {
    Object localObject = b(b, a);
    String str = a.getString(akq.d.mzuc_installing);
    Notification.Builder localBuilder = new Notification.Builder(a);
    localBuilder.setTicker((CharSequence)localObject);
    localBuilder.setContentTitle((CharSequence)localObject);
    localBuilder.setContentText(str);
    localBuilder.setOngoing(true);
    localBuilder.setWhen(0L);
    a(localBuilder, g());
    a(localBuilder);
    localObject = b(localBuilder);
    c.notify(100, (Notification)localObject);
  }
  
  public void c()
  {
    n();
    a(a.getString(akq.d.mzuc_download_fail), k());
  }
  
  public void d()
  {
    a(a.getString(akq.d.mzuc_install_fail), l());
  }
  
  public void e()
  {
    c.cancel(100);
    Intent localIntent = amy.a(a);
    Object localObject = localIntent;
    if (localIntent == null) {
      localObject = new Intent();
    }
    localObject = PendingIntent.getActivity(a, 0, (Intent)localObject, 134217728);
    a(a.getString(akq.d.mzuc_update_finish), (PendingIntent)localObject, 101);
  }
  
  public void f()
  {
    n();
    c.cancel(100);
  }
  
  public Bitmap g()
  {
    return anl.a(a.getPackageName(), a);
  }
  
  public PendingIntent h()
  {
    return MzUpdateComponentService.c(a, b);
  }
  
  public PendingIntent i()
  {
    return MzUpdateComponentService.a(a, b);
  }
  
  public PendingIntent j()
  {
    return MzUpdateComponentService.d(a, b);
  }
  
  public PendingIntent k()
  {
    return MzUpdateComponentService.e(a, b);
  }
  
  public PendingIntent l()
  {
    return MzUpdateComponentService.f(a, b);
  }
  
  public PendingIntent m()
  {
    return MzUpdateComponentService.b(a, b);
  }
}

/* Location:
 * Qualified Name:     anc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */