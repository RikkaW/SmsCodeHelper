/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.xiaomi.mms.mx.fw;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mms.mx.fw.faccount.JIDUtils;
import com.xiaomi.mms.mx.fw.misc.SerializedAsyncTaskProcessor;
import com.xiaomi.mms.mx.fw.misc.XmppHandler;
import com.xiaomi.mms.mx.utils.Log;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.IQ;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.XMPPError;
import java.util.ArrayList;

public class XmppIQHandler
extends XmppHandler {
    private static XmppIQHandler sUniqueInstance;
    private Context mContext;

    private XmppIQHandler(Context context) {
        this.mContext = context;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static XmppIQHandler getInstance(Context context) {
        if (sUniqueInstance == null) {
            synchronized (XmppIQHandler.class) {
                if (sUniqueInstance == null) {
                    sUniqueInstance = new XmppIQHandler(context);
                }
            }
        }
        return sUniqueInstance;
    }

    private boolean isConflictIQ(IQ iQ) {
        if (iQ.getExtension("conflict", "xm") != null) {
            return true;
        }
        return false;
    }

    private boolean isMisMatchIQ(IQ iQ) {
        if (iQ.getExtension("mismatch", "xm") != null) {
            return true;
        }
        return false;
    }

    private void processHmsErrorIQ(IQ object) {
        Object object2;
        Object object3;
        if (object != null && (object2 = object.getExtension("vipmsg", "xm:vip")) != null && (object2 = object2.getChildByName("vip")) != null && !TextUtils.isEmpty((CharSequence)(object2 = object2.getAttributeValue("id"))) && (object3 = object.getError()) != null) {
            object = object3.getType().toString();
            object3 = object3.getReason();
            if ("cancel".equalsIgnoreCase((String)object) && "not-acceptable".equalsIgnoreCase((String)object3)) {
                object = JIDUtils.getFullSmtpName((String)object2);
                new ArrayList().add(object);
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void processHmsSetIQ(IQ iQ) {
        if (iQ == null) return;
        {
            Log.d("XmppIQHanlder", "process hms set iq : " + iQ.toString());
            boolean bl = this.isMisMatchIQ(iQ);
            boolean bl2 = this.isConflictIQ(iQ);
            if (bl) {
                Log.d("XmppIQHanlder", "received mis-match message, should not happen, do nothing.");
                return;
            } else {
                if (!bl2) return;
                {
                    Log.d("XmppIQHanlder", "received conflict message, should not happen, do nothing.");
                    return;
                }
            }
        }
    }

    public boolean handle(Packet packet) {
        if (packet instanceof IQ && (packet = (IQ)packet) != null) {
            this.mTaskProcessor.addNewTask(new ProcessIQTask((IQ)packet));
        }
        return false;
    }

    private class ProcessIQTask
    extends SerializedAsyncTaskProcessor.SerializedAsyncTask {
        private IQ mIQ;

        private ProcessIQTask(IQ iQ) {
            this.mIQ = iQ;
        }

        @Override
        public void process() {
            if (this.mIQ.getType() == IQ.Type.SET) {
                XmppIQHandler.this.processHmsSetIQ(this.mIQ);
                return;
            }
            if (this.mIQ.getType() == IQ.Type.ERROR) {
                XmppIQHandler.this.processHmsErrorIQ(this.mIQ);
                return;
            }
            if (this.mIQ.getType() == IQ.Type.GET) {
                Log.e("XmppIQHanlder", "\u4e0d\u5e94\u8be5\u51fa\u73b0\u6536\u5230Get iq:" + this.mIQ.toXML());
                return;
            }
            Log.e("XmppIQHanlder", "unkown iq:" + this.mIQ.toXML());
        }
    }

}

