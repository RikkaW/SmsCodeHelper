/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.MotionEvent
 *  java.lang.Object
 */
package android.support.v4.view;

import android.view.MotionEvent;

class MotionEventCompatHoneycombMr1 {
    MotionEventCompatHoneycombMr1() {
    }

    static float getAxisValue(MotionEvent motionEvent, int n2) {
        return motionEvent.getAxisValue(n2);
    }

    static float getAxisValue(MotionEvent motionEvent, int n2, int n3) {
        return motionEvent.getAxisValue(n2, n3);
    }
}

