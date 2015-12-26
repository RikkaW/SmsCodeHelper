/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.Message
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayDeque
 *  java.util.concurrent.Executors
 *  java.util.concurrent.FutureTask
 *  java.util.concurrent.LinkedBlockingQueue
 *  java.util.concurrent.ThreadPoolExecutor
 *  java.util.concurrent.TimeUnit
 *  java.util.concurrent.atomic.AtomicBoolean
 */
package com.amap.api.mapcore2d;

import android.os.Handler;
import android.os.Message;
import com.amap.api.mapcore2d.cq$1;
import com.amap.api.mapcore2d.cq$2;
import com.amap.api.mapcore2d.cq$c$1;
import com.amap.api.mapcore2d.cr;
import com.amap.api.mapcore2d.cy;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class cq<Params, Progress, Result> {
    private static final ThreadFactory a;
    static final Executor b;
    private static final BlockingQueue<Runnable> c;
    private static final Executor d;
    private static final Executor e;
    private static final b f;
    private static volatile Executor g;
    private final e<Params, Result> h;
    private final FutureTask<Result> i;
    private volatile d j = d.a;
    private final AtomicBoolean k = new AtomicBoolean();
    private final AtomicBoolean l = new AtomicBoolean();

    /*
     * Enabled aggressive block sorting
     */
    static {
        void var0_1;
        a = new cr();
        c = new LinkedBlockingQueue(10);
        d = new ThreadPoolExecutor(5, 128, 1, TimeUnit.SECONDS, c, a, (RejectedExecutionHandler)new ThreadPoolExecutor.DiscardOldestPolicy());
        if (cy.c()) {
            c c2 = new c(null);
        } else {
            ExecutorService executorService = Executors.newSingleThreadExecutor((ThreadFactory)a);
        }
        e = var0_1;
        b = Executors.newFixedThreadPool((int)2, (ThreadFactory)a);
        f = new b(null);
        g = e;
    }

    public cq() {
        this.h = new cq$1(this);
        this.i = new cq$2(this, this.h);
    }

    static /* synthetic */ Object a(cq cq2, Object object) {
        return cq2.d(object);
    }

    static /* synthetic */ AtomicBoolean a(cq cq2) {
        return cq2.l;
    }

    static /* synthetic */ FutureTask b(cq cq2) {
        return cq2.i;
    }

    static /* synthetic */ void b(cq cq2, Object object) {
        cq2.c((Result)object);
    }

    static /* synthetic */ void c(cq cq2, Object object) {
        cq2.e(object);
    }

    private void c(Result Result) {
        if (!this.l.get()) {
            this.d(Result);
        }
    }

    private Result d(Result Result) {
        f.obtainMessage(1, new a<Object>(this, Result)).sendToTarget();
        return Result;
    }

    private void e() {
    }

    /*
     * Enabled aggressive block sorting
     */
    private void e(Result Result) {
        if (this.c()) {
            this.b(Result);
        } else {
            this.a(Result);
        }
        this.j = d.c;
    }

    public final d a() {
        return this.j;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public final /* varargs */ cq<Params, Progress, Result> a(Executor var1_1, Params ... var2_2) {
        if (this.j == d.a) ** GOTO lbl-1000
        switch (.a[this.j.ordinal()]) {
            default: lbl-1000: // 2 sources:
            {
                this.j = d.b;
                this.b();
                this.h.b = var2_2;
                var1_1.execute((Runnable)this.i);
                return this;
            }
            case 1: {
                throw new IllegalStateException("Cannot execute task: the task is already running.");
            }
            case 2: 
        }
        throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
    }

    protected /* varargs */ abstract Result a(Params ... var1);

    protected void a(Result Result) {
    }

    public final boolean a(boolean bl2) {
        this.k.set(true);
        return this.i.cancel(bl2);
    }

    protected void b() {
    }

    protected void b(Result Result) {
        this.e();
    }

    protected /* varargs */ void b(Progress ... arrProgress) {
    }

    public final /* varargs */ cq<Params, Progress, Result> c(Params ... arrParams) {
        return this.a(g, arrParams);
    }

    public final boolean c() {
        return this.k.get();
    }

    static class a<Data> {
        final cq a;
        final Data[] b;

        /* varargs */ a(cq cq2, Data ... arrData) {
            this.a = cq2;
            this.b = arrData;
        }
    }

    static class b
    extends Handler {
        private b() {
        }

        /* synthetic */ b(cr cr2) {
            this();
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

    static class c
    implements Executor {
        final ArrayDeque<Runnable> a = new ArrayDeque();
        Runnable b;

        private c() {
        }

        /* synthetic */ c(cr cr2) {
            this();
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        protected void a() {
            synchronized (this) {
                Runnable runnable;
                this.b = runnable = (Runnable)this.a.poll();
                if (runnable != null) {
                    d.execute(this.b);
                }
                return;
            }
        }

        @Override
        public void execute(Runnable runnable) {
            synchronized (this) {
                this.a.offer((Object)new cq$c$1(this, runnable));
                if (this.b == null) {
                    this.a();
                }
                return;
            }
        }
    }

    public static enum d {
        a,
        b,
        c;
        

        private d() {
        }
    }

    static abstract class e<Params, Result>
    implements Callable<Result> {
        Params[] b;

        private e() {
        }

        /* synthetic */ e(cr cr2) {
            this();
        }
    }

}

