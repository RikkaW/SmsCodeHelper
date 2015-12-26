/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.database.ContentObserver
 *  android.database.Cursor
 *  android.net.Uri
 *  java.io.PrintWriter
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Arrays
 */
package android.support.v4.content;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;

public class CursorLoader
extends AsyncTaskLoader<Cursor> {
    Cursor mCursor;
    final Loader<Cursor> mObserver;
    String[] mProjection;
    String mSelection;
    String[] mSelectionArgs;
    String mSortOrder;
    Uri mUri;

    public CursorLoader(Context context) {
        super(context);
        this.mObserver = new Loader.ForceLoadContentObserver();
    }

    public CursorLoader(Context context, Uri uri, String[] arrstring, String string2, String[] arrstring2, String string3) {
        super(context);
        this.mObserver = new Loader.ForceLoadContentObserver();
        this.mUri = uri;
        this.mProjection = arrstring;
        this.mSelection = string2;
        this.mSelectionArgs = arrstring2;
        this.mSortOrder = string3;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void deliverResult(Cursor cursor) {
        if (this.isReset()) {
            if (cursor == null) return;
            {
                cursor.close();
                return;
            }
        } else {
            Cursor cursor2 = this.mCursor;
            this.mCursor = cursor;
            if (this.isStarted()) {
                super.deliverResult(cursor);
            }
            if (cursor2 == null || cursor2 == cursor || cursor2.isClosed()) return;
            {
                cursor2.close();
                return;
            }
        }
    }

    @Override
    public void dump(String string2, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] arrstring) {
        super.dump(string2, fileDescriptor, printWriter, arrstring);
        printWriter.print(string2);
        printWriter.print("mUri=");
        printWriter.println((Object)this.mUri);
        printWriter.print(string2);
        printWriter.print("mProjection=");
        printWriter.println(Arrays.toString((Object[])this.mProjection));
        printWriter.print(string2);
        printWriter.print("mSelection=");
        printWriter.println(this.mSelection);
        printWriter.print(string2);
        printWriter.print("mSelectionArgs=");
        printWriter.println(Arrays.toString((Object[])this.mSelectionArgs));
        printWriter.print(string2);
        printWriter.print("mSortOrder=");
        printWriter.println(this.mSortOrder);
        printWriter.print(string2);
        printWriter.print("mCursor=");
        printWriter.println((Object)this.mCursor);
        printWriter.print(string2);
        printWriter.print("mContentChanged=");
        printWriter.println(this.mContentChanged);
    }

    public String[] getProjection() {
        return this.mProjection;
    }

    public String getSelection() {
        return this.mSelection;
    }

    public String[] getSelectionArgs() {
        return this.mSelectionArgs;
    }

    public String getSortOrder() {
        return this.mSortOrder;
    }

    public Uri getUri() {
        return this.mUri;
    }

    @Override
    public Cursor loadInBackground() {
        Cursor cursor = this.getContext().getContentResolver().query(this.mUri, this.mProjection, this.mSelection, this.mSelectionArgs, this.mSortOrder);
        if (cursor != null) {
            cursor.getCount();
            cursor.registerContentObserver(this.mObserver);
        }
        return cursor;
    }

    @Override
    public void onCanceled(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    @Override
    protected void onReset() {
        super.onReset();
        this.onStopLoading();
        if (this.mCursor != null && !this.mCursor.isClosed()) {
            this.mCursor.close();
        }
        this.mCursor = null;
    }

    @Override
    protected void onStartLoading() {
        if (this.mCursor != null) {
            this.deliverResult(this.mCursor);
        }
        if (this.takeContentChanged() || this.mCursor == null) {
            this.forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        this.cancelLoad();
    }

    public void setProjection(String[] arrstring) {
        this.mProjection = arrstring;
    }

    public void setSelection(String string2) {
        this.mSelection = string2;
    }

    public void setSelectionArgs(String[] arrstring) {
        this.mSelectionArgs = arrstring;
    }

    public void setSortOrder(String string2) {
        this.mSortOrder = string2;
    }

    public void setUri(Uri uri) {
        this.mUri = uri;
    }
}

