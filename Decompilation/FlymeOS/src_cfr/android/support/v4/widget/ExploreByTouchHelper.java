/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Rect
 *  android.os.Bundle
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.ViewParent
 *  android.view.accessibility.AccessibilityEvent
 *  android.view.accessibility.AccessibilityManager
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.LinkedList
 */
package android.support.v4.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewParentCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class ExploreByTouchHelper
extends AccessibilityDelegateCompat {
    private static final String DEFAULT_CLASS_NAME = View.class.getName();
    public static final int INVALID_ID = Integer.MIN_VALUE;
    private int mFocusedVirtualViewId = Integer.MIN_VALUE;
    private int mHoveredVirtualViewId = Integer.MIN_VALUE;
    private final AccessibilityManager mManager;
    private ExploreByTouchNodeProvider mNodeProvider;
    private final int[] mTempGlobalRect = new int[2];
    private final Rect mTempParentRect = new Rect();
    private final Rect mTempScreenRect = new Rect();
    private final Rect mTempVisibleRect = new Rect();
    private final View mView;

    public ExploreByTouchHelper(View view) {
        if (view == null) {
            throw new IllegalArgumentException("View may not be null");
        }
        this.mView = view;
        this.mManager = (AccessibilityManager)view.getContext().getSystemService("accessibility");
    }

    private boolean clearAccessibilityFocus(int n2) {
        if (this.isAccessibilityFocused(n2)) {
            this.mFocusedVirtualViewId = Integer.MIN_VALUE;
            this.mView.invalidate();
            this.sendEventForVirtualView(n2, 65536);
            return true;
        }
        return false;
    }

    private AccessibilityEvent createEvent(int n2, int n3) {
        switch (n2) {
            default: {
                return this.createEventForChild(n2, n3);
            }
            case -1: 
        }
        return this.createEventForHost(n3);
    }

    private AccessibilityEvent createEventForChild(int n2, int n3) {
        AccessibilityEvent accessibilityEvent = AccessibilityEvent.obtain((int)n3);
        accessibilityEvent.setEnabled(true);
        accessibilityEvent.setClassName((CharSequence)DEFAULT_CLASS_NAME);
        this.onPopulateEventForVirtualView(n2, accessibilityEvent);
        if (accessibilityEvent.getText().isEmpty() && accessibilityEvent.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        accessibilityEvent.setPackageName((CharSequence)this.mView.getContext().getPackageName());
        AccessibilityEventCompat.asRecord(accessibilityEvent).setSource(this.mView, n2);
        return accessibilityEvent;
    }

    private AccessibilityEvent createEventForHost(int n2) {
        AccessibilityEvent accessibilityEvent = AccessibilityEvent.obtain((int)n2);
        ViewCompat.onInitializeAccessibilityEvent(this.mView, accessibilityEvent);
        return accessibilityEvent;
    }

    private AccessibilityNodeInfoCompat createNode(int n2) {
        switch (n2) {
            default: {
                return this.createNodeForChild(n2);
            }
            case -1: 
        }
        return this.createNodeForHost();
    }

    /*
     * Enabled aggressive block sorting
     */
    private AccessibilityNodeInfoCompat createNodeForChild(int n2) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = AccessibilityNodeInfoCompat.obtain();
        accessibilityNodeInfoCompat.setEnabled(true);
        accessibilityNodeInfoCompat.setClassName(DEFAULT_CLASS_NAME);
        this.onPopulateNodeForVirtualView(n2, accessibilityNodeInfoCompat);
        if (accessibilityNodeInfoCompat.getText() == null && accessibilityNodeInfoCompat.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        accessibilityNodeInfoCompat.getBoundsInParent(this.mTempParentRect);
        if (this.mTempParentRect.isEmpty()) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        int n3 = accessibilityNodeInfoCompat.getActions();
        if ((n3 & 64) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        if ((n3 & 128) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        accessibilityNodeInfoCompat.setPackageName(this.mView.getContext().getPackageName());
        accessibilityNodeInfoCompat.setSource(this.mView, n2);
        accessibilityNodeInfoCompat.setParent(this.mView);
        if (this.mFocusedVirtualViewId == n2) {
            accessibilityNodeInfoCompat.setAccessibilityFocused(true);
            accessibilityNodeInfoCompat.addAction(128);
        } else {
            accessibilityNodeInfoCompat.setAccessibilityFocused(false);
            accessibilityNodeInfoCompat.addAction(64);
        }
        if (this.intersectVisibleToUser(this.mTempParentRect)) {
            accessibilityNodeInfoCompat.setVisibleToUser(true);
            accessibilityNodeInfoCompat.setBoundsInParent(this.mTempParentRect);
        }
        this.mView.getLocationOnScreen(this.mTempGlobalRect);
        n2 = this.mTempGlobalRect[0];
        n3 = this.mTempGlobalRect[1];
        this.mTempScreenRect.set(this.mTempParentRect);
        this.mTempScreenRect.offset(n2, n3);
        accessibilityNodeInfoCompat.setBoundsInScreen(this.mTempScreenRect);
        return accessibilityNodeInfoCompat;
    }

    private AccessibilityNodeInfoCompat createNodeForHost() {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = AccessibilityNodeInfoCompat.obtain(this.mView);
        ViewCompat.onInitializeAccessibilityNodeInfo(this.mView, accessibilityNodeInfoCompat);
        Object object = new LinkedList();
        this.getVisibleVirtualViews((List<Integer>)object);
        object = object.iterator();
        while (object.hasNext()) {
            Integer n2 = (Integer)object.next();
            accessibilityNodeInfoCompat.addChild(this.mView, n2);
        }
        return accessibilityNodeInfoCompat;
    }

    private boolean intersectVisibleToUser(Rect rect) {
        if (rect == null || rect.isEmpty()) {
            return false;
        }
        if (this.mView.getWindowVisibility() != 0) {
            return false;
        }
        ViewParent viewParent = this.mView.getParent();
        while (viewParent instanceof View) {
            if (ViewCompat.getAlpha((View)(viewParent = (View)viewParent)) <= 0.0f || viewParent.getVisibility() != 0) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        if (viewParent == null) {
            return false;
        }
        if (!this.mView.getLocalVisibleRect(this.mTempVisibleRect)) {
            return false;
        }
        return rect.intersect(this.mTempVisibleRect);
    }

    private boolean isAccessibilityFocused(int n2) {
        if (this.mFocusedVirtualViewId == n2) {
            return true;
        }
        return false;
    }

    private boolean manageFocusForChild(int n2, int n3, Bundle bundle) {
        switch (n3) {
            default: {
                return false;
            }
            case 64: {
                return this.requestAccessibilityFocus(n2);
            }
            case 128: 
        }
        return this.clearAccessibilityFocus(n2);
    }

    private boolean performAction(int n2, int n3, Bundle bundle) {
        switch (n2) {
            default: {
                return this.performActionForChild(n2, n3, bundle);
            }
            case -1: 
        }
        return this.performActionForHost(n3, bundle);
    }

    private boolean performActionForChild(int n2, int n3, Bundle bundle) {
        switch (n3) {
            default: {
                return this.onPerformActionForVirtualView(n2, n3, bundle);
            }
            case 64: 
            case 128: 
        }
        return this.manageFocusForChild(n2, n3, bundle);
    }

    private boolean performActionForHost(int n2, Bundle bundle) {
        return ViewCompat.performAccessibilityAction(this.mView, n2, bundle);
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean requestAccessibilityFocus(int n2) {
        if (!this.mManager.isEnabled() || !AccessibilityManagerCompat.isTouchExplorationEnabled(this.mManager) || this.isAccessibilityFocused(n2)) {
            return false;
        }
        this.mFocusedVirtualViewId = n2;
        this.mView.invalidate();
        this.sendEventForVirtualView(n2, 32768);
        return true;
    }

    private void updateHoveredVirtualView(int n2) {
        if (this.mHoveredVirtualViewId == n2) {
            return;
        }
        int n3 = this.mHoveredVirtualViewId;
        this.mHoveredVirtualViewId = n2;
        this.sendEventForVirtualView(n2, 128);
        this.sendEventForVirtualView(n3, 256);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        boolean bl2 = true;
        if (!this.mManager.isEnabled()) return false;
        if (!AccessibilityManagerCompat.isTouchExplorationEnabled(this.mManager)) {
            return false;
        }
        switch (motionEvent.getAction()) {
            default: {
                return false;
            }
            case 7: 
            case 9: {
                int n2 = this.getVirtualViewAt(motionEvent.getX(), motionEvent.getY());
                this.updateHoveredVirtualView(n2);
                if (n2 == Integer.MIN_VALUE) return false;
                return bl2;
            }
            case 10: 
        }
        if (this.mFocusedVirtualViewId == Integer.MIN_VALUE) return false;
        this.updateHoveredVirtualView(Integer.MIN_VALUE);
        return true;
    }

    @Override
    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
        if (this.mNodeProvider == null) {
            this.mNodeProvider = new ExploreByTouchNodeProvider();
        }
        return this.mNodeProvider;
    }

    public int getFocusedVirtualView() {
        return this.mFocusedVirtualViewId;
    }

    protected abstract int getVirtualViewAt(float var1, float var2);

    protected abstract void getVisibleVirtualViews(List<Integer> var1);

    public void invalidateRoot() {
        this.invalidateVirtualView(-1);
    }

    public void invalidateVirtualView(int n2) {
        this.sendEventForVirtualView(n2, 2048);
    }

    protected abstract boolean onPerformActionForVirtualView(int var1, int var2, Bundle var3);

    protected abstract void onPopulateEventForVirtualView(int var1, AccessibilityEvent var2);

    protected abstract void onPopulateNodeForVirtualView(int var1, AccessibilityNodeInfoCompat var2);

    /*
     * Enabled aggressive block sorting
     */
    public boolean sendEventForVirtualView(int n2, int n3) {
        ViewParent viewParent;
        if (n2 == Integer.MIN_VALUE || !this.mManager.isEnabled() || (viewParent = this.mView.getParent()) == null) {
            return false;
        }
        AccessibilityEvent accessibilityEvent = this.createEvent(n2, n3);
        return ViewParentCompat.requestSendAccessibilityEvent(viewParent, this.mView, accessibilityEvent);
    }

    class ExploreByTouchNodeProvider
    extends AccessibilityNodeProviderCompat {
        private ExploreByTouchNodeProvider() {
        }

        @Override
        public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int n2) {
            return ExploreByTouchHelper.this.createNode(n2);
        }

        @Override
        public boolean performAction(int n2, int n3, Bundle bundle) {
            return ExploreByTouchHelper.this.performAction(n2, n3, bundle);
        }
    }

}

