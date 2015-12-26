/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.res.AssetManager
 *  android.content.res.Resources
 *  java.io.BufferedReader
 *  java.io.ByteArrayOutputStream
 *  java.io.DataInputStream
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.FileOutputStream
 *  java.io.InputStreamReader
 *  java.io.Reader
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.net.HttpURLConnection
 *  java.util.ArrayList
 *  java.util.Arrays
 *  java.util.zip.ZipFile
 */
package cn.com.xy.sms.sdk.util;

import android.content.res.AssetManager;
import android.content.res.Resources;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.n;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipFile;

public final class d {
    private static String a = "FileUtils";

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static File a(String var0, String var1_4, InputStream var2_5) {
        if (var2_5 == null) {
            return null;
        }
        var7_6 = new File(String.valueOf((Object)var0) + var1_4);
        var5_8 = var4_7 = new FileOutputStream(var7_6);
        try {
            var6_9 = new byte[1024];
        }
        catch (Throwable var6_10) lbl-1000: // 3 sources:
        {
            block21 : {
                var5_8 = var4_7;
                var6_11.printStackTrace();
                var5_8 = var4_7;
                new StringBuilder("e=").append(var6_11.getLocalizedMessage());
                var5_8 = var4_7;
                if (!d.a(String.valueOf((Object)var0) + var1_4)) break block21;
                var5_8 = var4_7;
                d.c(String.valueOf((Object)var0) + var1_4);
            }
            d.a((Closeable)var4_7);
            d.a((Closeable)var2_5);
            return var7_6;
        }
        do {
            block20 : {
                var5_8 = var4_7;
                var3_14 = var2_5.read(var6_9);
                if (var3_14 > 0) break block20;
                var5_8 = var4_7;
                var4_7.flush();
                d.a((Closeable)var4_7);
                d.a((Closeable)var2_5);
                return var7_6;
            }
            var5_8 = var4_7;
            var4_7.write(var6_9, 0, var3_14);
            continue;
            break;
        } while (true);
        catch (Throwable var0_1) {
            var5_8 = null;
lbl45: // 2 sources:
            do {
                d.a((Closeable)var5_8);
                d.a((Closeable)var2_5);
                throw var0_2;
                break;
            } while (true);
        }
        {
            catch (Throwable var0_3) {
                ** continue;
            }
        }
        {
            catch (Throwable var6_12) {
                var4_7 = null;
                var7_6 = null;
                ** GOTO lbl-1000
            }
            catch (Throwable var6_13) {
                var4_7 = null;
                ** GOTO lbl-1000
            }
        }
    }

    public static String a(int n2) {
        if (n2 == 1) {
            return "X8448A";
        }
        return "X4667U";
    }

    public static String a(InputStream object) {
        try {
            object = new String(d.b((InputStream)object), "UTF-8");
            return object;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return "";
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void a(Closeable closeable) {
        if (closeable == null) return;
        try {
            closeable.close();
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(File file) {
        File[] arrfile;
        if (!file.exists()) return;
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (!file.isDirectory()) return;
        {
            arrfile = file.listFiles();
            if (arrfile == null) {
                file.delete();
                return;
            }
        }
        int n2 = 0;
        do {
            if (n2 >= arrfile.length) {
                file.delete();
                return;
            }
            d.a(arrfile[n2]);
            ++n2;
        } while (true);
    }

    public static void a(String string2, String string3, String string4) {
        try {
            new StringBuilder("dir=").append(string2).append("oldFileName=").append(string3).append("newFileName=").append(string4);
            string3 = new File(String.valueOf((Object)string2) + string3);
            if (string3.exists()) {
                string3.renameTo(new File(String.valueOf((Object)string2) + string4));
            }
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void a(String object, String string2, String string3, String string4) {
        try {
            object = d.e((String)object, string2, string3);
            a = "deleteFile";
            d.a(object, string4);
            return;
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
    private static void a(HttpURLConnection httpURLConnection) {
        if (httpURLConnection == null) return;
        try {
            httpURLConnection.disconnect();
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
    private static void a(List<File> object, String string2) {
        if (object == null) return;
        {
            try {
                if (object.isEmpty()) return;
                {
                    object = object.iterator();
                    while (object != null) {
                        String string3;
                        if (!object.hasNext()) {
                            return;
                        }
                        File file = (File)object.next();
                        if (file.getName().equals((Object)string2)) {
                            string3 = a;
                            new StringBuilder("\u4e0d\u5220\u9664").append(file.getAbsolutePath());
                            continue;
                        }
                        file.delete();
                        string3 = a;
                        new StringBuilder(String.valueOf((Object)a)).append("=").append(file.getAbsolutePath());
                    }
                    return;
                }
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return;
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void a(ZipFile zipFile) {
        if (zipFile == null) return;
        try {
            zipFile.close();
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static boolean a(String string2) {
        boolean bl2 = false;
        if (new File(string2).exists()) {
            bl2 = true;
        }
        return bl2;
    }

    public static InputStream b(String string2) {
        try {
            string2 = new File(string2);
            if (string2.exists()) {
                string2 = new FileInputStream((File)string2);
                return string2;
            }
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
        }
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void b(String list, String string2, String string3) {
        File file;
        try {
            file = Constant.getContext().getDir("outdex", 0);
            if (file == null) return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
        list = d.e(file.getCanonicalPath(), list, string2);
        a = "deleteDex";
        d.a(list, string3);
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static byte[] b(InputStream var0) {
        var6_1 = new byte[2560];
        var5_2 = new ByteArrayOutputStream();
        try {
            var3_3 = new DataInputStream(var0);
        }
        catch (Throwable var3_4) {
            var2_8 = null;
lbl32: // 2 sources:
            do {
                d.a((Closeable)var2_8);
                d.a((Closeable)var0);
                d.a((Closeable)var5_2);
                throw var3_5;
                break;
            } while (true);
        }
        do {
            block12 : {
                var2_8 = var3_3;
                var1_7 = var3_3.read(var6_1);
                if (var1_7 > 0) break block12;
                var2_8 = var3_3;
                var4_9 = var5_2.toByteArray();
                d.a((Closeable)var3_3);
                d.a((Closeable)var0);
                d.a((Closeable)var5_2);
                return var4_9;
            }
            var2_8 = var3_3;
            var5_2.write(var6_1, 0, var1_7);
            continue;
            break;
        } while (true);
        catch (Throwable var4_10) lbl-1000: // 2 sources:
        {
            var2_8 = var3_3;
            var4_11.printStackTrace();
            d.a((Closeable)var3_3);
            d.a((Closeable)var0);
            d.a((Closeable)var5_2);
            return var6_1;
        }
        {
            catch (Throwable var3_6) {
                ** continue;
            }
        }
        catch (Throwable var4_12) {
            var3_3 = null;
            ** GOTO lbl-1000
        }
    }

    public static boolean c(String string2) {
        boolean bl2;
        block4 : {
            boolean bl3;
            bl2 = bl3 = false;
            try {
                if (StringUtils.isNull(string2)) break block4;
                string2 = new File(string2);
                bl2 = bl3;
            }
            catch (Throwable var0_1) {
                return false;
            }
            if (!string2.exists()) break block4;
            bl2 = bl3;
            if (!string2.isFile()) break block4;
            string2.delete();
            bl2 = true;
        }
        return bl2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean c(String arrfile, String string2, String string3) {
        arrfile = new File[]((String)arrfile);
        if (!arrfile.exists()) {
            arrfile.mkdir();
        }
        if ((arrfile = arrfile.listFiles((FileFilter)new n(string2, string3))) == null) return false;
        try {
            int n2 = arrfile.length;
            if (n2 <= 0) return false;
            return true;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String d(String object, String string2, String string3) {
        object = new File((String)object);
        if (!object.exists()) {
            object.mkdir();
        }
        if ((object = object.listFiles((FileFilter)new n(string2, string3))) == null) return "";
        try {
            if (object.length <= 0) return "";
            return object[0].getCanonicalPath();
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
        }
        return "";
    }

    public static void d(String string2) {
        d.a(new File(string2));
    }

    public static List<File> e(String object, String arrobject, String string2) {
        block4 : {
            ArrayList arrayList = new ArrayList();
            try {
                object = new File((String)object);
                if (!object.exists()) {
                    object.mkdir();
                }
                arrobject = object.listFiles((FileFilter)new n((String)arrobject, string2));
                object = arrayList;
                if (arrobject == null) break block4;
                object = arrayList;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return arrayList;
            }
            if (arrobject.length <= 0) break block4;
            object = Arrays.asList((Object[])arrobject);
        }
        return object;
    }

    public static byte[] e(String string2) {
        return d.b((InputStream)new FileInputStream(string2));
    }

    /*
     * Exception decompiling
     */
    public static int f(String var0, String var1_13, String var2_18) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [10[CATCHBLOCK]], but top level block is 13[CATCHBLOCK]
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
    public static String f(String var0) {
        block14 : {
            var2_5 = null;
            var2_5 = var0 = Constant.getContext().getResources().getAssets().open((String)var0);
            try {
                var3_7 = new byte[var0.available()];
                var2_5 = var0;
                var0.read((byte[])var3_7);
                var2_5 = var0;
                var0.close();
                var2_5 = var0;
                var3_7 = new String((byte[])var3_7, "GB2312");
                var2_5 = var0;
                var1_11 = StringUtils.isNull((String)var3_7);
                ** if (var1_11) goto lbl-1000
            }
            catch (Throwable var3_10) {
                ** GOTO lbl26
            }
            catch (IOException var2_6) {
                ** continue;
            }
lbl-1000: // 1 sources:
            {
                d.a((Closeable)var0);
                return var3_7;
            }
lbl-1000: // 1 sources:
            {
                break block14;
            }
            catch (IOException var0_1) {
                var0 = var2_5;
lbl20: // 2 sources:
                do {
                    d.a((Closeable)var0);
lbl22: // 3 sources:
                    do {
                        return "-1";
                        break;
                    } while (true);
                    break;
                } while (true);
            }
            catch (Throwable var3_8) {
                var0 = null;
lbl26: // 2 sources:
                var2_5 = var0;
                var3_9.printStackTrace();
                d.a((Closeable)var0);
                ** GOTO lbl22
            }
            catch (Throwable var0_2) {
                var2_5 = null;
lbl33: // 2 sources:
                do {
                    d.a((Closeable)var2_5);
                    throw var0_3;
                    break;
                } while (true);
            }
        }
        d.a((Closeable)var0);
        ** while (true)
        {
            catch (Throwable var0_4) {
                ** continue;
            }
        }
    }

    /*
     * Exception decompiling
     */
    public static int g(String var0, String var1_1, String var2_12) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [4[TRYBLOCK]], but top level block is 33[SIMPLE_IF_TAKEN]
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
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static List<String> g(String var0) {
        var3_2 = null;
        var0 = Constant.getContext().getResources().getAssets().open((String)var0);
        var3_2 = var1_3 = new BufferedReader((Reader)new InputStreamReader((InputStream)var0, "GB2312"));
        var2_9 = var0;
        try {
            var4_13 = new ArrayList();
        }
        catch (IOException var2_10) {
            var2_11 = var1_3;
            var1_3 = var0;
            var0 = var2_11;
lbl28: // 3 sources:
            do {
                d.a((Closeable)var0);
                d.a((Closeable)var1_3);
                do {
                    return null;
                    break;
                } while (true);
                break;
            } while (true);
        }
        do {
            block20 : {
                var3_2 = var1_3;
                var2_9 = var0;
                var5_18 = var1_3.readLine();
                if (var5_18 != null) break block20;
                d.a((Closeable)var1_3);
                d.a((Closeable)var0);
                return var4_13;
            }
            var3_2 = var1_3;
            var2_9 = var0;
            var4_13.add(var5_18);
            continue;
            break;
        } while (true);
        catch (Throwable var4_14) {
            var1_3 = null;
            var0 = null;
lbl36: // 3 sources:
            var3_2 = var1_3;
            var2_9 = var0;
            var4_15.printStackTrace();
            d.a((Closeable)var1_3);
            d.a((Closeable)var0);
            return null;
        }
        catch (Throwable var1_4) {
            var0 = null;
lbl45: // 3 sources:
            do {
                d.a((Closeable)var3_2);
                d.a((Closeable)var0);
                throw var1_5;
                break;
            } while (true);
        }
        catch (Throwable var1_6) {
            ** GOTO lbl45
        }
        {
            catch (Throwable var1_7) {
                var0 = var2_9;
                ** continue;
            }
        }
        catch (Throwable var4_16) {
            var1_3 = null;
            ** GOTO lbl36
        }
        catch (Throwable var4_17) {
            ** GOTO lbl36
        }
        catch (IOException var0_1) {
            var0 = null;
            var1_3 = null;
            ** GOTO lbl28
        }
        catch (IOException var1_8) {
            var2_12 = null;
            var1_3 = var0;
            var0 = var2_12;
            ** continue;
        }
    }

    /*
     * Exception decompiling
     */
    private static int h(String var0, String var1_4, String var2_5) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [11[CATCHBLOCK], 12[CATCHBLOCK]], but top level block is 13[CATCHBLOCK]
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

