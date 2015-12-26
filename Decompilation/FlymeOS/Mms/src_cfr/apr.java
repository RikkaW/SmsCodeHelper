/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.pm.ApplicationInfo
 *  android.database.sqlite.SQLiteDatabase
 *  android.database.sqlite.SQLiteDatabase$CursorFactory
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.database.sqlite.SQLiteDatabase;
import java.util.HashMap;
import java.util.Map;

public class apr {
    private static apr c = null;
    private Map<String, SQLiteDatabase> a = new HashMap();
    private Context b = null;

    private apr(Context context) {
        this.b = context;
    }

    public static apr a() {
        return c;
    }

    private String a(String string2) {
        return String.format((String)string2, (Object[])new Object[]{this.b.getApplicationInfo().packageName});
    }

    public static void a(Context context) {
        if (c == null) {
            c = new apr(context);
        }
    }

    private String b(String string2, String string3) {
        return String.valueOf((Object)this.a(string3)) + "/" + string2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public SQLiteDatabase a(String string2, String string3) {
        SQLiteDatabase sQLiteDatabase;
        if (this.a.get(string2) != null && this.a.get(string2).isOpen()) {
            return this.a.get(string2);
        }
        if (this.b == null) {
            return null;
        }
        string3 = sQLiteDatabase = SQLiteDatabase.openDatabase((String)this.b(string2, string3), (SQLiteDatabase.CursorFactory)null, (int)16);
        if (sQLiteDatabase == null) return string3;
        this.a.put(string2, sQLiteDatabase);
        return sQLiteDatabase;
    }
}

