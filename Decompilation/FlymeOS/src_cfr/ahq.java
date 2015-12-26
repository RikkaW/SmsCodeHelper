/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.database.Cursor
 *  android.net.NetworkInfo
 *  android.net.Proxy
 *  android.net.Uri
 *  android.telephony.TelephonyManager
 *  android.text.TextUtils
 *  android.util.SparseArray
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.net.InetSocketAddress
 *  java.net.Proxy
 *  java.net.ProxySelector
 *  java.net.URI
 *  java.util.Locale
 *  org.apache.http.Header
 *  org.apache.http.HttpHost
 *  org.apache.http.HttpResponse
 *  org.apache.http.client.HttpClient
 *  org.apache.http.conn.ClientConnectionManager
 *  org.apache.http.conn.scheme.PlainSocketFactory
 *  org.apache.http.conn.scheme.Scheme
 *  org.apache.http.conn.scheme.SchemeRegistry
 *  org.apache.http.conn.scheme.SocketFactory
 *  org.apache.http.impl.client.DefaultHttpClient
 *  org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager
 *  org.apache.http.params.BasicHttpParams
 *  org.apache.http.params.HttpParams
 *  org.apache.http.params.HttpProtocolParams
 *  org.json.JSONException
 *  org.json.JSONObject
 */
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.NetworkInfo;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.SparseArray;
import com.amap.api.location.core.c;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.List;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONException;
import org.json.JSONObject;

public class ahq {
    private static ahq a = null;

    private ahq() {
    }

    /*
     * Enabled aggressive block sorting
     */
    public static int a(NetworkInfo networkInfo) {
        if (networkInfo == null || !networkInfo.isAvailable() || !networkInfo.isConnected()) {
            return -1;
        }
        return networkInfo.getType();
    }

    public static ahq a() {
        if (a == null) {
            a = new ahq();
        }
        return a;
    }

    public static String a(TelephonyManager telephonyManager) {
        int n2 = 0;
        if (telephonyManager != null) {
            n2 = telephonyManager.getNetworkType();
        }
        return (String)ahk.l.get(n2, (Object)"UNKNOWN");
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static Proxy a(Context object) {
        object = ProxySelector.getDefault();
        try {
            object = object.select(new URI(c.j()));
            if (object == null) return null;
            if (object.isEmpty()) return null;
        }
        catch (Exception var0_1) {
            return null;
        }
        Proxy proxy = (Proxy)object.get(0);
        if (proxy == null) return null;
        object = proxy;
        if (proxy.type() != Proxy.Type.DIRECT) return object;
        return null;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    public static HttpClient a(Context var0, NetworkInfo var1_4) {
        block34 : {
            block37 : {
                block35 : {
                    block38 : {
                        block41 : {
                            block39 : {
                                block42 : {
                                    block36 : {
                                        var3_11 = -1;
                                        var2_12 = 1;
                                        var5_13 = null;
                                        var8_14 = new BasicHttpParams();
                                        if (var1_4.getType() != 0) ** GOTO lbl174
                                        if (ahz.c() < 11) ** GOTO lbl26
                                        if ((var0 = ahq.a((Context)var0)) == null) ** GOTO lbl170
                                        try {
                                            var0 = (InetSocketAddress)var0.address();
lbl10: // 2 sources:
                                            ** while (var0 != null)
                                        }
                                        catch (Exception var0_1) {
                                            var0 = null;
                                            ** GOTO lbl10
                                        }
lbl-1000: // 1 sources:
                                        {
                                            var1_4 = var0.getHostName();
                                            var2_12 = var0.getPort();
                                            var0 = var1_4;
lbl14: // 8 sources:
                                            do {
                                                if (ahq.a((String)var0, var2_12)) {
                                                    var8_14.setParameter("http.route.default-proxy", (Object)new HttpHost((String)var0, var2_12, "http"));
                                                }
                                                ahz.a((HttpParams)var8_14, 30000);
                                                HttpProtocolParams.setUseExpectContinue((HttpParams)var8_14, (boolean)false);
                                                var0 = new SchemeRegistry();
                                                var0.register(new Scheme("http", (SocketFactory)PlainSocketFactory.getSocketFactory(), 80));
                                                return new DefaultHttpClient((ClientConnectionManager)new ThreadSafeClientConnManager((HttpParams)var8_14, (SchemeRegistry)var0), (HttpParams)var8_14);
                                                break;
                                            } while (true);
lbl22: // 1 sources:
                                            break block34;
                                        }
lbl26: // 1 sources:
                                        var6_15 = Uri.parse((String)"content://telephony/carriers/preferapn");
                                        var0 = var0.getContentResolver();
                                        var5_13 = var0 = var0.query((Uri)var6_15, null, null, null, null);
                                        if (var5_13 == null) break block35;
                                        var0 = var5_13;
                                        if (!var5_13.moveToFirst()) break block35;
                                        var0 = var5_13;
                                        var6_15 = var7_16 = var5_13.getString(var5_13.getColumnIndex("apn"));
                                        if (var7_16 != null) {
                                            var0 = var5_13;
                                            var6_15 = var7_16.toLowerCase(Locale.US);
                                            var0 = var5_13;
                                            ahz.a(new Object[]{"nm|found apn:", var6_15});
                                        }
                                        if (var6_15 == null) break block36;
                                        var0 = var5_13;
                                        if (!var6_15.contains((CharSequence)"ctwap")) break block36;
                                        var0 = var5_13;
                                        var6_15 = ahq.b();
                                        var0 = var5_13;
                                        if (TextUtils.isEmpty((CharSequence)var6_15)) break block37;
                                        var0 = var5_13;
                                        var4_17 = var6_15.equals((Object)"null");
                                        if (var4_17) break block37;
                                        var2_12 = 1;
                                        var0 = var6_15;
lbl53: // 2 sources:
                                        do {
                                            if (var2_12 == 0) {
                                                var0 = "10.0.0.200";
                                            }
                                            var3_11 = 80;
                                            var1_4 = var0;
lbl58: // 3 sources:
                                            do {
                                                var0 = var1_4;
                                                var2_12 = var3_11;
                                                if (var5_13 == null) ** GOTO lbl14
                                                var2_12 = var3_11;
                                                var0 = var1_4;
lbl64: // 2 sources:
                                                do {
                                                    var5_13.close();
                                                    ** GOTO lbl14
                                                    break;
                                                } while (true);
                                                break;
                                            } while (true);
                                            break;
                                        } while (true);
                                    }
                                    if (var6_15 == null) ** GOTO lbl163
                                    var0 = var5_13;
                                    try {
                                        if (!var6_15.contains((CharSequence)"wap")) break block35;
                                        var0 = var5_13;
                                        var6_15 = ahq.b();
                                        var0 = var5_13;
                                        if (TextUtils.isEmpty((CharSequence)var6_15)) break block38;
                                        var0 = var5_13;
                                        var4_18 = var6_15.equals((Object)"null");
                                        if (var4_18) break block38;
                                        var2_12 = 1;
                                        var0 = var6_15;
                                    }
                                    catch (Exception var1_10) {
                                        ** GOTO lbl122
                                    }
                                    catch (SecurityException var0_3) {
                                        ** GOTO lbl-1000
                                    }
lbl81: // 2 sources:
                                    do {
                                        if (var2_12 == 0) {
                                            var0 = "10.0.0.172";
                                        }
                                        var3_11 = 80;
                                        var1_4 = var0;
                                        ** GOTO lbl58
                                        break;
                                    } while (true);
                                    catch (SecurityException var0_2) lbl-1000: // 2 sources:
                                    {
                                        block40 : {
                                            var7_16 = null;
                                            var6_15 = null;
                                            if (var1_4.getExtraInfo() == null) break block39;
                                            var1_4 = var1_4.getExtraInfo().toLowerCase(Locale.US);
                                            var0 = ahq.b();
                                            if (var1_4.indexOf("ctwap") == -1) break block40;
                                            if (TextUtils.isEmpty((CharSequence)var0) || var0.equals((Object)"null")) break block41;
lbl96: // 2 sources:
                                            do {
                                                if (var2_12 == 0) {
                                                    var0 = "10.0.0.200";
                                                }
                                                var3_11 = 80;
                                                var1_4 = var0;
lbl101: // 3 sources:
                                                do {
                                                    var0 = var1_4;
                                                    var2_12 = var3_11;
                                                    if (var5_13 == null) ** GOTO lbl14
                                                    var5_13.close();
                                                    var0 = var1_4;
                                                    var2_12 = var3_11;
                                                    ** GOTO lbl14
                                                    break;
                                                } while (true);
                                                break;
                                            } while (true);
                                        }
                                        if (var1_4.indexOf("wap") == -1) break block39;
                                        if (TextUtils.isEmpty((CharSequence)var0) || var0.equals((Object)"null")) break block42;
                                        var2_12 = 1;
lbl114: // 2 sources:
                                        do {
                                            if (var2_12 == 0) {
                                                var0 = "10.0.0.200";
                                            }
                                            var3_11 = 80;
                                            var1_4 = var0;
                                            ** GOTO lbl101
                                            break;
                                        } while (true);
                                    }
                                    catch (Exception var1_5) {
                                        var5_13 = null;
lbl122: // 2 sources:
                                        var6_15 = null;
                                        var7_16 = null;
                                        var0 = var5_13;
                                        ahz.a((Throwable)var1_4);
                                        var0 = var7_16;
                                        var2_12 = var3_11;
                                        if (var5_13 == null) ** GOTO lbl14
                                        var0 = var6_15;
                                        var2_12 = var3_11;
                                        ** continue;
                                    }
                                    catch (Throwable var1_6) {
                                        var0 = null;
lbl135: // 3 sources:
                                        do {
                                            if (var0 != null) {
                                                var0.close();
                                            }
                                            throw var1_7;
                                            break;
                                        } while (true);
                                    }
                                    {
                                        catch (Throwable var1_8) {
                                            ** GOTO lbl135
                                        }
                                    }
                                    {
                                        catch (Throwable var1_9) {
                                            var0 = var5_13;
                                            ** continue;
                                        }
                                    }
                                }
                                var2_12 = 0;
                                var0 = var7_16;
                                ** while (true)
                            }
                            var1_4 = null;
                            ** while (true)
                        }
                        var2_12 = 0;
                        var0 = var6_15;
                        ** while (true)
                    }
                    var2_12 = 0;
                    var0 = null;
                    ** while (true)
                }
                var1_4 = null;
                ** while (true)
            }
            var2_12 = 0;
            var0 = null;
            ** while (true)
        }
        var2_12 = -1;
        var0 = null;
        ** GOTO lbl14
lbl174: // 1 sources:
        var0 = null;
        var2_12 = var3_11;
        ** while (true)
    }

    private static boolean a(String string2, int n2) {
        if (string2 != null && string2.length() > 0 && n2 != -1) {
            return true;
        }
        return false;
    }

    private static boolean a(HttpResponse httpResponse) {
        if ((httpResponse = httpResponse.getFirstHeader("Content-Encoding")) != null && httpResponse.getValue().equalsIgnoreCase("gzip")) {
            return true;
        }
        return false;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String[] a(JSONObject var0) {
        var1_2 = new String[]{null, null, null, null, null};
        if (var0 == null || c.j().length() == 0) {
            var1_2[0] = "false";
            return var1_2;
        }
        try {
            var2_3 = var0.getString("key");
            var3_4 = var0.getString("X-INFO");
            var4_5 = var0.getString("X-BIZ");
            var0 = var0.getString("User-Agent");
            ** if (TextUtils.isEmpty((CharSequence)var2_3) || TextUtils.isEmpty((CharSequence)var0)) goto lbl-1000
        }
        catch (JSONException var0_1) {}
lbl-1000: // 1 sources:
        {
            var1_2[0] = "true";
            var1_2[1] = var2_3;
            var1_2[2] = var3_4;
            var1_2[3] = var4_5;
            var1_2[4] = var0;
        }
lbl-1000: // 2 sources:
        {
        }
        if (var1_2[0] != null) {
            if (var1_2[0].equals((Object)"true") != false) return var1_2;
        }
        var1_2[0] = "true";
        return var1_2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static String b() {
        try {
            String string2 = android.net.Proxy.getDefaultHost();
            String string3 = string2;
            if (string2 != null) return string3;
            return "null";
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return "null";
        }
    }

    /*
     * Exception decompiling
     */
    public String a(Context var1_1, String var2_23, byte[] var3_35, String var4_42) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [90[SIMPLE_IF_TAKEN]], but top level block is 5[TRYBLOCK]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
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

    /*
     * Exception decompiling
     */
    public String a(byte[] var1_1, Context var2_9) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [38[UNCONDITIONALDOLOOP]], but top level block is 5[TRYBLOCK]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
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

    /*
     * Exception decompiling
     */
    public String a(byte[] var1_1, Context var2_27, JSONObject var3_39) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [79[SIMPLE_IF_TAKEN]], but top level block is 6[TRYBLOCK]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
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
}

