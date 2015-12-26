/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.net.ConnectivityManager
 *  android.net.LinkProperties
 *  android.net.http.AndroidHttpClient
 *  android.util.Log
 *  com.google.android.collect.Maps
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.net.InetAddress
 *  java.util.HashMap
 *  java.util.Locale
 *  org.apache.http.client.methods.HttpRequestBase
 *  org.apache.http.conn.params.ConnRouteParams
 *  org.apache.http.params.HttpConnectionParams
 *  org.apache.http.params.HttpParams
 *  org.apache.http.params.HttpProtocolParams
 */
package com.android.mms.transaction;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.http.AndroidHttpClient;
import android.util.Log;
import com.android.mms.MmsConfig;
import com.android.mms.transaction.ProgressReceiver;
import com.google.android.collect.Maps;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public class HttpUtils {
    private static final String HDR_VALUE_ACCEPT_LANGUAGE = HttpUtils.getCurrentAcceptLanguage(Locale.getDefault());
    private static final HashMap<Long, HttpRequestBase> mRequestMap = Maps.newHashMap();

    private HttpUtils() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    protected static boolean abortHttpConnection(long l) {
        HashMap<Long, HttpRequestBase> hashMap = mRequestMap;
        // MONITORENTER : hashMap
        HttpRequestBase httpRequestBase = (HttpRequestBase)mRequestMap.get((Object)l);
        // MONITOREXIT : hashMap
        if (httpRequestBase == null) {
            return false;
        }
        httpRequestBase.abort();
        return true;
    }

    private static void addLocaleToHttpAcceptLanguage(StringBuilder stringBuilder, Locale object) {
        String string = HttpUtils.convertObsoleteLanguageCodeToNew(object.getLanguage());
        if (string != null) {
            stringBuilder.append(string);
            object = object.getCountry();
            if (object != null) {
                stringBuilder.append("-");
                stringBuilder.append((String)object);
            }
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

    private static AndroidHttpClient createHttpClient(Context context) {
        context = AndroidHttpClient.newInstance((String)MmsConfig.getUserAgent(), (Context)context);
        HttpParams httpParams = context.getParams();
        HttpProtocolParams.setContentCharset((HttpParams)httpParams, (String)"UTF-8");
        HttpConnectionParams.setSoTimeout((HttpParams)httpParams, (int)MmsConfig.getHttpSocketTimeout());
        return context;
    }

    public static String getCurrentAcceptLanguage(Locale locale) {
        StringBuilder stringBuilder = new StringBuilder();
        HttpUtils.addLocaleToHttpAcceptLanguage(stringBuilder, locale);
        if (!Locale.US.equals((Object)locale)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("en-US");
        }
        return stringBuilder.toString();
    }

    private static void handleHttpConnectionException(Exception exception, String object) throws IOException {
        MyLog.e("Mms:transaction", "Url: " + (String)object + "\n" + exception.getMessage());
        object = new IOException(exception.getMessage());
        object.initCause((Throwable)exception);
        throw object;
    }

    /*
     * Exception decompiling
     */
    protected static byte[] httpConnection(Context var0, long var1_14, String var3_15, byte[] var4_17, int var5_19, boolean var6_20, String var7_21, int var8_22, ProgressReceiver var9_23) throws IOException {
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

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void setRequestLinkIp(Context object, HttpParams httpParams) {
        if ((object = ((ConnectivityManager)object.getSystemService("connectivity")).getLinkProperties(2)) == null) return;
        if ((object = object.getAddresses().iterator()) != null && object.hasNext()) {
            ConnRouteParams.setLocalAddress((HttpParams)httpParams, (InetAddress)((InetAddress)object.next()));
            Log.v((String)"Mms:transaction", (String)"bind the mms ip");
            return;
        }
        Log.e((String)"Mms:transaction", (String)"can not bind the mms ip");
    }
}

