/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Service
 *  android.content.Context
 *  android.content.Intent
 *  android.os.IBinder
 *  java.lang.Object
 *  java.lang.String
 */
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class ajp
extends Service {
    private ajj a;

    public IBinder onBind(Intent object) {
        if (this.a == null) {
            Context context;
            boolean bl2 = object.getBooleanExtra("online", false);
            boolean bl3 = object.getBooleanExtra("upload", true);
            object = context = this.getApplicationContext();
            if (context == null) {
                object = this;
            }
            this.a = ajn.a((Context)object, bl2, bl3);
        }
        return this.a.asBinder();
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int n2, int n3) {
        return super.onStartCommand(intent, n2, n3);
    }
}

