/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package com.xiaomi.mms.mx.fw;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mms.mx.fw.MipubMessageUtils;
import com.xiaomi.mms.mx.fw.fdata.MessageData;
import com.xiaomi.mms.mx.fw.futils.ISO8601DateParser;
import com.xiaomi.mms.mx.utils.Log;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.Message;
import java.text.ParseException;
import java.util.Collection;
import java.util.Iterator;

public class HmsXMPPProcessor {
    private static HmsXMPPProcessor sInstance = null;
    private Context mContext;

    private HmsXMPPProcessor(Context context) {
        this.mContext = context;
    }

    public static HmsXMPPProcessor getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new HmsXMPPProcessor(context.getApplicationContext());
        }
        return sInstance;
    }

    private MessageData handleHmsMessage(Message message) {
        long l;
        String string2 = message.getFrom();
        String string3 = message.getTo();
        String string4 = message.getSeq();
        String string5 = message.getBody();
        String string6 = message.getPacketID();
        String string7 = "";
        boolean bl = MipubMessageUtils.isTransient(message);
        int n = message.getFrom().lastIndexOf("/");
        if (n >= 0) {
            string7 = message.getFrom().substring(n + 1);
        }
        long l2 = l = this.getOfflineMessageSentTime(message);
        if (l < 0) {
            l2 = System.currentTimeMillis();
        }
        CommonPacketExtension commonPacketExtension = MipubMessageUtils.getMsgExtension(message);
        boolean bl2 = MipubMessageUtils.isGlobal(message);
        boolean bl3 = MipubMessageUtils.isTemplate(message);
        return new MessageData(string6, 0, l2, System.currentTimeMillis(), string2, message.getType(), commonPacketExtension.getText(), string4, null, string7, string3, string5, bl, bl2, bl3);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected long getOfflineMessageSentTime(Message object) {
        long l = -1;
        object = object.getExtensions();
        long l2 = l;
        if (object == null) return l2;
        Object object2 = object.iterator();
        do {
            l2 = l;
            if (!object2.hasNext()) return l2;
        } while (!(object = object2.next()).getElementName().equalsIgnoreCase("delay"));
        object2 = object.getAttributeValue("ts");
        if (!TextUtils.isEmpty((CharSequence)object2)) {
            try {
                return Long.parseLong((String)object2);
            }
            catch (NumberFormatException var1_2) {
                Log.e("HmsXMPPProcessor", "parse time stmap error : " + var1_2.toString());
                return -1;
            }
        }
        object = object.getAttributeValue("stamp");
        l2 = l;
        if (object == null) return l2;
        try {
            return ISO8601DateParser.parse((String)object).getTime();
        }
        catch (ParseException var6_6) {
            Log.e("HmsXMPPProcessor", String.format((String)"The timestamp (%s)cannot be converted to a propertime", (Object[])new Object[]{object}));
            return -1;
        }
    }

    public MessageData processHmsMessage(Message message) {
        MessageData messageData;
        MessageData messageData2 = messageData = null;
        if (message != null) {
            CommonPacketExtension commonPacketExtension = message.getExtension("ext");
            messageData2 = messageData;
            if (commonPacketExtension != null) {
                messageData2 = messageData;
                if ("subscribe".equals((Object)commonPacketExtension.getAttributeValue("type"))) {
                    messageData2 = this.handleHmsMessage(message);
                }
            }
        }
        return messageData2;
    }
}

