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
import android.support.v4.media.TransportMediatorCallback;
import android.support.v4.media.TransportPerformer;
import android.view.KeyEvent;

class TransportMediator$1
implements TransportMediatorCallback {
    final /* synthetic */ TransportMediator this$0;

    TransportMediator$1(TransportMediator transportMediator) {
        this.this$0 = transportMediator;
    }

    @Override
    public long getPlaybackPosition() {
        return this.this$0.mCallbacks.onGetCurrentPosition();
    }

    @Override
    public void handleAudioFocusChange(int n2) {
        this.this$0.mCallbacks.onAudioFocusChange(n2);
    }

    @Override
    public void handleKey(KeyEvent keyEvent) {
        keyEvent.dispatch(this.this$0.mKeyEventCallback);
    }

    @Override
    public void playbackPositionUpdate(long l2) {
        this.this$0.mCallbacks.onSeekTo(l2);
    }
}

