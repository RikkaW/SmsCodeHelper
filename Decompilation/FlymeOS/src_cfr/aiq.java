/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.ServiceConnection
 *  android.os.IBinder
 *  java.lang.Object
 *  java.lang.String
 */
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class aiq {
    private Context a;
    private a b;
    private air c;
    private ais d;

    public aiq(Context context, air air2) {
        this.a = context;
        this.c = air2;
        this.b = new a();
    }

    public void a() {
        Intent intent = new Intent();
        intent.setAction("com.meizu.flymesms.BIND");
        intent.setPackage("com.meizu.cloud");
        this.a.bindService(intent, (ServiceConnection)this.b, 1);
    }

    public boolean a(aio aio2) {
        if (this.d != null) {
            this.d.a(aio2);
            return true;
        }
        this.a();
        return false;
    }

    public boolean a(aip aip2) {
        if (this.d != null) {
            this.d.a("com.android.mms", aip2);
            return true;
        }
        this.a();
        return false;
    }

    public boolean a(String string2) {
        if (this.d != null) {
            this.d.a(string2);
            return true;
        }
        this.a();
        return false;
    }

    public boolean a(String string2, String string3, PendingIntent pendingIntent, String string4, boolean bl2) {
        if (this.d != null) {
            this.d.a(string2, string3, pendingIntent, string4, bl2);
            return true;
        }
        this.a();
        return false;
    }

    public boolean a(String string2, String string3, String string4, String string5, long l2) {
        if (this.d != null) {
            this.d.a(string2, string3, string4, string5, l2);
            return true;
        }
        this.a();
        return false;
    }

    public boolean a(String[] arrstring, PendingIntent pendingIntent) {
        if (this.d != null) {
            this.d.a(arrstring, pendingIntent);
            return true;
        }
        this.a();
        return false;
    }

    public boolean a(String[] arrstring, String string2, byte[] arrby, String string3, boolean bl2, String string4) {
        if (this.d != null) {
            this.d.a(arrstring, string2, arrby, string3, bl2, string4);
            return true;
        }
        this.a();
        return false;
    }

    public boolean b(aio aio2) {
        if (this.d != null) {
            this.d.b(aio2);
            return true;
        }
        return false;
    }

    public boolean b(aip aip2) {
        if (this.d != null) {
            this.d.b("com.android.mms", aip2);
            return true;
        }
        return false;
    }

    class a
    implements ServiceConnection {
        private a() {
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            aiq.this.d = ais.a.a(iBinder);
            if (aiq.this.c != null) {
                aiq.this.c.a();
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            aiq.this.d = null;
            if (aiq.this.c != null) {
                aiq.this.c.b();
            }
        }
    }

}

