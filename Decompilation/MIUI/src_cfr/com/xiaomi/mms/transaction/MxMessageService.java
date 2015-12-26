/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.database.Cursor
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.Bundle
 *  android.preference.PreferenceManager
 *  android.provider.Telephony
 *  android.provider.Telephony$MmsSms
 *  android.provider.Telephony$Sms
 *  android.provider.Telephony$Sms$Inbox
 *  android.provider.Telephony$Threads
 *  android.text.TextUtils
 *  com.xiaomi.accountsdk.activate.ActivateManager
 *  com.xiaomi.accountsdk.activate.ActivateManager$ActivateManagerFuture
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  miui.provider.ExtraTelephony
 *  miui.push.PushAttributes
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.xiaomi.mms.transaction;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Telephony;
import android.text.TextUtils;
import com.android.mms.data.Contact;
import com.android.mms.transaction.DownloadMxV2FileService;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.DownloadManager;
import com.android.mms.util.MSimUtils;
import com.miui.gallery.lib.MiuiGalleryUtils;
import com.xiaomi.accountsdk.activate.ActivateManager;
import com.xiaomi.mms.data.MidPhoneMap;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.data.RecentMessageCache;
import com.xiaomi.mms.transaction.Mx2MmsTransactionService;
import com.xiaomi.mms.transaction.MxMmsTransactionService;
import com.xiaomi.mms.transaction.MxTaskService;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.transaction.WakenService;
import com.xiaomi.mms.utils.B2cMessageUtils;
import com.xiaomi.mms.utils.MxCapabilityText;
import com.xiaomi.mms.utils.MxMessageLogicHelper;
import com.xiaomi.mms.utils.MxMessagePduHelper;
import com.xiaomi.mms.utils.logger.MyLog;
import java.util.Iterator;
import miui.provider.ExtraTelephony;
import miui.push.PushAttributes;
import org.json.JSONException;
import org.json.JSONObject;

public class MxMessageService
extends WakenService {
    private static final String[] ALL_MESSAGE_TYPE_CONDITION = new String[]{"_id", "type", "msg_box"};

    public static long adjustExpiredTime(long l) {
        long l2 = System.currentTimeMillis();
        long l3 = l;
        if (l < l2 - 1827387392) {
            l3 = l;
            if (l * 1000 < 1827387392 + l2) {
                l3 = l * 1000;
            }
        }
        return l3;
    }

    public static int getMxMmsCount(Context context) {
        return PreferenceManager.getDefaultSharedPreferences((Context)context).getInt("pref_mx_mms_count", 0);
    }

    public static int getMxSmsCount(Context context) {
        return PreferenceManager.getDefaultSharedPreferences((Context)context).getInt("pref_mx_sms_count", 0);
    }

    private static Uri getSmsMessageUriFromMxId(ContentResolver contentResolver, long l) {
        String string2;
        block4 : {
            Object var4_3 = null;
            Object var3_4 = null;
            Uri uri = Uri.parse((String)"content://sms");
            string2 = "mx_id=" + (Object)l;
            string2 = contentResolver.query(uri, new String[]{"_id"}, string2, null, null);
            contentResolver = var4_3;
            if (string2 != null) {
                contentResolver = var3_4;
                if (!string2.moveToFirst()) break block4;
                contentResolver = ContentUris.withAppendedId((Uri)uri, (long)string2.getLong(0));
            }
        }
        return contentResolver;
        finally {
            string2.close();
        }
    }

    private int guessSlotIdByMid(String string2) {
        int n;
        int n2 = MSimUtils.getMultiSimCount();
        for (n = 0; n < n2; ++n) {
            if (MSimUtils.getSimId((Context)this, n) == null) continue;
            ActivateManager.ActivateManagerFuture activateManagerFuture = ActivateManager.get((Context)this).getActivateInfo(n);
            try {
                boolean bl = string2.equals((Object)((Bundle)activateManagerFuture.getResult()).getString("activate_sim_user_id"));
                if (!bl) continue;
                return n;
            }
            catch (Exception var5_6) {
                MyLog.e("MxMessageService", "failed to get sim user for sim " + n, var5_6);
            }
        }
        n = MSimUtils.getInsertedSlotId();
        if (n != -1) {
            return n;
        }
        MyLog.w("MxMessageService", "no sim is active now, save msg to slot 0");
        return 0;
    }

    private void increaseMxMmsCount() {
        int n = PreferenceManager.getDefaultSharedPreferences((Context)this.getApplicationContext()).getInt("pref_mx_mms_count", 0);
        PreferenceManager.getDefaultSharedPreferences((Context)this.getApplicationContext()).edit().putInt("pref_mx_mms_count", n + 1).commit();
    }

    private void increaseMxSmsCount() {
        int n = PreferenceManager.getDefaultSharedPreferences((Context)this.getApplicationContext()).getInt("pref_mx_sms_count", 0);
        PreferenceManager.getDefaultSharedPreferences((Context)this.getApplicationContext()).edit().putInt("pref_mx_sms_count", n + 1).commit();
    }

    public static void resetMxCount(Context context) {
        PreferenceManager.getDefaultSharedPreferences((Context)context).edit().putInt("pref_mx_sms_count", 0).commit();
        PreferenceManager.getDefaultSharedPreferences((Context)context).edit().putInt("pref_mx_mms_count", 0).commit();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean shouldAbandonReceivedMessage(int n, String object, String string2) {
        boolean bl;
        block14 : {
            block13 : {
                if (!TextUtils.isEmpty((CharSequence)object)) {
                    if (1 != DownloadManager.getInstance().getServiceStateForSlotId(n)) return false;
                    MyLog.w("MxMessageService", "sim card is out of service,discard received b2c message for simIndex " + n);
                    return true;
                }
                if (!PreferenceManager.getDefaultSharedPreferences((Context)this.getApplicationContext()).getBoolean("pref_key_mx_filter_message_from_stranger", false)) return false;
                object = Contact.get(string2);
                if (object.existsInDatabase()) {
                    return false;
                }
                object.load(true, true);
                if (object.existsInDatabase()) {
                    return false;
                }
                string2 = Telephony.MmsSms.CONTENT_FILTER_BYPHONE_URI.buildUpon().appendPath(string2).build();
                object = null;
                try {
                    string2 = this.getApplicationContext().getContentResolver().query((Uri)string2, ALL_MESSAGE_TYPE_CONDITION, null, null, null);
                    if (string2 == null) break block13;
                    object = string2;
                    if (!string2.moveToFirst()) break block13;
                    do {
                        object = string2;
                        if (string2.getType(1) != 0) {
                            object = string2;
                            n = string2.getInt(1);
                            if (n == 2 || n == 4 || n == 5 || n == 6) {
                                object = string2;
                                MyLog.d("MxMessageService", "has send sms to sender");
                                boolean bl2 = false;
                                bl = false;
                                if (string2 == null) return bl;
                                bl = bl2;
                                break block14;
                            }
                        } else {
                            object = string2;
                            if (string2.getType(2) != 0) {
                                object = string2;
                                n = string2.getInt(2);
                                if (n == 4 || n == 2) {
                                    object = string2;
                                    MyLog.d("MxMessageService", "has send mms to sender");
                                    boolean bl3 = false;
                                    bl = false;
                                    if (string2 == null) return bl;
                                    bl = bl3;
                                    break block14;
                                }
                            }
                        }
                        object = string2;
                    } while (bl = string2.moveToNext());
                }
                catch (Throwable var3_4) {
                    if (object == null) throw var3_4;
                    object.close();
                    throw var3_4;
                }
            }
            boolean bl4 = true;
            bl = true;
            if (string2 == null) return bl;
            bl = bl4;
        }
        string2.close();
        return bl;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void storeSms(Context var1_1, String var2_3, String var3_4, Long var4_5, long var5_6, int var7_7, long var8_8, int var10_9, String var11_10, Long var12_11) {
        if (TextUtils.isEmpty((CharSequence)var2_3) || TextUtils.isEmpty((CharSequence)var3_4) || var4_5 == null) {
            MyLog.e("MxMessageService", "from or pdu not nullable");
            return;
        }
        var16_12 = SqliteWrapper.query((Context)var1_1, (ContentResolver)var1_1.getContentResolver(), (Uri)Telephony.Sms.Inbox.CONTENT_URI, (String[])null, (String)("mx_id=" + var5_6), (String[])null, (String)null);
        var13_13 = 0;
        if (var16_12 != null) {
            var13_13 = var16_12.getCount();
            var13_13 = var13_13 > 0 ? 1 : 0;
            if (var13_13 != 0) return;
        }
        var14_14 = System.currentTimeMillis();
        var16_12 = new ContentValues();
        var16_12.put("address", var2_3);
        var16_12.put("date", Long.valueOf((long)var14_14));
        var16_12.put("date_sent", var4_5);
        var16_12.put("protocol", Integer.valueOf((int)0));
        var16_12.put("seen", Integer.valueOf((int)0));
        if (MessageUtils.isServiceNumber(var1_1, var2_3)) {
            var16_12.put("advanced_seen", Integer.valueOf((int)1));
        } else {
            var16_12.put("advanced_seen", Integer.valueOf((int)0));
        }
        var16_12.put("reply_path_present", Integer.valueOf((int)0));
        var16_12.put("service_center", Integer.valueOf((int)0));
        var16_12.put("error_code", Integer.valueOf((int)0));
        var16_12.put("body", var3_4);
        var16_12.put("thread_id", Long.valueOf((long)Telephony.Threads.getOrCreateThreadId((Context)var1_1, (String)var2_3)));
        var16_12.put("mx_id", Long.valueOf((long)var5_6));
        var16_12.put("mx_status", Integer.valueOf((int)65537));
        var16_12.put("sim_id", Long.valueOf((long)var8_8));
        var16_12.put("block_type", Integer.valueOf((int)var10_9));
        if (var10_9 < 3 && (MiuiGalleryUtils.handleAsAlbumShareInvitation(var1_1, null, var3_4, "mms") || MessageUtils.handleFileShareMessage(var1_1, var2_3, var3_4))) {
            var16_12.put("block_type", Integer.valueOf((int)0));
            var16_12.put("read", Integer.valueOf((int)1));
            MessageUtils.insertUniqueMessage(var1_1, (ContentValues)var16_12);
            return;
        }
        ** GOTO lbl40
        finally {
            var16_12.close();
        }
lbl40: // 1 sources:
        var16_12.put("read", Integer.valueOf((int)0));
        if (!TextUtils.isEmpty((CharSequence)var11_10)) {
            var16_12.put("b2c_numbers", var11_10);
            if (var12_11 != null) {
                var16_12.put("b2c_ttl", var12_11);
            }
        }
        if (MessageUtils.insertUniqueMessage(var1_1, (ContentValues)var16_12) == null) return;
        if (var10_9 > 1) return;
        MessagingNotification.blockingUpdateNewMessageIndicator((Context)this, true, false);
        if (var10_9 == 1) return;
        MessageUtils.processReceivedMsgReport((Context)this, var2_3, var3_4, var7_7);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    protected void handleIntent(Intent object) {
        int n;
        String string2 = object.getAction();
        Object object2 = object.getStringExtra("from");
        Object object3 = object.getStringExtra("to");
        String string3 = object.getStringExtra("packetId");
        Object object4 = MxMessageLogicHelper.getSimpleMid((String)object3);
        Object object5 = PushSession.getInstance((Context)this);
        int n2 = n = object5.getSimIdByMid((String)object4);
        if (n < 0) {
            n2 = this.guessSlotIdByMid((String)object4);
        }
        object4 = object5 = object5.getMyFullMid(n2);
        if (TextUtils.isEmpty((CharSequence)object5)) {
            object4 = object3;
        }
        long l = MSimUtils.getSimIdBySlotId(n2);
        String string4 = MxMessageLogicHelper.getSimpleMid((String)object2);
        if ("com.xiaomi.mms.mx.ACTION_HANDLE_MX_RECEIVED".equals((Object)string2)) {
            String string5;
            String string6 = object.getStringExtra("b2c_number");
            if (this.shouldAbandonReceivedMessage(n2, string6, string5 = object.getStringExtra("address"))) return;
            if (RecentMessageCache.contain(string3)) {
                MxMessageLogicHelper.sendReceivedAck((Context)this, (String)object4, (String)object2, string3);
                return;
            }
            object5 = object3 = object.getStringExtra("body");
            if ("base64".equals((Object)object.getStringExtra("encoding"))) {
                object5 = MxMessageLogicHelper.base64Decode((String)object3);
            }
            String string7 = object.getStringExtra("fromType");
            object3 = string2 = null;
            try {
                if (!TextUtils.isEmpty((CharSequence)object5)) {
                    object3 = new JSONObject((String)object5);
                }
            }
            catch (JSONException var18_6) {
                MyLog.e("MxMessageService", "receive message with malformed body", (Throwable)var18_6);
                object3 = string2;
            }
            if (object3 == null) {
                MxMessageLogicHelper.sendReceivedAck((Context)this, (String)object4, (String)object2, string3, "malformed-body", "json exception when process msg body");
                MyLog.e("MxMessageService", "receive mi message without body");
            } else {
                object5 = object3.optString("type");
                Long l2 = object3.optLong("sentTime");
                long l3 = object3.optLong("msgId");
                int n3 = object3.optInt("mxType", 0);
                if (TextUtils.isEmpty((CharSequence)object5) || l3 == 0) {
                    MxMessageLogicHelper.sendReceivedAck((Context)this, (String)object4, (String)object2, string3, "malformed-body", "missing type or msgId");
                    MyLog.w("MxMessageService", "missing type or msgId");
                } else if ("sms".equals(object5)) {
                    int n4;
                    String string8 = object3.optString("pdu");
                    n = n4 = 0;
                    if (string5 != null) {
                        n = n4;
                        if (string8 != null) {
                            n = n4;
                            if (!B2cMessageUtils.isB2cNumber(string5)) {
                                n = ExtraTelephony.getSmsBlockType((Context)this, (String)string5, (String)string8, (int)n2);
                            }
                        }
                    }
                    if ("phone".equals((Object)string7)) {
                        MxMessageLogicHelper.sendReceivedAck((Context)this, (String)object4, (String)object2, string3);
                        object2 = Contact.get(string5);
                        string7 = object2.getMxPhoneNumber();
                        MxIdCache.put(string7, string4);
                        MidPhoneMap.put(string4, string7);
                        if (MxIdCache.online(string7, 1, true)) {
                            MxTaskService.queryStatus((Context)this, string7);
                        }
                        string2 = null;
                        object3 = null;
                        object4 = string2;
                        if (!TextUtils.isEmpty((CharSequence)string6)) {
                            object5 = object.getStringExtra("b2c_display_name");
                            object = object.getStringExtra("b2c_ttl");
                            object4 = string2;
                            object3 = object5;
                            if (!TextUtils.isEmpty((CharSequence)object)) {
                                long l4;
                                try {
                                    l4 = Long.valueOf((String)object);
                                }
                                catch (NumberFormatException var1_2) {
                                    object4 = null;
                                    object3 = object5;
                                }
                                object4 = 1000 * l4;
                                object3 = object5;
                            }
                        }
                        if (!TextUtils.isEmpty((CharSequence)object3)) {
                            boolean bl = TextUtils.isEmpty((CharSequence)object2.getRealName());
                            n4 = !TextUtils.isEmpty((CharSequence)object2.getYellowPageThumbnailName()) ? 1 : 0;
                            if (bl) {
                                object2.setName((String)object3);
                                object2.setIsB2cNumber(true);
                            }
                            if (bl || n4 != 0) {
                                B2cMessageUtils.insertOrUpdateB2cAddress(this.getApplicationContext(), string7, (String)object3);
                            }
                        }
                        this.storeSms((Context)this, string5, string8, l2, l3, n2, l, n, string6, (Long)object4);
                    } else {
                        MxMessageLogicHelper.sendReceivedAck((Context)this, (String)object4, (String)object2, string3, "unsupported-source", "unknown source type: " + string7);
                    }
                } else if ("mms".equals(object5)) {
                    object3 = object3.optString("subject");
                    string6 = object.getBundleExtra("attachment");
                    if (string6 != null) {
                        object = string6.getString("mimeType");
                        object5 = string6.getString("shareId");
                        string2 = string6.getString("expireAt");
                        string6 = string6.getString("msgSize");
                        if (!TextUtils.isEmpty((CharSequence)object) && !TextUtils.isEmpty((CharSequence)object5) && TextUtils.isDigitsOnly((CharSequence)string2) && TextUtils.isDigitsOnly((CharSequence)string6)) {
                            int n5;
                            long l5 = MxMessageService.adjustExpiredTime(Long.valueOf((String)string2));
                            long l6 = Long.valueOf((String)string6);
                            string2 = Contact.get(string5).getMxPhoneNumber();
                            MxIdCache.put(string2, string4);
                            MidPhoneMap.put(string4, string2);
                            if (MxIdCache.online(string2, 3, true)) {
                                MxTaskService.queryStatus((Context)this, string2);
                            }
                            n = n5 = 0;
                            if (string5 != null) {
                                n = n5;
                                if (!B2cMessageUtils.isB2cNumber(string5)) {
                                    n = ExtraTelephony.getSmsBlockType((Context)this, (String)string5, (String)null, (int)n2);
                                }
                            }
                            if ("phone".equals((Object)string7)) {
                                MxMessageLogicHelper.sendReceivedAck((Context)this, (String)object4, (String)object2, string3);
                                object = MxMessagePduHelper.persistNotification((Context)this, string5, System.currentTimeMillis(), l2, l5, (String)object3, (String)object, l6, (String)object5, l3, l, n, 0, null);
                                object4 = DownloadManager.getInstance();
                                boolean bl = object4.isAuto(l);
                                if (n > 1) {
                                    bl = false;
                                }
                                n2 = bl ? 129 : 128;
                                object4.markState((Uri)object, n2, l);
                                if (bl) {
                                    MxMmsTransactionService.startRetrieveMms((Context)this, (Uri)object);
                                } else {
                                    MessagingNotification.blockingUpdateNewMessageIndicator((Context)this, true, false);
                                }
                            } else {
                                MxMessageLogicHelper.sendReceivedAck((Context)this, (String)object4, (String)object2, string3, "unsupported-source", "unknown source type: " + string7);
                            }
                        } else {
                            MxMessageLogicHelper.sendReceivedAck((Context)this, (String)object4, (String)object2, string3, "malformed-body", "malformed attachment");
                            MyLog.w("MxMessageService", "malformed attachment");
                        }
                    } else {
                        MxMessageLogicHelper.sendReceivedAck((Context)this, (String)object4, (String)object2, string3, "malformed-body", "missing attachment");
                    }
                } else if ("mx2".equals(object5)) {
                    object5 = object3.optString("subject");
                    Object object6 = object.getBundleExtra("attachment");
                    if (object6 != null) {
                        string2 = object6.getString("mimeType");
                        String string9 = object6.getString("publicUrl");
                        string6 = object6.getString("mxExtension");
                        object = object3 = null;
                        if (!TextUtils.isEmpty((CharSequence)string9)) {
                            String string10 = object6.getString("fileSha1");
                            object = object3;
                            if (!TextUtils.isEmpty((CharSequence)string10)) {
                                object = string9 + " " + string10;
                            }
                        }
                        object3 = object;
                        if (TextUtils.isEmpty((CharSequence)object)) {
                            object3 = object6.getString("shareId");
                        }
                        object = object6.getString("expireAt");
                        object6 = object6.getString("msgSize");
                        if (!TextUtils.isEmpty((CharSequence)string2) && !TextUtils.isEmpty((CharSequence)object3) && TextUtils.isDigitsOnly((CharSequence)object) && TextUtils.isDigitsOnly((CharSequence)object6)) {
                            int n6;
                            long l7 = MxMessageService.adjustExpiredTime(Long.valueOf((String)object));
                            long l8 = Long.valueOf((String)object6);
                            object = Contact.get(string5).getMxPhoneNumber();
                            MxIdCache.put((String)object, string4);
                            MidPhoneMap.put(string4, (String)object);
                            if (MxIdCache.online((String)object, 0x300000003L, true)) {
                                MxTaskService.queryStatus((Context)this, (String)object);
                            }
                            n = n6 = 0;
                            if (string5 != null) {
                                n = n6;
                                if (!B2cMessageUtils.isB2cNumber(string5)) {
                                    n = ExtraTelephony.getSmsBlockType((Context)this, (String)string5, (String)null, (int)n2);
                                }
                            }
                            if ("phone".equals((Object)string7)) {
                                MxMessageLogicHelper.sendReceivedAck((Context)this, (String)object4, (String)object2, string3);
                                object = MxMessagePduHelper.persistNotification((Context)this, string5, System.currentTimeMillis(), l2, l7, (String)object5, string2, l8, (String)object3, l3, l, n, n3, string6);
                                Mx2MmsTransactionService.startRetrieveMx2((Context)this, (Uri)object);
                                if (n3 == 3) {
                                    DownloadMxV2FileService.startDownloadAudio(this.getApplicationContext(), (String)object3, n2, (Uri)object, true);
                                }
                                MessagingNotification.blockingUpdateNewMessageIndicator((Context)this, true, false);
                            } else {
                                MxMessageLogicHelper.sendReceivedAck((Context)this, (String)object4, (String)object2, string3, "unsupported-source", "unknown source type: " + string7);
                            }
                        } else {
                            MxMessageLogicHelper.sendReceivedAck((Context)this, (String)object4, (String)object2, string3, "malformed-body", "malformed attachment");
                        }
                    } else {
                        MxMessageLogicHelper.sendReceivedAck((Context)this, (String)object4, (String)object2, string3, "malformed-body", "missing attachment");
                    }
                } else {
                    MxMessageLogicHelper.sendReceivedAck((Context)this, (String)object4, (String)object2, string3, "unsupported-type", "unsupported msg type: " + (String)object5);
                }
            }
            RecentMessageCache.add(string3);
            return;
        }
        if ("com.xiaomi.mms.mx.ACTION_HANDLE_MX_SENT".equals((Object)string2)) {
            l = object.getLongExtra("msgId", -1);
            if (MxMessageLogicHelper.isSms(l)) {
                MessageUtils.updateSmsStatus((Context)this, l, 16);
                return;
            }
            if (!MxMessageLogicHelper.isMms(l)) return;
            {
                MxMessagePduHelper.updateMmsToSent((Context)this, l);
                return;
            }
        }
        if ("com.xiaomi.mms.mx.ACTION_HANDLE_MX_DELIVERED".equals((Object)string2)) {
            MxMessageLogicHelper.sendAckToServer((Context)this, (String)object4, (String)object2, string3);
            if (RecentMessageCache.contain(string3)) return;
            {
                l = object.getLongExtra("msgId", -1);
                boolean bl = MxMessageLogicHelper.isSms(l);
                boolean bl2 = MxMessageLogicHelper.isB2cSms(l);
                boolean bl3 = MxMessageLogicHelper.isMms(l);
                if (object.getBooleanExtra("error", false)) {
                    if (bl) {
                        MessageUtils.handleMxSmsFailed((Context)this, l, n2, bl2);
                    } else if (bl3 && (object = MxMessagePduHelper.getMmsMessageUriFromMxId(this.getContentResolver(), l, 2)) != null) {
                        n2 = MxMessagePduHelper.getMessageMx2Type((Context)this, ContentUris.parseId((Uri)object));
                        MxMessagePduHelper.setResponseStatus((Context)this, (Uri)object, 224);
                        l = ContentUris.parseId((Uri)object);
                        bl = n2 > 0;
                        MxMessagePduHelper.handleMxMmsFailed((Context)this, l, true, bl);
                    }
                } else if (bl) {
                    if (MessageUtils.updateSmsStatus((Context)this, l, 17) > 0) {
                        this.increaseMxSmsCount();
                        object = MxMessageService.getSmsMessageUriFromMxId(this.getContentResolver(), l);
                        if (object != null) {
                            MessagingNotification.nonBlockingUpdateDeliveryInfo(this.getApplicationContext(), (Uri)object);
                        }
                    }
                } else if (bl3 && MxMessagePduHelper.updateMmsToDelivered((Context)this, l) != null) {
                    this.increaseMxMmsCount();
                }
                RecentMessageCache.add(string3);
                return;
            }
        }
        if (!"com.xiaomi.mms.mx.ACTION_HANDLE_PRESENCE".equals((Object)string2)) return;
        object4 = object.getStringExtra("mid");
        boolean bl = object.getBooleanExtra("available", false);
        object = object.getStringExtra("client_attrs");
        long l9 = l = 1;
        if (object != null) {
            object = PushAttributes.parse((String)object).get("cap");
            l9 = l;
            if (!TextUtils.isEmpty((CharSequence)object)) {
                l = 0;
                object = MxCapabilityText.parse((String)object).iterator();
                do {
                    l9 = l;
                    if (!object.hasNext()) break;
                    object3 = (String)object.next();
                    if ("sms".equals(object3)) {
                        l |= 1;
                        continue;
                    }
                    if ("mms".equals(object3)) {
                        l |= 2;
                        continue;
                    }
                    if ("mx2image".equals(object3)) {
                        l |= 0x100000000L;
                        continue;
                    }
                    if ("mx2audio".equals(object3)) {
                        l |= 0x200000000L;
                        continue;
                    }
                    if (!"mxV2mms2".equals(object3)) continue;
                    l |= 0x400000000L;
                } while (true);
            }
        }
        object = MidPhoneMap.get((String)object4);
        if (bl) {
            if (object == null) return;
            {
                MxIdCache.online((String)object, l9, false);
                return;
            }
        }
        if (object == null) {
            return;
        }
        MxIdCache.offline((String)object);
    }
}

