/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.io.ByteArrayOutputStream
 *  java.io.File
 *  java.io.FileNotFoundException
 *  java.io.FileOutputStream
 *  java.io.OutputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  miui.util.HashUtils
 */
package com.android.mms.update;

import android.content.Context;
import com.android.mms.update.Request;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import miui.util.HashUtils;

public class StreamRequest
extends Request {
    public StreamRequest(Context context, String string2) {
        super(context, string2);
    }

    /*
     * Exception decompiling
     */
    private ByteArrayOutputStream getTempDownloadData() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [] lbl33 : TryStatement: try { 5[TRYBLOCK]

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

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    private void saveTemporaryDownloadedData(ByteArrayOutputStream var1_1) {
        var5_9 = null;
        var2_10 = null;
        var4_14 = null;
        var3_15 = new FileOutputStream((Object)this.mContext.getCacheDir() + File.separator + String.format((String)"%s_temp_downloaded", (Object[])new Object[]{HashUtils.getSHA1((String)this.getRequestUrl())}));
        var3_15.write(var1_1.toByteArray());
        var3_15.flush();
        if (var3_15 == null) ** GOTO lbl12
        var3_15.close();
lbl12: // 5 sources:
        do {
            return;
            break;
        } while (true);
        catch (FileNotFoundException var3_16) {
            var1_1 = var4_14;
lbl16: // 2 sources:
            var2_10 = var1_1;
            var3_15.printStackTrace();
            if (var1_1 == null) ** GOTO lbl12
            try {
                var1_1.close();
                return;
            }
            catch (IOException var1_2) lbl-1000: // 2 sources:
            {
                do {
                    var1_3.printStackTrace();
                    return;
                    break;
                } while (true);
            }
        }
        catch (IOException var3_17) {
            var1_1 = var5_9;
lbl29: // 2 sources:
            do {
                var2_10 = var1_1;
                var3_15.printStackTrace();
                if (var1_1 == null) ** GOTO lbl12
                {
                    catch (Throwable var1_5) lbl-1000: // 2 sources:
                    {
                        do {
                            if (var2_10 != null) {
                                var2_10.close();
                            }
lbl39: // 4 sources:
                            do {
                                throw var1_6;
                                break;
                            } while (true);
                            catch (IOException var2_11) {
                                var2_11.printStackTrace();
                                ** continue;
                            }
                            break;
                        } while (true);
                    }
                }
                try {
                    var1_1.close();
                    return;
                }
                catch (IOException var1_4) {
                    ** continue;
                }
                break;
            } while (true);
        }
        catch (IOException var1_7) {
            var1_7.printStackTrace();
            ** continue;
        }
        catch (Throwable var1_8) {
            var2_10 = var3_15;
            ** continue;
        }
        catch (IOException var2_12) {
            var1_1 = var3_15;
            var3_15 = var2_12;
            ** continue;
        }
        catch (FileNotFoundException var2_13) {
            var1_1 = var3_15;
            var3_15 = var2_13;
            ** GOTO lbl16
        }
    }

    @Override
    protected String getRequestUrl() {
        return this.mRequestUrl;
    }

    /*
     * Exception decompiling
     */
    public int requestStream(OutputStream var1_1, Map<String, String> var2_14) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [9[TRYBLOCK]], but top level block is 5[TRYBLOCK]
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

