/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.accessibility.AccessibilityNodeInfo
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityNodeInfo;

class AccessibilityNodeInfoCompatJellybeanMr2 {
    AccessibilityNodeInfoCompatJellybeanMr2() {
    }

    public static String getViewIdResourceName(Object object) {
        return ((AccessibilityNodeInfo)object).getViewIdResourceName();
    }

    public static void setViewIdResourceName(Object object, String string2) {
        ((AccessibilityNodeInfo)object).setViewIdResourceName(string2);
    }
}

