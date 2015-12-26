/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.widget.SearchView
 *  android.widget.SearchView$OnQueryTextListener
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.widget;

import android.support.v4.widget.SearchViewCompatHoneycomb;
import android.widget.SearchView;

final class SearchViewCompatHoneycomb$1
implements SearchView.OnQueryTextListener {
    final /* synthetic */ SearchViewCompatHoneycomb.OnQueryTextListenerCompatBridge val$listener;

    SearchViewCompatHoneycomb$1(SearchViewCompatHoneycomb.OnQueryTextListenerCompatBridge onQueryTextListenerCompatBridge) {
        this.val$listener = onQueryTextListenerCompatBridge;
    }

    public boolean onQueryTextChange(String string2) {
        return this.val$listener.onQueryTextChange(string2);
    }

    public boolean onQueryTextSubmit(String string2) {
        return this.val$listener.onQueryTextSubmit(string2);
    }
}

