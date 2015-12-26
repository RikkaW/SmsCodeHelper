/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.Editable
 *  android.view.KeyEvent
 *  android.view.View
 *  android.view.View$OnKeyListener
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v7.widget;

import android.support.v4.view.KeyEventCompat;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;

class SearchView$8
implements View.OnKeyListener {
    final /* synthetic */ SearchView this$0;

    SearchView$8(SearchView searchView) {
        this.this$0 = searchView;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean onKey(View view, int n2, KeyEvent keyEvent) {
        if (SearchView.access$1400(this.this$0) == null) {
            return false;
        }
        if (SearchView.access$1200(this.this$0).isPopupShowing() && SearchView.access$1200(this.this$0).getListSelection() != -1) {
            return SearchView.access$1500(this.this$0, view, n2, keyEvent);
        }
        if (SearchView.SearchAutoComplete.access$1600(SearchView.access$1200(this.this$0))) return false;
        if (!KeyEventCompat.hasNoModifiers(keyEvent)) return false;
        if (keyEvent.getAction() != 1) return false;
        if (n2 != 66) return false;
        view.cancelLongPress();
        SearchView.access$1700(this.this$0, 0, null, SearchView.access$1200(this.this$0).getText().toString());
        return true;
    }
}

