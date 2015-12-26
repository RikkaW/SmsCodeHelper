/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.FileUtils
 *  android.text.TextUtils
 *  java.io.BufferedReader
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.FileNotFoundException
 *  java.io.InputStreamReader
 *  java.io.Reader
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.UUID
 */
import android.content.Context;
import android.os.FileUtils;
import android.text.TextUtils;
import com.ted.android.contacts.common.util.FileUtil;
import com.ted.android.contacts.common.util.NovoFileUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class auw {
    private auy a;
    private bd b = null;

    public auw(auy auy2) {
        this.a = auy2;
    }

    private String a(be be2) {
        return this.a.e().concat("/" + be2.f());
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String a(InputStream object) {
        BufferedReader bufferedReader = new BufferedReader((Reader)new InputStreamReader((InputStream)object));
        object = new StringBuilder();
        do {
            String string2;
            block11 : {
                string2 = bufferedReader.readLine();
                if (string2 != null) break block11;
                bufferedReader.close();
                return object.toString();
            }
            object.append(string2).append("\n");
            continue;
            break;
        } while (true);
        catch (IOException iOException) {
            try {
                bufferedReader.close();
                return object.toString();
            }
            catch (IOException var1_3) {
                return object.toString();
            }
        }
        catch (Throwable throwable) {
            try {
                bufferedReader.close();
            }
            catch (IOException var1_4) {
                throw throwable;
            }
            throw throwable;
        }
        catch (IOException iOException2) {
            return object.toString();
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    public static String a(String var0) {
        block17 : {
            var2_5 = null;
            var0 = new FileInputStream(new File(var0));
            var2_5 = var1_6 = auw.a((InputStream)var0);
            if (var0 == null) break block17;
            try {
                var0.close();
                var2_5 = var1_6;
            }
            catch (IOException var0_4) {
                return var1_6;
            }
        }
        do {
            return var2_5;
            break;
        } while (true);
        catch (FileNotFoundException var0_1) {
            var0 = null;
lbl15: // 2 sources:
            do {
                if (var0 == null) ** continue;
                try {
                    var0.close();
                    return null;
                }
                catch (IOException var0_2) {
                    return null;
                }
                break;
            } while (true);
        }
        catch (Throwable var1_7) {
            var0 = null;
lbl24: // 2 sources:
            do {
                if (var0 != null) {
                    var0.close();
                }
lbl28: // 4 sources:
                do {
                    throw var1_8;
                    break;
                } while (true);
                catch (IOException var0_3) {
                    ** continue;
                }
                break;
            } while (true);
        }
        catch (Throwable var1_9) {
            ** continue;
        }
        catch (FileNotFoundException var1_10) {
            ** continue;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a() {
        Object object;
        if (this.b()) {
            File file;
            block7 : {
                if (!anw.b(this.a.d()) || !(file = new File(this.a.l())).exists()) return;
                try {
                    bd bd2 = bd.a(FileUtils.readTextFile((File)file, (int)0, (String)null));
                    if (bd2 != null) {
                        this.a(bd2);
                    }
                }
                catch (IOException var2_5) {
                    if (!aux.a) break block7;
                    var2_5.printStackTrace();
                }
            }
            file.delete();
            return;
        }
        object = String.valueOf((Object)this.a.j()) + "/" + "u.zip";
        String string2 = String.valueOf((Object)this.a.e()) + "/" + "u.zip";
        if (!bl.a(this.a.d(), (String)object, string2)) return;
        bj.a(new File(string2), new File(this.a.e()), true);
        try {
            new File(string2).delete();
        }
        catch (Exception var1_3) {}
        if ((object = this.c()) == null) {
            return;
        }
        this.a((bd)object);
        FileUtil.safeRenameTo(new File(this.a.i()), new File(this.a.f()));
        FileUtil.safeRenameTo(new File(this.a.h()), new File(this.a.g()));
    }

    public static void a(auy auy2) {
        if (auy2.a()) {
            auy2.b();
            auy2.c();
            new auw(auy2).a();
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void a(bd var1_1) {
        if (var1_1.c() != null && var1_1.c().size() > 0) {
            var4_2 = var1_1.c().iterator();
            var2_3 = false;
            while (var4_2.hasNext()) {
                var6_8 = var4_2.next();
                var5_5 = this.a((be)var6_8);
                var2_3 = var3_4 = bl.a(this.a.d(), var6_8.a(), (String)var5_5);
                if (!var3_4) break;
                var6_8 = this.b(var6_8.b());
                FileUtil.safeRenameTo(new File((String)var5_5), new File((String)var6_8));
                NovoFileUtil.writeTimestamp2File(this.a.d(), (String)var6_8, System.currentTimeMillis() / 1000);
                var2_3 = var3_4;
            }
            if (!var2_3) {
                return;
            }
        }
        if (var1_1.c() != null && var1_1.c().size() > 0) {
            this.a.a(var1_1.c());
        }
        if (var1_1.b() != null && var1_1.b().size() > 0) {
            this.a.a(var1_1.b().get(0));
        }
        if (var1_1.d() == null || var1_1.d().size() <= 0) ** GOTO lbl-1000
        var4_2 = var1_1.d().iterator();
        do {
            if (!var4_2.hasNext()) lbl-1000: // 2 sources:
            {
                if (var1_1.a() == null) return;
                if (var1_1.a().size() <= 0) return;
                var4_2 = var5_5 = anw.a(this.a.d());
                if (TextUtils.isEmpty((CharSequence)var5_5)) {
                    var4_2 = "U" + UUID.randomUUID().toString().replaceAll("-", "").replace((CharSequence)":", (CharSequence)"").toLowerCase();
                    break;
                }
                break;
            }
            var5_5 = this.b(var4_2.next().b());
            try {
                if (!(var5_5 = new File((String)var5_5)).exists()) continue;
                var5_5.delete();
            }
            catch (Exception var5_6) {}
        } while (true);
        var4_2 = this.a.k().concat("/" + (String)var4_2);
        var1_1 = var1_1.a().iterator();
        do {
            if (!var1_1.hasNext()) {
                FileUtil.deleteFile((String)var4_2);
                return;
            }
            var5_5 = var1_1.next();
            try {
                var6_8 = this.b(var5_5.b());
                anz.a(new File((String)var4_2), new File((String)var6_8), var5_5.f());
            }
            catch (Exception var5_7) {
            }
        } while (true);
    }

    private boolean b() {
        boolean bl2 = false;
        String string2 = String.valueOf((Object)this.a.j()) + "/" + "u.md5";
        String string3 = this.a.i();
        if (bl.a(this.a.d(), string2, string3)) {
            bl2 = auw.a(string3).equals((Object)auw.a(this.a.f()));
        }
        return bl2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private bd c() {
        Object var6_1 = null;
        List<be> list = auw.a(this.a.h());
        Object object = this.a.g();
        try {
            object = FileUtils.readTextFile((File)new File((String)object), (int)0, (String)null);
        }
        catch (IOException var1_4) {
            object = null;
        }
        Object object2 = !TextUtils.isEmpty((CharSequence)object) ? bd.a((String)object) : null;
        object = !TextUtils.isEmpty((CharSequence)((Object)list)) ? bd.a((String)((Object)list)) : null;
        if (this.b == null && object != null) {
            this.b = new bd();
            this.b.b((List<be>)new ArrayList());
            this.b.a((List<be>)new ArrayList());
            this.b.c((List<be>)new ArrayList());
            this.b.d((List<be>)new ArrayList());
        }
        list = object == null ? null : object.c();
        List<be> list2 = object2 == null ? null : object2.c();
        List<be> list3 = this.b == null ? null : this.b.c();
        this.a(list, list2, list3);
        list = object == null ? null : object.b();
        list2 = object2 == null ? null : object2.b();
        list3 = this.b == null ? null : this.b.b();
        this.a(list, list2, list3);
        list = object == null ? null : object.d();
        list2 = object2 == null ? null : object2.d();
        list3 = this.b == null ? null : this.b.d();
        this.a(list, list2, list3);
        list = object == null ? null : object.a();
        object2 = object2 == null ? null : object2.a();
        list2 = this.b == null ? var6_1 : this.b.a();
        this.a(list, (List<be>)object2, list2);
        if (anw.b(this.a.d()) || this.b.b().size() <= 0 && this.b.c().size() <= 0 && this.b.a().size() <= 0 && this.b.d().size() <= 0) return object;
        try {
            FileUtils.stringToFile((String)this.a.l(), (String)this.b.e());
            return object;
        }
        catch (IOException var2_6) {
            if (!aux.a) return object;
            var2_6.printStackTrace();
            return object;
        }
    }

    /*
     * Exception decompiling
     */
    public void a(List<be> var1_1, List<be> var2_2, List<be> var3_3) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Inconsistent graph [0] lbl5 : Nop: NOP\u000a does not have a source [] lbl1 : IfStatement: if (var1_1 == null || var2_2 == null) !!! goto bad target
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:49)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:22)
        // org.benf.cfr.reader.util.graph.GraphVisitorDFS.process(GraphVisitorDFS.java:68)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner.removeUnreachableCode(Cleaner.java:54)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:632)
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

    public String b(String string2) {
        String string3 = string2;
        if (string2 != null) {
            string3 = string2;
            if (!string2.startsWith("/")) {
                string3 = new File(this.a.d().getFilesDir(), string2).getAbsolutePath();
            }
        }
        return string3;
    }
}

