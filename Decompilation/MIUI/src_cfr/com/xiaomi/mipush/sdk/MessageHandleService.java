/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.IntentService
 *  android.content.Intent
 *  java.lang.Object
 *  java.lang.String
 *  java.util.concurrent.ConcurrentLinkedQueue
 */
package com.xiaomi.mipush.sdk;

import android.app.IntentService;
import android.content.Intent;
import com.xiaomi.mipush.sdk.PushMessageReceiver;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageHandleService
extends IntentService {
    private static ConcurrentLinkedQueue<MessageHandleJob> jobQueue = new ConcurrentLinkedQueue();

    public MessageHandleService() {
        super("MessageHandleThread");
    }

    public static void addJob(MessageHandleJob messageHandleJob) {
        if (messageHandleJob != null) {
            jobQueue.add((Object)messageHandleJob);
        }
    }

    /*
     * Exception decompiling
     */
    protected void onHandleIntent(Intent var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 5[CASE]
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

    public static class MessageHandleJob {
        private Intent intent;
        private PushMessageReceiver receiver;

        public MessageHandleJob(Intent intent, PushMessageReceiver pushMessageReceiver) {
            this.receiver = pushMessageReceiver;
            this.intent = intent;
        }

        public Intent getIntent() {
            return this.intent;
        }

        public PushMessageReceiver getReceiver() {
            return this.receiver;
        }
    }

}

