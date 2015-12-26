/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package cn.com.xy.sms.util;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.util.ParseManager;
import cn.com.xy.sms.util.SdkCallBack;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class k
implements XyCallBack {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ String d;
    private final /* synthetic */ String e;
    private final /* synthetic */ SdkCallBack f;
    private final /* synthetic */ String g;
    private final /* synthetic */ String h;
    private final /* synthetic */ String i;
    private final /* synthetic */ String j;
    private final /* synthetic */ String k;

    k(String string2, String string3, String string4, String string5, String string6, SdkCallBack sdkCallBack, String string7, String string8, String string9, String string10, String string11) {
        this.a = string2;
        this.b = string3;
        this.c = string4;
        this.d = string5;
        this.e = string6;
        this.f = sdkCallBack;
        this.g = string7;
        this.h = string8;
        this.i = string9;
        this.j = string10;
        this.k = string11;
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public final /* varargs */ void execute(Object ... var1_1) {
        block80 : {
            block81 : {
                var21_9 = null;
                var20_10 = null;
                if (!StringUtils.isNull(this.a)) {
                    try {
                        var19_11 = new JSONObject(this.a);
                    }
                    catch (JSONException var3_12) {
                        var3_12.printStackTrace();
                    }
lbl6: // 2 sources:
                    do {
                        block79 : {
                            if (var1_1 != null) {
                                var3_13 = var21_9;
                                if (var1_1.length < 2) break block79;
                                var3_13 = var21_9;
                                if (var1_1[1].toString().indexOf("[") == 0) break block80;
                            }
                        }
                        var3_13 = var21_9;
                        XyUtil.doXycallBackResult(this.f, null);
                        ** if (var19_11 != null) goto lbl-1000
lbl-1000: // 5 sources:
                        {
                            do {
                                return;
                                break;
                            } while (true);
                        }
lbl-1000: // 1 sources:
                        {
                            break block81;
                        }
                        break;
                    } while (true);
                }
                var19_11 = null;
                ** while (true)
            }
            try {
                var19_11.put("QueryTime", System.currentTimeMillis());
                ParseManager.updateMatchCacheManager(this.b, this.c, this.d, var19_11, this.e);
                return;
            }
            catch (JSONException var1_2) {
                var1_2.printStackTrace();
                return;
            }
        }
        var3_13 = var21_9;
        var22_15 = new JSONArray((String)var1_1[1]);
        var4_16 = "";
        var5_20 = "";
        var6_21 = "";
        var8_22 = "";
        var1_1 = "";
        var10_23 = "";
        var7_24 = "";
        var2_25 = 0;
        do {
            block91 : {
                block85 : {
                    block83 : {
                        block90 : {
                            block89 : {
                                block88 : {
                                    block87 : {
                                        block86 : {
                                            block84 : {
                                                block82 : {
                                                    var18_34 = "";
                                                    var3_13 = var21_9;
                                                    if (var2_25 < var22_15.length()) break block82;
                                                    var12_28 = var8_22;
                                                    var14_30 = var1_1;
                                                    var15_31 = var10_23;
                                                    var17_33 = var7_24;
lbl56: // 3 sources:
                                                    var3_13 = var21_9;
                                                    if (StringUtils.isNull(var10_23)) break block83;
                                                    var3_13 = var21_9;
                                                    var11_27 = new JSONObject();
                                                    var11_27.put("FlightCompany", (Object)var10_23);
                                                    var11_27.put("FlightDeptimePlanDate", var9_26);
                                                    var11_27.put("FlightArrtimePlanDate", var8_22);
                                                    var11_27.put("FlightHTerminal", var7_24);
                                                    var11_27.put("FlightDep", var6_21);
                                                    var11_27.put("FlightArr", var5_20);
                                                    var11_27.put("FlightQueryTime", System.currentTimeMillis());
                                                    var11_27.put("FlightDepAirport", var1_1);
                                                    var11_27.put("FlightArrAirport", (Object)var4_16);
                                                    XyUtil.doXycallBackResult(this.f, new Object[]{this.k, var11_27});
                                                    if (var19_11 == null) ** GOTO lbl-1000
                                                    try {
                                                        var19_11.put("QueryTime", System.currentTimeMillis());
                                                        JsonUtil.JSONCombine(var19_11, (JSONObject)var11_27);
                                                        ParseManager.updateMatchCacheManager(this.b, this.c, this.d, var19_11, this.e);
                                                        return;
                                                    }
                                                    catch (JSONException var1_3) {
                                                        var1_3.printStackTrace();
                                                        return;
                                                    }
                                                }
                                                var3_13 = var21_9;
                                                var4_16 = (JSONObject)var22_15.get(var2_25);
                                                var3_13 = var21_9;
                                                var16_32 = var4_16.getString("FlightCompany");
                                                var3_13 = var21_9;
                                                var7_24 = var4_16.getString("FlightDeptimePlanDate");
                                                var3_13 = var21_9;
                                                var10_23 = var4_16.getString("FlightArrtimePlanDate");
                                                var3_13 = var21_9;
                                                var1_1 = var4_16.getString("FlightHTerminal");
                                                var3_13 = var21_9;
                                                var8_22 = var4_16.getString("FlightDep");
                                                var3_13 = var21_9;
                                                var11_27 = var4_16.getString("FlightArr");
                                                var3_13 = var21_9;
                                                var9_26 = var4_16.getString("FlightDepAirport");
                                                var3_13 = var21_9;
                                                var13_29 = var4_16.getString("FlightArrAirport");
                                                var3_13 = var21_9;
                                                if (!StringUtils.isNull(this.g)) break block84;
                                                var3_13 = var21_9;
                                                if (!StringUtils.isNull(this.h)) break block84;
                                                var3_13 = var21_9;
                                                if (!StringUtils.isNull(this.i)) break block84;
                                                var3_13 = var21_9;
                                                var4_16 = var13_29;
                                                var5_20 = var9_26;
                                                var6_21 = var11_27;
                                                var12_28 = var8_22;
                                                var14_30 = var1_1;
                                                var15_31 = var10_23;
                                                var17_33 = var7_24;
                                                var18_34 = var16_32;
                                                if (StringUtils.isNull(this.j)) break block85;
                                            }
                                            var3_13 = var21_9;
                                            if (StringUtils.isNull(this.g)) break block86;
                                            var3_13 = var21_9;
                                            if (StringUtils.isNull(this.h)) break block86;
                                            var3_13 = var21_9;
                                            if (!this.g.equals(var8_22)) break block86;
                                            var3_13 = var21_9;
                                            var4_16 = var13_29;
                                            var5_20 = var9_26;
                                            var6_21 = var11_27;
                                            var12_28 = var8_22;
                                            var14_30 = var1_1;
                                            var15_31 = var10_23;
                                            var17_33 = var7_24;
                                            var18_34 = var16_32;
                                            if (this.h.equals(var11_27)) break block85;
                                        }
                                        var3_13 = var21_9;
                                        if (StringUtils.isNull(this.g)) break block87;
                                        var3_13 = var21_9;
                                        if (!StringUtils.isNull(this.h)) break block87;
                                        var3_13 = var21_9;
                                        if (!this.g.equals(var8_22)) break block87;
                                        var4_16 = "";
                                        var10_23 = var16_32;
                                        var3_13 = var7_24;
                                        var11_27 = "";
                                        var7_24 = var1_1;
                                        var6_21 = var8_22;
                                        var5_20 = "";
                                        var1_1 = var9_26;
                                        var8_22 = var11_27;
                                        var9_26 = var3_13;
                                    }
                                    var3_13 = var21_9;
                                    if (StringUtils.isNull(this.h)) break block88;
                                    var3_13 = var21_9;
                                    if (!StringUtils.isNull(this.g)) break block88;
                                    var3_13 = var21_9;
                                    if (!this.h.equals(var11_27)) break block88;
                                    var9_26 = "";
                                    var8_22 = var10_23;
                                    var7_24 = var1_1;
                                    var6_21 = "";
                                    var5_20 = var11_27;
                                    var1_1 = "";
                                    var4_16 = var13_29;
                                    var10_23 = var16_32;
                                }
                                var3_13 = var21_9;
                                if (!StringUtils.isNull(this.g)) break block89;
                                var3_13 = var21_9;
                                if (!StringUtils.isNull(this.h)) break block89;
                                var3_13 = var21_9;
                                if (StringUtils.isNull(this.i)) break block89;
                                var3_13 = var21_9;
                                if (StringUtils.isNull(this.j)) break block89;
                                var3_13 = var21_9;
                                if (!var9_26.contains((CharSequence)this.i)) break block89;
                                var3_13 = var21_9;
                                var4_16 = var13_29;
                                var5_20 = var9_26;
                                var6_21 = var11_27;
                                var12_28 = var8_22;
                                var14_30 = var1_1;
                                var15_31 = var10_23;
                                var17_33 = var7_24;
                                var18_34 = var16_32;
                                if (var13_29.contains((CharSequence)this.j)) break block85;
                            }
                            var3_13 = var21_9;
                            if (!StringUtils.isNull(this.g)) break block90;
                            var3_13 = var21_9;
                            if (!StringUtils.isNull(this.h)) break block90;
                            var3_13 = var21_9;
                            if (!StringUtils.isNull(this.j)) break block90;
                            var3_13 = var21_9;
                            if (StringUtils.isNull(this.i)) break block90;
                            var3_13 = var21_9;
                            var4_16 = var13_29;
                            var5_20 = var9_26;
                            var6_21 = var11_27;
                            var12_28 = var8_22;
                            var14_30 = var1_1;
                            var15_31 = var10_23;
                            var17_33 = var7_24;
                            var18_34 = var16_32;
                            if (var9_26.contains((CharSequence)this.i)) break block85;
                        }
                        var3_13 = var21_9;
                        if (!StringUtils.isNull(this.g)) break block91;
                        var3_13 = var21_9;
                        if (!StringUtils.isNull(this.h)) break block91;
                        var3_13 = var21_9;
                        if (!StringUtils.isNull(this.i)) break block91;
                        var3_13 = var21_9;
                        if (StringUtils.isNull(this.j)) break block91;
                        var3_13 = var21_9;
                        var4_16 = var13_29;
                        var5_20 = var9_26;
                        var6_21 = var11_27;
                        var12_28 = var8_22;
                        var14_30 = var1_1;
                        var15_31 = var10_23;
                        var17_33 = var7_24;
                        var18_34 = var16_32;
                        if (!var13_29.contains((CharSequence)this.j)) {
                        }
                        break block85;
                    }
                    var3_13 = var21_9;
                    XyUtil.doXycallBackResult(this.f, null);
                    if (var19_11 == null) ** GOTO lbl-1000
                    try {
                        var19_11.put("QueryTime", System.currentTimeMillis());
                        ParseManager.updateMatchCacheManager(this.b, this.c, this.d, var19_11, this.e);
                        return;
                    }
                    catch (JSONException var1_4) {
                        var1_4.printStackTrace();
                        return;
                    }
                    catch (JSONException var4_17) {
                        var1_1 = var20_10;
lbl282: // 2 sources:
                        do {
                            var3_13 = var1_1;
                            var4_18.printStackTrace();
                            var3_13 = var1_1;
                            XyUtil.doXycallBackResult(this.f, null);
                            if (var19_11 == null) ** GOTO lbl-1000
                            try {
                                var19_11.put("QueryTime", System.currentTimeMillis());
                                if (var1_1 == null) ** GOTO lbl298
                            }
                            catch (JSONException var1_5) {
                                var1_5.printStackTrace();
                                return;
                            }
                            JsonUtil.JSONCombine(var19_11, (JSONObject)var1_1);
lbl298: // 2 sources:
                            ParseManager.updateMatchCacheManager(this.b, this.c, this.d, var19_11, this.e);
                            return;
                            catch (Throwable var1_6) lbl-1000: // 2 sources:
                            {
                                do {
                                    if (var19_11 == null) ** continue;
                                    try {
                                        var19_11.put("QueryTime", System.currentTimeMillis());
                                        if (var3_13 == null) ** GOTO lbl311
                                    }
                                    catch (JSONException var3_14) {
                                        var3_14.printStackTrace();
                                        throw var1_7;
                                    }
                                    JsonUtil.JSONCombine(var19_11, (JSONObject)var3_13);
lbl311: // 2 sources:
                                    ParseManager.updateMatchCacheManager(this.b, this.c, this.d, var19_11, this.e);
                                    do {
                                        throw var1_7;
                                        break;
                                    } while (true);
                                    break;
                                } while (true);
                            }
                            break;
                        } while (true);
                    }
                    catch (Throwable var1_8) {
                        var3_13 = var11_27;
                        ** continue;
                    }
                    catch (JSONException var4_19) {
                        var1_1 = var11_27;
                        ** continue;
                    }
                }
                var10_23 = var18_34;
                var9_26 = var17_33;
                var8_22 = var15_31;
                var7_24 = var14_30;
                var1_1 = var5_20;
                var5_20 = var6_21;
                var6_21 = var12_28;
                ** GOTO lbl56
            }
            ++var2_25;
            var4_16 = var13_29;
            var5_20 = var9_26;
            var6_21 = var11_27;
        } while (true);
    }
}

