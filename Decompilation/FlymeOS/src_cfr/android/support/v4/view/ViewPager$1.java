/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.util.Comparator
 */
package android.support.v4.view;

import android.support.v4.view.ViewPager;
import java.util.Comparator;

final class ViewPager$1
implements Comparator<ViewPager.ItemInfo> {
    ViewPager$1() {
    }

    public int compare(ViewPager.ItemInfo itemInfo, ViewPager.ItemInfo itemInfo2) {
        return itemInfo.position - itemInfo2.position;
    }
}

