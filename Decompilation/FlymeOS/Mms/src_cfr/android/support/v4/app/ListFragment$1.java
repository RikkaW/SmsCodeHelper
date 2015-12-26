/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.widget.ListView
 *  java.lang.Object
 */
package android.support.v4.app;

import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

class ListFragment$1
implements Runnable {
    final /* synthetic */ ListFragment this$0;

    ListFragment$1(ListFragment listFragment) {
        this.this$0 = listFragment;
    }

    @Override
    public void run() {
        this.this$0.mList.focusableViewAvailable((View)this.this$0.mList);
    }
}

