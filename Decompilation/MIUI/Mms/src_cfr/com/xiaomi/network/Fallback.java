/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.net.URL
 *  java.util.ArrayList
 *  java.util.Arrays
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.xiaomi.network;

import android.text.TextUtils;
import com.xiaomi.network.AccessHistory;
import com.xiaomi.network.Host;
import com.xiaomi.network.HostManager;
import com.xiaomi.network.WeightedHost;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Fallback {
    public String city;
    public String country;
    private long effectDuration = 86400000;
    private ArrayList<WeightedHost> fallbackHosts = new ArrayList();
    public String host;
    public String ip;
    public String isp;
    private String mDomain = "s.mi1.cc";
    private String mISP;
    private double mPercent = 0.1;
    public String networkLabel;
    public String province;
    private long timestamp;
    protected String xforward;

    public Fallback(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            throw new IllegalArgumentException("the host is empty");
        }
        this.timestamp = System.currentTimeMillis();
        this.fallbackHosts.add((Object)new WeightedHost(string2, -1));
        this.networkLabel = HostManager.getInstance().getActiveNetworkLabel();
        this.host = string2;
    }

    private void deleteWeightedHost(String string2) {
        synchronized (this) {
            Iterator iterator = this.fallbackHosts.iterator();
            while (iterator.hasNext()) {
                if (!TextUtils.equals((CharSequence)((WeightedHost)iterator.next()).host, (CharSequence)string2)) continue;
                iterator.remove();
            }
            return;
        }
    }

    public void accessHost(String string2, int n, long l, long l2, Exception exception) {
        this.accessHost(string2, new AccessHistory(n, l, l2, exception));
    }

    public void accessHost(String string2, AccessHistory accessHistory) {
        synchronized (this) {
            for (WeightedHost weightedHost : this.fallbackHosts) {
                if (!TextUtils.equals((CharSequence)string2, (CharSequence)weightedHost.host)) continue;
                weightedHost.addAccessHistory(accessHistory);
                break;
            }
            return;
        }
    }

    void addHost(WeightedHost weightedHost) {
        synchronized (this) {
            this.deleteWeightedHost(weightedHost.host);
            this.fallbackHosts.add((Object)weightedHost);
            return;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public void addPreferredHost(String[] var1_1) {
        // MONITORENTER : this
        var2_2 = this.fallbackHosts.size() - 1;
        block3 : do {
            if (var2_2 < 0) {
                var2_2 = 0;
                for (WeightedHost var6_6 : this.fallbackHosts) {
                    if (var6_6.weight <= var2_2) continue;
                    var2_2 = var6_6.weight;
                }
                var3_3 = 0;
                do {
                    if (var3_3 >= var1_1.length) {
                        // MONITOREXIT : this
                        return;
                    }
                    this.addHost(new WeightedHost(var1_1[var3_3], var1_1.length + var2_2 - var3_3));
                    ++var3_3;
                } while (true);
            }
            var4_4 = var1_1.length;
            var3_3 = 0;
            do {
                if (var3_3 >= var4_4) ** GOTO lbl25
                var5_5 = var1_1[var3_3];
                if (TextUtils.equals((CharSequence)((WeightedHost)this.fallbackHosts.get((int)var2_2)).host, (CharSequence)var5_5)) {
                    this.fallbackHosts.remove(var2_2);
lbl25: // 2 sources:
                    --var2_2;
                    continue block3;
                }
                ++var3_3;
            } while (true);
            break;
        } while (true);
    }

    public void failedHost(String string2, long l, long l2, Exception exception) {
        this.accessHost(string2, -1, l, l2, exception);
    }

    public void failedUrl(String string2, long l, long l2, Exception exception) {
        try {
            this.failedHost(new URL(string2).getHost(), l, l2, exception);
            return;
        }
        catch (MalformedURLException var1_2) {
            return;
        }
    }

    public Fallback fromJSON(JSONObject jSONObject) throws JSONException {
        synchronized (this) {
            this.networkLabel = jSONObject.optString("net");
            this.effectDuration = jSONObject.getLong("ttl");
            this.mPercent = jSONObject.getDouble("pct");
            this.timestamp = jSONObject.getLong("ts");
            this.city = jSONObject.optString("city");
            this.province = jSONObject.optString("prv");
            this.country = jSONObject.optString("cty");
            this.isp = jSONObject.optString("isp");
            this.ip = jSONObject.optString("ip");
            this.host = jSONObject.optString("host");
            this.xforward = jSONObject.optString("xf");
            jSONObject = jSONObject.getJSONArray("fbs");
            int n = 0;
            do {
                if (n >= jSONObject.length()) break;
                this.addHost(new WeightedHost().fromJSON(jSONObject.getJSONObject(n)));
                ++n;
                continue;
                break;
            } while (true);
            return this;
        }
    }

    public ArrayList<String> getHosts() {
        synchronized (this) {
            ArrayList<String> arrayList = this.getHosts(false);
            return arrayList;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ArrayList<String> getHosts(boolean bl) {
        synchronized (this) {
            Object[] arrobject = new WeightedHost[this.fallbackHosts.size()];
            this.fallbackHosts.toArray(arrobject);
            Arrays.sort((Object[])arrobject);
            ArrayList arrayList = new ArrayList();
            int n = arrobject.length;
            int n2 = 0;
            while (n2 < n) {
                Object object = arrobject[n2];
                if (bl) {
                    arrayList.add((Object)object.host);
                } else {
                    int n3 = object.host.indexOf(":");
                    if (n3 != -1) {
                        arrayList.add((Object)object.host.substring(0, n3));
                    } else {
                        arrayList.add((Object)object.host);
                    }
                }
                ++n2;
            }
            return arrayList;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public String getISP() {
        synchronized (this) {
            if (!TextUtils.isEmpty((CharSequence)this.mISP)) {
                return this.mISP;
            }
            if (TextUtils.isEmpty((CharSequence)this.isp)) {
                return "hardcode_isp";
            }
            this.mISP = HostManager.join(new String[]{this.isp, this.province, this.city, this.country, this.ip}, "_");
            return this.mISP;
        }
    }

    public double getPercent() {
        if (this.mPercent < 1.0E-5) {
            return 0.1;
        }
        return this.mPercent;
    }

    public ArrayList<String> getUrls(String string2) throws MalformedURLException {
        ArrayList arrayList;
        if (TextUtils.isEmpty((CharSequence)string2)) {
            throw new IllegalArgumentException("the url is empty.");
        }
        if (TextUtils.equals((CharSequence)(string2 = new URL(string2)).getHost(), (CharSequence)this.host)) {
            arrayList = new ArrayList();
            Iterator iterator = this.getHosts(true).iterator();
            while (iterator.hasNext()) {
                Host host = Host.parse((String)iterator.next(), string2.getPort());
                arrayList.add((Object)new URL(string2.getProtocol(), host.getHost(), host.getPort(), string2.getFile()).toString());
            }
        } else {
            throw new IllegalArgumentException("the url is not supported by the fallback");
        }
        return arrayList;
    }

    ArrayList<WeightedHost> getWeightedHost() {
        return this.fallbackHosts;
    }

    public boolean isEffective() {
        if (System.currentTimeMillis() - this.timestamp < this.effectDuration) {
            return true;
        }
        return false;
    }

    boolean isExpired() {
        long l;
        long l2 = 864000000;
        if (864000000 < this.effectDuration) {
            l2 = this.effectDuration;
        }
        if ((l = System.currentTimeMillis()) - this.timestamp > l2 || l - this.timestamp > this.effectDuration && this.networkLabel.startsWith("WIFI-")) {
            return true;
        }
        return false;
    }

    public boolean match() {
        return TextUtils.equals((CharSequence)this.networkLabel, (CharSequence)HostManager.getInstance().getActiveNetworkLabel());
    }

    public boolean match(Fallback fallback) {
        return TextUtils.equals((CharSequence)this.networkLabel, (CharSequence)fallback.networkLabel);
    }

    public void setDomainName(String string2) {
        this.mDomain = string2;
    }

    public void setEffectiveDuration(long l) {
        if (l <= 0) {
            throw new IllegalArgumentException("the duration is invalid " + l);
        }
        this.effectDuration = l;
    }

    public void setPercent(double d2) {
        this.mPercent = d2;
    }

    public void succeedHost(String string2, long l, long l2) {
        this.accessHost(string2, 0, l, l2, null);
    }

    public void succeedUrl(String string2, long l, long l2) {
        try {
            this.succeedHost(new URL(string2).getHost(), l, l2);
            return;
        }
        catch (MalformedURLException var1_2) {
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public JSONObject toJSON() throws JSONException {
        synchronized (this) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("net", (Object)this.networkLabel);
            jSONObject.put("ttl", this.effectDuration);
            jSONObject.put("pct", this.mPercent);
            jSONObject.put("ts", this.timestamp);
            jSONObject.put("city", (Object)this.city);
            jSONObject.put("prv", (Object)this.province);
            jSONObject.put("cty", (Object)this.country);
            jSONObject.put("isp", (Object)this.isp);
            jSONObject.put("ip", (Object)this.ip);
            jSONObject.put("host", (Object)this.host);
            jSONObject.put("xf", (Object)this.xforward);
            JSONArray jSONArray = new JSONArray();
            Iterator iterator = this.fallbackHosts.iterator();
            do {
                if (!iterator.hasNext()) {
                    jSONObject.put("fbs", (Object)jSONArray);
                    return jSONObject;
                }
                jSONArray.put((Object)((WeightedHost)iterator.next()).toJSON());
            } while (true);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.networkLabel);
        stringBuilder.append("\n");
        stringBuilder.append(this.getISP());
        for (WeightedHost weightedHost : this.fallbackHosts) {
            stringBuilder.append("\n");
            stringBuilder.append(weightedHost.toString());
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}

