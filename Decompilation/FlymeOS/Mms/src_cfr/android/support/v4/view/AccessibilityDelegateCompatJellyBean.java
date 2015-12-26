/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.view.View
 *  android.view.View$AccessibilityDelegate
 *  android.view.ViewGroup
 *  android.view.accessibility.AccessibilityEvent
 *  android.view.accessibility.AccessibilityNodeProvider
 *  java.lang.Object
 */
package android.support.v4.view;

import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompatJellyBean$1;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeProvider;

class AccessibilityDelegateCompatJellyBean {
    AccessibilityDelegateCompatJellyBean() {
    }

    public static Object getAccessibilityNodeProvider(Object object, View view) {
        return ((View.AccessibilityDelegate)object).getAccessibilityNodeProvider(view);
    }

    public static Object newAccessibilityDelegateBridge(AccessibilityDelegateBridgeJellyBean accessibilityDelegateBridgeJellyBean) {
        return new AccessibilityDelegateCompatJellyBean$1(accessibilityDelegateBridgeJellyBean);
    }

    public static boolean performAccessibilityAction(Object object, View view, int n2, Bundle bundle) {
        return ((View.AccessibilityDelegate)object).performAccessibilityAction(view, n2, bundle);
    }

    public static interface AccessibilityDelegateBridgeJellyBean {
        public boolean dispatchPopulateAccessibilityEvent(View var1, AccessibilityEvent var2);

        public Object getAccessibilityNodeProvider(View var1);

        public void onInitializeAccessibilityEvent(View var1, AccessibilityEvent var2);

        public void onInitializeAccessibilityNodeInfo(View var1, Object var2);

        public void onPopulateAccessibilityEvent(View var1, AccessibilityEvent var2);

        public boolean onRequestSendAccessibilityEvent(ViewGroup var1, View var2, AccessibilityEvent var3);

        public boolean performAccessibilityAction(View var1, int var2, Bundle var3);

        public void sendAccessibilityEvent(View var1, int var2);

        public void sendAccessibilityEventUnchecked(View var1, AccessibilityEvent var2);
    }

}

