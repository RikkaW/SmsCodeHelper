/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.IntentService
 *  android.content.Context
 *  android.content.Intent
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.xiaomi.mipush.sdk;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PushMessageHandler
extends IntentService {
    private static List<MiPushClient.MiPushClientCallback> sCallbacks = new ArrayList();

    public PushMessageHandler() {
        super("mipush message handler");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected static void addPushCallbackClass(MiPushClient.MiPushClientCallback miPushClientCallback) {
        List<MiPushClient.MiPushClientCallback> list = sCallbacks;
        synchronized (list) {
            if (!sCallbacks.contains(miPushClientCallback)) {
                sCallbacks.add(miPushClientCallback);
            }
            return;
        }
    }

    public static boolean isCallbackEmpty() {
        return sCallbacks.isEmpty();
    }

    protected static boolean isCategoryMatch(String string2, String string3) {
        if (TextUtils.isEmpty((CharSequence)string2) && TextUtils.isEmpty((CharSequence)string3) || TextUtils.equals((CharSequence)string2, (CharSequence)string3)) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected static void onCommandResult(Context object, String string2, String string3, long l, String string4, List<String> list) {
        object = sCallbacks;
        synchronized (object) {
            Iterator<MiPushClient.MiPushClientCallback> iterator = sCallbacks.iterator();
            while (iterator.hasNext()) {
                MiPushClient.MiPushClientCallback miPushClientCallback = iterator.next();
                if (!PushMessageHandler.isCategoryMatch(string2, miPushClientCallback.getCategory())) continue;
                miPushClientCallback.onCommandResult(string3, l, string4, list);
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void onInitializeResult(long l, String string2, String string3) {
        List<MiPushClient.MiPushClientCallback> list = sCallbacks;
        synchronized (list) {
            Iterator<MiPushClient.MiPushClientCallback> iterator = sCallbacks.iterator();
            while (iterator.hasNext()) {
                iterator.next().onInitializeResult(l, string2, string3);
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void onReceiveMessage(Context object, MiPushMessage miPushMessage) {
        object = sCallbacks;
        synchronized (object) {
            Iterator<MiPushClient.MiPushClientCallback> iterator = sCallbacks.iterator();
            while (iterator.hasNext()) {
                MiPushClient.MiPushClientCallback miPushClientCallback = iterator.next();
                if (!PushMessageHandler.isCategoryMatch(miPushMessage.getCategory(), miPushClientCallback.getCategory())) continue;
                miPushClientCallback.onReceiveMessage(miPushMessage.getContent(), miPushMessage.getAlias(), miPushMessage.getTopic(), miPushMessage.isNotified());
                miPushClientCallback.onReceiveMessage(miPushMessage);
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected static void onSubscribeResult(Context object, String string2, long l, String string3, String string4) {
        object = sCallbacks;
        synchronized (object) {
            Iterator<MiPushClient.MiPushClientCallback> iterator = sCallbacks.iterator();
            while (iterator.hasNext()) {
                MiPushClient.MiPushClientCallback miPushClientCallback = iterator.next();
                if (!PushMessageHandler.isCategoryMatch(string2, miPushClientCallback.getCategory())) continue;
                miPushClientCallback.onSubscribeResult(l, string3, string4);
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected static void onUnsubscribeResult(Context object, String string2, long l, String string3, String string4) {
        object = sCallbacks;
        synchronized (object) {
            Iterator<MiPushClient.MiPushClientCallback> iterator = sCallbacks.iterator();
            while (iterator.hasNext()) {
                MiPushClient.MiPushClientCallback miPushClientCallback = iterator.next();
                if (!PushMessageHandler.isCategoryMatch(string2, miPushClientCallback.getCategory())) continue;
                miPushClientCallback.onUnsubscribeResult(l, string3, string4);
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void processMessageForCallback(Context object, PushMessageInterface list) {
        MiPushCommandMessage miPushCommandMessage;
        Object var2_2 = null;
        if (list instanceof MiPushMessage) {
            PushMessageHandler.onReceiveMessage((Context)object, (MiPushMessage)((Object)list));
            return;
        } else {
            if (!(list instanceof MiPushCommandMessage)) return;
            {
                miPushCommandMessage = (MiPushCommandMessage)((Object)list);
                list = miPushCommandMessage.getCommand();
                if ("register".equals((Object)list)) {
                    list = miPushCommandMessage.getCommandArguments();
                    object = var2_2;
                    if (list != null) {
                        object = var2_2;
                        if (!list.isEmpty()) {
                            object = list.get(0);
                        }
                    }
                    PushMessageHandler.onInitializeResult(miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), (String)object);
                    return;
                }
                if ("set-alias".equals(list) || "unset-alias".equals(list) || "accept-time".equals(list)) {
                    PushMessageHandler.onCommandResult((Context)object, miPushCommandMessage.getCategory(), list, miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), miPushCommandMessage.getCommandArguments());
                    return;
                }
                if ("subscribe-topic".equals(list)) {
                    list = miPushCommandMessage.getCommandArguments();
                    list = list != null && !list.isEmpty() ? list.get(0) : null;
                    PushMessageHandler.onSubscribeResult((Context)object, miPushCommandMessage.getCategory(), miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), (String)((Object)list));
                    return;
                }
                if (!"unsubscibe-topic".equals(list)) return;
                {
                    list = miPushCommandMessage.getCommandArguments();
                    list = list != null && !list.isEmpty() ? (String)list.get(0) : null;
                }
            }
        }
        PushMessageHandler.onUnsubscribeResult((Context)object, miPushCommandMessage.getCategory(), miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), (String)((Object)list));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected static void removeAllPushCallbackClass() {
        List<MiPushClient.MiPushClientCallback> list = sCallbacks;
        synchronized (list) {
            sCallbacks.clear();
            return;
        }
    }

    /*
     * Exception decompiling
     */
    protected void onHandleIntent(Intent var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [] lbl13 : TryStatement: try { 1[TRYBLOCK]

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

    static interface PushMessageInterface
    extends Serializable {
    }

}

