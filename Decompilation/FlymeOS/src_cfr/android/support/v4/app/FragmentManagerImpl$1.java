/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package android.support.v4.app;

import android.support.v4.app.FragmentManagerImpl;

class FragmentManagerImpl$1
implements Runnable {
    final /* synthetic */ FragmentManagerImpl this$0;

    FragmentManagerImpl$1(FragmentManagerImpl fragmentManagerImpl) {
        this.this$0 = fragmentManagerImpl;
    }

    @Override
    public void run() {
        this.this$0.execPendingActions();
    }
}

