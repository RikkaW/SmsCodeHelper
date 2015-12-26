/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  android.view.View
 *  android.view.ViewParent
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package android.support.v4.view;

import android.util.Log;
import android.view.View;
import android.view.ViewParent;

class ViewParentCompatLollipop {
    private static final String TAG = "ViewParentCompat";

    ViewParentCompatLollipop() {
    }

    public static boolean onNestedFling(ViewParent viewParent, View view, float f2, float f3, boolean bl2) {
        try {
            bl2 = viewParent.onNestedFling(view, f2, f3, bl2);
            return bl2;
        }
        catch (AbstractMethodError var1_2) {
            Log.e((String)"ViewParentCompat", (String)("ViewParent " + (Object)viewParent + " does not implement interface " + "method onNestedFling"), (Throwable)var1_2);
            return false;
        }
    }

    public static boolean onNestedPreFling(ViewParent viewParent, View view, float f2, float f3) {
        try {
            boolean bl2 = viewParent.onNestedPreFling(view, f2, f3);
            return bl2;
        }
        catch (AbstractMethodError var1_2) {
            Log.e((String)"ViewParentCompat", (String)("ViewParent " + (Object)viewParent + " does not implement interface " + "method onNestedPreFling"), (Throwable)var1_2);
            return false;
        }
    }

    public static void onNestedPreScroll(ViewParent viewParent, View view, int n2, int n3, int[] arrn) {
        try {
            viewParent.onNestedPreScroll(view, n2, n3, arrn);
            return;
        }
        catch (AbstractMethodError var1_2) {
            Log.e((String)"ViewParentCompat", (String)("ViewParent " + (Object)viewParent + " does not implement interface " + "method onNestedPreScroll"), (Throwable)var1_2);
            return;
        }
    }

    public static void onNestedScroll(ViewParent viewParent, View view, int n2, int n3, int n4, int n5) {
        try {
            viewParent.onNestedScroll(view, n2, n3, n4, n5);
            return;
        }
        catch (AbstractMethodError var1_2) {
            Log.e((String)"ViewParentCompat", (String)("ViewParent " + (Object)viewParent + " does not implement interface " + "method onNestedScroll"), (Throwable)var1_2);
            return;
        }
    }

    public static void onNestedScrollAccepted(ViewParent viewParent, View view, View view2, int n2) {
        try {
            viewParent.onNestedScrollAccepted(view, view2, n2);
            return;
        }
        catch (AbstractMethodError var1_2) {
            Log.e((String)"ViewParentCompat", (String)("ViewParent " + (Object)viewParent + " does not implement interface " + "method onNestedScrollAccepted"), (Throwable)var1_2);
            return;
        }
    }

    public static boolean onStartNestedScroll(ViewParent viewParent, View view, View view2, int n2) {
        try {
            boolean bl2 = viewParent.onStartNestedScroll(view, view2, n2);
            return bl2;
        }
        catch (AbstractMethodError var1_2) {
            Log.e((String)"ViewParentCompat", (String)("ViewParent " + (Object)viewParent + " does not implement interface " + "method onStartNestedScroll"), (Throwable)var1_2);
            return false;
        }
    }

    public static void onStopNestedScroll(ViewParent viewParent, View view) {
        try {
            viewParent.onStopNestedScroll(view);
            return;
        }
        catch (AbstractMethodError var1_2) {
            Log.e((String)"ViewParentCompat", (String)("ViewParent " + (Object)viewParent + " does not implement interface " + "method onStopNestedScroll"), (Throwable)var1_2);
            return;
        }
    }
}

