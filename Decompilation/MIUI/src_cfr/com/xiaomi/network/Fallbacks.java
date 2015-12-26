/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.xiaomi.network;

import android.text.TextUtils;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostManager;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class Fallbacks {
    private String host;
    private final ArrayList<Fallback> mFallbacks = new ArrayList();

    public Fallbacks() {
    }

    public Fallbacks(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            throw new IllegalArgumentException("the host is empty");
        }
        this.host = string2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public void addFallback(Fallback var1_1) {
        // MONITORENTER : this
        var2_2 = 0;
        do {
            if (var2_2 >= this.mFallbacks.size()) ** GOTO lbl7
            if (((Fallback)this.mFallbacks.get(var2_2)).match(var1_1)) {
                this.mFallbacks.set(var2_2, (Object)var1_1);
lbl7: // 2 sources:
                if (var2_2 >= this.mFallbacks.size()) {
                    this.mFallbacks.add((Object)var1_1);
                }
                // MONITOREXIT : this
                return;
            }
            ++var2_2;
        } while (true);
    }

    public Fallbacks fromJSON(JSONObject jSONObject) throws JSONException {
        synchronized (this) {
            this.host = jSONObject.getString("host");
            jSONObject = jSONObject.getJSONArray("fbs");
            int n = 0;
            do {
                if (n >= jSONObject.length()) break;
                this.mFallbacks.add((Object)new Fallback(this.host).fromJSON(jSONObject.getJSONObject(n)));
                ++n;
                continue;
                break;
            } while (true);
            return this;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public Fallback getFallback() {
        synchronized (this) {
            int n = this.mFallbacks.size() - 1;
            while (n >= 0) {
                Fallback fallback = (Fallback)this.mFallbacks.get(n);
                if (fallback.match()) {
                    HostManager.getInstance().setCurrentISP(fallback.getISP());
                    return fallback;
                }
                --n;
                continue;
            }
            return null;
        }
    }

    public ArrayList<Fallback> getFallbacks() {
        return this.mFallbacks;
    }

    public String getHost() {
        return this.host;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void purge(boolean bl) {
        synchronized (this) {
            int n = this.mFallbacks.size() - 1;
            while (n >= 0) {
                Fallback fallback = (Fallback)this.mFallbacks.get(n);
                if (bl) {
                    if (fallback.isExpired()) {
                        this.mFallbacks.remove(n);
                    }
                } else if (!fallback.isEffective()) {
                    this.mFallbacks.remove(n);
                }
                --n;
            }
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
            jSONObject.put("host", (Object)this.host);
            JSONArray jSONArray = new JSONArray();
            Iterator iterator = this.mFallbacks.iterator();
            do {
                if (!iterator.hasNext()) {
                    jSONObject.put("fbs", (Object)jSONArray);
                    return jSONObject;
                }
                jSONArray.put((Object)((Fallback)iterator.next()).toJSON());
            } while (true);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.host);
        stringBuilder.append("\n");
        Iterator iterator = this.mFallbacks.iterator();
        while (iterator.hasNext()) {
            stringBuilder.append((Fallback)iterator.next());
        }
        return stringBuilder.toString();
    }
}

