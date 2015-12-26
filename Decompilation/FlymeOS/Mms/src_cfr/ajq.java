/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentProviderOperation
 *  android.content.ContentProviderOperation$Builder
 *  android.content.ContentProviderResult
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.OperationApplicationException
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteFullException
 *  android.net.Uri
 *  android.os.RemoteException
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  org.json.JSONException
 *  org.json.JSONObject
 */
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.database.sqlite.SQLiteFullException;
import android.net.Uri;
import android.os.RemoteException;
import com.meizu.statsapp.UsageStatsProvider;
import com.meizu.statsapp.UsageStatsProxy;
import com.meizu.statsapp.util.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

final class ajq {
    private static volatile ajq a;
    private static Object e;
    private Context b;
    private boolean c;
    private UsageStatsProvider d;

    static {
        e = new Object();
    }

    private ajq(Context context, boolean bl2) {
        this.b = context;
        this.c = bl2;
        if (this.c) {
            this.d = new UsageStatsProvider(this.b);
            this.d.onCreate();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private int a(ArrayList<ContentProviderOperation> arrcontentProviderResult) {
        int n2 = 0;
        int n3 = 0;
        synchronized (this) {
            boolean bl2 = this.c;
            if (bl2) {
                try {
                    arrcontentProviderResult = this.d.applyBatch((ArrayList<ContentProviderOperation>)arrcontentProviderResult);
                }
                catch (OperationApplicationException var1_2) {
                    var1_2.printStackTrace();
                    arrcontentProviderResult = null;
                }
            } else {
                try {
                    arrcontentProviderResult = this.b.getContentResolver().applyBatch("com.meizu.usagestats", (ArrayList)arrcontentProviderResult);
                }
                catch (RemoteException var1_3) {
                    var1_3.printStackTrace();
                    arrcontentProviderResult = null;
                }
                catch (OperationApplicationException var1_4) {
                    var1_4.printStackTrace();
                    arrcontentProviderResult = null;
                }
            }
            int n4 = n2;
            if (arrcontentProviderResult != null) {
                n4 = n2;
                if (arrcontentProviderResult.length > 0) {
                    int n5 = arrcontentProviderResult.length;
                    n2 = 0;
                    do {
                        n4 = n3;
                        if (n2 >= n5) break;
                        n4 = arrcontentProviderResult[n2].count;
                        ++n2;
                        n3 = n4 + n3;
                    } while (true);
                }
            }
            return n4;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static ajq a(Context context, boolean bl2) {
        if (a == null) {
            Object object = e;
            synchronized (object) {
                if (a == null) {
                    a = new ajq(context, bl2);
                }
            }
        }
        return a;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static UsageStatsProxy.Event a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        UsageStatsProxy.Event event = new UsageStatsProxy.Event();
        event.b(cursor.getInt(cursor.getColumnIndex("_id")));
        event.a(cursor.getString(cursor.getColumnIndex("name")));
        event.a(cursor.getInt(cursor.getColumnIndex("type")));
        event.b(cursor.getString(cursor.getColumnIndex("sessionid")));
        event.c(cursor.getString(cursor.getColumnIndex("package")));
        event.d(cursor.getString(cursor.getColumnIndex("page")));
        event.a(cursor.getLong(cursor.getColumnIndex("time")));
        String string2 = cursor.getString(cursor.getColumnIndex("properties"));
        if (!Utils.isEmpty(string2)) {
            try {
                event.a((Object)new JSONObject(string2));
            }
            catch (JSONException var2_3) {
                var2_3.printStackTrace();
            }
        } else {
            event.a((Object)new JSONObject());
        }
        event.e(cursor.getString(cursor.getColumnIndex("network")));
        event.b(cursor.getLong(cursor.getColumnIndex("channel")));
        event.f(cursor.getString(cursor.getColumnIndex("flyme_version")));
        return event;
    }

    public static ContentValues b(UsageStatsProxy.Event event) {
        String string2;
        if (event == null) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", event.a());
        contentValues.put("type", Integer.valueOf((int)event.b()));
        contentValues.put("package", event.e());
        contentValues.put("sessionid", event.d());
        contentValues.put("time", Long.valueOf((long)event.c()));
        if (!Utils.isEmpty(event.f())) {
            contentValues.put("page", event.f());
        }
        if (!Utils.isEmpty(string2 = event.h())) {
            contentValues.put("properties", string2);
        }
        contentValues.put("network", event.j());
        contentValues.put("channel", Long.valueOf((long)event.k()));
        contentValues.put("flyme_version", event.l());
        return contentValues;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public int a() {
        int n2 = 0;
        synchronized (this) {
            block10 : {
                Cursor cursor = this.a(0);
                if (cursor != null) {
                    try {
                        int n3;
                        n2 = n3 = cursor.getCount();
                    }
                    catch (Exception var4_4) {
                        var4_4.printStackTrace();
                        break block10;
                    }
                    finally {
                        cursor.close();
                    }
                    cursor.close();
                }
            }
            return n2;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public int a(Collection<UsageStatsProxy.Event> var1_1) {
        // MONITORENTER : this
        if (var1_1 != null && (var2_2 = var1_1.size()) >= 1) {
            var5_4 = Uri.parse((String)"content://com.meizu.usagestats/event");
            var6_5 = new ArrayList();
            var1_1 = var1_1.iterator();
            var4_6 = 0;
            var2_2 = 0;
        } else {
            var3_3 = 0;
            do {
                // MONITOREXIT : this
                return var3_3;
                break;
            } while (true);
        }
        do {
            if (!var1_1.hasNext()) ** GOTO lbl25
            var7_7 = (UsageStatsProxy.Event)var1_1.next();
            var8_8 = ContentProviderOperation.newDelete((Uri)var5_4);
            var8_8.withSelection("_id=?", new String[]{String.valueOf((int)var7_7.i())});
            var6_5.add((Object)var8_8.build());
            if (++var4_6 <= 50) ** GOTO lbl29
            var3_3 = this.a(var6_5);
            var6_5.clear();
            var3_3 += var2_2;
            var2_2 = 0;
            ** GOTO lbl31
lbl25: // 1 sources:
            var3_3 = var2_2;
            if (var6_5.size() <= 0) ** continue;
            var3_3 = this.a(var6_5);
            return var2_2 + var3_3;
lbl29: // 1 sources:
            var3_3 = var2_2;
            var2_2 = var4_6;
lbl31: // 2 sources:
            var4_6 = var2_2;
            var2_2 = var3_3;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public Cursor a(int n2) {
        synchronized (this) {
            Uri uri = n2 > 0 ? Uri.parse((String)("content://com.meizu.usagestats/event?limit=" + String.valueOf((int)n2))) : Uri.parse((String)"content://com.meizu.usagestats/event");
            if (!this.c) return this.b.getContentResolver().query(uri, null, null, null, "time ASC");
            return this.d.query(uri, null, null, null, "time ASC");
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public Cursor a(int n2, long l2) {
        synchronized (this) {
            Uri uri = n2 > 0 ? Uri.parse((String)("content://com.meizu.usagestats/event?limit=" + String.valueOf((int)n2))) : Uri.parse((String)"content://com.meizu.usagestats/event");
            if (this.c) {
                return this.d.query(uri, null, "time < ?", new String[]{String.valueOf((long)l2)}, "time ASC");
            }
            return this.b.getContentResolver().query(uri, null, "time < ?", new String[]{String.valueOf((long)l2)}, "time ASC");
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(UsageStatsProxy.Event event) {
        synchronized (this) {
            if (event != null) {
                Uri uri = Uri.parse((String)"content://com.meizu.usagestats/event");
                event = ajq.b(event);
                try {
                    if (this.c) {
                        this.d.insert(uri, (ContentValues)event);
                    } else {
                        this.b.getContentResolver().insert(uri, (ContentValues)event);
                    }
                }
                catch (SQLiteFullException var1_2) {
                    var1_2.printStackTrace();
                    this.b();
                }
                catch (Exception var1_3) {
                    var1_3.printStackTrace();
                }
            }
            return;
        }
    }

    public int b() {
        synchronized (this) {
            int n2;
            Uri uri = Uri.parse((String)"content://com.meizu.usagestats/clear_events");
            if (this.c) {
                n2 = this.d.delete(uri, null, null);
                return n2;
            }
            n2 = this.b.getContentResolver().delete(uri, null, null);
        }
    }
}

