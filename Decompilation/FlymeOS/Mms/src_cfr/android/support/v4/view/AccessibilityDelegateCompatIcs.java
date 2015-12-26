/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$AccessibilityDelegate
 *  android.view.ViewGroup
 *  android.view.accessibility.AccessibilityEvent
 *  android.view.accessibility.AccessibilityNodeInfo
 *  java.lang.Object
 */
package android.support.v4.view;

import android.support.v4.view.AccessibilityDelegateCompatIcs$1;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

class AccessibilityDelegateCompatIcs {
    AccessibilityDelegateCompatIcs() {
    }

    public static boolean dispatchPopulateAccessibilityEvent(Object object, View view, AccessibilityEvent accessibilityEvent) {
        return ((View.AccessibilityDelegate)object).dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public static Object newAccessibilityDelegateBridge(AccessibilityDelegateBridge accessibilityDelegateBridge) {
        return new AccessibilityDelegateCompatIcs$1(accessibilityDelegateBridge);
    }

    public static Object newAccessibilityDelegateDefaultImpl() {
        return new View.AccessibilityDelegate();
    }

    public static void onInitializeAccessibilityEvent(Object object, View view, AccessibilityEvent accessibilityEvent) {
        ((View.AccessibilityDelegate)object).onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public static void onInitializeAccessibilityNodeInfo(Object object, View view, Object object2) {
        ((View.AccessibilityDelegate)object).onInitializeAccessibilityNodeInfo(view, (AccessibilityNodeInfo)object2);
    }

    public static void onPopulateAccessibilityEvent(Object object, View view, AccessibilityEvent accessibilityEvent) {
        ((View.AccessibilityDelegate)object).onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public static boolean onRequestSendAccessibilityEvent(Object object, ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return ((View.AccessibilityDelegate)object).onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public static void sendAccessibilityEvent(Object object, View view, int n2) {
        ((View.AccessibilityDelegate)object).sendAccessibilityEvent(view, n2);
    }

    public static void sendAccessibilityEventUnchecked(Object object, View view, AccessibilityEvent accessibilityEvent) {
        ((View.AccessibilityDelegate)object).sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }

    public static interface AccessibilityDelegateBridge {
        public boolean dispatchPopulateAccessibilityEvent(View var1, AccessibilityEvent var2);

        public void onInitializeAccessibilityEvent(View var1, AccessibilityEvent var2);

        public void onInitializeAccessibilityNodeInfo(View var1, Object var2);

        public void onPopulateAccessibilityEvent(View var1, AccessibilityEvent var2);

        public boolean onRequestSendAccessibilityEvent(ViewGroup var1, View var2, AccessibilityEvent var3);

        public void sendAccessibilityEvent(View var1, int var2);

        public void sendAccessibilityEventUnchecked(View var1, AccessibilityEvent var2);
    }

}

