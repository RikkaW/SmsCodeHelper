/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.app;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentContainer;
import android.view.View;

class Fragment$1
implements FragmentContainer {
    final /* synthetic */ Fragment this$0;

    Fragment$1(Fragment fragment) {
        this.this$0 = fragment;
    }

    @Nullable
    @Override
    public View findViewById(int n2) {
        if (this.this$0.mView == null) {
            throw new IllegalStateException("Fragment does not have a view");
        }
        return this.this$0.mView.findViewById(n2);
    }

    @Override
    public boolean hasView() {
        if (this.this$0.mView != null) {
            return true;
        }
        return false;
    }
}

