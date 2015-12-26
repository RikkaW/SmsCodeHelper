/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Double
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 */
import java.util.List;

public final class aic {
    private boolean a = false;
    private String b = "";
    private boolean c = false;
    private double d = 0.0;
    private double e = 0.0;

    protected aic(List list, String string2, String string3, String string4) {
        this.b = string4;
        this.d();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void d() {
        var3_1 = 0;
        var4_2 = this.b;
        if (var4_2 == null || var4_2.length() <= 8) ** GOTO lbl-1000
        var2_4 = 0;
        for (var1_3 = 1; var1_3 < var4_2.length() - 3; var2_4 ^= var4_2.charAt((int)var1_3), ++var1_3) {
        }
        if (Integer.toHexString((int)var2_4).equalsIgnoreCase(var4_2.substring(var4_2.length() - 2, var4_2.length()))) {
            var1_3 = 1;
        } else lbl-1000: // 2 sources:
        {
            var1_3 = 0;
        }
        if (var1_3 != 0) {
            var4_2 = this.b.substring(0, this.b.length() - 3);
            var2_4 = 0;
            for (var1_3 = var3_1; var1_3 < var4_2.length(); ++var1_3) {
                var3_1 = var2_4;
                if (var4_2.charAt(var1_3) == ',') {
                    var3_1 = var2_4 + 1;
                }
                var2_4 = var3_1;
            }
            if ((var4_2 = var4_2.split(",", var2_4 + 1)).length < 6) {
                return;
            }
            if (!(var4_2[2].equals((Object)"") || var4_2[var4_2.length - 3].equals((Object)"") || var4_2[var4_2.length - 2].equals((Object)"") || var4_2[var4_2.length - 1].equals((Object)""))) {
                Integer.valueOf((String)var4_2[2]).intValue();
                this.d = Double.valueOf((String)var4_2[var4_2.length - 3]);
                this.e = Double.valueOf((String)var4_2[var4_2.length - 2]);
                this.c = true;
            }
        }
        this.a = this.c;
    }

    protected final boolean a() {
        return this.a;
    }

    protected final double b() {
        return this.d;
    }

    protected final double c() {
        return this.e;
    }
}

