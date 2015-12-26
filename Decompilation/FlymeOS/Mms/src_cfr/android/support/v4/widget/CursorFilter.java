/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.database.Cursor
 *  android.widget.Filter
 *  android.widget.Filter$FilterResults
 *  java.lang.Object
 */
package android.support.v4.widget;

import android.database.Cursor;
import android.widget.Filter;

class CursorFilter
extends Filter {
    CursorFilterClient mClient;

    CursorFilter(CursorFilterClient cursorFilterClient) {
        this.mClient = cursorFilterClient;
    }

    public CharSequence convertResultToString(Object object) {
        return this.mClient.convertToString((Cursor)object);
    }

    protected Filter.FilterResults performFiltering(CharSequence charSequence) {
        charSequence = this.mClient.runQueryOnBackgroundThread(charSequence);
        Filter.FilterResults filterResults = new Filter.FilterResults();
        if (charSequence != null) {
            filterResults.count = charSequence.getCount();
            filterResults.values = charSequence;
            return filterResults;
        }
        filterResults.count = 0;
        filterResults.values = null;
        return filterResults;
    }

    protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        charSequence = this.mClient.getCursor();
        if (filterResults.values != null && filterResults.values != charSequence) {
            this.mClient.changeCursor((Cursor)filterResults.values);
        }
    }

    static interface CursorFilterClient {
        public void changeCursor(Cursor var1);

        public CharSequence convertToString(Cursor var1);

        public Cursor getCursor();

        public Cursor runQueryOnBackgroundThread(CharSequence var1);
    }

}

