/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.media.AudioManager
 *  android.media.AudioManager$OnAudioFocusChangeListener
 *  java.lang.Object
 */
package android.support.v4.media;

import android.media.AudioManager;
import android.support.v4.media.TransportMediatorCallback;
import android.support.v4.media.TransportMediatorJellybeanMR2;

class TransportMediatorJellybeanMR2$4
implements AudioManager.OnAudioFocusChangeListener {
    final /* synthetic */ TransportMediatorJellybeanMR2 this$0;

    TransportMediatorJellybeanMR2$4(TransportMediatorJellybeanMR2 transportMediatorJellybeanMR2) {
        this.this$0 = transportMediatorJellybeanMR2;
    }

    public void onAudioFocusChange(int n2) {
        this.this$0.mTransportCallback.handleAudioFocusChange(n2);
    }
}

