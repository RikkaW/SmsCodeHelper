/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Environment
 *  android.text.TextUtils
 *  java.io.File
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.Hashtable
 *  java.util.Map$Entry
 *  org.json.JSONException
 *  org.json.JSONObject
 */
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.amap.api.location.core.c;
import com.amap.api.location.core.e;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class ahg {
    String a = null;
    private LinkedHashMap<String, List<a>> b = new LinkedHashMap();
    private ahu c = null;
    private long d = 0;

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    ahg(Context context) {
        try {
            if (this.a == null) {
                this.a = e.a("MD5", c.b());
            }
            if (context == null) return;
        }
        catch (Throwable var1_2) {
            return;
        }
        context = this.a(context);
        if (context == null) return;
        this.c = ahu.a((File)context, 1, 0x100000);
    }

    private double a(double[] arrd, double[] arrd2) {
        double d2 = 0.0;
        double d3 = 0.0;
        double d4 = 0.0;
        for (int i2 = 0; i2 < arrd.length; ++i2) {
            d3 += arrd[i2] * arrd[i2];
            d2 += arrd2[i2] * arrd2[i2];
            d4 += arrd[i2] * arrd2[i2];
        }
        return d4 / (Math.sqrt((double)d3) * Math.sqrt((double)d2));
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private a a(String var1_1, StringBuilder var2_2, String var3_3, String var4_4) {
        var11_5 = null;
        var16_6 = new Hashtable();
        var17_7 = new Hashtable();
        var18_8 = new Hashtable();
        var12_9 = var4_4.equals((Object)"mem") != false ? this.b.entrySet().iterator() : null;
        var7_10 = true;
        block0 : do {
            if (var12_9 == null || !var12_9.hasNext()) ** GOTO lbl-1000
            if (var7_10) {
                var14_16 = this.b.get(var1_1);
                var13_15 = var1_1;
                var7_10 = false;
            } else {
                var14_16 = var12_9.next();
                var13_15 = (String)var14_16.getKey();
                var14_16 = (List)var14_16.getValue();
            }
            if (var11_5 == null) {
                if (var14_16 == null) continue;
            } else lbl-1000: // 2 sources:
            {
                var16_6.clear();
                var17_7.clear();
                var18_8.clear();
                return var11_5;
            }
            var9_13 = 0;
            block1 : do {
                if (var9_13 >= var14_16.size()) continue block0;
                var15_17 = (a)var14_16.get(var9_13);
                if (!TextUtils.isEmpty((CharSequence)var15_17.b()) && !TextUtils.isEmpty((CharSequence)var2_2) && var13_15.indexOf(var3_3) != -1) {
                    var8_12 = this.a(var15_17.b(), var2_2) ? var15_17.a().g() <= 300.0f : false;
                    this.a(var15_17.b(), var16_6);
                    this.a(var2_2.toString(), var17_7);
                    var18_8.clear();
                    var19_18 = var16_6.keySet().iterator();
                    while (var19_18.hasNext()) {
                        var18_8.put((Object)((String)var19_18.next()), (Object)"");
                    }
                    var19_18 = var17_7.keySet().iterator();
                    while (var19_18.hasNext()) {
                        var18_8.put((Object)((String)var19_18.next()), (Object)"");
                    }
                    var19_18 = var18_8.keySet();
                    var20_19 = new double[var19_18.size()];
                    var21_20 = new double[var19_18.size()];
                    var22_21 = var19_18.iterator();
                    var10_14 = 0;
                    break;
                }
lbl47: // 4 sources:
                do {
                    ++var9_13;
                    continue block1;
                    break;
                } while (true);
                break;
            } while (true);
            while (var22_21.hasNext()) {
                var23_22 = (String)var22_21.next();
                var5_11 = var16_6.containsKey((Object)var23_22) != false ? 1.0 : 0.0;
                var20_19[var10_14] = var5_11;
                var5_11 = var17_7.containsKey((Object)var23_22) != false ? 1.0 : 0.0;
                var21_20[var10_14] = var5_11;
                ++var10_14;
            }
            var19_18.clear();
            var5_11 = this.a(var20_19, var21_20);
            if (!var4_4.equals((Object)"mem")) ** GOTO lbl67
            if (var8_12 && var5_11 > 0.8500000238418579) {
                var11_5 = var15_17;
                continue;
            }
            if (var5_11 <= 0.8500000238418579) ** GOTO lbl47
            var11_5 = var15_17;
            continue;
lbl67: // 1 sources:
            if (!var4_4.equals((Object)"db") || var5_11 <= 0.8500000238418579) ** continue;
            var11_5 = var15_17;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private File a(Context context) {
        String string2 = Environment.getExternalStorageState();
        StringBuilder stringBuilder = null;
        if (!"mounted".equals((Object)string2)) return stringBuilder;
        stringBuilder = new StringBuilder();
        stringBuilder.append(context.getExternalCacheDir().getAbsolutePath()).append(File.separator);
        stringBuilder.append("locationCache");
        try {
            this.a(new File(stringBuilder.toString()));
        }
        catch (Throwable var3_3) {
            var3_3.printStackTrace();
        }
        stringBuilder.delete(0, stringBuilder.length());
        stringBuilder.append(context.getExternalCacheDir().getAbsolutePath()).append(File.separator);
        stringBuilder.append("newlocationCache");
        return new File(stringBuilder.toString());
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(File file) {
        if (file == null || !file.exists()) return;
        {
            if (file.isFile()) {
                file.delete();
                return;
            } else {
                if (!file.isDirectory()) return;
                {
                    File[] arrfile = file.listFiles();
                    int n2 = 0;
                    do {
                        if (n2 >= arrfile.length) {
                            file.delete();
                            return;
                        }
                        this.a(arrfile[n2]);
                        ++n2;
                    } while (true);
                }
            }
        }
    }

    private void a(String arrstring, Hashtable<String, String> hashtable) {
        hashtable.clear();
        for (String string2 : arrstring.split("#")) {
            if (string2.length() <= 0) continue;
            hashtable.put((Object)string2, (Object)"");
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean a(String object, String string2) {
        double d2;
        Hashtable hashtable = new Hashtable();
        Hashtable hashtable2 = new Hashtable();
        Hashtable hashtable3 = new Hashtable();
        this.a(string2, hashtable);
        this.a((String)object, hashtable2);
        hashtable3.clear();
        object = hashtable.keySet().iterator();
        while (object.hasNext()) {
            hashtable3.put((Object)((String)object.next()), (Object)"");
        }
        object = hashtable2.keySet().iterator();
        while (object.hasNext()) {
            hashtable3.put((Object)((String)object.next()), (Object)"");
        }
        object = hashtable3.keySet();
        double[] arrd = new double[object.size()];
        double[] arrd2 = new double[object.size()];
        Iterator iterator = object.iterator();
        int n2 = 0;
        while (iterator.hasNext()) {
            String string3 = (String)iterator.next();
            d2 = hashtable.containsKey((Object)string3) ? 1.0 : 0.0;
            arrd[n2] = d2;
            d2 = hashtable2.containsKey((Object)string3) ? 1.0 : 0.0;
            arrd2[n2] = d2;
            ++n2;
        }
        object.clear();
        d2 = this.a(arrd, arrd2);
        hashtable.clear();
        hashtable2.clear();
        hashtable3.clear();
        if (d2 > 0.8500000238418579) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private boolean a(String string2, StringBuilder stringBuilder) {
        int n2 = string2.indexOf(",access");
        if (n2 == -1) return false;
        if (n2 < 17) {
            return false;
        }
        if (stringBuilder.indexOf(",access") == -1) return false;
        string2 = string2.substring(n2 - 17, n2);
        if (stringBuilder.toString().indexOf(string2 + ",access") == -1) return false;
        return true;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    ahf a(String var1_1, StringBuilder var2_11, String var3_12) {
        block32 : {
            if (var3_12.equals((Object)"mem") && !ahk.k) {
                this.a();
                return null;
            }
            if (var1_1 != null && var1_1.indexOf("#cellwifi") != -1) {
                if ((var3_12 = this.a((String)var1_1, (StringBuilder)var2_11, "#cellwifi", (String)var3_12)) != null) {
                    // empty if block
                }
            } else if (var1_1 != null && var1_1.indexOf("#wifi") != -1) {
                if ((var3_12 = this.a((String)var1_1, (StringBuilder)var2_11, "#wifi", (String)var3_12)) == null) {
                    // empty if block
                }
            } else if (var1_1 != null && var1_1.indexOf("#cell") != -1) {
                var3_12 = var3_12.equals((Object)"mem") != false && (var3_12 = this.b.get(var1_1)) != null && var3_12.size() > 0 ? var3_12.get(var3_12.size() - 1) : null;
                if (var3_12 == null) {
                    // empty if block
                }
            } else {
                var3_12 = null;
            }
            if (this.a != null) ** GOTO lbl21
            this.a = c.b();
            if (var3_12 != null) ** GOTO lbl41
lbl21: // 2 sources:
            if (var2_11 == null) ** GOTO lbl-1000
            var4_13 = var2_11;
            try {
                if (var2_11.length() == 0) lbl-1000: // 2 sources:
                {
                    var4_13 = new StringBuilder("cell#");
                }
                var2_11 = this.c != null ? this.c.a((String)var1_1) : null;
                if (var2_11 != null) {
                    var5_14 = var2_11.entrySet().iterator();
                    var2_11 = var3_12;
                    break block32;
                }
                ** GOTO lbl61
            }
            catch (Throwable var1_2) {
                var1_1 = var3_12;
                ** GOTO lbl88
            }
            catch (IOException var1_5) {
                var1_1 = var3_12;
                ** GOTO lbl88
            }
            catch (JSONException var1_8) {
                var1_1 = var3_12;
                ** GOTO lbl88
            }
lbl41: // 1 sources:
            var1_1 = var3_12;
            ** GOTO lbl88
        }
        do {
            block35 : {
                block34 : {
                    block33 : {
                        var3_12 = var2_11;
                        if (var5_14 != null) {
                            var3_12 = var2_11;
                            if (!var5_14.hasNext()) break block33;
                            var3_12 = var5_14.next();
                            if (!this.a(e.b((String)var3_12.getKey(), this.a), var4_13.toString())) continue;
                            var6_15 = new ahf(new JSONObject(e.b((String)var3_12.getValue(), this.a)));
                            var6_15.g("mem");
                            var3_12 = new a();
                            var3_12.a(var6_15);
                            var3_12.a(var4_13.toString());
                            if (this.b == null) {
                                this.b = new LinkedHashMap<K, V>();
                            }
                            if (var1_1 != null) break block34;
                        }
                    }
                    var1_1 = var3_12;
                    ** GOTO lbl88
                }
                if (!this.b.containsKey(var1_1)) {
                    var2_11 = new ArrayList();
                    var2_11.add((Object)var3_12);
                    this.b.put(var1_1, var2_11);
                }
                var2_11 = this.b.get(var1_1);
                if (var2_11 != null && !var2_11.contains(var3_12)) {
                    var2_11.add(0, var3_12);
                }
                if (var2_11 != null) {
                    this.b.remove(var1_1);
                    this.b.put(var1_1, var2_11);
                }
                break block35;
                catch (Throwable var1_3) {
                    var1_1 = var2_11;
                }
                catch (Throwable var1_4) {}
                catch (IOException var1_6) {
                    var1_1 = var2_11;
                }
                catch (IOException var1_7) {}
                catch (JSONException var1_9) {
                    var1_1 = var2_11;
                }
lbl88: // 8 sources:
                if (var1_1 == null) return null;
                return var1_1.a();
                catch (JSONException var1_10) {}
            }
            finally {
                var2_11 = var3_12;
                continue;
            }
            break;
        } while (true);
    }

    void a() {
        this.d = 0;
        this.b.clear();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void a(String iterator, ahf object, StringBuilder hashMap) {
        block28 : {
            int n2;
            List<a> list;
            block29 : {
                Object object2;
                if (!ahk.k) {
                    this.a();
                    return;
                }
                if (!this.a((String)((Object)iterator), (ahf)object) || object.i().equals((Object)"mem")) return;
                if (iterator != null && iterator.contains((CharSequence)"wifi")) {
                    if (TextUtils.isEmpty((CharSequence)hashMap)) return;
                    if (object.g() >= 300.0f) {
                        object2 = hashMap.toString().split("#");
                        int n3 = object2.length;
                        int n4 = 0;
                        for (n2 = 0; n2 < n3; ++n2) {
                            int n5 = n4;
                            if (object2[n2].contains((CharSequence)",")) {
                                n5 = n4 + 1;
                            }
                            n4 = n5;
                        }
                        if (n4 >= 6) return;
                    } else if (object.g() <= 10.0f) {
                        return;
                    }
                } else if (iterator != null && iterator.contains((CharSequence)"cell") && hashMap.indexOf(",") != -1) {
                    return;
                }
                this.d = ahz.b();
                object2 = new String[]();
                object.g("mem");
                object2.a((ahf)object);
                if (hashMap != null) {
                    object2.a(hashMap.toString());
                }
                if (this.b == null) {
                    this.b = new LinkedHashMap();
                }
                if (iterator == null) return;
                if (this.b.containsKey((Object)iterator)) {
                    list = this.b.get(iterator);
                    if (list != null && !list.contains(object2)) {
                        list.add(0, (a)object2);
                    }
                    if (list != null) {
                        this.b.remove((Object)iterator);
                        this.b.put((Object)iterator, (Object)list);
                    }
                } else {
                    list = new ArrayList();
                    list.add((a)object2);
                    this.b.put((Object)iterator, (Object)list);
                }
                try {
                    block27 : {
                        if (this.a == null) {
                            this.a = e.a("MD5", c.b());
                        }
                        if (hashMap != null) {
                            object2 = hashMap;
                            if (hashMap.length() != 0) break block27;
                        }
                        object2 = new StringBuilder("cell#");
                    }
                    hashMap = this.c != null ? this.c.a((String)((Object)iterator)) : null;
                    list = e.d(object2.toString().getBytes(), this.a);
                    object = e.d(object.u().getBytes(), this.a);
                    if (hashMap == null || hashMap.size() == 0) {
                        hashMap = new HashMap();
                        hashMap.put((Object)list, object);
                        if (this.c != null) {
                            this.c.b((String)((Object)iterator), (Map<String, ? extends Serializable>)hashMap);
                        }
                        break block28;
                    }
                    Iterator iterator2 = hashMap.entrySet().iterator();
                    while (iterator2 != null && iterator2.hasNext()) {
                        Map.Entry entry = iterator2.next();
                        if (!this.a(e.b((String)entry.getKey(), this.a), object2.toString())) continue;
                        hashMap.remove(entry.getKey());
                        hashMap.put(list, object);
                        this.c.b((String)((Object)iterator), (Map<String, ? extends Serializable>)hashMap);
                        n2 = 0;
                        break block29;
                    }
                }
                catch (IOException var1_2) {
                }
                catch (Exception var1_3) {
                }
                n2 = 1;
            }
            if (n2 != 0) {
                hashMap.put(list, object);
                this.c.b((String)((Object)iterator), (Map<String, ? extends Serializable>)hashMap);
            }
        }
        if (this.b.size() <= 360) return;
        iterator = this.b.entrySet().iterator();
        if (iterator == null || !iterator.hasNext()) return;
        {
            iterator = (String)iterator.next().getKey();
            this.b.remove((Object)iterator);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    boolean a(String string2, ahf ahf2) {
        if (string2 == null || ahf2 == null || string2.indexOf("#network") == -1 || ahf2.e() == 0.0) {
            return false;
        }
        return true;
    }

    void b() {
        if (this.c != null) {
            this.c.a();
        }
    }

    static class a {
        private ahf a = null;
        private String b = null;

        protected a() {
        }

        public ahf a() {
            return this.a;
        }

        public void a(ahf ahf2) {
            this.a = ahf2;
        }

        public void a(String string2) {
            this.b = string2.replace((CharSequence)"##", (CharSequence)"#");
        }

        public String b() {
            return this.b;
        }
    }

}

