/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.inputmethod.InputMethodManager
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v7.widget;

import android.content.Context;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

class SearchView$1
implements Runnable {
    final /* synthetic */ SearchView this$0;

    SearchView$1(SearchView searchView) {
        this.this$0 = searchView;
    }

    @Override
    public void run() {
        InputMethodManager inputMethodManager = (InputMethodManager)this.this$0.getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            SearchView.HIDDEN_METHOD_INVOKER.showSoftInputUnchecked(inputMethodManager, (View)this.this$0, 0);
        }
    }
}

