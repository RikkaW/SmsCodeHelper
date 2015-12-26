/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.KeyEvent
 *  android.view.KeyEvent$Callback
 *  android.view.KeyEvent$DispatcherState
 *  android.view.View
 *  java.lang.Object
 */
package android.support.v4.view;

import android.view.KeyEvent;
import android.view.View;

class KeyEventCompatEclair {
    KeyEventCompatEclair() {
    }

    public static boolean dispatch(KeyEvent keyEvent, KeyEvent.Callback callback, Object object, Object object2) {
        return keyEvent.dispatch(callback, (KeyEvent.DispatcherState)object, object2);
    }

    public static Object getKeyDispatcherState(View view) {
        return view.getKeyDispatcherState();
    }

    public static boolean isTracking(KeyEvent keyEvent) {
        return keyEvent.isTracking();
    }

    public static void startTracking(KeyEvent keyEvent) {
        keyEvent.startTracking();
    }
}

