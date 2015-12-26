/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.Context
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.view.View
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.widget;

import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.v4.widget.SearchViewCompat$SearchViewCompatHoneycombImpl$1;
import android.support.v4.widget.SearchViewCompat$SearchViewCompatHoneycombImpl$2;
import android.support.v4.widget.SearchViewCompatHoneycomb;
import android.support.v4.widget.SearchViewCompatIcs;
import android.view.View;

public class SearchViewCompat {
    private static final SearchViewCompatImpl IMPL = Build.VERSION.SDK_INT >= 14 ? new SearchViewCompatIcsImpl() : (Build.VERSION.SDK_INT >= 11 ? new SearchViewCompatHoneycombImpl() : new SearchViewCompatStubImpl());

    private SearchViewCompat(Context context) {
    }

    public static CharSequence getQuery(View view) {
        return IMPL.getQuery(view);
    }

    public static boolean isIconified(View view) {
        return IMPL.isIconified(view);
    }

    public static boolean isQueryRefinementEnabled(View view) {
        return IMPL.isQueryRefinementEnabled(view);
    }

    public static boolean isSubmitButtonEnabled(View view) {
        return IMPL.isSubmitButtonEnabled(view);
    }

    public static View newSearchView(Context context) {
        return IMPL.newSearchView(context);
    }

    public static void setIconified(View view, boolean bl2) {
        IMPL.setIconified(view, bl2);
    }

    public static void setImeOptions(View view, int n2) {
        IMPL.setImeOptions(view, n2);
    }

    public static void setInputType(View view, int n2) {
        IMPL.setInputType(view, n2);
    }

    public static void setMaxWidth(View view, int n2) {
        IMPL.setMaxWidth(view, n2);
    }

    public static void setOnCloseListener(View view, OnCloseListenerCompat onCloseListenerCompat) {
        IMPL.setOnCloseListener((Object)view, onCloseListenerCompat.mListener);
    }

    public static void setOnQueryTextListener(View view, OnQueryTextListenerCompat onQueryTextListenerCompat) {
        IMPL.setOnQueryTextListener((Object)view, onQueryTextListenerCompat.mListener);
    }

    public static void setQuery(View view, CharSequence charSequence, boolean bl2) {
        IMPL.setQuery(view, charSequence, bl2);
    }

    public static void setQueryHint(View view, CharSequence charSequence) {
        IMPL.setQueryHint(view, charSequence);
    }

    public static void setQueryRefinementEnabled(View view, boolean bl2) {
        IMPL.setQueryRefinementEnabled(view, bl2);
    }

    public static void setSearchableInfo(View view, ComponentName componentName) {
        IMPL.setSearchableInfo(view, componentName);
    }

    public static void setSubmitButtonEnabled(View view, boolean bl2) {
        IMPL.setSubmitButtonEnabled(view, bl2);
    }

    public static abstract class OnCloseListenerCompat {
        final Object mListener;

        public OnCloseListenerCompat() {
            this.mListener = IMPL.newOnCloseListener(this);
        }

        public boolean onClose() {
            return false;
        }
    }

    public static abstract class OnQueryTextListenerCompat {
        final Object mListener;

        public OnQueryTextListenerCompat() {
            this.mListener = IMPL.newOnQueryTextListener(this);
        }

        public boolean onQueryTextChange(String string2) {
            return false;
        }

        public boolean onQueryTextSubmit(String string2) {
            return false;
        }
    }

    static class SearchViewCompatHoneycombImpl
    extends SearchViewCompatStubImpl {
        SearchViewCompatHoneycombImpl() {
        }

        @Override
        public CharSequence getQuery(View view) {
            return SearchViewCompatHoneycomb.getQuery(view);
        }

        @Override
        public boolean isIconified(View view) {
            return SearchViewCompatHoneycomb.isIconified(view);
        }

        @Override
        public boolean isQueryRefinementEnabled(View view) {
            return SearchViewCompatHoneycomb.isQueryRefinementEnabled(view);
        }

        @Override
        public boolean isSubmitButtonEnabled(View view) {
            return SearchViewCompatHoneycomb.isSubmitButtonEnabled(view);
        }

        @Override
        public Object newOnCloseListener(OnCloseListenerCompat onCloseListenerCompat) {
            return SearchViewCompatHoneycomb.newOnCloseListener(new SearchViewCompat$SearchViewCompatHoneycombImpl$2(this, onCloseListenerCompat));
        }

        @Override
        public Object newOnQueryTextListener(OnQueryTextListenerCompat onQueryTextListenerCompat) {
            return SearchViewCompatHoneycomb.newOnQueryTextListener(new SearchViewCompat$SearchViewCompatHoneycombImpl$1(this, onQueryTextListenerCompat));
        }

        @Override
        public View newSearchView(Context context) {
            return SearchViewCompatHoneycomb.newSearchView(context);
        }

        @Override
        public void setIconified(View view, boolean bl2) {
            SearchViewCompatHoneycomb.setIconified(view, bl2);
        }

        @Override
        public void setMaxWidth(View view, int n2) {
            SearchViewCompatHoneycomb.setMaxWidth(view, n2);
        }

        @Override
        public void setOnCloseListener(Object object, Object object2) {
            SearchViewCompatHoneycomb.setOnCloseListener(object, object2);
        }

        @Override
        public void setOnQueryTextListener(Object object, Object object2) {
            SearchViewCompatHoneycomb.setOnQueryTextListener(object, object2);
        }

        @Override
        public void setQuery(View view, CharSequence charSequence, boolean bl2) {
            SearchViewCompatHoneycomb.setQuery(view, charSequence, bl2);
        }

        @Override
        public void setQueryHint(View view, CharSequence charSequence) {
            SearchViewCompatHoneycomb.setQueryHint(view, charSequence);
        }

        @Override
        public void setQueryRefinementEnabled(View view, boolean bl2) {
            SearchViewCompatHoneycomb.setQueryRefinementEnabled(view, bl2);
        }

        @Override
        public void setSearchableInfo(View view, ComponentName componentName) {
            SearchViewCompatHoneycomb.setSearchableInfo(view, componentName);
        }

        @Override
        public void setSubmitButtonEnabled(View view, boolean bl2) {
            SearchViewCompatHoneycomb.setSubmitButtonEnabled(view, bl2);
        }
    }

    static class SearchViewCompatIcsImpl
    extends SearchViewCompatHoneycombImpl {
        SearchViewCompatIcsImpl() {
        }

        @Override
        public View newSearchView(Context context) {
            return SearchViewCompatIcs.newSearchView(context);
        }

        @Override
        public void setImeOptions(View view, int n2) {
            SearchViewCompatIcs.setImeOptions(view, n2);
        }

        @Override
        public void setInputType(View view, int n2) {
            SearchViewCompatIcs.setInputType(view, n2);
        }
    }

    static interface SearchViewCompatImpl {
        public CharSequence getQuery(View var1);

        public boolean isIconified(View var1);

        public boolean isQueryRefinementEnabled(View var1);

        public boolean isSubmitButtonEnabled(View var1);

        public Object newOnCloseListener(OnCloseListenerCompat var1);

        public Object newOnQueryTextListener(OnQueryTextListenerCompat var1);

        public View newSearchView(Context var1);

        public void setIconified(View var1, boolean var2);

        public void setImeOptions(View var1, int var2);

        public void setInputType(View var1, int var2);

        public void setMaxWidth(View var1, int var2);

        public void setOnCloseListener(Object var1, Object var2);

        public void setOnQueryTextListener(Object var1, Object var2);

        public void setQuery(View var1, CharSequence var2, boolean var3);

        public void setQueryHint(View var1, CharSequence var2);

        public void setQueryRefinementEnabled(View var1, boolean var2);

        public void setSearchableInfo(View var1, ComponentName var2);

        public void setSubmitButtonEnabled(View var1, boolean var2);
    }

    static class SearchViewCompatStubImpl
    implements SearchViewCompatImpl {
        SearchViewCompatStubImpl() {
        }

        @Override
        public CharSequence getQuery(View view) {
            return null;
        }

        @Override
        public boolean isIconified(View view) {
            return true;
        }

        @Override
        public boolean isQueryRefinementEnabled(View view) {
            return false;
        }

        @Override
        public boolean isSubmitButtonEnabled(View view) {
            return false;
        }

        @Override
        public Object newOnCloseListener(OnCloseListenerCompat onCloseListenerCompat) {
            return null;
        }

        @Override
        public Object newOnQueryTextListener(OnQueryTextListenerCompat onQueryTextListenerCompat) {
            return null;
        }

        @Override
        public View newSearchView(Context context) {
            return null;
        }

        @Override
        public void setIconified(View view, boolean bl2) {
        }

        @Override
        public void setImeOptions(View view, int n2) {
        }

        @Override
        public void setInputType(View view, int n2) {
        }

        @Override
        public void setMaxWidth(View view, int n2) {
        }

        @Override
        public void setOnCloseListener(Object object, Object object2) {
        }

        @Override
        public void setOnQueryTextListener(Object object, Object object2) {
        }

        @Override
        public void setQuery(View view, CharSequence charSequence, boolean bl2) {
        }

        @Override
        public void setQueryHint(View view, CharSequence charSequence) {
        }

        @Override
        public void setQueryRefinementEnabled(View view, boolean bl2) {
        }

        @Override
        public void setSearchableInfo(View view, ComponentName componentName) {
        }

        @Override
        public void setSubmitButtonEnabled(View view, boolean bl2) {
        }
    }

}

