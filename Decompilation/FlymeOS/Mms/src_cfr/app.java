/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteDatabase
 *  android.util.Log
 *  android.util.Pair
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.FileNotFoundException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.HashSet
 *  java.util.concurrent.Executors
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.Pair;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class app {
    private static final String a = app.class.getSimpleName();
    private static app b = null;
    private Context c;

    public app(Context context) {
        this.c = context;
    }

    public static app a(Context object) {
        synchronized (app.class) {
            if (b == null) {
                b = new app((Context)object);
            }
            object = b;
            return object;
        }
    }

    private HashSet<Pair<String, String>> a(String string2) {
        apr.a(this.c);
        string2 = apr.a().a("numcache.db", string2);
        Cursor cursor = string2.rawQuery("SELECT phone, json FROM num", null);
        HashSet hashSet = new HashSet(cursor.getCount());
        if (cursor != null && cursor.moveToFirst()) {
            do {
                hashSet.add((Object)Pair.create((Object)cursor.getString(0), (Object)cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        if (string2 != null) {
            string2.close();
        }
        return hashSet;
    }

    static /* synthetic */ void a(app app2, boolean bl2) {
        app2.a(bl2);
    }

    private void a(String string2, boolean bl2) {
        SharedPreferences.Editor editor = this.c.getSharedPreferences("txl.bat", 0).edit();
        editor.putBoolean(string2, bl2);
        editor.commit();
    }

    private void a(HashSet<Pair<String, String>> object) {
        aoh aoh2 = aoh.a(this.c);
        aoh2.getWritableDatabase().beginTransaction();
        object = object.iterator();
        do {
            if (!object.hasNext()) {
                aoh2.getWritableDatabase().setTransactionSuccessful();
                aoh2.getWritableDatabase().endTransaction();
                return;
            }
            Pair pair = (Pair)object.next();
            String string2 = apm.a((String)pair.second);
            aoh2.a((String)pair.first, string2, 0);
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void a(boolean bl2) {
        FileInputStream fileInputStream;
        HashSet<Pair<String, String>> hashSet = this.c.getCacheDir().getAbsolutePath();
        if (bl2 && !ff.a(this.c, "vcard.dat", (String)hashSet)) {
            return;
        }
        String string2 = String.valueOf((Object)hashSet) + File.separator + "vcard.dat";
        try {
            fileInputStream = new FileInputStream(string2);
            if (fileInputStream == null) return;
        }
        catch (FileNotFoundException var2_5) {
            var2_5.printStackTrace();
            return;
        }
        if (!ff.a((InputStream)fileInputStream, String.valueOf((Object)hashSet) + File.separator)) return;
        fileInputStream = new File(String.valueOf((Object)hashSet) + File.separator + "numcache.db");
        string2 = new File(string2);
        if (fileInputStream != null && fileInputStream.exists() && fileInputStream.length() == 0) {
            fileInputStream.delete();
            if (string2 == null) return;
            if (!string2.exists()) return;
            string2.delete();
            return;
        }
        hashSet = this.a((String)hashSet);
        if (fileInputStream != null && fileInputStream.exists()) {
            fileInputStream.delete();
        }
        if (string2 != null && string2.exists()) {
            string2.delete();
        }
        this.a(hashSet);
        this.a("load_local_data", true);
    }

    private boolean b(String string2, boolean bl2) {
        return this.c.getSharedPreferences("txl.bat", 0).getBoolean(string2, bl2);
    }

    public void a(boolean bl2, boolean bl3) {
        if (!bl3 && this.b("load_local_data", false)) {
            return;
        }
        ExecutorService executorService = Executors.newSingleThreadExecutor((ThreadFactory)new a());
        executorService.execute((Runnable)new apq(this, bl2));
        executorService.shutdown();
    }

}

