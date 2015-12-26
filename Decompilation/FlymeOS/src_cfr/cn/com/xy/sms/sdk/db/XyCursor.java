/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.database.CharArrayBuffer
 *  android.database.ContentObserver
 *  android.database.Cursor
 *  android.database.DataSetObserver
 *  android.database.sqlite.SQLiteDatabase
 *  android.net.Uri
 *  android.os.Bundle
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package cn.com.xy.sms.sdk.db;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.a.a;

public class XyCursor {
    private SQLiteDatabase a = null;
    private Cursor b = null;
    private int c = 0;

    public XyCursor(SQLiteDatabase sQLiteDatabase, Cursor cursor) {
        this.a = sQLiteDatabase;
        this.b = cursor;
    }

    public XyCursor(SQLiteDatabase sQLiteDatabase, Cursor cursor, int n2) {
        this.a = sQLiteDatabase;
        this.b = cursor;
        this.c = n2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void a(boolean var1_1) {
        try {
            if (this.b != null) {
                this.b.close();
            }
        }
        catch (Throwable var2_3) {}
        if (!var1_1) ** GOTO lbl17
        try {
            if (this.a != null) {
                if (this.c == 1) {
                    a.a(this.a);
                } else {
                    DBManager.close(this.a);
                }
                this.a = null;
                return;
            }
lbl17: // 1 sources:
        }
        finally {
            return;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void closeCursor(XyCursor var0, boolean var1_2) {
        if (var0 == null) {
        }
        try {
            if (var0.b != null) {
                var0.b.close();
            }
        }
        catch (Throwable var2_3) {}
        if (!var1_2) ** GOTO lbl19
        try {
            if (var0.a != null) {
                if (var0.c == 1) {
                    a.a(var0.a);
                } else {
                    DBManager.close(var0.a);
                }
                var0.a = null;
                return;
            }
lbl19: // 1 sources:
        }
        finally {
            return;
        }
    }

    public void copyStringToBuffer(int n2, CharArrayBuffer charArrayBuffer) {
        if (this.b != null) {
            this.b.copyStringToBuffer(n2, charArrayBuffer);
        }
    }

    public void deactivate() {
        if (this.b != null) {
            this.b.deactivate();
        }
    }

    public byte[] getBlob(int n2) {
        if (this.b != null) {
            return this.b.getBlob(n2);
        }
        return null;
    }

    public int getColumnCount() {
        if (this.b != null) {
            return this.b.getColumnCount();
        }
        return 0;
    }

    public int getColumnIndex(String string2) {
        if (this.b != null) {
            return this.b.getColumnIndex(string2);
        }
        return -1;
    }

    public int getColumnIndexOrThrow(String string2) {
        if (this.b != null) {
            return this.b.getColumnIndexOrThrow(string2);
        }
        return -1;
    }

    public String getColumnName(int n2) {
        if (this.b != null) {
            return this.b.getColumnName(n2);
        }
        return null;
    }

    public String[] getColumnNames() {
        if (this.b != null) {
            return this.b.getColumnNames();
        }
        return null;
    }

    public int getCount() {
        if (this.b != null) {
            return this.b.getCount();
        }
        return 0;
    }

    public Cursor getCur() {
        return this.b;
    }

    public double getDouble(int n2) {
        if (this.b != null) {
            return this.b.getDouble(n2);
        }
        return 0.0;
    }

    public Bundle getExtras() {
        if (this.b != null) {
            return this.b.getExtras();
        }
        return null;
    }

    public float getFloat(int n2) {
        if (this.b != null) {
            return this.b.getFloat(n2);
        }
        return 0.0f;
    }

    public int getInt(int n2) {
        if (this.b != null) {
            return this.b.getInt(n2);
        }
        return 0;
    }

    public long getLong(int n2) {
        if (this.b != null) {
            return this.b.getLong(n2);
        }
        return 0;
    }

    public SQLiteDatabase getMySQLiteDatabase() {
        return this.a;
    }

    public int getPosition() {
        if (this.b != null) {
            return this.b.getPosition();
        }
        return -1;
    }

    public short getShort(int n2) {
        if (this.b != null) {
            return this.b.getShort(n2);
        }
        return 0;
    }

    public String getString(int n2) {
        if (this.b != null) {
            return this.b.getString(n2);
        }
        return null;
    }

    public boolean getWantsAllOnMoveCalls() {
        if (this.b != null) {
            return this.b.getWantsAllOnMoveCalls();
        }
        return true;
    }

    public boolean isAfterLast() {
        if (this.b != null) {
            return this.b.isAfterLast();
        }
        return true;
    }

    public boolean isBeforeFirst() {
        if (this.b != null) {
            return this.b.isBeforeFirst();
        }
        return true;
    }

    public boolean isClosed() {
        if (this.b != null) {
            return this.b.isClosed();
        }
        return true;
    }

    public boolean isFirst() {
        if (this.b != null) {
            return this.b.isFirst();
        }
        return false;
    }

    public boolean isLast() {
        if (this.b != null) {
            return this.b.isLast();
        }
        return false;
    }

    public boolean isNull(int n2) {
        if (this.b != null) {
            return this.b.isNull(n2);
        }
        return false;
    }

    public boolean move(int n2) {
        if (this.b != null) {
            return this.b.move(n2);
        }
        return false;
    }

    public boolean moveToFirst() {
        if (this.b != null) {
            return this.b.moveToFirst();
        }
        return false;
    }

    public boolean moveToLast() {
        if (this.b != null) {
            return this.b.moveToLast();
        }
        return false;
    }

    public boolean moveToNext() {
        if (this.b != null) {
            return this.b.moveToNext();
        }
        return false;
    }

    public boolean moveToPosition(int n2) {
        if (this.b != null) {
            return this.b.moveToPosition(n2);
        }
        return false;
    }

    public boolean moveToPrevious() {
        if (this.b != null) {
            return this.b.moveToPrevious();
        }
        return false;
    }

    public void registerContentObserver(ContentObserver contentObserver) {
        if (this.b != null) {
            this.b.registerContentObserver(contentObserver);
        }
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        if (this.b != null) {
            this.b.registerDataSetObserver(dataSetObserver);
        }
    }

    public boolean requery() {
        if (this.b != null) {
            return this.b.requery();
        }
        return false;
    }

    public Bundle respond(Bundle bundle) {
        if (this.b != null) {
            return this.b.respond(bundle);
        }
        return null;
    }

    public void setCur(Cursor cursor) {
        this.b = cursor;
    }

    public void setMySQLiteDatabase(SQLiteDatabase sQLiteDatabase) {
        this.a = sQLiteDatabase;
    }

    public void setNotificationUri(ContentResolver contentResolver, Uri uri) {
        if (this.b != null) {
            this.b.setNotificationUri(contentResolver, uri);
        }
    }

    public void unregisterContentObserver(ContentObserver contentObserver) {
        if (this.b != null) {
            this.b.unregisterContentObserver(contentObserver);
        }
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        if (this.b != null) {
            this.b.unregisterDataSetObserver(dataSetObserver);
        }
    }
}

