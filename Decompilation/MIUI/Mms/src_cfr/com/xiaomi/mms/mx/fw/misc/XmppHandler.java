/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.xiaomi.mms.mx.fw.misc;

import com.xiaomi.mms.mx.fw.misc.SerializedAsyncTaskProcessor;
import com.xiaomi.mms.mx.fw.misc.XmppHandlerThread;

public abstract class XmppHandler {
    protected final SerializedAsyncTaskProcessor mTaskProcessor = XmppHandlerThread.getInstance();

    protected XmppHandler() {
    }
}

