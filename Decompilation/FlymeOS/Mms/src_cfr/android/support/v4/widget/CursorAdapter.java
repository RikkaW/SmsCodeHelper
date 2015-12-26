/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.ContentObserver
 *  android.database.Cursor
 *  android.database.DataSetObserver
 *  android.os.Handler
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.BaseAdapter
 *  android.widget.Filter
 *  android.widget.FilterQueryProvider
 *  android.widget.Filterable
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.support.v4.widget.CursorFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;

public abstract class CursorAdapter
extends BaseAdapter
implements CursorFilter.CursorFilterClient,
Filterable {
    @Deprecated
    public static final int FLAG_AUTO_REQUERY = 1;
    public static final int FLAG_REGISTER_CONTENT_OBSERVER = 2;
    protected boolean mAutoRequery;
    protected ChangeObserver mChangeObserver;
    public Context mContext;
    public Cursor mCursor;
    protected CursorFilter mCursorFilter;
    protected DataSetObserver mDataSetObserver;
    protected boolean mDataValid;
    protected FilterQueryProvider mFilterQueryProvider;
    protected int mRowIDColumn;

    @Deprecated
    public CursorAdapter(Context context, Cursor cursor) {
        this.init(context, cursor, 1);
    }

    public CursorAdapter(Context context, Cursor cursor, int n2) {
        this.init(context, cursor, n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public CursorAdapter(Context context, Cursor cursor, boolean bl2) {
        int n2 = bl2 ? 1 : 2;
        this.init(context, cursor, n2);
    }

    public abstract void bindView(View var1, Context var2, Cursor var3);

    @Override
    public void changeCursor(Cursor cursor) {
        if ((cursor = this.swapCursor(cursor)) != null) {
            cursor.close();
        }
    }

    @Override
    public CharSequence convertToString(Cursor cursor) {
        if (cursor == null) {
            return "";
        }
        return cursor.toString();
    }

    public int getCount() {
        if (this.mDataValid && this.mCursor != null) {
            return this.mCursor.getCount();
        }
        return 0;
    }

    @Override
    public Cursor getCursor() {
        return this.mCursor;
    }

    public View getDropDownView(int n2, View view, ViewGroup viewGroup) {
        if (this.mDataValid) {
            this.mCursor.moveToPosition(n2);
            View view2 = view;
            if (view == null) {
                view2 = this.newDropDownView(this.mContext, this.mCursor, viewGroup);
            }
            this.bindView(view2, this.mContext, this.mCursor);
            return view2;
        }
        return null;
    }

    public Filter getFilter() {
        if (this.mCursorFilter == null) {
            this.mCursorFilter = new CursorFilter(this);
        }
        return this.mCursorFilter;
    }

    public FilterQueryProvider getFilterQueryProvider() {
        return this.mFilterQueryProvider;
    }

    public Object getItem(int n2) {
        if (this.mDataValid && this.mCursor != null) {
            this.mCursor.moveToPosition(n2);
            return this.mCursor;
        }
        return null;
    }

    public long getItemId(int n2) {
        long l2;
        long l3 = l2 = 0;
        if (this.mDataValid) {
            l3 = l2;
            if (this.mCursor != null) {
                l3 = l2;
                if (this.mCursor.moveToPosition(n2)) {
                    l3 = this.mCursor.getLong(this.mRowIDColumn);
                }
            }
        }
        return l3;
    }

    public View getView(int n2, View view, ViewGroup viewGroup) {
        if (!this.mDataValid) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (!this.mCursor.moveToPosition(n2)) {
            throw new IllegalStateException("couldn't move cursor to position " + n2);
        }
        View view2 = view;
        if (view == null) {
            view2 = this.newView(this.mContext, this.mCursor, viewGroup);
        }
        this.bindView(view2, this.mContext, this.mCursor);
        return view2;
    }

    public boolean hasStableIds() {
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    void init(Context context, Cursor cursor, int n2) {
        boolean bl2 = true;
        if ((n2 & 1) == 1) {
            n2 |= 2;
            this.mAutoRequery = true;
        } else {
            this.mAutoRequery = false;
        }
        if (cursor == null) {
            bl2 = false;
        }
        this.mCursor = cursor;
        this.mDataValid = bl2;
        this.mContext = context;
        int n3 = bl2 ? cursor.getColumnIndexOrThrow("_id") : -1;
        this.mRowIDColumn = n3;
        if ((n2 & 2) == 2) {
            this.mChangeObserver = new ChangeObserver();
            this.mDataSetObserver = new MyDataSetObserver();
        } else {
            this.mChangeObserver = null;
            this.mDataSetObserver = null;
        }
        if (bl2) {
            if (this.mChangeObserver != null) {
                cursor.registerContentObserver((ContentObserver)this.mChangeObserver);
            }
            if (this.mDataSetObserver != null) {
                cursor.registerDataSetObserver(this.mDataSetObserver);
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Deprecated
    protected void init(Context context, Cursor cursor, boolean bl2) {
        int n2 = bl2 ? 1 : 2;
        this.init(context, cursor, n2);
    }

    public View newDropDownView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.newView(context, cursor, viewGroup);
    }

    public abstract View newView(Context var1, Cursor var2, ViewGroup var3);

    protected void onContentChanged() {
        if (this.mAutoRequery && this.mCursor != null && !this.mCursor.isClosed()) {
            this.mDataValid = this.mCursor.requery();
        }
    }

    @Override
    public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        if (this.mFilterQueryProvider != null) {
            return this.mFilterQueryProvider.runQuery(charSequence);
        }
        return this.mCursor;
    }

    public void setFilterQueryProvider(FilterQueryProvider filterQueryProvider) {
        this.mFilterQueryProvider = filterQueryProvider;
    }

    public Cursor swapCursor(Cursor cursor) {
        if (cursor == this.mCursor) {
            return null;
        }
        Cursor cursor2 = this.mCursor;
        if (cursor2 != null) {
            if (this.mChangeObserver != null) {
                cursor2.unregisterContentObserver((ContentObserver)this.mChangeObserver);
            }
            if (this.mDataSetObserver != null) {
                cursor2.unregisterDataSetObserver(this.mDataSetObserver);
            }
        }
        this.mCursor = cursor;
        if (cursor != null) {
            if (this.mChangeObserver != null) {
                cursor.registerContentObserver((ContentObserver)this.mChangeObserver);
            }
            if (this.mDataSetObserver != null) {
                cursor.registerDataSetObserver(this.mDataSetObserver);
            }
            this.mRowIDColumn = cursor.getColumnIndexOrThrow("_id");
            this.mDataValid = true;
            this.notifyDataSetChanged();
            return cursor2;
        }
        this.mRowIDColumn = -1;
        this.mDataValid = false;
        this.notifyDataSetInvalidated();
        return cursor2;
    }

    class ChangeObserver
    extends ContentObserver {
        public ChangeObserver() {
            super(new Handler());
        }

        public boolean deliverSelfNotifications() {
            return true;
        }

        public void onChange(boolean bl2) {
            CursorAdapter.this.onContentChanged();
        }
    }

    class MyDataSetObserver
    extends DataSetObserver {
        private MyDataSetObserver() {
        }

        public void onChanged() {
            CursorAdapter.this.mDataValid = true;
            CursorAdapter.this.notifyDataSetChanged();
        }

        public void onInvalidated() {
            CursorAdapter.this.mDataValid = false;
            CursorAdapter.this.notifyDataSetInvalidated();
        }
    }

}

