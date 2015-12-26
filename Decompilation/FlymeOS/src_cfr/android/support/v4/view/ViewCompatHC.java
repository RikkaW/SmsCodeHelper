/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.animation.ValueAnimator
 *  android.graphics.Paint
 *  android.view.View
 *  java.lang.Object
 */
package android.support.v4.view;

import android.animation.ValueAnimator;
import android.graphics.Paint;
import android.view.View;

class ViewCompatHC {
    ViewCompatHC() {
    }

    public static int combineMeasuredStates(int n2, int n3) {
        return View.combineMeasuredStates((int)n2, (int)n3);
    }

    public static float getAlpha(View view) {
        return view.getAlpha();
    }

    static long getFrameTime() {
        return ValueAnimator.getFrameDelay();
    }

    public static int getLayerType(View view) {
        return view.getLayerType();
    }

    public static int getMeasuredHeightAndState(View view) {
        return view.getMeasuredHeightAndState();
    }

    public static int getMeasuredState(View view) {
        return view.getMeasuredState();
    }

    public static int getMeasuredWidthAndState(View view) {
        return view.getMeasuredWidthAndState();
    }

    public static float getPivotX(View view) {
        return view.getPivotX();
    }

    public static float getPivotY(View view) {
        return view.getPivotY();
    }

    public static float getRotation(View view) {
        return view.getRotation();
    }

    public static float getRotationX(View view) {
        return view.getRotationX();
    }

    public static float getRotationY(View view) {
        return view.getRotationY();
    }

    public static float getScaleX(View view) {
        return view.getScaleX();
    }

    public static float getScaleY(View view) {
        return view.getScaleY();
    }

    public static float getTranslationX(View view) {
        return view.getTranslationX();
    }

    public static float getTranslationY(View view) {
        return view.getTranslationY();
    }

    public static float getX(View view) {
        return view.getX();
    }

    public static float getY(View view) {
        return view.getY();
    }

    public static void jumpDrawablesToCurrentState(View view) {
        view.jumpDrawablesToCurrentState();
    }

    public static int resolveSizeAndState(int n2, int n3, int n4) {
        return View.resolveSizeAndState((int)n2, (int)n3, (int)n4);
    }

    public static void setActivated(View view, boolean bl2) {
        view.setActivated(bl2);
    }

    public static void setAlpha(View view, float f2) {
        view.setAlpha(f2);
    }

    public static void setLayerType(View view, int n2, Paint paint) {
        view.setLayerType(n2, paint);
    }

    public static void setPivotX(View view, float f2) {
        view.setPivotX(f2);
    }

    public static void setPivotY(View view, float f2) {
        view.setPivotY(f2);
    }

    public static void setRotation(View view, float f2) {
        view.setRotation(f2);
    }

    public static void setRotationX(View view, float f2) {
        view.setRotationX(f2);
    }

    public static void setRotationY(View view, float f2) {
        view.setRotationY(f2);
    }

    public static void setSaveFromParentEnabled(View view, boolean bl2) {
        view.setSaveFromParentEnabled(bl2);
    }

    public static void setScaleX(View view, float f2) {
        view.setScaleX(f2);
    }

    public static void setScaleY(View view, float f2) {
        view.setScaleY(f2);
    }

    public static void setTranslationX(View view, float f2) {
        view.setTranslationX(f2);
    }

    public static void setTranslationY(View view, float f2) {
        view.setTranslationY(f2);
    }

    public static void setX(View view, float f2) {
        view.setX(f2);
    }

    public static void setY(View view, float f2) {
        view.setY(f2);
    }
}

