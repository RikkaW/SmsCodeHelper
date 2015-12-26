/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.Cursor
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.CheckedTextView
 *  android.widget.CursorAdapter
 *  android.widget.ListView
 *  java.lang.String
 */
package android.support.v7.app;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AlertController;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.ListView;

class AlertController$AlertParams$2
extends CursorAdapter {
    private final int mIsCheckedIndex;
    private final int mLabelIndex;
    final /* synthetic */ AlertController.AlertParams this$0;
    final /* synthetic */ AlertController val$dialog;
    final /* synthetic */ ListView val$listView;

    AlertController$AlertParams$2(AlertController.AlertParams alertParams, Context context, Cursor cursor, boolean bl2, ListView listView, AlertController alertController) {
        this.this$0 = alertParams;
        this.val$listView = listView;
        this.val$dialog = alertController;
        super(context, cursor, bl2);
        alertParams = this.getCursor();
        this.mLabelIndex = alertParams.getColumnIndexOrThrow(this.this$0.mLabelColumn);
        this.mIsCheckedIndex = alertParams.getColumnIndexOrThrow(this.this$0.mIsCheckedColumn);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void bindView(View view, Context context, Cursor cursor) {
        ((CheckedTextView)view.findViewById(16908308)).setText((CharSequence)cursor.getString(this.mLabelIndex));
        view = this.val$listView;
        int n2 = cursor.getPosition();
        boolean bl2 = cursor.getInt(this.mIsCheckedIndex) == 1;
        view.setItemChecked(n2, bl2);
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.this$0.mInflater.inflate(AlertController.access$900(this.val$dialog), viewGroup, false);
    }
}

