/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.mms.transaction.flyme.FlymeTransactionService;

class abc
implements air {
    final /* synthetic */ aba a;

    abc(aba aba2) {
        this.a = aba2;
    }

    @Override
    public void a() {
        Log.d((String)"SipSmsManager", (String)"onServiceConnected...");
        this.a.a(aba.d(this.a));
    }

    @Override
    public void b() {
        Log.d((String)"SipSmsManager", (String)"onServiceDisconnected...");
        this.a.b(aba.d(this.a));
        aba.a(this.a, null);
        Intent intent = new Intent(aba.c(this.a), (Class)FlymeTransactionService.class);
        intent.setAction("android.intent.action.ACTION_RESET_MSG_STATUS_WHEN_SERVICE_DISCONNECTED");
        intent.putExtra("From", aba.class.getName());
        aba.c(this.a).startService(intent);
    }
}

