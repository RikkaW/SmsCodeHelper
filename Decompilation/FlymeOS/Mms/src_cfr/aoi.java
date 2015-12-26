/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteDatabase
 *  android.database.sqlite.SQLiteOpenHelper
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class aoi
implements aoj {
    private final String a;
    private final SQLiteOpenHelper b;

    public aoi(SQLiteOpenHelper sQLiteOpenHelper, String string2) {
        this.b = sQLiteOpenHelper;
        this.a = string2;
    }

    @Override
    public int a(ContentValues contentValues, String string2, String[] arrstring) {
        if (this.b == null) {
            return 0;
        }
        try {
            int n2 = this.b.getWritableDatabase().update(this.a, contentValues, string2, arrstring);
            return n2;
        }
        catch (Exception var1_2) {
            return 0;
        }
    }

    @Override
    public int a(String string2, String[] arrstring) {
        return this.b.getWritableDatabase().delete(this.a, string2, arrstring);
    }

    @Override
    public Cursor a(String[] object, String string2, String[] arrstring, String string3) {
        if (object == null || !this.a.equals((Object)"num")) {
            return this.b.getWritableDatabase().query(this.a, (String[])object, string2, arrstring, null, null, string3);
        }
        int n2 = object.length;
        String[] arrstring2 = new String[n2 + 2];
        int n3 = 0;
        do {
            if (n3 >= n2) {
                object = new StringBuilder("(SELECT ");
                object.append("classify");
                object.append(" FROM ");
                object.append("marker");
                object.append(" WHERE ");
                object.append("marker");
                object.append(".");
                object.append("phone");
                object.append("=");
                object.append(this.a);
                object.append(".");
                object.append("phone");
                object.append(") AS ");
                object.append("classify");
                arrstring2[n2] = object.toString();
                object = new StringBuilder("(SELECT ");
                object.append("name");
                object.append(" FROM ");
                object.append("corrector");
                object.append(" WHERE ");
                object.append("corrector");
                object.append(".");
                object.append("phone");
                object.append("=");
                object.append(this.a);
                object.append(".");
                object.append("phone");
                object.append(") AS ");
                object.append("name");
                arrstring2[n2 + 1] = object.toString();
                return this.b.getWritableDatabase().query(this.a, arrstring2, string2, arrstring, null, null, string3);
            }
            arrstring2[n3] = object[n3];
            ++n3;
        } while (true);
    }

    @Override
    public boolean a(ContentValues contentValues) {
        boolean bl2;
        boolean bl3 = bl2 = false;
        if (this.b != null) {
            bl3 = bl2;
            if (this.b.getWritableDatabase().replace(this.a, null, contentValues) != -1) {
                bl3 = true;
            }
        }
        return bl3;
    }
}

