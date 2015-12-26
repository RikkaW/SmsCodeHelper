/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.accessibility.AccessibilityEvent
 *  java.lang.Object
 */
package android.support.v4.view;

import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.AccessibilityDelegateCompatJellyBean;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

class AccessibilityDelegateCompat$AccessibilityDelegateJellyBeanImpl$1
implements AccessibilityDelegateCompatJellyBean.AccessibilityDelegateBridgeJellyBean {
    final /* synthetic */ AccessibilityDelegateCompat.AccessibilityDelegateJellyBeanImpl this$0;
    final /* synthetic */ AccessibilityDelegateCompat val$compat;

    AccessibilityDelegateCompat$AccessibilityDelegateJellyBeanImpl$1(AccessibilityDelegateCompat.AccessibilityDelegateJellyBeanImpl accessibilityDelegateJellyBeanImpl, AccessibilityDelegateCompat accessibilityDelegateCompat) {
        this.this$0 = accessibilityDelegateJellyBeanImpl;
        this.val$compat = accessibilityDelegateCompat;
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        return this.val$compat.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    @Override
    public Object getAccessibilityNodeProvider(View object) {
        if ((object = this.val$compat.getAccessibilityNodeProvider((View)object)) != null) {
            return object.getProvider();
        }
        return null;
    }

    @Override
    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.val$compat.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    @Override
    public void onInitializeAccessibilityNodeInfo(View view, Object object) {
        this.val$compat.onInitializeAccessibilityNodeInfo(view, new AccessibilityNodeInfoCompat(object));
    }

    @Override
    public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.val$compat.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    @Override
    public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.val$compat.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    @Override
    public boolean performAccessibilityAction(View view, int n2, Bundle bundle) {
        return this.val$compat.performAccessibilityAction(view, n2, bundle);
    }

    @Override
    public void sendAccessibilityEvent(View view, int n2) {
        this.val$compat.sendAccessibilityEvent(view, n2);
    }

    @Override
    public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
        this.val$compat.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }
}

