/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.Intent
 *  android.database.Cursor
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.provider.Telephony
 *  android.provider.Telephony$MmsSms
 *  android.provider.Telephony$Threads
 *  android.text.TextUtils
 *  android.util.LruCache
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.net.URLEncoder
 *  java.util.HashMap
 *  java.util.Map$Entry
 *  java.util.Vector
 *  java.util.concurrent.ConcurrentLinkedQueue
 *  miui.provider.ExtraTelephony
 *  miui.provider.ExtraTelephony$Hms
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.xiaomi.mms.mx.fw;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.LruCache;
import com.xiaomi.mms.mx.fw.HmsChannelService;
import com.xiaomi.mms.mx.fw.HmsMessageLogicHelper;
import com.xiaomi.mms.mx.fw.HmsXMPPProcessor;
import com.xiaomi.mms.mx.fw.MipubMessageUtils;
import com.xiaomi.mms.mx.fw.fdata.HmsConversationInfo;
import com.xiaomi.mms.mx.fw.fdata.MessageData;
import com.xiaomi.mms.mx.fw.xmppmsg.HmsExtensionData;
import com.xiaomi.mms.mx.utils.Log;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.Message;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import miui.provider.ExtraTelephony;
import org.json.JSONException;
import org.json.JSONObject;

public class HmsPersister {
    private static Context mAppContext = null;
    private static Object mLock;
    private static long sCurrentMessageId;
    private static HmsPersister sInstance;
    private ConcurrentLinkedQueue<AckData> mAckDataQueue = null;
    private ConcurrentLinkedQueue<Message> mMessageReceivedLinkedQueue;

    static {
        sCurrentMessageId = -1;
        mLock = new Object();
    }

    private HmsPersister(Context context) {
        mAppContext = context;
        this.mAckDataQueue = new ConcurrentLinkedQueue();
        this.mMessageReceivedLinkedQueue = new ConcurrentLinkedQueue();
    }

    private static ContentValues convertToContentValues(Context context, MessageData messageData) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("read", Integer.valueOf((int)messageData.mIsRead));
        contentValues.put("seen", Integer.valueOf((int)0));
        contentValues.put("date", Long.valueOf((long)messageData.mReceivedTime));
        contentValues.put("mx_type", Integer.valueOf((int)messageData.getType()));
        HashMap hashMap = new HashMap();
        hashMap.put("body", messageData.mBody);
        if (messageData.mIsTemplate) {
            hashMap.put("template", "");
        }
        contentValues.put("mx_extension", HmsPersister.putToMxExtension(messageData.mExt, hashMap));
        contentValues.put("snippet", HmsPersister.generateSnippet(messageData.mExt));
        contentValues.put("address", messageData.mFrom);
        contentValues.put("advanced_seen", Integer.valueOf((int)1));
        contentValues.put("mx_content", HmsPersister.generateMxContent(messageData.mExt));
        contentValues.put("type", Integer.valueOf((int)1));
        contentValues.put("mx_seq", Long.valueOf((String)messageData.mSeq));
        long l = Telephony.Threads.getOrCreateThreadId((Context)context, (String)messageData.mFrom);
        if (l > 0) {
            contentValues.put("thread_id", Long.valueOf((long)l));
            return contentValues;
        }
        Log.w("HmsPersister", "this is a bad message , because this message data address " + messageData.mFrom + " do not have address like xxxxx@xiaomi.com");
        return contentValues;
    }

    private void enqueueAckMessage(boolean bl, boolean bl2, Message message) {
        if (!bl) {
            AckData ackData = new AckData();
            ackData.toAccountSmtp = message.getFrom();
            ackData.extId = message.getPacketID();
            ackData.ext = "received";
            ackData.seq = message.getSeq();
            ackData.isGlobal = bl2;
            this.mAckDataQueue.add((Object)ackData);
        }
    }

    public static String generateMxContent(String object) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty((CharSequence)object) && (object = HmsExtensionData.get((String)object)) != null && object.getHmsMessageEntry() != null) {
            stringBuilder.append(object.getHmsMessageEntry().content);
        }
        object = null;
        if (!TextUtils.isEmpty((CharSequence)stringBuilder.toString())) {
            object = stringBuilder.toString().replaceAll("<[^>]*>", "");
        }
        return object;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static long generateNewId(Context object) {
        Object object2 = mLock;
        // MONITORENTER : object2
        long l = sCurrentMessageId;
        boolean bl = sCurrentMessageId < 0;
        // MONITOREXIT : object2
        if (bl) {
            l = HmsPersister.initIdGenerator((Context)object);
        }
        object = mLock;
        // MONITORENTER : object
        if (sCurrentMessageId < 0) {
            sCurrentMessageId = l;
        }
        l = sCurrentMessageId;
        sCurrentMessageId = 1 + l;
        // MONITOREXIT : object
        return l;
    }

    public static String generateSnippet(String string2) {
        String string3;
        String string4 = "";
        String string5 = string3 = "";
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            HmsExtensionData hmsExtensionData = HmsExtensionData.get(string2);
            string2 = string4;
            if (hmsExtensionData != null) {
                string2 = string4;
                if (hmsExtensionData.getHmsMessageEntry() != null) {
                    string2 = string5 = hmsExtensionData.getHmsMessageEntry().title;
                    if (TextUtils.isEmpty((CharSequence)string5)) {
                        string2 = hmsExtensionData.getHmsMessageEntry().content;
                    }
                }
            }
            string5 = string3;
            if (!TextUtils.isEmpty((CharSequence)string2)) {
                string5 = string2.replaceAll("<[^>]*>", "");
            }
        }
        return string5;
    }

    public static Context getContext() {
        return mAppContext;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static HmsConversationInfo getHmsConversationInfo(Context object, String string2) {
        Cursor cursor = HmsPersister.getNewestHmsConversationInfo((Context)object, string2);
        object = cursor != null && cursor.moveToFirst() && !TextUtils.isEmpty((CharSequence)(object = cursor.getString(cursor.getColumnIndex("mx_seq")))) ? new HmsConversationInfo((String)object) : null;
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        if (Log.isDebug()) {
            long l = object != null ? object.getReadSeq() : 0;
            Log.d("HmsPersister", "get hms conversation info with miid : " + string2 + " seq : " + l);
        }
        return object;
    }

    private static HmsConversationInfo getHmsConversationInfo(String string2) {
        return HmsPersister.getHmsConversationInfo(HmsPersister.getContext(), string2);
    }

    public static HmsPersister getInstance() {
        return HmsPersister.getInstance(mAppContext);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static HmsPersister getInstance(Context context) {
        if (sInstance == null) {
            synchronized (HmsPersister.class) {
                if (sInstance == null) {
                    sInstance = new HmsPersister(context);
                }
            }
        }
        return sInstance;
    }

    private static long getMaxMessageId(Context context) {
        block6 : {
            long l;
            block7 : {
                Context context2 = null;
                try {
                    context = context.getContentResolver().query(ExtraTelephony.Hms.CONTENT_URI, new String[]{"_id"}, null, null, " _id DESC  LIMIT 1");
                    if (context == null) break block6;
                    context2 = context;
                }
                catch (Throwable var0_1) {
                    if (context2 != null) {
                        context2.close();
                    }
                    throw var0_1;
                }
                if (!context.moveToFirst()) break block6;
                context2 = context;
                l = context.getLong(0);
                if (context == null) break block7;
                context.close();
            }
            return l;
        }
        if (context != null) {
            context.close();
        }
        return 0;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Cursor getNewestHmsConversationInfo(Context object, String string2) {
        Object var4_3 = null;
        ContentResolver contentResolver = object.getContentResolver();
        string2 = contentResolver.query(Uri.parse((String)("content://hms/address/" + URLEncoder.encode((String)string2))), new String[]{"thread_id"}, null, null, "date DESC limit 1");
        object = var4_3;
        if (string2 == null) return object;
        object = var4_3;
        if (!string2.moveToFirst()) return object;
        long l = string2.getLong(string2.getColumnIndex("thread_id"));
        object = var4_3;
        if (l <= 0) return object;
        object = "_id = " + l;
        object = contentResolver.query(Uri.withAppendedPath((Uri)Telephony.MmsSms.CONTENT_URI, (String)"conversations").buildUpon().appendQueryParameter("simple", "true").build(), new String[]{"mx_seq"}, (String)object, null, null);
        return object;
        finally {
            if (string2 != null && !string2.isClosed()) {
                string2.close();
            }
        }
    }

    private void handleHmsAckReceived(Message object) {
        object = object.getExtension("received").getAttributeValue("id");
        String string2 = "mx_message_id = " + (String)object;
        HmsMessageLogicHelper.updateHmsTypeByMessageId(HmsPersister.getContext(), (String)object, 2);
        HmsMessageLogicHelper.moveHmsToFolder(HmsPersister.getContext(), 2, string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void handleHmsXmppReceived(MessageData messageData) {
        Log.d("HmsPersister", "handle received msg count 1");
        Object object = this.addMessageToUri(messageData);
        StringBuilder stringBuilder = new StringBuilder().append("handle insert to table and return row : ");
        object = object != null ? object.toSafeString() : "";
        Log.d("HmsPersister", stringBuilder.append((String)object).toString());
        object = new HmsConversationInfo();
        object.setReadSeq(Long.valueOf((String)messageData.mSeq));
        long l = Telephony.Threads.getOrCreateThreadId((Context)mAppContext, (String)messageData.mFrom);
        if (l >= 0) {
            this.updateSeq((HmsConversationInfo)object, "_id = " + l, null);
        }
        this.putToCache(messageData.mFrom, (HmsConversationInfo)object);
        this.sendAckMessage();
        object = new Intent("com.xiaomi.mms.action_hms_notification");
        object.putExtra("extra_hms_notification_address", messageData.mFrom);
        object.putExtra("extra_hms_notification_body", messageData.mBody);
        object.setPackage(HmsPersister.getContext().getPackageName());
        HmsPersister.getContext().startService((Intent)object);
    }

    private static long initIdGenerator(Context context) {
        long l = System.currentTimeMillis();
        long l2 = HmsPersister.getMaxMessageId(context);
        long l3 = l;
        if (l2 > l) {
            l3 = l2 + 1;
        }
        Log.i("HmsPersister", "the sId is initialized to be " + l3);
        return l3;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean isAlreadyReceived(Message object) {
        boolean bl = false;
        String string2 = object.getFrom();
        if (this.get(string2) == null) return false;
        if (mAppContext == null) {
            return false;
        }
        ContentResolver contentResolver = mAppContext.getContentResolver();
        String string3 = "address" + "=? AND " + "mx_seq" + "=?";
        object = object.getSeq();
        String[] arrstring = new String[]{string2, object};
        object = contentResolver.query(ExtraTelephony.Hms.CONTENT_URI, new String[]{"mx_seq"}, string3, arrstring, null);
        if (object != null) {
            int n = object.getCount();
            bl = n > 0;
        }
        return bl;
        finally {
            object.close();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean isValidSeq(String string2) {
        boolean bl = false;
        if (TextUtils.isEmpty((CharSequence)string2)) {
            Log.d("HmsPersister", "the message is invalid because the seq is null");
            return false;
        }
        long l = -1;
        try {
            long l2;
            l = l2 = Long.parseLong((String)string2);
            bl = true;
        }
        catch (NumberFormatException var7_5) {
            Log.e("HmsPersister", "error seq :" + string2);
        }
        if (l >= 0) return bl;
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static String putToMxExtension(String string2, Map<String, Object> object) {
        if (TextUtils.isEmpty((CharSequence)string2)) return string2;
        try {
            JSONObject jSONObject = new JSONObject(string2);
            object = object.entrySet().iterator();
            while (object.hasNext()) {
                Map.Entry entry = (Map.Entry)object.next();
                jSONObject.put((String)entry.getKey(), entry.getValue());
            }
            return jSONObject.toString();
        }
        catch (JSONException var1_2) {
            Log.e("HmsPersister", "failed to put some fields to mxExtension");
        }
        return string2;
    }

    private void sendAckMessage() {
        AckData ackData;
        while ((ackData = (AckData)this.mAckDataQueue.poll()) != null) {
            HmsChannelService.sendAckMessage(mAppContext, ackData.toAccountSmtp, ackData.ext, ackData.extId, ackData.seq, ackData.isGlobal);
        }
    }

    public Uri addMessageToUri(MessageData messageData) {
        ContentResolver contentResolver = mAppContext.getContentResolver();
        messageData = HmsPersister.convertToContentValues(mAppContext, messageData);
        return contentResolver.insert(ExtraTelephony.Hms.CONTENT_URI, (ContentValues)messageData);
    }

    /*
     * Enabled aggressive block sorting
     */
    public HmsConversationInfo get(String string2) {
        HmsConversationInfo hmsConversationInfo = HmsConversationInfoCache.INSTANCE.get(string2);
        long l = hmsConversationInfo != null ? hmsConversationInfo.getReadSeq() : -1;
        Log.d("HmsPersister", "get local seq with miid : " + string2 + "  seq : " + l);
        return hmsConversationInfo;
    }

    public MessageData process(Message message) {
        String string2 = message.getFrom();
        if (TextUtils.isEmpty((CharSequence)string2)) {
            Log.e("HmsPersister", String.format((String)"received a message sent by someone in my black list, from=%1$s, id=%2$s", (Object[])new Object[]{string2, message.getFrom()}));
            return null;
        }
        Log.w("HmsPersister", String.format((String)"received message from %1$s  to %2$s, id = %3$s, type = %4$s", (Object[])new Object[]{string2, message.getTo(), message.getPacketID(), message.getType()}));
        return HmsXMPPProcessor.getInstance(mAppContext).processHmsMessage(message);
    }

    public void process(Vector<Message> object) {
        this.mMessageReceivedLinkedQueue.addAll(object);
        while ((object = (Message)this.mMessageReceivedLinkedQueue.poll()) != null) {
            if (object.getType().equalsIgnoreCase("ack")) {
                this.handleHmsAckReceived((Message)object);
                continue;
            }
            boolean bl = MipubMessageUtils.isTransient((Message)object);
            boolean bl2 = MipubMessageUtils.isGlobal((Message)object);
            if (!this.isValidSeq(object.getSeq())) {
                Log.d("HmsPersister", "this message seq is invalid ,drop this message! the seq is " + object.getSeq());
                this.enqueueAckMessage(bl, bl2, (Message)object);
                this.sendAckMessage();
                continue;
            }
            if (this.isAlreadyReceived((Message)object)) {
                Log.d("HmsPersister", "this message already received! the seq is " + object.getSeq());
                this.enqueueAckMessage(bl, bl2, (Message)object);
                this.sendAckMessage();
                continue;
            }
            MessageData messageData = this.process((Message)object);
            if (messageData != null) {
                this.enqueueAckMessage(bl, bl2, (Message)object);
            }
            if (messageData == null) continue;
            this.handleHmsXmppReceived(messageData);
        }
    }

    public void putToCache(String string2, HmsConversationInfo hmsConversationInfo) {
        HmsConversationInfoCache.INSTANCE.put(string2, hmsConversationInfo);
    }

    public int updateSeq(HmsConversationInfo hmsConversationInfo, String string2, String[] arrstring) {
        ContentResolver contentResolver = mAppContext.getContentResolver();
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("mx_seq", hmsConversationInfo.toJson());
        return contentResolver.update(Telephony.Threads.CONTENT_URI, contentValues, string2, arrstring);
    }

    public static class AckData {
        public boolean deliverRequired = false;
        public String ext;
        public String extId;
        public boolean isGlobal = false;
        public String seq;
        public String toAccountSmtp;
    }

    private static enum HmsConversationInfoCache {
        INSTANCE;
        
        private LruCache<String, HmsConversationInfo> mLruCache = new LruCache(40);

        private HmsConversationInfoCache() {
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public HmsConversationInfo get(String string2) {
            HmsConversationInfo hmsConversationInfo;
            if (TextUtils.isEmpty((CharSequence)string2)) {
                return null;
            }
            HmsConversationInfo hmsConversationInfo2 = hmsConversationInfo = (HmsConversationInfo)this.mLruCache.get((Object)string2);
            if (hmsConversationInfo != null) return hmsConversationInfo2;
            hmsConversationInfo2 = hmsConversationInfo = HmsPersister.getHmsConversationInfo(string2);
            if (hmsConversationInfo == null) return hmsConversationInfo2;
            this.mLruCache.put((Object)string2, (Object)hmsConversationInfo);
            return hmsConversationInfo;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void put(String string2, HmsConversationInfo hmsConversationInfo) {
            if (!TextUtils.isEmpty((CharSequence)string2) && hmsConversationInfo != null) {
                HmsConversationInfoCache hmsConversationInfoCache = INSTANCE;
                synchronized (hmsConversationInfoCache) {
                    this.mLruCache.put((Object)string2, (Object)hmsConversationInfo);
                    return;
                }
            }
        }
    }

}

