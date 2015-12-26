/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.widget.SearchView
 *  android.widget.SearchView$OnCloseListener
 *  java.lang.Object
 */
package android.support.v4.widget;

import android.support.v4.widget.SearchViewCompatHoneycomb;
import android.widget.SearchView;

final class SearchViewCompatHoneycomb$2
implements SearchView.OnCloseListener {
    final /* synthetic */ SearchViewCompatHoneycomb.OnCloseListenerCompatBridge val$listener;

    SearchViewCompatHoneycomb$2(SearchViewCompatHoneycomb.OnCloseListenerCompatBridge onCloseListenerCompatBridge) {
        this.val$listener = onCloseListenerCompatBridge;
    }

    public boolean onClose() {
        return this.val$listener.onClose();
    }
}

