/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.ViewGroup
 *  android.view.ViewGroup$MarginLayoutParams
 *  java.lang.Object
 */
package android.support.v4.view;

import android.view.ViewGroup;

class MarginLayoutParamsCompatJellybeanMr1 {
    MarginLayoutParamsCompatJellybeanMr1() {
    }

    public static int getLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.getLayoutDirection();
    }

    public static int getMarginEnd(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.getMarginEnd();
    }

    public static int getMarginStart(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.getMarginStart();
    }

    public static boolean isMarginRelative(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.isMarginRelative();
    }

    public static void resolveLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams, int n2) {
        marginLayoutParams.resolveLayoutDirection(n2);
    }

    public static void setLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams, int n2) {
        marginLayoutParams.setLayoutDirection(n2);
    }

    public static void setMarginEnd(ViewGroup.MarginLayoutParams marginLayoutParams, int n2) {
        marginLayoutParams.setMarginEnd(n2);
    }

    public static void setMarginStart(ViewGroup.MarginLayoutParams marginLayoutParams, int n2) {
        marginLayoutParams.setMarginStart(n2);
    }
}

