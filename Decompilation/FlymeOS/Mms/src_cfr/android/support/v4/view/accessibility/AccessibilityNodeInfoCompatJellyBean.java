/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.view.View
 *  android.view.accessibility.AccessibilityNodeInfo
 *  java.lang.Object
 */
package android.support.v4.view.accessibility;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

class AccessibilityNodeInfoCompatJellyBean {
    AccessibilityNodeInfoCompatJellyBean() {
    }

    public static void addChild(Object object, View view, int n2) {
        ((AccessibilityNodeInfo)object).addChild(view, n2);
    }

    public static Object findFocus(Object object, int n2) {
        return ((AccessibilityNodeInfo)object).findFocus(n2);
    }

    public static Object focusSearch(Object object, int n2) {
        return ((AccessibilityNodeInfo)object).focusSearch(n2);
    }

    public static int getMovementGranularities(Object object) {
        return ((AccessibilityNodeInfo)object).getMovementGranularities();
    }

    public static boolean isAccessibilityFocused(Object object) {
        return ((AccessibilityNodeInfo)object).isAccessibilityFocused();
    }

    public static boolean isVisibleToUser(Object object) {
        return ((AccessibilityNodeInfo)object).isVisibleToUser();
    }

    public static Object obtain(View view, int n2) {
        return AccessibilityNodeInfo.obtain((View)view, (int)n2);
    }

    public static boolean performAction(Object object, int n2, Bundle bundle) {
        return ((AccessibilityNodeInfo)object).performAction(n2, bundle);
    }

    public static void setAccesibilityFocused(Object object, boolean bl2) {
        ((AccessibilityNodeInfo)object).setAccessibilityFocused(bl2);
    }

    public static void setMovementGranularities(Object object, int n2) {
        ((AccessibilityNodeInfo)object).setMovementGranularities(n2);
    }

    public static void setParent(Object object, View view, int n2) {
        ((AccessibilityNodeInfo)object).setParent(view, n2);
    }

    public static void setSource(Object object, View view, int n2) {
        ((AccessibilityNodeInfo)object).setSource(view, n2);
    }

    public static void setVisibleToUser(Object object, boolean bl2) {
        ((AccessibilityNodeInfo)object).setVisibleToUser(bl2);
    }
}

