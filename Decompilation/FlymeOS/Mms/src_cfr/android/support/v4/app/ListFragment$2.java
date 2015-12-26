/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.ListView
 *  java.lang.Object
 */
package android.support.v4.app;

import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

class ListFragment$2
implements AdapterView.OnItemClickListener {
    final /* synthetic */ ListFragment this$0;

    ListFragment$2(ListFragment listFragment) {
        this.this$0 = listFragment;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int n2, long l2) {
        this.this$0.onListItemClick((ListView)adapterView, view, n2, l2);
    }
}

