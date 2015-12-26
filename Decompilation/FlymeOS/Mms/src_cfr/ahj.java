/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.ServiceConnection
 *  android.os.IBinder
 *  java.lang.Object
 */
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

class ahj
implements ServiceConnection {
    final /* synthetic */ ahi.a a;
    final /* synthetic */ ahi b;

    ahj(ahi ahi2, ahi.a a2) {
        this.b = ahi2;
        this.a = a2;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        ahi.a(this.b, aft.a.a(iBinder));
        this.a.a(0);
    }

    public void onServiceDisconnected(ComponentName componentName) {
        ahi.a(this.b, null);
    }
}

