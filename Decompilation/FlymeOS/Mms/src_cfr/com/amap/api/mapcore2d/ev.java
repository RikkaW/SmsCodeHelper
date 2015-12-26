/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  java.io.ByteArrayOutputStream
 *  java.io.DataOutputStream
 *  java.io.OutputStream
 *  java.io.PushbackInputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.Throwable
 *  java.net.HttpURLConnection
 *  java.net.Proxy
 *  java.net.URL
 *  java.net.URLConnection
 *  java.security.SecureRandom
 *  java.util.LinkedList
 *  java.util.Map$Entry
 *  java.util.zip.GZIPInputStream
 *  javax.net.ssl.HttpsURLConnection
 *  javax.net.ssl.SSLContext
 *  javax.net.ssl.SSLSocketFactory
 *  org.apache.http.HttpEntity
 *  org.apache.http.client.utils.URLEncodedUtils
 *  org.apache.http.message.BasicNameValuePair
 */
package com.amap.api.mapcore2d;

import android.os.Build;
import com.amap.api.mapcore2d.cz;
import com.amap.api.mapcore2d.ed;
import com.amap.api.mapcore2d.ew;
import com.amap.api.mapcore2d.fa;
import com.amap.api.mapcore2d.fb;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.apache.http.HttpEntity;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class ev {
    private static ew a;
    private static TrustManager g;
    private int b;
    private int c;
    private boolean d;
    private SSLContext e;
    private Proxy f;

    static {
        g = new fb();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    ev(int n2, int n3, Proxy proxy, boolean bl2) {
        this.b = n2;
        this.c = n3;
        this.f = proxy;
        this.d = bl2;
        if (!bl2) return;
        try {
            proxy = SSLContext.getInstance((String)"TLS");
            proxy.init(null, new TrustManager[]{g}, null);
            this.e = proxy;
            return;
        }
        catch (NoSuchAlgorithmException var3_4) {
            ed.a(var3_4, "HttpUrlUtil", "HttpUrlUtil");
            var3_4.printStackTrace();
            return;
        }
        catch (KeyManagementException var3_5) {
            ed.a(var3_5, "HttpUrlUtil", "HttpUrlUtil");
            var3_5.printStackTrace();
            return;
        }
        catch (Throwable var3_6) {
            ed.a(var3_6, "HttpUtil", "HttpUtil");
            var3_6.printStackTrace();
            return;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private fa a(HttpURLConnection var1_1) {
        var4_4 = null;
        var5_6 = null;
        try {
            var8_8 = var1_1.getHeaderFields();
            var2_13 = var1_1.getResponseCode();
            if (var2_13 != 200) {
                throw new cz("\u7f51\u7edc\u5f02\u5e38\u539f\u56e0\uff1a" + var1_1.getResponseMessage() + " \u7f51\u7edc\u5f02\u5e38\u72b6\u6001\u7801\uff1a" + var2_13);
            }
            var7_27 = new ByteArrayOutputStream();
            ** GOTO lbl16
        }
        catch (IOException var3_14) {
            block52 : {
                block53 : {
                    block51 : {
                        var4_4 = null;
                        var6_24 = null;
                        var7_27 = null;
                        ** GOTO lbl119
lbl16: // 2 sources:
                        var6_24 = var1_1.getInputStream();
                        var3_15 = new PushbackInputStream(var6_24, 2);
                        try {
                            var4_4 = new byte[2];
                            var3_15.read((byte[])var4_4);
                            var3_15.unread((byte[])var4_4);
                        }
                        catch (Throwable var8_9) {
                            var4_4 = null;
                            var5_6 = var3_15;
                            var3_15 = var8_9;
                            ** GOTO lbl-1000
                        }
                        if (var4_4[0] == 31 && var4_4[1] == -117) {
                            var4_4 = new GZIPInputStream((InputStream)var3_15);
                            break block51;
                        }
                        ** GOTO lbl63
                        catch (Throwable var3_19) {
                            var8_8 = null;
                            var6_24 = null;
                            var7_27 = null;
                            var5_6 = var4_4;
                            var4_4 = var8_8;
                            ** GOTO lbl-1000
                        }
                        catch (Throwable var3_20) {
                            var8_8 = null;
                            var6_24 = null;
                            var5_6 = var4_4;
                            var4_4 = var8_8;
                            ** GOTO lbl-1000
                        }
                        catch (Throwable var3_21) {
                            var8_8 = null;
                            var5_6 = var4_4;
                            var4_4 = var8_8;
                            ** GOTO lbl-1000
                        }
                        catch (IOException var3_22) {
                            var4_4 = null;
                            var6_24 = null;
                            break block52;
                        }
                        catch (IOException var3_23) {
                            var4_4 = null;
                            break block52;
                        }
                        catch (IOException var8_11) {
                            var4_4 = null;
                            var5_6 = var3_15;
                            var3_15 = var8_11;
                            break block52;
                        }
lbl63: // 1 sources:
                        var4_4 = var3_15;
                    }
                    var5_6 = new byte[1024];
                    while ((var2_13 = var4_4.read((byte[])var5_6)) != -1) {
                        var7_27.write((byte[])var5_6, 0, var2_13);
                    }
                    if (ev.a != null) {
                        ev.a.a();
                    }
                    var5_6 = new fa();
                    var5_6.a = var7_27.toByteArray();
                    var5_6.b = var8_8;
                    if (var7_27 == null) break block53;
                    try {
                        var7_27.close();
                    }
                    catch (IOException var7_29) {
                        ed.a(var7_29, "HttpUrlUtil", "parseResult");
                        var7_29.printStackTrace();
                    }
                }
                if (var6_24 != null) {
                    try {
                        var6_24.close();
                    }
                    catch (Exception var6_26) {
                        ed.a(var6_26, "HttpUrlUtil", "parseResult");
                        var6_26.printStackTrace();
                    }
                }
                if (var3_15 != null) {
                    try {
                        var3_15.close();
                    }
                    catch (Exception var3_17) {
                        ed.a(var3_17, "HttpUrlUtil", "parseResult");
                        var3_17.printStackTrace();
                    }
                }
                if (var4_4 != null) {
                    try {
                        var4_4.close();
                    }
                    catch (Exception var3_18) {
                        ed.a(var3_18, "HttpUrlUtil", "parseResult");
                        var3_18.printStackTrace();
                    }
                }
                if (var1_1 == null) return var5_6;
                try {
                    var1_1.disconnect();
                    return var5_6;
                }
                catch (Throwable var1_3) {
                    ed.a(var1_3, "HttpUrlUtil", "parseResult");
                    var1_3.printStackTrace();
                    return var5_6;
                }
                catch (Throwable var8_10) {
                    var5_6 = var3_15;
                    var3_15 = var8_10;
                    ** GOTO lbl-1000
                }
                catch (IOException var8_12) {
                    var5_6 = var3_15;
                    var3_15 = var8_12;
                }
            }
            try {
                throw var3_15;
            }
            catch (Throwable var3_16) lbl-1000: // 6 sources:
            {
                if (var7_27 != null) {
                    try {
                        var7_27.close();
                    }
                    catch (IOException var7_28) {
                        ed.a(var7_28, "HttpUrlUtil", "parseResult");
                        var7_28.printStackTrace();
                    }
                }
                if (var6_24 != null) {
                    try {
                        var6_24.close();
                    }
                    catch (Exception var6_25) {
                        ed.a(var6_25, "HttpUrlUtil", "parseResult");
                        var6_25.printStackTrace();
                    }
                }
                if (var5_6 != null) {
                    try {
                        var5_6.close();
                    }
                    catch (Exception var5_7) {
                        ed.a(var5_7, "HttpUrlUtil", "parseResult");
                        var5_7.printStackTrace();
                    }
                }
                if (var4_4 != null) {
                    try {
                        var4_4.close();
                    }
                    catch (Exception var4_5) {
                        ed.a(var4_5, "HttpUrlUtil", "parseResult");
                        var4_5.printStackTrace();
                    }
                }
                if (var1_1 == null) throw var3_15;
                try {
                    var1_1.disconnect();
                }
                catch (Throwable var1_2) {
                    ed.a(var1_2, "HttpUrlUtil", "parseResult");
                    var1_2.printStackTrace();
                    throw var3_15;
                }
                throw var3_15;
            }
        }
    }

    private String a(Map<String, String> object) {
        LinkedList linkedList = new LinkedList();
        if (object != null) {
            for (Map.Entry entry : object.entrySet()) {
                linkedList.add(new BasicNameValuePair((String)entry.getKey(), (String)entry.getValue()));
            }
        }
        if (linkedList.size() > 0) {
            return URLEncodedUtils.format((List)linkedList, (String)"UTF-8");
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    private HttpURLConnection a(URL uRL) {
        uRL = this.f != null ? uRL.openConnection(this.f) : (HttpURLConnection)uRL.openConnection();
        if (this.d) {
            uRL = (HttpsURLConnection)uRL;
            ((HttpsURLConnection)uRL).setSSLSocketFactory(this.e.getSocketFactory());
        } else {
            uRL = (HttpURLConnection)uRL;
        }
        if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
            uRL.setRequestProperty("Connection", "close");
        }
        return uRL;
    }

    public static void a(ew ew2) {
        a = ew2;
    }

    private void a(Map<String, String> map, HttpURLConnection httpURLConnection) {
        if (map != null) {
            for (String string2 : map.keySet()) {
                httpURLConnection.addRequestProperty(string2, map.get(string2));
            }
        }
        httpURLConnection.setConnectTimeout(this.b);
        httpURLConnection.setReadTimeout(this.c);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    fa a(String object, Map<String, String> map, Map<String, String> object2) {
        try {
            object2 = this.a((Map<String, String>)object2);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append((String)object);
            if (object2 != null) {
                stringBuffer.append("?").append((String)object2);
            }
            object = this.a(new URL(stringBuffer.toString()));
            this.a(map, (HttpURLConnection)object);
            object.setRequestMethod("GET");
            object.setDoInput(true);
            object.connect();
            return this.a((HttpURLConnection)object);
        }
        catch (MalformedURLException var1_2) {
            ed.a(var1_2, "HttpUrlUtil", "getRequest");
            var1_2.printStackTrace();
            return null;
        }
        catch (IOException var1_3) {
            ed.a(var1_3, "HttpUrlUtil", "getRequest");
            var1_3.printStackTrace();
            return null;
        }
        catch (Throwable var1_4) {
            ed.a(var1_4, "HttpUrlUtil", "getRequest");
            var1_4.printStackTrace();
            return null;
        }
    }

    /*
     * Exception decompiling
     */
    fa a(String var1_1, Map<String, String> var2_13, Map<String, String> var3_27, HttpEntity var4_28) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [2[TRYBLOCK]], but top level block is 18[CATCHBLOCK]
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
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    fa a(String object, Map<String, String> map, Map<String, String> object2, byte[] arrby) {
        Object object3 = object;
        if (object2 == null) return this.a((String)object3, map, arrby);
        try {
            object2 = this.a((Map<String, String>)object2);
            object3 = new StringBuffer();
            object3.append((String)object);
            if (object2 != null) {
                object3.append("?").append((String)object2);
            }
            object3 = object3.toString();
            return this.a((String)object3, map, arrby);
        }
        catch (Throwable var1_2) {
            ed.a(var1_2, "HttpUrlUtil", "PostReqeust3");
            var1_2.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    fa a(String object, Map<String, String> dataOutputStream, byte[] arrby) {
        try {
            object = this.a(new URL((String)object));
            this.a((Map<String, String>)dataOutputStream, (HttpURLConnection)object);
            object.setRequestMethod("POST");
            object.setUseCaches(false);
            object.setDoInput(true);
            object.setDoOutput(true);
            if (arrby != null && arrby.length > 0) {
                dataOutputStream = new DataOutputStream(object.getOutputStream());
                dataOutputStream.write(arrby);
                dataOutputStream.close();
            }
            object.connect();
            return this.a((HttpURLConnection)object);
        }
        catch (MalformedURLException var1_2) {
            ed.a(var1_2, "HttpUrlUtil", "postRequest");
            var1_2.printStackTrace();
            return null;
        }
        catch (IOException var1_3) {
            ed.a(var1_3, "HttpUrlUtil", "postRequest");
            var1_3.printStackTrace();
            return null;
        }
        catch (Throwable var1_4) {
            ed.a(var1_4, "HttpUrlUtil", "postRequest");
            var1_4.printStackTrace();
            return null;
        }
    }

    fa b(String string2, Map<String, String> map, Map<String, String> object) {
        if ((object = this.a((Map<String, String>)object)) == null) {
            return this.a(string2, map, new byte[0]);
        }
        try {
            fa fa2 = this.a(string2, map, object.getBytes("UTF-8"));
            return fa2;
        }
        catch (UnsupportedEncodingException var4_5) {
            ed.a(var4_5, "HttpUrlUtil", "postRequest1");
            var4_5.printStackTrace();
            return this.a(string2, map, object.getBytes());
        }
    }
}

