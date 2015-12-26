/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.ViewParent
 *  java.lang.Object
 */
package android.support.v4.view;

import android.view.View;
import android.view.ViewParent;

class ViewParentCompatKitKat {
    ViewParentCompatKitKat() {
    }

    public static void notifySubtreeAccessibilityStateChanged(ViewParent viewParent, View view, View view2, int n2) {
        viewParent.notifySubtreeAccessibilityStateChanged(view, view2, n2);
    }
}

