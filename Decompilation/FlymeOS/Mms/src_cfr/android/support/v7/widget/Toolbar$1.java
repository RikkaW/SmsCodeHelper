/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.MenuItem
 *  java.lang.Object
 */
package android.support.v7.widget;

import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

class Toolbar$1
implements ActionMenuView.OnMenuItemClickListener {
    final /* synthetic */ Toolbar this$0;

    Toolbar$1(Toolbar toolbar) {
        this.this$0 = toolbar;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if (Toolbar.access$000(this.this$0) != null) {
            return Toolbar.access$000(this.this$0).onMenuItemClick(menuItem);
        }
        return false;
    }
}

