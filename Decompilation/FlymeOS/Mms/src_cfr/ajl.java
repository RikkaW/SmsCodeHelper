/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.ServiceConnection
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.content.pm.ServiceInfo
 *  android.database.ContentObserver
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.HandlerThread
 *  android.os.IBinder
 *  android.os.Looper
 *  android.os.Message
 *  android.provider.Settings
 *  android.provider.Settings$System
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import com.meizu.statsapp.UsageStatsProxy;
import com.meizu.statsapp.UsageStatusLog;
import com.meizu.statsapp.util.Utils;

public final class ajl {
    private Context a;
    private boolean b;
    private boolean c;
    private HandlerThread d = new HandlerThread("UsageStatsManagerThread");
    private a e;
    private b f;
    private volatile boolean g = true;
    private c h;
    private volatile ajj i;

    public ajl(Context context, boolean bl2, boolean bl3) {
        this.a = context;
        this.b = bl2;
        this.c = bl3;
        this.d.start();
        this.e = new a(this.d.getLooper());
        this.e.post((Runnable)new ajm(this));
    }

    static /* synthetic */ void a(ajl ajl2) {
        ajl2.b();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean a(Context context) {
        boolean bl3 = false;
        PackageManager packageManager = context.getPackageManager();
        boolean bl2 = bl3;
        if (packageManager == null) return bl2;
        try {
            context = packageManager.getServiceInfo(new ComponentName(context.getPackageName(), ajp.class.getName()), 0);
            bl2 = bl3;
            if (context == null) return bl2;
            return true;
        }
        catch (PackageManager.NameNotFoundException nameNotFoundException) {
            UsageStatusLog.d("UsageStatsManager", nameNotFoundException.getMessage());
            return false;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void b() {
        boolean bl2 = false;
        UsageStatusLog.initLog();
        if (ajl.a(this.a)) {
            HandlerThread handlerThread = this.d;
            synchronized (handlerThread) {
                this.h = new c(null);
                Intent intent = new Intent(this.a, (Class)ajp.class);
                intent.putExtra("online", this.b);
                intent.putExtra("upload", this.c);
                UsageStatusLog.d("UsageStatsManager", "bindService, " + this.h);
                this.a.bindService(intent, (ServiceConnection)this.h, 1);
                try {
                    this.d.wait();
                }
                catch (InterruptedException var3_4) {
                    var3_4.printStackTrace();
                }
            }
        } else {
            this.i = ajn.a(this.a, this.b, this.c);
        }
        if (!this.b) {
            if (Settings.System.getInt((ContentResolver)this.a.getContentResolver(), (String)"meizu_data_collection", (int)0) != 0) {
                bl2 = true;
            }
            this.g = bl2;
            this.f = new b(this.e);
            this.a.getContentResolver().registerContentObserver(Settings.System.getUriFor((String)"meizu_data_collection"), true, (ContentObserver)this.f);
        }
    }

    public void a(UsageStatsProxy.Event event) {
        Message message = this.e.obtainMessage(1);
        message.obj = event;
        this.e.sendMessage(message);
    }

    public void a(String string2, boolean bl2, String string3, long l2) {
        if (Utils.isEmpty(string3)) {
            return;
        }
        Message message = this.e.obtainMessage(3);
        Bundle bundle = new Bundle();
        bundle.putString("pckageName", string2);
        bundle.putBoolean("start", bl2);
        bundle.putString("name", string3);
        bundle.putLong("time", l2);
        message.setData(bundle);
        this.e.sendMessage(message);
    }

    public boolean a() {
        return this.g;
    }

    class a
    extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        public void handleMessage(Message message) {
            switch (message.what) {
                default: {
                    return;
                }
                case 3: {
                    try {
                        message = message.getData();
                        if (ajl.this.i == null) return;
                        ajl.this.i.a(message.getString("pckageName"), message.getBoolean("start"), message.getString("name"), message.getLong("time"));
                        return;
                    }
                    catch (Exception var1_2) {
                        var1_2.printStackTrace();
                        return;
                    }
                }
                case 1: {
                    try {
                        if (ajl.this.i == null) return;
                        ajl.this.i.a((UsageStatsProxy.Event)message.obj);
                        return;
                    }
                    catch (Exception var1_3) {
                        var1_3.printStackTrace();
                        return;
                    }
                }
                case 2: {
                    try {
                        if (ajl.this.i == null) return;
                        ajl.this.i.b((UsageStatsProxy.Event)message.obj);
                        return;
                    }
                    catch (Exception exception) {
                        exception.printStackTrace();
                        return;
                    }
                }
                case 4: 
            }
            boolean bl2 = message.arg1 != 0;
            if (ajl.this.c == bl2) return;
            ajl.this.c = bl2;
            try {
                if (ajl.this.i == null) return;
                ajl.this.i.a(ajl.this.c);
                return;
            }
            catch (Exception var1_5) {
                var1_5.printStackTrace();
                return;
            }
        }
    }

    class b
    extends ContentObserver {
        public b(Handler handler) {
            super(handler);
        }

        public void onChange(boolean bl2, Uri uri) {
            bl2 = false;
            if (Settings.System.getInt((ContentResolver)ajl.this.a.getContentResolver(), (String)"meizu_data_collection", (int)0) != 0) {
                bl2 = true;
            }
            UsageStatusLog.d("UsageStatsManager", "usage stats switch changed : " + bl2);
            if (ajl.this.g != bl2) {
                ajl.this.g = bl2;
            }
        }
    }

    class c
    implements ServiceConnection {
        private c() {
        }

        /* synthetic */ c(ajm ajm2) {
            this();
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            componentName = ajl.this.d;
            synchronized (componentName) {
                try {
                    UsageStatusLog.d("UsageStatsManager", "onServiceConnected, " + ajl.this.h);
                    ajl.this.i = ajj.a.a(iBinder);
                    UsageStatusLog.d("UsageStatsManager", "unbindService, " + ajl.this.h);
                    ajl.this.a.unbindService((ServiceConnection)ajl.this.h);
                }
                catch (Exception var2_3) {
                    var2_3.printStackTrace();
                }
                ajl.this.d.notifyAll();
                return;
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            UsageStatusLog.d("UsageStatsManager", "onServiceDisconnected, " + ajl.this.h);
        }
    }

}

