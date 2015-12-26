/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.accessibility.AccessibilityEvent
 *  java.lang.Object
 */
package android.support.v4.view;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat$AccessibilityDelegateIcsImpl$1;
import android.support.v4.view.AccessibilityDelegateCompat$AccessibilityDelegateJellyBeanImpl$1;
import android.support.v4.view.AccessibilityDelegateCompatIcs;
import android.support.v4.view.AccessibilityDelegateCompatJellyBean;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

public class AccessibilityDelegateCompat {
    private static final Object DEFAULT_DELEGATE;
    private static final AccessibilityDelegateImpl IMPL;
    final Object mBridge;

    /*
     * Enabled aggressive block sorting
     */
    static {
        IMPL = Build.VERSION.SDK_INT >= 16 ? new AccessibilityDelegateJellyBeanImpl() : (Build.VERSION.SDK_INT >= 14 ? new AccessibilityDelegateIcsImpl() : new AccessibilityDelegateStubImpl());
        DEFAULT_DELEGATE = IMPL.newAccessiblityDelegateDefaultImpl();
    }

    public AccessibilityDelegateCompat() {
        this.mBridge = IMPL.newAccessiblityDelegateBridge(this);
    }

    public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        return IMPL.dispatchPopulateAccessibilityEvent(DEFAULT_DELEGATE, view, accessibilityEvent);
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
        return IMPL.getAccessibilityNodeProvider(DEFAULT_DELEGATE, view);
    }

    Object getBridge() {
        return this.mBridge;
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        IMPL.onInitializeAccessibilityEvent(DEFAULT_DELEGATE, view, accessibilityEvent);
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        IMPL.onInitializeAccessibilityNodeInfo(DEFAULT_DELEGATE, view, accessibilityNodeInfoCompat);
    }

    public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        IMPL.onPopulateAccessibilityEvent(DEFAULT_DELEGATE, view, accessibilityEvent);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return IMPL.onRequestSendAccessibilityEvent(DEFAULT_DELEGATE, viewGroup, view, accessibilityEvent);
    }

    public boolean performAccessibilityAction(View view, int n2, Bundle bundle) {
        return IMPL.performAccessibilityAction(DEFAULT_DELEGATE, view, n2, bundle);
    }

    public void sendAccessibilityEvent(View view, int n2) {
        IMPL.sendAccessibilityEvent(DEFAULT_DELEGATE, view, n2);
    }

    public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
        IMPL.sendAccessibilityEventUnchecked(DEFAULT_DELEGATE, view, accessibilityEvent);
    }

    static class AccessibilityDelegateIcsImpl
    extends AccessibilityDelegateStubImpl {
        AccessibilityDelegateIcsImpl() {
        }

        @Override
        public boolean dispatchPopulateAccessibilityEvent(Object object, View view, AccessibilityEvent accessibilityEvent) {
            return AccessibilityDelegateCompatIcs.dispatchPopulateAccessibilityEvent(object, view, accessibilityEvent);
        }

        @Override
        public Object newAccessiblityDelegateBridge(AccessibilityDelegateCompat accessibilityDelegateCompat) {
            return AccessibilityDelegateCompatIcs.newAccessibilityDelegateBridge(new AccessibilityDelegateCompat$AccessibilityDelegateIcsImpl$1(this, accessibilityDelegateCompat));
        }

        @Override
        public Object newAccessiblityDelegateDefaultImpl() {
            return AccessibilityDelegateCompatIcs.newAccessibilityDelegateDefaultImpl();
        }

        @Override
        public void onInitializeAccessibilityEvent(Object object, View view, AccessibilityEvent accessibilityEvent) {
            AccessibilityDelegateCompatIcs.onInitializeAccessibilityEvent(object, view, accessibilityEvent);
        }

        @Override
        public void onInitializeAccessibilityNodeInfo(Object object, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            AccessibilityDelegateCompatIcs.onInitializeAccessibilityNodeInfo(object, view, accessibilityNodeInfoCompat.getInfo());
        }

        @Override
        public void onPopulateAccessibilityEvent(Object object, View view, AccessibilityEvent accessibilityEvent) {
            AccessibilityDelegateCompatIcs.onPopulateAccessibilityEvent(object, view, accessibilityEvent);
        }

        @Override
        public boolean onRequestSendAccessibilityEvent(Object object, ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return AccessibilityDelegateCompatIcs.onRequestSendAccessibilityEvent(object, viewGroup, view, accessibilityEvent);
        }

        @Override
        public void sendAccessibilityEvent(Object object, View view, int n2) {
            AccessibilityDelegateCompatIcs.sendAccessibilityEvent(object, view, n2);
        }

        @Override
        public void sendAccessibilityEventUnchecked(Object object, View view, AccessibilityEvent accessibilityEvent) {
            AccessibilityDelegateCompatIcs.sendAccessibilityEventUnchecked(object, view, accessibilityEvent);
        }
    }

    static interface AccessibilityDelegateImpl {
        public boolean dispatchPopulateAccessibilityEvent(Object var1, View var2, AccessibilityEvent var3);

        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(Object var1, View var2);

        public Object newAccessiblityDelegateBridge(AccessibilityDelegateCompat var1);

        public Object newAccessiblityDelegateDefaultImpl();

        public void onInitializeAccessibilityEvent(Object var1, View var2, AccessibilityEvent var3);

        public void onInitializeAccessibilityNodeInfo(Object var1, View var2, AccessibilityNodeInfoCompat var3);

        public void onPopulateAccessibilityEvent(Object var1, View var2, AccessibilityEvent var3);

        public boolean onRequestSendAccessibilityEvent(Object var1, ViewGroup var2, View var3, AccessibilityEvent var4);

        public boolean performAccessibilityAction(Object var1, View var2, int var3, Bundle var4);

        public void sendAccessibilityEvent(Object var1, View var2, int var3);

        public void sendAccessibilityEventUnchecked(Object var1, View var2, AccessibilityEvent var3);
    }

    static class AccessibilityDelegateJellyBeanImpl
    extends AccessibilityDelegateIcsImpl {
        AccessibilityDelegateJellyBeanImpl() {
        }

        @Override
        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(Object object, View view) {
            if ((object = AccessibilityDelegateCompatJellyBean.getAccessibilityNodeProvider(object, view)) != null) {
                return new AccessibilityNodeProviderCompat(object);
            }
            return null;
        }

        @Override
        public Object newAccessiblityDelegateBridge(AccessibilityDelegateCompat accessibilityDelegateCompat) {
            return AccessibilityDelegateCompatJellyBean.newAccessibilityDelegateBridge(new AccessibilityDelegateCompat$AccessibilityDelegateJellyBeanImpl$1(this, accessibilityDelegateCompat));
        }

        @Override
        public boolean performAccessibilityAction(Object object, View view, int n2, Bundle bundle) {
            return AccessibilityDelegateCompatJellyBean.performAccessibilityAction(object, view, n2, bundle);
        }
    }

    static class AccessibilityDelegateStubImpl
    implements AccessibilityDelegateImpl {
        AccessibilityDelegateStubImpl() {
        }

        @Override
        public boolean dispatchPopulateAccessibilityEvent(Object object, View view, AccessibilityEvent accessibilityEvent) {
            return false;
        }

        @Override
        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(Object object, View view) {
            return null;
        }

        @Override
        public Object newAccessiblityDelegateBridge(AccessibilityDelegateCompat accessibilityDelegateCompat) {
            return null;
        }

        @Override
        public Object newAccessiblityDelegateDefaultImpl() {
            return null;
        }

        @Override
        public void onInitializeAccessibilityEvent(Object object, View view, AccessibilityEvent accessibilityEvent) {
        }

        @Override
        public void onInitializeAccessibilityNodeInfo(Object object, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        }

        @Override
        public void onPopulateAccessibilityEvent(Object object, View view, AccessibilityEvent accessibilityEvent) {
        }

        @Override
        public boolean onRequestSendAccessibilityEvent(Object object, ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return true;
        }

        @Override
        public boolean performAccessibilityAction(Object object, View view, int n2, Bundle bundle) {
            return false;
        }

        @Override
        public void sendAccessibilityEvent(Object object, View view, int n2) {
        }

        @Override
        public void sendAccessibilityEventUnchecked(Object object, View view, AccessibilityEvent accessibilityEvent) {
        }
    }

}

