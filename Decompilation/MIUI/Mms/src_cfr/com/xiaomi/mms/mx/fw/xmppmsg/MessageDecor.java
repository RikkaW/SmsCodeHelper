/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.mms.mx.fw.xmppmsg;

import android.text.TextUtils;
import com.xiaomi.mms.mx.fw.HmsPersister;
import com.xiaomi.mms.mx.utils.Log;
import com.xiaomi.push.service.ServiceClient;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.Message;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class MessageDecor
extends Message {
    private CommonPacketExtension mMetadataExtension = null;

    protected void buildMessage(String string2, String string3, String string4) {
        this.buildeMessage(string2, string3, string4, "8", null, false, false, null, null);
    }

    protected void buildeMessage(String iterator, String string2, String string3, String string4, String string5, boolean bl, boolean bl2, String string6, String string7) {
        this.setFrom((String)((Object)iterator));
        this.setTo(string2);
        this.setPacketID(string3);
        this.setChannelId(string4);
        this.setAppId(string5);
        this.setIsTransient(bl);
        this.setEncrypted(bl2);
        this.setThread(string6);
        this.setSubject(string7);
        this.setTypeAttribute();
        this.setBodyElement();
        iterator = this.createCommonPacketExtensions();
        if (iterator != null) {
            iterator = iterator.iterator();
            while (iterator.hasNext()) {
                this.addExtension((CommonPacketExtension)iterator.next());
            }
        }
    }

    public abstract List<CommonPacketExtension> createCommonPacketExtensions();

    /*
     * Enabled aggressive block sorting
     */
    public final void sendMessage(boolean bl) {
        List list = (List)this.getExtensions();
        if (TextUtils.isEmpty((CharSequence)this.getBody()) && (list == null || list.isEmpty())) {
            return;
        }
        ServiceClient.getInstance(HmsPersister.getContext()).sendMessage(this, true);
        if (list.size() > 0) {
            Log.w("MessageDecor", "sending a Xmpp message to" + this.getTo() + " id=" + this.getPacketID() + " ext=" + ((CommonPacketExtension)list.get(0)).getElementName());
        } else {
            Log.w("MessageDecor", "sending a Xmpp message to " + this.getTo() + " id=" + this.getPacketID());
        }
        Log.d("MessageDecor", this.toXML());
    }

    public abstract void setBodyElement();

    public abstract void setTypeAttribute();
}

