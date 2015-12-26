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

class FragmentManagerImpl$2
implements Runnable {
    final /* synthetic */ FragmentManagerImpl this$0;

    FragmentManagerImpl$2(FragmentManagerImpl fragmentManagerImpl) {
        this.this$0 = fragmentManagerImpl;
    }

    @Override
    public void run() {
        this.this$0.popBackStackState(this.this$0.mActivity.mHandler, null, -1, 0);
    }
}

