/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteDatabase
 *  android.database.sqlite.SQLiteDatabase$CursorFactory
 *  android.database.sqlite.SQLiteException
 *  android.database.sqlite.SQLiteOpenHelper
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  org.json.JSONObject
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.ted.android.data.SmsEntity;
import org.json.JSONObject;

public class bo
extends SQLiteOpenHelper {
    private static final String a = bo.class.getSimpleName();
    private static bo b = null;
    private static final String[] d = new String[]{"msg_index", "_id", "json_string", "version"};
    private static final String e;
    private static final String f;
    private Context c;

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append("entity_cache");
        stringBuilder.append(" (");
        stringBuilder.append("msg_index");
        stringBuilder.append(" LONG PRIMARY KEY, ");
        stringBuilder.append("_id");
        stringBuilder.append(" VARCHAR(50), ");
        stringBuilder.append("json_string");
        stringBuilder.append(" VARCHAR(1024),");
        stringBuilder.append("version");
        stringBuilder.append(" INTEGER );");
        e = stringBuilder.toString();
        f = "DROP TABLE IF EXISTS entity_cache";
    }

    private bo(Context context) {
        super(context, "entity_cache.db", null, 2);
        this.c = context;
    }

    public static bo a(Context object) {
        synchronized (bo.class) {
            if (b == null) {
                b = new bo((Context)object);
            }
            object = b;
            return object;
        }
    }

    private SmsEntity a(Cursor cursor) {
        if (cursor != null) {
            return SmsEntity.fromJSON(cursor.getString(2));
        }
        return null;
    }

    private ContentValues b(SmsEntity smsEntity) {
        if (smsEntity != null) {
            ContentValues contentValues = new ContentValues(9);
            contentValues.put("msg_index", smsEntity.getMsgId());
            contentValues.put("_id", smsEntity.getMsgId());
            contentValues.put("json_string", smsEntity.toJSON().toString());
            contentValues.put("version", Integer.valueOf((int)9));
            return contentValues;
        }
        return null;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public SmsEntity a(long l2) {
        Object object;
        String[] arrstring;
        String string2;
        Object var5_3;
        block6 : {
            arrstring = null;
            var5_3 = null;
            object = d;
            string2 = String.valueOf((Object)"msg_index=? ") + " LIMIT 1";
            string2 = this.getReadableDatabase().query("entity_cache", (String[])object, string2, new String[]{String.valueOf((long)l2)}, null, null, null);
            object = arrstring;
            if (string2 != null) {
                block7 : {
                    object = arrstring;
                    if (!string2.moveToFirst()) break block6;
                    if (string2.getInt(3) >= 9) break block7;
                    this.b(string2.getLong(0));
                    arrstring = var5_3;
                    if (string2 == null) return arrstring;
                    arrstring = var5_3;
                    if (string2.isClosed()) return arrstring;
                    string2.close();
                    return var5_3;
                }
                object = this.a((Cursor)string2);
            }
        }
        arrstring = object;
        if (string2 == null) return arrstring;
        arrstring = object;
        if (string2.isClosed()) return arrstring;
        string2.close();
        return object;
        catch (SQLiteException sQLiteException) {
            try {
                sQLiteException.printStackTrace();
                arrstring = var5_3;
                if (string2 == null) return arrstring;
                arrstring = var5_3;
            }
            catch (Throwable var3_6) {
                if (string2 == null) throw var3_6;
                if (string2.isClosed()) throw var3_6;
                string2.close();
                throw var3_6;
            }
            if (string2.isClosed()) return arrstring;
            string2.close();
            return null;
        }
    }

    public boolean a(SmsEntity smsEntity) {
        if (smsEntity != null) {
            smsEntity = this.b(smsEntity);
            if (this.getWritableDatabase().insert("entity_cache", null, (ContentValues)smsEntity) > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean b(long l2) {
        if (this.getWritableDatabase().delete("entity_cache", "msg_index=? ", new String[]{String.valueOf((long)l2)}) > 0) {
            return true;
        }
        return false;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(e);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int n2, int n3) {
        if (n3 <= 1 || n3 <= n2) {
            return;
        }
        sQLiteDatabase.execSQL(f);
        sQLiteDatabase.execSQL(e);
    }
}

