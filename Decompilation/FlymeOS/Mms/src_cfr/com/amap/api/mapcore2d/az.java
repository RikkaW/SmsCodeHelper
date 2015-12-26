/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.Throwable
 *  java.util.HashMap
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpHost
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import com.amap.api.mapcore2d.ba;
import com.amap.api.mapcore2d.cy;
import com.amap.api.mapcore2d.da;
import com.amap.api.mapcore2d.dc;
import com.amap.api.mapcore2d.dg;
import com.amap.api.mapcore2d.di;
import com.amap.api.mapcore2d.ex;
import com.amap.api.mapcore2d.ey;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.json.JSONException;
import org.json.JSONObject;

class az
extends ey {
    private Context a;
    private String e;

    public az(Context context) {
        this.a = context;
        this.e = da.f(context);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private ba a(byte[] object) {
        if (object == null) {
            return null;
        }
        try {
            String string2 = (object = new JSONObject(new String((byte[])object))).optString("status");
            if ("0".equals((Object)string2)) return null;
            if (!"1".equals((Object)string2)) return null;
            object = this.a((JSONObject)object, "result");
            boolean bl2 = this.a(this.b(this.a(this.a((JSONObject)object, "exception"), "exceptinfo"), "ex_isupload"));
            return new ba(this.a(this.b(this.a(this.a((JSONObject)object, "common"), "commoninfo"), "com_isupload")), bl2);
        }
        catch (JSONException var1_2) {
            cy.a((Throwable)var1_2, "ManifestManager", "loadData");
            return null;
        }
        catch (Exception var1_3) {
            cy.a(var1_3, "ManifestManager", "loadData");
            return null;
        }
    }

    private JSONObject a(JSONObject jSONObject, String string2) {
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.optJSONObject(string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean a(String string2) {
        if (string2 == null || !string2.equals((Object)"1")) {
            return false;
        }
        return true;
    }

    private String b(JSONObject jSONObject, String string2) {
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.optString(string2);
    }

    public ba a() {
        try {
            Object object = ex.a(false);
            this.a(dg.a(this.a));
            object = this.a(object.a(this));
            return object;
        }
        catch (Exception var1_2) {
            cy.a(var1_2, "ManifestManager", "feachManifest");
            return null;
        }
    }

    @Override
    public Map<String, String> b() {
        HashMap hashMap = new HashMap();
        hashMap.put("User-Agent", "AMAP SDK Android 2DMap 2.5.0");
        return hashMap;
    }

    @Override
    public Map<String, String> c() {
        HashMap hashMap = new HashMap();
        hashMap.put("key", this.e);
        hashMap.put("opertype", "common;exception");
        hashMap.put("plattype", "android");
        hashMap.put("product", "2dmap");
        hashMap.put("version", "2.5.0");
        hashMap.put("ext", "standard");
        hashMap.put("output", "json");
        Object object = new StringBuffer();
        object.append("key=").append(this.e);
        object.append("&opertype=common;exception");
        object.append("&plattype=android");
        object.append("&product=").append("2dmap");
        object.append("&version=").append("2.5.0");
        object.append("&ext=standard");
        object.append("&output=json");
        object = di.a(object.toString());
        String string2 = dc.a();
        hashMap.put("ts", string2);
        hashMap.put("scode", dc.a(this.a, string2, (String)object));
        return hashMap;
    }

    @Override
    public String d() {
        return "http://restapi.amap.com/v3/config/resource";
    }

    @Override
    public HttpEntity e() {
        return null;
    }
}

