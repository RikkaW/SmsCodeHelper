/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.os.Build
 *  android.telephony.TelephonyManager
 *  java.io.File
 *  java.io.FileOutputStream
 *  java.io.Reader
 *  java.io.StringReader
 *  java.lang.Float
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.Runtime
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.HashMap
 *  java.util.Map$Entry
 *  java.util.zip.ZipEntry
 *  java.util.zip.ZipFile
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.PopupUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.d;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.json.JSONArray;
import org.json.JSONObject;

public class XyUtil {
    public static final String DEFAULT_IMEI = "360_DEFAULT_IMEI";
    private static String a = null;
    private static String b = null;

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static String a(Map<String, Object> var0, String var1_2) {
        block5 : {
            try {
                var4_3 = new JSONArray();
                var1_2 = new JSONArray(var1_2);
                if (var1_2.length() <= 0) break block5;
                var3_4 = var1_2.length();
                var2_5 = 0;
                ** GOTO lbl20
            }
            catch (Throwable var0_1) {
                // empty catch block
            }
            return "";
        }
lbl12: // 2 sources:
        do {
            var0 = var4_3.toString();
            if (StringUtils.isNull((String)var0) != false) return "";
            return var0;
            break;
        } while (true);
lbl-1000: // 1 sources:
        {
            var5_6 = XyUtil.a(var0, var1_2.getJSONObject(var2_5));
            if (var5_6 != null) {
                var4_3.put((Object)var5_6);
            }
            ++var2_5;
lbl20: // 2 sources:
            ** while (var2_5 < var3_4)
        }
lbl21: // 1 sources:
        ** while (true)
    }

    private static String a(JSONObject jSONObject) {
        String string2 = "";
        if (jSONObject != null) {
            string2 = StringUtils.encode(jSONObject.toString());
        }
        return string2;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static JSONObject a(Map<String, Object> object, JSONObject jSONObject) {
        Integer n2;
        String string3;
        String string2;
        block37 : {
            Float f2;
            block35 : {
                boolean bl2;
                block36 : {
                    block34 : {
                        block33 : {
                            if (jSONObject == null) return null;
                            string2 = (String)object.get("title_num");
                            string3 = (String)jSONObject.get("action");
                            if ("reply_sms".equalsIgnoreCase(string3)) {
                                String string4;
                                String string5 = (String)jSONObject.get("send_code");
                                String string6 = string4 = (String)JsonUtil.getValueFromJsonObject(jSONObject, "phone");
                                if (StringUtils.isNull(string4)) {
                                    string6 = (String)JsonUtil.getValueWithMap(object, "phoneNum");
                                }
                                object = XyUtil.b("type", string3, "send_code", string5, "phone", string6, "titleNo", string2);
                                jSONObject.remove("send_code");
                                jSONObject.remove("phone");
                                jSONObject.put("action_data", object);
                                return jSONObject;
                            }
                            if ("send_sms".equalsIgnoreCase(string3)) {
                                object = XyUtil.b("type", string3, "send_code", (String)jSONObject.get("send_code"), "phone", (String)jSONObject.get("phone"), "titleNo", string2);
                                jSONObject.remove("send_code");
                                jSONObject.remove("phone");
                                jSONObject.put("action_data", object);
                                return jSONObject;
                            }
                            if ("access_url".equalsIgnoreCase(string3)) {
                                object = XyUtil.b("type", string3, "url", (String)jSONObject.get("url"), "titleNo", string2);
                                jSONObject.remove("url");
                                jSONObject.put("action_data", object);
                                return jSONObject;
                            }
                            if ("down_url".equalsIgnoreCase(string3) || "download".equalsIgnoreCase(string3)) {
                                object = XyUtil.b("type", string3, "url", (String)jSONObject.get("url"), "titleNo", string2);
                                jSONObject.remove("url");
                                jSONObject.put("action_data", object);
                                return jSONObject;
                            }
                            if ("weibo_url".equalsIgnoreCase(string3)) {
                                object = XyUtil.b("type", string3, "url", (String)jSONObject.get("url"), "titleNo", string2);
                                jSONObject.remove("url");
                                jSONObject.put("action_data", object);
                                return jSONObject;
                            }
                            if ("call_phone".equalsIgnoreCase(string3) || "call".equalsIgnoreCase(string3)) {
                                object = XyUtil.b("type", string3, "phone", (String)jSONObject.get("phone"), "titleNo", string2);
                                jSONObject.remove("phone");
                                jSONObject.put("action_data", object);
                                return jSONObject;
                            }
                            if ("map_site".equalsIgnoreCase(string3) || "open_map".equalsIgnoreCase(string3)) {
                                object = XyUtil.b("type", "WEB_MAP_SITE", "address", (String)jSONObject.get("address"), "titleNo", string2);
                                jSONObject.remove("address");
                                jSONObject.remove("action");
                                jSONObject.put("action", (Object)"WEB_MAP_SITE");
                                jSONObject.put("action_data", object);
                                return jSONObject;
                            }
                            if ("recharge".equalsIgnoreCase(string3)) {
                                jSONObject.put("action_data", (Object)XyUtil.b("type", string3, "titleNo", string2, "phone", (String)jSONObject.get("phone")));
                                return jSONObject;
                            }
                            if ("copy_code".equalsIgnoreCase(string3)) {
                                object = XyUtil.b("type", string3, "titleNo", string2, "code", (String)jSONObject.get("code"));
                                jSONObject.remove("code");
                                jSONObject.put("action_data", object);
                                return jSONObject;
                            }
                            bl2 = "WEB_TRAFFIC_ORDER".equalsIgnoreCase(string3);
                            if (!bl2) break block33;
                            try {
                                object = XyUtil.a("type", string3, "titleNo", string2);
                                object.put("config", jSONObject.get("config"));
                                object = XyUtil.a((JSONObject)object);
                                jSONObject.remove("config");
                                jSONObject.put("action_data", object);
                            }
                            finally {
                                return jSONObject;
                            }
                        }
                        bl2 = "WEB_INSTALMENT_PLAN".equalsIgnoreCase(string3);
                        if (!bl2) break block34;
                        try {
                            f2 = Float.valueOf((float)0.0f);
                        }
                        catch (Throwable var0_2) {
                            return jSONObject;
                        }
                        object = 0;
                        try {
                            object = n2 = (Integer)jSONObject.get("amount");
                            break block35;
                        }
                        catch (Throwable var4_20) {}
                    }
                    bl2 = "WEB_QUERY_EXPRESS_FLOW".equalsIgnoreCase(string3);
                    if (!bl2) break block36;
                    try {
                        object = (String)jSONObject.get("express_name");
                        String string7 = (String)jSONObject.get("express_no");
                        JSONObject jSONObject2 = XyUtil.a(new String[]{"type", string3, "titleNo", string2, "express_name", object, "express_no", string7});
                        jSONObject2.put("postValue", (Object)XyUtil.a(new String[]{"express_name", object, "express_no", string7}));
                        object = XyUtil.a(jSONObject2);
                        jSONObject.remove("express_name");
                        jSONObject.remove("express_no");
                        jSONObject.put("action_data", object);
                    }
                    finally {
                        return jSONObject;
                    }
                }
                bl2 = "WEB_QUERY_FLIGHT_TREND".equalsIgnoreCase(string3);
                if (!bl2) return jSONObject;
                try {
                    object = (String)jSONObject.get("flight_num");
                    String string8 = (String)jSONObject.get("flight_date");
                    String string9 = (String)jSONObject.get("flight_from");
                    String string10 = (String)jSONObject.get("flight_to");
                    string2 = XyUtil.a(new String[]{"type", string3, "titleNo", string2, "flight_num", object, "flight_date", string8, "flight_from", string9, "flight_to", string10});
                    string2.put("postValue", (Object)XyUtil.a(new String[]{"flight_num", object, "flight_date", string8, "flight_from", string9, "flight_to", string10}));
                    object = XyUtil.a((JSONObject)string2);
                    jSONObject.remove("flight_num");
                    jSONObject.remove("flight_date");
                    jSONObject.remove("flight_from");
                    jSONObject.remove("flight_to");
                    jSONObject.put("action_data", object);
                }
                finally {
                    return jSONObject;
                }
            }
            int n3 = object.intValue();
            n2 = f2;
            if (n3 != 0) break block37;
            try {
                n2 = (Float)jSONObject.get("amount");
            }
            catch (Throwable var4_19) {
                n2 = f2;
            }
        }
        object = object.intValue() != 0 ? String.valueOf((Object)object) : "";
        if (n2.floatValue() != 0.0f) {
            object = String.valueOf((Object)n2);
        }
        object = XyUtil.a(new String[]{"type", string3, "titleNo", string2, "amount", object});
        object.put("config", jSONObject.get("config"));
        object.put("budgetSmsTemplate", JsonUtil.getValueFromJsonObject(jSONObject, "budgetSmsTemplate"));
        object.put("phone", JsonUtil.getValueFromJsonObject(jSONObject, "phone"));
        object = XyUtil.a((JSONObject)object);
        jSONObject.remove("budgetSmsTemplate");
        jSONObject.remove("phone");
        jSONObject.remove("config");
        jSONObject.put("action_data", object);
        return jSONObject;
        catch (Throwable throwable) {
            // empty catch block
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static /* varargs */ JSONObject a(String ... arrstring) {
        if (arrstring.length % 2 != 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        int n2 = 0;
        do {
            if (n2 >= arrstring.length) {
                return jSONObject;
            }
            try {
                jSONObject.put(arrstring[n2], (Object)arrstring[n2 + 1]);
            }
            catch (Throwable var3_3) {}
            n2 += 2;
        } while (true);
    }

    private static boolean a(int n2) {
        if (n2 == 1) {
            return true;
        }
        return false;
    }

    private static /* varargs */ String b(String ... arrstring) {
        JSONObject jSONObject;
        int n2;
        try {
            jSONObject = new JSONObject();
            n2 = arrstring.length;
            if (n2 % 2 != 0) {
                return "";
            }
            n2 = 0;
        }
        catch (Throwable var0_1) {
            return "";
        }
        do {
            if (n2 >= arrstring.length) {
                return StringUtils.encode(jSONObject.toString());
            }
            jSONObject.put(arrstring[n2], (Object)arrstring[n2 + 1]);
            n2 += 2;
            continue;
            break;
        } while (true);
    }

    private static boolean b(int n2) {
        if (n2 != 0 && n2 != 4 && n2 != 5 && n2 != 2 && n2 != 3) {
            return false;
        }
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Map<String, String> changeObjMapToStrMap(HashMap<String, Object> iterator) {
        HashMap hashMap = new HashMap();
        if (iterator == null) return hashMap;
        try {
            if (iterator.isEmpty()) return hashMap;
            for (Map.Entry entry : iterator.entrySet()) {
                if (!(entry.getValue() instanceof String)) continue;
                hashMap.put((Object)((String)entry.getKey()), (Object)((String)entry.getValue()));
            }
            return hashMap;
        }
        catch (Throwable var0_1) {
            return null;
        }
    }

    public static int checkNetWork(Context context) {
        return XyUtil.checkNetWork(context, 1);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int checkNetWork(Context context, int n2) {
        if (context == null) {
            return -1;
        }
        NetworkInfo networkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isAvailable()) return -1;
        int n3 = networkInfo.getType();
        switch (n2) {
            default: {
                return -1;
            }
            case 0: {
                if (!XyUtil.b(n3)) return 1;
                return 0;
            }
            case 1: {
                if (DuoquUtils.getSdkDoAction().getWifiType(context) == 1) {
                    return 1;
                }
                if (!XyUtil.a(n3)) return 1;
                return 0;
            }
            case 2: 
        }
        if (!XyUtil.a(n3) && !XyUtil.b(n3)) return 1;
        return 0;
    }

    public static void chmod(String string2, String string3) {
        try {
            if (StringUtils.isNull(string3)) {
                return;
            }
            string2 = "chmod " + string2 + " " + string3;
            Runtime.getRuntime().exec(string2);
            return;
        }
        catch (IOException var0_1) {
            var0_1.printStackTrace();
            return;
        }
        catch (Throwable var0_2) {
            var0_2.printStackTrace();
            return;
        }
    }

    /*
     * Exception decompiling
     */
    public static byte[] decompressBytes(byte[] var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [4[TRYBLOCK]], but top level block is 15[SIMPLE_IF_TAKEN]
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
    public static void doXycallBack(XyCallBack xyCallBack, String string2) {
        if (xyCallBack == null) return;
        try {
            xyCallBack.execute(string2);
            return;
        }
        catch (Throwable var0_1) {
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static /* varargs */ void doXycallBackResult(XyCallBack xyCallBack, Object ... arrobject) {
        if (xyCallBack == null) return;
        try {
            xyCallBack.execute(arrobject);
            return;
        }
        catch (Throwable var0_1) {
            return;
        }
    }

    public static String getImei(Context object) {
        if (object != null && (object = (TelephonyManager)object.getSystemService("phone")) != null) {
            try {
                if (object.getDeviceId() != null) {
                    object = object.getDeviceId();
                    return object;
                }
            }
            catch (Throwable var0_1) {
                // empty catch block
            }
        }
        return "360_DEFAULT_IMEI";
    }

    public static String getImeiAndXinghao(Context context) {
        return String.valueOf((Object)XyUtil.getImei(context)) + ";" + XyUtil.getPhoneModel(context);
    }

    public static LineNumberReader getLineByCompressFile(String object) {
        try {
            object = new LineNumberReader((Reader)new StringReader(new String(XyUtil.decompressBytes(d.e((String)object)), "UTF-8")));
            return object;
        }
        catch (Throwable var0_1) {
            return null;
        }
    }

    public static String getPhoneModel(Context context) {
        return String.valueOf((Object)Build.MODEL) + "," + Build.BRAND;
    }

    public static String getPhoneNumber(Context object) {
        try {
            object = ((TelephonyManager)object.getSystemService("phone")).getLine1Number();
            return object;
        }
        catch (Throwable var0_1) {
            return "";
        }
    }

    /*
     * Exception decompiling
     */
    public static String getSceneServiceAction(Map<String, Object> var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [2[TRYBLOCK]], but top level block is 8[UNCONDITIONALDOLOOP]
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
    public static int getSimIndex(Map<String, Object> object) {
        if (object == null) {
            return -1;
        }
        if ((object = (String)object.get("simIndex")) == null) return -1;
        try {
            return Integer.valueOf((String)object);
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
        }
        return -1;
    }

    public static String getXyValue(int n2) {
        if (n2 == 1) {
            if (a == null) {
                a = PopupUtil.getValue(n2);
            }
            return a;
        }
        if (b == null) {
            b = PopupUtil.getValue(n2);
        }
        return b;
    }

    public static void handleMapAction(Map<String, Object> map) {
        String string2;
        if (!(map == null || map.isEmpty() || StringUtils.isNull(string2 = (String)map.get("ADACTION")) || StringUtils.isNull(string2 = XyUtil.a(map, string2)))) {
            map.put("ADACTION", string2);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean isProvinceUsable(String string2, String string3) {
        boolean bl2 = true;
        try {
            boolean bl3;
            if (StringUtils.isNull(string2)) {
                return true;
            }
            if (!StringUtils.isNull(string2)) {
                bl3 = bl2;
                if (string2.equals((Object)"*")) return bl3;
            }
            if (StringUtils.isNull(string3)) {
                return false;
            }
            if (StringUtils.isNull(string2)) return false;
            int n2 = string2.replaceAll("\uff0c", ",").replaceAll("\uff1b", ";").replaceAll("\u5e02", "").indexOf(string3);
            bl3 = bl2;
            if (n2 != -1) return bl3;
            return false;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return false;
        }
    }

    public static void unZip(InputStream inputStream, String string2, String string3) {
        XyUtil.unZip(inputStream, string2, string3, false, "", false);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void unZip(InputStream var0, String var1_4, String var2_5, boolean var3_6, String var4_7, boolean var5_13) {
        var10_14 = null;
        var9_15 = null;
        var7_16 = new File(String.valueOf((Object)var2_5) + (String)var1_4);
        if (!var7_16.exists()) {
            var7_16.createNewFile();
        }
        var8_17 = new FileOutputStream(var7_16);
        try {
            ** GOTO lbl27
            catch (Throwable var0_1) {
                var1_4 = null;
                var2_5 = var10_14;
                ** GOTO lbl52
            }
            catch (Throwable var0_2) {
                var1_4 = var7_16;
                var2_5 = var10_14;
                ** GOTO lbl52
            }
            catch (Throwable var4_11) {
                var0 = null;
                var2_5 = var9_15;
                ** GOTO lbl41
            }
            catch (Throwable var4_12) {
                var0 = var7_16;
                var2_5 = var9_15;
                ** GOTO lbl41
            }
lbl27: // 1 sources:
            var9_15 = new byte[1024];
            do {
                if ((var6_18 = var0.read((byte[])var9_15)) == -1) {
                    var8_17.close();
                    XyUtil.upZipFile(var7_16, var2_5, var3_6, var4_7, var5_13);
                    var7_16.delete();
                    new StringBuilder("unZip: file: ").append((String)var1_4).append("\u5904\u7406\u6210\u529f\uff01");
                    break;
                }
                var8_17.write((byte[])var9_15, 0, var6_18);
                var8_17.flush();
            } while (true);
        }
        catch (Throwable var4_8) {
            var2_5 = var8_17;
            var0 = var7_16;
lbl41: // 3 sources:
            try {
                var4_9.printStackTrace();
                new StringBuilder("unZip: file: ").append((String)var1_4).append("\u5904\u7406\u5931\u8d25\uff01").append(var4_9.getMessage());
                throw new Exception("file: " + (String)var1_4 + "\u5904\u7406\u5931\u8d25\uff01" + var4_9.getMessage());
            }
            catch (Throwable var4_10) {
                var1_4 = var0;
                var0 = var4_10;
                ** GOTO lbl52
                catch (Throwable var0_3) {
                    var2_5 = var8_17;
                    var1_4 = var7_16;
                }
lbl52: // 4 sources:
                if (var1_4 != null && var1_4.exists()) {
                    var1_4.delete();
                }
                d.a((Closeable)var2_5);
                throw var0;
            }
        }
        if (var7_16.exists()) {
            var7_16.delete();
        }
        d.a((Closeable)var8_17);
    }

    public static void upZipFile(File file, String string2) {
        XyUtil.upZipFile(file, string2, false, "", false);
    }

    /*
     * Exception decompiling
     */
    public static void upZipFile(File var0, String var1_1, boolean var2_7, String var3_8, boolean var4_16) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [5[TRYBLOCK]], but top level block is 9[CATCHBLOCK]
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
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void upZipFile(String var0, String var1_4) {
        var5_11 = null;
        var4_12 = new File((String)var1_4);
        if (!var4_12.exists()) {
            var4_12.mkdirs();
        }
        var4_12 = new ZipFile(new File((String)var0));
        var9_13 = var4_12.entries();
        var0 = null;
        var5_11 = null;
        catch (Throwable var1_6) {
            block23 : {
                var5_11 = null;
                var4_12 = null;
                var0 = null;
                ** GOTO lbl101
                catch (Throwable var1_8) {
                    var5_11 = null;
                    var0 = null;
                    break block23;
                }
                catch (Throwable var0_2) {
                    var0 = null;
                    var1_4 = null;
                    var4_12 = var5_11;
                    ** GOTO lbl87
                }
                catch (Throwable var0_3) {
                    var0 = null;
                    var1_4 = var4_12;
                    var4_12 = var5_11;
                    ** GOTO lbl87
                }
                do {
                    block24 : {
                        var8_18 = var0;
                        var6_16 = var0;
                        var3_15 = var9_13.hasMoreElements();
                        if (var3_15) break block24;
                        d.a((ZipFile)var4_12);
                        d.a((Closeable)var0);
                        d.a((Closeable)var5_11);
                        return;
                    }
                    var8_18 = var0;
                    var6_16 = var0;
                    var7_17 = (ZipEntry)var9_13.nextElement();
                    var8_18 = var0;
                    var6_16 = var0;
                    if (StringUtils.isNull(var7_17.getName())) continue;
                    var8_18 = var0;
                    var6_16 = var0;
                    var8_18 = var0 = var4_12.getInputStream(var7_17);
                    var6_16 = var0;
                    var7_17 = new File(String.valueOf((Object)var1_4) + File.separator + new String(var7_17.getName().getBytes("8859_1"), "GB2312").replace((CharSequence)"\\.\\.", (CharSequence)""));
                    var8_18 = var0;
                    var6_16 = var0;
                    if (!var7_17.exists()) {
                        var8_18 = var0;
                        var6_16 = var0;
                        var10_19 = var7_17.getParentFile();
                        var8_18 = var0;
                        var6_16 = var0;
                        if (!var10_19.exists()) {
                            var8_18 = var0;
                            var6_16 = var0;
                            var10_19.mkdirs();
                        }
                        var8_18 = var0;
                        var6_16 = var0;
                        var7_17.createNewFile();
                    } else {
                        var8_18 = var0;
                        var6_16 = var0;
                        var7_17.delete();
                    }
                    var8_18 = var0;
                    var6_16 = var0;
                    var7_17 = new FileOutputStream((File)var7_17);
                    try {
                        var5_11 = new byte[1024];
lbl79: // 2 sources:
                        if ((var2_14 = var0.read((byte[])var5_11)) <= 0) {
                            d.a((Closeable)var0);
                            d.a((Closeable)var7_17);
                        }
                        ** GOTO lbl91
                        catch (Throwable var0_1) {
                            var0 = var5_11;
                            var1_4 = var4_12;
                            var4_12 = var8_18;
lbl87: // 3 sources:
                            d.a((ZipFile)var1_4);
                            d.a((Closeable)var4_12);
                            d.a((Closeable)var0);
                            return;
                        }
lbl91: // 1 sources:
                        var7_17.write((byte[])var5_11, 0, var2_14);
                        ** GOTO lbl79
                    }
                    catch (Throwable var1_5) {
                        var1_4 = var4_12;
                        var4_12 = var0;
                    }
                    finally {
                        var5_11 = var7_17;
                        continue;
                    }
                    break;
                } while (true);
                catch (Throwable var1_10) {
                    var0 = var6_16;
                }
            }
            d.a((ZipFile)var4_12);
            d.a((Closeable)var0);
            d.a((Closeable)var5_11);
            throw var1_7;
        }
    }
}

