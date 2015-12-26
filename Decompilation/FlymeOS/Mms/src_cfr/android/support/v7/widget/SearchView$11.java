/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemSelectedListener
 *  java.lang.Object
 */
package android.support.v7.widget;

import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;

class SearchView$11
implements AdapterView.OnItemSelectedListener {
    final /* synthetic */ SearchView this$0;

    SearchView$11(SearchView searchView) {
        this.this$0 = searchView;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int n2, long l2) {
        SearchView.access$1900(this.this$0, n2);
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}

