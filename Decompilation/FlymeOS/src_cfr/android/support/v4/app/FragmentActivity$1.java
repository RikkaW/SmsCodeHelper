/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.Message
 */
package android.support.v4.app;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManagerImpl;

class FragmentActivity$1
extends Handler {
    final /* synthetic */ FragmentActivity this$0;

    FragmentActivity$1(FragmentActivity fragmentActivity) {
        this.this$0 = fragmentActivity;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void handleMessage(Message message) {
        switch (message.what) {
            default: {
                super.handleMessage(message);
                return;
            }
            case 1: {
                if (!this.this$0.mStopped) return;
                {
                    this.this$0.doReallyStop(false);
                    return;
                }
            }
            case 2: 
        }
        this.this$0.onResumeFragments();
        this.this$0.mFragments.execPendingActions();
    }
}

