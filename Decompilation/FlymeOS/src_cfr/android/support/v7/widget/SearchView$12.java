/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.Editable
 *  android.text.TextWatcher
 *  java.lang.Object
 */
package android.support.v7.widget;

import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;

class SearchView$12
implements TextWatcher {
    final /* synthetic */ SearchView this$0;

    SearchView$12(SearchView searchView) {
        this.this$0 = searchView;
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int n2, int n3, int n4) {
    }

    public void onTextChanged(CharSequence charSequence, int n2, int n3, int n4) {
        SearchView.access$2000(this.this$0, charSequence);
    }
}

