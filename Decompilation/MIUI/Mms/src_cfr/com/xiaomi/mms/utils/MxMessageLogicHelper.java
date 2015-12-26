/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  android.util.Base64
 *  com.google.android.collect.Lists
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.Random
 *  java.util.concurrent.atomic.AtomicLong
 *  miui.push.CommonPacketExtension
 *  miui.push.Message
 *  miui.push.ServiceClient
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.xiaomi.mms.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.google.android.collect.Lists;
import com.xiaomi.mms.utils.logger.MyLog;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import miui.push.CommonPacketExtension;
import miui.push.Message;
import miui.push.ServiceClient;
import org.json.JSONException;
import org.json.JSONObject;

public class MxMessageLogicHelper {
    private static final AtomicLong B2C_SMS_ID;
    private static final AtomicLong MMS_ID;
    private static final AtomicLong SMS_ID;

    static {
        SMS_ID = new AtomicLong();
        B2C_SMS_ID = new AtomicLong();
        MMS_ID = new AtomicLong();
        long l = (long)new Random(System.nanoTime()).nextInt(65535) | (System.currentTimeMillis() & 0xFFFFFFFFFFFL) << 16;
        SMS_ID.set(0 | l);
        B2C_SMS_ID.set(0x2000000000000000L | l);
        MMS_ID.set(0x4000000000000000L | l);
    }

    public static String base64Decode(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            MyLog.d("MxMessageLogicHelper", "base64Decode pdu is empty");
            return string2;
        }
        if ((string2 = (String)Base64.decode((String)string2, (int)0)) == null) {
            MyLog.e("MxMessageLogicHelper", "error for decode to string");
            return null;
        }
        return new String((byte[])string2);
    }

    public static String base64Encode(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            MyLog.d("MxMessageLogicHelper", "base64Encode pdu is empty");
            return string2;
        }
        if (TextUtils.isEmpty((CharSequence)(string2 = Base64.encodeToString((byte[])string2.getBytes(), (int)2)))) {
            MyLog.e("MxMessageLogicHelper", "error for encode to string");
        }
        return string2;
    }

    public static CommonPacketExtension constructB2cNumbers(String string2, String string3) {
        CommonPacketExtension commonPacketExtension = new CommonPacketExtension("b2cNumber", null, "", "");
        if (!TextUtils.equals((CharSequence)string2, (CharSequence)string3)) {
            commonPacketExtension.setText(string2);
        }
        return commonPacketExtension;
    }

    public static String constructFullRecipientId(String string2) {
        if (string2.contains((CharSequence)"@")) {
            return string2;
        }
        return string2 + "@xiaomi.com";
    }

    public static CommonPacketExtension constructMmsAttachment(String string2, String string3, long l, long l2) {
        return new CommonPacketExtension("attachment", null, (List)Lists.newArrayList((Object[])new String[]{"shareId", "mimeType", "expireAt", "msgSize"}), (List)Lists.newArrayList((Object[])new String[]{string2, string3, String.valueOf((long)l), String.valueOf((long)l2)}));
    }

    public static String constructMmsBody(String string2, long l) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", (Object)"mms");
            jSONObject.put("msgId", l);
            jSONObject.put("sentTime", System.currentTimeMillis());
            jSONObject.put("subject", (Object)string2);
        }
        catch (JSONException var0_1) {
            throw new IllegalStateException("error when construct mms", (Throwable)var0_1);
        }
        return jSONObject.toString();
    }

    public static CommonPacketExtension constructMxAttachment(String string2, String string3, long l, long l2, String string4) {
        return new CommonPacketExtension("attachment", null, (List)Lists.newArrayList((Object[])new String[]{"shareId", "mimeType", "expireAt", "msgSize", "mxExtension"}), (List)Lists.newArrayList((Object[])new String[]{string2, string3, String.valueOf((long)l), String.valueOf((long)l2), string4}));
    }

    public static String constructMxBody(String string2, long l, String string3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", (Object)"mx2");
            jSONObject.put("msgId", l);
            jSONObject.put("sentTime", System.currentTimeMillis());
            jSONObject.put("subject", (Object)string2);
            jSONObject.put("mxType", (Object)string3);
        }
        catch (JSONException var0_1) {
            throw new IllegalStateException("error when construct mx", (Throwable)var0_1);
        }
        return jSONObject.toString();
    }

    public static String constructSmsBody(String string2, long l) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", (Object)"sms");
            jSONObject.put("pdu", (Object)string2);
            jSONObject.put("msgId", l);
            jSONObject.put("sentTime", System.currentTimeMillis());
        }
        catch (JSONException var0_1) {
            throw new IllegalStateException("error when construct sms", (Throwable)var0_1);
        }
        return jSONObject.toString();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String getSimpleMid(String string2) {
        if (string2 == null) {
            return null;
        }
        int n = string2.indexOf("@");
        String string3 = string2;
        if (n <= 0) return string3;
        return string2.substring(0, n);
    }

    public static boolean isB2cSms(long l) {
        if ((-1152921504606846976L & l) == 0x2000000000000000L) {
            return true;
        }
        return false;
    }

    public static boolean isMms(long l) {
        if ((-1152921504606846976L & l) == 0x4000000000000000L) {
            return true;
        }
        return false;
    }

    public static boolean isSms(long l) {
        if ((-1152921504606846976L & l) == 0 || MxMessageLogicHelper.isB2cSms(l)) {
            return true;
        }
        return false;
    }

    public static Long nextMiId(boolean bl) {
        return MxMessageLogicHelper.nextMiId(false, bl);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Long nextMiId(boolean bl, boolean bl2) {
        long l;
        if (bl) {
            l = MMS_ID.getAndIncrement();
            do {
                return l;
                break;
            } while (true);
        }
        if (bl2) {
            l = B2C_SMS_ID.getAndIncrement();
            return l;
        }
        l = SMS_ID.getAndIncrement();
        return l;
    }

    public static Message obtainMessage(String string2, String string3) {
        string3 = MxMessageLogicHelper.constructFullRecipientId(string3);
        Message message = new Message();
        message.setFrom(string2);
        message.setChannelId("3");
        message.setTo(string3);
        return message;
    }

    public static boolean sendAckToServer(Context context, String string2, String string3, String string4) {
        string2 = MxMessageLogicHelper.obtainMessage(string2, string3);
        string2.addExtension(new CommonPacketExtension("sent", null, "id", string4));
        return ServiceClient.getInstance((Context)context).sendMessage((Message)string2);
    }

    public static boolean sendDeleteCommandToServer(Context context, String string2, String string3, String string4) {
        string2 = MxMessageLogicHelper.obtainMessage(string2, string3);
        string3 = new CommonPacketExtension("deleted", "xm", "id", string4);
        string2.setType("chat");
        string2.addExtension((CommonPacketExtension)string3);
        return ServiceClient.getInstance((Context)context).sendMessage((Message)string2);
    }

    public static boolean sendReceivedAck(Context context, String string2, String string3, String string4) {
        return MxMessageLogicHelper.sendReceivedAck(context, string2, string3, string4, null, null);
    }

    public static boolean sendReceivedAck(Context context, String string2, String string3, String string4, String string5, String string6) {
        string2 = MxMessageLogicHelper.obtainMessage(string2, string3);
        string2.addExtension(new CommonPacketExtension("received", null, "id", string4));
        if (string5 != null) {
            string3 = new CommonPacketExtension("error", null, new String[]{"type", "reason"}, new String[]{"client", string5});
            string3.setText(string6);
            string2.addExtension((CommonPacketExtension)string3);
        }
        return ServiceClient.getInstance((Context)context).sendMessage((Message)string2);
    }
}

