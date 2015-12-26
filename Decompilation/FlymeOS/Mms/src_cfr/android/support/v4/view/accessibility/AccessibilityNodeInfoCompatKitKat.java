/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.accessibility.AccessibilityNodeInfo
 *  android.view.accessibility.AccessibilityNodeInfo$CollectionInfo
 *  android.view.accessibility.AccessibilityNodeInfo$CollectionItemInfo
 *  android.view.accessibility.AccessibilityNodeInfo$RangeInfo
 *  java.lang.Object
 */
package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityNodeInfo;

class AccessibilityNodeInfoCompatKitKat {
    AccessibilityNodeInfoCompatKitKat() {
    }

    static Object getCollectionInfo(Object object) {
        return ((AccessibilityNodeInfo)object).getCollectionInfo();
    }

    static Object getCollectionItemInfo(Object object) {
        return ((AccessibilityNodeInfo)object).getCollectionItemInfo();
    }

    static int getLiveRegion(Object object) {
        return ((AccessibilityNodeInfo)object).getLiveRegion();
    }

    static Object getRangeInfo(Object object) {
        return ((AccessibilityNodeInfo)object).getRangeInfo();
    }

    public static boolean isContentInvalid(Object object) {
        return ((AccessibilityNodeInfo)object).isContentInvalid();
    }

    public static Object obtainCollectionInfo(int n2, int n3, boolean bl2, int n4) {
        return AccessibilityNodeInfo.CollectionInfo.obtain((int)n2, (int)n3, (boolean)bl2);
    }

    public static Object obtainCollectionItemInfo(int n2, int n3, int n4, int n5, boolean bl2) {
        return AccessibilityNodeInfo.CollectionItemInfo.obtain((int)n2, (int)n3, (int)n4, (int)n5, (boolean)bl2);
    }

    public static void setCollectionInfo(Object object, Object object2) {
        ((AccessibilityNodeInfo)object).setCollectionInfo((AccessibilityNodeInfo.CollectionInfo)object2);
    }

    public static void setCollectionItemInfo(Object object, Object object2) {
        ((AccessibilityNodeInfo)object).setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo)object2);
    }

    public static void setContentInvalid(Object object, boolean bl2) {
        ((AccessibilityNodeInfo)object).setContentInvalid(bl2);
    }

    static void setLiveRegion(Object object, int n2) {
        ((AccessibilityNodeInfo)object).setLiveRegion(n2);
    }

    static class CollectionInfo {
        CollectionInfo() {
        }

        static int getColumnCount(Object object) {
            return ((AccessibilityNodeInfo.CollectionInfo)object).getColumnCount();
        }

        static int getRowCount(Object object) {
            return ((AccessibilityNodeInfo.CollectionInfo)object).getRowCount();
        }

        static boolean isHierarchical(Object object) {
            return ((AccessibilityNodeInfo.CollectionInfo)object).isHierarchical();
        }
    }

    static class CollectionItemInfo {
        CollectionItemInfo() {
        }

        static int getColumnIndex(Object object) {
            return ((AccessibilityNodeInfo.CollectionItemInfo)object).getColumnIndex();
        }

        static int getColumnSpan(Object object) {
            return ((AccessibilityNodeInfo.CollectionItemInfo)object).getColumnSpan();
        }

        static int getRowIndex(Object object) {
            return ((AccessibilityNodeInfo.CollectionItemInfo)object).getRowIndex();
        }

        static int getRowSpan(Object object) {
            return ((AccessibilityNodeInfo.CollectionItemInfo)object).getRowSpan();
        }

        static boolean isHeading(Object object) {
            return ((AccessibilityNodeInfo.CollectionItemInfo)object).isHeading();
        }
    }

    static class RangeInfo {
        RangeInfo() {
        }

        static float getCurrent(Object object) {
            return ((AccessibilityNodeInfo.RangeInfo)object).getCurrent();
        }

        static float getMax(Object object) {
            return ((AccessibilityNodeInfo.RangeInfo)object).getMax();
        }

        static float getMin(Object object) {
            return ((AccessibilityNodeInfo.RangeInfo)object).getMin();
        }

        static int getType(Object object) {
            return ((AccessibilityNodeInfo.RangeInfo)object).getType();
        }
    }

}

