/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.ActivityManager
 *  android.app.ActivityManager$RunningAppProcessInfo
 *  android.content.Context
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.net.wifi.WifiInfo
 *  android.net.wifi.WifiManager
 *  android.os.Process
 *  android.text.TextUtils
 *  java.io.BufferedReader
 *  java.io.BufferedWriter
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.FileOutputStream
 *  java.io.InputStreamReader
 *  java.io.OutputStream
 *  java.io.OutputStreamWriter
 *  java.io.Reader
 *  java.io.Writer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.net.URL
 *  java.util.ArrayList
 *  java.util.HashMap
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.xiaomi.network;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Process;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.file.IOUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.BasicNameValuePair;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.common.logger.thrift.mfs.HttpApi;
import com.xiaomi.network.AccessHistory;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.Fallbacks;
import com.xiaomi.network.HostFilter;
import com.xiaomi.network.UploadHostStatHelper;
import com.xiaomi.network.WeightedHost;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HostManager {
    private static HostManagerFactory factory;
    protected static boolean hostLoaded;
    protected static Map<String, ArrayList<String>> mReservedHosts;
    private static String sAppName;
    private static String sAppVersion;
    private static HostManager sInstance;
    private final long MAX_REQUEST_FAILURE_CNT = 15;
    private String currentISP = "isp_prov_city_country_ip";
    private UploadHostStatHelper.HttpRecordCallback httpRecordCallback;
    private long lastRemoteRequestTimestamp = 0;
    protected Map<String, Fallbacks> mHostsMapping = new HashMap();
    private long remoteRequestFailureCount = 0;
    protected Context sAppContext;
    private HostFilter sHostFilter;
    protected HttpGet sHttpGetter;
    private String sUserId = "0";

    static {
        mReservedHosts = new HashMap();
        hostLoaded = false;
    }

    protected HostManager(Context context, HostFilter hostFilter, HttpGet httpGet, String string2) {
        this(context, hostFilter, httpGet, string2, null, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected HostManager(Context context, HostFilter hostFilter, HttpGet httpGet, String string2, String string3, String string4) {
        this.httpRecordCallback = new UploadHostStatHelper.HttpRecordCallback(){

            @Override
            public List<HttpApi> generateStat() {
                try {
                    ArrayList<HttpApi> arrayList = HostManager.this.generateHostStats();
                    return arrayList;
                }
                catch (JSONException var1_2) {
                    return null;
                }
            }

            @Override
            public double getPercentage() {
                Fallback fallback = HostManager.this.getFallbacksByHost("f3.mi-stat.gslb.mi-idc.com");
                double d2 = 0.1;
                if (fallback != null) {
                    d2 = fallback.getPercent();
                }
                return d2;
            }
        };
        this.sAppContext = context.getApplicationContext();
        if (this.sAppContext == null) {
            this.sAppContext = context;
        }
        this.sHttpGetter = httpGet;
        this.sHostFilter = hostFilter == null ? new HostFilter(){

            @Override
            public boolean accept(String string2) {
                return true;
            }
        } : hostFilter;
        this.sUserId = string2;
        if (string3 == null) {
            string3 = context.getPackageName();
        }
        sAppName = string3;
        if (string4 == null) {
            string4 = this.getVersionName();
        }
        sAppVersion = string4;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static void addReservedHost(String var0, String var1_4) {
        var3_5 = HostManager.mReservedHosts.get(var0);
        var2_6 = HostManager.mReservedHosts;
        // MONITORENTER : var2_6
        if (var3_5 != null) ** GOTO lbl14
        try {
            var3_5 = new ArrayList();
        }
        catch (Throwable var0_3) {
            throw var0_1;
        }
        try {
            block6 : {
                var3_5.add((Object)var1_4);
                HostManager.mReservedHosts.put(var0, (ArrayList)var3_5);
                return;
lbl14: // 2 sources:
                if (var3_5.contains((Object)var1_4)) break block6;
                var3_5.add((Object)var1_4);
                return;
            }
            // MONITOREXIT : var2_6
            return;
        }
        catch (Throwable var0_2) {
            throw var0_1;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static HostManager getInstance() {
        synchronized (HostManager.class) {
            if (sInstance != null) return sInstance;
            throw new IllegalStateException("the host manager is not initialized yet.");
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private String getVersionName() {
        PackageInfo packageInfo = this.sAppContext.getPackageManager().getPackageInfo(this.sAppContext.getPackageName(), 16384);
        if (packageInfo == null) return "0";
        try {
            return packageInfo.versionName;
        }
        catch (Exception var1_2) {
            // empty catch block
        }
        return "0";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void init(Context context, HostFilter hostFilter, HttpGet httpGet, String string2, String string3, String string4) {
        synchronized (HostManager.class) {
            if (sInstance == null) {
                void var5_5;
                void var1_1;
                void var2_2;
                void var4_4;
                void var3_3;
                sInstance = factory == null ? new HostManager(context, (HostFilter)var1_1, (HttpGet)var2_2, (String)var3_3, (String)var4_4, (String)var5_5) : factory.createHostManager(context, (HostFilter)var1_1, (HttpGet)var2_2, (String)var3_3);
                if (sInstance != null) {
                    if (UploadHostStatHelper.getInstance() == null) {
                        UploadHostStatHelper.init(context);
                    }
                    UploadHostStatHelper.getInstance().addCallBack(HostManager.sInstance.httpRecordCallback);
                }
            }
            return;
        }
    }

    public static <T> String join(Collection<T> object, String string2) {
        if (object == null || object.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        object = object.iterator();
        while (object.hasNext()) {
            stringBuilder.append(object.next());
            if (!object.hasNext()) continue;
            stringBuilder.append(string2);
        }
        return stringBuilder.toString();
    }

    public static String join(String[] arrstring, String string2) {
        if (arrstring == null || arrstring.length == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(arrstring[0]);
        for (int i = 1; i < arrstring.length; ++i) {
            stringBuilder.append(string2);
            stringBuilder.append(arrstring[i]);
        }
        return stringBuilder.toString();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private String processNetwork(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return "unknown";
        }
        String string3 = string2;
        if (!string2.startsWith("WIFI")) return string3;
        return "WIFI";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private ArrayList<Fallback> requestRemoteFallbacks(ArrayList<String> arrayList) {
        int n;
        String string222;
        Object object;
        block27 : {
            JSONObject jSONObject;
            String string3;
            JSONObject jSONObject2;
            String string4;
            String string5;
            String string6;
            String string7;
            Object object2;
            this.purge();
            object = this.mHostsMapping;
            // MONITORENTER : object
            this.checkHostMapping();
            for (String string222 : this.mHostsMapping.keySet()) {
                if (arrayList.contains((Object)string222)) continue;
                arrayList.add((Object)string222);
            }
            // MONITOREXIT : object
            object = mReservedHosts;
            // MONITORENTER : object
            for (String string222 : mReservedHosts.keySet()) {
                if (arrayList.contains((Object)string222)) continue;
                arrayList.add((Object)string222);
            }
            // MONITOREXIT : object
            if (!arrayList.contains((Object)this.getHost())) {
                arrayList.add((Object)this.getHost());
            }
            string222 = new ArrayList(arrayList.size());
            for (n = 0; n < arrayList.size(); ++n) {
                string222.add((Object)null);
            }
            try {
                object = Network.isWIFIConnected(this.sAppContext) ? "wifi" : "wap";
                object2 = this.getRemoteFallbackJSON(arrayList, (String)object, this.sUserId);
                if (TextUtils.isEmpty((CharSequence)object2) || !"OK".equalsIgnoreCase((object2 = new JSONObject((String)object2)).getString("S"))) break block27;
                jSONObject = object2.getJSONObject("R");
                string5 = jSONObject.getString("province");
                string7 = jSONObject.getString("city");
                string6 = jSONObject.getString("isp");
                string4 = jSONObject.getString("ip");
                string3 = jSONObject.getString("country");
                jSONObject2 = jSONObject.getJSONObject((String)object);
                object2 = object;
                if (object.equals((Object)"wap")) {
                    object2 = this.getActiveNetworkLabel();
                }
                MyLog.warn("get bucket: " + string3 + " " + string5 + " " + " isp:" + string6 + " " + (String)object2 + " hosts:" + jSONObject2.toString());
            }
            catch (JSONException var4_3) {
                MyLog.warn("failed to get bucket" + var4_3.getMessage());
                break block27;
            }
            catch (IOException var4_4) {
                MyLog.warn("failed to get bucket" + var4_4.getMessage());
                break block27;
            }
            catch (Exception var4_5) {
                MyLog.warn("failed to get bucket" + var4_5.getMessage());
                break block27;
            }
            block13 : for (n = 0; n < arrayList.size(); ++n) {
                object2 = (String)arrayList.get(n);
                object = jSONObject2.optJSONArray((String)object2);
                if (object == null) {
                    MyLog.warn("no bucket found for " + (String)object2);
                    continue;
                }
                object2 = new Fallback((String)object2);
                int n2 = 0;
                do {
                    if (n2 < object.length()) {
                        String string8 = object.getString(n2);
                        if (!TextUtils.isEmpty((CharSequence)string8)) {
                            object2.addHost(new WeightedHost(string8, object.length() - n2));
                        }
                    } else {
                        string222.set(n, object2);
                        object2.country = string3;
                        object2.province = string5;
                        object2.isp = string6;
                        object2.ip = string4;
                        object2.city = string7;
                        if (jSONObject.has("stat-percent")) {
                            object2.setPercent(jSONObject.getDouble("stat-percent"));
                        }
                        if (jSONObject.has("stat-domain")) {
                            object2.setDomainName(jSONObject.getString("stat-domain"));
                        }
                        if (jSONObject.has("ttl")) {
                            object2.setEffectiveDuration((long)jSONObject.getInt("ttl") * 1000);
                        }
                        this.setCurrentISP(object2.getISP());
                        continue block13;
                    }
                    ++n2;
                } while (true);
            }
        }
        n = 0;
        do {
            if (n >= arrayList.size()) {
                this.persist();
                return string222;
            }
            object = (Fallback)string222.get(n);
            if (object != null) {
                this.updateFallbacks((String)arrayList.get(n), (Fallback)object);
            }
            ++n;
        } while (true);
    }

    public static void setHostManagerFactory(HostManagerFactory hostManagerFactory) {
        synchronized (HostManager.class) {
            factory = hostManagerFactory;
            if (UploadHostStatHelper.getInstance() != null && sInstance != null) {
                UploadHostStatHelper.getInstance().removeCallBack(HostManager.sInstance.httpRecordCallback);
            }
            sInstance = null;
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected boolean checkHostMapping() {
        Map<String, Fallbacks> map = this.mHostsMapping;
        synchronized (map) {
            if (hostLoaded) {
                return true;
            }
            hostLoaded = true;
            this.mHostsMapping.clear();
            try {
                String string2 = this.loadHosts();
                if (!TextUtils.isEmpty((CharSequence)string2)) {
                    this.fromJSON(string2);
                    MyLog.warn("loading the new hosts succeed");
                    return true;
                }
            }
            catch (Throwable var2_3) {
                MyLog.warn("load host exception " + var2_3.getMessage());
            }
            return false;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void clear() {
        Map<String, Fallbacks> map = this.mHostsMapping;
        synchronized (map) {
            this.mHostsMapping.clear();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void fromJSON(String string2) throws JSONException {
        Map<String, Fallbacks> map = this.mHostsMapping;
        synchronized (map) {
            this.mHostsMapping.clear();
            string2 = new JSONArray(string2);
            int n = 0;
            while (n < string2.length()) {
                Fallbacks fallbacks = new Fallbacks().fromJSON(string2.getJSONObject(n));
                this.mHostsMapping.put(fallbacks.getHost(), fallbacks);
                ++n;
            }
            return;
        }
    }

    /*
     * Exception decompiling
     */
    public ArrayList<HttpApi> generateHostStats() throws JSONException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [4[DOLOOP]], but top level block is 7[UNCONDITIONALDOLOOP]
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
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public String getActiveNetworkLabel() {
        Object object;
        block7 : {
            block6 : {
                if (this.sAppContext == null) {
                    return "unknown";
                }
                object = (ConnectivityManager)this.sAppContext.getSystemService("connectivity");
                if (object != null) break block6;
                return "unknown";
            }
            object = object.getActiveNetworkInfo();
            if (object != null) break block7;
            return "unknown";
        }
        if (object.getType() != 1) return object.getTypeName() + "-" + object.getSubtypeName();
        object = (WifiManager)this.sAppContext.getSystemService("wifi");
        if (object == null) return "unknown";
        try {
            if (object.getConnectionInfo() == null) return "unknown";
            return "WIFI-" + object.getConnectionInfo().getSSID();
        }
        catch (Exception var1_2) {
            // empty catch block
        }
        return "unknown";
    }

    public Fallback getFallbacksByHost(String string2) {
        return this.getFallbacksByHost(string2, true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public Fallback getFallbacksByHost(String string2, boolean bl) {
        Fallback fallback;
        if (TextUtils.isEmpty((CharSequence)string2)) {
            throw new IllegalArgumentException("the host is empty");
        }
        if (!this.sHostFilter.accept(string2)) {
            return null;
        }
        final Fallback fallback2 = this.getLocalFallback(string2);
        if (fallback2 != null) {
            fallback = fallback2;
            if (fallback2.isEffective()) return fallback;
        }
        if (!bl) return new Fallback(string2){
            Fallback local;

            @Override
            public void accessHost(String string2, AccessHistory accessHistory) {
                synchronized (this) {
                    if (this.local != null) {
                        this.local.accessHost(string2, accessHistory);
                    }
                    return;
                }
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public ArrayList<String> getHosts(boolean bl) {
                synchronized (this) {
                    ArrayList arrayList = new ArrayList();
                    if (this.local != null) {
                        arrayList.addAll(this.local.getHosts(true));
                    }
                    Map<String, ArrayList<String>> map = HostManager.mReservedHosts;
                    synchronized (map) {
                        ArrayList<String> arrayList2 = HostManager.mReservedHosts.get(this.host);
                        if (arrayList2 != null) {
                            arrayList.addAll(arrayList2);
                        }
                        return arrayList;
                    }
                }
            }

            @Override
            public boolean isEffective() {
                return false;
            }
        };
        if (!Network.hasNetwork(this.sAppContext)) return new ;
        fallback = this.requestRemoteFallback(string2);
        if (fallback == null) return new ;
        return fallback;
    }

    public Fallback getFallbacksByURL(String string2) throws MalformedURLException {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            throw new IllegalArgumentException("the url is empty");
        }
        return this.getFallbacksByHost(new URL(string2).getHost(), true);
    }

    protected String getHost() {
        return "resolver.gslb.mi-idc.com";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    protected Fallback getLocalFallback(String object) {
        Map<String, Fallbacks> map = this.mHostsMapping;
        // MONITORENTER : map
        this.checkHostMapping();
        object = this.mHostsMapping.get(object);
        // MONITOREXIT : map
        if (object == null) return null;
        if ((object = object.getFallback()) == null) return null;
        return object;
    }

    protected String getProcessName() {
        Object object = ((ActivityManager)this.sAppContext.getSystemService("activity")).getRunningAppProcesses();
        if (object != null) {
            object = object.iterator();
            while (object.hasNext()) {
                ActivityManager.RunningAppProcessInfo runningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)object.next();
                if (runningAppProcessInfo.pid != Process.myPid()) continue;
                return runningAppProcessInfo.processName;
            }
        }
        return "com.xiaomi";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected String getRemoteFallbackJSON(ArrayList<String> object, String iterator, String object2) throws IOException {
        Object object32 = new ArrayList();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("type", (String)((Object)iterator)));
        arrayList.add(new BasicNameValuePair("uuid", (String)object2));
        arrayList.add(new BasicNameValuePair("list", HostManager.join(object, ",")));
        object = this.getLocalFallback("resolver.gslb.mi-idc.com");
        iterator = String.format((String)"http://%1$s/gslb/gslb/getbucket.asp?ver=3.0", (Object[])new Object[]{"resolver.gslb.mi-idc.com"});
        if (object == null) {
            object32.add((Object)iterator);
            object = object32;
        } else {
            object = object.getUrls((String)((Object)iterator));
        }
        iterator = null;
        object2 = object.iterator();
        object = iterator;
        while (object2.hasNext()) {
            object = Uri.parse((String)((String)object2.next())).buildUpon();
            for (Object object32 : arrayList) {
                object.appendQueryParameter(object32.getName(), object32.getValue());
            }
            try {
                if (this.sHttpGetter != null) return this.sHttpGetter.doGet(object.toString());
                return Network.downloadXml(this.sAppContext, new URL(object.toString()));
            }
            catch (IOException var1_2) {
                continue;
            }
        }
        if (object == null) return null;
        throw object;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    protected String loadHosts() {
        block10 : {
            block11 : {
                var2_1 = null;
                var3_3 = null;
                var1_6 = var2_1;
                var4_8 = new File(this.sAppContext.getFilesDir(), this.getProcessName());
                var1_6 = var2_1;
                if (!var4_8.isFile()) break block10;
                var1_6 = var2_1;
                var2_1 = new BufferedReader((Reader)new InputStreamReader((InputStream)new FileInputStream(var4_8)));
                try {
                    var1_6 = new StringBuilder();
                    while ((var3_3 = var2_1.readLine()) != null) {
                        var1_6.append((String)var3_3);
                    }
                    var1_6 = var1_6.toString();
                    break block11;
                }
                catch (Throwable var3_4) {}
                ** GOTO lbl-1000
            }
            IOUtils.closeQuietly((Reader)var2_1);
            return var1_6;
        }
        IOUtils.closeQuietly(null);
        return null;
        catch (Throwable var3_5) {
            var1_6 = var2_1;
            var2_1 = var3_5;
            ** GOTO lbl-1000
        }
lbl-1000: // 2 sources:
        {
            do {
                var1_6 = var2_1;
                try {
                    MyLog.warn("load host exception " + var3_3.getMessage());
                }
                catch (Throwable var2_2) lbl-1000: // 2 sources:
                {
                    IOUtils.closeQuietly((Reader)var1_6);
                    throw var2_1;
                }
                IOUtils.closeQuietly((Reader)var2_1);
                return null;
                break;
            } while (true);
        }
        catch (Throwable var1_7) {
            var2_1 = var3_3;
            var3_3 = var1_7;
            ** continue;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void persist() {
        this.purge();
        Map<String, Fallbacks> map = this.mHostsMapping;
        synchronized (map) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter((Writer)new OutputStreamWriter((OutputStream)this.sAppContext.openFileOutput(this.getProcessName(), 0)));
                String string2 = this.toJSON().toString();
                if (!TextUtils.isEmpty((CharSequence)string2)) {
                    bufferedWriter.write(string2);
                }
                bufferedWriter.close();
            }
            catch (IOException var2_3) {
                var2_3.printStackTrace();
            }
            catch (JSONException var2_4) {
                var2_4.printStackTrace();
            }
            catch (Exception var2_5) {
                var2_5.printStackTrace();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void purge() {
        Map<String, Fallbacks> map = this.mHostsMapping;
        synchronized (map) {
            Iterator iterator = this.mHostsMapping.values().iterator();
            while (iterator.hasNext()) {
                iterator.next().purge(false);
            }
            boolean bl = false;
            block4 : while (!bl) {
                String string2;
                boolean bl2 = true;
                iterator = this.mHostsMapping.keySet().iterator();
                do {
                    bl = bl2;
                    if (!iterator.hasNext()) continue block4;
                } while (!this.mHostsMapping.get(string2 = (String)iterator.next()).getFallbacks().isEmpty());
                this.mHostsMapping.remove(string2);
                bl = false;
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void refreshFallbacks() {
        Map<String, Fallbacks> map = this.mHostsMapping;
        synchronized (map) {
            int n;
            ArrayList arrayList;
            block11 : {
                this.checkHostMapping();
                arrayList = new ArrayList(this.mHostsMapping.keySet());
                try {
                    n = arrayList.size() - 1;
                    break block11;
                }
                catch (Throwable var2_3) {}
                throw var2_3;
            }
            do {
                if (n >= 0) {
                    Fallbacks fallbacks = this.mHostsMapping.get(arrayList.get(n));
                    if (fallbacks != null && fallbacks.getFallback() != null) {
                        arrayList.remove(n);
                    }
                } else {
                    // MONITOREXIT [3, 5, 9] lbl18 : MonitorExitStatement: MONITOREXIT : var3_1
                    map = this.requestRemoteFallbacks(arrayList);
                    n = 0;
                    while (n < arrayList.size()) {
                        if (map.get(n) != null) {
                            this.updateFallbacks((String)arrayList.get(n), (Fallback)map.get(n));
                        }
                        ++n;
                    }
                    return;
                }
                --n;
            } while (true);
        }
    }

    protected Fallback requestRemoteFallback(String object) {
        if (System.currentTimeMillis() - this.lastRemoteRequestTimestamp > this.remoteRequestFailureCount * 60 * 1000) {
            this.lastRemoteRequestTimestamp = System.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            arrayList.add(object);
            object = (Fallback)this.requestRemoteFallbacks(arrayList).get(0);
            if (object != null) {
                this.remoteRequestFailureCount = 0;
                return object;
            }
            if (this.remoteRequestFailureCount < 15) {
                ++this.remoteRequestFailureCount;
            }
        }
        return null;
    }

    public void setCurrentISP(String string2) {
        this.currentISP = string2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected JSONArray toJSON() throws JSONException {
        Map<String, Fallbacks> map = this.mHostsMapping;
        synchronized (map) {
            JSONArray jSONArray = new JSONArray();
            Iterator<Fallbacks> iterator = this.mHostsMapping.values().iterator();
            while (iterator.hasNext()) {
                jSONArray.put((Object)iterator.next().toJSON());
            }
            return jSONArray;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void updateFallbacks(String string2, Fallback fallback) {
        if (TextUtils.isEmpty((CharSequence)string2) || fallback == null) {
            throw new IllegalArgumentException("the argument is invalid " + string2 + ", " + fallback);
        }
        if (!this.sHostFilter.accept(string2)) {
            return;
        }
        Map<String, Fallbacks> map = this.mHostsMapping;
        synchronized (map) {
            this.checkHostMapping();
            if (this.mHostsMapping.containsKey(string2)) {
                this.mHostsMapping.get(string2).addFallback(fallback);
            } else {
                Fallbacks fallbacks = new Fallbacks(string2);
                fallbacks.addFallback(fallback);
                this.mHostsMapping.put(string2, fallbacks);
            }
            return;
        }
    }

    public static interface HostManagerFactory {
        public HostManager createHostManager(Context var1, HostFilter var2, HttpGet var3, String var4);
    }

    public static interface HttpGet {
        public String doGet(String var1) throws IOException;
    }

}

