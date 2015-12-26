/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.AlarmManager
 *  android.app.PendingIntent
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.database.Cursor
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Handler
 *  android.os.HandlerThread
 *  android.os.Looper
 *  android.os.Message
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.Map$Entry
 *  java.util.Random
 *  org.apache.http.HttpEntity
 *  org.apache.http.client.entity.UrlEncodedFormEntity
 *  org.apache.http.message.BasicNameValuePair
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.meizu.statsapp.UsageStatsProxy;
import com.meizu.statsapp.UsageStatusLog;
import com.meizu.statsapp.util.Utils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ajt {
    private static volatile ajt a;
    private static Object b;
    private Context c;
    private boolean d;
    private volatile boolean e;
    private ajq f;
    private HandlerThread g = new HandlerThread("StatsUploadThread");
    private b h;
    private SharedPreferences i;
    private Map<String, Map<String, List<UsageStatsProxy.Event>>> j = new HashMap();
    private int k;
    private ajs l;
    private boolean m;
    private int n;
    private long o;
    private long p;
    private boolean q = false;
    private volatile boolean r = false;
    private int s = 0;
    private long t = 0;
    private boolean u = false;

    static {
        b = new Object();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private ajt(Context context, boolean bl2, boolean bl3) {
        this.c = context;
        this.d = bl2;
        this.e = bl3;
        int n2 = this.d ? ajk.c : ajk.a;
        this.n = n2;
        this.f = ajq.a(this.c, this.d);
        this.i = this.c.getSharedPreferences("com.meizu.stats", 0);
        this.o = this.i.getLong("last_upload_time", 0);
        this.q = this.i.getBoolean("today_upload_flag", false);
        this.s = this.i.getInt("ratio", 1);
        this.t = this.i.getLong("online_flow_sum", 0);
        if (!this.d) {
            UsageStatusLog.initLog();
        }
        this.g.start();
        this.h = new b(this.g.getLooper());
        if (this.d) {
            this.l = new ajs();
            context = new IntentFilter();
            context.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            try {
                this.c.unregisterReceiver((BroadcastReceiver)this.l);
            }
            catch (Exception var5_5) {}
            this.c.registerReceiver((BroadcastReceiver)this.l, (IntentFilter)context);
        }
        this.h.post((Runnable)new aju(this));
        if (!this.d) {
            context = new IntentFilter();
            context.addAction("android.intent.action.BATTERY_CHANGED");
            context = this.c.registerReceiver(null, (IntentFilter)context);
            if (context != null && context.getIntExtra("plugged", 0) != 0) {
                this.r = true;
            }
            context = new IntentFilter();
            context.addAction("android.intent.action.TIME_SET");
            context.addAction("android.intent.action.ACTION_POWER_CONNECTED");
            context.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
            this.c.registerReceiver((BroadcastReceiver)new a(null), (IntentFilter)context);
            this.a(System.currentTimeMillis());
            this.b();
        }
    }

    static /* synthetic */ int a(ajt ajt2, int n2) {
        ajt2.s = n2;
        return n2;
    }

    static /* synthetic */ ajq a(ajt ajt2) {
        return ajt2.f;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static ajt a(Context context, boolean bl2, boolean bl3) {
        if (a == null) {
            Object object = b;
            synchronized (object) {
                if (a == null) {
                    a = new ajt(context, bl2, bl3);
                }
            }
        }
        return a;
    }

    private ajy a(byte[] arrby) {
        String string2 = Utils.bytesToHexString(Utils.getMD5(arrby));
        int n2 = (int)(System.currentTimeMillis() / 1000);
        String string3 = String.valueOf((int)(new Random().nextInt() + n2));
        String string4 = String.valueOf((int)n2);
        return new ajy(new ajz[]{new akb("nonce", string3), new akb("ts", string4), new akb("md5", string2), new akb("sign", Utils.bytesToHexString(Utils.getMD5(String.format((String)"key=OjUiuYe80AUYnbgBNT6&nonce=%s&ts=%s&md5=%s", (Object[])new Object[]{string3, string4, string2}).getBytes()))), new ajx("collect", "collect", arrby, null, "UTF-8")});
    }

    private String a(int n2) {
        switch (n2) {
            default: {
                return "";
            }
            case 1: {
                return "action_x";
            }
            case 2: {
                return "page";
            }
            case 3: 
        }
        return "log";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private JSONObject a(String string2) {
        if (this.j.size() < 1) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ver", (Object)"2.0");
            this.a(jSONObject, string2);
            string2 = this.e();
            if (string2 == null) return null;
            jSONObject.put("apps", (Object)string2);
            return jSONObject;
        }
        catch (JSONException var1_2) {
            var1_2.printStackTrace();
            return null;
        }
    }

    private void a(long l2) {
        PendingIntent pendingIntent = PendingIntent.getBroadcast((Context)this.c, (int)1, (Intent)new Intent("com.meizu.usagestats.check_upload"), (int)134217728);
        AlarmManager alarmManager = (AlarmManager)this.c.getSystemService("alarm");
        if (alarmManager == null) {
            return;
        }
        alarmManager.cancel(pendingIntent);
        alarmManager.set(1, 28800000 + l2, pendingIntent);
        this.p = l2;
    }

    static /* synthetic */ void a(ajt ajt2, long l2) {
        ajt2.a(l2);
    }

    private void a(JSONObject jSONObject, String string2) {
        jSONObject.put("device", (Object)Build.MODEL);
        jSONObject.put("os_version", (Object)Build.VERSION.RELEASE);
        jSONObject.put("imei", (Object)Utils.getIMEI(this.c));
        jSONObject.put("country", (Object)Utils.getCountry(this.c));
        jSONObject.put("operator", (Object)Utils.getOperater(this.c));
        jSONObject.put("root", Utils.isRoot(this.c));
        jSONObject.put("sn", (Object)Utils.getSN());
        jSONObject.put("flyme_uid", (Object)Utils.getFlymeUid(this.c));
        String string3 = string2;
        if (TextUtils.isEmpty((CharSequence)string2)) {
            string3 = Build.DISPLAY;
        }
        jSONObject.put("flyme_ver", (Object)string3);
        jSONObject.put("mac_address", (Object)Utils.getMACAddress(this.c));
        jSONObject.put("product_model", (Object)Utils.PRODUCT_MODEL);
        jSONObject.put("build_mask", (Object)Utils.BUILD_MASK);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static void a(boolean bl2) {
        if (a == null) return;
        if (bl2) {
            a.b(2);
            return;
        }
        a.b(3);
    }

    static /* synthetic */ boolean a(ajt ajt2, JSONObject jSONObject, boolean bl2) {
        return ajt2.a(jSONObject, bl2);
    }

    static /* synthetic */ boolean a(ajt ajt2, boolean bl2) {
        ajt2.m = bl2;
        return bl2;
    }

    private boolean a(JSONObject jSONObject) {
        boolean bl2;
        boolean bl3 = bl2 = false;
        if (jSONObject != null) {
            bl3 = bl2;
            if ((long)jSONObject.toString().getBytes().length < 10240) {
                bl3 = bl2;
                if (this.q) {
                    UsageStatusLog.d("UsageStatsUploader", "offline this data.size=" + jSONObject.toString().getBytes().length);
                    bl3 = true;
                }
            }
        }
        return bl3;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private boolean a(JSONObject object, boolean bl2) {
        if (object == null) {
            return false;
        }
        String string2 = object.toString();
        if (TextUtils.isEmpty((CharSequence)string2)) return false;
        if (!bl2) {
            if (this.d) {
                if (!this.b((JSONObject)object)) return false;
            }
            if (!this.d) {
                if (!this.a((JSONObject)object)) return false;
            }
        }
        UsageStatusLog.d("UsageStatsUploader", "uploadEvent data=" + object);
        object = this.a(string2.getBytes());
        if (object == null) return false;
        int n2 = 0;
        while (n2 < 3) {
            if (!this.g()) return false;
            if (this.d(ajv.a("https://uxip.meizu.com/api/upload", (HttpEntity)object))) {
                UsageStatusLog.d("UsageStatsUploader", "uploadEvents, upload successfully.");
                this.f();
                return true;
            }
            ++n2;
        }
        return false;
    }

    static /* synthetic */ int b(ajt ajt2, int n2) {
        ajt2.k = n2;
        return n2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private JSONArray b(String string2) {
        long l2 = 0;
        Map<String, List<UsageStatsProxy.Event>> map = this.j.get(string2);
        if (map == null) return null;
        if (map.size() < 1) {
            return null;
        }
        string2 = new JSONArray();
        map = map.entrySet().iterator();
        do {
            JSONObject jSONObject;
            Object object;
            Map.Entry entry;
            if (map.hasNext()) {
                entry = (Map.Entry)map.next();
                object = (List)entry.getValue();
                if (object == null || object.size() < 1) continue;
                jSONObject = new JSONObject();
                jSONObject.put("sid", entry.getKey());
                entry = new JSONArray();
                object = object.iterator();
            } else {
                if (string2.length() > 0) {
                    return string2;
                }
                return null;
            }
            while (object.hasNext()) {
                UsageStatsProxy.Event event = (UsageStatsProxy.Event)object.next();
                long l3 = l2;
                if (0 == l2) {
                    l3 = event.k();
                }
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("network", (Object)event.j());
                jSONObject2.put("type", (Object)this.a(event.b()));
                jSONObject2.put("name", (Object)event.a());
                if (2 == event.b()) {
                    if (event.g() != null) {
                        try {
                            jSONObject2.put("launch", ((JSONObject)event.g()).get("start_time"));
                            jSONObject2.put("terminate", ((JSONObject)event.g()).get("stop_time"));
                        }
                        catch (Exception var11_10) {
                            var11_10.printStackTrace();
                        }
                    }
                } else {
                    jSONObject2.put("time", event.c());
                    jSONObject2.put("page", (Object)event.f());
                    jSONObject2.put("value", event.g());
                }
                entry.put((Object)jSONObject2);
                l2 = l3;
            }
            jSONObject.put("channel_id", l2);
            jSONObject.put("events", (Object)entry);
            string2.put((Object)jSONObject);
        } while (true);
    }

    private void b(int n2) {
        this.h.sendEmptyMessage(n2);
    }

    private void b(long l2) {
        this.o = l2;
        this.i.edit().putLong("last_upload_time", this.o).apply();
    }

    static /* synthetic */ void b(ajt ajt2, long l2) {
        ajt2.b(l2);
    }

    static /* synthetic */ void b(ajt ajt2, boolean bl2) {
        ajt2.c(bl2);
    }

    static /* synthetic */ boolean b(ajt ajt2) {
        return ajt2.g();
    }

    private boolean b(JSONObject jSONObject) {
        boolean bl2;
        boolean bl3 = bl2 = false;
        if (jSONObject != null) {
            long l2 = this.t + (long)jSONObject.toString().getBytes().length;
            UsageStatusLog.d("UsageStatsUploader", "mOnlineDayFlowSum=" + this.t + " online this data.size=" + jSONObject.toString().getBytes().length);
            bl3 = bl2;
            if (l2 < 0x100000) {
                this.t = l2;
                this.i.edit().putLong("online_flow_sum", this.t).apply();
                bl3 = true;
            }
        }
        return bl3;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private String c(String string2) {
        PackageManager packageManager = this.c.getPackageManager();
        if (packageManager == null) {
            return "";
        }
        Object var2_4 = null;
        try {
            string2 = packageManager.getPackageInfo(string2, 0);
        }
        catch (PackageManager.NameNotFoundException var1_2) {
            var1_2.printStackTrace();
            string2 = var2_4;
        }
        if (string2 != null) {
            return string2.versionName;
        }
        return "";
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private JSONObject c() {
        var1_1 = null;
        var3_3 = this.f.a(this.n);
        if (var3_3 == null) {
            return null;
        }
        UsageStatusLog.d("UsageStatsUploader", "parcelEvents, count=" + var3_3.getCount());
        this.j.clear();
        do {
            block14 : {
                if (!var3_3.moveToNext()) break;
                var4_8 = ajq.a(var3_3);
                if (var4_8 == null) continue;
                if (var1_1 != null) break block14;
                var1_1 = var2_4 = var4_8.l();
            }
            var5_9 = var4_8.e();
            var2_4 = var4_8.d();
            if (!this.j.containsKey(var5_9)) {
                var6_10 = new HashMap();
                var7_11 = new ArrayList();
                var7_11.add(var4_8);
                var6_10.put(var2_4, var7_11);
                this.j.put((String)var5_9, (Map<String, List<UsageStatsProxy.Event>>)var6_10);
                continue;
            }
            if (!(var5_9 = this.j.get(var5_9)).containsKey(var2_4)) {
                var6_10 = new ArrayList();
                var6_10.add(var4_8);
                var5_9.put(var2_4, var6_10);
                continue;
            }
            ((List)var5_9.get(var2_4)).add(var4_8);
            continue;
            break;
        } while (true);
        var3_3.close();
        do {
            return this.a(var1_1);
            break;
        } while (true);
        catch (Exception var2_5) lbl-1000: // 2 sources:
        {
            try {
                var2_6.printStackTrace();
                return this.a(var1_1);
            }
            catch (Throwable var1_2) {
                throw var1_2;
            }
            finally {
                var3_3.close();
                return this.a(var1_1);
            }
        }
        catch (Exception var2_7) {
            ** GOTO lbl-1000
        }
    }

    private void c(boolean bl2) {
        this.q = bl2;
        this.i.edit().putBoolean("today_upload_flag", this.q).apply();
    }

    static /* synthetic */ boolean c(ajt ajt2) {
        return ajt2.m;
    }

    static /* synthetic */ boolean c(ajt ajt2, boolean bl2) {
        ajt2.u = bl2;
        return bl2;
    }

    static /* synthetic */ long d(ajt ajt2) {
        return ajt2.p;
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private JSONObject d() {
        var3_1 = this.f.a(this.n, System.currentTimeMillis() - 86400000);
        if (var3_1 == null) {
            return null;
        }
        UsageStatusLog.d("UsageStatsUploader", "parcelSimpleEvents, count=" + var3_1.getCount());
        this.j.clear();
        var1_2 = null;
        do {
            block14 : {
                if (!var3_1.moveToNext() || this.j.size() >= ajk.b) break;
                var4_7 = ajq.a(var3_1);
                if (var4_7 == null) continue;
                if (var1_2 != null) break block14;
                var1_2 = var2_4 = var4_7.l();
            }
            var5_8 = var4_7.e();
            var2_4 = var4_7.d();
            if (!this.j.containsKey(var5_8)) {
                var6_9 = new HashMap();
                var7_10 = new ArrayList();
                var7_10.add(var4_7);
                var6_9.put(var2_4, var7_10);
                this.j.put((String)var5_8, (Map<String, List<UsageStatsProxy.Event>>)var6_9);
                continue;
            }
            if (!(var5_8 = this.j.get(var5_8)).containsKey(var2_4)) continue;
            ((List)var5_8.get(var2_4)).add(var4_7);
            continue;
            break;
        } while (true);
        var3_1.close();
        var2_4 = var1_2;
lbl31: // 2 sources:
        do {
            var1_2 = this.a((String)var2_4);
            while (var1_2 != null) {
                if ((long)var1_2.toString().getBytes().length <= 10240) return var1_2;
                UsageStatusLog.d("UsageStatsUploader", "data.size=" + var1_2.toString().getBytes().length);
                var1_2 = this.j.keySet().iterator();
                if (var1_2 == null) return null;
                this.j.remove(var1_2.next());
                var1_2 = this.a((String)var2_4);
            }
            return var1_2;
            break;
        } while (true);
        catch (Exception var2_5) lbl-1000: // 2 sources:
        {
            try {
                var2_4.printStackTrace();
                var2_4 = var1_2;
                ** continue;
            }
            catch (Throwable var1_3) {
                throw var1_3;
            }
            finally {
                var3_1.close();
            }
        }
        catch (Exception var2_6) {
            ** GOTO lbl-1000
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private boolean d(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return false;
        }
        try {
            int n2 = new JSONObject(string2).getInt("code");
            if (200 != n2) return false;
            return true;
        }
        catch (JSONException var1_2) {
            var1_2.printStackTrace();
            return false;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private JSONArray e() {
        JSONArray jSONArray = new JSONArray();
        for (String string2 : this.j.keySet()) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("package", (Object)string2);
            String string3 = "com.meizu.uxip.log".equals((Object)string2) ? "1.0" : this.c(string2);
            jSONObject.put("version", (Object)string3);
            string3 = this.b(string2);
            if (string3 == null) continue;
            jSONObject.put("sessions", (Object)string3);
            jSONArray.put((Object)jSONObject);
        }
        if (jSONArray.length() > 0) {
            return jSONArray;
        }
        return null;
    }

    static /* synthetic */ void e(ajt ajt2) {
        ajt2.h();
    }

    private void f() {
        Iterator<Map.Entry<String, Map<String, List<UsageStatsProxy.Event>>>> iterator = this.j.entrySet().iterator();
        while (iterator.hasNext()) {
            for (Map.Entry entry : ((Map)iterator.next().getValue()).entrySet()) {
                this.f.a((Collection)entry.getValue());
            }
        }
        if (this.d) {
            this.k = this.f.a();
            UsageStatusLog.d("UsageStatsUploader", "upload successful! update mEventCome=" + this.k);
        }
    }

    static /* synthetic */ Context g(ajt ajt2) {
        return ajt2.c;
    }

    private boolean g() {
        if (Utils.isNetworkWorking(this.c)) {
            return true;
        }
        return false;
    }

    static /* synthetic */ JSONObject h(ajt ajt2) {
        return ajt2.c();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void h() {
        if (!this.d) return;
        {
            long l2 = System.currentTimeMillis();
            long l3 = this.i.getLong("online_last_upload_time", 0);
            if (0 == l3) {
                this.i.edit().putLong("online_last_upload_time", l2).apply();
                return;
            } else {
                if (Math.abs((long)(l2 - l3)) < 86400000) return;
                {
                    this.i.edit().putLong("online_last_upload_time", l2).apply();
                    this.i();
                    return;
                }
            }
        }
    }

    private void i() {
        this.s = 1;
        this.t = 0;
        this.i.edit().putInt("ratio", this.s).apply();
        this.i.edit().putLong("online_flow_sum", this.t).apply();
    }

    static /* synthetic */ boolean i(ajt ajt2) {
        return ajt2.d;
    }

    static /* synthetic */ JSONObject j(ajt ajt2) {
        return ajt2.d();
    }

    static /* synthetic */ boolean k(ajt ajt2) {
        return ajt2.u;
    }

    static /* synthetic */ int l(ajt ajt2) {
        return ajt2.s;
    }

    static /* synthetic */ SharedPreferences m(ajt ajt2) {
        return ajt2.i;
    }

    static /* synthetic */ Map n(ajt ajt2) {
        return ajt2.j;
    }

    void a() {
        ++this.k;
        UsageStatusLog.d("UsageStatsUploader", "eventComein, mEventCome=" + this.k + " mRatio=" + this.s);
        if (this.k == ajk.f * this.s) {
            this.u = true;
            this.b();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    boolean a(UsageStatsProxy.Event object) {
        String string2;
        if (!this.e) return false;
        if (object == null) return false;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ver", (Object)"2.0");
            this.a(jSONObject, object.l());
            jSONObject.put("package", (Object)object.e());
            jSONObject.put("version", (Object)this.c(object.e()));
            jSONObject.put("sid", (Object)object.d());
            string2 = new JSONObject();
            string2.put("type", (Object)this.a(object.b()));
            string2.put("name", (Object)object.a());
            string2.put("time", object.c());
            string2.put("page", (Object)object.f());
            object = object.h();
            if (!Utils.isEmpty((String)object)) {
                string2.put("value", (Object)new JSONObject((String)object));
            } else {
                string2.put("value", (Object)new JSONObject());
            }
            jSONObject.put("event", (Object)string2);
        }
        catch (JSONException var1_2) {
            var1_2.printStackTrace();
        }
        if (jSONObject.length() < 1) return false;
        object = jSONObject.toString().getBytes();
        if (object == null) {
            return false;
        }
        object = Utils.bytesToHexString(Utils.getMD5((byte[])object));
        int n2 = (int)(System.currentTimeMillis() / 1000);
        string2 = String.valueOf((int)(new Random().nextInt() + n2));
        String string3 = String.valueOf((int)n2);
        String string4 = Utils.bytesToHexString(Utils.getMD5(String.format((String)"key=OjUiuYe80AUYnbgBNT6&nonce=%s&ts=%s&md5=%s", (Object[])new Object[]{string2, string3, object}).getBytes()));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("nonce", string2));
        arrayList.add(new BasicNameValuePair("ts", string3));
        arrayList.add(new BasicNameValuePair("md5", (String)object));
        arrayList.add(new BasicNameValuePair("sign", string4));
        arrayList.add(new BasicNameValuePair("uxip_data", jSONObject.toString()));
        try {
            return this.d(ajv.a("https://uxip.meizu.com/api/report/realtime", (HttpEntity)new UrlEncodedFormEntity((List)arrayList)));
        }
        catch (UnsupportedEncodingException var1_3) {
            var1_3.printStackTrace();
            return false;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    void b() {
        if (this.d && !this.e) {
            UsageStatusLog.d("UsageStatsUploader", "checkUpload, mUpload=" + this.e);
            return;
        } else {
            long l2 = Math.abs((long)(System.currentTimeMillis() - this.o));
            UsageStatusLog.d("UsageStatsUploader", "checkUpload, mOnline=" + this.d + ", intervalTime=" + l2 / 3600000 + "h, mPowerConnecting=" + this.r + ", mLastUploadTime=" + this.o);
            if (l2 >= 86400000) {
                this.c(true);
            }
            if (!this.d && 0 != this.o && l2 < 86400000 && (!this.r || l2 < 28800000)) return;
            {
                this.h.removeMessages(1);
                this.h.sendEmptyMessage(1);
                return;
            }
        }
    }

    void b(boolean bl2) {
        if (this.e != bl2) {
            this.e = bl2;
            UsageStatusLog.d("UsageStatsUploader", "setUploaded, mUpload=" + this.e);
        }
        if (this.e) {
            this.b();
        }
    }

    class a
    extends BroadcastReceiver {
        private a() {
        }

        /* synthetic */ a(aju aju2) {
            this();
        }

        /*
         * Enabled aggressive block sorting
         */
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.ACTION_POWER_CONNECTED".equals((Object)intent.getAction())) {
                if (!ajt.this.r) {
                    ajt.this.r = true;
                }
                ajt.this.h.removeMessages(5);
                ajt.this.h.sendEmptyMessage(5);
                UsageStatusLog.d("UsageStatsUploader", "ACTION_POWER_CONNECTED, mPowerConnecting=" + ajt.this.r);
                return;
            } else {
                if ("android.intent.action.ACTION_POWER_DISCONNECTED".equals((Object)intent.getAction())) {
                    if (ajt.this.r) {
                        ajt.this.r = false;
                    }
                    UsageStatusLog.d("UsageStatsUploader", "ACTION_POWER_DISCONNECTED, mPowerConnecting=" + ajt.this.r);
                    return;
                }
                if (!"android.intent.action.TIME_SET".equals((Object)intent.getAction())) return;
                {
                    Log.d((String)"UsageStatsUploader", (String)"ACTION_TIME_CHANGED, ");
                    ajt.this.h.sendEmptyMessage(4);
                    return;
                }
            }
        }
    }

    class b
    extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        /*
         * Exception decompiling
         */
        public void handleMessage(Message var1_1) {
            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
            // org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:371)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:449)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
            // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
            // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
            // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
            // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
            // org.benf.cfr.reader.Main.doJar(Main.java:128)
            // org.benf.cfr.reader.Main.main(Main.java:178)
            throw new IllegalStateException("Decompilation failed");
        }
    }

}

