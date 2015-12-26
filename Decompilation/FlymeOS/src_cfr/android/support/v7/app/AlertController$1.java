/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Message
 *  android.view.View
 *  android.view.View$OnClickListener
 *  java.lang.Object
 */
package android.support.v7.app;

import android.os.Message;
import android.support.v7.app.AlertController;
import android.view.View;

class AlertController$1
implements View.OnClickListener {
    final /* synthetic */ AlertController this$0;

    AlertController$1(AlertController alertController) {
        this.this$0 = alertController;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onClick(View view) {
        view = view == AlertController.access$000(this.this$0) && AlertController.access$100(this.this$0) != null ? Message.obtain((Message)AlertController.access$100(this.this$0)) : (view == AlertController.access$200(this.this$0) && AlertController.access$300(this.this$0) != null ? Message.obtain((Message)AlertController.access$300(this.this$0)) : (view == AlertController.access$400(this.this$0) && AlertController.access$500(this.this$0) != null ? Message.obtain((Message)AlertController.access$500(this.this$0)) : null));
        if (view != null) {
            view.sendToTarget();
        }
        AlertController.access$700(this.this$0).obtainMessage(1, (Object)AlertController.access$600(this.this$0)).sendToTarget();
    }
}

