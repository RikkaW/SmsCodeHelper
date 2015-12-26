/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.text.TextUtils
 *  java.io.BufferedWriter
 *  java.io.FileOutputStream
 *  java.io.OutputStream
 *  java.io.OutputStreamWriter
 *  java.io.Writer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.net.URL
 *  java.util.ArrayList
 *  java.util.Locale
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.xiaomi.network;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.BasicNameValuePair;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.Fallbacks;
import com.xiaomi.network.HostFilter;
import com.xiaomi.network.HostManager;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HostManagerV2
extends HostManager {
    private final int DEFAULT_PORT = 80;
    private final int RESERVED_PORT = 5222;
    private int port = 80;

    protected HostManagerV2(Context context, HostFilter hostFilter, HostManager.HttpGet httpGet, String string2) {
        this(context, hostFilter, httpGet, string2, null, null);
    }

    protected HostManagerV2(Context context, HostFilter hostFilter, HostManager.HttpGet httpGet, String string2, String string3, String string4) {
        super(context, hostFilter, httpGet, string2, string3, string4);
        HostManagerV2.addReservedHost("resolver.msg.xiaomi.net", "resolver.msg.xiaomi.net:5222");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    protected boolean checkHostMapping() {
        Map map = this.mHostsMapping;
        synchronized (map) {
            if (hostLoaded) {
                return true;
            }
            hostLoaded = true;
            this.mHostsMapping.clear();
            try {
                String string2 = this.loadHosts();
                if (!TextUtils.isEmpty((CharSequence)string2)) {
                    this.fromJSONObject(string2);
                    MyLog.info("loading the new hosts succeed");
                    return true;
                }
            }
            catch (Throwable var2_3) {
                MyLog.warn("load bucket failure: " + var2_3.getMessage());
            }
            return false;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void fromJSONObject(String string2) throws JSONException {
        Map map = this.mHostsMapping;
        synchronized (map) {
            this.mHostsMapping.clear();
            string2 = new JSONObject(string2);
            if (string2.optInt("ver") != 2) {
                throw new JSONException("Bad version");
            }
            string2 = string2.optJSONArray("data");
            int n = 0;
            while (n < string2.length()) {
                Fallbacks fallbacks = new Fallbacks().fromJSON(string2.getJSONObject(n));
                this.mHostsMapping.put(fallbacks.getHost(), fallbacks);
                ++n;
            }
            return;
        }
    }

    @Override
    protected String getHost() {
        return "resolver.msg.xiaomi.net";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    protected String getRemoteFallbackJSON(ArrayList<String> arrayList, String string2, String string3) throws IOException {
        Object object = new ArrayList<String>();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new BasicNameValuePair("type", string2));
        if (string2.equals((Object)"wap")) {
            arrayList2.add(new BasicNameValuePair("connpt", Network.getActiveConnPoint(this.sAppContext)));
        }
        arrayList2.add(new BasicNameValuePair("uuid", string3));
        arrayList2.add(new BasicNameValuePair("list", HostManagerV2.join(arrayList, ",")));
        Iterator iterator = this.getLocalFallback("resolver.msg.xiaomi.net");
        Object object2 = String.format((Locale)Locale.US, (String)"http://%1$s/gslb/?ver=3.0", (Object[])new Object[]{"resolver.msg.xiaomi.net:" + this.port});
        if (iterator == null) {
            object.add(object2);
            iterator = mReservedHosts;
            synchronized (iterator) {
                for (Object object3 : (ArrayList)mReservedHosts.get("resolver.msg.xiaomi.net")) {
                    object.add((Object)String.format((Locale)Locale.US, (String)"http://%1$s/gslb/?ver=3.0", (Object[])new Object[]{object3}));
                }
            }
        } else {
            object = iterator.getUrls((String)object2);
        }
        iterator = null;
        object2 = object.iterator();
        object = iterator;
        while (object2.hasNext()) {
            object = Uri.parse((String)((String)object2.next())).buildUpon();
            for (Object object3 : arrayList2) {
                object.appendQueryParameter(object3.getName(), object3.getValue());
            }
            try {
                if (this.sHttpGetter != null) return this.sHttpGetter.doGet(object.toString());
                return Network.downloadXml(this.sAppContext, new URL(object.toString()));
            }
            catch (IOException var4_5) {
                continue;
            }
        }
        if (object == null) return null;
        return super.getRemoteFallbackJSON(arrayList, string2, string3);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void persist() {
        Map map = this.mHostsMapping;
        synchronized (map) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter((Writer)new OutputStreamWriter((OutputStream)this.sAppContext.openFileOutput(this.getProcessName(), 0)));
                String string2 = this.toJSONObject().toString();
                if (!TextUtils.isEmpty((CharSequence)string2)) {
                    bufferedWriter.write(string2);
                }
                bufferedWriter.close();
            }
            catch (Exception var2_3) {
                MyLog.warn("persist bucket failure: " + var2_3.getMessage());
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
    public void purge() {
        Map map = this.mHostsMapping;
        synchronized (map) {
            Iterator iterator = this.mHostsMapping.values().iterator();
            while (iterator.hasNext()) {
                ((Fallbacks)iterator.next()).purge(true);
            }
            boolean bl = false;
            block4 : while (!bl) {
                String string2;
                boolean bl2 = true;
                iterator = this.mHostsMapping.keySet().iterator();
                do {
                    bl = bl2;
                    if (!iterator.hasNext()) continue block4;
                } while (!((Fallbacks)this.mHostsMapping.get(string2 = (String)iterator.next())).getFallbacks().isEmpty());
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
    protected JSONObject toJSONObject() throws JSONException {
        Map map = this.mHostsMapping;
        synchronized (map) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ver", 2);
            jSONObject.put("data", (Object)this.toJSON());
            return jSONObject;
        }
    }
}

