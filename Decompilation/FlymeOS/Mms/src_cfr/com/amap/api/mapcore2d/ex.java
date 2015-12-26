/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.Looper
 *  android.os.Message
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.net.InetSocketAddress
 *  java.net.Proxy
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpHost
 */
package com.amap.api.mapcore2d;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.mapcore2d.cz;
import com.amap.api.mapcore2d.ea;
import com.amap.api.mapcore2d.ed;
import com.amap.api.mapcore2d.ev;
import com.amap.api.mapcore2d.ex$1;
import com.amap.api.mapcore2d.ey;
import com.amap.api.mapcore2d.ez;
import com.amap.api.mapcore2d.fa;
import com.amap.api.mapcore2d.fc;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;

public class ex {
    private static ex a;
    private ea b;
    private Handler c;

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private ex(boolean var1_1, int var2_2) {
        super();
        if (!var1_1) ** GOTO lbl5
        try {
            this.b = ea.a(var2_2);
lbl5: // 2 sources:
            if (Looper.myLooper() == null) {
                this.c = new a(Looper.getMainLooper(), null);
                return;
            }
            this.c = new a();
            return;
        }
        catch (Throwable var3_3) {
            ed.a(var3_3, "NetManger", "NetManger1");
            var3_3.printStackTrace();
            return;
        }
    }

    public static ex a() {
        return ex.a(true, 5);
    }

    public static ex a(boolean bl2) {
        return ex.a(bl2, 5);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static ex a(boolean bl2, int n2) {
        synchronized (ex.class) {
            try {
                if (a == null) {
                    a = new ex(bl2, n2);
                    return a;
                } else {
                    if (!bl2) return a;
                    if (ex.a.b != null) return a;
                    ex.a.b = ea.a(n2);
                }
                return a;
            }
            catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return a;
        }
    }

    static /* synthetic */ fa a(ex ex2, ey ey2, boolean bl2) {
        return ex2.b(ey2, bl2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private fa a(ey object, boolean bl2) {
        HttpEntity httpEntity = object.e();
        byte[] arrby = object.a_();
        try {
            this.c((ey)object);
            ev ev2 = object.d == null ? null : new Object(Proxy.Type.HTTP, (SocketAddress)InetSocketAddress.createUnresolved((String)object.d.getHostName(), (int)object.d.getPort()));
            ev2 = new ev(object.b, object.c, (Proxy)ev2, bl2);
            if (httpEntity == null && arrby == null) {
                return ev2.b(object.d(), object.b(), object.c());
            }
            if (arrby == null) return ev2.a(object.d(), object.b(), object.c(), httpEntity);
            return ev2.a(object.d(), object.b(), object.c(), arrby);
        }
        catch (cz var1_2) {
            throw var1_2;
        }
        catch (Throwable var1_3) {
            var1_3.printStackTrace();
            throw new cz("\u672a\u77e5\u7684\u9519\u8bef");
        }
    }

    private void a(cz cz2, ez ez2) {
        fc fc2 = new fc();
        fc2.a = cz2;
        fc2.b = ez2;
        cz2 = Message.obtain();
        cz2.obj = fc2;
        cz2.what = 1;
        this.c.sendMessage((Message)cz2);
    }

    static /* synthetic */ void a(ex ex2, cz cz2, ez ez2) {
        ex2.a(cz2, ez2);
    }

    static /* synthetic */ void a(ex ex2, fa fa2, ez ez2) {
        ex2.a(fa2, ez2);
    }

    private void a(fa object, ez ez2) {
        ez2.a(object.b, object.a);
        object = new fc();
        object.b = ez2;
        ez2 = Message.obtain();
        ez2.obj = object;
        ez2.what = 0;
        this.c.sendMessage((Message)ez2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private fa b(ey ey2, boolean bl2) {
        try {
            Proxy proxy;
            this.c(ey2);
            if (ey2.d == null) {
                proxy = null;
                return new ev(ey2.b, ey2.c, proxy, bl2).a(ey2.d(), ey2.b(), ey2.c());
            }
            proxy = new Proxy(Proxy.Type.HTTP, (SocketAddress)InetSocketAddress.createUnresolved((String)ey2.d.getHostName(), (int)ey2.d.getPort()));
            return new ev(ey2.b, ey2.c, proxy, bl2).a(ey2.d(), ey2.b(), ey2.c());
        }
        catch (cz var1_2) {
            throw var1_2;
        }
        catch (Throwable var1_3) {
            var1_3.printStackTrace();
            throw new cz("\u672a\u77e5\u7684\u9519\u8bef");
        }
    }

    private void c(ey ey2) {
        if (ey2 == null) {
            throw new cz("requeust is null");
        }
        if (ey2.d() == null || "".equals((Object)ey2.d())) {
            throw new cz("request url is empty");
        }
    }

    public byte[] a(ey object) {
        try {
            object = this.a((ey)object, false);
            if (object != null) {
                return object.a;
            }
        }
        catch (cz var1_2) {
            throw var1_2;
        }
        catch (Throwable var1_3) {
            throw new cz("\u672a\u77e5\u7684\u9519\u8bef");
        }
        return null;
    }

    public byte[] b(ey object) {
        try {
            object = this.b((ey)object, false);
            if (object != null) {
                return object.a;
            }
        }
        catch (cz var1_2) {
            throw var1_2;
        }
        catch (Throwable var1_3) {
            throw new cz("\u672a\u77e5\u7684\u9519\u8bef");
        }
        return null;
    }

    static class a
    extends Handler {
        public a() {
        }

        private a(Looper looper) {
            super(looper);
            Looper.prepare();
        }

        /* synthetic */ a(Looper looper, ex$1 ex$1) {
            this(looper);
        }

        /*
         * Exception decompiling
         */
        public void handleMessage(Message var1_1) {
            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
            // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 4[CASE]
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
            // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
            // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
            // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
            // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
            // org.benf.cfr.reader.Main.doJar(Main.java:128)
            // org.benf.cfr.reader.Main.main(Main.java:178)
            throw new IllegalStateException("Decompilation failed");
        }
    }

}

