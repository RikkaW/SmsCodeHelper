/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.net.wifi.ScanResult
 *  android.net.wifi.WifiInfo
 *  android.net.wifi.WifiManager
 *  android.os.Bundle
 *  android.os.SystemClock
 *  android.telephony.CellLocation
 *  android.telephony.NeighboringCellInfo
 *  android.telephony.PhoneStateListener
 *  android.telephony.TelephonyManager
 *  android.telephony.cdma.CdmaCellLocation
 *  android.telephony.gsm.GsmCellLocation
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.ClassLoader
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.Collections
 *  java.util.Comparator
 *  java.util.HashMap
 *  java.util.Map$Entry
 *  java.util.Timer
 *  org.json.JSONObject
 */
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Log;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.core.AMapLocException;
import com.amap.api.location.core.d;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import org.json.JSONObject;

public class agu
implements ahp {
    private long A;
    private long B;
    private ahq C;
    private int D;
    private String E;
    private aie F;
    private StringBuilder G;
    private long H;
    private ahg I;
    private CellLocation J;
    private boolean K;
    ahi a;
    int b;
    boolean c;
    a d;
    TimerTask e;
    Timer f;
    agb g;
    int h;
    private Context i = null;
    private int j = 9;
    private ConnectivityManager k = null;
    private WifiManager l = null;
    private TelephonyManager m = null;
    private List<ahh> n = new ArrayList();
    private List<ScanResult> o = new ArrayList();
    private Map<PendingIntent, List<aho>> p = new HashMap();
    private Map<PendingIntent, List<aho>> q = new HashMap();
    private PhoneStateListener r = null;
    private int s = -113;
    private b t;
    private WifiInfo u;
    private JSONObject v;
    private ahf w;
    private long x;
    private boolean y;
    private long z;

    public agu() {
        this.t = new b(null);
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = 0;
        this.y = false;
        this.z = 0;
        this.A = 0;
        this.B = 0;
        this.C = ahq.a();
        this.D = 0;
        this.E = "00:00:00:00:00:00";
        this.F = null;
        this.G = new StringBuilder();
        this.H = 0;
        this.b = -1;
        this.c = false;
        this.I = null;
        this.d = new a();
        this.J = null;
        this.K = false;
        this.h = 0;
    }

    private void A() {
        try {
            if (!this.c && com.amap.api.location.core.a.b() && this.a != null) {
                this.a.b();
                this.c = true;
            }
            return;
        }
        catch (Throwable var1_1) {
            this.c = true;
            return;
        }
    }

    static /* synthetic */ long a(agu agu2, long l2) {
        agu2.A = l2;
        return l2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private ahf a(byte[] object, boolean bl2) {
        Object object2;
        if (this.i == null) {
            return null;
        }
        object2 = new ahr();
        ahz.b();
        object = this.C.a((byte[])object, this.i, this.v);
        ahz.b();
        try {
            d.a((String)object);
        }
        catch (AMapLocException var1_2) {
            throw var1_2;
        }
        catch (Exception var4_5) {}
        if (!ahz.a((ahf)(object2 = object2.a((String)object)))) {
            throw new AMapLocException("\u672a\u77e5\u7684\u9519\u8bef");
        }
        object = object2;
        if (object2.t() == null) return object;
        return object2;
    }

    private ahh a(NeighboringCellInfo neighboringCellInfo) {
        if (ahz.c() < 5) {
            return null;
        }
        try {
            ahh ahh2 = new ahh();
            String[] arrstring = ahz.a(this.m);
            ahh2.a = arrstring[0];
            ahh2.b = arrstring[1];
            ahh2.c = neighboringCellInfo.getLac();
            ahh2.d = neighboringCellInfo.getCid();
            ahh2.j = ahz.a(neighboringCellInfo.getRssi());
            return ahh2;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return null;
        }
    }

    static /* synthetic */ CellLocation a(agu agu2, CellLocation cellLocation) {
        agu2.J = cellLocation;
        return cellLocation;
    }

    private String a(int n2, int n3, int n4) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("e", n2);
        jSONObject.put("d", n3);
        jSONObject.put("u", n4);
        return jSONObject.toString();
    }

    static /* synthetic */ List a(agu agu2, List list) {
        agu2.o = list;
        return list;
    }

    static /* synthetic */ void a(agu agu2, int n2) {
        agu2.b(n2);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void a(CellLocation cellLocation) {
        CellLocation cellLocation2;
        CellLocation cellLocation3 = cellLocation2 = null;
        if (!this.y) {
            cellLocation3 = cellLocation2;
            if (this.m != null) {
                cellLocation3 = cellLocation2;
                if (this.m != null) {
                    cellLocation3 = this.z();
                }
            }
            if (cellLocation3 != null) {
                cellLocation = cellLocation3;
            }
        }
        if (cellLocation == null) {
            return;
        }
        switch (ahz.a(cellLocation, this.i)) {
            default: {
                return;
            }
            case 1: {
                if (this.m == null) return;
                this.c(cellLocation);
                return;
            }
            case 2: 
        }
        this.d(cellLocation);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(List<ScanResult> list) {
        synchronized (this) {
            int n2;
            if (list != null && (n2 = list.size()) >= 1) {
                HashMap hashMap = new HashMap();
                n2 = 0;
                do {
                    Object object;
                    if (n2 < list.size()) {
                        object = list.get(n2);
                        if (list.size() <= 20 || this.a(object.level)) {
                            object.SSID = object.SSID != null ? object.SSID.replace((CharSequence)"*", (CharSequence)".") : "null";
                            hashMap.put((Object)(object.level * 30 + n2), object);
                        }
                    } else {
                        object = new TreeMap(Collections.reverseOrder());
                        object.putAll(hashMap);
                        list.clear();
                        Iterator iterator = object.entrySet().iterator();
                        while (iterator.hasNext()) {
                            list.add((ScanResult)iterator.next().getValue());
                            if (list.size() <= 29) continue;
                        }
                        hashMap.clear();
                        object.clear();
                        break;
                    }
                    ++n2;
                } while (true);
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean a(int n2) {
        int n3 = 20;
        try {
            n2 = WifiManager.calculateSignalLevel((int)n2, (int)20);
        }
        catch (ArithmeticException var3_3) {
            ahz.a(var3_3);
            n2 = n3;
        }
        if (n2 >= 1) {
            return true;
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean a(long l2) {
        long l3 = ahz.a();
        if (l3 - l2 >= 300) return false;
        l2 = 0;
        if (this.w != null) {
            l2 = l3 - this.w.h();
        }
        if (l2 <= 10000) return true;
        return false;
    }

    static /* synthetic */ boolean a(agu agu2) {
        return agu2.q();
    }

    static /* synthetic */ boolean a(agu agu2, boolean bl2) {
        agu2.y = bl2;
        return bl2;
    }

    private boolean a(ScanResult scanResult) {
        boolean bl2;
        boolean bl3 = bl2 = false;
        if (scanResult != null) {
            block5 : {
                try {
                    if (!TextUtils.isEmpty((CharSequence)scanResult.BSSID)) break block5;
                    bl3 = bl2;
                }
                catch (Exception var1_2) {
                    return true;
                }
            }
            boolean bl4 = scanResult.BSSID.equals((Object)"00:00:00:00:00:00");
            bl3 = bl2;
            if (!bl4) {
                bl3 = true;
            }
        }
        return bl3;
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean a(WifiInfo wifiInfo) {
        if (wifiInfo == null || wifiInfo.getBSSID() == null || wifiInfo.getSSID() == null || wifiInfo.getBSSID().equals((Object)"00:00:00:00:00:00") || TextUtils.isEmpty((CharSequence)wifiInfo.getSSID())) {
            return false;
        }
        return true;
    }

    /*
     * Exception decompiling
     */
    private byte[] a(Object var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: First case is not immediately after switch.
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:366)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.replaceRawSwitches(SwitchReplacer.java:65)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:422)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    static /* synthetic */ int b(agu agu2) {
        return agu2.j;
    }

    static /* synthetic */ int b(agu agu2, int n2) {
        agu2.s = n2;
        return n2;
    }

    static /* synthetic */ long b(agu agu2, long l2) {
        agu2.z = l2;
        return l2;
    }

    private ahh b(CellLocation cellLocation) {
        cellLocation = (GsmCellLocation)cellLocation;
        ahh ahh2 = new ahh();
        String[] arrstring = ahz.a(this.m);
        ahh2.a = arrstring[0];
        ahh2.b = arrstring[1];
        ahh2.c = cellLocation.getLac();
        ahh2.d = cellLocation.getCid();
        ahh2.j = this.s;
        return ahh2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    private CellLocation b(List<?> var1_1) {
        if (var1_1 == null || var1_1.isEmpty()) {
            var1_1 = null;
            do {
                return var1_1;
                break;
            } while (true);
        }
        var12_2 = ClassLoader.getSystemClassLoader();
        var11_3 = null;
        var2_7 = 0;
        var9_8 = null;
        block15 : for (var3_6 = 0; var3_6 < var1_1.size(); ++var3_6) {
            block24 : {
                block23 : {
                    block22 : {
                        var13_16 = var1_1.get(var3_6);
                        if (var13_16 == null) lbl-1000: // 7 sources:
                        {
                            continue block15;
                        }
                        var15_18 = var12_2.loadClass("android.telephony.CellInfoGsm");
                        var16_19 = var12_2.loadClass("android.telephony.CellInfoWcdma");
                        var17_20 = var12_2.loadClass("android.telephony.CellInfoLte");
                        var14_17 = var12_2.loadClass("android.telephony.CellInfoCdma");
                        var8_12 = var15_18.isInstance(var13_16);
                        if (!var8_12) break block22;
                        var2_7 = 1;
lbl22: // 5 sources:
                        do {
                            if (var2_7 <= 0) ** GOTO lbl-1000
                            var10_13 = null;
                            if (var2_7 != 1) break block23;
                            var10_13 = var15_18.cast(var13_16);
lbl28: // 5 sources:
                            while ((var13_16 = ahs.a(var10_13, "getCellIdentity", new Object[0])) == null) {
                            }
                            ** GOTO lbl56
                            break;
                        } while (true);
                    }
                    if (!var16_19.isInstance(var13_16)) ** GOTO lbl36
                    var2_7 = 2;
                    ** GOTO lbl22
lbl36: // 1 sources:
                    if (!var17_20.isInstance(var13_16)) ** GOTO lbl39
                    var2_7 = 3;
                    ** GOTO lbl22
lbl39: // 1 sources:
                    var8_12 = var14_17.isInstance(var13_16);
                    if (!var8_12) ** GOTO lbl43
                    var2_7 = 4;
                    ** GOTO lbl22
lbl43: // 1 sources:
                    var2_7 = 0;
                    ** continue;
                }
                if (var2_7 != 2) ** GOTO lbl50
                var10_13 = var16_19.cast(var13_16);
                ** GOTO lbl28
lbl50: // 1 sources:
                if (var2_7 != 3) ** GOTO lbl53
                var10_13 = var17_20.cast(var13_16);
                ** GOTO lbl28
lbl53: // 1 sources:
                if (var2_7 != 4) ** GOTO lbl28
                var10_13 = var14_17.cast(var13_16);
                ** GOTO lbl28
lbl56: // 1 sources:
                if (var2_7 != 4) break block24;
                var10_13 = new CdmaCellLocation();
                var4_10 = ahs.b(var13_16, "getSystemId", new Object[0]);
                var5_11 = ahs.b(var13_16, "getNetworkId", new Object[0]);
                var6_21 = ahs.b(var13_16, "getBasestationId", new Object[0]);
                var7_22 = ahs.b(var13_16, "getLongitude", new Object[0]);
                var10_13.setCellLocationData(var6_21, ahs.b(var13_16, "getLatitude", new Object[0]), var7_22, var4_10, var5_11);
                var9_8 = var11_3;
                var1_1 = var10_13;
lbl66: // 4 sources:
                do {
                    if (var2_7 == 4) ** continue;
                    return var9_8;
                    break;
                } while (true);
            }
            if (var2_7 != 3) ** GOTO lbl85
            try {
                var4_10 = ahs.b(var13_16, "getTac", new Object[0]);
                var5_11 = ahs.b(var13_16, "getCi", new Object[0]);
                var10_13 = new GsmCellLocation();
            }
            catch (Exception var10_15) {}
            try {
                var10_13.setLacAndCid(var4_10, var5_11);
                var1_1 = var9_8;
                var9_8 = var10_13;
                ** GOTO lbl66
            }
            catch (Exception var11_4) {
                var11_3 = var10_13;
                ** GOTO lbl-1000
            }
lbl85: // 2 sources:
            var4_10 = ahs.b(var13_16, "getLac", new Object[0]);
            var5_11 = ahs.b(var13_16, "getCid", new Object[0]);
            var10_13 = new GsmCellLocation();
            try {
                var10_13.setLacAndCid(var4_10, var5_11);
                var1_1 = var9_8;
                var9_8 = var10_13;
            }
            catch (Exception var11_5) {
                var11_3 = var10_13;
                ** continue;
            }
            catch (Exception var10_14) {}
            ** GOTO lbl-1000
            catch (Exception var9_9) {
                var9_8 = var10_13;
            }
            ** GOTO lbl-1000
        }
        var1_1 = var9_8;
        var9_8 = var11_3;
        ** while (true)
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b(int n2) {
        if (n2 == -113) {
            this.s = -113;
            return;
        } else {
            this.s = n2;
            switch (this.j) {
                default: {
                    return;
                }
                case 1: 
                case 2: 
            }
            if (this.n.size() <= 0) return;
            {
                this.n.get((int)0).j = this.s;
                return;
            }
        }
    }

    static /* synthetic */ long c(agu agu2, long l2) {
        agu2.B = l2;
        return l2;
    }

    static /* synthetic */ List c(agu agu2) {
        return agu2.n;
    }

    private void c(int n2) {
        try {
            if (ahz.b() - this.H < 45000) {
                return;
            }
            if (this.e() && (!this.e() || this.F.f() >= 20)) {
                this.y();
                if (this.e == null) {
                    this.e = new agw(this, n2);
                }
                if (this.f == null) {
                    this.f = new Timer(false);
                    this.f.schedule(this.e, 3000, 3000);
                    return;
                }
            }
        }
        catch (Throwable var2_2) {
            var2_2.printStackTrace();
        }
    }

    static /* synthetic */ void c(agu agu2, int n2) {
        agu2.d(n2);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void c(CellLocation iterator) {
        if (this.n == null) return;
        if (iterator == null) return;
        if (this.m == null) {
            return;
        }
        this.n.clear();
        Object object = (GsmCellLocation)iterator;
        boolean bl2 = object.getLac() == -1 ? false : (object.getCid() == -1 || object.getCid() == 65535 || object.getCid() >= 268435455 ? false : (object.getLac() == 0 ? false : (object.getLac() > 65535 ? false : object.getCid() != 0)));
        if (!bl2) {
            this.j = 9;
            ahz.a(new Object[]{"case 2,gsm illegal"});
            return;
        }
        this.j = 1;
        object = null;
        this.n.add(this.b((CellLocation)iterator));
        iterator = object;
        if (this.m != null) {
            iterator = this.m.getNeighboringCellInfo();
        }
        if (iterator == null) return;
        iterator = iterator.iterator();
        while (iterator.hasNext()) {
            object = (NeighboringCellInfo)iterator.next();
            if (object.getCid() == -1) continue;
            bl2 = object.getLac() == -1 ? false : (object.getLac() == 0 ? false : (object.getLac() > 65535 ? false : (object.getCid() == -1 ? false : (object.getCid() == 0 ? false : (object.getCid() == 65535 ? false : object.getCid() < 268435455)))));
            if (!bl2 || (object = this.a((NeighboringCellInfo)object)) == null) continue;
            this.n.add((ahh)object);
        }
    }

    static /* synthetic */ WifiManager d(agu agu2) {
        return agu2.l;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void d(int var1_1) {
        block11 : {
            block12 : {
                var3_2 = 70254591;
                if (!this.e()) {
                    return;
                }
                try {
                    this.x();
                    var2_3 = var3_2;
                }
                catch (Throwable var4_5) {
                    var4_5.printStackTrace();
                    return;
                }
                switch (var1_1) {
                    case 1: {
                        var2_3 = 674234367;
                        ** GOTO lbl18
                    }
                    case 2: {
                        if (this.n()) break;
                        var2_3 = 674234367;
                    }
lbl18: // 3 sources:
                    case 0: {
                        ** GOTO lbl25
                    }
                    default: {
                        var2_3 = var3_2;
                        ** GOTO lbl25
                    }
                }
                var2_3 = 2083520511;
lbl25: // 4 sources:
                this.F.a(null, this.a(1, var2_3, 1));
                this.g = this.F.d();
                if (this.g == null) break block11;
                var4_4 = this.g.a();
                var4_4 = this.C.a((byte[])var4_4, this.i);
                if (!this.e()) break block11;
                if (TextUtils.isEmpty((CharSequence)var4_4) || !var4_4.equals((Object)"true")) break block12;
                this.F.a(this.g, this.a(1, var2_3, 1));
                break block11;
            }
            ++this.h;
            this.F.a(this.g, this.a(1, var2_3, 0));
        }
        this.y();
        if (this.e() && this.F.f() == 0) {
            this.w();
            return;
        }
        if (this.h < 3) return;
        this.w();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void d(CellLocation cellLocation) {
        this.n.clear();
        if (ahz.c() < 5) {
            return;
        }
        try {
            cellLocation = (CdmaCellLocation)cellLocation;
            if (cellLocation.getSystemId() <= 0) {
                this.j = 9;
                ahz.a(new Object[]{"cdma illegal"});
                return;
            }
            if (cellLocation.getNetworkId() < 0) {
                this.j = 9;
                ahz.a(new Object[]{"cdma illegal"});
                return;
            }
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
        if (cellLocation.getBaseStationId() < 0) {
            this.j = 9;
            ahz.a(new Object[]{"cdma illegal"});
            return;
        }
        this.j = 2;
        String[] arrstring = ahz.a(this.m);
        ahh ahh2 = new ahh();
        ahh2.a = arrstring[0];
        ahh2.b = arrstring[1];
        ahh2.g = cellLocation.getSystemId();
        ahh2.h = cellLocation.getNetworkId();
        ahh2.i = cellLocation.getBaseStationId();
        ahh2.j = this.s;
        ahh2.e = cellLocation.getBaseStationLatitude();
        ahh2.f = cellLocation.getBaseStationLongitude();
        this.n.add(ahh2);
    }

    static /* synthetic */ List e(agu agu2) {
        return agu2.o;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private boolean e(CellLocation var1_1) {
        block7 : {
            block8 : {
                var4_3 = false;
                var3_4 = false;
                if (var1_1 == null) {
                    return var3_4;
                }
                switch (ahz.a(var1_1, this.i)) {
                    case 1: {
                        var1_1 = (GsmCellLocation)var1_1;
                        var3_4 = var4_3;
                        if (var1_1.getLac() == -1) ** GOTO lbl40
                        var3_4 = var4_3;
                        if (var1_1.getLac() == 0) ** GOTO lbl40
                        var3_4 = var4_3;
                        if (var1_1.getLac() > 65535) ** GOTO lbl40
                        var3_4 = var4_3;
                        if (var1_1.getCid() == -1) ** GOTO lbl40
                        var3_4 = var4_3;
                        if (var1_1.getCid() == 0) ** GOTO lbl40
                        var3_4 = var4_3;
                        if (var1_1.getCid() == 65535) ** GOTO lbl40
                        if (var1_1.getCid() < 268435455) ** GOTO lbl23
                        var3_4 = var4_3;
                        ** GOTO lbl40
                    }
lbl23: // 2 sources:
                    default: {
                        ** GOTO lbl35
                    }
                    case 2: 
                }
                var3_4 = var4_3;
                try {
                    if (ahs.b((Object)var1_1, "getSystemId", new Object[0]) <= 0) break block7;
                    var3_4 = var4_3;
                    if (ahs.b((Object)var1_1, "getNetworkId", new Object[0]) < 0) break block7;
                    var2_5 = ahs.b((Object)var1_1, "getBaseStationId", new Object[0]);
                    if (var2_5 >= 0) break block8;
                    var3_4 = var4_3;
                }
                catch (Exception var1_2) {
                    var3_4 = true;
                }
            }
            var3_4 = true;
        }
        var4_3 = var3_4;
        if (var3_4 != false) return var4_3;
        return var3_4;
    }

    private void f() {
        this.l = (WifiManager)ahz.b(this.i, "wifi");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.i.registerReceiver((BroadcastReceiver)this.t, intentFilter);
        this.p();
    }

    static /* synthetic */ void f(agu agu2) {
        agu2.o();
    }

    private void g() {
        try {
            CellLocation.requestLocationUpdate();
            return;
        }
        catch (Throwable var1_1) {
            return;
        }
    }

    static /* synthetic */ void g(agu agu2) {
        agu2.g();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void h() {
        this.k = (ConnectivityManager)ahz.b(this.i, "connectivity");
        this.g();
        this.A = ahz.b();
        this.m = (TelephonyManager)ahz.b(this.i, "phone");
        int n2 = this.m != null ? this.m.getPhoneType() : 9;
        switch (n2) {
            default: {
                this.j = 9;
                break;
            }
            case 1: {
                this.j = 1;
                break;
            }
            case 2: {
                this.j = 2;
            }
        }
        this.r = new agv(this);
        n2 = ahz.c() < 7 ? 2 : 256;
        if (n2 == 0) {
            if (this.m == null) return;
            {
                this.m.listen(this.r, 16);
                return;
            }
        }
        try {
            if (this.m == null) return;
            this.m.listen(this.r, n2 | 16);
            return;
        }
        catch (SecurityException var2_2) {
            ahz.a(var2_2);
            return;
        }
    }

    static /* synthetic */ void h(agu agu2) {
        agu2.p();
    }

    static /* synthetic */ int i(agu agu2) {
        return agu2.D;
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private String i() {
        var3_1 = null;
        this.v();
        if (!this.t()) ** GOTO lbl9
        this.u = this.l.getConnectionInfo();
        block5 : do {
            switch (this.j) {
                default: {
                    return "";
                }
lbl9: // 1 sources:
                this.o();
                continue block5;
                case 1: {
                    if (this.n.size() <= 0) return "";
                    var1_3 = this.n.get(0);
                    var2_5 = new StringBuilder();
                    var2_5.append(var1_3.a).append("#");
                    var2_5.append(var1_3.b).append("#");
                    var2_5.append(var1_3.c).append("#");
                    var2_5.append(var1_3.d).append("#");
                    var2_5.append("network").append("#");
                    if (this.o.size() > 0) {
                        var1_3 = "cellwifi";
lbl22: // 2 sources:
                        do {
                            var2_5.append((String)var1_3);
                            return var2_5.toString();
                            break;
                        } while (true);
                    }
                    var1_3 = "cell";
                    ** continue;
                }
                case 2: {
                    if (this.n.size() <= 0) return "";
                    var1_4 = this.n.get(0);
                    var2_6 = new StringBuilder();
                    var2_6.append(var1_4.a).append("#");
                    var2_6.append(var1_4.b).append("#");
                    var2_6.append(var1_4.g).append("#");
                    var2_6.append(var1_4.h).append("#");
                    var2_6.append(var1_4.i).append("#");
                    var2_6.append("network").append("#");
                    if (this.o.size() > 0) {
                        var1_4 = "cellwifi";
lbl39: // 2 sources:
                        do {
                            var2_6.append((String)var1_4);
                            return var2_6.toString();
                            break;
                        } while (true);
                    }
                    var1_4 = "cell";
                    ** continue;
                }
                case 9: 
            }
            break;
        } while (true);
        var2_7 = String.format((String)"#%s#", (Object[])new Object[]{"network"});
        if (this.o.size() == 1) {
            var1_2 = var3_1;
            if (this.a(this.u) == false) return var1_2;
        }
        var1_2 = var3_1;
        if (this.o.size() == 0) return var1_2;
        if (this.o.size() != 1) return var2_7 + "wifi";
        if (this.a(this.u) == false) return var2_7 + "wifi";
        var1_2 = this.o.get(0);
        if (var1_2 == null) return var2_7;
        if (this.u.getBSSID().equals((Object)var1_2.BSSID) == false) return var2_7;
        return null;
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private StringBuilder j() {
        block19 : {
            block18 : {
                block20 : {
                    this.v();
                    var6_1 = new StringBuilder(700);
                    block0 : switch (this.j) {
                        default: {
                            do {
                                if (this.E == null || this.E.equals((Object)"00:00:00:00:00:00")) {
                                    if (this.u != null) break block0;
                                    if (this.l != null) {
                                        this.u = this.l.getConnectionInfo();
                                        if (this.u != null) {
                                            this.E = this.u.getMacAddress();
                                            if (this.E == null) {
                                                this.E = "00:00:00:00:00:00";
                                            }
                                            this.u = null;
                                        }
                                    }
                                }
lbl15: // 9 sources:
                                do {
                                    if (!this.t()) break block18;
                                    if (!this.a(this.u)) break block19;
                                    var4_3 = this.u.getBSSID();
lbl19: // 2 sources:
                                    var1_2 = 0;
                                    for (var2_4 = 0; var2_4 < this.o.size(); ++var2_4) {
                                        var5_6 = this.o.get(var2_4);
                                        var3_5 = var1_2;
                                        if (this.a((ScanResult)var5_6)) {
                                            var7_7 = var5_6.BSSID;
                                            var5_6 = "nb";
                                            if (var4_3.equals((Object)var7_7)) {
                                                var5_6 = "access";
                                                var1_2 = 1;
                                            }
                                            var6_1.append(String.format((String)"#%s,%s", (Object[])new Object[]{var7_7, var5_6}));
                                            var3_5 = var1_2;
                                        }
                                        var1_2 = var3_5;
                                    }
                                    break block20;
                                    break;
                                } while (true);
                                break;
                            } while (true);
                        }
                        case 1: {
                            var1_2 = 0;
                            block8 : do {
                                if (var1_2 >= this.n.size()) ** continue;
                                if (var1_2 != 0) break;
lbl39: // 2 sources:
                                do {
                                    ++var1_2;
                                    continue block8;
                                    break;
                                } while (true);
                                break;
                            } while (true);
                            var4_3 = this.n.get(var1_2);
                            var6_1.append("#").append(var4_3.b);
                            var6_1.append("|").append(var4_3.c);
                            var6_1.append("|").append(var4_3.d);
                            ** continue;
                        }
                    }
                    this.E = this.u.getMacAddress();
                    if (this.E != null) ** GOTO lbl15
                    this.E = "00:00:00:00:00:00";
                    ** while (true)
                }
                if (var4_3.length() > 0) {
                    var6_1.append("#").append((String)var4_3);
                    var6_1.append(",access");
                }
lbl55: // 4 sources:
                do {
                    if (var6_1.length() <= 0) return var6_1;
                    var6_1.deleteCharAt(0);
                    return var6_1;
                    break;
                } while (true);
            }
            this.o();
            ** while (true)
        }
        var4_3 = "";
        ** GOTO lbl19
    }

    static /* synthetic */ boolean j(agu agu2) {
        return agu2.n();
    }

    static /* synthetic */ void k(agu agu2) {
        agu2.w();
    }

    private byte[] k() {
        synchronized (this) {
            if (this.l()) {
                this.g();
                this.A = ahz.b();
            }
            if (this.m()) {
                this.p();
            }
            byte[] arrby = this.a((Object)null);
            return arrby;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean l() {
        if (this.y || this.A == 0 || ahz.b() - this.A < ahk.j) {
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean m() {
        if (!this.t() || this.B == 0 || ahz.b() - this.B < ahk.i) {
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private boolean n() {
        if (this.l == null) {
            return false;
        }
        if (!this.t()) return false;
        NetworkInfo networkInfo = null;
        try {
            if (this.k != null) {
                networkInfo = this.k.getActiveNetworkInfo();
            }
            if (ahq.a(networkInfo) == -1) return false;
            boolean bl2 = this.a(this.l.getConnectionInfo());
            if (!bl2) return false;
            return true;
        }
        catch (Throwable var2_2) {
            var2_2.printStackTrace();
            return false;
        }
        catch (SecurityException var2_3) {
            return false;
        }
    }

    private void o() {
        this.o.clear();
        this.u = null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void p() {
        if (!this.t()) return;
        try {
            this.l.startScan();
            this.B = ahz.b();
            return;
        }
        catch (SecurityException var1_1) {
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean q() {
        if (this.z == 0 || ahz.b() - this.z >= 2000) {
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void r() {
        if (this.w == null) return;
        if (this.p.size() < 1) {
            return;
        }
        Iterator<Map.Entry<PendingIntent, List<aho>>> iterator = this.p.entrySet().iterator();
        block2 : while (iterator != null) {
            if (!iterator.hasNext()) return;
            Intent intent = iterator.next();
            PendingIntent pendingIntent = (PendingIntent)intent.getKey();
            Object object = (List)intent.getValue();
            intent = new Intent();
            Bundle bundle = new Bundle();
            object = object.iterator();
            do {
                float f2;
                if (!object.hasNext()) continue block2;
                aho aho2 = (aho)object.next();
                long l2 = aho2.a();
                if (l2 != -1 && l2 < ahz.b() || (f2 = ahz.a(new double[]{aho2.b, aho2.a, this.w.f(), this.w.e()})) >= aho2.c) continue;
                bundle.putFloat("distance", f2);
                bundle.putString("fence", aho2.b());
                intent.putExtras(bundle);
                try {
                    pendingIntent.send(this.i, 0, intent);
                    continue;
                }
                catch (Throwable var9_9) {
                    var9_9.printStackTrace();
                    continue;
                }
                break;
            } while (true);
            break;
        }
        return;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void s() {
        switch (this.j) {
            case 1: {
                if (this.n.size() != 0) return;
                {
                    this.j = 9;
                    return;
                }
            }
            default: {
                return;
            }
            case 2: 
        }
        if (this.n.size() != 0) return;
        {
            this.j = 9;
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean t() {
        boolean bl2;
        boolean bl3;
        bl3 = false;
        bl2 = false;
        if (this.l == null) return bl2;
        try {
            bl3 = bl2 = this.l.isWifiEnabled();
        }
        catch (Exception var3_4) {}
        bl2 = bl3;
        if (bl3) return bl2;
        bl2 = bl3;
        if (ahz.c() <= 17) {
            return bl2;
        }
        try {
            return String.valueOf((Object)ahs.a((Object)this.l, "isScanAlwaysAvailable", new Object[0])).equals((Object)"true");
        }
        catch (Exception var3_3) {
            return bl3;
        }
    }

    private ahf u() {
        return this.a(this.k(), false);
    }

    private void v() {
        if (this.y) {
            this.j = 9;
            this.n.clear();
            return;
        }
        this.s();
    }

    private void w() {
        if (this.f != null) {
            this.f.cancel();
            this.f = null;
        }
        if (this.e != null) {
            this.e.cancel();
            this.e = null;
        }
    }

    private void x() {
        if (!this.e()) {
            return;
        }
        try {
            this.F.a(768);
            return;
        }
        catch (Throwable var1_1) {
            var1_1.printStackTrace();
            ahz.a(var1_1);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void y() {
        if (!this.e()) {
            return;
        }
        if (this.F.f() > 0) return;
        try {
            boolean bl2 = this.F.e();
            if (!bl2) return;
            return;
        }
        catch (Throwable var2_2) {
            var2_2.printStackTrace();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private CellLocation z() {
        CellLocation cellLocation;
        CellLocation cellLocation2;
        if (this.m == null) {
            return null;
        }
        try {
            cellLocation = this.m.getCellLocation();
        }
        catch (Exception var1_5) {
            cellLocation = null;
        }
        cellLocation2 = cellLocation;
        if (this.e(cellLocation)) return cellLocation2;
        try {
            cellLocation = cellLocation2 = this.b((List)ahs.a((Object)this.m, "getAllCellInfo", new Object[0]));
        }
        catch (Exception var2_2) {
        }
        catch (NoSuchMethodException var2_3) {}
        cellLocation2 = cellLocation;
        if (!this.e(cellLocation)) return cellLocation2;
        return cellLocation;
    }

    /*
     * Enabled aggressive block sorting
     */
    public int a(boolean bl2, int n2) {
        if (!bl2) {
            this.w();
        } else {
            this.c(n2);
        }
        if (this.e()) {
            return this.F.f();
        }
        return -1;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @Override
    public ahf a() {
        var2_1 = 1;
        if (this.i == null) {
            throw new AMapLocException("\u672a\u77e5\u7684\u9519\u8bef");
        }
        if (TextUtils.isEmpty((CharSequence)ahk.d)) {
            throw new AMapLocException("key\u9274\u6743\u5931\u8d25");
        }
        if (TextUtils.isEmpty((CharSequence)ahk.e)) {
            throw new AMapLocException("key\u9274\u6743\u5931\u8d25");
        }
        if ("false".equals((Object)ahq.a(this.v)[0])) {
            Log.e((String)"AuthLocation", (String)"key\u9274\u6743\u5931\u8d25");
            throw new AMapLocException("key\u9274\u6743\u5931\u8d25");
        }
        if (this.l()) {
            this.g();
            this.A = ahz.b();
        }
        if (this.m()) {
            this.p();
        }
        ++this.D;
        if (this.D > 1) {
            this.c();
            if (!this.c && com.amap.api.location.core.a.b()) {
                this.A();
            }
        }
        if (this.o == null) {
            this.o = new ArrayList();
        }
        if (this.D == 1) {
            this.y = ahz.a(this.i);
            if (this.t() && this.o.isEmpty() && this.l != null) {
                this.o = this.l.getScanResults();
            }
        }
        if (this.D == 1 && this.t() && this.o.isEmpty()) {
            for (var1_2 = 4; var1_2 > 0 && this.o.size() == 0; --var1_2) {
                SystemClock.sleep((long)500);
            }
        }
        if (this.a(this.x) && this.w != null) {
            this.x = ahz.a();
            return this.w;
        }
        try {
            this.a(this.J);
        }
        catch (Throwable var5_3) {
            var5_3.printStackTrace();
        }
        this.a(this.o);
        var7_5 = this.i();
        if (TextUtils.isEmpty((CharSequence)var7_5)) {
            if (com.amap.api.location.core.a.b() == false) throw new AMapLocException("\u83b7\u53d6\u57fa\u7ad9/WiFi\u4fe1\u606f\u4e3a\u7a7a\u6216\u5931\u8d25");
            if (this.b != 0) {
                SystemClock.sleep((long)500);
            }
            if (this.b != 0) throw new AMapLocException("\u83b7\u53d6\u57fa\u7ad9/WiFi\u4fe1\u606f\u4e3a\u7a7a\u6216\u5931\u8d25");
            this.w = this.a.d();
            this.r();
            this.d();
            if (this.w == null) throw new AMapLocException("\u83b7\u53d6\u57fa\u7ad9/WiFi\u4fe1\u606f\u4e3a\u7a7a\u6216\u5931\u8d25");
            return this.w;
        }
        var8_6 = this.j();
        var5_4 = null;
        try {
            var5_4 = var6_7 = this.I.a(var7_5, var8_6, "mem");
        }
        catch (Throwable var6_8) {}
        if (var5_4 == null) ** GOTO lbl-1000
        var3_9 = var5_4.h();
        if (ahz.a() - var3_9 > 3600000) {
            var1_2 = var2_1;
        } else lbl-1000: // 2 sources:
        {
            var1_2 = 0;
        }
        if (var5_4 == null || var1_2 != 0) {
            block27 : {
                try {
                    var6_7 = this.u();
                }
                catch (AMapLocException var9_10) {
                    var6_7 = var5_4;
                    if (var5_4 != null) break block27;
                    throw var9_10;
                }
            }
            this.w = var6_7;
        } else {
            this.w = var5_4;
        }
        this.I.a(var7_5, this.w, var8_6);
        var8_6.delete(0, var8_6.length());
        this.x = ahz.a();
        this.r();
        this.d();
        return this.w;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(aho aho2, PendingIntent pendingIntent) {
        long l2;
        if (pendingIntent == null || aho2 == null || (l2 = aho2.a()) != -1 && l2 < ahz.b()) {
            return;
        }
        if (this.p.get((Object)pendingIntent) != null) {
            List<aho> list = this.p.get((Object)pendingIntent);
            list.add(aho2);
            this.p.put(pendingIntent, list);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(aho2);
        this.p.put(pendingIntent, (List<aho>)arrayList);
    }

    @Override
    public void a(PendingIntent pendingIntent) {
        if (pendingIntent == null) {
            return;
        }
        this.p.remove((Object)pendingIntent);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void a(Context context) {
        if (context == null || this.i != null) {
            return;
        }
        this.i = context.getApplicationContext();
        try {
            if (this.a == null) {
                this.a = new ahi(context);
                this.a.a(this.d);
            }
        }
        catch (Throwable var2_3) {}
        try {
            if (this.I == null) {
                this.I = new ahg(context);
            }
        }
        catch (Throwable var1_2) {}
        ahz.a(this.i, "in debug mode, only for test");
        this.f();
        this.h();
        this.H = ahz.b();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @Override
    public void a(Context context, AMapLocation aMapLocation) {
        if (aMapLocation == null) return;
        if (this.q.size() < 1) {
            return;
        }
        Iterator<Map.Entry<PendingIntent, List<aho>>> iterator = this.q.entrySet().iterator();
        block2 : while (iterator != null) {
            if (!iterator.hasNext()) return;
            Intent intent = iterator.next();
            PendingIntent pendingIntent = (PendingIntent)intent.getKey();
            Object object = (List)intent.getValue();
            intent = new Intent();
            Bundle bundle = new Bundle();
            object = object.iterator();
            do {
                if (!object.hasNext()) continue block2;
                aho aho2 = (aho)object.next();
                long l2 = aho2.a();
                if (l2 != -1 && l2 < ahz.b()) continue;
                float f2 = ahz.a(new double[]{aho2.b, aho2.a, aMapLocation.getLatitude(), aMapLocation.getLongitude()});
                if (f2 >= aho2.c) {
                    if (aho2.d == 0) continue;
                    aho2.d = 0;
                }
                if (f2 < aho2.c) {
                    if (aho2.d == 1) continue;
                    aho2.d = 1;
                }
                bundle.putFloat("distance", f2);
                bundle.putString("fence", aho2.b());
                bundle.putInt("status", aho2.d);
                intent.putExtras(bundle);
                try {
                    pendingIntent.send(context, 0, intent);
                    continue;
                }
                catch (Throwable var11_11) {
                    var11_11.printStackTrace();
                    continue;
                }
                break;
            } while (true);
            break;
        }
        return;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(String arrstring) {
        if (arrstring == null || arrstring.indexOf("##") == -1 || (arrstring = arrstring.split("##")).length != 3) {
            return;
        }
        ahk.a(arrstring[0]);
        if (!ahk.e.equals((Object)arrstring[1])) {
            this.I.a();
        }
        ahk.b(arrstring[1]);
        ahk.c(arrstring[2]);
    }

    @Override
    public void a(JSONObject jSONObject) {
        this.v = jSONObject;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void b() {
        try {
            if (this.F != null) {
                this.F.c();
                this.K = false;
            }
        }
        catch (Throwable var1_1) {
            var1_1.printStackTrace();
        }
        try {
            if (this.i != null) {
                this.i.unregisterReceiver((BroadcastReceiver)this.t);
            }
        }
        catch (Throwable var1_3) {
            this.t = null;
            throw var1_3;
        }
        catch (Throwable var1_6) {}
        this.t = null;
        this.w();
        try {
            if (this.m != null && this.r != null) {
                this.m.listen(this.r, 0);
            }
        }
        catch (Throwable var1_4) {
            var1_4.printStackTrace();
        }
        if (this.i != null && this.I != null) {
            this.I.a();
            this.I.b();
        }
        ahk.a(false);
        this.x = 0;
        this.n.clear();
        this.p.clear();
        this.q.clear();
        this.s = -113;
        this.o();
        this.w = null;
        this.i = null;
        this.m = null;
        try {
            if (this.a != null) {
                if (this.b == 0 && this.c) {
                    this.a.a();
                }
                this.b = -1;
                this.c = false;
                this.a = null;
            }
            return;
        }
        catch (Throwable var1_5) {
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void b(aho aho2, PendingIntent pendingIntent) {
        long l2;
        if (pendingIntent == null || aho2 == null || (l2 = aho2.a()) != -1 && l2 < ahz.b()) {
            return;
        }
        if (this.q.get((Object)pendingIntent) != null) {
            List<aho> list = this.q.get((Object)pendingIntent);
            list.add(aho2);
            this.q.put(pendingIntent, list);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(aho2);
        this.q.put(pendingIntent, (List<aho>)arrayList);
    }

    @Override
    public void b(PendingIntent pendingIntent) {
        if (pendingIntent == null) {
            return;
        }
        this.q.remove((Object)pendingIntent);
    }

    public void c() {
        try {
            if (this.F == null) {
                this.F = aie.a(this.i);
                this.F.a(256);
            }
            if (!this.K) {
                this.K = true;
                this.F.a();
            }
            return;
        }
        catch (Throwable var1_1) {
            var1_1.printStackTrace();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public void d() {
        if (this.w == null) return;
        if (this.q.size() < 1) {
            return;
        }
        Iterator<Map.Entry<PendingIntent, List<aho>>> iterator = this.q.entrySet().iterator();
        block2 : while (iterator != null) {
            if (!iterator.hasNext()) return;
            Intent intent = iterator.next();
            PendingIntent pendingIntent = (PendingIntent)intent.getKey();
            Object object = (List)intent.getValue();
            intent = new Intent();
            Bundle bundle = new Bundle();
            object = object.iterator();
            do {
                if (!object.hasNext()) continue block2;
                aho aho2 = (aho)object.next();
                long l2 = aho2.a();
                if (l2 != -1 && l2 < ahz.b()) continue;
                float f2 = ahz.a(new double[]{aho2.b, aho2.a, this.w.f(), this.w.e()});
                if (f2 >= aho2.c) {
                    if (aho2.d == 0) continue;
                    aho2.d = 0;
                }
                if (f2 < aho2.c) {
                    if (aho2.d == 1) continue;
                    aho2.d = 1;
                }
                bundle.putFloat("distance", f2);
                bundle.putString("fence", aho2.b());
                bundle.putInt("status", aho2.d);
                intent.putExtras(bundle);
                try {
                    pendingIntent.send(this.i, 0, intent);
                    continue;
                }
                catch (Throwable var9_9) {
                    var9_9.printStackTrace();
                    continue;
                }
                break;
            } while (true);
            break;
        }
        return;
    }

    boolean e() {
        if (this.F == null) {
            return false;
        }
        return true;
    }

    class a
    implements ahi.a {
        a() {
        }

        @Override
        public void a(int n2) {
            agu.this.b = n2;
        }
    }

    class b
    extends BroadcastReceiver {
        private b() {
        }

        /* synthetic */ b(agv agv2) {
            this();
        }

        /*
         * Exception decompiling
         */
        public void onReceive(Context var1_1, Intent var2_4) {
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

