/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$CompressFormat
 *  android.os.Environment
 *  android.os.StatFs
 *  java.io.File
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.security.MessageDigest
 *  java.util.HashMap
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;
import com.amap.api.mapcore2d.cs$1;
import com.amap.api.mapcore2d.cw;
import com.amap.api.mapcore2d.cx;
import com.amap.api.mapcore2d.cy;
import com.amap.api.mapcore2d.ep;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class cs {
    private static final Bitmap.CompressFormat a = Bitmap.CompressFormat.JPEG;
    private ep b;
    private cx<String, Bitmap> c;
    private a d;
    private final Object e = new Object();
    private boolean f = true;
    private HashMap<String, WeakReference<Bitmap>> g;

    private cs(a a2) {
        this.b(a2);
    }

    public static int a(Bitmap bitmap) {
        if (cy.d()) {
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    public static long a(File file) {
        if (cy.b()) {
            return file.getUsableSpace();
        }
        file = new StatFs(file.getPath());
        long l2 = file.getBlockSize();
        return (long)file.getAvailableBlocks() * l2;
    }

    public static cs a(a a2) {
        return new cs(a2);
    }

    public static File a(Context object) {
        if (cy.a()) {
            return object.getExternalCacheDir();
        }
        object = "/Android/data/" + object.getPackageName() + "/cache/";
        return new File(Environment.getExternalStorageDirectory().getPath() + (String)object);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static File a(Context object, String string2) {
        File file = cs.a((Context)object);
        object = ("mounted".equals((Object)Environment.getExternalStorageState()) || !cs.e()) && file != null ? file.getPath() : object.getCacheDir().getPath();
        cw.a("ImageCache", "Disk cachePath: " + (String)object, 111);
        return new File((String)object + File.separator + string2);
    }

    private static String a(byte[] arrby) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i2 = 0; i2 < arrby.length; ++i2) {
            String string2 = Integer.toHexString((int)(arrby[i2] & 255));
            if (string2.length() == 1) {
                stringBuilder.append('0');
            }
            stringBuilder.append(string2);
        }
        return stringBuilder.toString();
    }

    static /* synthetic */ HashMap a(cs cs2) {
        return cs2.g;
    }

    private void b(a a2) {
        this.d = a2;
        if (this.d.f) {
            cw.a("ImageCache", "Memory cache created (size = " + this.d.a + ")", 111);
            if (cy.c()) {
                this.g = new HashMap();
            }
            this.c = new cs$1(this, this.d.a);
        }
        if (a2.h) {
            this.a();
        }
    }

    public static String c(String string2) {
        try {
            Object object = MessageDigest.getInstance((String)"MD5");
            object.update(string2.getBytes());
            object = cs.a(object.digest());
            return object;
        }
        catch (NoSuchAlgorithmException var1_2) {
            cy.a(var1_2, "ImageCache", "hashKeyForDisk");
            return String.valueOf((int)string2.hashCode());
        }
    }

    public static boolean e() {
        if (cy.b()) {
            return Environment.isExternalStorageRemovable();
        }
        return true;
    }

    static /* synthetic */ Bitmap.CompressFormat f() {
        return a;
    }

    /*
     * Enabled aggressive block sorting
     */
    public Bitmap a(String string2) {
        WeakReference weakReference;
        WeakReference weakReference2;
        if (cy.c() && this.g != null && (weakReference = (WeakReference)this.g.get((Object)string2)) != null) {
            block6 : {
                weakReference2 = (Bitmap)weakReference.get();
                if (weakReference2 != null) {
                    weakReference = weakReference2;
                    if (!weakReference2.isRecycled()) break block6;
                }
                weakReference = null;
            }
            this.g.remove((Object)string2);
        } else {
            weakReference = null;
        }
        weakReference2 = weakReference;
        if (weakReference == null) {
            weakReference2 = weakReference;
            if (this.c == null) return null;
            weakReference2 = this.c.a(string2);
        }
        if (weakReference2 != null && !weakReference2.isRecycled()) {
            cw.a("ImageCache", "Memory cache hit", 111);
            return weakReference2;
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a() {
        Object object = this.e;
        synchronized (object) {
            if (this.b == null || this.b.a()) {
                File file = this.d.c;
                if (this.d.g && file != null) {
                    int n2;
                    long l2;
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    if ((l2 = cs.a(file)) > (long)(n2 = this.d.b)) {
                        try {
                            this.b = ep.a(file, 1, 1, this.d.b);
                            cw.a("ImageCache", "Disk cache initialized", 111);
                        }
                        catch (IOException var5_3) {
                            cy.a(var5_3, "ImageCache", "initDiskCache");
                            this.d.c = null;
                            cw.a("ImageCache", "initDiskCache - " + (Object)((Object)var5_3), 112);
                        }
                    }
                }
            }
            this.f = false;
            this.e.notifyAll();
            return;
        }
    }

    /*
     * Exception decompiling
     */
    public void a(String var1_1, Bitmap var2_6) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 29[SIMPLE_IF_TAKEN]
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
    public Bitmap b(String var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [3[TRYBLOCK]], but top level block is 24[SIMPLE_IF_TAKEN]
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
    public void b() {
        if (cy.c() && this.g != null) {
            this.g.clear();
        }
        if (this.c != null) {
            this.c.a();
            cw.a("ImageCache", "Memory cache cleared", 111);
        }
        Object object = this.e;
        synchronized (object) {
            boolean bl2;
            this.f = true;
            if (this.b != null && !(bl2 = this.b.a())) {
                try {
                    this.b.c();
                    cw.a("ImageCache", "Disk cache cleared", 111);
                }
                catch (IOException var3_3) {
                    cy.a(var3_3, "ImageCache", "clearCache");
                    cw.a("ImageCache", "clearCache - " + (Object)((Object)var3_3), 112);
                }
                this.b = null;
                this.a();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void c() {
        Object object = this.e;
        synchronized (object) {
            ep ep2 = this.b;
            if (ep2 != null) {
                try {
                    this.b.b();
                    cw.a("ImageCache", "Disk cache flushed", 111);
                }
                catch (IOException var2_3) {
                    cy.a(var2_3, "ImageCache", "flush");
                    cw.a("ImageCache", "flush - " + (Object)((Object)var2_3), 112);
                }
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void d() {
        if (cy.c() && this.g != null) {
            this.g.clear();
        }
        if (this.c != null) {
            this.c.a();
            cw.a("ImageCache", "Memory cache cleared", 111);
        }
        Object object = this.e;
        synchronized (object) {
            ep ep2 = this.b;
            if (ep2 != null) {
                try {
                    if (!this.b.a()) {
                        this.b.c();
                        this.b = null;
                        cw.a("ImageCache", "Disk cache closed", 111);
                    }
                }
                catch (IOException var2_3) {
                    cy.a(var2_3, "ImageCache", "close");
                    cw.a("ImageCache", "close - " + (Object)((Object)var2_3), 112);
                }
            }
            return;
        }
    }

    public static class a {
        private int a = 5120;
        private int b = 10485760;
        private File c;
        private Bitmap.CompressFormat d = cs.f();
        private int e = 100;
        private boolean f = true;
        private boolean g = true;
        private boolean h = false;

        public a(Context context, String string2) {
            this.c = cs.a(context, string2);
        }

        static /* synthetic */ Bitmap.CompressFormat g(a a2) {
            return a2.d;
        }

        static /* synthetic */ int h(a a2) {
            return a2.e;
        }

        public void a(int n2) {
            this.a = n2;
        }

        public void a(String string2) {
            this.c = new File(string2);
        }

        public void a(boolean bl2) {
            this.f = bl2;
        }

        public void b(int n2) {
            this.b = n2;
        }

        public void b(boolean bl2) {
            this.g = bl2;
        }
    }

}

