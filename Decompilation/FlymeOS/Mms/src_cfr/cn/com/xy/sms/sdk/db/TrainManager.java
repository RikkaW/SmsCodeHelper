/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.db;

import android.content.ContentValues;
import android.content.Context;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.db.f;
import cn.com.xy.sms.sdk.db.g;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.util.i;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TrainManager {
    public static final String ADD_DATA_TIME = "ALTER TABLE tb_train ADD COLUMN data_time LONG default 0 ";
    public static final String ADD_STATION_LIST = "ALTER TABLE tb_train ADD COLUMN station_list TEXT ";
    public static final String CREATE_TABLE = "create table  if not exists tb_train (id INTEGER PRIMARY KEY,train_num TEXT not null unique,start_city TEXT,end_city TEXT,train_type INTEGER default 0,start_time TEXT,end_time TEXT,mileage TEXT,station_list TEXT,duration TEXT,data_time LONG default 0)";
    public static final String DATA_TIME = "data_time";
    public static final String DROP_TABLE = " DROP TABLE IF EXISTS tb_train";
    public static final String DURATION = "duration";
    public static final String END_CITY = "end_city";
    public static final String END_TIME = "end_time";
    public static final String ID = "id";
    public static final String MILEAGE = "mileage";
    public static final String START_CITY = "start_city";
    public static final String START_TIME = "start_time";
    public static final String STATION_LIST = "station_list";
    public static final String TABLE_NAME = "tb_train";
    public static final String TRAIN_NUM = "train_num";
    public static final String TRAIN_TYPE = "train_type";

    public static void checkDataOnline(XyCallBack xyCallBack, String string2, String string3, String object, String string4) {
        try {
            object = new g(xyCallBack, string2, string3, (String)object, string4);
            string3 = i.e(string3.replace((CharSequence)"\u6b21", (CharSequence)""));
            if (NetUtil.isEnhance()) {
                NetUtil.executePubNumServiceHttpRequest(string3, "990005", (XyCallBack)object, "", true, false, "checi", false);
                return;
            }
            XyUtil.doXycallBackResult(xyCallBack, string2);
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static boolean checkUpdateData() {
        new f().start();
        return true;
    }

    public static ContentValues getContentValues(JSONObject jSONObject) {
        if (jSONObject != null) {
            JSONArray jSONArray;
            JSONArray jSONArray2;
            int n2;
            block7 : {
                if (jSONObject.has("stations")) break block7;
                return null;
            }
            try {
                jSONArray2 = jSONObject.getJSONArray("stations");
                jSONArray = new JSONArray();
                n2 = 0;
            }
            catch (JSONException var0_1) {
                var0_1.printStackTrace();
            }
            do {
                block8 : {
                    if (n2 < jSONArray2.length()) break block8;
                    jSONObject.put("station_list", (Object)jSONArray);
                    jSONObject.put("data_time", System.currentTimeMillis());
                    return BaseManager.getContentValues(null, new String[]{"train_num", "start_city", "end_city", "train_type", "start_time", "end_time", "mileage", "station_list", "duration", "data_time"}, new String[]{"cc", "sfz", "zdz", "lclx", "fs", "ds", "lc", "station_list", "ls", "data_time"}, jSONObject, false);
                }
                JSONObject jSONObject2 = new JSONObject();
                JSONObject jSONObject3 = jSONArray2.getJSONObject(n2);
                jSONObject2.put("name", jSONObject3.get("cz"));
                jSONObject2.put("spt", jSONObject3.get("tcsj"));
                jSONObject2.put("stt", jSONObject3.get("kcsj"));
                jSONObject2.put("day", jSONObject3.get("rq"));
                jSONArray.put((Object)jSONObject2);
                ++n2;
                continue;
                break;
            } while (true);
        }
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String getEndCity(String string2) {
        String[] arrstring = null;
        if (!StringUtils.isNull(string2)) {
            arrstring = string2.split("/");
        }
        if (arrstring == null) return "";
        try {
            if (arrstring.length <= 0) return "";
            return TrainManager.queryEndCity(arrstring[0]);
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
        }
        return "";
    }

    /*
     * Exception decompiling
     */
    public static void importTrainData(Context var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [3[TRYBLOCK]], but top level block is 17[SIMPLE_IF_TAKEN]
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
     * Exception decompiling
     */
    public static String queryEndCity(String var0) {
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
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    public static JSONObject queryTrainInfo(String var0) {
        var0 = var0.replaceFirst("\u6b21", "");
        var2_2 = new String[]{"id", "train_num", "start_city", "end_city", "train_type", "start_time", "end_time", "mileage", "station_list", "duration", "data_time"};
        var0 = var1_5 = DBManager.query("tb_train", (String[])var2_2, "train_num = ?", new String[]{var0});
        try {
            var2_2 = BaseManager.loadSingleDataFromCursor((String[])var2_2, var1_5);
        }
        catch (Throwable var2_4) {
            ** GOTO lbl12
        }
        XyCursor.closeCursor(var1_5, true);
        return var2_2;
        catch (Throwable var2_3) {
            var1_5 = null;
lbl12: // 2 sources:
            var0 = var1_5;
            var2_2.printStackTrace();
            XyCursor.closeCursor(var1_5, true);
            return null;
        }
        catch (Throwable var0_1) {
            var2_2 = null;
            var1_6 = var0_1;
lbl20: // 2 sources:
            do {
                XyCursor.closeCursor((XyCursor)var2_2, true);
                throw var1_6;
                break;
            } while (true);
        }
        {
            catch (Throwable var1_7) {
                var2_2 = var0;
                ** continue;
            }
        }
    }
}

