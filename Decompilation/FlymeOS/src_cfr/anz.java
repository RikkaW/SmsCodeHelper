/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.FileOutputStream
 *  java.io.OutputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.zip.ZipEntry
 *  java.util.zip.ZipFile
 *  java.util.zip.ZipOutputStream
 */
import com.ted.android.contacts.common.util.FileUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class anz {
    private static final String a = anz.class.getSimpleName();

    /*
     * Exception decompiling
     */
    public static void a(String var0, b var1_5) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 8[CATCHBLOCK]
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
    private static void a(ZipOutputStream var0, File var1_8, String var2_10) {
        if (var1_8.isDirectory()) ** GOTO lbl35
        if (var1_8.canRead() == false) return;
        var5_14 = null;
        var1_8 = new FileInputStream((File)var1_8);
        var0.putNextEntry(new ZipEntry(var2_10));
        FileUtil.copyStream((InputStream)var1_8, (OutputStream)var0);
        var0.closeEntry();
        if (var1_8 == null) return;
        try {
            var1_8.close();
            return;
        }
        catch (IOException var0_1) {
            return;
        }
        catch (Exception var0_2) {
            return;
            catch (Throwable var0_4) {
                var1_8 = var5_14;
                ** GOTO lbl22
                catch (Throwable var0_6) {}
lbl22: // 2 sources:
                if (var1_8 == null) throw var0_5;
                try {
                    var1_8.close();
                }
                catch (IOException var1_9) {
                    throw var0_5;
                }
                throw var0_5;
            }
            catch (Exception var0_7) {}
            if (var1_8 == null) return;
            try {
                var1_8.close();
                return;
            }
            catch (IOException var0_3) {
                return;
            }
        }
lbl35: // 1 sources:
        if ((var1_8 = var1_8.listFiles()) == null) return;
        var4_11 = var1_8.length;
        var3_12 = 0;
        do {
            if (var3_12 >= var4_11) {
                return;
            }
            var5_13 = var1_8[var3_12];
            anz.a(var0, (File)var5_13, String.valueOf((Object)var2_10) + "/" + var5_13.getName());
            ++var3_12;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    public static boolean a(File var0, File var1_5, String var2_8) {
        block19 : {
            var3_10 = null;
            var0 = new ZipOutputStream((OutputStream)new FileOutputStream(var0));
            anz.a((ZipOutputStream)var0, var1_5, var2_8);
            var0.finish();
            var0.flush();
            if (var0 == null) break block19;
            try {
                var0.close();
            }
            catch (IOException var0_3) {
                ** continue;
            }
        }
lbl12: // 2 sources:
        do {
            return true;
            break;
        } while (true);
        catch (Exception var0_1) {
            var0 = null;
lbl16: // 2 sources:
            if (var0 != null) {
                var0.close();
            }
lbl19: // 4 sources:
            do {
                return false;
                break;
            } while (true);
        }
        catch (Throwable var0_2) {
            var1_5 = var3_10;
lbl23: // 2 sources:
            if (var1_5 != null) {
                var1_5.close();
            }
lbl26: // 4 sources:
            do {
                throw var0;
                break;
            } while (true);
        }
        {
            catch (IOException var0_4) {
                ** continue;
            }
        }
        {
            catch (IOException var1_6) {
                ** continue;
            }
        }
        catch (Throwable var2_9) {
            var1_5 = var0;
            var0 = var2_9;
            ** GOTO lbl23
        }
        catch (Exception var1_7) {
            ** GOTO lbl16
        }
    }

    public static boolean a(String string2, File file) {
        a a2 = new a();
        a2.b = file;
        anz.a(string2, a2);
        return a2.a;
    }

    static class a
    implements b {
        boolean a = true;
        File b;

        a() {
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive exception aggregation
         */
        @Override
        public boolean a(ZipFile var1_1, ZipEntry var2_3) {
            var4_7 = null;
            var5_10 = null;
            var3_12 = true;
            var6_13 = new File(this.b, var2_3.getName());
            if (var2_3.isDirectory()) {
                if (var6_13.exists() || var6_13.mkdirs()) {
                    var3_12 = false;
                    do {
                        return var3_12;
                        break;
                    } while (true);
                }
                this.a = false;
                return true;
            }
            var7_14 = var6_13.getParentFile();
            if (var7_14.exists() || var7_14.mkdirs()) {
                block20 : {
                    var1_1 = var1_1.getInputStream((ZipEntry)var2_3);
                    var2_3 = new FileOutputStream(var6_13);
                    FileUtil.copyStream((InputStream)var1_1, (OutputStream)var2_3);
                    if (var1_1 == null) break block20;
                    var1_1.close();
                }
                if (var2_3 != null) {
                    var2_3.close();
                }
                return false;
                catch (Exception var1_2) {
                    block21 : {
                        var1_1 = null;
                        var2_3 = var5_10;
lbl30: // 4 sources:
                        this.a = false;
                        if (var2_3 == null) break block21;
                        var2_3.close();
                    }
                    if (var1_1 == null) ** continue;
                    var1_1.close();
                    return true;
                }
                catch (Throwable var2_4) {
                    var1_1 = null;
lbl39: // 4 sources:
                    do {
                        if (var1_1 != null) {
                            var1_1.close();
                        }
                        if (var4_7 != null) {
                            var4_7.close();
                        }
                        throw var2_3;
                        break;
                    } while (true);
                }
            }
            this.a = false;
            return true;
            catch (Throwable var2_5) {
                ** GOTO lbl39
            }
            catch (Throwable var5_11) {
                var4_7 = var2_3;
                var2_3 = var5_11;
                ** GOTO lbl39
            }
            {
                catch (Throwable var4_8) {
                    var5_10 = var2_3;
                    var2_3 = var4_8;
                    var4_7 = var1_1;
                    var1_1 = var5_10;
                    ** continue;
                }
            }
            catch (Exception var2_6) {
                var4_7 = null;
                var2_3 = var1_1;
                var1_1 = var4_7;
                ** GOTO lbl30
            }
            catch (Exception var4_9) {
                var4_7 = var1_1;
                var1_1 = var2_3;
                var2_3 = var4_7;
                ** GOTO lbl30
            }
        }
    }

    public static interface b {
        public boolean a(ZipFile var1, ZipEntry var2);
    }

}

