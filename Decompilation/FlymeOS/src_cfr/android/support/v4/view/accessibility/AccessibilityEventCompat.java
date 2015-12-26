/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.view.accessibility.AccessibilityEvent
 *  java.lang.Object
 */
package android.support.v4.view.accessibility;

import android.os.Build;
import android.support.v4.view.accessibility.AccessibilityEventCompatIcs;
import android.support.v4.view.accessibility.AccessibilityEventCompatKitKat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.view.accessibility.AccessibilityEvent;

public class AccessibilityEventCompat {
    public static final int CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION = 4;
    public static final int CONTENT_CHANGE_TYPE_SUBTREE = 1;
    public static final int CONTENT_CHANGE_TYPE_TEXT = 2;
    public static final int CONTENT_CHANGE_TYPE_UNDEFINED = 0;
    private static final AccessibilityEventVersionImpl IMPL = Build.VERSION.SDK_INT >= 19 ? new AccessibilityEventKitKatImpl() : (Build.VERSION.SDK_INT >= 14 ? new AccessibilityEventIcsImpl() : new AccessibilityEventStubImpl());
    public static final int TYPES_ALL_MASK = -1;
    public static final int TYPE_ANNOUNCEMENT = 16384;
    public static final int TYPE_GESTURE_DETECTION_END = 524288;
    public static final int TYPE_GESTURE_DETECTION_START = 262144;
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_END = 1024;
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_START = 512;
    public static final int TYPE_TOUCH_INTERACTION_END = 2097152;
    public static final int TYPE_TOUCH_INTERACTION_START = 1048576;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUSED = 32768;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED = 65536;
    public static final int TYPE_VIEW_HOVER_ENTER = 128;
    public static final int TYPE_VIEW_HOVER_EXIT = 256;
    public static final int TYPE_VIEW_SCROLLED = 4096;
    public static final int TYPE_VIEW_TEXT_SELECTION_CHANGED = 8192;
    public static final int TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY = 131072;
    public static final int TYPE_WINDOW_CONTENT_CHANGED = 2048;

    private AccessibilityEventCompat() {
    }

    public static void appendRecord(AccessibilityEvent accessibilityEvent, AccessibilityRecordCompat accessibilityRecordCompat) {
        IMPL.appendRecord(accessibilityEvent, accessibilityRecordCompat.getImpl());
    }

    public static AccessibilityRecordCompat asRecord(AccessibilityEvent accessibilityEvent) {
        return new AccessibilityRecordCompat((Object)accessibilityEvent);
    }

    public static int getContentChangeTypes(AccessibilityEvent accessibilityEvent) {
        return IMPL.getContentChangeTypes(accessibilityEvent);
    }

    public static AccessibilityRecordCompat getRecord(AccessibilityEvent accessibilityEvent, int n2) {
        return new AccessibilityRecordCompat(IMPL.getRecord(accessibilityEvent, n2));
    }

    public static int getRecordCount(AccessibilityEvent accessibilityEvent) {
        return IMPL.getRecordCount(accessibilityEvent);
    }

    public static void setContentChangeTypes(AccessibilityEvent accessibilityEvent, int n2) {
        IMPL.setContentChangeTypes(accessibilityEvent, n2);
    }

    static class AccessibilityEventIcsImpl
    extends AccessibilityEventStubImpl {
        AccessibilityEventIcsImpl() {
        }

        @Override
        public void appendRecord(AccessibilityEvent accessibilityEvent, Object object) {
            AccessibilityEventCompatIcs.appendRecord(accessibilityEvent, object);
        }

        @Override
        public Object getRecord(AccessibilityEvent accessibilityEvent, int n2) {
            return AccessibilityEventCompatIcs.getRecord(accessibilityEvent, n2);
        }

        @Override
        public int getRecordCount(AccessibilityEvent accessibilityEvent) {
            return AccessibilityEventCompatIcs.getRecordCount(accessibilityEvent);
        }
    }

    static class AccessibilityEventKitKatImpl
    extends AccessibilityEventIcsImpl {
        AccessibilityEventKitKatImpl() {
        }

        @Override
        public int getContentChangeTypes(AccessibilityEvent accessibilityEvent) {
            return AccessibilityEventCompatKitKat.getContentChangeTypes(accessibilityEvent);
        }

        @Override
        public void setContentChangeTypes(AccessibilityEvent accessibilityEvent, int n2) {
            AccessibilityEventCompatKitKat.setContentChangeTypes(accessibilityEvent, n2);
        }
    }

    static class AccessibilityEventStubImpl
    implements AccessibilityEventVersionImpl {
        AccessibilityEventStubImpl() {
        }

        @Override
        public void appendRecord(AccessibilityEvent accessibilityEvent, Object object) {
        }

        @Override
        public int getContentChangeTypes(AccessibilityEvent accessibilityEvent) {
            return 0;
        }

        @Override
        public Object getRecord(AccessibilityEvent accessibilityEvent, int n2) {
            return null;
        }

        @Override
        public int getRecordCount(AccessibilityEvent accessibilityEvent) {
            return 0;
        }

        @Override
        public void setContentChangeTypes(AccessibilityEvent accessibilityEvent, int n2) {
        }
    }

    static interface AccessibilityEventVersionImpl {
        public void appendRecord(AccessibilityEvent var1, Object var2);

        public int getContentChangeTypes(AccessibilityEvent var1);

        public Object getRecord(AccessibilityEvent var1, int var2);

        public int getRecordCount(AccessibilityEvent var1);

        public void setContentChangeTypes(AccessibilityEvent var1, int var2);
    }

}

