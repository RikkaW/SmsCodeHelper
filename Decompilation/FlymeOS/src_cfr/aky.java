/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Dialog
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  java.lang.String
 */
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class aky
extends BroadcastReceiver {
    final /* synthetic */ aks a;

    aky(aks aks2) {
        this.a = aks2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void onReceive(Context context, Intent intent) {
        anf.c("Receive dialog show broadcast.");
        if (this.a.d == null || !this.a.d.isShowing()) return;
        try {
            this.a.d.dismiss();
            return;
        }
        catch (Exception var1_2) {
            anf.d("dismiss dialog exception:" + var1_2.getMessage());
            this.a.d.hide();
            aks.a(this.a);
            return;
        }
    }
}

