/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.app;

import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManagerImpl;

class FragmentManagerImpl$4
implements Runnable {
    final /* synthetic */ FragmentManagerImpl this$0;
    final /* synthetic */ int val$flags;
    final /* synthetic */ int val$id;

    FragmentManagerImpl$4(FragmentManagerImpl fragmentManagerImpl, int n2, int n3) {
        this.this$0 = fragmentManagerImpl;
        this.val$id = n2;
        this.val$flags = n3;
    }

    @Override
    public void run() {
        this.this$0.popBackStackState(this.this$0.mActivity.mHandler, null, this.val$id, this.val$flags);
    }
}

