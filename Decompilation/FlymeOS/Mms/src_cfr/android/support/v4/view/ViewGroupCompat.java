/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.accessibility.AccessibilityEvent
 *  java.lang.Object
 */
package android.support.v4.view;

import android.os.Build;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewGroupCompatHC;
import android.support.v4.view.ViewGroupCompatIcs;
import android.support.v4.view.ViewGroupCompatJellybeanMR2;
import android.support.v4.view.ViewGroupCompatLollipop;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

public class ViewGroupCompat {
    static final ViewGroupCompatImpl IMPL;
    public static final int LAYOUT_MODE_CLIP_BOUNDS = 0;
    public static final int LAYOUT_MODE_OPTICAL_BOUNDS = 1;

    static {
        int n2 = Build.VERSION.SDK_INT;
        IMPL = n2 >= 21 ? new ViewGroupCompatLollipopImpl() : (n2 >= 18 ? new ViewGroupCompatJellybeanMR2Impl() : (n2 >= 14 ? new ViewGroupCompatIcsImpl() : (n2 >= 11 ? new ViewGroupCompatHCImpl() : new ViewGroupCompatStubImpl())));
    }

    private ViewGroupCompat() {
    }

    public static int getLayoutMode(ViewGroup viewGroup) {
        return IMPL.getLayoutMode(viewGroup);
    }

    public static int getNestedScrollAxes(ViewGroup viewGroup) {
        return IMPL.getNestedScrollAxes(viewGroup);
    }

    public static boolean isTransitionGroup(ViewGroup viewGroup) {
        return IMPL.isTransitionGroup(viewGroup);
    }

    public static boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return IMPL.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public static void setLayoutMode(ViewGroup viewGroup, int n2) {
        IMPL.setLayoutMode(viewGroup, n2);
    }

    public static void setMotionEventSplittingEnabled(ViewGroup viewGroup, boolean bl2) {
        IMPL.setMotionEventSplittingEnabled(viewGroup, bl2);
    }

    public static void setTransitionGroup(ViewGroup viewGroup, boolean bl2) {
        IMPL.setTransitionGroup(viewGroup, bl2);
    }

    static class ViewGroupCompatHCImpl
    extends ViewGroupCompatStubImpl {
        ViewGroupCompatHCImpl() {
        }

        @Override
        public void setMotionEventSplittingEnabled(ViewGroup viewGroup, boolean bl2) {
            ViewGroupCompatHC.setMotionEventSplittingEnabled(viewGroup, bl2);
        }
    }

    static class ViewGroupCompatIcsImpl
    extends ViewGroupCompatHCImpl {
        ViewGroupCompatIcsImpl() {
        }

        @Override
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return ViewGroupCompatIcs.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
        }
    }

    static interface ViewGroupCompatImpl {
        public int getLayoutMode(ViewGroup var1);

        public int getNestedScrollAxes(ViewGroup var1);

        public boolean isTransitionGroup(ViewGroup var1);

        public boolean onRequestSendAccessibilityEvent(ViewGroup var1, View var2, AccessibilityEvent var3);

        public void setLayoutMode(ViewGroup var1, int var2);

        public void setMotionEventSplittingEnabled(ViewGroup var1, boolean var2);

        public void setTransitionGroup(ViewGroup var1, boolean var2);
    }

    static class ViewGroupCompatJellybeanMR2Impl
    extends ViewGroupCompatIcsImpl {
        ViewGroupCompatJellybeanMR2Impl() {
        }

        @Override
        public int getLayoutMode(ViewGroup viewGroup) {
            return ViewGroupCompatJellybeanMR2.getLayoutMode(viewGroup);
        }

        @Override
        public void setLayoutMode(ViewGroup viewGroup, int n2) {
            ViewGroupCompatJellybeanMR2.setLayoutMode(viewGroup, n2);
        }
    }

    static class ViewGroupCompatLollipopImpl
    extends ViewGroupCompatJellybeanMR2Impl {
        ViewGroupCompatLollipopImpl() {
        }

        @Override
        public int getNestedScrollAxes(ViewGroup viewGroup) {
            return ViewGroupCompatLollipop.getNestedScrollAxes(viewGroup);
        }

        @Override
        public boolean isTransitionGroup(ViewGroup viewGroup) {
            return ViewGroupCompatLollipop.isTransitionGroup(viewGroup);
        }

        @Override
        public void setTransitionGroup(ViewGroup viewGroup, boolean bl2) {
            ViewGroupCompatLollipop.setTransitionGroup(viewGroup, bl2);
        }
    }

    static class ViewGroupCompatStubImpl
    implements ViewGroupCompatImpl {
        ViewGroupCompatStubImpl() {
        }

        @Override
        public int getLayoutMode(ViewGroup viewGroup) {
            return 0;
        }

        @Override
        public int getNestedScrollAxes(ViewGroup viewGroup) {
            if (viewGroup instanceof NestedScrollingParent) {
                return ((NestedScrollingParent)viewGroup).getNestedScrollAxes();
            }
            return 0;
        }

        @Override
        public boolean isTransitionGroup(ViewGroup viewGroup) {
            return false;
        }

        @Override
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return true;
        }

        @Override
        public void setLayoutMode(ViewGroup viewGroup, int n2) {
        }

        @Override
        public void setMotionEventSplittingEnabled(ViewGroup viewGroup, boolean bl2) {
        }

        @Override
        public void setTransitionGroup(ViewGroup viewGroup, boolean bl2) {
        }
    }

}

