/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Canvas
 *  android.widget.EdgeEffect
 *  java.lang.Object
 */
package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.EdgeEffect;

class EdgeEffectCompatIcs {
    EdgeEffectCompatIcs() {
    }

    public static boolean draw(Object object, Canvas canvas) {
        return ((EdgeEffect)object).draw(canvas);
    }

    public static void finish(Object object) {
        ((EdgeEffect)object).finish();
    }

    public static boolean isFinished(Object object) {
        return ((EdgeEffect)object).isFinished();
    }

    public static Object newEdgeEffect(Context context) {
        return new EdgeEffect(context);
    }

    public static boolean onAbsorb(Object object, int n2) {
        ((EdgeEffect)object).onAbsorb(n2);
        return true;
    }

    public static boolean onPull(Object object, float f2) {
        ((EdgeEffect)object).onPull(f2);
        return true;
    }

    public static boolean onRelease(Object object) {
        object = (EdgeEffect)object;
        object.onRelease();
        return object.isFinished();
    }

    public static void setSize(Object object, int n2, int n3) {
        ((EdgeEffect)object).setSize(n2, n3);
    }
}

