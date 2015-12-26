/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.widget;

import android.support.v4.widget.SearchViewCompat;
import android.support.v4.widget.SearchViewCompatHoneycomb;

class SearchViewCompat$SearchViewCompatHoneycombImpl$1
implements SearchViewCompatHoneycomb.OnQueryTextListenerCompatBridge {
    final /* synthetic */ SearchViewCompat.SearchViewCompatHoneycombImpl this$0;
    final /* synthetic */ SearchViewCompat.OnQueryTextListenerCompat val$listener;

    SearchViewCompat$SearchViewCompatHoneycombImpl$1(SearchViewCompat.SearchViewCompatHoneycombImpl searchViewCompatHoneycombImpl, SearchViewCompat.OnQueryTextListenerCompat onQueryTextListenerCompat) {
        this.this$0 = searchViewCompatHoneycombImpl;
        this.val$listener = onQueryTextListenerCompat;
    }

    @Override
    public boolean onQueryTextChange(String string2) {
        return this.val$listener.onQueryTextChange(string2);
    }

    @Override
    public boolean onQueryTextSubmit(String string2) {
        return this.val$listener.onQueryTextSubmit(string2);
    }
}

