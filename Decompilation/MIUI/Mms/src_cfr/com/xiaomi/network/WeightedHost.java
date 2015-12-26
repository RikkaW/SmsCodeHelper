/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.ArrayList
 *  java.util.LinkedList
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.xiaomi.network;

import com.xiaomi.network.AccessHistory;
import com.xiaomi.network.UploadHostStatHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class WeightedHost
implements Comparable<WeightedHost> {
    private final LinkedList<AccessHistory> accessHistories = new LinkedList();
    String host;
    private long touchedTime = 0;
    protected int weight;

    public WeightedHost() {
        this(null, 0);
    }

    public WeightedHost(String string2, int n) {
        this.host = string2;
        this.weight = n;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void addAccessHistory(AccessHistory accessHistory) {
        synchronized (this) {
            if (accessHistory != null) {
                UploadHostStatHelper.getInstance().fireHostEvent();
                this.accessHistories.add((Object)accessHistory);
                int n = accessHistory.getWeight();
                if (n > 0) {
                    this.weight += accessHistory.getWeight();
                } else {
                    int n2 = 0;
                    for (int i = this.accessHistories.size() - 1; i >= 0 && ((AccessHistory)this.accessHistories.get(i)).getWeight() < 0; ++n2, --i) {
                    }
                    this.weight += n * n2;
                }
                if (this.accessHistories.size() > 30) {
                    accessHistory = (AccessHistory)this.accessHistories.remove();
                    this.weight -= accessHistory.getWeight();
                }
            }
            return;
        }
    }

    @Override
    public int compareTo(WeightedHost weightedHost) {
        if (weightedHost == null) {
            return 1;
        }
        return weightedHost.weight - this.weight;
    }

    public WeightedHost fromJSON(JSONObject jSONObject) throws JSONException {
        synchronized (this) {
            this.touchedTime = jSONObject.getLong("tt");
            this.weight = jSONObject.getInt("wt");
            this.host = jSONObject.getString("host");
            jSONObject = jSONObject.getJSONArray("ah");
            int n = 0;
            do {
                if (n >= jSONObject.length()) break;
                JSONObject jSONObject2 = jSONObject.getJSONObject(n);
                this.accessHistories.add((Object)new AccessHistory().fromJSON(jSONObject2));
                ++n;
                continue;
                break;
            } while (true);
            return this;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ArrayList<AccessHistory> getUnTouchedAccessHistory() {
        synchronized (this) {
            ArrayList arrayList = new ArrayList();
            Iterator iterator = this.accessHistories.iterator();
            do {
                if (!iterator.hasNext()) {
                    this.touchedTime = System.currentTimeMillis();
                    return arrayList;
                }
                AccessHistory accessHistory = (AccessHistory)iterator.next();
                if (accessHistory.getTime() <= this.touchedTime) continue;
                arrayList.add((Object)accessHistory);
            } while (true);
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
            jSONObject.put("tt", this.touchedTime);
            jSONObject.put("wt", this.weight);
            jSONObject.put("host", (Object)this.host);
            JSONArray jSONArray = new JSONArray();
            Iterator iterator = this.accessHistories.iterator();
            do {
                if (!iterator.hasNext()) {
                    jSONObject.put("ah", (Object)jSONArray);
                    return jSONObject;
                }
                jSONArray.put((Object)((AccessHistory)iterator.next()).toJSON());
            } while (true);
        }
    }

    public String toString() {
        return this.host + ":" + this.weight;
    }
}

