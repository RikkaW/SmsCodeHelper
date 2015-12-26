/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.Looper
 *  android.os.Message
 */
package android.support.v4.content;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;

class LocalBroadcastManager$1
extends Handler {
    final /* synthetic */ LocalBroadcastManager this$0;

    LocalBroadcastManager$1(LocalBroadcastManager localBroadcastManager, Looper looper) {
        this.this$0 = localBroadcastManager;
        super(looper);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            default: {
                super.handleMessage(message);
                return;
            }
            case 1: 
        }
        LocalBroadcastManager.access$000(this.this$0);
    }
}

