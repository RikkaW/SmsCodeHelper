/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.MotionEvent
 *  java.lang.Object
 */
package android.support.v4.view;

import android.view.MotionEvent;

class MotionEventCompatEclair {
    MotionEventCompatEclair() {
    }

    public static int findPointerIndex(MotionEvent motionEvent, int n2) {
        return motionEvent.findPointerIndex(n2);
    }

    public static int getPointerCount(MotionEvent motionEvent) {
        return motionEvent.getPointerCount();
    }

    public static int getPointerId(MotionEvent motionEvent, int n2) {
        return motionEvent.getPointerId(n2);
    }

    public static float getX(MotionEvent motionEvent, int n2) {
        return motionEvent.getX(n2);
    }

    public static float getY(MotionEvent motionEvent, int n2) {
        return motionEvent.getY(n2);
    }
}

