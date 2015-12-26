/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.net.URI
 *  org.apache.http.Header
 *  org.apache.http.HttpResponse
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.impl.client.AbstractHttpClient
 *  org.apache.http.protocol.HttpContext
 */
import android.util.Log;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

public class asi
implements Runnable {
    private final AbstractHttpClient a;
    private final HttpContext b;
    private final HttpUriRequest c;
    private final aut d;
    private int e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;

    public asi(AbstractHttpClient abstractHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, aut aut2) {
        this.a = abstractHttpClient;
        this.b = httpContext;
        this.c = httpUriRequest;
        this.d = aut2;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void c() {
        if (this.a()) {
            return;
        }
        if (this.c.getURI().getScheme() == null) {
            throw new MalformedURLException("No valid URI scheme was provided");
        }
        if (this.d instanceof aur) {
            ((aur)this.d).a(this.c);
        }
        HttpResponse httpResponse = this.a.execute(this.c, this.b);
        if (this.a()) return;
        if (this.d == null) return;
        this.d.a(this.d, httpResponse);
        if (this.a()) return;
        this.d.a(httpResponse);
        if (this.a()) return;
        this.d.b(this.d, httpResponse);
    }

    /*
     * Exception decompiling
     */
    private void d() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 5[CATCHBLOCK]
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

    private void e() {
        synchronized (this) {
            if (!this.h && this.f && !this.g) {
                this.g = true;
                if (this.d != null) {
                    this.d.c();
                }
            }
            return;
        }
    }

    public void a(asi asi2) {
    }

    public boolean a() {
        if (this.f) {
            this.e();
        }
        return this.f;
    }

    public void b(asi asi2) {
    }

    public boolean b() {
        if (!this.a() && !this.h) {
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @Override
    public void run() {
        if (this.a()) {
            return;
        }
        if (!this.i) {
            this.i = true;
            this.a(this);
        }
        if (this.a()) return;
        if (this.d != null) {
            this.d.a();
        }
        if (this.a()) return;
        try {
            this.d();
        }
        catch (IOException var1_1) {
            if (!this.a() && this.d != null) {
                this.d.a(0, null, null, var1_1);
            }
            Log.e((String)"AsyncHttpRequest", (String)"makeRequestWithRetries returned error, but handler is null", (Throwable)var1_1);
        }
        if (this.a()) return;
        if (this.d != null) {
            this.d.b();
        }
        if (this.a()) return;
        this.b(this);
        this.h = true;
    }
}

