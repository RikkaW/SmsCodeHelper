/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.location.GpsSatellite
 *  android.location.GpsStatus
 *  android.location.GpsStatus$Listener
 *  android.location.GpsStatus$NmeaListener
 *  android.location.Location
 *  android.location.LocationListener
 *  android.location.LocationManager
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.os.Handler
 *  android.os.Looper
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.Locale
 *  java.util.Map$Entry
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class aie {
    private static float P;
    private static float Q;
    private static float R;
    private static float S;
    private static int T;
    private static int U;
    private static int V;
    private static int W;
    private static int X;
    private static int Y;
    private static int Z;
    protected static boolean a;
    protected static boolean b;
    private static int c;
    private static int d;
    private static int e;
    private static int f;
    private static int g;
    private static int h;
    private static Object i;
    private static aie j;
    private Thread A = null;
    private Looper B = null;
    private agq C = null;
    private Location D = null;
    private agp E = null;
    private Handler F = null;
    private agr G;
    private LocationListener H;
    private BroadcastReceiver I;
    private GpsStatus J;
    private int K;
    private int L;
    private HashMap M;
    private int N;
    private int O;
    private boolean k = false;
    private boolean l = false;
    private int m = -1;
    private int n = 0;
    private int o = 0;
    private int p = 10000;
    private long q = 0;
    private Context r;
    private LocationManager s;
    private agf t;
    private agt u;
    private ahd v;
    private agc w;
    private ahc x;
    private ags y;
    private afw z;

    static {
        a = false;
        b = true;
        c = 10;
        d = 2;
        e = 10;
        f = 10;
        g = 50;
        h = 200;
        i = new Object();
        P = 1.1f;
        Q = 2.2f;
        R = 2.3f;
        S = 3.8f;
        T = 3;
        U = 10;
        V = 2;
        W = 7;
        X = 20;
        Y = 70;
        Z = 120;
    }

    /*
     * Enabled aggressive block sorting
     */
    private aie(Context context) {
        this.G = new agr(this);
        this.H = new agl(this);
        this.I = new agm(this);
        this.J = null;
        this.K = 0;
        this.L = 0;
        this.M = null;
        this.N = 0;
        this.O = 0;
        this.r = context;
        this.t = agf.a(context);
        this.z = new afw();
        this.u = new agt(this.t);
        this.w = new agc(context);
        this.v = new ahd(this.w);
        this.x = new ahc(this.w);
        this.s = (LocationManager)this.r.getSystemService("location");
        this.y = ags.a(this.r);
        this.y.a(this.G);
        this.n();
        List list = this.s.getAllProviders();
        boolean bl2 = list != null && list.contains("gps") && list.contains("passive");
        this.l = bl2;
        ahe.a(context);
    }

    static /* synthetic */ int a(aie aie2, int n2) {
        aie2.K = 0;
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    static /* synthetic */ int a(aie aie2, aic aic2, int n2) {
        if (aie2.N >= U) {
            return 1;
        }
        if (aie2.N <= T) {
            return 4;
        }
        double d2 = aic2.c();
        if (d2 <= (double)P) return 1;
        if (d2 >= (double)Q) {
            return 4;
        }
        d2 = aic2.b();
        if (d2 <= (double)R) return 1;
        if (d2 >= (double)S) {
            return 4;
        }
        if (n2 >= W) return 1;
        if (n2 <= V) {
            return 4;
        }
        if (aie2.M == null) return 3;
        return aie2.a(aie2.M);
    }

    /*
     * Enabled aggressive block sorting
     */
    private int a(HashMap iterator) {
        if (this.K > 4) {
            int n2;
            Object object = new ArrayList();
            ArrayList arrayList = new ArrayList();
            iterator = iterator.entrySet().iterator();
            int n3 = 0;
            while (iterator.hasNext()) {
                double[] arrd;
                List list = (List)((Map.Entry)iterator.next()).getValue();
                if (list == null || (arrd = this.a(list)) == null) continue;
                object.add(arrd);
                n2 = n3 + 1;
                arrayList.add(n3);
                n3 = n2;
            }
            if (!object.isEmpty()) {
                iterator = (Iterator)new double[2];
                n2 = object.size();
                for (n3 = 0; n3 < n2; ++n3) {
                    double[] arrd = (double[])object.get(n3);
                    int n4 = (Integer)arrayList.get(n3);
                    arrd[0] = arrd[0] * (double)n4;
                    arrd[1] = arrd[1] * (double)n4;
                    iterator[0] = iterator[0] + arrd[0];
                    iterator[1] = iterator[1] + arrd[1];
                }
                iterator[0] = iterator[0] / (double)n2;
                iterator[1] = iterator[1] / (double)n2;
                Iterator iterator2 = iterator[0];
                Iterator iterator3 = iterator[1];
                double d2 = iterator3 == 0.0 ? (iterator2 > 0.0 ? 90.0 : (iterator2 < 0.0 ? 270.0 : 0.0)) : Math.toDegrees((double)Math.atan((double)(iterator2 / iterator3)));
                object = new double[2];
                object[0] = Math.sqrt((double)(iterator2 * iterator2 + iterator3 * iterator3));
                object[1] = d2;
                String.format((Locale)Locale.CHINA, (String)"%d,%d,%d,%d", (Object[])new Object[]{Math.round((double)(iterator[0] * 100.0)), Math.round((double)(iterator[1] * 100.0)), Math.round((double)(object[0] * 100.0)), Math.round((double)(object[1] * 100.0))});
                if (object[0] <= (double)Y) {
                    return 1;
                }
                if (object[0] >= (double)Z) {
                    return 4;
                }
            }
        }
        return 3;
    }

    static /* synthetic */ long a(aie aie2, long l2) {
        aie2.q = l2;
        return l2;
    }

    static /* synthetic */ agp a(aie aie2, agp agp2) {
        aie2.E = agp2;
        return agp2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static aie a(Context context) {
        if (j != null) return j;
        Object object = i;
        synchronized (object) {
            if (j == null) {
                j = new aie(context);
            }
            return j;
        }
    }

    static /* synthetic */ GpsStatus a(aie aie2, GpsStatus gpsStatus) {
        aie2.J = gpsStatus;
        return gpsStatus;
    }

    static /* synthetic */ Location a(aie aie2, Location location) {
        aie2.D = location;
        return location;
    }

    static /* synthetic */ Handler a(aie aie2, Handler handler) {
        aie2.F = handler;
        return handler;
    }

    static /* synthetic */ Looper a(aie aie2, Looper looper) {
        aie2.B = looper;
        return looper;
    }

    public static String a(String string2) {
        if (string2.equals((Object)"version")) {
            return "COL.14.1126r";
        }
        return null;
    }

    static /* synthetic */ HashMap a(aie aie2, HashMap hashMap) {
        aie2.M = hashMap;
        return hashMap;
    }

    static /* synthetic */ void a(aie aie2) {
        aie2.n();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    static /* synthetic */ void a(aie aie2, Location object, int n2, long l2) {
        List list;
        afw afw2;
        Long l3;
        List list2;
        boolean bl2;
        System.currentTimeMillis();
        boolean bl3 = aie2.u.a((Location)object);
        if (bl3) {
            aie2.u.b.b = new Location((Location)object);
        }
        if (bl2 = aie2.u.b((Location)object)) {
            aie2.u.a.b = new Location((Location)object);
        }
        int n3 = 0;
        if (n2 == 1) {
            object = aie2.D;
            bl3 = true;
            bl2 = true;
        } else if (n2 == 2) {
            object = aie2.D;
            bl3 = false;
            bl2 = true;
        }
        if (bl3) {
            n2 = 1;
            if (bl2) {
                n2 = 3;
            }
        } else {
            n2 = n3;
            if (bl2) {
                n2 = 2;
            }
        }
        try {
            afw2 = aie2.z;
            object = afw.a((Location)object, aie2.t, n2, (byte)aie2.O, l2, false);
        }
        catch (Exception var1_2) {
            object = null;
        }
        if (object != null && aie2.t != null) {
            list2 = aie2.t.n();
            afw2 = l3 = Long.valueOf((long)0);
            if (list2 != null) {
                afw2 = l3;
                if (list2.size() > 0) {
                    afw2 = (Long)list2.get(0);
                }
            }
            object = object.a();
            aie2.v.a(afw2.longValue(), (byte[])object);
        }
        if (aie2.r == null) return;
        if (aie2.z == null) {
            return;
        }
        list2 = aie2.r.getSharedPreferences("app_pref", 0);
        if (list2.getString("get_sensor", "").equals((Object)"true")) return;
        try {
            object = aie2.z;
            object = afw.a(null, aie2.t, n2, (byte)aie2.O, l2, true);
            if (object == null) return;
            if (aie2.t == null) return;
            list = aie2.t.n();
        }
        catch (Exception var1_3) {
            return;
        }
        afw2 = l3 = Long.valueOf((long)0);
        if (list != null) {
            afw2 = l3;
            if (list.size() > 0) {
                afw2 = (Long)list.get(0);
            }
        }
        object = object.a();
        aie2.v.a(afw2.longValue(), (byte[])object);
        list2.edit().putString("get_sensor", "true").commit();
    }

    static /* synthetic */ void a(aie aie2, String string2) {
        ahe.a("collector");
    }

    private double[] a(List list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        double[] arrd = new double[2];
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object object = (GpsSatellite)iterator.next();
            if (object == null) continue;
            double d2 = 90.0f - object.getElevation();
            double d3 = object.getAzimuth();
            object = new double[2];
            object[0] = Math.sin((double)Math.toRadians((double)d3)) * d2;
            object[1] = d2 * Math.cos((double)Math.toRadians((double)d3));
            arrd[0] = arrd[0] + object[0];
            arrd[1] = arrd[1] + object[1];
        }
        int n2 = list.size();
        arrd[0] = arrd[0] / (double)n2;
        arrd[1] = arrd[1] / (double)n2;
        return arrd;
    }

    static /* synthetic */ int b(aie aie2, int n2) {
        aie2.L = 0;
        return 0;
    }

    static /* synthetic */ int b(aie aie2, HashMap hashMap) {
        return aie2.a(hashMap);
    }

    static /* synthetic */ ags b(aie aie2) {
        return aie2.y;
    }

    static /* synthetic */ String b(aie aie2, String string2) {
        return string2;
    }

    static /* synthetic */ int c(aie aie2, int n2) {
        aie2.m = n2;
        return n2;
    }

    static /* synthetic */ boolean c(aie aie2) {
        return aie2.k;
    }

    static /* synthetic */ int d(aie aie2, int n2) {
        aie2.N = n2;
        return n2;
    }

    static /* synthetic */ agp d(aie aie2) {
        return aie2.E;
    }

    static /* synthetic */ int e(aie aie2, int n2) {
        aie2.O = n2;
        return n2;
    }

    static /* synthetic */ LocationManager e(aie aie2) {
        return aie2.s;
    }

    static /* synthetic */ LocationListener f(aie aie2) {
        return aie2.H;
    }

    static /* synthetic */ int g() {
        return e;
    }

    static /* synthetic */ Location g(aie aie2) {
        return aie2.D;
    }

    static /* synthetic */ int h() {
        return h;
    }

    static /* synthetic */ int h(aie aie2) {
        return aie2.p;
    }

    static /* synthetic */ int i() {
        return d;
    }

    static /* synthetic */ GpsStatus i(aie aie2) {
        return aie2.J;
    }

    static /* synthetic */ int j() {
        return g;
    }

    static /* synthetic */ int j(aie aie2) {
        int n2 = aie2.L;
        aie2.L = n2 + 1;
        return n2;
    }

    static /* synthetic */ int k() {
        return f;
    }

    static /* synthetic */ int k(aie aie2) {
        return aie2.m;
    }

    static /* synthetic */ int l() {
        return c;
    }

    static /* synthetic */ agf l(aie aie2) {
        return aie2.t;
    }

    static /* synthetic */ int m() {
        return X;
    }

    static /* synthetic */ HashMap m(aie aie2) {
        return aie2.M;
    }

    static /* synthetic */ Handler n(aie aie2) {
        return aie2.F;
    }

    private void n() {
        this.n = this.y.b() * 1000;
        this.o = this.y.c();
        agt agt2 = this.u;
        int n2 = this.n;
        n2 = this.o;
        agt.a();
    }

    static /* synthetic */ int o(aie aie2) {
        return aie2.L;
    }

    static /* synthetic */ int p(aie aie2) {
        return aie2.O;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a() {
        ahb.a = true;
        if (!this.l || this.t == null || a) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter("android.location.GPS_ENABLED_CHANGE");
        intentFilter.addAction("android.location.GPS_FIX_CHANGE");
        b = true;
        this.r.registerReceiver(this.I, intentFilter);
        this.s.removeUpdates(this.H);
        if (this.B != null) {
            this.B.quit();
            this.B = null;
        }
        if (this.A != null) {
            this.A.interrupt();
            this.A = null;
        }
        this.A = new agn(this, "");
        this.A.start();
        this.t.a();
        a = true;
    }

    public void a(int n2) {
        if (n2 != 256 && n2 != 8736 && n2 != 768) {
            throw new RuntimeException("invalid Size! must be COLLECTOR_SMALL_SIZE or COLLECTOR_BIG_SIZE or COLLECTOR_MEDIUM_SIZE");
        }
        this.w.a(n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(agb agb2, String string2) {
        boolean bl2 = this.y.a(string2);
        if (agb2 != null) {
            Object object;
            byte[] arrby = agb2.a();
            if (bl2 && arrby != null && (object = ((ConnectivityManager)this.r.getSystemService("connectivity")).getActiveNetworkInfo()) != null && object.isConnected()) {
                if (object.getType() == 1) {
                    object = this.y;
                    int n2 = this.y.e();
                    object.a(arrby.length + n2);
                } else {
                    object = this.y;
                    int n3 = this.y.f();
                    object.b(arrby.length + n3);
                }
            }
            agb2.a(bl2);
            this.x.a(agb2);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void b() {
        ahb.a = false;
        if (!this.l || this.t == null || !a) {
            return;
        }
        if (this.I != null) {
            try {
                this.r.unregisterReceiver(this.I);
            }
            catch (Exception var1_1) {}
        }
        if (this.t != null) {
            this.t.w();
        }
        this.s.removeGpsStatusListener((GpsStatus.Listener)this.E);
        this.s.removeNmeaListener((GpsStatus.NmeaListener)this.E);
        this.E = null;
        this.s.removeUpdates(this.H);
        if (this.B != null) {
            this.B.quit();
            this.B = null;
        }
        if (this.A != null) {
            this.A.interrupt();
            this.A = null;
        }
        if (this.C != null) {
            this.k = false;
            this.C.interrupt();
            this.C = null;
        }
        this.t.b();
        a = false;
    }

    public void c() {
        if (!this.l) {
            return;
        }
        this.b();
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public agb d() {
        if (this.x == null) {
            return null;
        }
        this.e();
        if (!this.y.a()) return null;
        return this.x.a(this.y.d());
    }

    public boolean e() {
        boolean bl2;
        boolean bl3 = bl2 = false;
        if (this.t != null) {
            List list = this.t.n();
            bl3 = bl2;
            if (list != null) {
                bl3 = bl2;
                if (list.size() > 0) {
                    bl3 = this.w.b((Long)list.get(0));
                }
            }
        }
        return bl3;
    }

    public int f() {
        if (this.x != null) {
            return this.x.a();
        }
        return 0;
    }
}

