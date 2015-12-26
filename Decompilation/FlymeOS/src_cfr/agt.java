/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.location.Location
 *  android.net.wifi.ScanResult
 *  android.telephony.CellLocation
 *  java.lang.Object
 *  java.lang.String
 */
import android.location.Location;
import android.net.wifi.ScanResult;
import android.telephony.CellLocation;
import java.util.List;

public final class agt {
    private static int c = 10;
    private static int d = 100;
    private static float f = 0.5f;
    protected aha a;
    protected agx b;
    private agf e;

    protected agt(agf agf2) {
        this.a = new aha(this);
        this.b = new agx(this);
        this.e = agf2;
    }

    protected static void a() {
    }

    protected static void a(int n2) {
        c = n2;
    }

    protected static void b(int n2) {
        d = n2;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected final boolean a(Location object) {
        boolean bl2 = false;
        boolean bl3 = false;
        if (this.e == null) {
            return bl3;
        }
        List list = this.e.j();
        boolean bl4 = bl3;
        if (list == null) return bl4;
        bl4 = bl3;
        if (object == null) return bl4;
        "cell.list.size: " + list.size();
        agy agy2 = null;
        bl3 = bl2;
        if (list.size() >= 2) {
            agy2 = new agy((CellLocation)list.get(1));
            if (this.b.b == null) {
                bl3 = true;
            } else {
                bl4 = object.distanceTo(this.b.b) > (float)d;
                bl3 = bl4;
                if (!bl4) {
                    object = this.b.a;
                    boolean bl5 = agy2.e == object.e && agy2.d == object.d && agy2.c == object.c && agy2.b == object.b && agy2.a == object.a;
                    bl3 = !bl5;
                }
                "collect cell?: " + bl3;
            }
        }
        bl4 = bl3;
        if (!bl3) return bl4;
        this.b.a = agy2;
        return bl3;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected final boolean b(Location var1_1) {
        var7_2 = 0;
        if (this.e == null) {
            return false;
        }
        var11_3 = this.e.k();
        if (var11_3.size() < 2) ** GOTO lbl35
        var11_3 = (List)var11_3.get(1);
        if (this.a.b != null) ** GOTO lbl10
        var10_4 = true;
        ** GOTO lbl38
lbl10: // 1 sources:
        if (var11_3 == null || var11_3.size() <= 0) ** GOTO lbl32
        var10_4 = var1_1.distanceTo(this.a.b) > (float)agt.c;
        if (var10_4) ** GOTO lbl30
        var1_1 = this.a.a;
        var2_5 = agt.f;
        if (var11_3 != null && var1_1 != null) ** GOTO lbl18
        var4_7 = 0;
        ** GOTO lbl49
lbl18: // 1 sources:
        if (var11_3 != null && var1_1 != null) ** GOTO lbl21
        var4_7 = 0;
        ** GOTO lbl49
lbl21: // 1 sources:
        var8_10 = var11_3.size();
        var9_11 = var1_1.size();
        var3_6 = var8_10 + var9_11;
        if (var8_10 != 0 || var9_11 != 0) ** GOTO lbl27
        var4_7 = 1;
        ** GOTO lbl49
lbl27: // 1 sources:
        if (var8_10 != 0 && var9_11 != 0) ** GOTO lbl55
        var4_7 = 0;
        ** GOTO lbl49
lbl30: // 1 sources:
        var1_1 = var11_3;
        ** GOTO lbl40
lbl32: // 1 sources:
        var1_1 = var11_3;
        var10_4 = false;
        ** GOTO lbl40
lbl35: // 1 sources:
        var1_1 = null;
        var10_4 = false;
        ** GOTO lbl40
lbl38: // 3 sources:
        do {
            var1_1 = var11_3;
lbl40: // 4 sources:
            if (var10_4 == false) return var10_4;
            this.a.a.clear();
            var5_8 = var1_1.size();
            var4_7 = var7_2;
            while (var4_7 < var5_8) {
                this.a.a.add(new agz(((ScanResult)var1_1.get((int)var4_7)).BSSID));
                ++var4_7;
            }
            return var10_4;
            break;
        } while (true);
lbl49: // 6 sources:
        do {
            if (var4_7 != 0) ** GOTO lbl53
            var10_4 = true;
            ** GOTO lbl38
lbl53: // 1 sources:
            var10_4 = false;
            ** continue;
            break;
        } while (true);
lbl55: // 1 sources:
        var5_8 = 0;
        var4_7 = 0;
        do {
            if (var5_8 >= var8_10) ** GOTO lbl62
            var12_12 = ((ScanResult)var11_3.get((int)var5_8)).BSSID;
            if (var12_12 == null) ** GOTO lbl71
            ** GOTO lbl67
lbl62: // 1 sources:
            if ((float)(var4_7 << 1) < var3_6 * var2_5) ** GOTO lbl65
            var4_7 = 1;
            ** GOTO lbl49
lbl65: // 1 sources:
            var4_7 = 0;
            ** continue;
lbl67: // 2 sources:
            for (var6_9 = 0; var6_9 < var9_11; ++var6_9) {
                if (!var12_12.equals((Object)((agz)var1_1.get((int)var6_9)).a)) continue;
                ++var4_7;
                break;
            }
lbl71: // 3 sources:
            ++var5_8;
        } while (true);
    }
}

