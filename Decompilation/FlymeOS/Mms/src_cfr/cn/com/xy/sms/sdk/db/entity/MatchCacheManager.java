/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.System
 *  java.lang.Throwable
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.db.entity;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class MatchCacheManager {
    public static final String ADD_bubble_lasttime = "ALTER TABLE tb_match_cache ADD COLUMN bubble_lasttime INTEGER DEFAULT '0'";
    public static final String ADD_card_lasttime = "ALTER TABLE tb_match_cache ADD COLUMN card_lasttime INTEGER DEFAULT '0'";
    public static final String ADD_session_lasttime = "ALTER TABLE tb_match_cache ADD COLUMN session_lasttime INTEGER DEFAULT '0'";
    public static final String DROP_TABLE = " DROP TABLE IF EXISTS tb_match_cache";
    public static final String TABLE_NAME = "tb_match_cache";

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static long a() {
        long l2;
        String string2 = SysParamEntityManager.getStringParam(Constant.getContext(), "REPARSE_BUBBLE_CYCLE");
        long l3 = l2 = -1;
        if (!"-1".equals((Object)string2)) {
            try {
                l3 = Long.parseLong((String)string2);
            }
            catch (Exception var4_1) {
                l3 = l2;
            }
        }
        if (l3 > 0) {
            return l3;
        }
        return DexUtil.getUpdateCycleByType(14, 21600000);
    }

    /*
     * Exception decompiling
     */
    private static Map<String, JSONObject> a(String[] var0, int var1_2, XyCursor var2_3) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 4[UNCONDITIONALDOLOOP]
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
    private static void a(JSONObject jSONObject) {
        long l2;
        long l3;
        String string2 = (String)JsonUtil.getValueFromJsonObject(jSONObject, "bubble_result");
        try {
            l3 = Long.valueOf((String)JsonUtil.getValueFromJsonObject(jSONObject, "bubble_lasttime").toString());
            if (!StringUtils.isNull(string2)) {
                jSONObject.put("bubble_result", (Object)new JSONObject(string2));
            } else {
                jSONObject.remove("bubble_result");
                l2 = MatchCacheManager.a();
                if (l3 == 0 || System.currentTimeMillis() - l3 >= l2) {
                    jSONObject.put("need_parse_bubble", (Object)"");
                }
            }
        }
        catch (Throwable var5_3) {
            var5_3.printStackTrace();
        }
        string2 = (String)JsonUtil.getValueFromJsonObject(jSONObject, "session_reuslt");
        try {
            l3 = Long.valueOf((String)JsonUtil.getValueFromJsonObject(jSONObject, "session_lasttime").toString());
            new StringBuilder("simple data: ").append(string2).append(" lastTime: ").append(l3);
            if (!StringUtils.isNull(string2)) {
                JSONArray jSONArray = new JSONArray(string2);
                jSONObject.put("session_reuslt", (Object)jSONArray);
                LogManager.e("duoqu_xiaoyuan", "simple data: " + string2 + " lastTime: " + l3 + " dataArr: " + (Object)jSONArray, null);
                return;
            } else {
                LogManager.e("duoqu_xiaoyuan", "simple data: " + string2 + " lastTime: " + l3 + " 444444444444444 session_result: is null.", null);
                jSONObject.remove("session_reuslt");
                l2 = MatchCacheManager.a();
                if (l3 != 0 && System.currentTimeMillis() - l3 < l2) return;
                {
                    jSONObject.put("need_parse_simple", (Object)"");
                    return;
                }
            }
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean deleteBubbleData(String string2, String string3) {
        int n2;
        if (StringUtils.isNull(string2)) {
            return false;
        }
        try {
            n2 = StringUtils.isNull(string3) ? DBManager.delete("tb_match_cache", " msg_id = ? ", new String[]{string2}) : DBManager.delete("tb_match_cache", " msg_id = ? and msg_num_md5 = ? ", new String[]{string2, string3});
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            n2 = -1;
        }
        if (n2 <= 0) return false;
        return true;
    }

    /*
     * Exception decompiling
     */
    public static void deleteDataByMsgIds(Set<Integer> var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 9[UNCONDITIONALDOLOOP]
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

    public static int deleteDataByPhoneNum(String string2) {
        if (!StringUtils.isNull(string2)) {
            try {
                int n2 = DBManager.delete("tb_match_cache", " phonenum = ? ", new String[]{string2});
                return n2;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
            }
        }
        return -1;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void deleteMatchCache(String string2, long l2) {
        try {
            StringBuffer stringBuffer = new StringBuffer(" bubble_lasttime < ?");
            if (string2.length() == 8) {
                stringBuffer.append(" and scene_id = ? ");
            } else {
                stringBuffer.append(" and scene_id like '?%' ");
            }
            int n2 = DBManager.delete("tb_match_cache", stringBuffer.toString(), new String[]{String.valueOf((long)l2), string2});
            new StringBuilder("sceneId =").append(string2).append(" endParseTime=").append(l2).append("result=").append(n2);
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void deleteMatchCache(String string2, String string3, long l2) {
        try {
            int n2;
            string3 = new StringBuffer(String.valueOf((Object)string3) + "  < ?");
            if (StringUtils.isNull(string2)) {
                string3.append(" and (scene_id is null or length(scene_id) = 0) ");
                n2 = DBManager.delete("tb_match_cache", string3.toString(), new String[]{String.valueOf((long)l2)});
            } else if (string2.length() == 8) {
                string3.append(" and scene_id = ? ");
                n2 = DBManager.delete("tb_match_cache", string3.toString(), new String[]{String.valueOf((long)l2), string2});
            } else {
                string3.append(" and scene_id like '" + string2 + "%' ");
                n2 = DBManager.delete("tb_match_cache", string3.toString(), new String[]{String.valueOf((long)l2)});
            }
            new StringBuilder("sceneId =").append(string2).append(" endParseTime=").append(l2).append("result=").append(n2);
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static String getCreateTableSql() {
        return "create table  if not exists tb_match_cache (  id INTEGER PRIMARY KEY, msg_num_md5 TEXT, phonenum TEXT, msg_id TEXT, scene_id TEXT, popup_window_result TEXT, bubble_result TEXT, session_reuslt TEXT, card_result TEXT, save_time INTEGER DEFAULT '0', bubble_lasttime integer default 0, session_lasttime integer default 0, card_lasttime integer default 0, EXTEND TEXT)";
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static JSONObject getDataByParam(String var0) {
        block8 : {
            var1_2 = null;
            var2_5 = new String[]{"msg_id", "msg_num_md5", "bubble_result", "session_reuslt", "card_result", "bubble_lasttime", "session_lasttime", "card_lasttime", "save_time"};
            var0 = DBManager.query("tb_match_cache", var2_5, "msg_id=?", new String[]{var0}, null, null, null, null);
            var1_2 = BaseManager.loadSingleDataFromCursor(var2_5, (XyCursor)var0);
            if (var1_2 == null) break block8;
            MatchCacheManager.a((JSONObject)var1_2);
        }
        XyCursor.closeCursor((XyCursor)var0, true);
        return var1_2;
        catch (Throwable var1_3) {
            var0 = null;
            ** GOTO lbl22
            catch (Throwable var0_1) {}
            ** GOTO lbl-1000
            catch (Throwable var2_6) {
                var1_2 = var0;
                var0 = var2_6;
                ** GOTO lbl-1000
            }
            catch (Throwable var1_4) {}
lbl22: // 2 sources:
            try {
                var1_2.printStackTrace();
            }
            catch (Throwable var2_7) {
                var1_2 = var0;
                var0 = var2_7;
            }
            XyCursor.closeCursor((XyCursor)var0, true);
            return null;
        }
lbl-1000: // 3 sources:
        {
            XyCursor.closeCursor((XyCursor)var1_2, true);
            throw var0;
        }
    }

    public static String getMD5(String string2, String string3) {
        try {
            string2 = StringUtils.getPhoneNumberNo86(string2);
            if (!StringUtils.isNull(string2) && !StringUtils.isNull(string3)) {
                string2 = StringUtils.getMD5(String.valueOf((Object)string2) + string3);
                return string2;
            }
        }
        catch (Throwable var0_1) {
            // empty catch block
        }
        return "";
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    public static long insertOrUpdate(ContentValues var0, int var1_4) {
        block20 : {
            block21 : {
                var8_5 = null;
                var9_6 = null;
                var6_10 = -1;
                var10_11 = (String)var0.get("msg_id");
                if (StringUtils.isNull((String)var10_11)) break block20;
                var11_12 = new String[]{var10_11};
                var12_13 = new String[]{"msg_num_md5", "id"};
                var8_5 = var10_11 = DBManager.query("tb_match_cache", (String[])var12_13, " msg_id = ? ", var11_12);
                var4_14 = var6_10;
                var10_11 = BaseManager.loadSingleDataFromCursor((String[])var12_13, (XyCursor)var8_5);
                var4_14 = var6_10;
                var12_13 = (String)var0.get("msg_num_md5");
                var2_15 = var6_10;
                if (var10_11 != null) {
                    var4_14 = var6_10;
                    var9_6 = (String)JsonUtil.getValueFromJsonObject((JSONObject)var10_11, "msg_num_md5");
                    var4_14 = var6_10;
                    var2_15 = Long.valueOf((String)((String)JsonUtil.getValueFromJsonObject((JSONObject)var10_11, "id")));
                }
                if (var9_6 != null) {
                    var4_14 = var2_15;
                    if (var9_6.equals(var12_13)) break block21;
                }
                if (var9_6 != null) {
                    var4_14 = var2_15;
                    DBManager.delete("tb_match_cache", " msg_id = ? ", var11_12);
                }
                var4_14 = var2_15;
                var2_15 = DBManager.insert("tb_match_cache", (ContentValues)var0);
                new StringBuilder("insert matchcache msgid: ").append(var11_12[0]).append(" dataType: ").append(var1_4);
                var0 = var8_5;
lbl35: // 3 sources:
                do {
                    XyCursor.closeCursor((XyCursor)var0, true);
                    return var2_15;
                    break;
                } while (true);
            }
            var4_14 = var2_15;
            DBManager.update("tb_match_cache", (ContentValues)var0, " msg_id = ? ", var11_12);
            var4_14 = var2_15;
            new StringBuilder("update matchcache msgid: ").append(var11_12[0]).append(" dataType: ").append(var1_4);
            var0 = var8_5;
            ** GOTO lbl35
            catch (Throwable var9_7) {
                var2_15 = -1;
                var0 = var8_5;
                var8_5 = var9_7;
lbl51: // 4 sources:
                var8_5.printStackTrace();
                XyCursor.closeCursor((XyCursor)var0, true);
                return var2_15;
            }
            catch (Throwable var0_1) {
                var8_5 = null;
lbl56: // 3 sources:
                do {
                    XyCursor.closeCursor((XyCursor)var8_5, true);
                    throw var0;
                    break;
                } while (true);
            }
            catch (Throwable var0_2) {
                ** GOTO lbl56
            }
            {
                catch (Throwable var9_8) {
                    var8_5 = var0;
                    var0 = var9_8;
                    ** continue;
                }
            }
            {
                catch (Throwable var9_9) {
                    var0 = var8_5;
                    var2_15 = var4_14;
                    var8_5 = var9_9;
                    ** GOTO lbl51
                }
                catch (Throwable var0_3) {
                    var9_6 = var8_5;
                    var8_5 = var0_3;
                    var0 = var9_6;
                    ** GOTO lbl51
                }
            }
        }
        var0 = null;
        var2_15 = -1;
        ** while (true)
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    public static Map<String, JSONObject> loadDataByParam(String var0, String[] var1_2) {
        var2_4 = new String[]{"msg_id", "msg_num_md5", "bubble_result", "session_reuslt", "card_result", "bubble_lasttime", "session_lasttime", "card_lasttime", "save_time"};
        var0 = var1_2 = DBManager.query("tb_match_cache", (String[])var2_4, (String)var0, (String[])var1_2);
        try {
            var2_4 = MatchCacheManager.a((String[])var2_4, 0, (XyCursor)var1_2);
        }
        catch (Throwable var2_6) {
            ** GOTO lbl10
        }
        XyCursor.closeCursor((XyCursor)var1_2, true);
        return var2_4;
        catch (Throwable var2_5) {
            var1_2 = null;
lbl10: // 2 sources:
            var0 = var1_2;
            var2_4.printStackTrace();
            XyCursor.closeCursor((XyCursor)var1_2, true);
            return null;
        }
        catch (Throwable var0_1) {
            var2_4 = null;
            var1_2 = var0_1;
lbl18: // 2 sources:
            do {
                XyCursor.closeCursor((XyCursor)var2_4, true);
                throw var1_2;
                break;
            } while (true);
        }
        {
            catch (Throwable var1_3) {
                var2_4 = var0;
                ** continue;
            }
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    public static Map<String, JSONObject> loadDataByParam(String var0, String[] var1_2, String var2_5, String var3_8) {
        var4_9 = null;
        var5_10 = new String[]{"msg_id", "msg_num_md5", "bubble_result", "session_reuslt", "card_result", "bubble_lasttime", "session_lasttime", "card_lasttime", "save_time"};
        var0 = DBManager.query("tb_match_cache", var5_10, (String)var0, var1_2, null, null, var2_5, var3_8);
        try {
            var1_2 = MatchCacheManager.a(var5_10, 0, (XyCursor)var0);
        }
        catch (Throwable var2_6) {
            var1_2 = var0;
            var0 = var2_6;
            ** GOTO lbl17
        }
        XyCursor.closeCursor((XyCursor)var0, true);
        return var1_2;
        catch (Throwable var1_3) {
            var0 = null;
lbl12: // 3 sources:
            var1_2.printStackTrace();
            XyCursor.closeCursor((XyCursor)var0, true);
            return null;
        }
        catch (Throwable var0_1) {
            var1_2 = var4_9;
lbl17: // 3 sources:
            do {
                XyCursor.closeCursor(var1_2, true);
                throw var0;
                break;
            } while (true);
        }
        {
            catch (Throwable var2_7) {
                var1_2 = var0;
                var0 = var2_7;
                ** continue;
            }
        }
        catch (Throwable var1_4) {
            ** GOTO lbl12
        }
    }

    /*
     * Exception decompiling
     */
    public static int queryDataCount(String var0, String var1_3) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 4[SIMPLE_IF_TAKEN]
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
    public static void removeUselessKey(JSONObject jSONObject) {
        if (jSONObject != null) {
            for (int i2 = 0; i2 < 10; ++i2) {
                try {
                    String string2 = new String[]{"version", "mactchs_id", "layoutName", "ALLOW_VERCODE_MSG", "smsCenterNum", "channel", "RECOGNIZE_LEVEL", "is_return", "viewPartParam", "simIndex"}[i2];
                    if (!jSONObject.has(string2)) continue;
                    jSONObject.remove(string2);
                    continue;
                }
                catch (Throwable var0_1) {
                    var0_1.printStackTrace();
                    return;
                }
            }
        }
    }
}

