/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.accessibility.AccessibilityRecord
 *  java.lang.Object
 */
package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityRecord;

class AccessibilityRecordCompatIcsMr1 {
    AccessibilityRecordCompatIcsMr1() {
    }

    public static int getMaxScrollX(Object object) {
        return ((AccessibilityRecord)object).getMaxScrollX();
    }

    public static int getMaxScrollY(Object object) {
        return ((AccessibilityRecord)object).getMaxScrollY();
    }

    public static void setMaxScrollX(Object object, int n2) {
        ((AccessibilityRecord)object).setMaxScrollX(n2);
    }

    public static void setMaxScrollY(Object object, int n2) {
        ((AccessibilityRecord)object).setMaxScrollY(n2);
    }
}

