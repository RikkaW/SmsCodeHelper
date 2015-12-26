/*
 * Decompiled with CFR 0_110.
 */
package com.xiaomi.mms.mx.fw.misc;

import com.xiaomi.mms.mx.fw.misc.SerializedAsyncTaskProcessor;

public class XmppHandlerThread
extends SerializedAsyncTaskProcessor {
    private static XmppHandlerThread sInstance;

    private XmppHandlerThread() {
    }

    public static XmppHandlerThread getInstance() {
        synchronized (XmppHandlerThread.class) {
            if (sInstance == null) {
                sInstance = new XmppHandlerThread();
            }
            XmppHandlerThread xmppHandlerThread = sInstance;
            return xmppHandlerThread;
        }
    }
}

