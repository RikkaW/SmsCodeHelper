/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnFocusChangeListener
 *  java.lang.Object
 */
package android.support.v7.widget;

import android.support.v7.widget.SearchView;
import android.view.View;

class SearchView$4
implements View.OnFocusChangeListener {
    final /* synthetic */ SearchView this$0;

    SearchView$4(SearchView searchView) {
        this.this$0 = searchView;
    }

    public void onFocusChange(View view, boolean bl2) {
        if (SearchView.access$200(this.this$0) != null) {
            SearchView.access$200(this.this$0).onFocusChange((View)this.this$0, bl2);
        }
    }
}

