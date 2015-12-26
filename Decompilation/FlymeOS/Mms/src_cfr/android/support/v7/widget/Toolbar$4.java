/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnClickListener
 *  java.lang.Object
 */
package android.support.v7.widget;

import android.support.v7.widget.Toolbar;
import android.view.View;

class Toolbar$4
implements View.OnClickListener {
    final /* synthetic */ Toolbar this$0;

    Toolbar$4(Toolbar toolbar) {
        this.this$0 = toolbar;
    }

    public void onClick(View view) {
        this.this$0.collapseActionView();
    }
}

