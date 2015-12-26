/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnWindowAttachListener
 *  java.lang.Object
 */
package android.support.v4.media;

import android.support.v4.media.TransportMediatorJellybeanMR2;
import android.view.ViewTreeObserver;

class TransportMediatorJellybeanMR2$1
implements ViewTreeObserver.OnWindowAttachListener {
    final /* synthetic */ TransportMediatorJellybeanMR2 this$0;

    TransportMediatorJellybeanMR2$1(TransportMediatorJellybeanMR2 transportMediatorJellybeanMR2) {
        this.this$0 = transportMediatorJellybeanMR2;
    }

    public void onWindowAttached() {
        this.this$0.windowAttached();
    }

    public void onWindowDetached() {
        this.this$0.windowDetached();
    }
}

