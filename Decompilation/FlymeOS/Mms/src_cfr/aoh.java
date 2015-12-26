/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteDatabase
 *  android.database.sqlite.SQLiteDatabase$CursorFactory
 *  android.database.sqlite.SQLiteOpenHelper
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.ArrayList
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;
import com.ted.android.contacts.netparser.model.NumItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class aoh
extends SQLiteOpenHelper {
    private static final String a = aoh.class.getSimpleName();
    private static aoh b = null;
    private static final String[] c = new String[]{"json", "m_t", "c_t", "phone"};
    private static final String d;
    private static final String e;
    private static final String f;
    private static final String g;
    private static final String h;
    private Context i;
    private aoj j;
    private aoj k;
    private aoj l;
    private aoj m;
    private aoj n;

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append("num");
        stringBuilder.append(" (");
        stringBuilder.append("phone");
        stringBuilder.append(" VARCHAR(20) PRIMARY KEY, ");
        stringBuilder.append("json");
        stringBuilder.append(" VARCHAR(1024), ");
        stringBuilder.append("m_t");
        stringBuilder.append(" INTEGER, ");
        stringBuilder.append("c_t");
        stringBuilder.append(" INTEGER);");
        d = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append("marker");
        stringBuilder.append(" (");
        stringBuilder.append("phone");
        stringBuilder.append(" VARCHAR(20) PRIMARY KEY, ");
        stringBuilder.append("classify");
        stringBuilder.append(" TEXT, ");
        stringBuilder.append("is_manual");
        stringBuilder.append(" INTEGER, ");
        stringBuilder.append("data_type");
        stringBuilder.append(" INTEGER, ");
        stringBuilder.append("operation_type");
        stringBuilder.append(" INTEGER, ");
        stringBuilder.append("uploaded");
        stringBuilder.append(" INTEGER);");
        e = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append("m_mark");
        stringBuilder.append(" (");
        stringBuilder.append("classify");
        stringBuilder.append(" TEXT PRIMARY KEY);");
        f = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append("corrector");
        stringBuilder.append(" (");
        stringBuilder.append("phone");
        stringBuilder.append(" VARCHAR(20) PRIMARY KEY, ");
        stringBuilder.append("name");
        stringBuilder.append(" TEXT, ");
        stringBuilder.append("old_name");
        stringBuilder.append(" TEXT, ");
        stringBuilder.append("remark");
        stringBuilder.append(" TEXT, ");
        stringBuilder.append("contact_info");
        stringBuilder.append(" TEXT, ");
        stringBuilder.append("uploaded");
        stringBuilder.append(" INTEGER);");
        g = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append("spam");
        stringBuilder.append(" (");
        stringBuilder.append("id");
        stringBuilder.append(" VARCHAR(20) PRIMARY KEY, ");
        stringBuilder.append("json");
        stringBuilder.append(" TEXT, ");
        stringBuilder.append("uploaded");
        stringBuilder.append(" INTEGER);");
        h = stringBuilder.toString();
    }

    private aoh(Context context) {
        super(context, "numcache.db", null, 3);
        this.j = new aoi(this, "num");
        this.k = new aoi(this, "marker");
        this.l = new aoi(this, "m_mark");
        this.m = new aoi(this, "corrector");
        this.n = new aoi(this, "spam");
        this.i = context;
    }

    public static aoh a(Context object) {
        synchronized (aoh.class) {
            if (b == null) {
                b = new aoh((Context)object);
            }
            object = b;
            return object;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private NumItem a(Cursor object) {
        Object object2 = object.getString(0);
        int n2 = object.getInt(1);
        int n3 = object.getInt(2);
        String string2 = object.getString(4);
        String string3 = object.getString(5);
        if (!TextUtils.isEmpty((CharSequence)object2)) {
            NumItem numItem;
            String string4 = apm.b((String)object2);
            object2 = numItem = apj.a(string4);
            if (numItem == null) {
                Log.e((String)"json", (String)("parse error, number: " + object.getString(3) + ", json : " + string4));
                object2 = numItem;
            }
        } else {
            object2 = new NumItem();
            object2.c(object.getString(3));
        }
        if (object2 != null) {
            if (!TextUtils.isEmpty((CharSequence)string2)) {
                object = new NumItem.MarkerData(0, string2, apn.a(string2));
                object.f = true;
                object.d = "\u65e0\u6807\u8bb0".equals((Object)string2);
                object2.a((NumItem.MarkerData)object);
            }
            if (!TextUtils.isEmpty((CharSequence)string3)) {
                object2.d(string3);
            }
            object2.b(n2);
            object2.c(n3);
        }
        return object2;
    }

    private boolean h(String string2) {
        if (this.l == null || apn.a(string2) != Integer.MIN_VALUE) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("classify", string2);
        return this.l.a(contentValues);
    }

    public int a(ContentValues contentValues, String string2, String[] arrstring) {
        if (this.j != null) {
            return this.j.a(contentValues, string2, arrstring);
        }
        return 0;
    }

    public Cursor a() {
        return this.getReadableDatabase().query("marker", new String[]{"phone", "classify", "data_type", "operation_type", "uploaded"}, "uploaded=0", null, null, null, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    public NumItem a(String string2) {
        Object object;
        Object var3_2 = null;
        if (this.j != null) {
            object = c;
            String string3 = String.valueOf((Object)"phone=? ") + " LIMIT 1";
            string2 = this.j.a((String[])object, string3, new String[]{string2}, null);
        } else {
            string2 = null;
        }
        object = var3_2;
        if (string2 != null) {
            object = var3_2;
            if (string2.moveToFirst()) {
                object = this.a((Cursor)string2);
            }
        }
        anr.a((Cursor)string2);
        if (string2 != null && !string2.isClosed()) {
            string2.close();
        }
        return object;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public List<NumItem> a(List<String> var1_1) {
        var3_4 = null;
        if (var1_1 == null) return null;
        if (var1_1.size() == 0) {
            return null;
        }
        var4_5 = new ArrayList(var1_1.size());
        if (this.j == null) ** GOTO lbl18
        var3_4 = aoh.c;
        var5_6 = new StringBuilder("phone");
        var5_6.append(" in(");
        var1_1 = var1_1.iterator();
        do {
            if (!var1_1.hasNext()) {
                block10 : {
                    var5_6.replace(var5_6.length() - 1, var5_6.length(), "");
                    var5_6.append(")");
                    var5_6.append(" and m_t!=");
                    var5_6.append(-1);
                    var3_4 = this.j.a((String[])var3_4, var5_6.toString(), null, null);
lbl18: // 2 sources:
                    if (var3_4 != null) {
                        if (!var3_4.moveToFirst()) break block10;
                        do {
                            var4_5.add(this.a(var3_4));
                        } while (var2_8 = var3_4.moveToNext());
                    }
                }
                anr.a(var3_4);
                return var4_5;
            }
            var6_7 = (String)var1_1.next();
            var5_6.append("'");
            var5_6.append(var6_7);
            var5_6.append("',");
        } while (true);
        catch (Exception var1_2) {
            Log.e((String)aoh.a, (String)"getNum():\u8bfb\u53d6\u53f7\u7801\u8bc6\u522b\u5217\u8868\u6570\u636e\u5931\u8d25\uff01");
            return var4_5;
        }
        finally {
            anr.a(var3_4);
            return var4_5;
        }
    }

    public void a(aoj aoj2) {
        this.j = aoj2;
    }

    public boolean a(String string2, String string3, int n2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone", string2);
        contentValues.put("json", string3);
        contentValues.put("m_t", Integer.valueOf((int)n2));
        contentValues.put("c_t", Integer.valueOf((int)((int)(System.currentTimeMillis() / 1000))));
        if (this.j != null) {
            return this.j.a(contentValues);
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean a(String string2, String string3, int n2, int n3) {
        int n4 = 1;
        this.h(string3);
        if (this.k == null) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone", string2);
        contentValues.put("classify", string3);
        if (apn.a(string3) == Integer.MIN_VALUE) {
            contentValues.put("is_manual", Integer.valueOf((int)1));
        } else {
            contentValues.put("is_manual", Integer.valueOf((int)0));
        }
        contentValues.put("data_type", Integer.valueOf((int)n2));
        contentValues.put("operation_type", Integer.valueOf((int)n3));
        n2 = "\u65e0\u6807\u8bb0".equals((Object)string3) ? n4 : 0;
        contentValues.put("uploaded", Integer.valueOf((int)n2));
        return this.k.a(contentValues);
    }

    public boolean a(String string2, String string3, String string4, String string5, String string6) {
        String string7;
        block3 : {
            if (this.m == null) {
                return false;
            }
            if (!TextUtils.isEmpty((CharSequence)string3)) {
                string7 = string3;
                if (string3.trim().length() != 0) break block3;
            }
            string7 = string4;
        }
        string3 = new ContentValues();
        string3.put("phone", string2);
        string3.put("name", string7);
        string3.put("old_name", string4);
        string3.put("remark", string5);
        string3.put("uploaded", Integer.valueOf((int)0));
        string3.put("contact_info", string6);
        return this.m.a((ContentValues)string3);
    }

    public Cursor b() {
        return this.getReadableDatabase().query("corrector", new String[]{"phone", "name", "remark", "uploaded", "contact_info"}, "uploaded=0", null, null, null, null);
    }

    public void b(aoj aoj2) {
        this.k = aoj2;
    }

    public void b(String string2) {
        this.l.a("classify=?", new String[]{string2});
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public int c(String string2) {
        int n2;
        ContentValues contentValues = new ContentValues();
        contentValues.put("json", "");
        contentValues.put("m_t", Integer.valueOf((int)0));
        contentValues.put("c_t", Integer.valueOf((int)((int)(System.currentTimeMillis() / 1000))));
        try {
            n2 = this.j != null ? this.j.a(contentValues, "phone=? and m_t=-1", new String[]{string2}) : 0;
        }
        catch (Exception var4_4) {
            n2 = 0;
        }
        int n3 = n2;
        if (n2 != 0) return n3;
        contentValues = new ContentValues();
        contentValues.put("c_t", Integer.valueOf((int)((int)(System.currentTimeMillis() / 1000))));
        n3 = n2;
        try {
            if (this.j == null) return n3;
            return this.j.a(contentValues, "phone=? ", new String[]{string2});
        }
        catch (Exception var1_2) {
            return n2;
        }
    }

    public List<String> c() {
        Cursor cursor;
        ArrayList arrayList;
        block11 : {
            Cursor cursor2 = null;
            Cursor cursor3 = null;
            arrayList = new ArrayList();
            if (this.l == null) {
                return arrayList;
            }
            cursor = this.l.a(new String[]{"classify"}, null, null, null);
            if (cursor == null) break block11;
            cursor3 = cursor;
            cursor2 = cursor;
            try {
                if (!cursor.moveToFirst()) break block11;
            }
            catch (Exception var3_2) {
                cursor2 = cursor3;
                try {
                    Log.e((String)a, (String)"getAllManualClassifies() \u83b7\u53d6\u624b\u52a8\u8f93\u5165\u6807\u8bb0\u5931\u8d25");
                }
                catch (Throwable var2_4) {
                    anr.a(cursor2);
                    throw var2_4;
                }
                anr.a(cursor3);
                return arrayList;
            }
            do {
                boolean bl2;
                block12 : {
                    cursor3 = cursor;
                    cursor2 = cursor;
                    String string2 = cursor.getString(0);
                    cursor3 = cursor;
                    cursor2 = cursor;
                    if (TextUtils.isEmpty((CharSequence)string2)) break block12;
                    cursor3 = cursor;
                    cursor2 = cursor;
                    arrayList.add(string2);
                }
                cursor3 = cursor;
                cursor2 = cursor;
                if (bl2 = cursor.moveToNext()) continue;
                break;
            } while (true);
        }
        anr.a(cursor);
        return arrayList;
    }

    public void c(aoj aoj2) {
        this.l = aoj2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public int d(String string2) {
        int n2;
        ContentValues contentValues = new ContentValues();
        contentValues.put("json", "");
        contentValues.put("m_t", Integer.valueOf((int)0));
        contentValues.put("c_t", Integer.valueOf((int)((int)(System.currentTimeMillis() / 1000))));
        try {
            n2 = this.j != null ? this.j.a(contentValues, "phone=? ", new String[]{string2}) : 0;
        }
        catch (Exception var4_4) {
            n2 = 0;
        }
        int n3 = n2;
        if (n2 != 0) return n3;
        contentValues = new ContentValues();
        contentValues.put("c_t", Integer.valueOf((int)((int)(System.currentTimeMillis() / 1000))));
        n3 = n2;
        try {
            if (this.j == null) return n3;
            return this.j.a(contentValues, "phone=? ", new String[]{string2});
        }
        catch (Exception var1_2) {
            return n2;
        }
    }

    public void d(aoj aoj2) {
        this.m = aoj2;
    }

    public boolean e(String string2) {
        return this.a(string2, "", -1);
    }

    public int f(String string2) {
        if (this.k == null) {
            return 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("uploaded", Integer.valueOf((int)1));
        return this.k.a(contentValues, "phone=?", new String[]{string2});
    }

    public int g(String string2) {
        if (this.m == null) {
            return 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("uploaded", Integer.valueOf((int)1));
        return this.m.a(contentValues, "phone=?", new String[]{string2});
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(d);
        sQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS i_n_p on num (phone);");
        sQLiteDatabase.execSQL(e);
        sQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS i_m_p on marker (phone);");
        sQLiteDatabase.execSQL(f);
        sQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS i_m_c on m_mark (classify);");
        sQLiteDatabase.execSQL(g);
        sQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS i_c_p on corrector (phone);");
        sQLiteDatabase.execSQL(h);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void onUpgrade(SQLiteDatabase var1_1, int var2_2, int var3_3) {
        if (var3_3 > 1) ** GOTO lbl18
        return;
lbl-1000: // 1 sources:
        {
            var4_4 = String.format((String)"nc%d.sql", (Object[])new Object[]{var2_2});
            aok.a(var1_1, this.i, var4_4);
            switch (var2_2) {
                case 2: {
                    var1_1.execSQL(aoh.e);
                    var1_1.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS i_m_p on marker (phone);");
                    var1_1.execSQL(aoh.f);
                    var1_1.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS i_m_c on m_mark (classify);");
                    var1_1.execSQL(aoh.g);
                    var1_1.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS i_c_p on corrector (phone);");
                }
                default: {
                    break;
                }
                case 3: {
                    var1_1.execSQL(aoh.h);
                }
            }
            ++var2_2;
lbl18: // 2 sources:
            ** while (var2_2 <= var3_3)
        }
lbl19: // 1 sources:
    }
}

