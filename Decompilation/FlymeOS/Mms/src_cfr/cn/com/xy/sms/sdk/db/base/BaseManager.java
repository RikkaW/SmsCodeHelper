/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.database.sqlite.SQLiteDatabase
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.HashMap
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.db.base;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class BaseManager {
    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static String a(String string2, XyCursor xyCursor) {
        String string4;
        String string3 = string4 = null;
        if (xyCursor == null) return string3;
        string3 = string4;
        try {
            if (!xyCursor.moveToNext()) return string3;
            return xyCursor.getString(xyCursor.getColumnIndex(string2));
        }
        catch (Throwable throwable) {
            new StringBuilder("loadSingleFeildDataFromCursor Throwable=").append(throwable.getLocalizedMessage());
            return null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static /* varargs */ ContentValues getContentValues(ContentValues object, boolean bl2, JSONObject jSONObject, String ... arrstring) {
        if (jSONObject == null) return null;
        if (arrstring.length == 0) {
            return null;
        }
        Object object2 = object;
        if (object == null) {
            object2 = new ContentValues();
        }
        int n2 = arrstring.length;
        int n3 = 0;
        do {
            object = object2;
            if (n3 >= n2) return object;
            object = (String)JsonUtil.getValueFromJsonObject(jSONObject, arrstring[n3]);
            if (!bl2 || !StringUtils.isNull((String)object)) {
                object2.put(arrstring[n3], JsonUtil.getValueFromJsonObject(jSONObject, arrstring[n3]).toString());
            }
            ++n3;
        } while (true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static /* varargs */ ContentValues getContentValues(ContentValues contentValues, boolean bl2, String ... arrstring) {
        if (arrstring == null) return null;
        if (arrstring.length % 2 != 0) {
            return null;
        }
        ContentValues contentValues2 = contentValues;
        if (contentValues == null) {
            contentValues2 = new ContentValues();
        }
        int n2 = arrstring.length;
        int n3 = 0;
        do {
            contentValues = contentValues2;
            if (n3 >= n2) return contentValues;
            if (!bl2 || !StringUtils.isNull(arrstring[n3]) && !StringUtils.isNull(arrstring[n3 + 1])) {
                contentValues2.put(arrstring[n3], StringUtils.getNoNullString(arrstring[n3 + 1]));
            }
            n3 += 2;
        } while (true);
    }

    public static /* varargs */ ContentValues getContentValues(ContentValues contentValues, String ... arrstring) {
        return BaseManager.getContentValues(contentValues, false, arrstring);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static ContentValues getContentValues(ContentValues contentValues, String[] arrstring, String[] arrstring2, JSONObject jSONObject, boolean bl2) {
        if (jSONObject == null) return null;
        if (arrstring == null) return null;
        if (arrstring2 == null) {
            return null;
        }
        int n2 = arrstring.length;
        int n3 = arrstring2.length;
        if (n2 == 0) return null;
        if (n2 != n3) {
            return null;
        }
        ContentValues contentValues2 = contentValues;
        if (contentValues == null) {
            contentValues2 = new ContentValues();
        }
        n3 = 0;
        do {
            contentValues = contentValues2;
            if (n3 >= n2) return contentValues;
            if (bl2) {
                JsonUtil.putJsonToConV(contentValues2, jSONObject, arrstring[n3], arrstring2[n3]);
            } else {
                contentValues2.put(arrstring[n3], JsonUtil.getValueFromJsonObject(jSONObject, arrstring2[n3]).toString());
            }
            ++n3;
        } while (true);
    }

    /*
     * Exception decompiling
     */
    public static /* varargs */ boolean hasRecord(String var0, String var1_3, String var2_4, String ... var3_5) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 5[SIMPLE_IF_TAKEN]
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
    public static /* varargs */ long insertOrUpdate(String var0, String var1_3, String var2_8, String[] var3_9, String ... var4_10) {
        block13 : {
            var8_11 = null;
            var9_12 = new String[]{var1_3};
            var2_8 = DBManager.query((String)var0, var9_12, (String)var2_8, var3_9);
            if (var2_8 == null) break block13;
            if (var2_8.getCount() <= 0) break block13;
            var5_13 = (Integer)JsonUtil.getValueFromJsonObject(BaseManager.loadSingleDataFromCursor(var9_12, (XyCursor)var2_8), var1_3);
            var6_14 = var5_13;
lbl11: // 2 sources:
            do {
                XyCursor.closeCursor((XyCursor)var2_8, true);
                return var6_14;
                break;
            } while (true);
        }
        var6_14 = DBManager.insert((String)var0, BaseManager.getContentValues(null, var4_10));
        ** continue;
        catch (Throwable var1_4) {
            var0 = var8_11;
lbl21: // 3 sources:
            var1_5.printStackTrace();
            XyCursor.closeCursor((XyCursor)var0, true);
            return -1;
        }
        catch (Throwable var0_1) {
            var2_8 = null;
lbl26: // 3 sources:
            do {
                XyCursor.closeCursor((XyCursor)var2_8, true);
                throw var0;
                break;
            } while (true);
        }
        catch (Throwable var0_2) {
            ** GOTO lbl26
        }
        {
            catch (Throwable var1_6) {
                var2_8 = var0;
                var0 = var1_6;
                ** continue;
            }
        }
        catch (Throwable var1_7) {
            var0 = var2_8;
            ** GOTO lbl21
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static JSONArray loadArrDataFromCursor(String[] arrstring, XyCursor xyCursor) {
        if (xyCursor != null) {
            try {
                JSONArray jSONArray = new JSONArray();
                block3 : do {
                    if (!xyCursor.moveToNext()) {
                        return jSONArray;
                    }
                    JSONObject jSONObject = new JSONObject();
                    int n2 = 0;
                    do {
                        if (n2 >= arrstring.length) {
                            jSONArray.put((Object)jSONObject);
                            continue block3;
                        }
                        jSONObject.put(arrstring[n2], (Object)StringUtils.getNoNullString(xyCursor.getString(n2)));
                        ++n2;
                    } while (true);
                    break;
                } while (true);
            }
            catch (Throwable var0_1) {
                new StringBuilder("loadArrDataFromCursor Throwable=").append(var0_1.getLocalizedMessage());
            }
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static Map<String, JSONObject> loadMapDataFromCursor(String[] arrstring, int n2, XyCursor xyCursor) {
        if (xyCursor != null) {
            try {
                HashMap hashMap = new HashMap();
                block3 : do {
                    if (!xyCursor.moveToNext()) {
                        return hashMap;
                    }
                    JSONObject jSONObject = new JSONObject();
                    int n3 = 0;
                    do {
                        if (n3 >= arrstring.length) {
                            hashMap.put((String)JsonUtil.getValueFromJsonObject(jSONObject, arrstring[n2]), jSONObject);
                            continue block3;
                        }
                        jSONObject.put(arrstring[n3], (Object)StringUtils.getNoNullString(xyCursor.getString(n3)));
                        ++n3;
                    } while (true);
                    break;
                } while (true);
            }
            catch (Throwable var0_1) {
                new StringBuilder("loadMapDataFromCursor Throwable=").append(var0_1.getLocalizedMessage());
            }
        }
        return null;
    }

    public static JSONObject loadSingleDataFromCursor(String[] arrstring, XyCursor xyCursor) {
        block6 : {
            if (xyCursor != null) {
                int n2;
                JSONObject jSONObject;
                try {
                    if (!xyCursor.moveToNext()) break block6;
                    jSONObject = new JSONObject();
                    n2 = 0;
                }
                catch (Throwable var0_1) {
                    new StringBuilder("loadSingleDataFromCursor Throwable=").append(var0_1.getLocalizedMessage());
                }
                do {
                    block7 : {
                        if (n2 < arrstring.length) break block7;
                        return jSONObject;
                    }
                    jSONObject.put(arrstring[n2], (Object)StringUtils.getNoNullString(xyCursor.getString(n2)));
                    ++n2;
                    continue;
                    break;
                } while (true);
            }
            return null;
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static long saveOrUpdate(SQLiteDatabase sQLiteDatabase, String string2, ContentValues contentValues, String string3, String[] arrstring) {
        long l2 = 0;
        Object object = DBManager.dblock;
        synchronized (object) {
            int n2;
            try {
                n2 = sQLiteDatabase.update(string2, contentValues, string3, arrstring);
            }
            catch (Throwable var0_1) {
                new StringBuilder(String.valueOf((Object)string2)).append(" \u63d2\u5165\u6570\u636e Throwable=").append(var0_1.getLocalizedMessage());
                throw new Exception();
            }
            if ((long)n2 != 0) return l2;
            try {
                return sQLiteDatabase.insert(string2, null, contentValues);
            }
            catch (Throwable var0_2) {
                new StringBuilder(String.valueOf((Object)string2)).append(" \u63d2\u5165\u6570\u636e Throwable=").append(var0_2.getLocalizedMessage());
                throw new Exception();
            }
        }
    }

    public static /* varargs */ int update(String string2, String string3, String[] arrstring, String ... arrstring2) {
        try {
            int n2 = DBManager.update(string2, BaseManager.getContentValues(null, arrstring2), string3, arrstring);
            return n2;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return 0;
        }
    }
}

