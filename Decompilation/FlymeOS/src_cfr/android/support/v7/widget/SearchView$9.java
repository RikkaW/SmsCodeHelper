/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.KeyEvent
 *  android.widget.TextView
 *  android.widget.TextView$OnEditorActionListener
 *  java.lang.Object
 */
package android.support.v7.widget;

import android.support.v7.widget.SearchView;
import android.view.KeyEvent;
import android.widget.TextView;

class SearchView$9
implements TextView.OnEditorActionListener {
    final /* synthetic */ SearchView this$0;

    SearchView$9(SearchView searchView) {
        this.this$0 = searchView;
    }

    public boolean onEditorAction(TextView textView, int n2, KeyEvent keyEvent) {
        SearchView.access$900(this.this$0);
        return true;
    }
}

