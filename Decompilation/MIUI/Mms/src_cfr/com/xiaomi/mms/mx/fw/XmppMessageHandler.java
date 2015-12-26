/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.util.Vector
 */
package com.xiaomi.mms.mx.fw;

import com.xiaomi.mms.mx.fw.HmsPersister;
import com.xiaomi.mms.mx.fw.misc.SerializedAsyncTaskProcessor;
import com.xiaomi.mms.mx.fw.misc.XmppHandler;
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Packet;
import java.util.Collection;
import java.util.Vector;

public class XmppMessageHandler
extends XmppHandler {
    private static XmppMessageHandler sUniqueInstance;
    private volatile boolean mDelayed = false;
    private final Vector<Message> mPacketCache = new Vector();

    private XmppMessageHandler() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static XmppMessageHandler getInstance() {
        if (sUniqueInstance == null) {
            synchronized (XmppMessageHandler.class) {
                if (sUniqueInstance == null) {
                    sUniqueInstance = new XmppMessageHandler();
                }
            }
        }
        return sUniqueInstance;
    }

    public boolean handle(Packet packet) {
        if (packet instanceof Message) {
            packet = (Message)packet;
            this.mPacketCache.add((Object)packet);
            if (!this.mDelayed) {
                this.mDelayed = true;
                this.mTaskProcessor.addNewTaskWithDelayed(new SerializedAsyncTaskProcessor.SerializedAsyncTask(){

                    /*
                     * Enabled aggressive block sorting
                     * Enabled unnecessary exception pruning
                     * Enabled aggressive exception aggregation
                     */
                    @Override
                    public void process() {
                        Vector vector;
                        Vector vector2 = XmppMessageHandler.this.mPacketCache;
                        synchronized (vector2) {
                            XmppMessageHandler.this.mDelayed = false;
                            vector = new Vector((Collection)XmppMessageHandler.this.mPacketCache);
                            XmppMessageHandler.this.mPacketCache.clear();
                        }
                        HmsPersister.getInstance().process(vector);
                    }
                }, 200);
            }
            return true;
        }
        return false;
    }

}

