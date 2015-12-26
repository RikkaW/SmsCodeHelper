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

class FragmentManagerImpl$3
implements Runnable {
    final /* synthetic */ FragmentManagerImpl this$0;
    final /* synthetic */ int val$flags;
    final /* synthetic */ String val$name;

    FragmentManagerImpl$3(FragmentManagerImpl fragmentManagerImpl, String string2, int n2) {
        this.this$0 = fragmentManagerImpl;
        this.val$name = string2;
        this.val$flags = n2;
    }

    @Override
    public void run() {
        this.this$0.popBackStackState(this.this$0.mActivity.mHandler, this.val$name, -1, this.val$flags);
    }
}

