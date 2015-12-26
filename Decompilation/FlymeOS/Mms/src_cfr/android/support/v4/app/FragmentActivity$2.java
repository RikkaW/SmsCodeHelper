/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.Window
 *  java.lang.Object
 */
package android.support.v4.app;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentContainer;
import android.view.View;
import android.view.Window;

class FragmentActivity$2
implements FragmentContainer {
    final /* synthetic */ FragmentActivity this$0;

    FragmentActivity$2(FragmentActivity fragmentActivity) {
        this.this$0 = fragmentActivity;
    }

    @Nullable
    @Override
    public View findViewById(int n2) {
        return this.this$0.findViewById(n2);
    }

    @Override
    public boolean hasView() {
        Window window = this.this$0.getWindow();
        if (window != null && window.peekDecorView() != null) {
            return true;
        }
        return false;
    }
}

