/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.File
 *  java.io.FileNotFoundException
 *  java.io.FileOutputStream
 *  java.io.OutputStream
 *  java.io.Reader
 *  java.io.Writer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.zip.ZipOutputStream
 */
package com.xiaomi.channel.commonutils.file;

import com.xiaomi.channel.commonutils.logger.MyLog;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.zip.ZipOutputStream;

public class IOUtils {
    public static final String[] SUPPORTED_IMAGE_FORMATS = new String[]{"jpg", "png", "bmp", "gif", "webp"};

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void closeQuietly(InputStream inputStream) {
        if (inputStream == null) return;
        try {
            inputStream.close();
            return;
        }
        catch (IOException var0_1) {
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void closeQuietly(OutputStream outputStream) {
        if (outputStream == null) return;
        try {
            outputStream.flush();
        }
        catch (IOException var1_2) {}
        try {
            outputStream.close();
            return;
        }
        catch (IOException var0_1) {
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void closeQuietly(Reader reader) {
        if (reader == null) return;
        try {
            reader.close();
            return;
        }
        catch (IOException var0_1) {
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void closeQuietly(Writer writer) {
        if (writer == null) return;
        try {
            writer.close();
            return;
        }
        catch (IOException var0_1) {
            return;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void zip(File var0, File var1_7) {
        var3_10 = null;
        var2_11 = null;
        var5_13 = null;
        var4_14 = null;
        var0 = new FileOutputStream((File)var0, false);
        var0 = new ZipOutputStream((OutputStream)var0);
        IOUtils.zip((ZipOutputStream)var0, (File)var1_7, null, null);
        IOUtils.closeQuietly((OutputStream)var0);
        return;
        catch (FileNotFoundException var0_1) {
            block14 : {
                var0 = var4_14;
                ** GOTO lbl45
                catch (IOException var0_2) {
                    block13 : {
                        var1_7 = var3_10;
                        ** GOTO lbl32
                        catch (Throwable var0_4) {
                            var2_11 = var5_13;
                            ** GOTO lbl-1000
                        }
                        catch (Throwable var1_8) {
                            var2_11 = var0;
                            var0 = var1_8;
                            ** GOTO lbl-1000
                        }
                        catch (IOException var0_5) {
                            var1_7 = var3_10;
                            break block13;
                        }
                        catch (IOException var2_12) {
                            var1_7 = var0;
                            var0 = var2_12;
                        }
                    }
                    var2_11 = var1_7;
                    try {
                        MyLog.warn("zip file failure + " + var0.getMessage());
                    }
                    catch (Throwable var0_3) lbl-1000: // 3 sources:
                    {
                        IOUtils.closeQuietly((OutputStream)var2_11);
                        throw var0;
                    }
                    IOUtils.closeQuietly((OutputStream)var1_7);
                    return;
                }
                catch (FileNotFoundException var0_6) {
                    var0 = var4_14;
                    break block14;
                }
                catch (FileNotFoundException var1_9) {}
            }
            IOUtils.closeQuietly((OutputStream)var0);
            return;
        }
    }

    /*
     * Exception decompiling
     */
    public static void zip(ZipOutputStream var0, File var1_4, String var2_6, FileFilter var3_8) throws IOException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 22[SIMPLE_IF_TAKEN]
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

}

