/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnWindowFocusChangeListener
 *  java.lang.Object
 */
package android.support.v4.media;

import android.support.v4.media.TransportMediatorJellybeanMR2;
import android.view.ViewTreeObserver;

class TransportMediatorJellybeanMR2$2
implements ViewTreeObserver.OnWindowFocusChangeListener {
    final /* synthetic */ TransportMediatorJellybeanMR2 this$0;

    TransportMediatorJellybeanMR2$2(TransportMediatorJellybeanMR2 transportMediatorJellybeanMR2) {
        this.this$0 = transportMediatorJellybeanMR2;
    }

    public void onWindowFocusChanged(boolean bl2) {
        if (bl2) {
            this.this$0.gainFocus();
            return;
        }
        this.this$0.loseFocus();
    }
}

