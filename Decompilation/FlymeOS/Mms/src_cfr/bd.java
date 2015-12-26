/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class bd {
    private static String a = bd.class.getSimpleName();
    private List<be> b;
    private List<be> c;
    private List<be> d;
    private List<be> e;

    /*
     * Exception decompiling
     */
    public static bd a(String var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [] lbl71 : TryStatement: try { 8[TRYBLOCK]

        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:44)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:22)
        // org.benf.cfr.reader.util.graph.GraphVisitorDFS.process(GraphVisitorDFS.java:68)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner.removeUnreachableCode(Cleaner.java:54)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.RemoveDeterministicJumps.apply(RemoveDeterministicJumps.java:35)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:507)
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

    public List<be> a() {
        return this.b;
    }

    public void a(List<be> list) {
        this.b = list;
    }

    public List<be> b() {
        return this.c;
    }

    public void b(List<be> list) {
        this.c = list;
    }

    public List<be> c() {
        return this.e;
    }

    public void c(List<be> list) {
        this.e = list;
    }

    public List<be> d() {
        return this.d;
    }

    public void d(List<be> list) {
        this.d = list;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public String e() {
        Iterator<be> iterator;
        JSONObject jSONObject;
        JSONArray jSONArray;
        block21 : {
            block20 : {
                block19 : {
                    jSONObject = new JSONObject();
                    if (this.b() != null && this.b().size() > 0) {
                        jSONArray = new JSONArray();
                        iterator = this.b().iterator();
                        do {
                            if (!iterator.hasNext()) {
                                jSONObject.put("a", (Object)jSONArray);
                                break block19;
                            }
                            jSONArray.put((Object)iterator.next().e());
                        } while (true);
                        catch (JSONException jSONException) {}
                    }
                }
                if (this.c() != null && this.c().size() > 0) {
                    jSONArray = new JSONArray();
                    iterator = this.c().iterator();
                    do {
                        if (!iterator.hasNext()) {
                            jSONObject.put("d", (Object)jSONArray);
                            break block20;
                        }
                        jSONArray.put((Object)iterator.next().e());
                    } while (true);
                    catch (JSONException jSONException) {}
                }
            }
            if (this.a() != null && this.a().size() > 0) {
                jSONArray = new JSONArray();
                iterator = this.a().iterator();
                do {
                    if (!iterator.hasNext()) {
                        jSONObject.put("u", (Object)jSONArray);
                        break block21;
                    }
                    jSONArray.put((Object)iterator.next().e());
                } while (true);
                catch (JSONException jSONException) {}
            }
        }
        if (this.d() == null) return jSONObject.toString();
        if (this.d().size() <= 0) return jSONObject.toString();
        jSONArray = new JSONArray();
        iterator = this.d().iterator();
        do {
            if (!iterator.hasNext()) {
                jSONObject.put("r", (Object)jSONArray);
                return jSONObject.toString();
            }
            jSONArray.put((Object)iterator.next().e());
        } while (true);
        catch (JSONException jSONException) {
            return jSONObject.toString();
        }
    }
}

