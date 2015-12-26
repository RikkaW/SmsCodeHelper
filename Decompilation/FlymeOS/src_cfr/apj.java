/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.Float
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.HashMap
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
import android.util.Log;
import com.ted.android.contacts.netparser.model.DealItem;
import com.ted.android.contacts.netparser.model.MenuItem;
import com.ted.android.contacts.netparser.model.NumItem;
import com.ted.android.contacts.netparser.model.SpItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class apj {
    public static int a;
    public static int b;
    private static final String c;

    static {
        c = apj.class.getSimpleName();
        a = -1;
        b = -1;
    }

    public static NumItem a(String object) {
        block3 : {
            JSONObject jSONObject;
            Object var1_2 = null;
            try {
                jSONObject = new JSONObject((String)object).getJSONObject("data");
                object = var1_2;
                if (jSONObject == null) break block3;
            }
            catch (JSONException var0_1) {
                Log.e((String)c, (String)"parser network number json error:", (Throwable)var0_1);
                return null;
            }
            object = apj.a(jSONObject);
        }
        return object;
    }

    /*
     * Exception decompiling
     */
    public static NumItem a(JSONObject var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [8[TRYBLOCK]], but top level block is 23[CATCHBLOCK]
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
     */
    private static void a(JSONObject jSONObject, NumItem numItem) {
        if (!jSONObject.has("deals")) return;
        {
            jSONObject = jSONObject.getJSONArray("deals");
            ArrayList arrayList = new ArrayList(jSONObject.length());
            int n2 = 0;
            do {
                Object object;
                if (n2 >= jSONObject.length()) {
                    numItem.d(arrayList);
                    return;
                }
                DealItem dealItem = new DealItem();
                Object object2 = jSONObject.getJSONObject(n2);
                if (object2.has("deal_name")) {
                    dealItem.setDealName(object2.getString("deal_name"));
                }
                if (object2.has("deal_image")) {
                    dealItem.setDealImage(object2.getString("deal_image"));
                }
                if (object2.has("deal_desc")) {
                    dealItem.setDescription(object2.getString("deal_desc"));
                }
                if (object2.has("original_price")) {
                    dealItem.setOrigPrice(Float.parseFloat((String)object2.getString("original_price")));
                }
                if (object2.has("current_price")) {
                    dealItem.setCurrPrice(Float.parseFloat((String)object2.getString("current_price")));
                }
                if (object2.has("reservation")) {
                    boolean bl2 = object2.getInt("reservation") != 0;
                    dealItem.enableReserved(bl2);
                }
                if (object2.has("deal_url")) {
                    dealItem.setUrl(object2.getString("deal_url"));
                }
                if (object2.has("start_time") && (object = object2.get("start_time")) instanceof Integer) {
                    dealItem.setStartTime((Integer)object);
                }
                if (object2.has("end_time") && (object2 = object2.get("end_time")) instanceof Integer) {
                    dealItem.setStartTime((Integer)object2);
                }
                arrayList.add((Object)dealItem);
                ++n2;
            } while (true);
        }
    }

    /*
     * Exception decompiling
     */
    public static MenuItem b(JSONObject var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [4[TRYBLOCK]], but top level block is 5[TRYBLOCK]
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
    public static SpItem c(JSONObject jSONObject) {
        HashMap hashMap;
        Object object;
        SpItem spItem;
        try {
            spItem = new SpItem();
            if (jSONObject.has("sp")) {
                object = jSONObject.getString("sp");
                if (object.equalsIgnoreCase("cm")) {
                    spItem.a(SpItem.SpType.a);
                } else if (object.equalsIgnoreCase("cu")) {
                    spItem.a(SpItem.SpType.b);
                } else {
                    spItem.a(SpItem.SpType.c);
                }
            }
            if (jSONObject.has("a")) {
                spItem.a(jSONObject.getString("a"));
            }
            if (jSONObject.has("b")) {
                spItem.b(jSONObject.getString("b"));
            }
            object = jSONObject.keys();
            hashMap = new HashMap();
        }
        catch (JSONException var0_1) {
            Log.e((String)c, (String)"parser spItem error", (Throwable)var0_1);
            return null;
        }
        while (object.hasNext()) {
            String string2 = (String)object.next();
            hashMap.put(string2, (String)jSONObject.get(string2));
            spItem.a((Map<String, String>)hashMap);
        }
        return spItem;
    }
}

