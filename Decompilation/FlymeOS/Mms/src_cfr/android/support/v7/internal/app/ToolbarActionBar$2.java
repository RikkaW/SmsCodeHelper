/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.MenuItem
 *  java.lang.Object
 */
package android.support.v7.internal.app;

import android.support.v7.internal.app.ToolbarActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

class ToolbarActionBar$2
implements Toolbar.OnMenuItemClickListener {
    final /* synthetic */ ToolbarActionBar this$0;

    ToolbarActionBar$2(ToolbarActionBar toolbarActionBar) {
        this.this$0 = toolbarActionBar;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return ToolbarActionBar.access$000(this.this$0).onMenuItemSelected(0, menuItem);
    }
}

