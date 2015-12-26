/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.widget.SearchView
 *  java.lang.Object
 */
package android.support.v4.widget;

import android.content.Context;
import android.view.View;
import android.widget.SearchView;

class SearchViewCompatIcs {
    SearchViewCompatIcs() {
    }

    public static View newSearchView(Context context) {
        return new MySearchView(context);
    }

    public static void setImeOptions(View view, int n2) {
        ((SearchView)view).setImeOptions(n2);
    }

    public static void setInputType(View view, int n2) {
        ((SearchView)view).setInputType(n2);
    }

    public static class MySearchView
    extends SearchView {
        public MySearchView(Context context) {
            super(context);
        }

        public void onActionViewCollapsed() {
            this.setQuery((CharSequence)"", false);
            super.onActionViewCollapsed();
        }
    }

}

