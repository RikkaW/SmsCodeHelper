/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  android.util.Log
 *  com.android.okhttp.ConnectionPool
 *  com.android.okhttp.HostResolver
 *  com.android.okhttp.HttpHandler
 *  com.android.okhttp.HttpsHandler
 *  com.android.okhttp.OkHttpClient
 *  java.lang.Object
 *  java.lang.String
 *  java.net.HttpURLConnection
 *  java.net.Proxy
 *  java.net.URL
 *  java.util.Locale
 *  java.util.Map$Entry
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
package com.android.mms.service;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.service.MmsConfig;
import com.android.mms.service.exception.MmsHttpException;
import com.android.okhttp.ConnectionPool;
import com.android.okhttp.HostResolver;
import com.android.okhttp.HttpHandler;
import com.android.okhttp.HttpsHandler;
import com.android.okhttp.OkHttpClient;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.SocketFactory;

public class MmsHttpClient {
    private static final Pattern MACRO_P = Pattern.compile((String)"##(\\S+)##");
    private final ConnectionPool mConnectionPool;
    private final Context mContext;
    private final HostResolver mHostResolver;
    private final SocketFactory mSocketFactory;

    public MmsHttpClient(Context context, SocketFactory socketFactory, HostResolver hostResolver, ConnectionPool connectionPool) {
        this.mContext = context;
        this.mSocketFactory = socketFactory;
        this.mHostResolver = hostResolver;
        this.mConnectionPool = connectionPool;
    }

    private void addExtraHeaders(HttpURLConnection httpURLConnection, MmsConfig.Overridden overridden) {
        String[] arrstring = overridden.getHttpParams();
        if (!TextUtils.isEmpty((CharSequence)arrstring)) {
            arrstring = arrstring.split("\\|");
            int n = arrstring.length;
            for (int i = 0; i < n; ++i) {
                String[] arrstring2 = arrstring[i].split(":", 2);
                if (arrstring2.length != 2) continue;
                String string = arrstring2[0].trim();
                arrstring2 = MmsHttpClient.resolveMacro(this.mContext, arrstring2[1].trim(), overridden);
                if (TextUtils.isEmpty((CharSequence)string) || TextUtils.isEmpty((CharSequence)arrstring2)) continue;
                httpURLConnection.setRequestProperty(string, (String)arrstring2);
            }
        }
    }

    private static void addLocaleToHttpAcceptLanguage(StringBuilder stringBuilder, Locale object) {
        String string = MmsHttpClient.convertObsoleteLanguageCodeToNew(object.getLanguage());
        if (string != null) {
            stringBuilder.append(string);
            object = object.getCountry();
            if (object != null) {
                stringBuilder.append("-");
                stringBuilder.append((String)object);
            }
        }
    }

    private static void checkMethod(String string) throws MmsHttpException {
        if (!"GET".equals((Object)string) && !"POST".equals((Object)string)) {
            throw new MmsHttpException(0, "Invalid method " + string);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static String convertObsoleteLanguageCodeToNew(String string) {
        if (string == null) {
            return null;
        }
        if ("iw".equals((Object)string)) {
            return "he";
        }
        if ("in".equals((Object)string)) {
            return "id";
        }
        String string2 = string;
        if (!"ji".equals((Object)string)) return string2;
        return "yi";
    }

    public static String getCurrentAcceptLanguage(Locale locale) {
        StringBuilder stringBuilder = new StringBuilder();
        MmsHttpClient.addLocaleToHttpAcceptLanguage(stringBuilder, locale);
        if (!Locale.US.equals((Object)locale)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("en-US");
        }
        return stringBuilder.toString();
    }

    private static void logHttpHeaders(Map<String, List<String>> object) {
        StringBuilder stringBuilder = new StringBuilder();
        if (object != null) {
            object = object.entrySet().iterator();
            while (object.hasNext()) {
                Iterator iterator = (Map.Entry)object.next();
                String string = (String)iterator.getKey();
                if ((iterator = (List)iterator.getValue()) == null) continue;
                iterator = iterator.iterator();
                while (iterator.hasNext()) {
                    String string2 = (String)iterator.next();
                    stringBuilder.append(string).append('=').append(string2).append('\n');
                }
            }
            Log.v((String)"MmsService", (String)("HTTP: headers\n" + stringBuilder.toString()));
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private HttpURLConnection openConnection(URL uRL, Proxy proxy) throws MalformedURLException {
        String string = uRL.getProtocol();
        if (string.equals((Object)"http")) {
            proxy = HttpHandler.createHttpOkHttpClient((Proxy)proxy);
            do {
                return proxy.setSocketFactory(this.mSocketFactory).setHostResolver(this.mHostResolver).setConnectionPool(this.mConnectionPool).open(uRL);
                break;
            } while (true);
        }
        if (!string.equals((Object)"https")) throw new MalformedURLException("Invalid URL or unrecognized protocol " + string);
        proxy = HttpsHandler.createHttpsOkHttpClient((Proxy)proxy);
        return proxy.setSocketFactory(this.mSocketFactory).setHostResolver(this.mHostResolver).setConnectionPool(this.mConnectionPool).open(uRL);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static String resolveMacro(Context context, String string, MmsConfig.Overridden overridden) {
        void var6_6;
        if (TextUtils.isEmpty((CharSequence)string)) {
            return string;
        }
        Matcher matcher = MACRO_P.matcher((CharSequence)string);
        int n = 0;
        Object var6_5 = null;
        while (matcher.find()) {
            int n2;
            String string2;
            String string3;
            StringBuilder stringBuilder = var6_6;
            if (var6_6 == null) {
                stringBuilder = new StringBuilder();
            }
            if ((n2 = matcher.start()) > n) {
                stringBuilder.append(string.substring(n, n2));
            }
            if ((string3 = overridden.getHttpParamMacro(context, string2 = matcher.group(1))) != null) {
                stringBuilder.append(string3);
            } else {
                Log.w((String)"MmsService", (String)("HTTP: invalid macro " + string2));
            }
            n = matcher.end();
            StringBuilder stringBuilder2 = stringBuilder;
        }
        if (var6_6 != null && n < string.length()) {
            var6_6.append(string.substring(n));
        }
        if (var6_6 == null) return string;
        return var6_6.toString();
    }

    /*
     * Exception decompiling
     */
    public byte[] execute(String var1_1, byte[] var2_4, String var3_7, boolean var4_8, String var5_9, int var6_10, MmsConfig.Overridden var7_11) throws MmsHttpException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 9[CATCHBLOCK]
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

