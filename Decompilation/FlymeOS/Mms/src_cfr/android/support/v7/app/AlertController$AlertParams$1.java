/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.ArrayAdapter
 *  android.widget.ListView
 *  java.lang.Object
 */
package android.support.v7.app;

import android.content.Context;
import android.support.v7.app.AlertController;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

class AlertController$AlertParams$1
extends ArrayAdapter<CharSequence> {
    final /* synthetic */ AlertController.AlertParams this$0;
    final /* synthetic */ ListView val$listView;

    AlertController$AlertParams$1(AlertController.AlertParams alertParams, Context context, int n2, int n3, CharSequence[] arrcharSequence, ListView listView) {
        this.this$0 = alertParams;
        this.val$listView = listView;
        super(context, n2, n3, (Object[])arrcharSequence);
    }

    public View getView(int n2, View view, ViewGroup viewGroup) {
        view = super.getView(n2, view, viewGroup);
        if (this.this$0.mCheckedItems != null && this.this$0.mCheckedItems[n2]) {
            this.val$listView.setItemChecked(n2, true);
        }
        return view;
    }
}

