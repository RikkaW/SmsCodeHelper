/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentProviderOperation
 *  android.content.ContentProviderOperation$Builder
 *  android.content.ContentProviderResult
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.OperationApplicationException
 *  android.database.Cursor
 *  android.database.SQLException
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.RemoteException
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Mms$Draft
 *  android.provider.Telephony$Mms$Inbox
 *  android.provider.Telephony$Mms$Outbox
 *  android.provider.Telephony$Mms$Sent
 *  android.text.TextUtils
 *  com.google.android.mms.pdu.EncodedStringValue
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 */
package com.xiaomi.mms.utils;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.Telephony;
import android.text.TextUtils;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.xiaomi.mms.data.Mx2MessageModel;
import com.xiaomi.mms.mx.common.GlobalData;
import com.xiaomi.mms.mx.data.Attachment;
import com.xiaomi.mms.mx.utils.Mx2ExtentionHelper;
import com.xiaomi.mms.utils.logger.MyLog;
import java.util.ArrayList;
import java.util.List;

public class Mx2PduPersister {
    private static final String[] PDU_PROJECTION = new String[]{"_id", "msg_box", "thread_id", "sub", "m_id", "m_type", "date", "date_ms_part", "date_sent", "exp", "sim_id", "locked", "mx_type", "mx_extension", "d_tm"};

    /*
     * Enabled aggressive block sorting
     */
    private static ContentValues MxMessagetoContentValue(Mx2MessageModel mx2MessageModel) {
        ContentValues contentValues = null;
        if (mx2MessageModel != null) {
            contentValues = new ContentValues();
            contentValues.put("thread_id", Long.valueOf((long)mx2MessageModel.getThreadId()));
            contentValues.put("date", Long.valueOf((long)mx2MessageModel.getDate()));
            contentValues.put("date_ms_part", Long.valueOf((long)mx2MessageModel.getDateMsPart()));
            contentValues.put("date_sent", Long.valueOf((long)mx2MessageModel.getDateSent()));
            int n = mx2MessageModel.isLocked() ? 1 : 0;
            contentValues.put("locked", Integer.valueOf((int)n));
            contentValues.put("m_type", Integer.valueOf((int)128));
            contentValues.put("sim_id", Long.valueOf((long)mx2MessageModel.getSimId()));
            contentValues.put("exp", mx2MessageModel.getExpireTimestamp());
            contentValues.put("msg_box", Integer.valueOf((int)mx2MessageModel.getBoxId()));
            contentValues.put("mx_type", mx2MessageModel.getMxType());
            if (!TextUtils.isEmpty((CharSequence)mx2MessageModel.getBody())) {
                contentValues.put("sub", MiuiPduPersister.toIsoString((byte[])new EncodedStringValue(mx2MessageModel.getBody()).getTextString()));
                contentValues.put("sub_cs", Integer.valueOf((int)106));
            }
            contentValues.put("mx_extension", mx2MessageModel.buildLocalExtension());
        }
        return contentValues;
    }

    public static Uri buildUri(int n) {
        Uri uri = Telephony.Mms.CONTENT_URI;
        switch (n) {
            default: {
                return uri;
            }
            case 1: {
                return Telephony.Mms.Inbox.CONTENT_URI;
            }
            case 2: {
                return Telephony.Mms.Sent.CONTENT_URI;
            }
            case 3: {
                return Telephony.Mms.Draft.CONTENT_URI;
            }
            case 4: 
        }
        return Telephony.Mms.Outbox.CONTENT_URI;
    }

    public static Uri insertMxMessage(Context context, Mx2MessageModel arrstring) {
        ContentValues contentValues = null;
        if (arrstring != null) {
            contentValues = Mx2PduPersister.MxMessagetoContentValue((Mx2MessageModel)arrstring);
            contentValues = context = context.getContentResolver().insert(Mx2PduPersister.buildUri(arrstring.getType()), contentValues);
            if (context != null) {
                contentValues = context;
                if (arrstring.mConversation != null) {
                    contentValues = context;
                    if (arrstring.mConversation.getRecipients() != null) {
                        long l = ContentUris.parseId((Uri)context);
                        contentValues = context;
                        if (l > 0) {
                            arrstring = arrstring.mConversation.getRecipients().getNumbers();
                            contentValues = context;
                            if (arrstring != null) {
                                arrstring = EncodedStringValue.encodeStrings((String[])arrstring);
                                Mx2PduPersister.updateAddress(GlobalData.app(), l, 151, (EncodedStringValue[])arrstring);
                                contentValues = context;
                            }
                        }
                    }
                }
            }
        }
        return contentValues;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static Mx2MessageModel loadMessageByMsgId(Context var0, long var1_2) {
        var6_3 = null;
        var7_4 = null;
        var8_5 = null;
        var5_6 = var7_4;
        if (var1_2 <= -1) return var5_6;
        var5_6 = null;
        var3_7 = null;
        var4_9 = var0.getContentResolver();
        var0 = var3_7;
        try {
            var4_9 = var4_9.query(Telephony.Mms.CONTENT_URI.buildUpon().appendQueryParameter("caller_is_syncadapter", "1").build(), Mx2PduPersister.PDU_PROJECTION, "_id=? AND mx_type > 0", new String[]{String.valueOf((long)var1_2)}, null);
            var3_7 = var8_5;
            if (var4_9 != null) {
                var3_7 = var8_5;
                var0 = var4_9;
                var5_6 = var4_9;
                if (var4_9.moveToFirst()) {
                    var0 = var4_9;
                    var5_6 = var4_9;
                    var3_7 = new Mx2MessageModel((Cursor)var4_9);
                }
            }
            var5_6 = var3_7;
            if (var4_9 == null) return var5_6;
            var5_6 = var3_7;
        }
        catch (SQLException var3_8) {
            var5_6 = var0;
            try {
                MyLog.e("Mx2PduPersister.RICH", "loadMessageFromPdu fail");
                var5_6 = var7_4;
                if (var0 == null) return var5_6;
                var5_6 = var7_4;
            }
            catch (Throwable var0_1) {
                if (var5_6 == null) throw var0_1;
                if (var5_6.isClosed() != false) throw var0_1;
                var5_6.close();
                throw var0_1;
            }
            if (var0.isClosed() != false) return var5_6;
            var3_7 = var6_3;
lbl41: // 2 sources:
            var0.close();
            return var3_7;
        }
        if (var4_9.isClosed() != false) return var5_6;
        var0 = var4_9;
        ** GOTO lbl41
    }

    public static Mx2MessageModel loadMessageByUri(Context context, Uri uri) {
        long l = -1;
        if (uri != null) {
            l = ContentUris.parseId((Uri)uri);
        }
        return Mx2PduPersister.loadMessageByMsgId(context, l);
    }

    private static void persistAddress(ArrayList<ContentProviderOperation> arrayList, long l, int n, EncodedStringValue[] arrencodedStringValue) {
        ContentValues contentValues = new ContentValues(4);
        for (EncodedStringValue encodedStringValue : arrencodedStringValue) {
            contentValues.clear();
            contentValues.put("address", MiuiPduPersister.toIsoString((byte[])encodedStringValue.getTextString()));
            contentValues.put("charset", Integer.valueOf((int)encodedStringValue.getCharacterSet()));
            contentValues.put("type", Integer.valueOf((int)n));
            arrayList.add((Object)ContentProviderOperation.newInsert((Uri)Telephony.Mms.CONTENT_URI.buildUpon().appendPath(String.valueOf((long)l)).appendPath("addr").build()).withValues(contentValues).build());
        }
    }

    public static void updateAddress(Context context, long l, int n, EncodedStringValue[] arrencodedStringValue) {
        ArrayList arrayList = new ArrayList();
        arrayList.add((Object)ContentProviderOperation.newDelete((Uri)Telephony.Mms.CONTENT_URI.buildUpon().appendPath(String.valueOf((long)l)).appendPath("addr").build()).withSelection("type=" + n, null).build());
        Mx2PduPersister.persistAddress(arrayList, l, n, arrencodedStringValue);
        try {
            context.getContentResolver().applyBatch("mms", arrayList);
            return;
        }
        catch (OperationApplicationException var0_1) {
            MyLog.e("Mx2PduPersister.RICH", "Error while applying batch", (Throwable)var0_1);
            return;
        }
        catch (RemoteException var0_2) {
            MyLog.e("Mx2PduPersister.RICH", "Error while applying batch", (Throwable)var0_2);
            return;
        }
    }

    public static int updateExtension(Context context, Uri uri, List<Attachment> list) {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("mx_extension", Mx2ExtentionHelper.generateAttachmentsExtentionString(list, false));
        uri = uri.buildUpon().appendQueryParameter("blocked_flag", "2").build();
        return SqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)uri, (ContentValues)contentValues, (String)null, (String[])null);
    }

    public static int updateMxMessage(Context context, Mx2MessageModel mx2MessageModel, long l) {
        int n;
        int n2 = n = 0;
        if (l > 0) {
            n2 = n;
            if (mx2MessageModel != null) {
                mx2MessageModel = Mx2PduPersister.MxMessagetoContentValue(mx2MessageModel);
                n2 = context.getContentResolver().update(Telephony.Mms.CONTENT_URI, (ContentValues)mx2MessageModel, "_id=? ", new String[]{String.valueOf((long)l)});
            }
        }
        return n2;
    }
}

