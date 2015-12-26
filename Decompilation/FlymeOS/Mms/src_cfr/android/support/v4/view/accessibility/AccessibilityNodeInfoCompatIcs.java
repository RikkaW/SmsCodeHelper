/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Rect
 *  android.view.View
 *  android.view.accessibility.AccessibilityNodeInfo
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.view.accessibility;

import android.graphics.Rect;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.List;

class AccessibilityNodeInfoCompatIcs {
    AccessibilityNodeInfoCompatIcs() {
    }

    public static void addAction(Object object, int n2) {
        ((AccessibilityNodeInfo)object).addAction(n2);
    }

    public static void addChild(Object object, View view) {
        ((AccessibilityNodeInfo)object).addChild(view);
    }

    public static List<Object> findAccessibilityNodeInfosByText(Object object, String string2) {
        return ((AccessibilityNodeInfo)object).findAccessibilityNodeInfosByText(string2);
    }

    public static int getActions(Object object) {
        return ((AccessibilityNodeInfo)object).getActions();
    }

    public static void getBoundsInParent(Object object, Rect rect) {
        ((AccessibilityNodeInfo)object).getBoundsInParent(rect);
    }

    public static void getBoundsInScreen(Object object, Rect rect) {
        ((AccessibilityNodeInfo)object).getBoundsInScreen(rect);
    }

    public static Object getChild(Object object, int n2) {
        return ((AccessibilityNodeInfo)object).getChild(n2);
    }

    public static int getChildCount(Object object) {
        return ((AccessibilityNodeInfo)object).getChildCount();
    }

    public static CharSequence getClassName(Object object) {
        return ((AccessibilityNodeInfo)object).getClassName();
    }

    public static CharSequence getContentDescription(Object object) {
        return ((AccessibilityNodeInfo)object).getContentDescription();
    }

    public static CharSequence getPackageName(Object object) {
        return ((AccessibilityNodeInfo)object).getPackageName();
    }

    public static Object getParent(Object object) {
        return ((AccessibilityNodeInfo)object).getParent();
    }

    public static CharSequence getText(Object object) {
        return ((AccessibilityNodeInfo)object).getText();
    }

    public static int getWindowId(Object object) {
        return ((AccessibilityNodeInfo)object).getWindowId();
    }

    public static boolean isCheckable(Object object) {
        return ((AccessibilityNodeInfo)object).isCheckable();
    }

    public static boolean isChecked(Object object) {
        return ((AccessibilityNodeInfo)object).isChecked();
    }

    public static boolean isClickable(Object object) {
        return ((AccessibilityNodeInfo)object).isClickable();
    }

    public static boolean isEnabled(Object object) {
        return ((AccessibilityNodeInfo)object).isEnabled();
    }

    public static boolean isFocusable(Object object) {
        return ((AccessibilityNodeInfo)object).isFocusable();
    }

    public static boolean isFocused(Object object) {
        return ((AccessibilityNodeInfo)object).isFocused();
    }

    public static boolean isLongClickable(Object object) {
        return ((AccessibilityNodeInfo)object).isLongClickable();
    }

    public static boolean isPassword(Object object) {
        return ((AccessibilityNodeInfo)object).isPassword();
    }

    public static boolean isScrollable(Object object) {
        return ((AccessibilityNodeInfo)object).isScrollable();
    }

    public static boolean isSelected(Object object) {
        return ((AccessibilityNodeInfo)object).isSelected();
    }

    public static Object obtain() {
        return AccessibilityNodeInfo.obtain();
    }

    public static Object obtain(View view) {
        return AccessibilityNodeInfo.obtain((View)view);
    }

    public static Object obtain(Object object) {
        return AccessibilityNodeInfo.obtain((AccessibilityNodeInfo)((AccessibilityNodeInfo)object));
    }

    public static boolean performAction(Object object, int n2) {
        return ((AccessibilityNodeInfo)object).performAction(n2);
    }

    public static void recycle(Object object) {
        ((AccessibilityNodeInfo)object).recycle();
    }

    public static void setBoundsInParent(Object object, Rect rect) {
        ((AccessibilityNodeInfo)object).setBoundsInParent(rect);
    }

    public static void setBoundsInScreen(Object object, Rect rect) {
        ((AccessibilityNodeInfo)object).setBoundsInScreen(rect);
    }

    public static void setCheckable(Object object, boolean bl2) {
        ((AccessibilityNodeInfo)object).setCheckable(bl2);
    }

    public static void setChecked(Object object, boolean bl2) {
        ((AccessibilityNodeInfo)object).setChecked(bl2);
    }

    public static void setClassName(Object object, CharSequence charSequence) {
        ((AccessibilityNodeInfo)object).setClassName(charSequence);
    }

    public static void setClickable(Object object, boolean bl2) {
        ((AccessibilityNodeInfo)object).setClickable(bl2);
    }

    public static void setContentDescription(Object object, CharSequence charSequence) {
        ((AccessibilityNodeInfo)object).setContentDescription(charSequence);
    }

    public static void setEnabled(Object object, boolean bl2) {
        ((AccessibilityNodeInfo)object).setEnabled(bl2);
    }

    public static void setFocusable(Object object, boolean bl2) {
        ((AccessibilityNodeInfo)object).setFocusable(bl2);
    }

    public static void setFocused(Object object, boolean bl2) {
        ((AccessibilityNodeInfo)object).setFocused(bl2);
    }

    public static void setLongClickable(Object object, boolean bl2) {
        ((AccessibilityNodeInfo)object).setLongClickable(bl2);
    }

    public static void setPackageName(Object object, CharSequence charSequence) {
        ((AccessibilityNodeInfo)object).setPackageName(charSequence);
    }

    public static void setParent(Object object, View view) {
        ((AccessibilityNodeInfo)object).setParent(view);
    }

    public static void setPassword(Object object, boolean bl2) {
        ((AccessibilityNodeInfo)object).setPassword(bl2);
    }

    public static void setScrollable(Object object, boolean bl2) {
        ((AccessibilityNodeInfo)object).setScrollable(bl2);
    }

    public static void setSelected(Object object, boolean bl2) {
        ((AccessibilityNodeInfo)object).setSelected(bl2);
    }

    public static void setSource(Object object, View view) {
        ((AccessibilityNodeInfo)object).setSource(view);
    }

    public static void setText(Object object, CharSequence charSequence) {
        ((AccessibilityNodeInfo)object).setText(charSequence);
    }
}

