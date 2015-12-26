/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnClickListener
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.support.v7.internal.widget.ActionBarContextView;
import android.support.v7.view.ActionMode;
import android.view.View;

class ActionBarContextView$1
implements View.OnClickListener {
    final /* synthetic */ ActionBarContextView this$0;
    final /* synthetic */ ActionMode val$mode;

    ActionBarContextView$1(ActionBarContextView actionBarContextView, ActionMode actionMode) {
        this.this$0 = actionBarContextView;
        this.val$mode = actionMode;
    }

    public void onClick(View view) {
        this.val$mode.finish();
    }
}

