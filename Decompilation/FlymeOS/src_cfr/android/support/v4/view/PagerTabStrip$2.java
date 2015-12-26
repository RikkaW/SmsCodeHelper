/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnClickListener
 *  java.lang.Object
 */
package android.support.v4.view;

import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;

class PagerTabStrip$2
implements View.OnClickListener {
    final /* synthetic */ PagerTabStrip this$0;

    PagerTabStrip$2(PagerTabStrip pagerTabStrip) {
        this.this$0 = pagerTabStrip;
    }

    public void onClick(View view) {
        this.this$0.mPager.setCurrentItem(this.this$0.mPager.getCurrentItem() + 1);
    }
}

