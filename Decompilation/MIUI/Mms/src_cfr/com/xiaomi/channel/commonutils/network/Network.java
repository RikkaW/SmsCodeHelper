/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.telephony.TelephonyManager
 *  android.text.TextUtils
 *  android.util.Log
 *  java.io.BufferedReader
 *  java.io.DataOutputStream
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.InputStreamReader
 *  java.io.OutputStream
 *  java.io.Reader
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.Throwable
 *  java.net.HttpURLConnection
 *  java.net.InetSocketAddress
 *  java.net.Proxy
 *  java.net.URL
 *  java.net.URLConnection
 *  java.net.URLEncoder
 *  java.util.HashMap
 *  java.util.Map$Entry
 *  java.util.regex.Pattern
 */
package com.xiaomi.channel.commonutils.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.channel.commonutils.network.HttpResponse;
import com.xiaomi.channel.commonutils.string.MD5;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class Network {
    public static final Pattern ContentTypePattern_Charset;
    public static final Pattern ContentTypePattern_MimeType;
    public static final Pattern ContentTypePattern_XmlEncoding;

    static {
        ContentTypePattern_MimeType = Pattern.compile((String)"([^\\s;]+)(.*)");
        ContentTypePattern_Charset = Pattern.compile((String)"(.*?charset\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", (int)2);
        ContentTypePattern_XmlEncoding = Pattern.compile((String)"(\\<\\?xml\\s+.*?encoding\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", (int)2);
    }

    public static HttpResponse doHttpPost(Context context, String string2, Map<String, String> map) throws IOException {
        return Network.httpRequest(context, string2, "POST", null, Network.fromParamsMapToString(map));
    }

    public static String downloadXml(Context context, URL uRL) throws IOException {
        return Network.downloadXml(context, uRL, false, null, "UTF-8", null);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String downloadXml(Context var0, URL var1_3, boolean var2_5, String var3_6, String var4_7, String var5_8) throws IOException {
        var7_9 = null;
        try {
            var7_9 = var0 = Network.downloadXmlAsStream((Context)var0, (URL)var1_3, var2_5, var3_6, var5_8);
            var1_3 = new StringBuilder(1024);
            var7_9 = var0;
            var3_6 = new BufferedReader((Reader)new InputStreamReader((InputStream)var0, var4_7 /* !! */ ));
            var7_9 = var0;
            var4_7 /* !! */  = (String)new char[4096];
            do {
                var7_9 = var0;
                var6_10 = var3_6.read((char[])var4_7 /* !! */ );
                if (-1 != var6_10) {
                    var7_9 = var0;
                    var1_3.append((char[])var4_7 /* !! */ , 0, var6_10);
                    continue;
                }
                ** GOTO lbl25
                break;
            } while (true);
        }
        catch (Throwable var0_1) {
            if (var7_9 == null) throw var0_1;
            try {
                var7_9.close();
            }
            catch (IOException var1_4) {
                Log.e((String)"com.xiaomi.common.Network", (String)("Failed to close responseStream" + var1_4.toString()));
                throw var0_1;
            }
            throw var0_1;
lbl25: // 1 sources:
            if (var0 == null) return var1_3.toString();
            try {
                var0.close();
                return var1_3.toString();
            }
            catch (IOException var0_2) {
                Log.e((String)"com.xiaomi.common.Network", (String)("Failed to close responseStream" + var0_2.toString()));
                return var1_3.toString();
            }
        }
    }

    public static InputStream downloadXmlAsStream(Context context, URL uRL, boolean bl, String string2, String string3) throws IOException {
        return Network.downloadXmlAsStream(context, uRL, bl, string2, string3, null, null);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static InputStream downloadXmlAsStream(Context context, URL object, boolean bl, String object2, String string22, Map<String, String> map, HttpHeaderInfo httpHeaderInfo) throws IOException {
        block15 : {
            if (context == null) {
                throw new IllegalArgumentException("context");
            }
            if (object == null) {
                throw new IllegalArgumentException("url");
            }
            Object object3 = object;
            if (!bl) {
                object3 = new URL(Network.encryptURL(object.toString()));
            }
            HttpURLConnection.setFollowRedirects((boolean)true);
            context = Network.getHttpUrlConnection(context, (URL)object3);
            context.setConnectTimeout(10000);
            context.setReadTimeout(15000);
            if (!TextUtils.isEmpty((CharSequence)object2)) {
                context.setRequestProperty("User-Agent", (String)object2);
            }
            if (string22 != null) {
                context.setRequestProperty("Cookie", string22);
            }
            if (map == null) break block15;
            for (String string22 : map.keySet()) {
                context.setRequestProperty(string22, map.get(string22));
            }
        }
        if (httpHeaderInfo == null) return new DoneHandlerInputStream(context.getInputStream());
        try {
            if (!object.getProtocol().equals((Object)"http")) {
                if (!object.getProtocol().equals((Object)"https")) return new DoneHandlerInputStream(context.getInputStream());
            }
            httpHeaderInfo.ResponseCode = context.getResponseCode();
            if (httpHeaderInfo.AllHeaders == null) {
                httpHeaderInfo.AllHeaders = new HashMap();
            }
        }
        catch (IOException var0_1) {
            throw var0_1;
        }
        catch (Throwable var0_2) {
            throw new IOException(var0_2.getMessage());
        }
        int n = 0;
        do {
            object = context.getHeaderFieldKey(n);
            object2 = context.getHeaderField(n);
            if (object == null && object2 == null) {
                return new DoneHandlerInputStream(context.getInputStream());
            }
            if (!TextUtils.isEmpty((CharSequence)object) && !TextUtils.isEmpty((CharSequence)object2)) {
                httpHeaderInfo.AllHeaders.put((String)object, (String)object2);
            }
            ++n;
        } while (true);
    }

    public static String encryptURL(String string2) {
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            new String();
            return String.format((String)"%s&key=%s", (Object[])new Object[]{string2, MD5.MD5_32(String.format((String)"%sbe988a6134bc8254465424e5a70ef037", (Object[])new Object[]{string2}))});
        }
        return null;
    }

    public static String fromParamsMapToString(Map<String, String> stringBuffer) {
        if (stringBuffer != null && stringBuffer.size() > 0) {
            StringBuffer stringBuffer2 = new StringBuffer();
            for (Map.Entry<String, String> entry : stringBuffer.entrySet()) {
                if (entry.getKey() == null || entry.getValue() == null) continue;
                try {
                    stringBuffer2.append(URLEncoder.encode((String)((String)entry.getKey()), (String)"UTF-8"));
                    stringBuffer2.append("=");
                    stringBuffer2.append(URLEncoder.encode((String)((String)entry.getValue()), (String)"UTF-8"));
                    stringBuffer2.append("&");
                    continue;
                }
                catch (UnsupportedEncodingException var1_2) {
                    Log.d((String)"com.xiaomi.common.Network", (String)("Failed to convert from params map to string: " + var1_2.toString()));
                    Log.d((String)"com.xiaomi.common.Network", (String)("map: " + stringBuffer.toString()));
                    return null;
                }
            }
            stringBuffer = stringBuffer2;
            if (stringBuffer2.length() > 0) {
                stringBuffer = stringBuffer2.deleteCharAt(stringBuffer2.length() - 1);
            }
            return stringBuffer.toString();
        }
        return null;
    }

    public static String getActiveConnPoint(Context context) {
        if (Network.isWIFIConnected(context)) {
            return "wifi";
        }
        try {
            context = (ConnectivityManager)context.getSystemService("connectivity");
            if (context == null) {
                return "";
            }
        }
        catch (Exception var0_1) {
            return "";
        }
        try {
            context = context.getActiveNetworkInfo();
            if (context == null) {
                return "";
            }
        }
        catch (Exception var0_2) {
            return "";
        }
        return (context.getTypeName() + "-" + context.getSubtypeName() + "-" + context.getExtraInfo()).toLowerCase();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static int getActiveNetworkType(Context context) {
        block4 : {
            try {
                context = (ConnectivityManager)context.getSystemService("connectivity");
                if (context != null) break block4;
                return -1;
            }
            catch (Exception var0_1) {
                return -1;
            }
        }
        try {
            if ((context = context.getActiveNetworkInfo()) != null) return context.getType();
        }
        catch (Exception var0_2) {
            return -1;
        }
        return -1;
    }

    public static String getCMWapUrl(URL uRL) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(uRL.getProtocol()).append("://").append("10.0.0.172").append(uRL.getPath());
        if (!TextUtils.isEmpty((CharSequence)uRL.getQuery())) {
            stringBuilder.append("?").append(uRL.getQuery());
        }
        return stringBuilder.toString();
    }

    private static URL getDefaultStreamHandlerURL(String string2) throws MalformedURLException {
        return new URL(string2);
    }

    public static HttpURLConnection getHttpUrlConnection(Context object, URL uRL) throws IOException {
        if (!"http".equals((Object)uRL.getProtocol())) {
            return (HttpURLConnection)uRL.openConnection();
        }
        if (Network.isCtwap((Context)object)) {
            return (HttpURLConnection)uRL.openConnection(new Proxy(Proxy.Type.HTTP, (SocketAddress)new InetSocketAddress("10.0.0.200", 80)));
        }
        if (!Network.isCmwap((Context)object)) {
            return (HttpURLConnection)uRL.openConnection();
        }
        object = uRL.getHost();
        uRL = (HttpURLConnection)new URL(Network.getCMWapUrl(uRL)).openConnection();
        uRL.addRequestProperty("X-Online-Host", (String)object);
        return uRL;
    }

    public static boolean hasNetwork(Context context) {
        if (Network.getActiveNetworkType(context) >= 0) {
            return true;
        }
        return false;
    }

    /*
     * Exception decompiling
     */
    public static HttpResponse httpRequest(Context var0, String var1_6, String var2_8, Map<String, String> var3_9, String var4_10) throws IOException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK], 1[TRYBLOCK]], but top level block is 12[CATCHBLOCK]
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
     * Lifted jumps to return sites
     */
    public static boolean isCmwap(Context object) {
        if (!"CN".equalsIgnoreCase(((TelephonyManager)object.getSystemService("phone")).getSimCountryIso())) {
            return false;
        }
        try {
            if ((object = (ConnectivityManager)object.getSystemService("connectivity")) == null) return false;
        }
        catch (Exception var0_1) {
            return false;
        }
        try {
            if ((object = object.getActiveNetworkInfo()) == null) return false;
        }
        catch (Exception var0_2) {
            return false;
        }
        if (TextUtils.isEmpty((CharSequence)(object = object.getExtraInfo()))) return false;
        if (object.length() < 3) return false;
        if (object.contains((CharSequence)"ctwap")) return false;
        return object.regionMatches(true, object.length() - 3, "wap", 0, 3);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static boolean isCtwap(Context object) {
        if (!"CN".equalsIgnoreCase(((TelephonyManager)object.getSystemService("phone")).getSimCountryIso())) {
            return false;
        }
        try {
            if ((object = (ConnectivityManager)object.getSystemService("connectivity")) == null) return false;
        }
        catch (Exception var0_1) {
            return false;
        }
        try {
            if ((object = object.getActiveNetworkInfo()) == null) return false;
        }
        catch (Exception var0_2) {
            return false;
        }
        if (TextUtils.isEmpty((CharSequence)(object = object.getExtraInfo()))) return false;
        if (object.length() < 3) return false;
        if (!object.contains((CharSequence)"ctwap")) return false;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean isWIFIConnected(Context context) {
        try {
            context = (ConnectivityManager)context.getSystemService("connectivity");
            if (context == null) {
                return false;
            }
        }
        catch (Exception var0_1) {
            return false;
        }
        try {
            if ((context = context.getActiveNetworkInfo()) == null) return false;
        }
        catch (Exception var0_2) {
            return false;
        }
        if (1 != context.getType()) return false;
        return true;
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String uploadFile(String var0, Map<String, String> var1_3, File var2_10, String var3_14) throws IOException {
        if (!var2_10.exists()) {
            return null;
        }
        var20_18 = var2_10.getName();
        var16_19 = null;
        var14_20 = null;
        var12_21 = null;
        var11_22 = null;
        var17_23 = null;
        var15_24 = null;
        var13_25 = null;
        var18_26 = null;
        var10_27 = null;
        var9_28 = null;
        var8_29 = null;
        var5_30 = var17_23;
        var6_31 = var16_19;
        var7_32 = var18_26;
        try {
            var19_33 = (HttpURLConnection)new URL((String)var0).openConnection();
            var5_30 = var17_23;
            var6_31 = var16_19;
            var7_32 = var18_26;
            var19_33.setReadTimeout(15000);
            var5_30 = var17_23;
            var6_31 = var16_19;
            var7_32 = var18_26;
            var19_33.setConnectTimeout(10000);
            var5_30 = var17_23;
            var6_31 = var16_19;
            var7_32 = var18_26;
            var19_33.setDoInput(true);
            var5_30 = var17_23;
            var6_31 = var16_19;
            var7_32 = var18_26;
            var19_33.setDoOutput(true);
            var5_30 = var17_23;
            var6_31 = var16_19;
            var7_32 = var18_26;
            var19_33.setUseCaches(false);
            var5_30 = var17_23;
            var6_31 = var16_19;
            var7_32 = var18_26;
            var19_33.setRequestMethod("POST");
            var5_30 = var17_23;
            var6_31 = var16_19;
            var7_32 = var18_26;
            var19_33.setRequestProperty("Connection", "Keep-Alive");
            var5_30 = var17_23;
            var6_31 = var16_19;
            var7_32 = var18_26;
            var19_33.setRequestProperty("Content-Type", "multipart/form-data;boundary=*****");
            if (var1_3 != null) {
                var5_30 = var17_23;
                var6_31 = var16_19;
                var7_32 = var18_26;
                var0 = var1_3.entrySet().iterator();
                do {
                    var5_30 = var17_23;
                    var6_31 = var16_19;
                    var7_32 = var18_26;
                    if (!var0.hasNext()) break;
                    var5_30 = var17_23;
                    var6_31 = var16_19;
                    var7_32 = var18_26;
                    var1_3 = var0.next();
                    var5_30 = var17_23;
                    var6_31 = var16_19;
                    var7_32 = var18_26;
                    var19_33.setRequestProperty((String)var1_3.getKey(), (String)var1_3.getValue());
                } while (true);
            }
            var5_30 = var17_23;
            var6_31 = var16_19;
            var7_32 = var18_26;
            var19_33.setFixedLengthStreamingMode(var20_18.length() + 77 + (int)var2_10.length() + var3_14.length());
            var5_30 = var17_23;
            var6_31 = var16_19;
            var7_32 = var18_26;
            var0 = new DataOutputStream(var19_33.getOutputStream());
            ** GOTO lbl87
        }
        catch (IOException var1_4) {
            block30 : {
                var7_32 = var8_29;
                var6_31 = var11_22;
                var0 = var13_25;
                ** GOTO lbl167
lbl87: // 2 sources:
                var0.writeBytes("--*****\r\n");
                var0.writeBytes("Content-Disposition: form-data; name=\"" + var3_14 + "\";filename=\"" + var2_10.getName() + "\"" + "\r\n");
                var0.writeBytes("\r\n");
                var1_3 = new FileInputStream((File)var2_10);
                var2_10 = new byte[1024];
                while ((var4_34 = var1_3.read((byte[])var2_10)) != -1) {
                    var0.write((byte[])var2_10, 0, var4_34);
                    var0.flush();
                }
                var0.writeBytes("\r\n");
                var0.writeBytes("--");
                var0.writeBytes("*****");
                var0.writeBytes("--");
                var0.writeBytes("\r\n");
                var0.flush();
                var3_14 = new StringBuffer();
                var2_10 = new BufferedReader((Reader)new InputStreamReader((InputStream)new DoneHandlerInputStream(var19_33.getInputStream())));
                while ((var5_30 = var2_10.readLine()) != null) {
                    var3_14.append((String)var5_30);
                }
                var3_14 = var3_14.toString();
                if (var1_3 == null) ** GOTO lbl113
                try {
                    var1_3.close();
lbl113: // 2 sources:
                    if (var0 != null) {
                        var0.close();
                    }
                    var0 = var3_14;
                    if (var2_10 == null) return var0;
                    var2_10.close();
                    return var3_14;
                }
                catch (IOException var0_2) {
                    Log.e((String)"com.xiaomi.common.Network", (String)"error while closing strean", (Throwable)var0_2);
                    return var3_14;
                }
                catch (Throwable var1_7) {
                    var5_30 = var0;
                    var6_31 = var14_20;
                    var7_32 = var10_27;
                    var0 = var1_7;
                    ** GOTO lbl-1000
                }
                catch (Throwable var2_11) {
                    var5_30 = var0;
                    var6_31 = var1_3;
                    var7_32 = var10_27;
                    var0 = var2_11;
                    ** GOTO lbl-1000
                }
                catch (Throwable var3_15) {
                    var5_30 = var0;
                    var6_31 = var1_3;
                    var7_32 = var2_10;
                    var0 = var3_15;
                    ** GOTO lbl-1000
                }
                catch (Throwable var1_8) {
                    var6_31 = var12_21;
                    var7_32 = var9_28;
                    ** GOTO lbl186
                }
                catch (Throwable var2_12) {
                    var6_31 = var1_3;
                    var1_3 = var2_12;
                    var7_32 = var9_28;
                    ** GOTO lbl186
                }
                catch (Throwable var3_16) {
                    var6_31 = var1_3;
                    var1_3 = var3_16;
                    var7_32 = var2_10;
                    ** GOTO lbl186
                }
                catch (IOException var1_9) {
                    var6_31 = var11_22;
                    var7_32 = var8_29;
                    break block30;
                }
                catch (IOException var2_13) {
                    var6_31 = var1_3;
                    var1_3 = var2_13;
                    var7_32 = var8_29;
                    break block30;
                }
                catch (IOException var3_17) {
                    var6_31 = var1_3;
                    var1_3 = var3_17;
                    var7_32 = var2_10;
                }
            }
            var5_30 = var0;
            throw var1_3;
        }
        {
            catch (Throwable var0_1) lbl-1000: // 4 sources:
            {
                if (var6_31 == null) ** GOTO lbl174
                try {
                    var6_31.close();
lbl174: // 2 sources:
                    if (var5_30 != null) {
                        var5_30.close();
                    }
                    if (var7_32 == null) throw var0;
                    var7_32.close();
                }
                catch (IOException var1_6) {
                    Log.e((String)"com.xiaomi.common.Network", (String)"error while closing strean", (Throwable)var1_6);
                    throw var0;
                }
                throw var0;
                catch (Throwable var1_5) {}
                var7_32 = var9_28;
                var6_31 = var12_21;
                var0 = var15_24;
lbl186: // 4 sources:
                var5_30 = var0;
                {
                    throw new IOException(var1_3.getMessage());
                }
            }
        }
    }

    public static final class DoneHandlerInputStream
    extends FilterInputStream {
        private boolean done;

        public DoneHandlerInputStream(InputStream inputStream) {
            super(inputStream);
        }

        @Override
        public int read(byte[] arrby, int n, int n2) throws IOException {
            if (!this.done && (n = super.read(arrby, n, n2)) != -1) {
                return n;
            }
            this.done = true;
            return -1;
        }
    }

    public static class HttpHeaderInfo {
        public Map<String, String> AllHeaders;
        public int ResponseCode;

        public String toString() {
            return String.format((String)"resCode = %1$d, headers = %2$s", (Object[])new Object[]{this.ResponseCode, this.AllHeaders.toString()});
        }
    }

}

