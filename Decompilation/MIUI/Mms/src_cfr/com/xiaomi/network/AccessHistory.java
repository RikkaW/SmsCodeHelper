/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.xiaomi.network;

import org.json.JSONException;
import org.json.JSONObject;

public class AccessHistory {
    private long cost;
    private String exceptionName;
    private long size;
    private long ts;
    private int weight;

    public AccessHistory() {
        this(0, 0, 0, null);
    }

    public AccessHistory(int n, long l, long l2, Exception exception) {
        this.weight = n;
        this.cost = l;
        this.size = l2;
        this.ts = System.currentTimeMillis();
        if (exception != null) {
            this.exceptionName = exception.getClass().getSimpleName();
        }
    }

    public AccessHistory fromJSON(JSONObject jSONObject) throws JSONException {
        this.cost = jSONObject.getLong("cost");
        this.size = jSONObject.getLong("size");
        this.ts = jSONObject.getLong("ts");
        this.weight = jSONObject.getInt("wt");
        this.exceptionName = jSONObject.optString("expt");
        return this;
    }

    public long getCost() {
        return this.cost;
    }

    public String getException() {
        return this.exceptionName;
    }

    public long getSize() {
        return this.size;
    }

    public long getTime() {
        return this.ts;
    }

    public int getWeight() {
        return this.weight;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cost", this.cost);
        jSONObject.put("size", this.size);
        jSONObject.put("ts", this.ts);
        jSONObject.put("wt", this.weight);
        jSONObject.put("expt", (Object)this.exceptionName);
        return jSONObject;
    }
}

