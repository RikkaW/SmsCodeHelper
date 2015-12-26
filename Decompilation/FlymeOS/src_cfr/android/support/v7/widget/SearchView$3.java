/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.database.Cursor
 *  java.lang.Object
 */
package android.support.v7.widget;

import android.database.Cursor;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SuggestionsAdapter;

class SearchView$3
implements Runnable {
    final /* synthetic */ SearchView this$0;

    SearchView$3(SearchView searchView) {
        this.this$0 = searchView;
    }

    @Override
    public void run() {
        if (SearchView.access$100(this.this$0) != null && SearchView.access$100(this.this$0) instanceof SuggestionsAdapter) {
            SearchView.access$100(this.this$0).changeCursor(null);
        }
    }
}

