/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Dialog
 *  android.app.FragmentManager
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.os.AsyncTask
 *  java.lang.Boolean
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 */
package miui.external;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.AsyncTask;
import miui.external.SdkErrorActivity;

class c
implements DialogInterface.OnClickListener {
    final /* synthetic */ SdkErrorActivity a;

    c(SdkErrorActivity sdkErrorActivity) {
        this.a = sdkErrorActivity;
    }

    public void onClick(DialogInterface dialogInterface, int n) {
        dialogInterface.dismiss();
        dialogInterface = SdkErrorActivity.a(this.a);
        this.a.new SdkErrorActivity.a((Dialog)dialogInterface).show(this.a.getFragmentManager(), "SdkUpdatePromptDialog");
        new AsyncTask<Void, Void, Boolean>((Dialog)dialogInterface){
            final /* synthetic */ Dialog d;

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            protected /* varargs */ Boolean a(Void ... arrvoid) {
                try {
                    Thread.sleep((long)5000);
                }
                catch (InterruptedException var1_2) {
                    var1_2.printStackTrace();
                    return SdkErrorActivity.b(c.this.a);
                }
                do {
                    return SdkErrorActivity.b(c.this.a);
                    break;
                } while (true);
            }

            /*
             * Enabled aggressive block sorting
             */
            protected void a(Boolean bl) {
                this.d.dismiss();
                bl = bl != false ? SdkErrorActivity.c(c.this.a) : SdkErrorActivity.d(c.this.a);
                c.this.a.new SdkErrorActivity.a((Dialog)bl).show(c.this.a.getFragmentManager(), "SdkUpdateFinishDialog");
            }
        }.execute((Object[])new Void[0]);
    }

}

