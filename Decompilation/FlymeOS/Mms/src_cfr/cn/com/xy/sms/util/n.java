/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package cn.com.xy.sms.util;

import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.util.ParseManager;
import cn.com.xy.sms.util.SdkCallBack;
import cn.com.xy.sms.util.m;
import org.json.JSONException;
import org.json.JSONObject;

final class n
implements SdkCallBack {
    private /* synthetic */ m a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ String d;
    private final /* synthetic */ String e;
    private final /* synthetic */ SdkCallBack f;

    n(m m2, String string2, String string3, String string4, String string5, SdkCallBack sdkCallBack) {
        this.b = string2;
        this.c = string3;
        this.d = string4;
        this.e = string5;
        this.f = sdkCallBack;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @Override
    public final /* varargs */ void execute(Object ... var1_1) {
        block26 : {
            var6_5 = null;
            var7_6 = null;
            var5_7 = null;
            if (!StringUtils.isNull(this.b)) {
                try {
                    var3_11 = new JSONObject(this.b);
                    break block26;
                }
                catch (JSONException var3_12) {
                    var3_12.printStackTrace();
                }
            }
            var3_11 = null;
        }
        var4_13 = var1_1 != null && var1_1.length == 2 && var1_1[1] != null && "offNetwork".equalsIgnoreCase(var1_1[1].toString()) != false ? "offNetwork" : null;
        if (var1_1 == null) ** GOTO lbl-1000
        ** try [egrp 1[TRYBLOCK] [1 : 69->76)] { 
lbl-1000: // 4 sources:
        {
            catch (Throwable var5_10) {}
        }
lbl16: // 1 sources:
        if (var1_1.length != 6 || var1_1[0] == null || var1_1[1] == null) lbl-1000: // 2 sources:
        {
            block27 : {
                XyUtil.doXycallBackResult(this.f, new Object[]{var4_13});
                if (var3_11 != null) break block27;
                return;
            }
            try {
                var3_11.put("QueryTime", System.currentTimeMillis());
                var3_11.put("networkState", (Object)var4_13);
                var4_13 = var5_7;
                if (var1_1 != null) {
                    var4_13 = var5_7;
                    if (var1_1.length > 1) {
                        var4_13 = JsonUtil.getValueFromJsonObject((JSONObject)var1_1[1], "station_list").toString();
                    }
                }
                if (!StringUtils.isNull(var4_13)) {
                    var3_11.put("station_list", (Object)var4_13);
                }
                if (var1_1 == null) return;
                if (var1_1.length <= 0) return;
                ParseManager.updateMatchCacheManager(this.c, this.d, (String)var1_1[0], var3_11, this.e);
                return;
            }
            catch (JSONException var1_2) {
                var1_2.printStackTrace();
                return;
            }
        }
        if (!((Boolean)var1_1[5]).booleanValue() || (var2_14 = ParseManager.checkStationList(JsonUtil.getValueFromJsonObject((JSONObject)var1_1[1], "station_list").toString(), (String)var1_1[3], (String)var1_1[4], true))) ** GOTO lbl47
        try {
            XyUtil.doXycallBackResult(this.f, null);
            return;
        }
        catch (Throwable var5_8) {
            return;
lbl47: // 2 sources:
            XyUtil.doXycallBackResult(this.f, new Object[]{var1_1[0], var1_1[1], var1_1[2], var1_1[3], var1_1[4], var1_1[5]});
            if (var3_11 == null) return;
            try {
                var3_11.put("QueryTime", System.currentTimeMillis());
                var3_11.put("networkState", (Object)var4_13);
                var4_13 = var7_6;
                if (var1_1 != null) {
                    var4_13 = var7_6;
                    if (var1_1.length > 1) {
                        var4_13 = JsonUtil.getValueFromJsonObject((JSONObject)var1_1[1], "station_list").toString();
                    }
                }
                if (!StringUtils.isNull(var4_13)) {
                    var3_11.put("station_list", (Object)var4_13);
                }
                if (var1_1 == null) return;
                if (var1_1.length <= 0) return;
                ParseManager.updateMatchCacheManager(this.c, this.d, (String)var1_1[0], var3_11, this.e);
                return;
            }
            catch (JSONException var1_3) {
                var1_3.printStackTrace();
                return;
            }
            if (var3_11 == null) return;
            try {
                var3_11.put("QueryTime", System.currentTimeMillis());
                var3_11.put("networkState", (Object)var4_13);
                var4_13 = var6_5;
                if (var1_1 != null) {
                    var4_13 = var6_5;
                    if (var1_1.length > 1) {
                        var4_13 = JsonUtil.getValueFromJsonObject((JSONObject)var1_1[1], "station_list").toString();
                    }
                }
                if (!StringUtils.isNull(var4_13)) {
                    var3_11.put("station_list", (Object)var4_13);
                }
                if (var1_1 == null) throw var5_9;
                if (var1_1.length <= 0) throw var5_9;
                ParseManager.updateMatchCacheManager(this.c, this.d, (String)var1_1[0], var3_11, this.e);
            }
            catch (JSONException var1_4) {
                var1_4.printStackTrace();
                throw var5_9;
            }
            throw var5_9;
        }
    }
}

