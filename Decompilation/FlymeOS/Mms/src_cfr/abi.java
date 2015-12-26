/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.ServiceConnection
 *  android.os.IBinder
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

class abi
implements ServiceConnection {
    final /* synthetic */ abh a;

    abi(abh abh2) {
        this.a = abh2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void onServiceConnected(ComponentName object, IBinder iBinder) {
        abh.a(this.a, aiv.a.a(iBinder));
        Log.i((String)"SnsMessageManager", (String)("onServiceConnected, mSnsSmsService is " + abh.a(this.a)));
        object = abh.b(this.a);
        synchronized (object) {
            abh.b(this.a).notifyAll();
            return;
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        abh.a(this.a, null);
        Log.e((String)"SnsMessageManager", (String)"onServiceDisconnected, mSnsSmsService is null");
    }
}

