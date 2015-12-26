/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package android.support.v7.internal.app;

import android.support.v7.internal.app.WindowDecorActionBar;
import android.support.v7.view.ActionMode;

class WindowDecorActionBar$ActionModeImpl$1
implements ActionMode.BackPressedListener {
    final /* synthetic */ WindowDecorActionBar.ActionModeImpl this$1;

    WindowDecorActionBar$ActionModeImpl$1(WindowDecorActionBar.ActionModeImpl actionModeImpl) {
        this.this$1 = actionModeImpl;
    }

    @Override
    public boolean onBackPressed() {
        return true;
    }
}

