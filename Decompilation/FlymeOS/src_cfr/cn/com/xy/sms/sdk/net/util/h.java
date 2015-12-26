/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.HashMap
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.net.util;

import cn.com.xy.sms.sdk.db.entity.IccidInfo;
import cn.com.xy.sms.sdk.db.entity.SceneRule;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.x;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class h {
    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static int a(String object, IccidInfo iccidInfo) {
        int n2;
        int n3 = n2 = -1;
        object = StringUtils.stringConvertXML((String)object, "");
        if (object == null) {
            return -1;
        }
        n3 = n2;
        object = object.getDocumentElement();
        n3 = n2;
        n3 = n2 = Integer.parseInt((String)h.a((Element)object, "rstCode"));
        if (n2 != 1) return n3;
        n3 = n2;
        iccidInfo.iccid = h.a((Element)object, "iccid");
        n3 = n2;
        iccidInfo.operator = h.a((Element)object, "operator");
        n3 = n2;
        iccidInfo.provinces = h.a((Element)object, "provinces");
        n3 = n2;
        iccidInfo.city = h.a((Element)object, "city");
        n3 = n2;
        try {
            iccidInfo.updateTime = Long.parseLong((String)h.a((Element)object, "updateTime"));
            return n2;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
        }
        return n3;
    }

    private static String a(Element object, String string2) {
        if ((object = object.getElementsByTagName(string2)) != null && object.getLength() > 0) {
            return x.a(object.item(0));
        }
        return "";
    }

    /*
     * Exception decompiling
     */
    public static Map<String, Object> a(String var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 8[UNCONDITIONALDOLOOP]
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
    public static List<SceneRule> b(String var0) {
        block30 : {
            block31 : {
                var3_2 = new ArrayList();
                try {
                    var0 = StringUtils.stringConvertXML((String)var0, "");
                    if (var0 == null) {
                        return null;
                    }
                    if (LogManager.debug) {
                        // empty if block
                    }
                    var0 = var0.getElementsByTagName("SceneRule");
                    var1_3 = 0;
                    do {
                        if (var1_3 >= var0.getLength()) {
                            return var3_2;
                        }
                        var4_5 = new SceneRule();
                        var5_6 = ((Element)var0.item(var1_3)).getChildNodes();
                        var2_4 = 0;
lbl16: // 2 sources:
                        if (var2_4 < var5_6.getLength()) break;
                        var3_2.add(var4_5);
                        ++var1_3;
                    } while (true);
                    var6_7 = var5_6.item(var2_4);
                    if (var6_7.getNodeType() != 1) break block30;
                    var7_8 = var6_7.getNodeName();
                    if ("sceneId".equalsIgnoreCase(var7_8)) {
                        var4_5.scene_id = x.a(var6_7);
                        break block30;
                    }
                    if ("sceneRuleVersion".equalsIgnoreCase(var7_8)) {
                        var4_5.sceneruleVersion = x.a(var6_7);
                        break block30;
                    }
                    if (!"province".equalsIgnoreCase(var7_8)) break block31;
                    var4_5.province = x.a(var6_7);
                    break block30;
                }
                catch (Throwable var0_1) {
                    var0_1.printStackTrace();
                    return var3_2;
                }
            }
            if ("id".equalsIgnoreCase(var7_8)) {
                var4_5.id = x.a(var6_7);
            } else if ("operator".equalsIgnoreCase(var7_8)) {
                var4_5.operator = x.a(var6_7);
            } else if ("expire_date".equalsIgnoreCase(var7_8)) {
                var4_5.expire_date = x.a(var6_7);
            } else if ("fun_call".equalsIgnoreCase(var7_8)) {
                var4_5.Func_call = Integer.parseInt((String)x.a(var6_7));
            } else if ("fun_acc_url".equalsIgnoreCase(var7_8)) {
                var4_5.Func_acc_url = Integer.parseInt((String)x.a(var6_7));
            } else if ("fun_reply_sms".equalsIgnoreCase(var7_8)) {
                var4_5.Func_reply_sms = Integer.parseInt((String)x.a(var6_7));
            } else if ("fun_config".equalsIgnoreCase(var7_8)) {
                var4_5.Func_config = x.a(var6_7);
            } else if ("res_urls".equalsIgnoreCase(var7_8)) {
                var4_5.res_urls = x.a(var6_7);
            } else if ("s_version".equalsIgnoreCase(var7_8)) {
                var4_5.s_version = x.a(var6_7);
            } else if ("scene_page_conf".equalsIgnoreCase(var7_8)) {
                var4_5.Scene_page_config = x.a(var6_7);
            }
        }
        ++var2_4;
        ** GOTO lbl16
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean c(String object) {
        block4 : {
            object = StringUtils.stringConvertXML((String)object, "");
            if (object != null) break block4;
            return false;
        }
        object = object.getDocumentElement().getElementsByTagName("rstCode");
        if (object == null) return false;
        try {
            boolean bl2;
            if (object.getLength() <= 0 || StringUtils.isNull((String)(object = x.a(object.item(0)).toString())) || !(bl2 = object.equals((Object)"0"))) return false;
            return true;
        }
        catch (Throwable var0_1) {
            // empty catch block
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean d(String object) {
        block4 : {
            object = StringUtils.stringConvertXML((String)object, "");
            if (object != null) break block4;
            return false;
        }
        object = object.getDocumentElement().getElementsByTagName("rstCode");
        if (object == null) return false;
        try {
            boolean bl2;
            if (object.getLength() <= 0 || StringUtils.isNull((String)(object = x.a(object.item(0)).toString())) || !(bl2 = object.equals((Object)"0"))) return false;
            return true;
        }
        catch (Throwable var0_1) {
            // empty catch block
        }
        return false;
    }

    /*
     * Exception decompiling
     */
    public static HashMap<String, Object> e(String var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [7[UNCONDITIONALDOLOOP]], but top level block is 4[TRYBLOCK]
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
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static JSONArray f(String object) {
        try {
            JSONArray jSONArray = new JSONArray();
            Object object2 = StringUtils.stringConvertXML((String)object, "");
            if (object2 == null) {
                return null;
            }
            object = object2.getDocumentElement();
            Object object3 = object.getElementsByTagName("code");
            if (object3 != null && object3.getLength() > 0) {
                if (StringUtils.isNull((String)(object3 = x.a(object3.item(0)).toString()))) return null;
                if (!"0".equals(object3)) {
                    return null;
                }
            }
            object = (object = object.getElementsByTagName("res_type")) != null && object.getLength() > 0 ? x.a(object.item(0)).toString() : "";
            object2 = object2.getElementsByTagName("res");
            int n2 = 0;
            do {
                if (n2 >= object2.getLength()) {
                    return jSONArray;
                }
                object3 = new JSONObject();
                Element element = (Element)object2.item(n2);
                String string2 = element.getAttribute("version");
                String string3 = element.getAttribute("del_history");
                object3.put("res_version", (Object)string2);
                object3.put("del_history", (Object)string3);
                object3.put("res_url", (Object)x.a(element));
                object3.put("res_type", object);
                jSONArray.put(object3);
                ++n2;
            } while (true);
        }
        catch (Throwable var0_1) {
            return null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static JSONObject g(String object) {
        block4 : {
            object = StringUtils.stringConvertXML((String)object, "");
            if (object != null) break block4;
            return null;
        }
        object = object.getDocumentElement().getElementsByTagName("data");
        if (object == null) return null;
        try {
            if (object.getLength() <= 0) return null;
            if (StringUtils.isNull((String)(object = x.a(object.item(0)).toString()))) return null;
            return new JSONObject((String)object);
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
        }
        return null;
    }
}

