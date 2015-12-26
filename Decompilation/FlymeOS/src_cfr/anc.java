/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Notification
 *  android.app.Notification$BigTextStyle
 *  android.app.Notification$Builder
 *  android.app.Notification$Style
 *  android.app.NotificationManager
 *  android.app.PendingIntent
 *  android.app.Service
 *  android.content.Context
 *  android.content.Intent
 *  android.graphics.Bitmap
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import com.meizu.update.UpdateInfo;
import com.meizu.update.service.MzUpdateComponentService;

public class anc {
    private Service a;
    private UpdateInfo b;
    private NotificationManager c;
    private Notification.Builder d;

    public anc(Service service, UpdateInfo updateInfo) {
        this.a = service;
        this.b = updateInfo;
        this.c = (NotificationManager)this.a.getSystemService("notification");
    }

    public static String a(UpdateInfo updateInfo, Context context) {
        String string2 = anl.h(context);
        return String.format((String)context.getString(akq.d.mzuc_update_title_s), (Object[])new Object[]{string2, updateInfo.mVersionName});
    }

    public static final void a(Notification.Builder builder) {
        try {
            aji.a(anc.c(builder), "setInternalApp", new Class[]{Integer.TYPE}, new Object[]{1});
            return;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static final void a(Notification.Builder builder, Bitmap bitmap) {
        builder.setSmallIcon(akq.c.mzuc_stat_sys_update);
        if (bitmap != null) {
            builder.setLargeIcon(bitmap);
        }
    }

    public static void a(Context context) {
        ((NotificationManager)context.getSystemService("notification")).cancel(101);
    }

    public static final void a(Context context, Notification.Builder builder) {
        try {
            aji.a(anc.c(builder), "setCircleProgressBar", new Class[]{Boolean.TYPE}, new Object[]{true});
            return;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    private void a(String string2, PendingIntent pendingIntent) {
        this.a(string2, pendingIntent, 100);
    }

    private void a(String string2, PendingIntent pendingIntent, int n2) {
        String string3 = anc.b(this.b, (Context)this.a);
        Notification.Builder builder = new Notification.Builder((Context)this.a);
        builder.setTicker((CharSequence)string3);
        builder.setContentTitle((CharSequence)string3);
        builder.setContentText((CharSequence)string2);
        builder.setAutoCancel(true);
        builder.setWhen(System.currentTimeMillis());
        anc.a(builder, this.g());
        anc.a(builder);
        if (pendingIntent != null) {
            builder.setContentIntent(pendingIntent);
        }
        string2 = this.b(builder);
        this.c.notify(n2, (Notification)string2);
    }

    private void a(String string2, String string3, int n2) {
        Notification.Builder builder = new Notification.Builder((Context)this.a);
        builder.setTicker((CharSequence)string2);
        builder.setContentTitle((CharSequence)string2);
        builder.setContentText((CharSequence)string3);
        builder.setContentIntent(this.m());
        builder.setOngoing(true);
        builder.setWhen(0);
        builder.setProgress(100, n2, false);
        anc.a(builder, this.g());
        anc.a(builder);
        anc.a((Context)this.a, builder);
        this.d = builder;
    }

    public static String b(UpdateInfo updateInfo, Context context) {
        String string2 = anl.h(context);
        return String.format((String)context.getString(akq.d.mzuc_update_msg_title_s), (Object[])new Object[]{string2, updateInfo.mVersionName});
    }

    private static final Object c(Notification.Builder builder) {
        return aji.a((Object)builder, builder.getClass(), "mFlymeNotificationBuilder");
    }

    private void n() {
        this.a.stopForeground(true);
        this.c.cancel(100);
        this.d = null;
    }

    public void a() {
        String string2 = anc.a(this.b, (Context)this.a);
        String string3 = String.format((String)this.a.getString(akq.d.mzuc_notification_message_s), (Object[])new Object[]{this.b.mVersionDesc});
        Notification.Builder builder = new Notification.Builder((Context)this.a);
        builder.setTicker((CharSequence)string2);
        builder.setContentTitle((CharSequence)string2);
        builder.setContentText((CharSequence)string3);
        builder.setContentIntent(this.h());
        builder.setAutoCancel(true);
        anc.a(builder, this.g());
        anc.a(builder);
        if (ani.c()) {
            Notification.BigTextStyle bigTextStyle = new Notification.BigTextStyle();
            bigTextStyle.bigText((CharSequence)string3);
            bigTextStyle.setBigContentTitle((CharSequence)string2);
            builder.setStyle((Notification.Style)bigTextStyle);
            builder.setContentInfo(null);
            if (!this.b.mNeedUpdate) {
                builder.addAction(0, (CharSequence)this.a.getString(akq.d.mzuc_skip_version), this.j());
            }
            builder.addAction(0, (CharSequence)this.a.getString(akq.d.mzuc_update_immediately), this.i());
        }
        string2 = this.b(builder);
        this.c.notify(100, (Notification)string2);
        and.a((Context)this.a).a(and.a.c, this.b.mVersionName, anl.b((Context)this.a, this.a.getPackageName()));
    }

    public void a(int n2, long l2) {
        String string2 = anc.b(this.b, (Context)this.a);
        String string3 = anl.a(l2) + "/S";
        string3 = String.format((String)this.a.getString(akq.d.mzuc_download_progress_desc_s), (Object[])new Object[]{string3, this.b.mSize});
        if (this.d == null) {
            this.a(string2, string3, n2);
            this.a.startForeground(100, this.b(this.d));
            return;
        }
        this.d.setContentText((CharSequence)string3);
        this.d.setProgress(100, n2, false);
        this.c.notify(100, this.b(this.d));
    }

    public void a(String string2) {
        string2 = this.b(string2);
        this.a(this.a.getString(akq.d.mzuc_download_finish_install), (PendingIntent)string2);
    }

    public Notification b(Notification.Builder builder) {
        if (ani.a()) {
            return builder.build();
        }
        return builder.getNotification();
    }

    public PendingIntent b(String string2) {
        return MzUpdateComponentService.a((Context)this.a, this.b, string2);
    }

    public void b() {
        String string2 = anc.b(this.b, (Context)this.a);
        String string3 = this.a.getString(akq.d.mzuc_installing);
        Notification.Builder builder = new Notification.Builder((Context)this.a);
        builder.setTicker((CharSequence)string2);
        builder.setContentTitle((CharSequence)string2);
        builder.setContentText((CharSequence)string3);
        builder.setOngoing(true);
        builder.setWhen(0);
        anc.a(builder, this.g());
        anc.a(builder);
        string2 = this.b(builder);
        this.c.notify(100, (Notification)string2);
    }

    public void c() {
        this.n();
        this.a(this.a.getString(akq.d.mzuc_download_fail), this.k());
    }

    public void d() {
        this.a(this.a.getString(akq.d.mzuc_install_fail), this.l());
    }

    public void e() {
        Intent intent;
        this.c.cancel(100);
        Intent intent2 = intent = amy.a((Context)this.a);
        if (intent == null) {
            intent2 = new Intent();
        }
        intent2 = PendingIntent.getActivity((Context)this.a, (int)0, (Intent)intent2, (int)134217728);
        this.a(this.a.getString(akq.d.mzuc_update_finish), (PendingIntent)intent2, 101);
    }

    public void f() {
        this.n();
        this.c.cancel(100);
    }

    public Bitmap g() {
        return anl.a(this.a.getPackageName(), (Context)this.a);
    }

    public PendingIntent h() {
        return MzUpdateComponentService.c((Context)this.a, this.b);
    }

    public PendingIntent i() {
        return MzUpdateComponentService.a((Context)this.a, this.b);
    }

    public PendingIntent j() {
        return MzUpdateComponentService.d((Context)this.a, this.b);
    }

    public PendingIntent k() {
        return MzUpdateComponentService.e((Context)this.a, this.b);
    }

    public PendingIntent l() {
        return MzUpdateComponentService.f((Context)this.a, this.b);
    }

    public PendingIntent m() {
        return MzUpdateComponentService.b((Context)this.a, this.b);
    }
}

