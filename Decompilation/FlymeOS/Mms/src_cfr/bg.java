/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Math
 *  java.lang.String
 *  java.util.zip.Inflater
 */
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Inflater;

public class bg
extends FilterInputStream {
    protected Inflater a;
    protected byte[] b;
    protected String c = null;
    protected int d;
    boolean e = false;
    private boolean f = false;
    private boolean g = false;
    private byte[] h = new byte[1];
    private byte[] i = new byte[512];

    public bg(InputStream inputStream, Inflater inflater, int n2) {
        super(inputStream);
        if (inputStream == null || inflater == null) {
            throw new NullPointerException();
        }
        if (n2 <= 0) {
            throw new IllegalArgumentException("buffer size <= 0");
        }
        this.a = inflater;
        this.b = new byte[n2];
    }

    private void b() {
        if (this.f) {
            throw new IOException("Stream closed");
        }
    }

    protected void a() {
        this.b();
        this.d = this.in.read(this.b, 0, this.b.length);
        if (this.d == -1) {
            throw new EOFException("Unexpected end of ZLIB input stream");
        }
        if (this.c != null) {
            byte[] arrby = bk.a(this.b, this.d);
            this.a.setInput(arrby, 0, arrby.length);
            return;
        }
        this.a.setInput(this.b, 0, this.d);
    }

    @Override
    public int available() {
        this.b();
        if (this.g) {
            return 0;
        }
        return 1;
    }

    @Override
    public void close() {
        if (!this.f) {
            if (this.e) {
                this.a.end();
            }
            this.in.close();
            this.f = true;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    @Override
    public void mark(int n2) {
        // MONITORENTER : this
        // MONITOREXIT : this
    }

    @Override
    public boolean markSupported() {
        return false;
    }

    @Override
    public int read() {
        this.b();
        if (this.read(this.h, 0, 1) == -1) {
            return -1;
        }
        return this.h[0] & 255;
    }

    /*
     * Exception decompiling
     */
    @Override
    public int read(byte[] var1_1, int var2_4, int var3_5) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:371)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:449)
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

    @Override
    public void reset() {
        synchronized (this) {
            throw new IOException("mark/reset not supported");
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public long skip(long l2) {
        if (l2 < 0) {
            throw new IllegalArgumentException("negative skip length");
        }
        this.b();
        int n2 = (int)Math.min((long)l2, (long)Integer.MAX_VALUE);
        int n3 = 0;
        do {
            int n4;
            if (n3 >= n2) {
                do {
                    return n3;
                    break;
                } while (true);
            }
            int n5 = n4 = n2 - n3;
            if (n4 > this.i.length) {
                n5 = this.i.length;
            }
            if ((n5 = this.read(this.i, 0, n5)) == -1) {
                this.g = true;
                return n3;
            }
            n3 = n5 + n3;
        } while (true);
    }
}

