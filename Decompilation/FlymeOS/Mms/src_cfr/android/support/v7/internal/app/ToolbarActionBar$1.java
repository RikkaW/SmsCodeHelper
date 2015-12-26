/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package android.support.v7.internal.app;

import android.support.v7.internal.app.ToolbarActionBar;

class ToolbarActionBar$1
implements Runnable {
    final /* synthetic */ ToolbarActionBar this$0;

    ToolbarActionBar$1(ToolbarActionBar toolbarActionBar) {
        this.this$0 = toolbarActionBar;
    }

    @Override
    public void run() {
        this.this$0.populateOptionsMenu();
    }
}

