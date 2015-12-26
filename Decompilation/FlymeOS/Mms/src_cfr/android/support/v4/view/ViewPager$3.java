/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package android.support.v4.view;

import android.support.v4.view.ViewPager;

class ViewPager$3
implements Runnable {
    final /* synthetic */ ViewPager this$0;

    ViewPager$3(ViewPager viewPager) {
        this.this$0 = viewPager;
    }

    @Override
    public void run() {
        ViewPager.access$000(this.this$0, 0);
        this.this$0.populate();
    }
}

