/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.view.View
 *  android.view.View$AccessibilityDelegate
 *  android.view.ViewGroup
 *  android.view.accessibility.AccessibilityEvent
 *  android.view.accessibility.AccessibilityNodeInfo
 *  android.view.accessibility.AccessibilityNodeProvider
 *  java.lang.Object
 */
package android.support.v4.view;

import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompatJellyBean;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;

final class AccessibilityDelegateCompatJellyBean$1
extends View.AccessibilityDelegate {
    final /* synthetic */ AccessibilityDelegateCompatJellyBean.AccessibilityDelegateBridgeJellyBean val$bridge;

    AccessibilityDelegateCompatJellyBean$1(AccessibilityDelegateCompatJellyBean.AccessibilityDelegateBridgeJellyBean accessibilityDelegateBridgeJellyBean) {
        this.val$bridge = accessibilityDelegateBridgeJellyBean;
    }

    public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        return this.val$bridge.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
        return (AccessibilityNodeProvider)this.val$bridge.getAccessibilityNodeProvider(view);
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.val$bridge.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        this.val$bridge.onInitializeAccessibilityNodeInfo(view, (Object)accessibilityNodeInfo);
    }

    public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.val$bridge.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.val$bridge.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public boolean performAccessibilityAction(View view, int n2, Bundle bundle) {
        return this.val$bridge.performAccessibilityAction(view, n2, bundle);
    }

    public void sendAccessibilityEvent(View view, int n2) {
        this.val$bridge.sendAccessibilityEvent(view, n2);
    }

    public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
        this.val$bridge.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }
}

