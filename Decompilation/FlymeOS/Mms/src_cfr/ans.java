/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.BufferedReader
 *  java.io.InputStreamReader
 *  java.io.Reader
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.HashMap
 *  java.util.Properties
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ans {
    private Properties a = new Properties();
    private Map<String, Properties> b = new HashMap();

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void a(InputStream var1_1) {
        var14_2 = new BufferedReader((Reader)new InputStreamReader((InputStream)var1_1, "utf-8"));
        var15_3 = new char[4096];
        var4_4 = var14_2.read(var15_3, 0, 4096);
        var10_5 = a.a;
        var6_6 = false;
        var11_7 = null;
        var12_8 = null;
        var8_9 = new StringBuilder();
        block8 : do {
            if (var4_4 < 0) {
                if (var8_9.length() <= 0) return;
                var1_1 = var8_9.toString().trim();
                new StringBuilder();
                if (var12_8 == null) return;
                if (var11_7 == null) {
                    this.a(var12_8, (String)var1_1);
                    return;
                }
                this.a(var11_7, var12_8, (String)var1_1);
                return;
            }
            var5_12 = 0;
            do {
                if (var5_12 >= var4_4) {
                    var4_4 = var14_2.read(var15_3, 0, 4096);
                    continue block8;
                }
                var2_10 = var15_3[var5_12];
                var1_1 = var10_5;
                if (var10_5 != a.d) ** GOTO lbl36
                if (var2_10 == '\r') ** GOTO lbl-1000
                var7_13 = var8_9;
                var9_14 = var12_8;
                var13_15 = var11_7;
                var3_11 = var6_6;
                var1_1 = var10_5;
                if (var2_10 == '\n') lbl-1000: // 2 sources:
                {
                    var1_1 = a.a;
lbl36: // 2 sources:
                    if (var1_1 == a.b) {
                        var8_9.append(var2_10);
                        if (var2_10 == '\r') {
                            var1_1 = a.c;
                            var3_11 = var6_6;
                            var13_15 = var11_7;
                            var9_14 = var12_8;
                            var7_13 = var8_9;
                        } else {
                            var1_1 = a.a;
                            var7_13 = var8_9;
                            var9_14 = var12_8;
                            var13_15 = var11_7;
                            var3_11 = var6_6;
                        }
                    } else {
                        switch (var2_10) {
                            default: {
                                var8_9.append(var2_10);
                                var7_13 = var8_9;
                                var9_14 = var12_8;
                                var13_15 = var11_7;
                                var3_11 = var6_6;
                                break;
                            }
                            case '[': {
                                var7_13 = new StringBuilder();
                                var3_11 = true;
                                var9_14 = var12_8;
                                var13_15 = var11_7;
                                break;
                            }
                            case ']': {
                                if (var6_6) {
                                    var13_15 = var8_9.toString().trim();
                                    var7_13 = new StringBuilder();
                                    this.b.put(var13_15, new Properties());
                                    var3_11 = false;
                                    var9_14 = var12_8;
                                    break;
                                }
                                var8_9.append(var2_10);
                                var7_13 = var8_9;
                                var9_14 = var12_8;
                                var13_15 = var11_7;
                                var3_11 = var6_6;
                                break;
                            }
                            case '\\': {
                                var1_1 = a.b;
                                var7_13 = var8_9;
                                var9_14 = var12_8;
                                var13_15 = var11_7;
                                var3_11 = var6_6;
                                break;
                            }
                            case '#': 
                            case ';': {
                                var1_1 = a.d;
                                var7_13 = var8_9;
                                var9_14 = var12_8;
                                var13_15 = var11_7;
                                var3_11 = var6_6;
                                break;
                            }
                            case ':': 
                            case '=': {
                                if (var12_8 == null) {
                                    var9_14 = var8_9.toString().trim();
                                    var7_13 = new StringBuilder();
                                    var13_15 = var11_7;
                                    var3_11 = var6_6;
                                    break;
                                }
                                var8_9.append(var2_10);
                                var7_13 = var8_9;
                                var9_14 = var12_8;
                                var13_15 = var11_7;
                                var3_11 = var6_6;
                                break;
                            }
                            case '\n': 
                            case '\r': {
                                if (var1_1 == a.c && var2_10 == '\n') {
                                    var8_9.append(var2_10);
                                    var1_1 = a.a;
                                    var7_13 = var8_9;
                                    var9_14 = var12_8;
                                    var13_15 = var11_7;
                                    var3_11 = var6_6;
                                    break;
                                }
                                var7_13 = var8_9;
                                if (var8_9.length() > 0) {
                                    var9_14 = var8_9.toString().trim();
                                    var7_13 = var8_9 = new StringBuilder();
                                    if (var12_8 != null) {
                                        if (var11_7 == null) {
                                            this.a(var12_8, var9_14);
                                            var7_13 = var8_9;
                                        } else {
                                            this.a(var11_7, var12_8, var9_14);
                                            var7_13 = var8_9;
                                        }
                                    }
                                }
                                var9_14 = null;
                                var13_15 = var11_7;
                                var3_11 = var6_6;
                            }
                        }
                    }
                }
                ++var5_12;
                var8_9 = var7_13;
                var12_8 = var9_14;
                var11_7 = var13_15;
                var6_6 = var3_11;
                var10_5 = var1_1;
            } while (true);
            break;
        } while (true);
    }

    public void a(String string2, String string3) {
        this.a.setProperty(string2, string3);
    }

    public void a(String string2, String string3, String string4) {
        Properties properties;
        Properties properties2 = properties = this.b.get(string2);
        if (properties == null) {
            properties2 = new Properties();
            this.b.put(string2, properties2);
        }
        properties2.setProperty(string3, string4);
    }

    public String b(String string2, String string3) {
        if ((string2 = this.b.get(string2)) == null) {
            return null;
        }
        return string2.getProperty(string3);
    }

    static enum a {
        a,
        b,
        c,
        d;
        

        private a(String string3, int n3) {
        }
    }

}

