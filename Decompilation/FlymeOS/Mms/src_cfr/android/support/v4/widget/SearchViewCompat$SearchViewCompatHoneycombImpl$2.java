/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package android.support.v4.widget;

import android.support.v4.widget.SearchViewCompat;
import android.support.v4.widget.SearchViewCompatHoneycomb;

class SearchViewCompat$SearchViewCompatHoneycombImpl$2
implements SearchViewCompatHoneycomb.OnCloseListenerCompatBridge {
    final /* synthetic */ SearchViewCompat.SearchViewCompatHoneycombImpl this$0;
    final /* synthetic */ SearchViewCompat.OnCloseListenerCompat val$listener;

    SearchViewCompat$SearchViewCompatHoneycombImpl$2(SearchViewCompat.SearchViewCompatHoneycombImpl searchViewCompatHoneycombImpl, SearchViewCompat.OnCloseListenerCompat onCloseListenerCompat) {
        this.this$0 = searchViewCompatHoneycombImpl;
        this.val$listener = onCloseListenerCompat;
    }

    @Override
    public boolean onClose() {
        return this.val$listener.onClose();
    }
}

