/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.content.pm.ApplicationInfo
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.hardware.Sensor
 *  android.hardware.SensorManager
 *  android.location.GpsStatus
 *  android.location.GpsStatus$NmeaListener
 *  android.location.LocationManager
 *  android.net.wifi.ScanResult
 *  android.net.wifi.WifiInfo
 *  android.net.wifi.WifiManager
 *  android.os.Build
 *  android.os.Looper
 *  android.provider.Settings
 *  android.provider.Settings$Secure
 *  android.provider.Settings$System
 *  android.telephony.CellLocation
 *  android.telephony.NeighboringCellInfo
 *  android.telephony.PhoneStateListener
 *  android.telephony.TelephonyManager
 *  android.telephony.cdma.CdmaCellLocation
 *  android.telephony.gsm.GsmCellLocation
 *  android.text.TextUtils
 *  java.lang.Boolean
 *  java.lang.Character
 *  java.lang.Class
 *  java.lang.ClassLoader
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.lang.reflect.Method
 *  java.util.ArrayList
 *  java.util.Collections
 *  java.util.Comparator
 *  java.util.HashMap
 *  java.util.Timer
 */
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.GpsStatus;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TreeMap;

public final class agf {
    private static int D;
    private static agf u;
    private Timer A;
    private Thread B;
    private Looper C;
    private Context a;
    private TelephonyManager b;
    private LocationManager c;
    private WifiManager d;
    private SensorManager e;
    private String f;
    private String g;
    private String h;
    private boolean i;
    private int j;
    private boolean k;
    private long l;
    private String m;
    private String n;
    private int o;
    private int p;
    private int q;
    private String r;
    private long s;
    private long t;
    private agh v;
    private agi w;
    private CellLocation x;
    private agj y;
    private List z;

    static {
        u = null;
        D = 10000;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private agf(Context context) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = false;
        this.j = 0;
        this.k = false;
        this.l = -1;
        this.m = "";
        this.n = "";
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = "";
        this.s = 0;
        this.t = 0;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = new ArrayList();
        this.A = null;
        this.B = null;
        this.C = null;
        if (context == null) {
            return;
        }
        this.a = context;
        this.f = Build.MODEL;
        this.b = (TelephonyManager)context.getSystemService("phone");
        this.c = (LocationManager)context.getSystemService("location");
        this.d = (WifiManager)context.getSystemService("wifi");
        this.e = (SensorManager)context.getSystemService("sensor");
        if (this.b == null) return;
        if (this.d == null) return;
        try {
            this.g = this.b.getDeviceId();
        }
        catch (Exception var3_2) {}
        this.h = this.b.getSubscriberId();
        if (this.d.getConnectionInfo() != null) {
            this.n = this.d.getConnectionInfo().getMacAddress();
            if (this.n != null && this.n.length() > 0) {
                this.n = this.n.replace((CharSequence)":", (CharSequence)"");
            }
        }
        String[] arrstring = agf.b(this.b);
        this.o = Integer.parseInt((String)arrstring[0]);
        this.p = Integer.parseInt((String)arrstring[1]);
        this.q = this.b.getNetworkType();
        this.r = context.getPackageName();
        boolean bl2 = this.b.getPhoneType() == 2;
        this.i = bl2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void A() {
        if (this.d == null) {
            return;
        }
        try {
            if (!ahb.a) return;
            this.d.startScan();
            return;
        }
        catch (Exception var1_1) {
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private CellLocation B() {
        if (this.b == null) {
            return null;
        }
        try {
            return agf.b((List)agc.a((Object)this.b, "getAllCellInfo", new Object[0]));
        }
        catch (NoSuchMethodException var1_2) {
            return null;
        }
        catch (Exception var1_3) {
            return null;
        }
    }

    static /* synthetic */ int a(agf agf2, int n2) {
        agf2.o = n2;
        return n2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static int a(CellLocation cellLocation, Context context) {
        if (Settings.System.getInt((ContentResolver)context.getContentResolver(), (String)"airplane_mode_on", (int)0) == 1 || cellLocation == null) {
            return 9;
        }
        if (cellLocation instanceof GsmCellLocation) {
            return 1;
        }
        try {
            Class.forName((String)"android.telephony.cdma.CdmaCellLocation");
            return 2;
        }
        catch (Exception var0_1) {
            return 9;
        }
    }

    static /* synthetic */ long a(agf agf2, long l2) {
        agf2.s = l2;
        return l2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected static agf a(Context context) {
        if (u != null || !agf.c(context)) return u;
        LocationManager locationManager = (LocationManager)context.getSystemService("location");
        if (locationManager == null) return u;
        for (String string2 : locationManager.getAllProviders()) {
            if (!string2.equals((Object)"passive") && !string2.equals((Object)"gps")) continue;
            boolean bl2 = true;
            if (!bl2) return u;
            u = new agf(context);
            return u;
        }
        return u;
    }

    static /* synthetic */ agh a(agf agf2) {
        return agf2.v;
    }

    static /* synthetic */ agh a(agf agf2, agh agh2) {
        agf2.v = agh2;
        return agh2;
    }

    static /* synthetic */ agi a(agf agf2, agi agi2) {
        agf2.w = agi2;
        return agi2;
    }

    static /* synthetic */ Looper a(agf agf2, Looper looper) {
        agf2.C = looper;
        return looper;
    }

    static /* synthetic */ CellLocation a(agf agf2, CellLocation cellLocation) {
        agf2.x = cellLocation;
        return cellLocation;
    }

    static /* synthetic */ String a(agf agf2, String string2) {
        agf2.m = string2;
        return string2;
    }

    static /* synthetic */ Timer a(agf agf2, Timer timer) {
        agf2.A = timer;
        return timer;
    }

    static /* synthetic */ void a(agf agf2, GpsStatus.NmeaListener nmeaListener) {
        if (agf2.c == null || nmeaListener == null) {
            return;
        }
        agf2.c.addNmeaListener(nmeaListener);
    }

    static /* synthetic */ void a(agf agf2, PhoneStateListener phoneStateListener) {
        if (agf2.b != null) {
            agf2.b.listen(phoneStateListener, 273);
        }
    }

    private void a(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver == null || this.a == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        this.a.registerReceiver(broadcastReceiver, intentFilter);
    }

    private static void a(List list) {
        Object object;
        if (list == null || list.size() <= 0) {
            return;
        }
        HashMap hashMap = new HashMap();
        for (int i2 = 0; i2 < list.size(); ++i2) {
            object = (ScanResult)list.get(i2);
            if (object.SSID == null) {
                object.SSID = "null";
            }
            hashMap.put((Object)object.level, object);
        }
        object = new TreeMap(Collections.reverseOrder());
        object.putAll(hashMap);
        list.clear();
        Iterator iterator = object.keySet().iterator();
        while (iterator.hasNext()) {
            list.add(object.get((Object)((Integer)iterator.next())));
        }
        hashMap.clear();
        object.clear();
    }

    static /* synthetic */ boolean a(agf agf2, boolean bl2) {
        agf2.k = bl2;
        return bl2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean a(CellLocation cellLocation) {
        if (cellLocation == null) {
            return false;
        }
        switch (agf.a(cellLocation, this.a)) {
            default: {
                return true;
            }
            case 1: {
                if ((cellLocation = (GsmCellLocation)cellLocation).getLac() == -1) return false;
                if (cellLocation.getLac() == 0) return false;
                if (cellLocation.getLac() > 65535) return false;
                if (cellLocation.getCid() == -1) return false;
                if (cellLocation.getCid() == 0) return false;
                if (cellLocation.getCid() == 65535) return false;
                if (cellLocation.getCid() < 268435455) return true;
                return false;
            }
            case 2: 
        }
        try {
            if (agc.b((Object)cellLocation, "getSystemId", new Object[0]) <= 0) return false;
            if (agc.b((Object)cellLocation, "getNetworkId", new Object[0]) < 0) return false;
            int n2 = agc.b((Object)cellLocation, "getBaseStationId", new Object[0]);
            if (n2 >= 0) return true;
            return false;
        }
        catch (Exception var1_2) {
            return true;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean a(Object object) {
        Method method = WifiManager.class.getDeclaredMethod("isScanAlwaysAvailable", null);
        if (method == null) return false;
        try {
            return (Boolean)method.invoke(object, null);
        }
        catch (Exception var0_1) {
            // empty catch block
        }
        return false;
    }

    static /* synthetic */ String[] a(TelephonyManager telephonyManager) {
        return agf.b(telephonyManager);
    }

    static /* synthetic */ int b(agf agf2, int n2) {
        agf2.p = n2;
        return n2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static int b(Object object) {
        Method method = Sensor.class.getDeclaredMethod("getMinDelay", null);
        if (method == null) return 0;
        try {
            return (Integer)method.invoke(object, null);
        }
        catch (Exception var0_1) {
            // empty catch block
        }
        return 0;
    }

    static /* synthetic */ long b(agf agf2, long l2) {
        agf2.t = l2;
        return l2;
    }

    static /* synthetic */ agi b(agf agf2) {
        return agf2.w;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    private static CellLocation b(List var0) {
        if (var0 == null || var0.isEmpty()) {
            var0 = null;
            do {
                return var0;
                break;
            } while (true);
        }
        var11_1 = ClassLoader.getSystemClassLoader();
        var8_2 = null;
        var1_4 = 0;
        var9_5 = null;
        block14 : for (var2_3 = 0; var2_3 < var0.size(); ++var2_3) {
            block23 : {
                block22 : {
                    block21 : {
                        var12_14 = var0.get(var2_3);
                        if (var12_14 == null) ** GOTO lbl-1000
                        var14_16 = var11_1.loadClass("android.telephony.CellInfoGsm");
                        var15_17 = var11_1.loadClass("android.telephony.CellInfoWcdma");
                        var16_18 = var11_1.loadClass("android.telephony.CellInfoLte");
                        var13_15 = var11_1.loadClass("android.telephony.CellInfoCdma");
                        var7_9 = var14_16.isInstance(var12_14);
                        if (!var7_9) break block21;
                        var1_4 = 1;
lbl20: // 5 sources:
                        do {
                            if (var1_4 <= 0) ** GOTO lbl-1000
                            var10_10 = null;
                            if (var1_4 != 1) break block22;
                            var10_10 = var14_16.cast(var12_14);
lbl26: // 5 sources:
                            ** while ((var12_14 = agc.a((Object)var10_10, (String)"getCellIdentity", (Object[])new Object[0])) == null)
lbl-1000: // 7 sources:
                            {
                                continue block14;
lbl29: // 1 sources:
                                ** GOTO lbl55
                            }
                            break;
                        } while (true);
                    }
                    if (!var15_17.isInstance(var12_14)) ** GOTO lbl35
                    var1_4 = 2;
                    ** GOTO lbl20
lbl35: // 1 sources:
                    if (!var16_18.isInstance(var12_14)) ** GOTO lbl38
                    var1_4 = 3;
                    ** GOTO lbl20
lbl38: // 1 sources:
                    var7_9 = var13_15.isInstance(var12_14);
                    if (!var7_9) ** GOTO lbl42
                    var1_4 = 4;
                    ** GOTO lbl20
lbl42: // 1 sources:
                    var1_4 = 0;
                    ** continue;
                }
                if (var1_4 != 2) ** GOTO lbl49
                var10_10 = var15_17.cast(var12_14);
                ** GOTO lbl26
lbl49: // 1 sources:
                if (var1_4 != 3) ** GOTO lbl52
                var10_10 = var16_18.cast(var12_14);
                ** GOTO lbl26
lbl52: // 1 sources:
                if (var1_4 != 4) ** GOTO lbl26
                var10_10 = var13_15.cast(var12_14);
                ** GOTO lbl26
lbl55: // 1 sources:
                if (var1_4 != 4) break block23;
                var10_10 = new CdmaCellLocation();
                var3_7 = agc.b(var12_14, "getSystemId", new Object[0]);
                var4_8 = agc.b(var12_14, "getNetworkId", new Object[0]);
                var5_19 = agc.b(var12_14, "getBasestationId", new Object[0]);
                var6_20 = agc.b(var12_14, "getLongitude", new Object[0]);
                var10_10.setCellLocationData(var5_19, agc.b(var12_14, "getLatitude", new Object[0]), var6_20, var3_7, var4_8);
                var0 = var10_10;
lbl64: // 4 sources:
                do {
                    if (var1_4 == 4) ** continue;
                    return var8_2;
                    break;
                } while (true);
            }
            if (var1_4 == 3) {
                var3_7 = agc.b(var12_14, "getTac", new Object[0]);
                var4_8 = agc.b(var12_14, "getCi", new Object[0]);
                var8_2 = var10_10 = new GsmCellLocation();
                var10_10.setLacAndCid(var3_7, var4_8);
                var0 = var9_5;
                var8_2 = var10_10;
            }
            try {
                var3_7 = agc.b(var12_14, "getLac", new Object[0]);
                var4_8 = agc.b(var12_14, "getCid", new Object[0]);
                var8_2 = var10_10 = new GsmCellLocation();
            }
            catch (Exception var10_12) {
                ** GOTO lbl-1000
            }
            try {
                var10_10.setLacAndCid(var3_7, var4_8);
                var0 = var9_5;
                var8_2 = var10_10;
            }
            catch (Exception var10_13) {
                ** continue;
            }
            catch (Exception var10_11) {
                ** GOTO lbl-1000
            }
            catch (Exception var9_6) {
                var9_5 = var10_10;
                ** GOTO lbl-1000
            }
        }
        var0 = var9_5;
        ** while (true)
    }

    private void b(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver == null || this.a == null) {
            return;
        }
        try {
            this.a.unregisterReceiver(broadcastReceiver);
            return;
        }
        catch (Exception var1_2) {
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected static boolean b(Context object) {
        if (object == null) {
            return true;
        }
        if (Settings.Secure.getString((ContentResolver)object.getContentResolver(), (String)"mock_location").equals((Object)"0")) return false;
        PackageManager packageManager = object.getPackageManager();
        Object object2 = packageManager.getInstalledApplications(128);
        object = object.getPackageName();
        object2 = object2.iterator();
        boolean bl2 = false;
        block4 : do {
            int n2;
            int n3;
            boolean bl3 = bl2;
            if (!object2.hasNext()) return bl3;
            ApplicationInfo applicationInfo = (ApplicationInfo)object2.next();
            bl3 = bl2;
            if (bl2) return bl3;
            String[] arrstring = packageManager.getPackageInfo((String)applicationInfo.packageName, (int)4096).requestedPermissions;
            if (arrstring == null) continue;
            try {
                n2 = arrstring.length;
                n3 = 0;
            }
            catch (Exception var7_8) {
            }
            do {
                block7 : {
                    if (n3 >= n2) continue block4;
                    if (!arrstring[n3].equals((Object)"android.permission.ACCESS_MOCK_LOCATION")) break block7;
                    bl3 = applicationInfo.packageName.equals(object);
                    if (bl3) continue block4;
                    bl2 = true;
                }
                ++n3;
            } while (true);
        } while (true);
    }

    private static String[] b(TelephonyManager arrstring) {
        int n2 = 0;
        String string2 = null;
        if (arrstring != null) {
            string2 = arrstring.getNetworkOperator();
        }
        arrstring = new String[]{"0", "0"};
        if (TextUtils.isDigitsOnly((CharSequence)string2) && string2.length() > 4) {
            arrstring[0] = string2.substring(0, 3);
            char[] arrc = string2.substring(3).toCharArray();
            while (n2 < arrc.length && Character.isDigit((char)arrc[n2])) {
                ++n2;
            }
            arrstring[1] = string2.substring(3, n2 + 3);
        }
        return arrstring;
    }

    static /* synthetic */ int c(agf agf2, int n2) {
        agf2.j = n2;
        return n2;
    }

    static /* synthetic */ long c(agf agf2, long l2) {
        agf2.l = l2;
        return l2;
    }

    static /* synthetic */ WifiManager c(agf agf2) {
        return agf2.d;
    }

    private static boolean c(Context arrstring) {
        PackageManager packageManager = arrstring.getPackageManager();
        try {
            arrstring = packageManager.getPackageInfo(arrstring.getPackageName(), 4096);
            arrstring = arrstring.requestedPermissions;
        }
        catch (PackageManager.NameNotFoundException var0_1) {
            return false;
        }
        for (int i2 = 0; i2 < ahb.b.length; ++i2) {
            if (ahb.a(arrstring, ahb.b[i2])) continue;
            return false;
        }
        return true;
    }

    static /* synthetic */ Timer d(agf agf2) {
        return agf2.A;
    }

    static /* synthetic */ List e(agf agf2) {
        return agf2.z;
    }

    static /* synthetic */ TelephonyManager f(agf agf2) {
        return agf2.b;
    }

    static /* synthetic */ boolean g(agf agf2) {
        return agf2.i;
    }

    static /* synthetic */ int h(agf agf2) {
        return agf2.j;
    }

    static /* synthetic */ int z() {
        return D;
    }

    protected final String a(int n2) {
        new ArrayList();
        if (this.e == null) {
            return "null";
        }
        List list = this.e.getSensorList(-1);
        if (list == null || list.get(n2) == null || ((Sensor)list.get(n2)).getName() == null || ((Sensor)list.get(n2)).getName().length() <= 0) {
            return "null";
        }
        return ((Sensor)list.get(n2)).getName();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected final List a(float f2) {
        ArrayList arrayList = new ArrayList();
        long l2 = System.currentTimeMillis();
        float f3 = f2;
        if (Math.abs((float)f2) <= 1.0f) {
            f3 = 1.0f;
        }
        if (!this.c()) return arrayList;
        CellLocation cellLocation = (CellLocation)this.j().get(1);
        if (cellLocation == null || !(cellLocation instanceof GsmCellLocation)) return arrayList;
        arrayList.add(((GsmCellLocation)cellLocation).getLac());
        arrayList.add(((GsmCellLocation)cellLocation).getCid());
        if ((double)(l2 - (Long)this.j().get(0)) <= 50000.0 / (double)f3) {
            arrayList.add(1);
            return arrayList;
        }
        arrayList.add(0);
        return arrayList;
    }

    protected final void a() {
        this.b();
        if (this.C != null) {
            this.C.quit();
            this.C = null;
        }
        if (this.B != null) {
            this.B.interrupt();
            this.B = null;
        }
        this.B = new agg(this, "");
        this.B.start();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected final double b(int n2) {
        List list;
        new ArrayList();
        if (this.e == null || (list = this.e.getSensorList(-1)) == null || list.get(n2) == null) {
            return 0.0;
        }
        return ((Sensor)list.get(n2)).getMaximumRange();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected final List b(float f2) {
        ArrayList arrayList = new ArrayList();
        long l2 = System.currentTimeMillis();
        float f3 = f2;
        if (Math.abs((float)f2) <= 1.0f) {
            f3 = 1.0f;
        }
        if (!this.c()) return arrayList;
        CellLocation cellLocation = (CellLocation)this.j().get(1);
        if (cellLocation == null || !(cellLocation instanceof CdmaCellLocation)) return arrayList;
        cellLocation = (CdmaCellLocation)cellLocation;
        arrayList.add(cellLocation.getSystemId());
        arrayList.add(cellLocation.getNetworkId());
        arrayList.add(cellLocation.getBaseStationId());
        arrayList.add(cellLocation.getBaseStationLongitude());
        arrayList.add(cellLocation.getBaseStationLatitude());
        if ((double)(l2 - (Long)this.j().get(0)) <= 50000.0 / (double)f3) {
            arrayList.add(1);
            return arrayList;
        }
        arrayList.add(0);
        return arrayList;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected final void b() {
        Object object;
        if (this.v != null) {
            object = this.v;
            if (this.b != null) {
                this.b.listen((PhoneStateListener)object, 0);
            }
            this.v = null;
        }
        if (this.w != null) {
            object = this.w;
            if (this.c != null && object != null) {
                this.c.removeNmeaListener((GpsStatus.NmeaListener)object);
            }
            this.w = null;
        }
        if (this.A != null) {
            this.A.cancel();
            this.A = null;
        }
        if (this.C != null) {
            this.C.quit();
            this.C = null;
        }
        if (this.B != null) {
            this.B.interrupt();
            this.B = null;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected final int c(int n2) {
        List list;
        new ArrayList();
        if (this.e == null || (list = this.e.getSensorList(-1)) == null || list.get(n2) == null) {
            return 0;
        }
        return agf.b(list.get(n2));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected final boolean c() {
        CellLocation cellLocation = null;
        if (this.b != null && this.b.getSimState() == 5 && this.k) {
            return true;
        }
        if (this.b != null) {
            try {
                CellLocation cellLocation2;
                cellLocation = cellLocation2 = this.b.getCellLocation();
            }
            catch (Exception var2_3) {}
            if (cellLocation != null) {
                this.t = System.currentTimeMillis();
                this.x = cellLocation;
                return true;
            }
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected final int d(int n2) {
        List list;
        new ArrayList();
        if (this.e == null || (list = this.e.getSensorList(-1)) == null || list.get(n2) == null) {
            return 0;
        }
        return (int)((double)((Sensor)list.get(n2)).getPower() * 100.0);
    }

    protected final boolean d() {
        if (this.d != null && (this.d.isWifiEnabled() || agf.a((Object)this.d))) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected final double e(int n2) {
        List list;
        new ArrayList();
        if (this.e == null || (list = this.e.getSensorList(-1)) == null || list.get(n2) == null) {
            return 0.0;
        }
        return ((Sensor)list.get(n2)).getResolution();
    }

    protected final boolean e() {
        try {
            boolean bl2;
            if (this.c != null && (bl2 = this.c.isProviderEnabled("gps"))) {
                return true;
            }
        }
        catch (Exception var2_2) {
            // empty catch block
        }
        return false;
    }

    protected final byte f(int n2) {
        new ArrayList();
        if (this.e == null) {
            return 127;
        }
        List list = this.e.getSensorList(-1);
        if (list == null || list.get(n2) == null || ((Sensor)list.get(n2)).getType() > 127) {
            return 127;
        }
        return (byte)((Sensor)list.get(n2)).getType();
    }

    protected final String f() {
        if (this.f == null) {
            this.f = Build.MODEL;
        }
        if (this.f != null) {
            return this.f;
        }
        return "";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected final String g() {
        if (this.g == null && this.a != null) {
            this.b = (TelephonyManager)this.a.getSystemService("phone");
            if (this.b != null) {
                try {
                    this.g = this.b.getDeviceId();
                }
                catch (Exception var1_1) {}
            }
        }
        if (this.g != null) {
            return this.g;
        }
        return "";
    }

    protected final String g(int n2) {
        new ArrayList();
        if (this.e == null) {
            return "null";
        }
        List list = this.e.getSensorList(-1);
        if (list == null || list.get(n2) == null || ((Sensor)list.get(n2)).getVendor() == null || ((Sensor)list.get(n2)).getVendor().length() <= 0) {
            return "null";
        }
        return ((Sensor)list.get(n2)).getVendor();
    }

    protected final byte h(int n2) {
        new ArrayList();
        if (this.e == null) {
            return 127;
        }
        List list = this.e.getSensorList(-1);
        if (list == null || list.get(n2) == null || ((Sensor)list.get(n2)).getType() > 127) {
            return 127;
        }
        return (byte)((Sensor)list.get(n2)).getVersion();
    }

    protected final String h() {
        if (this.h == null && this.a != null) {
            this.b = (TelephonyManager)this.a.getSystemService("phone");
            if (this.b != null) {
                this.h = this.b.getSubscriberId();
            }
        }
        if (this.h != null) {
            return this.h;
        }
        return "";
    }

    protected final boolean i() {
        return this.i;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected final List j() {
        CellLocation cellLocation;
        if (Settings.System.getInt((ContentResolver)this.a.getContentResolver(), (String)"airplane_mode_on", (int)0) == 1) {
            return new ArrayList();
        }
        if (!this.c()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        if (!this.a(this.x) && this.a(cellLocation = this.B())) {
            this.t = System.currentTimeMillis();
        } else {
            cellLocation = this.x;
        }
        arrayList.add(this.t);
        arrayList.add(cellLocation);
        return arrayList;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected final List k() {
        int n2 = 0;
        ArrayList arrayList = new ArrayList();
        if (!this.d()) {
            return new ArrayList();
        }
        ArrayList arrayList2 = new ArrayList();
        synchronized (this) {
            if (System.currentTimeMillis() - this.s >= 3500) {
                return arrayList2;
            }
            int n3 = 1;
            if (n3 != 0) {
                arrayList2.add(this.s);
                for (n3 = n2; n3 < this.z.size(); ++n3) {
                    arrayList.add(this.z.get(n3));
                }
                arrayList2.add(arrayList);
            }
            return arrayList2;
        }
    }

    protected final byte l() {
        if (this.c()) {
            return (byte)this.j;
        }
        return -128;
    }

    protected final List m() {
        ArrayList arrayList = new ArrayList();
        if (this.b == null) {
            return arrayList;
        }
        if (!this.c()) {
            return arrayList;
        }
        if (this.b.getSimState() == 1) {
            return arrayList;
        }
        Iterator iterator = this.b.getNeighboringCellInfo().iterator();
        int n2 = 0;
        while (iterator.hasNext()) {
            NeighboringCellInfo neighboringCellInfo = (NeighboringCellInfo)iterator.next();
            if (n2 > 15) break;
            if (neighboringCellInfo.getLac() == 0 || neighboringCellInfo.getLac() == 65535 || neighboringCellInfo.getCid() == 65535 || neighboringCellInfo.getCid() == 268435455) continue;
            arrayList.add(neighboringCellInfo);
            ++n2;
        }
        return arrayList;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected final List n() {
        String string2;
        long l2;
        ArrayList arrayList = new ArrayList();
        if (this.e()) {
            l2 = this.l;
            string2 = this.m;
        } else {
            l2 = -1;
            string2 = "";
        }
        long l3 = l2;
        if (l2 <= 0) {
            l3 = System.currentTimeMillis() / 1000;
        }
        l2 = l3;
        if (l3 > Integer.MAX_VALUE) {
            l2 = l3 / 1000;
        }
        arrayList.add(l2);
        arrayList.add(string2);
        return arrayList;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected final long o() {
        long l2 = 0;
        long l3 = this.l;
        if (l3 > 0) {
            int n2 = String.valueOf((long)l3).length();
            do {
                l2 = l3;
                if (n2 == 13) break;
                l3 = n2 > 13 ? (l3 /= 10) : (l3 *= 10);
                n2 = String.valueOf((long)l3).length();
            } while (true);
        }
        return l2;
    }

    protected final String p() {
        if (this.n == null && this.a != null) {
            this.d = (WifiManager)this.a.getSystemService("wifi");
            if (this.d != null && this.d.getConnectionInfo() != null) {
                this.n = this.d.getConnectionInfo().getMacAddress();
                if (this.n != null && this.n.length() > 0) {
                    this.n = this.n.replace((CharSequence)":", (CharSequence)"");
                }
            }
        }
        if (this.n != null) {
            return this.n;
        }
        return "";
    }

    protected final int q() {
        return this.o;
    }

    protected final int r() {
        return this.p;
    }

    protected final int s() {
        return this.q;
    }

    protected final String t() {
        if (this.r == null && this.a != null) {
            this.r = this.a.getPackageName();
        }
        if (this.r != null) {
            return this.r;
        }
        return "";
    }

    protected final List u() {
        ArrayList arrayList = new ArrayList();
        if (this.d()) {
            List list = this.k();
            List list2 = (List)list.get(1);
            long l2 = (Long)list.get(0);
            agf.a(list2);
            arrayList.add(l2);
            if (list2 != null && list2.size() > 0) {
                for (int i2 = 0; i2 < list2.size(); ++i2) {
                    list = (ScanResult)list2.get(i2);
                    if (arrayList.size() - 1 >= 40) break;
                    if (list == null) continue;
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(list.BSSID.replace((CharSequence)":", (CharSequence)""));
                    arrayList2.add(list.level);
                    arrayList2.add(list.SSID);
                    arrayList.add(arrayList2);
                }
            }
        }
        return arrayList;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    protected final void v() {
        // MONITORENTER : this
        this.z.clear();
        // MONITOREXIT : this
        if (this.y != null) {
            this.b(this.y);
            this.y = null;
        }
        if (this.A != null) {
            this.A.cancel();
            this.A = null;
        }
        this.A = new Timer();
        this.y = new agj(this, 0);
        this.a(this.y);
        this.A();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    protected final void w() {
        // MONITORENTER : this
        this.z.clear();
        // MONITOREXIT : this
        if (this.y != null) {
            this.b(this.y);
            this.y = null;
        }
        if (this.A == null) return;
        this.A.cancel();
        this.A = null;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected final byte x() {
        List list;
        new ArrayList();
        if (this.e == null || (list = this.e.getSensorList(-1)) == null) {
            return 0;
        }
        return (byte)list.size();
    }

    protected final Context y() {
        return this.a;
    }
}

