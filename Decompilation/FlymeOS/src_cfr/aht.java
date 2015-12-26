/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.Byte
 *  java.lang.Double
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 */
import android.text.TextUtils;
import java.util.zip.CRC32;

public class aht {
    public byte[] A = null;
    public String B = null;
    public String C = null;
    public String D = null;
    public String E = null;
    public String a = "1";
    public short b = 0;
    public String c = null;
    public String d = null;
    public String e = null;
    public String f = null;
    public String g = null;
    public String h = null;
    public String i = null;
    public String j = null;
    public String k = null;
    public String l = null;
    public String m = null;
    public String n = null;
    public String o = null;
    public String p = null;
    public String q = null;
    public String r = null;
    public String s = null;
    public String t = null;
    public String u = null;
    public String v = null;
    public String w = null;
    public String x = null;
    public String y = null;
    public String z = null;

    private String a(String string2, int n2) {
        String[] arrstring = this.w.split("\\*")[n2].split(",");
        if (string2.equals((Object)"lac")) {
            return arrstring[0];
        }
        if (string2.equals((Object)"cellid")) {
            return arrstring[1];
        }
        if (string2.equals((Object)"signal")) {
            return arrstring[2];
        }
        return null;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private byte[] a(String var1_1) {
        var3_2 = var1_1.split(":");
        if (var3_2 == null) ** GOTO lbl-1000
        var1_1 = var3_2;
        if (var3_2.length != 6) lbl-1000: // 2 sources:
        {
            var1_1 = new String[6];
            for (var2_3 = 0; var2_3 < var1_1.length; ++var2_3) {
                var1_1[var2_3] = "0";
            }
        }
        var3_2 = (String[])new byte[6];
        var2_3 = 0;
        while (var2_3 < var1_1.length) {
            if (var1_1[var2_3].length() > 2) {
                var1_1[var2_3] = var1_1[var2_3].substring(0, 2);
            }
            var3_2[var2_3] = (byte)Integer.parseInt((String)var1_1[var2_3], (int)16);
            ++var2_3;
        }
        return var3_2;
    }

    private String b(String string2) {
        if (!this.v.contains((CharSequence)(string2 + ">"))) {
            return "0";
        }
        int n2 = this.v.indexOf(string2 + ">");
        int n3 = this.v.indexOf("</" + string2);
        return this.v.substring(n2 + string2.length() + 1, n3);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b() {
        if (TextUtils.isEmpty((CharSequence)this.a)) {
            this.a = "";
        }
        if (TextUtils.isEmpty((CharSequence)this.c)) {
            this.c = "";
        }
        if (TextUtils.isEmpty((CharSequence)this.d)) {
            this.d = "";
        }
        if (TextUtils.isEmpty((CharSequence)this.e)) {
            this.e = "";
        }
        if (TextUtils.isEmpty((CharSequence)this.f)) {
            this.f = "";
        }
        if (TextUtils.isEmpty((CharSequence)this.g)) {
            this.g = "";
        }
        if (TextUtils.isEmpty((CharSequence)this.h)) {
            this.h = "";
        }
        if (TextUtils.isEmpty((CharSequence)this.i)) {
            this.i = "";
        }
        if (TextUtils.isEmpty((CharSequence)this.j)) {
            this.j = "0";
        } else if (!this.j.equals((Object)"1") && !this.j.equals((Object)"2")) {
            this.j = "0";
        }
        if (TextUtils.isEmpty((CharSequence)this.D)) {
            this.D = "0";
        } else if (!this.D.equals((Object)"0") && !this.D.equals((Object)"1")) {
            this.D = "0";
        }
        this.k = TextUtils.isEmpty((CharSequence)this.k) ? "" : String.valueOf((int)Double.valueOf((double)(Double.parseDouble((String)this.k) * 1200000.0)).intValue());
        this.l = TextUtils.isEmpty((CharSequence)this.l) ? "" : String.valueOf((int)Double.valueOf((double)(Double.parseDouble((String)this.l) * 1000000.0)).intValue());
        if (TextUtils.isEmpty((CharSequence)this.m)) {
            this.m = "";
        }
        if (TextUtils.isEmpty((CharSequence)this.n)) {
            this.n = "";
        }
        if (TextUtils.isEmpty((CharSequence)this.o)) {
            this.o = "";
        }
        if (TextUtils.isEmpty((CharSequence)this.p)) {
            this.p = "";
        }
        if (TextUtils.isEmpty((CharSequence)this.q)) {
            this.q = "";
        }
        if (TextUtils.isEmpty((CharSequence)this.r)) {
            this.r = "";
        }
        if (TextUtils.isEmpty((CharSequence)this.B)) {
            this.B = "";
        }
        if (TextUtils.isEmpty((CharSequence)this.C)) {
            this.C = "";
        }
        if (TextUtils.isEmpty((CharSequence)this.s)) {
            this.s = "";
        }
        if (TextUtils.isEmpty((CharSequence)this.t)) {
            this.t = "0";
        } else if (!this.t.equals((Object)"1") && !this.t.equals((Object)"2")) {
            this.t = "0";
        }
        if (TextUtils.isEmpty((CharSequence)this.u)) {
            this.u = "0";
        } else if (!this.u.equals((Object)"1") && !this.u.equals((Object)"2")) {
            this.u = "0";
        }
        if (TextUtils.isEmpty((CharSequence)this.v)) {
            this.v = "";
        }
        if (TextUtils.isEmpty((CharSequence)this.w)) {
            this.w = "";
        }
        if (TextUtils.isEmpty((CharSequence)this.x)) {
            this.x = "";
        }
        if (TextUtils.isEmpty((CharSequence)this.y)) {
            this.y = "";
        }
        if (TextUtils.isEmpty((CharSequence)this.E)) {
            this.E = "";
        }
        if (TextUtils.isEmpty((CharSequence)this.z)) {
            this.z = "";
        }
        if (this.A == null) {
            this.A = new byte[0];
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public byte[] a() {
        block89 : {
            this.b();
            var1_1 = 3072;
            if (this.A != null) {
                var1_1 = 3072 + (this.A.length + 1);
            }
            var8_2 = new byte[var1_1];
            var8_2[0] = Byte.parseByte((String)this.a);
            var6_3 = ahv.b(this.b);
            System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)1, (int)var6_3.length);
            var1_1 = var2_21 = var6_3.length + 1;
            try {
                var6_3 = this.c.getBytes("GBK");
                var1_1 = var2_21++;
                var8_2[var2_21] = (byte)var6_3.length;
                var1_1 = var2_21;
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var2_21, (int)var6_3.length);
                var1_1 = var2_21;
                var3_22 = var6_3.length;
            }
            catch (Exception var6_4) {
                var8_2[var1_1] = 0;
                ++var1_1;
            }
            var1_1 = var2_21 + var3_22;
            var2_21 = var1_1;
            try {
                var6_3 = this.d.getBytes("GBK");
                var2_21 = var1_1++;
                var8_2[var1_1] = (byte)var6_3.length;
                var2_21 = var1_1;
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var1_1, (int)var6_3.length);
                var2_21 = var1_1;
                var3_22 = var6_3.length;
                var1_1 += var3_22;
            }
            catch (Exception var6_5) {
                var8_2[var2_21] = 0;
                var1_1 = var2_21 + 1;
            }
            var2_21 = var1_1;
            try {
                var6_3 = this.n.getBytes("GBK");
                var2_21 = var1_1++;
                var8_2[var1_1] = (byte)var6_3.length;
                var2_21 = var1_1;
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var1_1, (int)var6_3.length);
                var2_21 = var1_1;
                var3_22 = var6_3.length;
                var1_1 += var3_22;
            }
            catch (Exception var6_6) {
                var8_2[var2_21] = 0;
                var1_1 = var2_21 + 1;
            }
            var2_21 = var1_1;
            try {
                var6_3 = this.e.getBytes("GBK");
                var2_21 = var1_1++;
                var8_2[var1_1] = (byte)var6_3.length;
                var2_21 = var1_1;
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var1_1, (int)var6_3.length);
                var2_21 = var1_1;
                var3_22 = var6_3.length;
                var1_1 += var3_22;
            }
            catch (Exception var6_7) {
                var8_2[var2_21] = 0;
                var1_1 = var2_21 + 1;
            }
            var2_21 = var1_1;
            try {
                var6_3 = this.f.getBytes("GBK");
                var2_21 = var1_1++;
                var8_2[var1_1] = (byte)var6_3.length;
                var2_21 = var1_1;
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var1_1, (int)var6_3.length);
                var2_21 = var1_1;
                var3_22 = var6_3.length;
                var1_1 += var3_22;
            }
            catch (Exception var6_8) {
                var8_2[var2_21] = 0;
                var1_1 = var2_21 + 1;
            }
            var2_21 = var1_1;
            try {
                var6_3 = this.g.getBytes("GBK");
                var2_21 = var1_1++;
                var8_2[var1_1] = (byte)var6_3.length;
                var2_21 = var1_1;
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var1_1, (int)var6_3.length);
                var2_21 = var1_1;
                var3_22 = var6_3.length;
                var1_1 += var3_22;
            }
            catch (Exception var6_9) {
                var8_2[var2_21] = 0;
                var1_1 = var2_21 + 1;
            }
            var2_21 = var1_1;
            try {
                var6_3 = this.r.getBytes("GBK");
                var2_21 = var1_1++;
                var8_2[var1_1] = (byte)var6_3.length;
                var2_21 = var1_1;
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var1_1, (int)var6_3.length);
                var2_21 = var1_1;
                var3_22 = var6_3.length;
                var1_1 += var3_22;
            }
            catch (Exception var6_10) {
                var8_2[var2_21] = 0;
                var1_1 = var2_21 + 1;
            }
            var2_21 = var1_1;
            try {
                var6_3 = this.h.getBytes("GBK");
                var2_21 = var1_1++;
                var8_2[var1_1] = (byte)var6_3.length;
                var2_21 = var1_1;
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var1_1, (int)var6_3.length);
                var2_21 = var1_1;
                var3_22 = var6_3.length;
                var1_1 += var3_22;
            }
            catch (Exception var6_11) {
                var8_2[var2_21] = 0;
                var1_1 = var2_21 + 1;
            }
            var2_21 = var1_1;
            try {
                var6_3 = this.o.getBytes("GBK");
                var2_21 = var1_1++;
                var8_2[var1_1] = (byte)var6_3.length;
                var2_21 = var1_1;
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var1_1, (int)var6_3.length);
                var2_21 = var1_1;
                var3_22 = var6_3.length;
                var1_1 += var3_22;
            }
            catch (Exception var6_12) {
                var8_2[var2_21] = 0;
                var1_1 = var2_21 + 1;
            }
            var2_21 = var1_1;
            try {
                var6_3 = this.p.getBytes("GBK");
                var2_21 = var1_1++;
                var8_2[var1_1] = (byte)var6_3.length;
                var2_21 = var1_1;
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var1_1, (int)var6_3.length);
                var2_21 = var1_1;
                var3_22 = var6_3.length;
                var1_1 += var3_22;
            }
            catch (Exception var6_13) {
                var8_2[var2_21] = 0;
                var1_1 = var2_21 + 1;
            }
            if (TextUtils.isEmpty((CharSequence)this.q)) {
                var8_2[var1_1] = 0;
                ++var1_1;
            } else {
                var6_3 = this.a(this.q);
                var8_2[var1_1] = (byte)var6_3.length;
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)(++var1_1), (int)var6_3.length);
                var1_1 += var6_3.length;
            }
            var2_21 = var1_1;
            try {
                var6_3 = this.B.getBytes("GBK");
                var2_21 = var1_1++;
                var8_2[var1_1] = (byte)var6_3.length;
                var2_21 = var1_1;
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var1_1, (int)var6_3.length);
                var2_21 = var1_1;
                var3_22 = var6_3.length;
                var1_1 += var3_22;
            }
            catch (Exception var6_14) {
                var8_2[var2_21] = 0;
                var1_1 = var2_21 + 1;
            }
            var2_21 = var1_1;
            try {
                var6_3 = this.C.getBytes("GBK");
                var2_21 = var1_1++;
                var8_2[var1_1] = (byte)var6_3.length;
                var2_21 = var1_1;
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var1_1, (int)var6_3.length);
                var2_21 = var1_1;
                var3_22 = var6_3.length;
                var1_1 += var3_22;
            }
            catch (Exception var6_15) {
                var8_2[var2_21] = 0;
                var1_1 = var2_21 + 1;
            }
            var2_21 = var1_1;
            try {
                var6_3 = this.s.getBytes("GBK");
                var2_21 = var1_1++;
                var8_2[var1_1] = (byte)var6_3.length;
                var2_21 = var1_1;
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var1_1, (int)var6_3.length);
                var2_21 = var1_1;
                var3_22 = var6_3.length;
                var1_1 += var3_22;
            }
            catch (Exception var6_16) {
                var8_2[var2_21] = 0;
                var1_1 = var2_21 + 1;
            }
            var8_2[var1_1] = Byte.parseByte((String)this.t);
            var8_2[++var1_1] = Byte.parseByte((String)this.j);
            var1_1 = var2_21 = var1_1 + 1;
            if (this.j.equals((Object)"1")) {
                try {
                    var8_2[var2_21] = Byte.parseByte((String)this.D);
                    var1_1 = var2_21 + 1;
                }
                catch (Throwable var6_17) {
                    var6_17.printStackTrace();
                    var1_1 = var2_21;
                }
            }
            if (this.j.equals((Object)"1")) ** GOTO lbl-1000
            var2_21 = var1_1;
            if (this.j.equals((Object)"2")) lbl-1000: // 2 sources:
            {
                var6_3 = ahv.a(Integer.parseInt((String)this.k));
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var1_1, (int)var6_3.length);
                var2_21 = var1_1 + var6_3.length;
            }
            if (this.j.equals((Object)"1")) ** GOTO lbl-1000
            var1_1 = var2_21;
            if (this.j.equals((Object)"2")) lbl-1000: // 2 sources:
            {
                var6_3 = ahv.a(Integer.parseInt((String)this.l));
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var2_21, (int)var6_3.length);
                var1_1 = var2_21 + var6_3.length;
            }
            if (this.j.equals((Object)"1")) ** GOTO lbl-1000
            var2_21 = var1_1;
            if (this.j.equals((Object)"2")) lbl-1000: // 2 sources:
            {
                var6_3 = ahv.b(this.m);
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var1_1, (int)var6_3.length);
                var2_21 = var1_1 + var6_3.length;
            }
            var8_2[var2_21] = Byte.parseByte((String)this.u);
            ++var2_21;
            if (!this.u.equals((Object)"1")) ** GOTO lbl259
            var6_3 = ahv.b(this.b("mcc"));
            System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var2_21, (int)var6_3.length);
            var1_1 = var2_21 + var6_3.length;
            var6_3 = ahv.b(this.b("mnc"));
            System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var1_1, (int)var6_3.length);
            var6_3 = ahv.b(this.b("lac"));
            System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)(var1_1 += var6_3.length), (int)var6_3.length);
            var6_3 = ahv.a(this.b("cellid"));
            System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)(var1_1 += var6_3.length), (int)var6_3.length);
            var3_22 = var6_3.length + var1_1;
            var2_21 = Integer.parseInt((String)this.b("signal"));
            if (var2_21 > 127) {
                var1_1 = 0;
            } else {
                var1_1 = var2_21;
                if (var2_21 < -128) {
                    var1_1 = 0;
                }
            }
            var8_2[var3_22] = (byte)var1_1;
            var1_1 = var3_22 + 1;
            if (this.w.length() != 0) ** GOTO lbl254
            var8_2[var1_1] = 0;
            ++var1_1;
            ** GOTO lbl286
lbl254: // 1 sources:
            var4_23 = this.w.split("\\*").length;
            var8_2[var1_1] = (byte)var4_23;
            var2_21 = var1_1 + 1;
            var3_22 = 0;
            ** GOTO lbl384
lbl259: // 1 sources:
            var1_1 = var2_21;
            if (this.u.equals((Object)"2")) {
                var6_3 = ahv.b(this.b("mcc"));
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var2_21, (int)var6_3.length);
                var1_1 = var2_21 + var6_3.length;
                var6_3 = ahv.b(this.b("sid"));
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var1_1, (int)var6_3.length);
                var6_3 = ahv.b(this.b("nid"));
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)(var1_1 += var6_3.length), (int)var6_3.length);
                var6_3 = ahv.b(this.b("bid"));
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)(var1_1 += var6_3.length), (int)var6_3.length);
                var6_3 = ahv.a(this.b("lon"));
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)(var1_1 += var6_3.length), (int)var6_3.length);
                var6_3 = ahv.a(this.b("lat"));
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)(var1_1 += var6_3.length), (int)var6_3.length);
                var3_22 = var6_3.length + var1_1;
                var2_21 = Integer.parseInt((String)this.b("signal"));
                if (var2_21 > 127) {
                    var1_1 = 0;
                } else {
                    var1_1 = var2_21;
                    if (var2_21 < -128) {
                        var1_1 = 0;
                    }
                }
                var8_2[var3_22] = (byte)var1_1;
                var1_1 = var3_22 + 1;
                var8_2[var1_1] = 0;
                ++var1_1;
            }
lbl286: // 5 sources:
            do {
                if (this.x.length() == 0) {
                    var8_2[var1_1] = 0;
                    ++var1_1;
                } else {
                    block88 : {
                        var8_2[var1_1] = 1;
                        var1_1 = var2_21 = var1_1 + 1;
                        var6_3 = this.x.split(",");
                        var1_1 = var2_21;
                        var7_25 = this.a((String)var6_3[0]);
                        var1_1 = var2_21;
                        System.arraycopy((Object)var7_25, (int)0, (Object)var8_2, (int)var2_21, (int)var7_25.length);
                        var1_1 = var2_21;
                        var3_22 = var7_25.length;
                        var2_21 = var3_22 = var2_21 + var3_22;
                        var1_1 = var3_22;
                        var7_25 = var6_3[2].getBytes("GBK");
                        var2_21 = var3_22;
                        var1_1 = var3_22++;
                        var8_2[var3_22] = (byte)var7_25.length;
                        var2_21 = var3_22;
                        var1_1 = var3_22;
                        System.arraycopy((Object)var7_25, (int)0, (Object)var8_2, (int)var3_22, (int)var7_25.length);
                        var2_21 = var3_22;
                        var1_1 = var3_22;
                        var4_23 = var7_25.length;
                        var2_21 = var3_22 + var4_23;
                        var1_1 = var2_21;
                        var4_23 = Integer.parseInt((String)var6_3[1]);
                        if (var4_23 > 127) {
                            var3_22 = 0;
                            break block88;
                        }
                        var3_22 = var4_23;
                        if (var4_23 >= -128) break block88;
                        var3_22 = 0;
                    }
                    var1_1 = var2_21;
                    try {
                        var8_2[var2_21] = Byte.parseByte((String)String.valueOf((int)var3_22));
                        var1_1 = var2_21 + 1;
                    }
                    catch (Throwable var6_18) {
                        var6_3 = this.a("00:00:00:00:00:00");
                        System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var1_1, (int)var6_3.length);
                        var8_2[var1_1 += var6_3.length] = 0;
                        var8_2[++var1_1] = Byte.parseByte((String)"0");
                        ++var1_1;
                    }
                }
                var6_3 = this.y.split("\\*");
                if (!TextUtils.isEmpty((CharSequence)this.y) && var6_3.length != 0) {
                    var8_2[var1_1] = (byte)var6_3.length;
                    var2_21 = var1_1 + 1;
                    break block89;
                }
                var8_2[var1_1] = 0;
                ++var1_1;
lbl342: // 5 sources:
                do {
                    block90 : {
                        var2_21 = var1_1;
                        var7_25 = this.z.getBytes("GBK");
                        var6_3 = var7_25;
                        var2_21 = var1_1;
                        if (var7_25.length > 127) {
                            var6_3 = null;
                        }
                        if (var6_3 == null) {
                            var8_2[var1_1] = 0;
                            ++var1_1;
                            break block90;
                        }
                        var2_21 = var1_1++;
                        var8_2[var1_1] = (byte)var6_3.length;
                        var2_21 = var1_1;
                        try {
                            System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var1_1, (int)var6_3.length);
                            var2_21 = var1_1;
                            var3_22 = var6_3.length;
                            var1_1 += var3_22;
                        }
                        catch (Exception var6_20) {
                            var8_2[var2_21] = 0;
                            var1_1 = var2_21 + 1;
                        }
                    }
                    var2_21 = this.A != null ? this.A.length : 0;
                    var6_3 = ahv.b(var2_21);
                    System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var1_1, (int)var6_3.length);
                    var1_1 = var3_22 = var1_1 + var6_3.length;
                    if (var2_21 > 0) {
                        System.arraycopy((Object)this.A, (int)0, (Object)var8_2, (int)var3_22, (int)this.A.length);
                        var1_1 = var3_22 + this.A.length;
                    }
                    var6_3 = new byte[var1_1];
                    System.arraycopy((Object)var8_2, (int)0, (Object)var6_3, (int)0, (int)var1_1);
                    var7_25 = new CRC32();
                    var7_25.update((byte[])var6_3);
                    var7_25 = ahv.a(var7_25.getValue());
                    var8_2 = new byte[var7_25.length + var1_1];
                    System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)0, (int)var1_1);
                    System.arraycopy((Object)var7_25, (int)0, (Object)var8_2, (int)var1_1, (int)var7_25.length);
                    var1_1 = var7_25.length;
                    return var8_2;
                    break;
                } while (true);
                break;
            } while (true);
lbl384: // 1 sources:
            do {
                var1_1 = var2_21;
                if (var3_22 >= var4_23) ** continue;
                var6_3 = ahv.b(this.a("lac", var3_22));
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var2_21, (int)var6_3.length);
                var1_1 = var2_21 + var6_3.length;
                var6_3 = ahv.a(this.a("cellid", var3_22));
                System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var1_1, (int)var6_3.length);
                var5_24 = var6_3.length + var1_1;
                var2_21 = Integer.parseInt((String)this.a("signal", var3_22));
                if (var2_21 > 127) {
                    var1_1 = 0;
                } else {
                    var1_1 = var2_21;
                    if (var2_21 < -128) {
                        var1_1 = 0;
                    }
                }
                var8_2[var5_24] = (byte)var1_1;
                ++var3_22;
                var2_21 = var5_24 + 1;
            } while (true);
        }
        for (var3_22 = 0; var3_22 < var6_3.length; ++var3_22, ++var2_21) {
            var7_25 = var6_3[var3_22].split(",");
            var9_27 = this.a((String)var7_25[0]);
            System.arraycopy((Object)var9_27, (int)0, (Object)var8_2, (int)var2_21, (int)var9_27.length);
            var1_1 = var2_21 += var9_27.length;
            try {
                var9_27 = var7_25[2].getBytes("GBK");
                var1_1 = var2_21++;
                var8_2[var2_21] = (byte)var9_27.length;
                var1_1 = var2_21;
                System.arraycopy((Object)var9_27, (int)0, (Object)var8_2, (int)var2_21, (int)var9_27.length);
                var1_1 = var2_21;
                var4_23 = var9_27.length;
                var2_21 += var4_23;
            }
            catch (Exception var9_28) {
                var8_2[var1_1] = 0;
                var2_21 = var1_1 + 1;
            }
            if ((var4_23 = Integer.parseInt((String)var7_25[1])) > 127) {
                var1_1 = 0;
            } else {
                var1_1 = var4_23;
                if (var4_23 < -128) {
                    var1_1 = 0;
                }
            }
            var8_2[var2_21] = Byte.parseByte((String)String.valueOf((int)var1_1));
        }
        var1_1 = var2_21;
        if (this.E == null) ** GOTO lbl342
        var1_1 = var2_21;
        if (this.E.length() <= 0) ** GOTO lbl342
        try {
            var6_3 = ahv.b(Integer.parseInt((String)this.E));
            System.arraycopy((Object)var6_3, (int)0, (Object)var8_2, (int)var2_21, (int)var6_3.length);
            var1_1 = var6_3.length;
            var1_1 = var2_21 + var1_1;
        }
        catch (Throwable var6_19) {
            var6_19.printStackTrace();
            var1_1 = var2_21;
            ** continue;
        }
    }
}

