/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.view.View
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  java.lang.Object
 */
package android.support.v7.app;

import android.content.DialogInterface;
import android.support.v7.app.AlertController;
import android.view.View;
import android.widget.AdapterView;

class AlertController$AlertParams$3
implements AdapterView.OnItemClickListener {
    final /* synthetic */ AlertController.AlertParams this$0;
    final /* synthetic */ AlertController val$dialog;

    AlertController$AlertParams$3(AlertController.AlertParams alertParams, AlertController alertController) {
        this.this$0 = alertParams;
        this.val$dialog = alertController;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int n2, long l2) {
        this.this$0.mOnClickListener.onClick((DialogInterface)AlertController.access$600(this.val$dialog), n2);
        if (!this.this$0.mIsSingleChoice) {
            AlertController.access$600(this.val$dialog).dismiss();
        }
    }
}

