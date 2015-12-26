/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Parcelable
 *  android.view.View
 *  android.view.accessibility.AccessibilityNodeInfo
 *  android.view.accessibility.AccessibilityRecord
 *  java.lang.Object
 */
package android.support.v4.view.accessibility;

import android.os.Parcelable;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityRecord;
import java.util.List;

class AccessibilityRecordCompatIcs {
    AccessibilityRecordCompatIcs() {
    }

    public static int getAddedCount(Object object) {
        return ((AccessibilityRecord)object).getAddedCount();
    }

    public static CharSequence getBeforeText(Object object) {
        return ((AccessibilityRecord)object).getBeforeText();
    }

    public static CharSequence getClassName(Object object) {
        return ((AccessibilityRecord)object).getClassName();
    }

    public static CharSequence getContentDescription(Object object) {
        return ((AccessibilityRecord)object).getContentDescription();
    }

    public static int getCurrentItemIndex(Object object) {
        return ((AccessibilityRecord)object).getCurrentItemIndex();
    }

    public static int getFromIndex(Object object) {
        return ((AccessibilityRecord)object).getFromIndex();
    }

    public static int getItemCount(Object object) {
        return ((AccessibilityRecord)object).getItemCount();
    }

    public static Parcelable getParcelableData(Object object) {
        return ((AccessibilityRecord)object).getParcelableData();
    }

    public static int getRemovedCount(Object object) {
        return ((AccessibilityRecord)object).getRemovedCount();
    }

    public static int getScrollX(Object object) {
        return ((AccessibilityRecord)object).getScrollX();
    }

    public static int getScrollY(Object object) {
        return ((AccessibilityRecord)object).getScrollY();
    }

    public static Object getSource(Object object) {
        return ((AccessibilityRecord)object).getSource();
    }

    public static List<CharSequence> getText(Object object) {
        return ((AccessibilityRecord)object).getText();
    }

    public static int getToIndex(Object object) {
        return ((AccessibilityRecord)object).getToIndex();
    }

    public static int getWindowId(Object object) {
        return ((AccessibilityRecord)object).getWindowId();
    }

    public static boolean isChecked(Object object) {
        return ((AccessibilityRecord)object).isChecked();
    }

    public static boolean isEnabled(Object object) {
        return ((AccessibilityRecord)object).isEnabled();
    }

    public static boolean isFullScreen(Object object) {
        return ((AccessibilityRecord)object).isFullScreen();
    }

    public static boolean isPassword(Object object) {
        return ((AccessibilityRecord)object).isPassword();
    }

    public static boolean isScrollable(Object object) {
        return ((AccessibilityRecord)object).isScrollable();
    }

    public static Object obtain() {
        return AccessibilityRecord.obtain();
    }

    public static Object obtain(Object object) {
        return AccessibilityRecord.obtain((AccessibilityRecord)((AccessibilityRecord)object));
    }

    public static void recycle(Object object) {
        ((AccessibilityRecord)object).recycle();
    }

    public static void setAddedCount(Object object, int n2) {
        ((AccessibilityRecord)object).setAddedCount(n2);
    }

    public static void setBeforeText(Object object, CharSequence charSequence) {
        ((AccessibilityRecord)object).setBeforeText(charSequence);
    }

    public static void setChecked(Object object, boolean bl2) {
        ((AccessibilityRecord)object).setChecked(bl2);
    }

    public static void setClassName(Object object, CharSequence charSequence) {
        ((AccessibilityRecord)object).setClassName(charSequence);
    }

    public static void setContentDescription(Object object, CharSequence charSequence) {
        ((AccessibilityRecord)object).setContentDescription(charSequence);
    }

    public static void setCurrentItemIndex(Object object, int n2) {
        ((AccessibilityRecord)object).setCurrentItemIndex(n2);
    }

    public static void setEnabled(Object object, boolean bl2) {
        ((AccessibilityRecord)object).setEnabled(bl2);
    }

    public static void setFromIndex(Object object, int n2) {
        ((AccessibilityRecord)object).setFromIndex(n2);
    }

    public static void setFullScreen(Object object, boolean bl2) {
        ((AccessibilityRecord)object).setFullScreen(bl2);
    }

    public static void setItemCount(Object object, int n2) {
        ((AccessibilityRecord)object).setItemCount(n2);
    }

    public static void setParcelableData(Object object, Parcelable parcelable) {
        ((AccessibilityRecord)object).setParcelableData(parcelable);
    }

    public static void setPassword(Object object, boolean bl2) {
        ((AccessibilityRecord)object).setPassword(bl2);
    }

    public static void setRemovedCount(Object object, int n2) {
        ((AccessibilityRecord)object).setRemovedCount(n2);
    }

    public static void setScrollX(Object object, int n2) {
        ((AccessibilityRecord)object).setScrollX(n2);
    }

    public static void setScrollY(Object object, int n2) {
        ((AccessibilityRecord)object).setScrollY(n2);
    }

    public static void setScrollable(Object object, boolean bl2) {
        ((AccessibilityRecord)object).setScrollable(bl2);
    }

    public static void setSource(Object object, View view) {
        ((AccessibilityRecord)object).setSource(view);
    }

    public static void setToIndex(Object object, int n2) {
        ((AccessibilityRecord)object).setToIndex(n2);
    }
}

