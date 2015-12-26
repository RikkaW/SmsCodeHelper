/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.KeyEvent
 *  android.view.KeyEvent$Callback
 *  java.lang.Object
 */
package android.support.v4.media;

import android.support.v4.media.TransportMediator;
import android.support.v4.media.TransportPerformer;
import android.view.KeyEvent;

class TransportMediator$2
implements KeyEvent.Callback {
    final /* synthetic */ TransportMediator this$0;

    TransportMediator$2(TransportMediator transportMediator) {
        this.this$0 = transportMediator;
    }

    public boolean onKeyDown(int n2, KeyEvent keyEvent) {
        if (TransportMediator.isMediaKey(n2)) {
            return this.this$0.mCallbacks.onMediaButtonDown(n2, keyEvent);
        }
        return false;
    }

    public boolean onKeyLongPress(int n2, KeyEvent keyEvent) {
        return false;
    }

    public boolean onKeyMultiple(int n2, int n3, KeyEvent keyEvent) {
        return false;
    }

    public boolean onKeyUp(int n2, KeyEvent keyEvent) {
        if (TransportMediator.isMediaKey(n2)) {
            return this.this$0.mCallbacks.onMediaButtonUp(n2, keyEvent);
        }
        return false;
    }
}

