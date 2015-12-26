/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnMultiChoiceClickListener
 *  android.view.View
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.ListView
 *  java.lang.Object
 */
package android.support.v7.app;

import android.content.DialogInterface;
import android.support.v7.app.AlertController;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

class AlertController$AlertParams$4
implements AdapterView.OnItemClickListener {
    final /* synthetic */ AlertController.AlertParams this$0;
    final /* synthetic */ AlertController val$dialog;
    final /* synthetic */ ListView val$listView;

    AlertController$AlertParams$4(AlertController.AlertParams alertParams, ListView listView, AlertController alertController) {
        this.this$0 = alertParams;
        this.val$listView = listView;
        this.val$dialog = alertController;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int n2, long l2) {
        if (this.this$0.mCheckedItems != null) {
            this.this$0.mCheckedItems[n2] = this.val$listView.isItemChecked(n2);
        }
        this.this$0.mOnCheckboxClickListener.onClick((DialogInterface)AlertController.access$600(this.val$dialog), n2, this.val$listView.isItemChecked(n2));
    }
}

