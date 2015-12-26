/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.accessibility.AccessibilityNodeInfo
 *  android.view.accessibility.AccessibilityNodeInfo$AccessibilityAction
 *  android.view.accessibility.AccessibilityNodeInfo$CollectionInfo
 *  android.view.accessibility.AccessibilityNodeInfo$CollectionItemInfo
 *  java.lang.Object
 */
package android.support.v4.view.accessibility;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.List;

class AccessibilityNodeInfoCompatApi21 {
    AccessibilityNodeInfoCompatApi21() {
    }

    static void addAction(Object object, Object object2) {
        ((AccessibilityNodeInfo)object).addAction((AccessibilityNodeInfo.AccessibilityAction)object2);
    }

    static int getAccessibilityActionId(Object object) {
        return ((AccessibilityNodeInfo.AccessibilityAction)object).getId();
    }

    static CharSequence getAccessibilityActionLabel(Object object) {
        return ((AccessibilityNodeInfo.AccessibilityAction)object).getLabel();
    }

    static List<Object> getActionList(Object object) {
        return ((AccessibilityNodeInfo)object).getActionList();
    }

    public static CharSequence getError(Object object) {
        return ((AccessibilityNodeInfo)object).getError();
    }

    static Object newAccessibilityAction(int n2, CharSequence charSequence) {
        return new AccessibilityNodeInfo.AccessibilityAction(n2, charSequence);
    }

    public static Object obtainCollectionInfo(int n2, int n3, boolean bl2, int n4) {
        return AccessibilityNodeInfo.CollectionInfo.obtain((int)n2, (int)n3, (boolean)bl2, (int)n4);
    }

    public static Object obtainCollectionItemInfo(int n2, int n3, int n4, int n5, boolean bl2, boolean bl3) {
        return AccessibilityNodeInfo.CollectionItemInfo.obtain((int)n2, (int)n3, (int)n4, (int)n5, (boolean)bl2, (boolean)bl3);
    }

    public static void setError(Object object, CharSequence charSequence) {
        ((AccessibilityNodeInfo)object).setError(charSequence);
    }

    public static void setLabelFor(Object object, View view) {
        ((AccessibilityNodeInfo)object).setLabelFor(view);
    }

    public static void setLabelFor(Object object, View view, int n2) {
        ((AccessibilityNodeInfo)object).setLabelFor(view, n2);
    }

    static class CollectionItemInfo {
        CollectionItemInfo() {
        }

        public static boolean isSelected(Object object) {
            return ((AccessibilityNodeInfo.CollectionItemInfo)object).isSelected();
        }
    }

}

